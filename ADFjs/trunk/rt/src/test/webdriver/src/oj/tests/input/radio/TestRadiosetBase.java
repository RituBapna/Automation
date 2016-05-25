
package oj.tests.input.radio;

import java.util.List;

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
import java.util.List;
import org.openqa.selenium.By;

public class TestRadiosetBase extends TestInputBase {

    protected static final String RADIOSETGROUP = "radioset";
    protected static final String OJRADIOSET = "ojRadioset";
    protected static final String SUBID = "oj-radioset-input";
    protected static final String valueHolder = "curr-value";
    protected static final String OJIRSWIDGET = "oj-radioset";
    protected List<WebElement> inputs;

    public TestRadiosetBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp, OJRADIOSET, SUBID, valueHolder);
        this.widget = OJIRSWIDGET;
        this.widgetString = OJIRSWIDGET + " oj-component oj-form-control oj-component-initnode";
    }

    public TestRadiosetBase(String myAppRoot, String myContextRoot,
                         String pageTitle, String myPage,
                         String myComp, String exposerId) {
        super(myAppRoot, myContextRoot, pageTitle, myPage, myComp, OJRADIOSET, SUBID, valueHolder, exposerId);
        this.widget = OJIRSWIDGET;
        this.widgetString = OJIRSWIDGET + " oj-component oj-form-control oj-component-initnode";

    }

    public void createComp() throws Exception {
        super.createComp("Component created : radioset",
                         "Component created : high",
                         "Component created : medium",
                         "Component created : low");
    }

    public void destroyComp() throws Exception {
        super.destroyComp("Component destroyed : high",
        "Component destroyed : medium",
        "Component destroyed : low",
        "Component destroyed : radioset");
    }

    public void setup() {
        super.setup();
        inputs = findElements("{\"element\":\"#" + myComp + "\",\"subId\":\"oj-radioset-inputs\"}");
    }

    public String myCMLauncher() {
          return "low";
    }

    public String getInputValue() {
        return getInputValue(false);
    }

    public String getInputValue(boolean useSubId) {
        if(useSubId) {
            WebElement inputField =
                getElement("{\"element\":\"#" + myComp  + "\"}");
            String val = inputField.getAttribute("value");
            return val;
        } else {
            String evalString = "return $('#" + myComp + "')." + ojCommand + "('option','value')";
            String retValue = evalJavascript(evalString);
            log(evalString + " = " + retValue);
            return retValue;
        }
    }

    public void clickItem(int index) {
        WebElement target = inputs.get(index);

        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(target).click().perform();
    }


    public void testValue() throws Exception {
        setup();

        // 1. Verify initial value for page of 30 in input and displayed
        verifyInput("30");

        // 2. Check 90
        clickItem(1); // check 90
        verifyInput("90");

        // 3. Check 120
        clickItem(2);
        verifyInput("120");

        // 4. Use keyboard, arrow to choose 90, verify
        // TBD

        // 5. Click on value button to programmatically change value
        WebElement valueButton = getElement("id=btn_Value");
        actions.moveToElement(valueButton).click().perform();
        verifyInput("30");
        actions.moveToElement(valueButton).click().perform();
        verifyInput("90");

    }

    public List<WebElement> getCbInputs() {
      List<WebElement> inputs = input.findElements(By.className("oj-radio"));
      return inputs;
    }

        public void testValidator(String ... hints) throws Exception {
              setup();
              // click on Validator
              clickOnValidator();
              // Verify no div with oj-messaging-popup class in dom
              Assert.assertFalse(isMessageShown(),"Message not shown");
              // Verify input id has aria-invalid = false
              // Check inputs with id has disabled attribute  assertNotEditable
              for(WebElement we : getCbInputs()) {
                elementHasAttributeValue(we,"aria-invalid","false");
              }
              // Clear value
              clickOnFocus(); // focus good enough to get hints
              hintsShown(hints);  //hint should be shown now
              // Now choose 120
              clickItem(2);
              // Verify that parent div has oj-invalid class
              elementHasClass(input,"oj-invalid");
              // Verify input id has aria-invalid=true
              // Check inputs with id has disabled attribute  assertNotEditable
              for(WebElement we : getCbInputs()) {
                elementHasAttributeValue(we,"aria-invalid","true");
              }
              // Verify div with class oj-messaging-inline-container now in dom
              Assert.assertTrue(isMessageShown(),"Message shown");
              // Verify div with class oj-message-summary "The number is too high." contents
              // Verify div with class oj-message-detail has child span of "The number must be less than or equal to 100."
              messageShown("The number is too high.","The number must be less than or equal to 100.");
              // Enter Value of 90
              clickItem(1);
              // Verify that parent div no longer has oj-invalid class
              elementHasNoClass(input,"oj-invalid");
              // Verify that input id has aria-invalid=false
              for(WebElement we : getCbInputs()) {
                elementHasAttributeValue(we,"aria-invalid","false");
              }
              // Verify div with class oj-message-summary no longer in dom
              Assert.assertFalse(isMessageShown(),"Message not shown");

          }



        public void testDisabled() throws Exception {
            setup();
            // Click on disabled
            clickOnDisable();
            // Check inputs with id has disabled attribute  assertNotEditable
            for(WebElement we : getCbInputs()) {
              elementHasAttribute(we,"disabled");
            }
            // check parent div has class oj-disabled
            elementHasClass(input,"oj-disabled");
            // check parent div has aria-disabled = true
            elementHasAttributeValue(input,"aria-disabled","true");
            // Sendkeys and see if you can write to it
            try {
              clickItem(1); // switch from 30 to 90
            } catch (Exception e) {
              // Could get org.openqa.selenium.InvalidElementStateException
            }
            verifyInput(value0);  // initial value was 30, verify it is uhnchanged

            // Click on disabled again
            clickOnDisable();
            // Check input with id has no disabled attribute
            for(WebElement we : getCbInputs()) {
              elementHasNoAttribute(we,"disabled");
            }
            // check parent div has no class oj-disabled
            elementHasNoClass(input,"oj-disabled");
            // check parent div has aria-disabled = false
            elementHasAttributeValue(input,"aria-disabled","false");
            // Sendkeys and see if you can write to it
            clickItem(1); // check 90
            verifyInput("90");
          }


}
