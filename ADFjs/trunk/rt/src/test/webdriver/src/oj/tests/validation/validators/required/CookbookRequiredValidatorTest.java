package oj.tests.validation.validators.required;

import oj.tests.validation.validators.regexp.CookbookRegExpValidator;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookRequiredValidatorTest extends OJetBase {
    private static final String TEST_PAGE = "demo-validators-builtInValidatorRequired.html";
    private static final String TEST_PAGE_TITLE = "Validators - Built-in Required Validator";
    private static final String INPUT_ID = "required1";
    private static final String INPUT2_ID = "required2";
    private static final String INPUT3_ID = "required3";

    public CookbookRequiredValidatorTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "validation" })
    public void testHint() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT3_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
        getElement("{\"element\":\"#" + INPUT3_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
     
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input).click().perform(); // move focus to input 1
      
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "custom: enter at least one number");

    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testRequiredAttribute() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id="+INPUT_ID);
        WebElement input2 =
            getElement("id="+INPUT2_ID);
       
       //delete the value and move focus
       Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        
        //move focus out        
        actions.moveToElement(input2).click().perform(); // move focus to input 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(input);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value is required.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "You must enter a value.");

        //enter the value  and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
       
        input.sendKeys("12345"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to input 1
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");


    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testRequiredOption() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT2_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id="+INPUT2_ID);
        WebElement input2 =
            getElement("id="+INPUT_ID);
       
       //delete the value and move focus
       Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        
        //move focus out        
        actions.moveToElement(input2).click().perform(); // move focus to input 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(input);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value is required.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "You must enter a value.");

        //enter the value  and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
       
        input.sendKeys("12345"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to input 1
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");


    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testRequiredValidatorOption() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
        getElement("{\"element\":\"#" + INPUT3_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
        WebElement input2 =
            getElement("id="+INPUT_ID);
       
       //delete the value and move focus
       Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        
        //move focus out        
        actions.moveToElement(input2).click().perform(); // move focus to input 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(getParentElement(input));
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Custom: Value Required");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Custom: A value is required for the field - ''required' option with custom translations'.");

        //enter the value  and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
       
        input.sendKeys("12345"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to input 1
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");


    }
}
