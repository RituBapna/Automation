package oj.tests.popup;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class chromeOptionTest
  extends OJetBase
{
  private static final String TEST_PAGE = "testOjPopup.html";
  private static final String TEST_PAGE_TITLE = "ojPopup Test";
  private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
  private static final String POPUP_DIV = "popupdiv";
  private static final String COMPONENT_OUTSIDE_POPUP = "optionHeading";

  public chromeOptionTest()
  {
    super(new TestConfigBuilder().setContextRoot("popup/popupTest").setAppRoot("ojpopup").build());
  }

  @Test(groups =
    {
      "popup"
    })
  public void testChromeNoneValue()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

    //wait for the button to be displayed
    waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);

    //set chrome option to none
    evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'chrome', 'none')");
    //verify that chrome value is set to "none"
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'chrome')");
    Assert.assertEquals(rtn, "none");


    //open the popup by clicking on the button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    //Assert that popup is open
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //verify that popup has oj-opup-no-chrome class added to it
    WebElement grandParent = getParentElement(getParentElement(this.getElement("id=" + POPUP_DIV)));
    boolean hasNoChromeClass = grandParent.getAttribute("class").indexOf("oj-popup-no-chrome") > 0;
    Assert.assertTrue(hasNoChromeClass);
  }

  @Test(groups =
    {
      "popup"
    })
  public void testChromeDefaultValue()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

    //wait for the button to be displayed
    waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);

    //set chrome option to none
    evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'chrome', 'default')");
    //verify that chrome value is set to "none"
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'chrome')");
    Assert.assertEquals(rtn, "default");


    //open the popup by clicking on the button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    //Assert that popup is open
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //verify that popup DOES NOT have oj-opup-no-chrome class added to it
    WebElement grandParent = getParentElement(getParentElement(this.getElement("id=" + POPUP_DIV)));
    boolean hasNoChromeClass = grandParent.getAttribute("class").indexOf("oj-popup-no-chrome") > 0;
    Assert.assertFalse(hasNoChromeClass);
  }

}
