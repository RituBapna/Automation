package oj.tests.helphintmessaging.messaingandvents.inputtext;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EventsAfterInitialPageRender extends OJetBase {
    public EventsAfterInitialPageRender() {
        super(new TestConfigBuilder().setContextRoot("helphintmessaging").setAppRoot("").build());
    }

    @Test(groups = { "cookbook" , "messaging"})
    /*
        *  Test how checkboxset component respond when multiple messages of different severities are set using the messages options
        *  The severity of messages from most to least severe are 'Fatal', 'Error', 'Warning', 'Info', 'Confirmation'.
        *  The message with the highest severity determines the marker style applied on the component.
        *  Messages are listed in order going from most severe to the least.
        */
    public void testEventOnIntialPageRender_withDeferredMsg() throws Exception {

        startupTest("messagingTestPage.html", null);

        verifyTitle("Incorrect page title;", "JET Messaging tests");
        waitForElementVisible("id=btn2");
          waitForMilliseconds(10000);

        //Get the log Window data
        String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");
       
        System.out.println("####### optionChange Log: " + text2EventLog);

        boolean forPlacefolder =
            text2EventLog.indexOf("OptionChange Event for : placeholder, previous Value: undefined,  current Value: Some Place holder Value") > -1;
         boolean forRequired =
            text2EventLog.indexOf("OptionChange Event for : required, previous Value: false,  current Value: true") > -1;
         boolean forTitle =
            text2EventLog.indexOf("OptionChange Event for : title, previous Value: ,  current Value: <html><b>My title value</b></html>") > -1;
         boolean forMessagesHidden =
            text2EventLog.indexOf("OptionChange Event for : messagesHidden, previous Value lenth: 1,  current Value length: 1") > -1;
         boolean messagesHiddenPrevValue =
            text2EventLog.indexOf("messagesHidden Previous Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
         boolean messagesHiddenCurValue =
            text2EventLog.indexOf("messagesHidden Current Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
        
        Assert.assertTrue(forPlacefolder,
                          "ojoptionchange event not raised for PlaceHolder option");
        Assert.assertTrue(forRequired,
                          "ojoptionchange event not raised for required option");

        Assert.assertTrue(forTitle,
                          "ojoptionchange event not raised for Title option");
        Assert.assertTrue(forMessagesHidden,
                          "ojoptionchange event not raised for messageHidden option");

        Assert.assertTrue(messagesHiddenPrevValue,
                          "messageHidden previous value is not correct.");
        Assert.assertTrue(messagesHiddenCurValue,
                          "messageHidden current value is not correct.");

    }

   

}
