package oj.tests.input.select;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;
import java.util.List;
import org.testng.Assert;


public class TestSelectBase extends TestInputBase {

    protected static final String SELECTGROUP = "select";
    protected static final String OJSELECT = "ojSelect";
    protected static final String SUBID = "oj-select-input";
    protected static final String OJISWIDGET = "oj-select";
    protected static final String valueHolder = "curr-value";
    protected static final String CHOICEDIVPREFIX = "oj-select-choice";
    protected static final String LISTBOXINPUTCLASS = "oj-listbox-input";
    protected static final String LISTBOXOPTIONS = "oj-listbox-result-selectable";
    protected static final String ChoicePrefix = "ojChoiceId_";

    protected static final String SUBIDARROW = "oj-select-arrow";

    protected WebElement arrow;

    public TestSelectBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp, OJSELECT, SUBID, valueHolder);
        this.widget = OJISWIDGET;
        this.widgetString = OJISWIDGET + " oj-component oj-enabled oj-select-jet oj-form-control";
        this.widgetId = ChoicePrefix + "select";
    }

    public TestSelectBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp, String exposerId) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp, OJSELECT, SUBID, valueHolder, exposerId);
        this.widget = OJISWIDGET;
        this.widgetString = OJISWIDGET + " oj-component oj-enabled oj-select-jet oj-form-control";
        this.widgetId = ChoicePrefix + "select";
    }

    public void createComp() throws Exception {
        super.createComp("Component created : select");
    }

    public void destroyComp() throws Exception {
        super.destroyComp("Component destroyed : select");
    }

    // For most tests, you want to ignore the updates
    protected String filterMsg(String actual) {
        String filtered = super.filterMsg(actual);
        filtered = stripMsg(filtered,"rawValue = ");
        filtered = stripMsg(filtered,"rawValue option change handler");
        return filtered;
      }

    public void setup(boolean clearLog) {
        super.setup(clearLog);
        setArrow(myComp);
    }

