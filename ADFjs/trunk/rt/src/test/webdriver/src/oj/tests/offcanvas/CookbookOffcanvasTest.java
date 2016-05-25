package oj.tests.offcanvas;
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

public class CookbookOffcanvasTest extends OffcanvasBase {
    public CookbookOffcanvasTest() {
        super();
    }

    @Test(groups = { "cookbook" })
    public void offcanvasBasicTest() throws Exception {
        startupTest("demo-offcanvas-basic.html", null);
        verifyTitle("Incorrect page title;","Offcanvas - Basic");
        waitForElementVisible("id=parentDiv");
        waitForElementVisible("id=start");
        waitForElementVisible("id=end");
        waitForElementVisible("id=top");
        waitForElementVisible("id=bottom");
        
        openDrawer("start");
        canvasValueCheck("startDrawer","oj-offcanvas-open","oj-offcanvas-start");
        
        openDrawer("end");
        canvasValueCheck("endDrawer","oj-offcanvas-open","oj-offcanvas-end");
        
        openDrawer("top");
        canvasValueCheck("topDrawer","oj-offcanvas-open","oj-offcanvas-top");
        
        openDrawer("bottom");
        canvasValueCheck("bottomDrawer","oj-offcanvas-open","oj-offcanvas-bottom");
        
        /**
        * select push radio button 
        * check class applied to the ckicked drawer has default class 
        * applied class to drawer is="oj-offcanvas-start oj-panel oj-panel-alt5 oj-offcanvas-open "
        **/
        //*[@id="bottomDrawer"]
        getElement("id=pushopt").click();
        waitForMilliseconds(2000L);
        getElement("id=start").click();
        waitForMilliseconds(2000L);
        String pushClass=getElement("id=startDrawer").getAttribute("class");
        Assert.assertTrue(pushClass.equals("oj-offcanvas-start oj-panel oj-panel-alt5 oj-offcanvas-open"));
        getElement("id=start").click();
        
        /**
        * select overlay radio button 
        * check class applied to the ckicked drawer has default class 
        * applied class to drawer is="oj-offcanvas-start oj-panel oj-panel-alt5 oj-offcanvas-open oj-offcanvas-overlay"
        **/
        
        getElement("id=overlayopt").click();
        waitForMilliseconds(2000L);
        getElement("id=end").click();
        waitForMilliseconds(3000L);
        String overlayClass=getElement("id=endDrawer").getAttribute("class");
        Assert.assertTrue(overlayClass.equals("oj-offcanvas-end oj-panel oj-panel-alt4 oj-offcanvas-open oj-offcanvas-overlay"));
        System.out.println("++--"+ getElement("id=endDrawer").getAttribute("class"));
    }
    
   
    
    @Test(groups = { "cookbook" })
    public void offcanvasDismissalTest() throws Exception {
        startupTest("demo-offcanvas-dismissal.html", null);
        verifyTitle("Incorrect page title;","Offcanvas - Dismissal");
        waitForElementVisible("id=parentDiv");
        waitForElementVisible("id=iWrapper");
        WebElement offcanvasWrapperDiv = getElement("id=iWrapper");
        
        toggleDismissalCanvas("Toggle outer offcanvas");
        toggleDismissalCanvas("Toggle inner offcanvas");
        WebElement innerDrawer= getElement("innerDrawer");
        Assert.assertTrue(innerDrawer.isDisplayed());
        toggleDismissalCanvas("Close inner offcanvas");
        try{
        Assert.assertFalse(innerDrawer.isDisplayed());
        }
        catch(Exception e){
            }
       
    }
}
