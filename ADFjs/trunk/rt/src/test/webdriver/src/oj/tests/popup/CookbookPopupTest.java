package oj.tests.popup;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class CookbookPopupTest
  extends OJetBase
{
  public CookbookPopupTest()
  {
    super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
  }

  @Test(groups =
    {
      "cookbook"
    })
  public void popupAutoDismissal()
    throws Exception
  {
    startupTest("demo-popup-popup.html", null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", "Popup - Popup");

    // bug 19555535  - OJINPUTDATE GOBBLES UP CLICK EVENT IN FIREFOX
    // 1) Establish focus on the "Go" button.
    // 2) opens the popup by clicking on the "Go" button."
    // 3) it verifyes he popup is open.
    // 4) click on the search input.
    // 5) verify the popup auto dismisses
    // 6) verify that focus is within the search input
    //
    //This usecase can not be automated via qunit because qunit will only raise events
    //that are explicitly simulated.  The conditions that caused this error results from
    //the asynchronous sequence of events raised by the browser when clicking on an
    //input element triggering auto dismissal of the popup.

    // establish focus to the Go Button
    evalJavascript("$('#btnGo').focus();");

    // Click Go Button
    click("id=btnGo");

    // verify popup is open
    String rtn = evalJavascript("return $('#popup1').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");

    // click the search button
    Actions actions = new Actions(getWebDriver());
    WebElement searchElement = getElement("{\"element\":\"#searchQuery\",\"subId\":\"oj-inputsearch-input\"}");

    actions.moveToElement(searchElement).click(searchElement).perform();
    waitForMilliseconds(55);

    // verify popup is auto closed dismissed
    rtn = evalJavascript("return $('#popup1').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "false");

    // verify that auto dismissal doesn't override establishing focus to the
    // input element that was clicked.
    rtn = evalJavascript("return document.activeElement.id");
    Assert.assertEquals(rtn, searchElement.getAttribute("id"));
  }

  @Test(groups =
    {
      "cookbook"
    })
  public void popupCrossFiledValidation()
    throws Exception
  {
    startupTest("demo-appLevelValidation-crossFieldValidation.html", null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", "App Level Validation - Cross-Field Validation");

    //bug 19432704 - IE9: CUSTOMMESSAGE IS NOT DISPLAYED WHEN FOCUSONFIRSTINVALID IS CALLED IN 1 CASE
    //1) Establish focus on the create button.
    //2) Click on the create button
    //3) Verify the notewindow associated with the email is visible
    //
    //This usecase can not be automated via qunit because qunit will only raise events
    //that are explicitly simulated.  The conditions that caused this error results from
    //the asynchronous sequence of events raised by the browser when clicking on the button.

    // turn on notewindow message strategy for these components. default is now inline.
    evalJavascript("$('#emailId').ojInputText('option', 'displayOptions.messages', 'notewindow');");
    evalJavascript("$('#telNum').ojInputText('option', 'displayOptions.messages', 'notewindow');");

    // establish focus to the create button
    evalJavascript("$('#crossfield-example').find('button').focus();");
    waitForMilliseconds(55);

    // Click Create Button
    click("//div[@id='crossfield-example']/div/div/button");
    waitForMilliseconds(500);

    // find the associated notewindow
    String describedby = evalJavascript("return $('#emailId').attr('aria-describedby');");
    Assert.assertEquals(describedby.length() > 0, true);

    String rtn =
      evalJavascript("return $('#" + describedby + "').find('.oj-messaging-popup-container').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");
  }

  private final static class ThemeTestOption
  {
    public ThemeTestOption(String themeRadioId, String expectedModalityValue)
    {
      this.themeRadioId = themeRadioId;
      this.expectedModalityValue = expectedModalityValue;
    }
    String themeRadioId = null;
    String expectedModalityValue = null;
  };

  private static final ThemeTestOption[] _KNOWN_THEME_DEFAULT_MODALITY = new ThemeTestOption[]
  {
    new ThemeTestOption("platform_ios", "modal"), new ThemeTestOption("platform_web", "modeless"),
    new ThemeTestOption("platform_android", "modal"), new ThemeTestOption("platform_web", "modeless"),
    new ThemeTestOption("platform_windows", "modal"), new ThemeTestOption("platform_web", "modeless")
  };

  @Test(groups =
    {
      "cookbook"
    })
  public void popupThemeDefaultModalityOption()
    throws Exception
  {
    // this test verifies that the modality popup option defaults correctly
    // per mobile theme.

    startupTest("demo-popup-popup.html", null);
    maximizeWindow();
    verifyTitle("Incorrect page title;", "Popup - Popup");

    for (ThemeTestOption testOption: _KNOWN_THEME_DEFAULT_MODALITY)
    {
      click("id=buttonImage");
      waitForElementVisible("id=" + testOption.themeRadioId);

      WebElement radioElement = getElement("id=" + testOption.themeRadioId);
      if (isFirefox())
        radioElement.click();
      else
        radioElement.findElement(By.xpath("parent::*")).click();

      waitForMilliseconds(1000);

      String modality = evalJavascript("return $('#popup1').ojPopup('option', 'modality')");
      verifyEquals("default modality option", testOption.expectedModalityValue, modality);

      click("id=btnGo");

      String rtn = evalJavascript("return $('#popup1').ojPopup('isOpen')");
      Assert.assertEquals(rtn, "true");

      rtn = evalJavascript("return $('#__oj_zorder_container').find('.oj-component-overlay').length");
      Assert.assertEquals("modal".equals(testOption.expectedModalityValue)? "1": "0", rtn);

      Actions actions = new Actions(getWebDriver());
      actions.sendKeys(Keys.ESCAPE).perform();
      waitForMilliseconds(1000);

      rtn = evalJavascript("return $('#popup1').ojPopup('isOpen')");
      Assert.assertEquals(rtn, "false");

    }
    ;
  };

}
