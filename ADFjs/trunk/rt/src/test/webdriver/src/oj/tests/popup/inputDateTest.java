package oj.tests.popup;

import java.util.Date;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class inputDateTest
  extends OJetBase
{
  private static final String TEST_PAGE = "testOjPopup.html";
  private static final String TEST_PAGE_TITLE = "ojPopup Test";
  private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
  private static final String POPUP_DIV = "popupdiv";
  private static final String INPUTDATE_ID = "date10";
  private static final String INPUTDATETIME_ID = "date11";
  private static final String DIALOG_WITH_MENU_ID = "dialogWithUserDefinedHeader";
  private static final String MENU_BUTTON_INSIDE_DIALOG = "dialogbtn1";
  private static final String MENU_ID = "myMenu";

  public inputDateTest()
  {
    super(new TestConfigBuilder().setContextRoot("popup/popupTest").setAppRoot("ojpopup").build());
  }

  @Test(groups =
    {
      "popup"
    })
  public void testInvokeDatepicker_AutoDismissNone()
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
    WebElement dateIcon =
      getElement("{\"element\":\"#" + INPUTDATE_ID + "\",\"subId\":\"oj-inputdatetime-calendar-icon\"}");
    dateIcon.click();
    //Verify that calendar content is displayed
    WebElement calendar = getElement("{\"element\":\"#" + INPUTDATE_ID + "\",\"subId\":\"oj-datepicker-content\"}");

    //get the grand parent because the "display" style is set on it
    WebElement grandParentElem = getParentElement(getParentElement(calendar));
    String style = grandParentElem.getAttribute("style");

    //verify that calendar is displayed
    boolean notDisplayed = style.indexOf("display: none;") > -1;
    Assert.assertFalse(notDisplayed);

    //verify that popup is open
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");
    //click on different element and verify that calendar closes
    WebElement inputField =
      getElement("{\"element\":\"#" + INPUTDATETIME_ID + "\",\"subId\":\"oj-inputdatetime-date-input\"}");

    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(inputField).click().perform();
    style = grandParentElem.getAttribute("style");
    //verify that calendar is not displayed any more
    boolean displayed = style.indexOf("display: block;") > -1;
    Assert.assertFalse(displayed);
    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "false");
  }

  @Test(groups =
    {
      "popup"
    })
  public void testInvokeDatepicker_AutoDismissFocusLoss()
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
    WebElement dateIcon =
      getElement("{\"element\":\"#" + INPUTDATE_ID + "\",\"subId\":\"oj-inputdatetime-calendar-icon\"}");
    dateIcon.click();
    //Verify that calendar content is displayed
    WebElement calendar = getElement("{\"element\":\"#" + INPUTDATE_ID + "\",\"subId\":\"oj-datepicker-content\"}");
    //get the grand parent because the "display" style is set on it
    WebElement grandParentElem = getParentElement(getParentElement(calendar));
    String style = grandParentElem.getAttribute("style");

    //verify that calendar is displayed
    boolean notDisplayed = style.indexOf("display: none;") > -1;
    Assert.assertFalse(notDisplayed);

    //verify that popup is open too
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");
    //click on different element and verify that calendar closes
    WebElement inputField =
      getElement("{\"element\":\"#" + INPUTDATETIME_ID + "\",\"subId\":\"oj-inputdatetime-date-input\"}");

    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(inputField).click().perform();
    style = grandParentElem.getAttribute("style");
    //verify that calendar is not displayed any more
    boolean displayed = style.indexOf("display: block;") > -1;
    Assert.assertFalse(displayed);
    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "false");
  }


}
