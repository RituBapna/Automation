package oj.tests.navigationlist.testapp.staticmarkup.collapsible;

import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;

public class EventsTest extends NavigationlistTestBase {
	private static final String TEST_PAGE = "htmlMarkup_static.html";
	private static final String TEST_PAGE_TITLE = "Navigation List: Static Markup Test";
	private static final String NAVLIST_ITEM_ZOOMIN = "zoomin";
	private static final String NAVLIST_ITEM_ZOOMOUT = "zoomout";
	private static final String NAVLIST_ITEM_PLAYBACK = "playback";
	private static final String NAVLIST_ITEM_PLAY = "play";
	private static final String NAVLIST_HIERARCHICAL_MENU_PLAYBACK = "Playback";
	private static final String NAVLIST_ID = "navList";
	private static final String NAVLIST_DISABLED_ITEM = "print";

	public EventsTest() {
		super("ojnavigationlist", "navigationlist/navigationlistTest");
	}

	@Test(groups = { "navigationlist" })
	public void testEventsOnItemSelectionByUserAction() throws Exception {
		startupTest(TEST_PAGE, null);
		verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

		// wait for the navigationlist to be displayed
		waitForElementVisible(NAVLIST_ID);
		// Change drill mode to collapsible
		changeDrillModeToCollapsible();
		// Clear Event Log window
		WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
		clearEventLogBtn.click();

		// Click on leaf node zoomin item
		selectListItem(NAVLIST_ID, NAVLIST_ITEM_ZOOMIN);

		// Get the log Window data
		String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
		// Get event log
		// WebElement eventLog = getElement("id=eventlog");
		System.out.println("####### eventLog: " + eventLog);

		boolean onBeforeCurrentItemLog = eventLog
				.indexOf("ON : ojbeforecurrentitem: navList data { key: zoomin; item: [object Object];}") > -1;
		boolean optionBeforeCurrentItemLog = eventLog
				.indexOf("OPTION : ojbeforecurrentitem: navList data { key: zoomin; item: [object Object];}") > -1;
		boolean onOptionChangeCurrentItemLog = eventLog.indexOf(
				"ON : ojoptionchange: navList data { item: [object Object]; option: currentItem; previousValue: null; value: zoomin; optionMetadata: [object Object];}") > -1;
		boolean optionOptionChangeCurrentItemLog = eventLog.indexOf(
				"OPTION : ojoptionchange: navList data { item: [object Object]; option: currentItem; previousValue: null; value: zoomin; optionMetadata: [object Object];}") > -1;

		boolean onBeforeSelectLog = eventLog
				.indexOf("ON : ojbeforeselect: navList data { item: [object Object]; key: zoomin;}") > -1;
		boolean optionBeforeSelectLog = eventLog
				.indexOf("OPTION : ojbeforeselect: navList data { item: [object Object]; key: zoomin;}") > -1;
		boolean onOptionChangeSelectLog = eventLog.indexOf(
				"ON : ojoptionchange: navList data { item: [object Object]; option: selection; previousValue: null; value: zoomin; optionMetadata: [object Object];}") > -1;
		boolean optionOptionChangeSelectLog = eventLog.indexOf(
				"OPTION : ojoptionchange: navList data { item: [object Object]; option: selection; previousValue: null; value: zoomin; optionMetadata: [object Object];}") > -1;

		Assert.assertTrue(onBeforeCurrentItemLog,
				"on method Used: ojbeforecurrentitem event called with correct parameters");
		Assert.assertTrue(optionBeforeCurrentItemLog,
				"option Used: ojbeforecurrentitem event called with correct parameters");
		Assert.assertTrue(onOptionChangeCurrentItemLog,
				"on method used: ojoptionchange event for currentitem called with correct parameters");
		Assert.assertTrue(optionOptionChangeCurrentItemLog,
				"option used: ojoptionchange event for currentitem called with correct parameters");
		Assert.assertTrue(onBeforeSelectLog, "on method used: ojbeforeselect event called with correct parameters");
		Assert.assertTrue(optionBeforeSelectLog, "option used: ojbeforeselect event called with correct parameters");
		Assert.assertTrue(onOptionChangeSelectLog,
				"on method used: ojoptionchange event called for selection with correct parameters");
		Assert.assertTrue(optionOptionChangeSelectLog,
				"option used: ojoptionchange event called for selection with correct parameters");

		// verify that item is selected
		verifyItemIsSelected(NAVLIST_ID, NAVLIST_ITEM_ZOOMIN);
	}

