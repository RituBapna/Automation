package oj.tests.navigationlist.testapp.jsontreedatasource;

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
    private static final String TEST_PAGE = "treeDataSource_Json_simple.html";
    private static final String TEST_PAGE_TITLE = "Navigation List: TreeDataSource";
    private static final String NAVLIST_ROOT_HEADING = "Navigation List";
    private static final String NAVLIST_CURRENT_HEADING = "Private";
    private static final String NAVLIST_ID = "navList";
    private static final String NAVLIST_ITEM_TO_SELECT = "private";
    private static final String NAVLIST_ITEM_TO_FOCUS = "tape";
    private static final String NAVLIST_ITEM_TAPE = "tape";
    private static final String[] NAVLIST_CHILD_ITEMS = { "f5", "f6", "f7", "f8" };

    private static final String[] NAVLIST_CHILD_HEIRARCHICAL_MENU = { "Navigation List" };
    private static final String HIERARCHICAL_MENUITEM_TO_CLICK = "Navigation List";
    private static final String[] NAVLIST_ITEMS = { "public", "private", "work", "others" };
    private static final String EXPANDED_COMBOBOX_ID = "combobox";


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
        verifyHeading(NAVLIST_ID, NAVLIST_CURRENT_HEADING);
        verifyHierarchicalMenu(NAVLIST_ID, NAVLIST_CHILD_HEIRARCHICAL_MENU);
        verifyItemsAreNotSelected(NAVLIST_ID, NAVLIST_CHILD_ITEMS);
        verifyGetExpandedMethodReturnValue("private,");
    }


    @Test(groups = { "navigationlist" })
    public void testUIAfterSelectionByObservableValueChange() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Set expanded option to private using observable value
        WebElement input =
            getElement("{\"element\":\"#" + EXPANDED_COMBOBOX_ID + "\",\"subId\":\"oj-combobox-input\"}");
        input.sendKeys("private");
        input.sendKeys(Keys.TAB);


        verifyHeading(NAVLIST_ID, NAVLIST_CURRENT_HEADING);
        verifyHierarchicalMenu(NAVLIST_ID, NAVLIST_CHILD_HEIRARCHICAL_MENU);
        verifyItemsAreNotSelected(NAVLIST_ID, NAVLIST_CHILD_ITEMS);
    }


    @Test(groups = { "navigationlist" })
    public void testUIAfterNavigatingBackToParentUsingHeaderLink() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Navigate by clicking on "private"
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_SELECT);


        //Navigate back by clicking on the Previous link
        navigateBackByClickingOnPreviousLink(NAVLIST_ID);
        verifyHeading(NAVLIST_ID, NAVLIST_ROOT_HEADING);
        verifyItemsAreNotSelected(NAVLIST_ID, NAVLIST_ITEMS);
        //Verify

        //4. all items are collapsed
        for (String item : NAVLIST_ITEMS) {
            WebElement listItem =
                getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" +
                           item + "\"}");
            String classes;
            boolean isSelected, isCollapsed;

            //collapsed list items
            classes = listItem.getAttribute("class");
            isCollapsed = classes.indexOf("oj-collapsed") > -1;
            Assert.assertTrue(isCollapsed);


        }


        verifyGetExpandedMethodReturnValue("");
    }

    @Test(groups = { "navigationlist" })
    public void testUIAfterNavigatingBackToParentUsingHierarchicalMenuItem() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);

        //Navigate by clicking on "private"
        navigate(NAVLIST_ID, NAVLIST_ITEM_TO_SELECT);


        //Navigate back by clicking on the Previous link
        navigateBackByClickingOnHierarchicalMenuItem(NAVLIST_ID, HIERARCHICAL_MENUITEM_TO_CLICK);
        verifyHeading(NAVLIST_ID, NAVLIST_ROOT_HEADING);
        verifyItemsAreNotSelected(NAVLIST_ID, NAVLIST_ITEMS);
        //Verify

        //4. all items are collapsed
        for (String item : NAVLIST_ITEMS) {
            WebElement listItem =
                getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" +
                           item + "\"}");
            String classes;
            boolean isSelected, isCollapsed;

            //collapsed list items
            classes = listItem.getAttribute("class");
            isCollapsed = classes.indexOf("oj-collapsed") > -1;
            Assert.assertTrue(isCollapsed);

        }

        verifyGetExpandedMethodReturnValue("");
    }


}
