package oj.tests.tree;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class TreeLayoutTest extends OJetBase {

	private static final String TITLE_LAYOUT = "Tree Layout";



    public TreeLayoutTest() {
        super(new TestConfigBuilder().setContextRoot("tree").setAppRoot("treeLoad").build());
    }


    @Test(groups = { "tree" })
	public void loadPage() throws Exception {

			//Start the test and bring up the browser
			startupTest("TreeLayout.html",null);

			//Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
			verifyTitle("Incorrect page title;", TITLE_LAYOUT);

    }


    @Test(groups = { "tree" })
	public void testTreeUnderCollapsibleBox() throws Exception {

		//Start the test and bring up the browser
		startupTest("TreeLayout.html",null);

		waitForElementVisible("id=oj-collapsible-ojCollapsibleWithBox-header");

		WebElement collapsible = getElement("id=oj-collapsible-ojCollapsibleWithBox-header");
		log("###### collapsible"+ collapsible);

		waitForText("{\"element\":\"#ojCollapsibleWithBox\",\"subId\":\"oj-collapsible-header\"}","Tree with Expand OjCollapsible with class OjBox");

		//START TREE Validation

		//The tree should be visible initially as the collapsible is expanded
		waitForElementVisible("id=treeBoxLoadDatasource");

		WebElement news = getElement("{\"element\":\"#treeBoxLoadDatasource\",\"subId\":\"oj-tree-node['#news']['title']\"}");
		log("**************news"+news.isDisplayed());
		news.click();

		WebElement home = getElement("{\"element\":\"#treeBoxLoadDatasource\",\"subId\":\"oj-tree-node['#home']['title']\"}");
		log("**************home"+home.isDisplayed());
		home.click();

		//Make sure blogs shows up collapsed
		WebElement blogs = getElement("{\"element\":\"#treeBoxLoadDatasource\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

		//Expanded the blogs node
		blogs.click();

		//Find the children of blogs node: Today
		waitForElementVisible("{\"element\":\"#treeBoxLoadDatasource\",\"subId\":\"oj-tree-node['#today']['title']\"}");

		WebElement today = getElement("{\"element\":\"#treeBoxLoadDatasource\",\"subId\":\"oj-tree-node['#today']['title']\"}");
		Assert.assertEquals(today.isDisplayed(),true);

		blogs = getElement("{\"element\":\"#treeBoxLoadDatasource\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

		//Collapsed the blogs node
		blogs.click();

		//END TREE Validation


		//Collapse the collapsible
		WebElement collapIcon = getElement("{\"element\":\"#ojCollapsibleWithBox\",\"subId\":\"oj-collapsible-header-icon\"}");
		log("collapIcon: " + collapIcon);

		Assert.assertNotNull(collapIcon);

		collapIcon.click();  // This should expand the Collapsible

		//Now that the collapsible is collapsed then the tree should not be available.

		boolean result = checkIfDisplayed(By.id("treeBoxLoadDatasource"));
		Assert.assertFalse(result);



	}

	 @Test(groups = { "tree" })
		public void testTreeUnderOjCollapsible() throws Exception {

			//Start the test and bring up the browser
			startupTest("TreeLayout.html",null);

			//Collapse the first collapsible
			waitForElementVisible("id=oj-collapsible-ojCollapsibleWithBox-header");
			WebElement ojCollapsibleWithBoxIcon = getElement("{\"element\":\"#ojCollapsibleWithBox\",\"subId\":\"oj-collapsible-header-icon\"}");
			Assert.assertNotNull(ojCollapsibleWithBoxIcon);
			ojCollapsibleWithBoxIcon.click();


			//Expand the collapsible
			waitForElementVisible("id=oj-collapsible-ojCollapsibleOuter-header");
			waitForText("{\"element\":\"#ojCollapsibleOuter\",\"subId\":\"oj-collapsible-header\"}","OjCollapsible");

			waitForElementVisible("id=ojCollapsibleOuter");
			WebElement ojCollapsibleOuterIcon = getElement("{\"element\":\"#ojCollapsibleOuter\",\"subId\":\"oj-collapsible-header-icon\"}");
			Assert.assertNotNull(ojCollapsibleOuterIcon);
			ojCollapsibleOuterIcon.click();  // This should expand the Collapsible

			waitForMilliseconds(2000L);

			//Now that the collapsible is expanded  the tree should  be available.
			boolean result = false;
			result = checkIfDisplayed(By.id("treeLoadDatasource"));
			Assert.assertTrue(result);

			log("treeLoadDatasource:" + getElement("id=treeLoadDatasource"));
			log("treeLoadDatasource displayed:"+ getElement("id=treeLoadDatasource").isDisplayed());

			//START TREE Validation

			//The tree should be visible initially as the collapsible is expanded
			waitForElementVisible("id=treeLoadDatasource");

			WebElement news = getElement("{\"element\":\"#treeLoadDatasource\",\"subId\":\"oj-tree-node['#news']['title']\"}");
			log("**************news"+news);
			log("**************news isDisplayed"+news.isDisplayed());
			news.click();

			WebElement home = getElement("{\"element\":\"#treeLoadDatasource\",\"subId\":\"oj-tree-node['#home']['title']\"}");
			log("**************home"+home.isDisplayed());
			home.click();


			//Make sure blogs shows up collapsed
			WebElement blogs = getElement("{\"element\":\"#treeLoadDatasource\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

			//Expanded the blogs node
			blogs.click();

			//Find the children of blogs node: Today
			waitForElementVisible("{\"element\":\"#treeLoadDatasource\",\"subId\":\"oj-tree-node['#today']['title']\"}");

			WebElement today = getElement("{\"element\":\"#treeLoadDatasource\",\"subId\":\"oj-tree-node['#today']['title']\"}");
			Assert.assertEquals(today.isDisplayed(),true);

			blogs = getElement("{\"element\":\"#treeLoadDatasource\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

			//Collapsed the blogs node
			blogs.click();

			//END TREE Validation


			//Collapse the collapsible
			ojCollapsibleOuterIcon.click();  // This should expand the Collapsible

			//Need to wait for page to be loaded
			waitForMilliseconds(1000L);

			//Now that the collapsible is expanded  the tree should  be available.
			result = checkIfDisplayed(By.id("treeLoadDatasource"));
			Assert.assertFalse(result);

	}


	@Test(groups = { "tree" })
	public void testTreeUnderNestedCollapsibleWithBox() throws Exception {

		//Start the test and bring up the browser
		startupTest("TreeLayout.html",null);

		//Collapse the first collapsible
		waitForElementVisible("id=oj-collapsible-ojCollapsibleWithBox-header");
		WebElement ojCollapsibleWithBoxIcon = getElement("{\"element\":\"#ojCollapsibleWithBox\",\"subId\":\"oj-collapsible-header-icon\"}");
		Assert.assertNotNull(ojCollapsibleWithBoxIcon);
		ojCollapsibleWithBoxIcon.click();


		waitForElementVisible("id=oj-collapsible-outerNestedCollapsibleWithBox-header");

		//Collapse the first collapsible
		waitForElementVisible("id=oj-collapsible-ojCollapsibleWithBox-header");
		waitForElementVisible("id=outerNestedCollapsibleWithBox");
		waitForText("{\"element\":\"#outerNestedCollapsibleWithBox\",\"subId\":\"oj-collapsible-header\"}","Tree under Nested Collapsible Box");

		WebElement outerNestedCollapsibleWithBoxIcon = getElement("{\"element\":\"#outerNestedCollapsibleWithBox\",\"subId\":\"oj-collapsible-header-icon\"}");
		Assert.assertNotNull(outerNestedCollapsibleWithBoxIcon);
		outerNestedCollapsibleWithBoxIcon.click();  // This should expand the Collapsible

		waitForElementVisible("id=ojCollapsibleNestedBox");
		waitForElementVisible("{\"element\":\"#ojCollapsibleNestedBox\",\"subId\":\"oj-collapsible-header-icon\"}");

		WebElement ojCollapsibleNestedBoxIcon = getElement("{\"element\":\"#ojCollapsibleNestedBox\",\"subId\":\"oj-collapsible-header-icon\"}");
		log("ojCollapsibleNestedBoxIcon: " + ojCollapsibleNestedBoxIcon);

		Assert.assertNotNull(ojCollapsibleNestedBoxIcon);

		ojCollapsibleNestedBoxIcon.click();  // This should expand the Inner Collapsible

		waitForMilliseconds(3000L);

		//Now that the collapsible is expanded  the tree should  be available.
		boolean result = checkIfDisplayed(By.id("treeNestedBoxLoadDatasource"));
		Assert.assertTrue(result);


		//START TREE Validation

		//The tree should be visible initially as the collapsible is expanded
		waitForElementVisible("id=treeNestedBoxLoadDatasource");

		waitForElementVisible("{\"element\":\"#treeNestedBoxLoadDatasource\",\"subId\":\"oj-tree-node['#news']['title']\"}");

		WebElement news = getElement("{\"element\":\"#treeNestedBoxLoadDatasource\",\"subId\":\"oj-tree-node['#news']['title']\"}");
		log("**************news"+news.isDisplayed());
		news.click();

		WebElement home = getElement("{\"element\":\"#treeNestedBoxLoadDatasource\",\"subId\":\"oj-tree-node['#home']['title']\"}");
		log("**************home"+home.isDisplayed());
		home.click();

		//Make sure blogs shows up collapsed
		WebElement blogs = getElement("{\"element\":\"#treeNestedBoxLoadDatasource\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

		//Expanded the blogs node
		blogs.click();

		//Find the children of blogs node: Today
		waitForElementVisible("{\"element\":\"#treeNestedBoxLoadDatasource\",\"subId\":\"oj-tree-node['#today']['title']\"}");

		WebElement today = getElement("{\"element\":\"#treeNestedBoxLoadDatasource\",\"subId\":\"oj-tree-node['#today']['title']\"}");
		Assert.assertEquals(today.isDisplayed(),true);

		blogs = getElement("{\"element\":\"#treeNestedBoxLoadDatasource\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

		//Collapsed the blogs node
		blogs.click();

		//END TREE Validation


		//Collapse the collapsible
		outerNestedCollapsibleWithBoxIcon.click();

		//Need to wait for page to be loaded
		waitForMilliseconds(2000L);

		//Now that the collapsible is collapsed  the tree should NOT be available.
		result = checkIfDisplayed(By.id("treeNestedBoxLoadDatasource"));
		Assert.assertFalse(result);




	}

	@Test(groups = { "tree" })
	public void testTreeUnderNestedCollapsible() throws Exception {

			//Start the test and bring up the browser
			startupTest("TreeLayout.html",null);

			//Collapse the first collapsible
			waitForElementVisible("id=oj-collapsible-ojCollapsibleWithBox-header");
			WebElement ojCollapsibleWithBoxIcon = getElement("{\"element\":\"#ojCollapsibleWithBox\",\"subId\":\"oj-collapsible-header-icon\"}");
			Assert.assertNotNull(ojCollapsibleWithBoxIcon);
			ojCollapsibleWithBoxIcon.click();

			//Collapse the first nested collapsible
			waitForElementVisible("id=oj-collapsible-outerCollapsible-header");
			waitForElementVisible("id=outerCollapsible");
			waitForText("{\"element\":\"#outerCollapsible\",\"subId\":\"oj-collapsible-header\"}","Nested Collapsible");

			WebElement outerCollapsibleIcon = getElement("{\"element\":\"#outerCollapsible\",\"subId\":\"oj-collapsible-header-icon\"}");
			Assert.assertNotNull(outerCollapsibleIcon);
			outerCollapsibleIcon.click();  // This should expand the Collapsible

			waitForElementVisible("id=ojCollapsibleNested");
			waitForElementVisible("id=oj-collapsible-ojCollapsibleNested-header");
			WebElement ojCollapsibleNestedIcon = getElement("{\"element\":\"#ojCollapsibleNested\",\"subId\":\"oj-collapsible-header-icon\"}");
			log("ojCollapsibleNestedIcon: " + ojCollapsibleNestedIcon);
			Assert.assertNotNull(ojCollapsibleNestedIcon);
			ojCollapsibleNestedIcon.click();  // This should expand the Inner Collapsible

			//Need to wait for page to be loaded
			waitForMilliseconds(1000L);

			//Now that the collapsible is expanded  the tree should  be available.
			boolean result = checkIfDisplayed(By.id("treeNestedLoadDatasourceAcc"));
			Assert.assertTrue(result);


			//START TREE Validation

			//The tree should be visible initially as the collapsible is expanded
			waitForElementVisible("id=treeNestedLoadDatasourceAcc");

			WebElement news = getElement("{\"element\":\"#treeNestedLoadDatasourceAcc\",\"subId\":\"oj-tree-node['#news']['title']\"}");
			log("**************news"+news.isDisplayed());
			news.click();

			WebElement home = getElement("{\"element\":\"#treeNestedLoadDatasourceAcc\",\"subId\":\"oj-tree-node['#home']['title']\"}");
			log("**************home"+home.isDisplayed());
			home.click();

			//Make sure blogs shows up collapsed
			WebElement blogs = getElement("{\"element\":\"#treeNestedLoadDatasourceAcc\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

			//Expanded the blogs node
			blogs.click();

			//Find the children of blogs node: Today
			waitForElementVisible("{\"element\":\"#treeNestedLoadDatasourceAcc\",\"subId\":\"oj-tree-node['#today']['title']\"}");

			WebElement today = getElement("{\"element\":\"#treeNestedLoadDatasourceAcc\",\"subId\":\"oj-tree-node['#today']['title']\"}");
			Assert.assertEquals(today.isDisplayed(),true);

			blogs = getElement("{\"element\":\"#treeNestedLoadDatasourceAcc\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

			//Collapsed the blogs node
			blogs.click();

			//END TREE Validation


			//Collapse the collapsible
			outerCollapsibleIcon.click();

			//Need to wait for page to be loaded
			waitForMilliseconds(1000L);

			//Now that the collapsible is expanded  the tree should  be available.
			result = checkIfDisplayed(By.id("treeNestedLoadDatasourceAcc"));
			Assert.assertFalse(result);

		}


		 @Test(groups = { "tree" })
			public void testCollapseOrExpandCollapsibleButton() throws Exception {

				//Start the test and bring up the browser
				startupTest("TreeLayout.html",null);

				waitForElementVisible("id=oj-collapsible-ojCollapsibleWithBox-header");

				WebElement collapsible = getElement("id=oj-collapsible-ojCollapsibleWithBox-header");
				log("###### collapsible"+ collapsible);

				waitForText("{\"element\":\"#ojCollapsibleWithBox\",\"subId\":\"oj-collapsible-header\"}","Tree with Expand OjCollapsible with class OjBox");

				//START TREE Validation

				//The tree should be visible initially as the collapsible is expanded
				waitForElementVisible("id=treeBoxLoadDatasource");

				WebElement news = getElement("{\"element\":\"#treeBoxLoadDatasource\",\"subId\":\"oj-tree-node['#news']['title']\"}");
				log("**************news"+news.isDisplayed());
				news.click();

				WebElement home = getElement("{\"element\":\"#treeBoxLoadDatasource\",\"subId\":\"oj-tree-node['#home']['title']\"}");
				log("**************home"+home.isDisplayed());
				home.click();

				//Make sure blogs shows up collapsed
				WebElement blogs = getElement("{\"element\":\"#treeBoxLoadDatasource\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

				//Expanded the blogs node
				blogs.click();

				//Find the children of blogs node: Today
				waitForElementVisible("{\"element\":\"#treeBoxLoadDatasource\",\"subId\":\"oj-tree-node['#today']['title']\"}");

				WebElement today = getElement("{\"element\":\"#treeBoxLoadDatasource\",\"subId\":\"oj-tree-node['#today']['title']\"}");
				Assert.assertEquals(today.isDisplayed(),true);

				blogs = getElement("{\"element\":\"#treeBoxLoadDatasource\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

				//Collapsed the blogs node
				blogs.click();

				//END TREE Validation


				//Collapse the collapsible
				WebElement collapseOrExpandCollapsibleBtn = getElement("id=collapseOrExpandCollapsible");
				Assert.assertNotNull(collapseOrExpandCollapsibleBtn);

				collapseOrExpandCollapsibleBtn.click();  // This should expand the Collapsible

				//Now that the collapsible is collapsed then the tree should not be available.

				//Need to wait for page to be loaded
				waitForMilliseconds(1000L);

				boolean result = checkIfDisplayed(By.id("treeBoxLoadDatasource"));
				Assert.assertFalse(result);



			}

			 @Test(groups = { "tree" })
			public void addAndRemoveContextMenu() throws Exception {

				//Start the test and bring up the browser
				startupTest("TreeLayout.html",null);

				waitForElementVisible("id=oj-collapsible-ojCollapsibleWithBox-header");

				WebElement collapsible = getElement("id=oj-collapsible-ojCollapsibleWithBox-header");
				log("###### collapsible"+ collapsible);

				waitForText("{\"element\":\"#ojCollapsibleWithBox\",\"subId\":\"oj-collapsible-header\"}","Tree with Expand OjCollapsible with class OjBox");

				//START TREE Validation

				//The tree should be visible initially as the collapsible is expanded
				waitForElementVisible("id=treeBoxLoadDatasource");

				WebElement news = getElement("{\"element\":\"#treeBoxLoadDatasource\",\"subId\":\"oj-tree-node['#news']['title']\"}");
				log("**************news"+news.isDisplayed());
				news.click();

				//Add  context Menu
				WebElement addContextmenu = getElement("id=addContextmenu");
				Assert.assertNotNull(addContextmenu);

				addContextmenu.click();  // This should add a context menu to collapsible


				String blogs1Locator = "{\"element\":\"#treeBoxLoadDatasource\",\"subId\":\"oj-tree-node['#blogs']['title']\"}";

				//Click Before menu item
				rightClickAndSelectMenuOption(blogs1Locator,"id=ui-id-1");

				waitForText("id=resultsLayoutContainer","Selected menu Item: Before");

				WebElement removeContextmenu = getElement("id=removeContextmenu");
				removeContextmenu.click();

				boolean menuDisplayed = true;

				try{
					rightClickAndSelectMenuOption(blogs1Locator,"id=ui-id-1");
				}catch(org.openqa.selenium.WebDriverException nse){
					 menuDisplayed = false;
				}

				Assert.assertEquals(menuDisplayed,false);


			}






	private void log(String log)
	    {
	        System.out.println(log);

			getLogger().fine("[TreeLayoutTest] " + log);
    }


}
