package oj.tests.popup;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class comboboxTest
  extends OJetBase
{
  private static final String TEST_PAGE = "testOjPopup.html";
  private static final String TEST_PAGE_TITLE = "ojPopup Test";
  private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
  private static final String POPUP_DIV = "popupdiv";
  private static final String INPUTDATE_ID = "date10";
  private static final String COMBOBOX_ID = "combobox";
  private static final String ACCORDION_ID = "accordionPage";
  private static final String MENU_BUTTON_INSIDE_DIALOG = "dialogbtn1";
  private static final String MENU_ID = "myMenu";

  public comboboxTest()
  {

    super(new TestConfigBuilder().setContextRoot("popup/popupTest").setAppRoot("ojpopup").build());
  }

  @Test(groups =
    {
      "popup"
    })
  public void testInvokeCombobox_AutoDismissNone()
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
    //expand collapsible that contains select cmponent
    //Scroll down
    //JavascriptExecutor jse = (JavascriptExecutor)getWebDriver();
    //jse.executeScript("window.scrollBy(0,250)", "");
    evalJavascript("window.scrollBy(0,250)", "");
    this.waitForMilliseconds(1000);
    WebElement headerIcon =
      getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":2}");
    System.out.println("**** headerIcon: " + headerIcon.getAttribute("class"));
    System.out.println("**** headerIcon: " + headerIcon.getAttribute("aria-labelledby"));
    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(headerIcon).click().perform();
    //headerIcon.click();
    evalJavascript("window.scrollBy(0,1000)", "");
    this.waitForMilliseconds(1000);
    //open the seelct drop box by clicking on the select component
    WebElement selectInput = getElement("{\"element\":\"#" + COMBOBOX_ID + "\",\"subId\":\"oj-combobox-input\"}");

    actions.moveToElement(selectInput).click().perform();


    //   evalJavascript("window.scrollBy(0,900)", "");
    this.waitForMilliseconds(1000);
    //Verify that drop list is displayed
    WebElement results = getElement("{\"element\":\"#" + COMBOBOX_ID + "\",\"subId\":\"oj-combobox-results\"}");
    Assert.assertNotNull(results);
    List<WebElement> items = results.findElements(By.tagName("li"));
    //verify that list has items
    System.out.println("*** Item COUNT: " + items.size());

    Assert.assertTrue(items.size() == 8);

    //verify that popup is open
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");
    //click on different element and verify that select list box closes
    //TODO: clicking on different field does not closes the drop down list.
    /*
        WebElement inputField =
            getElement("{\"element\":\"#" + INPUTDATE_ID + "\",\"subId\":\"oj-inputdatetime-date-input\"}");

        actions.moveToElement(inputField).click().perform();
*/
    actions.moveToElement(selectInput).click().perform();
    //verify that select list box closed
    boolean dropdownPresent =
      this.isElementPresent("{\"element\":\"#" + COMBOBOX_ID + "\",\"subId\":\"oj-combobox-results\"}");

    Assert.assertFalse(dropdownPresent);
    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "false");

  }
}
