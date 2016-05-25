package oj.tests.popup;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class inputPasswordTest
  extends OJetBase
{
  private static final String TEST_PAGE = "testOjPopup.html";
  private static final String TEST_PAGE_TITLE = "ojPopup Test";
  private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
  private static final String POPUP_DIV = "popupdiv";
  private static final String INPUTPASSWORD_ID = "password";
  private static final String INPUTPASSWORD_VAL = "curr-pwdvalue";
  private static final String ACCORDION_ID = "accordionPage";


  public inputPasswordTest()
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
    //expand the collapsible that has password field
    evalJavascript("window.scrollBy(0,250)", "");
    WebElement headerIcon =
      getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":2}");

    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(headerIcon).click().perform();
    waitForMilliseconds(1000);
    
    //evalJavascript("window.scrollBy(0,800)", "");
    // WebElement inputField = getElement("id=" + INPUTPASSWORD_ID);
    WebElement inputField =
      getElement("{\"element\":\"#" + INPUTPASSWORD_ID + "\",\"subId\":\"oj-inputpassword-input\"}");

    actions = new Actions(getWebDriver());
    actions.moveToElement(inputField).click().perform();
    inputField.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
    inputField.sendKeys("Hello1");
    inputField.sendKeys(Keys.TAB);

    //Verify that value is set in password field
    String val = evalJavascript("return $('#" + INPUTPASSWORD_ID + "').ojInputPassword('option', 'value')");
    Assert.assertEquals(val, "Hello1");
    //check that observable value is updated on page
    WebElement textArea_val = getElement("id=" + INPUTPASSWORD_VAL);
    Assert.assertEquals(textArea_val.getText(), "Hello1");
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
    //evalJavascript("window.scrollBy(0,250)", "");
    WebElement headerIcon =
      getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":2}");

    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(headerIcon).click().perform();
    waitForMilliseconds(1000);

   // evalJavascript("window.scrollBy(0,550)", "");
    WebElement inputField =
      getElement("{\"element\":\"#" + INPUTPASSWORD_ID + "\",\"subId\":\"oj-inputpassword-input\"}");


    actions = new Actions(getWebDriver());
    actions.moveToElement(inputField).click().perform();
    inputField.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
    inputField.sendKeys("Hello2");
    inputField.sendKeys(Keys.TAB);

    actions.moveToElement(inputField).click().perform();
    //Verify that value is set in password field
    String val = evalJavascript("return $('#" + INPUTPASSWORD_ID + "').ojInputPassword('option', 'value')");
    Assert.assertEquals(val, "Hello2");
    //check that observable value is updated on page
    WebElement pwd_val = getElement("id=" + INPUTPASSWORD_VAL);
    Assert.assertEquals(pwd_val.getText(), "Hello2");
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
  public void testTitleAndHint_AutoDismissFocusLoss()
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
    WebElement headerIcon =
      getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":2}");

    headerIcon.click();
    evalJavascript("window.scrollBy(0,800)", "");
    this.waitForMilliseconds(1000);
    WebElement inputField =
      getElement("{\"element\":\"#" + INPUTPASSWORD_ID + "\",\"subId\":\"oj-inputpassword-input\"}");

    Actions actions = new Actions(getWebDriver());

    //Move focus to password field

    actions.moveToElement(inputField).click().perform();

    //Verify the title value
    WebElement msg = getMessagingContentNodeBySubId(inputField, "oj-messaging-popup-title", 0);
    Assert.assertEquals(msg.getText().trim(), "enter password");
    //Verify the hint value
    msg = getMessagingContentNodeBySubId(inputField, "oj-messaging-popup-validator-hint", 0);
    Assert.assertEquals(msg.getText().trim(), "enter at least 3 letters or numbers.");
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
  public void testValidatorError_AutoDismissFocusLoss()
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
    WebElement headerIcon =
      getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":2}");

    headerIcon.click();
    evalJavascript("window.scrollBy(0,800)", "");
    this.waitForMilliseconds(1000);
    //Move focus to password field
    WebElement inputField =
      getElement("{\"element\":\"#" + INPUTPASSWORD_ID + "\",\"subId\":\"oj-inputpassword-input\"}");

    this.waitForMilliseconds(10000);
    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(inputField).click().perform();
    inputField.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
    inputField.sendKeys("xx");
    inputField.sendKeys(Keys.TAB);

    actions.moveToElement(inputField).click().perform();

    //Verify the validator error value
    WebElement msg = getMessagingContentNodeBySubId(inputField, "oj-messaging-popup-message-summary", 0);
    Assert.assertEquals(msg.getText().trim(), "Format is incorrect.");
    msg = getMessagingContentNodeBySubId(inputField, "oj-messaging-popup-message-detail", 0);
    Assert.assertEquals(msg.getText().trim(), "You must enter at least 3 letters or numbers");
    //verify that popup is open
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
  }

}
