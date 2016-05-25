package oj.tests.responsive;

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

public class CookbookResponsiveTest extends OJetBase {
    public CookbookResponsiveTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "responsive" })
    public void testResponsiveOptionChangeCustomQuery() throws Exception {
        startupTest("demo-responsiveoptions-optioncustomquery.html", null);
        verifyTitle("Incorrect page title;", "Responsive Option Changes - Custom Queries");

        waitForElementVisible("id=bttnlabel");

        // set the screen width to be smaller than 800px and taller than it is wide
        int height = 600;
        WebDriver driver = getWebDriver();
        driver.manage().window().setSize(
            new Dimension(CookbookResponsiveTestUtils.WIDTH_SM, height));
        
        // see comment for _SLEEP_TIME constant for more info
        sleep(_SLEEP_TIME);

        // get the display value of first button
        String display = evalJavascript("return $('#bttndisplay').ojButton( 'option', 'display' )");
        Assert.assertEquals(display, "icons");


        // get the label value of second button
        String label = evalJavascript("return $('#bttnlabel').ojButton( 'option', 'label' )");
        Assert.assertEquals(label, "portrait");


        // set the screen width to be larger than 800px and wider than it is tall
        driver.manage().window().setSize(
            new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, height));
        
        // see comment for _SLEEP_TIME constant for more info
        sleep(_SLEEP_TIME);

        // get the display value of first button
        display = evalJavascript("return $('#bttndisplay').ojButton( 'option', 'display' )");
        Assert.assertEquals(display, "all");


        // get the label value of second button
        label = evalJavascript("return $('#bttnlabel').ojButton( 'option', 'label' )");
        Assert.assertEquals(label, "landscape");

    }



    @Test(groups = { "cookbook", "responsive" })
    public void testResponsiveOptionChangeFrameworkQuery() throws Exception {
        startupTest("demo-responsiveoptions-optionfwkquery.html", null);
        verifyTitle("Incorrect page title;", "Responsive Option Changes - Framework Queries");

        waitForElementVisible("id=bttnlabel");

        // set the screen width to be small
        WebDriver driver = getWebDriver();
        driver.manage().window().setSize(
            new Dimension(CookbookResponsiveTestUtils.WIDTH_SM, CookbookResponsiveTestUtils.TEST_HEIGHT));
        
        // see comment for _SLEEP_TIME constant for more info
        sleep(_SLEEP_TIME);

        // get the display value of first button
        String display = evalJavascript("return $('#bttndisplay').ojButton( 'option', 'display' )");
        Assert.assertEquals(display, "icons");


        // get the label value of second button
        String label = evalJavascript("return $('#bttnlabel').ojButton( 'option', 'label' )");
        Assert.assertEquals(label, "cal");


        // set the screen width to be medium
        driver.manage().window().setSize(
            new Dimension(CookbookResponsiveTestUtils.WIDTH_MD, CookbookResponsiveTestUtils.TEST_HEIGHT));
        
        // see comment for _SLEEP_TIME constant for more info
        sleep(_SLEEP_TIME);

        // get the label value of second button
        label = evalJavascript("return $('#bttnlabel').ojButton( 'option', 'label' )");
        Assert.assertEquals(label, "calendar");


        // set the screen width to be large
        driver.manage().window().setSize(
            new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, CookbookResponsiveTestUtils.TEST_HEIGHT));
        
        // see comment for _SLEEP_TIME constant for more info
        sleep(_SLEEP_TIME);

        // get the display value of first button
        display = evalJavascript("return $('#bttndisplay').ojButton( 'option', 'display' )");
        Assert.assertEquals(display, "all");


        // get the label value of second button
        label = evalJavascript("return $('#bttnlabel').ojButton( 'option', 'label' )");
        Assert.assertEquals(label, "daily calendar");

    }


    @Test(groups = { "cookbook", "responsive" })
    public void testResponsiveLoading() throws Exception {
        startupTest("demo-responsiveloading-templates.html", null);
        verifyTitle("Incorrect page title;", "Responsive Loading - Template");


        // set the screen width to be small
        WebDriver driver = getWebDriver();
        driver.manage().window().setSize( 
            new Dimension(CookbookResponsiveTestUtils.WIDTH_SM, CookbookResponsiveTestUtils.TEST_HEIGHT));

        // make sure the small template content is visible
        waitForElementVisible("id=sm_md");


        // set the screen width to be large
        driver.manage().window().setSize(
            new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, CookbookResponsiveTestUtils.TEST_HEIGHT));

        // make sure the large template content is visible
        waitForElementVisible("id=lg_xl");

    }


    @Test(groups = { "cookbook", "responsive" })
    public void testResponsiveImageSizeChanges() throws Exception {
        startupTest("demo-responsiveimages-responsivesizing.html", null);
        verifyTitle("Incorrect page title;", "Responsive Image Tag - Size Changes");

        waitForElementVisible("id=puzzle");

        // set the screen width to be small
        WebDriver driver = getWebDriver();
        driver.manage().window().setSize(
            new Dimension(CookbookResponsiveTestUtils.WIDTH_SM, CookbookResponsiveTestUtils.TEST_HEIGHT));

        sleep(_SLEEP_TIME);
        
        // get the src value of image
        String src = evalJavascript("return $('#puzzle').attr('src')");
        Assert.assertEquals(src, "images/responsive/puzzle_small.png");




        // set the screen width to be large
        driver.manage().window().setSize(
            new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, CookbookResponsiveTestUtils.TEST_HEIGHT));
        
        // see comment for _SLEEP_TIME constant for more info
        sleep(_SLEEP_TIME);

       
        // get the src value of image
        src = evalJavascript("return $('#puzzle').attr('src')");
        Assert.assertEquals(src, "images/responsive/puzzle.png");

    }



    // we need to wait for the knockout binding to fire
    // and change the value before we check the value, so 
    // sleeping 250ms after the screen size is changed.
    //
    // We could maybe use something like this
    //
    //     WebDriverWait wait = new WebDriverWait(driver, 1);
    //     wait.until(ExpectedConditions.textToBePresentInElementLocated(
    //                    [dom element to check], "cal"));
    // 
    // 
    // but the default wait time in code above is 500ms, 
    // so 250ms sleep time seems ok.
    private static final long _SLEEP_TIME = 250;

}
