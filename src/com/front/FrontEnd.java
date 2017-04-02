package com.front;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.cluster.ClusterState;

import com.dao.Dao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents all front end operations 
 * which interact with the search server
 * @author Nicholas Carugati
 * @author Shubham Sharma
 *
 */
public class FrontEnd implements FrontEndAPI {

	/**
	 * The dao instance to connect to the search server
	 */
	private Dao dao;
	
	/**
	 * The list of all the databases linked to the client
	 */
	private Map<String, String> dbPaths = new HashMap<String, String>();
	
	/**
	 * Creates a new front end instance
	 * @param dao the dao instance thats linked to the client
	 * @throws UnknownHostException if the client cannot connect to the
	 * search server
	 */
	public FrontEnd(Dao dao) throws UnknownHostException {
		this.dao = dao;
		preloadIndices();
	}

	/**
	 * Adds a new database source
	 * @param path the path of the offline source
	 */
	public void addDBSource(String path) {
		String name = normalizePath(path);
		if(dbExists(name))
			removeDB(name);
		
	    CreateIndexRequestBuilder builder =
	    		dao.getClient().admin().indices().prepareCreate(name);
	    CreateIndexResponse response = builder.execute().actionGet();
		if (!response.isAcknowledged()) {
			System.out.println("A problem occurred during creation."); //Modal box?
		} else {
			dbPaths.put(name, path);
		}
	}
	
	/**
	 * Determines if the database exists on the search server
	 * @param name the name of the database
	 * @return if the database exists
	 */
	private boolean dbExists(String name) {
		IndicesExistsResponse res = 
				dao.getClient().admin().indices().prepareExists(name).execute().actionGet();
		return res.isExists();
	}
	
	/**
	 * A string manipulation utility 
	 * which converts paths into filenames without extensions
	 * @param the string of the path to convert
	 * @return the name of the file
	 */
	private static String normalizePath(String s) {
		String name = null;
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println(os);
		
		if(os.contains("windows"))
			s = s.replace("\\", "\\\\");
	
		String str[] = os.contains("windows") ? s.split("\\\\") : s.split("/");
		name = str[str.length - 1].split("\\.")[0];
		System.out.println("name is: "+name);
		return name;
	}
	
	/**
	 * Fetches committee data with under a specific database model
	 * @param commNname the path of the committee
	 * @param db the name of the database
	 * @param file if the operation should be treated as a file or directory
	 */
	public void fetchCommittees(String commNname, String db, boolean file) {
		String name = normalizePath(commNname);
		System.out.println(name);
		CommParser cp = new CommParser(file ? commNname : dbPaths.get(name));
		List<ArticleEntry> data = cp.getMemberEntries();
		
		ObjectMapper mapper = new ObjectMapper();
		for(ArticleEntry memEntry : data) {
			try 
			{
				String val = mapper.writeValueAsString(memEntry);
				dao.getClient().prepareIndex(db, "data").setSource(val).get();
			} catch (JsonProcessingException e) {
				System.out.println("Error parsing object instance");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Fetches committee data with under the default database model
	 * @param commNname the path of the committee
	 * @param file if the operation should be treated as a file or directory
	 */
	public void fetchCommittees(String commNname, boolean file)
	{
		String name = normalizePath(commNname);
		System.out.println(name);
		CommParser cp = new CommParser(file ? commNname : dbPaths.get(name));
		List<ArticleEntry> data = cp.getMemberEntries();
		
		ObjectMapper mapper = new ObjectMapper();
		for(ArticleEntry memEntry : data) {
			try 
			{
				String val = mapper.writeValueAsString(memEntry);
				dao.getClient().prepareIndex(name, "data").setSource(val).get();
			} catch (JsonProcessingException e) {
				System.out.println("Error parsing object instance");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method which loads data onto the search server
	 * @param dbName the database where the data should load to
	 */
	public void fetchData(String dbName) 
	{
		String name = null;
		if(dbName.indexOf('/') >= 0) {
			String s[] = dbName.split("/");
			name = s[s.length - 1].split("\\.")[0];
		} else {
			name = dbName;
		}
		System.out.println(name);
		DataSetParser dsp = new DataSetParser(dbPaths.get(name));
		List<ArticleEntry> data = dsp.getEntries();
		
		BulkProcessor bp = new BulkHandler(dao.getClient())
			// BulkProcessing policies
			.setConcurrentRequests(BulkHandler.CONCURRENT_REQUESTS)
			.setBulkActions(BulkHandler.BULK_ACTIONS)
			.setBulkSize(BulkHandler.BULK_SIZE)
			.build();
		
		ObjectMapper mapper = new ObjectMapper();
		for(int i = 0; i < data.size(); i++) {
			try {
				String val = mapper.writeValueAsString(data.get(i));
				IndexRequest request = new IndexRequest(name, "data").source(val);
				bp.add(request);
				bp.flush();
			} catch (JsonProcessingException e) {
				System.out.println("Error parsing object instance");
				e.printStackTrace();
			}
		}
		bp.close();
		updateDatabase();
	}

	/**
	 * Removes a database from the search server
	 * @param dbName the name of the database to remove
	 */
	public void removeDB(String dbName) {
		DeleteIndexResponse response = 
				dao.getClient().admin().indices().delete(new DeleteIndexRequest(dbName)).actionGet();
		if (!response.isAcknowledged()) {
			System.out.println("A problem occurred during removal.");
		} else {
			dbPaths.remove(dbName);
		}
	}

	/**
	 * Re-indexes a database after a add or remove operation
	 */
	public void updateDatabase() {
		dao.getClient().admin().indices().prepareRefresh().get();
	}
	
	/**
	 * Loads all the committee data in the default path
	 */
	public void loadCommittees()
	{
		try(Stream<Path> paths = Files.walk(Paths.get("data/committees")))
		{
		    paths.forEach(filePath -> {
		        if (Files.isRegularFile(filePath) && filePath.toString().endsWith(".txt"))
		        {
		            String committeeName = filePath.getFileName().toString().split("\\.")[0];
		            System.out.println(committeeName);
		        }
		    });
		} 
		catch (IOException e) {
			System.out.println("Couldn't load committees");
			e.printStackTrace();
		} 
	}
	
	/**
	 * Gets the indices of all active databases
	 * @return the indices on the search server
	 */
	public String[] getIndices() {
		ClusterAdminClient cac = dao.getClient().admin().cluster();
		ClusterState cs = cac.prepareState().execute().actionGet().getState();
		String[] indices = cs.getMetaData().getConcreteAllIndices();
		return indices;
	}
	
	/**
	 * Prefetches indices that already exist on the search server
	 */
	private void preloadIndices() {
		String[] indices = getIndices();
		for(String idx : indices) {
			dbPaths.put(idx, ""); //Preloaded indices uploaded in the database don't need to store path
		}
	}
	
	/**
	 * Main method used for testing purposes
	 * @param args program arguments
	 */
	public static void main(String[] args) {
		try {
			FrontEnd fe = new FrontEnd(new Dao());
			fe.addDBSource("data/dblp.xml");
			fe.addDBSource("data/committees");
			fe.fetchCommittees("data/committees/", true);
			//fe.fetchCommittees("data/committees/ase2001-pc.txt", "committees", true);
			fe.fetchData("dblp");			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
