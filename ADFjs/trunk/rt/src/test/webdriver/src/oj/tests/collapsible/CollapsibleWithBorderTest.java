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

public class CollapsibleWithBorderTest extends OJetBase
{
  
  public CollapsibleWithBorderTest()
  {                                               
    super(new TestConfigBuilder().setContextRoot("layoutNavigation").setAppRoot("collapsibleTest").build());
  }

    @Test(groups = { "collapsible" })
  public void collapsibleWithBorderTest()throws Exception
  {
    startupTest("collapsibleWithBorderPage.html",null);
    maximizeWindow();
    String text=null;
   
      verifyTitle("Incorrect page title;", "Collapsible With Header Level Page");
      waitForElementVisible("id=c1");
      waitForMilliseconds(500L);
      waitForElementVisible("id=h1");
      //System.out.println("+++++++++++++"+getWebDriver().findElement(By.tagName("h1")));//child::text()));
      Assert.assertEquals("Header 1",getWebDriver().findElement(By.tagName("h1")).getText());
      Assert.assertTrue(getWebDriver().findElement(By.tagName("h1"))!= null);
      
      waitForElementVisible("id=h4");
      Assert.assertEquals("Header 4",getWebDriver().findElement(By.tagName("h4")).getText());
      Assert.assertTrue(getWebDriver().findElement(By.tagName("h4"))!= null);
      
      
  }
}

