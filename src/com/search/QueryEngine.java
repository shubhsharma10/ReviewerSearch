package com.search;

import com.dao.DaoAPI;
import com.dao.SearchResult;

/**
 * The class file which represents all operations available from QueryEngine
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public class QueryEngine implements QueryEngineAPI 
{	
	private DaoAPI daoInstance;
	
	/**
	 * Instantiates a new query engine service
	 * @param daoInstance the current instance of the dao
	 */
	public QueryEngine(DaoAPI daoInstance)
	{
		this.daoInstance = daoInstance;
	}
	
	/**
	 * Method which handles the searching based on 
	 * filtration attributes
	 * @param filter the filter instance which queries the results
	 * @param True if query is for dblp database else false
	 * @param maximum number of items requested in query
	 * @return The search result
	 */
	public SearchResult search(Filters filters,boolean isPaper,int limit) 
	{
		return this.daoInstance.getQueryResult(filters.getQuery(),isPaper,limit);
	}
	
	/**
	 * A search placeholder method
	 * @params Raw query params
	 * @return nothing
	 */
	public SearchResult search(String params) {
		return null;
	}

}
