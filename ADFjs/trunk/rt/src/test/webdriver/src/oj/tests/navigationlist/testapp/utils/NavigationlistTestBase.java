package oj.tests.navigationlist.testapp.utils;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;

public class NavigationlistTestBase extends OJetBase {
    public NavigationlistTestBase(String myAppRoot, String myContextRoot) {
        super(new TestConfigBuilder().setContextRoot(myContextRoot).setAppRoot(myAppRoot).build());
    }

    //used for collapsible and sliding
    public void navigate(String navlistId, String itemToClick) {

        WebElement listItem =
            getElement("{\"element\":\"#" + navlistId + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" +
                       itemToClick + "\"}");
        listItem.click();
        waitForMilliseconds(1000);
    }

    //used for collapsible
    public void collapse(String navlistId, String itemToClick) {

        WebElement listItem =
            getElement("{\"element\":\"#" + navlistId + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" +
                       itemToClick + "\"}");
        WebElement clickableDiv = listItem.findElement(By.tagName("div"));

        Actions action = new Actions(this.getWebDriver());
        action.moveToElement(clickableDiv).click().perform();
        waitForMilliseconds(1000);
    }

    //used for sliding
    public void navigateBackByClickingOnPreviousLink(String navlistId) {

        WebElement prevLink =
            getElement("{\"element\":\"#" + navlistId + "\",\"subId\":\"oj-navigationlist-previous-link\"}");
        prevLink.click();
    }

    //used for sliding
    public void navigateBackByClickingOnHierarchicalMenuItem(String navlistId,
                                                             String menuItem) {
        //check the drop down menu
        WebElement hierarchicalMenuBtn =
            getElement("{\"element\":\"#" + navlistId + "\",\"subId\":\"oj-navigationlist-hierarchical-button\"}");
        hierarchicalMenuBtn.click();

        WebElement hierarchicalMenu =
            getElement("{\"element\":\"#" + navlistId + "\",\"subId\":\"oj-navigationlist-hierarchical-menu\"}");
        List<WebElement> menuitems = hierarchicalMenu.findElements(By.tagName("li"));

        for (int i = 0; i < menuitems.size(); i++) {
            WebElement anchor = menuitems.get(i).findElement(By.tagName("a"));
            String item = anchor.findElement(By.className("oj-navigationlist-hierarchical-menu-label")).getText();
            if (item.equals(menuItem)) {
                anchor.click();
                break;
            }
        }
    }

    //used for none, collapsible and sliding
    public void selectListItem(String navlistId, String item) {
        WebElement listItem =
            getElement("{\"element\":\"#" + navlistId + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" + item +
                       "\"}");

        listItem.click();

    }

    //used for  sliding
    public void changeRootLabel(String label) {
        WebElement inputRootLabel = getElement("id=rootlabel");
        //    Actions actions = new Actions(getWebDriver());
        //    actions.moveToElement(inputRootLabel).click().perform();
        inputRootLabel.sendKeys(label);
        inputRootLabel.sendKeys(Keys.TAB);
        WebElement refreshBtn = getElement("id=refresh1");
        refreshBtn.click();
    }

    //used for none, collapsible and sliding
    public void verifyItemIsDisabled(String navlistId, String item) {
        WebElement listItem =
            getElement("{\"element\":\"#" + navlistId + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" + item +
                       "\"}");
        String classes = listItem.getAttribute("class");

        //assert its disabled
        boolean isDisabled = classes.indexOf("oj-disabled") > -1;
        Assert.assertTrue(isDisabled);

    }

    //used for  sliding
    public void verifyHeading(String navlistId, String headingVal) {
        //check the list heading
        WebElement navlist = getElement("id=" + navlistId);
        WebElement headingElem = navlist.findElement(By.className("oj-navigationlist-current-header"));
        Assert.assertEquals(headingElem.getText(), headingVal);
    }

    //used for collapsible and sliding
    public void verifyListItems(String navlistId, String[] expectedChildListItems, String keyOfSelectedItem) {

        //Verify
        //1. NaviList  items
        //2. No item is selected

        for (String item : expectedChildListItems) {
            boolean isPresent =
                this.isElementPresent("{\"element\":\"#" + navlistId +
                                      "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" + item + "\"}");

        }

        for (String item : expectedChildListItems) {

            boolean isPresent =
                this.isElementPresent("{\"element\":\"#" + navlistId +
                                      "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" + item + "\"}");

            Assert.assertTrue(isPresent);

            if (isPresent) {
                WebElement listItem =
                    getElement("{\"element\":\"#" + navlistId + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" +
                               item + "\"}");
                String classes;
                boolean isSelected;

                classes = listItem.getAttribute("class");

                //assert its not selected
                isSelected = classes.indexOf("oj-selected") > -1;
                if (item.equals(keyOfSelectedItem)) {
                    Assert.assertTrue(isSelected);
                } else {
                    Assert.assertFalse(isSelected);
                }
            } //if
        }

    }

