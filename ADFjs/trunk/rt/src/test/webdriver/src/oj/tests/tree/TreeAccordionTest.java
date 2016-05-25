package oj.tests.tree;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;

public class TreeAccordionTest extends OJetBase {

	private static final String TITLE_AC = "Tree Accordion";



    public TreeAccordionTest() {
        super(new TestConfigBuilder().setContextRoot("tree").setAppRoot("treeLoad").build());
    }


    @Test(groups = { "tree" })
	public void loadPage() throws Exception {

			//Start the test and bring up the browser
			startupTest("TreeAccordion.html",null);

			//Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
			verifyTitle("Incorrect page title;", TITLE_AC);

    }


    @Test(groups = { "tree" }, dependsOnMethods = { "loadPage"})
	public void testTreeUnderAccCollap() throws Exception {

		//Start the test and bring up the browser
		startupTest("TreeAccordion.html",null);

		waitForElementVisible("id=oj-collapsible-collapsibleWithBox-header");

		WebElement collapsible = getElement("id=oj-collapsible-collapsibleWithBox-header");
		log("###### collapsible"+ collapsible);

		WebElement collapIcon = getElement("{\"element\":\"#collapsibleWithBox\",\"subId\":\"oj-collapsible-header-icon\"}");
		log("collapIcon: " + collapIcon);

		Assert.assertNotNull(collapIcon);

		collapIcon.click();  // This should expand the Collapsible

		WebElement innerCollapsible = getElement("id=oj-collapsible-ojCollapsible-header");
		log("###### innerCollapsible"+ innerCollapsible);

		waitForElementVisible("{\"element\":\"#ojCollapsible\",\"subId\":\"oj-collapsible-header-icon\"}");
		WebElement innerCollapIcon = getElement("{\"element\":\"#ojCollapsible\",\"subId\":\"oj-collapsible-header-icon\"}");
		log("innerCollapIcon: " + innerCollapIcon);

		Assert.assertNotNull(innerCollapIcon);

		innerCollapIcon.click();  // This should expand the Collapsible blogs

		waitForElementVisible("id=treeNestedBoxLoadDatasource");

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

    }

