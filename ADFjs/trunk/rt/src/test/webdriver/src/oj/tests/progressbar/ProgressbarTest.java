package oj.tests.progressbar;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;

public class ProgressbarTest extends OJetBase {

	private static final String TITLE_PB = "ProgressBar";

	private static final String RESULTS_CONTAINER = "resultsFunction";

	private static final String CURRENT_PAGE = "currentPage";


    public ProgressbarTest() {
        super(new TestConfigBuilder().setContextRoot("progressbar").setAppRoot("progressbarTest").build());
    }


    @Test(groups = { "pb" })
	    public void testProgressbar() throws Exception {

			//Start the test and bring up the browser
			startupTest("progressBar.html",null);

			//Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
			verifyTitle("Incorrect page title;", TITLE_PB);
			waitForElementVisible("id=resultsFunction");
			waitForText(RESULTS_CONTAINER, "In ojcreate event of progressBar");



    }

    @Test(groups = { "pb" })
	public void testPBBasicFunctionality() throws Exception {

		//Start the test and bring up the browser
		startupTest("progressBar.html",null);

		waitForElementVisible("id=resultsFunction");

		waitForElementVisible("id=progressbar");

		WebElement spinnerInput = getElement("id=spinner-input");

		WebElement inputNumber = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-input\"}");
		String valuenow = inputNumber.getAttribute("aria-valuenow");
		Assert.assertEquals(valuenow, "20");

		WebElement inputNumberPB = getElement("id=progressbar");
		String valuenowPB = inputNumberPB.getAttribute("aria-valuenow");
		Assert.assertEquals(valuenow, "20");

