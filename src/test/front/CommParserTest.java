package test.front;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.front.ArticleEntry;
import com.front.CommParser;

public class CommParserTest {
    /**
     * test method to check the parsing of 
     * committee files
     */
	@Test
	public void testInitiateParsing() {
		CommParser cp = new CommParser("testdata/commitee_test");
	}
	/**
	 * test method to check the retrieval of author 
	 * who served as a General Chair	
	 */
	@Test
	public void testGetMemberEntries_authorG() {
		
	    String authorName = "Debra J. Richardson";
		CommParser cp = new CommParser("testdata/commitee_test");	
		List<ArticleEntry> expResult = cp.getMemberEntries(); 
		 assertEquals(expResult.get(0).getAuthor(),authorName);	
	}
	/**
	 * test method to check the retrieval of author 
	 * who served as a Program Chair	
	 */
	@Test
	public void testGetMemberEntries_authorP() {
		
	    String authorName = "Michael Goedicke";
		CommParser cp = new CommParser("testdata/commitee_test");	
		List<ArticleEntry> expResult = cp.getMemberEntries(); 
		 assertEquals(expResult.get(2).getAuthor(),authorName);	
	}
	/**
	 * test method to check the retrieval of author 
	 * who is part of a committee	
	 */
	@Test
	public void testGetMemberEntries_author() {
		
	    String authorName = "Wolfgang Emmerich";
		CommParser cp = new CommParser("testdata/commitee_test");	
		List<ArticleEntry> expResult = cp.getMemberEntries(); 
		 assertEquals(expResult.get(7).getAuthor(),authorName);	
	}
	
		
	}


