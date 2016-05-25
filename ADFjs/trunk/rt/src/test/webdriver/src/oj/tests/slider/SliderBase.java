package oj.tests.slider;
import java.util.List;
import java.util.NoSuchElementException;

import oracle.ojet.automation.test.OJetBase;// locationD:\OJET\ADFjs\trunk\built\test\webdriver\classes\oracle\ojet\automation
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

public class SliderBase extends OJetBase {
   public SliderBase() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    } 
 
    public  WebElement getCollapseExpandIcon(String componentId,String subId,String Key) {
        //WebElement expandCollapseIcon = getElement("{\"element\":\"#listview\",\"subId\":\"oj-listview-icon\",\"key\":\"a\"}");
        WebElement expandCollapseIcon = getElement("{\"element\":\"#"+componentId+"\",\"subId\":\""+ subId+"\",\"key\":\""+Key+"\"}");
        return expandCollapseIcon;
    }
    
    public  void isTrue(String id,String className) {
        //Assert.assertTrue(getElement("id=andyjones").getAttribute("class").contains("oj-selected"));
        Assert.assertTrue(getElement("id="+id).getAttribute("class").contains(className));
    }
    
    public  void isEqual(String className,String id ) {
        //Assert.assertEquals("oj-listview-item-element oj-collapsed",getElement("id=a").getAttribute("class"));
        Assert.assertEquals(className,getElement("id="+id).getAttribute("class"));
    }
     public  void optionEqualCheck(String option,String value ) {
        //Assert.assertEquals("oj-listview-item-element oj-collapsed",getElement("id=a").getAttribute("class"));
        Assert.assertEquals(option,value);
    }
    
}
