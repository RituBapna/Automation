package oj.tests.popup;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class dialogTest
  extends OJetBase
{
  private static final String TEST_PAGE = "testOjPopup.html";
  private static final String TEST_PAGE_TITLE = "ojPopup Test";
  private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
  private static final String POPUP_DIV = "popupdiv";
  private static final String FIRST_IN_POPUP_COLLAPSIBLE_ID = "collapsiblePage1";
  private static final String INVOKE_DIALOG_WITH_MENU_BUTTON = "invokeDialogWithMenuBtn";
  private static final String DIALOG_WITH_MENU_ID = "dialogWithUserDefinedHeader";

  public dialogTest()
  {
    super(new TestConfigBuilder().setContextRoot("popup/popupTest").setAppRoot("ojpopup").build());
  }

  @Test(groups =
    {
      "popup"
    })
  public void testInvokeDialog_AutoDismissNone()
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
    //Assert that popup is open
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //expand the collapsible that contains the button to invoke dialog
    WebElement headerIcon =
      getElement("{\"element\":\"#" + FIRST_IN_POPUP_COLLAPSIBLE_ID + "\",\"subId\":\"oj-collapsible-header-icon\"}");
    headerIcon.click();
    waitForMilliseconds(1000);
    //Click on the button to invoke the dialog.
    click("id=" + INVOKE_DIALOG_WITH_MENU_BUTTON);

    //assert that dialog is open
    rtn = evalJavascript("return $('#" + DIALOG_WITH_MENU_ID + "').ojDialog('isOpen')");
    Assert.assertEquals(rtn, "true");
    //asert that popup is  open too
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //Close the dialog by clicking on its close icon
    WebElement closeIcon =
      getElement("{\"element\":\"#" + DIALOG_WITH_MENU_ID + "\",\"subId\":\"oj-dialog-close-icon\"}");
    closeIcon.click();

    //assert that dialog is NOT open
    rtn = evalJavascript("return $('#" + DIALOG_WITH_MENU_ID + "').ojDialog('isOpen')");
    Assert.assertEquals(rtn, "false");
    // close popup by clicking on toggle button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    //assert popup is not open
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "false");

  }

  @Test(groups =
    {
      "popup"
    })
  public void testInvokeDialog_AutoDismissFocusLoss()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

    //wait for the button to be displayed
    waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
    //set autoDismiss set to "focusLoss"
    evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'focusLoss')");
    //open the popup by clicking on the button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    //Assert that popup is open
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //expand the collapsible that contains the button to invoke dialog
    WebElement headerIcon =
      getElement("{\"element\":\"#" + FIRST_IN_POPUP_COLLAPSIBLE_ID + "\",\"subId\":\"oj-collapsible-header-icon\"}");
    headerIcon.click();
    waitForMilliseconds(1000);


    //Click on the button to invoke the dialog.
    click("id=" + INVOKE_DIALOG_WITH_MENU_BUTTON);

    //assert that dialog is open
    rtn = evalJavascript("return $('#" + DIALOG_WITH_MENU_ID + "').ojDialog('isOpen')");
    Assert.assertEquals(rtn, "true");
    //asert that popup is NOT open
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "false");

    //Close the dialog by clicking on its close icon
    WebElement closeIcon =
      getElement("{\"element\":\"#" + DIALOG_WITH_MENU_ID + "\",\"subId\":\"oj-dialog-close-icon\"}");
    closeIcon.click();

    //assert that dialog and popup are NOT open
    rtn = evalJavascript("return $('#" + DIALOG_WITH_MENU_ID + "').ojDialog('isOpen')");
    Assert.assertEquals(rtn, "false");


  }
}
