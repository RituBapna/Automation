package oj.tests.tree;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;

public class TreeFunctionTest extends OJetBase {

	private static final String TITLE_FUNC = "Tree Functions";
	private static final String RESULTS_CONTAINER = "resultsFunction";
	private static final String RESULTS1_CONTAINER = "resultsFunction1";



    public TreeFunctionTest() {
        super(new TestConfigBuilder().setContextRoot("tree").setAppRoot("treeLoad").build());
    }


    @Test(groups = { "tree" })
	public void loadPage() throws Exception {

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			//Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
			verifyTitle("Incorrect page title;", TITLE_FUNC);

    }



//, dependsOnMethods = { "loadPage" }

    @Test(groups = { "tree" })
		public void testTreeMain() throws Exception {

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			waitForElementVisible("id=treeFunctionMain");

			WebElement treeMain = getElement("id=treeFunctionMain");

			log("###### treeMain"+ treeMain);

			//Make sure blogs shows up expanded
			WebElement blogs = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#blogs']['title']\"}");

			//Find the children of blogs node: Today
			WebElement today = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#today']['title']\"}");

			//Find the children of blogs node: Yesterday
			WebElement yesterday = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#yesterday']['title']\"}");

			//Find the element North
			WebElement north = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#msusanorth']['title']\"}");


			//Find the element South
			WebElement south = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#msusasouth']['title']\"}");


			//Find the element Japan
			WebElement japan = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#asiajap']['title']\"}");


			//Find the element Gold Tier
			WebElement gold = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#supgold']['title']\"}");

			//Find the children of blogs node: Yesterday
			WebElement yesterdayIcon = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#yesterday']['icon']\"}");

			Assert.assertNotNull(yesterdayIcon);


    }

    @Test(groups = { "tree" })
	public void expandAndCollapseBlog() throws Exception {

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			waitForElementVisible("id=treeFunctionMain");

			//Need to wait for results to be updated
			waitForMilliseconds(3000L);

			//Find the children of blogs node: Today
			WebElement today = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#today']['title']\"}");

			Assert.assertTrue(today.isDisplayed());

			//Make sure blogs shows up expanded
			WebElement blogs = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

			log("blogs: "+ blogs);

			//Collapse the blogs node
			blogs.click();

			//Need to wait for results to be updated
			waitForMilliseconds(2000L);

			log("Text: "+ getElement("id=resultsFunction").getText());

			//waitForText(RESULTS_CONTAINER, "In collapseHandler event -- id=blogs");

			boolean isTodayDisplayed  = true;

			try{
				today = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#today']['title']\"}");
				isTodayDisplayed = today.isDisplayed();
			}catch(org.openqa.selenium.NoSuchElementException nse){
				isTodayDisplayed = false;
			}

			//Since we have collapsed Blogs node we should not see any Today node.
			Assert.assertFalse(isTodayDisplayed);

			log("isTodayDisplayed" + isTodayDisplayed);

			//Expand the blogs node
			blogs = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");
			blogs.click();

			waitForMilliseconds(2000L);

			today = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#today']['title']\"}");
			isTodayDisplayed = today.isDisplayed();

			Assert.assertTrue(isTodayDisplayed);
	}





