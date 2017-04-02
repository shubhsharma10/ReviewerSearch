package com.access;

/**
 * The API provided for all components for all operations to be used for Controller
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public interface AccessControlAPI
{
	/**
	 * Method which handles the registration of a new user
	 * @param user new user which needs to be added
	 * @since The class for User is not implemented the object type will
	 * remain as an object until implementation.
	*/
	public void registerUser(User user);
	
	/**
	 * Method which handles the deletion of an existing user
	 * @param user user which needs to be deleted
	 * @since The class for User is not implemented the object type will
	 * remain as an object until implementation.
	*/
	public void deleteUser(String username);
	
	/**
	 * Method which handles the verification of a user
	 * @param user user which needs to be verified
	 * @since The class for User is not implemented the object type will
	 * remain as an object until implementation.
	*/
	public boolean validateUser(User user);
	
	/**
	 * Determines if the user exists in the database
	 * @param username the username to find
	 * @return if the username is in the database or not.
	 */
	public boolean userExists(String username);

}
