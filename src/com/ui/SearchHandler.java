package com.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.elasticsearch.client.transport.NoNodeAvailableException;
import org.elasticsearch.index.IndexNotFoundException;

import com.controller.QueryEngineController;
import com.dao.SearchResult;
import com.search.Filter;
import com.search.FilterOp;
import com.search.Filters;
import com.search.OpType;

/**
 * GUI Class which lets users search for publication data
 * @author Nicholas Carugati
 * @author Chandhini Chandersekar 
 * @author Shubham Sharma
 */
public class SearchHandler extends Handler {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1674466278122406028L;

	/**
	 * The component which lets the user control how many results should be viewed
	 */
	private JSlider sliderResultCount;

	/**
	 * The button which logs out the user from the search system
	 */
	private JButton bnLogout;

	/**
	 * The button which executes a search for the user
	 */
	private JButton bnSearch;

	/**
	 * The button which lets the user pick advanced controls
	 */
	private JButton bnAdvanced;

	/**
	 * The different types of searches that a user can choose from
	 */
	private static final String[] SEARCH_TYPES = { "Title", "Author", "Journal", "Year",
	"Volume" };

	/**
	 * The button component which lets a user add a filter
	 */
	private JButton bnAdd = new JButton("+");

	/**
	 * The button component which lets users clear all filters 
	 */
	private JButton bnClearFilters = new JButton("Clear Fields");

	/**
	 * The scroll panel which contains all of the filter components
	 */
	private JScrollPane pane;

	/**
	 * The panel which contains each graphical filter component and its data
	 */
	private JPanel scrollGroup;

	/**
	 * The radio button component which lets the user unionize filters using AND
	 */
	private JRadioButton rdAnd;

	/**
	 * The radio button component which lets the user unionize filters using OR
	 */
	private JRadioButton rdOr;

	/**
	 * The button group which houses the radio button controls.
	 */
	private ButtonGroup bg;

	/**
	 * The container which houses the radio button components
	 */
	private Container radioContainer;

	/**
	 * The dictionary which contains all operation types
	 */
	private static Map<String, OpType> typeDict = new HashMap<String, OpType>();

	/**
	 * The resultant which gets passed to the result panel after
	 * a query is executed
	 */
	private SearchResult resultant;

	/**
	 * The constructor which makes an initialization 
	 * call to instantiate all data members and components
	 */
	public SearchHandler() {
		initialize();
	}