	@Test(groups = { "tree" })
	public void createNodeOnRightClick() throws Exception {

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			waitForElementVisible("id=treeFunctionMain");

			//Make sure news node is selected
			WebElement news = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#news']['title']\"}");
			news.click();


			//Find the button 'Create Node(Right Click)
			String btnLocator = "id=cn";

			//Find the menu -> action item
			String beforeActionLocator = "id=ui-id-1";

			//Get Menu options on right click and click on before
			rightClickAndSelectMenuOption(btnLocator,beforeActionLocator);

  			//Verify Newly added nodes
			WebElement nnabefore = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nnabefore']['title']\"}");
			WebElement nn2before = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nn2before']['title']\"}");
			WebElement nn3before = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nn3before']['title']\"}");
			WebElement nn4before = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nn4before']['title']\"}");


			//Find the menu -> action item
			String afterActionLocator = "id=ui-id-2";

			//Get Menu options on right click and click on after
			rightClickAndSelectMenuOption(btnLocator,afterActionLocator);


			//Verify Newly added nodes
			WebElement nnaafter = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nnaafter']['title']\"}");
			WebElement nn2after = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nn2after']['title']\"}");
			WebElement nn3after = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nn3after']['title']\"}");
			WebElement nn4after = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nn4after']['title']\"}");

			//Find the menu -> action item
			String insideActionLocator = "id=ui-id-3";

			//Get Menu options on right click and click on inside
			rightClickAndSelectMenuOption(btnLocator,insideActionLocator);

			//Verify Newly added nodes
			WebElement nnainside = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nnainside']['title']\"}");
			WebElement nn2inside = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nn2inside']['title']\"}");
			WebElement nn3inside = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nn3inside']['title']\"}");
			WebElement nn4inside = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nn4inside']['title']\"}");

			//Find the menu -> action item
			String firstActionLocator = "id=ui-id-4";

			//Get Menu options on right click and click on first
			rightClickAndSelectMenuOption(btnLocator,firstActionLocator	);

			//Verify Newly added nodes
			WebElement nnafirst = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nnafirst']['title']\"}");
			WebElement nn2first = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nn2first']['title']\"}");
			WebElement nn3first = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nn3first']['title']\"}");
			WebElement nn4first = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nn4first']['title']\"}");

			//Find the menu -> action item
			String lastActionLocator = "id=ui-id-5";

			//Get Menu options on right click and click on last
			rightClickAndSelectMenuOption(btnLocator,lastActionLocator	);


			//Verify Newly added nodes
			WebElement nnalast = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nnalast']['title']\"}");
			WebElement nn2last = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nn2last']['title']\"}");
			WebElement nn3last = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nn3last']['title']\"}");
			WebElement nn4last = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nn4last']['title']\"}");


		}

		@Test(groups = { "tree" }, dependsOnMethods = { "loadPage" })
		public void selectLinksNode(){

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			waitForElementVisible("id=treeFunctionMain");

			//Find the button 'Select Links'
			WebElement btnLocator = getElement("id=selectLink");
			btnLocator.click();
			waitForText(RESULTS1_CONTAINER, "Selected Links Node");

		}

		@Test(groups = { "tree" })
		public void selectFewNodesAndExpand(){

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			waitForElementVisible("id=treeFunctionMain");

			//Find the button 'selectFewNodesAndExpand'
			WebElement btnLocator = getElement("id=selectFewNodesAndExpand");
			btnLocator.click();
			waitForText(RESULTS1_CONTAINER, "Links Microsoft East Europe All");

		}

		@Test(groups = { "tree" })
		public void deSelectFirstSelectedNode(){

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			waitForElementVisible("id=treeFunctionMain");

			//Find the button 'Select Links'
			WebElement btnLocator1 = getElement("id=selectLink");
			btnLocator1.click();
			waitForText(RESULTS1_CONTAINER, "Selected Links Node");

			//Find the button 'deSelectFirstSelectedNode'
			WebElement btnLocator = getElement("id=deSelectFirstSelectedNode");
			btnLocator.click();
			waitForText(RESULTS1_CONTAINER, "DeSelected");

		}

		@Test(groups = { "tree" })
		public void deselectAllSelectedNodes(){

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			waitForElementVisible("id=treeFunctionMain");

			//Find the button 'selectFewNodesAndExpand'
			WebElement btnLocator1 = getElement("id=selectFewNodesAndExpand");
			btnLocator1.click();
			waitForText(RESULTS1_CONTAINER, "Links Microsoft East Europe All");

			//Find the button 'selectFewNodesAndExpand'
			WebElement btnLocator = getElement("id=deselectAllSelectedNode");
			btnLocator.click();
			waitForText(RESULTS1_CONTAINER, "DeSelected All");

		}

		// Tests both 'Expand Selected Node' and 'Collapse First Selected Node' buttons
		@Test(groups = { "tree" })
		public void collapseAndExpandNode(){

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			waitForElementVisible("id=treeFunctionMain");

			//Make sure blogs node is selected
			WebElement blogs = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#blogs']['title']\"}");
			blogs.click();

			waitForMilliseconds(2000L);

			//Find the button 'collapseSelectedNode'
			WebElement btnLocator1 = getElement("id=collapseSelectedNode");
			btnLocator1.click();

			//Need to wait for results to be updated
			waitForMilliseconds(2000L);

			boolean isTodayDisplayed  = true;

			WebElement today = null;

			try{
				today = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#today']['title']\"}");
				isTodayDisplayed = today.isDisplayed();
			}catch(org.openqa.selenium.NoSuchElementException nse){
				isTodayDisplayed = false;
			}

			Assert.assertFalse(isTodayDisplayed);


			blogs = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#blogs']['title']\"}");
			blogs.click();

			waitForMilliseconds(2000L);

			//Find the button 'expandSelectedNode'
			WebElement btnLocator = getElement("id=expandSelectedNode");
			btnLocator.click();

			//Need to wait for results to be updated
			waitForMilliseconds(2000L);

			log("Text: "+ getElement("id=resultsFunction").getText());

			today = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#today']['title']\"}");
			isTodayDisplayed = today.isDisplayed();

			Assert.assertTrue(isTodayDisplayed);

		}

