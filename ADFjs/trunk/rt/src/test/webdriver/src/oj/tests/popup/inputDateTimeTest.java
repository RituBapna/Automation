package oj.tests.popup;

import java.util.Calendar;
import java.util.Date;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class inputDateTimeTest
  extends OJetBase
{
  private static final String TEST_PAGE = "testOjPopup.html";
  private static final String TEST_PAGE_TITLE = "ojPopup Test";
  private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
  private static final String POPUP_DIV = "popupdiv";
  private static final String INPUTDATE_ID = "date10";
  private static final String INPUTDATETIME_ID = "date11";


  public inputDateTimeTest()
  {
    super(new TestConfigBuilder().setContextRoot("popup/popupTest").setAppRoot("ojpopup").build());
  }

  @Test(groups =
    {
      "popup"
    })
  public void testInvokeDatepicker_AutoDismissNone()
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
    WebElement dateIcon =
      getElement("{\"element\":\"#" + INPUTDATETIME_ID + "\",\"subId\":\"oj-inputdatetime-calendar-icon\"}");
    dateIcon.click();
    //Verify that calendar content is displayed
    WebElement calendar = getElement("{\"element\":\"#" + INPUTDATETIME_ID + "\",\"subId\":\"oj-datepicker-content\"}");
    //get the grand parent because the "display" style is set on it
    WebElement grandParentElem = getParentElement(getParentElement(calendar));
    String style = grandParentElem.getAttribute("style");

    //verify that calendar is displayed
    boolean notDisplayed = style.indexOf("display: none;") > -1;
    Assert.assertFalse(notDisplayed);

    //verify that popup is open too
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");
    //click on different element and verify that calendar closes
    WebElement inputField =
      getElement("{\"element\":\"#" + INPUTDATE_ID + "\",\"subId\":\"oj-inputdatetime-date-input\"}");

    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(inputField).click().perform();
    style = grandParentElem.getAttribute("style");
    //verify that calendar is not displayed any more
    boolean displayed = style.indexOf("display: block;") > -1;
    Assert.assertFalse(displayed);
    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "false");
  }

  @Test(groups =
    {
      "popup"
    })
  public void testInvokeDatepicker_AutoDismissFocusLoss()
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
    WebElement dateIcon =
      getElement("{\"element\":\"#" + INPUTDATETIME_ID + "\",\"subId\":\"oj-inputdatetime-calendar-icon\"}");
    dateIcon.click();
    //Verify that calendar content is displayed
    WebElement calendar = getElement("{\"element\":\"#" + INPUTDATETIME_ID + "\",\"subId\":\"oj-datepicker-content\"}");
    //get the grand parent because the "display" style is set on it
    WebElement grandParentElem = getParentElement(getParentElement(calendar));
    String style = grandParentElem.getAttribute("style");

    //verify that calendar is displayed
    boolean notDisplayed = style.indexOf("display: none;") > -1;
    Assert.assertFalse(notDisplayed);

    //verify that popup is open too
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");
    //click on different element and verify that calendar closes
    WebElement inputField =
      getElement("{\"element\":\"#" + INPUTDATE_ID + "\",\"subId\":\"oj-inputdatetime-date-input\"}");

    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(inputField).click().perform();
    style = grandParentElem.getAttribute("style");
    //verify that calendar is not displayed any more
    boolean displayed = style.indexOf("display: block;") > -1;
    Assert.assertFalse(displayed);
    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "false");
  }

  @Test(groups =
    {
      "popup"
    })
  public void testInvokeTimepicker_AutoDismissNone()
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
    WebElement timeIcon =
      getElement("{\"element\":\"#" + INPUTDATETIME_ID + "\",\"subId\":\"oj-inputdatetime-time-icon\"}");
    timeIcon.click();
    //Verify that calendar content is displayed
    WebElement timeList = getElement("{\"element\":\"#" + INPUTDATETIME_ID + "\",\"subId\":\"oj-listbox-drop\"}");
    //get the grand parent because the "display" style is set on it
    //  WebElement grandParentElem = getParentElement(getParentElement(calendar));
    String style = timeList.getAttribute("style");

    //verify that calendar is displayed
    boolean notDisplayed = style.indexOf("display: none;") > -1;
    Assert.assertFalse(notDisplayed);

    //verify that popup is open too
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");
    //click on different element and verify that calendar closes
    WebElement inputField =
      getElement("{\"element\":\"#" + INPUTDATE_ID + "\",\"subId\":\"oj-inputdatetime-date-input\"}");

    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(inputField).click().perform();
    style = timeList.getAttribute("style");
    //verify that calendar is not displayed any more
    boolean displayed = style.indexOf("display: block;") > -1;
    Assert.assertFalse(displayed);
    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "false");
  }

  @Test(groups =
    {
      "popup"
    })
  public void testInvokeTimepicker_AutoDismissFocusLoss()
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
    WebElement timeIcon =
      getElement("{\"element\":\"#" + INPUTDATETIME_ID + "\",\"subId\":\"oj-inputdatetime-time-icon\"}");
    timeIcon.click();
    //Verify that time list is displayed
    WebElement timeList = getElement("{\"element\":\"#" + INPUTDATETIME_ID + "\",\"subId\":\"oj-listbox-drop\"}");

    String style = timeList.getAttribute("style");
    boolean notDisplayed = style.indexOf("display: none;") > -1;
    Assert.assertFalse(notDisplayed);

    //verify that popup is open too
    String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "true");
    //click on different element and verify that time list closes
    WebElement inputField =
      getElement("{\"element\":\"#" + INPUTDATE_ID + "\",\"subId\":\"oj-inputdatetime-date-input\"}");

    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(inputField).click().perform();
    style = timeList.getAttribute("style");
    //verify that time list is not displayed any more
    boolean displayed = style.indexOf("display: block;") > -1;
    Assert.assertFalse(displayed);
    //close the popup
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
    rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
    Assert.assertEquals(rtn, "false");
  }

  // GV disabled test due to failues.
  //@Test(groups = { "popup" })
  public void testSetValueUsingDatepicker_AutoDismissNone()
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

    WebElement dateIcon =
      getElement("{\"element\":\"#" + INPUTDATETIME_ID + "\",\"subId\":\"oj-inputdatetime-calendar-icon\"}");
    dateIcon.click();
    //get calendar content
    WebElement calendar = getElement("{\"element\":\"#" + INPUTDATETIME_ID + "\",\"subId\":\"oj-datepicker-content\"}");
    //get current month and year
    Calendar now = Calendar.getInstance(); // Gets the current date and time
    int year = now.get(Calendar.YEAR); // The current year as an int
    int month = now.get(Calendar.MONTH) + 1; // The current month as an int
    String yr_str = String.valueOf(year);
    String dateToCompare = "0" + String.valueOf(month) + "/01/" + yr_str.substring(2) + " 12:00 AM";

    //Click on first of the current month on calendar to set the date
    waitForMilliseconds(3000);
    WebElement firstOfCurrentMonth = calendar.findElement(By.xpath("//table/tbody/tr[1]/td[1]/a"));

    //firstOfCurrentMonth.click();
    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(firstOfCurrentMonth).click().perform();

    WebElement inputField =
      getElement("{\"element\":\"#" + INPUTDATETIME_ID + "\",\"subId\":\"oj-inputdatetime-date-input\"}");

    Assert.assertEquals(dateToCompare, inputField.getAttribute("value"));
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
  }

  @Test(groups =
    {
      "popup"
    })
  public void testSetValueUsingTimepicker_AutoDismissNone()
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

    WebElement timeIcon =
      getElement("{\"element\":\"#" + INPUTDATETIME_ID + "\",\"subId\":\"oj-inputdatetime-time-icon\"}");
    timeIcon.click();
    //get time list content
    WebElement timeList = getElement("{\"element\":\"#" + INPUTDATETIME_ID + "\",\"subId\":\"oj-listbox-drop\"}");

    //Click on the fifth tem in the time list
    WebElement fifthElem = timeList.findElement(By.xpath("//li[contains(@class, 'oj-listbox-result')][5]"));
    Actions actions = new Actions(getWebDriver());
    actions.moveToElement(fifthElem).click().perform();
    Calendar now = Calendar.getInstance(); // Gets the current date and time
    int year = now.get(Calendar.YEAR); // The current year as an int
    int month = now.get(Calendar.MONTH) + 1; // The current month as an int
    int date = now.get(Calendar.DATE);
    String date_str = String.valueOf(date);
    String convertedDate = (date_str.length() > 1)? date_str: "0" + date_str;
    String yr_str = String.valueOf(year);
    String dateToCompare = String.format("%02d", month) + "/" + convertedDate + "/" + yr_str.substring(2) + " 02:00 AM";

    WebElement inputField =
      getElement("{\"element\":\"#" + INPUTDATETIME_ID + "\",\"subId\":\"oj-inputdatetime-date-input\"}");
    System.out.println("$$$$$ Value: " + inputField.getAttribute("value"));
    Assert.assertEquals(inputField.getAttribute("value"), dateToCompare);
    click("id=" + BUTTON_TOGGLE_POPUP_DIV);
  }
}

