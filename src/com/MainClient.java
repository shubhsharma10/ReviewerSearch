package com;

import com.ui.RootWindow;

/**
 * The class file which represents the main application
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public class MainClient {

	
	/**
	 * The main method which executes the application
	 * @param args additional arguments given by the console
	 */
	public static void main(String[] args) {
		initialize();
	}
	
	/**
	 * Initializes all the window components
	 */
	private static void initialize() {
		new RootWindow(); 
	}
	

}
