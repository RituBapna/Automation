package oj.tests.navigationlist.testapp.staticmarkup.sliding;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;

public class HierarchicalMenuThresholdValueTest extends NavigationlistTestBase {
    private static final String TEST_PAGE = "htmlMarkup_static.html";
    private static final String TEST_PAGE_TITLE = "Navigation List: Static Markup Test";
    private static final String NAVLIST_ROOT_HEADING = "Navigation List";
    private static final String NAVLIST_CHILD1_HEADING = "Playback";
    private static final String NAVLIST_CHILD2_HEADING = "Play";
    private static final String NAVLIST_CHILD3_HEADING = "Track 8";
    private static final String NAVLIST_CHILD4_HEADING = "Sub Track 8";
    private static final String NAVLIST_CHILD5_HEADING = "sub sub Track 4";
    private static final String NAVLIST_CHILD6_HEADING = "sub sub sub Track 4";
    private static final String NAVLIST_ID = "navList";
    private static final String NAVLIST_ITEM_TO_GO_TO_CHILD1 = "playback";
    private static final String NAVLIST_ITEM_TO_GO_TO_CHILD2 = "play";
    private static final String NAVLIST_ITEM_TO_GO_TO_CHILD3 = "track8";
    private static final String NAVLIST_ITEM_TO_GO_TO_CHILD4 = "subtrack8";
    private static final String NAVLIST_ITEM_TO_GO_TO_CHILD5 = "subsubtrack4";
    private static final String NAVLIST_ITEM_TO_GO_TO_CHILD6 = "subsubsubtrack4";
    private static final String NAVLIST_ITEM_TO_FOCUS = "subsubsubsubtrack4";
    private static final String NAVLIST_ITEM_SUBSUBSUBSUBTRACK5 = "subsubsubsubtrack5";
    private static final String NAVLIST_ITEM_ZOOMIN = "zoomin";
    private static final String[] NAVLIST_ITEMS = { "save", "zoomin", "zoomout", "print", "playback" };
    private static final String[] NAVLIST_CHILD1_ITEMS = { "prev", "stop", "play", "nextItem" };
    private static final String[] NAVLIST_CHILD2_ITEMS = {
        "track1", "track2", "track3", "track4", "track5", "track6", "track7", "track8"
    };
    private static final String[] NAVLIST_CHILD3_ITEMS = {
        "subtrack1", "subtrack2", "subtrack3", "subtrack4", "subtrack5", "subtrack6", "subtrack7", "subtrack8"
    };
    private static final String[] NAVLIST_CHILD4_ITEMS = {
        "subsubtrack1", "subsubtrack2", "subsubtrack3", "subsubtrack4" };
    private static final String[] NAVLIST_CHILD5_ITEMS = {
        "subsubsubtrack1", "subsubsubtrack2", "subsubsubtrack3", "subsubsubtrack4" };
    private static final String[] NAVLIST_CHILD6_ITEMS = {
        "subsubsubsubtrack1", "subsubsubsubtrack2", "subsubsubsubtrack3", "subsubsubsubtrack4", "subsubsubsubtrack5",
        "subsubsubsubtrack6", "subsubsubsubtrack7", "subsubsubsubtrack8", "subsubsubsubtrack9", "subsubsubsubtrack10",
        "subsubsubsubtrack11", "subsubsubsubtrack12", "subsubsubsubtrack13", "subsubsubsubtrack14",
        "subsubsubsubtrack15", "subsubsubsubtrack16", "subsubsubsubtrack17", "subsubsubsubtrack18",
        "subsubsubsubtrack19", "subsubsubsubtrack20", "subsubsubsubtrack21", "subsubsubsubtrack22",
        "subsubsubsubtrack23", "subsubsubsubtrack24", "subsubsubsubtrack25", "subsubsubsubtrack26",
        "subsubsubsubtrack27", "subsubsubsubtrack28"
    };
    private static final String[] NAVLIST_CHILD1_HEIRARCHICAL_MENU = { "Navigation List" };
    private static final String[] NAVLIST_CHILD2_HEIRARCHICAL_MENU = { "Navigation List", "Playback" };
    private static final String[] NAVLIST_CHILD3_HEIRARCHICAL_MENU = { "Navigation List", "Playback", "Play" };
    private static final String[] NAVLIST_CHILD4_HEIRARCHICAL_MENU = {
        "Navigation List", "Playback", "Play", "Track 8" };
    private static final String[] NAVLIST_CHILD5_HEIRARCHICAL_MENU = {
        "Navigation List", "Playback", "Play", "Track 8", "Sub Track 8" };
    private static final String[] NAVLIST_CHILD6_HEIRARCHICAL_MENU = {
        "Navigation List", "Playback", "Play", "Track 8", "Sub Track 8", "sub sub Track 4"
    };
    private static final String NAVLIST_DISABLED_ITEM = "print";
    
