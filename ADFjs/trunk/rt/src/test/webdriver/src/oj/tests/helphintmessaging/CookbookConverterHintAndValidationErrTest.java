package oj.tests.helphintmessaging;

import oracle.ojet.automation.test.OJetBase;

import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookConverterHintAndValidationErrTest extends OJetBase {
    public CookbookConverterHintAndValidationErrTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "messaging" })
    public void testConverterHintAsPlaceholder_date() throws Exception {
        startupTest("demo-helpHintsMessaging-converterHintMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Converter Hints and Messages");
        waitForElementVisible("id=date10");

        //enter incorrct value
        WebElement date = getElement("id=date10");
        Actions actions = new Actions(getWebDriver());
        actions.sendKeys(date, Keys.ESCAPE);
        actions.sendKeys(date, "a");
        actions.sendKeys(date, Keys.TAB);
        //check that validation error and title are displayed in notewindow
        actions.moveToElement(date).click().perform();
        actions.sendKeys(date, Keys.ESCAPE);
        WebElement msg = getMessagingContentNodeBySubId(date, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "'a' is not in the expected date format.",
                            "validation summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value in the same format as this example: '11/29/98'",
                            "validation err detail is displayed in note window");
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(),
                            "enter a date in your preferred format and we will attempt to figure it out",
                            "title is displayed in note window");

    }

    @Test(groups = { "cookbook", "messaging" })
    public void testConverterHintAsPlaceholder_datetime() throws Exception {
        startupTest("demo-helpHintsMessaging-converterHintMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Converter Hints and Messages");
        waitForElementVisible("id=date11");

        //test inputdatetime has converter hint as placeholder
        WebElement date = getElement("id=date11");
        Actions actions = new Actions(getWebDriver());
        actions.sendKeys(date, Keys.ESCAPE);
        actions.sendKeys(date, "a");
        actions.sendKeys(date, Keys.TAB);
        //check that validation error and title are displayed in notewindow
        actions.moveToElement(date).click().perform();
        actions.sendKeys(date, Keys.ESCAPE);
        WebElement msg = getMessagingContentNodeBySubId(date, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "'a' is not in the expected date and time format.",
                            "validation summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(),
                            "Enter a value in the same format as this example: '11/29/98 03:45 PM'",
                            "validation err detail is displayed in note window");
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(),
                            "enter a datetime in your preferred format and we will attempt to figure it out",
                            "title is displayed in note window");

    }

    @Test(groups = { "cookbook", "messaging" })
    public void testConverterHintAsPlaceholder_inputtext() throws Exception {
        startupTest("demo-helpHintsMessaging-converterHintMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Converter Hints and Messages");
        waitForElementVisible("id=date12");

        //test inputtext has converter hint as placeholder
        WebElement date = getElement("id=date12");
        Actions actions = new Actions(getWebDriver());
        date.sendKeys("a");
        date.sendKeys(Keys.TAB);
        //check that validation error and title are displayed in notewindow
        actions.moveToElement(date).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(date, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "'a' is not in the expected time format.",
                            "validation summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value in the same format as this example: '3:45 PM'",
                            "validation err detail is displayed in note window");
        msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(), "enter the time in hours, minutes",
                            "title is displayed in note window");

    }

    @Test(groups = { "cookbook", "messaging" })
    public void testConverterHintAsPlaceholder_inputnumber() throws Exception {
        startupTest("demo-helpHintsMessaging-converterHintMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Converter Hints and Messages");
        waitForElementVisible("id=currency13");

        //test inputnumber does not display converter hint as placeholder
        //bug ?? TODO

        /*
    WebElement date = getElement("id=currency13");
    String placeholder = date.getAttribute("placeholder");
    Assert.assertEquals(placeholder, "h:mm a", "converter hint as placeholder");
    */
        //test title in note window
        // WebElement currency = getElement("id=currency13");
        WebElement currency = getElement("{\"element\":\"#currency13\",\"subId\":\"oj-inputnumber-input\"}");
        WebElement date = getElement("id=date10");
        Actions actions = new Actions(getWebDriver());

        actions.sendKeys(currency, "a");
        actions.sendKeys(currency, Keys.TAB);
        //call ESC key to dismiss the calendar after TAB
        actions.sendKeys(date, Keys.ESCAPE);
        //check that validation error and title are displayed in notewindow
        actions.moveToElement(currency).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(currency, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "'a' is not in the expected currency format.",
                            "validation summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(currency, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value in the same format as this example: '$ 12,345.99'",
                            "validation err detail is displayed in note window");
        msg = getMessagingContentNodeBySubId(currency, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(), "enter an amount with or without grouping separator",
                            "title is displayed in note window");

    }

}
