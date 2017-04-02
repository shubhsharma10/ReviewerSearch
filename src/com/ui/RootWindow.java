package com.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.ui.modal.EditDatabaseModal;
import com.ui.modal.NewCommitteeModal;
import com.ui.modal.NewDatabaseModal;

/**
 * The root GUI component which serves as a container for the 
 * Dynamic panels that are used on it
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Rajul Kumar
 * @author Shubham Sharma
 */
public class RootWindow extends JFrame implements ActionListener {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -8470922149072236466L;
	
	/**
	 * The title of the program
	 */
	private static final String TITLE = "Candidate Searcher - Prototype";
	
	/**
	 * The main session singleton there can only be one instantiated per session
	 */
	private static Session session;

	/**
	 * The component which handles all operations on the top menu frame
	 */
	private JMenuBar menu;
	
	/**
	 * The menu option which handles all new operations the users want to perform
	 */
	private JMenu menuItemFileNew;
	
	/**
	 * The menu option which handles all operations users want to edit
	 */
	private JMenu optEdit;
	
	/**
	 * The menu option which lets the user add a database
	 */
	private JMenuItem dbAdd;
	
	/**
	 * The menu option which lets the user add a conference
	 */
	private JMenuItem confAdd;
	
	/**
	 * The menu option which lets the user add a committee
	 */
	private JMenuItem comAdd;
	
	/**
	 * The menu option which allows the user to edit databases linked to the application
	 */
	private JMenuItem menuItemEditDbs;
	
	/**
	 * The menu option which allows the user to edit conferences
	 */
	private JMenuItem menuItemEditConferences;
	
	/**
	 * The menu option which allows the user to edit committees
	 */
	private JMenuItem menuItemEditCommittees;
	
	/**
	 * The constructor class which initializes 
	 * all components and data members and creates a new window
	 */
	public RootWindow() {
		super(TITLE);
		// Helper function for menu bar to reduce clutter
		buildMenuBar();
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		getContentPane().add(new LoginHandler());
		setSize(700, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		listen();
		setVisible(true);
	}
	
	/**
	 * Builds the visual menu bar for the user interface during instantiation
	 */
	private void buildMenuBar() {
		menu = new JMenuBar();
		
		//File Menu Option
		JMenu optFile = new JMenu("File");
		optFile.setMnemonic(KeyEvent.VK_F);
		
		//Quit Option
		JMenuItem menuItemFileQuit = new JMenuItem("Quit", KeyEvent.VK_Q);
		
		//New Option
		menuItemFileNew = new JMenu("New");
		dbAdd = new JMenuItem("Database", KeyEvent.VK_D);
		confAdd = new JMenuItem("Conference", KeyEvent.VK_D);
		confAdd.setVisible(false); //To be implemented in Phase IV
		comAdd = new JMenuItem("Committee", KeyEvent.VK_D);
		
		dbAdd.addActionListener(this);
		confAdd.addActionListener(this);
		comAdd.addActionListener(this);
		menuItemFileNew.add(dbAdd);
		menuItemFileNew.add(confAdd);
		menuItemFileNew.add(comAdd);
		
		optFile.add(menuItemFileNew);
		optFile.add(menuItemFileQuit);
		menuItemFileQuit.addActionListener(new ActionListener() {
			/**
			 * Action Listener which allows the user to quit
			 * @param e The action event the component is listening on
			 */
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//Edit option
		optEdit = new JMenu("Edit");
		optEdit.setMnemonic(KeyEvent.VK_E);
		
		menuItemEditDbs = new JMenuItem("Databases");
		menuItemEditDbs.addActionListener(this);
		
		menuItemEditConferences = new JMenuItem("Conferences");
		menuItemEditConferences.setVisible(false); //To be implemented in Phase IV
		menuItemEditConferences.addActionListener(this);
		
		menuItemEditCommittees = new JMenuItem("Committees");
		menuItemEditCommittees.setVisible(false); //To be implemented in Phase IV
		menuItemEditCommittees.addActionListener(this);
		
		optEdit.add(menuItemEditDbs);
		optEdit.add(menuItemEditConferences);
		optEdit.add(menuItemEditCommittees);

		menu.add(optFile);
		menu.add(optEdit);
		setJMenuBar(menu);
	}
	
	/**
	 * Hides certain menu options if the user isn't logged in
	 */
	private void determineMenuBarConceal() {
		PanelType currPanel = session.getPanelType();
		boolean isOffline = (currPanel == PanelType.LOGIN || currPanel == PanelType.REGISTER);
		if(isOffline) {
			menuItemFileNew.setVisible(false);
			optEdit.setVisible(false);
		} else if(!isOffline) {
			menuItemFileNew.setVisible(true);
			optEdit.setVisible(true);
		}
	}
	
	/**
	 * Method which dynamically swaps different panels on the same JFrame
	 * @param handle the new panel to swap
	 */
	public void attach(Handler handle) {
		Container pane = getContentPane();
		pane.removeAll();
		pane.add(handle);
		pane.revalidate();
		pane.repaint();
		updatePanelType();
		this.pack();
	}
	
	/**
	 * Updates the panel type to see different features/data for each panel
	 */
	public void updatePanelType() {
		Component pane = this.getContentPane().getComponents()[0];
		if(pane instanceof LoginHandler) {
			session.setPanelType(PanelType.LOGIN);
		} else if(pane instanceof ProfileHandler) {
			session.setPanelType(PanelType.PROFILE);
		} else if(pane instanceof RegisterHandler) {
			session.setPanelType(PanelType.REGISTER);
		} else if(pane instanceof SearchHandler) {
			session.setPanelType(PanelType.SEARCH);
		} else if(pane instanceof SearchResultHandler) {
			session.setPanelType(PanelType.SEARCH_RESULT);
		}
		determineMenuBarConceal();
	}
	
	/**
	 * Retrieves the session that's attached to the application
	 * @return the session attached to this execution
	 */
	public static Session getSessionSingleton() {
		return session;
	}
	
	public Component getCurrentWindow() {
		return this.getContentPane().getComponent(0);
	}
	
	/**
	 * Instantiates a new session for the entire application usage
	 */
	private void listen() {
		session = new Session();
		updatePanelType();
	}

	/**
	 * Handles all the user interactions for this window
	 * @param e The event component which was interacted
	 */
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == dbAdd) {
			new NewDatabaseModal(this);
		} else if(src == menuItemEditDbs) {
			new EditDatabaseModal(this);
		} else if(src == comAdd) {
			new NewCommitteeModal(this);
		}
		
	}
	
}
