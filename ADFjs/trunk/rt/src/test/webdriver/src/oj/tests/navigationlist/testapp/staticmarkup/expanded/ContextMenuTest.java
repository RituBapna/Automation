package oj.tests.navigationlist.testapp.staticmarkup.expanded;

import java.util.List;

import oj.tests.navigationlist.testapp.utils.NavigationlistTestBase;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class ContextMenuTest extends NavigationlistTestBase {
    private static final String TEST_PAGE = "htmlMarkup_static.html";
    private static final String TEST_PAGE_TITLE = "Navigation List: Static Markup Test";
    private static final String NAVLIST_ITEM_ZOOMIN = "zoomin";
    private static final String NAVLIST_ID = "navList";
    private static final String NAVLIST_ITEM_SAVE = "save";
    private static final String NAVLIST_ITEM_PLAYBACK = "playback";
    private static final String NAVLIST_ITEM_PLAY = "play";
    private static final String NAVLIST_ITEM_TRACK8 = "track8";
    private static final String NAVLIST_ITEM_SUBTRACK3 = "subtrack3";
    

    public ContextMenuTest() {
       
        super("ojnavigationlist", "navigationlist/navigationlistTest");
    }

    @Test(groups = { "navigationlist" })
    public void testContextMenuLeafItem() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Change drill mode to none/expanded
        changeDrillModeToNone();
        //make sute that menu is not displayed
        WebElement contextMenu = getElement("id=myMenu");
        String style = contextMenu.getAttribute("style");
        Assert.assertTrue(style.indexOf("display: none;") > -1);
        
        //Right mouse click on "zoomin" nav list item
        WebElement listItem =
            getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" +
                       NAVLIST_ITEM_ZOOMIN + "\"}");
        listItem.click();
        Actions action = new Actions(getWebDriver());
        action.contextClick(listItem).perform();
        waitForElementVisible("id=myMenu");
        style = contextMenu.getAttribute("style");
        Assert.assertTrue(style.indexOf("display: none;") == -1);
       
       List<WebElement> menuitems = contextMenu.findElements(By.tagName("li"));
       Assert.assertEquals(menuitems.size(), 6, "There are 6 menu items in context menu");
      
        WebElement lastmenuitem = menuitems.get(menuitems.size()-1).findElement(By.tagName("a"));
        Assert.assertEquals(lastmenuitem.getText(), "zoomin");
        
        //Click on last menu item
        action.moveToElement(lastmenuitem).click().perform();
        //lastmenuitem.click();
        
        //Check thast menu item has been clicked
        waitForMilliseconds(1000);
        WebElement result = getElement("id=results_menu");
        Assert.assertEquals(result.getText(), "zoomin");
    }
    
    @Test(groups = { "navigationlist" })
    public void testContextMenuParentItem() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Change drill mode to noner
        changeDrillModeToNone();
        //make sute that menu is not displayed
        WebElement contextMenu = getElement("id=myMenu");
        String style = contextMenu.getAttribute("style");
        Assert.assertTrue(style.indexOf("display: none;") > -1);
        
        //Right mouse click on "save" nav list item
         
        WebElement listItem = getParentElementInExpandedList(NAVLIST_ID, NAVLIST_ITEM_SAVE);
        Actions action = new Actions(getWebDriver());
        action.contextClick(listItem).perform();
        waitForElementVisible("id=myMenu");
        style = contextMenu.getAttribute("style");
        Assert.assertTrue(style.indexOf("display: none;") == -1);
       
       List<WebElement> menuitems = contextMenu.findElements(By.tagName("li"));
       Assert.assertEquals(menuitems.size(), 6, "There are 6 menu items in context menu");
      
        WebElement lastmenuitem = menuitems.get(menuitems.size()-1).findElement(By.tagName("a"));
        Assert.assertEquals(lastmenuitem.getText(), "save");
        
        //click on last menu item
        action.moveToElement(lastmenuitem).click().perform();
    
        //Check thast menu item has been clicked
        waitForMilliseconds(1000);
        WebElement result = getElement("id=results_menu");
        Assert.assertEquals(result.getText(), "save");
    }
    
    @Test(groups = { "navigationlist" })
    public void testNavigateAndContextMenu() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the navigationlist to be displayed
        waitForElementVisible(NAVLIST_ID);
        //Change drill mode to none
        changeDrillModeToNone();
      
        //make sute that menu is not displayed
        WebElement contextMenu = getElement("id=myMenu");
        String style = contextMenu.getAttribute("style");
        Assert.assertTrue(style.indexOf("display: none;") > -1);
        waitForMilliseconds(1000);
        //Right mouse click on "sub track3" nav list item
        WebElement listItem =
            getElement("{\"element\":\"#" + NAVLIST_ID + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" +
                       NAVLIST_ITEM_SUBTRACK3 + "\"}");
       
        listItem.click();
        Actions action = new Actions(getWebDriver());
        action.contextClick(listItem).perform();
        waitForElementVisible("id=myMenu");
        style = contextMenu.getAttribute("style");
        Assert.assertTrue(style.indexOf("display: none;") == -1);
       
       List<WebElement> menuitems = contextMenu.findElements(By.tagName("li"));
       Assert.assertEquals(menuitems.size(), 6, "There are 6 menu items in context menu");
      
        WebElement lastmenuitem = menuitems.get(menuitems.size()-1).findElement(By.tagName("a"));
        Assert.assertEquals(lastmenuitem.getText(), "subtrack3");
        
        //Click on last menu item
        action.moveToElement(lastmenuitem).click().perform();
        //lastmenuitem.click();
        
        //Check thast menu item has been clicked
        waitForMilliseconds(1000);
        WebElement result = getElement("id=results_menu");
        Assert.assertEquals(result.getText(), "subtrack3");
    }
    
   
}


