package com.ui;

import com.controller.BaseController;

/**
 * Represents a single session during the application's execution
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public class Session {

	/**
	 * The username attached to the session
	 */
	private String username;
	
	/**
	 * The session's current controller for all server/client interactions
	 */
	private BaseController base;
	
	/**
	 * The current panel the session is on
	 */
	private PanelType panel;
	
	/**
	 * The search data that is currently being stored 
	 */
	private SearchHandler searchData;
	
	/**
	 * Instantiates a new session and instantiates the controller and Dao
	 */
	public Session() {
		this("");
		this.base = new BaseController();
	}
	
	/**
	 * Instantiates a new session under a specific username
	 * @param username the new username that should be attached to the session
	 */
	public Session(String username) {
		this.username = username;
		this.base = new BaseController();
	}

	/**
	 * Retrieves the username
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username of the session
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Sets the type of the panel
	 * @param panel the type of panel the user is currently on
	 */
	public void setPanelType(PanelType panel) {
		this.panel = panel;
	}
	
	/**
	 * Retrieves the type of panel the session is currently on
	 * @return the current session's panel type
	 */
	public PanelType getPanelType() {
		return panel;
	}

	/**
	 * Gets the session's current controller
	 * @return The current controller settings
	 */
	public BaseController getController() {
		return base;
	}
	
	/**
	 * Sets the current session's controller
	 * @param base The new controller to instantiate
	 */
	public void setControllerType(BaseController base) {
		this.base = base;
	}

	/**
	 * Retrieves the search data
	 * @return The search data to be loaded into the panel
	 */
	public SearchHandler getSearchData() {
		return searchData;
	}

	/**
	 * Saves the search data to the current session
	 * @param searchData the search data to save
	 */
	public void setSearchData(SearchHandler searchData) {
		this.searchData = searchData;
	}
	
	
	
	

}
