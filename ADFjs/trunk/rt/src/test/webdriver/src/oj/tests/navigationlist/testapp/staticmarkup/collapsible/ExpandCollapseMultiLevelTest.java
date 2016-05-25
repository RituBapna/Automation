package oj.tests.navigationlist.testapp.staticmarkup.collapsible;

import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;

public class ExpandCollapseMultiLevelTest extends NavigationlistTestBase {
    private static final String TEST_PAGE = "htmlMarkup_static.html";
    private static final String TEST_PAGE_TITLE = "Navigation List: Static Markup Test";
  
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
   
    private static final String NAVLIST_DISABLED_ITEM = "print";


    public ExpandCollapseMultiLevelTest() {
        super("ojnavigationlist", "navigationlist/navigationlistTest");
    }

    @Test(groups = { "navigationlist" })
    public void testUIAfterNavigationByClick() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);

        //Change drill mode to collapsible
        changeDrillModeToCollapsible();

        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD1);
        verifyItemsAreNotSelected(NAVLIST_ID, NAVLIST_CHILD1_ITEMS);
        verifyGetExpandedMethodReturnValue("playback,");

        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD2);
        verifyItemsAreNotSelected(NAVLIST_ID, NAVLIST_CHILD2_ITEMS);
        verifyGetExpandedMethodReturnValue("playback,play,");

        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD3);
        verifyItemsAreNotSelected(NAVLIST_ID, NAVLIST_CHILD3_ITEMS);
        verifyGetExpandedMethodReturnValue("playback,play,track8,");

        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD4);
        verifyItemsAreNotSelected(NAVLIST_ID, NAVLIST_CHILD4_ITEMS);
        verifyGetExpandedMethodReturnValue("playback,play,track8,subtrack8,");

        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD5);
        verifyItemsAreNotSelected(NAVLIST_ID, NAVLIST_CHILD5_ITEMS);
        verifyGetExpandedMethodReturnValue("playback,play,track8,subtrack8,subsubtrack4,");

        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD6);
        verifyItemsAreNotSelected(NAVLIST_ID, NAVLIST_CHILD6_ITEMS);
       
        verifyGetExpandedMethodReturnValue("playback,play,track8,subtrack8,subsubtrack4,subsubsubtrack4,");

        waitForMilliseconds(1000);
        //Click on leaf node subsubsubsubtrack5 item
        selectListItem(NAVLIST_ID, NAVLIST_ITEM_SUBSUBSUBSUBTRACK5);


        String selExpectedVal = "Option Value: subsubsubsubtrack5 Observable Value: subsubsubsubtrack5";

        verifySelectedValue(selExpectedVal);

        testUIAfterNavigatingBack();
    }


    void testUIAfterNavigatingBack() {
        String selExpectedVal = "Option Value: subsubsubsubtrack5 Observable Value: subsubsubsubtrack5";
        //Navigate back by clicking on the Previous link
        //Navigate back to Sub Sub Track4
        collapse(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD6);
        verifyListItems(NAVLIST_ID, NAVLIST_CHILD5_ITEMS, null);
        verifySelectedValue(selExpectedVal);
        verifyGetExpandedMethodReturnValue("playback,play,track8,subtrack8,subsubtrack4,");

        //Go back to  sub Track 8
        collapse(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD5);
        verifyListItems(NAVLIST_ID, NAVLIST_CHILD4_ITEMS, null);
        verifySelectedValue(selExpectedVal);
        verifyGetExpandedMethodReturnValue("playback,play,track8,subtrack8,");

        //Go back to  Track 8
        collapse(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD4);
        verifyListItems(NAVLIST_ID, NAVLIST_CHILD3_ITEMS, null);
        verifySelectedValue(selExpectedVal);
        verifyGetExpandedMethodReturnValue("playback,play,track8,");

        //Go back to Play
        collapse(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD3);
        verifyListItems(NAVLIST_ID, NAVLIST_CHILD2_ITEMS, null);
        verifySelectedValue(selExpectedVal);
        verifyGetExpandedMethodReturnValue("playback,play,");

        //Go back to Playback
        collapse(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD2);
        verifyListItems(NAVLIST_ID, NAVLIST_CHILD1_ITEMS, null);
        verifySelectedValue(selExpectedVal);
        verifyGetExpandedMethodReturnValue("playback,");

        //Go back to Navigation List
        collapse(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD1);
        verifyListItems(NAVLIST_ID, NAVLIST_ITEMS, null);
        verifySelectedValue(selExpectedVal);
        verifyGetExpandedMethodReturnValue("");

        //4. "save" and "playback" are collapsed
        for (String item : NAVLIST_ITEMS) {
            WebElement listItem =
                getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" +
                           item + "\"}");
            String classes;
            boolean isSelected, isCollapsed;
            switch (item) {
            case "save":
            case "playback":
                //collapsed list items
                classes = listItem.getAttribute("class");
                isCollapsed = classes.indexOf("oj-collapsed") > -1;
                Assert.assertTrue(isCollapsed);

                break;
            default:
                classes = listItem.getAttribute("class");
                isCollapsed = classes.indexOf("oj-collapsed") > -1;
                Assert.assertFalse(isCollapsed);
                break;
            }

        }

        //verify that "print" is disabled
        verifyItemIsDisabled(NAVLIST_ID, NAVLIST_DISABLED_ITEM);

        //Click on zoomin and verify that zoomin is selected
        selectListItem(NAVLIST_ID, NAVLIST_ITEM_ZOOMIN);
        selExpectedVal = "Option Value: zoomin Observable Value: zoomin";
        verifySelectedValue(selExpectedVal);

    }

    @Test(groups = { "navigationlist" })
    public void testUIAfterNavigationByOptionMethod() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);

        //Set expanded option to playback, play,  using option method
        WebElement btn = getElement("id=setexpop1");
        btn.click();


        verifyListItems(NAVLIST_ID, NAVLIST_CHILD2_ITEMS, null);
        verifyGetExpandedMethodReturnValue("playback,play,");

    }

    @Test(groups = { "navigationlist" })
    public void testUIAfterNavigatingBackToRootUsingOptionMethod() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);

        //Navigate
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD1);
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD2);
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_GO_TO_CHILD3);
        //Set expanded option to []  using option method
        WebElement btn = getElement("id=setexpop2");
        btn.click();


        verifyListItems(NAVLIST_ID, NAVLIST_ITEMS, null);
        verifyGetExpandedMethodReturnValue("");

    }
}
