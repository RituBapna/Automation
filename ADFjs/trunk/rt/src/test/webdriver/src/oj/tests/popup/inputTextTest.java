package oj.tests.popup;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class inputTextTest
  extends OJetBase
{
  private static final String TEST_PAGE = "testOjPopup.html";
  private static final String TEST_PAGE_TITLE = "ojPopup Test";
  private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
  private static final String POPUP_DIV = "popupdiv";
  private static final String INPUTTEXT_ID = "date12";

  private static final String ACCORDION_ID = "accordionPage";

  public inputTextTest()
  {
    super(new TestConfigBuilder().setContextRoot("popup/popupTest").setAppRoot("ojpopup").build());
  }

  @Test(groups =
    {
      "popup"
    })
  public void testSetValue_AutoDismissNone()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

    //wait for the button to be displayed
    waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
    //set  autoDismiss  to "none"
    evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'none')");
    //open the popup by clicking on the button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);

    WebElement inputField = getElement("id=" + INPUTTEXT_ID);


    inputField.sendKeys("1:30");
    inputField.sendKeys(Keys.TAB);

    //Verify that value is set in textArea field
    String val = evalJavascript("return $('#" + INPUTTEXT_ID + "').ojInputText('option', 'value')");
    Assert.assertEquals(val, "T01:30:00");

    //verify that popup is open
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);

  }

  @Test(groups =
    {
      "popup"
    })
  public void testSetValue_AutoDismissFocusLoss()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

    //wait for the button to be displayed
    waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
    //set  autoDismiss  to "none"
    evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'none')");
    //open the popup by clicking on the button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    WebElement inputField = getElement("id=" + INPUTTEXT_ID);


    inputField.sendKeys("2:30 pm");
    inputField.sendKeys(Keys.TAB);

    //Verify that value is set in textArea field
    String val = evalJavascript("return $('#" + INPUTTEXT_ID + "').ojInputText('option', 'value')");
    Assert.assertEquals(val, "T14:30:00");

    //verify that popup is open
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
  }

  @Test(groups =
    {
      "popup"
    })
  public void testTitleAndPlaceholder_AutoDismissFocusLoss()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

    //wait for the button to be displayed
    waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
    //set  autoDismiss  to "none"
    evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'none')");
    //open the popup by clicking on the button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    //get the inputText field
    WebElement inputField = getElement("id=" + INPUTTEXT_ID);
    //Verify the placeholder Value
    String placeholderVal = inputField.getAttribute("placeholder");
    Assert.assertEquals(placeholderVal, "h:mm a");
    Actions actions = new Actions(getWebDriver());

    //Move focus to inputtext field

    actions.moveToElement(inputField).click().perform();

    //Verify the title value
    WebElement msg = getMessagingContentNodeBySubId(inputField, "oj-messaging-popup-title", 0);
    Assert.assertEquals(msg.getText().trim(), "enter the time in hours, minutes");

    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
  }

  @Test(groups =
    {
      "popup"
    })
  public void testConverterError_AutoDismissFocusLoss()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

    //wait for the button to be displayed
    waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
    //set  autoDismiss  to "none"
    evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'none')");
    //open the popup by clicking on the button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    WebElement inputField = getElement("id=" + INPUTTEXT_ID);
    //MEnter incorrect value in inputtext Field

    Actions actions = new Actions(getWebDriver());

    inputField.sendKeys("x");
    inputField.sendKeys(Keys.TAB);
    //move focus to input Text field
    actions.moveToElement(inputField).click().perform();
    waitForMilliseconds(500);
    actions.moveToElement(inputField).click().perform();
    waitForMilliseconds(1000);
    //Verify the converter error value
    WebElement msg = getMessagingContentNodeBySubId(inputField, "oj-messaging-popup-message-summary", 0);
    Assert.assertEquals(msg.getText().trim(), "'x' is not in the expected time format.");
    msg = getMessagingContentNodeBySubId(inputField, "oj-messaging-popup-message-detail", 0);
    Assert.assertEquals(msg.getText().trim(), "Enter a value in the same format as this example: '3:45 PM'");

    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
  }

}


