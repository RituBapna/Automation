package oj.tests.rowexpander;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

public class RowExpanderTableTest extends OJetBase {

	private static final String TITLE_REX = "RowExpander with Table";
	private static final String RESULTS_CONTAINER = "resultsFunction";



    public RowExpanderTableTest() {
        super(new TestConfigBuilder().setContextRoot("rowexpander").setAppRoot("rowexpanderTest").build());
    }


    @Test(groups = { "rex"})
	public void createRowEx() throws Exception {

			//Start the test and bring up the browser
			startupTest("rowExpanderWithTable1.html",null);

			//Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
			verifyTitle("Incorrect page title;", TITLE_REX);

			waitForElementVisible("id=table1");

			waitForElementVisible("id=resultsFunction");

			waitForText(RESULTS_CONTAINER, "t1 ojcreate event method -- event id=t4");

    }


		


    @Test(groups = { "rex"}, dependsOnMethods = { "createRowEx"})
	public void testTableRowExHeader() throws Exception {

		//Start the test and bring up the browser
		startupTest("rowExpanderWithTable1.html",null);

		waitForElementVisible("id=table1");

		WebElement table1 = getElement("id=table1");
		log("###### table1"+ table1);


		WebElement dgHeader0 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-header\",\"index\":\"0\"}");
		log("dgHeader0"+dgHeader0.getText());
		String dgHeader0Text   = dgHeader0.getText();
		Assert.assertTrue(dgHeader0Text.contains("Task Name"));

