package com.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingUtilities;

import com.controller.QueryEngineController;
import com.dao.SearchResult;
import com.front.ArticleEntry;
import com.search.Filter;
import com.search.FilterOp;
import com.search.Filters;
import com.search.OpType;

/**
 * GUI Class which displays all relevant information
 * from a search query's row
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Shubham Sharma
 */
public class ProfileHandler extends Handler {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = 2853654165004680817L;

	/**
	 * The raw query info data
	 */
	private Object[] profileInfo;
	
	/**
	 * The label for the result's full name
	 */
	private JLabel lblFullName;
	
	/**
	 * The label for the result's title
	 */
	private JLabel lblTitle;
	
	/**
	 * The label for the result's number of pages.
	 */
	private JLabel lblPages;
	
	/**
	 * The label for the result's year of publication
	 */
	private JLabel lblYear;
	
	/**
	 * The label for the result's volume number.
	 */
	private JLabel lblVolume;
	
	/**
	 * The label for the result's journal name
	 */
	private JLabel lblJournal;
	
	/**
	 * The label for the result's number
	 */
	private JLabel lblNumber;
	
	/**
	 * The label for the result's URL
	 */
	@SuppressWarnings("unused")
	private JLabel lblUrl;
	
	/**
	 * The button component which shows the user relevant committee data
	 */
	private JButton btnCommittee;
	
	/**
	 * The button component which shoes the user relevant committee data
	 */
	private JButton btnArticles;
	
	/**
	 * The listbox component which stores all of the user's chosen relevant information
	 */
	private JList<String> list;

	/**
	 * The constructor which initializes the data members and components
	 * Also stores the raw data for the query row that was selected
	 * @param profileInfo the raw query data provided by the search result
	 */
	public ProfileHandler(Object[] profileInfo) {
		this.profileInfo = profileInfo;
		initialize();
	}

	/**
	 * The method which instantiates all data members and components
	 */
	public void initialize() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		lblFullName = new JLabel("Full Name: " + (profileInfo[1] != null ? profileInfo[1] : ""));
		GridBagConstraints gbc_lblFullName = new GridBagConstraints();
		gbc_lblFullName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFullName.insets = new Insets(0, 0, 5, 0);
		gbc_lblFullName.gridx = 0;
		gbc_lblFullName.gridy = 0;
		add(lblFullName, gbc_lblFullName);

		lblTitle = new JLabel("Title: " + (profileInfo[2] != null ? profileInfo[2] : ""));
		GridBagConstraints gbc_lblLocation = new GridBagConstraints();
		gbc_lblLocation.insets = new Insets(0, 0, 5, 0);
		gbc_lblLocation.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLocation.gridx = 0;
		gbc_lblLocation.gridy = 1;
		add(lblTitle, gbc_lblLocation);

		lblPages = new JLabel("Pages: " + (profileInfo[3] != null ? profileInfo[3] : ""));
		GridBagConstraints gbc_lblPages = new GridBagConstraints();
		gbc_lblPages.anchor = GridBagConstraints.WEST;
		gbc_lblPages.insets = new Insets(0, 0, 5, 0);
		gbc_lblPages.gridx = 0;
		gbc_lblPages.gridy = 2;
		add(lblPages, gbc_lblPages);

		lblYear = new JLabel("Year: " + (profileInfo[4] != null ? profileInfo[4] : ""));
		GridBagConstraints gbc_lblYear = new GridBagConstraints();
		gbc_lblYear.anchor = GridBagConstraints.WEST;
		gbc_lblYear.insets = new Insets(0, 0, 5, 0);
		gbc_lblYear.gridx = 0;
		gbc_lblYear.gridy = 3;
		add(lblYear, gbc_lblYear);

		lblVolume = new JLabel("Volume:" + (profileInfo[5] != null ? profileInfo[5] : ""));
		GridBagConstraints gbc_lblVolume = new GridBagConstraints();
		gbc_lblVolume.anchor = GridBagConstraints.WEST;
		gbc_lblVolume.insets = new Insets(0, 0, 5, 0);
		gbc_lblVolume.gridx = 0;
		gbc_lblVolume.gridy = 4;
		add(lblVolume, gbc_lblVolume);

		lblJournal = new JLabel("Journal: " + (profileInfo[6] != null ? profileInfo[6] : ""));
		GridBagConstraints gbc_lblJournal = new GridBagConstraints();
		gbc_lblJournal.anchor = GridBagConstraints.WEST;
		gbc_lblJournal.insets = new Insets(0, 0, 5, 0);
		gbc_lblJournal.gridx = 0;
		gbc_lblJournal.gridy = 5;
		add(lblJournal, gbc_lblJournal);