	@Test(groups = { "navigationlist" })
	public void testEventsOnItemSelectionByOptionMethod() throws Exception {
		startupTest(TEST_PAGE, null);
		verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

		// wait for the navigationlist to be displayed
		waitForElementVisible(NAVLIST_ID);
		// Change drill mode to collapsible
		changeDrillModeToCollapsible();
		// Clear Event Log window
		WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
		clearEventLogBtn.click();

		// Set selection option using option method
		WebElement setBtn = getElement("id=setselop");
		setBtn.click();

		// Get the log Window data
		String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
		// Get event log
		// WebElement eventLog = getElement("id=eventlog");
		System.out.println("####### eventLog: " + eventLog);

		boolean onBeforeCurrentItemLog = eventLog
				.indexOf("ON : ojbeforecurrentitem: navList data { key: zoomin; item: [object Object];}") > -1;
		boolean optionBeforeCurrentItemLog = eventLog
				.indexOf("OPTION : ojbeforecurrentitem: navList data { key: zoomin; item: [object Object];}") > -1;
		boolean onOptionChangeCurrentItemLog = eventLog.indexOf(
				"ON : ojoptionchange: navList data { option: currentItem; previousValue: null; value: zoomin; optionMetadata: [object Object];}") > -1;
		boolean optionOptionChangeCurrentItemLog = eventLog.indexOf(
				"OPTION : ojoptionchange: navList data { option: currentItem; previousValue: null; value: zoomin; optionMetadata: [object Object];}") > -1;

		boolean onBeforeSelectLog = eventLog
				.indexOf("ON : ojbeforeselect: navList data { item: [object Object]; key: zoomin;}") > -1;
		boolean optionBeforeSelectLog = eventLog
				.indexOf("OPTION : ojbeforeselect: navList data { item: [object Object]; key: zoomin;}") > -1;
		boolean onOptionChangeSelectLog = eventLog.indexOf(
				"ON : ojoptionchange: navList data { option: selection; previousValue: null; value: zoomin; optionMetadata: [object Object];}") > -1;
		boolean optionOptionChangeSelectLog = eventLog.indexOf(
				"OPTION : ojoptionchange: navList data { option: selection; previousValue: null; value: zoomin; optionMetadata: [object Object];}") > -1;

		Assert.assertFalse(onBeforeCurrentItemLog,
				"on method Used: ojbeforecurrentitem event called with correct parameters");
		Assert.assertFalse(optionBeforeCurrentItemLog,
				"option Used: ojbeforecurrentitem event called with correct parameters");
		Assert.assertFalse(onOptionChangeCurrentItemLog,
				"on method used: ojoptionchange event for currentitem called with correct parameters");
		Assert.assertFalse(optionOptionChangeCurrentItemLog,
				"option used: ojoptionchange event for currentitem called with correct parameters");
		Assert.assertTrue(onBeforeSelectLog, "on method used: ojbeforeselect event called with correct parameters");
		Assert.assertTrue(optionBeforeSelectLog, "option used: ojbeforeselect event called with correct parameters");
		Assert.assertTrue(onOptionChangeSelectLog,
				"on method used: ojoptionchange event called for selection with correct parameters");
		Assert.assertTrue(optionOptionChangeSelectLog,
				"option used: ojoptionchange event called for selection with correct parameters");

		// verify that item is selected
		verifyItemIsSelected(NAVLIST_ID, NAVLIST_ITEM_ZOOMIN);
	}
	/*
	 * // Will uncomment this after bug 21225866 is fixed
	 * 
	 * @Test(groups = { "navigationlist" }) public void
	 * testEventsOnItemSelectionByObservable() throws Exception {
	 * startupTest(TEST_PAGE, null); verifyTitle("Incorrect page title;",
	 * TEST_PAGE_TITLE);
	 * 
	 * //wait for the navigationlist to be displayed
	 * waitForElementVisible(NAVLIST_ID); //Change drill mode to collapsible
	 * changeDrillModeToCollapsible(); //Clear Event Log window WebElement
	 * clearEventLogBtn = getElement("id=clearEventLogBtn");
	 * clearEventLogBtn.click();
	 * 
	 * //Set selection option using observable WebElement setBtn =
	 * getElement("id=setselob"); setBtn.click();
	 * 
	 * //Get the log Window data String eventLog = evalJavascript(
	 * "return $('#eventlog').ojTextArea('option', 'value')"); //Get event log
	 * // WebElement eventLog = getElement("id=eventlog"); System.out.println(
	 * "####### eventLog: " + eventLog);
	 * 
	 * boolean onBeforeCurrentItemLog = eventLog.indexOf(
	 * "ON : ojbeforecurrentitem: navList data { key: zoomin; item: [object Object];}"
	 * ) > -1; boolean optionBeforeCurrentItemLog = eventLog.indexOf(
	 * "OPTION : ojbeforecurrentitem: navList data { key: zoomin; item: [object Object];}"
	 * ) > -1; boolean onOptionChangeCurrentItemLog = eventLog.indexOf(
	 * "ON : ojoptionchange: navList data { option: currentItem; previousValue: null; value: zoomin; optionMetadata: [object Object];}"
	 * ) > -1; boolean optionOptionChangeCurrentItemLog = eventLog.indexOf(
	 * "OPTION : ojoptionchange: navList data { option: currentItem; previousValue: null; value: zoomin; optionMetadata: [object Object];}"
	 * ) > -1;
	 * 
	 * boolean onBeforeSelectLog = eventLog.indexOf(
	 * "ON : ojbeforeselect: navList data { item: [object Object]; key: zoomin;}"
	 * ) > -1; boolean optionBeforeSelectLog = eventLog.indexOf(
	 * "OPTION : ojbeforeselect: navList data { item: [object Object]; key: zoomin;}"
	 * ) > -1; boolean onOptionChangeSelectLog = eventLog.indexOf(
	 * "ON : ojoptionchange: navList data { option: selection; previousValue: null; value: zoomin; optionMetadata: [object Object];}"
	 * ) > -1; boolean optionOptionChangeSelectLog = eventLog.indexOf(
	 * "OPTION : ojoptionchange: navList data { option: selection; previousValue: null; value: zoomin; optionMetadata: [object Object];}"
	 * ) > -1;
	 * 
	 * 
	 * Assert.assertFalse(onBeforeCurrentItemLog,
	 * "on method Used: ojbeforecurrentitem event called with correct parameters"
	 * ); Assert.assertFalse(optionBeforeCurrentItemLog,
	 * "option Used: ojbeforecurrentitem event called with correct parameters");
	 * Assert.assertFalse(onOptionChangeCurrentItemLog,
	 * "on method used: ojoptionchange event for currentitem called with correct parameters"
	 * ); Assert.assertFalse(optionOptionChangeCurrentItemLog,
	 * "option used: ojoptionchange event for currentitem called with correct parameters"
	 * ); Assert.assertFalse(onBeforeSelectLog,
	 * "on method used: ojbeforeselect event called with correct parameters");
	 * Assert.assertFalse(optionBeforeSelectLog,
	 * "option used: ojbeforeselect event called with correct parameters");
	 * Assert.assertTrue(onOptionChangeSelectLog,
	 * "on method used: ojoptionchange event called for selection with correct parameters"
	 * ); Assert.assertTrue(optionOptionChangeSelectLog,
	 * "option used: ojoptionchange event called for selection with correct parameters"
	 * );
	 * 
	 * //verify that item is selected verifyItemIsSelected(NAVLIST_ID,
	 * NAVLIST_ITEM_ZOOMIN); }
	 */

	@Test(groups = { "navigationlist" })
	public void testEventsOnDisabledItemSelectionByUserClick() throws Exception {
		startupTest(TEST_PAGE, null);
		verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

		// wait for the navigationlist to be displayed
		waitForElementVisible(NAVLIST_ID);
		// Change drill mode to collapsible
		changeDrillModeToCollapsible();
		// Clear Event Log window
		WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
		clearEventLogBtn.click();

		// click on disabled item
		selectListItem(NAVLIST_ID, NAVLIST_DISABLED_ITEM);

		// Get the log Window data
		String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
		// Get event log
		// WebElement eventLog = getElement("id=eventlog");
		System.out.println("####### eventLog: " + eventLog);

		Assert.assertEquals(eventLog, "", "No events raised");
		verifyItemIsNotSelected(NAVLIST_ID, NAVLIST_DISABLED_ITEM);
	}

