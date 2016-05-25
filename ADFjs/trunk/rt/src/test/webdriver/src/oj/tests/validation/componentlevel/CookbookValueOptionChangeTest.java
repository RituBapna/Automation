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


public class CookbookValueOptionChangeTest extends OJetBase {
    public CookbookValueOptionChangeTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook" , "validation"})
    public void testComponentStartWithCorrectValue() throws Exception {
        startupTest("demo-validationUsecases-valueOption.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Value Option Changes");
        waitForElementVisible("id=numDays");

        //Set start date to Dec 4, 2014
        WebElement startDay = getElement("id=day");

        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(startDay).click().perform();
        startDay.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
        startDay.sendKeys("12/4/2014");
        startDay.sendKeys(Keys.TAB);
        //Verify that value is set
        WebElement dayval = getElement("id=dayval");
        Assert.assertEquals(dayval.getText().trim(), "[Component Value: 2014-12-04]");

        //Set the number of day to 2
        WebElement numDaysInput = getElement("{\"element\":\"#numDays\",\"subId\":\"oj-inputnumber-input\"}");
        numDaysInput.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
        numDaysInput.sendKeys("2");
        numDaysInput.sendKeys(Keys.TAB);

        //Verify that End Dat value is changed
        WebElement nextDayVal = getElement("id=nextdayval");
        boolean isValueCorrect = nextDayVal.getText().indexOf("2014-12-05") > -1;
        Assert.assertTrue(isValueCorrect, "End Date value has changed");

        //Change End Date to a weekend date and verify error
        WebElement endDay = getElement("id=nextday");

        endDay.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
        endDay.sendKeys("12/6/2014");
        endDay.sendKeys(Keys.TAB);

        WebElement endDayGrandParentElem = getParentElement(getParentElement(endDay));
        String classes = endDayGrandParentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "validation error theme is applied");
        //verify error msg

        //  Actions actions = new Actions(getWebDriver());
        actions.moveToElement(endDay).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(endDay, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "The day you chose is a weekend!");

