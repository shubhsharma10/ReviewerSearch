package com.ui.modal;
import java.awt.Container;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import com.controller.FrontEndController;
import com.ui.RootWindow;


/**
 * GUI Class which helps manage 
 * databases linked to the application
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 *
 */
public class EditDatabaseModal extends JDialog implements ActionListener, PropertyChangeListener {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -4072967758749036960L;
	
	/**
	 * The button component which deletes databases linked to application.
	 */
	private JButton bndelete;
	
	/**
	 * The list of databases currently linked to the application.
	 */
	private JList<String> listDB;
	
	/**
	 * The default list model which houses the list of databases.
	 */
	private DefaultListModel<String> model;


	/**
	 * The constructor class which instantiates all data members and components
	 * @param parent the JFrame component this Modal was opened from
	 */
	public EditDatabaseModal(JFrame parent) {
		super(parent, "Edit Database");
		this.setLocationRelativeTo(parent);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		Container c1 = new Container();

		GridBagLayout gbl_c1 = new GridBagLayout();
		gbl_c1.columnWidths = new int[]{300, 0};
		gbl_c1.rowHeights = new int[]{30, 200, 0};
		gbl_c1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_c1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		c1.setLayout(gbl_c1);

		JLabel lblEditDb = new JLabel("Database List");
		GridBagConstraints gbc_lblEditDb = new GridBagConstraints();
		gbc_lblEditDb.fill = GridBagConstraints.BOTH;
		gbc_lblEditDb.insets = new Insets(0, 0, 5, 0);
		gbc_lblEditDb.gridx = 0;
		gbc_lblEditDb.gridy = 0;
		c1.add(lblEditDb, gbc_lblEditDb);
		getContentPane().add(c1);

		prefetchDatabases();
		listDB.setPreferredSize(new Dimension(300, 200));
		GridBagConstraints gbc_listDB = new GridBagConstraints();
		gbc_listDB.fill = GridBagConstraints.BOTH;
		gbc_listDB.gridx = 0;
		gbc_listDB.gridy = 1;
		c1.add(listDB, gbc_listDB);

		Container c2 = new Container();
		c2.setLayout(new FlowLayout());
		bndelete = new JButton("Delete");
		bndelete.addActionListener(this);
		c2.add(bndelete);
		getContentPane().add(c2);

		this.pack();
		this.setVisible(true);
	}

	/**
	 * A method which preprocesses all of the databases linked to application
	 */
	public void prefetchDatabases() {
		try {
			RootWindow.getSessionSingleton().setControllerType(new FrontEndController());
			String[] indices = ((FrontEndController) 
					RootWindow.getSessionSingleton().getController()).getIndices();
			model = new DefaultListModel<String>();
			for(String s : indices)
				model.addElement(s);
			listDB = new JList<String>(model);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Determines if there was any changes to the component and refreshes window
	 * @param evt The event component which was adjusted
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		revalidate();
		repaint();
	}


	/**
	 * Handles all the user interactions for this modal window
	 * @param e The event component which was interacted
	 */
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == bndelete) {
			try {
				int index = listDB.getSelectedIndex();
				FrontEndController front = (FrontEndController) 
						RootWindow.getSessionSingleton().getController();
				front.removeDB(listDB.getSelectedValue());
				model.remove(index);
				listDB.revalidate();
				listDB.repaint();
				revalidate();
				repaint();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}