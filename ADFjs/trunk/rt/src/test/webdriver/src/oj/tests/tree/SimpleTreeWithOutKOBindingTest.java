package oj.tests.tree;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.Select;

public class SimpleTreeWithOutKOBindingTest extends OJetBase {

	private static final String TITLE_FUNC = "Tree without using KO binding";


    public SimpleTreeWithOutKOBindingTest() {
        super(new TestConfigBuilder().setContextRoot("tree").setAppRoot("treeLoad").build());
    }


    @Test(groups = { "tree" })
	public void loadPage() throws Exception {

			//Start the test and bring up the browser
			startupTest("SimpleTreeWithOutKOBinding.html",null);

			//Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
			verifyTitle("Incorrect page title;", TITLE_FUNC);

    }





    @Test(groups = { "tree" })
		public void testTreeWithOutKOBinding() throws Exception {

			//Start the test and bring up the browser
			startupTest("SimpleTreeWithOutKOBinding.html",null);

			waitForElementVisible("id=myTree");

			boolean ms = getElement("{\"element\":\"#myTree\",\"subId\":\"oj-tree-node['#ms']['title']\"}").isDisplayed();
			Assert.assertEquals(ms,true);

			boolean msusa = getElement("{\"element\":\"#myTree\",\"subId\":\"oj-tree-node['#msusa']['title']\"}").isDisplayed();
			Assert.assertEquals(msusa, false);

			boolean supgold = getElement("{\"element\":\"#myTree\",\"subId\":\"oj-tree-node['#supgold']['title']\"}").isDisplayed();
			Assert.assertEquals(supgold, true);

			//Expand blogs
			WebElement blogs = getElement("{\"element\":\"#myTree\",\"subId\":\"oj-tree-node['#blogs']['disclosure']\"}");

			//expand the blogs node
			blogs.click();

			waitForElementVisible("{\"element\":\"#myTree\",\"subId\":\"oj-tree-node['#today']['title']\"}");

			boolean today = getElement("{\"element\":\"#myTree\",\"subId\":\"oj-tree-node['#today']['title']\"}").isDisplayed();
			Assert.assertEquals(today, true);



    }

    private void log(String log)
	{
		System.out.println(log);
		getLogger().fine("[TreeFunctionsTest] " + log);
    }

}