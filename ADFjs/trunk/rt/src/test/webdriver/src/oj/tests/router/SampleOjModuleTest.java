package oj.tests.router;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

import org.testng.Assert;
import static org.testng.Assert.assertTrue;


import org.testng.annotations.Test;

public class SampleOjModuleTest extends OJetBase {
    private static final String HOME_PAGE = "index.html";
    private static final String TITLE = "Oracle JET Router Sample - Home";
    private static final String HOME_CONTENT = "Welcome to the JET Router demo";

    public SampleOjModuleTest() {
        super(new TestConfigBuilder().setContextRoot("router").setAppRoot("Sample-ojModule").
            setHomePage(HOME_PAGE).setHomePageTitle(TITLE).build());
    }

    @Test (groups = { "router" })
    public void testInitialPage() throws Exception {
        startupTest();

        waitForText("id=title", HOME_CONTENT);
        checkUrl(HOME_PAGE);

        verifyElement("id=home");
    }

    public void click(String locator)
    {
        WebElement webElt = getElement(locator);

        // Click should be applied to the label for input button
        if ("input".equals(webElt.getTagName()))
        {
            try
            {
                String id = locator.substring(locator.indexOf('=') + 1);
                webElt = getElement("//label[@for='" + id + "'][@class='oj-button-label']");
            }
            catch (NoSuchElementException e)
            {
                webElt = getParentElement(webElt);
            }
        }

        webElt.click();
    }

    @Test (groups = { "router" }, dependsOnMethods = { "testInitialPage" })
    public void testDefaultOnEmptyUrl() throws Exception {
        // Start the test with an URL without index.html
        startupTest("", TITLE);

        waitForText("id=title", HOME_CONTENT);
        // Check URL is empty
        checkUrl("");

        verifyElement("id=home");

        // Since home is the default state for the router, check the Home button is selected.
        assertTrue(isSelected("id=home"), "home is not selected.");
    }

    @Test (groups = { "router" }, dependsOnMethods = { "testInitialPage" })
    public void testToolbarNavigation() throws Exception {
        startupTest();

        waitForText("id=title", HOME_CONTENT);
        checkUrl(HOME_PAGE);

        // Click on book button and verify content
        click("id=book");
        waitForText("id=preface", "Preface");
        checkUrl("book");

        // Click on tables button and verify content
        click("id=tables");
        waitForText("id=dept", "Department Table");
        checkUrl("tables");

        // Click back button, check on book
        navigateBack();
        waitForText("id=preface", "Preface");
        checkUrl("book");

        // Click back button, check on home
        navigateBack();
        waitForText("id=title", HOME_CONTENT);
        checkUrl(HOME_PAGE);
    }

    @Test (groups = { "router" }, dependsOnMethods = { "testInitialPage" })
    public void testChapterAndBack() throws Exception {
        startupTest();

        waitForText("id=title", HOME_CONTENT);
        checkUrl(HOME_PAGE);

        // Click on book button and verify content
        click("id=book");
        waitForText("id=preface", "Preface");
        checkUrl("book");

        // Click on chapter 3
        click("id=chapter3");

        // Verify we are on Chapter 3
        waitForText("id=currentPage", "Chapter 3");
        checkUrl("book/chapter3");

        // Click back button
        navigateBack();
        waitForElementNotPresent("id=currentPage");

        // Click back button again
        navigateBack();
        waitForText("id=title", HOME_CONTENT);
        checkUrl(HOME_PAGE);
    }

    @Test (groups = { "router" }, dependsOnMethods = { "testInitialPage" })
    public void testChapterEditToTableAndBack() throws Exception {
        startupTest();

        waitForText("id=title", HOME_CONTENT);
        checkUrl(HOME_PAGE);

        // Click on book button and verify content
        click("id=book");
        waitForText("id=preface", "Preface");
        checkUrl("book");

        // Click on chapter 3
        click("id=chapter2");

        // Verify we are on Chapter 2
        waitForText("id=currentPage", "Chapter 2");
        checkUrl("book/chapter2");

        // Go in edit mode
        click("id=mode");
        waitForElementVisible("id=text");
        checkUrl("book/chapter2/edit");
        String value = getElementValue("id=text");
        verifyTrue("Cannot find content of chapter 2", (value != null) && value.startsWith("More up mistaken for"));

        // Click on tables button and verify content
        click("id=tables");
        waitForText("id=dept", "Department Table");
        checkUrl("tables");

        // Click back button
        navigateBack();
        waitForElementVisible("id=text");
        checkUrl("book/chapter2/edit");
        value = getElementValue("id=text");
        verifyTrue("Cannot find content of chapter 2", (value != null) && value.startsWith("More up mistaken for"));
    }

