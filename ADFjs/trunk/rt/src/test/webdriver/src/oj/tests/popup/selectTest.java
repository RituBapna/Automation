package oj.tests.popup;

import oracle.ojet.automation.test.OJetBase;

import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class selectTest extends OJetBase {
    private static final String TEST_PAGE = "testOjPopup.html";
    private static final String TEST_PAGE_TITLE = "ojPopup Test";
    private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
    private static final String POPUP_DIV = "popupdiv";
    private static final String INPUTDATE_ID = "date10";
    private static final String SELECT_ID = "groupSelect";
    private static final String ACCORDION_ID = "accordionPage";
    private static final String MENU_BUTTON_INSIDE_DIALOG = "dialogbtn1";
    private static final String MENU_ID = "myMenu";

    public selectTest() {
        super(new TestConfigBuilder().setContextRoot("popup/popupTest").setAppRoot("ojpopup").build());
    }

    @Test(groups = { "popup" })
    public void testInvokeSelect_AutoDismissNone() throws Exception {
        startupTest(TEST_PAGE, null);
        maximizeWindow();
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the button to be displayed
        waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
        //set  autoDismiss  to "none"
        evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'none')");
        //open the popup by clicking on the button
        click("id=" + BUTTON_TOGGLE_POPUP_DIV);
        //expmad collapsible that contains select cmponent
        //Scroll down
        //JavascriptExecutor jse = (JavascriptExecutor)getWebDriver();
        //jse.executeScript("window.scrollBy(0,250)", "");
        evalJavascript("window.scrollBy(0,500)", "");
        this.waitForMilliseconds(1000);
        WebElement headerIcon =
            getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":2}");
        System.out.println("**** headerIcon: " + headerIcon.getAttribute("class"));
        System.out.println("**** headerIcon: " + headerIcon.getAttribute("aria-labelledby"));

        //  actions.moveToElement(headerIcon).click().perform();
        headerIcon.click();
      
       
        //open the seelct drop box by clicking on the select component
        WebElement selectInput = getElement("{\"element\":\"#" + SELECT_ID + "\",\"subId\":\"oj-select-chosen\"}");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(selectInput).click().perform();
        //TODO -- 0j-select-drop  getNodeBySubId is broken
        evalJavascript("window.scrollBy(0,1000)", "");
        this.waitForMilliseconds(1000);
        /*
        //Verify that drop list is displayed
        WebElement list = getElement("{\"element\":\"#" + SELECT_ID + "\",\"subId\":\"oj-select-drop\"}");
        System.out.println("**** LIST: " + list.getAttribute("class"));
        System.out.println("**** LIST: " + list.getAttribute("style"));

        //get the  "display" value from style attribute

        String style = list.getAttribute("style");

        //verify that calendar is displayed
        boolean displayed = style.indexOf("display: block;") > -1;
        Assert.assertTrue(displayed);

        //verify that popup is open
        String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
        Assert.assertEquals(rtn, "true");
        //click on different element and verify that select list box closes
        WebElement inputField =
            getElement("{\"element\":\"#" + INPUTDATE_ID + "\",\"subId\":\"oj-inputdatetime-date-input\"}");

        actions.moveToElement(inputField).click().perform();
        //verify that select list box closed
        displayed = style.indexOf("display: block;") > -1;
        Assert.assertFalse(displayed);
        //close the popup
        click("id=" + BUTTON_TOGGLE_POPUP_DIV);
        rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
        Assert.assertEquals(rtn, "false");
*/
    }
}
