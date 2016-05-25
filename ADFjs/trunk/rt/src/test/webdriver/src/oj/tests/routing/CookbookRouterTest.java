package oj.tests.routing;

import oj.tests.common.TestCompBase;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;


public class CookbookRouterTest extends OJetBase {

    private static final String TITLE_ROUTER_SIMPLE = "Router - Simple";
    private static final String TITLE_ROUTER_QUERYPARAM = "Router - Using Query Parameters";
    private static final String TITLE_ROUTER_TABS = "Router - Tabs";
    private static final String TITLE_ROUTER_CHILD = "Router - Child Router";
    private static final String TITLE_ROUTER_NESTED = "Router - ojModule with nested routers";
    private static final String TITLE_ROUTER_ARBITRARY = "Router - Arbitrary number of states";
    private static final String TITLE_ROUTER_STORAGE = "Router - Bookmarkable Storage";


    private static final String PAGE_CONTENT = "pageContent";
    private static final String HOME_CONTENT = "Welcome to the JET Router demo";
    private static final String CURRENT_PAGE = "currentPage";
    private static final String PREFACE_CONTENT = "This is the Preface.";
    private static final String PREFACE_CONTENT1 = "Preface";
    private static final String CHAP_TEMP = "This is Chapter ";
    private static final String CHAPTER1_CONTENT = CHAP_TEMP + "1.";
    private static final String CHAPTER2_CONTENT = CHAP_TEMP + "2.";
    private static final String CHAPTER3_CONTENT = CHAP_TEMP + "3.";
    
    private static final String CHAPTER1_CONTENT1 = "Chapter 1";
    private static final String CHAPTER2_CONTENT1 = "Chapter 2";
    private static final String CHAPTER3_CONTENT1 = "Chapter 3";

    public CookbookRouterTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook" })
    public void testRouterSimple() throws Exception {
        startupTest("demo-router-simple.html", null);

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_ROUTER_SIMPLE);

        waitForElementVisible("id=pref");
        getElement("id=pref").sendKeys(Keys.RETURN);
        waitForMilliseconds(1000L);
        checkUrl("demo-router-simple.html");
        checkPageContent(PAGE_CONTENT,PREFACE_CONTENT);

        waitForElementVisible("id=chap1");
        getElement("id=chap1").sendKeys(Keys.RETURN);
        waitForMilliseconds(1000L);
        checkUrl("chap1");
        checkPageContent(PAGE_CONTENT,CHAPTER1_CONTENT);

        waitForElementVisible("id=chap2");
        getElement("id=chap2").sendKeys(Keys.RETURN);
        waitForMilliseconds(1000L);
        checkUrl("chap2");
        checkPageContent(PAGE_CONTENT,CHAPTER2_CONTENT);

