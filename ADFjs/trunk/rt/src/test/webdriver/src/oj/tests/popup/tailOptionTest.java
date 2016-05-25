package oj.tests.popup;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class tailOptionTest
  extends OJetBase
{
  private static final String TEST_PAGE = "testOjPopup.html";
  private static final String TEST_PAGE_TITLE = "ojPopup Test";
  private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
  private static final String POPUP_DIV = "popupdiv";


  public tailOptionTest()
  {
    super(new TestConfigBuilder().setContextRoot("popup/popupTest").setAppRoot("ojpopup").build());
  }

  @Test(groups =
    {
      "popup"
    })
  public void testPopupWithNoTail()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

    //wait for the button to be displayed
    waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
    //verify that tail value is set to "none"
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'tail')");
    Assert.assertEquals(rtn, "none");

    //open the popup by clicking on the button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    //Assert that popup is open
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //Get the popup element and go to its gand parent to test that it has no tail
    WebElement grandParentElem = getParentElement(getParentElement(getElement("id=" + POPUP_DIV)));
    String classes = grandParentElem.getAttribute("class");
    //verify that popup doe snot have oj-popup-tail-simple
    boolean hasSimpleTailClass = classes.indexOf("oj-popup-tail-simple") > -1;
    Assert.assertFalse(hasSimpleTailClass);
  }

  @Test(groups =
    {
      "popup"
    })
  public void testPopupWithSimpleTail()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
    //wait for the button to be displayed
    waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);

    evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'tail', 'simple')");
    //verify that tail value is set to "simple"
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'tail')");
    Assert.assertEquals(rtn, "simple");

    //open the popup by clicking on the button
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    //Assert that popup is open
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    //Get the popup element and go to its gand parent to test that it has no tail
    WebElement grandParentElem = getParentElement(getParentElement(getElement("id=" + POPUP_DIV)));
    String classes = grandParentElem.getAttribute("class");
    //verify that popup doe snot have oj-popup-tail-simple
    boolean hasSimpleTailClass = classes.indexOf("oj-popup-tail-simple") > -1;
    Assert.assertTrue(hasSimpleTailClass);

  }
}
