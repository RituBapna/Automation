package oj.tests.validation.validators.regexp;

import oj.tests.validation.validators.length.CookbookLengthValidator;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookRegExpValidator extends OJetBase {
    private static final String TEST_PAGE = "demo-validators-builtInValidatorRegExp.html";
    private static final String TEST_PAGE_TITLE = "Validators - Built-in RegExp Validator";
    private static final String INPUT_ID = "pattern1";
    private static final String INPUT2_ID = "pattern2";
    private static final String INPUT3_ID = "pattern3";

    public CookbookRegExpValidator() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "validation" })
    public void testHint() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT3_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id="+INPUT3_ID);
     
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input).click().perform(); // move focus to input 1
      
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "enter at least 3 letters or numbers.");

    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testPatternAttribute() throws Exception {
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
        Assert.assertEquals(msg.getText().trim(), "Format is incorrect.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Value '1' must match this pattern: '[a-zA-Z0-9]{3,}'");

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
    public void testPatternOption() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT2_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id="+INPUT2_ID);
        WebElement input2 =
            getElement("id="+INPUT_ID);
       
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
        Assert.assertEquals(msg.getText().trim(), "Format is incorrect.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Value '1' must match this pattern: '[a-zA-Z0-9]{3,}'");

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
    public void testregExpValidatorOption() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT3_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id="+INPUT3_ID);
        WebElement input2 =
            getElement("id="+INPUT_ID);
       
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
        Assert.assertEquals(msg.getText().trim(), "Format is incorrect.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "You must enter at least 3 letters or numbers");

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
