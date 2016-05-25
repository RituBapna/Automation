package oj.tests.tree;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;

public class TreeLoadTest extends OJetBase {

	private static final String TITLE_HC = "Tree Loads the Hardcoded json data";
	private static final String TITLE_REMOTEDS = "Tree Using remote Json datasource with Ref and Sup expanded";
	private static final String TITLE_FUNCTCALL = "Tree Using Json using Function Call with no ID";
	private static final String TITLE_TRANSDATA = "Tree Using transformed Json data";
	private static final String TITLE_GETFUNCT = "Tree Using Json file load via Get Function Return";
	private static final String TITLE_GETURL = "Tree Using remote Json data via direct URL";
	private static final String TITLE_HTMLAJAXURL = "Tree Using Html Ajax file load via Direct URL";
	private static final String TITLE_INLINEHTML = "Tree Using Html inline file";
	private static final String RESULTS_CONTAINER = "resultsContainer";

	private static final String CURRENT_PAGE = "currentPage";


    public TreeLoadTest() {
        super(new TestConfigBuilder().setContextRoot("tree").setAppRoot("treeLoad").build());
    }


    @Test(groups = { "tree" })
	    public void testLoadHCData() throws Exception {

			//Start the test and bring up the browser
			startupTest("treeLoadHCData.html",null);

			//Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
			verifyTitle("Incorrect page title;", TITLE_HC);
			checkPageContent(TITLE_HC);

			commonLoadTestForJson();

    }

    @Test(groups = { "tree" })
	public void testLoadRemoteDSWithInitExpand() throws Exception {

		//Start the test and bring up the browser
		startupTest("treeLoadRemoteDSWithInitExpand.html",null);

		//Verify the url
		String url = getBrowserUrl();
		log("URL##########"+ url);

		// Verify if the title of the page is correct
		verifyTitle("Incorrect page title;", TITLE_REMOTEDS);
		checkPageContent(TITLE_REMOTEDS);

		commonLoadTestForJson();

		//Make sure we check on init expanded objects

		WebElement refsAll = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#refsall']['title']\"}");

		WebElement refsUSA = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#refsusa']['title']\"}");

		WebElement refsEurope = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#refseurope']['title']\"}");

		WebElement supgold = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#supgold']['title']\"}");

		WebElement supsilver = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#supsilver']['title']\"}");



    }

    @Test(groups = { "tree" })
	public void testLoadFunctCall() throws Exception {

			//Start the test and bring up the browser
			startupTest("treeLoadFunctCall.html",null);

			//Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
			verifyTitle("Incorrect page title;", TITLE_FUNCTCALL);
			checkPageContent(TITLE_FUNCTCALL);

			commonLoadTestForJson();


    }

    @Test(groups = { "tree" })
	public void testLoadUsingTransformedData() throws Exception {

			//Start the test and bring up the browser
			startupTest("treeLoadUsingTransformedData.html",null);

			//Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
			verifyTitle("Incorrect page title;", TITLE_TRANSDATA);
			checkPageContent(TITLE_TRANSDATA);

			commonLoadTestForJson();


    }


    @Test(groups = { "tree" })
		public void testLoadViaGetFunctCall() throws Exception {

				//Start the test and bring up the browser
				startupTest("treeLoadViaGetFunctCall.html",null);

				//Verify the url
				String url = getBrowserUrl();
				log("URL##########"+ url);

				// Verify if the title of the page is correct
				verifyTitle("Incorrect page title;", TITLE_GETFUNCT);
				checkPageContent(TITLE_GETFUNCT);

				commonLoadTestForJson();


    }

    @Test(groups = { "tree" })
		public void testLoadViaGetURL() throws Exception {

				//Start the test and bring up the browser
				startupTest("treeLoadViaGetURL.html",null);

				//Verify the url
				String url = getBrowserUrl();
				log("URL##########"+ url);

				// Verify if the title of the page is correct
				verifyTitle("Incorrect page title;", TITLE_GETURL);
				checkPageContent(TITLE_GETURL);

				commonLoadTestForJson();


    }

