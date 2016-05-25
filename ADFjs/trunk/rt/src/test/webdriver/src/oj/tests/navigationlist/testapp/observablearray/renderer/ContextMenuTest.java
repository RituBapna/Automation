package oj.tests.navigationlist.testapp.observablearray.renderer;

import java.util.List;

import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class ContextMenuTest extends NavigationlistTestBase {
    private static final String TEST_PAGE = "tableDataSource_OA_renderer.html";
    private static final String TEST_PAGE_TITLE = "Navigation List: TableDataSource";
    private static final String NAVLIST_ITEM_BASE = "base";
    private static final String NAVLIST_ID = "navList";
    private static final String NAVLIST_ITEM_SETTINGS = "settings";
   
    

    public ContextMenuTest() {
       
        super("ojnavigationlist", "navigationlist/navigationlistTest");
    }

    @Test(groups = { "navigationlist" })
    public void testContextMenuLeafItem() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Click on button to Populate the
        //list
        WebElement initBtn = getElement("id=init");
        initBtn.click();
        this.waitForMilliseconds(1000);
        //make sute that menu is not displayed
        WebElement contextMenu = getElement("id=myMenu");
        String style = contextMenu.getAttribute("style");
        Assert.assertTrue(style.indexOf("display: none;") > -1);
        
        //Right mouse click on "base" nav list item
        WebElement listItem =
            getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" +
                       NAVLIST_ITEM_BASE + "\"}");
       
       
        Actions action = new Actions(getWebDriver());
        action.contextClick(listItem).perform();
        waitForElementVisible("id=myMenu");
        style = contextMenu.getAttribute("style");
        Assert.assertTrue(style.indexOf("display: none;") == -1);
       
       List<WebElement> menuitems = contextMenu.findElements(By.tagName("li"));
       Assert.assertEquals(menuitems.size(), 6, "There are 6 menu items in context menu");
      
        WebElement lastmenuitem = menuitems.get(menuitems.size()-1).findElement(By.tagName("a"));
        Assert.assertEquals(lastmenuitem.getText(), "base");
        
        //Click on last menu item
        action.moveToElement(lastmenuitem).click().perform();
        //lastmenuitem.click();
        
        //Check thast menu item has been clicked
        waitForMilliseconds(1000);
        WebElement result = getElement("id=results_menu");
        Assert.assertEquals(result.getText(), "base");
    }
    
   
   
}
