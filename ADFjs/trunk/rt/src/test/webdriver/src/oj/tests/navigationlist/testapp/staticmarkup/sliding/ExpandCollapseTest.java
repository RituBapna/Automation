package oj.tests.navigationlist.testapp.staticmarkup.sliding;

import java.util.List;

import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;
import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;

public class ExpandCollapseTest extends NavigationlistTestBase {
    private static final String TEST_PAGE = "htmlMarkup_static.html";
    private static final String TEST_PAGE_TITLE = "Navigation List: Static Markup Test";
    private static final String NAVLIST_ROOT_HEADING = "Navigation List";
    private static final String NAVLIST_CURRENT_HEADING = "Save";
    private static final String NAVLIST_ID = "navList";
    private static final String NAVLIST_ITEM_TO_SELECT = "save";
    private static final String NAVLIST_ITEM_TO_FOCUS = "tape";
    private static final String NAVLIST_ITEM_TAPE = "tape";
    private static final String[] NAVLIST_CHILD_ITEMS = { "disk", "tape" };
    private static final String NAVLIST_DISABLED_ITEM = "print";
    private static final String[] NAVLIST_CHILD_HEIRARCHICAL_MENU = {"Navigation List"};
    private static final String HIERARCHICAL_MENUITEM_TO_CLICK = "Navigation List";
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
        //Click on "save" nav list item
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_SELECT);
        verifyHeading(NAVLIST_ID,  NAVLIST_CURRENT_HEADING);
        verifyHierarchicalMenu(NAVLIST_ID,NAVLIST_CHILD_HEIRARCHICAL_MENU);      
        verifyItemsAreNotSelected(NAVLIST_ID,  NAVLIST_CHILD_ITEMS);
        verifyGetExpandedMethodReturnValue("save,");
    }
    
    
    @Test(groups = { "navigationlist" })
    public void testUIAfterSelectionByObservableValueChange() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Set expanded option to save using observable value
        WebElement input = getElement("{\"element\":\"#" + EXPANDED_COMBOBOX_ID + "\",\"subId\":\"oj-combobox-input\"}");
        input.sendKeys("save");
        input.sendKeys(Keys.TAB);
               
    
        verifyHeading(NAVLIST_ID,  NAVLIST_CURRENT_HEADING);
        verifyHierarchicalMenu(NAVLIST_ID,NAVLIST_CHILD_HEIRARCHICAL_MENU);      
        verifyItemsAreNotSelected(NAVLIST_ID,  NAVLIST_CHILD_ITEMS);
    }
    
  
    
    @Test(groups = { "navigationlist" })
    public void testUIAfterNavigatingBackToParentUsingHeaderLink() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Navigate by clicking on "Save"
        navigate( NAVLIST_ID, NAVLIST_ITEM_TO_SELECT);
       

        //Navigate back by clicking on the Previous link
        navigateBackByClickingOnPreviousLink(NAVLIST_ID);
        verifyHeading(NAVLIST_ID, NAVLIST_ROOT_HEADING);
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
    
    @Test(groups = { "navigationlist" })
    public void testUIAfterNavigatingBackToParentUsingHierarchicalMenuItem() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Navigate by clicking on "Save"
        navigate( NAVLIST_ID, NAVLIST_ITEM_TO_SELECT);
       

        //Navigate back by clicking on the Previous link
        navigateBackByClickingOnHierarchicalMenuItem(NAVLIST_ID, HIERARCHICAL_MENUITEM_TO_CLICK);
        verifyHeading(NAVLIST_ID, NAVLIST_ROOT_HEADING);
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
