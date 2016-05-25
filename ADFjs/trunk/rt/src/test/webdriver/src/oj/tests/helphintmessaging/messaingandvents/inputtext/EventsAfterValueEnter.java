package oj.tests.helphintmessaging.messaingandvents.inputtext;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

public class EventsAfterValueEnter extends OJetBase {
	public EventsAfterValueEnter() {
		super(new TestConfigBuilder().setContextRoot("helphintmessaging").setAppRoot("").build());
	}

	// Blank to valid value entered programmatically
	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterValidValue_enteredProgrammatically() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Enter valid Value
		WebElement setValidValBtn = getElement("id=text2SetV");
		setValidValBtn.click();

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");

		System.out.println("####### optionChange Log: " + text2EventLog);

		// Log messages
		boolean forRawValue1 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: undefined,  current Value: Hello123") > -1;

		boolean forMessagesHidden = text2EventLog.indexOf(
				"OptionChange Event for : messagesHidden, previous Value lenth: 1,  current Value length: 0") > -1;
		boolean messagesHiddenPrevValue = text2EventLog.indexOf(
				"messagesHidden Previous Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
		boolean messagesHiddenCurValue = text2EventLog.indexOf("messagesHidden Current Value:") > -1;

		boolean forValue = text2EventLog
				.indexOf("OptionChange Event for : value, previous Value: undefined,  current Value: Hello123") > -1;

		Assert.assertTrue(forRawValue1, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forMessagesHidden, "ojoptionchange event not raised for messageHidden option");
		Assert.assertTrue(messagesHiddenPrevValue, "messageHidden previous value is not correct.");
		Assert.assertTrue(messagesHiddenCurValue, "messageHidden current value is not correct.");
		Assert.assertTrue(forValue, "ojvalue event not raised");

	}

	// Blank to valid value entered by user
	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterValidValue_enteredByUser() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Enter valid Value
		WebElement input = getElement("id=text2");
		input.sendKeys("Hello1");
		input.sendKeys(Keys.TAB);

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");

		System.out.println("####### optionChange Log: " + text2EventLog);

		// Log messages
		boolean forRawValue1 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: undefined,  current Value: H") > -1;

		boolean forRawValue2 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: H,  current Value: He") > -1;
		boolean forRawValue3 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: He,  current Value: Hel") > -1;
		boolean forRawValue4 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: Hel,  current Value: Hell") > -1;
		boolean forRawValue5 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: Hell,  current Value: Hello") > -1;
		boolean forRawValue6 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: Hello,  current Value: Hello1") > -1;

		boolean forMessagesHidden = text2EventLog.indexOf(
				"OptionChange Event for : messagesHidden, previous Value lenth: 1,  current Value length: 0") > -1;
		boolean messagesHiddenPrevValue = text2EventLog.indexOf(
				"messagesHidden Previous Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
		boolean messagesHiddenCurValue = text2EventLog.indexOf("messagesHidden Current Value:") > -1;

		boolean forValue = text2EventLog
				.indexOf("OptionChange Event for : value, previous Value: undefined,  current Value: Hello1") > -1;

		Assert.assertTrue(forRawValue1, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forRawValue2, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forRawValue3, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forRawValue4, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forRawValue5, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forRawValue6, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forMessagesHidden, "ojoptionchange event not raised for messageHidden option");
		Assert.assertTrue(messagesHiddenPrevValue, "messageHidden previous value is not correct.");
		Assert.assertTrue(messagesHiddenCurValue, "messageHidden current value is not correct.");
		Assert.assertTrue(forValue, "ojvalue event not raised");

	}