    //used for none, collapsible and sliding
    public void verifyItemIsSelected(String navlistId, String item) {
        WebElement listItem =
            getElement("{\"element\":\"#" + navlistId + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" + item +
                       "\"}");
        String classes;
        boolean isSelected;

        classes = listItem.getAttribute("class");

        //assert its  selected
        isSelected = classes.indexOf("oj-selected") > -1;

        Assert.assertTrue(isSelected);


    }

    //used for none, collapsible and sliding
    public void verifyItemIsNotSelected(String navlistId, String item) {
        WebElement listItem =
            getElement("{\"element\":\"#" + navlistId + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" + item +
                       "\"}");
        String classes;
        boolean isSelected;

        classes = listItem.getAttribute("class");

        //assert its  selected
        isSelected = classes.indexOf("oj-selected") > -1;

        Assert.assertFalse(isSelected);


    }

    //used for none, collapsible and sliding
    public void verifyItemsAreNotSelected(String navlistId, String[] items) {
        for (String item : items) {
            WebElement listItem =
                getElement("{\"element\":\"#" + navlistId + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" +
                           item + "\"}");
            String classes;
            boolean isSelected;

            classes = listItem.getAttribute("class");

            //assert its  selected
            isSelected = classes.indexOf("oj-selected") > -1;

            Assert.assertFalse(isSelected);

        }
    }

    //used for none, collapsible and sliding
    public void verifySelectedValue(String selExpectedVal) {
        WebElement selItemBtn = getElement("id=getsel");
        selItemBtn.click();
        WebElement selItemResult = getElement("id=results_s");
        //String selExpectedVal = "Option Value: null Observable Value: null";
        Assert.assertEquals(selItemResult.getText(), selExpectedVal);

    }

    //used for none, collapsible and sliding
    public void verifyGetExpandedMethodReturnValue(String expectedVal) {
        WebElement btn = getElement("id=getexpanded");
        btn.click();
        WebElement result = getElement("id=results_e");
        //String selExpectedVal = "Option Value: null Observable Value: null";
        Assert.assertEquals(result.getText(), expectedVal);

    }

    //utility method for sliding drill mode
    public void navigateAndVerifyChildItems(String navlistId, String itemToClick, String headingVal,
                                            String[] expectedChildListItems, String[] expectedHierarchicalMenuItems) {

        navigate(navlistId, itemToClick);
        verifyHeading(navlistId, headingVal);
        verifyHierarchicalMenu(navlistId, expectedHierarchicalMenuItems);
        verifyItemsAreNotSelected(navlistId, expectedChildListItems);

        //and check the values of currentItem  option
        // WebElement currItemBtn = getElement("id=getCurr");
        // currItemBtn.click();
        //  WebElement currItemResult = getElement("id=results_c");
        // String currExpectedVal = "Option Value: disk Observable Value: undefined";
        //  Assert.assertEquals(currItemResult.getText(), currExpectedVal);

        // check the values of  selected options
        //NOTE that Parent node cannot be selected because of the navlist markup setting [item:{selectable: itemOnly}]
        WebElement selItemBtn = getElement("id=getsel");
        selItemBtn.click();
        WebElement selItemResult = getElement("id=results_s");
        String selExpectedVal = "Option Value: null Observable Value: null";
        Assert.assertEquals(selItemResult.getText(), selExpectedVal);

    }

    //used for  sliding
    public void verifyHierarchicalMenu(String navlistId,
                                       String[] expectedHierarchicalMenuItems) {
        //check the drop down menu
        WebElement hierarchicalMenuBtn =
            getElement("{\"element\":\"#" + navlistId + "\",\"subId\":\"oj-navigationlist-hierarchical-button\"}");
        hierarchicalMenuBtn.click();

        WebElement hierarchicalMenu =
            getElement("{\"element\":\"#" + navlistId + "\",\"subId\":\"oj-navigationlist-hierarchical-menu\"}");
        List<WebElement> menuitems = hierarchicalMenu.findElements(By.tagName("li"));
        //verify # of items in the list
        Assert.assertEquals(menuitems.size(), expectedHierarchicalMenuItems.length);
        for (int i = 0; i < expectedHierarchicalMenuItems.length; i++) {
            WebElement anchor = menuitems.get(i).findElement(By.tagName("a"));
            Assert.assertEquals(anchor.findElement(By.className("oj-navigationlist-hierarchical-menu-label")).getText(),
                                expectedHierarchicalMenuItems[i]);
        }
    }

