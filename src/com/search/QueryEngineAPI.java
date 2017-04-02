package com.search;

import com.dao.SearchResult;

/**
 * The API provided for all components for all operations to be used for query engine
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public interface QueryEngineAPI 
{
	/**
	 * Method which handles search query based on filters
	 * @param filters parameter for search based on filters
	 * @param True if query is for dblp database else false
	 * @param maximum number of items requested in query
	*/
	public SearchResult search(Filters filters,boolean isPaper,int limit);
	
	/**
	 * Method which handles search query based on string parameters
	 * @param parms parameter for search query
	*/
	public SearchResult search(String params);
}
