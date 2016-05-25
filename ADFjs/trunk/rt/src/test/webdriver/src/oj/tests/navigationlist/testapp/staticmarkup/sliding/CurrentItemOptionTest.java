package oj.tests.navigationlist.testapp.staticmarkup.sliding;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;

public class CurrentItemOptionTest extends NavigationlistTestBase {
	private static final String TEST_PAGE = "htmlMarkup_static.html";
	private static final String TEST_PAGE_TITLE = "Navigation List: Static Markup Test";
	private static final String NAVLIST_ITEM_ZOOMIN = "zoomin";
	private static final String NAVLIST_ITEM_ZOOMOUT = "zoomout";
	private static final String NAVLIST_ITEM_PLAYBACK = "playback";
	private static final String NAVLIST_ITEM_PLAY = "play";
	private static final String NAVLIST_HIERARCHICAL_MENU_PLAYBACK = "Playback";
	private static final String NAVLIST_ID = "navList";
	private static final String NAVLIST_DISABLED_ITEM = "print";

	public CurrentItemOptionTest() {
		super("ojnavigationlist", "navigationlist/navigationlistTest");
	}

	@Test(groups = { "navigationlist" })
	public void testcurrentItemOptionAndObservableUpdatedOnSelectionByUserAction() throws Exception {
		startupTest(TEST_PAGE, null);
		verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

		// wait for the navigationlist to be displayed
		waitForElementVisible(NAVLIST_ID);

		// Click on leaf node zoomin item
		selectListItem(NAVLIST_ID, NAVLIST_ITEM_ZOOMIN);

		//Click on "get Current Item value" button
		 WebElement btn = getElement("id=getCurr");
		 btn.click();
		 
		 
		 WebElement result = getElement("id=results_c");
		 String currItemVal = result.getText();
		 
		boolean isValCorrect = currItemVal
				.indexOf("Option Value: zoomin Observable Value: zoomin") > -1;
		
		Assert.assertTrue(isValCorrect,
				"current Item value is not correct.");
	
	
	}
	
	@Test(groups = { "navigationlist" })
	public void testCurrentItemOptionAndObservableUpdatedAfterProgrammaticSelection() throws Exception {
		startupTest(TEST_PAGE, null);
		verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

		// wait for the navigationlist to be displayed
		waitForElementVisible(NAVLIST_ID);

		// Click on button to programmatically select node zoomin 
		 WebElement selectbtn = getElement("id=setselob");
		 selectbtn.click();

		//Click on "get Current Item value" button
		 WebElement btn = getElement("id=getCurr");
		 btn.click();
		 
		 
		 WebElement result = getElement("id=results_c");
		 String currItemVal = result.getText();
		 
		boolean isValCorrect = currItemVal
				.indexOf("Option Value: null Observable Value: undefined") > -1;
		
		Assert.assertTrue(isValCorrect,
				"current Item value is not correct.");
	
	
	}
	
	//automation for bug 22461870  
	@Test(groups = { "navigationlist" })
	public void testCurrentItemOptionAndObservableValueAfterClickingSelecteditem() throws Exception {
		startupTest(TEST_PAGE, null);
		verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

		// wait for the navigationlist to be displayed
		waitForElementVisible(NAVLIST_ID);
		
		// Click on button to programmatically select node zoomin 
		 WebElement selectbtn = getElement("id=setselob");
		 selectbtn.click();

		// Click on leaf node zoomin item
		selectListItem(NAVLIST_ID, NAVLIST_ITEM_ZOOMIN);
			
		//Click on "get Current Item value" button
		 WebElement btn = getElement("id=getCurr");
		 btn.click();
		 
		 
		 WebElement result = getElement("id=results_c");
		 String currItemVal = result.getText();
		 
		boolean isValCorrect = currItemVal
				.indexOf("Option Value: zoomin Observable Value: zoomin") > -1;
		
		Assert.assertTrue(isValCorrect,
				"current Item value is not correct.");
	
	
	}
}
