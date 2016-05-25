package oj.tests.validation.componentlevel;
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

public class CookbookShowMessagesComponentTest extends OJetBase {
    public CookbookShowMessagesComponentTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testDeferredOnComponentCreation() throws Exception {
        startupTest("demo-validationUsecases-showMessagesMethod.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Show Deferred Messages");
        waitForElementVisible("id=showMsgsBtn");
        
        //Click on Show Messages button
        WebElement showMsgsButton = getElement("id=showMsgsBtn");
        showMsgsButton.click();
        
        //Notice Username field does not display has a deferred error icon
        WebElement status = getElement("id=status");
        String style = status.getAttribute("style");
        boolean isDisplayed = style.indexOf("inline") > -1;
        Assert.assertFalse(isDisplayed, "deferred validation error indicator is not displayed");
        
        //Notice password field does not display has a deferred error icon
        WebElement pwdstatus = getElement("id=pwdstatus");
        String pwdstyle = pwdstatus.getAttribute("style");
        boolean isPwdDisplayed = pwdstyle.indexOf("inline") > -1;
        Assert.assertFalse(isPwdDisplayed, "deferred validation error indicator is not displayed");

        //Verify that  username field has error theme applied
        WebElement username = getElement("id=username");
        WebElement parentElem = getParentElement(username);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "validation error theme is applied");
        
        //Verify that  password field has error theme applied
        WebElement password = getElement("id=password");
        WebElement pwdParentElem = getParentElement(password);
        classes = pwdParentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "validation error theme is applied");
        
        //Move focus on username and note that validation error is displayed       
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(username).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(username, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value is required.");
        
        //Move focus on password and note that validation error is displayed       
        actions.moveToElement(password).click().perform();
        msg = getMessagingContentNodeBySubId(password, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value is required.");

      

    }
}
