package oj.tests.navigationlist.testapp.staticmarkup.expanded;

import java.util.List;

import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

    public class InitialDisplayUITest extends NavigationlistTestBase {
        private static final String TEST_PAGE = "htmlMarkup_static.html";
        private static final String TEST_PAGE_TITLE = "Navigation List: Static Markup Test";
       
        private static final String NAVLIST_ID = "navList";
        private static final String NAVLIST_DRILLMODE = "none";
        private static final String NAVLIST_DISABLED_ITEM = "print";
        private static final String[] NAVLIST_ITEMS = { "save", "zoomin", "zoomout", "print", "playback" };
        private static final String NAVLIST_ITEM_SAVE = "save";
        private static final String NAVLIST_ITEM_PLAYBACK = "playback";
        private static final String NAVLIST_ITEM_PLAY = "play";
        private static final String NAVLIST_ITEM_TRACK8 = "track8";
        private static final String NAVLIST_ITEM_SUBTRACK8 = "subtrack8";
        private static final String NAVLIST_ITEM_SUBSUBTRACK4 = "subsubtrack4";
        private static final String NAVLIST_ITEM_SUBSUBSUBTRACK4 = "subsubsubtrack4";
        
        private static final String[] NAVLIST_SAVE_LIST = { "disk", "tape"};
        private static final String[] NAVLIST_PLAYBACK_LIST = { "prev", "stop", "play", "nextItem" };
        private static final String[] NAVLIST_PLAY_LIST = {
            "track1", "track2", "track3", "track4", "track5", "track6", "track7", "track8"
        };
        private static final String[] NAVLIST_TRACK8_LIST = {
            "subtrack1", "subtrack2", "subtrack3", "subtrack4", "subtrack5", "subtrack6", "subtrack7", "subtrack8"
        };
        private static final String[] NAVLIST_SUBTRACK8_LIST = {
            "subsubtrack1", "subsubtrack2", "subsubtrack3", "subsubtrack4" };
        private static final String[] NAVLIST_SUBSUBTRACK4_LIST = {
            "subsubsubtrack1", "subsubsubtrack2", "subsubsubtrack3", "subsubsubtrack4" };
        private static final String[] NAVLIST_SUBSUBSUBTRACK4_LIST = {
            "subsubsubsubtrack1", "subsubsubsubtrack2", "subsubsubsubtrack3", "subsubsubsubtrack4", "subsubsubsubtrack5",
            "subsubsubsubtrack6", "subsubsubsubtrack7", "subsubsubsubtrack8", "subsubsubsubtrack9", "subsubsubsubtrack10",
            "subsubsubsubtrack11", "subsubsubsubtrack12", "subsubsubsubtrack13", "subsubsubsubtrack14",
            "subsubsubsubtrack15", "subsubsubsubtrack16", "subsubsubsubtrack17", "subsubsubsubtrack18",
            "subsubsubsubtrack19", "subsubsubsubtrack20", "subsubsubsubtrack21", "subsubsubsubtrack22",
            "subsubsubsubtrack23", "subsubsubsubtrack24", "subsubsubsubtrack25", "subsubsubsubtrack26",
            "subsubsubsubtrack27", "subsubsubsubtrack28"
        };

        public InitialDisplayUITest() {
            super("ojnavigationlist", "navigationlist/navigationlistTest");
        }

        @Test(groups = { "navigationlist" })
        public void testbasicUI() throws Exception {
            startupTest(TEST_PAGE, null);
            verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

            //wait for the navigationlist to be displayed
            waitForElementVisible(NAVLIST_ID);
            //Change drill mode to none / expanded
            changeDrillModeToNone();
            //verify nav list drill mode
            verifyDrillMode(NAVLIST_ID, NAVLIST_DRILLMODE);
           
            //Verify "save" and "playback" are expanded
            verifySubListItemsInExpandedList(NAVLIST_ID, NAVLIST_ITEM_SAVE, NAVLIST_SAVE_LIST);
            verifySubListItemsInExpandedList(NAVLIST_ID, NAVLIST_ITEM_PLAYBACK, NAVLIST_PLAYBACK_LIST);
            verifySubListItemsInExpandedList(NAVLIST_ID, NAVLIST_ITEM_PLAY, NAVLIST_PLAY_LIST);
            verifySubListItemsInExpandedList(NAVLIST_ID, NAVLIST_ITEM_TRACK8, NAVLIST_TRACK8_LIST);
            verifySubListItemsInExpandedList(NAVLIST_ID, NAVLIST_ITEM_SUBTRACK8, NAVLIST_SUBTRACK8_LIST);
            verifySubListItemsInExpandedList(NAVLIST_ID, NAVLIST_ITEM_SUBSUBTRACK4, NAVLIST_SUBSUBTRACK4_LIST);
            verifySubListItemsInExpandedList(NAVLIST_ID, NAVLIST_ITEM_SUBSUBSUBTRACK4, NAVLIST_SUBSUBSUBTRACK4_LIST);

            
            //verify that "print" is disabled
             WebElement listItem = getElement("{\"element\":\"#"+ NAVLIST_ID+ "\",\"subId\":\"oj-navigationlist-item\",\"key\":\""+ NAVLIST_DISABLED_ITEM +"\"}");
            String classes = listItem.getAttribute("class");

            //assert its disabled
            boolean isDisabled = classes.indexOf("oj-disabled") > -1;
            Assert.assertTrue(isDisabled);
            
            verifyGetExpandedMethodReturnValue("save,playback,play,track8,subtrack8,subsubtrack4,subsubsubtrack4,");

        }
        
        

    }

