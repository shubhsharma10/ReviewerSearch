//package test.ui;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JPasswordField;
//import javax.swing.JRadioButton;
//import javax.swing.JSlider;
//import javax.swing.JTextField;
//import com.ui.Handler;
//import com.ui.LoginHandler;
//import com.ui.RootWindow;
//import com.ui.SearchHandler;
//import abbot.finder.ComponentNotFoundException;
//import abbot.finder.MultipleComponentsFoundException;
//import abbot.tester.JButtonTester;
//import abbot.tester.JComboBoxTester;
//import abbot.tester.JSliderTester;
//import abbot.tester.JTextComponentTester;
//import junit.extensions.abbot.ComponentTestFixture;
//import junit.extensions.abbot.TestHelper;
//
//
//public class SearchHandlerTest extends ComponentTestFixture {
//	Handler h;
//	RootWindow rt;
//	/**
//	 * test method to check a valid login
//	 * with correct credentials for username 
//	 * and password as admin
//	 * @throws ComponentNotFoundException
//	 * @throws MultipleComponentsFoundException
//	 */
//	public void testLogin() throws ComponentNotFoundException, MultipleComponentsFoundException {
//		@SuppressWarnings("unused")
//		ActionListener al = new ActionListener() {
//			public void actionPerformed(ActionEvent ev) {
//				textEntry = ev.getActionCommand();                            
//
//			}};
//
//
//			rt = new RootWindow();
//			h = (LoginHandler) rt.getCurrentWindow();
//			LoginHandler login = (LoginHandler) h;
//
//			JTextField textField = login.getTextUsername();
//			JTextComponentTester userTester = new JTextComponentTester();
//			userTester.actionEnterText(textField, "admin");
//
//			JPasswordField pwdField = login.getTextPassword();
//			JTextComponentTester passTester = new JTextComponentTester();
//			passTester.actionEnterText(pwdField, "admin");
//
//			JButton loginButton = ((LoginHandler) h).getBnLogin();
//			JButtonTester tester = new JButtonTester();
//			tester.actionClick(loginButton);
//	}
//    /**
//     * test method to check the search of a basic query that
//     * includes search by options, operation type and keyword
//     */
//	@SuppressWarnings("unused")
//	private String textEntry;
//	public void testSingleQuery() throws ComponentNotFoundException, MultipleComponentsFoundException {
//
//		testLogin();
//		h = (SearchHandler) rt.getCurrentWindow();
//		SearchHandler search = (SearchHandler) h;
//
//		@SuppressWarnings("rawtypes")
//		JComboBox cbSearchBy = search.getFilters().get(0).getCbSearchBy();
//		JComboBoxTester cbSearchByTester = new JComboBoxTester();
//		cbSearchByTester.actionSelectIndex(cbSearchBy, 1);
//
//		@SuppressWarnings("rawtypes")
//		JComboBox cbOption = search.getFilters().get(0).getCbOptions();
//		JComboBoxTester cbOptionsTester = new JComboBoxTester();
//		cbOptionsTester.actionSelectIndex(cbOption, 1);
//
//
//		JTextField textKey = search.getFilters().get(0).getTxtKeyword();
//		JTextComponentTester keywordTester = new JTextComponentTester();
//		keywordTester.actionEnterText(textKey, "Brian");
//
//		JButton searchButton = search.getBnSearch();
//		assertEquals("Wrong Button after selection", 
//				"Search", searchButton.getText());
//		JButtonTester bnSearchTest = new JButtonTester();
//		bnSearchTest.actionClick(searchButton);
//
//	}
//	/**
//	 * test method to check the slider that displays 
//	 * the specified number of search results for a query
//	 * @throws ComponentNotFoundException
//	 * @throws MultipleComponentsFoundException
//	 */
//	public void testSlider() throws ComponentNotFoundException, MultipleComponentsFoundException {
//
//		testLogin();
//		h = (SearchHandler) rt.getCurrentWindow();
//		SearchHandler search = (SearchHandler) h;
//		
//		@SuppressWarnings("rawtypes")
//		JComboBox cbSearchBy = search.getFilters().get(0).getCbSearchBy();
//		JComboBoxTester cbSearchByTester = new JComboBoxTester();
//		cbSearchByTester.actionSelectIndex(cbSearchBy, 1);
//
//		@SuppressWarnings("rawtypes")
//		JComboBox cbOption = search.getFilters().get(0).getCbOptions();
//		JComboBoxTester cbOptionsTester = new JComboBoxTester();
//		cbOptionsTester.actionSelectIndex(cbOption, 1);
//
//
//		JTextField textKey = search.getFilters().get(0).getTxtKeyword();
//		JTextComponentTester keywordTester = new JTextComponentTester();
//		keywordTester.actionEnterText(textKey, "Brian");
//		
//		JSlider slider = search.getSliderResultCount();
//		JSliderTester sliderTester = new JSliderTester();
//		sliderTester.actionDrag(slider);
//
//		JButton searchButton = search.getBnSearch();
//		assertEquals("Wrong Button after selection", 
//				"Search", searchButton.getText());
//		JButtonTester bnSearchTest = new JButtonTester();
//		bnSearchTest.actionClick(searchButton);
//
//	}
//	/**
//     * test method to check the search of a basic query that
//     * when there is no connection to the elastic search server
//     */
//	
//	public void testNoDblpConnection() throws ComponentNotFoundException, MultipleComponentsFoundException {
//
//		testLogin();
//		h = (SearchHandler) rt.getCurrentWindow();
//		SearchHandler search = (SearchHandler) h;
//
//		@SuppressWarnings("rawtypes")
//		JComboBox cbSearchBy = search.getFilters().get(0).getCbSearchBy();
//		JComboBoxTester cbSearchByTester = new JComboBoxTester();
//		cbSearchByTester.actionSelectIndex(cbSearchBy, 1);
//
//		@SuppressWarnings("rawtypes")
//		JComboBox cbOption = search.getFilters().get(0).getCbOptions();
//		JComboBoxTester cbOptionsTester = new JComboBoxTester();
//		cbOptionsTester.actionSelectIndex(cbOption, 1);
//
//
//		JTextField textKey = search.getFilters().get(0).getTxtKeyword();
//		JTextComponentTester keywordTester = new JTextComponentTester();
//		keywordTester.actionEnterText(textKey, "Brian");
//
//		
//		JButton searchButton = search.getBnSearch();
//		assertEquals("Wrong Button after selection", 
//				"Search", searchButton.getText());
//		JButtonTester bnSearchTest = new JButtonTester();
//		bnSearchTest.actionClick(searchButton); 
//
//	}
//	/**
//     * test method to check the search of a nested query that
//     * includes search by options, operation types and keywords
//     */
//	public void testNestedQuery() throws ComponentNotFoundException, MultipleComponentsFoundException {
//
//		testLogin();
//		h = (SearchHandler) rt.getCurrentWindow();
//		SearchHandler search = (SearchHandler) h;
//		
//		JButton addButton = search.getBnAdd();
//		assertEquals("Wrong Button after selection", 
//				"+", addButton.getText());
//		JButtonTester bnSearchTest = new JButtonTester();
//		bnSearchTest.actionClick(addButton);
//		
//		JRadioButton opButton = search.getRdOr();
//		JButtonTester opTest = new JButtonTester();
//		opTest.actionClick(opButton);
//				
//		String[] keywordInputs = { "Brian", "Hans"};
//	
//		assert (search.getFilters().size() == 2);
//        for(int i = 0; i < search.getFilters().size(); i++) {
//    		@SuppressWarnings("rawtypes")
//			JComboBox cbSearchBy = search.getFilters().get(i).getCbSearchBy();
//    		JComboBoxTester cbSearchByTester = new JComboBoxTester();
//    		cbSearchByTester.actionSelectIndex(cbSearchBy, 1);
//
//    		@SuppressWarnings("rawtypes")
//			JComboBox cbOption = search.getFilters().get(i).getCbOptions();
//    		JComboBoxTester cbOptionsTester = new JComboBoxTester();
//    		cbOptionsTester.actionSelectIndex(cbOption, 1);
//
//    		JTextField textKey = search.getFilters().get(i).getTxtKeyword();
//    		JTextComponentTester keywordTester = new JTextComponentTester();
//    		keywordTester.actionEnterText(textKey, keywordInputs[i]);
//        }
//
//		JButton searchButton = search.getBnSearch();
//		assertEquals("Wrong Button after selection", 
//				"Search", searchButton.getText());
//		JButtonTester bnSearchTestNested = new JButtonTester();
//		bnSearchTestNested.actionClick(searchButton);
//
//	}
//	/**
//	 * test method to check if a particular query row 
//	 * is removed when "-" button is clicked
//	 * @throws ComponentNotFoundException
//	 * @throws MultipleComponentsFoundException
//	 */ 
//	public void testRemoveQuery() throws ComponentNotFoundException, MultipleComponentsFoundException {
//
//		testLogin();
//		h = (SearchHandler) rt.getCurrentWindow();
//		SearchHandler search = (SearchHandler) h;
//		
//		@SuppressWarnings("rawtypes")
//		JComboBox cbSearchBy = search.getFilters().get(0).getCbSearchBy();
//		JComboBoxTester cbSearchByTester = new JComboBoxTester();
//		cbSearchByTester.actionSelectIndex(cbSearchBy, 1);
//
//		@SuppressWarnings("rawtypes")
//		JComboBox cbOption = search.getFilters().get(0).getCbOptions();
//		JComboBoxTester cbOptionsTester = new JComboBoxTester();
//		cbOptionsTester.actionSelectIndex(cbOption, 1);
//
//		JTextField textKey = search.getFilters().get(0).getTxtKeyword();
//		JTextComponentTester keywordTester = new JTextComponentTester();
//		keywordTester.actionEnterText(textKey, "Brian");
//
//		JButton removeButton = search.getFilters().get(0).getBnRemove();
//		assertEquals("Wrong Button after selection", 
//				"-", removeButton.getText());
//		JButtonTester bnRemoveTest = new JButtonTester();
//		bnRemoveTest.actionClick(removeButton);
//
//	}
//	
//	/**
//	 * test method to check if fields are cleared 
//	 * when clear fields button is clicked
//	 * @throws ComponentNotFoundException
//	 * @throws MultipleComponentsFoundException
//	 */ 
//	public void testClearFilter() throws ComponentNotFoundException, MultipleComponentsFoundException {
//
//		testLogin();
//		h = (SearchHandler) rt.getCurrentWindow();
//		SearchHandler search = (SearchHandler) h;
//		
//		@SuppressWarnings("rawtypes")
//		JComboBox cbSearchBy = search.getFilters().get(0).getCbSearchBy();
//		JComboBoxTester cbSearchByTester = new JComboBoxTester();
//		cbSearchByTester.actionSelectIndex(cbSearchBy, 1);
//
//		@SuppressWarnings("rawtypes")
//		JComboBox cbOption = search.getFilters().get(0).getCbOptions();
//		JComboBoxTester cbOptionsTester = new JComboBoxTester();
//		cbOptionsTester.actionSelectIndex(cbOption, 1);
//
//
//		JTextField textKey = search.getFilters().get(0).getTxtKeyword();
//		JTextComponentTester keywordTester = new JTextComponentTester();
//		keywordTester.actionEnterText(textKey, "Brian");
//
//		JButton clearButton = search.getBnClearFilters();
//		assertEquals("Wrong Button after selection", 
//				"Clear Fields", clearButton.getText());
//		JButtonTester bnClearTest = new JButtonTester();
//		bnClearTest.actionClick(clearButton);
//
//	}
//	/**
//	 * test method to check if correct action 
//	 * is performed when logout button is clicked
//	 * @throws ComponentNotFoundException
//	 * @throws MultipleComponentsFoundException
//	 */
//	public void testLogOut() throws ComponentNotFoundException, MultipleComponentsFoundException {
//
//		testLogin();
//		h = (SearchHandler) rt.getCurrentWindow();
//		SearchHandler search = (SearchHandler) h;
//
//		JButton logoutButton = search.getBnLogout();
//		assertEquals("Wrong Button after selection", 
//				"Logout", logoutButton.getText());
//		JButtonTester bnLogoutTest = new JButtonTester();
//		bnLogoutTest.actionClick(logoutButton);
//
//	}
//	
//	public SearchHandlerTest(String name) { super(name); }
//
//	public static void main(String[] args) {
//		TestHelper.runTests(args, SearchHandlerTest.class);
//	}
//}