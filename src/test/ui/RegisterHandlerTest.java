//package test.ui;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JPasswordField;
//import javax.swing.JTextField;
//
//import com.ui.Handler;
//import com.ui.LoginHandler;
//import com.ui.RegisterHandler;
//import com.ui.RootWindow;
//
//import abbot.finder.ComponentNotFoundException;
//import abbot.finder.MultipleComponentsFoundException;
//import abbot.tester.JButtonTester;
//import abbot.tester.JTextComponentTester;
//import junit.extensions.abbot.ComponentTestFixture;
//import junit.extensions.abbot.TestHelper;
//
//
//
//public class RegisterHandlerTest extends ComponentTestFixture {
//
//	Handler h;
//	RootWindow rt;
//	@SuppressWarnings("unused")
//	private String gotClick;
//	@SuppressWarnings("unused")
//	/**
//	 * test method to check a valid registration
//	 * when all the credentials are filled in correctly
//	 * @throws ComponentNotFoundException
//	 * @throws MultipleComponentsFoundException
//	 */
//	public void testValidRegistration() throws ComponentNotFoundException, MultipleComponentsFoundException {
//		ActionListener al = new ActionListener() {
//			public void actionPerformed(ActionEvent ev) {
//				gotClick = ev.getActionCommand();                            
//
//			}};
//
//			rt = new RootWindow();			
//			h = (LoginHandler) rt.getCurrentWindow();
//			
//			JButton regButton = ((LoginHandler) h).getBnRegister();
//			JButtonTester tester = new JButtonTester();
//			tester.actionClick(regButton);
//			
//			h = (RegisterHandler) rt.getCurrentWindow();
//			RegisterHandler register = (RegisterHandler) h;
//			
//		    JTextField textField = register.getTextUsername();
//		    JTextComponentTester userTester = new JTextComponentTester();
//		    userTester.actionEnterText(textField, "johnsmith");
//
//		    JPasswordField pwdField = register.getTextPassword();
//			JTextComponentTester passTester = new JTextComponentTester();
//			passTester.actionEnterText(pwdField, "123456");
//
//		    JPasswordField verPwdField = register.getTextConfirm();
//			JTextComponentTester verTester = new JTextComponentTester();
//			verTester.actionEnterText(verPwdField, "123456");
//
//		    JTextField emailField = register.getTextEmail();
//			JTextComponentTester emailTester = new JTextComponentTester();
//			emailTester.actionEnterText(emailField, "johnsmith"+ '@' +"email.com");
//
//			JButton registerButton = register.getBnRegister();
//		    assertEquals("Wrong Button after selection", 
//	   		     "Register", registerButton.getText());
//		    JButtonTester bnRegisterTest = new JButtonTester();
//		    bnRegisterTest.actionClick(registerButton);
//
//	}
//	/**
//	 * test method to check an invalid registration
//	 * when the password and check password fields
//	 * do not match
//	 * @throws ComponentNotFoundException
//	 * @throws MultipleComponentsFoundException
//	 */
//	public void testPasswordMismatch() throws ComponentNotFoundException, MultipleComponentsFoundException {
//		@SuppressWarnings("unused")
//		ActionListener al = new ActionListener() {
//			public void actionPerformed(ActionEvent ev) {
//				gotClick = ev.getActionCommand();                            
//
//			}};
//
//			rt = new RootWindow();			
//			h = (LoginHandler) rt.getCurrentWindow();
//			
//			JButton regButton = ((LoginHandler) h).getBnRegister();
//			JButtonTester tester = new JButtonTester();
//			tester.actionClick(regButton);
//			
//			h = (RegisterHandler) rt.getCurrentWindow();			
//			RegisterHandler register = (RegisterHandler) h;
//			
//		    JTextField textField = register.getTextUsername();
//		    JTextComponentTester userTester = new JTextComponentTester();
//		    userTester.actionEnterText(textField, "Hans Will");
//
//		    JPasswordField pwdField = register.getTextPassword();
//			JTextComponentTester passTester = new JTextComponentTester();
//			passTester.actionEnterText(pwdField, "123456");
//
//		    JPasswordField verPwdField = register.getTextConfirm();
//			JTextComponentTester verTester = new JTextComponentTester();
//			verTester.actionEnterText(verPwdField, "xyzs");
//
//		    JTextField emailField = register.getTextEmail();
//			JTextComponentTester emailTester = new JTextComponentTester();
//			emailTester.actionEnterText(emailField, "hanswill"+ '@' +"email.com");
//
//			JButton registerButton = register.getBnRegister();
//		    assertEquals("Wrong Button after selection", 
//	   		     "Register", registerButton.getText());
//		    JButtonTester bnRegisterTest = new JButtonTester();
//		    bnRegisterTest.actionClick(registerButton);
//
//	}
//	/**
//	 * test method to check an invalid registration
//	 * when one or many required credentials are left blank
//	 * @throws ComponentNotFoundException
//	 * @throws MultipleComponentsFoundException
//	 */
//	public void testBlankTextFields() throws ComponentNotFoundException, MultipleComponentsFoundException {
//		@SuppressWarnings("unused")
//		ActionListener al = new ActionListener() {
//			public void actionPerformed(ActionEvent ev) {
//				gotClick = ev.getActionCommand();                            
//
//			}};
//
//			rt = new RootWindow();			
//			h = (LoginHandler) rt.getCurrentWindow();
//			
//			JButton regButton = ((LoginHandler) h).getBnRegister();
//			JButtonTester tester = new JButtonTester();
//			tester.actionClick(regButton);	
//			
//			h = (RegisterHandler) rt.getCurrentWindow();			
//			RegisterHandler register = (RegisterHandler) h;
//			
//		    JTextField textField = register.getTextUsername();
//		    JTextComponentTester userTester = new JTextComponentTester();
//		    userTester.actionEnterText(textField, "");
//
//		    JPasswordField pwdField = register.getTextPassword();
//			JTextComponentTester passTester = new JTextComponentTester();
//			passTester.actionEnterText(pwdField, "123456");
//
//		    JPasswordField verPwdField = register.getTextConfirm();
//			JTextComponentTester verTester = new JTextComponentTester();
//			verTester.actionEnterText(verPwdField, "xyzs");
//
//		    JTextField emailField = register.getTextEmail();
//			JTextComponentTester emailTester = new JTextComponentTester();
//			emailTester.actionEnterText(emailField, "hans"+ '@' +"email.com");
//
//			JButton registerButton = register.getBnRegister();
//		    assertEquals("Wrong Button after selection", 
//	   		     "Register", registerButton.getText());
//		    JButtonTester bnRegisterTest = new JButtonTester();
//		    bnRegisterTest.actionClick(registerButton);
//
//	}
//	/**
//	 * test method to check if correct action 
//	 * is performed when login button is clicked
//	 * @throws ComponentNotFoundException
//	 * @throws MultipleComponentsFoundException
//	 */
//	public void testLoginButton() throws ComponentNotFoundException, MultipleComponentsFoundException {
//		@SuppressWarnings("unused")
//		ActionListener al = new ActionListener() {
//			public void actionPerformed(ActionEvent ev) {
//				gotClick = ev.getActionCommand();                            
//
//			}};
//
//			rt = new RootWindow();			
//			h = (LoginHandler) rt.getCurrentWindow();
//			
//			JButton regButton = ((LoginHandler) h).getBnRegister();
//			JButtonTester tester = new JButtonTester();
//			tester.actionClick(regButton);
//			
//			h = (RegisterHandler) rt.getCurrentWindow();			
//			RegisterHandler register = (RegisterHandler) h;
//		    
//			JButton loginButton = register.getBnLogin();
//		    assertEquals("Wrong Button after selection", 
//	   		     "Login", loginButton.getText());
//		    JButtonTester bnLoginTest = new JButtonTester();
//		    bnLoginTest.actionClick(loginButton);
//
//	}
//	/**
//	 * test method to check if fields are cleared 
//	 * when clear fields button is clicked
//	 * @throws ComponentNotFoundException
//	 * @throws MultipleComponentsFoundException
//	 */ 
//	public void testClearField() throws ComponentNotFoundException, MultipleComponentsFoundException {
//		@SuppressWarnings("unused")
//		ActionListener al = new ActionListener() {
//			public void actionPerformed(ActionEvent ev) {
//				gotClick = ev.getActionCommand();                            
//
//			}};
//
//			rt = new RootWindow();			
//			h = (LoginHandler) rt.getCurrentWindow();
//			
//			JButton regButton = ((LoginHandler) h).getBnRegister();
//			JButtonTester tester = new JButtonTester();
//			tester.actionClick(regButton);
//			
//			h = (RegisterHandler) rt.getCurrentWindow();			
//			RegisterHandler register = (RegisterHandler) h;
//			
//		    JTextField textField = register.getTextUsername();
//		    JTextComponentTester userTester = new JTextComponentTester();
//		    userTester.actionEnterText(textField, "abcd");
//
//		    JPasswordField pwdField = register.getTextPassword();
//			JTextComponentTester passTester = new JTextComponentTester();
//			passTester.actionEnterText(pwdField, "123456");
//
//		    JPasswordField verPwdField = register.getTextConfirm();
//			JTextComponentTester verTester = new JTextComponentTester();
//			verTester.actionEnterText(verPwdField, "xyzs");
//
//		    JTextField emailField = register.getTextEmail();
//			JTextComponentTester emailTester = new JTextComponentTester();
//			emailTester.actionEnterText(emailField, "abcd"+ '@' +"email.com");
//
//			JButton clearButton = register.getBnClear();
//		    assertEquals("Wrong Button after selection", 
//	   		     "Clear Fields", clearButton.getText());
//		    JButtonTester bnClearTest = new JButtonTester();
//		    bnClearTest.actionClick(clearButton);
//
//	}
//	
//	
//
//	public RegisterHandlerTest(String name) { super(name); }
//
//	public static void main(String[] args) {
//		TestHelper.runTests(args, RegisterHandlerTest.class);
//	}
//}