		// Tests both 'Expand Selected Node' and 'Collapse First Selected Node' buttons
		@Test(groups = { "tree" })
		public void collapseAndExpandAllNodes(){

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			waitForElementVisible("id=treeFunctionMain");

			//Make sure blogs node is selected
			WebElement blogs = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#blogs']['title']\"}");
			blogs.click();

			waitForMilliseconds(2000L);


			//Make sure links node is selected
			String linkLocator = "{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#links']['title']\"}";
			clickWithHoldKey(linkLocator,"SHIFT");


			//Find the button 'collapseAllSelectedNode'
			WebElement btnLocator1 = getElement("id=collapseAllSelectedNode");
			btnLocator1.click();

			waitForMilliseconds(2000L);

			log("Text: "+ getElement("id=resultsFunction1").getText());

			waitForText(RESULTS1_CONTAINER, "collapseAllSelectedNode method Blogs Links");

			//Find the button 'expandAllSelectedNode'
			WebElement btnLocator = getElement("id=expandAllSelectedNode");
			btnLocator.click();
			waitForMilliseconds(2000L);
			log("Text: "+ getElement("id=resultsFunction1").getText());
			waitForText(RESULTS1_CONTAINER, "expandAllSelectedNode method Blogs Links");

		}

		// Tests both expandLastTwoNodes
		@Test(groups = { "tree" })
		public void expandLastTwoNodes(){

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			waitForElementVisible("id=treeFunctionMain");

			//Make sure references node is selected
			WebElement references = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#references']['title']\"}");
			references.click();

			waitForMilliseconds(2000L);


			//Make sure sups node is selected
			String supsLocator = "{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#sups']['title']\"}";
			clickWithHoldKey(supsLocator,"SHIFT");

			WebElement collapseAllSelectedNode = getElement("id=collapseAllSelectedNode");
			collapseAllSelectedNode.click();
			waitForMilliseconds(2000L);

			log("Text: "+ getElement("id=resultsFunction1").getText());

			waitForText(RESULTS1_CONTAINER, "collapseAllSelectedNode method References Suppliers");

			boolean isRefAllDisplayed = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#refsall']['title']\"}").isDisplayed();

			boolean isSupGoldDisplayed = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#supgold']['title']\"}").isDisplayed();

			Assert.assertEquals(isRefAllDisplayed,false);
			Assert.assertEquals(isSupGoldDisplayed,false);

			//Find the button 'expandLastTwoNodes'
			WebElement btnLocator1 = getElement("id=expandLastTwoNodes");
			btnLocator1.click();
			String resultText = getElement("id=resultsFunction1").getText();
			log("Text: "+ resultText);

			Assert.assertEquals(resultText, "Expanded References and Suppliers");

			waitForElementVisible("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#refsall']['title']\"}");

			isRefAllDisplayed = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#refsall']['title']\"}").isDisplayed();

			isSupGoldDisplayed = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#supgold']['title']\"}").isDisplayed();

			log("************ isRefAllDisplayed" + isRefAllDisplayed);
			log("************ isSupGoldDisplayed" + isSupGoldDisplayed);

			Assert.assertEquals(isRefAllDisplayed,true);
			Assert.assertEquals(isSupGoldDisplayed,true);



		}


		// Tests both createUnderFirstSelectedNodeAndExpand
		@Test(groups = { "tree" })
		public void createUnderFirstSelectedNodeAndExpand(){

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			waitForElementVisible("id=treeFunctionMain");

			//Make sure news node is selected
			WebElement news = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#news']['title']\"}");
			news.click();

			//Find the button 'createUnderFirstSelectedNodeAndExpand'
			WebElement btnLocator1 = getElement("id=createUnderFirstSelectedNodeAndExpand");
			btnLocator1.click();

			boolean nn = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#nn']['title']\"}").isDisplayed();
			boolean sn1 = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#sn1']['title']\"}").isDisplayed();
			boolean sn2 = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#sn2']['title']\"}").isDisplayed();
			boolean sn3 = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#sn3']['title']\"}").isDisplayed();

			Assert.assertEquals(nn,true);
			Assert.assertEquals(sn1,true);
			Assert.assertEquals(sn2,true);
			Assert.assertEquals(sn3,true);



		}

