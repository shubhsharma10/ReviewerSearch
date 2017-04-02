package com.ui;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * The class file which serves as a template for some of the common UI panels
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public abstract class Handler extends JPanel implements UIControllerAPI, ActionListener {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -1576077576130318917L;
	
	/**
	 * Method which clears all the text fields in a given UI
	 */
	protected abstract void clearFields();



}