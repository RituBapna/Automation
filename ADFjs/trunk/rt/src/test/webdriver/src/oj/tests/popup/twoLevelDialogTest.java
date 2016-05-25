package oj.tests.popup;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class twoLevelDialogTest
  extends OJetBase
{
  private static final String TEST_PAGE = "testOjPopup.html";
  private static final String TEST_PAGE_TITLE = "ojPopup Test";
  private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
  private static final String POPUP_DIV = "popupdiv";
  private static final String FIRST_IN_POPUP_COLLAPSIBLE_ID = "collapsiblePage1";
  private static final String INVOKE_DIALOG_WITH_MENU_BUTTON = "invokeDialogWithMenuBtn";
  private static final String DIALOG_WITH_MENU_ID = "dialogWithUserDefinedHeader";
  private static final String INVOKE_SECOND_LEVEL_DIALOG_BUTTON = "openDlgWithTable1";
  private static final String DIALOG_WITH_TABLE_ID = "simpleDialog2";


  public twoLevelDialogTest()
  {
    super(new TestConfigBuilder().setContextRoot("popup/popupTest").setAppRoot("ojpopup").build());
  }

  @Test(groups =
    {
      "popup"
    })
  public void testInvoke2LevelsOfDialog_AutoDismissNone()
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


    //expand the collapsible that contains the button to invoke dialog
    WebElement headerIcon =
      getElement("{\"element\":\"#" + FIRST_IN_POPUP_COLLAPSIBLE_ID + "\",\"subId\":\"oj-collapsible-header-icon\"}");
    headerIcon.click();
    waitForMilliseconds(1000);
    //Click on the button to invoke the first level dialog.
    click("id=" + INVOKE_DIALOG_WITH_MENU_BUTTON);
    // on button inside the dialog to invoke another dialog
    click("id=" + INVOKE_SECOND_LEVEL_DIALOG_BUTTON);
    //assert that both the dialogs are open

    String rtn = evalJavascript("return $('#" + DIALOG_WITH_MENU_ID + "').ojDialog('isOpen')");
    Assert.assertEquals(rtn, "true");
    rtn = evalJavascript("return $('#" + DIALOG_WITH_TABLE_ID + "').ojDialog('isOpen')");
    Assert.assertEquals(rtn, "true");

    //Close both the dialog by clicking on its close icon
    WebElement closeIcon =
      getElement("{\"element\":\"#" + DIALOG_WITH_TABLE_ID + "\",\"subId\":\"oj-dialog-close-icon\"}");
    closeIcon.click();
    closeIcon = getElement("{\"element\":\"#" + DIALOG_WITH_MENU_ID + "\",\"subId\":\"oj-dialog-close-icon\"}");
    closeIcon.click();

    //assert that dialog is NOT open
    rtn = evalJavascript("return $('#" + DIALOG_WITH_TABLE_ID + "').ojDialog('isOpen')");
    Assert.assertEquals(rtn, "false");
    rtn = evalJavascript("return $('#" + DIALOG_WITH_MENU_ID + "').ojDialog('isOpen')");
    Assert.assertEquals(rtn, "false");
    // close popup by clicking on toggle button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);


  }

  @Test(groups =
    {
      "popup"
    })
  public void testInvoke2LevelsOfDialog_AutoDismissFocusLoss()
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

    //expand the collapsible that contains the button to invoke dialog
    WebElement headerIcon =
      getElement("{\"element\":\"#" + FIRST_IN_POPUP_COLLAPSIBLE_ID + "\",\"subId\":\"oj-collapsible-header-icon\"}");
    headerIcon.click();
    waitForMilliseconds(1000);


    //Click on the button to invoke the first level dialog.
    click("id=" + INVOKE_DIALOG_WITH_MENU_BUTTON);
    // on button inside the dialog to invoke another dialog
    click("id=" + INVOKE_SECOND_LEVEL_DIALOG_BUTTON);
    //assert that both the dialogs are open

    String rtn = evalJavascript("return $('#" + DIALOG_WITH_MENU_ID + "').ojDialog('isOpen')");
    Assert.assertEquals(rtn, "true");
    rtn = evalJavascript("return $('#" + DIALOG_WITH_TABLE_ID + "').ojDialog('isOpen')");
    Assert.assertEquals(rtn, "true");

    //Close both the dialog by clicking on its close icon
    WebElement closeIcon =
      getElement("{\"element\":\"#" + DIALOG_WITH_TABLE_ID + "\",\"subId\":\"oj-dialog-close-icon\"}");
    closeIcon.click();
    closeIcon = getElement("{\"element\":\"#" + DIALOG_WITH_MENU_ID + "\",\"subId\":\"oj-dialog-close-icon\"}");
    closeIcon.click();

    //assert that dialog is NOT open
    rtn = evalJavascript("return $('#" + DIALOG_WITH_TABLE_ID + "').ojDialog('isOpen')");
    Assert.assertEquals(rtn, "false");
    rtn = evalJavascript("return $('#" + DIALOG_WITH_MENU_ID + "').ojDialog('isOpen')");
    Assert.assertEquals(rtn, "false");

    //assert that popup is not open either
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "false");
  }
}
