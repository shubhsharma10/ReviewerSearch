package com.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.controller.AccessController;

/**
 * GUI Class which handles registration for a user
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 */
public class RegisterHandler extends Handler {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -7059205018121128793L;

	/**
	 * The new user's username
	 */
	private String username;

	/**
	 * The new user's password
	 */
	private String password;

	/**
	 * The new user's email (Not currently used yet)
	 */
	@SuppressWarnings("unused")
	private String email;

	/**
	 * The textfield where the user inputs their username
	 */
	private JTextField textUsername;
	
	/**
	 * The textfield where the user inputs their email
	 */
	private JTextField textEmail;
	
	/**
	 * The textfield where the user inputs their password
	 */
	private JPasswordField textPassword;
	
	/**
	 * The textfield where the user verifies their password
	 */
	private JPasswordField textConfirm;
	
	/**
	 * The button component which registers the user into the system
	 */
	private JButton bnRegister;
	
	/**
	 * The button component which directs the user to the login panel
	 */
	private JButton bnLogin;
	
	/**
	 * The button component which clears all fields
	 */
	private JButton bnClear;

	/**
	 * The constructor which does an initialization call 
	 * to instantiate all components and data members
	 */
	public RegisterHandler() {
		initialize();
	}

	/**
	 * Initializes all components and data members.
	 */
	public void initialize() {
		setLayout(new GridLayout(5, 1));

		Container c1 = new Container();
		c1.setLayout(new FlowLayout());
		this.add(c1);

		JLabel lblUsername = new JLabel("Username: ");
		c1.add(lblUsername);

		textUsername = new JTextField();
		c1.add(textUsername);
		textUsername.setColumns(15);

		Container c2 = new Container();
		c2.setLayout(new FlowLayout());
		this.add(c2);

		JLabel lblPassword = new JLabel("Password: ");
		c2.add(lblPassword);

		textPassword = new JPasswordField();
		textPassword.setColumns(15);
		c2.add(textPassword);

		Container c3 = new Container();
		c3.setLayout(new FlowLayout());
		this.add(c3);

		JLabel lblConfirmPassword = new JLabel("Confirm Password: ");
		c3.add(lblConfirmPassword);

		textConfirm = new JPasswordField();
		textConfirm.setColumns(15);
		c3.add(textConfirm);

		Container c4 = new Container();
		c4.setLayout(new FlowLayout());
		this.add(c4);

		JLabel lblEmail = new JLabel("E-mail: ");
		c4.add(lblEmail);

		textEmail = new JTextField();
		c4.add(textEmail);
		textEmail.setColumns(15);

		Container c5 = new Container();
		c5.setLayout(new FlowLayout());
		this.add(c5);

		bnRegister = new JButton("Register");
		bnRegister.addActionListener(this);
		c5.add(bnRegister);

		bnLogin = new JButton("Login");
		bnLogin.addActionListener(this);
		c5.add(bnLogin);

		bnClear = new JButton("Clear Fields");
		bnClear.addActionListener(this);
		c5.add(bnClear);
	}

	/**
	 * Saves the username to the session if the registration is successful
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
		if(src == bnLogin) {
			save();
			ancestor.attach(new LoginHandler());
		} else if(src == bnClear) {
			clearFields();
		} else if(src == bnRegister) {
			register();
		}
	}

	/**
	 * Clears all the text fields for this form.
	 */
	@Override
	protected void clearFields() {
		if(textUsername != null && textPassword != null 
				&& textConfirm != null && textEmail != null) {
			textUsername.setText("");
			textPassword.setText("");
			textConfirm.setText("");
			textEmail.setText("");
		}
	}


	/**
	 * The method which assists the user in registration
	 */
	private void register() {
		RootWindow ancestor = (RootWindow) SwingUtilities.getWindowAncestor(this);
		RootWindow.getSessionSingleton().setControllerType(new AccessController());
		AccessController controller = ((AccessController) 
				RootWindow.getSessionSingleton().getController());
		if(!controller.userExists(textUsername.getText())) {
			username = textUsername.getText();
			if(username.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please enter a username.", "Enter username",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if(Arrays.equals(textPassword.getPassword(), textConfirm.getPassword())) {
				password = new String(textPassword.getPassword());
				if(password.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Please enter a password.", "Enter password",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				save();
				controller.registerUser(username, password);
				ancestor.attach(new SearchHandler());
			} else {
				JOptionPane.showMessageDialog(this,
						"Passwords do not match.",
						"Invalid match",
						JOptionPane.WARNING_MESSAGE);
				username = "";
				return;
			}
		} else {
			JOptionPane.showMessageDialog(this,
					"Username already exists.",
					"User exists",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Getters and setters used for testing purposes
	 */
	public JTextField getTextUsername() {
		return textUsername;
	}

	public JTextField getTextEmail() {
		return textEmail;
	}

	public JPasswordField getTextPassword() {
		return textPassword;
	}

	public JPasswordField getTextConfirm() {
		return textConfirm;
	}

	public JButton getBnRegister() {
		return bnRegister;
	}

	public JButton getBnLogin() {
		return bnLogin;
	}

	public JButton getBnClear() {
		return bnClear;
	}

}
