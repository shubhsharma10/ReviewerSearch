package com.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.dao.SearchResult;
import com.front.ArticleEntry;

/**
 * GUI Class which lets users search for publication data
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Shubham Sharma
 */
public class SearchResultHandler extends Handler implements MouseListener {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -6703948320319539964L;
	
	/**
	 * The main component which stores all the query results
	 */
	private JTable resultantTable;
	
	public JTable getResultantTable() {
		return resultantTable;
	}

	/**
	 * The button component which allows the user to go back to the search panel
	 */
	private JButton bnBack;
	
	/**
	 * The column names for the table
	 */
	private String[] columnNames = 
		{"Serial No.","Name", "Title", "Pages", "Year", "Volume", "Journal", "Number"};
	
	
	/**
	 * The raw data resultant
	 */
    private Object[][] resultant;


    /**
     * The method which instantiates all components and data members
     */
	public void initialize() {
		setLayout(new GridLayout(2, 0));
        resultantTable = new JTable(new ResultantDataModel());
        resultantTable.setPreferredScrollableViewportSize(new Dimension(900, 90));
        resultantTable.setFillsViewportHeight(true);
        resultantTable.addMouseListener(this);
        JScrollPane scrollPane = new JScrollPane(resultantTable);
        add(scrollPane);
        
        Container c = new Container();
        c.setLayout(new FlowLayout());
        bnBack = new JButton("Back");
        bnBack.addActionListener(this);
        c.add(bnBack);
        add(c);
	}

	/**
	 * Saves data to the session
	 * Nothing needs to be saved to this panel
	 */
	public void save() { /* Nothing */ }
	
	/**
	 * Populates the table from the converted search result
	 * Initializes the window components after data processing
	 * @param results the converted search result data
	 */
	public void populate(List<String[]> results) {
		this.resultant = new Object[results.size()][];
		for(int i = 0; i < resultant.length; i++) {
			resultant[i] = results.get(i);
		}
		Arrays.toString(resultant);
		initialize();
		
	}

	/**
	 * Handles all the user interactions for this panel
	 * @param e The event component which was interacted
	 */
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		RootWindow ancestor = (RootWindow) SwingUtilities.getWindowAncestor(this);
		if(src == bnBack) {
			if(RootWindow.getSessionSingleton().getSearchData() == null) {
				ancestor.attach(new SearchHandler());
			} else {
				ancestor.attach(RootWindow.getSessionSingleton().getSearchData());
			}

		}
	}

	/**
	 * Clears any fields on this panel -- no components need to be cleared
	 */
	@Override
	protected void clearFields() { /* Nothing */ }
	
	/**
	 * Loads the results and converts it into raw data
	 * @param search the search result formulated by a query
	 * @return The list of query records
	 */
	public List<String[]> loadResults(SearchResult search) {
		List<String[]> results = new ArrayList<String[]>();
		int i=1;
		for(ArticleEntry articles : search.getArticles()) {
			String[] resultOfString = new String[8];
			resultOfString[0] = String.valueOf(i);
			resultOfString[1] = articles.getAuthor();
			resultOfString[2] = articles.getTitle();
			resultOfString[3] = articles.getPages();
			resultOfString[4] = articles.getYear();
			resultOfString[5] = articles.getVolume();
			resultOfString[6] = articles.getJournal();
			resultOfString[7] = articles.getNumber();
			results.add(resultOfString);
			i++;
		}
		return results;
	}
	

	/**
	 * Handles mouse functionality
	 * Primarily used to double click a row to get profile info
	 * @param e The mouse event
	 */
	public void mouseClicked(MouseEvent e) {
		Object src = e.getSource();
		if (e.getClickCount() == 2) {
			int row = ((JTable) src).getSelectedRow();
			Object[] tuple = new Object[columnNames.length];
			for(int i = 0; i < tuple.length; i++) {
				tuple[i] = ((JTable) src).getModel().getValueAt(row, i);
			}
			createProfileWindow(tuple);
		}
	}
	

	/**
	 * Executes a new profile window
	 * @param tuple the raw data of the selected record
	 */
	private void createProfileWindow(final Object[] tuple) {
		EventQueue.invokeLater(new Runnable() { 
			public void run() {
            	JFrame frame = new JFrame("Profile Lookup - " + tuple[1].toString());
        		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        		frame.getContentPane().add(new ProfileHandler(tuple));
        		frame.setLocationRelativeTo(null);
        		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        		frame.pack();
        		frame.setVisible(true);
            }
		});
	}
	
	/**
	 * Handles pressed mouse events -- Does nothing
	 * @param e The mouse event
	 */
	public void mousePressed(MouseEvent e) { }
	
	/**
	 * Handles released mouse events -- Does nothing
	 * @param e The mouse event
	 */
	public void mouseReleased(MouseEvent e) { }
	
	/**
	 * Handles mouse events which enter the UI window -- Does nothing
	 * @param e The mouse event
	 */
	public void mouseEntered(MouseEvent e) { }
	
	/**
	 * Handles mouse events which exit the UI window -- Does nothing
	 * @param e The mouse event
	 */
	public void mouseExited(MouseEvent e) { }

	/**
	 * Represents the resultant data model which is used 
	 * to determine the data structure of the table component
	 * @author Nicholas Carugati
	 *
	 */
	private class ResultantDataModel extends DefaultTableModel {
		
		/**
		 * Generated serial version UID.
		 */
		private static final long serialVersionUID = 7565871242467698669L;

		/**
		 * Constructor which instantiates the default data model 
		 */
		public ResultantDataModel() {
			super(resultant, columnNames);
		}
		
		/**
		 * Determines if the cell is edible
		 * @return false - The cell should not be editable
		 */
        @Override
        public boolean isCellEditable(int row, int column) {
           return false;
        }
        
        /**
         * Retrieves the value at a specifc row in the table
         * @return the string set in a specific row.
         */
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
        	return resultant[rowIndex][columnIndex];
        }
		
	}

	public JButton getBnBack() {
		// TODO Auto-generated method stub
		return this.bnBack;
	}

	public String[] getColumnNames() {
		return columnNames;
	}
	
}

