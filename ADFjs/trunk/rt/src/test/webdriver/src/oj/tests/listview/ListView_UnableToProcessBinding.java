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

public class ListView_UnableToProcessBinding extends ListViewBase {
    public ListView_UnableToProcessBinding() {
        super();
    }

    @Test(groups = { "cookbook" })
    public void listView_UnableToProcessBinding() throws Exception {
        startupTest("demo-listView-filterSortListView.html", null);
        waitForElementVisible("id=listview");
        this.waitForMilliseconds(500);
        Assert.assertTrue(getWebDriver().findElement(By.className("oj-checkboxset")).isDisplayed());
        Assert.assertTrue(getWebDriver().findElement(By.id("price_filter_lbl")).isDisplayed());
        Assert.assertTrue(getWebDriver().findElement(By.id("author_filter_lbl")).isDisplayed());
        Assert.assertTrue(getWebDriver().findElement(By.id("rating_filter_lbl")).isDisplayed());
        this.waitForMilliseconds(500);
        this.waitForMilliseconds(500);
       
    }
   

           
}
