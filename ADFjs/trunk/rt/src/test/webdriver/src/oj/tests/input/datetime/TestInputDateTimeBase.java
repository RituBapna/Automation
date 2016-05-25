package oj.tests.input.datetime;

import java.awt.Component;

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

public class TestInputDateTimeBase extends TestInputBase {

    protected static final String INPUTDATETIMEGROUP = "inputDateTime";
    protected static final String OJINPUTDATETIME = "ojInputDateTime";
    protected static final String SUBID = "oj-inputdatetime-date-input";
    protected static final String valueHolder = "valueHolder";
    protected static final String ICONSUBID = "oj-inputdatetime-time-icon";
    protected static final String DROPSUBID = "oj-listbox-drop";
    protected static final String CONTENTSUBID = "oj-datepicker-content";
    protected static final String CURRENTSUBID = "oj-datepicker-current";
    protected static final String MONTHSUBID = "oj-datepicker-month";
    protected static final String NEXTICONSUBID = "oj-datepicker-next-icon";
    protected static final String PREVICONSUBID = "oj-datepicker-prev-icon";
    protected static final String YEARSUBID = "oj-datepicker-year";
    protected static final String CALENDARICONSUBID = "oj-inputdatetime-calendar-icon";
    protected static final String OJIDTWIDGET = "oj-inputdatetime-date-time";
    protected WebElement dropListbox;
    protected WebElement timeIcon;
    protected WebElement content;
    protected WebElement current;
    protected WebElement month;
    protected WebElement nextIcon;
    protected WebElement prevIcon;
    protected WebElement year;
    protected WebElement calendarIcon;