	@Test(groups = { "tree" }, dependsOnMethods = { "testTreeUnderAccCollap"})
	public void testTreeUnderNestedAccordion() throws Exception {

		//Start the test and bring up the browser
		startupTest("TreeAccordion.html",null);

		waitForElementVisible("id=oj-collapsible-c4-header");

		WebElement accordion = getElement("id=oj-collapsible-c4-header");
		log("###### accordion: 	" + accordion);

		WebElement c4 = getElement("{\"element\":\"#c4\",\"subId\":\"oj-collapsible-header-icon\"}");
		log("c4: " + c4);

		Assert.assertNotNull(c4);

		c4.click();  // This should expand the Collapsible

		WebElement nestedAccordion = getElement("id=oj-collapsible-ic1-header");

		log("###### nestedAccordion: 	" + nestedAccordion);
		waitForElementVisible("{\"element\":\"#ic1\",\"subId\":\"oj-collapsible-header-icon\"}");
		WebElement ic1 = getElement("{\"element\":\"#ic1\",\"subId\":\"oj-collapsible-header-icon\"}");
		log("ic1: " + ic1);

		Assert.assertNotNull(ic1);

		ic1.click();  // This should expand the Collapsible

		waitForMilliseconds(2000L);
		waitForElementVisible("id=treeAcc1LoadDatasource");

		WebElement news = getElement("{\"element\":\"#treeAcc1LoadDatasource\",\"subId\":\"oj-tree-node['#news']['title']\"}");
		log("**************news"+news.isDisplayed());
		waitForElementVisible("{\"element\":\"#treeAcc1LoadDatasource\",\"subId\":\"oj-tree-node['#news']['title']\"}");
		news.click();

		WebElement home = getElement("{\"element\":\"#treeAcc1LoadDatasource\",\"subId\":\"oj-tree-node['#home']['title']\"}");
		log("**************home"+home.isDisplayed());


		//Make sure blogs shows up collapsed
		WebElement blogs = getElement("{\"element\":\"#treeAcc1LoadDatasource\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

		//Expanded the blogs node
		blogs.click();

		//Find the children of blogs node: Today
		waitForElementVisible("{\"element\":\"#treeAcc1LoadDatasource\",\"subId\":\"oj-tree-node['#today']['title']\"}");

		WebElement today = getElement("{\"element\":\"#treeAcc1LoadDatasource\",\"subId\":\"oj-tree-node['#today']['title']\"}");
		Assert.assertEquals(today.isDisplayed(),true);

		blogs = getElement("{\"element\":\"#treeAcc1LoadDatasource\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

		//Collapsed the blogs node
		blogs.click();

		WebElement nestedAccordion2 = getElement("id=oj-collapsible-ic2-header");
		log("###### nestedAccordion2: 	" + nestedAccordion2);

		waitForElementVisible("{\"element\":\"#ic2\",\"subId\":\"oj-collapsible-header-icon\"}");
		WebElement ic2 = getElement("{\"element\":\"#ic2\",\"subId\":\"oj-collapsible-header-icon\"}");
		Assert.assertNotNull(ic2);
		ic2.click();  // This should expand the nested Collapsible

		waitForMilliseconds(3000L);

		try{

		waitForElementVisible("id=treeAcc2LoadDatasource");
		}catch(Exception e){
			ic2 = getElement("{\"element\":\"#ic2\",\"subId\":\"oj-collapsible-header-icon\"}");
			Assert.assertNotNull(ic2);
			ic2.click();  // This should expand the nested Collapsible

		}

		WebElement news1 = getElement("{\"element\":\"#treeAcc2LoadDatasource\",\"subId\":\"oj-tree-node['#news']['title']\"}");
		waitForElementVisible("{\"element\":\"#treeAcc2LoadDatasource\",\"subId\":\"oj-tree-node['#news']['title']\"}");
		log("**************news"+news1.isDisplayed());

		WebElement home1 = getElement("{\"element\":\"#treeAcc2LoadDatasource\",\"subId\":\"oj-tree-node['#home']['title']\"}");
		log("**************home"+home1.isDisplayed());


		//Make sure blogs shows up collapsed
		WebElement blogs1 = getElement("{\"element\":\"#treeAcc2LoadDatasource\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

		//Need to wait for element to be clickable
		waitForMilliseconds(1000L);

		//Expanded the blogs node
		blogs1.click();

		//Find the children of blogs node: Today
		waitForElementVisible("{\"element\":\"#treeAcc2LoadDatasource\",\"subId\":\"oj-tree-node['#today']['title']\"}");

		WebElement today1 = getElement("{\"element\":\"#treeAcc2LoadDatasource\",\"subId\":\"oj-tree-node['#today']['title']\"}");
		Assert.assertEquals(today1.isDisplayed(),true);

		blogs1 = getElement("{\"element\":\"#treeAcc2LoadDatasource\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

		//Collapsed the blogs node
		blogs1.click();

		ic2.click();  // This should collapse the nested accordion


    }


    @Test(groups = { "tree" }, dependsOnMethods = { "testTreeUnderNestedAccordion"})
	public void treeTreeinTabs() throws Exception {

			//Start the test and bring up the browser
			startupTest("TreeAccordion.html",null);

			waitForElementVisible("id=sort-tabs-1");

			//Find the tree in tab1
			//Find the tree element
			WebElement tree = getElement("id=treeTab1");
			log("Found Element for id=treeTab1 " + tree.toString());

			boolean isDisplayed = tree.isDisplayed();

			log("Tree visible" + isDisplayed);

			Assert.assertEquals(isDisplayed, true);

			WebElement tab2 = getElement("{\"element\":\"#sortTabs\",\"subId\":\"oj-tabs-title\",\"index\":1}");

			log("tab2"+tab2);

			//Click tab2
			tab2.click();

			tree = getElement("id=treeTab1");
						log("Found Element for id=treeTab1 " + tree.toString());

			isDisplayed = tree.isDisplayed();

			log("Tree visible" + isDisplayed);
			Assert.assertEquals(isDisplayed, false);

			waitForText("sort-tabs-2","Sortable Tab 2 Content");

			WebElement tab1 = getElement("{\"element\":\"#sortTabs\",\"subId\":\"oj-tabs-title\",\"index\":0}");

			log("tab1"+tab1);

			//Click tab1
			tab1.click();

			tree = getElement("id=treeTab1");
						log("Found Element for id=treeTab1 " + tree.toString());

			isDisplayed = tree.isDisplayed();

			log("Tree visible" + isDisplayed);
			Assert.assertEquals(isDisplayed, true);

			WebElement news = getElement("{\"element\":\"#treeTab1\",\"subId\":\"oj-tree-node['#news']['title']\"}");
			log("**************news"+news.isDisplayed());
			news.click();

			WebElement home = getElement("{\"element\":\"#treeTab1\",\"subId\":\"oj-tree-node['#home']['title']\"}");
			log("**************home"+home.isDisplayed());
			home.click();

    }

    	@Test(groups = { "tree" }, dependsOnMethods = { "treeTreeinTabs"})
		public void changeTabOrientation() throws Exception {

				//Start the test and bring up the browser
				startupTest("TreeAccordion.html",null);

				waitForElementVisible("id=sort-tabs-1");

				//Find the tree in tab1
				//Find the tree element
				WebElement tree = getElement("id=treeTab1");
				log("Found Element for id=treeTab1 " + tree.toString());

				boolean isDisplayed = tree.isDisplayed();

				log("Tree visible" + isDisplayed);

				Assert.assertEquals(isDisplayed, true);

				WebElement tab2 = getElement("{\"element\":\"#sortTabs\",\"subId\":\"oj-tabs-tab\",\"index\":1}");

				log("tab2"+tab2);

				//Click tab2
				tab2.click();

				WebElement btnTab = getElement("id=changeTabOrientation");
				btnTab.click();

				//Verify tab contents after the tab orientation change

				WebElement tab1 = getElement("{\"element\":\"#sortTabs\",\"subId\":\"oj-tabs-title\",\"index\":0}");

				log("tab1"+tab1);

				//Click tab1
				tab1.click();

				tree = getElement("id=treeTab1");
							log("Found Element for id=treeTab1 " + tree.toString());

				isDisplayed = tree.isDisplayed();

				log("Tree visible" + isDisplayed);
				Assert.assertEquals(isDisplayed, true);

				WebElement news = getElement("{\"element\":\"#treeTab1\",\"subId\":\"oj-tree-node['#news']['title']\"}");
				log("**************news"+news.isDisplayed());
				news.click();

				WebElement home = getElement("{\"element\":\"#treeTab1\",\"subId\":\"oj-tree-node['#home']['title']\"}");
				log("**************home"+home.isDisplayed());
				home.click();


		}



    private void commonTestForTree(){

			// Finds the blogs node and make sure its not null
			waitForElementVisible("{\"element\":\"#treeTab1\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");
			WebElement blogs = getElement("{\"element\":\"#treeTab1\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

			log("Node: " + blogs);

			Assert.assertNotNull(blogs);

			blogs.click();  // This should expand the node blogs

			log("clicked");

			//Find the children of blogs node: Today
			WebElement today = getElement("{\"element\":\"#treeTab1\",\"subId\":\"oj-tree-node['#today']['title']\"}");

			//Find the children of blogs node: Yesterday
			WebElement yesterday = getElement("{\"element\":\"#treeTab1\",\"subId\":\"oj-tree-node['#yesterday']['title']\"}");

			//Find the children of blogs node: 2DaysBack
			WebElement twodaysback = getElement("{\"element\":\"#treeTab1\",\"subId\":\"oj-tree-node['#2daysback']['title']\"}");


			//Find the children of blogs node: Archive
			WebElement archive = getElement("{\"element\":\"#treeTab1\",\"subId\":\"oj-tree-node['#archive']['title']\"}");

	}



	private void log(String log)
	    {
	        System.out.println(log);

			getLogger().fine("[TreeAccordionTest] " + log);
    }


}
