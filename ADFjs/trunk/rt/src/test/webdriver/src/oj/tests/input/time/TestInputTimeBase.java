package oj.tests.input.time;

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

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.NoSuchElementException;

public class TestInputTimeBase extends TestInputBase {

    protected static final String INPUTTIMEGROUP = "inputTime";
    protected static final String OJINPUTTIME = "ojInputTime";
    protected static final String SUBID = "oj-inputdatetime-time-input";
    protected static final String valueHolder = "valueHolder";
    protected static final String ICONSUBID = "oj-inputdatetime-time-icon";
    protected static final String DROPSUBID = "oj-listbox-drop";
    protected static final String OJITMWIDGET = "oj-inputdatetime-time-only";
    protected WebElement dropListbox;
    protected WebElement timeIcon;


    public TestInputTimeBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp,
              OJINPUTTIME, SUBID, valueHolder);
        this.widget = OJITMWIDGET;
        this.widgetString = OJITMWIDGET + " oj-component oj-inputdatetime oj-form-control";
    }

    public TestInputTimeBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp, String exposerId) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp,
              OJINPUTTIME, SUBID, valueHolder, exposerId);
        this.widget = OJITMWIDGET;
        this.widgetString = OJITMWIDGET + " oj-component oj-inputdatetime oj-form-control";

    }

    // bug 21227413 - duplicate create and destroy events
    public void createComp() throws Exception {
        super.createComp("Component created : text-input",
                         "Component created : text-input");
    }

    public void destroyComp() throws Exception {
        super.destroyComp("Component destroyed : text-input",
                          "Component destroyed : text-input");
    }

    public void setup() {
        super.setup();
        setTimeIcon(getElement("{\"element\":\"#" + myComp + "\",\"subId\":\"" + ICONSUBID + "\"}"));
        setDropListbox(getElement("{\"element\":\"#" + myComp + "\",\"subId\":\"" + DROPSUBID + "\"}"));
        this.value0 = "12:30 PM"; // initial value, change has needed;
        this.value0a = "2014-06-14T12:30:20.000"; // initial valu
        this.value1 = "10:30 AM";
        this.value1a = "T10:30:00";
    }

    // Displayed value varies from value of component
    /* public void verifyInput(String val, String displayed) {
        Assert.assertEquals(getInputValue(),displayed);
        Assert.assertEquals(value.getText(),val);
    }*/

    public void invokeEditMode() {
      getMethod("show");
      getMethod("hide");
    }

    public void invokePicker() {
      getMethod("show");
    }

    public void clearInput() throws Exception {
      invokeEditMode();
      input.sendKeys(Keys.CONTROL + "a");
      input.sendKeys(Keys.DELETE);
    }


    public void dismissPicker() {
        timeIcon.click();
    }

    public void enterInput(String val) throws Exception {
        invokeEditMode();
        super.enterInput(val);
    }


    public void testValue() throws Exception {
        setup();

        // 1. Verify initial value for page of 12:30 PM
        verifyInput("12:30 PM","2014-06-14T12:30:20.000");
        // Assert.assertEquals(getInputValue(),"2014-06-14T12:30:20.000");  // 2014-06-14T12:30:20.000

        // 2. Enter a value by keyboard and TAB
        clearInput();
        enterInput("10:30 AM");
        verifyInput("10:30 AM","T10:30:00");

        // 3. Enter a value by keyboard and click off
        clearInput();
        enterInput("10:30 AM",false);
        verifyInput("10:30 AM","T10:30:00");

        //45. Click on value button to programmatically change value
        WebElement valueButton = getElement("id=btn_Value");
        actions.moveToElement(valueButton).click().perform();
        actions.moveToElement(valueButton).click().perform();
        verifyInput("12:30 PM","2014-07-02T12:30:20.000");

        // 5. Enter nothing
        clearInput();
        submitInput(true);
        verifyInput("");

    }

    public void testPicker() throws Exception {
        setup();

        // 1. Use Picker
        clearInput();
        timeIcon.click();
        WebElement item1 = getElement("id=2_sel0"); // first item in list is 2_sel0, 12:00 AM
        item1.click();
        verifyInput("12:00 AM","T00:00:00");

        // 2. Use arrow down twice
/*        clearInput();
        WebElement focusButton = getElement("id=btn_focus");
        focusButton.click();
        waitForMilliseconds(1000L);
        input.sendKeys(Keys.DOWN);
        waitForMilliseconds(1000L);
        dropListbox.sendKeys(Keys.DOWN);
        dropListbox.sendKeys(Keys.RETURN);
        waitForMilliseconds(1000L);
        verifyInput("T13:00:00","01:00 PM");
*/

    }

        public void testValidator() throws Exception {
              testValidator("hh:mm a","Enter a time divisible by 15.","tooltip1");
            }


        public void testValidator(String ... hints) throws Exception {
              setup();
              // click on Validator, get the odddayvalidator
              clickOnValidator();
              // Verify no div with oj-messaging-popup class in dom
              Assert.assertFalse(isMessageShown(),"Message not shown");
              // Verify input id has aria-invalid = false
              elementHasAttributeValue(input,"aria-invalid","false");
              // Place cursor in input
              clickOnFocus();
              // Clear value
              hintsShown(hints);
              // Enter Value that fails, an non 15 min inc
              clearInput();
              enterInput("11:01 PM");
              // Verify that parent div has oj-invalid class
              elementHasClass(widgetComp,"oj-invalid");
              // Verify input id has aria-invalid=true
              elementHasAttributeValue(input,"aria-invalid","true");
              // Verify div with class oj-messaging-inline-container now in dom
              Assert.assertTrue(isMessageShown(),"Message shown");
              // Verify div with class oj-message-summary "The number is too high." contents
              // Verify div with class oj-message-detail has child span of "The number must be less than or equal to 100."
              messageShown("Uh oh, not an increment value.", "Hey put the increment value");
              // Clear value
              clearInput();
              // Enter Value that passes, an 15 min inc
              enterInput("11:15 PM");
              // Verify that parent div no longer has oj-invalid class
              elementHasNoClass(widgetComp,"oj-invalid");
              // Verify that input id has aria-invalid=false
              elementHasAttributeValue(input,"aria-invalid","false");
              // Verify div with class oj-message-summary no longer in dom
              Assert.assertFalse(isMessageShown(),"Message not shown");

          }



        public void testTitle() throws Exception {
              testTitle("hh:mm a","tooltip2");
          }



    public void setDropListbox(WebElement dropListbox) {
        this.dropListbox = dropListbox;
    }

    public WebElement getDropListbox() {
        return dropListbox;
    }

    public void setTimeIcon(WebElement timeIcon) {
        this.timeIcon = timeIcon;
    }

    public WebElement getTimeIcon() {
        return timeIcon;
    }

    public void myCMClear() {
          clickOnClear();
    }

}
