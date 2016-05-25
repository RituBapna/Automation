package oj.tests.helphintmessaging;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookCustomConverterHintTest extends OJetBase{
    public CookbookCustomConverterHintTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }
    
    @Test(groups = { "cookbook", "messaging" })
    public void testCustonConverterHint_date() throws Exception {
        startupTest("demo-helpHintsMessaging-converterHintMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Converter Hints and Messages");
        waitForElementVisible("id=date21");
        
        //test inputdate has custom converter hint as placeholder
        WebElement date = getElement("id=date21");
        String placeholder = date.getAttribute("placeholder");
        Assert.assertEquals(placeholder, "custom placeholder text", "converter hint as placeholder");
        
        //test converter hint and title are in note window
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(date).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-converter-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "mm/dd/yy", "converter hint is displayed in note window");
        
         msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(), "enter a date in your preferred format and we will attempt to figure it out", "title is displayed in note window");
        
        
    }
    
    @Test(groups = { "cookbook", "messaging" })
    public void testCustomConverterHint_inputtext() throws Exception {
        startupTest("demo-helpHintsMessaging-converterHintMessages.html", null);
        verifyTitle("Incorrect page title;", "Help, Hints and Messaging - Converter Hints and Messages");
        waitForElementVisible("id=date22");
        
        //test inputtext has converter hint as placeholder
        WebElement date = getElement("id=date22");
        String placeholder = date.getAttribute("placeholder");
        Assert.assertEquals(placeholder, "custom placeholder text", "converter hint as placeholder");
        
        //test title in note window
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(date).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-converter-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "h:mm a", "converter hint is displayed in note window");
        
         msg = getMessagingContentNodeBySubId(date, "oj-messaging-popup-title", 0);
        Assert.assertEquals(msg.getText().trim(), "enter the time in hours, minutes", "title is displayed in note window");
      
    }
    
}
