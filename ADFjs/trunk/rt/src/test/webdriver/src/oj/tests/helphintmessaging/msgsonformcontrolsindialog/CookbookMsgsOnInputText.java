package oj.tests.helphintmessaging.msgsonformcontrolsindialog;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookMsgsOnInputText extends OJetBase {
    public CookbookMsgsOnInputText() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "messaging" })
    public void testRequiredMsg() throws Exception {
        startupTest("demo-helpHintsMessaging-dialogFormMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Note Window Messages on Form Controls in Dialog");
        waitForElementVisible("id=openDialogBtn");
        openDialog();
        WebElement showMsgsBtn = getShowMsgBtn();
        showMsgsBtn.click();
        //focvus should already be on inputText field
        //Verify that required validation error
        //is displayed

        WebElement text = getElement("id=inputcontrol");
        WebElement msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value is required.",
                            "validation summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "You must enter a value.",
                            "validation err detail is displayed in note window");
        //Verify that error icon is displayed in the error msgYou must enter a value.
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 0);
        boolean errorIconPresent = isChildElementPresent(msg, By.className("oj-message-error-icon"));
        Assert.assertTrue(errorIconPresent, "error icon is present in not window");
        //verify that oj-invalid is applied to it
        WebElement parentElem = getParentElement(text);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "oj-invalid is applied to inputText");


    }

    @Test(groups = { "cookbook", "messaging" })
    public void testFatalMsg() throws Exception {
        startupTest("demo-helpHintsMessaging-dialogFormMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Note Window Messages on Form Controls in Dialog");
        waitForElementVisible("id=openDialogBtn");
        openDialog();
        WebElement buttonset = getElement("id=severityTypeButtonSet");
        WebElement fatalBtn = getButtonAtIndexFromButtonSet(buttonset, 0);

        fatalBtn.click();
        //Move focus on inputText field
        //Verify that fatal validation error is displayed
        Actions actions = new Actions(getWebDriver());
        WebElement text = getElement("id=inputcontrol");
        actions.moveToElement(text).click().perform();

        WebElement msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Fatal Error Summary Text",
                            "fatal summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Fatal Error Detail Text",
                            "fatal err detail is displayed in note window");
        //Verify that error icon is displayed in the error msgYou must enter a value.
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 0);
        boolean errorIconPresent = isChildElementPresent(msg, By.className("oj-message-error-icon"));
        Assert.assertTrue(errorIconPresent, "error icon is present in not window");
        //verify that oj-invalid is applied to it
        WebElement parentElem = getParentElement(text);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "oj-invalid is applied to inputText");


    }

    @Test(groups = { "cookbook", "messaging" })
    public void testErrorMsg() throws Exception {
        startupTest("demo-helpHintsMessaging-dialogFormMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Note Window Messages on Form Controls in Dialog");
        waitForElementVisible("id=openDialogBtn");
        openDialog();
        WebElement buttonset = getElement("id=severityTypeButtonSet");
        WebElement errorBtn = getButtonAtIndexFromButtonSet(buttonset, 1);

        errorBtn.click();
        //Move focus on inputText field
        //Verify that error msg type is displayed
        Actions actions = new Actions(getWebDriver());
        WebElement text = getElement("id=inputcontrol");
        actions.moveToElement(text).click().perform();

        WebElement msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Error Summary Text", "error summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Error Detail Text", "fatal err detail is displayed in note window");
        //Verify that error icon is displayed in the error msgYou must enter a value.
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 0);
        boolean errorIconPresent = isChildElementPresent(msg, By.className("oj-message-error-icon"));
        Assert.assertTrue(errorIconPresent, "error icon is present in not window");
        //verify that oj-invalid is applied to it
        WebElement parentElem = getParentElement(text);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "oj-invalid is applied to inputText");


    }

    @Test(groups = { "cookbook", "messaging" })
    public void testWarningMsg() throws Exception {
        startupTest("demo-helpHintsMessaging-dialogFormMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Note Window Messages on Form Controls in Dialog");
        waitForElementVisible("id=openDialogBtn");
        openDialog();
        WebElement buttonset = getElement("id=severityTypeButtonSet");
        WebElement warningBtn = getButtonAtIndexFromButtonSet(buttonset, 2);

        warningBtn.click();
        //Move focus on inputText field
        //Verify that error msg type is displayed
        Actions actions = new Actions(getWebDriver());
        WebElement text = getElement("id=inputcontrol");
        actions.moveToElement(text).click().perform();

        WebElement msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Warning Summary Text",
                            "warning summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Warning Detail Text",
                            "warning err detail is displayed in note window");
        //Verify that error icon is displayed in the error msgYou must enter a value.
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 0);
        boolean iconPresent = isChildElementPresent(msg, By.className("oj-message-warning-icon"));
        Assert.assertTrue(iconPresent, "warning icon is present in not window");
        //verify that oj-invalid is applied to it
        WebElement parentElem = getParentElement(text);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-warning") > -1;
        Assert.assertTrue(hasValidationErr, "oj-warning is applied to inputText");


    }

    @Test(groups = { "cookbook", "messaging" })
    public void testInfoMsg() throws Exception {
        startupTest("demo-helpHintsMessaging-dialogFormMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Note Window Messages on Form Controls in Dialog");
        waitForElementVisible("id=openDialogBtn");
        openDialog();
        WebElement buttonset = getElement("id=severityTypeButtonSet");
        WebElement infoBtn = getButtonAtIndexFromButtonSet(buttonset, 3);

        infoBtn.click();
        //Move focus on inputText field
        //Verify that error msg type is displayed
        Actions actions = new Actions(getWebDriver());
        WebElement text = getElement("id=inputcontrol");
        actions.moveToElement(text).click().perform();

        WebElement msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Info Summary Text", "info summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Info Detail Text", "info detail is displayed in note window");
        //Verify that info  icon is displayed in the error msgYou must enter a value.
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 0);
        boolean iconPresent = isChildElementPresent(msg, By.className("oj-message-info-icon"));
        Assert.assertTrue(iconPresent, "warning icon is present in not window");
        //verify that oj-invalid is applied to it
        WebElement parentElem = getParentElement(text);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "oj-invalid is NOT applied to inputText");

        hasValidationErr = classes.indexOf("oj-warning") > -1;
        Assert.assertFalse(hasValidationErr, "oj-warning is NOT applied to inputText");


    }


    @Test(groups = { "cookbook", "messaging" })
    public void testConfirmationMsg() throws Exception {
        startupTest("demo-helpHintsMessaging-dialogFormMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Note Window Messages on Form Controls in Dialog");
        waitForElementVisible("id=openDialogBtn");
        openDialog();
        WebElement buttonset = getElement("id=severityTypeButtonSet");
        WebElement cBtn = getButtonAtIndexFromButtonSet(buttonset, 4);

        cBtn.click();
        //Move focus on inputText field
        //Verify that error msg type is displayed
        Actions actions = new Actions(getWebDriver());
        WebElement text = getElement("id=inputcontrol");
        actions.moveToElement(text).click().perform();
        this.waitForMilliseconds(1000);
        WebElement msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Summary Text",
                            "confirmation summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Detail Text",
                            "Confirmation detail is displayed in note window");
        //Verify that Confirmation  icon is displayed in the error msgYou must enter a value.
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 0);
        boolean iconPresent = isChildElementPresent(msg, By.className("oj-message-confirmation-icon"));
        Assert.assertTrue(iconPresent, "Confirmation icon is present in not window");
        //verify that oj-invalid is applied to it
        WebElement parentElem = getParentElement(text);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "oj-invalid is NOT applied to inputText");

        hasValidationErr = classes.indexOf("oj-warning") > -1;
        Assert.assertFalse(hasValidationErr, "oj-warning is NOT applied to inputText");


    }

    WebElement getShowMsgBtn() {
        WebElement parent = getElement("id=showHiddenMessages");
        List<WebElement> btns = parent.findElements(By.tagName("button"));
        return btns.get(0);
    }

    WebElement getButtonAtIndexFromButtonSet(WebElement buttonset, int index) {

        List<WebElement> btns = buttonset.findElements(By.className("oj-button"));
        return btns.get(index);

    }

    void openDialog() {
        WebElement btn = getElement("id=openDialogBtn");
        btn.click();
    }
}