	// valid value to Blank entered by user
	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterValidToBlank_enteredByUser() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Enter valid Value
		WebElement input = getElement("id=text2");
		input.sendKeys("Hello1");
		input.sendKeys(Keys.TAB);

		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// delete the entered value and TAb out
		input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); // clear
																					// the
																					// field
		input.sendKeys(Keys.TAB);

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");

		System.out.println("####### optionChange Log: " + text2EventLog);

		// Log messages
		boolean forRawValue1 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: Hello1,  current Value: ") > -1;

		boolean forMessagesShown = text2EventLog.indexOf(
				"OptionChange Event for : messagesShown, previous Value lenth: 1,  current Value length: 1") > -1;
		boolean messagesShownPrevValue = text2EventLog.indexOf(
				"messagesShown Previous Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
		boolean messagesShownCurValue = text2EventLog.indexOf(
				"messagesShown Current Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;

		boolean forValue = text2EventLog.indexOf("OptionChange Event for : value") > -1;

		Assert.assertTrue(forRawValue1, "ojrawvalue event not raised for every user entry");

		Assert.assertTrue(forMessagesShown, "ojoptionchange event not raised for messageHidden option");
		Assert.assertTrue(messagesShownPrevValue, "messageHidden previous value is not correct.");
		Assert.assertTrue(messagesShownCurValue, "messageHidden current value is not correct.");
		Assert.assertFalse(forValue, "ojvalue event raised when it should not");

	}

	// Valid value changed to a different valid value
	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterValChangedFromValidToValid_enteredByUser() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Enter valid Value
		WebElement input = getElement("id=text2");
		input.sendKeys("Hello1");
		input.sendKeys(Keys.TAB);

		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Change to another valid value
		// Enter valid Value
		input = getElement("id=text2");
		input.sendKeys("2");
		input.sendKeys(Keys.TAB);

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");
		System.out.println("####### optionChange Log: " + text2EventLog);

		// Log messages
		boolean forRawValue1 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: Hello1,  current Value: Hello12") > -1;
		boolean forValue = text2EventLog
				.indexOf("OptionChange Event for : value, previous Value: Hello1,  current Value: Hello12") > -1;

		Assert.assertTrue(forRawValue1, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forValue, "ojvalue event not raised");

	}

	// Valid value changed to a different valid value
	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterValChangedFromValidToInvalid_enteredByUser() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Enter valid Value
		WebElement input = getElement("id=text2");
		input.sendKeys("Hello1");
		input.sendKeys(Keys.TAB);

		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Change to invalid value

		input = getElement("id=text2");
		input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); // clear
																					// the
																					// field
		input.sendKeys("Hello");
		input.sendKeys(Keys.TAB);

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");
		System.out.println("####### optionChange Log: " + text2EventLog);

		// Log messages
		boolean forRawValue = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: Hello1,  current Value: ") > -1;
		boolean forRawValue1 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: ,  current Value: H") > -1;
		boolean forRawValue2 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: H,  current Value: He") > -1;
		boolean forRawValue3 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: He,  current Value: Hel") > -1;
		boolean forRawValue4 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: Hel,  current Value: Hell") > -1;
		boolean forRawValue5 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: Hell,  current Value: Hello") > -1;

		boolean forMessagesShown = text2EventLog.indexOf(
				"OptionChange Event for : messagesShown, previous Value lenth: 1,  current Value length: 1") > -1;
		boolean messagesShownPrevValue = text2EventLog.indexOf(
				"messagesShown Previous Value: [0: severity: 4, summary: 'Text 2:' too Weak, detail: You must enter a password that meets our minimum security requirements.") > -1;
		boolean messagesShownCurValue = text2EventLog.indexOf(
				"messagesShown Current Value: [0: severity: 4, summary: 'Text 2:' too Weak, detail: You must enter a password that meets our minimum security requirements.") > -1;

		boolean forValue = text2EventLog.indexOf("OptionChange Event for : value") > -1;

		Assert.assertTrue(forRawValue, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forRawValue1, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forRawValue2, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forRawValue3, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forRawValue4, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forRawValue5, "ojrawvalue event not raised for every user entry");

		Assert.assertTrue(forMessagesShown, "ojoptionchange event not raised for messagesShown option");
		Assert.assertTrue(messagesShownPrevValue, "messagesShown previous value is not correct.");
		Assert.assertTrue(messagesShownCurValue, "messagesShown current value is not correct.");

		Assert.assertFalse(forValue, "ojvalue event raised when it should not");

	}

	// Blank to invalid value entered programmatically
	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterInvalidValue_enteredProgrammatically() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Enter valid Value
		WebElement setInvalidValbtn = getElement("id=text2Set");
		setInvalidValbtn.click();

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");

		System.out.println("####### optionChange Log: " + text2EventLog);

		// Log messages
		boolean forRawValue1 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: undefined,  current Value: aaa") > -1;

		boolean forMessagesHidden = text2EventLog.indexOf(
				"OptionChange Event for : messagesHidden, previous Value lenth: 1,  current Value length: 0") > -1;
		boolean messagesHiddenPrevValue = text2EventLog.indexOf(
				"messagesHidden Previous Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
		boolean messagesHiddenCurValue = text2EventLog.indexOf("messagesHidden Current Value:") > -1;

		boolean forValue = text2EventLog
				.indexOf("OptionChange Event for : value, previous Value: undefined,  current Value: aaa") > -1;

		Assert.assertTrue(forRawValue1, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forMessagesHidden, "ojoptionchange event not raised for messageHidden option");
		Assert.assertTrue(messagesHiddenPrevValue, "messageHidden previous value is not correct.");
		Assert.assertTrue(messagesHiddenCurValue, "messageHidden current value is not correct.");
		Assert.assertTrue(forValue, "ojvalue event not raised");

	}

	// Blank to invalid value entered by user
	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterInvalidValue_enteredByUser() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Enter invalid Value
		WebElement input = getElement("id=text2");
		input.sendKeys("H");
		input.sendKeys(Keys.TAB);

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");

		System.out.println("####### optionChange Log: " + text2EventLog);

		// Log messages
		boolean forRawValue1 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: undefined,  current Value: H") > -1;

		boolean forMessagesHidden = text2EventLog.indexOf(
				"OptionChange Event for : messagesHidden, previous Value lenth: 1,  current Value length: 0") > -1;
		boolean messagesHiddenPrevValue = text2EventLog.indexOf(
				"messagesHidden Previous Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
		boolean messagesHiddenCurValue = text2EventLog.indexOf("messagesHidden Current Value:") > -1;

		boolean forValue = text2EventLog.indexOf("OptionChange Event for : value") > -1;

		Assert.assertTrue(forRawValue1, "ojrawvalue event not raised for every user entry");

		Assert.assertTrue(forMessagesHidden, "ojoptionchange event not raised for messageHidden option");
		Assert.assertTrue(messagesHiddenPrevValue, "messageHidden previous value is not correct.");
		Assert.assertTrue(messagesHiddenCurValue, "messageHidden current value is not correct.");
		Assert.assertFalse(forValue, "ojvalue event raised when it should not be");

	}

	// invalid value to Blank entered by user
	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterInvalidToBlank_enteredByUser() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Enter invalid Value
		WebElement input = getElement("id=text2");
		input.sendKeys("Hello");
		input.sendKeys(Keys.TAB);

		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// delete the entered value and TAb out
		input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); // clear
		input.sendKeys(Keys.TAB);

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");

		System.out.println("####### optionChange Log: " + text2EventLog);

		// Log messages
		boolean forRawValue1 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: Hello,  current Value: ") > -1;

		boolean forMessagesShown = text2EventLog.indexOf(
				"OptionChange Event for : messagesShown, previous Value lenth: 1,  current Value length: 0") > -1;
		boolean messagesShownPrevValue = text2EventLog.indexOf(
				"messagesShown Previous Value: [0: severity: 4, summary: 'Text 2:' too Weak, detail: You must enter a password that meets our minimum security requirements.") > -1;
		boolean messagesShownCurValue = text2EventLog.indexOf("messagesShown Current Value:") > -1;

		boolean forMessagesShown1 = text2EventLog.indexOf(
				"OptionChange Event for : messagesShown, previous Value lenth: 1,  current Value length: 1") > -1;
		boolean messagesShownPrevValue1 = text2EventLog.indexOf(
				"messagesShown Previous Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;
		boolean messagesShownCurValue1 = text2EventLog.indexOf(
				"messagesShown Current Value: [0: severity: 4, summary: Value is required., detail: You must enter a value.") > -1;

		boolean forValue = text2EventLog.indexOf("OptionChange Event for : value") > -1;

		Assert.assertTrue(forRawValue1, "ojrawvalue event not raised for every user entry");

		Assert.assertTrue(forMessagesShown, "ojoptionchange event not raised for messageHidden option");
		Assert.assertTrue(messagesShownPrevValue, "messageHidden previous value is not correct.");
		Assert.assertTrue(messagesShownCurValue, "messageHidden current value is not correct.");

		Assert.assertTrue(forMessagesShown1, "ojoptionchange event not raised for messageHidden option");
		Assert.assertTrue(messagesShownPrevValue1, "messageHidden previous value is not correct.");
		Assert.assertTrue(messagesShownCurValue1, "messageHidden current value is not correct.");
		Assert.assertFalse(forValue, "ojvalue event raised when it should not");

	}

	// Invalid value changed to a different invalid value
	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterValChangedFromInvalidToInvalid_enteredByUser() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Enter invalid Value
		WebElement input = getElement("id=text2");
		input.sendKeys("H");
		input.sendKeys(Keys.TAB);

		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Change to another invalid value
		input = getElement("id=text2");
		input.sendKeys("2");
		input.sendKeys(Keys.TAB);

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");
		System.out.println("####### optionChange Log: " + text2EventLog);

		// Log messages
		boolean forRawValue1 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: H,  current Value: H2") > -1;
		boolean forValue = text2EventLog.indexOf("OptionChange Event for : value") > -1;

		Assert.assertTrue(forRawValue1, "ojrawvalue event not raised for every user entry");
		Assert.assertFalse(forValue, "ojvalue event raised when it should not be");

	}

	// invalid value changed to a valid value
	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterValChangedFromInvalidToValid_enteredByUser() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Enter invalid Value
		WebElement input = getElement("id=text2");
		input.sendKeys("H");
		input.sendKeys(Keys.TAB);

		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Change to valid value

		input = getElement("id=text2");
		input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); // clear
																					// the
																					// field
		input.sendKeys("Hello1");
		input.sendKeys(Keys.TAB);

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");
		System.out.println("####### optionChange Log: " + text2EventLog);

		// Log messages
		boolean forRawValue = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: H,  current Value: ") > -1;
		boolean forRawValue1 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: ,  current Value: H") > -1;
		boolean forRawValue2 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: H,  current Value: He") > -1;
		boolean forRawValue3 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: He,  current Value: Hel") > -1;
		boolean forRawValue4 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: Hel,  current Value: Hell") > -1;
		boolean forRawValue5 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: Hell,  current Value: Hello") > -1;
		boolean forRawValue6 = text2EventLog
				.indexOf("OptionChange Event for : rawValue, previous Value: Hello,  current Value: Hello1") > -1;

		boolean forMessagesShown = text2EventLog.indexOf(
				"OptionChange Event for : messagesShown, previous Value lenth: 1,  current Value length: 0") > -1;
		boolean messagesShownPrevValue = text2EventLog.indexOf(
				"messagesShown Previous Value: [0: severity: 4, summary: 'Text 2:' too Weak, detail: You must enter a password that meets our minimum security requirements.") > -1;
		boolean messagesShownCurValue = text2EventLog.indexOf("messagesShown Current Value:") > -1;

		boolean forValue = text2EventLog
				.indexOf("OptionChange Event for : value, previous Value: undefined,  current Value: Hello1") > -1;

		Assert.assertTrue(forRawValue, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forRawValue1, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forRawValue2, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forRawValue3, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forRawValue4, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forRawValue5, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forRawValue6, "ojrawvalue event not raised for every user entry");
		Assert.assertTrue(forMessagesShown, "ojoptionchange event not raised for messagesShown option");
		Assert.assertTrue(messagesShownPrevValue, "messagesShown previous value is not correct.");
		Assert.assertTrue(messagesShownCurValue, "messagesShown current value is not correct.");

		Assert.assertTrue(forValue, "ojvalue event not raised");

	}

}
