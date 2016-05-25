package oj.tests.tree;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.interactions.Actions;


public class TreeDialogPopupTest extends OJetBase {

	private static final String TITLE_DP = "Tree DialogPopup";



    public TreeDialogPopupTest() {
        super(new TestConfigBuilder().setContextRoot("tree").setAppRoot("treeLoad").build());
    }


    @Test(groups = { "tree" })
	public void loadPage() throws Exception {

			//Start the test and bring up the browser
			startupTest("TreeDialogPopup.html",null);

			//Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
			verifyTitle("Incorrect page title;", TITLE_DP);

    }


    @Test(groups = { "tree" })
	public void testTreeDialogPopup() throws Exception {

		//Start the test and bring up the browser
		startupTest("TreeDialogPopup.html",null);

		//Click the button stillCanClickMe
		WebElement stillCanClickMe = getElement("id=stillCanClickMe");
		log("stillCanClickMe"+stillCanClickMe);

		waitForElementVisible("id=stillCanClickMe");
		stillCanClickMe.click();

		WebElement modalDialog0 = getElement("{\"element\":\"#modalDialog0\",\"subId\":\"oj-dialog-body\"}");

		log("modalDialog0"+modalDialog0.toString());

		//Validate the dialog content
		waitForText("{\"element\":\"#modalDialog0\",\"subId\":\"oj-dialog-body\"}","Tree in a Modeless Dialog");

		//Close the dialog
		WebElement closeModalDialog0 = getElement("{\"element\":\"#modalDialog0\",\"subId\":\"oj-dialog-header-close-wrapper\"}");
		log("closeModalDialog0:"+closeModalDialog0);
		closeModalDialog0.click();

		//Make sure the dialog is not displayed anymore
		Assert.assertFalse(checkIfDisplayed(By.id("modalDialog0")));

	}

	@Test(groups = { "tree" })
		public void testTreeInModalDialog() throws Exception {

			//Start the test and bring up the browser
			startupTest("TreeDialogPopup.html",null);

			//Click the button openModalDialog
			WebElement openModalDialog = getElement("id=openModalDialog");
			log("openModalDialog"+openModalDialog);

			waitForElementVisible("id=openModalDialog");
			openModalDialog.click();

			WebElement modalDialog = getElement("{\"element\":\"#modalDialog\",\"subId\":\"oj-dialog-body\"}");

			log("modalDialog"+modalDialog.toString());

			//Validate the dialog header
			waitForText("{\"element\":\"#modalDialog\",\"subId\":\"oj-dialog-header\"}","Tree in a Model Dialog (user-defined Header)");

			//Verify tree
			waitForElementVisible("id=treeModelDialog");

			WebElement treeMain = getElement("id=treeModelDialog");

			log("###### treeMain"+ treeMain);

			//Make sure blogs shows up expanded
			WebElement blogs = getElement("{\"element\":\"#treeModelDialog\",\"subId\":\"oj-tree-node['#blogs']['title']\"}");
			blogs.click();

			//Find the children of blogs node: Today
			WebElement today = getElement("{\"element\":\"#treeModelDialog\",\"subId\":\"oj-tree-node['#today']['title']\"}");

			//Find the children of blogs node: Yesterday
			WebElement yesterday = getElement("{\"element\":\"#treeModelDialog\",\"subId\":\"oj-tree-node['#yesterday']['title']\"}");

			//Close the dialog
			WebElement closeModalDialog = getElement("{\"element\":\"#modalDialog\",\"subId\":\"oj-dialog-header-close-wrapper\"}");
			log("closeModalDialog:"+closeModalDialog);
			closeModalDialog.click();

			//Make sure the dialog is not displayed anymore
			Assert.assertFalse(checkIfDisplayed(By.id("modalDialog")));
	}


