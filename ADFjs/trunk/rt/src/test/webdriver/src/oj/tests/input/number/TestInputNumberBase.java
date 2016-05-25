package oj.tests.input.number;

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

public class TestInputNumberBase extends TestInputBase {

    protected static final String INPUTNUMBERGROUP = "inputNumber";
    protected static final String OJINPUTNUMBER = "ojInputNumber";
    protected static final String OJINWIDGET = "oj-inputnumber";
    protected static final String SUBID = "oj-inputnumber-input";
    protected static final String valueHolder = "curr-value";
    protected WebElement upArrow;
    protected WebElement downArrow;


    public TestInputNumberBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp,
              OJINPUTNUMBER, SUBID, valueHolder);
        this.widget = OJINWIDGET;
        this.widgetString = OJINWIDGET + " oj-component oj-form-control";
    }

    public TestInputNumberBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp, String exposerId) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp,
              OJINPUTNUMBER, SUBID, valueHolder, exposerId);
        this.widget = OJINWIDGET;
        this.widgetString = OJINWIDGET + " oj-component oj-form-control";
    }

    public void createComp() throws Exception {
        super.createComp("Component created : inputnumber-id");
    }

    public void destroyComp() throws Exception {
        super.destroyComp("Component destroyed : inputnumber-id");
    }

    public void setup() {
        super.setup();
        upArrow = getElement("{\"element\":\"#" + myComp + "\",\"subId\":\"oj-inputnumber-up\"}");
        downArrow = getElement("{\"element\":\"#" + myComp + "\",\"subId\":\"oj-inputnumber-down\"}");
    }

    // For most tests, you want to ignore the updates
/*    protected String filterMsg(String actual) {
        String filtered = super.filterMsg(actual);
        filtered = stripMsg(filtered," oj-focus");
        return filtered;
      }
*/

    public void testValue() throws Exception {
        setup();

        // 1. Verify initial value for page of 30 in input and displayed
        Assert.assertEquals(getInputValue(),"30");

        // 2. Enter a value by keyboard and TAB
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(Keys.BACK_SPACE);
        enterInput("60");
        verifyInput("60");

        // 3. Enter a value by keyboard and TAB and see how it copes with browser autocomplete
        testValueEntry("3");

        // 4. Modify existing value by adding 0 at end
        enterInput("0");
        verifyInput("30");

        // 5. Enter a value by keyboard and click elsewhere on page
        // Bug 23072971 needs to be fixed
        // testValueEntry("90",false);

        // 6. Highlight value copy and paste at end and click elsewhere on page
        clearInput();
        enterInput("3");
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.CONTROL + "c");
        input.sendKeys(Keys.CONTROL + "v");
        input.sendKeys(Keys.CONTROL + "v");
        submitInput(true);
        verifyInput("33");

        // 7. Enter unicode
        // testValueEntry("?");  svn seems to have stripped my unicode

        // 8. Click on value button to programmatically change value
        WebElement valueButton = getElement("id=btn_Value");
        actions.moveToElement(valueButton).click().perform();
        actions.moveToElement(valueButton).click().perform();
        verifyInput("30");

        // 11. Enter nothing
        clearInput();
        submitInput(true);
        verifyInput("");

    }

    public void testSpinner() throws Exception {
        setup();
        upArrow.click();
        verifyInput("40");
        downArrow.click();
        verifyInput("30");
    }

    public void testTitle() throws Exception {
        super.testTitle("Enter a number between -20 and 100.","tooltip2");
    }

    public void testUpdates() throws Exception {
        setup(true);
        // Since this inputnum limits to 100, use 100 instead of 123 default
        // Type in value 100 and submit
        clearInput();
        enterInput("100");
        waitForMilliseconds(800L);
        // Verify log shows:

        assertMsgLog("option change[value]: 100 (from 30)",
          "rawValue = 100",
          "rawValue option change handler",
          "rawValue = 10",
          "rawValue option change handler",
          "rawValue = 1",
          "rawValue option change handler",
          "rawValue = ",
          "rawValue option change handler");
      }

        public void testValidator() throws Exception {
              super.testValidator("Enter a number between -20 and 100.",
                "Enter a number less than or equal to 100.","tooltip text");
          }



}
