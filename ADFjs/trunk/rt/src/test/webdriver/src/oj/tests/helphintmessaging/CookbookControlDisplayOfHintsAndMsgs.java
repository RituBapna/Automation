package oj.tests.helphintmessaging;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookControlDisplayOfHintsAndMsgs extends OJetBase {
    public CookbookControlDisplayOfHintsAndMsgs() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "messaging" })
    public void testCustomValidator() throws Exception {
        startupTest("demo-helpHintsMessaging-hintsMessagesTitle.html", null);
        verifyTitle("Incorrect page title;",
                    "Help, Hints and Messaging - Control Display of Hints, Messages and Title");
        waitForElementVisible("id=date12");

        //Verify that when focus is on the input date field convert hint, validator hint and title is not displayed in Note Window
        WebElement date = getElement("id=date12");
        Actions actions = new Actions(getWebDriver());
        //move focus to date field
        actions.moveToElement(date).click().perform();
        //verify hints and title values
        WebElement msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-converter-hint", 0);
        Assert.assertNull(msg, "No converter hint is displayed in note window");


        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-validator-hint", 0);
        Assert.assertNull(msg, "No validator hint is displayed in note window");

        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-title", 0);
        Assert.assertNull(msg, "No title is displayed in note window");

        //test that validation error is displayed along with hints and title
        //Set incorrect value and Tab off
        actions.sendKeys(date, Keys.ESCAPE);
        actions.sendKeys(date, "a");
        actions.sendKeys(date, Keys.TAB);
        //Verify that error msg, hints and title are displayed in note window
        actions.moveToElement(date).click().perform();
        actions.sendKeys(date, Keys.ESCAPE);
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "'a' is not in the expected date format.",
                            "validation summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(),
                            "Enter a value in the same format as this example: 'November 29, 1998'",
                            "validation err detail is displayed in note window");

        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-converter-hint", 0);
        Assert.assertNull(msg, "No converter hint is displayed in note window");


        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-validator-hint", 0);
        Assert.assertNull(msg, "No validator hint is displayed in note window");

        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-title", 0);
        Assert.assertNull(msg, "No title is displayed in note window");

        //enter a valid value and tab out
        actions.sendKeys(date, Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),Keys.DELETE); //clear the field
        actions.sendKeys(date, "12/12/1994");
        actions.sendKeys(date, Keys.TAB);

        actions.moveToElement(date).click().perform();
        actions.sendKeys(date, Keys.ESCAPE);

        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-message-summary", 0);
        Assert.assertNull(msg, "No validation err is displayed in note window");
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-message-detail", 0);
        Assert.assertNull(msg, "No validation error is displayed in note window");
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-converter-hint", 0);
        Assert.assertNull(msg, "No converter hint is displayed in note window");


        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-validator-hint", 0);
        Assert.assertNull(msg, "No validator hint is displayed in note window");

        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-title", 0);
        Assert.assertNull(msg, "No title is displayed in note window");
    }
}
