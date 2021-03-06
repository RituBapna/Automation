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

public class msgsOnTextArea
  extends OJetBase
{
  public msgsOnTextArea()
  {
    super(new TestConfigBuilder().setContextRoot("popup/popupTest").setAppRoot("ojpopup").build());
  }

  @Test(groups =
    {
      "popup"
    })
  /*
   *  Test how checkboxset component respond when multiple messages of different severities are set using the messages options
   *  The severity of messages from most to least severe are 'Fatal', 'Error', 'Warning', 'Info', 'Confirmation'.
   *  The message with the highest severity determines the marker style applied on the component.
   *  Messages are listed in order going from most severe to the least.
   */
  public void testAddMultipleMessages()
    throws Exception
  {

    startupTest("testDiffMessageTypesInPopup.html", null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", "ojPopup - Message Type Test");
    waitForElementVisible("id=btnGo");

    // open the popup
    click("id=btnGo");

    //initially no msg exists on textareacontrol
    //move focus on textareacontrol and verify no notewindow exists

    Actions actions = new Actions(getWebDriver());
    WebElement text = getElement("id=textareacontrol");
    actions.moveToElement(text).click().perform();
    WebElement msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
    Assert.assertNull(msg, "initially no message exists on textArea");

    //Select "confirmation"
    WebElement buttonset = getElement("id=severityTypeButtonSet");
    WebElement confirmationBtn = getButtonAtIndexFromButtonSet(buttonset, 4);
    //confirmationBtn.click();
    actions.moveToElement(confirmationBtn).click().perform();
    
    //Move focus on textArea field
    //Verify that confirmation msg type is displayed
    actions.moveToElement(text).click().perform();
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
    WebElement parentElem = getParentElement(text);
    String classes = parentElem.getAttribute("class");
    boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
    Assert.assertFalse(hasValidationErr, "oj-invalid is NOT applied to textArea");
    hasValidationErr = classes.indexOf("oj-warning") > -1;
    Assert.assertFalse(hasValidationErr, "oj-warning is NOT applied to textArea");
    //move focus to time component so that msg popup does not cover the buttonset
    WebElement time = getElement("id=timecontrol");
    actions.moveToElement(time).click().perform();
    // info and confirmation selected
    //actions.sendKeys(Keys.ESCAPE).build().perform();
    WebElement infoBtn = getButtonAtIndexFromButtonSet(buttonset, 3);
    actions.moveToElement(infoBtn).click().perform();   
    //infoBtn.click();
    
    //Move focus on textArea field
    //Verify that info and confirmation msg types are displayed
    actions.moveToElement(text).click().perform();
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
    parentElem = getParentElement(text);
    classes = parentElem.getAttribute("class");
    hasValidationErr = classes.indexOf("oj-invalid") > -1;
    Assert.assertFalse(hasValidationErr, "oj-invalid is NOT applied to textArea");
    hasValidationErr = classes.indexOf("oj-warning") > -1;
    Assert.assertFalse(hasValidationErr, "oj-warning is NOT applied to textArea");
    //move focus to time component so that msg popup does not cover the buttonset

    actions.moveToElement(time).click().perform();
    //warning, info and confirmation is selected
    //actions.sendKeys(Keys.ESCAPE).build().perform();
    WebElement warningBtn = getButtonAtIndexFromButtonSet(buttonset, 2);
    actions.moveToElement(warningBtn).click().perform();
    //warningBtn.click();
    //Move focus on textArea field
    //Verify that 3 msg types are displayed
    actions.moveToElement(text).click().perform();

    msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
    Assert.assertEquals(msg.getText().trim(), "Warning Summary Text", "warning summary is displayed in note window");
    msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
    Assert.assertEquals(msg.getText().trim(), "Warning Detail Text", "warning err detail is displayed in note window");
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
    parentElem = getParentElement(text);
    classes = parentElem.getAttribute("class");
    hasValidationErr = classes.indexOf("oj-warning") > -1;
    Assert.assertTrue(hasValidationErr, "oj-warning is applied to textArea");

    //move focus to time component so that msg popup does not cover the buttonset
    actions.moveToElement(time).click().perform();
    //error, warning, info and confirmation msg types are selected
    //actions.sendKeys(Keys.ESCAPE).build().perform();
    WebElement errorBtn = getButtonAtIndexFromButtonSet(buttonset, 1);
    //errorBtn.click();
    actions.moveToElement(errorBtn).click().perform();

    //Move focus on textArea field
    //Verify that 4 msg types are displayed

    actions.moveToElement(text).click().perform();
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
    Assert.assertEquals(msg.getText().trim(), "Warning Summary Text", "warning summary is displayed in note window");
    msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 1);
    Assert.assertEquals(msg.getText().trim(), "Warning Detail Text", "warning err detail is displayed in note window");
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
    parentElem = getParentElement(text);
    classes = parentElem.getAttribute("class");
    hasValidationErr = classes.indexOf("oj-invalid") > -1;
    Assert.assertTrue(hasValidationErr, "oj-invalid is applied to textArea");

    //move focus to time component so that msg popup does not cover the buttonset
    actions.moveToElement(time).click().perform();
    //fatal, error, warning, info and confirmation msg types are selected
    //actions.sendKeys(Keys.ESCAPE).build().perform();
    WebElement fatalBtn = getButtonAtIndexFromButtonSet(buttonset, 0);
    //fatalBtn.click();
    actions.moveToElement(fatalBtn).click().perform();

    
    //Move focus on textArea field
    //Verify that fatal, error, warning, info and confirmation msg types are displayed
    actions.moveToElement(text).click().perform();
    msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
    Assert.assertEquals(msg.getText().trim(), "Fatal Error Summary Text", "fatal summary is displayed in note window");
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
    Assert.assertEquals(msg.getText().trim(), "Warning Summary Text", "warning summary is displayed in note window");
    msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 2);
    Assert.assertEquals(msg.getText().trim(), "Warning Detail Text", "warning err detail is displayed in note window");
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
    parentElem = getParentElement(text);
    classes = parentElem.getAttribute("class");
    hasValidationErr = classes.indexOf("oj-invalid") > -1;
    Assert.assertTrue(hasValidationErr, "oj-invalid is applied to textArea");

    //unselect all msg types and verify that utextArea has no msges
    //actions.sendKeys(Keys.ESCAPE).build().perform();
    actions.moveToElement(fatalBtn).click().perform();
    //fatalBtn.click();
    
    actions.moveToElement(errorBtn).click().perform();
    //errorBtn.click();
    
    actions.moveToElement(warningBtn).click().perform();
    //warningBtn.click();

    actions.moveToElement(infoBtn).click().perform();    
    //infoBtn.click();

    actions.moveToElement(confirmationBtn).click().perform();        
    //confirmationBtn.click();
    
    actions.moveToElement(text).click().perform();
    msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
    Assert.assertNull(msg, "no message exists on textArea");
  }

  @Test(groups =
    {
      "popup"
    })
  /*
   * Toggle off any 'severity type' button to remove the message of the selected severity from the 'messagesCustom' option of each component.
   * Notice the component is styled based on the highest severity of the messages. If the highest severity is
   *   'fatal', the oj-invalid marker style is applied to the component
   *   'error', the oj-invalid marker style is applied to the component. NOTE: 'fatal' messages have the same visual effect as 'error'.
   *   'warning', the oj-warning marker style is applied to the component.
   *   'info', no styles are applied to the component.
   *   'confirmation', no styles are applied to the component.
   */
  public void testRemoveMessageTypesOneByOne()
    throws Exception
  {

    startupTest("testDiffMessageTypesInPopup.html", null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", "ojPopup - Message Type Test");
    waitForElementVisible("id=btnGo");

    // open the popup
    click("id=btnGo");

    //select all msg types
    waitForElementVisible("id=severityTypeButtonSet");
    WebElement buttonset = getElement("id=severityTypeButtonSet");
    WebElement confirmationBtn = getButtonAtIndexFromButtonSet(buttonset, 4);
    WebElement infoBtn = getButtonAtIndexFromButtonSet(buttonset, 3);
    WebElement warningBtn = getButtonAtIndexFromButtonSet(buttonset, 2);
    WebElement errorBtn = getButtonAtIndexFromButtonSet(buttonset, 1);
    WebElement fatalBtn = getButtonAtIndexFromButtonSet(buttonset, 0);
    
    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(fatalBtn).click().perform();    
    //fatalBtn.click();
    
    actions = new Actions(getWebDriver());
    actions.moveToElement(errorBtn).click().perform();
    //errorBtn.click();

    actions = new Actions(getWebDriver());
    actions.moveToElement(warningBtn).click().perform();    
    //warningBtn.click();
    
    actions = new Actions(getWebDriver());
    actions.moveToElement(infoBtn).click().perform();    
    //infoBtn.click();

    actions = new Actions(getWebDriver());
    actions.moveToElement(confirmationBtn).click().perform();        
    //confirmationBtn.click();
    
    //Deselect fatal msg
    
    actions = new Actions(getWebDriver());
    actions.moveToElement(fatalBtn).click().perform();        
    //fatalBtn.click();
    
    //Move focus on textArea and verify that 4 msg types are displayed in order with correct icons
    //and that input text is decorated with oj-invalid
    actions = new Actions(getWebDriver());
    WebElement text = getElement("id=textareacontrol");
    actions.moveToElement(text).click().perform();
    
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
    Assert.assertEquals(msg.getText().trim(), "Warning Summary Text", "warning summary is displayed in note window");
    msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 1);
    Assert.assertEquals(msg.getText().trim(), "Warning Detail Text", "warning err detail is displayed in note window");
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
    WebElement parentElem = getParentElement(text);
    String classes = parentElem.getAttribute("class");
    boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
    Assert.assertTrue(hasValidationErr, "oj-invalid is applied to textArea");

    //move focus to time component so that msg popup does not cover the buttonset
    WebElement time = getElement("id=timecontrol");
    actions.moveToElement(time).click().perform();
    //deselect Error as well. Only warning, infor and conformation msg types are selected
    //actions.sendKeys(Keys.ESCAPE).build().perform();
    errorBtn.click();
    actions.moveToElement(text).click().perform();
    //warning is first
    msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
    Assert.assertEquals(msg.getText().trim(), "Warning Summary Text", "warning summary is displayed in note window");
    msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
    Assert.assertEquals(msg.getText().trim(), "Warning Detail Text", "warning err detail is displayed in note window");
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
    parentElem = getParentElement(text);
    classes = parentElem.getAttribute("class");
    hasValidationErr = classes.indexOf("oj-warning") > -1;
    Assert.assertTrue(hasValidationErr, "oj-warning is applied to textArea");

    //move focus to time component so that msg popup does not cover the buttonset
    actions.moveToElement(time).click().perform();
    //deselect warning.  Only info and confirmation are selected
    //actions.sendKeys(Keys.ESCAPE).build().perform();
    warningBtn.click();
    actions.moveToElement(text).click().perform();
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
    parentElem = getParentElement(text);
    classes = parentElem.getAttribute("class");
    hasValidationErr = classes.indexOf("oj-invalid") > -1;
    Assert.assertFalse(hasValidationErr, "oj-invalid is NOT applied to textArea");
    hasValidationErr = classes.indexOf("oj-warning") > -1;
    Assert.assertFalse(hasValidationErr, "oj-warning is NOT applied to textArea");

    //move focus to time component so that msg popup does not cover the buttonset
    actions.moveToElement(time).click().perform();
    //deselect info. Only confirmation is selected
    //actions.sendKeys(Keys.ESCAPE).build().perform();
    infoBtn.click();
    //only confirmation msg is displayed
    actions.moveToElement(text).click().perform();
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
    parentElem = getParentElement(text);
    classes = parentElem.getAttribute("class");
    hasValidationErr = classes.indexOf("oj-invalid") > -1;
    Assert.assertFalse(hasValidationErr, "oj-invalid is NOT applied to textArea");
    hasValidationErr = classes.indexOf("oj-warning") > -1;
    Assert.assertFalse(hasValidationErr, "oj-warning is NOT applied to textArea");

    //move focus to time component so that msg popup does not cover the buttonset
    actions.moveToElement(time).click().perform();
    //deselect confirmation.  No msg types are now selected
    //actions.sendKeys(Keys.ESCAPE).build().perform();
    //confirmationBtn.click();
    actions.moveToElement(confirmationBtn).click().perform();
    actions.moveToElement(text).click().perform();
    msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
    Assert.assertNull(msg, "no message exists on textArea");

  }

  WebElement getButtonAtIndexFromButtonSet(WebElement buttonset, int index)
  {

    List<WebElement> btns = buttonset.findElements(By.className("oj-button"));
    return btns.get(index);

  }
}
