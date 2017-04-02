package com.access;

/**
 * The class that defines the user details that are required 
 * to identify a user and make any modifications to it
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public class User 
{
	/**
	 * The user's username
	 */
	private String userName;
	
	/**
	 * The user's password
	 */
	private String password;
	
	/**
	 * The user's title position
	 */
	private String title;

	/**
	 * Creates a new user instance with no title specification
	 * @param userName the username of the user
	 * @param password the password of the user
	 */
	public User(String userName, String password) {
		this(userName, password, "");
	}

	/**
	 * Creates a new user instance with a title specification
	 * @param userName the username of the user
	 * @param password the password of the user
	 * @param the title of the user
	 */
	public User(String userName, String password, String title) {
		this.userName = userName;
		this.password = password;
		this.title = title;
	}

	/**
	 * Retrieves the username linked to the user instance
	 * @return the username of the user
	 */
	public String getUsername() {
		return this.userName;
	}

	/**
	 * Retrieves the title linked to the user instance
	 * @return the title of the user
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the username linked to the user instance
	 * @return the same user instance
	 */
	public String setUsername() {
		return this.userName;
	}

	/**
	 * Sets the title linked to the user instance
	 * @return the title user instance
	 */
	public String setTitle() {
		return this.title;
	}
	
	/**
	 * Gets the password linked to the user instance
	 * @return the user's password
	 */
	public String getPassword() {
		return this.password;
	}
}