	/*
	 * //Will enable the test after bug 21226222 is fixed
	 * 
	 * @Test(groups = { "navigationlist" }) public void
	 * testEventsOnDisabledItemSelectionByOptionMethod() throws Exception {
	 * startupTest(TEST_PAGE, null); verifyTitle("Incorrect page title;",
	 * TEST_PAGE_TITLE);
	 * 
	 * //wait for the navigationlist to be displayed
	 * waitForElementVisible(NAVLIST_ID);
	 * 
	 * //Clear Event Log window WebElement clearEventLogBtn =
	 * getElement("id=clearEventLogBtn"); clearEventLogBtn.click();
	 * 
	 * //set selection option using option method WebElement setBtn =
	 * getElement("id=setsel1"); setBtn.click();
	 * 
	 * //Get the log Window data String eventLog = evalJavascript(
	 * "return $('#eventlog').ojTextArea('option', 'value')"); //Get event log
	 * // WebElement eventLog = getElement("id=eventlog"); System.out.println(
	 * "####### eventLog: " + eventLog);
	 * 
	 * Assert.assertEquals(eventLog, "", "No events raised");
	 * verifyItemIsNotSelected(NAVLIST_ID, NAVLIST_DISABLED_ITEM); }
	 */
	@Test(groups = { "navigationlist" })
	public void testEventsOnChangeInItemSelectionByUserAction() throws Exception {
		startupTest(TEST_PAGE, null);
		verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

		// wait for the navigationlist to be displayed
		waitForElementVisible(NAVLIST_ID);
		// Change drill mode to collapsible
		changeDrillModeToCollapsible();

		// Click on leaf node zoomin item
		selectListItem(NAVLIST_ID, NAVLIST_ITEM_ZOOMIN);

		// Clear Event Log window
		WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
		clearEventLogBtn.click();

		// Click on zoomout item to change selection
		selectListItem(NAVLIST_ID, NAVLIST_ITEM_ZOOMOUT);

		// Get the log Window data
		String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
		// Get event log
		// WebElement eventLog = getElement("id=eventlog");
		System.out.println("####### eventLog: " + eventLog);

		boolean onBeforeCurrentItemLog = eventLog.indexOf(
				"ON : ojbeforecurrentitem: navList data { key: zoomout; item: [object Object]; previousKey: zoomin; previousItem: [object Object];}") > -1;
		boolean optionBeforeCurrentItemLog = eventLog.indexOf(
				"OPTION : ojbeforecurrentitem: navList data { key: zoomout; item: [object Object]; previousKey: zoomin; previousItem: [object Object];}") > -1;
		boolean onOptionChangeCurrentItemLog = eventLog.indexOf(
				"ON : ojoptionchange: navList data { item: [object Object]; option: currentItem; previousValue: zoomin; value: zoomout; optionMetadata: [object Object];}") > -1;
		boolean optionOptionChangeCurrentItemLog = eventLog.indexOf(
				"OPTION : ojoptionchange: navList data { item: [object Object]; option: currentItem; previousValue: zoomin; value: zoomout; optionMetadata: [object Object];}") > -1;

		boolean onBeforeSelectLog = eventLog
				.indexOf("ON : ojbeforeselect: navList data { item: [object Object]; key: zoomout;}") > -1;
		boolean optionBeforeSelectLog = eventLog
				.indexOf("OPTION : ojbeforeselect: navList data { item: [object Object]; key: zoomout;}") > -1;
		boolean onOptionChangeSelectLog = eventLog.indexOf(
				"ON : ojoptionchange: navList data { item: [object Object]; option: selection; previousValue: zoomin; value: zoomout; optionMetadata: [object Object];}") > -1;
		boolean optionOptionChangeSelectLog = eventLog.indexOf(
				"OPTION : ojoptionchange: navList data { item: [object Object]; option: selection; previousValue: zoomin; value: zoomout; optionMetadata: [object Object];}") > -1;

		Assert.assertTrue(onBeforeCurrentItemLog,
				"on method Used: ojbeforecurrentitem event called with correct parameters");
		Assert.assertTrue(optionBeforeCurrentItemLog,
				"option Used: ojbeforecurrentitem event called with correct parameters");
		Assert.assertTrue(onOptionChangeCurrentItemLog,
				"on method used: ojoptionchange event for currentitem called with correct parameters");
		Assert.assertTrue(optionOptionChangeCurrentItemLog,
				"option used: ojoptionchange event for currentitem called with correct parameters");
		Assert.assertTrue(onBeforeSelectLog, "on method used: ojbeforeselect event called with correct parameters");
		Assert.assertTrue(optionBeforeSelectLog, "option used: ojbeforeselect event called with correct parameters");
		Assert.assertTrue(onOptionChangeSelectLog,
				"on method used: ojoptionchange event called for selection with correct parameters");
		Assert.assertTrue(optionOptionChangeSelectLog,
				"option used: ojoptionchange event called for selection with correct parameters");

		// verify that item is selected
		verifyItemIsSelected(NAVLIST_ID, NAVLIST_ITEM_ZOOMOUT);
		verifyItemIsNotSelected(NAVLIST_ID, NAVLIST_ITEM_ZOOMIN);
	}

