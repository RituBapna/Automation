package oj.tests.tree;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.Select;

public class TreeChangeUsingSelectModelTest extends OJetBase {

	private static final String TITLE_FUNC = "Change Tree Contents using Select";


    public TreeChangeUsingSelectModelTest() {
        super(new TestConfigBuilder().setContextRoot("tree").setAppRoot("treeLoad").build());
    }


    @Test(groups = { "tree" })
	public void loadPage() throws Exception {

			//Start the test and bring up the browser
			startupTest("TreeChangeUsingSelectModel.html",null);

			//Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
			verifyTitle("Incorrect page title;", TITLE_FUNC);

    }





    @Test(groups = { "tree" })
		public void testTreeChangeUsingSelectOption() throws Exception {

			//Start the test and bring up the browser
			startupTest("TreeChangeUsingSelectModel.html",null);

			waitForElementVisible("id=ojChoiceId_select_selected");

			WebElement selectChoice = getElement("id=ojChoiceId_select_selected");

			log("###### selectChoice"+ selectChoice);

			waitForElementVisible("id=treeFunctionMain");

			WebElement treeMain = getElement("id=treeFunctionMain");

			log("###### treeMain"+ treeMain);

			//Find the node : Select Me
			WebElement selectMe = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#node0']['title']\"}");

			log("title ******"+ selectMe.getText());

			Assert.assertEquals(selectMe.getText(),"Select Me");

			//Make sure blogs shows up expanded
			WebElement blogs = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

			//Collapse the blogs node
			blogs.click();

			waitForElementVisible("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#today']['title']\"}");

			WebElement today = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#today']['title']\"}");

			Assert.assertEquals(today.getText(),"Today");

			WebElement two = getElement("id=changeSelectToTWO");

			two.click();
			//Need to wait for loading to finish
			waitForMilliseconds(1000L);

			//Find the node : Home2
			String home2 = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#home2']['title']\"}").getText();

			log("title ******"+ home2);

			Assert.assertEquals(home2,"Home2");

			//Find the node : treenode2
			WebElement treenode2 = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#treenode2']['title']\"}");

			log("title ******"+ treenode2.getText());

			Assert.assertEquals(treenode2.getText(),"TreeNode2");

			WebElement three = getElement("id=changeSelectToTHREE");

			three.click();

			//Need to wait for loading to finish
			waitForMilliseconds(1000L);

			//Find the node : Home3
			WebElement home3 = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#home3']['title']\"}");

			log("title ******"+ home3.getText());

			Assert.assertEquals(home3.getText(),"Home3");

			//Find the node : Tree3 Node
			WebElement treenode3 = getElement("{\"element\":\"#treeFunctionMain\",\"subId\":\"oj-tree-node['#news']['title']\"}");

			log("title ******"+ treenode3.getText());

			Assert.assertEquals(treenode3.getText(),"Tree3 Node");

    }

    private void log(String log)
	{
		System.out.println(log);
		getLogger().fine("[TreeFunctionsTest] " + log);
    }

}