	/**
	 * Instantiates all data members and components for this panel.
	 */
	public void initialize() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 910, 0 };
		gridBagLayout.rowHeights = new int[] { -10, 131, -11, 9, 23, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		Container c1 = new Container();
		c1.setLayout(new FlowLayout());
		radioContainer = new Container();
		radioContainer.setLayout(new FlowLayout());
		rdAnd = new JRadioButton("AND");
		rdAnd.setSelected(true);
		rdOr = new JRadioButton("OR");
		bg = new ButtonGroup();
		bg.add(rdAnd);
		bg.add(rdOr);
		radioContainer.add(rdAnd);
		radioContainer.add(rdOr);
		radioContainer.setVisible(false);
		c1.add(radioContainer);
		GridBagConstraints gbc_c1 = new GridBagConstraints();
		gbc_c1.fill = GridBagConstraints.BOTH;
		gbc_c1.insets = new Insets(0, 0, 5, 0);
		gbc_c1.gridx = 0;
		gbc_c1.gridy = 0;
		this.add(c1, gbc_c1);

		scrollGroup = new JPanel();
		scrollGroup.setLayout(new GridLayout(1, 1));
		scrollGroup.add(new FilterContainer());
		pane = new JScrollPane(scrollGroup);
		pane.setPreferredSize(new Dimension(this.getWidth(), 200));
		pane.setViewportView(scrollGroup);
		GridBagConstraints gbc_pane = new GridBagConstraints();
		gbc_pane.fill = GridBagConstraints.BOTH;
		gbc_pane.insets = new Insets(0, 0, 5, 0);
		gbc_pane.gridx = 0;
		gbc_pane.gridy = 1;
		this.add(pane, gbc_pane);

		Container c2 = new Container();
		c2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bnAdd.addActionListener(this);
		bnClearFilters.addActionListener(this);
		c2.add(bnAdd);
		c2.add(bnClearFilters);
		GridBagConstraints gbc_c2 = new GridBagConstraints();
		gbc_c2.fill = GridBagConstraints.BOTH;
		gbc_c2.insets = new Insets(0, 0, 5, 0);
		gbc_c2.gridx = 0;
		gbc_c2.gridy = 2;
		this.add(c2, gbc_c2);

		Container c3 = new Container();
		c3.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		JLabel lbRowsDisplayed = new JLabel("Edit Rows Displayed: ");
		c3.add(lbRowsDisplayed);

		sliderResultCount = new JSlider();
		sliderResultCount.setMinimum(10);
		sliderResultCount.setPaintLabels(true);
		sliderResultCount.setValue(30);
		sliderResultCount.setPaintTicks(true);
		sliderResultCount.setMajorTickSpacing(10);
		sliderResultCount.setMinorTickSpacing(1);
		sliderResultCount.setPreferredSize(new Dimension(400, 80));
		c3.add(sliderResultCount);
		GridBagConstraints gbc_c3 = new GridBagConstraints();
		gbc_c3.fill = GridBagConstraints.BOTH;
		gbc_c3.insets = new Insets(0, 0, 5, 0);
		gbc_c3.gridx = 0;
		gbc_c3.gridy = 3;
		this.add(c3, gbc_c3);

		Container c4 = new Container();
		bnLogout = new JButton("Logout");
		bnLogout.addActionListener(this);
		c4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		c4.add(bnLogout);

		bnSearch = new JButton("Search");
		bnSearch.addActionListener(this);
		c4.add(bnSearch);

		bnAdvanced = new JButton("Advanced");
		bnAdvanced.setVisible(false); // TODO: ADVANCED CONTROLS: WILL BE DONE
		// FOR PHASE IV
		bnAdvanced.addActionListener(this);
		c4.add(bnAdvanced);

		GridBagConstraints gbc_c4 = new GridBagConstraints();
		gbc_c4.fill = GridBagConstraints.BOTH;
		gbc_c4.gridx = 0;
		gbc_c4.gridy = 4;
		this.add(c4, gbc_c4);
		resultant = null;
	}

	/**
	 * Saves the filters after a search is performed to be modified again
	 */
	public void save() {
		RootWindow.getSessionSingleton().setSearchData(this);
	}

	/**
	 * The main method which assembles and executes a query.
	 */
	public void handle() {
		Filters queryFilters = new Filters();
		for (FilterContainer filt : getFilters()) {
			Filter filter = new Filter();
			filter.setFilterAttribute(filt.cbSearchBy.getSelectedItem().toString().toLowerCase());
			filter.setOperation(typeDict.get(filt.cbOptions.getSelectedItem().toString()));
			if (filt.getType() == 0) {
				filter.setStringValue(filt.txtKeyword.getText());
			} else if (filt.getType() == 1) {
				Integer intValue = null;
				try {
					intValue = Integer.parseInt(filt.txtKeyword.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "Entered value is not a valid integer value",
							"Invalid Integer value", JOptionPane.ERROR_MESSAGE);

				}
				if (intValue == null)
					return;
				else
					filter.setNumericValue(intValue);
			} else if (filt.getType() == 2) {
				int[] range = new int[2];
				Integer minValue = null;
				Integer maxValue = null;
				try {
					minValue = Integer.parseInt(filt.txtKeyword.getText());
					maxValue = Integer.parseInt(filt.numericRange.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "Entered value is not a valid integer value",
							"Invalid Integer value", JOptionPane.ERROR_MESSAGE);

				}

				if (minValue == null || maxValue == null)
					return;
				else {
					range[0] = minValue;
					range[1] = maxValue;
				}
				filter.setRange(range);
			}
			queryFilters.addToList(filter);
		}

		if (!queryFilters.getFilters().isEmpty()) {
			if (queryFilters.getFilters().size() >= 2) {
				FilterOp filterOp = rdAnd.isSelected() ? FilterOp.AND : FilterOp.OR;
				queryFilters.UpdateFilterOp(filterOp);
			}
			try {
				RootWindow.getSessionSingleton().setControllerType(new QueryEngineController());
				int limit = sliderResultCount.getValue();
				resultant = ((QueryEngineController) RootWindow.getSessionSingleton().getController()).search(queryFilters,
						true, limit);
			} catch(NoNodeAvailableException n) {
				JOptionPane.showMessageDialog(this, "Unable to contact the search server.", "No Connection!",
						JOptionPane.ERROR_MESSAGE);
			} catch(IndexNotFoundException idx) {
				JOptionPane.showMessageDialog(this, "Unable to contact the search server.", "No Connection!",
						JOptionPane.ERROR_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(this, "You must have one filter to search", "Please set a filter.",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Handles all the user interactions for this panel
	 * @param e The event component which was interacted
	 */
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		RootWindow ancestor = (RootWindow) SwingUtilities.getWindowAncestor(this);
		if (src == bnLogout) {
			logout();
		} else if (src == bnSearch) {
			handle();
			if (resultant != null) {
				SearchResultHandler res = new SearchResultHandler();
				save();
				res.populate(res.loadResults(resultant));
				ancestor.attach(res);
				resultant = null;
			}
		} else if (src == bnAdvanced) {
			// TODO: Expand from invisible components on screen
		} else if (src == bnAdd) {
			createFilter();
		} else if (src == bnClearFilters) {
			clearFields();
		}
	}

	/**
	 * A method which gets all the filter components
	 * @return the list of filter component instances
	 */
	public List<FilterContainer> getFilters() {
		Component[] filterComponents = scrollGroup.getComponents();
		List<FilterContainer> filters = new ArrayList<FilterContainer>();
		for (Component filter : filterComponents) {
			if (filter instanceof FilterContainer) {
				filters.add((FilterContainer) filter);
			}
		}
		return filters;
	}

	/**
	 * Logs out the user and removes all session data
	 */
	private void logout() {
		RootWindow ancestor = (RootWindow) SwingUtilities.getWindowAncestor(this);
		RootWindow.getSessionSingleton().setUsername("");
		RootWindow.getSessionSingleton().setSearchData(null);
		ancestor.attach(new LoginHandler());
	}

	/**
	 * Clears all the filters
	 */
	@Override
	protected void clearFields() {
		List<FilterContainer> filters = getFilters();
		for (FilterContainer filter : filters) {
			scrollGroup.remove(filter);
		}
		radioContainer.setVisible(false);
		clearRadioOptions();
		scrollGroup.revalidate();
		scrollGroup.repaint();
		pane.revalidate();
		pane.repaint();
	}

	/**
	 * Removes a single filter component
	 * @param filt a filter component to remove from the scroll group
	 */
	private void removeFilter(FilterContainer filt) {
		RootWindow ancestor = (RootWindow) SwingUtilities.getWindowAncestor(this);
		scrollGroup.remove(filt);
		List<FilterContainer> filters = getFilters();
		if (filters.isEmpty()) {
			scrollGroup.setVisible(false);
		} else {
			GridLayout gl = (GridLayout) scrollGroup.getLayout();
			gl.setRows(gl.getRows() - 1);
		}
		if (filters.size() <= 1) {
			radioContainer.setVisible(false);
			clearRadioOptions();
		}
		scrollGroup.revalidate();
		scrollGroup.repaint();
		pane.revalidate();
		pane.repaint();
		if (ancestor != null) {
			ancestor.pack();
		}
	}

	/**
	 * Creates a filter to add into the scroll group
	 * @param filter the filter to add into the scroll group
	 */
	private void createFilter() {
		RootWindow ancestor = (RootWindow) SwingUtilities.getWindowAncestor(this);
		List<FilterContainer> list = getFilters();

		if (list.isEmpty()) {
			scrollGroup.setVisible(true);
		} else {
			GridLayout gl = (GridLayout) scrollGroup.getLayout();
			gl.setRows(gl.getRows() + 1);
		}

		scrollGroup.add(new FilterContainer());
		if (getFilters().size() > 1) {
			radioContainer.setVisible(true);
		}
		scrollGroup.revalidate();
		scrollGroup.repaint();
		this.revalidate();
		this.repaint();
		ancestor.pack();
	}

	/**
	 * Clears all radio options when there is either one filter or none
	 */
	public void clearRadioOptions() {
		rdAnd.setSelected(false);
		rdOr.setSelected(false);
	}

	/**
	 * The inner class which represents a filter component
	 * @author Nicholas Carugati
	 *
	 */
	public class FilterContainer extends JPanel implements ActionListener {

		/**
		 * Generate serial version UID
		 */
		private static final long serialVersionUID = -2252295934712123500L;

		/**
		 * Constant which are used for search types that require a string
		 */
		private final String[] KEYWORD_OPTIONS_STRING = { "HAS ANY", "HAS PHRASE", "HAS PREFIX" };

		/**
		 * Constant which are used for search types that require a keyword
		 */
		private final String[] KEYWORD_OPTIONS_INTEGER = { "EQUAL", "NOT EQUAL", "LESS THAN", "GREATER THAN",
				"LESS THAN OR EQUAL TO", "GREATER THAN OR EQUAL TO", "BETWEEN INCLUDING", "BETWEEN EXCLUDING" };

		/**
		 * The container which houses the textboxes to formulate a query
		 */
		Container keywordContainer;

		/**
		 * The combobox where the user can specify a search type
		 */
		JComboBox<String> cbSearchBy;

		/**
		 * The combobox where the user can specify a range of options
		 */
		JComboBox<String> cbOptions = new JComboBox<String>(KEYWORD_OPTIONS_STRING);

		/**
		 * The main search textbox which requires the user to input a keyword
		 */
		JTextField txtKeyword = new JTextField();

		/**
		 * The numeric range which requires the user to input a number
		 */
		JTextField numericRange = new JTextField();

		/**
		 * The button which gives the user to remove a filter
		 */
		JButton bnRemove = new JButton("-");

		/**
		 * The type of query based on the search type
		 * 0 = String type query
		 * 1 = Integer type query
		 * 2 = Integer type query with range
		 */
		private int type;

		/**
		 * Gets the current type
		 * @return the current query type
		 */
		public int getType() {
			return type;
		}

		/**
		 * Constructor which instantiates data members and components for a filter
		 */
		public FilterContainer() {
			super();
			this.setLayout(new FlowLayout());

			keywordContainer = new Container();
			keywordContainer.setLayout(new FlowLayout());

			cbSearchBy = new JComboBox<String>(SEARCH_TYPES);
			cbSearchBy.addActionListener(this);
			keywordContainer.add(cbSearchBy);

			cbOptions.addActionListener(this);
			keywordContainer.add(cbOptions);
			txtKeyword.setColumns(15);
			keywordContainer.add(txtKeyword);

			numericRange.setVisible(false);
			numericRange.setColumns(5);
			keywordContainer.add(numericRange);
			this.add(keywordContainer);

			bnRemove.addActionListener(this);
			this.add(bnRemove);
			this.type = 0;
			this.setVisible(true);
		}

		
		/**
		 * Handles all the user interactions for this component
		 * @param e The event component which was interacted
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cbOptions) {
				switch ((String) cbOptions.getSelectedItem()) {
				case "HAS ANY":
				case "HAS PHRASE":
				case "HAS PREFIX":
					type = 0;
					numericRange.setVisible(false);
					break;
				case "EQUAL":
				case "NOT EQUAL":
				case "LESS THAN":
				case "GREATER THAN":
				case "LESS THAN OR EQUAL TO":
				case "GREATER THAN OR EQUAL TO":
					type = 1;
					numericRange.setVisible(false);
					break;
				case "BETWEEN INCLUDING":
				case "BETWEEN EXCLUDING":
					type = 2;
					numericRange.setVisible(true);
				}
				this.revalidate();
				this.repaint();
			} else if (e.getSource() == bnRemove) {
				removeFilter(this);
			} else if (e.getSource() == cbSearchBy) {
				DefaultComboBoxModel<String> model;
				switch ((String) cbSearchBy.getSelectedItem()) {
				case "Title":
				case "Author":
				case "Journal":
					type = 0;
					model = new DefaultComboBoxModel<String>(KEYWORD_OPTIONS_STRING);
					cbOptions.setModel(model);
					break;
				case "Year":
				case "Volume":
					type = 1;
					model = new DefaultComboBoxModel<String>(KEYWORD_OPTIONS_INTEGER);
					cbOptions.setModel(model);
					break;
				}
			}
		}

		/**
		 * Getters used for testing purposes
		 */
		public Container getKeywordContainer() {
			return keywordContainer;
		}

		public JComboBox<String> getCbSearchBy() {
			return cbSearchBy;
		}

		public JComboBox<String> getCbOptions() {
			return cbOptions;
		}

		public JTextField getTxtKeyword() {
			return txtKeyword;
		}

		public JTextField getNumericRange() {
			return numericRange;
		}

		public JButton getBnRemove() {
			return bnRemove;
		}
		
		

	}

	/**
	 * Getters used for testing purposes
	 */
	public JSlider getSliderResultCount() {
		return sliderResultCount;
	}

	public JButton getBnLogout() {
		return bnLogout;
	}

	public JButton getBnSearch() {
		return bnSearch;
	}

	public JButton getBnAdvanced() {
		return bnAdvanced;
	}

	public JButton getBnAdd() {
		return bnAdd;
	}

	public JButton getBnClearFilters() {
		return bnClearFilters;
	}

	public JRadioButton getRdAnd() {
		return rdAnd;
	}

	public JRadioButton getRdOr() {
		return rdOr;
	}
	

	/**
	 * Class loading opertaions which load the OpTpye dictionary
	 */
	static {
		OpType[] operations = { OpType.NONE, OpType.HAS_ANY, OpType.HAS_PHRASE, OpType.HAS_PREFIX, OpType.EQUAL,
				OpType.NOT_EQUAL, OpType.LESSER, OpType.GREATER, OpType.LESS_EQUAL, OpType.GREAT_EQUAL,
				OpType.BETWEEN_INCL, OpType.BETWEEN_EXCL };
		String[] str = { "none", "HAS ANY", "HAS PHRASE", "HAS PREFIX", "EQUAL", "NOT EQUAL", "LESS THAN",
				"GREATER THAN", "LESS THAN OR EQUAL TO", "GREATER THAN OR EQUAL TO", "BETWEEN INCLUDING",
		"BETWEEN EXCLUDING" };
		for (int i = 0; i < operations.length; i++) {
			typeDict.put(str[i], operations[i]);
		}

	}


}