	@Test(groups = { "navigationlist" })
	public void testEventsOnChangeInItemSelectionByOptionMethod() throws Exception {
		startupTest(TEST_PAGE, null);
		verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

		// wait for the navigationlist to be displayed
		waitForElementVisible(NAVLIST_ID);

		// Change drill mode to collapsible
		changeDrillModeToCollapsible();
		// Set selection option using option method
		WebElement setBtn = getElement("id=setselop");
		setBtn.click();

		// Clear Event Log window
		WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
		clearEventLogBtn.click();

		// Change selection option using option method
		setBtn = getElement("id=setselop1");
		setBtn.click();

		// Get the log Window data
		String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
		// Get event log
		// WebElement eventLog = getElement("id=eventlog");
		System.out.println("####### eventLog: " + eventLog);

		boolean onBeforeCurrentItemLog = eventLog.indexOf(
				"ON : ojbeforecurrentitem: navList data { key: zoomout; item: [object Object]; previousKey: zoomin; previousItem: [object Object];}") > -1;
		boolean optionBeforeCurrentItemLog = eventLog.indexOf(
				"OPTION : ojbeforecurrentitem: navList data { key: zoomout; item: [object Object]; previousKey: zoomin; previousItem: [object Object];}") > -1;
		boolean onOptionChangeCurrentItemLog = eventLog.indexOf(
				"ON : ojoptionchange: navList data { option: currentItem; previousValue: zoomin; value: zoomout; optionMetadata: [object Object];}") > -1;
		boolean optionOptionChangeCurrentItemLog = eventLog.indexOf(
				"OPTION : ojoptionchange: navList data { option: currentItem; previousValue: zoomin; value: zoomout; optionMetadata: [object Object];}") > -1;

		boolean onBeforeSelectLog = eventLog
				.indexOf("ON : ojbeforeselect: navList data { item: [object Object]; key: zoomout;}") > -1;
		boolean optionBeforeSelectLog = eventLog
				.indexOf("OPTION : ojbeforeselect: navList data { item: [object Object]; key: zoomout;}") > -1;
		boolean onOptionChangeSelectLog = eventLog.indexOf(
				"ON : ojoptionchange: navList data { option: selection; previousValue: zoomin; value: zoomout; optionMetadata: [object Object];}") > -1;
		boolean optionOptionChangeSelectLog = eventLog.indexOf(
				"OPTION : ojoptionchange: navList data { option: selection; previousValue: zoomin; value: zoomout; optionMetadata: [object Object];}") > -1;

		Assert.assertFalse(onBeforeCurrentItemLog,
				"on method Used: ojbeforecurrentitem event called with correct parameters");
		Assert.assertFalse(optionBeforeCurrentItemLog,
				"option Used: ojbeforecurrentitem event called with correct parameters");
		Assert.assertFalse(onOptionChangeCurrentItemLog,
				"on method used: ojoptionchange event for currentitem called with correct parameters");
		Assert.assertFalse(optionOptionChangeCurrentItemLog,
				"option used: ojoptionchange event for currentitem called with correct parameters");
		Assert.assertTrue(onBeforeSelectLog, "on method used: ojbeforeselect event called with correct parameters");
		Assert.assertTrue(optionBeforeSelectLog, "option used: ojbeforeselect event called with correct parameters");
		Assert.assertTrue(onOptionChangeSelectLog,
				"on method used: ojoptionchange event called for selection with correct parameters");
		Assert.assertTrue(optionOptionChangeSelectLog,
				"option used: ojoptionchange event called for selection with correct parameters");

		// verify that item is selected
		verifyItemIsNotSelected(NAVLIST_ID, NAVLIST_ITEM_ZOOMIN);
		verifyItemIsSelected(NAVLIST_ID, NAVLIST_ITEM_ZOOMOUT);
	}

	@Test(groups = { "navigationlist" })
	public void testEventsOnRootParentExapandByUserAction() throws Exception {
		startupTest(TEST_PAGE, null);
		verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

		// wait for the navigationlist to be displayed
		waitForElementVisible(NAVLIST_ID);
		// Change drill mode to collapsible
		changeDrillModeToCollapsible();
		// Clear Event Log window
		WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
		clearEventLogBtn.click();

		// Click on playback to expand
		navigate(NAVLIST_ID, NAVLIST_ITEM_PLAYBACK);

		// Get the log Window data
		String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
		// Get event log
		// WebElement eventLog = getElement("id=eventlog");
		System.out.println("####### eventLog: " + eventLog);

		boolean onBeforeCurrentItemLog = eventLog
				.indexOf("ON : ojbeforecurrentitem: navList data { key: playback; item: [object Object];}") > -1;
		boolean optionBeforeCurrentItemLog = eventLog
				.indexOf("OPTION : ojbeforecurrentitem: navList data { key: playback; item: [object Object];}") > -1;
		boolean onOptionChangeCurrentItemLog = eventLog.indexOf(
				"ON : ojoptionchange: navList data { item: [object Object]; option: currentItem; previousValue: null; value: playback; optionMetadata: [object Object];}") > -1;
		boolean optionOptionChangeCurrentItemLog = eventLog.indexOf(
				"OPTION : ojoptionchange: navList data { item: [object Object]; option: currentItem; previousValue: null; value: playback; optionMetadata: [object Object];}") > -1;

		boolean onBeforeExpandLog = eventLog
				.indexOf("ON : ojbeforeexpand: navList data { item: [object Object]; key: playback;}") > -1;
		boolean optionBeforeExpandLog = eventLog
				.indexOf("OPTION : ojbeforeexpand: navList data { item: [object Object]; key: playback;}") > -1;

		boolean onExpandLog = eventLog
				.indexOf("ON : ojexpand: navList data { item: [object Object]; key: playback;}") > -1;
		boolean optionExpandLog = eventLog
				.indexOf("OPTION : ojexpand: navList data { item: [object Object]; key: playback;}") > -1;
		/*
		 * boolean onBeforeCurrentItemLog1 = eventLog.indexOf(
		 * "ON : ojbeforecurrentitem: navList data { key: prev; item: [object Object]; previousKey: playback; previousItem: [object Object];}"
		 * ) > -1; boolean optionBeforeCurrentItemLog1 = eventLog.indexOf(
		 * "OPTION : ojbeforecurrentitem: navList data { key: prev; item: [object Object]; previousKey: playback; previousItem: [object Object];}"
		 * ) > -1; boolean onOptionChangeCurrentItemLog1 = eventLog.indexOf(
		 * "ON : ojoptionchange: navList data { option: currentItem; previousValue: playback; value: prev; optionMetadata: [object Object];}"
		 * ) > -1; boolean optionOptionChangeCurrentItemLog1 = eventLog.indexOf(
		 * "OPTION : ojoptionchange: navList data { option: currentItem; previousValue: playback; value: prev; optionMetadata: [object Object];}"
		 * ) > -1;
		 */

		Assert.assertTrue(onBeforeCurrentItemLog,
				"on method Used: ojbeforecurrentitem event called with correct parameters");
		Assert.assertTrue(optionBeforeCurrentItemLog,
				"option Used: ojbeforecurrentitem event called with correct parameters");
		Assert.assertTrue(onOptionChangeCurrentItemLog,
				"on method used: ojoptionchange event for currentitem called with correct parameters");
		Assert.assertTrue(optionOptionChangeCurrentItemLog,
				"option used: ojoptionchange event for currentitem called with correct parameters");
		Assert.assertTrue(onBeforeExpandLog, "on method used: ojbeforeselect event called with correct parameters");
		Assert.assertTrue(optionBeforeExpandLog, "option used: ojbeforeselect event called with correct parameters");
		Assert.assertTrue(onExpandLog,
				"on method used: ojoptionchange event called for selection with correct parameters");
		Assert.assertTrue(optionExpandLog,
				"option used: ojoptionchange event called for selection with correct parameters");
		/*
		 * Assert.assertTrue(onBeforeCurrentItemLog1,
		 * "on method Used: ojbeforecurrentitem event called with correct parameters"
		 * ); Assert.assertTrue(optionBeforeCurrentItemLog1,
		 * "option Used: ojbeforecurrentitem event called with correct parameters"
		 * ); Assert.assertTrue(onOptionChangeCurrentItemLog1,
		 * "on method used: ojoptionchange event for currentitem called with correct parameters"
		 * ); Assert.assertTrue(optionOptionChangeCurrentItemLog1,
		 * "option used: ojoptionchange event for currentitem called with correct parameters"
		 * );
		 */

	}

