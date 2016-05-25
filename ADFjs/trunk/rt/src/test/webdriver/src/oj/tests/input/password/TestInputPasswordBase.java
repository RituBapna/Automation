package oj.tests.input.password;

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

public class TestInputPasswordBase extends TestInputBase {

    protected static final String INPUTPASSWORDGROUP = "inputPassword";
    protected static final String OJINPUTPASSWORD = "ojInputPassword";
    protected static final String OJIPWIDGET = "oj-inputpassword";
    protected static final String SUBID = "oj-inputpassword-input";
    protected static final String valueHolder = "valueHolder";


    public TestInputPasswordBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp,
              OJINPUTPASSWORD, SUBID, valueHolder);
        this.widget = OJIPWIDGET;
        this.widgetString = OJIPWIDGET + " oj-form-control oj-component";
    }

    public TestInputPasswordBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp, String exposerId) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp,
              OJINPUTPASSWORD, SUBID, valueHolder, exposerId);
        this.widget = OJIPWIDGET;
        this.widgetString = OJIPWIDGET + " oj-form-control oj-component";
    }

    public void createComp() throws Exception {
        super.createComp("Component created : text-input");
    }

    public void destroyComp() throws Exception {
        super.destroyComp("Component destroyed : text-input");
    }

    public void setup() {
        super.setup();
    }

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
        testValueEntry("90",false);

        // 6. Highlight value copy and paste at end and click elsewhere on page
/*        actions.moveToElement(value).doubleClick().perform();
        input.sendKeys(Keys.CONTROL + "c");
        actions.moveToElement(input).doubleClick().perform();
        input.sendKeys(Keys.CONTROL + "v");
        input.sendKeys(Keys.CONTROL + "v");
        submitInput(true);
        verifyInput("9090");
*/

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

        public void testValidator() throws Exception {
              super.testValidator("Enter a number less than or equal to 100.",
                "tooltip text");
          }



}
