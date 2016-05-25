package oj.tests.input;

import oj.tests.common.TestCompBase;

import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.By;
import java.util.List;

import org.testng.Assert;

public class TestInputBase extends TestCompBase {

    protected static final String INPUTGROUP = "input";
    protected static final String LABELID = "mainlabelid";
    protected WebElement input;
    protected WebElement value;
    protected WebElement label;
    protected WebElement widgetComp;
    protected Actions actions;
    protected String ojCommand;
    protected String subId;
    protected String valueHolder;
    protected String widgetString;
    protected String widget;
    protected String widgetId = "";
    protected String widgetClass = "";
    protected String value0 = "30"; // initial value, displayed;
    protected String value0a = "30"; // initial value, internal
    protected String value1 = "60";
    protected String value1a = "60";

    public TestInputBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp, String ojCommand,
                         String subId, String valueHolder) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp);
        this.ojCommand = ojCommand;
        this.subId = subId;
        this.valueHolder = valueHolder;
        this.widgetString = widget + " oj-form-control oj-component";

    }

    public TestInputBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp, String ojCommand,
                         String subId, String valueHolder, String exposerId) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp, exposerId);
        this.ojCommand = ojCommand;
        this.subId = subId;
        this.valueHolder = valueHolder;
        this.widgetString = widget + " oj-form-control oj-component";
    }

    public void setup(boolean clearLog) {
        startupTest(myPage, null);
        if(clearLog == true)
          clickOnClear();
        showComponent();
        if(widgetId != "")
          waitForElementVisible("id=" + widgetId);
        else if (widgetClass != "")
          waitForElementVisible("css=." + widgetClass);
        else
          waitForElementVisible(compLocator());
        setInput(getElement(compLocator()));
        setValue(getElement(valueHolderLocator()));
        setLabel(getElement("id=" + LABELID));
        setWidgetComp(getMyWidget());
        setActions(new Actions(getWebDriver()));
        log("TestInputBase: done");
    }

    public void setup() {
        setup(false);
    }


    public String valueHolderLocator() {
        return "id=" + valueHolder;
    }

    public void setInput(WebElement input) {
        this.input = input;
    }

    public WebElement getInput() {
        return input;
    }

    public void setLabel(WebElement label) {
        this.label = label;
    }

    public WebElement getLabel() {
        return label;
    }

    public void setWidgetComp(WebElement w) {
        this.widgetComp = w;
    }

    public WebElement getWidgetComp() {
        return widgetComp;
    }

    public void setWidgetString(String s) {
        this.widgetString = s;
    }

    public String getWidgetString() {
        return this.widgetString;
    }

    public void setValue(WebElement value) {
        this.value = value;
    }

    public WebElement getValue() {
        return value;
    }

    public void setActions(Actions actions) {
        this.actions = actions;
    }

    public Actions getActions() {
        return actions;
    }

    public void setValueHolder(String valueHolder) {
        this.valueHolder = valueHolder;
    }

    public String getValueHolder() {
        return valueHolder;
    }

    public void setOjCommand(String ojCommand) {
        this.ojCommand = ojCommand;
    }

    public String getOjCommand() {
        return ojCommand;
    }

    public String getCurrentValue() {
      WebElement currentValue = getElement(valueHolderLocator());
      String val = currentValue.getText();
      return val;
    }

    public String getInputValue() {
        return getInputValue(true);
    }

    public String getInputValue(boolean useSubId) {
        if(useSubId) {
            String location = "{\"element\":\"#" + myComp + "\",\"subId\":\"" + subId + "\"}";
            WebElement inputField = getElement(location);
            String val = inputField.getAttribute("value");
            log("getInputValue.location = " + location + " , val = " + val);
            return val;
        } else {
            String evalString = "return $('#" + myComp + "')." + ojCommand + "('option','value')";
            String retValue = evalJavascript(evalString);
            log(evalString + " = " + retValue);
            return retValue;
        }
    }

    public String getMethod(String myMethod) {
        return getOjMethod(ojCommand,myMethod);
    }

    public String getMethod(String myMethod,String value) {
        return getOjMethod(ojCommand,myMethod,value);
    }

    public String getMyJq(String method, String ... values) {
        return getJq(myComp,method, values);
    }

