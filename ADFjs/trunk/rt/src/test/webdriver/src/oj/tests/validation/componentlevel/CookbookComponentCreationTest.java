package oj.tests.validation.componentlevel;

import java.util.List;
import java.util.NoSuchElementException;


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


public class CookbookComponentCreationTest extends OJetBase {
    public CookbookComponentCreationTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    //This test is failing because of bug 21316610 
    @Test(groups = { "cookbook", "validation" })
    public void testDeferredOnComponentCreation() throws Exception {
        startupTest("demo-validationUsecases-componentCreate.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Component Creation");
        waitForElementVisible("id=username");

        //Notice Username field has a deferred error. Check this by verifying that error icon is displayed
        WebElement status = getElement("id=status");
        String style = status.getAttribute("style");
        boolean isDisplayed = style.indexOf("inline") > -1;
        Assert.assertTrue(isDisplayed, "deferred validation error indicator is displayed");

        //Verify that when foucus is on username field (one with defrred err) , error message is not displayed
        WebElement username = getElement("id=username");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(username).click().perform();
        WebElement parentElem = getParentElement(username);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "No validation error theme is applied");
        WebElement msg = getMessagingContentNodeBySubId(username, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");

        //Tab out of the username and verify no error is still displayed
        username.sendKeys(Keys.TAB);
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "No validation error theme is applied");

        //enter value in username field. Then clear the value and Tab out
        //Verify No errro


        actions.moveToElement(username).click().perform();
        username.sendKeys("fooname"); //enter fooname
        username.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete fooname
        username.sendKeys(Keys.TAB); //tab out
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "No validation error theme is applied");


    }

    @Test(groups = { "cookbook" , "validation"}, dependsOnMethods = { "testDeferredOnComponentCreation" })
    public void testDeferredOnUserInteraction() throws Exception {
        startupTest("demo-validationUsecases-componentCreate.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Component Creation");
        waitForElementVisible("id=username");

        //Enter invalid value in username and Tab out
        WebElement username = getElement("id=username");
        username.sendKeys("fo"); //enter fo
        username.sendKeys(Keys.TAB); //tab out

        // verify that username is decorated with invalid theme
        WebElement parentElem = getParentElement(username);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "Format validation error displayed");

        //Move focus on username and verify the error message
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(username).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(username, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Format is incorrect.", "validation error is displayed");

        //Verify that deferred validation msg is cleared
        WebElement status = getElement("id=status");
        String style = status.getAttribute("style");
        boolean isDisplayed = style.indexOf("inline") > -1;
        Assert.assertFalse(isDisplayed, "deferred validation error indicator is not displayed");

        //Clear the username value and tab off. verify that required validation err is shown
        actions.moveToElement(username).click().perform();

        username.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete fooname
        username.sendKeys(Keys.TAB); //tab out
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "required validation error theme is applied");
        actions.moveToElement(username).click().perform();
        msg = getMessagingContentNodeBySubId(username, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value is required.", "required validation error is displayed");

        //Enter a valid value in username and tab off. Verify that required validation err is cleared
        actions.moveToElement(username).click().perform();

        username.sendKeys("fooagain"); //delete fooname
        username.sendKeys(Keys.TAB); //tab out

        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, " validation error is cleared");

        //clear the value again and tab off. Verify that required validation error is displayed again

        actions.moveToElement(username).click().perform();

        username.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete fooname
        username.sendKeys(Keys.TAB); //tab out
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "required validation error theme is applied");
        actions.moveToElement(username).click().perform();
        msg = getMessagingContentNodeBySubId(username, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value is required.", "required validation error is displayed");


    }

}
