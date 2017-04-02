package com.controller;

import com.dao.SearchResult;
import com.search.Filters;

/**
 * The API provided for all operations to be used to access the business layer via controller
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public interface ControllerAPI {

	/**
	 * Method which handles the searching based on 
	 * filtration attributes
	 * @param filter the filter instance which queries the results
	 * @param True if query is for dblp database else false
	 * @param maximum number of items requested in query
	 */
	public SearchResult search(Filters filter,boolean isPaper,int limit);
	
	/**
	 * Method which handles searching based on keywords 
	 * @param str the string to query in a search
	 */
	public void search(String str);
	
	/**
	 * Adds a new database
	 * @param str the name of the host to add to the database
	 */
	public void addNewDB(String host);
	
	/**
	 * Removes a database when needed
	 * @param str the name of the host to remove from the known database hosts.
	 */
	public void removeDB(String str);
	
	/**
	 * Method which fetches data from a given database name,which has been
	 * added already
	 * @param dbName name of the database which needs to be fetched
	 */
	public void fetchData(String dbName);
	
	/**
	 * Method which handles the registration of a new user
	 * @param username username of the new user
	 * @param password password of the new user
	*/
	public void registerUser(String username,String password);
	
	/**
	 * Method which handles the deletion of an user
	 * @param username username of the user which needs to be deleted
	*/
	public void deleteUser(String username);
	
	/**
	 * Method which verifies given information
	 * @param param List of parameters which needs to be verified
	*/
	public boolean verify(String username, String password);
	
}
