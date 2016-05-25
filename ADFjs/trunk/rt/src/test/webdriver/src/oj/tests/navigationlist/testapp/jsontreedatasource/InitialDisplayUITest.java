package oj.tests.navigationlist.testapp.jsontreedatasource;

import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InitialDisplayUITest extends NavigationlistTestBase {
    private static final String TEST_PAGE = "treeDataSource_Json_simple.html";
    private static final String TEST_PAGE_TITLE = "Navigation List: TreeDataSource";
    private static final String NAVLIST_ROOT_HEADING = "Navigation List";
    private static final String NAVLIST_ID = "navList";
    private static final String NAVLIST_DRILLMODE = "sliding";
    
    private static final String[] NAVLIST_ITEMS = { "public", "private", "work", "others" };
    

    public InitialDisplayUITest() {
        super("ojnavigationlist", "navigationlist/navigationlistTest");
    }

    @Test(groups = { "navigationlist" })
    public void testbasicUI() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //verify drillmode 
        verifyDrillMode(NAVLIST_ID, NAVLIST_DRILLMODE);
        //get Navigation list heading
        verifyHeading(NAVLIST_ID, NAVLIST_ROOT_HEADING );
        //Verify 
        //1. NaviList display all its items  
        //2. No item is selected
     
        verifyListItems(NAVLIST_ID,  NAVLIST_ITEMS, null);
       
        //Verify all list items are collapsed
        for (String item : NAVLIST_ITEMS) {
         WebElement listItem =
                getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" + item +
                           "\"}");
            String classes;
            boolean isSelected, isCollapsed;
         
                classes = listItem.getAttribute("class");
                isCollapsed = classes.indexOf("oj-collapsed") > -1;
                Assert.assertTrue(isCollapsed);
      
            }
            
        }
        
     

    }