		lblNumber = new JLabel("Number: " + (profileInfo[7] != null ? profileInfo[7] : ""));
		GridBagConstraints gbc_lblNumber = new GridBagConstraints();
		gbc_lblNumber.anchor = GridBagConstraints.WEST;
		gbc_lblNumber.insets = new Insets(0, 0, 5, 0);
		gbc_lblNumber.gridx = 0;
		gbc_lblNumber.gridy = 6;
		add(lblNumber, gbc_lblNumber);

		/*lblUrl = new JLabel("URL: " + profileInfo[7].toString());
		GridBagConstraints gbc_lblUrl = new GridBagConstraints();
		gbc_lblUrl.anchor = GridBagConstraints.WEST;
		gbc_lblUrl.insets = new Insets(0, 0, 5, 0);
		gbc_lblUrl.gridx = 0;
		gbc_lblUrl.gridy = 7;
		add(lblUrl, gbc_lblUrl);*/


		Container buttonContainer = new Container();
		buttonContainer.setLayout(new FlowLayout());
		btnCommittee = new JButton("Show Committees");
		btnCommittee.addActionListener(this);
		buttonContainer.add(btnCommittee);
		btnArticles = new JButton("Show Articles");
		btnArticles.addActionListener(this);
		buttonContainer.add(btnArticles);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 9;
		add(buttonContainer, gbc_btnNewButton);


		list = new JList<String>();
		/*list.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {"No results to display"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});*/
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.HORIZONTAL;
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.gridx = 0;
		gbc_list.gridy = 11;
		add(list, gbc_list);


	}

	/**
	 * Saves any data to the session -- There's nothing to be saved.
	 */
	public void save() { /* Nothing */ }

	/**
	 * Clears any data from the session or the UI window. -- This method does nothing.
	 */
	public void clear() { /* Nothing */ }

	/**
	 * Handles all the user interactions for this panel
	 * @param e The event component which was interacted
	 */
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == btnArticles) {
			populate(true);
		} else if(src == btnCommittee) {
			populate(false);
		}
	}

	/**
	 * Updates the profile window to show any additional relevant data.
	 * @param article whether if the user wants relevant articles or conferences
	 */
	private void populate(boolean article) {
		Filters queryFilters = new Filters();

		Filter filter = new Filter();
		filter.setFilterAttribute("author");
		filter.setOperation(OpType.HAS_PHRASE);
		filter.setStringValue(profileInfo[1].toString());
		queryFilters.addToList(filter);
		queryFilters.UpdateFilterOp(FilterOp.AND);

		RootWindow.getSessionSingleton().setControllerType(new QueryEngineController());
		SearchResult resultant = ((QueryEngineController) 
				RootWindow.getSessionSingleton()
				.getController()).search(queryFilters, article ? true : false, 25);

		populate(loadResults(resultant, article ? true : false));
		

	}

	/**
	 * Loads the results of the relevant data
	 * @param search The search query
	 * @param article whether if we want to get data on titles or committee names
	 * @return The resultant set with no duplicates.
	 */
	private Set<String> loadResults(SearchResult search, boolean article) {
		Set<String> results = new HashSet<String>();
		for(ArticleEntry articles : search.getArticles()) {
			results.add(article ? articles.getTitle() : articles.getCommitteeName());
		}
		return results;
	}

	/**
	 * Populates the results into the listbox
	 * @param results the resultant with no duplicates
	 */
	private void populate(Set<String> results) {
		JFrame ancestor = (JFrame) SwingUtilities.getWindowAncestor(this);
		DefaultListModel<String> model = new DefaultListModel<String>();
		if(!results.isEmpty()) {
			for(String s : results) {
				model.addElement(s);
			}
		} else {
			model.addElement("No results to display");
		}
		list.setModel(model);
		ancestor.pack();
		list.revalidate();
		list.repaint();
	}

	/**
	 * Closes the relevant data if the user desires -- currently not used
	 */
	@Override
	protected void clearFields() {
		list.setVisible(false);
	}

	
	/**
	 * Getters used for testing purposes
	 */
	
	public JButton getBtnCommittee() {
		return btnCommittee;
	}

	public JButton getBtnArticles() {
		return btnArticles;
	}


}
