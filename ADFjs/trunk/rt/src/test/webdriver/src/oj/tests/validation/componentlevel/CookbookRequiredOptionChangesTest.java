package oj.tests.validation.componentlevel;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;


public class CookbookRequiredOptionChangesTest extends OJetBase {
    public CookbookRequiredOptionChangesTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "validation" })
    public void testAppSetRequiredOption() throws Exception {
        startupTest("demo-validationUsecases-requiredOption.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Required Option Changes");
        waitForElementVisible("id=username");

        //Check that no deferred validation icon is displayed because username is not required
        WebElement status = getElement("id=status");
        String style = status.getAttribute("style");
        boolean isDisplayed = style.indexOf("inline") > -1;
        Assert.assertFalse(isDisplayed, "username is not required");

        //Verify that label does not have required indicator
        boolean isRequiredIconPresent = isElementPresent(By.className("oj-label-required-icon"));
        Assert.assertFalse(isRequiredIconPresent, "required indocator is NOT present for username label");
        

        //click on Toggle Require option
        WebElement toggleRequiredOptionBtn = getElement("id=toggleReqBtn");
        toggleRequiredOptionBtn.click();

        //Verify that label now has required indicator

     
         isRequiredIconPresent = isElementPresent(By.className("oj-label-required-icon"));
        Assert.assertTrue(isRequiredIconPresent, "required indocator is present for username label");

        //Verifu that username field has deferred validation err
        style = status.getAttribute("style");
        isDisplayed = style.indexOf("inline") > -1;
        Assert.assertTrue(isDisplayed, "username has deferred validation error indicator");

        //move focus to username field

        Actions actions = new Actions(getWebDriver());
        WebElement username = getElement("id=username");
        actions.moveToElement(username).click().perform();

        //Verify that username field is not decorated with validation error
        WebElement parentElem = getParentElement(username);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is not applied");

    }

    @Test(groups = { "cookbook", "validation" }, dependsOnMethods = { "testAppSetRequiredOption" })
    public void testAppUnSetRequiredOption() throws Exception {
        startupTest("demo-validationUsecases-requiredOption.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Required Option Changes");
        waitForElementVisible("id=username");

        //check that required Option is set on username before proceeding with the test
        WebElement toggleRequiredOptionBtn = getElement("id=toggleReqBtn");
        WebElement status = getElement("id=status");
        String style = status.getAttribute("style");
        boolean isDisplayed = style.indexOf("inline") > -1;
        if (!isDisplayed)
            toggleRequiredOptionBtn.click();

        //Set incorrect value for username
        WebElement username = getElement("id=username");
        username.sendKeys("ab");
        username.sendKeys(Keys.TAB);

        //Verify that deferred validation indicator is removed

        style = status.getAttribute("style");
        isDisplayed = style.indexOf("inline") > -1;
        Assert.assertFalse(isDisplayed, "deferred validation error is cleared");

        //Verify that regExp validation error is present
        WebElement parentElem = getParentElement(username);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "validation error theme is applied");

        //Verify the validator error
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(username).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(username, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Format is incorrect.");

        //unset required option for username
        toggleRequiredOptionBtn.click();

        //Verify that label does not have required indicator
        boolean isPresent = isElementPresent("id=usernameIcons");
        Assert.assertFalse(isPresent, "required indicator not present for username label");


        //Verifu that username field does not has deferred validation err
        style = status.getAttribute("style");
        isDisplayed = style.indexOf("inline") > -1;
        Assert.assertFalse(isDisplayed, "username does not have deferred validation error");

        //move focus to username field
        //Verify that username field still has regExp validation error
        actions.moveToElement(username).click().perform();
        parentElem = getParentElement(username);
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "validation error theme is  applied");
        msg = getMessagingContentNodeBySubId(username, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Format is incorrect.");
    }

    @Test(groups = { "cookbook", "validation" }, dependsOnMethods = { "testAppUnSetRequiredOption" })
    public void testAppToggleRequiredOptionAfterClearingValue() throws Exception {
        startupTest("demo-validationUsecases-requiredOption.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Required Option Changes");
        waitForElementVisible("id=username");

        //check that required Option is NOT set on username and username field has incorrect value before proceeding with the test
        WebElement toggleRequiredOptionBtn = getElement("id=toggleReqBtn");
        Actions actions = new Actions(getWebDriver());
        WebElement status = getElement("id=status");
        String style = status.getAttribute("style");
        boolean isDisplayed = style.indexOf("inline") > -1;
        if (isDisplayed)
            toggleRequiredOptionBtn.click();
        //Set incorrect value for username
        WebElement username = getElement("id=username");
        actions.moveToElement(username).click().perform();
        username.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
        username.sendKeys("ab");
        username.sendKeys(Keys.TAB);
        //BEGIN TEST

        //enter Valid value in username and verify that value is set in the vew model
        username.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
        username.sendKeys("hello");
        username.sendKeys(Keys.TAB);

        WebElement val = getElement("id=val");
        Assert.assertEquals(val.getText(), "[Component Value: hello]", "value is updated in view model");


        //Turn on required option
        toggleRequiredOptionBtn.click();

        //Now clear the username value and tab out
        username.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
        username.sendKeys(Keys.TAB);

        //Verify that riquired  validation error is displayed

        WebElement parentElem = getParentElement(username);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "validation error theme is applied.");

        //Verify the validator error

        actions.moveToElement(username).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(username, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value is required.");

        //Verify that model value is still "hello"
        Assert.assertEquals(val.getText(), "[Component Value: hello]", "view model value is not cleared");

        //unset required option for username
        toggleRequiredOptionBtn.click();


        //move focus to username field
        //Verify that username field has no validation error
        actions.moveToElement(username).click().perform();
        parentElem = getParentElement(username);
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is  not applied");

        //Verify that null value is set in view model now
        Assert.assertEquals(val.getText(), "[Component Value: ]", "null value is updated in view model");

    }

}
