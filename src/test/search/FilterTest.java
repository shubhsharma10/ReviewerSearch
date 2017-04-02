package test.search;

import static org.junit.Assert.*;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

import com.search.Filter;
import com.search.OpType;

public class FilterTest 
{
	/**
	 * test method to check the query that checks
	 * the operation HAS_ANY that inputs a string value
	 */
	@Test
	public void testStringAttribute()
	{
		Filter ft = new Filter();
		ft.setFilterAttribute("fullName");
		ft.setStringValue("Don");
		ft.setOperation(OpType.HAS_ANY);
		QueryBuilder qb = QueryBuilders.matchQuery("fullName", "Don");
		assertEquals(qb.toString(),ft.getQuery().toString());
	}
	/**
	 * test method to check the query that checks
	 * the operation HAS_PREFIX that inputs a string value
	 */
	@Test
	public void testStringPrefixAttribute()
	{
		Filter ft = new Filter();
		ft.setFilterAttribute("fullName");
		ft.setStringValue("Don");
		ft.setOperation(OpType.HAS_PREFIX);
		QueryBuilder qb = QueryBuilders.matchPhrasePrefixQuery("fullName", "Don");
		assertEquals("running test case",qb.toString(),ft.getQuery().toString());
	}
	/**
	 * test method to check the query that checks
	 * the operation HAS_PHRASE that inputs a string value
	 */
	@Test
	public void testStringPhraseAttribute()
	{
		Filter ft = new Filter();
		ft.setFilterAttribute("fullName");
		ft.setStringValue("Don");
		ft.setOperation(OpType.HAS_PHRASE);
		QueryBuilder qb = QueryBuilders.matchPhraseQuery("fullName", "Don");
		assertEquals(qb.toString(),ft.getQuery().toString());
	}
	/**
	 * test method to check the query that checks
	 * the operation EQUAL that inputs a numeric value
	 */
	@Test
	public void testNumericEqualityAttribute()
	{
		Filter ft = new Filter();
		ft.setFilterAttribute("age");
		ft.setNumericValue(10);
		ft.setOperation(OpType.EQUAL);
		QueryBuilder qb = QueryBuilders.termQuery("age", 10);
		assertEquals(qb.toString(),ft.getQuery().toString());
	}
		
}
