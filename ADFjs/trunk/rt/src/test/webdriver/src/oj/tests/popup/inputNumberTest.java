package oj.tests.popup;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class inputNumberTest
  extends OJetBase
{
  private static final String TEST_PAGE = "testOjPopup.html";
  private static final String TEST_PAGE_TITLE = "ojPopup Test";
  private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
  private static final String POPUP_DIV = "popupdiv";
  private static final String INPUTNUMBER_ID = "currency13";


  public inputNumberTest()
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
    WebElement upArrow = getElement("{\"element\":\"#" + INPUTNUMBER_ID + "\",\"subId\":\"oj-inputnumber-up\"}");
    upArrow.click();
    //Verify that value is set in inputNumbner field
    WebElement inputField = getElement("{\"element\":\"#" + INPUTNUMBER_ID + "\",\"subId\":\"oj-inputnumber-input\"}");

    String val = inputField.getAttribute("value");

    Assert.assertEquals(val, "$ 1.00");

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
    WebElement downArrow = getElement("{\"element\":\"#" + INPUTNUMBER_ID + "\",\"subId\":\"oj-inputnumber-down\"}");

    downArrow.click();
    //Verify that value is set in inputNumbner field
    WebElement inputField = getElement("{\"element\":\"#" + INPUTNUMBER_ID + "\",\"subId\":\"oj-inputnumber-input\"}");

    String val = inputField.getAttribute("value");

    Assert.assertEquals(val, "-$ 1.00");

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
  public void testTitle_AutoDismissFocusLoss()
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

    //Move focus to inputNumbner field
    WebElement inputField = getElement("{\"element\":\"#" + INPUTNUMBER_ID + "\",\"subId\":\"oj-inputnumber-input\"}");
    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(inputField).click().perform();

    //Verify the title value
    WebElement msg = getMessagingContentNodeBySubId(inputField, "oj-messaging-popup-title", 0);
    Assert.assertEquals(msg.getText().trim(), "enter an amount with or without grouping separator");
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
  public void testConvertorError_AutoDismissFocusLoss()
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
    evalJavascript("window.scrollBy(0,250)", "");
    //Move focus to inputNumbner field
    WebElement inputField = getElement("{\"element\":\"#" + INPUTNUMBER_ID + "\",\"subId\":\"oj-inputnumber-input\"}");
    inputField.sendKeys("xx");
    inputField.sendKeys(Keys.TAB);
    WebElement inputTextField = getElement("id=date12");
    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(inputTextField).click().perform();
    actions.moveToElement(inputField).click().perform();
    this.waitForMilliseconds(5000);
    //Verify the convertor error value
    WebElement msg = getMessagingContentNodeBySubId(inputField, "oj-messaging-popup-message-summary", 0);
    Assert.assertEquals(msg.getText().trim(), "'xx' is not in the expected currency format.");
    msg = getMessagingContentNodeBySubId(inputField, "oj-messaging-popup-message-detail", 0);
    Assert.assertEquals(msg.getText().trim(), "Enter a value in the same format as this example: '$ 12,345.99'",
                        "Confirmation detail is displayed in note window");
    //verify that popup is open
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
  }

}