	@Test(groups = { "tree" })
	public void testTreeInNestedPopup() throws Exception {

		//Start the test and bring up the browser
		startupTest("TreeDialogPopup.html",null);

		getWebDriver().manage().window().maximize();

		//Click the button nestedPopup and bring up the popup
		WebElement nestedPopup = getElement("id=nestedPopup");
		log("nestedPopup"+nestedPopup);

		waitForElementVisible("id=nestedPopup");
		nestedPopup.click();

		//Close the popup
		WebElement closePopup1 = getElement("id=closePopup1");
		closePopup1.click();

		//Make sure the dialog is closed and is not visible in screen
		Assert.assertFalse(checkIfDisplayed(By.id("popup1")));

		//Open the popup again
		nestedPopup = getElement("id=nestedPopup");
		waitForElementVisible("id=nestedPopup");
		nestedPopup.click();

		waitForElementVisible("id=openPopup2");
		WebElement openPopup2 = getElement("id=openPopup2");
		openPopup2.click();

		//Click the button closePopup2
		waitForElementVisible("id=closePopup2");
		WebElement closePopup2 = getElement("id=closePopup2");
		WebDriverWait wdw = new WebDriverWait(getWebDriver(),1000);
		wdw.until(ExpectedConditions.elementToBeClickable(closePopup2));
		try{
			closePopup2.click();
		}catch(Exception e){
			closePopup2 = getElement("id=closePopup2");
			closePopup2.click();
		}

		//Open both the popups again
		waitForElementVisible("id=nestedPopup");
		nestedPopup = getElement("id=nestedPopup");
		nestedPopup.click();

		waitForElementVisible("id=openPopup2");
		openPopup2 = getElement("id=openPopup2");
		wdw = new WebDriverWait(getWebDriver(),1000);
		wdw.until(ExpectedConditions.elementToBeClickable(openPopup2));
		openPopup2.click();

		//Verify tree data
		Assert.assertTrue(checkIfDisplayed(By.id("treeModelPopup1")));

		//Verify tree
		waitForElementVisible("id=treeModelPopup1");

		WebElement treeModelPopup1 = getElement("id=treeModelPopup1");

		log("###### treeModelPopup1:"+ treeModelPopup1);

		//Make sure blogs shows up expanded
		WebElement blogs = getElement("{\"element\":\"#treeModelPopup1\",\"subId\":\"oj-tree-node['#blogs']['title']\"}");
		log("blogs"+ blogs.isDisplayed());
		blogs.click();

	}


		@Test(groups = { "tree" })
			public void testTreeInModelessPopup() throws Exception {

				//Start the test and bring up the browser
				startupTest("TreeDialogPopup.html",null);

				//Click the button openModelessDialog and bring up the popup
				waitForElementVisible("id=openModelessDialog");
				WebElement openModelessDialog = getElement("id=openModelessDialog");
				openModelessDialog.click();

				//Escape the dialog to close it
				pressEscapeKey();

				//Open the dialog
				openModelessDialog = getElement("id=openModelessDialog");
				openModelessDialog.click();

				WebElement blogs = getElement("{\"element\":\"#treeModelDialog1\",\"subId\":\"oj-tree-node['#blogs11']['title']\"}");
				blogs.click();

				WebElement openAnotherModalDialog = getElement("id=openAnotherModalDialog");
				openAnotherModalDialog.click();

				//Escape the dialog to close it
				pressEscapeKey();

				//Verify tree data
				Assert.assertTrue(checkIfDisplayed(By.id("treeModelDialog1")));

				getElement("id=right1").click();

				WebElement treeModelDialog1 = getElement("id=treeModelDialog1");

				log("###### treeModelDialog1:"+ treeModelDialog1);

				blogs = getElement("{\"element\":\"#treeModelDialog1\",\"subId\":\"oj-tree-node['#blogs11']['title']\"}");
				blogs.click();

				WebElement oracle11 = getElement("{\"element\":\"#treeModelDialog1\",\"subId\":\"oj-tree-node['#oracle11']['title']\"}");
				Assert.assertFalse(oracle11.isDisplayed());

				waitForMilliseconds(2000L);

				log("right1:" + getElement("id=right1").isDisplayed());

				rightClickAndSelectMenuOption("id=right1","id=after");

				oracle11 = getElement("{\"element\":\"#treeModelDialog1\",\"subId\":\"oj-tree-node['#oracle11']['title']\"}");
				Assert.assertTrue(oracle11.isDisplayed());

				rightClickAndSelectMenuOption("id=right1","id=first");

				blogs = getElement("{\"element\":\"#treeModelDialog1\",\"subId\":\"oj-tree-node['#blogs11']['title']\"}");
				Assert.assertTrue(blogs.isDisplayed());

				WebElement links = getElement("{\"element\":\"#treeModelDialog1\",\"subId\":\"oj-tree-node['#links11']['disclosure']\"}");
				links.click();

				waitForMilliseconds(2000L);

				blogs = getElement("{\"element\":\"#treeModelDialog1\",\"subId\":\"oj-tree-node['#blogs11']['title']\"}");
				log("blogs.isDisplayed()"+blogs.isDisplayed());
				Assert.assertFalse(blogs.isDisplayed());

				WebElement closeMD1 = getElement("id=closeMD1");
				closeMD1.click();




		}








	private void log(String log)
	    {
	        System.out.println(log);

			getLogger().fine("[TreePopupDialogTest] " + log);
    }


}
