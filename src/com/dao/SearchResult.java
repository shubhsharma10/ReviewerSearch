package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.search.SearchHit;
import com.front.ArticleEntry;

/**
 * The class which stores the result after querying from Elastic Search server
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public class SearchResult
{
	List<ArticleEntry> articles;
	
	/**
	 * Instantiates a new search result
	 */
	public SearchResult()
	{
		this.articles = new ArrayList<ArticleEntry>();
	}
	
	/**
	 * Instantiates a new search result with raw search data
	 * @param hits the raw search data
	 */
	public SearchResult(SearchHit[] hits)
	{
		this.articles = new ArrayList<ArticleEntry>();
		for(SearchHit hit : hits)
		{
			articles.add(parseSearchHit(hit));
		}
	}
	
	/**
	 * Gets all the articles from a specific search result
	 * @return list of Articles where each article is row retrieved from database
	 */
	public List<ArticleEntry> getArticles()
	{
		return this.articles;
	}
	
	/**
	 * Method which parses row entry received from database
	 * @return The row entry from the search server
	 */
	private ArticleEntry parseSearchHit(SearchHit hit)
	{
		ArticleEntry ae = new ArticleEntry();
		Map<String,Object> map = hit.sourceAsMap();
		for (Map.Entry<String, Object> entry : map.entrySet()) 
		{
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    
		    if(key.equalsIgnoreCase("mdate") && value != null)
		    	ae.setMdate(value.toString());
		    
		    else if(key.equalsIgnoreCase("key") && value != null)
		    	ae.setKey(value.toString());
		    
			else if(key.equalsIgnoreCase("author") && value != null)
				ae.setAuthor(value.toString());
		    
			else if(key.equalsIgnoreCase("title") && value != null)
				ae.setTitle(value.toString());
		    
			else if(key.equalsIgnoreCase("pages") && value != null)
				ae.setPages(value.toString());
		    
			else if(key.equalsIgnoreCase("year") && value != null)
				ae.setYear(value.toString());
		    
			else if(key.equalsIgnoreCase("volume") && value != null)
				ae.setVolume(value.toString());
		    
			else if(key.equalsIgnoreCase("journal") && value != null)
				ae.setJournal(value.toString());
		    
			else if(key.equalsIgnoreCase("number") && value != null)			
					ae.setNumber(value.toString());
		    
			else if(key.equalsIgnoreCase("url") && value != null)
					ae.setUrl(value.toString());
		    
			else if(key.equalsIgnoreCase("ee") && value != null)			
				ae.setEe(value.toString());
		    
			else if(key.equalsIgnoreCase("committeeName") && value != null)
			{
				ae.setCommitteeName(value.toString());
				ae.setIsPaper(false);
			}
		    
			else if(key.equalsIgnoreCase("memberType") && value != null)			
				ae.setMemberType(value.toString());	
		    
			else if(key.equalsIgnoreCase("committeeYear") && value != null)			
				ae.setCommitteeYear(value.toString());	
		}
		return ae;
	}	
}
