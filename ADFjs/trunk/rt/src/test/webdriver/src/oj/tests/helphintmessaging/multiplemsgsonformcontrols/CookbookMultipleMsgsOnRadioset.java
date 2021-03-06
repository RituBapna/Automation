package oj.tests.helphintmessaging.multiplemsgsonformcontrols;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookMultipleMsgsOnRadioset extends OJetBase {
    public CookbookMultipleMsgsOnRadioset() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
        }
        
        @Test(groups = { "cookbook" , "messaging"})
        /*
        *  Test how Radioset component respond when multiple messages of different severities are set using the messages options
        *  The severity of messages from most to least severe are 'Fatal', 'Error', 'Warning', 'Info', 'Confirmation'.
        *  The message with the highest severity determines the marker style applied on the component.
        *  Messages are listed in order going from most severe to the least.
        */
        public void testAddMultipleMessages() throws Exception {
        
        startupTest("demo-helpHintsMessaging-multipleMessages.html", null);
        
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Note Window Multiple Messages");
        waitForElementVisible("id=datetimecontrol");

        //initially no msg exists on datetimecontrol
        //move focus on radioset and verify no notewindow exists
         Actions actions = new Actions(getWebDriver());
        WebElement text = getElement("id=radioSetId");    
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", text);
        Thread.sleep(500); 
        actions.moveToElement(text).perform();
        WebElement msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertNull(msg, "initially no message exists on Radioset");

        //Select "confirmation"
        WebElement buttonset = getElement("id=severityTypeButtonSet");
        WebElement confirmationBtn = getButtonAtIndexFromButtonSet(buttonset, 4);
  ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", confirmationBtn);
        Thread.sleep(500); 
        confirmationBtn.click();
        //Move focus on Radioset field
        //Verify that confirmation msg type is displayed
          ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", text);
        Thread.sleep(500); 
        actions.moveToElement(text).perform(); 
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Summary Text",
                            "confirmation summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Detail Text",
                            "Confirmation detail is displayed in note window");
        //Verify that Confirmation  icon is displayed in the error msgYou must enter a value.
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 0);
        boolean iconPresent = isChildElementPresent(msg, By.className("oj-message-confirmation-icon"));
        Assert.assertTrue(iconPresent, "Confirmation icon is present in not window");
        //verify that oj-invalid is not applied to it
        WebElement radioset = getElement("id=radioSetId");
        String classes = radioset.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "oj-invalid is NOT applied to Radioset");
        hasValidationErr = classes.indexOf("oj-warning") > -1;
        Assert.assertFalse(hasValidationErr, "oj-warning is NOT applied to Radioset");

        // info and confirmation selected
        actions.sendKeys(Keys.ESCAPE).build().perform();
        WebElement infoBtn = getButtonAtIndexFromButtonSet(buttonset, 3);
  ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", text);
        Thread.sleep(500); 
       
        infoBtn.click();
        //Move focus on Radioset field
        //Verify that info and confirmation msg types are displayed
          ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", text);
        Thread.sleep(500); 
        actions.moveToElement(text).perform(); 
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
        radioset = getElement("id=radioSetId");
        classes = radioset.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "oj-invalid is NOT applied to Radioset");
        hasValidationErr = classes.indexOf("oj-warning") > -1;
        Assert.assertFalse(hasValidationErr, "oj-warning is NOT applied to Radioset");

        //warning, info and confirmation is selected
        actions.sendKeys(Keys.ESCAPE).build().perform();
        WebElement warningBtn = getButtonAtIndexFromButtonSet(buttonset, 2);
  ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", warningBtn);
        Thread.sleep(500); 
       
        warningBtn.click();
        //Move focus on Radioset field
        //Verify that 3 msg types are displayed
         ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", text);
        Thread.sleep(500); 
        actions.moveToElement(text).perform();

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
        radioset = getElement("id=radioSetId");
        classes = radioset.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-warning") > -1;
        Assert.assertTrue(hasValidationErr, "oj-warning is applied to Radioset");

        //error, warning, info and confirmation msg types are selected
        actions.sendKeys(Keys.ESCAPE).build().perform();
        WebElement errorBtn = getButtonAtIndexFromButtonSet(buttonset, 1);
  ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", errorBtn);
        Thread.sleep(500); 
      
        errorBtn.click();
        //Move focus on Radioset field
        //Verify that 4 msg types are displayed

         ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", text);
        Thread.sleep(500); 
        actions.moveToElement(text).perform();
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
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
        iconPresent = isChildElementPresent(msg, By.className("oj-message-warning-icon"));
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
        radioset = getElement("id=radioSetId");
        classes = radioset.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "oj-invalid is applied to Radioset");

        //fatal, error, warning, info and confirmation msg types are selected
        actions.sendKeys(Keys.ESCAPE).build().perform();
        WebElement fatalBtn = getButtonAtIndexFromButtonSet(buttonset, 0);
  ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", fatalBtn);
        Thread.sleep(500); 
      
        fatalBtn.click();
        //Move focus on Radioset field
        //Verify that fatal, error, warning, info and confirmation msg types are displayed
         ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", text);
        Thread.sleep(500); 
        actions.moveToElement(text).perform(); 
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Fatal Error Summary Text",
                            "fatal summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Fatal Error Detail Text",
                            "fatal err detail is displayed in note window");
        //Verify that error icon is displayed in the error msgYou must enter a value.
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 0);
        errorIconPresent = isChildElementPresent(msg, By.className("oj-message-error-icon"));
        Assert.assertTrue(errorIconPresent, "error icon is present in not window");
        //error msg is second in th elist
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 1);
        Assert.assertEquals(msg.getText().trim(), "Error Summary Text", "error summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 1);
        Assert.assertEquals(msg.getText().trim(), "Error Detail Text", "fatal err detail is displayed in note window");
        //Verify that error icon is displayed in the error msg
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 1);
        errorIconPresent = isChildElementPresent(msg, By.className("oj-message-error-icon"));
        Assert.assertTrue(errorIconPresent, "error icon is present in note window");
        //warning msg is third in the list
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 2);
        Assert.assertEquals(msg.getText().trim(), "Warning Summary Text",
                            "warning summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 2);
        Assert.assertEquals(msg.getText().trim(), "Warning Detail Text",
                            "warning err detail is displayed in note window");
        //Verify that warning icon is displayed in the  msg
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 2);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-warning-icon"));
        Assert.assertTrue(iconPresent, "warning icon is present in note window");
        //info msg type is displayed fourth
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 3);
        Assert.assertEquals(msg.getText().trim(), "Info Summary Text", "info summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 3);
        Assert.assertEquals(msg.getText().trim(), "Info Detail Text", "info detail is displayed in note window");
        //Verify that info  icon is displayed in the  msg
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 3);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-info-icon"));
        Assert.assertTrue(iconPresent, "info icon is present in note window");
        // conformation msg type is displayed fifth
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 4);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Summary Text",
                            "confirmation summary is displayed in note window");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 4);
        Assert.assertEquals(msg.getText().trim(), "Confirmation Detail Text",
                            "Confirmation detail is displayed in note window");
        //Verify that Confirmation  icon is displayed in the error msgYou must enter a value.
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-content", 4);
        iconPresent = isChildElementPresent(msg, By.className("oj-message-confirmation-icon"));
        Assert.assertTrue(iconPresent, "Confirmation icon is present in note window");
        //verify that oj-invalid is applied to it
        radioset = getElement("id=radioSetId");
        classes = radioset.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "oj-invalid is applied to Radioset");

        //unselect all msg types and verify that uRadioset has no msges
        actions.sendKeys(Keys.ESCAPE).build().perform();
  ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", fatalBtn);
        Thread.sleep(500); 
     
        fatalBtn.click();
        errorBtn.click();
        warningBtn.click();
        infoBtn.click();
        confirmationBtn.click();
        actions.moveToElement(text).perform(); 
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertNull(msg, "no message exists on Radioset");
        }

        @Test(groups = { "cookbook", "messaging" })
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
        
        startupTest("demo-helpHintsMessaging-multipleMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Note Window Multiple Messages");
        waitForElementVisible("id=datetimecontrol");

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
        WebElement text = getElement("id=radioSetId");    
        
        //Deselect fatal msg
        fatalBtn.click();

        //Move focus on Radioset and verify that 4 msg types are displayed in order with correct icons
        //and that input text is decorated with oj-invalid
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
        WebElement radioset = getElement("id=radioSetId");
        String classes = radioset.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "oj-invalid is applied to Radioset");

        //deselect Error as well. Only warning, infor and conformation msg types are selected
        actions.sendKeys(Keys.ESCAPE).build().perform();
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
        radioset = getElement("id=radioSetId");
        classes = radioset.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-warning") > -1;
        Assert.assertTrue(hasValidationErr, "oj-warning is applied to Radioset");

        //deselect warning.  Only info and confirmation are selected
        actions.sendKeys(Keys.ESCAPE).build().perform();
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
        radioset = getElement("id=radioSetId");
        classes = radioset.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "oj-invalid is NOT applied to Radioset");
        hasValidationErr = classes.indexOf("oj-warning") > -1;
        Assert.assertFalse(hasValidationErr, "oj-warning is NOT applied to Radioset");

        //deselect info. Only confirmation is selected
        actions.sendKeys(Keys.ESCAPE).build().perform();
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
        radioset = getElement("id=radioSetId");
        classes = radioset.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "oj-invalid is NOT applied to Radioset");
        hasValidationErr = classes.indexOf("oj-warning") > -1;
        Assert.assertFalse(hasValidationErr, "oj-warning is NOT applied to Radioset");

        //deselect confirmation.  No msg types are now selected
        actions.sendKeys(Keys.ESCAPE).build().perform();
        confirmationBtn.click();
        actions.moveToElement(text).perform(); 
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertNull(msg, "no message exists on Radioset");

        }

        WebElement getButtonAtIndexFromButtonSet(WebElement buttonset, int index) {

        List<WebElement> btns = buttonset.findElements(By.className("oj-button"));
        return btns.get(index);

        }

}
