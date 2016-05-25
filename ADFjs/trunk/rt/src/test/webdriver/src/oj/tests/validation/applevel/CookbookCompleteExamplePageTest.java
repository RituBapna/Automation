package oj.tests.validation.applevel;

import java.util.List;
import java.util.NoSuchElementException;

import oj.tests.tabs.CookbookTabsTest;

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


public class CookbookCompleteExamplePageTest extends OJetBase {
    public CookbookCompleteExamplePageTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "validation" })
    public void testInitialComponentsState() throws Exception {
        startupTest("demo-appLevelValidation-completeExample.html", null);
        verifyTitle("Incorrect page title;", "App Level Validation - Complete Example");
        waitForElementVisible("id=create");

        //Click on create button
        WebElement createBtn = getElement("id=create");
        createBtn.click();

        //Verify that required fields (user name, password and conform Password fields have validation error
        WebElement username = getElement("id=username");
        WebElement parentElem = getParentElement(username);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "defered validation error on required field");
        WebElement password = getElement("id=password");
        parentElem = getParentElement(password);
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "defered validation error on required field");
        WebElement cpassword = getElement("id=cpassword");
        parentElem = getParentElement(cpassword);
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "defered validation error on required field");

        //Make sure username field has focus
        WebElement activeElem = getWebDriver().switchTo().activeElement();
        boolean isFocusOnUsername = activeElem.equals(username);
        Assert.assertTrue(isFocusOnUsername, "First element with error has focus");

        //Make sure validation error is displayed for user name
        WebElement msg = getMessagingContentNodeBySubId(username, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value is required.");
        msg = getMessagingContentNodeBySubId(username, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "You must enter a value.");

        //Create Button disabled
        Object isDisabled = evalJavascript("return $('#create').ojButton('option', 'disabled')");
        Assert.assertEquals(isDisabled.toString(), "true", "Create button is disabled on  validation err");

    }

    @Test(groups = { "cookbook" , "validation" }, dependsOnMethods = { "testInitialComponentsState" })
    public void testAfterAllValidationErrorFixedSubmitButtonEnabled() throws Exception {
        startupTest("demo-appLevelValidation-completeExample.html", null);
        verifyTitle("Incorrect page title;", "App Level Validation - Complete Example");
        waitForElementVisible("id=create");

        //Click on create button
        WebElement createBtn = getElement("id=create");
        createBtn.click();

        //Set Value in Username field and tab out
        WebElement username = getElement("id=username");
        username.sendKeys("fooName");
        username.sendKeys(Keys.TAB);
        WebElement parentElem = getParentElement(username);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, " validation error cleared");

        //Set Value in password field and tab out
        WebElement password = getElement("id=password");
        password.sendKeys("Hello1");
        password.sendKeys(Keys.TAB);
        parentElem = getParentElement(password);
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error cleared");

        //Set Value in confirm password field and tab out
        WebElement cpassword = getElement("id=cpassword");
        cpassword.sendKeys("Hello1");
        cpassword.sendKeys(Keys.TAB);
        parentElem = getParentElement(cpassword);
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation errorcleared");


        //Create Button enabled
        Object isDisabled = evalJavascript("return $('#create').ojButton('option', 'disabled')");
        Assert.assertEquals(isDisabled.toString(), "false",
                            "Create button is enabled after  validation errors are cleared");

    }


}
