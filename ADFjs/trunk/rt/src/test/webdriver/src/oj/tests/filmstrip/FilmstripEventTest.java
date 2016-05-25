package oj.tests.filmstrip;

import java.util.List;

import java.util.concurrent.TimeUnit;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Alert;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.Select;

//http://localhost:7101/layoutNavigation/filmstripTest/filmStripMethodTest.html
public class FilmstripEventTest extends OJetBase {

    private static final String TITLE = "Jet ojFilmstrip Test";

    public FilmstripEventTest() {
        super(new TestConfigBuilder().setContextRoot("layoutNavigation").setAppRoot("filmstripTest").build());
    }
    
    
    @Test(groups = { "filmstripTest" })
    public void testFilmstrip() throws Exception {
        //Start the test and bring up the browser
        startupTest("filmstripEventTest.html",null);
        getWebDriver().manage().window().maximize();
        waitForMilliseconds(1000L);
		String url = getBrowserUrl();

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE);
        waitForElementVisible("id=filmStripArrow");
        
        waitForElementVisible("id=selectionDiv");
        this.waitForMilliseconds(1000);
        WebElement destoyFS = getElement("id=destroyButton");
        WebElement createText = getElement("id=createTextId");
        destoyFS.click();
        waitForMilliseconds(1000L);
        WebDriver driver = getWebDriver();
        Alert alertBox=driver.switchTo().alert();
        Assert.assertEquals("destroy", alertBox.getText());
        System.out.println(alertBox.getText());
        alertBox.accept();
        driver.close();
        this.waitForMilliseconds(3000);
       
    }
}

