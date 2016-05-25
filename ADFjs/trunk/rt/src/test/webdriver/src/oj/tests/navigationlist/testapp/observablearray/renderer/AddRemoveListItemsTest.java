package oj.tests.navigationlist.testapp.observablearray.renderer;

import java.util.List;

import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddRemoveListItemsTest extends NavigationlistTestBase {
    private static final String TEST_PAGE = "tableDataSource_OA_renderer.html";
    private static final String TEST_PAGE_TITLE = "Navigation List: TableDataSource";
    private static final String NAVLIST_SELECTED_ITEM = "security";
    private static final String NAVLIST_ID = "navList";
   
    private static final String NAVLIST_DISABLED_ITEM = "environment";
    private static final String[] NAVLIST_ITEMS = { "settings", "tools", "base", "environment", "security" };
    private static final String[] NAVLIST_ITEMS_AFTER_ADD = { "settings", "tools", "base", "environment", "security" };
    private static final String[] NAVLIST_ITEMS_AFTER_DELETE = { "settings", "tools", "base", "environment" };
    private static final String[] NAVLIST_ITEMS_NAMES = { "Settings", "Tools", "Base", "Environment", "Security" };


    public AddRemoveListItemsTest() {
        super("ojnavigationlist", "navigationlist/navigationlistTest");
    }

    @Test(groups = { "navigationlist" })
    public void testDeleteAnExistingItem() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Click on button to Populate the
        //list
        WebElement initBtn = getElement("id=init");
        initBtn.click();
        //Delete the last item from the list
        WebElement removeBtn = getElement("id=remove");
        removeBtn.click();
       
        verifyListItems(NAVLIST_ID, NAVLIST_ITEMS_AFTER_DELETE, "");

    }

    @Test(groups = { "navigationlist" })
    public void testAddingANewListItem() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);

        //Click on button to Populate the
        //list
        WebElement btn = getElement("id=init");
        btn.click();

        //Add a list item to the end of the list
        WebElement addBtn = getElement("id=add");
        addBtn.click();
        
        verifyListItems(NAVLIST_ID, NAVLIST_ITEMS_AFTER_ADD, NAVLIST_SELECTED_ITEM);
       
        this.verifyItemIsDisabled(NAVLIST_ID, NAVLIST_DISABLED_ITEM);
        
        //getExpanded method return null
        verifyGetExpandedMethodReturnValue("");

    }

    @Test(groups = { "navigationlist" }, dependsOnMethods= {"testAddingANewListItem", "testDeleteAnExistingItem"})
    public void testDeletingNewListItem() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);

        //Click on button to Populate the
        //list
        WebElement btn = getElement("id=init");
        btn.click();

        //Add a list item to the end of the list
        WebElement addBtn = getElement("id=add");
        addBtn.click();

        //Delete the last item from the list
        WebElement removeBtn = getElement("id=remove");
        removeBtn.click();
        
        verifyListItems(NAVLIST_ID, NAVLIST_ITEMS, NAVLIST_SELECTED_ITEM);
       // verifyContentsOfEachListItem(NAVLIST_ID, NAVLIST_ITEMS, NAVLIST_ITEMS_NAMES);
        this.verifyItemIsDisabled(NAVLIST_ID, NAVLIST_DISABLED_ITEM);
        
        //getExpanded method return null
        verifyGetExpandedMethodReturnValue("");

    }
    
   
}


