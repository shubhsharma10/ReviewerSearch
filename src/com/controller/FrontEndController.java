package com.controller;

import java.io.File;
import java.net.UnknownHostException;

import com.dao.Dao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.front.FrontEnd;
import com.front.FrontEndAPI;

/**
 * The controller class which handles all search server operations for the user
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public class FrontEndController extends BaseController
{
	
	/**
	 * The controller front end instance
	 */
	private FrontEndAPI feAPI;
	
	/**
	 * Instantiates a new front end controller
	 * @throws UnknownHostException throws if 
	 * the user is unable to connect to the database
	 */
	public FrontEndController() throws UnknownHostException {
		super();
		this.feAPI = new FrontEnd((Dao) daoInstance);
	}
	
	/**
	 * Adds a new database to the search server
	 * @param dbname the new database name
	 */
	public void addNewDB(String dbName) {
		try {
			feAPI.addDBSource(dbName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes the database from the search server
	 * @param dbName the name of the database to remove
	 */
	public void removeDB(String dbName) {
		feAPI.removeDB(dbName);
	}
	
	/**
	 * Uploads offline data to the search server
	 * @param dataPath the offline data path that should be upload
	 */
	public void fetchData(String dataPath) {
		try {
			feAPI.fetchData(dataPath);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves any offline committee data
	 * @param dataPath the path of the committee data
	 * @param strings a optional database to insert if specified
	 */
	public void fetchCommittees(String dataPath, String...strings) {
		if(feAPI instanceof FrontEnd) {
			File file = new File(dataPath);
			if(file.isDirectory()) {
				((FrontEnd) feAPI).fetchCommittees(dataPath, false);
			} else {
				((FrontEnd) feAPI).fetchCommittees(dataPath, strings[0], true);
			}
		}
	}
	
	public String[] getIndices() {
		return ((FrontEnd) feAPI).getIndices();
	}
}