        waitForElementVisible("id=chap3");
        getElement("id=chap3").sendKeys(Keys.RETURN);
        waitForMilliseconds(1000L);
        checkUrl("chap3");
        checkPageContent(PAGE_CONTENT,CHAPTER3_CONTENT);

    }

    @Test(groups = { "cookbook" })
    public void testRouterQueryParameter() throws Exception {
        startupTest("demo-router-params.html", null);

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_ROUTER_QUERYPARAM);

        waitForElementVisible("id=preface");
        getElement("id=preface").sendKeys(Keys.RETURN);
        waitForMilliseconds(1000L);
        checkUrl("demo-router-params.html");
        checkPageContent(PAGE_CONTENT,PREFACE_CONTENT);

        waitForElementVisible("id=chapter1");
        getElement("id=chapter1").sendKeys(Keys.RETURN);
        waitForMilliseconds(1000L);
        checkUrl("demo-router-params.html?root=chapter1");
        checkPageContent(PAGE_CONTENT,CHAPTER1_CONTENT);

        waitForElementVisible("id=chapter2");
        getElement("id=chapter2").sendKeys(Keys.RETURN);
        waitForMilliseconds(1000L);
        checkUrl("demo-router-params.html?root=chapter2");
        checkPageContent(PAGE_CONTENT,CHAPTER2_CONTENT);

        waitForElementVisible("id=chapter3");
        getElement("id=chapter3").sendKeys(Keys.RETURN);
        waitForMilliseconds(1000L);
        checkUrl("demo-router-params.html?root=chapter3");
        checkPageContent(PAGE_CONTENT,CHAPTER3_CONTENT);

    }

    @Test(groups = { "cookbook" })
    public void testRouterTabs() throws Exception {
        startupTest("demo-router-tabs.html", null);

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_ROUTER_TABS);

        waitForElementVisible("id=tabs");
        getElement("{\"element\":\"#tabs\",\"index\":0,\"subId\":\"oj-tabs-tab\"}").click();
        waitForMilliseconds(1000L);
        checkUrl("demo-router-tabs.html");
        checkPageContent("preface", "...Preface");
        
        waitForElementVisible("id=tabs");
        getElement("{\"element\":\"#tabs\",\"index\":1,\"subId\":\"oj-tabs-tab\"}").click();
        waitForMilliseconds(1000L);
        checkUrl("demo-router-tabs.html?root=chapter1");
        checkPageContent("chapter1", "..." + CHAPTER1_CONTENT);

        waitForElementVisible("id=tabs");
        getElement("{\"element\":\"#tabs\",\"index\":2,\"subId\":\"oj-tabs-tab\"}").click();
        waitForMilliseconds(1000L);
        checkUrl("demo-router-tabs.html?root=chapter2");
        checkPageContent("chapter2", "..." + CHAPTER2_CONTENT);

        waitForElementVisible("id=tabs");
        getElement("{\"element\":\"#tabs\",\"index\":3,\"subId\":\"oj-tabs-tab\"}").click();
        waitForMilliseconds(1000L);
        checkUrl("demo-router-tabs.html?root=chapter3");
        checkPageContent("chapter3", "..." + CHAPTER3_CONTENT);


    }

    @Test(groups = { "cookbook" })
    public void testRouterChildRouting() throws Exception {
        startupTest("demo-router-child.html", null);

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_ROUTER_CHILD);
        WebElement element = null;

        //Square
        waitForElementVisible("id=square");
        getElement("id=square").sendKeys(Keys.RETURN);
        waitForMilliseconds(1000L);
        Assert.assertTrue(checkIfDisplayed(By.className("square")));
        checkUrl("demo-router-child.html");

        waitForElementVisible("id=red");
        click("id=red");
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("square"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("red"));
        checkUrl("demo-router-child.html");

        waitForElementVisible("id=blue");
        click("id=blue");
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("square"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("blue"));
        checkUrl("demo-router-child.html?root=square&color=blue");

        waitForElementVisible("id=green");
        click("id=green");
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("square"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("green"));
        checkUrl("demo-router-child.html?root=square&color=green");


        //Circle
        waitForElementVisible("id=circle");
        getElement("id=circle").sendKeys(Keys.RETURN);
        waitForMilliseconds(1000L);
        Assert.assertTrue(checkIfDisplayed(By.className("circle")));
        checkUrl("demo-router-child.html?root=circle");

        waitForElementVisible("id=red");
        click("id=red");
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("circle"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("red"));
        checkUrl("demo-router-child.html?root=circle");

        waitForElementVisible("id=blue");
        click("id=blue");
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("circle"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("blue"));
        checkUrl("demo-router-child.html?root=circle&color=blue");

        waitForElementVisible("id=green");
        click("id=green");
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("circle"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("green"));
        checkUrl("demo-router-child.html?root=circle&color=green");

        //Oval
        waitForElementVisible("id=oval");
        getElement("id=oval").sendKeys(Keys.RETURN);
        waitForMilliseconds(1000L);
        Assert.assertTrue(checkIfDisplayed(By.className("oval")));
        checkUrl("demo-router-child.html?root=oval");

        waitForElementVisible("id=red");
        click("id=red");
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("oval"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("red"));
        checkUrl("demo-router-child.html?root=oval");

        waitForElementVisible("id=blue");
        click("id=blue");
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("oval"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("blue"));
        checkUrl("demo-router-child.html?root=oval&color=blue");

        waitForElementVisible("id=green");
        click("id=green");
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("oval"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("green"));
        checkUrl("demo-router-child.html?root=oval&color=green");

    }

    @Test(groups = { "cookbook" })
    public void testRouterNestedRouting() throws Exception {
        startupTest("demo-router-views.html", null);

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_ROUTER_NESTED);

        //Home
        waitForElementVisible("id=home");
        getElement("id=home").sendKeys(Keys.RETURN);
        waitForMilliseconds(1000L);
        checkPageContent("content", HOME_CONTENT);
        Assert.assertTrue(getWebDriver().findElement(By.className("demo-page-content-area")).getText().contains(HOME_CONTENT));
        checkUrl("demo-router-views.html");

        //Book
        waitForElementVisible("id=book");
        getElement("id=book").sendKeys(Keys.RETURN);
        waitForMilliseconds(1000L);
        checkUrl("demo-router-views.html?root=book");

        waitForElementVisible("id=preface");
        click("id=preface");
        waitForMilliseconds(1000L);
        checkPageContent(CURRENT_PAGE, PREFACE_CONTENT1);
        checkUrl("demo-router-views.html?root=book&chapter=preface");

        waitForElementVisible("id=chapter1");
        click("id=chapter1");        
        waitForElementVisible(CURRENT_PAGE);
        checkPageContent(CURRENT_PAGE, CHAPTER1_CONTENT1);
        checkUrl("demo-router-views.html?root=book&chapter=chapter1");

        waitForElementVisible("id=chapter2");
        click("id=chapter2");
        waitForElementVisible(CURRENT_PAGE);
        checkPageContent(CURRENT_PAGE, CHAPTER2_CONTENT1);
        checkUrl("demo-router-views.html?root=book&chapter=chapter2");

        waitForElementVisible("id=chapter3");
        click("id=chapter3");
        waitForElementVisible(CURRENT_PAGE);
        checkPageContent(CURRENT_PAGE, CHAPTER3_CONTENT1);
        checkUrl("demo-router-views.html?root=book&chapter=chapter3");

        //Table
        waitForElementVisible("id=table");
        getElement("id=table").sendKeys(Keys.RETURN);
        waitForMilliseconds(1000L);
        checkUrl("demo-router-views.html?root=table");

        waitForElementVisible("id=dept");
        click("id=dept");
        waitForElementVisible("id=dataGrid");
        Assert.assertTrue(isDisplayed("id=dataGrid"));
        checkUrl("demo-router-views.html?root=table&table=dept");

        waitForElementVisible("id=emp");
        click("id=emp");
        waitForElementVisible("id=dataGrid");
        Assert.assertTrue(isDisplayed("id=dataGrid"));
        checkUrl("demo-router-views.html?root=table&table=emp");
    }
    
    @Test(groups = { "cookbook" })
    public void testRouterNestedRoutingCancelNavigation() throws Exception {
        startupTest("demo-router-views.html", null);

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_ROUTER_NESTED);

        //Book
        waitForElementVisible("id=book");
        getElement("id=book").sendKeys(Keys.RETURN);
        waitForMilliseconds(1000L);
        checkUrl("demo-router-views.html?root=book");
        
        waitForElementVisible("id=chapter1");
        click("id=chapter1");        
        waitForElementVisible(CURRENT_PAGE);
        checkPageContent(CURRENT_PAGE, CHAPTER1_CONTENT1);
        checkUrl("demo-router-views.html?root=book&chapter=chapter1");
        
        //modify the text
        waitForElementVisible("text");
        getElement("text").sendKeys("test");        
        
        //select any other Chapter in the navigation list to get dialog window and click ok
        click("id=chapter2");
        waitForElementVisible("exitDialog");
        Assert.assertTrue(isDisplayed("exitDialog"));
        click("dlgOkBtn");
        
        //verify the changes are discarded and 2nd chapter is displayed                
        waitForElementVisible(CURRENT_PAGE);
        checkPageContent(CURRENT_PAGE, CHAPTER2_CONTENT1);
        checkUrl("demo-router-views.html?root=book&chapter=chapter2");

        //go back to chapter 1 and verify dialog cancel
        click("id=chapter1");        
        waitForElementVisible(CURRENT_PAGE);
        checkPageContent(CURRENT_PAGE, CHAPTER1_CONTENT1);
        checkUrl("demo-router-views.html?root=book&chapter=chapter1");
        
        //modify the text
        waitForElementVisible("text");
        getElement("text").sendKeys("test");        
        
        //select any other Chapter in the navigation list to get dialog window
        click("id=chapter2");
        waitForElementVisible("exitDialog");
        Assert.assertTrue(isDisplayed("exitDialog"));
        
        //Close the dialog by clicking on its close icon
        getElement("{\"element\":\"#exitDialog\",\"subId\":\"oj-dialog-close-icon\"}").click();
        
        //verify the transition is canceled and nothing changes on the page               
        waitForElementVisible(CURRENT_PAGE);
        checkPageContent(CURRENT_PAGE, CHAPTER1_CONTENT1);
        checkUrl("demo-router-views.html?root=book&chapter=chapter1");
        
        //click save
        click("id=save");
        waitForMilliseconds(3000L);
        
        //changes are saved and no dialog box                
        waitForElementVisible("id=chapter2");
        click("id=chapter2");        
        waitForElementVisible(CURRENT_PAGE);
        checkPageContent(CURRENT_PAGE, CHAPTER2_CONTENT1);
        checkUrl("demo-router-views.html?root=book&chapter=chapter2");
        
    }

    @Test(groups = { "cookbook" })
    public void testRouterArbitraryStates() throws Exception {
        startupTest("demo-router-many.html", null);

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_ROUTER_ARBITRARY);
        waitForElementVisible("id=menuButton");

        clickAndSelectMenuButtonOption("id=menuButton", "id=ui-id-10");
        waitForElementVisible("id=name");
        Assert.assertEquals("Ward", getTextBoxValue("name", "oj-inputtext-input"));
        waitForElementVisible("id=job");
        Assert.assertEquals("Salesman", getTextBoxValue("job", "oj-inputtext-input"));
        waitForElementVisible("id=sal");
        Assert.assertEquals("1250", getTextBoxValue("sal", "oj-inputtext-input"));
        checkUrl("7521");
        waitForMilliseconds(2000L);

        clickAndSelectMenuButtonOption("id=menuButton", "id=ui-id-9");
        waitForElementVisible("id=name");
        Assert.assertEquals("Allen", getTextBoxValue("name", "oj-inputtext-input"));
        waitForElementVisible("id=job");
        Assert.assertEquals("Salesman", getTextBoxValue("job", "oj-inputtext-input"));
        waitForElementVisible("id=sal");
        Assert.assertEquals("1600", getTextBoxValue("sal", "oj-inputtext-input"));
        checkUrl("7499");
    }

    @Test(groups = { "cookbook" })
    public void testRouterBookmarkableStorage() throws Exception {
        startupTest("demo-router-bookmark.html", null);

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);
        String[] row1 = { "7369", "Smith", "Clerk", "800", "20" };
        String[] row2 = { "7499", "Allen", "Salesman", "1600", "30" };
        String[] row3 = { "7521", "Ward", "Salesman", "1250", "30" };
        String[] row4 = { "7566", "Jones", "Manager", "2975", "20" };
        String[] row5 = { "7654", "Martin", "Salesman", "1250", "30" };
        String[] row6 = { "7698", "Blake", "Manager", "2850", "30" };
        String[] row7 = { "7782", "Clark", "Manager", "2450", "10" };
        String[] row8 = { "7788", "Scott", "Analyst", "3000", "20" };
        String[] row9 = { "7839", "King", "President", "5000", "10" };


        //Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_ROUTER_STORAGE);
        waitForElementVisible("id=table");
        
        //sort table
        String menuLocator = "id=ui-id-8";
        String subMenuLocator = "id=ui-id-10";
        String elemLocator = "{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"" + 1 + "\"}";        
        /*
        rightClickAndSelectSubMenuOption(elemLocator, menuLocator, subMenuLocator);        
        waitForMilliseconds(5000L);
        click("id=save");        
        checkUrl("demo-router-bookmark.html?oj_Router=1N4IgTg9hAuIFygNYFMCe8QEsAmIA0I2mYyAxtJhAHYbbIDOpyVRVA5iAL6dA");
        
        verifyTableSortOrder(row1, row2, row3, row4, row5, row6, row7, row8, row9);
*/

    }
    
    @Test(groups = { "cookbook" })
    public void testRouterGoFeature() throws Exception {
        startupTest("demo-router-child.html", null);

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_ROUTER_CHILD);
        waitForElementVisible("id=menuButton");
        WebElement element=null;

        //Square - Red
        mouseClickAndSelectSubMenuOption("id=menuButton", "id=ui-id-8", "id=ui-id-11");                
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("square"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("red"));
        checkUrl("demo-router-child.html");

        //Square - Blue
        mouseClickAndSelectSubMenuOption("id=menuButton", "id=ui-id-8", "id=ui-id-12");                
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("square"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("blue"));
        checkUrl("demo-router-child.html?root=square&color=blue");

        //Square - Green
        mouseClickAndSelectSubMenuOption("id=menuButton", "id=ui-id-8", "id=ui-id-13");                
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("square"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("green"));
        checkUrl("demo-router-child.html?root=square&color=green");

        //Circle - Red
        mouseClickAndSelectSubMenuOption("id=menuButton", "id=ui-id-9", "id=ui-id-14");                        
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("circle"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("red"));
        checkUrl("demo-router-child.html?root=circle");

        //Circle - Blue
        mouseClickAndSelectSubMenuOption("id=menuButton", "id=ui-id-9", "id=ui-id-15");                        
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("circle"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("blue"));
        checkUrl("demo-router-child.html?root=circle&color=blue");

        //Circle - Green
        mouseClickAndSelectSubMenuOption("id=menuButton", "id=ui-id-9", "id=ui-id-16");                        
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("circle"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("green"));
        checkUrl("demo-router-child.html?root=circle&color=green");

        //Oval - Red
        mouseClickAndSelectSubMenuOption("id=menuButton", "id=ui-id-10", "id=ui-id-17");                        
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("oval"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("red"));
        checkUrl("demo-router-child.html?root=oval");

        //Oval - Blue
        mouseClickAndSelectSubMenuOption("id=menuButton", "id=ui-id-10", "id=ui-id-18");                        
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("oval"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("blue"));
        checkUrl("demo-router-child.html?root=oval&color=blue");

        //Oval - Red
        mouseClickAndSelectSubMenuOption("id=menuButton", "id=ui-id-10", "id=ui-id-19");                        
        waitForMilliseconds(1000L);
        element = getWebDriver().findElement(By.className("oval"));
        Assert.assertTrue(element.isDisplayed());
        Assert.assertTrue(element.getAttribute("style").contains("green"));
        checkUrl("demo-router-child.html?root=oval&color=green");
    }

    private void checkPageContent(String locator, String expectedContent) {
        String content = getText(locator);
        waitForText(locator, expectedContent);
    }


    private void log(String log) {        
        getLogger().fine("[CookbookRouterTest] " + log);
    }

    /**
     * Check the current browser url end with the correct value passed in
     * parameter.
     */
    private void checkUrl(String chapter) {
        String url = getBrowserUrl();
        if (url == null) {
            url = "<null>";
        }
        log("Browser URL is: '" + url + "'.");        

        // Check the last path segment
        String segment = url.substring(url.lastIndexOf('/') + 1);
        verifyEquals("URL is incorrect, '" + url + "' ", chapter, segment);
    }

    public void clickAndSelectMenuButtonOption(String elementLocator,
                                               String menuActionLocator) throws WebDriverException {

        /*
        WebElement elem = getElement(elementLocator);
        Actions menuClick = new Actions(getWebDriver());
        menuClick.moveToElement(elem);
        menuClick.click(elem);

        waitForMilliseconds(3000L);

        menuClick.perform();

        waitForElementVisible(menuActionLocator);

        //Click on menu option
        WebElement menuOpt = getElement(menuActionLocator);
        menuOpt.click();*/

        click(elementLocator);

        //Move mouse over to user selected menu item
        WebElement userSelection = getElement(menuActionLocator);
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(userSelection).click().perform();

    }
    
    public void mouseClickAndSelectSubMenuOption(String elementLocator, String menuLocator,
                                                 String subMenuLocator) throws WebDriverException {


        WebElement elem = getElement(elementLocator);
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(elem).click().build().perform();

        waitForElementVisible(menuLocator);
        WebElement menuFirst = getElement(menuLocator);
        log("menuFirst" + menuFirst);
        actions.moveToElement(menuFirst).click().build().perform();
        //actions.build().perform();

        //Click on menu option  Sort Ascending
        waitForElementVisible(subMenuLocator);
        WebElement menuOpt = getElement(subMenuLocator);

        WebDriverWait wdw = new WebDriverWait(getWebDriver(), 100);
        wdw.until(ExpectedConditions.elementToBeClickable(menuOpt));

        actions.moveToElement(menuOpt).build().perform();

        log("menuOpt****" + menuOpt);
        menuOpt.click();

        //Need to wait
        waitForMilliseconds(5000L);

    }

    public void sortTable(int index) {
        String ascendLocator =
            "{\"element\":\"#table\",\"subId\":\"oj-table-sort-ascending\",\"index\":\"" + index + "\"}";
        String descendLocator =
            "{\"element\":\"#table\",\"subId\":\"oj-table-sort-descending\",\"index\":\"" + index + "\"}";

        Actions actions = new Actions(getWebDriver());
        WebElement sortIcon = getElement(descendLocator);
        actions.moveToElement(sortIcon);
        actions.click();
        actions.perform();
    }

    public void verifyTableSortOrder(String[]... rows) {
        //WebElement table = getElement(locator);
        //log("###### table" + table);
        int i = 0;
        int j = 0;
        for (String[] row : rows) {
            for (String cell : row) {
                WebElement rowNCol0 =
                    getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"" + row +
                               "\",\"columnIndex\":\"" + j + "\"}");
                Assert.assertEquals(rowNCol0.getText(), cell);
                j++;
            }
            i++;
        }
    }

    public String getTextBoxValue(String locator, String subId) {
        WebElement inputField = getElement("{\"element\":\"#" + locator + "\",\"subId\":\"" + subId + "\"}");
        return inputField.getAttribute("value");
    }

}
