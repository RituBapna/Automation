package oj.tests.input.combobox;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import javax.xml.transform.Source;

import oj.tests.common.TestCompBase;
import oj.tests.input.TestInputBase;
import oj.tests.table.table.TestTableBase;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;

public class TestComboboxBase extends TestInputBase {

    protected static final String COMBOBOXGROUP = "combobox";
    protected static final String OJCOMBOBOX = "ojCombobox";
    protected static final String OJICWIDGET = "oj-combobox";
    protected static final String SUBID = "oj-combobox-input";
    // for jquery purposes, prefix the subid to the id to get the actual input
    protected static final String valueHolder = "curr-value";

    protected static final String siArrow = "oj-combobox-arrow";
    protected static final String siDrop = "oj-combobox-drop";
    protected static final String siInput = "oj-combobox-input";
    protected static final String siResults = "oj-combobox-results";
    protected static final String siSelection = "oj-combobox-selection";
    protected static final String choiceIdPrefix = "oj-combobox-choice";
    protected static final String ChoicePrefix = "ojChoiceId_";


    public TestComboboxBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp, OJCOMBOBOX, SUBID, valueHolder);
        this.widget = OJICWIDGET;
        this.widgetString = OJICWIDGET + " oj-component oj-enabled oj-form-control";
        this.widgetId = ChoicePrefix + "combobox";
    }

    public TestComboboxBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp, String exposerId) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp, OJCOMBOBOX, SUBID, valueHolder, exposerId);
        this.widget = OJICWIDGET;
        this.widgetString = OJICWIDGET + " oj-component oj-enabled oj-form-control";
        this.widgetId = ChoicePrefix + "combobox";
    }

    public void createComp() throws Exception {
        super.createComp("Component created : combobox");
    }

    public void destroyComp() throws Exception {
        super.destroyComp("Component destroyed : combobox");
    }

    // For most tests, you want to ignore the updates
    protected String filterMsg(String actual) {
        String filtered = super.filterMsg(actual);
        filtered = stripMsg(filtered,"rawValue = 30");
        filtered = stripMsg(filtered,"rawValue option change handler");
        return filtered;
      }

    // For comboboxes, they create an inputtext of the following id,
    // so all the regulat operations happen on that; so override the unused one
    public String myComp() {
      return SUBID + "-" + myComp;
    }

    public String myChoiceComp() {
      return choiceIdPrefix + "-" + myComp;
    }

    public String myInputComp() {
      return SUBID + "-" + myComp;
    }

    public WebElement getParentContainer(WebElement myElement) {
        WebElement parentDiv = parentOf(myElement);
        WebElement gparentDiv = parentOf(parentDiv);
        return gparentDiv;
    }

    public void setup() {
        super.setup();
        setInput(getElement("{\"element\":\"#" + myComp + "\",\"subId\":\"" + SUBID + "\"}"));
/*        startupTest(myPage, null);
        showComponent();
        waitForElementVisible(valueHolderLocator());
        setInput(getElement("{\"element\":\"#" + myComp + "\",\"subId\":\"" + SUBID + "\"}"));
        setValue(getElement(valueHolderLocator()));
        setActions(new Actions(getWebDriver())); */
    }

    public void verifyValue(String val) {
        Assert.assertEquals(value.getText(),"[\"" + val + "\"]");
    }


/*    public void verifyInput(String val) {
        String vals = getInputValue();
        Assert.assertEquals(vals, val);
        // Assert.assertEquals(value.getText(),exp);
        String [] vals = value.getText();
        // assert that the number of found <option> elements matches the expectations
        assertEquals(value.getText().size(), vals.length);
        // assert that the value of every <option> element equals the expected value
        for (int i = 0; i < exp.length; i++) {
            assertEquals(exp[i], vals.get(i).getAttribute("value"));
        }
    }
*/


    public void testValue() throws Exception {
        setup();

        // 1. Verify initial value for page of 30 in input and displayed
        Assert.assertEquals(getInputValue(),"30");

        // 2. Enter a value by keyboard and TAB
        clearInput();
        enterInput("60");
        verifyInput("60");

        // 3. Enter a value by keyboard and TAB and see how it copes with browser autocomplete
        clearInput();
        testValueEntry("6");

        // 4. Modify existing value by adding 0 at end
        enterInput("0");
        verifyInput("60");

        // 5. Enter a value by keyboard and click elsewhere on page
        clearInput();
        testValueEntry("30",false);

        // 6. Highlight value copy and paste at end and click elsewhere on page
        input.sendKeys(Keys.chord(Keys.CONTROL,"a"),
            Keys.chord(Keys.CONTROL,"c"),
            Keys.chord(Keys.CONTROL,"v"),
            Keys.chord(Keys.CONTROL,"v"));
        /* Once it goes from selenium to webdriver w promises, we can then the above
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.CONTROL + "c");
        input.sendKeys(Keys.CONTROL + "v");
        input.sendKeys(Keys.CONTROL + "v");
        */
        waitForMilliseconds(1000L);
        submitInput(true);
        verifyInput("3030");

        waitForMilliseconds(1000L);

        // 7. Verify value picked off list for currency matches
        clearInput();
        enterInput("90");
        // verifyInput("$90.00");
        verifyInputValue("$90.00");
        verifyValue("90");

        // 7. Enter unicode
        // testValueEntry("?");  svn seems to have stripped my unicode

        // 8. Click on value button to programmatically change value
        // WebElement valueButton = getElement("id=btn_Value");
        // actions.moveToElement(valueButton).click().perform();
        clickOnValue();
        // verifyInput("$120.00");
        verifyInputValue("$120.00");
        verifyValue("120");
        // actions.moveToElement(valueButton).click().perform();
        clickOnValue();
        // verifyInput("");
        verifyInputValue("");
        Assert.assertEquals(value.getText(),"[]");

        // 9. Enter very long value
        testValueEntry("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

        // 10. Enter odd characters, quotes, wildcards, delimiters, slashes
        // Bug 23102207 to be fixed
        // testValueEntry("\"\'\\*%&%`~/>$#)(][}{|+-=_@");

        // 11. Enter nothing
        clearInput();
        submitInput(true);
        // verifyInput("");
        verifyInputValue("");
        Assert.assertEquals(value.getText(),"[]");


    }

    // Doesn't work on chrome or firefox, not sure why...
    public void testDragAndDrop() throws Exception {
        setup();


        clearInput();
        WebElement source = getElement("id=exposed");
        Action doubleClick = actions.doubleClick(source).build();
        Action manualClick = actions
            .clickAndHold(source)
            .moveToElement(input,5,5)  // offset to make sure dropped in middle of element
            .release()
            .build();
        doubleClick.perform();
        manualClick.perform();
        submitInput(true);

        WebElement nonoji = getElement("id=nonoji");
        Action clickAndDrag = actions.dragAndDrop(nonoji, input).build();
        clickAndDrag.perform();

        verifyInput("exposed ");

    }

    public void testTitle(String ... hints) throws Exception {
          setup();
          // Click on Title button
          clickOnTitle();
          // Verify div with oj-messaging-popup in div with role="tooltip"
          // Verify div with class oj-form-control-hint-title shows up in dom and has
          //   span child with content "tooltip2"
          // clickOnFocus();   Focus doesn't work for comboboxes, just actually type into box
          clearInput();
          hintsShown(hints);
      }

        public void testUpdates() throws Exception {
              testUpdates("added item 123",
                "option change[value]: 123 (from 30)",
                "rawValue = 123",
                "rawValue = 12",
                "rawValue = 1",
                "rawValue = "
                );
        }


}
