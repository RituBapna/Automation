package oj.tests.validation.componentlevel;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;


public class CookbookResetMethodTest extends OJetBase {
    public CookbookResetMethodTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "validation" })
    public void testResetWithEmptyValues() throws Exception {
        startupTest("demo-validationUsecases-resetMethod.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Reset Component");
        waitForElementVisible("id=resetBtn");

        //Click on reset button
        WebElement resetBtn = getElement("id=resetBtn");
        resetBtn.click();

        //verify that age component still had deferred err icon
        WebElement ageStatus = getElement("id=ageStatus");
        String style = ageStatus.getAttribute("style");
        boolean isDisplayed = style.indexOf("inline") > -1;
        Assert.assertTrue(isDisplayed, "deferred validation error indicator is  displayed");

        //Verify that age value is undefined
        WebElement ageVal = getElement("id=ageVal");
        boolean isUndefined = ageVal.getText().indexOf("undefined") > -1;
        Assert.assertTrue(isUndefined, "component value is not set");
        // setting focus on age does not show any error
        WebElement age = getElement("id=age");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(age).click().perform();
        WebElement ageParentElem = getParentElement(age);
        String classes = ageParentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is not applied");

        // weight field has no error and component value is undefined
        WebElement weight = getElement("id=weight");
        WebElement WtParentElem = getParentElement(weight);
        classes = WtParentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is not applied");
        WebElement weightVal = getElement("id=weightVal");
        isUndefined = weightVal.getText().indexOf("undefined") > -1;
        Assert.assertTrue(isUndefined, "component value is not set");


    }

    @Test(groups = { "cookbook", "validation" }, dependsOnMethods = { "testResetWithEmptyValues" })
    public void testRestClearsCustomMsg() throws Exception {
        startupTest("demo-validationUsecases-resetMethod.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Reset Component");
        waitForElementVisible("id=resetBtn");

        //Click on Add Custom Message button
        WebElement addCustomMsgBtn = getElement("id=addCustomMsgBtn");
        addCustomMsgBtn.click();
        //Verify that both age and Weight fieldas has custom msg
        WebElement age = getElement("id=age");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(age).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(age, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Not entering your correct age can be grounds for disqualification!");

        WebElement weight = getElement("id=weight");
        actions.moveToElement(weight).click().perform();
        msg = getMessagingContentNodeBySubId(weight, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(),
                            "Not entering your correct weight might place you in the wrong competition group!");


        //Click on reset button
        WebElement resetBtn = getElement("id=resetBtn");
        resetBtn.click();

        //verify that age component  had deferred err icon
        WebElement ageStatus = getElement("id=ageStatus");
        String style = ageStatus.getAttribute("style");
        boolean isDisplayed = style.indexOf("inline") > -1;
        Assert.assertTrue(isDisplayed, "deferred validation error indicator is  displayed");

        //Verify that age value is undefined
        WebElement ageVal = getElement("id=ageVal");
        boolean isUndefined = ageVal.getText().indexOf("undefined") > -1;
        Assert.assertTrue(isUndefined, "component value is not set");
        // setting focus on age does not show any error


        actions.moveToElement(age).click().perform();
        WebElement ageParentElem = getParentElement(age);
        String classes = ageParentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is not applied");

        // weight field has no error and component value is undefined

        WebElement WtParentElem = getParentElement(weight);
        classes = WtParentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is not applied");
        WebElement weightVal = getElement("id=weightVal");
        isUndefined = weightVal.getText().indexOf("undefined") > -1;
        Assert.assertTrue(isUndefined, "component value is not set");

    }

    @Test(groups = { "cookbook" , "validation"}, dependsOnMethods = { "testRestClearsCustomMsg" })
    public void testResetAfterInvalidValue() throws Exception {
        startupTest("demo-validationUsecases-resetMethod.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Reset Component");
        waitForElementVisible("id=resetBtn");

        //Enter in invalid value in age and tab out
        WebElement ageInput = getElement("{\"element\":\"#age\",\"subId\":\"oj-inputnumber-input\"}");

        ageInput.sendKeys("ab");
        ageInput.sendKeys(Keys.TAB);


        //Enter invalid valie in weight
        WebElement wtInput = getElement("{\"element\":\"#weight\",\"subId\":\"oj-inputnumber-input\"}");

        wtInput.sendKeys("ab");
        wtInput.sendKeys(Keys.TAB);


        //Click on Add Cutom Message btn
        WebElement addCutomMsgBtn = getElement("id=addCustomMsgBtn");
        addCutomMsgBtn.click();

        //Verify error in age field
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(ageInput).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(ageInput, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "'ab' is not in the expected number format.");
        msg = getMessagingContentNodeBySubId(ageInput, "oj-messaging-inline-message-summary", 1);
        Assert.assertEquals(msg.getText().trim(), "Not entering your correct age can be grounds for disqualification!");

        //Verify err in weight field
        actions.moveToElement(wtInput).click().perform();
        msg = getMessagingContentNodeBySubId(wtInput, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "'ab' is not in the expected number format.");
        msg = getMessagingContentNodeBySubId(wtInput, "oj-messaging-inline-message-summary", 1);
        Assert.assertEquals(msg.getText().trim(),
                            "Not entering your correct weight might place you in the wrong competition group!");

        //Click on reset button
        WebElement resetBtn = getElement("id=resetBtn");
        resetBtn.click();

        //Verify age has deferred error status
        WebElement ageStatus = getElement("id=ageStatus");
        String style = ageStatus.getAttribute("style");
        boolean isDisplayed = style.indexOf("inline") > -1;
        Assert.assertTrue(isDisplayed, "deferred validation error indicator is  displayed");

        //No err for theme applied to age
        WebElement age = getElement("id=age");

        actions.moveToElement(age).click().perform();
        WebElement ageParentElem = getParentElement(age);
        String classes = ageParentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is not applied");

        //no error theme applied to weight
        WebElement wt = getElement("id=weight");

        actions.moveToElement(wt).click().perform();
        WebElement wtParentElem = getParentElement(wt);
        classes = wtParentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is not applied");

    }
    
    @Test(groups = { "cookbook", "validation" }, dependsOnMethods = { "testResetAfterInvalidValue" })
    public void testRestAfterMixedValues() throws Exception {
        startupTest("demo-validationUsecases-resetMethod.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Reset Component");
        waitForElementVisible("id=resetBtn");

        //Enter a valid value in age and tab out
        //Setting this value to test that after reset the last valid value is set
        WebElement ageInput = getElement("{\"element\":\"#age\",\"subId\":\"oj-inputnumber-input\"}");
        ageInput.sendKeys("45");
        ageInput.sendKeys(Keys.TAB);

        //Enter in invalid value in age now and tab out
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(ageInput).click().perform();
        ageInput.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
        ageInput.sendKeys("2");
        ageInput.sendKeys(Keys.TAB);


        //Enter valid valie in weight
        WebElement wtInput = getElement("{\"element\":\"#weight\",\"subId\":\"oj-inputnumber-input\"}");

        wtInput.sendKeys("160");
        wtInput.sendKeys(Keys.TAB);


        //Click on Add Cutom Message btn
        WebElement addCutomMsgBtn = getElement("id=addCustomMsgBtn");
        addCutomMsgBtn.click();

        //Verify error in age field
       
        actions.moveToElement(ageInput).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(ageInput, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Number is too low.");
        msg = getMessagingContentNodeBySubId(ageInput, "oj-messaging-inline-message-summary", 1);
        Assert.assertEquals(msg.getText().trim(), "Not entering your correct age can be grounds for disqualification!");

        //Verify err in weight field
        actions.moveToElement(wtInput).click().perform();
     
        msg = getMessagingContentNodeBySubId(wtInput, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(),
                            "Not entering your correct weight might place you in the wrong competition group!");

        //Click on reset button
        WebElement resetBtn = getElement("id=resetBtn");
        resetBtn.click();

        //Verify age value is reverted back to 45 and error or warning theme is applied
       
      
        WebElement age = getElement("id=age");

        actions.moveToElement(age).click().perform();
        WebElement ageParentElem = getParentElement(age);
        String classes = ageParentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is not applied");
        boolean hasWarningErr = classes.indexOf("oj-warning") > -1;
        Assert.assertFalse(hasWarningErr, "Warning theme is not applied");

        //no warning theme applied to weight
        WebElement wt = getElement("id=weight");

        actions.moveToElement(wt).click().perform();
        WebElement wtParentElem = getParentElement(wt);
        classes = wtParentElem.getAttribute("class");
        hasWarningErr = classes.indexOf("oj-warning") > -1;
        Assert.assertFalse(hasWarningErr, "Warning theme is not applied");
        
        //test that age value is 45
        
        String val = evalJavascript("return $('#age').ojInputNumber('option', 'value')");
        //Second tab is selected
        Assert.assertEquals(val, "45");


    }
}
