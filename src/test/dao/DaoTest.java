package test.dao;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dao.Dao;
import com.dao.SearchResult;
import com.front.FrontEnd;


public class DaoTest {
	


	/**
	 * test method to test the query that fetches 
	 * the expected author name from the committee database
	 */


	static FrontEnd feInstance = null;

	
	@BeforeClass
	public static void setup() throws UnknownHostException, InterruptedException
	{
		Dao daoInstance = new Dao();
		
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
	}
	


	@Test
	public void testGetQueryResultCommittee() throws UnknownHostException, InterruptedException{
		Dao dao = new Dao();
		try{
		QueryBuilder qb = QueryBuilders.matchPhrasePrefixQuery("author", "Hans");
	    SearchResult expResult = dao.getTestQueryResult(qb, "dblptest", 50);
	    assertTrue(expResult.getArticles().get(0).getAuthor().contains("Hans"));
		}
		catch(Exception e)
		{
			Thread.sleep(10000);
			QueryBuilder qb = QueryBuilders.matchPhrasePrefixQuery("author", "Hans");
		    SearchResult expResult = dao.getQueryResult(qb, true, 50);
		    assertEquals("Hans V. Hansen",expResult.getArticles().get(0).getAuthor());
		}
	}
	
	@AfterClass
	public static void cleanup()
	{
		feInstance.removeDB("dblptest");
	}
	
}
