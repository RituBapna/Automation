package oj.tests.helphintmessaging.messaingandvents.inputtext;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

public class EventsAfterConverterError  extends OJetBase {
	public EventsAfterConverterError() {
		super(new TestConfigBuilder().setContextRoot("helphintmessaging").setAppRoot("").build());
	}

	// events after field with Converter Err
	@Test(groups = { "cookbook", "messaging" })
	public void testIsvalidValForFieldWithDeferredMsg() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

	
	
	
	}
}