    public TestInputDateTimeBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp,
              OJINPUTDATETIME, SUBID, valueHolder);
        this.widget = OJIDTWIDGET;
        this.widgetString = OJIDTWIDGET + " oj-component oj-inputdatetime oj-form-control";

    }

    public TestInputDateTimeBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp, String exposerId) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp,
              OJINPUTDATETIME, SUBID, valueHolder, exposerId);
        this.widget = OJIDTWIDGET;
        this.widgetString = OJIDTWIDGET + " oj-component oj-inputdatetime oj-form-control";

    }

    public void createComp() throws Exception {
        super.createComp("Component created : inline-input",
                         "Component created : text-input",
                         "Component created : text-input");
    }

    public void destroyComp() throws Exception {
        super.destroyComp("Component destroyed : inline-input",
        "Component destroyed : text-input",
        "Component destroyed : text-input");
    }

    public void destroyComp(String msg) throws Exception {
        super.destroyComp(msg, "Component destroyed : inline-input",
        "Component destroyed : text-input",
        "Component destroyed : text-input");
    }


    public void setup() {
        super.setup();
        setTimeIcon(getElement("{\"element\":\"#" + myComp + "\",\"subId\":\"" + ICONSUBID + "\"}"));
        setDropListbox(getElement("{\"element\":\"#" + myComp + "\",\"subId\":\"" + DROPSUBID + "\"}"));
        setCalendarIcon(getElement("{\"element\":\"#" + myComp + "\",\"subId\":\"" + CALENDARICONSUBID + "\"}"));
        this.value0 = ""; // initial value, Displayed;
        this.value0a = ""; // initial value, internal;
        this.value1 = "07/03/14 12:00 AM";
        this.value1a = "2014-07-03T00:00:00";
                // enterInput(value1);
                // verifyInput(value1a,value1);
    }

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

    public void clearInput() throws Exception {
      invokeEditMode();
      input.sendKeys(Keys.CONTROL + "a");
      input.sendKeys(Keys.DELETE);
    }

    public void enterInput(String val) throws Exception {
        invokeEditMode();
        super.enterInput(val);
    }

    public void dismissPicker() {
        calendarIcon.click();
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
    // Displayed value varies from value of component
/*     public void verifyInput(String val, String displayed) {
        Assert.assertEquals(getInputValue(),displayed);
        Assert.assertEquals(value.getText(),val);
    }
*/



    public void testValue() throws Exception {
        setup();

        // 1. Verify initial value of nothing
        verifyInput("");

        // 2. Enter a value by keyboard and TAB
        // clearInput();
        enterInput("07/03/14 12:00 AM");
//         verifyInput("2014-07-03T00:00:00","07/03/14 12:00 AM");
        verifyInput("07/03/14 12:00 AM","2014-07-03T00:00:00");

        // 3. Enter a value by keyboard and click off
        clearInput();
        // bug 23072971 blocking, so commenting out until fixed
//        enterInput("07/04/14 11:00 PM",false);
        enterInput("07/04/14 11:00 PM");
//        verifyInput("2014-07-04T23:00:00","07/04/14 11:00 PM");
        verifyInput("07/04/14 11:00 PM","2014-07-04T23:00:00");

        //45. Click on value button to programmatically change value
        WebElement valueButton = getElement("id=btn_Value");
        actions.moveToElement(valueButton).click().perform();
        actions.moveToElement(valueButton).click().perform();
        // verifyInput("2014-07-02T12:30:20.000","07/02/14 12:30 PM");
        verifyInput("07/02/14 12:30 PM","2014-07-02T12:30:20.000");

        // 5. Enter nothing
        clearInput();
        submitInput(true);
        verifyInput("");
    }

    public void testPicker() throws Exception {
        setup();

        // 1. Use Picker
        // clearInput();
        enterInput("06/16/15 2:00 AM");
        timeIcon.click();
        WebElement item1 = getElement("id=4_sel0"); // first item in list is 4_sel0, 12:00 AM
        item1.click();
          // verifyInput("2015-06-16T00:00:00","06/16/15 12:00 AM");
        verifyInput("06/16/15 12:00 AM","2015-06-16T00:00:00");

        // 2. Use date picker
        calendarIcon.click();
        waitForMilliseconds(1000L);
        postPickerSetup();
        nextIcon.click(); // move to July 2015
        waitForMilliseconds(1000L);
        WebElement date1 = getElement("oj-dp-2-1-0-0-0"); // 5 th of july
        date1.click();
        waitForMilliseconds(2000L);
        // verifyInput("2015-07-05T00:00:00","07/05/15 12:00 AM");
        verifyInput("07/05/15 12:00 AM","2015-07-05T00:00:00");

        // 3. Use drop down selects for month and year
        // TBD

    }

    public void initRequiredValue() {
      // by default, most pages have values.  If they do not, override this and set
      clickOnValue();
    }

/*        public void testRequired() throws Exception {
              setup();
              WebElement w = getMyWidget();
              // click on required
              clickOnRequired();
              clickOnValue();   // date times start with null, so must put value in first
              // Verify label has parent span with title "Required" and class oj-label-required-icon
              labelHasAttributeValue(input,"title","Required");
              labelHasClass(input,"oj-label-required-icon");
              // Verify input id has aria-required=true aria-invalid = false
              elementHasAttributeValue(input,"aria-required","true");
              // verify input parent div has oj-required
              elementHasClass(w,"oj-required");
              // clear input
              clearInput();
              // Tab off to submitInput
              submitInput(true);
              // Verify that parent div has oj-invalid class
              elementHasClass(w,"oj-invalid");
              // Verify input id has aria-invalid=true
              elementHasAttributeValue(input,"aria-invalid","true");
              // Verify div with class oj-messaging-inline-container now in dom
              // Verify div with class oj-message-summary "Value is required." contents
              // Verify div with class oj-message-detail has child span of "You must enter a value."
              messageShown("Value is required.","You must enter a value.");
              // Enter input 10
              clearInput();
              enterInput("07/03/14 12:00 AM");
              // Verify that parent div no longer has oj-invalid class
              elementHasNoClass(w,"oj-invalid");
              // Verify that input id has aria-invalid=false
              elementHasAttributeValue(input,"aria-invalid","false");
              // Verify div with class oj-message-summary no longer in dom
              Assert.assertFalse(isMessageShown(),"Message not shown");
          }
*/
        public void testTitle() throws Exception {
              testTitle("mm/dd/yy hh:mm a","tooltip2");
          }


        public void testValidator() throws Exception {
              testValidator("mm/dd/yy hh:mm a","Enter a time divisible by 15.","tooltip1");
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
              enterInput("07/04/14 11:01 PM");
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
              enterInput("07/04/14 11:15 PM");
              // Verify that parent div no longer has oj-invalid class
              elementHasNoClass(widgetComp,"oj-invalid");
              // Verify that input id has aria-invalid=false
              elementHasAttributeValue(input,"aria-invalid","false");
              // Verify div with class oj-message-summary no longer in dom
              Assert.assertFalse(isMessageShown(),"Message not shown");

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

        public void myCMClear() {
              clickOnClear();
        }


}
