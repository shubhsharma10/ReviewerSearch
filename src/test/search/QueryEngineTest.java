package test.search;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;
import com.dao.Dao;
import com.dao.SearchResult;
import com.front.DataSetParser;
import com.front.FrontEnd;
import com.search.Filter;
import com.search.Filters;
import com.search.OpType;
import com.search.QueryEngine;

public class QueryEngineTest {
	
	
    /**
     * test method to check that the data base is loaded,
     * data is fetched from the database,
     * fetch result of query that involves an attribute,
     * value and operation type,
     * the uploaded data is flushed from elastic search 
     * @throws UnknownHostException
     * @throws InterruptedException
     */
	@Test
	public void testSearch() throws UnknownHostException, InterruptedException
	{
		Dao daoInstance = new Dao();
		FrontEnd feInstance = null;
		try {
			
			feInstance = new FrontEnd(daoInstance);
		} catch (UnknownHostException e) {
			System.out.println("Couldn't create instance");
		}
		if(feInstance != null)
		{
			feInstance.addDBSource("testdata/dblptest.xml");
			feInstance.fetchData("dblptest");
			Thread.sleep(1000);
		}
	   
		@SuppressWarnings("unused")
		QueryEngine qe = new QueryEngine(daoInstance);
	    Filters filters = new Filters();
	    Filter filter = new Filter();
	    filter.setFilterAttribute("author");
		filter.setStringValue("Hans");
		filter.setOperation(OpType.HAS_ANY);
		filters.addToList(filter);
	    SearchResult expResult = daoInstance.getTestQueryResult(filters.getQuery(), "dblptest", 50);
	    assertEquals("Hans Ulrich Simon",expResult.getArticles().get(0).getAuthor());	    
	    feInstance.removeDB("dblptest");
	    
	}
	
	
	
}