	@Test(groups = { "navigationlist" })
	public void testEventsOnChildItemExapandByUserAction() throws Exception {
		startupTest(TEST_PAGE, null);
		verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

		// wait for the navigationlist to be displayed
		waitForElementVisible(NAVLIST_ID);
		// Change drill mode to collapsible
		changeDrillModeToCollapsible();

		// Click on playback (root parent) to expand
		navigate(NAVLIST_ID, NAVLIST_ITEM_PLAYBACK);

		// Clear Event Log window
		WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
		clearEventLogBtn.click();

		// Click on play (child item) to expand
		navigate(NAVLIST_ID, NAVLIST_ITEM_PLAY);

		// Get the log Window data
		String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
		// Get event log
		// WebElement eventLog = getElement("id=eventlog");
		System.out.println("####### eventLog: " + eventLog);

		boolean onBeforeCurrentItemLog = eventLog.indexOf(
				"ON : ojbeforecurrentitem: navList data { key: play; item: [object Object]; previousKey: playback; previousItem: [object Object];}") > -1;
		boolean optionBeforeCurrentItemLog = eventLog.indexOf(
				"OPTION : ojbeforecurrentitem: navList data { key: play; item: [object Object]; previousKey: playback; previousItem: [object Object];}") > -1;
		boolean onOptionChangeCurrentItemLog = eventLog.indexOf(
				"ON : ojoptionchange: navList data { item: [object Object]; option: currentItem; previousValue: playback; value: play; optionMetadata: [object Object];}") > -1;
		boolean optionOptionChangeCurrentItemLog = eventLog.indexOf(
				"OPTION : ojoptionchange: navList data { item: [object Object]; option: currentItem; previousValue: playback; value: play; optionMetadata: [object Object];}") > -1;

		boolean onBeforeExpandLog = eventLog
				.indexOf("ON : ojbeforeexpand: navList data { item: [object Object]; key: play;}") > -1;
		boolean optionBeforeExpandLog = eventLog
				.indexOf("OPTION : ojbeforeexpand: navList data { item: [object Object]; key: play;}") > -1;

		boolean onExpandLog = eventLog.indexOf("ON : ojexpand: navList data { item: [object Object]; key: play;}") > -1;
		boolean optionExpandLog = eventLog
				.indexOf("OPTION : ojexpand: navList data { item: [object Object]; key: play;}") > -1;
		/*
		 * boolean onBeforeCurrentItemLog1 = eventLog.indexOf(
		 * "ON : ojbeforecurrentitem: navList data { key: track1; item: [object Object]; previousKey: play; previousItem: [object Object];}"
		 * ) > -1; boolean optionBeforeCurrentItemLog1 = eventLog.indexOf(
		 * "OPTION : ojbeforecurrentitem: navList data { key: track1; item: [object Object]; previousKey: play; previousItem: [object Object];}"
		 * ) > -1; boolean onOptionChangeCurrentItemLog1 = eventLog.indexOf(
		 * "ON : ojoptionchange: navList data { option: currentItem; previousValue: play; value: track1; optionMetadata: [object Object];}"
		 * ) > -1; boolean optionOptionChangeCurrentItemLog1 = eventLog.indexOf(
		 * "OPTION : ojoptionchange: navList data { option: currentItem; previousValue: play; value: track1; optionMetadata: [object Object];}"
		 * ) > -1;
		 */

		Assert.assertTrue(onBeforeCurrentItemLog,
				"on method Used: ojbeforecurrentitem event called with correct parameters");
		Assert.assertTrue(optionBeforeCurrentItemLog,
				"option Used: ojbeforecurrentitem event called with correct parameters");
		Assert.assertTrue(onOptionChangeCurrentItemLog,
				"on method used: ojoptionchange event for currentitem called with correct parameters");
		Assert.assertTrue(optionOptionChangeCurrentItemLog,
				"option used: ojoptionchange event for currentitem called with correct parameters");

		Assert.assertTrue(onBeforeExpandLog, "on method used: ojbeforeselect event called with correct parameters");
		Assert.assertTrue(optionBeforeExpandLog, "option used: ojbeforeselect event called with correct parameters");
		Assert.assertTrue(onExpandLog,
				"on method used: ojoptionchange event called for selection with correct parameters");
		Assert.assertTrue(optionExpandLog,
				"option used: ojoptionchange event called for selection with correct parameters");
		/*
		 * Assert.assertTrue(onBeforeCurrentItemLog1,
		 * "on method Used: ojbeforecurrentitem event called with correct parameters"
		 * ); Assert.assertTrue(optionBeforeCurrentItemLog1,
		 * "option Used: ojbeforecurrentitem event called with correct parameters"
		 * ); Assert.assertTrue(onOptionChangeCurrentItemLog1,
		 * "on method used: ojoptionchange event for currentitem called with correct parameters"
		 * ); Assert.assertTrue(optionOptionChangeCurrentItemLog1,
		 * "option used: ojoptionchange event for currentitem called with correct parameters"
		 * );
		 * 
		 */
	}

