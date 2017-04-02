package com.controller;

import com.access.AccessControl;
import com.access.AccessControlAPI;
import com.access.User;

/**
 * The controller class which handles all access operations for the user
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public class AccessController extends BaseController
{
	
	/**
	 * The controller access instance
	 */
	private AccessControlAPI acAPI;
	
	/**
	 * Instantiates a new access controller session
	 */
	public AccessController() {
		super();
		this.acAPI = new AccessControl();
	}
	
	/**
	 * Registers a new user to the database
	 * @param user The username string to register
	 * @param password the password string to register
	 */
	@Override
	public void registerUser(String username, String password) {
		acAPI.registerUser(new User(username, password));
	}

	/**
	 * Deletes the user from the database
	 * @param username the username to search for to delete
	 */
	@Override
	public void deleteUser(String username) {
		acAPI.deleteUser(username);
	}

	/**
	 * Verifies if the username's credentials are valid for login
	 * @param username the username to verify
	 * @param the password to verify
	 */
	@Override
	public boolean verify(String username, String password) {
		return acAPI.validateUser(new User(username, password));
	}
	
	/**
	 * Determines if the user exists
	 * @param username the username to search for
	 * @return if the username exists in the database
	 */
	public boolean userExists(String username) {
		return acAPI.userExists(username);
	}
	
	
	
}
