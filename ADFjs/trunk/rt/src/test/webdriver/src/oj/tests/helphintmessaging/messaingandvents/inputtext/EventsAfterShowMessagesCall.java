package oj.tests.helphintmessaging.messaingandvents.inputtext;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EventsAfterShowMessagesCall extends OJetBase {
	public EventsAfterShowMessagesCall() {
		super(new TestConfigBuilder().setContextRoot("helphintmessaging").setAppRoot("").build());
	}

	@Test(groups = { "cookbook", "messaging" })

	public void testEventsAfterShowMessagesCall_withDeferredMsg() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Click on showMessages btn
		WebElement showMsgBtn = getElement("id=text2show");
		showMsgBtn.click();

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");

		System.out.println("####### optionChange Log: " + text2EventLog);

		boolean forMessagesHidden = text2EventLog.indexOf(
				"OptionChange Event for : messagesHidden, previous Value lenth: 1,  current Value length: 0") > -1;
		boolean messagesHiddenPrevValue = text2EventLog.indexOf(
				"messagesHidden Previous Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
		boolean messagesHiddenCurValue = text2EventLog.indexOf("messagesHidden Current Value:") > -1;
		boolean forMessagesShown = text2EventLog.indexOf(
				"OptionChange Event for : messagesShown, previous Value lenth: 1,  current Value length: 1") > -1;
		boolean messagesShownPrevValue = text2EventLog.indexOf(
				"messagesShown Previous Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
		boolean messagesShownCurValue = text2EventLog.indexOf(
				"messagesShown Current Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;

		Assert.assertTrue(forMessagesHidden, "ojoptionchange event not raised for messageHidden option");
		Assert.assertTrue(messagesHiddenPrevValue, "messageHidden previous value is not correct.");
		Assert.assertTrue(messagesHiddenCurValue, "messageHidden current value is not correct.");
		Assert.assertTrue(forMessagesShown, "ojoptionchange event not raised for messageHidden option");
		Assert.assertTrue(messagesShownPrevValue, "messageHidden previous value is not correct.");
		Assert.assertTrue(messagesShownCurValue, "messageHidden current value is not correct.");

	}

	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterShowMessagesCall_withoutDeferredMsg() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Click on showMessages btn
		WebElement showMsgBtn = getElement("id=text2show");
		showMsgBtn.click();

		// Clear the log after showMessages of deffered messages
		clearLogBtn.click();

		// Call showMessages again
		showMsgBtn.click();

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");

		System.out.println("####### optionChange Log: " + text2EventLog);

		Assert.assertEquals(text2EventLog, "", "No optionChange event should be raised. But found events in log");

	}
	
	

}
