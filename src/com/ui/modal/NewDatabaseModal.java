package com.ui.modal;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.controller.FrontEndController;
import com.ui.RootWindow;

/**
 * GUI Class which helps create new databases to link to the application
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 *
 */
public class NewDatabaseModal extends JDialog implements ActionListener, PropertyChangeListener {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -4072967758749036960L;
	
	/**
	 * The specified path of the data source
	 */
	private JTextField txtPath;
	
	/**
	 * The button component which enables the user to choose a file
	 */
	private JButton bnBrowse;
	
	/**
	 * The button component which enables the user to begin the uploading process.
	 */
	private JButton bnUploadDatabase;
	
	/**
	 * The component which assists the user 
	 * in choosing the file path for the data source
	 */
	private JFileChooser chooser;

	/**
	 * The constructor class which instantiates all data members and components
	 * @param parent the JFrame component this Modal was opened from
	 */
	public NewDatabaseModal(JFrame parent) {
		super(parent, "Add New Database");
		this.setLocationRelativeTo(parent);
		getContentPane().setLayout(new GridLayout(3, 1, 0, 0));

		Container c1 = new Container();
		c1.setLayout(new FlowLayout());
		JLabel lblChooseFile = new JLabel("Choose File:");
		c1.add(lblChooseFile);
		txtPath = new JTextField();
		c1.add(txtPath);
		txtPath.setColumns(25);
		txtPath.setEditable(false);
		bnBrowse = new JButton("Browse");
		bnBrowse.addActionListener(this);
		c1.add(bnBrowse);
		JLabel label_1 = new JLabel("");
		c1.add(label_1);
		getContentPane().add(c1);


		Container c3 = new Container();
		bnUploadDatabase = new JButton("Upload Database");
		bnUploadDatabase.addActionListener(this);
		c3.add(bnUploadDatabase);
		c3.setLayout(new FlowLayout());
		getContentPane().add(c3);
		
		chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		
		pack();
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	/**
	 * Handles all the user interactions for this modal window
	 * @param e The event component which was interacted
	 */
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == bnBrowse) {
			int val = chooser.showOpenDialog(this);
			if (val == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				txtPath.setText(file.getPath());
			}
		} else if(evt == bnUploadDatabase) {
			if(txtPath.getText().equals("")) {
				JOptionPane.showMessageDialog(this,
					    "Please specify a file name.",
					    "Specify file name",
					    JOptionPane.INFORMATION_MESSAGE);
			} else {
				try {
					RootWindow.getSessionSingleton().setControllerType(new FrontEndController());
					FrontEndController front = (FrontEndController) 
							RootWindow.getSessionSingleton().getController();
					String path = txtPath.getText();
					front.addNewDB(path.toLowerCase());
					front.fetchData(path.toLowerCase());
					JOptionPane.showMessageDialog(this,
						    "Database successfully added!",
						    "Success!",
						    JOptionPane.PLAIN_MESSAGE);
					this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
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


}
