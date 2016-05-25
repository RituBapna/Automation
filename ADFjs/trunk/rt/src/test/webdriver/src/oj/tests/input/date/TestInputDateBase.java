package oj.tests.input.date;

import java.awt.Component;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import oj.tests.common.TestCompBase;
import oj.tests.input.TestInputBase;
import oj.tests.table.table.TestTableBase;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.NoSuchElementException;
import java.util.List;

public class TestInputDateBase extends TestInputBase {

    protected static final String INPUTDATEGROUP = "inputDate";
    protected static final String OJINPUTDATE = "ojInputDate";
    protected static final String SUBID = "oj-inputdatetime-date-input";
    protected static final String valueHolder = "valueHolder";
    protected static final String CONTENTSUBID = "oj-datepicker-content";
    protected static final String CURRENTSUBID = "oj-datepicker-current";
    protected static final String MONTHSUBID = "oj-datepicker-month";
    protected static final String NEXTICONSUBID = "oj-datepicker-next-icon";
    protected static final String PREVICONSUBID = "oj-datepicker-prev-icon";
    protected static final String YEARSUBID = "oj-datepicker-year";
    protected static final String CALENDARICONSUBID = "oj-inputdatetime-calendar-icon";
    protected static final String OJIDWIDGET = "oj-inputdatetime-date-only";
    protected WebElement content;
    protected WebElement current;
    protected WebElement month;
    protected WebElement nextIcon;
    protected WebElement prevIcon;
    protected WebElement year;
    protected WebElement calendarIcon;


