package oj.tests.helphintmessaging.messaingandvents.inputtext;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

public class EventsAfterCustomErrMsgAdded extends OJetBase {
	public EventsAfterCustomErrMsgAdded() {
		super(new TestConfigBuilder().setContextRoot("helphintmessaging").setAppRoot("").build());
	}

	// Blank field with deffered msg. Add custom msg
	@Test(groups = { "cookbook", "messaging" })
	public void testEventsAfterCustomErrMsgAddedToFieldWithDeferredMsg() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

		// Click The clear event Log btn
		WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
		clearLogBtn.click();

		// Enter Button To add custom msg
		WebElement setCustomErrBtn = getElement("id=btn2111");
		setCustomErrBtn.click();

		// Get the log Window data
		String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");

		System.out.println("####### optionChange Log: " + text2EventLog);

		// Log messages

		boolean forMessagesCustom = text2EventLog.indexOf(
				"OptionChange Event for : messagesShown, previous Value lenth: 0,  current Value length: 2") > -1;
		boolean messagesCustomPrevValue = text2EventLog.indexOf("messagesCustom Previous Value:") > -1;
		boolean messagesCustomCurValue = text2EventLog.indexOf(
				"messagesCustom Current Value: [0: severity: 4, summary: custom Error Summary : 1, detail: custom Error Detail : 1, [1: severity: 2, summary: custom Info Summary : 2, detail: custom Info Detail : 2") > -1;

		boolean forMessagesShown = text2EventLog.indexOf(
				"OptionChange Event for : messagesShown, previous Value lenth: 0,  current Value length: 2") > -1;
		boolean messagesShownPrevValue = text2EventLog.indexOf("messagesShown Previous Value:") > -1;
		boolean messagesShownCurValue = text2EventLog.indexOf(
				"messagesShown Current Value: [0: severity: 4, summary: custom Error Summary : 1, detail: custom Error Detail : 1, [1: severity: 2, summary: custom Info Summary : 2, detail: custom Info Detail : 2") > -1;

		boolean forValue = text2EventLog
				.indexOf("OptionChange Event for : value") > -1;