    @Test(groups = { "tree" })
		public void testLoadHtmlViaAjax() throws Exception {

				//Start the test and bring up the browser
				startupTest("treeLoadHtmlViaAjax.html",null);

				//Verify the url
				String url = getBrowserUrl();
				log("URL##########"+ url);

				// Verify if the title of the page is correct
				verifyTitle("Incorrect page title;", TITLE_HTMLAJAXURL);
				checkPageContent(TITLE_HTMLAJAXURL);

				commonLoadTestForHtml();
    }


    @Test(groups = { "tree" })
	public void testLoadViaInlineHtml() throws Exception {

			//Start the test and bring up the browser
			startupTest("treeLoadViaInlineHtml.html",null);

			//Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
			verifyTitle("Incorrect page title;", TITLE_INLINEHTML);
			checkPageContent(TITLE_INLINEHTML);

			//Find the tree element
			WebElement tree = getElement("id=tree");
			log("Found Element for id=tree " + tree.toString());

			commonLoadTestForHtml();

    }

    private void commonLoadTestForJson(){

		//Find the tree element
		WebElement tree = getElement("id=tree");
		log("Found Element for id=tree " + tree.toString());

		//Make sure News attributes are correct
		WebElement news = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#news']['title']\"}");
		Assert.assertNotNull(news);
		news.click();

		String content = getText(RESULTS_CONTAINER);
		log("content"+content);
        content.contains("id=news");



		// Finds the blogs node and make sure its not null
		waitForElementVisible("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");
		WebElement blogs = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

		log("Node: " + blogs);

		Assert.assertNotNull(blogs);

		blogs.click();  // This should expand the node blogs

		log("clicked");

		//Find the children of blogs node: Today
		WebElement today = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#today']['title']\"}");

		//Find the children of blogs node: Yesterday
		WebElement yesterday = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#yesterday']['title']\"}");

		//Find the children of blogs node: 2DaysBack
		WebElement twodaysback = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#2daysback']['title']\"}");


		//Find the children of blogs node: Archive
		WebElement archive = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#archive']['title']\"}");

	}

	private void commonLoadTestForHtml(){


		//Find the tree element
		WebElement tree = getElement("id=tree");
		log("Found Element for id=tree " + tree.toString());

		//Make sure News attributes are correct
		WebElement news = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#news']['title']\"}");
		Assert.assertNotNull(news);
		news.click();

		String content = getText(RESULTS_CONTAINER);
		log("content"+content);
        waitForText(RESULTS_CONTAINER, "id=news");


		// Finds the blogs node and make sure its not null
		waitForElementVisible("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");
		WebElement blogs = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

		log("Node: " + blogs);

		Assert.assertNotNull(blogs);

		blogs.click();  // This should expand the node blogs

		//Find the children of blogs node: Today
		WebElement today = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#today']['title']\"}");

		//Find the children of blogs node: Previous
		WebElement previous = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#prev']['title']\"}");

		WebElement previousDiscIcon = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#prev']['disclosure']\"}");

		Assert.assertNotNull(previousDiscIcon);

		//Click on Previous Node
		waitForElementVisible("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#prev']['disclosure']\"}");
		previousDiscIcon.click();

		//Find the children of previous node: Yesterday
		WebElement yesterday = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#yesterday']['title']\"}");

		//Find the children of previous node: 2DaysBack
		WebElement twodaysback = getElement("{\"element\":\"#tree\",\"subId\":\"oj-tree-node['#daysback2']['title']\"}");




	}

    private void checkPageContent(String expectedContent)
	    {
	        String content = getText(CURRENT_PAGE);
	        log("content #### "+ content);
	        waitForText(CURRENT_PAGE, expectedContent);
	    }

	private void log(String log)
	    {
	        System.out.println("[TreeLoadTest] " + log);
	        getLogger().fine("[TreeLoadTest] " + log);
    }


}
