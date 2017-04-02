package com.front;

import java.net.UnknownHostException;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * The API provided for all components for all operations to be used for front end
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public interface FrontEndAPI
{
	/**
	 * Method which handles the addition of a new database source
	 * @param path parameter for the path from which to retrieve database
	 * @throws UnknownHostException if the method cannot connect to the server
	 */
	public void addDBSource(String path) throws UnknownHostException;
	
	/**
	 * Method which fetches database from a given database name,which has been
	 * added already
	 * @param dbName name of the database which needs to be fetched
	 * @throws JsonProcessingException if there is an illegal parsing rule
	 */
	public void fetchData(String dbName) throws JsonProcessingException;
	
	/**
	 * Method which removes an existing database
	 * @param dbName name of the database which needs to be removed
	 */
	public void removeDB(String dbName);
	
	/**
	 * Mehtod called to add the data fetched from the data source to the database
	 */
	public void updateDatabase();
}
