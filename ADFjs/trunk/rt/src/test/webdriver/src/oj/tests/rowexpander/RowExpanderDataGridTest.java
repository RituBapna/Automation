package oj.tests.rowexpander;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

public class RowExpanderDataGridTest extends OJetBase {

	private static final String TITLE_REXSpecific = "RowExpander With Specific Rows Expanded";
	private static final String RESULTS_CONTAINER = "resultsFunction";



    public RowExpanderDataGridTest() {
        super(new TestConfigBuilder().setContextRoot("rowexpander").setAppRoot("rowexpanderTest").build());
    }


    @Test(groups = { "rex"})
	public void testPageLoad() throws Exception {

			//Start the test and bring up the browser
			startupTest("rowExpanderWithSpecificRowsExpanded.html",null);

			//Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
			verifyTitle("Incorrect page title;", TITLE_REXSpecific);

			waitForElementVisible("id=datagridInitExp");


    }



	@Test(groups = { "rex"})
	public void testDataGridRowExHeader() throws Exception {

		//Start the test and bring up the browser
		startupTest("rowExpanderWithSpecificRowsExpanded.html",null);

		waitForElementVisible("id=datagridInitExp");

		WebElement dgHeader0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}");
		Assert.assertEquals("Task Name",dgHeader0.getText());