		Assert.assertTrue(forMessagesCustom, "ojoptionchange event not raised for messagesCustom option");
		Assert.assertTrue(messagesCustomPrevValue, "messagesCustom previous value is not correct.");
		Assert.assertTrue(messagesCustomCurValue, "messagesCustom current value is not correct.");
		Assert.assertTrue(forMessagesShown, "ojoptionchange event not raised for messagesShown option");
		Assert.assertTrue(messagesShownPrevValue, "messagesShown previous value is not correct.");
		Assert.assertTrue(messagesShownCurValue, "messagesShown current value is not correct.");
		Assert.assertFalse(forValue, "ojvalue event raised when it should be");

	}
	
	//Enter Valid value and Add custom msg
		@Test(groups = { "cookbook", "messaging" })
		public void testEventsAfterCustomErrMsgAddedToFieldWithValidValue() throws Exception {

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

			// Enter Button To add custom msg
			WebElement setCustomErrBtn = getElement("id=btn2111");
			setCustomErrBtn.click();

			// Get the log Window data
			String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");
			System.out.println("####### optionChange Log: " + text2EventLog);

			// Log messages
			boolean forMessagesCustom = text2EventLog.indexOf(
					"OptionChange Event for : messagesShown, previous Value lenth: 0,  current Value length: 2") > -1;
			boolean messagesCustomPrevValue = text2EventLog.indexOf("messagesCustom Previous Value:") > -1;
			boolean messagesCustomCurValue = text2EventLog.indexOf(
					"messagesCustom Current Value: [0: severity: 4, summary: custom Error Summary : 1, detail: custom Error Detail : 1, [1: severity: 2, summary: custom Info Summary : 2, detail: custom Info Detail : 2") > -1;

			boolean forMessagesShown = text2EventLog.indexOf(
					"OptionChange Event for : messagesShown, previous Value lenth: 0,  current Value length: 2") > -1;
			boolean messagesShownPrevValue = text2EventLog.indexOf("messagesShown Previous Value:") > -1;
			boolean messagesShownCurValue = text2EventLog.indexOf(
					"messagesShown Current Value: [0: severity: 4, summary: custom Error Summary : 1, detail: custom Error Detail : 1, [1: severity: 2, summary: custom Info Summary : 2, detail: custom Info Detail : 2") > -1;

			boolean forValue = text2EventLog
					.indexOf("OptionChange Event for : value") > -1;

			Assert.assertTrue(forMessagesCustom, "ojoptionchange event not raised for messagesCustom option");
			Assert.assertTrue(messagesCustomPrevValue, "messagesCustom previous value is not correct.");
			Assert.assertTrue(messagesCustomCurValue, "messagesCustom current value is not correct.");
			Assert.assertTrue(forMessagesShown, "ojoptionchange event not raised for messagesShown option");
			Assert.assertTrue(messagesShownPrevValue, "messagesShown previous value is not correct.");
			Assert.assertTrue(messagesShownCurValue, "messagesShown current value is not correct.");
			Assert.assertFalse(forValue, "ojvalue is raised when it should not be.");

		}
		
		//Enter Valid value and Add custom msg
				@Test(groups = { "cookbook", "messaging" })
				public void testEventsAfterCustomErrMsgAddedToFieldWithInvalidValue() throws Exception {

					startupTest("messagingTestPage.html", null);

					verifyTitle("Incorrect page title;", "JET Messaging tests");
					waitForElementVisible("id=btn2");

					// Enter valid Value
					WebElement input = getElement("id=text2");
					input.sendKeys("Hello");
					input.sendKeys(Keys.TAB);
					
					// Click The clear event Log btn
					WebElement clearLogBtn = getElement("id=text2ClearLogBtn");
					clearLogBtn.click();

					// Enter Button To add custom msg
					WebElement setCustomErrBtn = getElement("id=btn2111");
					setCustomErrBtn.click();

					// Get the log Window data
					String text2EventLog = evalJavascript("return $('#text2-area').ojTextArea('option', 'value')");
					System.out.println("####### optionChange Log: " + text2EventLog);
					
					// Log messages
					boolean forMessagesCustom = text2EventLog.indexOf(
							"OptionChange Event for : messagesCustom, previous Value lenth: 0,  current Value length: 2") > -1;
					boolean messagesCustomPrevValue = text2EventLog.indexOf("messagesCustom Previous Value:") > -1;
					boolean messagesCustomCurValue = text2EventLog.indexOf(
							"messagesCustom Current Value: [0: severity: 4, summary: custom Error Summary : 1, detail: custom Error Detail : 1, [1: severity: 2, summary: custom Info Summary : 2, detail: custom Info Detail : 2") > -1;

					boolean forMessagesShown = text2EventLog.indexOf(
							"OptionChange Event for : messagesShown, previous Value lenth: 1,  current Value length: 3") > -1;
					boolean messagesShownPrevValue = text2EventLog.indexOf("messagesShown Previous Value: [0: severity: 4, summary: 'Text 2:' too Weak, detail: You must enter a password that meets our minimum security requirements.") > -1;
					boolean messagesShownCurValue = text2EventLog.indexOf(
							"messagesShown Current Value: [0: severity: 4, summary: 'Text 2:' too Weak, detail: You must enter a password that meets our minimum security requirements., [1: severity: 4, summary: custom Error Summary : 1, detail: custom Error Detail : 1, [2: severity: 2, summary: custom Info Summary : 2, detail: custom Info Detail : 2") > -1;

					boolean forValue = text2EventLog
							.indexOf("OptionChange Event for : value") > -1;

					Assert.assertTrue(forMessagesCustom, "ojoptionchange event not raised for messagesCustom option");
					Assert.assertTrue(messagesCustomPrevValue, "messagesCustom previous value is not correct.");
					Assert.assertTrue(messagesCustomCurValue, "messagesCustom current value is not correct.");
					Assert.assertTrue(forMessagesShown, "ojoptionchange event not raised for messagesShown option");
					Assert.assertTrue(messagesShownPrevValue, "messagesShown previous value is not correct.");
					Assert.assertTrue(messagesShownCurValue, "messagesShown current value is not correct.");
					Assert.assertFalse(forValue, "ojvalue is raised when it should not be.");

				}
}
