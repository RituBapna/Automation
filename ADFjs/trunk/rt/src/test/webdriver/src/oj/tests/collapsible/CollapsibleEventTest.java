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

public class CollapsibleEventTest extends OJetBase
{
  
  public CollapsibleEventTest()
  {                                               
    super(new TestConfigBuilder().setContextRoot("layoutNavigation").setAppRoot("collapsibleTest").build());
  }

    @Test(groups = { "collapsible" })
  public void testEventsUsingJqueryOn()throws Exception
  {
    startupTest("ojcollapsibleOptionTestPage.html",null);
    maximizeWindow();
    String text=null;
  
    //verifyTitle("Incorrect page title;", "Collapsible Test");
    waitForElementVisible("id=collapsiblePage");
    WebElement collapsibleDiv = getElement("id=collapsiblePage");
	WebElement parentCollapsibleDiv = getElement("{\"element\":\"#collapsiblePage\",\"subId\":\"oj-collapsible-disclosure\"}");
    
   //Collapse the already opened outer parent collapsible 
    parentCollapsibleDiv.click();
    this.waitForMilliseconds(1000);
    
    String beforeTriggeredFuncName=getElement("id=functionName1").getText();
    Assert.assertTrue(beforeTriggeredFuncName.equals("ojbeforecollapse"));
    System.out.println("++++++++++++++++++"+beforeTriggeredFuncName);
    this.waitForMilliseconds(1000);
      
    Assert.assertTrue(getElement("id=functionName2").getText().equals("ojcollapse"));
      
    //expand collapsible
    parentCollapsibleDiv.click();
    this.waitForMilliseconds(1000);
      
    Assert.assertTrue(getElement("id=functionName1").getText().equals("ojbeforeexpand"));
    Assert.assertTrue(getElement("id=functionName2").getText().equals("ojexpand"));
  }
}