    @Test (groups = { "router" }, dependsOnMethods = { "testInitialPage" })
    public void testEditChapter() throws Exception {
        startupTest();

        waitForText("id=title", HOME_CONTENT);
        checkUrl(HOME_PAGE);

        // Click on book button and verify content
        click("id=book");
        waitForText("id=preface", "Preface");
        checkUrl("book");

        // Click on chapter 2
        click("id=chapter2");

        // Verify we are on Chapter 2
        waitForText("id=currentPage", "Chapter 2");
        checkUrl("book/chapter2");

        // Go in edit mode
        click("id=mode");
        waitForElementVisible("id=text");
        checkUrl("book/chapter2/edit");
        String value = getElementValue("id=text");
        verifyTrue("Cannot find content of chapter 2", (value != null) && value.startsWith("More up mistaken for"));

        // Change content of textarea
        sendKeys("id=text", "This is a test.");

        // Click on chapter 1 and wait for dialog to popup
        click("id=chapter1");
        waitForElementVisible("id=exitDialog");
        // Click Ok button
        click("id=dlgOkBtn");

        // Verify we finally made it to chapter 1
        waitForText("id=currentPage", "Chapter 1");
        checkUrl("book/chapter1");

        // Click back button and check edit mode
        navigateBack();
        waitForElementVisible("id=text");
        checkUrl("book/chapter2/edit");
        value = getElementValue("id=text");
        verifyTrue("Cannot find content of chapter 2", (value != null) && value.startsWith("More up mistaken for"));

        // Change content of textarea
        sendKeys("id=text", "This is a test.");

        // Click on chapter 1 and wait for dialog to popup
        click("id=chapter1");
        waitForElementVisible("id=exitDialog");
        // Close dialog
        evalJavascript("$('#exitDialog').ojDialog('close');");
        // Check we are still on chapter
        waitForText("id=currentPage", "Chapter 2");
        checkUrl("book/chapter2/edit");

        click("id=cancel");
        value = getElementText("id=showText");
        verifyTrue("Invalid content of chapter 2", (value != null) && value.startsWith("More up mistaken for"));
    }


    @Test (groups = { "router" }, dependsOnMethods = { "testInitialPage" })
    public void testRefreshPathSegmentOneLevel() throws Exception {
        String url = "book";
        startupTest(url, TITLE);

        waitForText("id=preface", "Preface");
        checkUrl(url);

        // Click on book button and verify content
        click("id=home");
        waitForText("id=title", HOME_CONTENT);
        checkUrl("");

        // Click on tables button and verify content
        click("id=tables");
        waitForText("id=dept", "Department Table");
        checkUrl("tables");
    }

    @Test (groups = { "router" }, dependsOnMethods = { "testInitialPage" })
    public void testRefreshPathSegmentTwoLevel() throws Exception {
        String url = "book/chapter1";
        startupTest(url, TITLE);

        // Verify we landed on book/chapter1
        waitForText("id=currentPage", "Chapter 1");
        checkUrl(url);

        // Click on book button and verify content
        click("id=home");
        waitForText("id=title", HOME_CONTENT);
        checkUrl("");

        // Click on tables button and verify content
        click("id=tables");
        waitForText("id=dept", "Department Table");
        checkUrl("tables");
    }

    @Test (groups = { "router" }, dependsOnMethods = { "testInitialPage" })
    public void testBackOnIndex() throws Exception {
        startupTest();

        waitForText("id=title", HOME_CONTENT);
        checkUrl(HOME_PAGE);

        verifyElement("id=home");

        click("id=bookChap2");
        waitForText("id=currentPage", "Chapter 2");
        checkUrl("book/chapter2");

        navigateBack();

        // Verify we are back on home page
        waitForText("id=title", HOME_CONTENT);
        checkUrl(HOME_PAGE);
    }

