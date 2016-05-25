package oj.tests.helphintmessaging.messaingandvents.inputtext;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

public class EventsAfterValidateCall extends OJetBase {
	public EventsAfterValidateCall() {
		super(new TestConfigBuilder().setContextRoot("helphintmessaging").setAppRoot("").build());
	}

	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterValidateCall_withDeferredMsg() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Click on validate btn
		WebElement validateBtn = getElement("id=text2Validate");
		validateBtn.click();

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
	public void testEventsAfterValidateCall_withoutDeferredMsg() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Click on showMessages btn
		WebElement validateBtn = getElement("id=text2Validate");
		validateBtn.click();

		// Clear the log after showMessages of deffered messages
		clearLogBtn.click();

		// Call showMessages again
		validateBtn.click();

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");

		System.out.println("####### optionChange Log: " + text2EventLog);

		boolean forMessagesShown = text2EventLog.indexOf(
				"OptionChange Event for : messagesShown, previous Value lenth: 1,  current Value length: 0") > -1;
		boolean messagesShownPrevValue = text2EventLog.indexOf(
				"messagesShown Previous Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
		boolean messagesShownCurValue = text2EventLog.indexOf("messagesShown Current Value:") > -1;
		boolean forMessagesShown2 = text2EventLog.indexOf(
				"OptionChange Event for : messagesShown, previous Value lenth: 1,  current Value length: 1") > -1;
		boolean messagesShownPrevValue2 = text2EventLog.indexOf(
				"messagesShown Previous Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
		boolean messagesShownCurValue2 = text2EventLog.indexOf(
				"messagesShown Current Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;

		Assert.assertTrue(forMessagesShown, "ojoptionchange event not raised for messageHidden option");
		Assert.assertTrue(messagesShownPrevValue, "messageHidden previous value is not correct.");
		Assert.assertTrue(messagesShownCurValue, "messageHidden current value is not correct.");
		Assert.assertTrue(forMessagesShown2, "ojoptionchange event not raised for messageHidden option");
		Assert.assertTrue(messagesShownPrevValue2, "messageHidden previous value is not correct.");
		Assert.assertTrue(messagesShownCurValue2, "messageHidden current value is not correct.");

	}
	
	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterValidateCall_withErrorCustomMsg() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Enter valid Value
					WebElement input = getElement("id=text2");
					input.sendKeys("Hello1");
					input.sendKeys(Keys.TAB);
					
		// Enter Button To add custom msg
				WebElement setCustomErrBtn = getElement("id=btn2111");
				setCustomErrBtn.click();
				
		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Click on showMessages btn
		WebElement validateBtn = getElement("id=text2Validate");
		validateBtn.click();

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");

		System.out.println("####### optionChange Log: " + text2EventLog);

		boolean forMessagesShown = text2EventLog.indexOf(
				"OptionChange Event for : messagesShown, previous Value lenth: 2,  current Value length: 0") > -1;
		boolean messagesShownPrevValue = text2EventLog.indexOf(
				"messagesShown Previous Value: [0: severity: 4, summary: custom Error Summary : 1, detail: custom Error Detail : 1, [1: severity: 2, summary: custom Info Summary : 2, detail: custom Info Detail : 2") > -1;
		boolean messagesShownCurValue = text2EventLog.indexOf("messagesShown Current Value:") > -1;
		boolean forMessagesCustom = text2EventLog.indexOf(
				"OptionChange Event for : messagesCustom, previous Value lenth: 2,  current Value length: 0") > -1;
		boolean messagesCustomPrevValue = text2EventLog.indexOf(
				"messagesCustom Previous Value: [0: severity: 4, summary: custom Error Summary : 1, detail: custom Error Detail : 1, [1: severity: 2, summary: custom Info Summary : 2, detail: custom Info Detail : 2") > -1;
		boolean messagesCustomCurValue = text2EventLog.indexOf(
				"messagesCustom Current Value:") > -1;

		Assert.assertTrue(forMessagesShown, "ojoptionchange event not raised for messagesShown option");
		Assert.assertTrue(messagesShownPrevValue, "messageShown previous value is not correct.");
		Assert.assertTrue(messagesShownCurValue, "messageShown current value is not correct.");
		Assert.assertTrue(forMessagesCustom, "ojoptionchange event not raised for messageCustom option");
		Assert.assertTrue(messagesCustomPrevValue, "messageCustom previous value is not correct.");
		Assert.assertTrue(messagesCustomCurValue, "messageCustom current value is not correct.");

	}

}
