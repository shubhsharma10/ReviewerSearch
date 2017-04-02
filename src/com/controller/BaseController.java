package com.controller;
import java.net.UnknownHostException;

import com.dao.Dao;
import com.dao.DaoAPI;
import com.dao.SearchResult;
import com.search.Filters;

/**
 * The controller class which serves 
 * as a parent template for all other controllers
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public class BaseController implements ControllerAPI 
{
	/**
	 * The instance for the Dao to connect to the database and search server
	 */
	protected DaoAPI daoInstance;
	
	/**
	 * Instantiates a new controller instance 
	 * and connects to the respected databases
	 */
	public BaseController() {
		try {
			daoInstance = new Dao();
		} 
		catch (UnknownHostException e) {
			System.out.println("Couldn't create DAO instance");
			e.printStackTrace();
		}
	}

	/**
	 * Template method which assist the user in searching for something
	 * @param filter the applied filter to the search
	 * @param isPaper if were searched committee data or article data
	 * @param limit The limit of results to display 
	 */
	public SearchResult search(Filters filter,boolean isPaper,int limit) {		
		return null;
	}

	/**
	 * The method which handles a general search routine with raw text
	 * @param str the string to search for 
	 */
	public void search(String str) {

	}

	/**
	 * Inserts a new database into the search server
	 * @param host the name of the host to add
	 */
	public void addNewDB(String host) {

	}

	/**
	 * Removes a database from the search server
	 * @param str the database name
	 */
	public void removeDB(String str) {	

	}

	/**
	 * Pulls offline data to the databse
	 * @param dbName the name of the database to pull data to
	 */
	public void fetchData(String dbName) {	

	}

	/**
	 * Registers the user on the user server
	 * @param username the user's new username
	 * @param password the user's new password
	 */
	public void registerUser(String username, String password) {

	}

	/**
	 * Deletes a user from the user server
	 * @param username the username to search for deletion
	 */
	public void deleteUser(String username) {

	}

	/**
	 * Verifies if the user's credentials match for login
	 * @return if the user's credentials are suitable for login.
	 */
	public boolean verify(String username, String password) {
		return false;
	}

}
