package oj.tests.popup;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class menuTest extends OJetBase {
    private static final String TEST_PAGE = "testOjPopup.html";
    private static final String TEST_PAGE_TITLE = "ojPopup Test";
    private static final String BUTTON_TOGGLE_POPUP_DIV = "btn1";
    private static final String POPUP_DIV = "popupdiv";
    private static final String BUTTONMENU_ID = "menuButton";
    private static final String JETBUTTONWITHCONTEXTMENU_ID = "myButton";
    private static final String HTMLCOMPWITHCONTEXTMENU_ID = "myAnchor";
    private static final String ACCORDION_ID = "accordionPage";

    private static final String MENU_ID = "myMenu";

    public menuTest() {
        super(new TestConfigBuilder().setContextRoot("popup/popupTest").setAppRoot("ojpopup").build());
    }

    @Test(groups = { "popup" })
    public void testMenuOnJETComp_AutoDismissNone() throws Exception {
        startupTest(TEST_PAGE, null);
        maximizeWindow();
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the button to be displayed
        waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
        //set  autoDismiss  to "none"
        evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'none')");
        //open the popup by clicking on the button
        click("id=" + BUTTON_TOGGLE_POPUP_DIV);
        //expmad collapsible that contains menu cmponent
        //Scroll down

        evalJavascript("window.scrollBy(0,250)", "");
        WebElement headerIcon =
            getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":3}");
        System.out.println("**** headerIcon: " + headerIcon.getAttribute("class"));
        System.out.println("**** headerIcon: " + headerIcon.getAttribute("aria-labelledby"));

        //  actions.moveToElement(headerIcon).click().perform();
        headerIcon.click();
        evalJavascript("window.scrollBy(0,850)", "");
        this.waitForMilliseconds(1000);
        //open the context menu on ojButton
        this.contextClick("id=" + JETBUTTONWITHCONTEXTMENU_ID);

        WebElement menu = getElement("id=" + MENU_ID);

        boolean isNotDisplayed = (menu.getAttribute("style").indexOf("display: none;") > -1);
        Assert.assertFalse(isNotDisplayed);

        //Move mouse over Zoom menu item
        WebElement zoom = getElement("id=zoom");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(zoom).click().perform();
        this.waitForMilliseconds(1000);
        //Verify that zoomin sub menu is visible
        WebElement zoomin = getElement("id=zoomin");
        Assert.assertTrue(zoomin.isDisplayed());

        zoomin.click();

        //check that zoomin was selected
        WebElement selectedMenuItem = getElement("id=results");
        Assert.assertEquals(selectedMenuItem.getText(), "Zoom In");

        //check that laucher is myButton was selected
        WebElement launcher = getElement("id=launcher");
        Assert.assertEquals(launcher.getText(), JETBUTTONWITHCONTEXTMENU_ID);
        //Assert that menu is closed

        isNotDisplayed = (menu.getAttribute("style").indexOf("display: none;") > -1);
        Assert.assertTrue(isNotDisplayed);
    }

    @Test(groups = { "popup" })
    public void testMenuOnJETComp_AutoDismissFocusLoss() throws Exception {
        startupTest(TEST_PAGE, null);
        maximizeWindow();
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the button to be displayed
        waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
        //set  autoDismiss  to "focusLoss"
        evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'focusLoss')");
        //open the popup by clicking on the button
        click("id=" + BUTTON_TOGGLE_POPUP_DIV);
        //expmad collapsible that contains menu cmponent
        //Scroll down

        evalJavascript("window.scrollBy(0,250)", "");
        WebElement headerIcon =
            getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":3}");
        System.out.println("**** headerIcon: " + headerIcon.getAttribute("class"));
        System.out.println("**** headerIcon: " + headerIcon.getAttribute("aria-labelledby"));

        //  actions.moveToElement(headerIcon).click().perform();
        headerIcon.click();
        evalJavascript("window.scrollBy(0,850)", "");
        this.waitForMilliseconds(1000);
        //open the context menu on ojButton
        this.contextClick("id=" + JETBUTTONWITHCONTEXTMENU_ID);

        WebElement menu = getElement("id=" + MENU_ID);

        boolean isNotDisplayed = (menu.getAttribute("style").indexOf("display: none") > -1 || menu.getAttribute("style").indexOf("display:none") > -1);
        Assert.assertFalse(isNotDisplayed);

        //Move mouse over Zoom menu item
        WebElement zoom = getElement("id=zoom");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(zoom).click().perform();
        this.waitForMilliseconds(1000);
        //Verify that zoomin sub menu is visible
        WebElement zoomin = getElement("id=zoomin");
        Assert.assertTrue(zoomin.isDisplayed());

        zoomin.click();

        //check that zoomin was selected
        WebElement selectedMenuItem = getElement("id=results");
        Assert.assertEquals(selectedMenuItem.getText(), "Zoom In");

        //check that laucher is myButton was selected
        WebElement launcher = getElement("id=launcher");
        Assert.assertEquals(launcher.getText(), JETBUTTONWITHCONTEXTMENU_ID);
        //Assert that menu is closed

        isNotDisplayed = (menu.getAttribute("style").indexOf("display: none") > -1 || menu.getAttribute("style").indexOf("display:none") > -1);
        Assert.assertTrue(isNotDisplayed);
    }

    @Test(groups = { "popup" })
    public void testMenuOnHTMLComp_AutoDismissNone() throws Exception {
        startupTest(TEST_PAGE, null);
        maximizeWindow();
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the button to be displayed
        waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
        //set  autoDismiss  to "none"
        evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'none')");
        //open the popup by clicking on the button
        click("id=" + BUTTON_TOGGLE_POPUP_DIV);
        //expmad collapsible that contains menu cmponent
        //Scroll down

        evalJavascript("window.scrollBy(0,250)", "");
        WebElement headerIcon =
            getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":3}");
        System.out.println("**** headerIcon: " + headerIcon.getAttribute("class"));
        System.out.println("**** headerIcon: " + headerIcon.getAttribute("aria-labelledby"));

        //  actions.moveToElement(headerIcon).click().perform();
        headerIcon.click();
        evalJavascript("window.scrollBy(0,850)", "");
        this.waitForMilliseconds(1000);
        //open the context menu on ojButton
        this.contextClick("id=" + HTMLCOMPWITHCONTEXTMENU_ID);

        WebElement menu = getElement("id=" + MENU_ID);

        boolean isNotDisplayed = (menu.getAttribute("style").indexOf("display: none;") > -1);
        Assert.assertFalse(isNotDisplayed);

        //Move mouse over Zoom menu item
        WebElement zoom = getElement("id=zoom");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(zoom).click().perform();
        this.waitForMilliseconds(1000);
        //Verify that zoomout sub menu is visible
        WebElement zoomin = getElement("id=zoomout");
        Assert.assertTrue(zoomin.isDisplayed());

        zoomin.click();

        //check that zoomin was selected
        WebElement selectedMenuItem = getElement("id=results");
        Assert.assertEquals(selectedMenuItem.getText(), "Zoom Out");

        //check that laucher is myButton was selected
        WebElement launcher = getElement("id=launcher");
        Assert.assertEquals(launcher.getText(), HTMLCOMPWITHCONTEXTMENU_ID);
        //Assert that menu is closed

        isNotDisplayed = (menu.getAttribute("style").indexOf("display: none;") > -1);
        Assert.assertTrue(isNotDisplayed);
    }

    @Test(groups = { "popup" })
    public void testMenuOnHTMLComp_AutoDismissFocusLoss() throws Exception {
        startupTest(TEST_PAGE, null);
        maximizeWindow();
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the button to be displayed
        waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
        //set  autoDismiss  to "focusLoss"
        evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'focusLoss')");
        //open the popup by clicking on the button
        click("id=" + BUTTON_TOGGLE_POPUP_DIV);
        //expmad collapsible that contains menu cmponent
        //Scroll down

        evalJavascript("window.scrollBy(0,250)", "");
        WebElement headerIcon =
            getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":3}");
        System.out.println("**** headerIcon: " + headerIcon.getAttribute("class"));
        System.out.println("**** headerIcon: " + headerIcon.getAttribute("aria-labelledby"));

        //  actions.moveToElement(headerIcon).click().perform();
        headerIcon.click();
        evalJavascript("window.scrollBy(0,850)", "");
        this.waitForMilliseconds(1000);
        //open the context menu on ojButton
        this.contextClick("id=" + HTMLCOMPWITHCONTEXTMENU_ID);

        WebElement menu = getElement("id=" + MENU_ID);

        boolean isNotDisplayed = (menu.getAttribute("style").indexOf("display: none;") > -1);
        Assert.assertFalse(isNotDisplayed);

        //Move mouse over Zoom menu item
        WebElement zoom = getElement("id=zoom");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(zoom).click().perform();
        this.waitForMilliseconds(1000);
        //Verify that zoomout sub menu is visible
        WebElement zoomin = getElement("id=zoomout");
        Assert.assertTrue(zoomin.isDisplayed());

        zoomin.click();

        //check that zoomin was selected
        WebElement selectedMenuItem = getElement("id=results");
        Assert.assertEquals(selectedMenuItem.getText(), "Zoom Out");

        //check that laucher is myButton was selected
        WebElement launcher = getElement("id=launcher");
        Assert.assertEquals(launcher.getText(), HTMLCOMPWITHCONTEXTMENU_ID);
        //Assert that menu is closed

        isNotDisplayed = (menu.getAttribute("style").indexOf("display: none;") > -1);
        Assert.assertTrue(isNotDisplayed);
    }

    @Test(groups = { "popup" })
    public void testButtonMenu_AutoDismissNone() throws Exception {
        startupTest(TEST_PAGE, null);
        maximizeWindow();
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the button to be displayed
        waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
        //set  autoDismiss  to "none"
        evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'none')");
        //open the popup by clicking on the button
        click("id=" + BUTTON_TOGGLE_POPUP_DIV);
        //expmad collapsible that contains menu cmponent
        //Scroll down

        evalJavascript("window.scrollBy(0,250)", "");
        WebElement headerIcon =
            getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":3}");
        System.out.println("**** headerIcon: " + headerIcon.getAttribute("class"));
        System.out.println("**** headerIcon: " + headerIcon.getAttribute("aria-labelledby"));

        //  actions.moveToElement(headerIcon).click().perform();
        headerIcon.click();
        evalJavascript("window.scrollBy(0,850)", "");
        //open the button
        click("id=" + BUTTONMENU_ID);

        WebElement menu = getElement("id=" + MENU_ID);

        boolean isNotDisplayed = (menu.getAttribute("style").indexOf("display: none;") > -1);
        Assert.assertFalse(isNotDisplayed);

        //Move mouse over Zoom menu item
        WebElement zoom = getElement("id=zoom");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(zoom).click().perform();
        this.waitForMilliseconds(1000);
        //Verify that zoomout sub menu is visible
        WebElement zoomin = getElement("id=zoomout");
        Assert.assertTrue(zoomin.isDisplayed());

        zoomin.click();

        //check that zoomin was selected
        WebElement selectedMenuItem = getElement("id=results");
        Assert.assertEquals(selectedMenuItem.getText(), "Zoom Out");

        //check that laucher is myButton was selected
        WebElement launcher = getElement("id=launcher");
        Assert.assertEquals(launcher.getText(), BUTTONMENU_ID);
        //Assert that menu is closed

        isNotDisplayed = (menu.getAttribute("style").indexOf("display: none;") > -1);
        Assert.assertTrue(isNotDisplayed);
    }

    @Test(groups = { "popup" })
    public void testButtonMenu_AutoDismissFocusLoss() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);

        //wait for the button to be displayed
        waitForElementVisible(BUTTON_TOGGLE_POPUP_DIV);
        //set  autoDismiss  to "focusLoss"
        evalJavascript("return $('#" + POPUP_DIV + "').ojPopup('option', 'autoDismiss', 'focusLoss')");
        //open the popup by clicking on the button
        click("id=" + BUTTON_TOGGLE_POPUP_DIV);
        //expmad collapsible that contains menu cmponent
        //Scroll down

        evalJavascript("window.scrollBy(0,250)", "");
        WebElement headerIcon =
            getElement("{\"element\":\"#" + ACCORDION_ID + "\",\"subId\":\"oj-accordion-header-icon\",\"index\":3}");
        System.out.println("**** headerIcon: " + headerIcon.getAttribute("class"));
        System.out.println("**** headerIcon: " + headerIcon.getAttribute("aria-labelledby"));

        //  actions.moveToElement(headerIcon).click().perform();
        headerIcon.click();
        evalJavascript("window.scrollBy(0,850)", "");

        //open the context menu on ojButton
        click("id=" + BUTTONMENU_ID);

        WebElement menu = getElement("id=" + MENU_ID);

        boolean isNotDisplayed = (menu.getAttribute("style").indexOf("display: none") > -1 || menu.getAttribute("style").indexOf("display:none") > -1);
        
        Assert.assertFalse(isNotDisplayed);

        //Move mouse over Zoom menu item
        WebElement zoom = getElement("id=zoom");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(zoom).click().perform();
        this.waitForMilliseconds(1000);
        //Verify that zoomout sub menu is visible
        WebElement zoomin = getElement("id=zoomout");
        Assert.assertTrue(zoomin.isDisplayed());

        zoomin.click();

        //check that zoomin was selected
        WebElement selectedMenuItem = getElement("id=results");
        Assert.assertEquals(selectedMenuItem.getText(), "Zoom Out");

        //check that laucher is myButton was selected
        WebElement launcher = getElement("id=launcher");
        Assert.assertEquals(launcher.getText(), BUTTONMENU_ID);
        //Assert that menu is closed

        isNotDisplayed = (menu.getAttribute("style").indexOf("display: none") > -1 || menu.getAttribute("style").indexOf("display:none") > -1);
        Assert.assertTrue(isNotDisplayed);
    }
}
