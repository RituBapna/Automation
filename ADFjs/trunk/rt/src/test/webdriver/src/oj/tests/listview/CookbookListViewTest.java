package oj.tests.listview;

import java.io.File;

import java.util.List;
import java.util.NoSuchElementException;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//D:\OJET\ADFjs\trunk\rt\src\test\testUtils\src\oracle\ojet\automation\test

public class CookbookListViewTest extends ListViewBase {
    public CookbookListViewTest() {
        super();
    }

    @Test(groups = { "cookbook" })
    public void staticListViewTest() throws Exception {
        startupTest("demo-listView-staticListView.html", null);
        verifyTitle("Incorrect page title;","List View - Static Content");
        waitForElementVisible("id=listview");
       /* try{
                   //take screenshot and save it in a file
                   File screenshot = ((TakesScreenshot)getWebDriver()).getScreenshotAs(OutputType.FILE);

                   //copy the file to the required path FileUtils.copyFile(screenshot,new File("screenshot.jpg"));
                    FileUtils.copyFile(screenshot,new File("D:\\OJET\\ADFjs\\trunk\\screenshot\\screenshot.png"));
                    System.out.println("+++++++++++++screenshot is  taken +++++++++++++++");
                    Thread.sleep(4000);
                   //driver.quit();

       }catch(Exception e){
                   //if it fails to take screenshot then this block will execute
                   System.out.println("Failure to take screenshot "+e);

               }*/
      
        WebElement listViewDiv=getElement("id=listview");
        String str=listViewDiv.getText();
        Assert.assertTrue(str.length()>0);
        Assert.assertEquals("multiple", evalJavascript("return $('#listview').ojListView('option', 'selectionMode')"));
        
    }
    @Test(groups = { "cookbook" })
    public void staticHierListViewTest() throws Exception {
        startupTest("demo-listView-staticHierListView.html", null);
        verifyTitle("Incorrect page title;","List View - Hierarchical Static Content");
        waitForElementVisible("id=listview");
        WebElement listViewDiv=getElement("id=listview");
        WebElement expandCollapseIcon=null;
        
        WebElement ee=getElement("{\"element\":\"#listview\",\"subId\":\"oj-listview-disclosure\",\"key\":\"a\"}");
        
        System.out.println("++++++++++++++ee"+ee);
        getElement("{\"element\":\"#listview\",\"subId\":\"oj-listview-disclosure\",\"key\":\"a\"}").click();
         waitForMilliseconds(500L);
        
        getElement("{\"element\":\"#listview\",\"subId\":\"oj-listview-disclosure\",\"key\":\"a\"}").click();
         waitForMilliseconds(500L);
        Assert.assertEquals("[a, b, c]", evalJavascript("return $('#listview').ojListView('option', 'expanded')"));
        Assert.assertEquals("multiple", evalJavascript("return $('#listview').ojListView('option', 'selectionMode')"));
        
        isEqual("oj-listview-item-element oj-expanded","a");
        
        expandCollapseIcon=getCollapseExpandIcon("listview","oj-listview-icon","a");
        
        /*WebDriver driver = getWebDriver();
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).click(getElement("id=amybartlet")).keyUp(Keys.CONTROL).perform();
        action.keyDown(Keys.CONTROL).click(getElement("id=andyjones")).keyUp(Keys.CONTROL).perform();*/
        
        clickWithHoldKey("id=amybartlet", "CONTROL");
        clickWithHoldKey("id=andyjones", "CONTROL");
        waitForMilliseconds(2000L);
        isTrue("amybartlet","oj-selected");
        isTrue("andyjones","oj-selected");
        expandCollapseIcon.click();//collapses node a
        
        getCollapseExpandIcon("listview","oj-listview-icon","b").click();
        waitForMilliseconds(2000L);
        
        expandCollapseIcon=getCollapseExpandIcon("listview","oj-listview-icon","c");
        expandCollapseIcon.click();
        waitForMilliseconds(2000L);
    
        isEqual("oj-listview-item-element oj-collapsed","a");
        
        getCollapseExpandIcon("listview","oj-listview-icon","a").click();
        waitForMilliseconds(2000L);
        getCollapseExpandIcon("listview","oj-listview-icon","b").click();
        waitForMilliseconds(2000L);
        getCollapseExpandIcon("listview","oj-listview-icon","c").click();
        waitForMilliseconds(2000L);
        
        // WebElement expandCollapseIcon = getElement("{\"element\":\"#listview\",\"subId\":\"oj-listview-icon\",\"key\":\"a\"}");
        //Assert.assertEquals("oj-listview-item-element oj-collapsed",getElement("id=a").getAttribute("class"));
    }
     @Test(groups = { "cookbook" })
    public void arrayListViewTest() throws Exception {
        startupTest("demo-listView-arrayListView.html", null);
        verifyTitle("Incorrect page title;","List View - Using Array");
        waitForElementVisible("id=listview");
        WebElement listViewDiv=getElement("id=listview");
        String str=listViewDiv.getText();
        
        Assert.assertTrue(str.length()>0);
        Assert.assertEquals("single", evalJavascript("return $('#listview').ojListView('option', 'selectionMode')"));
        
        //evalJavascript("return $('#listview').ojListView('option', 'item'));
        //format of data is : {template=server_template, renderer={}, selectable=true, focusable=true}
        
        Assert.assertTrue(Boolean.parseBoolean(evalJavascript("return $('#listview').ojListView('option', 'item').focusable")));
        Assert.assertTrue(Boolean.parseBoolean(evalJavascript("return $('#listview').ojListView('option', 'item').selectable")));
        
        //rightClickAndSelectMenuOption("Settings","action1");
        waitForMilliseconds(3000L);
       /* WebElement settingId = getElement("id=Settings");
        Actions action = new Actions(getWebDriver());
        action.moveToElement(settingId);
        action.contextClick(settingId);
        waitForMilliseconds(3000L);
        action.build().perform();
        
        waitForElementVisible("id=action1");
        WebElement action1 = getElement("id=action1");
        action1.click();
        */
        //item:{template: 'server_template',focusable:false,renderer: function(itemContext){return itemContext['data'].get('name');}}
        // right click event:: action.contextClick(contextMenu).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        //action.keyDown(Keys.CONTROL).click(getElement("id=amybartlet")).keyUp(Keys.CONTROL).perform();
        //action.keyDown(Keys.CONTROL).click(getElement("id=andyjones")).keyUp(Keys.CONTROL).perform();
        
    }
    @Test(groups = { "cookbook" })
    public void collectionListViewTest() throws Exception {
        startupTest("demo-listView-collectionListView.html", null);
        verifyTitle("Incorrect page title;","List View - Using oj.Collection");
        waitForElementVisible("id=listview");
        WebElement listViewDiv=getElement("id=listview");
        
        String str=listViewDiv.getText();
        System.out.println("++++++++Ritu"+str);
       // Assert.assertTrue(str.length()>0);
        
        Assert.assertEquals("single", evalJavascript("return $('#listview').ojListView('option', 'selectionMode')"));
        Assert.assertEquals("loadMoreOnScroll",evalJavascript("return $('#listview').ojListView('option','scrollPolicy')"));
        Assert.assertEquals("15",evalJavascript("return $('#listview').ojListView('option','scrollPolicyOptions').fetchSize"));
        
        Assert.assertEquals("width:100%",evalJavascript("return $('#listview').ojListView('option','rootAttributes').style").split(";")[0]);
        Assert.assertEquals("height:300px",evalJavascript("return $('#listview').ojListView('option','rootAttributes').style").split(";")[1]);
        Assert.assertEquals("overflow-x:hidden",evalJavascript("return $('#listview').ojListView('option','rootAttributes').style").split(";")[2]);
        
        System.out.println("++++++++Ritu"+evalJavascript("return $('#listview').ojListView('option','rootAttributes').style").split(";")[0]);//
        waitForElementVisible("id=Oracle Retail2015-03-04 2:40am");
        WebElement oracle=getElement("id=Oracle Retail2015-03-04 2:40am");
        oracle.click();
        waitForMilliseconds(3000L);
        
    }
    @Test(groups = { "cookbook" })
    public void jsonHierListViewTest() throws Exception {
        startupTest("demo-listView-jsonHierListView.html", null);
        verifyTitle("Incorrect page title;","List View - Using Hierarchical JSON Data");
        waitForElementVisible("id=listview");
        WebElement listViewDiv=getElement("id=listview");
        
        String str=listViewDiv.getText();
        System.out.println("=========================================="+getElement("id=folders").getText().length());
        Assert.assertTrue(str.length()>0);
        
        /*Assert.assertEquals("single", evalJavascript("return $('#listview').ojListView('option', 'selectionMode')"));
        Assert.assertEquals("loadMoreOnScroll",evalJavascript("return $('#listview').ojListView('option','scrollPolicy')"));
        Assert.assertEquals("10",evalJavascript("return $('#listview').ojListView('option','scrollPolicyOptions').fetchSize"));
        
        Assert.assertEquals("width:100%",evalJavascript("return $('#listview').ojListView('option','rootAttributes').style").split(";")[0]);
        Assert.assertEquals("height:300px",evalJavascript("return $('#listview').ojListView('option','rootAttributes').style").split(";")[1]);
        Assert.assertEquals("overflow-x:hidden",evalJavascript("return $('#listview').ojListView('option','rootAttributes').style").split(";")[2]);
        
        System.out.println("++++++++Attribute"+evalJavascript("return $('#listview').ojListView('option','rootAttributes').style").split(";")[0]);//
        waitForElementVisible("id=Oracle Retail2015-03-04 2:40am");
        WebElement oracle=getElement("id=Oracle Retail2015-03-04 2:40am");
        oracle.click();*/
        evalJavascript("return $('#listview').ojListView('option', 'drillMode', 'collapsible')");
        
        waitForMilliseconds(500L);
        getCollapseExpandIcon("listview","oj-listview-icon","folders").click();
        waitForMilliseconds(500L);
        getCollapseExpandIcon("listview","oj-listview-icon","files").click();
        waitForMilliseconds(500L);
        
    }
    @Test(groups = { "cookbook" })
    public void observableArrayListViewTest() throws Exception {
        startupTest("demo-listView-observableArrayListView.html", null);
        verifyTitle("Incorrect page title;","List View - Using Observable Array");
        waitForElementVisible("id=listViewContainer");
        waitForElementVisible("id=listview");
        waitForElementVisible("id=inputItem");
        
        String[] lVArray = {"Milk","Flour","Sugar","Vanilla Extract"};
        WebElement LVDiv = getElement("id=listview");
        
        LVContentCheck(LVDiv,lVArray);

        WebElement input =getElement("id=inputItem");
        input.sendKeys("Tea");
        getElement("id=addButton").click();
        
        String[] lVArrayAfterAdd = {"Milk","Flour","Sugar","Vanilla Extract","Tea"};
        LVContentCheck(LVDiv,lVArrayAfterAdd);
        
        //int index=1;
        //getWebDriver().FindElement(By.CssSelector("#listview li:nth-child(" + i + ")"));
        //By locator = By.cssSelector("#listview li:nth-child(" + index + ")");
        //getWebDriver().findElement(By.xpath("//li[1]/span")).click();
        
        getElement("id=ui-id-13").click();
        waitForMilliseconds(1000L);
        getElement("id=removeButton").click();
        waitForMilliseconds(1000L);
        
        String[] lVArrayAfterRemove = {"Flour","Sugar","Vanilla Extract","Tea"};
        LVContentCheck(LVDiv,lVArrayAfterRemove);
        waitForMilliseconds(1000L);
    }
    
