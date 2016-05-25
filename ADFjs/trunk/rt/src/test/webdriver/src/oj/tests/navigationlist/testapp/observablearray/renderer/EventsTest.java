package oj.tests.navigationlist.testapp.observablearray.renderer;

import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;

public class EventsTest extends NavigationlistTestBase {
    private static final String TEST_PAGE = "tableDataSource_OA_renderer.html";
    private static final String TEST_PAGE_TITLE = "Navigation List: TableDataSource";
    private static final String NAVLIST_ITEM_SECURITY = "security";
    private static final String NAVLIST_ITEM_BASE = "base";
    private static final String NAVLIST_ITEM_SETTINGS = "settings";
   
    private static final String NAVLIST_ID = "navList";
    private static final String NAVLIST_DISABLED_ITEM = "environment";
    
   

    public EventsTest() {
        super("ojnavigationlist", "navigationlist/navigationlistTest");
    }
    
    @Test(groups = { "navigationlist" })
    public void testEventsOnEmptyListInitialization() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        
        //Get the log Window data
        String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
        //Get event log
        // WebElement eventLog = getElement("id=eventlog");
        System.out.println("####### eventLog: " + eventLog);

        boolean onCreateLog =
            eventLog.indexOf("ON : ojcreate: navList data {}") > -1;
        boolean optionCreateLog =
            eventLog.indexOf("OPTION : ojcreate: navList data {}") > -1;
        
