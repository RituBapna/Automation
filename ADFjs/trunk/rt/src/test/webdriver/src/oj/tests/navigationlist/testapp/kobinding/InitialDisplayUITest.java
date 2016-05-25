package oj.tests.navigationlist.testapp.kobinding;

import java.util.List;

import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

    public class InitialDisplayUITest extends NavigationlistTestBase {
        private static final String TEST_PAGE = "htmlMarkup_koBinding.html";
        private static final String TEST_PAGE_TITLE = "Navigation List: static markup: ko binding";
       
        private static final String NAVLIST_ID = "navList";
        private static final String NAVLIST_DRILLMODE = "none";
        private static final String NAVLIST_DISABLED_ITEM = "environment";
        private static final String[] NAVLIST_ITEMS = { "settings", "tools", "base", "environment", "security" };
        private static final String NAVLIST_ITEM_SECURITY = "security";
        private static final String NAVLIST_ITEM_BASE = "base";

        public InitialDisplayUITest() {
            super("ojnavigationlist", "navigationlist/navigationlistTest");
        }

        @Test(groups = { "navigationlist" })
        public void testbasicUI() throws Exception {
            startupTest(TEST_PAGE, null);
            verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

            //wait for the navigationlist to be displayed
            waitForElementVisible(NAVLIST_ID);
 Thread.sleep(10000);
          
            //verify nav list drill mode
            verifyDrillMode(NAVLIST_ID, NAVLIST_DRILLMODE);
 Thread.sleep(8000);
           
            //Verify list items and that security is selected
            verifyListItems(NAVLIST_ID, NAVLIST_ITEMS, NAVLIST_ITEM_SECURITY);
            
            //verify that "environment" is disabled
             WebElement listItem = getElement("{\"element\":\"#"+ NAVLIST_ID+ "\",\"subId\":\"oj-navigationlist-item\",\"key\":\""+ NAVLIST_DISABLED_ITEM +"\"}");
            String classes = listItem.getAttribute("class");

            //assert its disabled
            boolean isDisabled = classes.indexOf("oj-disabled") > -1;
            Assert.assertTrue(isDisabled);
          
        }
        
        @Test(groups = { "navigationlist" })
        public void testChangeItemSelection() throws Exception {
            startupTest(TEST_PAGE, null);
            verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

            //wait for the navigationlist to be displayed
            waitForElementVisible(NAVLIST_ID);
           
            //Select "base" by clicking on it
            selectListItem(NAVLIST_ID, NAVLIST_ITEM_BASE);
            
            verifyItemIsSelected(NAVLIST_ID, NAVLIST_ITEM_BASE);
            verifyItemIsNotSelected(NAVLIST_ID, NAVLIST_ITEM_SECURITY);
        }
        

    }

