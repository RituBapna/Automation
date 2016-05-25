package oj.tests.helphintmessaging.isvalidapi.inputtext;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

public class IsValidReturnValueTest extends OJetBase {
	public IsValidReturnValueTest() {
		super(new TestConfigBuilder().setContextRoot("helphintmessaging").setAppRoot("").build());
	}

	// field with deffered msg.
	@Test(groups = { "cookbook", "messaging" })
	public void testIsvalidValForFieldWithDeferredMsg() throws Exception {

		startupTest("messagingTestPage.html", null);

		verifyTitle("Incorrect page title;", "JET Messaging tests");
		waitForElementVisible("id=btn2");

	
		// Click on IsValid button
		WebElement isvalidBtn = getElement("id=text2Valid");
		isvalidBtn.click();

		// Get the isValid return value
		
		WebElement isvalidReturnValBtn = getElement("id=text2IsValidVal");
		String retVal = isvalidReturnValBtn.getText();
		
		
		Assert.assertEquals(retVal, "isValid: false", "incorrect isValid return value.");
	
	}
	
	//isValid value for field with incorrect value
		@Test(groups = { "cookbook", "messaging" })
		public void testIsvalidValForFieldWithIncorrectVal_programmatic() throws Exception {

			startupTest("messagingTestPage.html", null);

			verifyTitle("Incorrect page title;", "JET Messaging tests");
			waitForElementVisible("id=btn2");

			// Enter invalid Value programmatically
			WebElement setInvalidValBtn = getElement("id=text2Set");
			setInvalidValBtn.click();
			
			// Click on validate button
			WebElement validateBtn = getElement("id=text2Validate");
			validateBtn.click();
			
			// Click on IsValid button
			WebElement isvalidBtn = getElement("id=text2Valid");
			isvalidBtn.click();

			// Get the isValid return value			
			WebElement isvalidReturnValBtn = getElement("id=text2IsValidVal");
			String retVal = isvalidReturnValBtn.getText();
						
			Assert.assertEquals(retVal, "isValid: false", "incorrect isValid return value.");

		}
		
		//isValid value for field with correct value
				@Test(groups = { "cookbook", "messaging" })
				public void testIsvalidValForFieldWithCorrectVal_programmatic() throws Exception {

					startupTest("messagingTestPage.html", null);

					verifyTitle("Incorrect page title;", "JET Messaging tests");
					waitForElementVisible("id=btn2");

					// Enter invalid Value programmatically
					WebElement setValidValBtn = getElement("id=text2SetV");
					setValidValBtn.click();
					
					// Click on validate button
					WebElement validateBtn = getElement("id=text2Validate");
					validateBtn.click();
					
					// Click on IsValid button
					WebElement isvalidBtn = getElement("id=text2Valid");
					isvalidBtn.click();

					// Get the isValid return value			
					WebElement isvalidReturnValBtn = getElement("id=text2IsValidVal");
					String retVal = isvalidReturnValBtn.getText();
								
					Assert.assertEquals(retVal, "isValid: true", "incorrect isValid return value.");

				}
				
				//isValid value for field with incorrect value
				@Test(groups = { "cookbook", "messaging" })
				public void testIsvalidValForFieldWithIncorrectVal_userEntered() throws Exception {

					startupTest("messagingTestPage.html", null);

					verifyTitle("Incorrect page title;", "JET Messaging tests");
					waitForElementVisible("id=btn2");

					// Enter invalid Value 
					// Enter valid Value
					WebElement input = getElement("id=text2");
					input.sendKeys("Hello");
					input.sendKeys(Keys.TAB);
				
					
					// Click on IsValid button
					WebElement isvalidBtn = getElement("id=text2Valid");
					isvalidBtn.click();

					// Get the isValid return value			
					WebElement isvalidReturnValBtn = getElement("id=text2IsValidVal");
					String retVal = isvalidReturnValBtn.getText();
								
					Assert.assertEquals(retVal, "isValid: false", "incorrect isValid return value.");

				}
				
				//isValid value for field with correct value
						@Test(groups = { "cookbook", "messaging" })
						public void testIsvalidValForFieldWithCorrectVal_userEntered() throws Exception {

							startupTest("messagingTestPage.html", null);

							verifyTitle("Incorrect page title;", "JET Messaging tests");
							waitForElementVisible("id=btn2");
 
							// Enter valid Value
							WebElement input = getElement("id=text2");
							input.sendKeys("Hello1");
							input.sendKeys(Keys.TAB);
						
							// Click on IsValid button
							WebElement isvalidBtn = getElement("id=text2Valid");
							isvalidBtn.click();

							// Get the isValid return value			
							WebElement isvalidReturnValBtn = getElement("id=text2IsValidVal");
							String retVal = isvalidReturnValBtn.getText();
										
							Assert.assertEquals(retVal, "isValid: true", "incorrect isValid return value.");

						}
						
						//isValid value for field with correct value but custom msg with "Error" status
						@Test(groups = { "cookbook", "messaging" })
						public void testIsvalidValForFieldWithCorrectVal_errorCustomMsg() throws Exception {

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
							
							// Click on IsValid button
							WebElement isvalidBtn = getElement("id=text2Valid");
							isvalidBtn.click();

							// Get the isValid return value			
							WebElement isvalidReturnValBtn = getElement("id=text2IsValidVal");
							String retVal = isvalidReturnValBtn.getText();
										
							Assert.assertEquals(retVal, "isValid: false", "incorrect isValid return value.");

						}
						
						//isValid value for field with correct value but custom msg with "Warning" status
						@Test(groups = { "cookbook", "messaging" })
						public void testIsvalidValForFieldWithCorrectVal_warningCustomMsg() throws Exception {

							startupTest("messagingTestPage.html", null);

							verifyTitle("Incorrect page title;", "JET Messaging tests");
							waitForElementVisible("id=btn2");
 
							// Enter valid Value
							WebElement input = getElement("id=text2");
							input.sendKeys("Hello1");
							input.sendKeys(Keys.TAB);
						
							// Enter Button To add custom msg
							WebElement setCustomErrBtn = getElement("id=btn21111");
							setCustomErrBtn.click();
							
							// Click on IsValid button
							WebElement isvalidBtn = getElement("id=text2Valid");
							isvalidBtn.click();

							// Get the isValid return value			
							WebElement isvalidReturnValBtn = getElement("id=text2IsValidVal");
							String retVal = isvalidReturnValBtn.getText();
										
							Assert.assertEquals(retVal, "isValid: true", "incorrect isValid return value.");

						}
						
		
}
