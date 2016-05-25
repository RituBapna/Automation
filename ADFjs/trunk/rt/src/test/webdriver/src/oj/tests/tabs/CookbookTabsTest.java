package oj.tests.tabs;

import java.util.List;
import java.util.NoSuchElementException;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class CookbookTabsTest extends OJetBase {
    public CookbookTabsTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "tabs" })
    public void testBasicTabSwitching() throws Exception {
        startupTest("demo-tabs-basic.html", null);
        verifyTitle("Incorrect page title;", "Tabs - Basic Tabs");
        waitForElementVisible("id=tabs");

        //make sure that first tab is selected
        String tabId = evalJavascript("return $('#tabs').ojTabs('option', 'selected')");
        Assert.assertEquals(tabId, "0");
        //get the second tab and click to select it
        WebElement tab = getElement("{\"element\":\"#tabs\",\"index\":1,\"subId\":\"oj-tabs-tab\"}");
        tab.click();
        //id of the current selected steps
        tabId = evalJavascript("return $('#tabs').ojTabs('option', 'selected')");
        //Second tab is selected
        Assert.assertEquals(tabId, "1");

        //clcik  on third tab and verify that third tab is selected
        tab = getElement("{\"element\":\"#tabs\",\"index\":2,\"subId\":\"oj-tabs-tab\"}");
        tab.click();
        //id of the current selected steps
        tabId = evalJavascript("return $('#tabs').ojTabs('option', 'selected')");
        //third tab is selected
        Assert.assertEquals(tabId, "2");
    }


    @Test(groups = { "cookbook", "tabs" })
    public void testDisabledTabCannotBeSelected() throws Exception {
        startupTest("demo-tabs-basic.html", null);
        verifyTitle("Incorrect page title;", "Tabs - Basic Tabs");
        waitForElementVisible("id=tabs");


        //get the fourth disabled tab and click to select it
        WebElement tab = getElement("{\"element\":\"#tabs\",\"index\":3,\"subId\":\"oj-tabs-tab\"}");
        tab.click();
        //id of the current selected steps
        String tabId = evalJavascript("return $('#tabs').ojTabs('option', 'selected')");
        //Still the First  tab is selected
        Assert.assertEquals(tabId, "0");


    }

    /*
    @Test(groups = { "cookbook" })
    public void testTabSorting() throws Exception {
        startupTest("demo-tabs-sortable.html", null);
        verifyTitle("Incorrect page title;", "Tabs - Sortable Tabs");
        waitForElementVisible("id=sortTabs");


        //Move 4th tab to 1st position
        WebElement sourceTab = getElement("{\"element\":\"#sortTabs\",\"index\":0,\"subId\":\"oj-tabs-tab\"}");
        WebElement targetTab = getElement("{\"element\":\"#sortTabs\",\"index\":3,\"subId\":\"oj-tabs-tab\"}");
        //  (new Actions(getWebDriver())).dragAndDrop(sourceTab, targetTab).perform();
        Actions builder = new Actions(getWebDriver());

        builder.clickAndHold(sourceTab);
        builder.moveToElement(targetTab);
        builder.release(sourceTab);
        builder.build().perform();


        //id of the current selected tab
        String tabId = evalJavascript("return $('#sortTabs').ojTabs('option', 'selected')");
        //Still the First  tab is selected
        Assert.assertEquals(tabId, "sort-tabs-1");

        //label of the fourth tab
        WebElement tab = getElement("{\"element\":\"#sortTabs\",\"index\":3,\"subId\":\"oj-tabs-title\"}");
        //  WebElement tabslist = getElement("{\"element\":\"#sortTabs\",\"subId\":\"oj-tabs-conveyor\"}");
        Assert.assertEquals(tab.getText(), "Sortable Tab 1");


    }
*/

    @Test(groups = { "cookbook", "tabs" })
    public void testOnSpecifiedTabsAreRemovable() throws Exception {
        startupTest("demo-tabs-sortable.html", null);
        verifyTitle("Incorrect page title;", "Tabs - Sortable Tabs");
        waitForElementVisible("id=sortTabs");


        //first tab is not removable
        boolean present = isChildElementPresent(getElement("id=tid1"), By.className("oj-removable"));
        Assert.assertFalse(present);
        present = isChildElementPresent(getElement("id=tid1"), By.className("oj-tabs-close-icon"));
        Assert.assertFalse(present);

        //2nd  tab is not removable
        present = isChildElementPresent(getElement("id=tid2"), By.className("oj-removable"));
        Assert.assertFalse(present);
        present = isChildElementPresent(getElement("id=tid2"), By.className("oj-tabs-close-icon"));
        Assert.assertFalse(present);

        //3rd  tab is  removable
        present = isChildElementPresent(getElement("id=tid3"), By.className("oj-removable"));
        Assert.assertTrue(present);
        present = isChildElementPresent(getElement("id=tid3"), By.className("oj-tabs-close-icon"));
        Assert.assertTrue(present);

        //4th  tab is  removable
        present = isChildElementPresent(getElement("id=tid4"), By.className("oj-removable"));
        Assert.assertTrue(present);
        present = isChildElementPresent(getElement("id=tid4"), By.className("oj-tabs-close-icon"));
        Assert.assertTrue(present);

    }


    @Test(groups = { "cookbook", "tabs" })
    public void testTabContextMenu() throws Exception {
        startupTest("demo-tabs-sortable.html", null);
        verifyTitle("Incorrect page title;", "Tabs - Sortable Tabs");
        waitForElementVisible("id=sortTabs");


        WebElement sourceTab = getElement("id=tid1");
        Actions action = new Actions(getWebDriver());
        action.contextClick(sourceTab).perform();
        waitForElementVisible("id=sortTabscontextmenu");
        WebElement contextMenu = getElement("id=sortTabscontextmenu");


        //Verify that 4 menu items are displayed on first tab and Cut" is the only one enabled
        WebElement cut = contextMenu.findElement(By.id("ojtabscut"));
        boolean disabled = cut.getAttribute("class").indexOf("oj-disabled") >= 0;
        Assert.assertFalse(disabled, "Cut menu item is disabled");

        WebElement pastebefore = contextMenu.findElement(By.id("ojtabspastebefore"));
        disabled = pastebefore.getAttribute("class").indexOf("oj-disabled") >= 0;
        Assert.assertTrue(disabled, "Paste Before menu item is not disabled");

        WebElement pasteafter = contextMenu.findElement(By.id("ojtabspasteafter"));
        disabled = pasteafter.getAttribute("class").indexOf("oj-disabled") >= 0;
        Assert.assertTrue(disabled, "Paste Ater menu item is not disabled");

        WebElement remove = contextMenu.findElement(By.id("ojtabsremove"));
        disabled = remove.getAttribute("class").indexOf("oj-disabled") >= 0;
        Assert.assertTrue(disabled, "Remove menu item is not disabled");

        //move the tab using cut and paste context menu
        //click on Cut menu item
        action.click(cut).perform();
        //right mouse click on tab 4
        sourceTab = getElement("id=tid4");
        //open context menu
        action.contextClick(sourceTab).perform();
        waitForElementVisible("id=sortTabscontextmenu");
        contextMenu = getElement("id=sortTabscontextmenu");
        //Verify that all 4 menu items are enabled
        cut = contextMenu.findElement(By.id("ojtabscut"));
        disabled = cut.getAttribute("class").indexOf("oj-disabled") >= 0;
        Assert.assertFalse(disabled, "Cut menu item is disabled");

        pastebefore = contextMenu.findElement(By.id("ojtabspastebefore"));
        disabled = pastebefore.getAttribute("class").indexOf("oj-disabled") >= 0;
        Assert.assertFalse(disabled, "Paste Before menu item is not disabled");

        pasteafter = contextMenu.findElement(By.id("ojtabspasteafter"));
        disabled = pasteafter.getAttribute("class").indexOf("oj-disabled") >= 0;
        Assert.assertFalse(disabled, "Paste After menu item is not disabled");

        remove = contextMenu.findElement(By.id("ojtabsremove"));
        disabled = remove.getAttribute("class").indexOf("oj-disabled") >= 0;
        Assert.assertFalse(disabled, "Remove menu item is not disabled");
        //click on paste after menu item
        action.click(pasteafter).perform();
        //Assert that "Sortable Tab 1" is the last tab now
        List<WebElement> tabs =
            getElement("id=sortTabs").findElement(By.className("oj-tabs-nav")).findElements(By.tagName("li"));
        System.out.println("****tabs size ######: " + tabs.size());
        //get the title of 4th tab
        WebElement titleSpan = tabs.get(3).findElement(By.className("oj-tabs-title"));
        Assert.assertEquals(titleSpan.getText(), "Sortable Tab 1");

        //remove a tab using context menu item
        sourceTab = getElement("id=tid4");
        //open context menu
        action.contextClick(sourceTab).perform();
        waitForElementVisible("id=sortTabscontextmenu");
        contextMenu = getElement("id=sortTabscontextmenu");
        remove = contextMenu.findElement(By.id("ojtabsremove"));
        action.click(remove).perform();
        //Assert that there are only 3 tabs left
        tabs = getElement("id=sortTabs").findElement(By.className("oj-tabs-nav")).findElements(By.tagName("li"));
        System.out.println("****tabs size ######: " + tabs.size());

        Assert.assertEquals(3, tabs.size());
        boolean present =
            isChildElementPresent(getElement("id=sortTabs").findElement(By.className("oj-tabs-nav")), By.id("tid4"));
        Assert.assertFalse(present);

    }

    @Test(groups = { "cookbook", "tabs" }, dependsOnMethods = { "testAddTabAndDeleteIt" })
    public void testDeleteFirstTab() throws Exception {
        startupTest("demo-tabs-addRemove.html", null);
        verifyTitle("Incorrect page title;", "Tabs - Add And Remove Tabs");
        waitForElementVisible("id=addTabs");
        //Delete the first tab (already existing tab)
        WebElement deleteIcon = getElement("{\"element\":\"#addTabs\",\"index\":0,\"subId\":\"oj-tabs-close-icon\"}");
        deleteIcon.click();
        //Get the title of the first tab after delete
        WebElement title = getElement("{\"element\":\"#addTabs\",\"index\":0,\"subId\":\"oj-tabs-title\"}");
        Assert.assertEquals(title.getText(), "Shorter label");

    }

    @Test(groups = { "cookbook", "tabs" })
    public void testAddTabAndDeleteIt() throws Exception {
        startupTest("demo-tabs-addRemove.html", null);
        verifyTitle("Incorrect page title;", "Tabs - Add And Remove Tabs");
        waitForElementVisible("id=addTabs");
        WebElement okBtn = null, addBtn = null;
        WebElement wrapperDiv = getElement("id=addTabWrapper");
        addBtn = getButton(wrapperDiv, "Add Tab");

        addBtn.click();
        waitForElementVisible("id=tabDialog");
        WebElement footer = getElement("{\"element\":\"#tabDialog\",\"subId\":\"oj-dialog-footer\"}");

        okBtn = getButton(footer, "OK");

        okBtn.click();

        WebElement newTabTitle = getElement("{\"element\":\"#addTabs\",\"index\":3,\"subId\":\"oj-tabs-title\"}");

        Assert.assertEquals(newTabTitle.getText(), "Tab 1");
        //get the Delete Icon for the newly added tab
        WebElement deleteIcon = getElement("{\"element\":\"#addTabs\",\"index\":3,\"subId\":\"oj-tabs-close-icon\"}");
        deleteIcon.click();
        Boolean ispresent = isElementPresent("{\"element\":\"#addTabs\",\"index\":3,\"subId\":\"oj-tabs-title\"}");

        Assert.assertFalse(ispresent, "New Tab is deleted");


    }

    @Test(groups = { "cookbook", "tabs" })
    public void testNoEventWhenSelectedTabIsClicked() throws Exception {
        startupTest("demo-tabs-events.html", null);
        verifyTitle("Incorrect page title;", "Tabs - Events");
        waitForElementVisible("id=tabs1");
        WebElement div = getElement("id=tabs1p");

        //clear log
        WebElement clearLog = getButton(div, "Clear log");

        clearLog.click();
        //Click on the selected tab
        WebElement tab = getElement("{\"element\":\"#tabs1\",\"index\":0,\"subId\":\"oj-tabs-tab\"}");
        tab.click();

        //Get event log
        //WebElement eventLog = getElement("id=eventlog");
        waitForElementVisible("id=eventlog");
        String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
        boolean noLogs = eventLog.trim().length() == 0;
        System.out.println("####### eventLogL: " + eventLog.trim().length());
        System.out.println("####### eventLogL:  *" + eventLog + "*");
        Assert.assertTrue(noLogs, "no jet event raised when selected tab is clicked");

    }


    @Test(groups = { "cookbook", "tabs" }, dependsOnMethods = { "testNoEventWhenSelectedTabIsClicked" })
    public void testEventsUponTabSwitch() throws Exception {
        startupTest("demo-tabs-events.html", null);
        verifyTitle("Incorrect page title;", "Tabs - Events");
        waitForElementVisible("id=tabs1");
        //ClearLog
        WebElement div = getElement("id=tabs1p");

        WebElement clearLog = getButton(div, "Clear log");

        clearLog.click();
        //switch to second tab
        WebElement tab = getElement("{\"element\":\"#tabs1\",\"index\":1,\"subId\":\"oj-tabs-tab\"}");
        tab.click();
        waitForElementVisible("id=eventlog");
        String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
        //Get event log
        // WebElement eventLog = getElement("id=eventlog");
        System.out.println("####### eventLog: " + eventLog);
        boolean beforeDeselectLog =
            eventLog.indexOf("ojbeforedeselect: tabs1 data { fromTab: t1; fromContent: p1; toTab: t2; toContent: p2;}") >
            -1;
        boolean beforeSelectLog =
            eventLog.indexOf("ojbeforeselect: tabs1 data { fromTab: t1; fromContent: p1; toTab: t2; toContent: p2;}") >
            -1;
        boolean optionChangelog = eventLog.indexOf("optionChange: tabs1 data { previousValue: t1; value: t2}") > -1;
        boolean deselectLog =
            eventLog.indexOf("ojdeselect: tabs1 data { fromTab: t1; fromContent: p1; toTab: t2; toContent: p2;}") > -1;
        boolean selectLog =
            eventLog.indexOf("ojselect: tabs1 data { fromTab: t1; fromContent: p1; toTab: t2; toContent: p2;}") > -1;

        Assert.assertTrue(beforeDeselectLog, "ojbeforedeselect event called with correct parameters");
        Assert.assertTrue(beforeSelectLog, "ojbeforeselect event called with correct parameters");
        Assert.assertTrue(optionChangelog, "ojoptionchange event called with correct parameters");
        Assert.assertTrue(deselectLog, "ojdeselect event called with correct parameters");
        Assert.assertTrue(selectLog, "ojselect event called with correct parameters");

    }

    @Test(groups = { "cookbook", "tabs" }, dependsOnMethods = { "testEventsUponTabSwitch" })
    public void testTabRemoveEvent() throws Exception {
        startupTest("demo-tabs-events.html", null);
        verifyTitle("Incorrect page title;", "Tabs - Events");
        waitForElementVisible("id=tabs1");
        //Clear log
        WebElement div = getElement("id=tabs1p");

        //make sure tab 1 selected
        WebElement tab = getElement("{\"element\":\"#tabs1\",\"index\":0,\"subId\":\"oj-tabs-tab\"}");
        tab.click();
        WebElement clearLog = getButton(div, "Clear log");

        clearLog.click();
        //Delete the first tab (already existing tab)
        WebElement deleteIcon = getElement("{\"element\":\"#tabs1\",\"index\":0,\"subId\":\"oj-tabs-close-icon\"}");
        deleteIcon.click();

        //Get event log
        //   WebElement eventLog = getElement("id=eventlog");
        waitForElementVisible("id=eventlog");
        String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
        System.out.println("####### eventLog: " + eventLog);
        boolean beforeRemoveLog = eventLog.indexOf("ojbeforeremove: tabs1 data { tab: t1; content: p1;}") > -1;
        boolean beforeDeselectLog =
            eventLog.indexOf("ojbeforedeselect: tabs1 data { fromTab: t1; fromContent: p1; toTab: t2; toContent: p2;}") >
            -1;
        boolean beforeSelectLog =
            eventLog.indexOf("ojbeforeselect: tabs1 data { fromTab: t1; fromContent: p1; toTab: t2; toContent: p2;}") >
            -1;
        boolean optionChangelog = eventLog.indexOf("optionChange: tabs1 data { previousValue: t1; value: t2}") > -1;
        boolean deselectLog =
            eventLog.indexOf("ojdeselect: tabs1 data { fromTab: t1; fromContent: p1; toTab: t2; toContent: p2;}") > -1;
        boolean selectLog =
            eventLog.indexOf("ojselect: tabs1 data { fromTab: t1; fromContent: p1; toTab: t2; toContent: p2;}") > -1;
        boolean removeLog = eventLog.indexOf("ojremove: tabs1 data { tab: t1; content: p1;}") > -1;


        Assert.assertTrue(beforeRemoveLog, "ojbeforeremove event called with correct parameters");
        Assert.assertTrue(beforeDeselectLog, "ojbeforedeselect event called with correct parameters");
        Assert.assertTrue(beforeSelectLog, "ojbeforeselect event called with correct parameters");
        Assert.assertTrue(optionChangelog, "ojoptionchange event called with correct parameters");
        Assert.assertTrue(deselectLog, "ojdeselect event called with correct parameters");
        Assert.assertTrue(selectLog, "ojselect event called with correct parameters");
        Assert.assertTrue(removeLog, "ojremove event called with correct parameters");

        //Now Move focus on second tab and deleted the first tab
        tab = getElement("{\"element\":\"#tabs1\",\"index\":1,\"subId\":\"oj-tabs-tab\"}");
        tab.click();
        clearLog.click();
        deleteIcon = getElement("{\"element\":\"#tabs1\",\"index\":0,\"subId\":\"oj-tabs-close-icon\"}");
        deleteIcon.click();
        waitForElementVisible("id=eventlog");
        eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
        beforeRemoveLog = eventLog.indexOf("ojbeforeremove: tabs1 data { tab: t2; content: p2;}") > -1;
        removeLog = eventLog.indexOf("ojremove: tabs1 data { tab: t2; content: p2;}") > -1;
        Assert.assertTrue(beforeRemoveLog, "ojbeforeremove event called with correct parameters");
        Assert.assertTrue(removeLog, "ojremove event called with correct parameters");
    }

    /*
    @Test(groups = { "cookbook" })
    public void testVerticalOrientationOfTab() throws Exception {
        startupTest("demo-tabs-vertical.html", null);
        verifyTitle("Incorrect page title;", "Tabs - Vertical Tabs");
        waitForElementVisible("id=vtabs");

        String orientation = evalJavascript("return $('#vtabs').ojTabs('option', 'orientation')");

        Assert.assertEquals(orientation, "vertical", "Tab orientation is vertical");

        //cannot make a disabled vertical tab current
        //2nd tab is disabled

        WebElement tab = getElement("{\"element\":\"#vtabs\",\"index\":1,\"subId\":\"oj-tabs-tab\"}");
        tab.click();

        //get the current selected tab. make sure its still the third tab
        String tabId = evalJavascript("return $('#vtabs').ojTabs('option', 'selected')");
        Assert.assertEquals(tabId, "tabs-3");

        //Select the first tab, which is enabled
        tab = getElement("{\"element\":\"#vtabs\",\"index\":0,\"subId\":\"oj-tabs-tab\"}");
        tab.click();

        //check that first tab is the currently selected tab
        tabId = evalJavascript("return $('#vtabs').ojTabs('option', 'selected')");
        Assert.assertEquals(tabId, "tabs-1");

    }
*/

    @Test(groups = { "cookbook", "tabs" })
    public void testTabEdgeSupport() throws Exception {
        startupTest("demo-tabs-edge.html", null);
        verifyTitle("Incorrect page title;", "Tabs - Tab Bar Edge");
        waitForElementVisible("id=vtabs");

        String edge = evalJavascript("return $('#vtabs').ojTabs('option', 'edge')");
        Assert.assertEquals(edge, "top", "Tab orientation is top");

        //2nd tab is selected

        WebElement tab = getElement("{\"element\":\"#vtabs\",\"index\":1,\"subId\":\"oj-tabs-tab\"}");
        tab.click();

        //Change the edge to bottom
        List<WebElement> inputs = findElements("{\"element\":\"#edgeId\",\"subId\":\"oj-radioset-inputs\"}");
        inputs.get(1).click();

        //check the edge of the tab
        edge = evalJavascript("return $('#vtabs').ojTabs('option', 'edge')");
        Assert.assertEquals(edge, "bottom", "Tab orientation is bottom");

        //verify that second tab is still selected
        String index = evalJavascript("return $('#vtabs').ojTabs('option', 'selected')");
        Assert.assertEquals(index, "1");

        //Change the edge to start
        inputs.get(2).click();

        //check the edge of the tab
        edge = evalJavascript("return $('#vtabs').ojTabs('option', 'edge')");
        Assert.assertEquals(edge, "start", "Tab orientation is start");

        //verify that second tab is still selected
        index = evalJavascript("return $('#vtabs').ojTabs('option', 'selected')");
        Assert.assertEquals(index, "1");

        //Change the edge to end
        inputs.get(3).click();

        //check the edge of the tab
        edge = evalJavascript("return $('#vtabs').ojTabs('option', 'edge')");
        Assert.assertEquals(edge, "end", "Tab orientation is end");

        //verify that second tab is still selected
        index = evalJavascript("return $('#vtabs').ojTabs('option', 'selected')");
        Assert.assertEquals(index, "1");

        //Change the edge to top again
        inputs.get(0).click();

        //check the edge of the tab
        edge = evalJavascript("return $('#vtabs').ojTabs('option', 'edge')");
        Assert.assertEquals(edge, "top", "Tab orientation is top");

        //verify that second tab is still selected
        index = evalJavascript("return $('#vtabs').ojTabs('option', 'selected')");
        Assert.assertEquals(index, "1");

    }

    public WebElement getButton(WebElement parent, String label) {
        List<WebElement> buttons = parent.findElements(By.tagName("button"));
        System.out.println(" Button size:  " + buttons.size());
        WebElement button = null;
        for (WebElement btn : buttons) {
            WebElement span = btn.findElement(By.tagName("span"));
            String btnLabel = span.getText();
            System.out.println("#### Button Label : *" + btnLabel + "*");
            System.out.println("#### Passed Label : *" + label + "*");
            if (btnLabel.trim().equals(label)) {
                System.out.println("###matched");
                button = btn;
            }

        }
        System.out.println("**** button: " + button.getAttribute("disabled"));
        return button;
    }

    @Test(groups = { "cookbook", "tabs" })
    public void testAddTabToTheBegining() throws Exception {
        startupTest("demo-tabs-button.html", null);
        verifyTitle("Incorrect page title;", "Tabs - Buttons on Tab bar");
        waitForElementVisible("id=tabsDiv");
        WebElement okBtn = null, addBtn = null;
        WebElement wrapperDiv = getElement("id=tabsWithButtons");
        addBtn = getButton(wrapperDiv, "add");

        addBtn.click();
        waitForElementVisible("id=addDialog");
        //cHANGE INDEX VALUE TO 0 IN DIALOG
        Actions action = new Actions(getWebDriver());
        WebElement indexinput = getElement("id=i1");
        action.moveToElement(indexinput).click().perform();
        indexinput.clear();
        indexinput.sendKeys("0");
        indexinput.sendKeys(Keys.TAB);

        //Click OK on dialog
        WebElement footer = getElement("{\"element\":\"#addDialog\",\"subId\":\"oj-dialog-footer\"}");

        okBtn = getButton(footer, "OK");
        okBtn.click();


        WebElement tabBar = getElement("id=tabbar");
        List<WebElement> tabs = tabBar.findElements(By.tagName("li"));
        WebElement titleSpan = tabs.get(0).findElement(By.className("oj-tabs-title"));
        Assert.assertEquals(titleSpan.getText(), "Tab 1");
        //get the Delete Icon for the newly added tab
        WebElement deleteIcon = getElement("{\"element\":\"#tabsDiv\",\"index\":0,\"subId\":\"oj-tabs-close\"}");
        deleteIcon.click();
        //check the title of the first tab after delete
        tabs = tabBar.findElements(By.tagName("li"));
        titleSpan = tabs.get(0).findElement(By.className("oj-tabs-title"));
        Assert.assertEquals(titleSpan.getText(), "First Label");
    }

    @Test(groups = { "cookbook", "tabs" })
    public void testAddTabToTheMiddle() throws Exception {
        startupTest("demo-tabs-button.html", null);
        verifyTitle("Incorrect page title;", "Tabs - Buttons on Tab bar");
        waitForElementVisible("id=tabsDiv");
        WebElement okBtn = null, addBtn = null;
        WebElement wrapperDiv = getElement("id=tabsDiv");
        addBtn = getButton(wrapperDiv, "add");

        addBtn.click();
        waitForElementVisible("id=addDialog");
        //cHANGE INDEX VALUE TO 0 IN DIALOG
        Actions action = new Actions(getWebDriver());
        WebElement indexinput = getElement("id=i1");
        action.moveToElement(indexinput).click().perform();
        indexinput.clear();
        indexinput.sendKeys("2");
        indexinput.sendKeys(Keys.TAB);

        //Click OK on dialog
        WebElement footer = getElement("{\"element\":\"#addDialog\",\"subId\":\"oj-dialog-footer\"}");
        okBtn = getButton(footer, "OK");
        okBtn.click();

        WebElement tabBar = getElement("id=tabbar");
        List<WebElement> tabs = tabBar.findElements(By.tagName("li"));
        WebElement titleSpan = tabs.get(2).findElement(By.className("oj-tabs-title")); //newly inserted tab
        Assert.assertEquals(titleSpan.getText(), "Tab 1");
        //get the Delete Icon for the newly added tab
        WebElement deleteIcon = getElement("{\"element\":\"#tabsDiv\",\"index\":2,\"subId\":\"oj-tabs-close\"}");
        deleteIcon.click();
        //check the title of the first tab after delete
        tabs = tabBar.findElements(By.tagName("li"));
        titleSpan = tabs.get(2).findElement(By.className("oj-tabs-title")); //newly inserted tab
        Assert.assertEquals(titleSpan.getText(), "Relatively long label");
    }

    @Test(groups = { "cookbook", "tabs" })
    public void testButtonsOnTabBar() throws Exception {
        startupTest("demo-tabs-button.html", null);
        verifyTitle("Incorrect page title;", "Tabs - Buttons on Tab bar");
        waitForElementVisible("id=tabsDiv");
        WebElement okBtn = null, addBtn = null;
        WebElement wrapperDiv = getElement("id=tabsDiv");
        //Test the button before the first tab header
        WebElement startFacetId = getElement("id=div1");
        boolean hasfacetClass = startFacetId.getAttribute("class").indexOf("oj-tabs-facet") > -1;
        boolean hasstartClass = startFacetId.getAttribute("class").indexOf("oj-start") > -1;

        Assert.assertTrue(hasfacetClass);
        Assert.assertTrue(hasstartClass);
        List<WebElement> btns = startFacetId.findElements(By.tagName("button"));
        Assert.assertEquals(btns.size(), 1, "1 button before the first tab header");
        Assert.assertEquals(btns.get(0).findElement(By.className("oj-button-text")).getText(), "start",
                            "button label is not 'start'");

        //Test the button after  the last tab header
        WebElement endFacetId = getElement("id=div2");
        hasfacetClass = endFacetId.getAttribute("class").indexOf("oj-tabs-facet") > -1;
        boolean hasflexClass = endFacetId.getAttribute("class").indexOf("demo-tabs-facet-flex") > -1;

        Assert.assertTrue(hasfacetClass);
        Assert.assertTrue(hasflexClass);
        btns = endFacetId.findElements(By.tagName("button"));
        Assert.assertEquals(btns.size(), 2, "2 buttons after the last tab header");
        Assert.assertEquals(btns.get(0).findElement(By.className("oj-button-text")).getText(), "add",
                            "button label is not 'add'");
        Assert.assertEquals(btns.get(1).findElement(By.className("oj-button-text")).getText(), "remove",
                            "button label is not 'remove'");

    }

    @Test(groups = { "cookbook", "tabs" })
    public void tesRemovingTabsUsingCloseTabApi() throws Exception {
        startupTest("demo-tabs-button.html", null);
        verifyTitle("Incorrect page title;", "Tabs - Buttons on Tab bar");
        waitForElementVisible("id=tabsDiv");
        WebElement okBtn = null, addBtn = null;
        WebElement wrapperDiv = getElement("id=tabsDiv");

        //Test the button after  the last tab header
        WebElement endFacetId = getElement("id=div2");

        List<WebElement> btns = endFacetId.findElements(By.tagName("button"));
        WebElement removeBtn = btns.get(1);
        //click on  button to open the drop down menu
        Actions action = new Actions(getWebDriver());
        action.click(removeBtn).perform();
       
        //click on "Shorter Label" menu item to remove it
        getElement("id=mshort").click();
        //verify that "Shorter label" tab is not displated
        boolean present = this.isChildElementPresent(getElement("id=tabbar"), By.id("short"));
        Assert.assertFalse(present);

        //verify that "Shorter label" content  is not displated
        present = this.isChildElementPresent(getElement("id=tabsDiv"), By.id("tabs-2"));
        Assert.assertFalse(present);

    }
}
