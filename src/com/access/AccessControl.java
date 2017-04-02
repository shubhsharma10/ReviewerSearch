/**
 * 
 */
package com.access;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the controller which handles user credentials and services
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public class AccessControl implements AccessControlAPI 
{
	/**
	 * This is a placeholder database. A functioning database to handle users is TBD.
	 */
	private static List<User> users = new ArrayList<User>();

	/**
	 * Registers the user to the database
	 * @param user the user instance to add to the database
	 */
	public void registerUser(User user) {
		users.add(user);
	}

	/**
	 * Deletes the user from the database
	 * @param username the name of the user to delete
	 */
	@Override
	public void deleteUser(String username) {
		for(User user : users) {
			if(user.getUsername().equals(username)) {
				users.remove(user);
				return;
			}
		}
		
	}

	/**
	 * Determines if the user's credentials are valid
	 * @param the user instance to inspect
	 */
	@Override
	public boolean validateUser(User user) {
		for(User u : users) {
			if(u.getUsername().equals(user.getUsername()) 
					&& u.getPassword().equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Determines if the user exists in the database
	 * @param username the username 
	 */
	@Override
	public boolean userExists(String username) {
		for(User u : users) {
			if(u.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Initiates a sample user for testing
	 */
	{
		User adminUser = new User("admin", "admin", "");
		users.add(adminUser);
		
	}
}
