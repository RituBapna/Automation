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

public class CollapsibleWithouKoBindingTest extends OJetBase
{
  
  public CollapsibleWithouKoBindingTest()
  {                                               
    super(new TestConfigBuilder().setContextRoot("layoutNavigation").setAppRoot("collapsibleTest").build());
  }

    @Test(groups = { "collapsibleWithouKoBinding" })
  public void collapsibleWithouKoBindingTest()throws Exception
  {
    //waitForElementVisible("id=collapsiblePageWithoutKO");
    waitForMilliseconds(500L);
    startupTest("collapsibleWithouKoBindingPage.html",null);
    maximizeWindow();
    //waitForMilliseconds(1000000L);
      
      String  collClass=getElement("id=collapsiblePageWithoutKO").getAttribute("class");//
          System.out.println("++++++++++++++++++++++"+ collClass);
      Assert.assertEquals(collClass, "oj-collapsible oj-component oj-component-initnode");
      
    toggleCollapsible();
    toggleCollapsible();
    
 //      boolean supgold = getElement("{\"element\":\"#collapsibleWithouKoBinding\",\"subId\":\"oj-collapsible-disclosure\"}").isDisplayed();
      //System.out.println("++++++++++++++++++++++"+ supgold);
    //boolean supgold = getElement("{\"element\":\"#myTree\",\"subId\":\"oj-tree-node['#supgold']['title']\"}").isDisplayed();
    //Assert.assertEquals(supgold, true);
       
  }
    public void toggleCollapsible(){
        
      Actions action = new Actions(getWebDriver());
      WebElement we = getWebDriver().findElement(By.xpath("//*[@id='h']"));
      action.moveToElement(we).build().perform();
      waitForMilliseconds(1000L);
    }
}