		WebElement dgHeader1 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-header\",\"index\":\"1\"}");
		log("dgHeader1"+dgHeader1.getText());
		String dgHeader1Text   = dgHeader1.getText();
		Assert.assertTrue(dgHeader1Text.contains("Resource"));

		WebElement dgHeader2 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-header\",\"index\":\"2\"}");
		log("dgHeader2"+dgHeader2.getText());
		String dgHeader2Text   = dgHeader2.getText();
		Assert.assertTrue(dgHeader2Text.contains("Start Date"));

		WebElement dgHeader3 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-header\",\"index\":\"3\"}");
		log("dgHeader3"+dgHeader3.getText());
		String dgHeader3Text   = dgHeader3.getText();
		Assert.assertTrue(dgHeader3Text.contains("End Date"));




    }

    @Test(groups = { "rex"}, dependsOnMethods = { "testTableRowExHeader"})
		public void testTableRowExValues() throws Exception {

			//Start the test and bring up the browser
			startupTest("rowExpanderWithTable1.html",null);

			waitForElementVisible("id=table1");

			WebElement row0Col0 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-data-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("Task 1",row0Col0.getText());

			WebElement row0Col1 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-data-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("Larry",row0Col1.getText());

			WebElement row0Col2 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-data-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("1/1/2014",row0Col2.getText());

			WebElement row0Col3 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-data-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("10/1/2014",row0Col3.getText());

			WebElement row1Col0 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-data-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("Task 2",row1Col0.getText());

			WebElement row1Col1 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-data-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("Larry",row1Col1.getText());

			WebElement row1Col2 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-data-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("4/1/2014",row1Col2.getText());

			WebElement row1Col3 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-data-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("12/1/2014",row1Col3.getText());

			WebElement t1Icon = getElement("{\"element\":\"#t1\",\"subId\":\"oj-rowexpander-disclosure\"}");
			t1Icon.click();

			//Need to wait for loading to finish
			waitForMilliseconds(2000L);

			log("Text: "+ getElement("id=resultsFunction1").getText());

			waitForText("id=resultsFunction1", "Expand Func: RowKey: t1");


			row1Col0 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("Task 1-1",row1Col0.getText());

			row1Col1 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("Larry",row1Col1.getText());

			row1Col2 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("1/1/2014",row1Col2.getText());

			row1Col3 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("3/1/2014",row1Col3.getText());

			WebElement t11 = getElement("id=t1-1");
			log("t11: " + t11);




			WebElement t11Icon = getElement("{\"element\":\"#t1-1\",\"subId\":\"oj-rowexpander-disclosure\"}");
			t11Icon.click();

			//Need to wait for loading to finish
			waitForMilliseconds(2000L);

			log("Text: "+ getElement("id=resultsFunction1").getText());

			waitForText("id=resultsFunction1", "Expand Func: RowKey: t1-1");

			WebElement row2Col0 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("Task 1-1-1",row2Col0.getText());

			WebElement row2Col1 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("Larry",row2Col1.getText());

			WebElement row2Col2 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("1/1/2014",row2Col2.getText());

			WebElement row2Col3 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("2/1/2014",row2Col3.getText());

			t1Icon = getElement("{\"element\":\"#t1\",\"subId\":\"oj-rowexpander-disclosure\"}");
			t1Icon.click();

			//Need to wait for loading to finish
			waitForMilliseconds(2000L);

			log("Text: "+ getElement("id=resultsFunction1").getText());

			waitForText("id=resultsFunction1", "Collapse Func: RowKey: t1");




    }

    @Test(groups = { "rex"},dependsOnMethods = { "testTableRowExValues"})
	public void testSortingTableRowEx() throws Exception {

			String browsername = getBrowserName();

				//Webdriver has issues with 2 level menus on Firefox and so commenting it out.
				if ( !(browsername.equalsIgnoreCase("firefox")) ){
					//Start the test and bring up the browser
					startupTest("rowExpanderWithTable1.html",null);

					waitForElementVisible("id=table1");

					WebElement row0Col0 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 1",row0Col0.getText());

					WebElement row0Col1 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("Larry",row0Col1.getText());

					WebElement row0Col2 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("1/1/2014",row0Col2.getText());

					WebElement row0Col3 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("10/1/2014",row0Col3.getText());

					WebElement row1Col0 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 2",row1Col0.getText());

					WebElement row1Col1 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("Larry",row1Col1.getText());

					WebElement row1Col2 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("4/1/2014",row1Col2.getText());

					WebElement row1Col3 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("12/1/2014",row1Col3.getText());


					//Verify Sort Descending

					String elemLocator = "{\"element\":\"#table1\",\"subId\":\"oj-table-header\",\"index\":\"0\"}";
					String menuLocator = "id=ui-id-1";
					String subMenuLocator = "id=ui-id-3";

					rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);

					waitForMilliseconds(2000L);


					row0Col0 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 4",row0Col0.getText());

					row1Col0 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 3",row1Col0.getText());

					WebElement row2Col0 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 2",row2Col0.getText());

					WebElement row3Col0 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 1",row3Col0.getText());

					//Verify Sort Ascending

					elemLocator = "{\"element\":\"#table1\",\"subId\":\"oj-table-header\",\"index\":\"0\"}";
					menuLocator = "id=ui-id-1";
					subMenuLocator = "id=ui-id-2";

					rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);

					waitForMilliseconds(2000L);


					row0Col0 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 1",row0Col0.getText());

					row1Col0 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 2",row1Col0.getText());

					row2Col0 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 3",row2Col0.getText());

					row3Col0 = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 4",row3Col0.getText());


					WebElement sortAscendingIcon = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-sort-ascending\",\"index\":\"0\"}");
					sortAscendingIcon.click();

					waitForMilliseconds(2000L);

					String row0Col0AscText = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}").getText();
					log("row0Col0AscText "+ row0Col0AscText);
					Assert.assertEquals("Task 4",row0Col0AscText);

					WebElement row1Col0Asc = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 3",row1Col0Asc.getText());

					WebElement row2Col0Asc = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 2",row2Col0Asc.getText());

					WebElement row3Col0Asc = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 1",row3Col0Asc.getText());

					WebElement sortDescendingIcon = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-sort-descending\",\"index\":\"0\"}");
					sortDescendingIcon.click();

					waitForMilliseconds(2000L);

					String row0Col0Desc = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}").getText();
					Assert.assertEquals("Task 1",row0Col0Desc);

					WebElement row1Col0Desc = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 2",row1Col0Desc.getText());

					WebElement row2Col0Desc = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 3",row2Col0Desc.getText());

					WebElement row3Col0Desc = getElement("{\"element\":\"#table1\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("Task 4",row3Col0Desc.getText());

				}

		}




 @Test(groups = { "rex"}, dependsOnMethods = { "testSortingTableRowEx"})
	public void destroyRowEx() throws Exception {

		//Start the test and bring up the browser
		startupTest("rowExpanderWithTable1.html",null);

		waitForElementVisible("id=t1");


		WebElement t1Icon = getElement("{\"element\":\"#t1\",\"subId\":\"oj-rowexpander-disclosure\"}");
		log("t1Icon:	"+t1Icon);
		t1Icon.click();

		waitForElementVisible("id=destroyRowEx");

		boolean rexIcon = true;

		WebElement destroyRowEx = getElement("id=destroyRowEx");
		destroyRowEx.click();

		waitForText(RESULTS_CONTAINER, "t1 ojdestroy event method -- event id=t1");


		try{
			t1Icon = getElement("id=t1");
			rexIcon = t1Icon.isDisplayed();
		}catch(Exception e) {
			rexIcon = false;
		}

		Assert.assertFalse(rexIcon);


    }




	private void log(String log)
	    {
	        System.out.println(log);

			getLogger().fine("[RowExpanderTableTest] " + log);
    }


}
