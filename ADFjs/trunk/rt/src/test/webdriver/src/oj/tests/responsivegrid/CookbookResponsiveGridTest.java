package oj.tests.responsivegrid;

import java.util.NoSuchElementException;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import oj.tests.responsive.CookbookResponsiveTestUtils;

public class CookbookResponsiveGridTest extends OJetBase {
    public CookbookResponsiveGridTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "responsive", "responsivegrid" })
    public void testGridBasics() throws Exception {
        startupTest("demo-gridbasics-basics.html", null);
        verifyTitle("Incorrect page title;", "Grid - Grid");

        waitForElementVisible("id=componentDemo");

        WebDriver driver = getWebDriver();

        _checkBasicGrid(driver, CookbookResponsiveTestUtils.WIDTH_XL, 2);
        _checkBasicGrid(driver, CookbookResponsiveTestUtils.WIDTH_LG, 4);
        _checkBasicGrid(driver, CookbookResponsiveTestUtils.WIDTH_MD, 6);
        _checkBasicGrid(driver, CookbookResponsiveTestUtils.WIDTH_SM, 8);

    }



    @Test(groups = { "cookbook", "responsive", "responsivegrid" })
    public void testNested() throws Exception {
        startupTest("demo-gridbasics-gridnested.html", null);
        verifyTitle("Incorrect page title;", "Grid - Nested Grid");

        waitForElementVisible("id=componentDemo");

        // get the width of the container the grid is in
        int containerWidth = Integer.parseInt(evalJavascript("return $('.demo-grid-display').outerWidth();"));

        // get the width of the first column
        int firstCol = Integer.parseInt(evalJavascript("return $('.demo-grid-display .oj-sm-10').outerWidth()"));

        // get the width of the nested grid
        int nestedGrid = Integer.parseInt(evalJavascript("return $('.demo-grid-display .oj-sm-10 .oj-row').outerWidth()"));

        // get the width of first column of the nested grid
        int nestedColWidth = Integer.parseInt(evalJavascript("return $('.demo-grid-display .oj-sm-10 .oj-row .oj-sm-10').outerWidth()"));
        
        _checkWidths(containerWidth, firstCol, 10);
        _checkWidths(nestedGrid, nestedColWidth, 10);

    }


    @Test(groups = { "cookbook", "responsive", "responsivegrid" })
    public void testOffsets() throws Exception {
        startupTest("demo-gridbasics-gridoffsets.html", null);
        verifyTitle("Incorrect page title;", "Grid - Offset Columns");

        waitForElementVisible("id=componentDemo");

        // get the width of the container the grid is in
        int containerWidth = Integer.parseInt(evalJavascript("return $('.demo-grid-display').outerWidth();"));

        // get the left x position of the container the grid is in
        double containerLeft = Double.parseDouble(evalJavascript("return $('.demo-grid-display').offset().left;"));

        // get the left x position of the offset column in the last row
        double offsetLeft = Double.parseDouble(evalJavascript("return $('.demo-grid-display').find('.oj-row').eq(3).find('.oj-col').eq(0).offset().left"));
  
        // number of offset cols
        int offsetCols = 3;

        // get the offset width
        double offsetWidth = offsetLeft - containerLeft;

        _checkOffsets(offsetCols, containerWidth, offsetWidth);
    }




    @Test(groups = { "cookbook", "responsive", "responsivegrid" })
    public void testCentering() throws Exception {
        startupTest("demo-gridbasics-gridcentered.html", null);
        verifyTitle("Incorrect page title;", "Grid - Centering Columns");

        waitForElementVisible("id=componentDemo");

        // When centered a certain offset width is expected,
        // meaning the width from the left edge of containter to left end of the column,
        // so figure out if the offset width is what we're expecting

        // set the screen width to large
        WebDriver driver = getWebDriver();
        driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, CookbookResponsiveTestUtils.TEST_HEIGHT));

        // get the width of the container the grid is in
        int containerWidth = Integer.parseInt(evalJavascript("return $('.demo-grid-display').outerWidth();"));

        // get the left x position of the container the grid is in
        double containerLeft = Double.parseDouble(evalJavascript("return $('.demo-grid-display').offset().left;"));


        // get the left x position  of the of the "centered large and xlargre screens" column in the second row
        double colLeft = Double.parseDouble(evalJavascript("return $('.oj-lg-center .oj-col').offset().left"));
  
        // number of cols in the centered column
        int numCols = 6;
                              
        // get the offset width
        double offsetWidth = colLeft - containerLeft; 

        // number of offset cols
        double expectedOffsetCols =  (CookbookResponsiveTestUtils.GRID_COLS - numCols)/2.0;

        _checkOffsets(expectedOffsetCols, containerWidth, offsetWidth);



        // now set the screen width to medium,
        // at a medium screen width the same column should no longer be centered
        driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_MD, CookbookResponsiveTestUtils.TEST_HEIGHT));

        // get the width of the container the grid is in
        containerWidth = Integer.parseInt(evalJavascript("return $('.demo-grid-display').outerWidth();"));

        // get the left x position of the container the grid is in
        containerLeft = Double.parseDouble(evalJavascript("return $('.demo-grid-display').offset().left;"));

        // get the left x position  of the of the "centered large and xlargre screens" column in the second row
        colLeft = Double.parseDouble(evalJavascript("return $('.oj-lg-center .oj-col').offset().left"));

        // get the offset width
        offsetWidth = colLeft - containerLeft;
  
        // at a medium screen width the same column should no longer be centered,
        // so the number of offset columns is 0
        expectedOffsetCols =  0;

        _checkOffsets(expectedOffsetCols, containerWidth, offsetWidth);


    }




    @Test(groups = { "cookbook", "responsive", "responsivegrid" })
    public void testPadding() throws Exception {
        startupTest("demo-gridbasics-gridnopad.html", null);
        verifyTitle("Incorrect page title;", "Grid - Remove Padding");

        waitForElementVisible("id=componentDemo");

        //check the padding in the single row demo
        int padding = Integer.parseInt(evalJavascript("return parseInt($('.demo-grid-display.oj-cols-nopad .oj-col').css('padding-left'));"));

        Assert.assertEquals(padding, 0);
        

        //check the padding in the multiple row demo
        padding = Integer.parseInt(evalJavascript("return parseInt($('.oj-cols-nopad .demo-grid-display .oj-col').css('padding-left'));"));

        Assert.assertEquals(padding, 0);
        
    }




    @Test(groups = { "cookbook", "responsive", "responsivegrid" })
    public void testWrapping() throws Exception {
        startupTest("demo-gridbasics-gridwrapping.html", null);
        verifyTitle("Incorrect page title;", "Grid - Wrapping Columns");

        waitForElementVisible("id=componentDemo");

        String startString = "return parseInt($('.demo-grid-display .oj-row .oj-col').eq(";
        String endString = ").offset().top)";

        String col1TopJS = startString + 0 + endString;
        String col2TopJS = startString + 1 + endString;
        String col3TopJS = startString + 2 + endString;

        //------------------------------------------
        // set the screen width to small
        WebDriver driver = getWebDriver();
        driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_SM, CookbookResponsiveTestUtils.TEST_HEIGHT));

        //check the top offset of the first column
        int col1Top = Integer.parseInt(evalJavascript(col1TopJS));

        //check the top offset of the second column
        int col2Top = Integer.parseInt(evalJavascript(col2TopJS));

        //check the top offset of the third column
        int col3Top = Integer.parseInt(evalJavascript(col3TopJS));

        // at small all the columns should be on different lines
        Assert.assertNotEquals(col1Top, col2Top);
        Assert.assertNotEquals(col2Top, col3Top);


        //------------------------------------------
        // set the screen width to medium
        driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_MD, CookbookResponsiveTestUtils.TEST_HEIGHT));

        //check the top offset of the first column
        col1Top = Integer.parseInt(evalJavascript(col1TopJS));

        //check the top offset of the second column
        col2Top = Integer.parseInt(evalJavascript(col2TopJS));

        //check the top offset of the third column
        col3Top = Integer.parseInt(evalJavascript(col3TopJS));

        // at medium the first column is on one line, 
        // and the second two columns are on the next line. 
        Assert.assertNotEquals(col1Top, col2Top);
        Assert.assertEquals(col2Top, col3Top);



        //------------------------------------------
        // set the screen width to large
        driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, CookbookResponsiveTestUtils.TEST_HEIGHT));

        //check the top offset of the first column
        col1Top = Integer.parseInt(evalJavascript(col1TopJS));

        //check the top offset of the second column
        col2Top = Integer.parseInt(evalJavascript(col2TopJS));

        //check the top offset of the third column
        col3Top = Integer.parseInt(evalJavascript(col3TopJS));

        // at large the columns are all on the same line
        Assert.assertEquals(col1Top, col2Top);
        Assert.assertEquals(col2Top, col3Top);
        
    }


    @Test(groups = { "cookbook", "responsive", "responsivegrid" })
    public void testHiding() throws Exception {
        startupTest("demo-gridbasics-gridvisibility.html", null);
        verifyTitle("Incorrect page title;", "Grid - Hiding Columns");

        waitForElementVisible("id=componentDemo");

        String startString = "return $('.demo-grid-display .oj-row .oj-col').eq(";
        String endString = ").css('display')";

        String col1JS = startString + 0 + endString;
        String col2JS = startString + 1 + endString;
        String col3JS = startString + 2 + endString;

        //------------------------------------------
        // set the screen width to small
        WebDriver driver = getWebDriver();
        driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_SM, CookbookResponsiveTestUtils.TEST_HEIGHT));

        //check the display of the columns
        String col1 = evalJavascript(col1JS);
        String col2 = evalJavascript(col2JS);
        String col3 = evalJavascript(col3JS);

        // at small only the first column visible
        Assert.assertNotEquals(col1, "none");
        Assert.assertEquals(col2, "none");
        Assert.assertEquals(col3, "none");

        //------------------------------------------
        // set the screen width to medium
        driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_MD, CookbookResponsiveTestUtils.TEST_HEIGHT));

        //check the display of the columns
        col1 = evalJavascript(col1JS);
        col2 = evalJavascript(col2JS);
        col3 = evalJavascript(col3JS);

        // at medium only the last column hidden
        Assert.assertNotEquals(col1, "none");
        Assert.assertNotEquals(col2, "none");
        Assert.assertEquals(col3, "none");


        //------------------------------------------
        // set the screen width to large
        driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, CookbookResponsiveTestUtils.TEST_HEIGHT));

        //check the display of the columns
        col1 = evalJavascript(col1JS);
        col2 = evalJavascript(col2JS);
        col3 = evalJavascript(col3JS);

        // at large all columns visible
        Assert.assertNotEquals(col1, "none");
        Assert.assertNotEquals(col2, "none");
        Assert.assertNotEquals(col3, "none");
    }



    @Test(groups = { "cookbook", "responsive", "responsivegrid" })
    public void testTwoColConvenience() throws Exception {
        startupTest("demo-gridbasics-gridodds.html", null);
        verifyTitle("Incorrect page title;", "Grid - Two Columns Conveniences");

        waitForElementVisible("id=componentDemo");

        String startString = "return $('.demo-grid-display .oj-md-odd-cols-4 .oj-row .oj-col').eq(";
        String endString = ").outerWidth()";

        String col1JS = startString + 0 + endString;
        String col2JS = startString + 1 + endString;


        WebDriver driver = getWebDriver();

        //------------------------------------------
        // set the screen width to large
        int currBrowserWidth = CookbookResponsiveTestUtils.WIDTH_LG;
        driver.manage().window().setSize(new Dimension(currBrowserWidth, CookbookResponsiveTestUtils.TEST_HEIGHT));


        // get the width of the container the grid is in
        int containerWidth = Integer.parseInt(evalJavascript("return $('.demo-grid-display').outerWidth();"));

        //check the display of the columns
        int col1Width = Integer.parseInt(evalJavascript(col1JS));
        _checkWidths(containerWidth, col1Width, 4, currBrowserWidth);

        int col2Width = Integer.parseInt(evalJavascript(col2JS));
        _checkWidths(containerWidth, col2Width, 8, currBrowserWidth);


        //------------------------------------------
        // set the screen width to small
        currBrowserWidth = CookbookResponsiveTestUtils.WIDTH_SM;
        driver.manage().window().setSize(new Dimension(currBrowserWidth, CookbookResponsiveTestUtils.TEST_HEIGHT));


        // get the width of the container the grid is in
        containerWidth = Integer.parseInt(evalJavascript("return $('.demo-grid-display').outerWidth();"));

        //check the display of the columns
        col1Width = Integer.parseInt(evalJavascript(col1JS));
        _checkWidths(containerWidth, col1Width, 12, currBrowserWidth);

        col2Width = Integer.parseInt(evalJavascript(col2JS));
        _checkWidths(containerWidth, col2Width, 12, currBrowserWidth);
    }





    @Test(groups = { "cookbook", "responsive", "responsivegrid" })
    public void testFourColConvenience() throws Exception {
        startupTest("demo-gridbasics-gridevens.html", null);
        verifyTitle("Incorrect page title;", "Grid - Four Columns Conveniences");

        waitForElementVisible("id=componentDemo");

        String startString = "return $('.demo-grid-display .oj-md-odd-cols-2 .oj-row .oj-col').eq(";
        String endString = ").outerWidth()";

        String col1JS = startString + 0 + endString;
        String col2JS = startString + 1 + endString;
        String col3JS = startString + 2 + endString;
        String col4JS = startString + 3 + endString;


        WebDriver driver = getWebDriver();

        //------------------------------------------
        // set the screen width to large
        int currBrowserWidth = CookbookResponsiveTestUtils.WIDTH_LG;
        driver.manage().window().setSize(new Dimension(currBrowserWidth, CookbookResponsiveTestUtils.TEST_HEIGHT));


        // get the width of the container the grid is in
        int containerWidth = Integer.parseInt(evalJavascript("return $('.demo-grid-display').outerWidth();"));

        //check the display of the columns
        int col1Width = Integer.parseInt(evalJavascript(col1JS));
        _checkWidths(containerWidth, col1Width, 2, currBrowserWidth);

        int col2Width = Integer.parseInt(evalJavascript(col2JS));
        _checkWidths(containerWidth, col2Width, 4, currBrowserWidth);

        int col3Width = Integer.parseInt(evalJavascript(col3JS));
        _checkWidths(containerWidth, col3Width, 2, currBrowserWidth);

        int col4Width = Integer.parseInt(evalJavascript(col4JS));
        _checkWidths(containerWidth, col4Width, 4, currBrowserWidth);


        //------------------------------------------
        // set the screen width to small
        currBrowserWidth = CookbookResponsiveTestUtils.WIDTH_SM;
        driver.manage().window().setSize(new Dimension(currBrowserWidth, CookbookResponsiveTestUtils.TEST_HEIGHT));


        // get the width of the container the grid is in
        containerWidth = Integer.parseInt(evalJavascript("return $('.demo-grid-display').outerWidth();"));

        //check the display of the columns
        col1Width = Integer.parseInt(evalJavascript(col1JS));
        _checkWidths(containerWidth, col1Width, 12, currBrowserWidth);

        col2Width = Integer.parseInt(evalJavascript(col2JS));
        _checkWidths(containerWidth, col2Width, 12, currBrowserWidth);

        col3Width = Integer.parseInt(evalJavascript(col3JS));
        _checkWidths(containerWidth, col3Width, 12, currBrowserWidth);

        col4Width = Integer.parseInt(evalJavascript(col4JS));
        _checkWidths(containerWidth, col4Width, 12, currBrowserWidth);
    }



    private void _checkOffsets(
        double expectedOffsetCols,
        int containerWidth,
        double offsetWidth)
    {

        // number of offset cols
        double expectedOffsetPercentage = expectedOffsetCols/CookbookResponsiveTestUtils.GRID_COLS;

        // get the expected offset width
        double expectedOffsetWidth = containerWidth * expectedOffsetPercentage;

        // check if the width in the range
        // add/subtract a couple pixels for browser funkiness to the max/min
        boolean widthInRange = offsetWidth <  (expectedOffsetWidth + 2) && 
                               offsetWidth >  (expectedOffsetWidth - 2);


        // generate the error message
        String assertErrorMessage = "";
 
        if (widthInRange == false)
        {
          assertErrorMessage = "\ncontainer width:        " + containerWidth + 
                               "\noffset width:           " + offsetWidth + 
                               "\nexpected offset cols:   " + expectedOffsetCols + 
                               "\nexpected percentage:    " + expectedOffsetPercentage + 
                               "\nfound percentage:       " + ((double)offsetWidth)/containerWidth + "\n";   
        }


        Assert.assertTrue(widthInRange, assertErrorMessage);
    }

        
    private void _checkBasicGrid (
        WebDriver driver,
        int browserWidth,
        int colCount
        )
    {


        //------------------------------
        // set the screen width
        driver.manage().window().setSize(new Dimension(browserWidth, CookbookResponsiveTestUtils.TEST_HEIGHT));

        // get the width of the container the grid is in
        int containerWidth = Integer.parseInt(evalJavascript("return $('.demo-grid-display').outerWidth();"));
        // get the width of the first column in the last row.
        int colWidth = Integer.parseInt(evalJavascript("return $('.demo-grid-display').find('.oj-row').eq(3).find('.oj-col').eq(0).outerWidth();"));
        
        _checkWidths(containerWidth, colWidth, colCount, browserWidth);
    }



    private void _checkWidths (
        int containerWidth,
        int colWidth,
        int colCount
        )
    {
        _checkWidths(containerWidth, colWidth, colCount, 0);
    }

    private void _checkWidths (
        int containerWidth,
        int colWidth,
        int colCount,
        int browserWidth
        )
    {
        // expected col width based on the colCount
        double expectedColWidth = containerWidth * (colCount/12.0);

        // add/subtract a couple pixels for browser funkiness to the max/min
        double expectedColWidthMax = expectedColWidth + 2;
        double expectedColWidthMin = expectedColWidth - 2;

        // check if the width in the range
        boolean colWidthInRange = colWidth <  expectedColWidthMax && 
                                  colWidth >  expectedColWidthMin;

        // generate the error message
        String assertErrorMessage = "";
 
        if (colWidthInRange == false)
        {
          if (browserWidth > 0)
          {
            assertErrorMessage = "\nbrowser width:       " + browserWidth;
          }

          assertErrorMessage = assertErrorMessage + 
                               "\ncontainer width:     " + containerWidth + 
                               "\ncolumn width:        " + colWidth + 
                               "\nexpected col count:  " + colCount + 
                               "\nexpected percentage: " + (colCount/12.0) + 
                               "\nfound percentage:    " + ((double)colWidth)/containerWidth + "\n";
        }


        Assert.assertTrue(colWidthInRange, assertErrorMessage);
    }


}