        //Change the start day to a different week day
        startDay.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
        startDay.sendKeys("12/3/2014");
        startDay.sendKeys(Keys.TAB);
        //Verify that error is removed from End daty field
        classes = endDayGrandParentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is cleared");

    }

    @Test(groups = { "cookbook", "validation" }, dependsOnMethods = { "testComponentStartWithCorrectValue" })
    public void testAppSetsIncorrectValue() throws Exception {
        startupTest("demo-validationUsecases-valueOption.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Value Option Changes");
        waitForElementVisible("id=numDays");

        //cleick on SetWeekend End Date!! button
        WebElement setWeekendEndDayBtn = getElement("setWeekendBtn2");
        setWeekendEndDayBtn.click();

        //Verify that End day field is not marked with error because validation is not run when value is set programmatically

        WebElement endDay = getElement("id=nextday");
        WebElement endDayParentElem = getParentElement(getParentElement(endDay));
        String classes = endDayParentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is not applied");


        // clear the start day and tab off. Verify error is displayed
        WebElement startDay = getElement("id=day");

        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(startDay).click().perform();
        startDay.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
        startDay.sendKeys(Keys.TAB);

        WebElement startDayParentElem = getParentElement(getParentElement(startDay));
        classes = startDayParentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "validation error theme is  applied");

        //Check Validation error msg

        actions.moveToElement(startDay).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(startDay, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value is required.");

        //Click on set Weekday Start Day and verify that error is removed from Start day
        WebElement setWeekendStartDayBtn = getElement("id=setWeekendBtn");
        setWeekendStartDayBtn.click();
        this.waitForMilliseconds(1000);
        classes = startDayParentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error is cleared");


    }

    @Test(groups = { "cookbook", "validation" }, dependsOnMethods = { "testAppSetsIncorrectValue" })
    public void testAppClearsValueDeferredValidationIsRun() throws Exception {
        startupTest("demo-validationUsecases-valueOption.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Value Option Changes");
        waitForElementVisible("id=numDays");

        //Clears Start Date
        Actions actions = new Actions(getWebDriver());
        WebElement startDay = getElement("id=day");
        actions.moveToElement(startDay).click().perform();
        startDay.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
        startDay.sendKeys(Keys.TAB);

        // Set End Date to Weekend date
        //Change End Date to a weekend date and verify error
        WebElement endDay = getElement("id=nextday");
        endDay.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
        endDay.sendKeys("12/6/2014");
        endDay.sendKeys(Keys.TAB);

        // Click on "Clear value" button
        WebElement createNewTaskbtn = getElement("createNewTaskBtn");
        createNewTaskbtn.click();
        //Verify that Start Date has deferred err
        WebElement startDateStatus = getElement("id=startDateStatus");
        String style = startDateStatus.getAttribute("style");
        boolean isDisplayed = style.indexOf("inline") > -1;
        Assert.assertTrue(isDisplayed, "deferred validation error indicator is  displayed");

        //Move focus to Start Date and verify that no error theme  is displayed
        actions.moveToElement(startDay).click().perform();
        WebElement startDayParentElem = getParentElement(getParentElement(startDay));
        String classes = startDayParentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is not  applied");

        //End Date has no error
        WebElement endDayParentElem = getParentElement(getParentElement(endDay));
        classes = endDayParentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is not applied");


    }

    @Test(groups = { "cookbook", "validation" }, dependsOnMethods = { "testAppClearsValueDeferredValidationIsRun" })
    public void testCustomErrorClearedOnUserInteraction() throws Exception {
        startupTest("demo-validationUsecases-valueOption.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Value Option Changes");
        waitForElementVisible("id=numDays");


        //Click on Add Custom Message button
        WebElement addCustomMsgBtn = getElement("addCustomMsgBtn");
        addCustomMsgBtn.click();

        //Verify Start Date has error
        Actions actions = new Actions(getWebDriver());
        WebElement startDay = getElement("id=day");
        WebElement startDayParentElem = getParentElement(getParentElement(startDay));
        String classes = startDayParentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "validation error theme is   applied");

        actions.moveToElement(startDay).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(startDay, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "App Error");

        //Verify End Date has Error
        WebElement endDay = getElement("id=nextday");
        WebElement endDayParentElem = getParentElement(getParentElement(endDay));
        classes = endDayParentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "validation error theme is  applied");
        actions.moveToElement(endDay).click().perform();
        msg = getMessagingContentNodeBySubId(endDay, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "App Error");

        //Set Start Date to valid value 12/4/2014
        //Verify that Cutom error error is cleared
        actions.moveToElement(startDay).click().perform();
        startDay.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
        startDay.sendKeys("12/4/2014");
        startDay.sendKeys(Keys.TAB);
        classes = startDayParentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "Custom App error theme is   cleared");

        //Verify that end Date error is cleared as well
        classes = endDayParentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "Custom error theme is  cleared");


    }
    
    @Test(groups = { "cookbook", "validation" }, dependsOnMethods = { "testCustomErrorClearedOnUserInteraction" })
    public void testCustomErrorClearedOnProgrammaticUpdate() throws Exception {
        startupTest("demo-validationUsecases-valueOption.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Value Option Changes");
        waitForElementVisible("id=numDays");


        //Click on Add Custom Message button
        WebElement addCustomMsgBtn = getElement("addCustomMsgBtn");
        addCustomMsgBtn.click();

        // Click on "Clear value" button
        WebElement createNewTaskbtn = getElement("createNewTaskBtn");
        createNewTaskbtn.click();
        //Verify that Start Date has deferred err
        WebElement startDateStatus = getElement("id=startDateStatus");
        String style = startDateStatus.getAttribute("style");
        boolean isDisplayed = style.indexOf("inline") > -1;
        Assert.assertTrue(isDisplayed, "deferred validation error indicator is  displayed");

        //Move focus to Start Date and verify that no error theme  is displayed
        Actions actions = new Actions(getWebDriver());
        WebElement startDay = getElement("id=day");
        actions.moveToElement(startDay).click().perform();
        WebElement startDayParentElem = getParentElement(getParentElement(startDay));
        String classes = startDayParentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is not  applied");

        //End Date has no error
        WebElement endDay = getElement("id=nextday");
        WebElement endDayParentElem = getParentElement(getParentElement(endDay));
        classes = endDayParentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is not applied");

    }

}
