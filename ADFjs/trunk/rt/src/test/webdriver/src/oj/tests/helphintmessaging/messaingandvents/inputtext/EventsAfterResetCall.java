package oj.tests.helphintmessaging.messaingandvents.inputtext;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

public class EventsAfterResetCall extends OJetBase {
	public EventsAfterResetCall() {
		super(new TestConfigBuilder().setContextRoot("helphintmessaging").setAppRoot("").build());
	}

	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterResetCall_withDeferredMsg() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Click on validate btn
		WebElement resetBtn = getElement("id=text2Reset");
		resetBtn.click();

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");

		System.out.println("####### optionChange Log: " + text2EventLog);

		boolean forMessagesHidden = text2EventLog.indexOf(
				"OptionChange Event for : messagesHidden, previous Value lenth: 1,  current Value length: 0") > -1;
		boolean messagesHiddenPrevValue = text2EventLog.indexOf(
				"messagesHidden Previous Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
		boolean messagesHiddenCurValue = text2EventLog.indexOf("messagesHidden Current Value:") > -1;
		boolean forMessagesHidden2 = text2EventLog.indexOf(
				"OptionChange Event for : messagesHidden, previous Value lenth: 1,  current Value length: 1") > -1;
		boolean messagesHiddenPrevValue2 = text2EventLog.indexOf(
				"messagesHidden Previous Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
		boolean messagesHiddenCurValue2 = text2EventLog.indexOf(
				"messagesHidden Current Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;

		Assert.assertTrue(forMessagesHidden, "ojoptionchange event not raised for messageHidden option");
		Assert.assertTrue(messagesHiddenPrevValue, "messageHidden previous value is not correct.");
		Assert.assertTrue(messagesHiddenCurValue, "messageHidden current value is not correct.");
		Assert.assertTrue(forMessagesHidden2, "ojoptionchange event not raised for messageHidden option");
		Assert.assertTrue(messagesHiddenPrevValue2, "messageHidden previous value is not correct.");
		Assert.assertTrue(messagesHiddenCurValue2, "messageHidden current value is not correct.");

	}

	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterResetCall_withoutDeferredMsg() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Click on showMessages btn - to clear the deffered msgs
		WebElement showMsgBtn = getElement("id=text2show");
		showMsgBtn.click();

		// Clear the log after showMessages of deffered messages
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Click on reset btn
		WebElement resetBtn = getElement("id=text2Reset");
		resetBtn.click();
		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");

		System.out.println("####### optionChange Log: " + text2EventLog);

		boolean forMessagesShown = text2EventLog.indexOf(
				"OptionChange Event for : messagesShown, previous Value lenth: 1,  current Value length: 0") > -1;
		boolean messagesShownPrevValue = text2EventLog.indexOf(
				"messagesShown Previous Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
		boolean messagesShownCurValue = text2EventLog.indexOf(
				"messagesShown Current Value:") > -1;
		boolean forMessagesHidden = text2EventLog.indexOf(
				"OptionChange Event for : messagesHidden, previous Value lenth: 1,  current Value length: 1") > -1;
		boolean messagesHiddenPrevValue = text2EventLog.indexOf(
				"messagesHidden Previous Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
		boolean messagesHiddenCurValue = text2EventLog.indexOf(
				"messagesHidden Current Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;

		boolean forRawValue = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: undefined,  current Value:") > -1;

		Assert.assertTrue(forMessagesShown, "ojoptionchange event not raised for messageShown option");
		Assert.assertTrue(messagesShownPrevValue, "messageShown previous value is not correct.");
		Assert.assertTrue(messagesShownCurValue, "messageShown current value is not correct.");
		Assert.assertTrue(forMessagesHidden, "ojoptionchange event not raised for messageShown option");
		Assert.assertTrue(messagesHiddenPrevValue, "messageShown previous value is not correct.");
		Assert.assertTrue(messagesHiddenCurValue, "messageShown current value is not correct.");
		Assert.assertTrue(forRawValue, "ojrawvalue event not raised.");

	}

	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterResetCall_withValidValue() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Click on enter Valid Value btn
		WebElement setValidValBtn = getElement("id=text2SetV");
		setValidValBtn.click();

		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Click on showMessages btn
		WebElement resetBtn = getElement("id=text2Reset");
		resetBtn.click();

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");

		System.out.println("####### optionChange Log: " + text2EventLog);

		Assert.assertEquals(text2EventLog, "", "No  event should be raised. But found events in log");

	}

	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterResetCall_withInvalidValue() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Enter invalid Value
		WebElement input = getElement("id=text2");
		input.sendKeys("xx");
		input.sendKeys(Keys.TAB);

		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Click on Reset btn
		WebElement resetBtn = getElement("id=text2Reset");
		resetBtn.click();

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");

		System.out.println("####### optionChange Log: " + text2EventLog);

		boolean forMessagesShown = text2EventLog.indexOf(
				"OptionChange Event for : messagesShown, previous Value lenth: 1,  current Value length: 0") > -1;
		boolean messagesShownPrevValue = text2EventLog.indexOf(
				"messagesShown Previous Value: [0: severity: 4, summary: 'Text 2:' too Weak, detail: You must enter a password that meets our minimum security requirements.") > -1;
		boolean messagesShownCurValue = text2EventLog.indexOf("messagesShown Current Value:") > -1;
		boolean forMessagesHidden = text2EventLog.indexOf(
				"OptionChange Event for : messagesHidden, previous Value lenth: 1,  current Value length: 1") > -1;
		boolean messagesHiddenPrevValue = text2EventLog.indexOf(
				"messagesHidden Previous Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
		boolean messagesHiddenCurValue = text2EventLog.indexOf(
				"messagesHidden Current Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
		boolean forRawValue = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: xx,  current Value:") > -1;

		Assert.assertTrue(forMessagesShown, "ojoptionchange event not raised for messageHidden option");
		Assert.assertTrue(messagesShownPrevValue, "messageHidden previous value is not correct.");
		Assert.assertTrue(messagesShownCurValue, "messageHidden current value is not correct.");
		Assert.assertTrue(forMessagesHidden, "ojoptionchange event not raised for messageHidden option");
		Assert.assertTrue(messagesHiddenPrevValue, "messageHidden previous value is not correct.");
		Assert.assertTrue(messagesHiddenCurValue, "messageHidden current value is not correct.");
		Assert.assertTrue(forRawValue, "rawValue  event not raised");

	}

	// Field has validation error and the custom Err messages. Reset is called
	// on it
	@Test(groups = { "cookbook", "messaging" })
	public void testEventsWhenResetCalled_customErrMsgAndFieldWithInvalidValue() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Enter valid Value
		WebElement input = getElement("id=text2");
		input.sendKeys("Hello");
		input.sendKeys(Keys.TAB);

		// Enter Button To add custom msg
		WebElement setCustomErrBtn = getElement("id=btn2111");
		setCustomErrBtn.click();

		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Click on Reset btn
		WebElement resetBtn = getElement("id=text2Reset");
		resetBtn.click();

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");
		System.out.println("####### optionChange Log: " + text2EventLog);

		// Log messages
		boolean forMessagesCustom = text2EventLog.indexOf(
				"OptionChange Event for : messagesCustom, previous Value lenth: 2,  current Value length: 0") > -1;
		boolean messagesCustomPrevValue = text2EventLog.indexOf(
				"messagesCustom Previous Value: [0: severity: 4, summary: custom Error Summary : 1, detail: custom Error Detail : 1, [1: severity: 2, summary: custom Info Summary : 2, detail: custom Info Detail : 2") > -1;
		boolean messagesCustomCurValue = text2EventLog.indexOf("messagesCustom Current Value:") > -1;

		boolean forMessagesShown = text2EventLog.indexOf(
				"OptionChange Event for : messagesShown, previous Value lenth: 3,  current Value length: 0") > -1;
		boolean messagesShownPrevValue = text2EventLog.indexOf(
				"messagesShown Previous Value: [0: severity: 4, summary: 'Text 2:' too Weak, detail: You must enter a password that meets our minimum security requirements., [1: severity: 4, summary: custom Error Summary : 1, detail: custom Error Detail : 1, [2: severity: 2, summary: custom Info Summary : 2, detail: custom Info Detail : 2") > -1;
		boolean messagesShownCurValue = text2EventLog.indexOf("messagesShown Current Value:") > -1;

		boolean forMessagesHidden = text2EventLog.indexOf(
				"OptionChange Event for : messagesHidden, previous Value lenth: 1,  current Value length: 1") > -1;
		boolean messagesHiddenPrevValue = text2EventLog.indexOf(
				"messagesHidden Previous Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
		boolean messagesHiddenCurValue = text2EventLog.indexOf(
				"messagesHidden Current Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;

		boolean forRawValue = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: Hello,  current Value:") > -1;

		boolean forValue = text2EventLog.indexOf("OptionChange Event for : value") > -1;

		Assert.assertTrue(forMessagesCustom, "ojoptionchange event not raised for messagesCustom option");
		Assert.assertTrue(messagesCustomPrevValue, "messagesCustom previous value is not correct.");
		Assert.assertTrue(messagesCustomCurValue, "messagesCustom current value is not correct.");
		Assert.assertTrue(forMessagesShown, "ojoptionchange event not raised for messagesShown option");
		Assert.assertTrue(messagesShownPrevValue, "messagesShown previous value is not correct.");
		Assert.assertTrue(messagesShownCurValue, "messagesShown current value is not correct.");
		Assert.assertTrue(forMessagesHidden, "ojoptionchange event not raised for messagesHidden option");
		Assert.assertTrue(messagesHiddenPrevValue, "messagesHidden previous value is not correct.");
		Assert.assertTrue(messagesHiddenCurValue, "messagesHidden current value is not correct.");
		Assert.assertTrue(forRawValue, "rawValue current value is not correct.");
		Assert.assertFalse(forValue, "ojvalue is raised when it should not be.");

	}

}