/*    public void setup(boolean clearLog) {
        startupTest(myPage, null);
        showComponent();
        waitForElementVisible(myDivComp());
        // For input, we want element with id =
        if(clearLog == true)
          clickOnClear();
        setInput(getElement(myComp()));
        setValue(getElement(valueHolderLocator()));
        setLabel(getElement("id=" + LABELID));
        setWidgetComp(getMyWidget());
        setArrow(myComp);
        setActions(new Actions(getWebDriver()));
    }
*/

    public void setArrow(String id) {
      this.arrow = getElement("{\"element\":\"#" + id + "\",\"subId\":\"" + SUBIDARROW + "\"}");
    }

    public String myDivComp() {
      return CHOICEDIVPREFIX + "-" + myComp;
    }


    public WebElement myDiv() {
      return getElement(myDivComp());
    }

    public String mySelectComp() {
      return myComp;
    }

    public String myChoice() {
      return ChoicePrefix + myComp;
    }

    public String myInputCompClass() {
      return LISTBOXINPUTCLASS;
    }

    public void clickOnFocus() {
        super.clickOnFocus(); // sadly, doens't work for select
        // kludge, invoke menu and dismiss leaves focus.
        // If menu is open, this closes and reopens.
        arrow.click();
        arrow.click();
    }


    public WebElement myProxy() {
      return getElement(myChoice());
    }

    public WebElement mySelect() {
        return getElement(mySelectComp());
    }

    public WebElement myInput() {
      return getElement("css=." + myInputCompClass());
    }

    public void verifyInputValue(String val) {
        // Assert.assertEquals(getOjMethod(OJSELECT,"option","value"),"[\"" + val + "\"]");
        Assert.assertEquals(getInputValue(false),"[" + val + "]");
    }

    public void verifyValue(String val) {
        Assert.assertEquals(value.getText(),"[\"" + val + "\"]");
    }

    public List<WebElement> getOptions() {
      WebElement body = getElement("id=html_body");
      List<WebElement> options = body.findElements(By.className(LISTBOXOPTIONS));
      return options;
    }

    // For now, use mouse to choose item matching this, to test other items
    public void enterInput(String val, boolean tab) throws Exception {
//        input.sendKeys(val);
//         submitInput(tab);
        selectItem(true,val);
    }

    public void selectItem(boolean useMouse, String item) {
      if(!useMouse)
        log("Not implemented");
      // Approach 1:
      // Put focus on element
      // Type in item
      // DOWN ARROW and ENTER
      // Approach 2:
      else {
        // Click on dropdown
        arrow.click();
        // Click on option whose name matches item
        List<WebElement> options = getOptions();
        for(WebElement o : options) {
          if(o.getText().equals(item))
            {
              o.click();
              return;
            }
        }
      }
    }

    public void testValue() throws Exception {
        setup();

        // 1. Verify initial value for page of 30 in input and displayed
        verifyInput("30");

        // 2. Select a value by mouse click
        selectItem(true,"60");
        verifyInput("60");


        // 2. Enter a value by keyboard and TAB
/*        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(Keys.BACK_SPACE);
        enterInput("90");
        verifyInput("90");

        // 3. Enter a value by keyboard and TAB and see how it copes with browser autocomplete
        testValueEntry("3");

        // 4. Modify existing value by adding 0 at end
        enterInput("0");
        verifyInput("30");

        // 5. Enter a value by keyboard and click elsewhere on page
        testValueEntry("90",false);

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
*/
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

    public void testRequired() throws Exception {
          setup();
          // click on required
          clickOnRequired();
          // Verify label has parent span with title "Required" and class oj-label-required-icon
          labelHasAttributeValue(input,"title","Required");
          labelHasClass(input,"oj-label-required-icon");
          // Verify input id has aria-required=true aria-invalid = false
          elementHasAttributeValue(myDiv(),"aria-required","true");
          // verify input parent div has oj-required
          elementHasClass(this.widgetComp,"oj-required");
          // clear input
          selectItem(true,"0");
          // Verify that parent div has oj-invalid class
          elementHasClass(this.widgetComp,"oj-invalid");
          // Verify input id has aria-invalid=true
          elementHasAttributeValue(myDiv(),"aria-invalid","true");
          // Verify div with class oj-messaging-inline-container now in dom
          // Verify div with class oj-message-summary "Value is required." contents
          // Verify div with class oj-message-detail has child span of "You must enter a value."
          messageShown("Value is required.","You must enter a value.");
          // Enter input 10
          selectItem(true,"60");
          // Verify that parent div no longer has oj-invalid class
          elementHasNoClass(this.widgetComp,"oj-invalid");
          // Verify that input id has aria-invalid=false
          elementHasAttributeValue(myDiv(),"aria-invalid","false");
          // Verify div with class oj-message-summary no longer in dom
          Assert.assertFalse(isMessageShown(),"Message not shown");
      }

        public void testValidator(String ... hints) throws Exception {
              setup();
              // click on Validator
              clickOnValidator();
              // Verify no div with oj-messaging-popup class in dom
              Assert.assertFalse(isMessageShown(),"Message not shown");
              // Place cursor in input
              // clickOnFocus();
              // Clear value
              // hintsShown(hints);
              selectItem(true,"$120.00");
              // Verify that parent div has oj-invalid class
              elementHasClass(this.widgetComp,"oj-invalid");
              // Verify input id has aria-invalid=true
              elementHasAttributeValue(myDiv(),"aria-invalid","true");
              // Verify div with class oj-messaging-inline-container now in dom
              Assert.assertTrue(isMessageShown(),"Message shown");
              // Verify div with class oj-message-summary "The number is too high." contents
              // Verify div with class oj-message-detail has child span of "The number must be less than or equal to 100."
              messageShown("The number is too high.","The number must be less than or equal to 100.");
              // Clear value
              // clearInput();
              // Enter Value of 100
              selectItem(true,"60");
              // Verify that parent div no longer has oj-invalid class
              elementHasNoClass(this.widgetComp,"oj-invalid");
              // Verify that input id has aria-invalid=false
              elementHasAttributeValue(myDiv(),"aria-invalid","false");
              // Verify div with class oj-message-summary no longer in dom
              Assert.assertFalse(isMessageShown(),"Message not shown");

          }



      public void testDisabled() throws Exception {
          setup();
          // Click on disabled
          clickOnDisable();
          // check parent div has class oj-disabled
          parentHasClass(myDiv(),"oj-disabled");
          // check parent div has aria-disabled = true
          parentHasAttributeValue(myDiv(),"aria-disabled","true");
          // Sendkeys and see if you can write to it
          try {
            enterInput("60");
          } catch (Exception e) {
            // Could get org.openqa.selenium.InvalidElementStateException
          }
          verifyInput("30");  // initial value was 30, verify it is uhnchanged

          // Click on disabled again
          clickOnDisable();
          // check parent div has no class oj-disabled
          parentHasNoClass(myDiv(),"oj-disabled");
          // check parent div has aria-disabled = false
          parentHasAttributeValue(myDiv(),"aria-disabled","false");

          enterInput("60");
          waitForMilliseconds(2000L);

          verifyInput("60");  // initial value was 30, verify it is uhnchanged
        }


}
