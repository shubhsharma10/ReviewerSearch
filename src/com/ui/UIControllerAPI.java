package com.ui;

/**
 * The API provided for all user interfaces for all user interactions
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public interface UIControllerAPI {
	
	/**
	 * Initializes all of the components for the user interface
	 */
	public void initialize();
	
	/**
	 * Saves any data necescary for the components for the user to return to
	 */
	public void save();

}
