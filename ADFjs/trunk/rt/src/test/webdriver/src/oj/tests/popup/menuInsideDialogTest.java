package oj.tests.popup;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class menuInsideDialogTest
  extends OJetBase
{
  private static final String TEST_PAGE = "testOjPopup.html";
  private static final String TEST_PAGE_TITLE = "ojPopup Test";
  private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
  private static final String POPUP_DIV = "popupdiv";
  private static final String FIRST_IN_POPUP_COLLAPSIBLE_ID = "collapsiblePage1";
  private static final String INVOKE_DIALOG_WITH_MENU_BUTTON = "invokeDialogWithMenuBtn";
  private static final String DIALOG_WITH_MENU_ID = "dialogWithUserDefinedHeader";
  private static final String MENU_BUTTON_INSIDE_DIALOG = "dialogbtn1";
  private static final String MENU_ID = "myMenu";

  public menuInsideDialogTest()
  {
    super(new TestConfigBuilder().setContextRoot("popup/popupTest").setAppRoot("ojpopup").build());
  }

  @Test(groups =
    {
      "popup"
    })
  public void testInvokeMenuInsideDialog_AutoDismissNone()
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
    // on button to invoke button menu
    click("id=" + MENU_BUTTON_INSIDE_DIALOG);

    //Assert that menu is open
    WebElement menu = getElement("id=" + MENU_ID);

    boolean isNotDisplayed = (menu.getAttribute("style").indexOf("display: none;") > -1);
    Assert.assertFalse(isNotDisplayed);

    //Move mouse over Zoom menu item
    WebElement zoom = getElement("id=zoom");
    zoom.click();
    waitForMilliseconds(1000);

    //Verify that zoomin sub menu is visible
    //evalJavascript("return $('#zoomin').trigger('click');");
    Actions actions = new Actions(getWebDriver());
    WebElement zoomin = getElement("id=zoomin");
    actions.moveToElement(zoomin).click().perform();
    waitForMilliseconds(1000);
    
    //check that oomin was selected
    WebElement selectedMenuItem = getElement("id=results_d");
    Assert.assertEquals(selectedMenuItem.getText(), "Zoom In");

    //Assert that menu is closed

    isNotDisplayed = (menu.getAttribute("style").indexOf("display: none;") > -1);
    Assert.assertTrue(isNotDisplayed);

    //Close the dialog
    WebElement closeIcon =
      getElement("{\"element\":\"#" + DIALOG_WITH_MENU_ID + "\",\"subId\":\"oj-dialog-close-icon\"}");
    closeIcon.click();
    //close trhe popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
  }

  @Test(groups =
    {
      "popup"
    })
  public void testInvokeMenuInsideDialog_AutoDismissFocusLoss()
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
    // on button to invoke button menu
    click("id=" + MENU_BUTTON_INSIDE_DIALOG);

    //Assert that menu is open
    WebElement menu = getElement("id=" + MENU_ID);

    boolean isNotDisplayed = (menu.getAttribute("style").indexOf("display: none;") > -1);
    Assert.assertFalse(isNotDisplayed);

    //Move mouse over Zoom menu item
    WebElement zoom = getElement("id=zoom");
    zoom.click();
    
    //Verify that zoomin sub menu is visible
    //evalJavascript("return $('#zoomin').trigger('click');");
    //Move mouse over Zoom menu item
    Actions actions = new Actions(getWebDriver());
    WebElement zoomin = getElement("id=zoomin");
    actions.moveToElement(zoomin).click().perform();
    waitForMilliseconds(1000);
    
    
    //check that oomin was selected
    WebElement selectedMenuItem = getElement("id=results_d");
    Assert.assertEquals(selectedMenuItem.getText(), "Zoom In");

    //Assert that menu is closed

    isNotDisplayed = (menu.getAttribute("style").indexOf("display: none;") > -1);
    Assert.assertTrue(isNotDisplayed);

    //Close the dialog
    WebElement closeIcon =
      getElement("{\"element\":\"#" + DIALOG_WITH_MENU_ID + "\",\"subId\":\"oj-dialog-close-icon\"}");
    closeIcon.click();

  }

}
