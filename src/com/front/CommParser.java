package com.front;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * The class which handles the parsing of committee files in plaintext documents
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public class CommParser {

	/**
	 * The current list of entries caught by the parser
	 */
	private List<ArticleEntry> entries;
	
	/**
	 * The path of the folder to read for the parser
	 */
	private String folderPath;
	
	/**
	 * The absolute path string for all the files in a directory
	 */
	private List<String> filePaths;
	
	/**
	 * The file names for all the files in a directory
	 */
	private List<String> fileNames;
	
	/**
	 * Instantiates a new committee parser
	 * @param path the path of the directory
	 */
	public CommParser(String path) {
		File file = new File(path);
		this.entries = new ArrayList<ArticleEntry>();
		if(file.isDirectory()) {
			this.folderPath = path;
			this.fileNames = new ArrayList<String>();
			this.filePaths = new ArrayList<String>();
			loadFileInfo();
			initiateParsing();
		} else {
			loadSingleFile(path);
		}
	}
	
	/**
	 * Retrieves all of the captured member entries
	 * @return the member entries
	 */
	public List<ArticleEntry> getMemberEntries()
	{
		return this.entries;
	}
	
	/**
	 * Testing method which prints all entries caught
	 */
	public void printAllEntries()
	{
		System.out.println("Number of members are: "+this.entries.size());
		for(ArticleEntry entry : this.entries)
		{
			System.out.println(entry.toString());
		}
	}
	
	/**
	 * Loads the info of all files in a directory
	 */
	private void loadFileInfo()
	{
		try(Stream<Path> paths = Files.walk(Paths.get(this.folderPath)))
		{
		    paths.forEach(filePath -> {
		        if (Files.isRegularFile(filePath) && filePath.toString().endsWith(".txt"))
		        {
		            String committeeName = filePath.getFileName().toString().split("\\.")[0];
		            this.fileNames.add(committeeName);
		            this.filePaths.add(filePath.toString());
		        }
		    });
		} 
		catch (IOException e) {
			System.out.println("Couldn't load committees");
			e.printStackTrace();
		} 
	}
	
	/**
	 * Extracts the committee abreviation from the file name based on regex
	 * @param rawCommitteeName the whole filename of the committee list
	 * @return the name of the committee abbreviation
	 */
	private String extractCommitteeAbbrv(String rawCommitteeName)
	{
		String name = rawCommitteeName.replaceAll("[^A-Za-z]+", "");
		return name.trim();
	}
	
	/**
	 * Gets the year of a specific committee name from a file based on regex
	 * @param rawCommitteeName the whole filename of the committee list
	 * @return the year that is on the filename of the committee list
	 */
	private String extractYear(String rawCommitteeName)
	{
		String numValue = rawCommitteeName.replaceAll("[^0-9]", "");
		return numValue.trim();
	}
	
	/**
	 * Loads a single file into the parser instance
	 * @param fileName the file name to read
	 */
	private void loadSingleFile(String fileName) {
		try {
			Scanner input = null;
			String name = null;
			if(fileName.indexOf('/') >= 0) {
				String s[] = fileName.split("/");
				name = s[s.length - 1].split("\\.")[0];
			} else {
				name = fileName;
			}
			String rawCommName = name;
			if(rawCommName.endsWith("-pc"))
				rawCommName = rawCommName.substring(0, rawCommName.length() - 3);


			String commName = extractCommitteeAbbrv(rawCommName);
			String commYear = extractYear(rawCommName);

			MemberType memType = MemberType.Member;
			String memName = "";

			FileInputStream file = new FileInputStream(fileName);
			input = new Scanner(file);

			while(input.hasNext())
			{
				//process line by line
				String nextLine = input.nextLine();
				if(nextLine.startsWith("G:"))
				{
					memType = MemberType.GeneralChair;
					memName = nextLine.substring(2);
				}
				else if(nextLine.startsWith("P:"))
				{
					memType = MemberType.ProgramChair;
					memName = nextLine.substring(2);
				}
				else if(nextLine.startsWith("C:"))
				{
					memType = MemberType.ConferenceChair;
					memName = nextLine.substring(2);
				}
				else if(nextLine.startsWith("E:"))
				{
					memType = MemberType.ExtReviewComt;
					memName = nextLine.substring(2);
				}
				else
				{
					memName = nextLine;
				}

				ArticleEntry member = new ArticleEntry();
				member.setIsPaper(false);
				member.setAuthor(memName);
				member.setMemberType(memType);
				member.setCommitteeName(commName);
				member.setCommitteeYear(commYear);
				this.entries.add(member);
			}
			input.close();
		} catch(IOException e) {
			System.out.println("Couldn't load committees");
			e.printStackTrace();
		}
	}
	
	/**
	 * The primary method which reads a whole 
	 * directory and adds the data to the parser
	 */
	private void initiateParsing() {
       try
       {
    	   Scanner input = null;
    	   for(int i=0;i<this.filePaths.size();i++)
    	   {
    		   String rawCommName = this.fileNames.get(i);
    		   if(rawCommName.endsWith("-pc"))
    			   rawCommName = rawCommName.substring(0,rawCommName.length()-3);
    		   
   
    		   String commName = extractCommitteeAbbrv(rawCommName);
    		   String commYear = extractYear(rawCommName);
    		   
    		   MemberType memType = MemberType.Member;
	    	   String memName = "";
	    	   
	    	   FileInputStream file = new FileInputStream(this.filePaths.get(i));
	    	   input = new Scanner(file);
	    	   
	    	   while(input.hasNext())
	    	   {
	    	       //process line by line
	    		   String nextLine = input.nextLine();
	    		   if(nextLine.startsWith("G:"))
	    		   {
	    			   memType = MemberType.GeneralChair;
	    			   memName = nextLine.substring(2);
	    		   }
	    		   else if(nextLine.startsWith("P:"))
	    		   {
	    			   memType = MemberType.ProgramChair;
	    			   memName = nextLine.substring(2);
	    		   }
	    		   else if(nextLine.startsWith("C:"))
	    		   {
	    			   memType = MemberType.ConferenceChair;
	    			   memName = nextLine.substring(2);
	    		   }
	    		   else if(nextLine.startsWith("E:"))
	    		   {
	    			   memType = MemberType.ExtReviewComt;
	    			   memName = nextLine.substring(2);
	    		   }
	    		   else
	    		   {
	    			   memName = nextLine;
	    		   }
	    		   
	    		   ArticleEntry member = new ArticleEntry();
	    		   member.setIsPaper(false);
	    		   member.setAuthor(memName);
	    		   member.setMemberType(memType);
	    		   member.setCommitteeName(commName);
	    		   member.setCommitteeYear(commYear);
    			   this.entries.add(member);
	    	   }
	
	    	   input.close();
    	   }
       }
       catch(IOException e) {
    	   System.out.println("Couldn't load committees");
			e.printStackTrace();
       }
    }
	
	/**
	 * Main method class used for testing purposes
	 * @param args program arguments
	 */
	public static void main(String[] args)
	{
		CommParser cp = new CommParser("data/committees");
		cp.printAllEntries();
	}
	
}
