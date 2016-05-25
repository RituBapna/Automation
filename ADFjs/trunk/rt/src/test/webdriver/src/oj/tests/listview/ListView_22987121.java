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

public class ListView_22987121 extends ListViewBase {
    public ListView_22987121() {
        super();
    }

    @Test(groups = { "cookbook" })
    public void staticListViewTest() throws Exception {
        startupTest("demo-listView-jsonHierListView.html", null);
        waitForElementVisible("id=listview");
      
        WebElement listViewDiv=getElement("id=listview");
        this.waitForMilliseconds(500);
        String tabId = evalJavascript("return $('#tabs').ojTabs('option', 'selected')");
        String collapsibel = evalJavascript("return $('#listview').ojListView('option','drillMode','collapsible')");
        this.waitForMilliseconds(500);
        getElement("{\"element\":\"#listview\",\"subId\":\"oj-listview-disclosure\",\"key\":\"files\"}").click();
        getElement("{\"element\":\"#listview\",\"subId\":\"oj-listview-disclosure\",\"key\":\"folders\"}").click();
       
        this.waitForMilliseconds(500);
       
        Assert.assertTrue(getWebDriver().findElement(By.id("folders")).isDisplayed());
        Assert.assertTrue(getWebDriver().findElement(By.id("files")).isDisplayed());
        String expanded = evalJavascript("return $('#listview').ojListView('option','expanded',[])");
        
        this.waitForMilliseconds(500);
        
    }
   

           
}
