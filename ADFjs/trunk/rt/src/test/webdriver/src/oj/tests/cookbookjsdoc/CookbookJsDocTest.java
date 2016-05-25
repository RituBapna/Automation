package oj.tests.cookbookjsdoc;

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

public class CookbookJsDocTest extends OJetBase {
    public CookbookJsDocTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components").setAppRoot("public_html/jsdocs").build());
    }

    @Test(groups = { "cookbook" })
    public void cookbookDialogJsDocTest() throws Exception {
        startupTest("oj.ojDialog.html", null);
        verifyTitle("Incorrect page title;","JSDoc: Class: ojDialog");
        waitForElementVisible("id=touch-section");
        waitForMilliseconds(200L);
        waitForElementVisible("id=keyboard-section");
        
    }
    @Test(groups = { "cookbook" })
    public void cookbookNavlistJsDocTest() throws Exception {
        startupTest("oj.ojNavigationList.html", null);
        verifyTitle("Incorrect page title;","JSDoc: Class: ojNavigationList");
        waitForElementVisible("id=touch-section");
        waitForMilliseconds(200L);
        waitForElementVisible("id=keyboard-section");
        
    }
           
}
