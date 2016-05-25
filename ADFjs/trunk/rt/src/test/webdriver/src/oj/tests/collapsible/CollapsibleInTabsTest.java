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

public class CollapsibleInTabsTest extends OJetBase
{
  
  public CollapsibleInTabsTest()
  {                                               
    super(new TestConfigBuilder().setContextRoot("layoutNavigation").setAppRoot("collapsibleTest").build());
  }

  @Test(groups = { "collapsible" })
  public void testCollapsibleOption()throws Exception
  {
    startupTest("collapsibleInTabPage.html",null);
    maximizeWindow();
    this.waitForMilliseconds(500);
    WebElement collapsibleDiv = getElement("id=collapsiblePage");
    collapsibleDiv.click();
      this.waitForMilliseconds(5000);
      
  }
    
    
}

