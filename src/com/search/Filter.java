package com.search;

import java.util.Arrays;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * The class file which represents a smallest query possible
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public class Filter implements IQueryParams
{
	/**
	 * The filter attribute
	 */
	private String filterAttr;
	
	/**
	 * The string value
	 */
	private String strVal;
	
	/**
	 * The numeric value
	 */
	private Integer numVal;
	
	/**
	 * The number range for numeric search
	 */
	private int[] range;
	
	/**
	 * The expression operation type
	 */
	private OpType expr;
	
	/**
	 * Creates a new filter instance
	 */
	public Filter()
	{
		filterAttr = new String();
		strVal = new String();
		numVal = 0;
		range = new int[2];
		Arrays.fill(range, 0);
		expr = OpType.NONE;
	}
	
	/**
	 * Set parameter which is equivalent to column name in database
	 * @param filterAttr the new filter attributes
	 */
	public void setFilterAttribute(String filterAttr)
	{
		this.filterAttr = filterAttr;
	}
	
	/**
	 * Set value to search if field to be searched is of type String
	 * @param strVal the new string value
	 */
	public void setStringValue(String strVal)
	{
		this.strVal = strVal;
	}
	
	/**
	 * Set value to search if field to be searched is of type Integer
	 * @param numVal the new numeric value
	 */
	public void setNumericValue(Integer numVal)
	{
		this.numVal = numVal;
	}
	
	/**
	 * Set value to search if field to be searched is of type Integer range
	 * @param range the new range values
	 */
	public void setRange(int[] range)
	{
		this.range[0] = range[0];
		this.range[1] = range[1];
	}

	/**
	 * Set operation for String values if's one of : Has any,Has Prefix, Has phrase
	 * For Integer values it's one of: Equal, Not equal, Less than, Greater than,
	 * 							Less than and equal, Greater than and equal,
	 * 							Between including limits, Between excluding limits
	 * @param optype the operation type
	 */
	public void setOperation(OpType optype)
	{
		this.expr = optype;
	}
	
	/**
	 * Returns query as per the format acceptable by ElasticSearch
	 * @return the query
	 */
	public QueryBuilder getQuery()
	{	
		switch(this.expr)
		{
			case HAS_ANY:
				return QueryBuilders.matchQuery(filterAttr, strVal);
			case HAS_PHRASE:
				return QueryBuilders.matchPhraseQuery(filterAttr, strVal);
			case HAS_PREFIX:
				return QueryBuilders.matchPhrasePrefixQuery(filterAttr, strVal);
			case EQUAL:
				return QueryBuilders.termQuery(filterAttr, this.numVal);
			case NOT_EQUAL:
				return QueryBuilders.boolQuery().mustNot(QueryBuilders.termQuery(filterAttr, numVal));
			case LESSER:
				return QueryBuilders.rangeQuery(filterAttr).lt(numVal);
			case GREATER:
				return QueryBuilders.rangeQuery(filterAttr).gt(numVal);
			case LESS_EQUAL:
				return QueryBuilders.rangeQuery(filterAttr).lte(numVal);
			case GREAT_EQUAL:
				return QueryBuilders.rangeQuery(filterAttr).gte(numVal);
			case BETWEEN_INCL:
				return QueryBuilders.rangeQuery(filterAttr).lte(range[1]).gte(range[0]);
			case BETWEEN_EXCL:
				return QueryBuilders.rangeQuery(filterAttr).lt(range[1]).gt(range[0]);
			default:
				return QueryBuilders.matchAllQuery();
		}
	}
}
