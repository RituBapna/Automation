package oj.tests.popup;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class inlineDateTimeTest
  extends OJetBase
{
  private static final String TEST_PAGE = "testOjPopup.html";
  private static final String TEST_PAGE_TITLE = "ojPopup Test";
  private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
  private static final String POPUP_DIV = "popupdiv";
  private static final String INLINEDATETIME_ID = "inline";
  private static final String INPUTDATE_ID = "date10";

  public inlineDateTimeTest()
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

    //Verify that calendar content is displayed
    WebElement calendar =
      getElement("{\"element\":\"#" + INLINEDATETIME_ID + "\",\"subId\":\"oj-datepicker-content\"}");

    String style = calendar.getAttribute("style");
    String c = calendar.getAttribute("class");
    System.out.println("@@@@STYLE: " + style);
    System.out.println("@@@@CLASS: " + c);
    //verify that calendar is displayed
    boolean displayed = style.indexOf("display: block;") > -1;
    Assert.assertTrue(displayed);

    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);

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

    //Verify that calendar content is displayed
    WebElement calendar =
      getElement("{\"element\":\"#" + INLINEDATETIME_ID + "\",\"subId\":\"oj-datepicker-content\"}");
    //get the grand parent because the "display" style is set on it

    String style = calendar.getAttribute("style");

    //verify that calendar is displayed
    boolean displayed = style.indexOf("display: block;") > -1;
    Assert.assertTrue(displayed);

    //verify that popup is open too
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);

  }

  @Test(groups =
    {
      "popup"
    })
  public void testInvokeTimepicker_AutoDismissNone()
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
    WebElement timeIcon =
      getElement("{\"element\":\"#" + INLINEDATETIME_ID + "\",\"subId\":\"oj-inputdatetime-time-icon\"}");
    timeIcon.click();
    //Verify that calendar content is displayed
    WebElement timeList = getElement("{\"element\":\"#" + INLINEDATETIME_ID + "\",\"subId\":\"oj-listbox-drop\"}");
    //get the grand parent because the "display" style is set on it
    //  WebElement grandParentElem = getParentElement(getParentElement(calendar));
    String style = timeList.getAttribute("style");

    //verify that calendar is displayed
    boolean notDisplayed = style.indexOf("display: none;") > -1;
    Assert.assertFalse(notDisplayed);

    //verify that popup is open too
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");
    //click on different element and verify that calendar closes
    WebElement inputField =
      getElement("{\"element\":\"#" + INPUTDATE_ID + "\",\"subId\":\"oj-inputdatetime-date-input\"}");

    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(inputField).click().perform();
    style = timeList.getAttribute("style");
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
  public void testInvokeTimepicker_AutoDismissFocusLoss()
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
    WebElement timeIcon =
      getElement("{\"element\":\"#" + INLINEDATETIME_ID + "\",\"subId\":\"oj-inputdatetime-time-icon\"}");
    timeIcon.click();
    //Verify that time list is displayed
    WebElement timeList = getElement("{\"element\":\"#" + INLINEDATETIME_ID + "\",\"subId\":\"oj-listbox-drop\"}");

    String style = timeList.getAttribute("style");
    boolean notDisplayed = style.indexOf("display: none;") > -1;
    Assert.assertFalse(notDisplayed);

    //verify that popup is open too
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");
    //click on different element and verify that time list closes
    WebElement inputField =
      getElement("{\"element\":\"#" + INPUTDATE_ID + "\",\"subId\":\"oj-inputdatetime-date-input\"}");

    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(inputField).click().perform();
    style = timeList.getAttribute("style");
    //verify that time list is not displayed any more
    boolean displayed = style.indexOf("display: block;") > -1;
    Assert.assertFalse(displayed);
    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "false");
  }
}
