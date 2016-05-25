package oj.tests.validation.componentlevel;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

   
    public class CookbookRefreshMethodTest extends OJetBase {
        public CookbookRefreshMethodTest() {
            super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
        }

        @Test(groups = { "cookbook", "validation" })
        public void testRefreshAfterLabelChange() throws Exception {
            startupTest("demo-validationUsecases-refreshMethod.html", null);
            verifyTitle("Incorrect page title;", "Component Validation - Refresh Component");
            waitForElementVisible("id=username");
            //refresh the page
            getWebDriver().navigate().refresh();
            
            //Verify that label value for username
            WebElement label = getElement("id=lusername");
            Assert.assertEquals(label.getText(), "Username", "label value is 'Username'");
            
            //Verify that label has required indicator
           
            boolean isRequiredIconPresent = isElementPresent(By.className("oj-label-required-icon"));
            Assert.assertTrue(isRequiredIconPresent, "required indocator is present for username label");
            
       

            //Click on change label button.
            WebElement changeLabel = getElement("id=changeLabel");
            changeLabel.click();
            
            //           Verify that label value has changed
            Assert.assertEquals(label.getText(), "USERNAME", "label value is 'USERNAME'");
            
            //Verify that label still has required indicator
             
             isRequiredIconPresent = isElementPresent(By.className("oj-label-required-icon"));
            Assert.assertTrue(isRequiredIconPresent, "required indocator is present for username label");
            
       

        }
        
    @Test(groups = { "cookbook", "validation" })
    public void testRefreshAfterInvalidValueAndLabelChange() throws Exception {
        startupTest("demo-validationUsecases-refreshMethod.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Refresh Component");
        waitForElementVisible("id=username");
        //refresh the page
        getWebDriver().navigate().refresh();
        
        //Enter invalid valie in username and tab off
        WebElement username = getElement("id=username");
        username.sendKeys("a");
        username.sendKeys(Keys.TAB);
        
        //Verify that error is displayed for username
        WebElement parentElem = getParentElement(username);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "validation error theme is applied");

        //Verify the validator error, Note that the label value is used in the err
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(username).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(username, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Username field requires at least 3 characters.");

       
        //Click on change label button.
        WebElement changeLabel = getElement("id=changeLabel");
        changeLabel.click();
        
        //Verify that error is still displayed but the label name in the error msg has changed
        username.sendKeys(Keys.TAB);
        actions.moveToElement(username).click().perform();
         msg = getMessagingContentNodeBySubId(username, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "USERNAME field requires at least 3 characters.");

    

    }

}
