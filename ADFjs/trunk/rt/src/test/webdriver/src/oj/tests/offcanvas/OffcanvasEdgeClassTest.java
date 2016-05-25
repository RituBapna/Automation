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

public class OffcanvasEdgeClassTest  extends OffcanvasBase {
    public OffcanvasEdgeClassTest () {
        super();
    }

    @Test(groups = { "cookbook" })
    public void offcanvasEdgeClassTest () throws Exception {
        startupTest("uiComponents-offcanvas-basic.html", null);
        verifyTitle("Incorrect page title;","Offcanvas - Basic");
        waitForElementVisible("id=parentDiv");
        
        waitForMilliseconds(1000L);
        
        WebElement startDrawer = getElement("id=startDrawer");
        
        String position=startDrawer.getCssValue("position");
        String box_sizing=startDrawer.getCssValue("box-sizing");
        String border_radius=startDrawer.getCssValue("border-radius");
        String display=startDrawer.getCssValue("display");
        
        Assert.assertEquals("absolute", startDrawer.getCssValue("position"));
        Assert.assertEquals("border-box", startDrawer.getCssValue("box-sizing"));
        Assert.assertEquals("0px", startDrawer.getCssValue("border-radius"));
        Assert.assertEquals("none", startDrawer.getCssValue("display"));
        
        System.out.println("+++style+++++" +position + ", "+box_sizing + ", "+border_radius + ", "+ display);
        
    }
   
}
