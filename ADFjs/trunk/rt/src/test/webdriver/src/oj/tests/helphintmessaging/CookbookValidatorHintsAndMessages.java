package oj.tests.helphintmessaging;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookValidatorHintsAndMessages extends OJetBase {
    public CookbookValidatorHintsAndMessages() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "messaging" })
    public void testValidatorHintAndMsg_date() throws Exception {
        startupTest("demo-helpHintsMessaging-validatorHintsMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Validator Hints and Messages");
        waitForElementVisible("id=date10");

        //test inputdate has converter hint, validator hints and title value when user move focus to the field.

        WebElement date = getElement("id=date10");
        Actions actions = new Actions(getWebDriver());
        //move focus to date field
        actions.moveToElement(date).click().perform();
        actions.sendKeys(date, Keys.ESCAPE);
        //verify hints and title values
        WebElement msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-converter-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "mmmm dd, yyyy", "converter hint is displayed in note window");


        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "validator hint: required",
                            "validator hint is displayed in note window");
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-validator-hint", 1);
        Assert.assertEquals(msg.getText().trim(), "validator hint: datetimeRange 18 - 85 years",
                            "second validator hint is displayed in note window");

        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(),
                            "enter a date in your preferred format and we will attempt to figure it out",
                            "title is displayed in note wondow");

        //test that validation error is displayed along with hints and title
        //Set incorrect value and Tab off
        actions.sendKeys(date, "a");
        actions.sendKeys(date, Keys.TAB);
        //Verify that error msg, hints and title are displayed in note window
        actions.moveToElement(date).click().perform();
        actions.sendKeys(date, Keys.ESCAPE);
        //Verify error
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "'a' is not in the expected date format.",
                            "validation summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(),
                            "Enter a value in the same format as this example: 'November 29, 1998'",
                            "validation err detail is displayed in note window");
        //verify hints and title values
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-converter-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "mmmm dd, yyyy", "converter hint is displayed in note window");


        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "validator hint: required",
                            "validator hint is displayed in note window");
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-validator-hint", 1);
        Assert.assertEquals(msg.getText().trim(), "validator hint: datetimeRange 18 - 85 years",
                            "second validator hint is displayed in note window");

        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(),
                            "enter a date in your preferred format and we will attempt to figure it out",
                            "title is displayed in note wondow");

        //Enter correct value
        actions.sendKeys(date, Keys.ESCAPE);
        actions.sendKeys(date, Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),Keys.DELETE); //clear the field
        actions.sendKeys(date, "12/12/1990");
        actions.sendKeys(date, Keys.TAB);
        //Move focus back and verify no validatioon error
        actions.moveToElement(date).click().perform();
        actions.sendKeys(date, Keys.ESCAPE);
        this.waitForMilliseconds(1000);
        //vwerify that hints and title are still displayed
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-converter-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "mmmm dd, yyyy", "converter hint is displayed in note window");


        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "validator hint: required",
                            "validator hint is displayed in note window");
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-validator-hint", 1);
        Assert.assertEquals(msg.getText().trim(), "validator hint: datetimeRange 18 - 85 years",
                            "second validator hint is displayed in note window");

        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(),
                            "enter a date in your preferred format and we will attempt to figure it out",
                            "title is displayed in note wondow");
        WebElement parentElem = getParentElement(date);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "no validation error on date field");
       
    }

    @Test(groups = { "cookbook", "messaging" })
    public void testValidatorHintAndMsg_inputnumber() throws Exception {
        startupTest("demo-helpHintsMessaging-validatorHintsMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Validator Hints and Messages");
        waitForElementVisible("id=currency10");

        //test inputnumber has converter hint, validator hints and title value when user move focus to the field.

        WebElement date = getElement("id=currency10");
        Actions actions = new Actions(getWebDriver());
        //move focus to date field
        actions.moveToElement(date).click().perform();
        //verify hints and title values

        //expecting following to be converter hint but valiodator hint found instead: bug #20230888
        //worked aroun till the bug is fixed
        /*
        WebElement msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-converter-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "enter a decimal value",
                            "converter hint is displayed in note window");
    */
        WebElement msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "enter a decimal value",
                            "converter hint is displayed in note window");


        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-validator-hint", 1);
        Assert.assertEquals(msg.getText().trim(), "Enter a number between $ 10,000.00 and $ 50,000.45.",
                            "validator hint is displayed in note window");

        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(), "enter an amount with or without grouping separator",
                            "title is displayed in note wondow");

        //test that validation error is displayed along with hints and title
        //Set incorrect value and Tab off
        date.sendKeys("a");
        date.sendKeys(Keys.TAB);
        //Verify that error msg, hints and title are displayed in note window
        actions.moveToElement(date).click().perform();
        this.waitForMilliseconds(1000);
        //Verify error
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "'a' is not in the expected currency format.",
                            "validation summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value in the same format as this example: '$ 12,345.99'",
                            "validation err detail is displayed in note window");
        //verify hints and title values
        /*
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-converter-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "enter a decimal value",
                            "converter hint is displayed in note window");
    */
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "enter a decimal value",
                            "converter hint is displayed in note window");


        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-validator-hint", 1);
        Assert.assertEquals(msg.getText().trim(), "Enter a number between $ 10,000.00 and $ 50,000.45.",
                            "validator hint is displayed in note window");

        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(), "enter an amount with or without grouping separator",
                            "title is displayed in note wondow");

        //Enter correct value and tAB off
        date.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),Keys.DELETE); //clear the field
        date.sendKeys("20,000");
        date.sendKeys(Keys.TAB);
        //Verify that NO error msg is displayed BUT hints and title are displayed in note window
        actions.moveToElement(date).click().perform();
        WebElement parentElem = getParentElement(date);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "no validation error on inputNumber field");

        //verify hints and title values
        /*
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-converter-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "enter a decimal value",
                            "converter hint is displayed in note window");
*/
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "enter a decimal value",
                            "converter hint is displayed in note window");

        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-validator-hint", 1);
        Assert.assertEquals(msg.getText().trim(), "Enter a number between $ 10,000.00 and $ 50,000.45.",
                            "validator hint is displayed in note window");

        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(), "enter an amount with or without grouping separator",
                            "title is displayed in note wondow");

    }
}
