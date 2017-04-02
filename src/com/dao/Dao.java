package com.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.access.User;
import com.front.ArticleEntry;

/**
 * The class which provides for all components for all operations to be used for the databases
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public class Dao implements DaoAPI {
	
	/**
	 * The main search database client
	 */
	private TransportClient client;
	
	/**
	 * The settings for the database client
	 */
	private Settings settings;
	
	/**
	 * The constant for the committee database name
	 */
	private static final String COMMITTEE_INDEX_NAME = "committees";
	
	/**
	 * The constant for the main search database name
	 */
	private static final String DBLP_INDEX_NAME = "dblp";
	
	/**
	 * The name of the type which houses all documents for search server
	 */
	private String typeName = "data";
	
	/**
	 * The search server's port number
	 */
	private int esPortNumber = 9300;
	
	/**
	 * Instantiates a new Dao connection
	 * Establishes a new connection to the search server.
	 * @throws UnknownHostException if the host is unknown
	 */
	public Dao() throws UnknownHostException
	{
		this.settings = Settings.builder().put("cluster.name", "elasticsearch").build();
		this.client = new PreBuiltTransportClient(settings);
		client.addTransportAddress(
				new InetSocketTransportAddress(InetAddress.getByName("localhost"), esPortNumber));
	}
	
	/**
	 * Method which fetches the search result for a given query
	 * @param query having the search attributes to look for
	 * @param True if query is for dblp database else false
	 * @param maximum number of items requested in query
	 * @return the result set of the given query 
	 */
	public SearchResult getQueryResult(QueryBuilder query,boolean isPaper,int limit)
	{
		if(isPaper)
			return getQueryResult(query, Dao.DBLP_INDEX_NAME, limit);
		else 
			return getQueryResult(query, Dao.COMMITTEE_INDEX_NAME, limit);
	}	
	
	/**
	 * Method which returns Elastic search transport client
	 * @return the client instance of this Dao.
	 */
	public TransportClient getClient() {
		return client;
	}
	
	/**
	 * Method which returns settings for Transport client
	 * @return the settings for the client
	 */
	public Settings getSettings() {
		return settings;
	}
	
	/**
	 * (non-Javadoc)
	 * @see com.dao.DaoAPI#addUser(com.access.User)
	 */
	public void addUser(User user) { /* Nothing */ }
	
	/**
	 * (non-Javadoc)
	 * @see com.dao.DaoAPI#deleteUser(com.access.User)
	 */
	public void deleteUser(User user) { /* Nothing */ }
	
	/**
	 * This method is written for testing purposes
	 * Method which fetches the search result for a given query
	 * @param query having the search attributes to look for
	 * @param indexName ES index name, in which search has to be done
	 * @param maximum number of items requested in query
	 * @return the result set of the given query 
	 */
	public SearchResult getTestQueryResult(QueryBuilder query,String indexName,int limit)
	{
		return getQueryResult(query,indexName,limit);
	}
	
	/**
	 * Method which fetches the search result for a given query
	 * @param query having the search attributes to look for
	 * @param indexName ES index name, in which search has to be done
	 * @param maximum number of items requested in query
	 * @return the result set of the given query 
	 */
	private SearchResult getQueryResult(QueryBuilder query,String indexName,int limit)
	{
		SearchResponse response = client.prepareSearch(indexName)
				.setTypes(typeName)
				.setSearchType(SearchType.QUERY_THEN_FETCH)
				.setQuery(query)
				.setSize(limit)
				.get();
		SearchHit[] hits =  response.getHits().getHits();
		SearchResult sr = new SearchResult(hits);
		return sr;
	}
	
	/**
	 * Main class used for testing purposes
	 * @param args additional program arguments
	 */
	public static void main(String[] args)
	{
		// For HTTP query 
		// http://127.0.0.1:9200/dblp/_search/?size=1000&pretty=1
		QueryBuilder qb = QueryBuilders.boolQuery()
				.must(QueryBuilders.matchQuery("author", "Brian Sadler"));
		try 
		{
			Dao dbInterface = new Dao();

			SearchResult srt = dbInterface.getQueryResult(qb,false,50);

			

			System.out.println("Number of articles retrieved are: "+srt.getArticles().size());
			for(ArticleEntry ae : srt.getArticles())
				System.out.println(ae.getAuthor().toString());
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		
	}

}
