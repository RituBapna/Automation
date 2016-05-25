package oj.tests.navigationlist.testapp.staticmarkup.collapsible;


import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ExpandCollapseTest extends NavigationlistTestBase {
    private static final String TEST_PAGE = "htmlMarkup_static.html";
    private static final String TEST_PAGE_TITLE = "Navigation List: Static Markup Test";
   
    private static final String NAVLIST_ID = "navList";
   
    private static final String NAVLIST_ITEM_TO_SELECT = "save";
    private static final String[] NAVLIST_CHILD_ITEMS = { "disk", "tape" };
    private static final String NAVLIST_DISABLED_ITEM = "print";
  private static final String[] NAVLIST_ITEMS = { "save", "zoomin", "zoomout", "print", "playback" };
    private static final String EXPANDED_COMBOBOX_ID =  "combobox";


    public ExpandCollapseTest() {
       
        super("ojnavigationlist", "navigationlist/navigationlistTest");
    }

    @Test(groups = { "navigationlist" })
    public void testUIAfterSelectionByClick() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Change drill mode to collapsible
        changeDrillModeToCollapsible();
        
        //Click on "save" nav list item
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_SELECT);
         verifyItemsAreNotSelected(NAVLIST_ID, NAVLIST_CHILD_ITEMS);
        
        verifyGetExpandedMethodReturnValue("save,");
    }
    
    
    @Test(groups = { "navigationlist" })
    public void testUIAfterSelectionByObservableValueChange() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        
        //Change drill mode to collapsible
        changeDrillModeToCollapsible();
        
        //Set expanded option to save using observable value
        WebElement input = getElement("{\"element\":\"#" + EXPANDED_COMBOBOX_ID + "\",\"subId\":\"oj-combobox-input\"}");
        input.sendKeys("save");
        input.sendKeys(Keys.TAB);
               
        verifyItemsAreNotSelected(NAVLIST_ID,  NAVLIST_CHILD_ITEMS);
        verifyGetExpandedMethodReturnValue("save,");
    }
    
  
    
    @Test(groups = { "navigationlist" })
    public void testUIAfterCollapse() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Change drill mode to collapsible
        changeDrillModeToCollapsible();
        
        //Expand by clicking on "Save" 
        navigate( NAVLIST_ID, NAVLIST_ITEM_TO_SELECT);
        
        //Collapse by clicking on "Save" again
        collapse( NAVLIST_ID, NAVLIST_ITEM_TO_SELECT);
       

        verifyItemsAreNotSelected(NAVLIST_ID,  NAVLIST_ITEMS);
        //Verify 
       
        //4. "save" and "playback" are collapsed
        for (String item : NAVLIST_ITEMS) {
         WebElement listItem =
                getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" + item +
                           "\"}");
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
        WebElement listItem = getElement("{\"element\":\"#"+ NAVLIST_ID+ "\",\"subId\":\"oj-navigationlist-item\",\"key\":\""+ NAVLIST_DISABLED_ITEM +"\"}");
        String classes = listItem.getAttribute("class");

        //assert its disabled
        boolean isDisabled = classes.indexOf("oj-disabled") > -1;
        Assert.assertTrue(isDisabled);
        
        verifyGetExpandedMethodReturnValue("");
    }
    
  
    
   
}