	@Test(groups = { "navigationlist" })
	public void testEventsOnCollapseByUserAction() throws Exception {
		startupTest(TEST_PAGE, null);
		verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

		// wait for the navigationlist to be displayed
		waitForElementVisible(NAVLIST_ID);
		// Change drill mode to collapsible
		changeDrillModeToCollapsible();

		// Click on playback to expand
		navigate(NAVLIST_ID, NAVLIST_ITEM_PLAYBACK);

		// Click on play to expand
		navigate(NAVLIST_ID, NAVLIST_ITEM_PLAY);

		// Clear Event Log window
		WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
		clearEventLogBtn.click();

		// Click of play to collapse
		collapse(NAVLIST_ID, NAVLIST_ITEM_PLAY);
		waitForMilliseconds(1000);

		// Get the log Window data
		String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
		// Get event log
		// WebElement eventLog = getElement("id=eventlog");
		System.out.println("####### eventLog: " + eventLog);

		boolean onBeforeCollapseLog = eventLog
				.indexOf("ON : ojbeforecollapse: navList data { item: [object Object]; key: play;}") > -1;
		boolean optionBeforeCollapseLog = eventLog
				.indexOf("OPTION : ojbeforecollapse: navList data { item: [object Object]; key: play;}") > -1;
		boolean onCollapseLog = eventLog
				.indexOf("ON : ojcollapse: navList data { item: [object Object]; key: play;}") > -1;
		boolean optionCollapseLog = eventLog
				.indexOf("OPTION : ojcollapse: navList data { item: [object Object]; key: play;}") > -1;
		/*
		 * boolean onBeforeCurrentItemLog = eventLog.indexOf(
		 * "ON : ojbeforecurrentitem: navList data { key: play; item: [object Object]; previousKey: track1; previousItem: [object Object];}"
		 * ) > -1; boolean optionBeforeCurrentItemLog = eventLog.indexOf(
		 * "OPTION : ojbeforecurrentitem: navList data { key: play; item: [object Object]; previousKey: track1; previousItem: [object Object];}"
		 * ) > -1; boolean onOptionChangeCurrentItemLog = eventLog.indexOf(
		 * "ON : ojoptionchange: navList data { option: currentItem; previousValue: track1; value: play; optionMetadata: [object Object];}"
		 * ) > -1; boolean optionOptionChangeCurrentItemLog = eventLog.indexOf(
		 * "OPTION : ojoptionchange: navList data { option: currentItem; previousValue: track1; value: play; optionMetadata: [object Object];}"
		 * ) > -1;
		 * 
		 * 
		 * Assert.assertTrue(onBeforeCurrentItemLog,
		 * "on method Used: ojbeforecurrentitem event called with correct parameters"
		 * ); Assert.assertTrue(optionBeforeCurrentItemLog,
		 * "option Used: ojbeforecurrentitem event called with correct parameters"
		 * ); Assert.assertTrue(onOptionChangeCurrentItemLog,
		 * "on method used: ojoptionchange event for currentitem called with correct parameters"
		 * ); Assert.assertTrue(optionOptionChangeCurrentItemLog,
		 * "option used: ojoptionchange event for currentitem called with correct parameters"
		 * );
		 */
		Assert.assertTrue(onBeforeCollapseLog, "on method used: ojbeforecollapse event called with correct parameters");
		Assert.assertTrue(optionBeforeCollapseLog,
				"option used: ojbeforecollapse event called with correct parameters");
		Assert.assertTrue(onCollapseLog,
				"on method used: ojcollapse event called for selection with correct parameters");
		Assert.assertTrue(optionCollapseLog,
				"option used: ojcollapse event called for selection with correct parameters");

	}

