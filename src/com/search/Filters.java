package com.search;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * The class that defines the filters
 * that are passed from the UI to the 
 * query engine in order to carry out
 * a search on the data 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public class Filters implements IQueryParams
{
	/**
	 * The list of parameters
	 */
	private List<IQueryParams> listOfParams;
	
	/**
	 * The current filter operation
	 */
	private FilterOp filterOp;
	
	/**
	 * Instantiates a new collection container for filters
	 */
	public Filters()
	{
		this.listOfParams = new ArrayList<IQueryParams>();
		this.filterOp = FilterOp.AND;
	}
	
	/**
	 * Method which returns list of Filter or Filters
	 * @return List of Filter(s)
	 */
	public List<IQueryParams> getFilters()
	{
		return this.listOfParams;
	}
	
	/**
	 * Method which adds new filter or filters to the existing list
	 * @param newItem Filter or Filters
	 */
	public void addToList(IQueryParams newItem)
	{
		listOfParams.add(newItem);
	}
	
	/**
	 * Method which updates operation which will be done
	 * between list of filter/filters
	 * @param newOpVal Operation(AND/OR)
	 */
	public void UpdateFilterOp(FilterOp newOpVal)
	{
		this.filterOp = newOpVal;
	}
	
	/**
	 * Method which return Query corresponding to this Filters
	 * @return the query
	 */
	public QueryBuilder getQuery()
	{
		BoolQueryBuilder qb = QueryBuilders.boolQuery();
		if(this.filterOp == FilterOp.AND)
		{
			for(IQueryParams param : listOfParams)
			{
				qb.must(param.getQuery());
			}				
		}
		else
		{
			for(IQueryParams param : listOfParams)
			{
				qb.should(param.getQuery());
			}	
		}
		
		return qb;		
	}
}
