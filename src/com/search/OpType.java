package com.search;

/**
 * The Enum file which represents a operation allowed in a query
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public enum OpType 
{
	NONE,
	
	// If any word is present
	HAS_ANY,
	
	// If this phrase is present in row
	HAS_PHRASE,
	
	// If this phrase is present as a prefix
	HAS_PREFIX,
	
	// Numeric value comparison
	EQUAL,
	NOT_EQUAL,
	LESSER,
	GREATER,
	LESS_EQUAL,
	GREAT_EQUAL,
	BETWEEN_INCL,
	BETWEEN_EXCL
}


