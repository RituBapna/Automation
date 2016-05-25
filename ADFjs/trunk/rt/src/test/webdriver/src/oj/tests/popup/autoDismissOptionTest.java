package oj.tests.popup;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class autoDismissOptionTest
  extends OJetBase
{
  private static final String TEST_PAGE = "testOjPopup.html";
  private static final String TEST_PAGE_TITLE = "ojPopup Test";
  private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
  private static final String POPUP_DIV = "popupdiv";
  private static final String COMPONENT_OUTSIDE_POPUP = "optionsHeading";


  public autoDismissOptionTest()
  {
    super(new TestConfigBuilder().setContextRoot("popup/popupTest").setAppRoot("ojpopup").build());
  }

  @Test(groups =
    {
      "popup"
    })
  public void testPopupClosesOnFocusLoss()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

    //wait for the button to be displayed
    waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
    //verify that autoDismiss value is set to "focusLoss"
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss')");
    Assert.assertEquals(rtn, "focusLoss");

    //open the popup by clicking on the button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    //Assert that popup is open
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //Click outside the popup
    click("id=" + COMPONENT_OUTSIDE_POPUP);
    //verify that popup is not open
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "false");
  }

  @Test(groups =
    {
      "popup"
    })
  public void testAutoDismissNoneValue()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
    //wait for the button to be displayed
    waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
    //set autoDismiss value to none
    /*
        WebElement selectChosen = getElement("{\"element\":\"#ad\",\"subId\":\"oj-select-chosen\"}");

        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(selectChosen).click().perform();
        //select the second item in drop down list
        WebElement selectResults = getElement("{\"element\":\"#ad\",\"subId\":\"oj-select-results\"}");
*/
    evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'none')");
    //verify that autoDismiss value is set to "none"
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss')");
    Assert.assertEquals(rtn, "none");

    //open the popup by clicking on the button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    //Assert that popup is open
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //Click outside the popup
    click("id=" + COMPONENT_OUTSIDE_POPUP);
    //verify that popup is still  open
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //click on the toggle button and verify that popup closes
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "false");

  }
}
