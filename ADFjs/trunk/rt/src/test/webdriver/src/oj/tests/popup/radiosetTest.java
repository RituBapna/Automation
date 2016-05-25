package oj.tests.popup;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class radiosetTest extends OJetBase {
    private static final String TEST_PAGE = "testOjPopup.html";
    private static final String TEST_PAGE_TITLE = "ojPopup Test";
    private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
    private static final String POPUP_DIV = "popupdiv";
    private static final String RADIOSET_ID = "radioSetId";
    private static final String RADIOSET_VAL = "curr-value";
    private static final String ACCORDION_ID = "accordionPage";
    private static final String CLEARRADIOSETVALUE_BTN = "clearRadioVal";

    public radiosetTest() {
        super(new TestConfigBuilder().setContextRoot("popup/popupTest").setAppRoot("ojpopup").build());
    }

    @Test(groups = { "popup" })
    public void testSetValue_AutoDismissNone() throws Exception {
        startupTest(TEST_PAGE, null);
        maximizeWindow();
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the button to be displayed
        waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
        //set  autoDismiss  to "none"
        evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'none')");
        //open the popup by clicking on the button
        click("id=" + BUTTON_TOGGLE_POPUP_DIV);
        //expand the collapsible that has radioset
        evalJavascript("window.scrollBy(0,250)", "");
        WebElement headerIcon =
            getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":2}");

        headerIcon.click();
        evalJavascript("window.scrollBy(0,550)", "");
        // WebElement inputField = getElement("id=" +  INPUTTEXTAREA_ID);
        //set the radioset value to "Deep Dish" (first in radio list)
        List<WebElement> inputs =
            findElements("{\"element\":\"#" + RADIOSET_ID + "\",\"subId\":\"oj-radioset-inputs\"}");
        WebElement text = inputs.get(0);

        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(text).click().perform();


        //Verify that value is set in radioset
        String val = evalJavascript("return $('#" + RADIOSET_ID + "').ojRadioset('option', 'value')");
        Assert.assertEquals(val, "crust11");

        //check that observable value is updated on page
        WebElement currval = getElement("id=" + RADIOSET_VAL);
        Assert.assertEquals(currval.getText(), "crust11");

        //verify that popup is open
        String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
        Assert.assertEquals(rtn, "true");

        //close the popup
        click("id=" + BUTTON_TOGGLE_POPUP_DIV);

    }

    @Test(groups = { "popup" })
    public void testSetValue_AutoDismissFocusLoss() throws Exception {
        startupTest(TEST_PAGE, null);
        maximizeWindow();
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the button to be displayed
        waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
        //set  autoDismiss  to "focusLoss"
        evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'focusLoss')");
        //open the popup by clicking on the button
        click("id=" + BUTTON_TOGGLE_POPUP_DIV);
        //expand the collapsible that has radioset
        evalJavascript("window.scrollBy(0,250)", "");
        WebElement headerIcon =
            getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":2}");

        headerIcon.click();
        evalJavascript("window.scrollBy(0,550)", "");

        //set the radioset value to "Deep Dish" (first in radio list)
        List<WebElement> inputs =
            findElements("{\"element\":\"#" + RADIOSET_ID + "\",\"subId\":\"oj-radioset-inputs\"}");
        WebElement text = inputs.get(0);

        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(text).click().perform();


        //Verify that value is set in radioset
        String val = evalJavascript("return $('#" + RADIOSET_ID + "').ojRadioset('option', 'value')");
        Assert.assertEquals(val, "crust11");

        //check that observable value is updated on page
        WebElement currval = getElement("id=" + RADIOSET_VAL);
        Assert.assertEquals(currval.getText(), "crust11");

        //verify that popup is open
        String rtn = evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('isOpen')");
        Assert.assertEquals(rtn, "true");

        //close the popup
        click("id=" + BUTTON_TOGGLE_POPUP_DIV);

    }

    @Test(groups = { "popup" })
    public void testTitle() throws Exception {
        startupTest(TEST_PAGE, null);
        maximizeWindow();
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the button to be displayed
        waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
        //set  autoDismiss  to "focusLoss"
        evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'focusLoss')");
        //open the popup by clicking on the button
        click("id=" + BUTTON_TOGGLE_POPUP_DIV);
        //expand the collapsible that has radioset
        evalJavascript("window.scrollBy(0,250)", "");
        WebElement headerIcon =
            getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":2}");

        headerIcon.click();
        evalJavascript("window.scrollBy(0,550)", "");

        //move focus to "Deep Dish" (first in radio list)
        List<WebElement> inputs =
            findElements("{\"element\":\"#" + RADIOSET_ID + "\",\"subId\":\"oj-radioset-inputs\"}");
        WebElement text = inputs.get(0);
        
        evalJavascript("$('#" + text.getAttribute("id") + "').focus()");

        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(text).perform();
        waitForMilliseconds(1000);
        actions.moveToElement(text).perform();
        waitForMilliseconds(1000);

        //Verify the title value
        WebElement msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(), "radioset title");


        //close the popup
        click("id=" + BUTTON_TOGGLE_POPUP_DIV);

    }

    @Test(groups = { "popup" })
    public void testValidationError() throws Exception {
        startupTest(TEST_PAGE, null);
        maximizeWindow();
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the button to be displayed
        waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
        //set  autoDismiss  to "focusLoss"
        evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'focusLoss')");
        //open the popup by clicking on the button
        click("id=" + BUTTON_TOGGLE_POPUP_DIV);
        //expand the collapsible that has radioset
        evalJavascript("window.scrollBy(0,250)", "");
        WebElement headerIcon =
            getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":2}");

        headerIcon.click();
        evalJavascript("window.scrollBy(0,750)", "");
        this.waitForMilliseconds(1000);
        //Click on the button to clear the radio button value so that the required error is displayed
        WebElement clearValBtn = getElement("id=" + CLEARRADIOSETVALUE_BTN);
        clearValBtn.click();
        //move focus to "Deep Dish" (first in radio list)
        List<WebElement> inputs =
            findElements("{\"element\":\"#" + RADIOSET_ID + "\",\"subId\":\"oj-radioset-inputs\"}");
        WebElement text = inputs.get(0);

        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(text).perform();
   
        //Verify the title value
        WebElement msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value is required.");
        msg = getMessagingContentNodeBySubId(text, "oj-messaging-popup-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "You must enter a value.");


        //close the popup
        click("id=" + BUTTON_TOGGLE_POPUP_DIV);

    }
}
