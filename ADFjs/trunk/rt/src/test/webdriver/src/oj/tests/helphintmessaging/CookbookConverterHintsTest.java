package oj.tests.helphintmessaging;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;


   
    public class CookbookConverterHintsTest extends OJetBase {
        public CookbookConverterHintsTest() {
            super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
        }

        @Test(groups = { "cookbook", "messaging" })
        public void testConverterHintAsPlaceholder_date() throws Exception {
            startupTest("demo-helpHintsMessaging-converterHintMessages.html", null);
            verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Converter Hints and Messages");
            waitForElementVisible("id=date10");
            
            //test inputdate has converter hint as placeholder
            WebElement date = getElement("id=date10");
            String placeholder = date.getAttribute("placeholder");
            Assert.assertEquals(placeholder, "mm/dd/yy", "converter hint as placeholder");
            
            //test title in note window
            Actions actions = new Actions(getWebDriver());
            actions.moveToElement(date).click().perform();
            WebElement msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-title", 0);
            Assert.assertEquals(msg.getText().trim(), "enter a date in your preferred format and we will attempt to figure it out", "title is displayed in note window");
          
        }
    @Test(groups = { "cookbook", "messaging" })
    public void testConverterHintAsPlaceholder_datetime() throws Exception {
        startupTest("demo-helpHintsMessaging-converterHintMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Converter Hints and Messages");
        waitForElementVisible("id=date11");
        
        //test inputdatetime has converter hint as placeholder
        WebElement date = getElement("id=date11");
        String placeholder = date.getAttribute("placeholder");
        Assert.assertEquals(placeholder, "mm/dd/yy hh:mm a", "converter hint as placeholder");
        
        //test title in note window
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(date).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(), "enter a datetime in your preferred format and we will attempt to figure it out", "title is displayed in note window");
      
    }
    @Test(groups = { "cookbook", "messaging" })
    public void testConverterHintAsPlaceholder_inputtext() throws Exception {
        startupTest("demo-helpHintsMessaging-converterHintMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Converter Hints and Messages");
        waitForElementVisible("id=date12");
        
        //test inputtext has converter hint as placeholder
        WebElement date = getElement("id=date12");
        String placeholder = date.getAttribute("placeholder");
        Assert.assertEquals(placeholder, "h:mm a", "converter hint as placeholder");
        
        //test title in note window
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(date).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(), "enter the time in hours, minutes", "title is displayed in note window");
      
    }
    
    @Test(groups = { "cookbook", "messaging" })
    public void testConverterHintAsPlaceholder_inputnumber() throws Exception {
        startupTest("demo-helpHintsMessaging-converterHintMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Converter Hints and Messages");
        waitForElementVisible("id=currency13");
        
        //test inputnumber does not display converter hint as placeholder
        //By design
        
        /*
        WebElement date = getElement("id=currency13");
        String placeholder = date.getAttribute("placeholder");
        Assert.assertEquals(placeholder, "h:mm a", "converter hint as placeholder");
        */
        //test title in note window
        WebElement currency = getElement("id=currency13");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(currency).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(currency, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(), "enter an amount with or without grouping separator", "title is displayed in note window");
      
    }

}
