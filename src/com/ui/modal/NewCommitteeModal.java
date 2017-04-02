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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
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
public class NewCommitteeModal extends JDialog implements ActionListener, PropertyChangeListener {

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
	private JButton bnUploadCommittee;
	
	/**
	 * The component which assists the user 
	 * in choosing the file path for the data source
	 */
	private JFileChooser chooser;
	
	/**
	 * The button group for the radio button options
	 */
	private ButtonGroup bg;
	
	/**
	 * The radio button component which a user can choose to upload by directory
	 */
	private JRadioButton rdDirectory;
	
	/**
	 * The radio button component which a user can choose to upload by a single file
	 */
	private JRadioButton rdFile;
	
	/**
	 * The textbox component which specifies the user's database name to put data in
	 */
	private JTextField textDBName;
	
	/**
	 * The container which houses the database specification
	 */
	private Container dbSpecific;
	
	

	/**
	 * The constructor class which instantiates all data members and components
	 * @param parent the JFrame component this Modal was opened from
	 */
	public NewCommitteeModal(JFrame parent) {
		super(parent, "Add New Database");
		this.setLocationRelativeTo(parent);
		getContentPane().setLayout(new GridLayout(4, 1, 0, 0));
		
		Container c2 = new Container();
		c2.setLayout(new FlowLayout());
		rdDirectory = new JRadioButton("Directory");
		rdDirectory.addActionListener(this);
		rdFile = new JRadioButton("File");
		rdFile.addActionListener(this);
		bg = new ButtonGroup();
		bg.add(rdDirectory);
		bg.add(rdFile);
		c2.add(rdDirectory);
		c2.add(rdFile);
		rdDirectory.setSelected(true);
		getContentPane().add(c2);

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
		
		dbSpecific = new Container();
		dbSpecific.setLayout(new FlowLayout());
		JLabel lblInsertDB = new JLabel("To which database?: ");
		textDBName = new JTextField();
		textDBName.setColumns(12);
		dbSpecific.add(lblInsertDB);
		dbSpecific.add(textDBName);
		dbSpecific.setVisible(false);
		getContentPane().add(dbSpecific);

		Container c3 = new Container();
		bnUploadCommittee = new JButton("Upload Committee Info");
		bnUploadCommittee.addActionListener(this);
		c3.add(bnUploadCommittee);
		c3.setLayout(new FlowLayout());
		getContentPane().add(c3);
		
		chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "xml");
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
			chooser.setFileSelectionMode(
					rdDirectory.isSelected() ? 
							JFileChooser.DIRECTORIES_ONLY : JFileChooser.FILES_ONLY);
			
			int val = chooser.showOpenDialog(this);
			if (val == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				txtPath.setText(file.getPath());
			}
		} else if(evt == bnUploadCommittee) {
			if(txtPath.getText().equals("")) {
				JOptionPane.showMessageDialog(this,
					    "Please specify a file name.",
					    "Specify file name or directory",
					    JOptionPane.INFORMATION_MESSAGE);
			} else {
				try {
					RootWindow.getSessionSingleton().setControllerType(new FrontEndController());
					FrontEndController front = (FrontEndController) 
							RootWindow.getSessionSingleton().getController();
					String path = txtPath.getText();
					String db = textDBName.getText();
					if(rdDirectory.isSelected() || 
							(this.textDBName.getText().isEmpty() && this.rdFile.isSelected())) {
						front.addNewDB(path);
					}
					if(rdDirectory.isSelected()) {
						front.fetchCommittees(path);
					} else {
						front.fetchCommittees(path, db);
						front.fetchCommittees(path);
					}
					JOptionPane.showMessageDialog(this,
						    "Committee successfully added!",
						    "Success!",
						    JOptionPane.PLAIN_MESSAGE);
					this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else if(evt == rdFile) {
			dbSpecific.setVisible(true);
		} else if(evt == rdDirectory) {
			dbSpecific.setVisible(false);
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
