//package test.ui;
//
//
//import java.awt.event.*;
//import javax.swing.JButton;
//import javax.swing.JPasswordField;
//import javax.swing.JTextField;
//import junit.extensions.abbot.*;
//import com.ui.Handler;
//import com.ui.LoginHandler;
//import com.ui.RootWindow;
//import abbot.finder.ComponentNotFoundException;
//import abbot.finder.MultipleComponentsFoundException;
//import abbot.tester.*;
//
//
//public class LoginHandlerTest extends ComponentTestFixture {
//
//	Handler h;
//	RootWindow rt;
//	@SuppressWarnings("unused")
//	private  String gotClick;
//	@SuppressWarnings("unused")
//	/**
//	 * test method to check a valid login
//	 * with correct credentials for username 
//	 * and password as admin
//	 * @throws ComponentNotFoundException
//	 * @throws MultipleComponentsFoundException
//	 */
//	public  void testValidLogin() throws ComponentNotFoundException, MultipleComponentsFoundException {
//		ActionListener al = new ActionListener() {
//			public void actionPerformed(ActionEvent ev) {
//				gotClick = ev.getActionCommand();                            
//			
//			}};
//						
//		    rt = new RootWindow();			
//			h = (LoginHandler) rt.getCurrentWindow();
//			LoginHandler login = (LoginHandler) h;
//			
//			JTextField textField = login.getTextUsername();
//		    JTextComponentTester userTester = new JTextComponentTester();
//		    userTester.actionEnterText(textField, "admin");
//
//		    JPasswordField pwdField = login.getTextPassword();
//			JTextComponentTester passTester = new JTextComponentTester();
//			passTester.actionEnterText(pwdField, "admin");
//			
//			JButton loginButton = ((LoginHandler) h).getBnLogin();
//			JButtonTester tester = new JButtonTester();
//			tester.actionClick(loginButton);
//			
//			
//	}
//	/**
//	 * test method to check if correct action 
//	 * is performed when register button is clicked
//	 * @throws ComponentNotFoundException
//	 * @throws MultipleComponentsFoundException
//	 */
//	public  void testRegisterButton() throws ComponentNotFoundException, MultipleComponentsFoundException {
//		@SuppressWarnings("unused")
//		ActionListener al = new ActionListener() {
//			public void actionPerformed(ActionEvent ev) {
//				gotClick = ev.getActionCommand();                            
//			
//			}};		
//			
//		    rt = new RootWindow();			
//			h = (LoginHandler) rt.getCurrentWindow();
//			@SuppressWarnings("unused")
//			LoginHandler login = (LoginHandler) h;
//
//			JButton registerButton = ((LoginHandler) h).getBnRegister();
//			JButtonTester test = new JButtonTester();
//			test.actionClick(registerButton);
//	}
//	/**
//	 * test method to check if fields are cleared 
//	 * when clear fields button is clicked
//	 * @throws ComponentNotFoundException
//	 * @throws MultipleComponentsFoundException
//	 */ 
//	public  void testClearButton() throws ComponentNotFoundException, MultipleComponentsFoundException {
//		@SuppressWarnings("unused")
//		ActionListener al = new ActionListener() {
//			public void actionPerformed(ActionEvent ev) {
//				gotClick = ev.getActionCommand();                            
//			
//			}};
//			
//		    rt = new RootWindow();
//			h = (LoginHandler) rt.getCurrentWindow();
//			LoginHandler login = (LoginHandler) h;
//			
//			JTextField textField = login.getTextUsername();
//		    JTextComponentTester userTester = new JTextComponentTester();
//		    userTester.actionEnterText(textField, "abcd");
//
//		    JPasswordField pwdField = login.getTextPassword();
//			JTextComponentTester passTester = new JTextComponentTester();
//			passTester.actionEnterText(pwdField, "password");
//
//			JButton clearButton = ((LoginHandler) h).getBnClear();
//			JButtonTester testClear = new JButtonTester();
//			testClear.actionClick(clearButton);
//	}
//	/**
//	 * test method to check a invalid login
//	 * with incorrect credentials for username 
//	 * and password 
//	 * @throws ComponentNotFoundException
//	 * @throws MultipleComponentsFoundException
//	 */
//	public  void testInvalidLogin() throws ComponentNotFoundException, MultipleComponentsFoundException {
//		@SuppressWarnings("unused")
//		ActionListener al = new ActionListener() {
//			public void actionPerformed(ActionEvent ev) {
//				gotClick = ev.getActionCommand();                            
//			
//			}};
//			
//		    rt = new RootWindow();			
//			h = (LoginHandler) rt.getCurrentWindow();
//			LoginHandler login = (LoginHandler) h;
//			
//			JTextField textField = login.getTextUsername();
//		    JTextComponentTester userTester = new JTextComponentTester();
//		    userTester.actionEnterText(textField, "abcd");
//
//		    JPasswordField pwdField = login.getTextPassword();
//			JTextComponentTester passTester = new JTextComponentTester();
//			passTester.actionEnterText(pwdField, "password");
//			
//			JButton loginButton = ((LoginHandler) h).getBnLogin();
//			JButtonTester tester = new JButtonTester();
//			tester.actionClick(loginButton);
//	
//	}
//
//	public LoginHandlerTest(String name) { super(name); }
//
//	public static void main(String[] args) {
//		TestHelper.runTests(args, LoginHandlerTest.class);
//	}
//}