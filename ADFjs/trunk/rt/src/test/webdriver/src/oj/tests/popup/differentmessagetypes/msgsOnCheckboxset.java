package oj.tests.popup.differentmessagetypes;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class msgsOnCheckboxset extends OJetBase {
    public msgsOnCheckboxset() {
        super(new TestConfigBuilder().setContextRoot("popup/popupTest").setAppRoot("ojpopup").build());
    }

    @Test(groups = { "popup" })
    /*
                       *  Test how checkboxset component respond when multiple messages of different severities are set using the messages options
                       *  The severity of messages from most to least severe are 'Fatal', 'Error', 'Warning', 'Info', 'Confirmation'.
                       *  The message with the highest severity determines the marker style applied on the component.
                       *  Messages are listed in order going from most severe to the least.
                       */
    public void testAddMultipleMessages() throws Exception {

        startupTest("testDiffMessageTypesInPopup.html", null);
        maximizeWindow();
        verifyTitle("Incorrect page title;", "ojPopup - Message Type Test");
        waitForElementVisible("id=btnGo");

        // open the popup
      click("id=btnGo");
        
        //initially no msg exists on datetimecontrol
        //move focus on datetimecontrol and verify no notewindow exists

        Actions actions = new Actions(getWebDriver());
        evalJavascript("window.scrollBy(0,850)", "");
        List<WebElement> inputs = findElements("{\"element\":\"#checkboxSetId\",\"subId\":\"oj-checkboxset-inputs\"}");
        WebElement text = inputs.get(0);
        actions.moveToElement(text).perform();
        WebElement msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertNull(msg, "initially no message exists on checkboxset");

        //Select "confirmation"
        WebElement buttonset = getElement("id=severityTypeButtonSet");
        WebElement confirmationBtn = getButtonAtIndexFromButtonSet(buttonset, 4);
        confirmationBtn.click();
        //Move focus on checkboxset field
        //Verify that confirmation msg type is displayed
        actions.moveToElement(text).perform();
        actions.moveToElement(text).perform();
        // this.waitForMilliseconds(3000);
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Summary Text",
                            "confirmation summary is displayed in note window");
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Detail Text",
                            "Confirmation detail is displayed in note window");
        //Verify that Confirmation  icon is displayed in the error msgYou must enter a value.
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 0);
        boolean iconPresent = isChildElementPresent(msg, By.className("oj-message-confirmation-icon"));
        Assert.assertTrue(iconPresent, "Confirmation icon is present in not window");
        //verify that oj-invalid is not applied to it
        WebElement checkboxset = getElement("id=checkboxSetId");
        String classes = checkboxset.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "oj-invalid is NOT applied to checkboxset");
        hasValidationErr = classes.indexOf("oj-warning") > -1;
        Assert.assertFalse(hasValidationErr, "oj-warning is NOT applied to checkboxset");

        // info and confirmation selected
        ////actions.sendKeys(Keys.ESCAPE).build().perform();
        WebElement infoBtn = getButtonAtIndexFromButtonSet(buttonset, 3);
        
        infoBtn.click();
        //Move focus on checkboxset field
        //Verify that info and confirmation msg types are displayed
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Info Summary Text", "info summary is displayed in note window");
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Info Detail Text", "info detail is displayed in note window");
        //Verify that info  icon is displayed in the error msgYou must enter a value.
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 0);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-info-icon"));
        Assert.assertTrue(iconPresent, "info icon is present in note window");
        //second message is of conformation type
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 1);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Summary Text",
                            "confirmation summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 1);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Detail Text",
                            "Confirmation detail is displayed in note window");
        //Verify that Confirmation  icon is displayed in the error msgYou must enter a value.
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 1);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-confirmation-icon"));
        Assert.assertTrue(iconPresent, "Confirmation icon is present in note window");
        //verify that oj-invalid is not applied to it
        checkboxset = getElement("id=checkboxSetId");
        classes = checkboxset.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "oj-invalid is NOT applied to checkboxset");
        hasValidationErr = classes.indexOf("oj-warning") > -1;
        Assert.assertFalse(hasValidationErr, "oj-warning is NOT applied to checkboxset");

        //warning, info and confirmation is selected
        //actions.sendKeys(Keys.ESCAPE).build().perform();
        WebElement warningBtn = getButtonAtIndexFromButtonSet(buttonset, 2);
        warningBtn.click();
        //Move focus on checkboxset field
        //Verify that 3 msg types are displayed
        actions.moveToElement(text).perform();

        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Warning Summary Text",
                            "warning summary is displayed in note window");
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Warning Detail Text",
                            "warning err detail is displayed in note window");
        //Verify that error icon is displayed in the error msgYou must enter a value.
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 0);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-warning-icon"));
        Assert.assertTrue(iconPresent, "warning icon is present in note window");
        //info msg type is displayed second
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 1);
        Assert.assertEquals(msg.getText().trim(), "Info Summary Text", "info summary is displayed in note window");
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 1);
        Assert.assertEquals(msg.getText().trim(), "Info Detail Text", "info detail is displayed in note window");
        //Verify that info  icon is displayed in the error msgYou must enter a value.
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 1);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-info-icon"));
        Assert.assertTrue(iconPresent, "info icon is present in note window");
        // conformation msg type is displayed thrid
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 2);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Summary Text",
                            "confirmation summary is displayed in note window");
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 2);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Detail Text",
                            "Confirmation detail is displayed in note window");
        //Verify that Confirmation  icon is displayed in the error msgYou must enter a value.
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 2);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-confirmation-icon"));
        Assert.assertTrue(iconPresent, "Confirmation icon is present in note window");
        //verify that oj-warning is applied to it
        checkboxset = getElement("id=checkboxSetId");
        classes = checkboxset.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-warning") > -1;
        Assert.assertTrue(hasValidationErr, "oj-warning is applied to checkboxset");

        //error, warning, info and confirmation msg types are selected
        //actions.sendKeys(Keys.ESCAPE).build().perform();
        WebElement errorBtn = getButtonAtIndexFromButtonSet(buttonset, 1);
        errorBtn.click();
        //Move focus on checkboxset field
        //Verify that 4 msg types are displayed

        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Error Summary Text", "error summary is displayed in note window");
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Error Detail Text", "fatal err detail is displayed in note window");
        //Verify that error icon is displayed in the error msg
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 0);
        boolean errorIconPresent = isChildElementPresent(msg, By.className("oj-message-error-icon"));
        Assert.assertTrue(errorIconPresent, "error icon is present in note window");
        //warning msg is second in the list
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 1);
        Assert.assertEquals(msg.getText().trim(), "Warning Summary Text",
                            "warning summary is displayed in note window");
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 1);
        Assert.assertEquals(msg.getText().trim(), "Warning Detail Text",
                            "warning err detail is displayed in note window");
        //Verify that warning icon is displayed in the  msg
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 1);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-warning-icon"));
        Assert.assertTrue(iconPresent, "warning icon is present in note window");
        //info msg type is displayed third
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 2);
        Assert.assertEquals(msg.getText().trim(), "Info Summary Text", "info summary is displayed in note window");
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 2);
        Assert.assertEquals(msg.getText().trim(), "Info Detail Text", "info detail is displayed in note window");
        //Verify that info  icon is displayed in the  msg
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 2);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-info-icon"));
        Assert.assertTrue(iconPresent, "info icon is present in note window");
        // conformation msg type is displayed fourth
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 3);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Summary Text",
                            "confirmation summary is displayed in note window");
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 3);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Detail Text",
                            "Confirmation detail is displayed in note window");
        //Verify that Confirmation  icon is displayed in the error msgYou must enter a value.
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 3);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-confirmation-icon"));
        Assert.assertTrue(iconPresent, "Confirmation icon is present in note window");
        //verify that oj-invalid is applied to it
        checkboxset = getElement("id=checkboxSetId");
        classes = checkboxset.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "oj-invalid is applied to checkboxset");

        //fatal, error, warning, info and confirmation msg types are selected
        //actions.sendKeys(Keys.ESCAPE).build().perform();
        WebElement fatalBtn = getButtonAtIndexFromButtonSet(buttonset, 0);
        fatalBtn.click();
        //Move focus on checkboxset field
        //Verify that fatal, error, warning, info and confirmation msg types are displayed
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Fatal Error Summary Text",
                            "fatal summary is displayed in note window");
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Fatal Error Detail Text",
                            "fatal err detail is displayed in note window");
        //Verify that error icon is displayed in the error msgYou must enter a value.
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 0);
        errorIconPresent = isChildElementPresent(msg, By.className("oj-message-error-icon"));
        Assert.assertTrue(errorIconPresent, "error icon is present in not window");
        //error msg is second in th elist
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 1);
        Assert.assertEquals(msg.getText().trim(), "Error Summary Text", "error summary is displayed in note window");
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 1);
        Assert.assertEquals(msg.getText().trim(), "Error Detail Text", "fatal err detail is displayed in note window");
        //Verify that error icon is displayed in the error msg
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 1);
        errorIconPresent = isChildElementPresent(msg, By.className("oj-message-error-icon"));
        Assert.assertTrue(errorIconPresent, "error icon is present in note window");
        //warning msg is third in the list
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 2);
        Assert.assertEquals(msg.getText().trim(), "Warning Summary Text",
                            "warning summary is displayed in note window");
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 2);
        Assert.assertEquals(msg.getText().trim(), "Warning Detail Text",
                            "warning err detail is displayed in note window");
        //Verify that warning icon is displayed in the  msg
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 2);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-warning-icon"));
        Assert.assertTrue(iconPresent, "warning icon is present in note window");
        //info msg type is displayed fourth
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 3);
        Assert.assertEquals(msg.getText().trim(), "Info Summary Text", "info summary is displayed in note window");
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 3);
        Assert.assertEquals(msg.getText().trim(), "Info Detail Text", "info detail is displayed in note window");
        //Verify that info  icon is displayed in the  msg
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 3);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-info-icon"));
        Assert.assertTrue(iconPresent, "info icon is present in note window");
        // conformation msg type is displayed fifth
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 4);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Summary Text",
                            "confirmation summary is displayed in note window");
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 4);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Detail Text",
                            "Confirmation detail is displayed in note window");
        //Verify that Confirmation  icon is displayed in the error msgYou must enter a value.
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 4);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-confirmation-icon"));
        Assert.assertTrue(iconPresent, "Confirmation icon is present in note window");
        //verify that oj-invalid is applied to it
        checkboxset = getElement("id=checkboxSetId");
        classes = checkboxset.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "oj-invalid is applied to checkboxset");

        //unselect all msg types and verify that ucheckboxset has no msges
        //actions.sendKeys(Keys.ESCAPE).build().perform();
        fatalBtn.click();
        errorBtn.click();
        warningBtn.click();
        infoBtn.click();
        confirmationBtn.click();
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertNull(msg, "no message exists on checkboxset");
    }

    @Test(groups = { "popup" })
    /*
                       * Toggle off any 'severity type' button to remove the message of the selected severity from the 'messagesCustom' option of each component.
                       * Notice the component is styled based on the highest severity of the messages. If the highest severity is

                        *   'fatal', the oj-invalid marker style is applied to the component
                        *   'error', the oj-invalid marker style is applied to the component. NOTE: 'fatal' messages have the same visual effect as 'error'.
                        *   'warning', the oj-warning marker style is applied to the component.
                        *   'info', no styles are applied to the component.
                        *   'confirmation', no styles are applied to the component.

                       */
    public void testRemoveMessageTypesOneByOne() throws Exception {

        startupTest("testDiffMessageTypesInPopup.html", null);
        maximizeWindow();
        verifyTitle("Incorrect page title;", "ojPopup - Message Type Test");
        waitForElementVisible("id=btnGo");
        // open the popup
        click("id=btnGo");
        //select all msg types
        WebElement buttonset = getElement("id=severityTypeButtonSet");
        WebElement confirmationBtn = getButtonAtIndexFromButtonSet(buttonset, 4);
        WebElement infoBtn = getButtonAtIndexFromButtonSet(buttonset, 3);
        WebElement warningBtn = getButtonAtIndexFromButtonSet(buttonset, 2);
        WebElement errorBtn = getButtonAtIndexFromButtonSet(buttonset, 1);
        WebElement fatalBtn = getButtonAtIndexFromButtonSet(buttonset, 0);
        fatalBtn.click();
        errorBtn.click();
        warningBtn.click();
        infoBtn.click();
        confirmationBtn.click();
        Actions actions = new Actions(getWebDriver());
        List<WebElement> inputs = findElements("{\"element\":\"#checkboxSetId\",\"subId\":\"oj-checkboxset-inputs\"}");
        WebElement text = inputs.get(0);
        //Deselect fatal msg
        fatalBtn.click();
        //Move focus on checkboxset and verify that 4 msg types are displayed in order with correct icons
        //and that input text is decorated with oj-invalid
        evalJavascript("window.scrollBy(0,550)", "");
        actions.moveToElement(text).perform();
        WebElement msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Error Summary Text", "error summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Error Detail Text", "fatal err detail is displayed in note window");
        //Verify that error icon is displayed in the error msg
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 0);
        boolean errorIconPresent = isChildElementPresent(msg, By.className("oj-message-error-icon"));
        Assert.assertTrue(errorIconPresent, "error icon is present in note window");
        //warning msg is second in the list
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 1);
        Assert.assertEquals(msg.getText().trim(), "Warning Summary Text",
                            "warning summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 1);
        Assert.assertEquals(msg.getText().trim(), "Warning Detail Text",
                            "warning err detail is displayed in note window");
        //Verify that warning icon is displayed in the  msg
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 1);
        boolean iconPresent = isChildElementPresent(msg, By.className("oj-message-warning-icon"));
        Assert.assertTrue(iconPresent, "warning icon is present in note window");
        //info msg type is displayed third
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 2);
        Assert.assertEquals(msg.getText().trim(), "Info Summary Text", "info summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 2);
        Assert.assertEquals(msg.getText().trim(), "Info Detail Text", "info detail is displayed in note window");
        //Verify that info  icon is displayed in the  msg
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 2);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-info-icon"));
        Assert.assertTrue(iconPresent, "info icon is present in note window");
        // conformation msg type is displayed fourth
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 3);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Summary Text",
                            "confirmation summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 3);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Detail Text",
                            "Confirmation detail is displayed in note window");
        //Verify that Confirmation  icon is displayed in the error msgYou must enter a value.
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 3);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-confirmation-icon"));
        Assert.assertTrue(iconPresent, "Confirmation icon is present in note window");
        //verify that oj-invalid is applied to it
        WebElement checkboxset = getElement("id=checkboxSetId");
        String classes = checkboxset.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "oj-invalid is applied to checkboxset");

        //deselect Error as well. Only warning, infor and conformation msg types are selected
        //actions.sendKeys(Keys.ESCAPE).build().perform();
        errorBtn.click();
        actions.moveToElement(text).perform();
        //warning is first
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Warning Summary Text",
                            "warning summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Warning Detail Text",
                            "warning err detail is displayed in note window");
        //Verify that error icon is displayed in the error msgYou must enter a value.
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 0);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-warning-icon"));
        Assert.assertTrue(iconPresent, "warning icon is present in note window");
        //info msg type is displayed second
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 1);
        Assert.assertEquals(msg.getText().trim(), "Info Summary Text", "info summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 1);
        Assert.assertEquals(msg.getText().trim(), "Info Detail Text", "info detail is displayed in note window");
        //Verify that info  icon is displayed in the error msgYou must enter a value.
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 1);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-info-icon"));
        Assert.assertTrue(iconPresent, "info icon is present in note window");
        // conformation msg type is displayed thrid
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 2);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Summary Text",
                            "confirmation summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 2);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Detail Text",
                            "Confirmation detail is displayed in note window");
        //Verify that Confirmation  icon is displayed in the error msgYou must enter a value.
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 2);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-confirmation-icon"));
        Assert.assertTrue(iconPresent, "Confirmation icon is present in note window");
        //verify that oj-warning is applied to it
        checkboxset = getElement("id=checkboxSetId");
        classes = checkboxset.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-warning") > -1;
        Assert.assertTrue(hasValidationErr, "oj-warning is applied to checkboxset");

        //deselect warning.  Only info and confirmation are selected
        //actions.sendKeys(Keys.ESCAPE).build().perform();
        warningBtn.click();
        actions.moveToElement(text).perform();
        //info is first
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Info Summary Text", "info summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Info Detail Text", "info detail is displayed in note window");
        //Verify that info  icon is displayed in the error msgYou must enter a value.
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 0);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-info-icon"));
        Assert.assertTrue(iconPresent, "info icon is present in note window");
        //second message is of conformation type
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 1);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Summary Text",
                            "confirmation summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 1);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Detail Text",
                            "Confirmation detail is displayed in note window");
        //Verify that Confirmation  icon is displayed in the error msgYou must enter a value.
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 1);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-confirmation-icon"));
        Assert.assertTrue(iconPresent, "Confirmation icon is present in note window");
        //verify that oj-invalid is not applied to it
        checkboxset = getElement("id=checkboxSetId");
        classes = checkboxset.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "oj-invalid is NOT applied to checkboxset");
        hasValidationErr = classes.indexOf("oj-warning") > -1;
        Assert.assertFalse(hasValidationErr, "oj-warning is NOT applied to checkboxset");

        //deselect info. Only confirmation is selected
        //actions.sendKeys(Keys.ESCAPE).build().perform();
        infoBtn.click();
        //only confirmation msg is displayed
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Summary Text",
                            "confirmation summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Detail Text",
                            "Confirmation detail is displayed in note window");
        //Verify that Confirmation  icon is displayed in the error msgYou must enter a value.
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 0);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-confirmation-icon"));
        Assert.assertTrue(iconPresent, "Confirmation icon is present in not window");
        //verify that oj-invalid is not applied to it
        checkboxset = getElement("id=checkboxSetId");
        classes = checkboxset.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "oj-invalid is NOT applied to checkboxset");
        hasValidationErr = classes.indexOf("oj-warning") > -1;
        Assert.assertFalse(hasValidationErr, "oj-warning is NOT applied to checkboxset");

        //deselect confirmation.  No msg types are now selected
        //actions.sendKeys(Keys.ESCAPE).build().perform();
        confirmationBtn.click();
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertNull(msg, "no message exists on checkboxset");

    }

    WebElement getButtonAtIndexFromButtonSet(WebElement buttonset, int index) {

        List<WebElement> btns = buttonset.findElements(By.className("oj-button"));
        return btns.get(index);

    }

}
