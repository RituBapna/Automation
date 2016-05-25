package oj.tests.navigationlist.testapp.staticmarkup.collapsible;

import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;

import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

    public class InitialDisplayUITest extends NavigationlistTestBase {
        private static final String TEST_PAGE = "htmlMarkup_static.html";
        private static final String TEST_PAGE_TITLE = "Navigation List: Static Markup Test";
       
        private static final String NAVLIST_ID = "navList";
        private static final String NAVLIST_DRILLMODE = "collapsible";
        private static final String NAVLIST_DISABLED_ITEM = "print";
        private static final String[] NAVLIST_ITEMS = { "save", "zoomin", "zoomout", "print", "playback" };
        

        public InitialDisplayUITest() {
            super("ojnavigationlist", "navigationlist/navigationlistTest");
        }

        @Test(groups = { "navigationlist" })
        public void testbasicUI() throws Exception {
            startupTest(TEST_PAGE, null);
            verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

            //wait for the navigationlist to be displayed
            waitForElementVisible(NAVLIST_ID);
            //Change drill mode to collapsible
            changeDrillModeToCollapsible();
            //verify nav list drill mode
            verifyDrillMode(NAVLIST_ID, NAVLIST_DRILLMODE);
            //Verify 
            //1. NaviList display all its items  
            //2. No item is selected
         
            verifyListItems(NAVLIST_ID,  NAVLIST_ITEMS, null);
           
            //Verify "save" and "playback" are collapsed
            for (String item : NAVLIST_ITEMS) {
             WebElement listItem =
                    getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" + item +
                               "\"}");
                String classes;
                boolean  isCollapsed;
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
            WebElement listItem = getElement("{\"element\":\"#"+ NAVLIST_ID+ "\",\"subId\":\"oj-navigationlist-item\",\"key\":\""+ NAVLIST_DISABLED_ITEM +"\"}");
            String classes = listItem.getAttribute("class");

            //assert its disabled
            boolean isDisabled = classes.indexOf("oj-disabled") > -1;
            Assert.assertTrue(isDisabled);
            
            verifyGetExpandedMethodReturnValue("");

        }
        
        

    }
