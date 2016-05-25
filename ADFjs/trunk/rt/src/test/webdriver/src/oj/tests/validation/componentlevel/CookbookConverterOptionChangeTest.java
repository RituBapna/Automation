package oj.tests.validation.componentlevel;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;


public class CookbookConverterOptionChangeTest extends OJetBase {
    public CookbookConverterOptionChangeTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook" , "validation"})
    public void testConverteroptionChangeValidVal() throws Exception {
        startupTest("demo-validationUsecases-converterOption.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Converter Option Changes");
        waitForElementVisible("id=birthdate");
        //refresh the page
        getWebDriver().navigate().refresh();

        //set bithdate to 12-12-12
        Actions actions = new Actions(getWebDriver());
        WebElement birthdate = getElement("id=birthdate");
        birthdate.sendKeys("12-12-12");
        birthdate.sendKeys(Keys.TAB);

        //Verify that date is set to 12/12/12
        String setVal = birthdate.getAttribute("value");
        Assert.assertEquals(setVal, "12/12/12", "date value is in short format");

        //placeholder value is m/d/yy
        String placeholder = birthdate.getAttribute("placeholder");
        Assert.assertEquals(placeholder, "m/d/yy", "placeholder value is in date short format");

        //Verify the view model value
        WebElement val = getElement("id=val");
        Assert.assertEquals(val.getText(), "[Component Value: 2012-12-12]", "view model is updated");

        // Click on Change Concerter buutton
        WebElement changeDC = getElement("id=changeDC");
        changeDC.click();

        //Verify that placeholder and value changes as per the converter
        setVal = birthdate.getAttribute("value");
        Assert.assertEquals(setVal, "December 12, 2012", "date value is in long format");

        placeholder = birthdate.getAttribute("placeholder");
        Assert.assertEquals(placeholder, "mmmm d, y", "placeholder value is in date long format");

        val = getElement("id=val");
        Assert.assertEquals(val.getText(), "[Component Value: 2012-12-12]", "view model value is still te same");
    }

    @Test(groups = { "cookbook", "validation" })
    public void testConverterOptionChangeWithDeferredErr() throws Exception {
        startupTest("demo-validationUsecases-converterOption.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Converter Option Changes");
        waitForElementVisible("id=birthdate");
        //refresh the page
        getWebDriver().navigate().refresh();

        //Verify that birthdate has deferred err
        Actions actions = new Actions(getWebDriver());
        WebElement status = getElement("id=status");
        String style = status.getAttribute("style");
        boolean isDisplayed = style.indexOf("inline") > -1;
        Assert.assertTrue(isDisplayed, "birthdate field has deferred validation err");

        //Click on change Converter Option button
        WebElement changeDC = getElement("id=changeDC");
        changeDC.click();

        //Verify that deferred err is still displayed
        style = status.getAttribute("style");
        isDisplayed = style.indexOf("inline") > -1;
        Assert.assertTrue(isDisplayed, "birthdate field has deferred validation err");


    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testConverteroptionChangeInvalidVal() throws Exception {
        startupTest("demo-validationUsecases-converterOption.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Converter Option Changes");
        waitForElementVisible("id=birthdate");
        //refresh the page
        getWebDriver().navigate().refresh();

        //set bithdate to today
        Actions actions = new Actions(getWebDriver());
        WebElement birthdate = getElement("id=birthdate");
        birthdate.sendKeys("today");
        birthdate.sendKeys(Keys.TAB);

        //Verify the validation error
              
        actions.moveToElement(birthdate).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(birthdate, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value in the same format as this example: '11/29/98'");
        
        // Click on Change Concerter buutton
        WebElement changeDC = getElement("id=changeDC");
        changeDC.click();

        //Verify that birthdate still have error but the date format in msg is different
        birthdate.sendKeys(Keys.TAB);
        actions.moveToElement(birthdate).click().perform();
         msg = getMessagingContentNodeBySubId(birthdate, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value in the same format as this example: 'November 29, 1998'");
        
       
    }

}
