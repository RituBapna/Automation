package oj.tests.input.text;

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

public class TestInputTextBase extends TestInputBase {

    protected static final String INPUTTEXTGROUP = "inputText";
    protected static final String OJINPUTTEXT = "ojInputText";
    protected static final String OJITWIDGET = "oj-inputtext";
    protected static final String SUBID = "oj-inputtext-input";
    protected static final String valueHolder = "valueHolder";

    public TestInputTextBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp, OJINPUTTEXT, SUBID, valueHolder);
        this.widget = OJITWIDGET;
        this.widgetString = OJITWIDGET + " oj-form-control oj-component";
    }

    public TestInputTextBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp, String exposerId) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp, OJINPUTTEXT, SUBID, valueHolder, exposerId);
        this.widget = OJITWIDGET;
        this.widgetString = OJITWIDGET + " oj-form-control oj-component";
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
        // testValueEntry("90",false); Filed bug 23072971
        testValueEntry("90");

        // 6. Highlight value copy and paste at end and click elsewhere on page
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.CONTROL + "c");
        input.sendKeys(Keys.CONTROL + "v");
        input.sendKeys(Keys.CONTROL + "v");
        submitInput(true);
        verifyInput("9090");

        // 7. Enter unicode
        // testValueEntry("?");  svn seems to have stripped my unicode

        // 8. Click on value button to programmatically change value
        WebElement valueButton = getElement("id=btn_Value");
        actions.moveToElement(valueButton).click().perform();
        verifyInput("");
        actions.moveToElement(valueButton).click().perform();
        verifyInput("30");

        // 9. Enter very long value
        testValueEntry("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

        // 10. Enter odd characters, quotes, wildcards, delimiters, slashes
        testValueEntry("\"\'\\*%&%`~/>$#)(][}{|+-=_@");

        // 11. Enter nothing
        clearInput();
        submitInput(true);
        verifyInput("");

    }


}