		WebElement inputNumberUp = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-up\"}");
		inputNumberUp.click();
		valuenow = inputNumber.getAttribute("aria-valuenow");
		Assert.assertEquals(valuenow, "30");

		inputNumberPB = getElement("id=progressbar");
		valuenowPB = inputNumberPB.getAttribute("aria-valuenow");
		Assert.assertEquals(valuenow, "30");

		WebElement inputNumberDown = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-down\"}");
		inputNumberDown.click();
		inputNumberDown.click();

		inputNumber = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-input\"}");
		valuenow = inputNumber.getAttribute("aria-valuenow");
		Assert.assertEquals(valuenow, "10");

		inputNumberPB = getElement("id=progressbar");
		valuenowPB = inputNumberPB.getAttribute("aria-valuenow");
		Assert.assertEquals(valuenow, "10");


		WebElement indeterminateButton = getElement("id=indeterminateButton");
		indeterminateButton.click();

		inputNumber = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-input\"}");
		valuenow = inputNumber.getAttribute("aria-valuenow");
		Assert.assertEquals(valuenow, "-1");

		inputNumberPB = getElement("id=progressbar");
		valuenowPB = inputNumberPB.getAttribute("aria-valuenow");
		Assert.assertEquals(valuenow, "-1");


		inputNumberUp = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-up\"}");
		inputNumberUp.click();
		inputNumber = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-input\"}");
		valuenow = inputNumber.getAttribute("aria-valuenow");
		Assert.assertEquals(valuenow, "0");

		inputNumberPB = getElement("id=progressbar");
		valuenowPB = inputNumberPB.getAttribute("aria-valuenow");
		Assert.assertEquals(valuenow, "0");


		inputNumberUp = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-up\"}");
		inputNumberUp.click();
		inputNumber = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-input\"}");
		valuenow = inputNumber.getAttribute("aria-valuenow");
		Assert.assertEquals(valuenow, "10");

		inputNumberPB = getElement("id=progressbar");
		valuenowPB = inputNumberPB.getAttribute("aria-valuenow");
		Assert.assertEquals(valuenow, "10");



    }

    @Test(groups = { "pb" })
	public void disableEnablePB() throws Exception {

			//Start the test and bring up the browser
			startupTest("progressBar.html",null);

			waitForElementVisible("id=resultsFunction");

			waitForElementVisible("id=progressbar");

			WebElement spinnerInput = getElement("id=spinner-input");

			WebElement inputNumber = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-input\"}");

			String valuenow = inputNumber.getAttribute("aria-valuenow");

			log("valuenow: "+ valuenow);

			Assert.assertEquals(valuenow, "20");

			WebElement inputNumberPB = getElement("id=progressbar");
			String valuenowPB = inputNumberPB.getAttribute("aria-valuenow");
			Assert.assertEquals(valuenow, "20");


			WebElement disableProgressBar = getElement("id=disableProgressbar");
			disableProgressBar.click();

			inputNumber = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-input\"}");
			valuenow = inputNumber.getAttribute("aria-valuenow");
			Assert.assertEquals(valuenow, "20");

			inputNumberPB = getElement("id=progressbar");
			valuenowPB = inputNumberPB.getAttribute("aria-valuenow");
			Assert.assertEquals(valuenow, "20");


			WebElement inputNumberUp = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-up\"}");
			inputNumberUp.click();
			inputNumber = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-input\"}");
			valuenow = inputNumber.getAttribute("aria-valuenow");
			Assert.assertEquals(valuenow, "20");

			inputNumberPB = getElement("id=progressbar");
			valuenowPB = inputNumberPB.getAttribute("aria-valuenow");
			Assert.assertEquals(valuenow, "20");

			WebElement enableProgressBar = getElement("id=enableProgressbar");
			enableProgressBar.click();
			inputNumber = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-input\"}");
			valuenow = inputNumber.getAttribute("aria-valuenow");
			Assert.assertEquals(valuenow, "20");

			inputNumberUp = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-up\"}");
			inputNumberUp.click();
			inputNumber = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-input\"}");
			valuenow = inputNumber.getAttribute("aria-valuenow");
			Assert.assertEquals(valuenow, "30");

			inputNumberPB = getElement("id=progressbar");
			valuenowPB = inputNumberPB.getAttribute("aria-valuenow");
			Assert.assertEquals(valuenow, "30");

    }


    @Test(groups = { "pb" })
	public void verifyMinAndMaxValuePB() throws Exception {

			//Start the test and bring up the browser
			startupTest("progressBar.html",null);

			waitForElementVisible("id=progressbar");

			WebElement spinnerInput = getElement("id=spinner-input");

			WebElement inputNumber = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-input\"}");

			String valuenow = inputNumber.getAttribute("aria-valuenow");

			log("valuenow: "+ valuenow);

			Assert.assertEquals(valuenow, "20");

			WebElement inputNumberDown = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-down\"}");
			inputNumberDown.click();
			inputNumberDown.click();
			inputNumberDown.click();
			inputNumberDown.click();

			inputNumber = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-input\"}");
			valuenow = inputNumber.getAttribute("aria-valuenow");
			Assert.assertEquals(valuenow, "0");


			WebElement inputNumberPB = getElement("id=progressbar");
			String valuenowPB = inputNumberPB.getAttribute("aria-valuenow");
			Assert.assertEquals(valuenow, "0");


			WebElement inputNumberUp = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-up\"}");
			inputNumberUp.click();
			inputNumberUp.click();
			inputNumberUp.click();
			inputNumberUp.click();
			inputNumberUp.click();
			inputNumberUp.click();
			inputNumberUp.click();
			inputNumberUp.click();
			inputNumberUp.click();
			inputNumberUp.click();
			inputNumberUp.click();
			inputNumberUp.click();
			inputNumber = getElement("{\"element\":\"#spinner-input\",\"subId\":\"oj-inputnumber-input\"}");
			valuenow = inputNumber.getAttribute("aria-valuenow");
			Assert.assertEquals(valuenow, "100");

			inputNumberPB = getElement("id=progressbar");
			valuenowPB = inputNumberPB.getAttribute("aria-valuenow");
			Assert.assertEquals(valuenow, "100");

	}

	@Test(groups = { "pb" })
	public void testAddRemoveContextMenu() throws Exception {

		//Start the test and bring up the browser
		startupTest("progressBar.html",null);

		waitForElementVisible("id=addContextMenu");

		WebElement addContextMenu = getElement("id=addContextMenu");
		addContextMenu.click();

		rightClickAndSelectMenuOption("id=progressbar","id=ui-id-4");

		WebElement removeContextMenu = getElement("id=removeContextMenu");
		removeContextMenu.click();

		boolean menuDisplayed = true;

		try{
			rightClickAndSelectMenuOption("id=progressbar","id=ui-id-4");
		}catch(org.openqa.selenium.WebDriverException nse){
			 menuDisplayed = false;
		}

		Assert.assertEquals(menuDisplayed,false);

    }

     @Test(groups = { "pb" })
		public void destroyPB() throws Exception {

			//Start the test and bring up the browser
			startupTest("progressBar.html",null);

			waitForElementVisible("id=resultsFunction");

			waitForElementVisible("id=progressbar");

			WebElement destroyProgressBar = getElement("id=destroyProgressBar");
			destroyProgressBar.click();

			waitForText(RESULTS_CONTAINER, "ProgressBar destroyed.");

			boolean isPBDisplayed  = true;

			WebElement pb = null;

			try{
				pb = getElement("id=progressbar");
				String value = pb.getAttribute("aria-valuenow");
				if (value==null) isPBDisplayed = false;
			}catch(java.lang.Exception nse){
				System.out.println(nse);
				isPBDisplayed = false;
			}

			Assert.assertEquals(isPBDisplayed, false);


		}



    private void log(String log)
	    {
	        System.out.println("[ProgressBarTest] " + log);
	        getLogger().fine("[ProgressBarTest] " + log);
    }


}
