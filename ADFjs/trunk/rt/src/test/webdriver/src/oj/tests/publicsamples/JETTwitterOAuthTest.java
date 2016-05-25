package oj.tests.publicsamples;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.Select;

public class JETTwitterOAuthTest extends OJetBase {

    private static final String TITLE = "Oracle JET OAuth object";

    public JETTwitterOAuthTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/public_samples").setAppRoot("JET-OAuth-Header/public_html").build());
    }

    @Test(groups = { "TwitterOAuth" })
    public void twitterOAuth() throws Exception {
        //Start the test and bring up the browser
        startupTest("index.html",null);

        //Verify the url
		String url = getBrowserUrl();
		log("URL##########"+ url);

		// Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE);

        // Make sure save button and table
        waitForElementVisible("id=btnLogin");

    }

   @Test(groups = { "TwitterOAuth" })
   public void testTwitterOAuthLogin() throws Exception {

   			//Start the test and bring up the browser
   			startupTest("index.html",null);

   			waitForElementVisible("id=btnLogin");

   			WebElement userLogin = getElement("id=btnLogin");
   			userLogin.click();

   			getElement("id=buttonCancel").click();

   			userLogin = getElement("id=btnLogin");
   			userLogin.click();

   			WebElement username = getElement("id=username");
   			username.sendKeys("oracle");

   			WebElement password = getElement("id=password");
   			password.sendKeys("oracle");

   			getElement("id=buttonSubmit").click();

   			waitForElementVisible("id=getData");

   			getElement("id=getData").click();

   			//Need to wait for loading to finish
			waitForMilliseconds(6000L);

			waitForElementVisible("id=table");

			WebElement dgHeader0 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"0\"}");
			Assert.assertEquals("User Id",dgHeader0.getText());

			WebElement dgHeader1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"1\"}");
			Assert.assertEquals("Created",dgHeader1.getText());

			WebElement dgHeader2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"2\"}");
			Assert.assertEquals("Text",dgHeader2.getText());

			WebElement dgHeader3 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"3\"}");
			Assert.assertEquals("User Name",dgHeader3.getText());

			WebElement dgHeader4 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"4\"}");
			Assert.assertEquals("Location",dgHeader4.getText());

			/*
			waitForElementVisible("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");

			WebElement row0Col0 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
			Assert.assertNotNull(row0Col0.getText());

			WebElement row0Col3 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
			Assert.assertNotNull(row0Col3.getText());
			*/

			waitForElementVisible("id=query");

			Select dropdown = new Select(getElement("id=query"));

			dropdown.selectByVisibleText("Tweeter API");

			getElement("id=getData").click();

   			//Need to wait for loading to finish
			waitForMilliseconds(6000L);

			WebElement row0Col0 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
			Assert.assertNotNull(row0Col0.getText());

			WebElement row0Col3 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
			Assert.assertNotNull(row0Col3.getText());

			dropdown = new Select(getElement("id=query"));

			dropdown.selectByVisibleText("JavaScript");

			getElement("id=getData").click();

			//Need to wait for loading to finish
			waitForMilliseconds(6000L);

			row0Col0 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
			Assert.assertNotNull(row0Col0.getText());

			row0Col3 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
			Assert.assertNotNull(row0Col3.getText());

			dropdown = new Select(getElement("id=query"));

			dropdown.selectByVisibleText("Oracle");

			getElement("id=getData").click();

			//Need to wait for loading to finish
			waitForMilliseconds(6000L);

			row0Col0 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
			Assert.assertNotNull(row0Col0.getText());

			row0Col3 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
			Assert.assertNotNull(row0Col3.getText());

			dropdown = new Select(getElement("id=query"));

			dropdown.selectByVisibleText("Microsoft");

			getElement("id=getData").click();

			//Need to wait for loading to finish
			waitForMilliseconds(6000L);

			row0Col0 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
			Assert.assertNotNull(row0Col0.getText());

			row0Col3 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
			Assert.assertNotNull(row0Col3.getText());

			getElement("id=btnLogout").click();

			boolean isGetData = true;

			try{
				WebElement getData = getElement("id=getData");
				isGetData = getData.isDisplayed();
			}catch(Exception e){
				isGetData = false;
			}

			Assert.assertFalse(isGetData);





  }




	private void log(String log)
	{
		System.out.println(log);

		getLogger().fine("[JETTwitterOAuthTest] " + log);
	}


}
