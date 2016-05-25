package oj.tests.input.ojswitch;

import oj.tests.input.TestInputBase;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import oj.tests.common.TestCompBase;
import oj.tests.input.TestInputBase;
import oj.tests.table.table.TestTableBase;
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;

public class TestSwitchBase extends TestInputBase {

    protected static final String SWITCHGROUP = "switch";
    protected static final String OJSWITCH = "ojSwitch";
    protected static final String SUBID = "oj-switch-input";
    protected static final String valueHolder = "curr-value";
    protected static final String OJSWWIDGET = "oj-switch";
    protected static final String thumb = "oj-switch-thumb";
    protected static final String track = "oj-switch-track";
    protected WebElement myThumb;
    protected WebElement mySwitch;

    public TestSwitchBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp, OJSWITCH, SUBID, valueHolder);
        this.widget = OJSWWIDGET;
        this.widgetString = OJSWWIDGET + " oj-component oj-form-control oj-selected";
        this.widgetClass = "oj-switch-container";
    }

    public TestSwitchBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp, String exposerId) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp, OJSWITCH, SUBID, valueHolder, exposerId);
        this.widget = OJSWWIDGET;
        this.widgetString = OJSWWIDGET + " oj-component oj-form-control oj-selected";
        this.widgetClass = "oj-switch-container";
    }

    public void createComp() throws Exception {
        super.createComp("Component created : switch");
    }

    public void destroyComp() throws Exception {
        super.destroyComp("Component destroyed : switch");
    }


    public void setup() {
        super.setup();
        this.mySwitch = getMyWidget();
        this.myThumb = this.mySwitch.findElement(By.className("oj-switch-thumb"));
    }

    public String getInputValue() {
        return getInputValue(false);
    }

    public void toggleInput() {
      // by default, use mouse
      toggleInput(true);
    }

    public void toggleInput(boolean mouse) {
      if(mouse)
        this.mySwitch.click();
      else
        this.myThumb.sendKeys(Keys.SPACE);
    }

    public void enterInput(String val, boolean tab) throws Exception {
        if(getInputValue() == "true") {
          if(val == "false")
            toggleInput();
        } else {
          if(val == "true")
            toggleInput();
        }
        // submitInput(tab);  Submit doesn't matter for switch
    }

    public void testValue() throws Exception {
        setup();

        WebElement w = getMyWidget();

        // 1. Verify initial value for page of 30 in input and displayed
        Assert.assertEquals(getInputValue(),"true");

        // 2. Enter a value by keyboard SPACE onto thumb
        WebElement t = w.findElement(By.className("oj-switch-thumb"));
        t.sendKeys(Keys.SPACE);  // toggleInput(false);
        verifyInput("false","OFF");
        t.sendKeys(Keys.SPACE);  // toggleInput(false);
        verifyInput("true","ON");

        // 3. Enter a value by mouse
        w.click();    // toggleInput(true);
        verifyInput("false","OFF");
        w.click();   // toggleInput(true);
        verifyInput("true","ON");

        // 8. Click on value button to programmatically change value
        WebElement valueButton = getElement("id=btn_Value");
        actions.moveToElement(valueButton).click().perform();
        verifyInput("false","OFF");
        actions.moveToElement(valueButton).click().perform();
        verifyInput("true","ON");

    }

      public void clickOnFocus() {
          this.myThumb.sendKeys("");
          // t.setFocus();
          // Can do directly using :
          // getJq(myComp(), "focus");
          // Not testing the focus button on our app pages :)
          // clickOn("id=btn_focus");
      }


        public void testReadOnly() throws Exception {
            setup();
            // Click on read only
            clickOnReadOnly();
            // Sendkeys and see if you can write to it
            try {
              toggleInput();
            } catch (Exception e) {
              // Could get org.openqa.selenium.InvalidElementStateException
            }
            verifyInput("true","ON");  // initial value was 30, verify it is unchanged

            // Click on read only again
            clickOnReadOnly();
            // Sendkeys and see if you can write to its
            toggleInput();
            verifyInput("false","OFF");  // initial value was 30, verify it is uhnchanged
          }



}