        Assert.assertTrue(onCreateLog,
                          "on method Used: ojcreate event called");
        Assert.assertTrue(optionCreateLog,
                          "option Used: ojcreate event called");
        
    }
    
    @Test(groups = { "navigationlist" })
    public void testEventsOnItemSelectionOnInitialization() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
       
        //Clear Event Log window
        WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
        clearEventLogBtn.click();
        
        //Click on button to Populate the
        //list
        WebElement initBtn = getElement("id=init");
        initBtn.click();
        this.waitForMilliseconds(1000);
        //Get the log Window data
        String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
        //Get event log
        // WebElement eventLog = getElement("id=eventlog");
        System.out.println("####### eventLog: " + eventLog);

     
        boolean onBeforeSelectLog =
            eventLog.indexOf("ON : ojbeforeselect: navList data { item: [object Object]; key: security;}") > -1;
        boolean optionBeforeSelectLog =
            eventLog.indexOf("OPTION : ojbeforeselect: navList data { item: [object Object]; key: security;}") > -1;
        boolean onOptionChangeSelectLog =
            eventLog.indexOf("ON : ojoptionchange: navList data { option: selection; previousValue: null; value: security; optionMetadata: [object Object];}") >
            -1;
        boolean optionOptionChangeSelectLog =
            eventLog.indexOf("OPTION : ojoptionchange: navList data { option: selection; previousValue: null; value: security; optionMetadata: [object Object];}") >
            -1;

        Assert.assertTrue(onBeforeSelectLog, "on method used: ojbeforeselect event called with correct parameters");
        Assert.assertTrue(optionBeforeSelectLog, "option used: ojbeforeselect event called with correct parameters");
        Assert.assertTrue(onOptionChangeSelectLog,
                          "on method used: ojoptionchange event called for selection with correct parameters");
        Assert.assertTrue(optionOptionChangeSelectLog,
                          "option used: ojoptionchange event called for selection with correct parameters");

        //verify that item is selected
        verifyItemIsSelected(NAVLIST_ID, NAVLIST_ITEM_SECURITY);
    }

    @Test(groups = { "navigationlist" })
    public void testEventsOnItemSelectionByUserAction() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Click on button to Populate the
        //list
        WebElement initBtn = getElement("id=init");
        initBtn.click();
        this.waitForMilliseconds(1000);
        //Clear Event Log window
        WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
        clearEventLogBtn.click();

        //Click on leaf node base item
        selectListItem(NAVLIST_ID, NAVLIST_ITEM_BASE);
        this.waitForMilliseconds(10000);
        //Get the log Window data
        String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
        //Get event log
        // WebElement eventLog = getElement("id=eventlog");
        System.out.println("####### eventLog: " + eventLog);

        boolean onBeforeCurrentItemLog =
            eventLog.indexOf("ON : ojbeforecurrentitem: navList data { key: base; item: [object Object];}") > -1;
        boolean optionBeforeCurrentItemLog =
            eventLog.indexOf("OPTION : ojbeforecurrentitem: navList data { key: base; item: [object Object];}") > -1;
        boolean onOptionChangeCurrentItemLog =
            eventLog.indexOf("ON : ojoptionchange: navList data { item: [object Object]; option: currentItem; previousValue: null; value: base; optionMetadata: [object Object];") >
            -1;
        boolean optionOptionChangeCurrentItemLog =
            eventLog.indexOf("OPTION : ojoptionchange: navList data { item: [object Object]; option: currentItem; previousValue: null; value: base; optionMetadata: [object Object];") >
            -1;

        boolean onBeforeSelectLog =
            eventLog.indexOf("ON : ojbeforeselect: navList data { item: [object Object]; key: base;}") > -1;
        boolean optionBeforeSelectLog =
            eventLog.indexOf("OPTION : ojbeforeselect: navList data { item: [object Object]; key: base;}") > -1;
        boolean onOptionChangeSelectLog =
            eventLog.indexOf("ON : ojoptionchange: navList data { item: [object Object]; option: selection; previousValue: security; value: base; optionMetadata: [object Object];}") >
            -1;
        boolean optionOptionChangeSelectLog =
            eventLog.indexOf("OPTION : ojoptionchange: navList data { item: [object Object]; option: selection; previousValue: security; value: base; optionMetadata: [object Object];}") >
            -1;


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

        //verify that item is selected
        verifyItemIsSelected(NAVLIST_ID, NAVLIST_ITEM_BASE);
    }


    @Test(groups = { "navigationlist" })
    public void testEventsOnItemSelectionByOptionMethod() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Click on button to Populate the
        //list
        WebElement initBtn = getElement("id=init");
        initBtn.click();
        //Clear Event Log window
        WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
        clearEventLogBtn.click();

        //Set selection option using option method
        WebElement setBtn = getElement("id=setsel");
        setBtn.click();

        //Get the log Window data
        String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
        //Get event log
        // WebElement eventLog = getElement("id=eventlog");
        System.out.println("####### eventLog: " + eventLog);

      
        boolean onBeforeSelectLog =
            eventLog.indexOf("ON : ojbeforeselect: navList data { item: [object Object]; key: base;}") > -1;
        boolean optionBeforeSelectLog =
            eventLog.indexOf("OPTION : ojbeforeselect: navList data { item: [object Object]; key: base;}") > -1;
        boolean onOptionChangeSelectLog =
            eventLog.indexOf("ON : ojoptionchange: navList data { option: selection; previousValue: security; value: base; optionMetadata: [object Object];}") >
            -1;
        boolean optionOptionChangeSelectLog =
            eventLog.indexOf("ON : ojoptionchange: navList data { option: selection; previousValue: security; value: base; optionMetadata: [object Object];}") >
            -1;


        Assert.assertTrue(onBeforeSelectLog, "on method used: ojbeforeselect event called with correct parameters");
        Assert.assertTrue(optionBeforeSelectLog, "option used: ojbeforeselect event called with correct parameters");
        Assert.assertTrue(onOptionChangeSelectLog,
                          "on method used: ojoptionchange event called for selection with correct parameters");
        Assert.assertTrue(optionOptionChangeSelectLog,
                          "option used: ojoptionchange event called for selection with correct parameters");

        //verify that item is selected
        verifyItemIsSelected(NAVLIST_ID, NAVLIST_ITEM_BASE);
    }
   
    @Test(groups = { "navigationlist" })
    public void testEventsOnItemSelectionByObservable() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Click on button to Populate the
        //list
        WebElement initBtn = getElement("id=init");
        initBtn.click();
        
        //Clear Event Log window
        WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
        clearEventLogBtn.click();

        //Set selection option using observable
        WebElement setBtn = getElement("id=setselob");
        setBtn.click();

        //Get the log Window data
        String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
        //Get event log
        // WebElement eventLog = getElement("id=eventlog");
        System.out.println("####### eventLog: " + eventLog);

      
        boolean onBeforeSelectLog = eventLog.indexOf("ON : ojoptionchange: navList data { option: selection; previousValue: base; value: settings; optionMetadata: [object Object];}") > -1;
        boolean optionBeforeSelectLog = eventLog.indexOf("OPTION : ojoptionchange: navList data { option: selection; previousValue: base; value: settings; optionMetadata: [object Object];}") > -1;
        boolean onOptionChangeSelectLog = eventLog.indexOf("ON : ojoptionchange: navList data { option: selection; previousValue: security; value: base; optionMetadata: [object Object];}") > -1;
        boolean optionOptionChangeSelectLog = eventLog.indexOf("OPTION : ojoptionchange: navList data { option: selection; previousValue: security; value: base; optionMetadata: [object Object];}") > -1;


        Assert.assertFalse(onBeforeSelectLog, "on method used: ojbeforeselect event called with correct parameters");
        Assert.assertFalse(optionBeforeSelectLog, "option used: ojbeforeselect event called with correct parameters");
        Assert.assertTrue(onOptionChangeSelectLog, "on method used: ojoptionchange event called for selection with correct parameters");
        Assert.assertTrue(optionOptionChangeSelectLog, "option used: ojoptionchange event called for selection with correct parameters");

        //verify that item is selected
        verifyItemIsSelected(NAVLIST_ID, NAVLIST_ITEM_BASE);
    }
 

    @Test(groups = { "navigationlist" })
    public void testEventsOnDisabledItemSelectionByUserClick() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Click on button to Populate the
        //list
        WebElement initBtn = getElement("id=init");
        initBtn.click();
        this.waitForMilliseconds(1000);
        //Clear Event Log window
        WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
        clearEventLogBtn.click();

        //click on disabled item
        selectListItem(NAVLIST_ID, NAVLIST_DISABLED_ITEM);
        
        //Get the log Window data
        String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
        //Get event log
        // WebElement eventLog = getElement("id=eventlog");
        System.out.println("####### eventLog: " + eventLog);


        Assert.assertEquals(eventLog, "", "No events raised");
        verifyItemIsNotSelected(NAVLIST_ID, NAVLIST_DISABLED_ITEM);
    }
    /*
     //Will enable the test after bug 21226222  is fixed

    @Test(groups = { "navigationlist" })
    public void testEventsOnDisabledItemSelectionByOptionMethod() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
    changeDrillModeToNone();
        //Clear Event Log window
        WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
        clearEventLogBtn.click();

        //set selection option using option method
        WebElement setBtn = getElement("id=setsel1");
        setBtn.click();

        //Get the log Window data
        String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
        //Get event log
        // WebElement eventLog = getElement("id=eventlog");
        System.out.println("####### eventLog: " + eventLog);

        Assert.assertEquals(eventLog, "", "No events raised");
        verifyItemIsNotSelected(NAVLIST_ID, NAVLIST_DISABLED_ITEM);
    }
*/
    @Test(groups = { "navigationlist" })
    public void testEventsOnChangeInItemSelectionByUserAction() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Click on button to Populate the
        //list
        WebElement initBtn = getElement("id=init");
        initBtn.click();
        this.waitForMilliseconds(1000); 
        //Click on leaf node base item
        selectListItem(NAVLIST_ID, NAVLIST_ITEM_BASE);
        this.waitForMilliseconds(1000); 
        //Clear Event Log window
        WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
        clearEventLogBtn.click();
        
        //Click on  zoomout item to change selection
        selectListItem(NAVLIST_ID, NAVLIST_ITEM_SETTINGS);
        this.waitForMilliseconds(1000); 

        //Get the log Window data
        String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
        //Get event log
        // WebElement eventLog = getElement("id=eventlog");
        System.out.println("####### eventLog: " + eventLog);

        boolean onBeforeCurrentItemLog =
            eventLog.indexOf("ON : ojbeforecurrentitem: navList data { key: settings; item: [object Object]; previousKey: base; previousItem: [object Object];}") >
            -1;
        boolean optionBeforeCurrentItemLog =
            eventLog.indexOf("OPTION : ojbeforecurrentitem: navList data { key: settings; item: [object Object]; previousKey: base; previousItem: [object Object];}") >
            -1;
        boolean onOptionChangeCurrentItemLog =
            eventLog.indexOf("ON : ojoptionchange: navList data { item: [object Object]; option: currentItem; previousValue: base; value: settings; optionMetadata: [object Object];}") >
            -1;
        boolean optionOptionChangeCurrentItemLog =
            eventLog.indexOf("OPTION : ojoptionchange: navList data { item: [object Object]; option: currentItem; previousValue: base; value: settings; optionMetadata: [object Object];}") >
            -1;

        boolean onBeforeSelectLog =
            eventLog.indexOf("ON : ojbeforeselect: navList data { item: [object Object]; key: settings;}") > -1;
        boolean optionBeforeSelectLog =
            eventLog.indexOf("OPTION : ojbeforeselect: navList data { item: [object Object]; key: settings;}") > -1;
        boolean onOptionChangeSelectLog =
            eventLog.indexOf("ON : ojoptionchange: navList data { item: [object Object]; option: selection; previousValue: base; value: settings; optionMetadata: [object Object];}") >
            -1;
        boolean optionOptionChangeSelectLog =
            eventLog.indexOf("ON : ojoptionchange: navList data { item: [object Object]; option: selection; previousValue: base; value: settings; optionMetadata: [object Object];}") >
            -1;


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

        //verify that item is selected
        verifyItemIsSelected(NAVLIST_ID, NAVLIST_ITEM_SETTINGS);
        verifyItemIsNotSelected(NAVLIST_ID, NAVLIST_ITEM_BASE);
    }


    @Test(groups = { "navigationlist" })
    public void testEventsOnChangeInItemSelectionByOptionMethod() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Click on button to Populate the
        //list
        WebElement initBtn = getElement("id=init");
        initBtn.click();
       
        //Set selection option using option method - to base
        WebElement setBtn = getElement("id=setsel");
        setBtn.click();

        //Clear Event Log window
        WebElement clearEventLogBtn = getElement("id=clearEventLogBtn");
        clearEventLogBtn.click();

        //Change selection option using option method - to settings
        setBtn = getElement("id=setsel1");
        setBtn.click();

        //Get the log Window data
        String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
        //Get event log
        // WebElement eventLog = getElement("id=eventlog");
        System.out.println("####### eventLog: " + eventLog);

       
        boolean onBeforeSelectLog =
            eventLog.indexOf("ON : ojbeforeselect: navList data { item: [object Object]; key: settings;}") > -1;
        boolean optionBeforeSelectLog =
            eventLog.indexOf("OPTION : ojbeforeselect: navList data { item: [object Object]; key: settings;}") > -1;
        boolean onOptionChangeSelectLog =
            eventLog.indexOf("ON : ojoptionchange: navList data { option: selection; previousValue: base; value: settings; optionMetadata: [object Object];}") >
            -1;
        boolean optionOptionChangeSelectLog =
            eventLog.indexOf("OPTION : ojoptionchange: navList data { option: selection; previousValue: base; value: settings; optionMetadata: [object Object];}") >
            -1;


        Assert.assertTrue(onBeforeSelectLog, "on method used: ojbeforeselect event called with correct parameters");
        Assert.assertTrue(optionBeforeSelectLog, "option used: ojbeforeselect event called with correct parameters");
        Assert.assertTrue(onOptionChangeSelectLog,
                          "on method used: ojoptionchange event called for selection with correct parameters");
        Assert.assertTrue(optionOptionChangeSelectLog,
                          "option used: ojoptionchange event called for selection with correct parameters");

        //verify that item is selected
        verifyItemIsNotSelected(NAVLIST_ID, NAVLIST_ITEM_BASE);
        verifyItemIsSelected(NAVLIST_ID, NAVLIST_ITEM_SETTINGS);
    }

  

    
   
}