    @Test(groups = { "cookbook" })
    public void filterSortListViewTest() throws Exception {
        startupTest("demo-listView-filterSortListView.html", null);
        verifyTitle("Incorrect page title;","List View - External Sort and Filter Controls");
        waitForElementVisible("id=root");
        waitForElementVisible("id=ojChoiceId_sortBy");
        
        getElement("id=ojChoiceId_sortBy").click();
        
        Assert.assertTrue(getElement("id=listview").getText().contains("Java: A Beginner's Guide"));
        
        waitForMilliseconds(500L);
        
        waitForElementVisible("id=paging");
        WebElement nextPage = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-next\"}");
        nextPage.click();
        Assert.assertTrue(getElement("id=listview").getText().contains("Java Programming in a Multicore World"));
        
        getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-next\"}").click();
        Assert.assertTrue(getElement("id=listview").getText().contains("Oracle Fusion Middleware 11g Architecture and Management"));
        //System.out.println(getElement("id=listview").getText());
        
        getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-last\"}").click();
        Assert.assertTrue(getElement("id=listview").getText().contains("Oracle Fusion Developer Guide:"));
        //waitForMilliseconds(5000L);
        
        /*
        Refined by price 
        selecting among different check options 
        Based ob selection data get refined for listview
        */
        
        /*
        When first option is selected
        no data is displayed
        */
        
        waitForElementVisible("id=price_filter");
        getElement("{\"element\":\"#price_filter\",\"index\":1,\"subId\":\"oj-checkboxset-inputs\"}").click();
        Assert.assertTrue(getElement("id=listview").getText().contains("No items to display."));
        //System.out.println("+++++++++++++No Data++++++++++++"+getElement("id=listview").getText());
        waitForMilliseconds(1000L);
        
        /*
        First uncheck option one
        When second option is selected
        Data which falls between $30 - $39.99 is displayed
        */
        getElement("id=price_opt1").click();
        getElement("id=price_opt2").click();
        
        getWebDriver().findElement(By.className("denominator")).getText();
        //Assert.assertTrue(getElement("id=listview").getText().contains("No items to display."));
        Assert.assertEquals("$35.00", getWebDriver().findElement(By.className("denominator")).getText());
        waitForMilliseconds(1000L);
        getElement("id=price_opt2").click();
        
        
        /*
        Refined by author 
        refined based on selected author name 
        */
        
        waitForElementVisible("id=author_filter");
        getElement("id=dcoward").click();
        Assert.assertTrue(getElement("id=listview").getText().contains("Danny Coward"));
        //System.out.println("+++++++++++++No Data++++++++++++"+getElement("id=listview").getText());
        waitForMilliseconds(1000L);
        
    }
           
}
