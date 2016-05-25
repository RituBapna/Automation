package oj.tests.selection.combobox;

import java.util.List;

import org.testng.Assert;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import org.testng.annotations.Test;

public class CookbookMultiSelectTest extends OJetBase {
    public CookbookMultiSelectTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook" })
    public void testCustomValidator() throws Exception {
        startupTest("demo-combobox-multi.html", null);
        //verifyTitle("Incorrect page title;", "Combobox - Multi select");
        waitForElementVisible("id=curr-value");

        WebElement text = getElement("{\"element\":\"#combobox\",\"subId\":\"oj-combobox-input\"}");

        Actions actions = new Actions(getWebDriver());

        //verify that the current value of combobox
        //WebElement selectedValues = getElement("{\"element\":\"#combobox\",\"subId\":\"oj-combobox-selection\"}");
        WebElement selectedValue = getElement("id=curr-value");

        Assert.assertEquals(selectedValue.getText(), "[\"Chrome\",\"Safari\"]");
        //move focus to combobox field
        actions.moveToElement(text).click().perform();
        //set text value "inter" in combobox
        text.sendKeys("inter");
       // this.waitForMilliseconds(1000);
        WebElement resultSet = getElement("{\"element\":\"#combobox\",\"subId\":\"oj-combobox-results\"}");
        waitForMilliseconds(15000L); 
        //get the selectable elements
        List<WebElement> list = resultSet.findElements(By.className("oj-listbox-result-selectable"));
        WebElement item = list.get(0);
        actions.moveToElement(item).click().perform();
        selectedValue = getElement("id=curr-value");

        Assert.assertEquals(selectedValue.getText(), "[\"Chrome\",\"Safari\",\"Internet Explorer\"]");
    }
}
