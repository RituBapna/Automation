package oj.tests.offcanvas;
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

public class OffcanvasBase extends OJetBase {
   public OffcanvasBase() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    } 
 
    public void  openDrawer(String id) {
        WebElement selectedDrawer = getElement(id);
        selectedDrawer.click();
    }
    
    public void canvasValueCheck(String... args) {
        waitForMilliseconds(2000L);
        WebElement currentOpenDrawer = getElement(args[0]);
        Assert.assertEquals("block", currentOpenDrawer.getCssValue("display"));
        Assert.assertTrue( currentOpenDrawer.getAttribute("class").contains(args[1]));
        Assert.assertTrue( currentOpenDrawer.getAttribute("class").contains(args[2]));  
        if(args.length==5){
            Assert.assertTrue( currentOpenDrawer.getAttribute("class").contains(args[3]));
        }
    }
    
    public void  toggleDismissalCanvas(String str) {
        WebElement wrpperDiv = getElement("id=iWrapper");//Toggle outer
        List<WebElement> buttons = wrpperDiv.findElements(By.tagName("button"));
        String buttonTitle="";
        for (WebElement button : buttons) {
            buttonTitle = button.getAttribute("title");
            if (buttonTitle.equals(str)) {
                button.click();
                waitForMilliseconds(2000L);
            }

        }
    }
}
