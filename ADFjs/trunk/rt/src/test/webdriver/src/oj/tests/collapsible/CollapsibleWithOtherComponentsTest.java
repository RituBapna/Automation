package oj.tests.collapsible;

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

public class CollapsibleWithOtherComponentsTest extends OJetBase
{
  
  public CollapsibleWithOtherComponentsTest()
  {                                               
    super(new TestConfigBuilder().setContextRoot("layoutNavigation").setAppRoot("collapsibleTest").build());
  }

    @Test(groups = { "collapsible" })
  public void collapsibleWithOtherComponentsTest()throws Exception
  {
    startupTest("collapsibleWithOtherComponentsPage.html",null);
    maximizeWindow();
    String text=null;
   
        verifyTitle("Incorrect page title;", "Collapsible With Other JET Components Page");
        waitForElementVisible("id=collapsiblePage");
        waitForMilliseconds(500L); 
        
        waitForElementVisible("id=dvtElement");
        waitForMilliseconds(500L);
        WebElement dvtElementCollapsible = getElement("{\"element\":\"#dvtElement\",\"subId\":\"oj-collapsible-disclosure\"}");
        dvtElementCollapsible.click();
        waitForMilliseconds(500L);
        Assert.assertTrue(getWebDriver().findElements( By.id("chart-container") ).size() != 0);
        dvtElementCollapsible.click();      

        waitForElementVisible("id=formElement");
        waitForMilliseconds(500L);
        WebElement formElementCollapsible = getElement("{\"element\":\"#formElement\",\"subId\":\"oj-collapsible-disclosure\"}");
        formElementCollapsible.click();
        waitForMilliseconds(500L);
        Assert.assertTrue(getWebDriver().findElements( By.id("text-input") ).size() != 0);
        formElementCollapsible.click();
      
        waitForElementVisible("id=validatorElement");
        waitForMilliseconds(500L);
        WebElement validatorElementCollapsible = getElement("{\"element\":\"#validatorElement\",\"subId\":\"oj-collapsible-disclosure\"}");
        validatorElementCollapsible.click();
        waitForMilliseconds(500L);
        Assert.assertTrue(getWebDriver().findElements( By.id("length1") ).size() != 0);
      
        getElement("{\"element\":\"#length1\",\"subId\":\"oj-inputtext-input\"}").click();
      
        getWebDriver().findElement(By.name("length1")).sendKeys("AAAAAAAAAAAA"); 
        getWebDriver().findElement(By.name("length1")).sendKeys(Keys.ENTER);
      
        waitForMilliseconds(500L);
        String errorColorRED = getElement("id=length1").getCssValue("border-color");
        Assert.assertEquals("rgb(221, 102, 102)",errorColorRED);//RED COLOR
        System.out.println("+++++++++++++++++++"+errorColorRED);
      
      //TAB
        WebElement tab = getElement("{\"element\":\"#tabs\",\"index\":1,\"subId\":\"oj-tabs-tab\"}");
        tab.click();
        Assert.assertTrue(getWebDriver().findElements( By.id("tabCollapsible") ).size() != 0);
        waitForMilliseconds(5000L);
  }
}

