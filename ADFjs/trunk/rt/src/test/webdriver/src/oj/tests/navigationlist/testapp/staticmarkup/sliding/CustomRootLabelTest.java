package oj.tests.navigationlist.testapp.staticmarkup.sliding;


import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;

public class CustomRootLabelTest extends NavigationlistTestBase {
    private static final String TEST_PAGE = "htmlMarkup_static.html";
    private static final String TEST_PAGE_TITLE = "Navigation List: Static Markup Test";
   // private static final String NAVLIST_ROOT_HEADING = "Navigation List";
    private static final String NAVLIST_CUSTOM_HEADING = "My Custom Root Label";
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
    private static final String[] NAVLIST_CHILD1_HEIRARCHICAL_MENU = { "My Custom Root Label" };
    private static final String[] NAVLIST_CHILD2_HEIRARCHICAL_MENU = { "My Custom Root Label", "Playback" };
    private static final String[] NAVLIST_CHILD3_HEIRARCHICAL_MENU = { "My Custom Root Label", "Playback", "Play" };
    private static final String[] NAVLIST_CHILD4_HEIRARCHICAL_MENU = {
        "My Custom Root Label", "Playback", "Play", "Track 8" };
    private static final String[] NAVLIST_CHILD5_HEIRARCHICAL_MENU = {
        "My Custom Root Label", "Playback", "Play", "Track 8", "Sub Track 8" };
    private static final String[] NAVLIST_CHILD6_HEIRARCHICAL_MENU = {
        "My Custom Root Label", "Playback", "Play", "Track 8", "Sub Track 8", "sub sub Track 4"
    };
    private static final String NAVLIST_DISABLED_ITEM = "print";


    public CustomRootLabelTest() {
        super("ojnavigationlist", "navigationlist/navigationlistTest");
    }

    @Test(groups = { "navigationlist" })
    public void testCustonRootLabel() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Select an Item       
        selectListItem( NAVLIST_ID, NAVLIST_ITEM_ZOOMIN);
        String selExpectedVal = "Option Value: zoomin Observable Value: zoomin";
        verifySelectedValue(selExpectedVal);
        //Change Root Label
        changeRootLabel(NAVLIST_CUSTOM_HEADING);
  Thread.sleep(5000); 
        
        //Verify heading
        verifyHeading(NAVLIST_ID, NAVLIST_CUSTOM_HEADING);
        
        //Verify selected Value again
        verifySelectedValue(selExpectedVal);
        
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD1);
        verifyHierarchicalMenu(NAVLIST_ID, NAVLIST_CHILD1_HEIRARCHICAL_MENU);
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD2);
        verifyHierarchicalMenu(NAVLIST_ID, NAVLIST_CHILD2_HEIRARCHICAL_MENU);
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD3);
        verifyHierarchicalMenu(NAVLIST_ID, NAVLIST_CHILD3_HEIRARCHICAL_MENU);
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD4);
        verifyHierarchicalMenu(NAVLIST_ID, NAVLIST_CHILD4_HEIRARCHICAL_MENU);
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD5);
        verifyHierarchicalMenu(NAVLIST_ID, NAVLIST_CHILD5_HEIRARCHICAL_MENU);
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD6);
        verifyHierarchicalMenu(NAVLIST_ID, NAVLIST_CHILD6_HEIRARCHICAL_MENU);
       
        testUIAfterNavigatingBackUsingPreviousLink();
    }


    void testUIAfterNavigatingBackUsingPreviousLink() {
        String selExpectedVal = "Option Value: zoomin Observable Value: zoomin";
        //Navigate back by clicking on the Previous link
        //Navigate back to Sub Sub Track4
        navigateBackByClickingOnPreviousLink(NAVLIST_ID);
        verifyHeading(NAVLIST_ID, NAVLIST_CHILD5_HEADING);
        verifyListItems(NAVLIST_ID, NAVLIST_CHILD5_ITEMS, null);
        verifySelectedValue(selExpectedVal);

        //Go back to  sub Track 8
        navigateBackByClickingOnPreviousLink(NAVLIST_ID);
        verifyHeading(NAVLIST_ID, NAVLIST_CHILD4_HEADING);
        verifyListItems(NAVLIST_ID, NAVLIST_CHILD4_ITEMS, null);
        verifySelectedValue(selExpectedVal);

        //Go back to  Track 8
        navigateBackByClickingOnPreviousLink(NAVLIST_ID);
        verifyHeading(NAVLIST_ID, NAVLIST_CHILD3_HEADING);
        verifyListItems(NAVLIST_ID, NAVLIST_CHILD3_ITEMS, null);
        verifySelectedValue(selExpectedVal);

        //Go back to Play
        navigateBackByClickingOnPreviousLink(NAVLIST_ID);
        verifyHeading(NAVLIST_ID, NAVLIST_CHILD2_HEADING);
        verifyListItems(NAVLIST_ID, NAVLIST_CHILD2_ITEMS, null);
        verifySelectedValue(selExpectedVal);

        //Go back to Playback
        navigateBackByClickingOnPreviousLink(NAVLIST_ID);
        verifyHeading(NAVLIST_ID, NAVLIST_CHILD1_HEADING);
        verifyListItems(NAVLIST_ID, NAVLIST_CHILD1_ITEMS, null);
        verifySelectedValue(selExpectedVal);

        //Go back to Navigation List
        navigateBackByClickingOnPreviousLink(NAVLIST_ID);
        verifyHeading(NAVLIST_ID, NAVLIST_CUSTOM_HEADING);
        verifyListItems(NAVLIST_ID, NAVLIST_ITEMS, "zoomin");
        verifySelectedValue(selExpectedVal);

        
    }

    @Test(groups = { "navigationlist" }, dependsOnMethods = {"testCustonRootLabel"})
    public void testUIAfterNavigatingBackUsingHierarchicalMenu() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);

        changeRootLabel(NAVLIST_CUSTOM_HEADING);
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD1);
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD2);
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD3);
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD4);
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD5);
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD6);
        //Click on leaf node subsubsubsubtrack5 item
        selectListItem( NAVLIST_ID, NAVLIST_ITEM_SUBSUBSUBSUBTRACK5);
        String selExpectedVal = "Option Value: subsubsubsubtrack5 Observable Value: subsubsubsubtrack5";
       
        //Navigate back to Root
        navigateBackByClickingOnHierarchicalMenuItem(NAVLIST_ID, NAVLIST_CUSTOM_HEADING );
        verifyHeading(NAVLIST_ID, NAVLIST_CUSTOM_HEADING);
        verifyListItems(NAVLIST_ID, NAVLIST_ITEMS, null);
        verifySelectedValue(selExpectedVal);


     
    }


}
