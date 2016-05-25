package oj.tests.validation.validators.custom;

import oj.tests.validation.validators.required.CookbookRequiredValidatorTest;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookCustomValidatorTest extends OJetBase {
    private static final String TEST_PAGE = "demo-validators-customValidators.html";
    private static final String TEST_PAGE_TITLE = "Validators - Custom Validator";
    private static final String INPUT_ID = "password";
    private static final String INPUT2_ID = "cpassword";
   

    public CookbookCustomValidatorTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

   
    
    @Test(groups = { "cookbook", "validation" })
    public void testConformPasswordFieldMatch() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id="+INPUT_ID);
        WebElement input2 =
            getElement("id="+INPUT2_ID);
        Actions actions = new Actions(getWebDriver());
       //Set Hello1 in password field
       input.sendKeys( "Hello1");
        actions.moveToElement(input2).click().perform(); // move focus to input date field
        //Set Hello2 in confirm password field
        input2.sendKeys( "Hello2");
       
       // move focus    
        actions.moveToElement(input).click().perform(); // move focus to input date field
        
        //Verify error
        WebElement parentElem = getParentElement(input2);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input2, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Error");
        msg = getMessagingContentNodeBySubId(input2, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "The passwords must match!");
        
        //set the Conform password to correct value
        input2.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input2.sendKeys( "Hello1");       
        // move focus
        actions.moveToElement(input).click().perform();      

        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input2, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");


    }
    
    
    @Test(groups = { "cookbook", "validation" })
    public void testConfirmPasswordFieldMatchIfPawwordChangedAgain() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id="+INPUT_ID);
        WebElement input2 =
            getElement("id="+INPUT2_ID);
        Actions actions = new Actions(getWebDriver());
       //Set Hello1 in password field
       input.sendKeys( "Hello1");
        actions.moveToElement(input2).click().perform(); // move focus to input date field
        //Set Hello2 in confirm password field
        input2.sendKeys( "Hello1");
        actions.moveToElement(input).click().perform();
        //Now change password field again
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
        input.sendKeys( "fooBar1");
       // move focus    
        actions.moveToElement(input2).click().perform(); // move focus to input date field
        
        //Verify error
        WebElement parentElem = getParentElement(input2);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input2, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Error");
        msg = getMessagingContentNodeBySubId(input2, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "The passwords must match!");
        
        //set the Conform password to correct value
        input2.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input2.sendKeys( "fooBar1");       
        // move focus
        actions.moveToElement(input).click().perform();      

        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input2, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");


    }
    
}
