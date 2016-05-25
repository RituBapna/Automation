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

public class CollapsibleOptionTest extends OJetBase
{
  
  public CollapsibleOptionTest()
  {                                               
    super(new TestConfigBuilder().setContextRoot("layoutNavigation").setAppRoot("collapsibleTest").build());
  }

  @Test(groups = { "collapsible" })
  public void testCollapsibleOption()throws Exception
  {
    startupTest("ojcollapsibleOptionTestPage.html",null);
    maximizeWindow();
    this.waitForMilliseconds(500);
      
    selectItemfromComboList("disabledOption",0,"disableOutputText","true");
    this.waitForMilliseconds(500);
    
    selectItemfromComboList("disabledOption",1,"disableOutputText","false");
    this.waitForMilliseconds(500);
      
    selectItemfromComboList("expandedValue",1,"expandedOutputText","false");
    this.waitForMilliseconds(500);
      
    selectItemfromComboList("expandArea",0,"expandedAreaOutputText","disclosureIcon");
    this.waitForMilliseconds(1500);
      
    selectItemfromComboList("expandOn",1,"expandOnOutputText","mouseover");
    this.waitForMilliseconds(1000);
      
    
  }
    @Test(groups = { "collapsible" })
  public void setOptionsUsingOptionMethod()throws Exception
  {
    startupTest("ojcollapsibleOptionTestPage.html",null);
    maximizeWindow();
    this.waitForMilliseconds(500);
     
    getElement("id=setOptionsButton").click();
    this.waitForMilliseconds(1000);
     
    Assert.assertEquals("false",evalJavascript("return $('#collapsiblePage').ojCollapsible('option', 'expanded')"));
    evalJavascript("return $('#collapsiblePage').ojCollapsible('option', 'expanded',true)");
    this.waitForMilliseconds(1000);
    Assert.assertEquals("true",evalJavascript("return $('#collapsiblePage').ojCollapsible('option', 'expanded')"));
  }
    
    
    @Test(groups = { "collapsible" })
  public void testGetOptions()throws Exception
  {
    startupTest("ojcollapsibleOptionTestPage.html",null);
    maximizeWindow();
    String text=null;
  
    getElement("id=selectedOptionsButton").click();
    this.waitForMilliseconds(1000);
      
    text=getElement("id=disableOutputText").getText();
    System.out.println("++++++++++++++++++"+text);
    Assert.assertTrue(text.contains("By Getter Option Disabled:: false"));
    this.waitForMilliseconds(1000);
      
    text=getElement("id=expandedOutputText").getText();
    System.out.println("++++++++++++++++++"+text);
    Assert.assertTrue(text.contains("By Getter Option Expanded:: true"));
    this.waitForMilliseconds(1000);
      
    text=getElement("id=expandedAreaOutputText").getText();
    System.out.println("++++++++++++++++++"+text);
    Assert.assertTrue(text.contains("By Getter Option Expand Area:: header"));
    this.waitForMilliseconds(1000);
      
    text=getElement("id=expandOnOutputText").getText();
    System.out.println("++++++++++++++++++"+text);
    Assert.assertTrue(text.contains("By Getter Option Expand On:: click"));
    this.waitForMilliseconds(1000);
    
  }
    
    
    public void containsStringCheck(String str1, String id) {
        String text=getElement("id="+id).getText();
        Assert.assertTrue( text.contains(str1));
    }

public void selectItemfromComboList(String itemIdToBeClicked, int itemNoToselect, String outputTextFiledId, String valueTocompare){

        WebElement comboboxInput =  getElement("{\"element\":\"#" +itemIdToBeClicked+"\",\"subId\":\"oj-combobox-arrow\"}");
        comboboxInput.click();
        WebElement resultset =  getElement("{\"element\":\"#" +itemIdToBeClicked + "\",\"subId\":\"oj-combobox-results\"}");
        List<WebElement> listItems = resultset.findElements(By.tagName("li"));
        listItems.get(itemNoToselect).click();
        String text=getElement("id="+outputTextFiledId).getText();
        System.out.println("++++++++++++++++++"+text);
        Assert.assertTrue(text.contains(valueTocompare));
        waitForMilliseconds(500L);
    }
}

