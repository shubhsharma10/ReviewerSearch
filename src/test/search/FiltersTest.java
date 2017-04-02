package test.search;

import static org.junit.Assert.*;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import com.search.Filter;
import com.search.FilterOp;
import com.search.Filters;
import com.search.OpType;

public class FiltersTest {
    /**
     * test method to get expected filters
     * that involves an attribute, string value and 
     * the operation type
     */
	@Test
	public void testGetFilters() {
		Filters filters = new Filters();
		Filter filter = new Filter();
		assertEquals(0,filters.getFilters().size());
	    filter.setFilterAttribute("author");
		filter.setStringValue("Hans");
		filter.setOperation(OpType.HAS_ANY);
		filters.addToList(filter);
		assertEquals(1,filters.getFilters().size());
	}
	/**
     * test method to check the basic query
     * that involves an attribute, string value and 
     * the operation type
     */
	@Test
	public void testGetQuery() {
		Filters filters = new Filters();
		Filter filter = new Filter();
	    filter.setFilterAttribute("journal");
		filter.setStringValue("Acta Inf.");
		filter.setOperation(OpType.HAS_ANY);
		filters.addToList(filter);
		BoolQueryBuilder qb = QueryBuilders.boolQuery();
		qb.must(QueryBuilders.matchQuery("journal", "Acta Inf."));
		assertEquals(qb.toString(),filters.getQuery().toString());
	}
	/**
     * test method to check the nested query
     * that involves  attributes, string value, numeric value, 
     * the operation types and AND operation
     */
	@Test
	public void testGetMultipleAndQueries() {
		Filters filters = new Filters();
		Filter filter1 = new Filter();
		Filter filter2 = new Filter();
		filters.UpdateFilterOp(FilterOp.AND);
	    filter1.setFilterAttribute("author");
		filter1.setStringValue("Hans");
		filter1.setOperation(OpType.HAS_ANY);
		filter2.setFilterAttribute("year");
		filter2.setNumericValue(2012);
		filter2.setOperation(OpType.EQUAL);
		filters.addToList(filter1);
		filters.addToList(filter2);
		BoolQueryBuilder qb = QueryBuilders.boolQuery();
		qb.must(QueryBuilders.matchQuery("author", "Hans"));
		qb.must(QueryBuilders.termQuery("year", 2012));
		assertEquals(qb.toString(),filters.getQuery().toString());
	}
	/**
     * test method to check the nested query
     * that involves  attributes, string value, numeric value, 
     * the operation types and OR operation
     */	
	@Test
	public void testGetMultipleOrQueries() {
		Filters filters = new Filters();
		Filter filter1 = new Filter();
		Filter filter2 = new Filter();
		filters.UpdateFilterOp(FilterOp.OR);
	    filter1.setFilterAttribute("title");
		filter1.setStringValue("Pattern Matching in Trees and Nets.");
		filter1.setOperation(OpType.HAS_ANY);
		filter2.setFilterAttribute("volume");
		filter2.setNumericValue(20);
		filter2.setOperation(OpType.EQUAL);
		filters.addToList(filter1);
		filters.addToList(filter2);
		BoolQueryBuilder qb = QueryBuilders.boolQuery();
		qb.should(QueryBuilders.matchQuery("title", "Pattern Matching in Trees and Nets."));
		qb.should(QueryBuilders.termQuery("volume", 20));
		assertEquals(qb.toString(),filters.getQuery().toString());
	}
}
