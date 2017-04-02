package com.dao;

import org.elasticsearch.index.query.QueryBuilder;

import com.access.User;

/**
 * The API provided for all components for all operations to be used for the databases
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public interface DaoAPI {
	
	/**
	 * Method which fetches the search result for a given query
	 * @param query having the search attributes to look for
	 * @param isPaper true if query is for dblp database else false
	 * @param limit the amount of results the query should render
	 * @return the result set of the given query 
	 */
	public SearchResult getQueryResult(QueryBuilder query,boolean isPaper,int limit);
	
	/**
	 * Method used to add user for the system
	 * @param user details to register a user
	 */
	public void addUser(User user);
	
	/**
	 * Method to delete a user
	 * @param user detials to identify the user to be deleted
	 */
	public void deleteUser(User user);
}
