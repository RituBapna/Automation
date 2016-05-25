package oj.tests.validation.validators.numberrange;

import oj.tests.validation.validators.length.CookbookLengthValidator;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookNumberRangeValidator extends OJetBase {
    private static final String TEST_PAGE = "demo-validators-builtInValidatorNumberRange.html";
    private static final String TEST_PAGE_TITLE = "Validators - Built-in NumberRange Validator";
    private static final String INPUT_ID = "numberRange1";
    private static final String INPUT2_ID = "numberRange2";

    public CookbookNumberRangeValidator() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "validation" })
    public void testDefaultHint() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT2_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id="+INPUT2_ID);
     
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input).click().perform(); // move focus to input 1
      
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a number between 10,000 and 25,000.");

    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testMinOptions() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
        getElement("{\"element\":\"#" + INPUT2_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
        WebElement input2 =
            getElement("id="+INPUT_ID);
       
        input.sendKeys( "9999.99");       
        //move focus out 
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(getParentElement(input));
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Number is too low.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Number 9999.99 must be greater than or equal to 10,000.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
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
    public void testMaxOptions() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
        getElement("{\"element\":\"#" + INPUT2_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
        WebElement input2 =
            getElement("id="+INPUT_ID);
       
        input.sendKeys( "25,000.01");       
        //move focus out 
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(getParentElement(input));
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Number is too high.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Number 25000.01 must be less than or equal to 25,000.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input.sendKeys("12,345"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to input 1
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");


    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testCustomHint() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id="+INPUT_ID);
     
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input).click().perform(); // move focus to input 1
      
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value between $10,000.05 and $25,000.95.");

    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testMinNumberRange() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT2_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id="+INPUT_ID);
        WebElement input2 =
        getElement("{\"element\":\"#" + INPUT2_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
       
        input.sendKeys("$10,000.01");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input 1
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(input);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Number is too low.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Number 10000.01 must be greater than or equal to $10,000.05.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
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
    public void testMaxNumberRange() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT2_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id="+INPUT_ID);
        WebElement input2 =
        getElement("{\"element\":\"#" + INPUT2_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
       
        input.sendKeys("26000");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input 1
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(input);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Number is too high.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Number 26000 must be less than or equal to $25,000.95.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
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
