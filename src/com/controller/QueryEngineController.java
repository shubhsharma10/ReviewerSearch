package com.controller;

import com.dao.Dao;
import com.dao.SearchResult;
import com.search.Filters;
import com.search.QueryEngine;
import com.search.QueryEngineAPI;

/**
 * The controller class which handles all search server operations for the user
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public class QueryEngineController extends BaseController
{
	/**
	 * The query engine interface instance
	 */
	private QueryEngineAPI qeAPI;
	
	/**
	 * Instantiates a new Query Engine controller.
	 */
	public QueryEngineController() {
		super();
		this.qeAPI = new QueryEngine((Dao) daoInstance);
	}
	
	/**
	 * Performs a search query 
	 * @param filter the filters for the search query
	 * @param isPaper determines if its a committee search or a article search
	 * @param limit determines how many results should be rendered
	 * @returns The search result to be soon populated
	 */
	@Override
	public SearchResult search(Filters filter, boolean isPaper,int limit)
	{		
		return this.qeAPI.search(filter, isPaper,limit);
	}

	/**
	 * Performs a search query with no result
	 * This is a method stub
	 */
	@Override
	public void search(String str)
	{
	}
}