		WebElement dgHeader1 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"1\"}");
		Assert.assertEquals("Resource",dgHeader1.getText());

		WebElement dgHeader2 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"2\"}");
		Assert.assertEquals("Start Date",dgHeader2.getText());

		WebElement dgHeader3 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"3\"}");
		Assert.assertEquals("End Date",dgHeader3.getText());




    }

    @Test(groups = { "rex"})
	public void testDGRowExSpecificRowsExpanded() throws Exception {

			//Start the test and bring up the browser
			startupTest("rowExpanderWithSpecificRowsExpanded.html",null);

			waitForElementVisible("id=datagridInitExp");

			WebElement t1Icon = getElement("{\"element\":\"#t1\",\"subId\":\"oj-rowexpander-disclosure\"}");
			Assert.assertEquals("true", t1Icon.getAttribute("aria-expanded"));

			WebElement t2Icon = getElement("{\"element\":\"#t2\",\"subId\":\"oj-rowexpander-disclosure\"}");
			Assert.assertEquals("false", t2Icon.getAttribute("aria-expanded"));

			WebElement t3Icon = getElement("{\"element\":\"#t3\",\"subId\":\"oj-rowexpander-disclosure\"}");
			Assert.assertEquals("true", t3Icon.getAttribute("aria-expanded"));

	}

	@Test(groups = { "rex"})
		public void testDGRowExValues() throws Exception {

			//Start the test and bring up the browser
			startupTest("rowExpanderWithSpecificRowsExpanded.html",null);

			waitForElementVisible("id=datagridInitExp");

			WebElement row0Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("Task 1",row0Col0.getText());

			WebElement row0Col1 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("Larry",row0Col1.getText());

			WebElement row0Col2 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("1/1/2014",row0Col2.getText());

			WebElement row0Col3 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("10/1/2014",row0Col3.getText());

			WebElement row1Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("Task 1-1",row1Col0.getText());

			WebElement row1Col1 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("Larry",row1Col1.getText());

			WebElement row1Col2 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("1/1/2014",row1Col2.getText());

			WebElement row1Col3 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("3/1/2014",row1Col3.getText());


	}

	@Test(groups = { "rex"})
	public void testSortingDatagridRowEx() throws Exception {

			String browsername = getBrowserName();

			//Webdriver has issues with 2 level menus on Firefox and so commenting it out.
			if ( !(browsername.equalsIgnoreCase("firefox")) ){
					//Start the test and bring up the browser
					startupTest("rowExpanderWithSpecificRowsExpanded.html",null);

					waitForElementVisible("id=datagridInitExp");

					WebElement row0Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 1",row0Col0.getText());

					WebElement row0Col1 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("Larry",row0Col1.getText());

					WebElement row0Col2 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("1/1/2014",row0Col2.getText());

					WebElement row0Col3 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("10/1/2014",row0Col3.getText());

					WebElement row1Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 1-1",row1Col0.getText());

					WebElement row1Col1 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("Larry",row1Col1.getText());

					WebElement row1Col2 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("1/1/2014",row1Col2.getText());

					WebElement row1Col3 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("3/1/2014",row1Col3.getText());



					//This does not work as sort icon is not visible
					//WebElement lastNameSortIcon = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-sort-icon\",\"axis\":\"column\",\"index\":\"0\"}");
					//log("lastNameSortIcon" + lastNameSortIcon);
					//lastNameSortIcon.click();

					//Verify Sort Descending

					String elemLocator = "{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
					String menuLocator = "id=ui-id-2";
					String subMenuLocator = "id=ui-id-6";

					rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);

					row0Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 4",row0Col0.getText());

					row1Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 3",row1Col0.getText());

					WebElement row2Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 3-2",row2Col0.getText());

					WebElement row3Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 3-1",row3Col0.getText());

					//Verify Sort Ascending

					elemLocator = "{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
					menuLocator = "id=ui-id-2";
					subMenuLocator = "id=ui-id-5";

					rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);


					row0Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 1",row0Col0.getText());

					row1Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 1-1",row1Col0.getText());

					row2Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 1-2",row2Col0.getText());

					row3Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 1-3",row3Col0.getText());


					WebElement sortAscendingIcon = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-sort-ascending\",\"axis\":\"column\",\"index\":\"0\"}");
					sortAscendingIcon.click();


					//Need to wait for Sorting to finish
					waitForMilliseconds(1000L);

					row0Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 4",row0Col0.getText());

					row1Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 3",row1Col0.getText());

					row2Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 3-2",row2Col0.getText());

					row3Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 3-1",row3Col0.getText());

					WebElement sortDescendingIcon = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-sort-descending\",\"axis\":\"column\",\"index\":\"0\"}");
					sortDescendingIcon.click();

					//Need to wait for Sorting to finish
					waitForMilliseconds(1000L);

					row0Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 1",row0Col0.getText());

					row1Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 1-1",row1Col0.getText());

					row2Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 1-2",row2Col0.getText());

					row3Col0 = getElement("{\"element\":\"#datagridInitExp\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 1-3",row3Col0.getText());
				}


		}


	@Test(groups = { "rex"})
	public void testDGRowExAllRowsExpanded() throws Exception {

			//Start the test and bring up the browser
			startupTest("rowExpanderWithAllRowsExpanded.html",null);

			//Wait for page to display
			waitForMilliseconds(1000L);

			waitForElementVisible("id=datagridAllExp");

			WebElement t1Icon = getElement("{\"element\":\"#t1\",\"subId\":\"oj-rowexpander-disclosure\"}");
			Assert.assertEquals("true", t1Icon.getAttribute("aria-expanded"));

			WebElement t2Icon = getElement("{\"element\":\"#t2\",\"subId\":\"oj-rowexpander-disclosure\"}");
			Assert.assertEquals("true", t2Icon.getAttribute("aria-expanded"));

			WebElement t3Icon = getElement("{\"element\":\"#t3\",\"subId\":\"oj-rowexpander-disclosure\"}");
			Assert.assertEquals("true", t3Icon.getAttribute("aria-expanded"));

			WebElement t11Icon = getElement("{\"element\":\"#t1-1\",\"subId\":\"oj-rowexpander-disclosure\"}");
			Assert.assertEquals("true", t11Icon.getAttribute("aria-expanded"));

			WebElement t12Icon = getElement("{\"element\":\"#t1-2\",\"subId\":\"oj-rowexpander-disclosure\"}");
			Assert.assertEquals("true", t12Icon.getAttribute("aria-expanded"));

			WebElement t22Icon = getElement("{\"element\":\"#t2-2\",\"subId\":\"oj-rowexpander-disclosure\"}");
			Assert.assertEquals("true", t22Icon.getAttribute("aria-expanded"));

			WebElement t23Icon = getElement("{\"element\":\"#t2-3\",\"subId\":\"oj-rowexpander-disclosure\"}");
			Assert.assertEquals("true", t23Icon.getAttribute("aria-expanded"));

			WebElement t231Icon = getElement("{\"element\":\"#t2-3-1\",\"subId\":\"oj-rowexpander-disclosure\"}");
			Assert.assertEquals("true", t231Icon.getAttribute("aria-expanded"));

			WebElement t31Icon = getElement("{\"element\":\"#t3-1\",\"subId\":\"oj-rowexpander-disclosure\"}");
			Assert.assertEquals("true", t3Icon.getAttribute("aria-expanded"));


			WebElement addData = getElement("id=addData");
			addData.click();

			WebElement t1NewIcon = getElement("{\"element\":\"#t1New\",\"subId\":\"oj-rowexpander-disclosure\"}");
			Assert.assertEquals("true", t1NewIcon.getAttribute("aria-expanded"));

			//UNCOMMENT the code below once the bug is fixed


			WebElement t1New1Icon = getElement("{\"element\":\"#t1New-1\",\"subId\":\"oj-rowexpander-disclosure\"}");
			Assert.assertEquals("true", t1New1Icon.getAttribute("aria-expanded"));






	}


	private void log(String log)
	    {
	        System.out.println(log);

			getLogger().fine("[RowExpanderDataGridTest] " + log);
    }


}
