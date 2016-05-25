package oj.tests.popup;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class initialFocusOptionTest
  extends OJetBase
{
  private static final String TEST_PAGE = "testOjPopup.html";
  private static final String TEST_PAGE_TITLE = "ojPopup Test";
  private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
  private static final String POPUP_DIV = "popupdiv";
  private static final String FIRST_IN_POPUP_COLLAPSIBLE_ID = "collapsiblePage1";
  private static final String NUMBEROFMONTHS_BUTTON = "numberOfMonths";

  public initialFocusOptionTest()
  {
    super(new TestConfigBuilder().setContextRoot("popup/popupTest").setAppRoot("ojpopup").build());
  }

  @Test(groups =
    {
      "popup"
    })
  public void testInitialFocusNoneValue()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

    //wait for the button to be displayed
    waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);

    //set chrome option to none
    evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'initialFocus', 'none')");
    //verify that chrome value is set to "none"
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'initialFocus')");
    Assert.assertEquals(rtn, "none");


    //open the popup by clicking on the button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    //Assert that popup is open
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //verify that popup has oj-opup-no-chrome class added to it
    WebElement openPopupButton = getElement("id=" + BUTTON_TOGGLE_POPUP_DIV);
    boolean hasFocusClass = openPopupButton.getAttribute("class").indexOf("oj-focus") > 0;
    Assert.assertTrue(hasFocusClass);
  }

  @Test(groups =
    {
      "popup"
    })
  public void testInitialFocusFirstFocusableValue()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

    //wait for the button to be displayed
    waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);

    //set chrome option to none
    evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'initialFocus', 'firstFocusable')");
    //verify that chrome value is set to "none"
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'initialFocus')");
    Assert.assertEquals(rtn, "firstFocusable");

    //open the popup by clicking on the button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    //Assert that popup is open
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //tabb twice so that the focus is on "update NumberOfMonths" button.
    rtn = evalJavascript("return document.activeElement.classList.contains(\"oj-collapsible-header-icon\")");
    String rtn2 = evalJavascript("return document.activeElement.classList");
    //verify that header icon for the the first collapsible in popup has the focus
    System.out.println("@@@@RETURN : : " + rtn);
    System.out.println("@@@@RETURN : : " + rtn2);

    Assert.assertEquals(rtn, "true");
  }

  @Test(groups =
    {
      "popup"
    })
  public void testInitialFocusPopupValue()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

    //wait for the button to be displayed
    waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);

    //set chrome option to none
    evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'initialFocus', 'popup')");
    //verify that chrome value is set to "none"
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'initialFocus')");
    Assert.assertEquals(rtn, "popup");

    //open the popup by clicking on the button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    //Assert that popup is open
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //tabb twice so that the focus is on "update NumberOfMonths" button.
    String activeId = evalJavascript("return document.activeElement.id");
    String popupId = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('widget').attr('id')");

    Assert.assertEquals(activeId, popupId);
  }
}
