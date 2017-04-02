package com.search;

import org.elasticsearch.index.query.QueryBuilder;

/**
 * The Interface file which contains operation for getting Query as expected 
 * by ElasticSearch Server
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public interface IQueryParams
{
	/**
	 * Method which converts Filter/Filters parameter to Query
	 * as expected by ElasticSearch Server
	 * @return the query
	 */
	public QueryBuilder getQuery();
}
