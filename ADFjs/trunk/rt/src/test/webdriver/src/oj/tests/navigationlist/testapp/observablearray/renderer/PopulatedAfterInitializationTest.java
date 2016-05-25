package oj.tests.navigationlist.testapp.observablearray.renderer;

import java.util.List;

import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PopulatedAfterInitializationTest extends NavigationlistTestBase {
    private static final String TEST_PAGE = "tableDataSource_OA_renderer.html";
    private static final String TEST_PAGE_TITLE = "Navigation List: TableDataSource";
    private static final String NAVLIST_SELECTED_ITEM = "security";
    private static final String NAVLIST_ID = "navList";
    private static final String NAVLIST_DRILLMODE = "none";
    private static final String NAVLIST_DISABLED_ITEM = "environment";
    private static final String[] NAVLIST_ITEMS = { "settings", "tools", "base", "environment", "security" };
    private static final String[] NAVLIST_ITEMS_NAMES = { "Settings", "Tools", "Base", "Environment", "Security" };


    public PopulatedAfterInitializationTest() {
        super("ojnavigationlist", "navigationlist/navigationlistTest");
    }

    @Test(groups = { "navigationlist" })
    public void testInitialListIsEmpty() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //verify drillmode
        verifyDrillMode(NAVLIST_ID, NAVLIST_DRILLMODE);

        WebElement navlist = getElement("id=" + NAVLIST_ID);
        boolean isListEmpty = isElementPresent(By.className("oj-listview-empty-text"));
        Assert.assertTrue(isListEmpty);

        //getExpanded method return null

        verifyGetExpandedMethodReturnValue("");

    }

    @Test(groups = { "navigationlist" })
    public void testPopulateEmptyList() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);

        WebElement navlist = getElement("id=" + NAVLIST_ID);
        //Click on button to Populate the
        //list
        WebElement btn = getElement("id=init");
        btn.click();

        verifyListItems(NAVLIST_ID, NAVLIST_ITEMS, NAVLIST_SELECTED_ITEM);
       
        this.verifyItemIsDisabled(NAVLIST_ID, NAVLIST_DISABLED_ITEM);
        
        //getExpanded method return null
        verifyGetExpandedMethodReturnValue("");

    }

    void verifyContentsOfEachListItem() {
        int i = 0;
        for (String item : NAVLIST_ITEMS) {
            WebElement listItem =
                getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" +
                           item + "\"}");
            WebElement spanElem = listItem.findElement(By.className("oj-navigationlist-item-label"));
            List<WebElement> contentElements = spanElem.findElements(By.xpath("./*"));
            Assert.assertEquals(contentElements.size(), 5);

            //First element is the icon
            boolean icon = contentElements.get(0).getAttribute("class").indexOf("oj-navigationlist-item-icon") > -1;
            Assert.assertTrue(icon);

            //second element is link with certaion text
            String linkText = contentElements.get(1).getText().trim();
            Assert.assertEquals(linkText, NAVLIST_ITEMS_NAMES[i]);
            //third is dvt
            boolean dvt = contentElements.get(2).getAttribute("class").indexOf("oj-ratinggauge") > -1;
            Assert.assertTrue(dvt);
            //fourth label
            boolean label = contentElements.get(3).getAttribute("class").indexOf("oj-inputtext-label") > -1;
            Assert.assertTrue(label);
            //fifth is input text
            boolean input = contentElements.get(4).getAttribute("class").indexOf("oj-inputtext") > -1;
            Assert.assertTrue(input);
            i++;

        }
    }
}
