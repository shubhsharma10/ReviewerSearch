
package com.front;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Represents the Data parser which extracts XML data
 * @author Nicholas Carugati
 */
public class DataSetParser extends DefaultHandler {

	/**
	 * The entries in this parsing instance
	 */
	private List<ArticleEntry> entries;
	
	/**
	 * The single XML filename
	 */
	private String fileName;
	
	/**
	 * The current line being read
	 */
	private StringBuilder lineOutput;
	
	/**
	 * A single instance to convert line data into a java object
	 */
	private ArticleEntry singleton;

	/**
	 * Instantiates a new XML to Java Object parser
	 * @param fileName the XML filename to read
	 */
	public DataSetParser(String fileName) {
		this.fileName = fileName;
		entries = new ArrayList<ArticleEntry>();
		lineOutput = new StringBuilder();
		initiateParsing();
	}

	/**
	 * Prepares the SAXParser to read the XML file
	 */
	private void initiateParsing() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(fileName, this);
		} 
		catch (Exception e) 
		{
			System.out.println("Finished loading xml");
		}
	}

	/**
	 * Retrives all of the capured entries in this parser
	 * @return the entries in this parset
	 */
	public List<ArticleEntry> getEntries() {
		return entries;
	}

	/**
	 * Captures parent class elements in a XML document
	 * @param elementName the current element name being read
	 * @param attributes any internal attributes associated with the element
	 */
	@Override
	public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {
		if (elementName.equalsIgnoreCase("article")) {
			singleton = new ArticleEntry();
			singleton.setMdate(attributes.getValue("mdate"));
			singleton.setKey(attributes.getValue("key"));
		}
	}

	/**
	 * Captures child class elements in a XML document
	 * @param element the current XML element that's being read
	 */
	@Override
	public void endElement(String s, String s1, String element) throws SAXException {
		if (element.equals("article")) {
			entries.add(singleton);
		}
		if (element.equalsIgnoreCase("author")) {
			singleton.setAuthor(lineOutput.toString());
		}
		if (element.equalsIgnoreCase("title")) {
			singleton.setTitle(lineOutput.toString());
		}
		if (element.equalsIgnoreCase("pages")) {
			singleton.setPages(lineOutput.toString());
		}
		if (element.equalsIgnoreCase("year")) {
			singleton.setYear(lineOutput.toString());
		}
		if (element.equalsIgnoreCase("volume")) {
			singleton.setVolume(lineOutput.toString());
		}
		if (element.equalsIgnoreCase("journal")) {
			singleton.setJournal(lineOutput.toString());
		}
		if (element.equalsIgnoreCase("number")) {
			singleton.setNumber(lineOutput.toString());
		}
		if (element.equalsIgnoreCase("url")) {
			singleton.setUrl(lineOutput.toString());
		}
		if (element.equalsIgnoreCase("ee")) {
			singleton.setEe(lineOutput.toString());
		}
		lineOutput.setLength(0);
	}

	/**
	 * Captures all of the characters in a single line and sends to the line output
	 * @param ac the string in character array
	 * @param i the min of the line
	 * @param j the max of the line
	 */
	@Override
	public void characters(char[] ac, int i, int j) throws SAXException {
		lineOutput.append(ac, i, j);
	}

	/**
	 * Main method used for testing purposes
	 * @param args program arguments
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		new DataSetParser("data/dblp.xml");
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
	}
}
