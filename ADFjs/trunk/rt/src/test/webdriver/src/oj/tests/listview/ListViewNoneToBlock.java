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

public class ListViewNoneToBlock extends OJetBase {
    public ListViewNoneToBlock() {
       super(new TestConfigBuilder().setContextRoot("datacollection").setAppRoot("listviewTest").build());
    }

    @Test(groups = { "lvNoneToToBlock" })
    public void listViewNoneToBlock() throws Exception {
        startupTest("listviewNoneToBlockPage.html",null);
        waitForElementVisible("id=wrapperDiv");
        this.waitForMilliseconds(100);
       
      //  String displayVal = getElement("id=listview").getCssValue("display");
          
        waitForElementVisible("id=toggleShowHide");
        WebElement toggleShowHide=getElement("id=toggleShowHide");
        toggleShowHide.click();
        
         this.waitForMilliseconds(1000);
        getElement("id=getDisplayVal").click();
        
        Assert.assertEquals("block",getElement("id=loadOutputPage").getText());
        System.out.println("+++++++text++++++++++++++"+getElement("id=loadOutputPage").getText());
        
        this.waitForMilliseconds(1000);
        
        
    }
   

           
}