    //used for none, collapsible and sliding
    public void changeDrillModeToCollapsible() {
        String radioBtnId = "drillMode";
        //List<WebElement> btns = findElements("{\"element\":\"#" + radioBtnId + "\",\"subId\":\"oj-radioset-inputs\"}");
        WebElement btn = getElement("id=collapsibleOpt");
        btn.click();

    }

    //used for none, collapsible and sliding
    public void changeDrillModeToNone() {
        String radioBtnId = "drillMode";
        WebElement btn = getElement("id=expandOpt");
        btn.click();

    }

    //used for none, collapsible and sliding
    public void verifyDrillMode(String navlistId, String expectedDrillMode) {

        String drillMode = evalJavascript("return $('#" + navlistId + "').ojNavigationList('option', 'drillMode')");
        Assert.assertEquals(drillMode, expectedDrillMode);

    }

    //used for list with drillmode = none
    public void verifySubListItemsInExpandedList(String navlistId, String parentItem, String[] expectedSubList) {
        WebElement listItem =
            getElement("{\"element\":\"#" + navlistId + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" +
                       parentItem + "\"}");

        WebElement sublist = listItem.findElement(By.tagName("ul"));
        String styleValue = sublist.getAttribute("style");
        boolean isDisplayed = styleValue.equals("display: block;");
        Assert.assertTrue(isDisplayed);

        //Verify the number of sublist items
        List<WebElement> sublistitems = sublist.findElements(By.xpath("./li"));
        Assert.assertEquals(sublistitems.size(), expectedSubList.length);

        verifyListItems(navlistId, expectedSubList, null);

    }

    //used for none drillmode only
    public WebElement getParentElementInExpandedList(String navlistId, String parentItemId) {

        WebElement listItem =
            getElement("{\"element\":\"#" + navlistId + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" +
                       parentItemId + "\"}");
        WebElement clickableDiv = listItem.findElement(By.tagName("div"));

        return clickableDiv;

    }
    
    //UTILTIY METHODS FOR tableDataSource_OA_template.html
    
    public void verifyContentsOfEachListItem(String navlistId, String[] navlistItems, String[] navlistNames) {
        int i = 0;
        for (String item : navlistItems) {
            WebElement listItem =
                getElement("{\"element\":\"#" + navlistId + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" +
                           item + "\"}");
            WebElement spanElem = listItem.findElement(By.className("oj-navigationlist-item-label"));
            List<WebElement> contentElements = spanElem.findElements(By.xpath("./*"));
            Assert.assertEquals(contentElements.size(), 5);

            //First element is the icon
            boolean icon = contentElements.get(0).getAttribute("class").indexOf("oj-navigationlist-item-icon") > -1;
            Assert.assertTrue(icon);

            //second element is link with certaion text
            String linkText = contentElements.get(1).getText().trim();
            Assert.assertEquals(linkText, navlistNames[i]);
            //third is dvt
            boolean dvt = contentElements.get(2).getAttribute("class").indexOf("oj-ratinggauge") > -1;
            Assert.assertTrue(dvt);
            //fourth label
            boolean label = contentElements.get(3).getAttribute("class").indexOf("oj-inputtext-label") > -1;
            Assert.assertTrue(label);
            //fifth is input text
            boolean input = contentElements.get(4).getAttribute("class").indexOf("oj-inputtext") > -1;
            Assert.assertTrue(input);
            i++;

        }
    }
    
    public void selectListItemWithMultipleChildren(String navlistId, String item) {
        WebElement listItem =
            getElement("{\"element\":\"#" + navlistId + "\",\"subId\":\"oj-navigationlist-item\",\"key\":\"" + item +
                       "\"}");

        WebElement spanElem = listItem.findElement(By.className("oj-navigationlist-item-label"));
        List<WebElement> contentElements = spanElem.findElements(By.xpath("./*"));
        WebElement dvtCompoent = contentElements.get(0);
        dvtCompoent.click();

    }
}