    public TestInputDateBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp,
              OJINPUTDATE, SUBID, valueHolder);
        this.widget = OJIDWIDGET;
        this.widgetString = OJIDWIDGET + " oj-component oj-inputdatetime oj-form-control";

    }

    public TestInputDateBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp, String exposerId) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp,
              OJINPUTDATE, SUBID, valueHolder, exposerId);
        this.widget = OJIDWIDGET;
        this.widgetString = OJIDWIDGET + " oj-component oj-inputdatetime oj-form-control";
    }

    // bug 21227413 - duplicate create and destroy events
    public void createComp() throws Exception {
        super.createComp("Component created : inline-input",
                         "Component created : inline-input",
                         "Component created : text-input",
                         "Component created : text-input");
    }

    public void destroyComp() throws Exception {
        super.destroyComp("Component destroyed : inline-input",
            "Component destroyed : inline-input",
            "Component destroyed : text-input",
            "Component destroyed : text-input");
    }

    public void setup() {
        super.setup();
        setCalendarIcon(getElement("{\"element\":\"#" + myComp + "\",\"subId\":\"" + CALENDARICONSUBID + "\"}"));
        // this.value0a = "2014-06-14T00:00:00.000";
        // this.value1 = "2014-07-03";
        this.value0 = "06/14/14"; // initial value, change has needed;
        this.value0a = "2014-06-14T00:00:00.000"; // initial valu
        this.value1 = "07/03/14";
        this.value1a = "2014-07-03";
    }

    public void postPickerSetup() {
        setContent(getElement("{\"element\":\"#" + myComp + "\",\"subId\":\"" + CONTENTSUBID + "\"}"));
        setNextIcon(getElement("{\"element\":\"#" + myComp + "\",\"subId\":\"" + NEXTICONSUBID + "\"}"));
        setPrevIcon(getElement("{\"element\":\"#" + myComp + "\",\"subId\":\"" + PREVICONSUBID + "\"}"));
        setYear(getElement("{\"element\":\"#" + myComp + "\",\"subId\":\"" + YEARSUBID + "\"}"));
        setMonth(getElement("{\"element\":\"#" + myComp + "\",\"subId\":\"" + MONTHSUBID + "\"}"));
    }

    public void postFooterSetup() {
        setCurrent(getElement("{\"element\":\"#" + myComp + "\",\"subId\":\"" + CURRENTSUBID + "\"}"));
    }

    public void enterInput(String val) throws Exception {
        invokeEditMode();
        super.enterInput(val);
    }

    // Displayed value varies from value of component
    /* public void verifyInput(String val, String displayed) {
        Assert.assertEquals(getInputValue(),displayed);
        Assert.assertEquals(value.getText(),val);
    }*/

    public boolean isDatePickerShown() {
      boolean pickerShown = false;
      // id="2_oj-datepicker-div_wrapper_layer"
      // or class="oj-datepicker-popup" inside div with class oj-popup-layer
      try {
        WebElement popup = getElement("id=2_oj-datepicker-div_wrapper_layer");
        pickerShown = true;
      } catch (NoSuchElementException e) {
        log("date picker not found");
      }

      return pickerShown;
    }

    public void invokeEditMode() {
      getMethod("show");
      getMethod("hide");
    }

    public void invokePicker() {
      getMethod("show");
    }

    public void testValue() throws Exception {
        setup();

        // 1. Verify initial value of nothing
        verifyInput("06/14/14","2014-06-14T00:00:00.000");

        // 2. Enter a value by keyboard and TAB
        clearInput();
        enterInput("07/03/14");
        verifyInput("07/03/14","2014-07-03");

        // 3. Enter a value by keyboard and click off
        clearInput();
        // bug 23072971 blocking, so commenting out until fixed
        // enterInput("07/04/14",false);
        enterInput("07/04/14");
        verifyInput("07/04/14","2014-07-04");

        //45. Click on value button to programmatically change value
        WebElement valueButton = getElement("id=btn_Value");
        actions.moveToElement(valueButton).click().perform();
        actions.moveToElement(valueButton).click().perform();
        verifyInput("07/02/14","2014-07-02T00:00:00.000");

        // 5. Enter nothing
        clearInput();
        submitInput(true);
        verifyInput("");
    }

    public void clearInput() throws Exception {
      invokeEditMode();
      input.sendKeys(Keys.CONTROL + "a");
      input.sendKeys(Keys.DELETE);
    }

    public void dismissPicker() {
        calendarIcon.click();
    }

    public void testPicker() throws Exception {
        setup();

        // 2. Use date picker
        calendarIcon.click();
        waitForMilliseconds(1000L);
        postPickerSetup();
        nextIcon.click(); // move to July 2015
        waitForMilliseconds(1000L);
        WebElement date1 = getElement("oj-dp-2-1-0-0-0"); // 5 th of july
        date1.click();
        waitForMilliseconds(2000L);
        verifyInput("07/05/15 12:00 AM","2015-07-05T00:00:00");

        }

        public void testTitle() throws Exception {
              testTitle("mm/dd/yy","tooltip2");
          }


    public void setContent(WebElement content) {
        this.content = content;
    }

    public WebElement getContent() {
        return content;
    }

    public void setCurrent(WebElement current) {
        this.current = current;
    }

    public WebElement getCurrent() {
        return current;
    }

    public void setMonth(WebElement month) {
        this.month = month;
    }

    public WebElement getMonth() {
        return month;
    }

    public void setNextIcon(WebElement nextIcon) {
        this.nextIcon = nextIcon;
    }

    public WebElement getNextIcon() {
        return nextIcon;
    }

    public void setPrevIcon(WebElement prevIcon) {
        this.prevIcon = prevIcon;
    }

    public WebElement getPrevIcon() {
        return prevIcon;
    }

    public void setYear(WebElement year) {
        this.year = year;
    }

    public WebElement getYear() {
        return year;
    }

    public void setCalendarIcon(WebElement calendarIcon) {
        this.calendarIcon = calendarIcon;
    }

    public WebElement getCalendarIcon() {
        return calendarIcon;
    }

        public void testValidator() throws Exception {
              testValidator("mm/dd/yy","Enter an odd day.","tooltip1");
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
              // Enter Value that fails, an even day
              clearInput();
              enterInput("07/06/14");
              // Verify that parent div has oj-invalid class
              elementHasClass(widgetComp,"oj-invalid");
              // Verify input id has aria-invalid=true
              elementHasAttributeValue(input,"aria-invalid","true");
              // Verify div with class oj-messaging-inline-container now in dom
              Assert.assertTrue(isMessageShown(),"Message shown");
              // Verify div with class oj-message-summary "The number is too high." contents
              // Verify div with class oj-message-detail has child span of "The number must be less than or equal to 100."
              messageShown("Uh oh, not an odd day.","Hey put an odd day");
              // Clear value
              clearInput();
              // Enter Value that passes, an odd day
              enterInput("07/07/14");
              // Verify that parent div no longer has oj-invalid class
              elementHasNoClass(widgetComp,"oj-invalid");
              // Verify that input id has aria-invalid=false
              elementHasAttributeValue(input,"aria-invalid","false");
              // Verify div with class oj-message-summary no longer in dom
              Assert.assertFalse(isMessageShown(),"Message not shown");

          }

          public void myCMClear() {
                clickOnClear();
          }


}