		// Tests both hoverAndDehoverSelectedNode
		@Test(groups = { "tree" })
		public void hoverAndDehoverSelectedNode(){

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			waitForElementVisible("id=treeFunctionMain");

			WebElement btnLocator1 = getElement("id=hoverAndDehoverSelectedNode");
			btnLocator1.click();
			waitForText(RESULTS1_CONTAINER, "Called Hover and Dehover.");

		}

		// Tests both destroyTree
		@Test(groups = { "tree" })
		public void destroyTree(){

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			waitForElementVisible("id=treeFunctionMain");

			WebElement btnLocator1 = getElement("id=destroyTree");
			btnLocator1.click();
			waitForText(RESULTS1_CONTAINER, "ojdestroy event method");

		}

		// Tests  removeSelectedNode
			@Test(groups = { "tree" })
			public void removeSelectedNode(){

				//Start the test and bring up the browser
				startupTest("TreeFunctions.html",null);

				waitForElementVisible("id=treeFunctionMain");

				//Make sure news node is selected
				WebElement news = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#news']['title']\"}");
				news.click();

				WebElement btnLocator1 = getElement("id=removeSelectedNode");
				btnLocator1.click();
				waitForText(RESULTS1_CONTAINER, "ojremove event method -- node id=news selected");

				boolean newsDisplayed = true;

				//Make sure news node is removed.
				try{
					news = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#news']['title']\"}");
				}catch(org.openqa.selenium.NoSuchElementException nse){
					newsDisplayed = false;
				}

				Assert.assertEquals(newsDisplayed,false);


		}



		// Tests  getText
		@Test(groups = { "tree" })
		public void getTextFunc(){

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			waitForElementVisible("id=treeFunctionMain");

			WebElement btnLocator1 = getElement("id=getText");
			btnLocator1.click();
			waitForText(RESULTS1_CONTAINER, "No Nodes Selected.");

			//Make sure news node is selected
			WebElement news = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#news']['title']\"}");
			news.click();


			btnLocator1.click();
			waitForText(RESULTS1_CONTAINER, "Selected Node Text: News");

		}


		// Tests  getPathOfSelectedNode
		@Test(groups = { "tree" })
		public void getPathOfSelectedNode(){

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			waitForElementVisible("id=treeFunctionMain");

			//Make sure news node is selected
			WebElement news = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#news']['title']\"}");
			news.click();

			rightClickAndSelectMenuOption("id=getPathOfSelectedNode", "id=ui-id-22");
			waitForText(RESULTS1_CONTAINER, "path: --> 0 = News");

			rightClickAndSelectMenuOption("id=getPathOfSelectedNode", "id=ui-id-23");
			waitForText(RESULTS1_CONTAINER, "path: News");

		}


		// Tests  getParentChildrenSibling
		@Test(groups = { "tree" })
		public void getParentChildrenSibling(){

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			waitForElementVisible("id=treeFunctionMain");

			//Make sure microsoft node is selected
			WebElement ms= getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#ms']['title']\"}");
			ms.click();

			WebElement getParent = getElement("id=getParent");
			getParent.click();
			waitForText(RESULTS1_CONTAINER, "Text: Links");

			WebElement getChildren = getElement("id=getChildren");
			getChildren.click();
			waitForText(RESULTS1_CONTAINER, "Text: USA");
			waitForText(RESULTS1_CONTAINER, "Text: Europe");
			waitForText(RESULTS1_CONTAINER, "Text: Asia");

			WebElement getNextSibling = getElement("id=getNextSibling");
			getNextSibling.click();
			waitForText(RESULTS1_CONTAINER, "No Next Sibling.");

			WebElement getPrevSibling = getElement("id=getPrevSibling");
			getPrevSibling.click();
			waitForText(RESULTS1_CONTAINER, "Path: Links,IBM");

		}


