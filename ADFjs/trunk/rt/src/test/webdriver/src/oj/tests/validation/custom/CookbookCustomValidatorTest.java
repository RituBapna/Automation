package oj.tests.validation.custom;


import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

/*
 * Test steps:
    In the Password field enter a valid password and tab off (e.g. Hello1).
    In the Confirm Password field enter a value that does not match the password and tab off (e.g., Hello2). Notice the element shows a validation error thrown by the custom validator.
    Now in the Confirm Password field enter the same value as in Password field and tab off (e.g., Hello1). Notice that this value is accepted and the error cleared.
    In the Password field change the value to a different valid value and tab off (e.g. Foobar1). Notice that the Confirm Password field automatically shows an error.
    In the Confirm Password field enter a value that matches one entered in Password field and tab off (e.g. Foobar1). Notice that the error in the Confirm Password field is cleared.

 */

public class CookbookCustomValidatorTest extends OJetBase {
    public CookbookCustomValidatorTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "validation" })
    public void testCustomValidator() throws Exception {
        startupTest("demo-validators-customValidators.html", null);
        verifyTitle("Incorrect page title;", "Validators - Custom Validator");
        waitForElementVisible("id=password");

        //Set value to Hello1 in password field and tab off
        WebElement password = getElement("id=password");
        password.sendKeys("Hello1");
        password.sendKeys(Keys.TAB);

        //Set value in confirm Password field ti different value and check for err

        WebElement cpassword = getElement("id=cpassword");
        cpassword.sendKeys("Hello2");
        cpassword.sendKeys(Keys.TAB);
        WebElement parentElem = getParentElement(cpassword);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, " validation error on confirm password field");

        //Verify the eeror msg
        //move focus back to confirm password
        new Actions(getWebDriver()).moveToElement(cpassword).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(cpassword, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Error");
        msg = getMessagingContentNodeBySubId(cpassword, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "The passwords must match!");

        //Set the confirm Password field to correct value
        cpassword.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),Keys.DELETE); //clear the field
        cpassword.sendKeys("Hello1");
        cpassword.sendKeys(Keys.TAB);
        //make sure error is cleared
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, " validation error is cleared");

        //In the Password field change the value to a different valid value and tab off
        password = getElement("id=password");
        password.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),Keys.DELETE); //clear the field
        password.sendKeys("1Hello1");
        password.sendKeys(Keys.TAB);

        //Verify that confirm password field has error again
        parentElem = getParentElement(cpassword);
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, " validation error on confirm password field");

        //Fix that conform password value and tab out
        cpassword.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),Keys.DELETE); //clear the field
        cpassword.sendKeys("1Hello1");
        cpassword.sendKeys(Keys.TAB);

        //Verify that error from conform password is cleared again
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, " validation error is cleared");


    }
}

