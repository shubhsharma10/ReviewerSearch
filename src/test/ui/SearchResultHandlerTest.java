//package test.ui;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JPasswordField;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import junit.extensions.abbot.ComponentTestFixture;
//import junit.extensions.abbot.TestHelper;
//import abbot.finder.ComponentNotFoundException;
//import abbot.finder.MultipleComponentsFoundException;
//import abbot.tester.JButtonTester;
//import abbot.tester.JComboBoxTester;
//import abbot.tester.JTableTester;
//import abbot.tester.JTextComponentTester;
//import com.ui.Handler;
//import com.ui.LoginHandler;
//import com.ui.RootWindow;
//import com.ui.SearchHandler;
//import com.ui.SearchResultHandler;
//
//
//public class SearchResultHandlerTest extends ComponentTestFixture {
//
//	@SuppressWarnings("unused")
//	private String result;
//	@SuppressWarnings("unused")
//	
//	/**
//	 * test method to check if expected cell
//	 * appears selected when a particular cell is clicked
//	 * in the search result table
//	 * @throws ComponentNotFoundException
//	 * @throws MultipleComponentsFoundException
//	 */
//	public void testClickToProfile() throws ComponentNotFoundException, MultipleComponentsFoundException {
//		ActionListener al = new ActionListener() {
//			public void actionPerformed(ActionEvent ev) {
//				result = ev.getActionCommand();                            
//			
//			}};
//						
//			Handler h;
//			RootWindow rt = new RootWindow();
//			
//			h = (LoginHandler) rt.getCurrentWindow();
//			LoginHandler login = (LoginHandler) h;
//			
//			JTextField textField = login.getTextUsername();
//		    JTextComponentTester userTester = new JTextComponentTester();
//		    userTester.actionEnterText(textField, "admin");
//
//		    JPasswordField pwdField = login.getTextPassword();
//			JTextComponentTester passTester = new JTextComponentTester();
//			passTester.actionEnterText(pwdField, "admin");
//			
//			JButton loginButton = ((LoginHandler) h).getBnLogin();
//			JButtonTester tester = new JButtonTester();
//			tester.actionClick(loginButton);
//						
//			h = (SearchHandler) rt.getCurrentWindow();
//			SearchHandler search = (SearchHandler) h;
//			
//			@SuppressWarnings("rawtypes")
//			JComboBox cbSearchBy = search.getFilters().get(0).getCbSearchBy();
//			JComboBoxTester cbSearchByTester = new JComboBoxTester();
//			cbSearchByTester.actionSelectIndex(cbSearchBy, 1);
//
//			@SuppressWarnings("rawtypes")
//			JComboBox cbOption = search.getFilters().get(0).getCbOptions();
//			JComboBoxTester cbOptionsTester = new JComboBoxTester();
//			cbOptionsTester.actionSelectIndex(cbOption, 1);
//
//			JTextField textKey = search.getFilters().get(0).getTxtKeyword();
//			JTextComponentTester keywordTester = new JTextComponentTester();
//			keywordTester.actionEnterText(textKey, "Brian");
//
//			JButton searchButton = search.getBnSearch();
//			JButtonTester bnSearchTest = new JButtonTester();
//			bnSearchTest.actionClick(searchButton);
//			
//			h = (SearchResultHandler) rt.getCurrentWindow();
//			SearchResultHandler searchHandler = (SearchResultHandler) h;
//			
//			JTable searchResultTable = searchHandler.getResultantTable();
//			JTableTester tableTest = new JTableTester();
//			tableTest.actionClick(searchResultTable, 0, 0);
//			
//	}
//	/**
//	 * test method to check if correct action 
//	 * is performed when back button is clicked
//	 * @throws ComponentNotFoundException
//	 * @throws MultipleComponentsFoundException
//	 */
//	public void testClickBack() throws ComponentNotFoundException, MultipleComponentsFoundException {
//		@SuppressWarnings("unused")
//		ActionListener al = new ActionListener() {
//			public void actionPerformed(ActionEvent ev) {
//				result = ev.getActionCommand();                            
//			
//			}};
//						
//			Handler h;
//			RootWindow rt = new RootWindow();			
//			h = (LoginHandler) rt.getCurrentWindow();
//			LoginHandler login = (LoginHandler) h;
//			
//			JTextField textField = login.getTextUsername();
//		    JTextComponentTester userTester = new JTextComponentTester();
//		    userTester.actionEnterText(textField, "admin");
//
//		    JPasswordField pwdField = login.getTextPassword();
//			JTextComponentTester passTester = new JTextComponentTester();
//			passTester.actionEnterText(pwdField, "admin");
//			
//			JButton loginButton = ((LoginHandler) h).getBnLogin();
//			JButtonTester tester = new JButtonTester();
//			tester.actionClick(loginButton);
//						
//			h = (SearchHandler) rt.getCurrentWindow();
//			SearchHandler search = (SearchHandler) h;
//			
//			@SuppressWarnings("rawtypes")
//			JComboBox cbSearchBy = search.getFilters().get(0).getCbSearchBy();
//			JComboBoxTester cbSearchByTester = new JComboBoxTester();
//			cbSearchByTester.actionSelectIndex(cbSearchBy, 1);
//
//			@SuppressWarnings("rawtypes")
//			JComboBox cbOption = search.getFilters().get(0).getCbOptions();
//			JComboBoxTester cbOptionsTester = new JComboBoxTester();
//			cbOptionsTester.actionSelectIndex(cbOption, 1);
//
//			JTextField textKey = search.getFilters().get(0).getTxtKeyword();
//			JTextComponentTester keywordTester = new JTextComponentTester();
//			keywordTester.actionEnterText(textKey, "Brian");
//
//			JButton searchButton = search.getBnSearch();
//			JButtonTester bnSearchTest = new JButtonTester();
//			bnSearchTest.actionClick(searchButton);
//			
//			h = (SearchResultHandler) rt.getCurrentWindow();
//			SearchResultHandler searchHandler = (SearchResultHandler) h;
//			
//			JButton backButton = searchHandler.getBnBack();
//			assertEquals("Wrong Button after selection", 
//					"Back", backButton.getText());
//			JButtonTester bnBackTest = new JButtonTester();
//			bnBackTest.actionClick(backButton);
//    	    
//    }
//
//	public SearchResultHandlerTest(String name) { super(name); }
//
//	public static void main(String[] args) {
//		TestHelper.runTests(args, SearchResultHandlerTest.class);
//	}
//}