		// Tests  moveNode
		@Test(groups = { "tree" })
		public void moveNode(){

			//Start the test and bring up the browser
			startupTest("TreeFunctions.html",null);

			waitForElementVisible("id=treeFunctionMain");

			//Make sure microsoft node is selected
			WebElement news= getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#news']['title']\"}");
			news.click();


			// Move Before Links node
			rightClickAndSelectMenuOption("id=moveNode","id=ui-id-12");
			waitForText(RESULTS1_CONTAINER, "Moved News to before");

			// Move after Links node
			rightClickAndSelectMenuOption("id=moveNode","id=ui-id-13");
			waitForText(RESULTS1_CONTAINER, "Moved News to after");

			// Move inside Links node
			rightClickAndSelectMenuOption("id=moveNode","id=ui-id-14");
			waitForText(RESULTS1_CONTAINER, "Moved News to inside");

			// Move first Links node
			rightClickAndSelectMenuOption("id=moveNode","id=ui-id-15");
			waitForText(RESULTS1_CONTAINER, "Moved News to first");

			// Move last Links node
			rightClickAndSelectMenuOption("id=moveNode","id=ui-id-16");
			waitForText(RESULTS1_CONTAINER, "Moved News to last");

		}

		// Tests  copyNode
			@Test(groups = { "tree" })
			public void copyNode(){

				//Start the test and bring up the browser
				startupTest("TreeFunctions.html",null);

				waitForElementVisible("id=treeFunctionMain");

				//Make sure microsoft node is selected
				WebElement news= getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#news']['title']\"}");
				news.click();


				// Move Before Links node
				rightClickAndSelectMenuOption("id=copyNode","id=ui-id-17");
				waitForText(RESULTS1_CONTAINER, "Copied News to before");

				// Move after Links node
				rightClickAndSelectMenuOption("id=copyNode","id=ui-id-18");
				waitForText(RESULTS1_CONTAINER, "Copied News to after");

				// Move inside Links node
				rightClickAndSelectMenuOption("id=copyNode","id=ui-id-19");
				waitForText(RESULTS1_CONTAINER, "Copied News to inside");

				// Move first Links node
				rightClickAndSelectMenuOption("id=copyNode","id=ui-id-20");
				waitForText(RESULTS1_CONTAINER, "Copied News to first");

				// Move last Links node
				rightClickAndSelectMenuOption("id=copyNode","id=ui-id-21");
				waitForText(RESULTS1_CONTAINER, "Copied News to last");

			}



		// Tests  renameSelectedNode
			@Test(groups = { "tree" })
			public void renameSelectedNode(){

				//Start the test and bring up the browser
				startupTest("TreeFunctions.html",null);

				waitForElementVisible("id=treeFunctionMain");

				//Make sure microsoft node is selected
				WebElement news= getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#news']['title']\"}");
				news.click();


				WebElement renameSelectedNode= getElement("id=renameSelectedNode");
				renameSelectedNode.click();
				waitForText(RESULTS1_CONTAINER, "Renamed to Node renamed");

				news= getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#news']['title']\"}");
				log("title ******"+ news.getText());

				Assert.assertEquals(news.getText(),"Node renamed");


			}


			// Tests  refreshTree
			@Test(groups = { "tree" })
			public void refreshTree(){

				//Start the test and bring up the browser
				startupTest("TreeFunctions.html",null);

				waitForElementVisible("id=treeFunctionMain");

				WebElement refreshTree= getElement("id=refreshTree");
				refreshTree.click();

				WebElement smith = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#fred1']['title']\"}");

				WebElement jones = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#Bill1']['title']\"}");

				Assert.assertEquals(smith.getText(),"Smith");
				Assert.assertEquals(jones.getText(),"Jones");


			}

			// Tests  toggleExpandSelectedNode
			@Test(groups = { "tree" })
			public void toggleExpandSelectedNode(){

				//Start the test and bring up the browser
				startupTest("TreeFunctions.html",null);

				waitForElementVisible("id=treeFunctionMain");

				WebElement blogs = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#blogs']['title']\"}");
				blogs.click();

				WebElement toggleExpandSelectedNode= getElement("id=toggleExpandSelectedNode");
				toggleExpandSelectedNode.click();


				waitForText(RESULTS1_CONTAINER, "Toggled Blogs");
			}

