package oj.tests.helphintmessaging;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;


public class CookbookHelpTitleTest extends OJetBase {
    public CookbookHelpTitleTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook" , "messaging"})
    public void testHelp() throws Exception {
        startupTest("demo-helpHintsMessaging-helpTitle.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Help and Title");
        waitForElementVisible("id=text10");
        // test help option with both definition and source
        //definition is present
        WebElement text10Label = getElement("id=ltext10");
        String definition = text10Label.getAttribute("title");
        Assert.assertEquals(definition, "custom help text", "definition is present");
        String classes = text10Label.getAttribute("class");
        boolean isCorrectClassPresent = classes.indexOf("oj-label-help-def") > -1;
        Assert.assertTrue(isCorrectClassPresent, "label has correct class defined for help definition");
        //source is present
        boolean helpIconPresent = isElementPresent("id=text10Icons");
        Assert.assertTrue(helpIconPresent, "help icon parent element is present");
        WebElement helpIconParent = getElement("id=text10Icons");
        boolean isSourceAchorPresent = isChildElementPresent(helpIconParent, By.className("oj-label-help-icon-anchor"));
        Assert.assertTrue(isSourceAchorPresent, "achor element is present for help source");
        WebElement sourceAchor = helpIconParent.findElement(By.className("oj-label-help-icon-anchor"));
        classes = sourceAchor.getAttribute("class");
        boolean isSourceHelpIconPresent = classes.indexOf("oj-label-help-icon") > -1;
        Assert.assertTrue(isSourceHelpIconPresent, "help icon element is present for help source");


        //test help option with only source
        //definition not present
        WebElement text11Label = getElement("id=ltext11");
        definition = text11Label.getAttribute("title");
        Assert.assertEquals(definition, "", "definition is not present");
        classes = text11Label.getAttribute("class");
        isCorrectClassPresent = classes.indexOf("oj-label-help-def") > -1;
        Assert.assertFalse(isCorrectClassPresent, "label does not have class defined for help definition");
        //source is present
        helpIconPresent = isElementPresent("id=text11Icons");
        Assert.assertTrue(helpIconPresent, "help icon parent element is present");
        helpIconParent = getElement("id=text11Icons");
         isSourceAchorPresent = isChildElementPresent(helpIconParent, By.className("oj-label-help-icon-anchor"));
        Assert.assertTrue(isSourceAchorPresent, "achor element is present for help source");
         sourceAchor = helpIconParent.findElement(By.className("oj-label-help-icon-anchor"));
        classes = sourceAchor.getAttribute("class");
         isSourceHelpIconPresent = classes.indexOf("oj-label-help-icon") > -1;
        Assert.assertTrue(isSourceHelpIconPresent, "help icon element is present for help source");


        //test help option with only definition
        //definition is present
        WebElement text12Label = getElement("id=ltext12");
        definition = text12Label.getAttribute("title");
        Assert.assertEquals(definition, "custom help text", "definition is present");
        classes = text12Label.getAttribute("class");
        isCorrectClassPresent = classes.indexOf("oj-label-help-def") > -1;
        Assert.assertTrue(isCorrectClassPresent, "label has correct class defined for help definition");
        //source is not present
         helpIconPresent = isElementPresent("id=text12Icons");
        Assert.assertTrue(helpIconPresent, "help icon  is not present");
    }

    @Test(groups = { "cookbook", "messaging" })
    public void testTitle() throws Exception {
        startupTest("demo-helpHintsMessaging-helpTitle.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Help and Title");
        waitForElementVisible("id=text20");
       
       //Test title as attribute
        Actions actions = new Actions(getWebDriver());
        
        WebElement text20 = getElement("id=text20");
        actions.moveToElement(text20).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(text20, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(), "enter at least 3 alphanumeric characters");
        
        
        //Test title as option
        WebElement text21 = getElement("id=text21");
        actions.moveToElement(text21).click().perform();
        msg = getMessagingContentNodeBySubId(text21, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(), "enter at least 3 alphanumeric characters");
        
        //Test title with formated text
        
        WebElement text22 = getElement("id=text22");
        actions.moveToElement(text22).click().perform();
        msg = getMessagingContentNodeBySubId(text22, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(), "enter at least 3 alphanumeric characters");
    }

    public boolean isChildElementPresent(WebElement parent, By locator) {
        try {
            // Attempt a get. This will throw an exception if the element is not found.
            parent.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
