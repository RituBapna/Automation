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

public class CollapsibleMethodTest extends OJetBase
{
  
  public CollapsibleMethodTest()
  {                                               
    super(new TestConfigBuilder().setContextRoot("layoutNavigation").setAppRoot("collapsibleTest").build());
  }

    @Test(groups = { "collapsible" })
  public void collapsibleMethodTest()throws Exception
  {
    startupTest("ojcollapsibleMethodTestPage.html",null);
    maximizeWindow();
    String text=null;
   
      verifyTitle("Incorrect page title;", "Collapsible Method Test");
      waitForElementVisible("id=collapsiblePage");
      waitForMilliseconds(500L);
      
      getElement("id=collaspeButton").click();
      waitForMilliseconds(500L);
      
      Assert.assertEquals("oj-collapsible oj-component oj-component-initnode oj-collapsed",getElement("id=collapsiblePage").getAttribute("class"));
      //System.out.println("++++++++++++++" + getElement("id=collapsiblePage").getAttribute("class"));
      
      getElement("id=expandButton").click();
      waitForMilliseconds(500L);
       
      Assert.assertEquals("oj-collapsible oj-component oj-component-initnode oj-expanded",getElement("id=collapsiblePage").getAttribute("class"));
      //System.out.println("++++++++++++++" + getElement("id=collapsiblePage").getAttribute("class"));
      
      getElement("id=getOptionsButton").click();
      waitForMilliseconds(5000L);

   
  }
}