    @Test (groups = { "router" }, dependsOnMethods = { "testInitialPage" })
    public void testRecordToTableAndBack() throws Exception {
        startupTest();

        waitForText("id=title", HOME_CONTENT);
        checkUrl(HOME_PAGE);

        // Click on tables button and verify content
        click("id=tables");
        waitForText("id=dept", "Department Table");
        checkUrl("tables");

        // Click on dept button and verify content
        click("id=dept");
        waitForElementVisible("id=dataGrid");
        checkUrl("tables/dept");
        WebElement header0 = findElements("{\"element\":\"#dataGrid\",\"subId\":\"oj-table-header\", \"index\": 0}").get(0);
        String value = header0.getText();
        log("Header col0 is " + value);
        verifyEquals("Table is not dept", "Department Id", value);

        WebElement cellElt = findElements("{\"element\":\"#dataGrid\",\"subId\":\"oj-table-cell\", \"columnIndex\": 0, \"rowIndex\": 1}").get(0);
        value = cellElt.getText();
        log("Found Element for id=dataGrid:cell(0, 1) " + value);
        verifyEquals("dataGrid(1,0) is not 20", "20", value);
        cellElt.click();

        // Verify on record with name = Research
        waitForValue("id=name", "Research");
        checkUrl("tables/dept/20");

        // Click on emp button and verify content
        click("id=emp");
        waitForElementVisible("id=dataGrid");
        checkUrl("tables/emp");
        header0 = findElements("{\"element\":\"#dataGrid\",\"subId\":\"oj-table-header\", \"index\": 0}").get(0);
        value = header0.getText();
        log("Header col0 is " + value);
        verifyEquals("Table is not emp", "Employee Id", value);

        // Click back button
        navigateBack();
        // Verify on record with name = Research
        waitForValue("id=name", "Research");
        checkUrl("tables/dept/20");
    }

    @Test (groups = { "router" }, dependsOnMethods = { "testInitialPage" })
    public void testRecordToBookAndBack() throws Exception {
        startupTest();

        waitForText("id=title", HOME_CONTENT);
        checkUrl(HOME_PAGE);

        // Click on tables button and verify content
        click("id=tables");
        waitForText("id=dept", "Department Table");
        checkUrl("tables");

        // Click on dept button and verify content
        click("id=dept");
        waitForElementVisible("id=dataGrid");
        checkUrl("tables/dept");

        WebElement header0 = findElements("{\"element\":\"#dataGrid\",\"subId\":\"oj-table-header\", \"index\": 0}").get(0);
        String value = header0.getText();
        log("Header col0 is " + value);
        verifyEquals("Table is not dept", "Department Id", value);

        // Click on row 2 of table
        WebElement cellElt = findElements("{\"element\":\"#dataGrid\",\"subId\":\"oj-table-cell\", \"columnIndex\": 0, \"rowIndex\": 1}").get(0);
        value = cellElt.getText();
        log("Found Element for id=dataGrid:cell(0, 1) " + value);
        verifyEquals("dataGrid(1,0) is not 20", "20", value);
        cellElt.click();

        // Verify on record with name = Research
        waitForValue("id=name", "Research");
        checkUrl("tables/dept/20");

        // Click on book button and verify content
        click("id=book");
        waitForText("id=preface", "Preface");
        checkUrl("book");

        // Click back button
        navigateBack();
        // Verify on record with name = Research
        waitForValue("id=name", "Research");
        checkUrl("tables/dept/20");
    }

    /**
     * Check the current browser url end with the correct value passed in parameter.
     */
    private void checkUrl(String segment)
    {
        String url = getBrowserUrl();
        if (url == null)
        {
            url = "<null>";
        }
        log("Browser URL is: '" + url + "'.");

        verifyEquals("URL is incorrect, '" + url + "' ",
                     url, "http://localhost:7101/router/Sample-ojModule/" + segment);
    }

    private void log(String log)
    {
        getLogger().fine("[SampleOjModuleTest] " + log);
    }
}
