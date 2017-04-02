package com.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.controller.AccessController;

/**
 * GUI Class which helps manage 
 * databases linked to the application
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 *
 */
public class LoginHandler extends Handler {

	/**
	 * Generated Serial Version UID.
	 */
	private static final long serialVersionUID = 2983070574094726674L;


	/**
	 * The user's username to log into
	 */
	private String username;

	/**
	 * The user's password to log into
	 */
	private String password;
	
	/**
	 * The component where the user inputs their username
	 */
	private JTextField textUsername;
	
	/**
	 * The button component which logins the user
	 */
	private JButton bnLogin;
	
	/**
	 * The button component which directs the user to the registration page
	 */
	private JButton bnRegister;
	
	/**
	 * The button component which clears all the text fields for the user
	 */
	private JButton bnClear;
	
	/**
	 * The username label
	 */
	private JLabel lbUsername;
	
	/**
	 * The password label
	 */
	private JLabel lbPassword;
	
	/**
	 * The textfield which stores the user's password
	 */
	private JPasswordField textPassword;

	

	/**
	 * The main class which sends an initialization call to
	 * instantiate all data members and components
	 */
	public LoginHandler() {
		initialize();
	}


	/**
	 * The initialization class which instantiates all data members and components
	 */
	public void initialize() {
		setLayout(new GridLayout(5, 1, 0, 0));

		Container c1 = new Container();
		add(c1);

		c1.setLayout(new FlowLayout());

		lbUsername = new JLabel("Username:");
		c1.add(lbUsername);

		textUsername = new JTextField();
		textUsername.setColumns(15);
		c1.add(textUsername);

		Container c2 = new Container();
		c2.setLayout(new FlowLayout());
		add(c2);

		lbPassword = new JLabel("Password:");
		c2.add(lbPassword);

		textPassword = new JPasswordField();
		textPassword.setColumns(15);
		c2.add(textPassword);

		Container c3 = new Container();
		c3.setEnabled(false);
		add(c3);
		c3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		bnLogin = new JButton("Login");
		bnLogin.addActionListener(this);
		c3.add(bnLogin);

		bnRegister = new JButton("Register");
		bnRegister.addActionListener(this);
		c3.add(bnRegister);

		bnClear = new JButton("Clear Fields");
		bnClear.addActionListener(this);
		c3.add(bnClear);
	}

	/**
	 * Saves the session singleton's currently logged in user
	 */
	public void save() {
		if(username != null) {
			RootWindow.getSessionSingleton().setUsername(username);
		}
	}

	/**
	 * Handles all the user interactions for this panel
	 * @param e The event component which was interacted
	 */
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		RootWindow ancestor = (RootWindow) SwingUtilities.getWindowAncestor(this);
		if(src == bnRegister) {
			ancestor.attach(new RegisterHandler());
		} else if(src == bnClear) {
			clearFields();
		} else if(src == bnLogin) {
			login();
		}
	}

	/**
	 * Method which clears all fields for this login form.
	 */
	@Override
	protected void clearFields() {
		if(textUsername != null && textPassword != null) {
			textUsername.setText("");
			textPassword.setText("");
		}
	}

	/**
	 * The method which logs in the user and verifies credentials
	 */
	private void login() {
		RootWindow ancestor = (RootWindow) SwingUtilities.getWindowAncestor(this);
		RootWindow.getSessionSingleton().setControllerType(new AccessController());
		AccessController ac = 
				(AccessController) RootWindow.getSessionSingleton().getController();
		username = textUsername.getText();
		password = new String(textPassword.getPassword());
		if(ac.verify(username, password)) {
			save();
			ancestor.attach(new SearchHandler());
		} else {
			JOptionPane.showMessageDialog(this,
					"Invalid username or password.",
					"Invalid credentials",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Getters used for testing purposes
	 */
	public JButton getBnLogin() {
		return bnLogin;
	}


	public JButton getBnRegister() {
		return bnRegister;
	}


	public JButton getBnClear() {
		return bnClear;
	}


	public JTextField getTextUsername() {
		return textUsername;
	}

	public JPasswordField getTextPassword() {
		return textPassword;
	}
	public void setTextUsername(JTextField textUsername) {
		this.textUsername = textUsername;
	}


	public void setTextPassword(JPasswordField textPassword) {
		this.textPassword = textPassword;
	}


	
}
