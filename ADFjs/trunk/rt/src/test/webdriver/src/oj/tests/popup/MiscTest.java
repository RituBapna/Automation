package oj.tests.popup;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MiscTest
  extends OJetBase
{

  public MiscTest()
  {
    super(new TestConfigBuilder().setContextRoot("popup/popupTest").setAppRoot("ojpopup").build());
  }

  @Test(groups =
    {
      "popup"
    })
  public void testInputValidationAutoDismissFocusLoss()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
    maximizeWindow();

    // This test verify that auto dismissal of a popup will allow the input
    // components to first validate.  The input compoents must recieve a blur
    // event.

    //wait for the button to be displayed
    waitForElementVisible("btnGo");

    //click on the button to launch the popup
    click("id=btnGo");

    //wait for the popup to display
    waitForElementVisible("inputnumber-id");

    // type a valid number in the input number
    doType("id=inputnumber-id", "55", true);

    //click on a div that will take focus to dismiss the popup
    click("id=focusArea");

    //wait for the popup to hide
    waitForMilliseconds(100);
    
    //verify the input component validated and pushed the value into
    //the observable.

    String currentValue = getElement("curr-value").getText();
    Assert.assertEquals("55", currentValue);
  };

  @Test(groups =
    {
      "popup"
    })
  public void testPreventBeforeCloseAutoDismissFocusLoss()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
    maximizeWindow();

    //This test verifies that a focus loss popup dismissal can be prevented
    //It also verifes that the sequence of events will allow the input compoents
    //to validate (blur event fired) prior to firing the beforeclose event.
    //The beforeclose event listener in this test will prevent the popup from closing
    //if the input component is not valid.

    //wait for the button to be displayed
    waitForElementVisible("btnGo");

    //click on the button to launch the popup
    click("id=btnGo");

    //wait for the popup to display
    waitForElementVisible("inputnumber-id");

    // type an invalid valid into the input number
    doType("id=inputnumber-id", "5555", true);

    //click on a div that will take focus to dismiss the popup
    click("id=focusArea");

    // Let the browser fire the sequence of events
    waitForMilliseconds(100);

    //verify the popup is still open
    String isPopupOpen = evalJavascript("return window.$('#myPopup').ojPopup('isOpen');");
    Assert.assertEquals("true", isPopupOpen);

    //verify the input component is not valid
    String isValid = evalJavascript("return window.$('#inputnumber-id').ojInputNumber('isValid');");
    Assert.assertEquals("false", isValid);
  }

  @Test(groups =
    {
      "popup"
    })
  public void testInputValidationOnForwardTabPressForSingleTabstop()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
    maximizeWindow();

    // focus of the test is Tab from the input number in the popup

    //Tabbing in a popup will cycle within the popup preventing from tabing out.
    //If there is an input component that is a single tabstop in the popup then it is
    //the first and last top stop.  Tabbing in this case should result in the outer chome
    //of the popup being focused so that the blur and validation will occur on input
    //components.

    //wait for the button to be displayed
    waitForElementVisible("btnGo");

    //click on the button to launch the popup
    click("id=btnGo");

    //wait for the popup to display
    waitForElementVisible("inputnumber-id");

    // establish focus in the input number
    click("id=inputnumber-id");
    
    // type an invalid valid into the input number
    doType("id=inputnumber-id", "5555", true);
    
    // tab out of the input element
    getElement("id=inputnumber-id").sendKeys(Keys.TAB);

    // Let the browser fire the sequence of events
    waitForMilliseconds(100);

    //verify the popup is still open
    String isPopupOpen = evalJavascript("return window.$('#myPopup').ojPopup('isOpen');");
    Assert.assertEquals("true", isPopupOpen);

    //verify the input component is not valid
    String isValid = evalJavascript("return window.$('#inputnumber-id').ojInputNumber('isValid');");
    Assert.assertEquals("false", isValid);
        
  }

  @Test(groups =
    {
      "popup"
    })
  public void testInputValidationOnBackwardTabPressForSingleTabstop()
    throws Exception
  {
    startupTest(TEST_PAGE, null);
    verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
    maximizeWindow();
    
    // focus of the test is shift+Tab from the input number in the popup

    //Tabbing in a popup will cycle within the popup preventing from tabing out.
    //If there is an input component that is a single tabstop in the popup then it is
    //the first and last top stop.  Tabbing in this case should result in the outer chome
    //of the popup being focused so that the blur and validation will occur on input
    //components.

    //wait for the button to be displayed
    waitForElementVisible("btnGo");

    //click on the button to launch the popup
    click("id=btnGo");

    //wait for the popup to display
    waitForElementVisible("inputnumber-id");

    // establish focus in the input number
    click("id=inputnumber-id");
    
    // type an invalid valid into the input number
    doType("id=inputnumber-id", "5555", true);
    
    // tab out of the input element
    getElement("id=inputnumber-id").sendKeys(Keys.SHIFT, Keys.TAB);

    // Let the browser fire the sequence of events
    waitForMilliseconds(100);

    //verify the popup is still open
    String isPopupOpen = evalJavascript("return window.$('#myPopup').ojPopup('isOpen');");
    Assert.assertEquals("true", isPopupOpen);

    //verify the input component is not valid
    String isValid = evalJavascript("return window.$('#inputnumber-id').ojInputNumber('isValid');");
    Assert.assertEquals("false", isValid);
        
  }

  private static final String TEST_PAGE = "testOjPopupMisc.html";
  private static final String TEST_PAGE_TITLE = "ojPopup Miscellaneous Test";

}
