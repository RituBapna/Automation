package oj.tests.validation.validators.length;

import oj.tests.validation.validators.datetimerange.CookbookDateTimeRangeValidator;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookLengthValidator extends OJetBase {
    private static final String TEST_PAGE = "demo-validators-builtInValidatorLength.html";
    private static final String TEST_PAGE_TITLE = "Validators - Built-in Length Validator";
    private static final String INPUT_ID = "length1";
    private static final String INPUT2_ID = "length2";

    public CookbookLengthValidator() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "validation" })
    public void testDefaultHint() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id="+INPUT_ID);
     
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input).click().perform(); // move focus to input 1
      
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter 5 or more characters, up to a maximum of 10.");

    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testMinLength_defaultErr() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id="+INPUT_ID);
        WebElement input2 =
            getElement("id="+INPUT2_ID);
       
        input.sendKeys( "1");
        input.sendKeys(Keys.TAB);
        
        //move focus out 
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(input);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "There are too few characters.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter 5 or more characters, not fewer.");

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
    public void testMaxLength_defaultErr() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id="+INPUT_ID);
        WebElement input2 =
            getElement("id="+INPUT2_ID);
       
        input.sendKeys("12345678901");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(input);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "There are too many characters.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter 10 or fewer characters, not more.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input.sendKeys("1234567890"); //enter the new value
      
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
        waitForElementVisible("id=" + INPUT2_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id="+INPUT2_ID);
     
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input).click().perform(); // move focus to input 1
      
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "value must have at least 5 characters but not more than 10");

    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testMinLength_customErr() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT2_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id="+INPUT2_ID);
        WebElement input2 =
            getElement("id="+INPUT_ID);
       
        input.sendKeys("1");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input 1
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(input);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Custom: Too few characters");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Custom: Number of characters is too low. Enter at least 5 characters.");

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
    public void testMaxLength_customErr() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT2_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id="+INPUT2_ID);
        WebElement input2 =
            getElement("id="+INPUT_ID);
       
        input.sendKeys("12345678901");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input 1
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(input);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Custom: Too many characters");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Custom: Number of characters is too high. Enter at most 10 characters");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input.sendKeys("1234567890"); //enter the new value
      
        actions.moveToElement(input2).click().perform(); // move focus to input 1
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");

    }

}
