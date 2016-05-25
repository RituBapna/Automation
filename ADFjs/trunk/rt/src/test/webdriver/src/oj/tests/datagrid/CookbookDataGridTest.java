package oj.tests.datagrid;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class CookbookDataGridTest extends OJetBase {

    private static final String TITLE_CELL_BASED = "Data Grid - Cell Based";
    private static final String TITLE_ROW_BASED = "Data Grid - Row Based";
    private static final String TITLE_CELL_CUSTOMIZATION = "Data Grid - Cell Customization";
    private static final String TITLE_CHART_STAMPING = "Data Grid - Chart Stamping";
    private static final String TITLE_CUSTOM_RENDERER = "Data Grid - Custom Renderer";
    private static final String TITLE_CUSTOM_CONTEXT_MENU = "Data Grid - Custom Context Menu";
    private static final String TITLE_CUSTOM_DATASOURCE = "Data Grid - Custom Data Source (Advanced)";
    private static final String TITLE_PAGING = "Paging Control - Paging Data Grid";

    public CookbookDataGridTest() {
        super(new TestConfigBuilder().setContextRoot("components").setAppRoot("public_html").build());
    }

    @Test(groups = {"cookbook"})
    public void testCellBasedDataGrid() throws Exception {
        //Start the test and bring up the browser
        startupTest("uiComponents-dataGrid-cellBasedGrid.html", null);

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_CELL_BASED);
        waitForElementVisible("id=datagrid");

        //Verify Header - Row
        WebElement dgRowHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"0\"}");
        Assert.assertEquals("0", dgRowHeader0.getText());
        WebElement dgRowHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"1\"}");
        Assert.assertEquals("1", dgRowHeader1.getText());
        WebElement dgRowHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"2\"}");
        Assert.assertEquals("2", dgRowHeader2.getText());

        //Verify Header - Column
        WebElement dgColHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}");
        Assert.assertEquals("0", dgColHeader0.getText());
        WebElement dgColHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"1\"}");
        Assert.assertEquals("1", dgColHeader1.getText());
        WebElement dgColHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"2\"}");
        Assert.assertEquals("2", dgColHeader2.getText());

        //Verify Data	
        WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("X", row0Col0.getText());
        WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("O", row0Col1.getText());
        WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("X", row0Col2.getText());

        WebElement row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("O", row1Col0.getText());
        WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("X", row1Col1.getText());
        WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("O", row1Col2.getText());

        WebElement row2Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("X", row2Col0.getText());
        WebElement row2Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("O", row2Col1.getText());
        WebElement row2Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("X", row2Col2.getText());

    }

    @Test(groups = {"cookbook"})
    public void testCellBasedDataGridSorting() throws Exception {

        //Start the test and bring up the browser
        startupTest("uiComponents-dataGrid-cellBasedGrid.html", null);

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_CELL_BASED);
        waitForElementVisible("id=datagrid");

        //Bring up sort context menu with sub menu
        String elementLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
        String menuLocator = "id=ui-id-1";
        String subMenuLocator = "ui-id-3";
        rightClickAndSelectSubMenuOption(elementLocator, menuLocator, subMenuLocator);

        //Need to wait for Sorting to finish
        waitForMilliseconds(3000L);

        //Verify Data	
        WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("X", row0Col0.getText());
        WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("O", row0Col1.getText());
        WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("X", row0Col2.getText());

        WebElement row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("X", row1Col0.getText());
        WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("O", row1Col1.getText());
        WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("X", row1Col2.getText());

        WebElement row2Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("O", row2Col0.getText());
        WebElement row2Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("X", row2Col1.getText());
        WebElement row2Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("O", row2Col2.getText());

        //Bring up sort context menu with sub menu
        elementLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
        menuLocator = "id=ui-id-1";
        subMenuLocator = "ui-id-2";
        rightClickAndSelectSubMenuOption(elementLocator, menuLocator, subMenuLocator);

        //Need to wait for Sorting to finish
        waitForMilliseconds(3000L);

        //Verify Data	
        row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("O", row0Col0.getText());
        row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("X", row0Col1.getText());
        row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("O", row0Col2.getText());

        row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("X", row1Col0.getText());
        row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("O", row1Col1.getText());
        row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("X", row1Col2.getText());

        row2Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("X", row2Col0.getText());
        row2Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("O", row2Col1.getText());
        row2Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("X", row2Col2.getText());

    }

    @Test(groups = {"cookbook"})
    public void testRowBasedDataGrid() throws Exception {
        //Start the test and bring up the browser
        startupTest("uiComponents-dataGrid-formattedRowBasedGrid.html", null);
        getWebDriver().manage().window().maximize();

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_ROW_BASED);
        waitForElementVisible("id=datagrid");

        //Verify Header - Column
        WebElement dgColHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}");
        Assert.assertEquals("First Name", dgColHeader0.getText());
        WebElement dgColHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"1\"}");
        Assert.assertEquals("Last Name", dgColHeader1.getText());
        WebElement dgColHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"2\"}");
        Assert.assertEquals("Email Address", dgColHeader2.getText());
        WebElement dgColHeader3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"3\"}");
        Assert.assertEquals("Phone #", dgColHeader3.getText());
        WebElement dgColHeader4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"4\"}");
        Assert.assertEquals("Date Hired", dgColHeader4.getText());
        WebElement dgColHeader5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"5\"}");
        Assert.assertEquals("Salary", dgColHeader5.getText());
        WebElement dgColHeader6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"6\"}");
        Assert.assertEquals("Department Id", dgColHeader6.getText());

        //Verify Header - Row
        WebElement dgRowHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"0\"}");
        Assert.assertEquals("100", dgRowHeader0.getText());
        WebElement dgRowHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"1\"}");
        Assert.assertEquals("101", dgRowHeader1.getText());
        WebElement dgRowHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"2\"}");
        Assert.assertEquals("102", dgRowHeader2.getText());
        WebElement dgRowHeader3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"3\"}");
        Assert.assertEquals("103", dgRowHeader3.getText());
        WebElement dgRowHeader4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"4\"}");
        Assert.assertEquals("104", dgRowHeader4.getText());

        //Verify Data	
        WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("Steven", row0Col0.getText());
        WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("King", row0Col1.getText());
        WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("SKING", row0Col2.getText());
        WebElement row0Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("515.123.4567", row0Col3.getText());
        WebElement row0Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("Jun 17, 1987", row0Col4.getText());
        WebElement row0Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("$24,000.00", row0Col5.getText());
        WebElement row0Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("90", row0Col6.getText());

    }

    @Test(groups = {"cookbook"})
    public void testRowBasedDataGridSorting() throws Exception {
        //Start the test and bring up the browser
        startupTest("uiComponents-dataGrid-formattedRowBasedGrid.html", null);
        getWebDriver().manage().window().maximize();

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_ROW_BASED);
        waitForElementVisible("id=datagrid");

        //Bring up sort context menu with sub menu
        String elementLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
        String menuLocator = "id=ui-id-2";
        String subMenuLocator = "ui-id-8";
        rightClickAndSelectSubMenuOption(elementLocator, menuLocator, subMenuLocator);

        //Need to wait for Sorting to finish
        waitForMilliseconds(3000L);

        //Verify Header - Row
        WebElement dgRowHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"0\"}");
        Assert.assertEquals("180", dgRowHeader0.getText());
        WebElement dgRowHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"1\"}");
        Assert.assertEquals("206", dgRowHeader1.getText());
        WebElement dgRowHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"2\"}");
        Assert.assertEquals("171", dgRowHeader2.getText());
        WebElement dgRowHeader3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"3\"}");
        Assert.assertEquals("195", dgRowHeader3.getText());
        WebElement dgRowHeader4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"4\"}");
        Assert.assertEquals("106", dgRowHeader4.getText());

        //Verify Data	
        WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("Winston", row0Col0.getText());
        WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("Taylor", row0Col1.getText());
        WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("WTAYLOR", row0Col2.getText());
        WebElement row0Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("650.507.9876", row0Col3.getText());
        WebElement row0Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("Jan 24, 1998", row0Col4.getText());
        WebElement row0Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("$3,200.00", row0Col5.getText());
        WebElement row0Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("50", row0Col6.getText());

        WebElement row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("William", row1Col0.getText());
        WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("Gietz", row1Col1.getText());
        WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("WGIETZ", row1Col2.getText());
        WebElement row1Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("515.123.8181", row1Col3.getText());
        WebElement row1Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("Jun 7, 1994", row1Col4.getText());
        WebElement row1Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("$8,300.00", row1Col5.getText());
        WebElement row1Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("110", row1Col6.getText());

        WebElement row2Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("William", row2Col0.getText());
        WebElement row2Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("Smith", row2Col1.getText());
        WebElement row2Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("WSMITH", row2Col2.getText());
        WebElement row2Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("011.44.1343.629268", row2Col3.getText());
        WebElement row2Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("Feb 23, 1999", row2Col4.getText());
        WebElement row2Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("$7,400.00", row2Col5.getText());
        WebElement row2Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("80", row2Col6.getText());

        //Bring up sort context menu with sub menu
        elementLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
        menuLocator = "id=ui-id-2";
        subMenuLocator = "ui-id-7";
        rightClickAndSelectSubMenuOption(elementLocator, menuLocator, subMenuLocator);

        //Need to wait for Sorting to finish
        waitForMilliseconds(3000L);

        //Verify Header - Row
        dgRowHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"0\"}");
        Assert.assertEquals("121", dgRowHeader0.getText());
        dgRowHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"1\"}");
        Assert.assertEquals("196", dgRowHeader1.getText());
        dgRowHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"2\"}");
        Assert.assertEquals("147", dgRowHeader2.getText());
        dgRowHeader3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"3\"}");
        Assert.assertEquals("103", dgRowHeader3.getText());
        dgRowHeader4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"4\"}");
        Assert.assertEquals("115", dgRowHeader4.getText());

        //Verify Data
        row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("Adam", row0Col0.getText());
        row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("Fripp", row0Col1.getText());
        row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("AFRIPP", row0Col2.getText());
        row0Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("650.123.2234", row0Col3.getText());
        row0Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("Apr 10, 1997", row0Col4.getText());
        row0Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("$8,200.00", row0Col5.getText());
        row0Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("50", row0Col6.getText());

        row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("Alana", row1Col0.getText());
        row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("Walsh", row1Col1.getText());
        row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("AWALSH", row1Col2.getText());
        row1Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("650.507.9811", row1Col3.getText());
        row1Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("Apr 24, 1998", row1Col4.getText());
        row1Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("$3,100.00", row1Col5.getText());
        row1Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("50", row1Col6.getText());

        row2Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("Alberto", row2Col0.getText());
        row2Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("Errazuriz", row2Col1.getText());
        row2Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("AERRAZUR", row2Col2.getText());
        row2Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("011.44.1344.429278", row2Col3.getText());
        row2Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("Mar 10, 1997", row2Col4.getText());
        row2Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("$12,000.00", row2Col5.getText());
        row2Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("80", row2Col6.getText());

    }

    @Test(groups = {"cookbook"})
    public void testRowBasedDataGridResize() throws Exception {
        //Start the test and bring up the browser
        startupTest("uiComponents-dataGrid-formattedRowBasedGrid.html", null);

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_ROW_BASED);

        //wait
        waitForElementVisible("id=datagrid");
        waitForMilliseconds(3000L);

        //Resize Width - Bring up context menu with sub menu
        String elementLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
        String menuLocator = "id=ui-id-1";
        String subMenuLocator = "ui-id-5";
        rightClickAndSelectSubMenuOption(elementLocator, menuLocator, subMenuLocator);

        //Click on number spinner
        waitForElementVisible("id=ojDialogWrapper-datagriddialog");
        WebElement inputNumberUp = getElement("{\"element\":\"#datagridspinner\",\"subId\":\"oj-inputnumber-up\"}");
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        waitForMilliseconds(3000L);
        WebElement dgDialogSubmitBtn = getElement("id=datagriddialogsubmit");
        dgDialogSubmitBtn.click();
        waitForMilliseconds(3000L);
        WebElement dgColumn0 = getElement("id=datagrid:c0");
        Assert.assertEquals(true, dgColumn0.getAttribute("style").contains("width: 120px;"));

        //Resize Height - Bring up context menu with sub menu
        waitForMilliseconds(3000L);
        elementLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
        menuLocator = "id=ui-id-1";
        subMenuLocator = "ui-id-6";
        rightClickAndSelectSubMenuOption(elementLocator, menuLocator, subMenuLocator);

        //Click on sub number spinner
        waitForElementVisible("id=ojDialogWrapper-datagriddialog");
        inputNumberUp = getElement("{\"element\":\"#datagridspinner\",\"subId\":\"oj-inputnumber-up\"}");
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();

        waitForMilliseconds(3000L);
        dgDialogSubmitBtn = getElement("id=datagriddialogsubmit");
        dgDialogSubmitBtn.click();
        waitForMilliseconds(3000L);
        dgColumn0 = getElement("id=datagrid:columnHeader");
        Assert.assertEquals(true, dgColumn0.getAttribute("style").contains("height: 70px;"));

    }

    @Test(groups = {"cookbook"})
    public void testCellCustomizationDataGrid() throws Exception {
        //Start the test and bring up the browser
        startupTest("uiComponents-dataGrid-gaugeStampGrid.html", null);
        getWebDriver().manage().window().maximize();

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_CELL_CUSTOMIZATION);
        waitForElementVisible("id=datagrid");
       // waitForElementVisible("class=oj-ratinggauge");

        //Verify Header - Column
        WebElement dgColHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}");
        Assert.assertEquals("Symbol", dgColHeader0.getText());
        WebElement dgColHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"1\"}");
        Assert.assertEquals("Current Price", dgColHeader1.getText());
        WebElement dgColHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"2\"}");
        Assert.assertEquals("Change", dgColHeader2.getText());
        WebElement dgColHeader3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"3\"}");
        Assert.assertEquals("Change %", dgColHeader3.getText());
        WebElement dgColHeader4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"4\"}");
        Assert.assertEquals("YTD Returns", dgColHeader4.getText());
        WebElement dgColHeader5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"5\"}");
        Assert.assertEquals("Morningstar Rating", dgColHeader5.getText());

        //Verify Header - Row
        WebElement dgRowHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"0\"}");
        Assert.assertEquals("GNMA Fund Investor Shares", dgRowHeader0.getText());
        WebElement dgRowHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"1\"}");
        Assert.assertEquals("High-Yield Corp Fund Inv", dgRowHeader1.getText());
        WebElement dgRowHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"2\"}");
        Assert.assertEquals("Inter-Term Bond Index Inv", dgRowHeader2.getText());
        WebElement dgRowHeader3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"3\"}");
        Assert.assertEquals("I-T Investment-Grade Inv", dgRowHeader3.getText());
        WebElement dgRowHeader4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"4\"}");
        Assert.assertEquals("Inter-Term Treasury Inv", dgRowHeader4.getText());

        //Need to wait for Sorting to finish
        waitForMilliseconds(3000L);

        //Verify Data	
        WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("VFIIX", row0Col0.getText());
        WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("$10.55", row0Col1.getText());
        WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("$0.00", row0Col2.getText());
        WebElement row0Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("0.00%", row0Col3.getText());
        WebElement row0Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("2.24%", row0Col4.getText());
        //Make sure Gauge is displayed and value is correct
        WebElement row0Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"5\"}");
        WebElement ratingGaugeElemRow0Col5 = row0Col5.findElement(By.className("oj-ratinggauge"));
        Assert.assertEquals(true, ratingGaugeElemRow0Col5.isDisplayed());
        String row0Col5Val = evalJavascript("return $('#gaugeid0').ojRatingGauge('option', 'value')");
        Assert.assertEquals("5", row0Col5Val);

        WebElement row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("VWEHX", row1Col0.getText());
        WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("$5.94", row1Col1.getText());
        WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("$0.01", row1Col2.getText());
        WebElement row1Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("0.17%", row1Col3.getText());
        WebElement row1Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("4.85%", row1Col4.getText());
        //Make sure Gauge is displayed and value is correct
        WebElement row1Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"5\"}");
        WebElement ratingGaugeElemRow1Col5 = row1Col5.findElement(By.className("oj-ratinggauge"));
        Assert.assertEquals(true, ratingGaugeElemRow1Col5.isDisplayed());
        String row1Col5Val = evalJavascript("return $('#gaugeid1').ojRatingGauge('option', 'value')");
        Assert.assertEquals("3", row1Col5Val);

    }

    @Test(groups = {"cookbook"})
    public void testCustomizationDataGridSorting() throws Exception {
        //Start the test and bring up the browser
        startupTest("uiComponents-dataGrid-gaugeStampGrid.html", null);
        getWebDriver().manage().window().maximize();

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_CELL_CUSTOMIZATION);
        waitForElementVisible("id=datagrid");

        //Bring up sort context menu with sub menu
        String elementLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"5\"}";
        String menuLocator = "id=ui-id-2";
        String subMenuLocator = "ui-id-6";
        rightClickAndSelectSubMenuOption(elementLocator, menuLocator, subMenuLocator);

        //Need to wait for Sorting to finish
        waitForMilliseconds(5000L);

        //Verify Header - Row
        WebElement dgRowHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"0\"}");
        Assert.assertEquals("GNMA Fund Investor Shares", dgRowHeader0.getText());
        WebElement dgRowHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"1\"}");
        Assert.assertEquals("Capital Opportunity Inv", dgRowHeader1.getText());
        WebElement dgRowHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"2\"}");
        Assert.assertEquals("Selected Value Fund", dgRowHeader2.getText());
        WebElement dgRowHeader3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"3\"}");
        Assert.assertEquals("Inter-Term Treasury Inv", dgRowHeader3.getText());
        WebElement dgRowHeader4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"4\"}");
        Assert.assertEquals("Energy Fund Investor", dgRowHeader4.getText());

        //Verify Data	
        WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("VFIIX", row0Col0.getText());
        WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("$10.55", row0Col1.getText());
        WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("$0.00", row0Col2.getText());
        WebElement row0Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("0.00%", row0Col3.getText());
        WebElement row0Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("2.24%", row0Col4.getText());

        WebElement row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("VGSIX", row1Col0.getText());
        WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("$44.56", row1Col1.getText());
        WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("-$0.45", row1Col2.getText());
        WebElement row1Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("-1.00%", row1Col3.getText());
        WebElement row1Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("32.54%", row1Col4.getText());

        WebElement row2Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("VASVX", row2Col0.getText());
        WebElement row2Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("$27.17", row2Col1.getText());
        WebElement row2Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("-$0.17", row2Col2.getText());
        WebElement row2Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("-0.62%", row2Col3.getText());
        WebElement row2Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("29.50%", row2Col4.getText());

        //Bring up sort context menu with sub menu
        elementLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"5\"}";
        menuLocator = "id=ui-id-2";
        subMenuLocator = "ui-id-5";
        rightClickAndSelectSubMenuOption(elementLocator, menuLocator, subMenuLocator);

        //Need to wait for Sorting to finish
        waitForMilliseconds(5000L);

        //Verify Header - Row
        dgRowHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"0\"}");
        Assert.assertEquals("FTSE AW xUS Sm-Cp Idx Inv", dgRowHeader0.getText());
        dgRowHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"1\"}");
        Assert.assertEquals("High-Yield Corp Fund Inv", dgRowHeader1.getText());
        dgRowHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"2\"}");
        Assert.assertEquals("Pacific Stock Index Inv", dgRowHeader2.getText());
        dgRowHeader3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"3\"}");
        Assert.assertEquals("Small-Cap Value Index", dgRowHeader3.getText());
        dgRowHeader4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"4\"}");
        Assert.assertEquals("FTSE All-World ex-US Inv", dgRowHeader4.getText());

        //Verify Data
        row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("VFSVX", row0Col0.getText());
        row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("$37.97", row0Col1.getText());
        row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("$0.02", row0Col2.getText());
        row0Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("0.05%", row0Col3.getText());
        row0Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("12.20%", row0Col4.getText());

        row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("VWEHX", row1Col0.getText());
        row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("$5.94", row1Col1.getText());
        row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("$0.01", row1Col2.getText());
        row1Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("0.17%", row1Col3.getText());
        row1Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("4.85%", row1Col4.getText());

        row2Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("VPACX", row2Col0.getText());
        row2Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("$11.53", row2Col1.getText());
        row2Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("-$0.03", row2Col2.getText());
        row2Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("-0.26%", row2Col3.getText());
        row2Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("15.06%", row2Col4.getText());

    }

    @Test(groups = {"cookbook"})
    public void testCustomizationDataGridResize() throws Exception {
        //Start the test and bring up the browser
        startupTest("uiComponents-dataGrid-gaugeStampGrid.html", null);
        getWebDriver().manage().window().maximize();

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_CELL_CUSTOMIZATION);
        waitForElementVisible("id=datagrid");

        ////Resize Width - Bring up context menu with sub menu
        String elementLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
        String menuLocator = "id=ui-id-1";
        String subMenuLocator = "ui-id-3";
        rightClickAndSelectSubMenuOption(elementLocator, menuLocator, subMenuLocator);

        waitForElementVisible("id=ojDialogWrapper-datagriddialog");
        WebElement inputNumberUp = getElement("{\"element\":\"#datagridspinner\",\"subId\":\"oj-inputnumber-up\"}");
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        inputNumberUp.click();
        waitForMilliseconds(3000L);
        WebElement dgDialogSubmitBtn = getElement("id=datagriddialogsubmit");
        dgDialogSubmitBtn.click();
        waitForMilliseconds(3000L);
        WebElement dgColumn0 = getElement("id=datagrid:c0");
        Assert.assertEquals(true, dgColumn0.getAttribute("style").contains("width: 125px;"));

        //Rezie Height - TODO check for disabled menu
        waitForMilliseconds(3000L);
        elementLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
        menuLocator = "id=ui-id-1";
        subMenuLocator = "ui-id-4";

    }

    @Test(groups = {"cookbook"})
    public void testChartStampingDataGrid() throws Exception {
        //Start the test and bring up the browser
        startupTest("uiComponents-dataGrid-pieChartStampGrid.html", null);

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_CHART_STAMPING);
        waitForElementVisible("id=datagrid");

        //Verify Header - Column
        WebElement dgColHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}");
        Assert.assertEquals("2008", dgColHeader0.getText());
        WebElement dgColHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"1\"}");
        Assert.assertEquals("2009", dgColHeader1.getText());
        WebElement dgColHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"2\"}");
        Assert.assertEquals("2010", dgColHeader2.getText());

        //Verify Header - Row
        WebElement dgRowHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"0\"}");
        Assert.assertEquals("A", dgRowHeader0.getText());
        WebElement dgRowHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"1\"}");
        Assert.assertEquals("B", dgRowHeader1.getText());

        //Data Verification
        WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
        WebElement pieChartElemRow0Col0 = row0Col0.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow0Col0.isDisplayed());
        verifyPieChartData("#pie02008", "3447.25", "2883.1", "3.447K", "2.883K");
        WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
        WebElement pieChartElemRow0Col1 = row0Col1.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow0Col1.isDisplayed());
        verifyPieChartData("#pie02009", "11070.41", "3017.5", "11.07K", "3.018K");
        String pieSeriesCountRow0Col1 = evalJavascript("return $(\"#pie02009\").ojChart('getSeriesCount')");
        Assert.assertEquals("2", pieSeriesCountRow0Col1);
        WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        WebElement pieChartElemRow0Col2 = row0Col2.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow0Col2.isDisplayed());
        verifyPieChartData("#pie02010", "2968.3", "9802.79", "2.968K", "9.803K");

        WebElement row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
        WebElement pieChartElemRow1Col0 = row1Col0.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow1Col0.isDisplayed());
        verifyPieChartData("#pie12008", "46113", "32081.75", "46.11K", "32.08K");
        WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
        WebElement pieChartElemRow1Col1 = row1Col1.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow1Col1.isDisplayed());
        verifyPieChartData("#pie12009", "48450.5", "30051.25", "48.45K", "30.05K");
        WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        WebElement pieChartElemRow1Col2 = row1Col2.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow1Col2.isDisplayed());
        verifyPieChartData("#pie12010", "59683.51", "37526.65", "59.68K", "37.53K");

        WebElement row2Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
        WebElement pieChartElemRow2Col0 = row2Col0.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow2Col0.isDisplayed());
        verifyPieChartData("#pie22008", "65349.75", "26275", "65.35K", "26.28K");
        WebElement row2Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"1\"}");
        WebElement pieChartElemRow2Col1 = row2Col1.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow2Col1.isDisplayed());
        verifyPieChartData("#pie22009", "937.25", "32045.5", "937.3", "32.05K");
        WebElement row2Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"2\"}");
        WebElement pieChartElemRow2Col2 = row2Col2.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow2Col2.isDisplayed());
        verifyPieChartData("#pie22010", "108397.29", "35432", "108.4K", "35.43K");

    }

    @Test(groups = {"cookbook"})
    public void testChartStampingDataGridSorting() throws Exception {
        //Start the test and bring up the browser        
        startupTest("uiComponents-dataGrid-pieChartStampGrid.html", null);

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_CHART_STAMPING);
        waitForElementVisible("id=datagrid");

        //Bring up sort context menu with sub menu
        String elementLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
        String menuLocator = "id=ui-id-1";
        String subMenuLocator = "ui-id-3";
        rightClickAndSelectSubMenuOption(elementLocator, menuLocator, subMenuLocator);

        //Need to wait for Sorting to finish
        waitForElementVisible("id=datagrid:rowHeader");
        waitForMilliseconds(5000L);

        //Verify Header - Row
        WebElement dgRowHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"5\"}");
        Assert.assertEquals("F", dgRowHeader0.getText());
        WebElement dgRowHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"4\"}");
        Assert.assertEquals("E", dgRowHeader1.getText());

        //Data Verification
        WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"5\",\"columnIndex\":\"0\"}");
        WebElement pieChartElemRow0Col0 = row0Col0.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow0Col0.isDisplayed());
        verifyPieChartData("#pie52008", "4277", "180", "4.277K", "180.0");
        WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"5\",\"columnIndex\":\"1\"}");
        WebElement pieChartElemRow0Col1 = row0Col1.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow0Col1.isDisplayed());
        verifyPieChartData("#pie52009", "3558", "556", "3.558K", "556.0");
        String pieSeriesCountRow0Col1 = evalJavascript("return $(\"#pie02009\").ojChart('getSeriesCount')");
        Assert.assertEquals("2", pieSeriesCountRow0Col1);
        WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"5\",\"columnIndex\":\"2\"}");
        WebElement pieChartElemRow0Col2 = row0Col2.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow0Col2.isDisplayed());
        verifyPieChartData("#pie52010", "609", "441", "609.0", "441.0");

        WebElement row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"0\"}");
        WebElement pieChartElemRow1Col0 = row1Col0.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow1Col0.isDisplayed());
        verifyPieChartData("#pie42008", "18415.5", "5737", "18.42K", "5.737K");
        WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"1\"}");
        WebElement pieChartElemRow1Col1 = row1Col1.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow1Col1.isDisplayed());
        verifyPieChartData("#pie42009", "18390.2", "5642", "18.39K", "5.642K");
        WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"2\"}");
        WebElement pieChartElemRow1Col2 = row1Col2.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow1Col2.isDisplayed());
        verifyPieChartData("#pie42010", "2179", "3988", "2.179K", "3.988K");

        //Verify ascending sorting
        //Bring up sort context menu with sub menu
        elementLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
        menuLocator = "id=ui-id-1";
        subMenuLocator = "ui-id-2";
        rightClickAndSelectSubMenuOption(elementLocator, menuLocator, subMenuLocator);

        //Need to wait for Sorting to finish
        waitForElementVisible("id=datagrid:rowHeader");
        waitForMilliseconds(5000L);

        //Verify Header - Row
        dgRowHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"5\"}");
        Assert.assertEquals("F", dgRowHeader0.getText());
        dgRowHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"4\"}");
        Assert.assertEquals("E", dgRowHeader1.getText());

        //Data Verification
        row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"5\",\"columnIndex\":\"0\"}");
        pieChartElemRow0Col0 = row0Col0.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow0Col0.isDisplayed());
        verifyPieChartData("#pie52008", "4277", "180", "4.277K", "180.0");
        row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"5\",\"columnIndex\":\"1\"}");
        pieChartElemRow0Col1 = row0Col1.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow0Col1.isDisplayed());
        verifyPieChartData("#pie52009", "3558", "556", "3.558K", "556.0");
        pieSeriesCountRow0Col1 = evalJavascript("return $(\"#pie02009\").ojChart('getSeriesCount')");
        Assert.assertEquals("2", pieSeriesCountRow0Col1);
        row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"5\",\"columnIndex\":\"2\"}");
        pieChartElemRow0Col2 = row0Col2.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow0Col2.isDisplayed());
        verifyPieChartData("#pie52010", "609", "441", "609.0", "441.0");

        row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"0\"}");
        pieChartElemRow1Col0 = row1Col0.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow1Col0.isDisplayed());
        verifyPieChartData("#pie42008", "18415.5", "5737", "18.42K", "5.737K");
        row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"1\"}");
        pieChartElemRow1Col1 = row1Col1.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow1Col1.isDisplayed());
        verifyPieChartData("#pie42009", "18390.2", "5642", "18.39K", "5.642K");
        row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"2\"}");
        pieChartElemRow1Col2 = row1Col2.findElement(By.className("oj-chart"));
        Assert.assertEquals(true, pieChartElemRow1Col2.isDisplayed());
        verifyPieChartData("#pie42010", "2179", "3988", "2.179K", "3.988K");

    }

    @Test(groups = {"cookbook"})
    public void testCustomRendererDataGrid() throws Exception {
        //Start the test and bring up the browser
        startupTest("uiComponents-dataGrid-customRendererGrid.html", null);
        getWebDriver().manage().window().maximize();

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_CUSTOM_RENDERER);
        waitForElementVisible("id=datagrid");
        waitForMilliseconds(3000L);
        //Verify Header - Column        
        WebElement dgColHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"2\"}");
        Assert.assertEquals("GNP Deflator (1972=100)", dgColHeader2.getText());
        WebElement dgColHeader3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"3\"}");
        Assert.assertEquals("GNP Real Trend", dgColHeader3.getText());
        WebElement dgColHeader4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"4\"}");
        Assert.assertEquals("Commercial Paper Rate", dgColHeader4.getText());
        WebElement dgColHeader5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"5\"}");
        Assert.assertEquals("Corporate Bond Yield", dgColHeader5.getText());
        WebElement dgColHeader6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"6\"}");
        Assert.assertEquals("M1 Money Supply", dgColHeader6.getText());

        //Verify Header - Row
        WebElement dgRowHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"0\"}");
        Assert.assertEquals("1983 Q4", dgRowHeader0.getText());
        WebElement dgRowHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"1\"}");
        Assert.assertEquals("1983 Q3", dgRowHeader1.getText());
        WebElement dgRowHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"2\"}");
        Assert.assertEquals("1983 Q2", dgRowHeader2.getText());
        WebElement dgRowHeader3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"3\"}");
        Assert.assertEquals("1983 Q1", dgRowHeader3.getText());
        WebElement dgRowHeader4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"4\"}");
        Assert.assertEquals("1983", dgRowHeader4.getText());

        //Verify Data	        
        WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("218.2", row0Col2.getText());
        WebElement row0Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("1685.7", row0Col3.getText());
        WebElement row0Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("9.19", row0Col4.getText());
        WebElement row0Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("13.46", row0Col5.getText());
        WebElement row0Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("523.4", row0Col6.getText());

        WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("215.88", row1Col2.getText());
        WebElement row1Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("1673.2", row1Col3.getText());
        WebElement row1Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("9.44", row1Col4.getText());
        WebElement row1Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("13.39", row1Col5.getText());
        WebElement row1Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("517.2", row1Col6.getText());

        WebElement row2Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("214.26", row2Col2.getText());
        WebElement row2Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("1660.7", row2Col3.getText());
        WebElement row2Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("8.61", row2Col4.getText());
        WebElement row2Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("13.29", row2Col5.getText());
        WebElement row2Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("505.2", row2Col6.getText());

        WebElement row3Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("212.86", row3Col2.getText());
        WebElement row3Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("1648.4", row3Col3.getText());
        WebElement row3Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("8.34", row3Col4.getText());
        WebElement row3Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("13.94", row3Col5.getText());
        WebElement row3Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("490.9", row3Col6.getText());

        WebElement row4Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("861.2", row4Col2.getText());
        WebElement row4Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("6668", row4Col3.getText());
        WebElement row4Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("35.58", row4Col4.getText());
        WebElement row4Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("54.08", row4Col5.getText());
        WebElement row4Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("2036.7", row4Col6.getText());

    }

    @Test(groups = {"cookbook"})
    public void testCustomRendererDataGridSorting() throws Exception {
        //Start the test and bring up the browser
        startupTest("uiComponents-dataGrid-customRendererGrid.html", null);
        getWebDriver().manage().window().maximize();
        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_CUSTOM_RENDERER);
        waitForElementVisible("id=datagrid");

        //Bring up sort context menu with sub menu
        String elementLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"4\"}";
        String menuLocator = "id=ui-id-1";
        String subMenuLocator = "ui-id-3";
        rightClickAndSelectSubMenuOption(elementLocator, menuLocator, subMenuLocator);

        //Need to wait for Sorting to finish
        waitForMilliseconds(3000L);

        //Verify Header - Row
        WebElement dgRowHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"0\"}");
        Assert.assertEquals("1981", dgRowHeader0.getText());
        WebElement dgRowHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"1\"}");
        Assert.assertEquals("1980", dgRowHeader1.getText());
        WebElement dgRowHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"2\"}");
        Assert.assertEquals("1982", dgRowHeader2.getText());
        WebElement dgRowHeader3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"3\"}");
        Assert.assertEquals("1979", dgRowHeader3.getText());
        WebElement dgRowHeader4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"4\"}");
        Assert.assertEquals("1974", dgRowHeader4.getText());

        //Verify Data	       
        WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("782.44", row0Col2.getText());
        WebElement row0Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("6283.8", row0Col3.getText());
        WebElement row0Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("59.08", row0Col4.getText());
        WebElement row0Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("63.87", row0Col5.getText());
        WebElement row0Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("1720.3", row0Col6.getText());

        WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("713.77", row1Col2.getText());
        WebElement row1Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("6100.7", row1Col3.getText());
        WebElement row1Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("49.03", row1Col4.getText());
        WebElement row1Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("53.51", row1Col5.getText());
        WebElement row1Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("1605.9", row1Col6.getText());

        WebElement row2Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("829.55", row2Col2.getText());
        WebElement row2Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("6472.4", row2Col3.getText());
        WebElement row2Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("47.52", row2Col4.getText());
        WebElement row2Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("65.41", row2Col5.getText());
        WebElement row2Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("1834", row2Col6.getText());

        //Bring up sort context menu with sub menu
        elementLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"4\"}";
        menuLocator = "id=ui-id-1";
        subMenuLocator = "ui-id-2";
        rightClickAndSelectSubMenuOption(elementLocator, menuLocator, subMenuLocator);

        //Need to wait for Sorting to finish
        waitForMilliseconds(3000L);

        //Verify Header - Row
        dgRowHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"0\"}");
        Assert.assertEquals("1941 Q3", dgRowHeader0.getText());
        dgRowHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"1\"}");
        Assert.assertEquals("1941 Q4", dgRowHeader1.getText());
        dgRowHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"2\"}");
        Assert.assertEquals("1939 Q1", dgRowHeader2.getText());
        dgRowHeader3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"3\"}");
        Assert.assertEquals("1941 Q2", dgRowHeader3.getText());
        dgRowHeader4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"4\"}");
        Assert.assertEquals("1939 Q2", dgRowHeader4.getText());

        //Verify Data	       
        row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("31.85", row0Col2.getText());
        row0Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("417.58", row0Col3.getText());
        row0Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("0.5", row0Col4.getText());
        row0Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("4.28", row0Col5.getText());
        row0Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("46.35", row0Col6.getText());

        row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("32.55", row1Col2.getText());
        row1Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("420", row1Col3.getText());
        row1Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("0.52", row1Col4.getText());
        row1Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("4.28", row1Col5.getText());
        row1Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("47.97", row1Col6.getText());

        row2Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("28.36", row2Col2.getText());
        row2Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("394.18", row2Col3.getText());
        row2Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("0.56", row2Col4.getText());
        row2Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("5.12", row2Col5.getText());
        row2Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("31.48", row2Col6.getText());
    }

    @Test(groups = {"cookbook"})
    public void testCustomContextMenu() throws Exception {
        //Start the test and bring up the browser
        startupTest("uiComponents-dataGrid-customContextMenuGrid.html", null);
        getWebDriver().manage().window().maximize();

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_CUSTOM_CONTEXT_MENU);
        waitForElementVisible("id=datagrid");

        String elementLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
        String menuLocator = "id=ui-id-2";
        String subMenuLocator = "ui-id-3";
        rightClickAndSelectSubMenuOption(elementLocator, menuLocator, subMenuLocator);

        WebElement resultTextElem = getElement("id=results");
        Assert.assertEquals("Item 2", resultTextElem.getText());

    }

    @Test(groups = {"cookbook"})
    public void testCustomDataSource() throws Exception {
        //Start the test and bring up the browser
        startupTest("uiComponents-dataGrid-customDataSourceGrid.html", null);
        getWebDriver().manage().window().maximize();

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_CUSTOM_DATASOURCE);
        waitForElementVisible("id=datagrid");

        //Verify Header - Column
        WebElement dgColHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}");
        Assert.assertEquals("C0", dgColHeader0.getText());
        WebElement dgColHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"1\"}");
        Assert.assertEquals("C1", dgColHeader1.getText());
        WebElement dgColHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"2\"}");
        Assert.assertEquals("C2", dgColHeader2.getText());
        WebElement dgColHeader3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"3\"}");
        Assert.assertEquals("C3", dgColHeader3.getText());
        WebElement dgColHeader4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"4\"}");
        Assert.assertEquals("C4", dgColHeader4.getText());
        WebElement dgColHeader5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"5\"}");
        Assert.assertEquals("C5", dgColHeader5.getText());
        WebElement dgColHeader6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"6\"}");
        Assert.assertEquals("C6", dgColHeader6.getText());
        WebElement dgColHeader7 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"7\"}");
        Assert.assertEquals("C7", dgColHeader7.getText());
        WebElement dgColHeader8 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"8\"}");
        Assert.assertEquals("C8", dgColHeader8.getText());
        WebElement dgColHeader9 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"9\"}");
        Assert.assertEquals("C9", dgColHeader9.getText());
        WebElement dgColHeader10 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"10\"}");
        Assert.assertEquals("C10", dgColHeader10.getText());

        //Verify Header - Row
        WebElement dgRowHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"0\"}");
        Assert.assertEquals("R0", dgRowHeader0.getText());
        WebElement dgRowHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"1\"}");
        Assert.assertEquals("R1", dgRowHeader1.getText());
        WebElement dgRowHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"2\"}");
        Assert.assertEquals("R2", dgRowHeader2.getText());
        WebElement dgRowHeader3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"3\"}");
        Assert.assertEquals("R3", dgRowHeader3.getText());
        WebElement dgRowHeader4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"row\",\"index\":\"4\"}");
        Assert.assertEquals("R4", dgRowHeader4.getText());

        //Verify Data		
        WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("0,0", row0Col0.getText());
        WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("0,1", row0Col1.getText());
        WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("0,2", row0Col2.getText());
        WebElement row0Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("0,3", row0Col3.getText());
        WebElement row0Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("0,4", row0Col4.getText());
        WebElement row0Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("0,5", row0Col5.getText());
        WebElement row0Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("0,6", row0Col6.getText());
        WebElement row0Col7 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"7\"}");
        Assert.assertEquals("0,7", row0Col7.getText());
        WebElement row0Col8 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"8\"}");
        Assert.assertEquals("0,8", row0Col8.getText());
        WebElement row0Col9 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"9\"}");
        Assert.assertEquals("0,9", row0Col9.getText());
        WebElement row0Col10 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"10\"}");
        Assert.assertEquals("0,10", row0Col10.getText());

        WebElement row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("1,0", row1Col0.getText());
        WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("1,1", row1Col1.getText());
        WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("1,2", row1Col2.getText());
        WebElement row1Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("1,3", row1Col3.getText());
        WebElement row1Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("1,4", row1Col4.getText());
        WebElement row1Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("1,5", row1Col5.getText());
        WebElement row1Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("1,6", row1Col6.getText());
        WebElement row1Col7 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"7\"}");
        Assert.assertEquals("1,7", row1Col7.getText());
        WebElement row1Col8 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"8\"}");
        Assert.assertEquals("1,8", row1Col8.getText());
        WebElement row1Col9 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"9\"}");
        Assert.assertEquals("1,9", row1Col9.getText());
        WebElement row1Col10 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"10\"}");
        Assert.assertEquals("1,10", row1Col10.getText());

        WebElement row2Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("2,0", row2Col0.getText());
        WebElement row2Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("2,1", row2Col1.getText());
        WebElement row2Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("2,2", row2Col2.getText());
        WebElement row2Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("2,3", row2Col3.getText());
        WebElement row2Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("2,4", row2Col4.getText());
        WebElement row2Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("2,5", row2Col5.getText());
        WebElement row2Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("2,6", row2Col6.getText());
        WebElement row2Col7 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"7\"}");
        Assert.assertEquals("2,7", row2Col7.getText());
        WebElement row2Col8 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"8\"}");
        Assert.assertEquals("2,8", row2Col8.getText());
        WebElement row2Col9 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"9\"}");
        Assert.assertEquals("2,9", row2Col9.getText());
        WebElement row2Col10 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"10\"}");
        Assert.assertEquals("2,10", row2Col10.getText());

        WebElement row3Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("3,0", row3Col0.getText());
        WebElement row3Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("3,1", row3Col1.getText());
        WebElement row3Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("3,2", row3Col2.getText());
        WebElement row3Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("3,3", row3Col3.getText());
        WebElement row3Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("3,4", row3Col4.getText());
        WebElement row3Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("3,5", row3Col5.getText());
        WebElement row3Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("3,6", row3Col6.getText());
        WebElement row3Col7 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"7\"}");
        Assert.assertEquals("3,7", row3Col7.getText());
        WebElement row3Col8 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"8\"}");
        Assert.assertEquals("3,8", row3Col8.getText());
        WebElement row3Col9 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"9\"}");
        Assert.assertEquals("3,9", row3Col9.getText());
        WebElement row3Col10 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"10\"}");
        Assert.assertEquals("3,10", row3Col10.getText());

        WebElement row4Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("4,0", row4Col0.getText());
        WebElement row4Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("4,1", row4Col1.getText());
        WebElement row4Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("4,2", row4Col2.getText());
        WebElement row4Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("4,3", row4Col3.getText());
        WebElement row4Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("4,4", row4Col4.getText());
        WebElement row4Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("4,5", row4Col5.getText());
        WebElement row4Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("4,6", row4Col6.getText());
        WebElement row4Col7 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"7\"}");
        Assert.assertEquals("4,7", row4Col7.getText());
        WebElement row4Col8 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"8\"}");
        Assert.assertEquals("4,8", row4Col8.getText());
        WebElement row4Col9 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"9\"}");
        Assert.assertEquals("4,9", row4Col9.getText());
        WebElement row4Col10 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"4\",\"columnIndex\":\"10\"}");
        Assert.assertEquals("4,10", row4Col10.getText());
    }

    @Test(groups = {"cookbook"})
    public void testPagingDataGrid() throws Exception {
        //Start the test and bring up the browser
        startupTest("uiComponents-pagingControl-basicPagingDataGrid.html", null);
        getWebDriver().manage().window().maximize();

        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_PAGING);

        //wait
        waitForElementVisible("id=datagrid");
        waitForElementVisible("id=paging");

        //Verify Header - Column
        WebElement dgColHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}");
        Assert.assertEquals("EMPLOYEE_ID", dgColHeader0.getText());
        WebElement dgColHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"1\"}");
        Assert.assertEquals("FIRST_NAME", dgColHeader1.getText());
        WebElement dgColHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"2\"}");
        Assert.assertEquals("LAST_NAME", dgColHeader2.getText());
        WebElement dgColHeader3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"3\"}");
        Assert.assertEquals("EMAIL", dgColHeader3.getText());
        WebElement dgColHeader4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"4\"}");
        Assert.assertEquals("PHONE_NUMBER", dgColHeader4.getText());
        WebElement dgColHeader5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"5\"}");
        Assert.assertEquals("HIRE_DATE", dgColHeader5.getText());
        WebElement dgColHeader6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"6\"}");
        Assert.assertEquals("SALARY", dgColHeader6.getText());
        WebElement dgColHeader7 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"7\"}");
        Assert.assertEquals("DEPARTMENT_ID", dgColHeader7.getText());

        //Verify Data		
        WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("100", row0Col0.getText());
        WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("Steven", row0Col1.getText());
        WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("King", row0Col2.getText());
        WebElement row0Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("SKING", row0Col3.getText());
        WebElement row0Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("515.123.4567", row0Col4.getText());
        WebElement row0Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("1987-06-17", row0Col5.getText());
        WebElement row0Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("24000", row0Col6.getText());
        WebElement row0Col7 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"7\"}");
        Assert.assertEquals("90", row0Col7.getText());

        WebElement row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("101", row1Col0.getText());
        WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("Neena", row1Col1.getText());
        WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("Kochhar", row1Col2.getText());
        WebElement row1Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("NKOCHHAR", row1Col3.getText());
        WebElement row1Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("515.123.4568", row1Col4.getText());
        WebElement row1Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("1989-09-21", row1Col5.getText());
        WebElement row1Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("17000", row1Col6.getText());
        WebElement row1Col7 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"7\"}");
        Assert.assertEquals("90", row1Col7.getText());

        WebElement nextArrowIcon = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-next\"}");
        nextArrowIcon.click();

        //Need to wait for Sorting to finish
        waitForMilliseconds(3000L);

        //Verify Data		
        row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("110", row0Col0.getText());
        row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("John", row0Col1.getText());
        row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("Chen", row0Col2.getText());
        row0Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("JCHEN", row0Col3.getText());
        row0Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("515.124.4269", row0Col4.getText());
        row0Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("1997-09-28", row0Col5.getText());
        row0Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("8200", row0Col6.getText());
        row0Col7 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"7\"}");
        Assert.assertEquals("100", row0Col7.getText());

        row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("111", row1Col0.getText());
        row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("Ismael", row1Col1.getText());
        row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("Sciarra", row1Col2.getText());
        row1Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("ISCIARRA", row1Col3.getText());
        row1Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("515.124.4369", row1Col4.getText());
        row1Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("1997-09-30", row1Col5.getText());
        row1Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("7700", row1Col6.getText());
        row1Col7 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"7\"}");
        Assert.assertEquals("100", row1Col7.getText());

        WebElement prevArrowIcon = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-previous\"}");
        prevArrowIcon.click();

        //Need to wait for Sorting to finish
        waitForMilliseconds(3000L);

        //Verify Data		
        row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("100", row0Col0.getText());
        row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("Steven", row0Col1.getText());
        row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("King", row0Col2.getText());
        row0Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("SKING", row0Col3.getText());
        row0Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("515.123.4567", row0Col4.getText());
        row0Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("1987-06-17", row0Col5.getText());
        row0Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("24000", row0Col6.getText());
        row0Col7 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"7\"}");
        Assert.assertEquals("90", row0Col7.getText());

        row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("101", row1Col0.getText());
        row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("Neena", row1Col1.getText());
        row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("Kochhar", row1Col2.getText());
        row1Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("NKOCHHAR", row1Col3.getText());
        row1Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("515.123.4568", row1Col4.getText());
        row1Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("1989-09-21", row1Col5.getText());
        row1Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("17000", row1Col6.getText());
        row1Col7 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"7\"}");
        Assert.assertEquals("90", row1Col7.getText());

        WebElement lastArrowIcon = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-last\"}");
        lastArrowIcon.click();

        //Need to wait for Sorting to finish
        waitForMilliseconds(3000L);

        //Verify Data		
        row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("200", row0Col0.getText());
        row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("Jennifer", row0Col1.getText());
        row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("Whalen", row0Col2.getText());
        row0Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("JWHALEN", row0Col3.getText());
        row0Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("515.123.4444", row0Col4.getText());
        row0Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("1987-09-17", row0Col5.getText());
        row0Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("4400", row0Col6.getText());
        row0Col7 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"7\"}");
        Assert.assertEquals("10", row0Col7.getText());

        row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("201", row1Col0.getText());
        row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("Michael", row1Col1.getText());
        row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("Hartstein", row1Col2.getText());
        row1Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("MHARTSTE", row1Col3.getText());
        row1Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("515.123.5555", row1Col4.getText());
        row1Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("1996-02-17", row1Col5.getText());
        row1Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("13000", row1Col6.getText());
        row1Col7 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"7\"}");
        Assert.assertEquals("20", row1Col7.getText());

        WebElement firstArrowIcon = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-first\"}");
        firstArrowIcon.click();

        //Need to wait for Sorting to finish
        waitForMilliseconds(3000L);

        //Verify Data		
        row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("100", row0Col0.getText());
        row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("Steven", row0Col1.getText());
        row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("King", row0Col2.getText());
        row0Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("SKING", row0Col3.getText());
        row0Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("515.123.4567", row0Col4.getText());
        row0Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("1987-06-17", row0Col5.getText());
        row0Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("24000", row0Col6.getText());
        row0Col7 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"7\"}");
        Assert.assertEquals("90", row0Col7.getText());

        row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
        Assert.assertEquals("101", row1Col0.getText());
        row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
        Assert.assertEquals("Neena", row1Col1.getText());
        row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
        Assert.assertEquals("Kochhar", row1Col2.getText());
        row1Col3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
        Assert.assertEquals("NKOCHHAR", row1Col3.getText());
        row1Col4 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
        Assert.assertEquals("515.123.4568", row1Col4.getText());
        row1Col5 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"5\"}");
        Assert.assertEquals("1989-09-21", row1Col5.getText());
        row1Col6 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"6\"}");
        Assert.assertEquals("17000", row1Col6.getText());
        row1Col7 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"7\"}");
        Assert.assertEquals("90", row1Col7.getText());

    }

    @Test(groups = {"cookbook"})
    public void testScrollbarDataGrid() throws Exception {
        //Start the test and bring up the browser
        startupTest("uiComponents-dataGrid-customRendererGrid.html", null);
        getWebDriver().manage().window().maximize();
        //Verify the url
        String url = getBrowserUrl();
        log("URL##########" + url);

        //wait
        waitForElementVisible("id=datagrid");

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE_CUSTOM_RENDERER);

        //Verfication
        //Make sure scrollbars are getting displayed
        WebElement scrollbarElem = getElement("id=datagrid:scroller");
        Assert.assertEquals(true, scrollbarElem.isDisplayed());

        //Verfication - TODO scrollbar navigation(after the ER is implemented)
    }

    private void verifyPieChartData(String locator, String seriesVal1, String seriesVal2, String tooltipVal1, String tooltipVal2) {
        Assert.assertEquals("Male", evalJavascript("return $('" + locator + "').ojChart('getDataItem',0,1).getSeries()"));
        Assert.assertEquals("Group 1", evalJavascript("return $('" + locator + "').ojChart('getDataItem',0,1).getGroup()"));
        Assert.assertEquals(seriesVal1, evalJavascript("return $('" + locator + "').ojChart('getDataItem',0,1).getValue()"));
        Assert.assertEquals("Series: Male<br>Value: " + tooltipVal1, evalJavascript("return $('" + locator + "').ojChart('getDataItem',0,1).getTooltip()"));

        Assert.assertEquals("Female", evalJavascript("return $('" + locator + "').ojChart('getDataItem',1,1).getSeries()"));
        Assert.assertEquals("Group 1", evalJavascript("return $('" + locator + "').ojChart('getDataItem',1,1).getGroup()"));
        Assert.assertEquals(seriesVal2, evalJavascript("return $('" + locator + "').ojChart('getDataItem',1,1).getValue()"));
        Assert.assertEquals("Series: Female<br>Value: " + tooltipVal2, evalJavascript("return $('" + locator + "').ojChart('getDataItem',1,1).getTooltip()"));

        //Assert.assertEquals("true", evalJavascript("return $('"+locator +"').ojChart('option','legend.rendered')"));
        Assert.assertEquals("bottom", evalJavascript("return $('" + locator + "').ojChart('option','legend.position')"));
    }

    private void log(String log) {
        System.out.println(log);
        getLogger().fine("[CookbookDataGridTest] " + log);
    }

}
