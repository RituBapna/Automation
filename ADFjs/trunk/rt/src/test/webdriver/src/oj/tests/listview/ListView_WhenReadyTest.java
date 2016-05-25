/*
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

public class ListView_WhenReadyMethodAdded extends ListViewBase {
    public ListView_WhenReadyMethodAdded() {
        super();
    }

    @Test(groups = { "cookbook" })
    public void staticListViewTest() throws Exception {
        startupTest("demo-listView-collectionListView.html", null);
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
*/
package oj.tests.listview;

import java.util.List;
import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.Select;

public class ListView_WhenReadyTest extends OJetBase {
    private static final String TITLE = "Jet ojListViewEvent Test";
    private static final String CURRENTITEM_COMBOBOX_ID = "currentItemCombo"; //currentItem combo box id
    private static final String DRILLMODE_COMBOBOX_ID= "drillModeCombo";
    private static final String SELECTIONMODE_COMBOBOX_ID= "selectionModeId";
    public ListView_WhenReadyTest() {
        super(new TestConfigBuilder().setContextRoot("datacollection").setAppRoot("listviewTest").build());
    }
    
    @Test(groups = { "listviewTest" })
    public void testListview() throws Exception {
        startupTest("listview_whenReadyPage.html",null);
        getWebDriver().manage().window().maximize();
        String url = getBrowserUrl();
        
        System.out.println("++++++url++++++++"+url);
       // getElement("{\"element\":\"#listview\",\"subId\":\"oj-listview-disclosure\",\"key\":\"a\"}").click();
         waitForMilliseconds(2000L);
        waitForElementVisible("id=buttonBar");
        WebElement promiseButton=getElement("id=promiseButton");
        promiseButton.click();
        waitForMilliseconds(2000L);
        String promiseObj=getElement("id=promiseReturnedText").getText();
         waitForMilliseconds(2000L);
        Assert.assertEquals("Promise returned",promiseObj);
        System.out.println("++++++++++++++ele+++++++++"+promiseObj);
       // getElement("{\"element\":\"#listview\",\"subId\":\"oj-listview-disclosure\",\"key\":\"a\"}").click();
         waitForMilliseconds(200L);
        
        
        
        
       
        
    }
    
    
}


