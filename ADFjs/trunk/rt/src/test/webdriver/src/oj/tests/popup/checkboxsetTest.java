package oj.tests.popup;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import org.testng.Assert;
import org.testng.annotations.Test;

public class checkboxsetTest
  extends OJetBase
{
  private static final String TEST_PAGE = "testOjPopup.html";
  private static final String TEST_PAGE_TITLE = "ojPopup Test";
  private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
  private static final String POPUP_DIV = "popupdiv";
  private static final String CHECKBOXSET_ID = "checkboxSetAgreeId";

  private static final String ACCORDION_ID = "accordionPage";

  public checkboxsetTest()
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
    //expand the collapsible that has radioset
    evalJavascript("window.scrollBy(0,250)", "");
    WebElement headerIcon =
      getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":2}");

    headerIcon.click();
    this.waitForMilliseconds(1000);
    evalJavascript("window.scrollBy(0,850)", "");

    //set the checkbox value
    List<WebElement> inputs =
      findElements("{\"element\":\"#" + CHECKBOXSET_ID + "\",\"subId\":\"oj-checkboxset-inputs\"}");
    WebElement text = inputs.get(0);

    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(text).click().perform();


    //Verify that value is set in checkbox
    String val = evalJavascript("return $('#" + CHECKBOXSET_ID + "').ojCheckboxset('option', 'value')");
    Assert.assertEquals(val, "[agree]");


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
    //set  autoDismiss  to "focusLoss"
    evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'focusLoss')");
    //open the popup by clicking on the button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    //expand the collapsible that has radioset
    evalJavascript("window.scrollBy(0,250)", "");
    WebElement headerIcon =
      getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":2}");

    headerIcon.click();
    this.waitForMilliseconds(1000);
    evalJavascript("window.scrollBy(0,950)", "");

    //set the checkbox value
    List<WebElement> inputs =
      findElements("{\"element\":\"#" + CHECKBOXSET_ID + "\",\"subId\":\"oj-checkboxset-inputs\"}");
    WebElement text = inputs.get(0);

    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(text).click().perform();


    //Verify that value is set in checkbox
    String val = evalJavascript("return $('#" + CHECKBOXSET_ID + "').ojCheckboxset('option', 'value')");
    Assert.assertEquals(val, "[agree]");


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
  public void testTitle()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

    //wait for the button to be displayed
    waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
    //set  autoDismiss  to "focusLoss"
    evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'focusLoss')");
    //open the popup by clicking on the button
    //click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    WebElement btn1 = getElement(BUTTON_TOGGLE_POPUP_DIV);
    btn1.click();
  
    //expand the collapsible that has radioset
    //evalJavascript("window.scrollBy(0,250)", "");
    WebElement headerIcon =
      getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-disclosure\",\"index\":2}");

    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(headerIcon).click().perform();

    this.waitForMilliseconds(1000);
    //evalJavascript("window.scrollBy(0,950)", "");

    //move focus to "Deep Dish" (first in radio list)
    List<WebElement> inputs =
      findElements("{\"element\":\"#" + CHECKBOXSET_ID + "\",\"subId\":\"oj-checkboxset-inputs\"}");
    WebElement text = inputs.get(0);

    evalJavascript("$('#" + text.getAttribute("id") + "').focus()");
    this.waitForMilliseconds(1000);

    actions = new Actions(getWebDriver());
((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", text);
        Thread.sleep(500); 
    actions.moveToElement(text).perform();

 
    //Verify the title value
    WebElement msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-title", 0);
    Assert.assertEquals(msg.getText().trim(), "checkboxset title");


    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);

  }

  @Test(groups =
    {
      "popup"
    })
  public void testValidationError()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

    //wait for the button to be displayed
    waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
    //set  autoDismiss  to "focusLoss"
    evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'focusLoss')");
    //open the popup by clicking on the button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    //expand the collapsible that has radioset
    evalJavascript("window.scrollBy(0,250)", "");
    WebElement headerIcon =
      getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":2}");

    headerIcon.click();
    this.waitForMilliseconds(1000);
    evalJavascript("window.scrollBy(0,1050)", "");
    List<WebElement> inputs =
      findElements("{\"element\":\"#" + CHECKBOXSET_ID + "\",\"subId\":\"oj-checkboxset-inputs\"}");

    WebElement text = inputs.get(0);
    evalJavascript("$('#" + text.getAttribute("id") + "').focus()");


    //set the value and then unset it because (deferred validation)
    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(text).click().perform();
    waitForMilliseconds(1000);
    actions.moveToElement(text).click().perform();
    waitForMilliseconds(1000);
    //Move the focus to different element
    //text.sendKeys(Keys.TAB);

    //move focus back to checkboxset to see the error
    //actions.moveToElement(text).perform();
    evalJavascript("$('#" + text.getAttribute("id") + "').focus()");
    waitForMilliseconds(2000);

    //Verify the title value
    WebElement msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
    Assert.assertEquals(msg.getText().trim(), "Value is required.");
    msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
    Assert.assertEquals(msg.getText().trim(), "You must enter a value.");

    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);

  }

}
