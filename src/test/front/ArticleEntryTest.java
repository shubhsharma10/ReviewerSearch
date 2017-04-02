package test.front;

import static org.junit.Assert.*;

import org.junit.Test;

import com.front.ArticleEntry;
import com.front.MemberType;

public class ArticleEntryTest {
    /**
     * test method to check the expected article date is set
     */
	@Test
	public void testSetMdate() {
		ArticleEntry ae = new ArticleEntry();
		String mDate = "2011-01-11";
		ae.setMdate(mDate);
		assertEquals(ae.getMdate(),mDate);
	}
	 /**
     * test method to check the expected article date is retrieved
     */
	@Test
	public void testGetMdate(){
		ArticleEntry ae = new ArticleEntry();
		String expResult = "2011-01-11";
		ae.setMdate("2011-01-11");
		String result = ae.getMdate();
		assertEquals(expResult, result);
	}
	 /**
     * test method to check the expected article key is set
     */
	@Test
	public void testSetKey()
	{
		ArticleEntry ae = new ArticleEntry();
		String key = "Journals/acta/BestR81";
		ae.setKey(key);
		assertEquals(ae.getKey(),key);
	}
	 /**
     * test method to check the expected article key is retrieved
     */
	@Test
	public void testGetKey(){
		ArticleEntry ae = new ArticleEntry();
		String expResult = "Journals/acta/BestR81";
		ae.setKey("Journals/acta/BestR81");
		String result = ae.getKey();
		assertEquals(expResult, result);
	}
	/**
     * test method to check the expected article author is set
     */
	@Test
	public void testSetAuthor()
	{
		ArticleEntry ae = new ArticleEntry();
		String author = "Oded Shmueli";
		ae.setAuthor(author);
		assertEquals(ae.getAuthor(),author);
	}
	/**
     * test method to check the expected article author is retrieved
     */
	@Test
	public void testGetAuthor(){
		ArticleEntry ae = new ArticleEntry();
		String expResult = "Oded Shmueli";
		ae.setAuthor("Oded Shmueli");
		String result = ae.getAuthor();
		assertEquals(expResult, result);
	}
	/**
     * test method to check the expected article title is set
     */	
	@Test
	public void testSetTitle()
	{
		ArticleEntry ae = new ArticleEntry();
		String title = "NP-complete Problems Simplified on Tree Schemas.";
		ae.setTitle(title);
		assertEquals(ae.getTitle(),title);
	}
	/**
     * test method to check the expected article title is retrieved
     */
	@Test
	public void testGetTitle(){
		ArticleEntry ae = new ArticleEntry();
		String expResult = "NP-complete Problems Simplified on Tree Schemas.";
		ae.setTitle("NP-complete Problems Simplified on Tree Schemas.");
		String result = ae.getTitle();
		assertEquals(expResult, result);
	}
	/**
     * test method to check the expected article pages is set
     */	
	@Test
	public void testSetPages()
	{
		ArticleEntry ae = new ArticleEntry();
		String pages = "171-178";
		ae.setPages(pages);
		assertEquals(ae.getPages(),pages);
	}
	/**
     * test method to check the expected article pages is retrieved
     */
	@Test
	public void testGetPages(){
		ArticleEntry ae = new ArticleEntry();
		String expResult = "171-178";
		ae.setPages("171-178");
		String result = ae.getPages();
		assertEquals(expResult, result);
	}
	/**
     * test method to check the expected article year is set
     */	
	@Test
	public void testSetYear()
	{
		ArticleEntry ae = new ArticleEntry();
		String year = "1983";
		ae.setYear(year);
		assertEquals(ae.getYear(),year);
	}
	/**
     * test method to check the expected article year is retrieved
     */
	@Test
	public void testGetYear(){
		ArticleEntry ae = new ArticleEntry();
		String expResult = "1983";
		ae.setYear("1983");
		String result = ae.getYear();
		assertEquals(expResult, result);
	}
	/**
     * test method to check the expected article volume is set
     */	
	@Test
	public void testSetVolume()
	{
		ArticleEntry ae = new ArticleEntry();
		String volume = "20";
		ae.setVolume(volume);
		assertEquals(ae.getVolume(),volume);
	}
	/**
     * test method to check the expected article volume is retrieved
     */
	@Test
	public void testGetVolume(){
		ArticleEntry ae = new ArticleEntry();
		String expResult = "20";
		ae.setVolume("20");
		String result = ae.getVolume();
		assertEquals(expResult, result);
	}
	/**
     * test method to check the expected article journal is set
     */	
	@Test
	public void testSetJournal()
	{
		ArticleEntry ae = new ArticleEntry();
		String journal = "Acta Inf.";
		ae.setJournal(journal);
		assertEquals(ae.getJournal(),journal);
	}
	/**
     * test method to check the expected article journal is retrieved
     */
	@Test
	public void testGetJournal(){
		ArticleEntry ae = new ArticleEntry();
		String expResult = "Acta Inf.";
		ae.setJournal("Acta Inf.");
		String result = ae.getJournal();
		assertEquals(expResult, result);
	}
	/**
     * test method to check the expected article number is set
     */	
	@Test
	public void testSetNumber()
	{
		ArticleEntry ae = new ArticleEntry();
		String number = "5";
		ae.setNumber(number);
		assertEquals(ae.getNumber(),number);
	}
	/**
     * test method to check the expected article number is retrieved
     */
	@Test
	public void testGetNumber(){
		ArticleEntry ae = new ArticleEntry();
		String expResult = "5";
		ae.setNumber("5");
		String result = ae.getNumber();
		assertEquals(expResult, result);
	}
	/**
     * test method to check the expected article url is set
     */	
	@Test
	public void testSetUrl()
	{
		ArticleEntry ae = new ArticleEntry();
		String url = "db/journals/acta/acta20.html#Simon83";
		ae.setUrl(url);
		assertEquals(ae.getUrl(),url);
	}
	/**
     * test method to check the expected article url is retrieved
     */
	@Test
	public void testGetUrl(){
		ArticleEntry ae = new ArticleEntry();
		String expResult = "db/journals/acta/acta20.html#Simon83";
		ae.setUrl("db/journals/acta/acta20.html#Simon83");
		String result = ae.getUrl();
		assertEquals(expResult, result);
	}
	/**
     * test method to check the expected article ee is set
     */	
	@Test
	public void testSetEe()
	{
		ArticleEntry ae = new ArticleEntry();
		String ee = "http://dx.doi.org/10.1007/BF01257084";
		ae.setEe(ee);
		assertEquals(ae.getEe(),ee);
	}
	/**
     * test method to check the expected article ee is retrieved
     */
	@Test
	public void testGetEe(){
		ArticleEntry ae = new ArticleEntry();
		String expResult = "http://dx.doi.org/10.1007/BF01257084";
		ae.setEe("http://dx.doi.org/10.1007/BF01257084");
		String result = ae.getEe();
		assertEquals(expResult, result);
	}
	/**
     * test method to check the expected committee name is set
     */	
	@Test
	public void testGetCommName()
	{
		ArticleEntry ae = new ArticleEntry();
		String commName = "IEEE Access";
		ae.setCommitteeName(commName);
		assertEquals(ae.getCommitteeName(),"IEEE ACCESS");
	}
	/**
     * test method to check the expected committee name is retrieved
     */
	@Test
	public void testSetCommName(){
		ArticleEntry ae = new ArticleEntry();
		String commName = "IEEE";
		ae.setCommitteeName("IEEE");
		assertEquals(ae.getCommitteeName(),commName);
	}
	/**
     * test method to check the expected committee year is set
     */	
	@Test
	public void testGetCommYear()	{
		ArticleEntry ae = new ArticleEntry();
		Integer commYear = 2012;
		ae.setCommitteeYear(commYear.toString());
		assertEquals(ae.getCommitteeYear(),commYear);
	}
	/**
     * test method to check the expected committee year is retrieved
     */
	@Test
	public void testSetCommYear(){
		ArticleEntry ae = new ArticleEntry();
		Integer commYear = 2012;
		ae.setCommitteeYear("2012");
		assertEquals(ae.getCommitteeYear(),commYear);
	}
	/**
     * test method to check the entry of an invalid member type
     */
	@Test
	public void testInvalidMemberType()	{
		ArticleEntry ae = new ArticleEntry();
		MemberType defaultValue = MemberType.Member;
		ae.setMemberType("asdf");
		assertEquals(ae.getMemberType().toString(),defaultValue.toString());
	}
	/**
     * test method to check the expected committee member type is set
     */	
	@Test
	public void testSetMemberType(){
		ArticleEntry ae = new ArticleEntry();
		MemberType typeValue = MemberType.ConferenceChair;
		ae.setMemberType(MemberType.ConferenceChair);
		assertEquals(ae.getMemberType().toString(),typeValue.toString());
	}
		
		
}