    private static final String THRESHOLD_INPUT_ID = "menuThreshold";

    public HierarchicalMenuThresholdValueTest() {
        super("ojnavigationlist", "navigationlist/navigationlistTest");
    }

    	//No menu when threshold value is -1
    @Test(groups = { "navigationlist" })
    public void testNoHeirarchicalMenuDisplayed() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        
        // Set threshold value to 2
        
        WebElement downarrow =
                getElement("{\"element\":\"#" + THRESHOLD_INPUT_ID + "\",\"subId\":\"oj-inputnumber-down\"}"); 
        downarrow.click();
        
        //Click on item to navigate 
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD1);
        //check that hierarchical menu not displayed
        WebElement hierarchicalMenuBtn =
                getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-hierarchical-button\"}");           
        String styleVal = hierarchicalMenuBtn.getAttribute("style");
        Assert.assertEquals(styleVal, "visibility: hidden;" );
        
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD2);
        //check that hierarchical menu not displayed
        hierarchicalMenuBtn =
                getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-hierarchical-button\"}");            
        styleVal = hierarchicalMenuBtn.getAttribute("style");
        Assert.assertEquals(styleVal, "visibility: hidden;" );
        
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD3);
        hierarchicalMenuBtn =
                getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-hierarchical-button\"}");           
        styleVal = hierarchicalMenuBtn.getAttribute("style");
        Assert.assertEquals(styleVal, "visibility: hidden;" );
        
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD4);
        hierarchicalMenuBtn =
                getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-hierarchical-button\"}");            
        styleVal = hierarchicalMenuBtn.getAttribute("style");
        Assert.assertEquals(styleVal, "visibility: hidden;" );
        
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD5);
        hierarchicalMenuBtn =
                getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-hierarchical-button\"}");            
        styleVal = hierarchicalMenuBtn.getAttribute("style");
        Assert.assertEquals(styleVal, "visibility: hidden;" );
        
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD6);
        hierarchicalMenuBtn =
                getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-hierarchical-button\"}");            
        styleVal = hierarchicalMenuBtn.getAttribute("style");
        Assert.assertEquals(styleVal, "visibility: hidden;" );
        
      
        
    }

	//hierarchical menu displayed at 2nd level when threshold value is 2
@Test(groups = { "navigationlist" })
public void testHeirarchicalMenuDisplayedAtThresholdLevel() throws Exception {
    startupTest(TEST_PAGE, null);
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

    //wait for the navigationlist to be displayed
    waitForElementVisible(NAVLIST_ID);

    // Set threshold value to 2
    WebElement uparrow =
            getElement("{\"element\":\"#" + THRESHOLD_INPUT_ID + "\",\"subId\":\"oj-inputnumber-up\"}"); 
    uparrow.click();
    uparrow.click();
    
    //Click on item to navigate 
    navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD1);
    //check that hierarchical menu not displayed
    WebElement hierarchicalMenuBtn =
            getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-hierarchical-button\"}");           
   String styleVal = hierarchicalMenuBtn.getAttribute("style");
   Assert.assertEquals(styleVal, "visibility: hidden;" );
    
    navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD2);
   
    //check that hierarchical menu not displayed
    hierarchicalMenuBtn =
            getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-hierarchical-button\"}");            
    styleVal = hierarchicalMenuBtn.getAttribute("style");
    Assert.assertEquals(styleVal, "visibility: visible;" );
    
    
    verifyHierarchicalMenu(NAVLIST_ID, NAVLIST_CHILD2_HEIRARCHICAL_MENU);
    navigateBackByClickingOnPreviousLink(NAVLIST_ID);
    
    hierarchicalMenuBtn =
            getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-hierarchical-button\"}");            
    styleVal = hierarchicalMenuBtn.getAttribute("style");
    Assert.assertEquals(styleVal, "visibility: hidden;" );
}


}
