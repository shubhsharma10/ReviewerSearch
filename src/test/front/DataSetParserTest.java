package test.front;

import static org.junit.Assert.*;


import java.util.List;

import org.junit.Test;

import com.front.ArticleEntry;
import com.front.DataSetParser;

public class DataSetParserTest {
	/**
     * test method to check the parsing of 
     * dblp data
     */
	@Test
	public void testInitiateParsing() {
		DataSetParser dsp = new DataSetParser("testdata/dblptest.xml");
		
	}
	/**
	 * test method to check the retrieval 
	 * of expected author 	
	 */
	@Test
	public void testGetEntries_author()
	{
		 String actual = "Hans Ulrich Simon";
		 DataSetParser dsp = new DataSetParser("testdata/dblptest.xml");
		 List<ArticleEntry> expResult = dsp.getEntries(); 
		 assertEquals(expResult.get(0).getAuthor(),actual);		 
	}
	/**
	 * test method to check the retrieval 
	 * of expected title 	
	 */
	@Test
	public void testGetEntries_title()
	{
		String actual = "Pattern Matching in Trees and Nets.";
		DataSetParser dsp = new DataSetParser("testdata/dblptest.xml");
		List<ArticleEntry> expResult = dsp.getEntries(); 
		assertEquals(expResult.get(0).getTitle(),actual);	
	}
	/**
	 * test method to check the retrieval 
	 * of expected pages 	
	 */
	@Test
	public void testGetEntries_pages()
	{
		String actual = "227-248";
		DataSetParser dsp = new DataSetParser("testdata/dblptest.xml");
		List<ArticleEntry> expResult = dsp.getEntries(); 
		assertEquals(expResult.get(0).getPages(),actual);	
	}
	/**
	 * test method to check the retrieval 
	 * of expected volume 	
	 */
	@Test
	public void testGetEntries_volume()
	{
		String actual = "20";
		DataSetParser dsp = new DataSetParser("testdata/dblptest.xml");
		List<ArticleEntry> expResult = dsp.getEntries(); 
		assertEquals(expResult.get(0).getVolume(),actual);	
	}
	/**
	 * test method to check the retrieval 
	 * of expected journal 	
	 */
	@Test
	public void testGetEntries_journal()
	{
		String actual = "Acta Inf.";
		DataSetParser dsp = new DataSetParser("testdata/dblptest.xml");
		List<ArticleEntry> expResult = dsp.getEntries(); 
		assertEquals(expResult.get(0).getJournal(),actual);	
	}
	/**
	 * test method to check the retrieval 
	 * of expected url 	
	 */
	@Test
	public void testGetEntries_url()
	{
		String actual = "db/journals/acta/acta20.html#Simon83";
		DataSetParser dsp = new DataSetParser("testdata/dblptest.xml");
		List<ArticleEntry> expResult = dsp.getEntries(); 
		assertEquals(expResult.get(0).getUrl(),actual);	
	}
	/**
	 * test method to check the retrieval 
	 * of expected ee 	
	 */
	@Test
	public void testGetEntries_ee()
	{
		String actual = "http://dx.doi.org/10.1007/BF01257084";
		DataSetParser dsp = new DataSetParser("testdata/dblptest.xml");
		List<ArticleEntry> expResult = dsp.getEntries(); 
		assertEquals(expResult.get(0).getEe(),actual);	
	}
}
