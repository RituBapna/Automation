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

public class ListViewNotAllIdsExpanded extends OJetBase {
    public ListViewNotAllIdsExpanded() {
       super(new TestConfigBuilder().setContextRoot("datacollection").setAppRoot("listviewTest").build());
    }

    @Test(groups = { "lvNotAllIdsExpanded" })
    public void listViewNoneToBlock() throws Exception {
        startupTest("listviewNotAllIdsExpanded.html",null);
        waitForElementVisible("id=wrapperDiv");
        this.waitForMilliseconds(1000);
        
        Assert.assertEquals("oj-listview-item-element oj-collapsed",getElement("id=c").getAttribute("class"));
        Assert.assertEquals("oj-listview-item-element oj-expanded",getElement("id=a").getAttribute("class"));
        Assert.assertEquals("oj-listview-item-element oj-expanded",getElement("id=b").getAttribute("class"));
       
        this.waitForMilliseconds(500);
        
        
    }
   

           
}