/*    public String getMyWidgetString() {
        String s = getMethod("widget");
        log("getMyWidgetString = " + s);
        return s;
    }
*/
    public String getMyWidgetId() {
      String s = "." + widgetString.replace(' ','.');
      log("getMyWidgetId = " + s);
      return s;
    }


    public WebElement getMyWidget() {
      // String location = "{\"element\":\"" + getMyWidgetId() + "\"}";
      // log("location = " + location);
      WebElement w = getElement("css=" + getMyWidgetId());
      return w;
    }

    public void clearInput() throws Exception {
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.DELETE);
    }

    public void enterInput(String val) throws Exception {
        enterInput(val,true);
    }

    public void enterInput(String val, boolean tab) throws Exception {
        input.sendKeys(val);
        submitInput(tab);
    }

    public void submitInput(boolean tab) throws Exception {
        if(tab)
            input.sendKeys(Keys.TAB);
        else
            actions.moveToElement(value).click().perform();
        }

    public void verifyInput(String val) {
        verifyInputValue(val);
        verifyValue(val);
    }

    public void verifyInput(String val1, String val2) {
        verifyInputValue(val1);
        verifyValue(val2);
    }

    public void verifyInputValue(String val) {
        Assert.assertEquals(getInputValue(),val);
    }

    public void verifyValue(String val) {
        Assert.assertEquals(value.getText(),val);
    }


    public void testValueEntry(String val) throws Exception {
        testValueEntry(val,true);
    }

    public void testValueEntry(String val, boolean tab) throws Exception {
        clearInput();
        enterInput(val,tab);
        verifyInput(val);
    }

    public void testDoubleClickCopyPaste() throws Exception {
        setup();
        // highlight the 30 by double clicking
        Action doubleClick = actions.doubleClick(input).build();
        doubleClick.perform();
        // CTRL-C to copy then RIGHT-ARROW to unhilight then CTRL--V to paste
        input.sendKeys(Keys.chord(Keys.CONTROL,"c"),
            Keys.ARROW_RIGHT,
            Keys.chord(Keys.CONTROL,"v"));
        submitInput(true);
        verifyInput("3030");
    }


        // Doesn't work on chrome or firefox, not sure why...
        public void testDragAndDrop() throws Exception {
            setup();
            clearInput();
/*            WebElement source = getElement("id=exposed");
            Action doubleClick = actions.doubleClick(source).build();
            Action manualClick = actions
                .clickAndHold(source)
                .moveToElement(input,5,5)  // offset to make sure dropped in middle of element
                .release()
                .build();
            doubleClick.perform();
            waitForMilliseconds(2000L);
            manualClick.perform();
            waitForMilliseconds(2000L);
            submitInput(true);
            waitForMilliseconds(2000L);

            WebElement nonoji = getElement("id=nonoji");
            Action clickAndDrag = actions.dragAndDrop(nonoji, input).build();
            clickAndDrag.perform();
            waitForMilliseconds(2000L);

            verifyInput("exposed ");
*/
        }

        public void testDisabled() throws Exception {
            setup();
            // Click on disabled
            clickOnDisable();
            // Check input with id has disabled attribute  assertNotEditable
            elementHasAttribute(input,"disabled");
            // check parent div has class oj-disabled
            parentHasClass(input,"oj-disabled");
            // check parent div has aria-disabled = true
            parentHasAttributeValue(input,"aria-disabled","true");
            // Sendkeys and see if you can write to it
            try {
              enterInput(value1);
            } catch (Exception e) {
              // Could get org.openqa.selenium.InvalidElementStateException
            }
            verifyInput(value0);  // initial value was 30, verify it is uhnchanged

            // Click on disabled again
            clickOnDisable();
            // Check input with id has no disabled attribute
            elementHasNoAttribute(input,"disabled");
            // check parent div has no class oj-disabled
            parentHasNoClass(input,"oj-disabled");
            // check parent div has aria-disabled = false
            parentHasAttributeValue(input,"aria-disabled","false");
            // Sendkeys and see if you can write to it
            clearInput();
            enterInput(value1);
            verifyInput(value1);  // initial value was 30, verify it is uhnchanged
          }

        public void testReadOnly() throws Exception {
            setup();
            // Click on read only
            clickOnReadOnly();
            // Check input with id has readonly attribute assertElementPresent | //input[@id='locatorId' and @readOnly] |
            elementHasAttribute(input,"readonly");
            // Sendkeys and see if you can write to it
            try {
              enterInput(value1);
            } catch (Exception e) {
              // Could get org.openqa.selenium.InvalidElementStateException
            }
            verifyInput(value0,value0a);  // initial value was 30, verify it is unchanged

            // Click on read only again
            clickOnReadOnly();
            // Check input with id has no readonly attribute  element.getAttribute("readonly")
            elementHasNoAttribute(input,"readonly");
            // Sendkeys and see if you can write to its
            clearInput();
            enterInput(value1);
            verifyInput(value1,value1a);  // initial value was 30, verify it is uhnchanged
          }


        public void initRequiredValue() {
          // by default, most pages have values.  If they do not, override this and set
        }

        /**
        * @see <a href="http://otm/otm/displayNodeDetails.do?preview=true&nodeType=1&nodeId=6345010">OTM 6345010</a>
        * @see <a href="https://bug.oraclecorp.com/pls/bug/webbug_edit.edit_info_top?rptno=23109695">bug 23109695</a>
        * @see <a href="https://jira.oraclecorp.com/jira/browse/JET-1488">JIRA JET-1488</a>
        * @throws Exception if component not found in dom
        */

        public void testRequired() throws Exception {
              setup();
              // click on required
              clickOnRequired();
              // by default, most pages have values.  If they do not, override this and set
              initRequiredValue();
              // Verify label has parent span with title "Required" and class oj-label-required-icon
              labelHasAttributeValue(input,"title","Required");
              labelHasClass(input,"oj-label-required-icon");
              // Verify input id has aria-required=true aria-invalid = false
              elementHasAttributeValue(input,"aria-required","true");
              // verify input parent div has oj-required
              // parentHasClass(input,"oj-required");
              elementHasClass(widgetComp,"oj-required");
              // clear input
              clearInput();
              // Tab off to submitInput
              submitInput(true);
              // Verify that parent div has oj-invalid class
              // parentHasClass(input,"oj-invalid");
              elementHasClass(widgetComp,"oj-invalid");
              // Verify input id has aria-invalid=true
              elementHasAttributeValue(input,"aria-invalid","true");
              // Verify div with class oj-messaging-inline-container now in dom
              // Verify div with class oj-message-summary "Value is required." contents
              // Verify div with class oj-message-detail has child span of "You must enter a value."
              messageShown("Value is required.","You must enter a value.");
              // Enter input
              enterInput(value1);
              // Verify that parent div no longer has oj-invalid class
              // parentHasNoClass(input,"oj-invalid");
              elementHasNoClass(widgetComp,"oj-invalid");
              // Verify that input id has aria-invalid=false
              elementHasAttributeValue(input,"aria-invalid","false");
              // Verify div with class oj-message-summary no longer in dom
              Assert.assertFalse(isMessageShown(),"Message not shown");
          }

        public void testValidator() throws Exception {
              testValidator("Enter a number less than or equal to 100.");
            }

        public void testValidator(String ... hints) throws Exception {
              setup();
              // click on Validator
              clickOnValidator();
              // Verify no div with oj-messaging-popup class in dom
              Assert.assertFalse(isMessageShown(),"Message not shown");
              // Verify input id has aria-invalid = false
              elementHasAttributeValue(input,"aria-invalid","false");
              // Place cursor in input
              clickOnFocus();
              // Clear value
              clearInput();
              hintsShown(hints);
              // Enter Value of 110
              enterInput("110");
              // Verify that parent div has oj-invalid class
              parentHasClass(input,"oj-invalid");
              // Verify input id has aria-invalid=true
              elementHasAttributeValue(input,"aria-invalid","true");
              // Verify div with class oj-messaging-inline-container now in dom
              Assert.assertTrue(isMessageShown(),"Message shown");
              // Verify div with class oj-message-summary "The number is too high." contents
              // Verify div with class oj-message-detail has child span of "The number must be less than or equal to 100."
              messageShown("The number is too high.","The number must be less than or equal to 100.");
              // Clear value
              clearInput();
              // Enter Value of 100
              enterInput("100");
              // Verify that parent div no longer has oj-invalid class
              parentHasNoClass(input,"oj-invalid");
              // Verify that input id has aria-invalid=false
              elementHasAttributeValue(input,"aria-invalid","false");
              // Verify div with class oj-message-summary no longer in dom
              Assert.assertFalse(isMessageShown(),"Message not shown");

          }

        public void testConverter() throws Exception {
              setup();
              // click on Converter
              clickOnConverter();
              // Verify that value is shown as "$30.00" and comp value is 30
              // $("#text-input").ojInputText( "getNodeBySubId", {'subId': 'oj-inputtext-input'} ).value
              verifyInputValue("$30.00");
              verifyValue("30");
              // Enter value 10
              clearInput();
              enterInput("10");
              // Verify that value is shown as "$10.00" but comp value is 10
              verifyInputValue("$10.00");
              verifyValue("10");
              // Enter value $20.00
              clearInput();
              enterInput("$20.00");
              verifyInputValue("$20.00");
              verifyValue("20");
              // Verify that value is shown as "$20.00" but comp value is 20
              // Enter value $20.00
              clearInput();
              enterInput("-$20.00");
              verifyInputValue("-$20.00");
              verifyValue("-20");

              clearInput();
              enterInput("0");
              verifyInputValue("$0.00");
              verifyValue("0");

              clearInput();
              enterInput("$$");
              verifyInputValue("$$");
              verifyValue("0");

              messageShown("'$$' is not in the expected currency format.","Enter a value in the same format as this example: '$12,345.99'");

          }

        public String myCMLauncher() {
              return myComp;
        }

        public void myCMClear() {
          // for date times, you can't focus unless you clear to go to edit mode
          // clickOnClear();
        }

        public void testContextMenu() throws Exception {
              String menuLocator = "id=ui-id-1"; // "Item 1";
              setup(true);
              // click on Context Menu button
              clickOnMenu();
              // Could verify that parent div now has contextmenu attibute with value "MyMenu"
              // Right click in input
              // Could Verify that dom has div with class oj-menu-layer
              // Click on Item 1 in Menu
              waitForMilliseconds(800L);
              rightClickAndSelectMenuOption(compLocator(),menuLocator);
              waitForMilliseconds(2000L);
              // Verify log says "menu opened by = " id (text-input)
              // Verify that log has "menu item selected = Item 1"
              assertMsgLog("menu item selected = Item 1","menu opened by = ",myCMLauncher(),
                "Setting val = #myMenu (was = )");

              // Below is for keyboard access to menu
              // Verify Dom has no div with class oj-menu-layer
              Assert.assertFalse(isMenuShown(), "Menu shown");
              // Cursor in field
              myCMClear(); // for date times, you can't focus unless you clear to go to edit mode
              clickOnFocus();
              // SHIFT_F10
              String selectAll = Keys.chord(Keys.SHIFT,Keys.F10);
              input.sendKeys(selectAll);
              // Verify dom has div with class oj-menu-layer
              Assert.assertTrue(isMenuShown(), "Menu not shown");
              // DOWN ARROW 3 times and RIGHT ARROW then enter
              WebElement menu = getElement("id=myMenu");
              menu.sendKeys(Keys.ARROW_DOWN);
              menu.sendKeys(Keys.ARROW_DOWN);
              menu.sendKeys(Keys.ARROW_DOWN);
              menu.sendKeys(Keys.ARROW_RIGHT);
              waitForMilliseconds(500L);
              menu.sendKeys(Keys.ENTER);
              waitForMilliseconds(2000L);
              // Verify log has "menu item select = Item 3-1"
              assertMsgLog("menu item selected = Item 3-1","menu opened by = ",myCMLauncher(),"#",
              myCMLauncher(),".focus","menu item selected = Item 1","menu opened by = ",myCMLauncher(),
                "Setting val = #myMenu (was = )");
          }

        public void testHelp() throws Exception {
              setup();
              // Click on Help button
              clickOnHelp();
              // Verify label now has oj-label-help-def class
              WebElement label = labelOf(input);
              elementHasClass(label,"oj-label-help-def");
              // Verify label now has parent span with id "text-inputIcons", ie , <id>Icons
              WebElement mySpan = spanOf(label);
              elementHasAttributeValue(mySpan,"id",myComp + "Icons");
              // Verify span has child <a> with class oj-label-help-icon-anchor and oj-label-help-icon
              List<WebElement> children = mySpan.findElements(By.tagName("a"));
              Assert.assertTrue(children.size() == 1, "Anchor to help link not found");
              WebElement myA = children.get(0);
              elementHasClass(myA,"oj-label-help-icon-anchor");
              elementHasClass(myA,"oj-label-help-icon");
              elementHasAttributeValue(myA,"href","http://localhost:7101/common/help1.html");
              elementHasAttributeValue(myA,"title","help1");
              //    and href="../../common/help1.html" and title "help1"
              // Could Click on <a> and verify that navigates to help1.html file
              // myA.click();
              // Verify title "Help1" on new page
              // verifyTitle("Incorrect page title;","Help1");

          }
        public void testTitle() throws Exception {
              testTitle("tooltip2");
          }

        public void testTitle(String ... hints) throws Exception {
              setup();
              // Click on Title button
              clickOnTitle();
              // Verify div with oj-messaging-popup in div with role="tooltip"
              // Verify div with class oj-form-control-hint-title shows up in dom and has
              //   span child with content "tooltip2"
              clickOnFocus();
              hintsShown(hints);
          }

        public void testPattern() throws Exception {
              setup();
              // Click on Pattern
              clickOnPattern();
              // Enter value "10" and tab
              clearInput();
              enterInput("10");
              // verify Message "Format is incorrect"
              messageShown("Format is incorrect.");
              // Enter value  'ABC" and tab
              clearInput();
              enterInput("ABC");
              // Verify no message and value shown
              Assert.assertFalse(isMessageShown(),"Pattern not accepted");
          }

        public void testWidget() throws Exception {
              testWidget(this.widgetString);
          }

        public void testWidget(String expected) throws Exception {
              setup(true);
              // Click on widget
              clickOnWidget();
              // Verify log shows
              // #text-input.widget =  (oj-inputtext oj-form-control oj-component)
              assertMsgLog("#" + myComp + ".widget =  (" + expected + ")");
          }


        public void testUpdates() throws Exception {
              testUpdates("option change[value]: 123 (from 30)",
                "rawValue = 123",
                "rawValue option change handler",
                "rawValue = 12",
                "rawValue option change handler",
                "rawValue = 1",
                "rawValue option change handler",
                "rawValue = ",
                "rawValue option change handler");
        }

        public void testUpdates(String ... msgs) throws Exception {
              setup(true);
              // Type in value 123 and submit
              clearInput();
              enterInput("123");
              waitForMilliseconds(1500L);
              // Verify log shows:

              assertMsgLog(msgs);
          }
        public void testGetNodeBySubId() throws Exception {
              setup();
              // Click on GetNodeBySubId button
              clickOnGetNode();
              // Verify log shows value
              //TBD

          }
        public void testMessages() throws Exception {
              setup();
              clickOnMessages();
              messageShown("Error Summary","Error Detail");
          }
        public void testEvents() throws Exception {
              setup();
              //TBD
          }



        public void testNativeTheme() throws Exception {
              setup();
              if(exposerId.length() > 0) {
                  dismissExposer();
                  clickOnCssNative();
                  showComponent();
              } else
                  clickOnCssNative();
              //TBD
          }
        public void testMinCss() throws Exception {
              setup();
              if(exposerId.length() > 0) {
                  dismissExposer();
                  clickOnMinCss();
                  showComponent();
              } else
                  clickOnMinCss();
              //TBD
          }
        public void testLTR() throws Exception {
              setup();
              if(exposerId.length() > 0) {
                  dismissExposer();
                  clickOnLtr();
                  showComponent();
              } else
                  clickOnLtr();
              //TBD
          }
        public void testLabel() throws Exception {
              setup();
              //TBD
          }
        public void testRootAttributes() throws Exception {
              setup();
              //TBD
          }

          public void clickOnCollapsible() {
              String locString = "{\"element\":\"#" + exposerId + "\",\"subId\":\"oj-collapsible-disclosure\"}";
              WebElement header1 = getElement(locString);
              header1.click();
          }

        public void dismissExposer() {
            if(exposerId.length() > 0) {
              // if dialog, clickOnOk();
              if(exposerId.matches("btn_opendialog"))
                clickOnOk();
              // if popup, clickOnClosePopup
              else if(exposerId.matches("btn_openpopup"))
                clickOnClosePopup();
              else if(exposerId.matches("collapsiblePage"))
                clickOnCollapsible();
              // else doesn't matter, collapisble doesn't need this.
            }
        }


}