	/*
	 * //Will uncomment it when bug 21227477 is fixed
	 * 
	 * @Test(groups = { "navigationlist" }) public void
	 * testEventsOnRootParentExapandByMethodVetoableFalse() throws Exception {
	 * startupTest(TEST_PAGE, null); verifyTitle("Incorrect page title;",
	 * TEST_PAGE_TITLE);
	 * 
	 * //wait for the navigationlist to be displayed
	 * waitForElementVisible(NAVLIST_ID); //Change drill mode to collapsible
	 * changeDrillModeToCollapsible(); //Clear Event Log window WebElement
	 * clearEventLogBtn = getElement("id=clearEventLogBtn");
	 * clearEventLogBtn.click();
	 * 
	 * //Click on playback to expand WebElement expandBtn =
	 * getElement("id=evf"); expandBtn.click();
	 * 
	 * waitForMilliseconds(1000); //Get the log Window data String eventLog =
	 * evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
	 * //Get event log // WebElement eventLog = getElement("id=eventlog");
	 * System.out.println("####### eventLog: " + eventLog);
	 * 
	 * boolean onBeforeCurrentItemLog = eventLog.indexOf(
	 * "ON : ojbeforecurrentitem: navList data { key: prev; item: [object Object];}"
	 * ) > -1; boolean optionBeforeCurrentItemLog = eventLog.indexOf(
	 * "OPTION : ojbeforecurrentitem: navList data { key: prev; item: [object Object];}"
	 * ) > -1; boolean onOptionChangeCurrentItemLog = eventLog.indexOf(
	 * "ON : ojoptionchange: navList data { option: currentItem; previousValue: null; value: prev; optionMetadata: [object Object];}"
	 * ) > -1; boolean optionOptionChangeCurrentItemLog = eventLog.indexOf(
	 * "OPTION : ojoptionchange: navList data { option: currentItem; previousValue: null; value: prev; optionMetadata: [object Object];}"
	 * ) > -1;
	 * 
	 * boolean onBeforeExpandLog = eventLog.indexOf(
	 * "ON : ojbeforeexpand: navList data { item: [object Object]; key: playback;}"
	 * ) > -1; boolean optionBeforeExpandLog = eventLog.indexOf(
	 * "OPTION : ojbeforeexpand: navList data { item: [object Object]; key: playback;}"
	 * ) > -1;
	 * 
	 * boolean onExpandLog = eventLog.indexOf(
	 * "ON : ojexpand: navList data { item: [object Object]; key: playback;}") >
	 * -1; boolean optionExpandLog = eventLog.indexOf(
	 * "OPTION : ojexpand: navList data { item: [object Object]; key: playback;}"
	 * ) > -1;
	 * 
	 * boolean onBeforeCurrentItemLog1 = eventLog.indexOf(
	 * "ON : ojbeforecurrentitem: navList data { key: prev; item: [object Object]; previousKey: playback; previousItem: [object Object];}"
	 * ) > -1; boolean optionBeforeCurrentItemLog1 = eventLog.indexOf(
	 * "OPTION : ojbeforecurrentitem: navList data { key: prev; item: [object Object]; previousKey: playback; previousItem: [object Object];}"
	 * ) > -1; boolean onOptionChangeCurrentItemLog1 = eventLog.indexOf(
	 * "ON : ojoptionchange: navList data { option: currentItem; previousValue: playback; value: prev; optionMetadata: [object Object];}"
	 * ) > -1; boolean optionOptionChangeCurrentItemLog1 = eventLog.indexOf(
	 * "OPTION : ojoptionchange: navList data { option: currentItem; previousValue: playback; value: prev; optionMetadata: [object Object];}"
	 * ) > -1;
	 * 
	 * 
	 * Assert.assertTrue(onBeforeCurrentItemLog,
	 * "on method Used: ojbeforecurrentitem event called with correct parameters"
	 * ); Assert.assertTrue(optionBeforeCurrentItemLog,
	 * "option Used: ojbeforecurrentitem event called with correct parameters");
	 * Assert.assertTrue(onOptionChangeCurrentItemLog,
	 * "on method used: ojoptionchange event for currentitem called with correct parameters"
	 * ); Assert.assertTrue(optionOptionChangeCurrentItemLog,
	 * "option used: ojoptionchange event for currentitem called with correct parameters"
	 * ); Assert.assertTrue(onBeforeExpandLog,
	 * "on method used: ojbeforeselect event called with correct parameters");
	 * Assert.assertTrue(optionBeforeExpandLog,
	 * "option used: ojbeforeselect event called with correct parameters");
	 * Assert.assertTrue(onExpandLog,
	 * "on method used: ojoptionchange event called for selection with correct parameters"
	 * ); Assert.assertTrue(optionExpandLog,
	 * "option used: ojoptionchange event called for selection with correct parameters"
	 * );
	 * 
	 * Assert.assertTrue(onBeforeCurrentItemLog1,
	 * "on method Used: ojbeforecurrentitem event called with correct parameters"
	 * ); Assert.assertTrue(optionBeforeCurrentItemLog1,
	 * "option Used: ojbeforecurrentitem event called with correct parameters");
	 * Assert.assertTrue(onOptionChangeCurrentItemLog1,
	 * "on method used: ojoptionchange event for currentitem called with correct parameters"
	 * ); Assert.assertTrue(optionOptionChangeCurrentItemLog1,
	 * "option used: ojoptionchange event for currentitem called with correct parameters"
	 * );
	 * 
	 * 
	 * }
	 */
	/*
	 * //will uncomment it after bug 21315947 is fixed
	 * 
	 * @Test(groups = { "navigationlist" }) public void
	 * testEventsOnRootParentExapandByMethodVetoableTrue() throws Exception {
	 * startupTest(TEST_PAGE, null); verifyTitle("Incorrect page title;",
	 * TEST_PAGE_TITLE);
	 * 
	 * //wait for the navigationlist to be displayed
	 * waitForElementVisible(NAVLIST_ID); //Change drill mode to collapsible
	 * changeDrillModeToCollapsible(); //Clear Event Log window WebElement
	 * clearEventLogBtn = getElement("id=clearEventLogBtn");
	 * clearEventLogBtn.click();
	 * 
	 * //Click on playback to expand WebElement expandBtn =
	 * getElement("id=evt"); expandBtn.click();
	 * 
	 * waitForMilliseconds(1000); //Get the log Window data String eventLog =
	 * evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
	 * //Get event log // WebElement eventLog = getElement("id=eventlog");
	 * System.out.println("####### eventLog: " + eventLog);
	 * 
	 * boolean onBeforeExpandLog = eventLog.indexOf(
	 * "ON : ojbeforeexpand: navList data { item: [object Object]; key: playback;}"
	 * ) > -1; boolean optionBeforeExpandLog = eventLog.indexOf(
	 * "OPTION : ojbeforeexpand: navList data { item: [object Object]; key: playback;}"
	 * ) > -1;
	 * 
	 * boolean onExpandLog = eventLog.indexOf(
	 * "ON : ojexpand: navList data { item: [object Object]; key: playback;}") >
	 * -1; boolean optionExpandLog = eventLog.indexOf(
	 * "OPTION : ojexpand: navList data { item: [object Object]; key: playback;}"
	 * ) > -1;
	 * 
	 * Assert.assertTrue(onBeforeExpandLog,
	 * "on method used: ojbeforeselect event called with correct parameters");
	 * Assert.assertTrue(optionBeforeExpandLog,
	 * "option used: ojbeforeselect event called with correct parameters");
	 * Assert.assertTrue(onExpandLog,
	 * "on method used: ojoptionchange event called for selection with correct parameters"
	 * ); Assert.assertTrue(optionExpandLog,
	 * "option used: ojoptionchange event called for selection with correct parameters"
	 * );
	 * 
	 * 
	 * 
	 * }
	 */
	@Test(groups = { "navigationlist" })
	public void testEventsOnCollapseByMethodVetoableFalse() throws Exception {
		startupTest(TEST_PAGE, null);
		verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

		// wait for the navigationlist to be displayed
		waitForElementVisible(NAVLIST_ID);
		// Change drill mode to collapsible
		changeDrillModeToCollapsible();

		// Click on playback to expand
		WebElement expandPlayback = getElement("id=evt");
		expandPlayback.click();
		waitForMilliseconds(1000);

		// Click on play to expand
		WebElement expandPlay = getElement("id=evt1");
		expandPlay.click();
		waitForMilliseconds(1000);

		// Clear Event Log window
		WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
		clearEventLogBtn.click();

		// Click of button that calls collapse method
		WebElement collapseBtn = getElement("id=cvf");
		collapseBtn.click();

		waitForMilliseconds(1000);

		// Get the log Window data
		String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
		// Get event log
		// WebElement eventLog = getElement("id=eventlog");
		System.out.println("####### eventLog: " + eventLog);

		boolean onBeforeCollapseLog = eventLog
				.indexOf("ON : ojbeforecollapse: navList data { item: [object Object]; key: play;}") > -1;
		boolean optionBeforeCollapseLog = eventLog
				.indexOf("OPTION : ojbeforecollapse: navList data { item: [object Object]; key: play;}") > -1;
		boolean onCollapseLog = eventLog
				.indexOf("ON : ojcollapse: navList data { item: [object Object]; key: play;}") > -1;
		boolean optionCollapseLog = eventLog
				.indexOf("OPTION : ojcollapse: navList data { item: [object Object]; key: play;}") > -1;
		/*
		 * boolean onBeforeCurrentItemLog = eventLog.indexOf(
		 * "ON : ojbeforecurrentitem: navList data { key: play; item: [object Object]; previousKey: track1; previousItem: [object Object];}"
		 * ) > -1; boolean optionBeforeCurrentItemLog = eventLog.indexOf(
		 * "OPTION : ojbeforecurrentitem: navList data { key: play; item: [object Object]; previousKey: track1; previousItem: [object Object];}"
		 * ) > -1; boolean onOptionChangeCurrentItemLog = eventLog.indexOf(
		 * "ON : ojoptionchange: navList data { option: currentItem; previousValue: track1; value: play; optionMetadata: [object Object];}"
		 * ) > -1; boolean optionOptionChangeCurrentItemLog = eventLog.indexOf(
		 * "OPTION : ojoptionchange: navList data { option: currentItem; previousValue: track1; value: play; optionMetadata: [object Object];}"
		 * ) > -1;
		 * 
		 * 
		 * Assert.assertTrue(onBeforeCurrentItemLog,
		 * "on method Used: ojbeforecurrentitem event called with correct parameters"
		 * ); Assert.assertTrue(optionBeforeCurrentItemLog,
		 * "option Used: ojbeforecurrentitem event called with correct parameters"
		 * ); Assert.assertTrue(onOptionChangeCurrentItemLog,
		 * "on method used: ojoptionchange event for currentitem called with correct parameters"
		 * ); Assert.assertTrue(optionOptionChangeCurrentItemLog,
		 * "option used: ojoptionchange event for currentitem called with correct parameters"
		 * );
		 */
		Assert.assertTrue(onBeforeCollapseLog, "on method used: ojbeforecollapse event called with correct parameters");
		Assert.assertTrue(optionBeforeCollapseLog,
				"option used: ojbeforecollapse event called with correct parameters");
		Assert.assertTrue(onCollapseLog,
				"on method used: ojcollapse event called for selection with correct parameters");
		Assert.assertTrue(optionCollapseLog,
				"option used: ojcollapse event called for selection with correct parameters");

	}

