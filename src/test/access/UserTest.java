package test.access;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.access.User;

public class UserTest {

	/**
	 * test method to check if the username is set
	 */
	@Test
	public void testSetUsername() {
		User user = new User("admin", null);
		user.setUsername();		
	}
	
	/**
	 * test method to check if the user name is retrieved 
	 */
	@Test
	public void testGetUsername(){
		User user = new User("admin",null);
		String expResult = user.setUsername();
		String result = user.getUsername();
		assertEquals(expResult, result);
}
	/**
	 * test method to check if the password is retrieved
	 */	
	@Test
	public void testGetPassword(){
		User user = new User(null,"admin");
		String result = user.getPassword();
		assertEquals("admin", result);
}
	/**
	 * test method to check if the title is set
	 */
	@Test
	public void testSetTitle() {
		User user = new User(null,null,"Test User");
		user.setTitle();		
	}
	/**
	 * test method to check if the title is retrieved
	 */
	@Test
	public void testGetTitle(){
		User user = new User(null,null,"Test User");
		String expResult = user.setTitle();
		String result = user.getTitle();
		assertEquals(expResult, result);
}
}
