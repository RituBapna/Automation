package oj.tests.navigationlist.testapp.collection;


import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

    public class NavListBasedOnADFBCRestWSTest extends NavigationlistTestBase {
        private static final String TEST_PAGE = "tableDataSource_Collection_template.html";
        private static final String TEST_PAGE_TITLE = "Navigation List: TableDataSource: Collection";
       
        private static final String NAVLIST_ID = "navList";
        private static final String NAVLIST_DRILLMODE = "none";
      
        private static final String NAVLIST_ITEM_ADMINISTRATION = "10";
        private static final String NAVLIST_ITEM_SALES = "80";
        private static final String NAVLIST_ITEM_RECRUITING = "260";
      

        public NavListBasedOnADFBCRestWSTest() {
            super("ojnavigationlist", "navigationlist/navigationlistTest");
        }

        @Test(groups = { "navigationlist" })
        public void testbasicUI() throws Exception {
            startupTest(TEST_PAGE, null);
            verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

            //wait for the navigationlist to be displayed
            waitForElementVisible(NAVLIST_ID);
            
           
            //verify nav list drill mode
            verifyDrillMode(NAVLIST_ID, NAVLIST_DRILLMODE);
           
         
            
            //verify that "Administration" is list item is present 
            boolean isPresent = this.isElementPresent("{\"element\":\"#"+ NAVLIST_ID+ "\",\"subId\":\"oj-navigationlist-item\",\"key\":\""+ NAVLIST_ITEM_ADMINISTRATION +"\"}");
            
            Assert.assertTrue(isPresent);
            
            //verify that "Sales" is list item is present 
            isPresent = this.isElementPresent("{\"element\":\"#"+ NAVLIST_ID+ "\",\"subId\":\"oj-navigationlist-item\",\"key\":\""+ NAVLIST_ITEM_SALES +"\"}");
            Assert.assertTrue(isPresent);
            
            //verify that "Recruiting " is list item is present 
            isPresent = this.isElementPresent("{\"element\":\"#"+ NAVLIST_ID+ "\",\"subId\":\"oj-navigationlist-item\",\"key\":\""+ NAVLIST_ITEM_RECRUITING +"\"}");
            Assert.assertTrue(isPresent);            

        }
        
        

    }