	@Test(groups = { "navigationlist" })
	public void testEventsOnCollapseByMethodVetoableTrue() throws Exception {
		startupTest(TEST_PAGE, null);
		verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

		// wait for the navigationlist to be displayed
		waitForElementVisible(NAVLIST_ID);
		// Change drill mode to collapsible
		changeDrillModeToCollapsible();

		// Click on playback to expand
		WebElement expandPlayback = getElement("id=evt");
		expandPlayback.click();
		waitForMilliseconds(1000);

		// Click on play to expand
		WebElement expandPlay = getElement("id=evt1");
		expandPlay.click();
		waitForMilliseconds(1000);

		// Clear Event Log window
		WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
		clearEventLogBtn.click();

		// Click of button that calls collapse method
		WebElement collapseBtn = getElement("id=cvt");
		collapseBtn.click();

		waitForMilliseconds(1000);

		// Get the log Window data
		String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
		// Get event log
		// WebElement eventLog = getElement("id=eventlog");
		System.out.println("####### eventLog: " + eventLog);

		boolean onBeforeCollapseLog = eventLog
				.indexOf("ON : ojbeforecollapse: navList data { item: [object Object]; key: play;}") > -1;
		boolean optionBeforeCollapseLog = eventLog
				.indexOf("OPTION : ojbeforecollapse: navList data { item: [object Object]; key: play;}") > -1;
		boolean onCollapseLog = eventLog
				.indexOf("ON : ojcollapse: navList data { item: [object Object]; key: play;}") > -1;
		boolean optionCollapseLog = eventLog
				.indexOf("OPTION : ojcollapse: navList data { item: [object Object]; key: play;}") > -1;

		Assert.assertTrue(onBeforeCollapseLog, "on method used: ojbeforecollapse event called with correct parameters");
		Assert.assertTrue(optionBeforeCollapseLog,
				"option used: ojbeforecollapse event called with correct parameters");
		Assert.assertTrue(onCollapseLog,
				"on method used: ojcollapse event called for selection with correct parameters");
		Assert.assertTrue(optionCollapseLog,
				"option used: ojcollapse event called for selection with correct parameters");

	}

	@Test(groups = { "navigationlist" })
	public void testEventsOnExpandByMethodVetoableTrue() throws Exception {
		startupTest(TEST_PAGE, null);
		verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

		// wait for the navigationlist to be displayed
		waitForElementVisible(NAVLIST_ID);
		// Change drill mode to collapsible
		changeDrillModeToCollapsible();

		// Clear Event Log window
		WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
		clearEventLogBtn.click();

		// Click on playback to expand
		WebElement expandPlayback = getElement("id=evt");
		expandPlayback.click();
		waitForMilliseconds(1000);

		// Get the log Window data
		String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
		// Get event log
		// WebElement eventLog = getElement("id=eventlog");
		System.out.println("####### eventLog: " + eventLog);

		boolean onBeforeExpandLog = eventLog
				.indexOf("ON : ojbeforeexpand: navList data { item: [object Object]; key: playback;}") > -1;
		boolean optionBeforeExpandLog = eventLog
				.indexOf("OPTION : ojbeforeexpand: navList data { item: [object Object]; key: playback;}") > -1;
		boolean onExpandLog = eventLog
				.indexOf("ON : ojexpand: navList data { item: [object Object]; key: playback;}") > -1;
		boolean optionExpandLog = eventLog
				.indexOf("OPTION : ojexpand: navList data { item: [object Object]; key: playback;}") > -1;

		Assert.assertTrue(onBeforeExpandLog, "on method used: ojbeforeexpand event called with correct parameters");
		Assert.assertTrue(optionBeforeExpandLog, "option used: ojbeforeexpand event called with correct parameters");

		Assert.assertTrue(onExpandLog, "on method used: ojexpand event called for selection with correct parameters");
		Assert.assertTrue(optionExpandLog, "option used: ojexpand event called for selection with correct parameters");

	}

}