			// Tests  toggleSelectLinksNode
			@Test(groups = { "tree" })
			public void toggleSelectLinksNode(){

				//Start the test and bring up the browser
				startupTest("TreeFunctions.html",null);

				waitForElementVisible("id=treeFunctionMain");

				WebElement toggleSelectLinksNode= getElement("id=toggleSelectLinksNode");
				toggleSelectLinksNode.click();
				waitForText(RESULTS1_CONTAINER, "ojbefore event method -- Function Name=select");

			}


			// Tests  showIcons
			@Test(groups = { "tree" })
			public void showIcons(){

				//Start the test and bring up the browser
				startupTest("TreeFunctions.html",null);

				waitForElementVisible("id=treeFunctionMain");

				WebElement showIcons= getElement("id=showIcons");
				showIcons.click();

				WebElement blogsIcon = null;

				try{
					blogsIcon = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#blogs']['icon']\"}");
				}catch(org.openqa.selenium.WebDriverException wde){

				}

				Assert.assertNull(blogsIcon);

				showIcons.click();
				blogsIcon = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#blogs']['icon']\"}");
				Assert.assertNotNull(blogsIcon);


			}

			// Tests  disableTree
			@Test(groups = { "tree" })
			public void disableTree(){

				//Start the test and bring up the browser
				startupTest("TreeFunctions.html",null);

				waitForElementVisible("id=treeFunctionMain");

				WebElement news = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#news']['title']\"}");
				news.click();

				WebElement home = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#home']['title']\"}");
				home.click();

				WebElement disableTree= getElement("id=disableTree");
				disableTree.click();

				news = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#news']['title']\"}");

				WebElement resultsElement = getElement("id=resultsFunction1");

				log(resultsElement.toString());

				Assert.assertNotEquals(resultsElement.getText(), "ojdehover event method -- node id=news selected");

			}


			// Tests  testCollapsibleTree
				@Test(groups = { "tree" })
				public void testCollapsibleTree(){

					//Start the test and bring up the browser
					startupTest("TreeFunctions.html",null);

					waitForElementVisible("id=treeFunctionMain");

					WebElement collapIcon = getElement("{\"element\":\"#ojCollapsible\",\"subId\":\"oj-collapsible-header-icon\"}");
					collapIcon.click();

					waitForElementVisible("id=treeCollapsible");

					String blogs1Locator = "{\"element\":\"#treeCollapsible\",\"subId\":\"oj-tree-node['#blogs1']['title']\"}";

					WebElement blogs1Element = getElement(blogs1Locator);


					WebElement addContextMenuToCollapsibleTree = getElement("id=addContextMenuToCollapsibleTree");
					addContextMenuToCollapsibleTree.click();

					rightClickAndSelectMenuOption(blogs1Locator,"id=ui-id-6");

					WebElement removeContextMenuToCollapsibleTree = getElement("id=removeContextMenuToCollapsibleTree");
					removeContextMenuToCollapsibleTree.click();

					boolean menuDisplayed = true;

					try{
						rightClickAndSelectMenuOption(blogs1Locator,"id=ui-id-6");
					}catch(org.openqa.selenium.WebDriverException nse){
						 menuDisplayed = false;
					}

					Assert.assertEquals(menuDisplayed,false);




			}


			// Tests  Add nodes and Delete Nodes
			@Test(groups = { "tree" })
			public void addDeleteNodes(){

				//Start the test and bring up the browser
				startupTest("TreeFunctions.html",null);

				waitForElementVisible("id=treeFunctionMain");

				WebElement news = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#news']['title']\"}");
				news.click();

				WebElement home = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#home']['title']\"}");
				home.click();

				WebElement addNodes= getElement("id=addNodes");
				addNodes.click();

				waitForElementVisible("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#node0']['disclosure']\"}");

				WebElement node0Disclosure = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#node0']['disclosure']\"}");
				node0Disclosure.click();

				waitForElementVisible("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#node3']['title']\"}");

				WebElement node3 = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#node3']['title']\"}");
				node3.click();

				WebElement node1 = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#node1']['title']\"}");
				node1.click();


				WebElement delNodes = getElement("id=delNodes");
				delNodes.click();

				WebElement node1Ele = null;

				try{
					node1Ele = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#node1']['title']\"}");
				}catch(org.openqa.selenium.WebDriverException wde){

				}

				Assert.assertNull(node1Ele);

			}














	private void log(String log)
	    {
	        System.out.println(log);
	        getLogger().fine("[TreeFunctionsTest] " + log);
    }


}
