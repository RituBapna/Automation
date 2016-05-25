package oj.tests.commonmodelapi;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class RestWSDataGridDSTest extends OJetBase {

    private static final String TITLE = "DataGrid using Rest WS Collection";

    public RestWSDataGridDSTest() {
        super(new TestConfigBuilder().setContextRoot("commonmodelapi").setAppRoot("application").build());
    }

    @Test(groups = { "cma"})
    public void testRestWSDG() throws Exception {
        //Start the test and bring up the browser
        startupTest("ArrayFromRestWSDataGridDS.html",null);

        //Verify the url
		String url = getBrowserUrl();
		log("URL##########"+ url);

		// Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE);

        // Make sure datagridArray is displayed
        waitForElementVisible("id=datagridArray");

        waitForElementVisible("id=datagridArrayPaging");


    }


    @Test(groups = { "cma"})
		public void testRestWSDGHeader() throws Exception {

			//Start the test and bring up the browser
			startupTest("ArrayFromRestWSDataGridDS.html",null);

			waitForElementVisible("id=datagridArray");

			waitForElementText("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}","Department No");

			WebElement dgHeader0 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}");
			Assert.assertEquals("Department No",dgHeader0.getText());

			WebElement dgHeader1 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"1\"}");
			Assert.assertEquals("Department Name",dgHeader1.getText());

			WebElement dgHeader2 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"2\"}");
			Assert.assertEquals("Location",dgHeader2.getText());

			WebElement dgHeader3 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"3\"}");
			Assert.assertEquals("Property1",dgHeader3.getText());

			WebElement dgHeader4 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"4\"}");
			Assert.assertEquals("Property2",dgHeader4.getText());




	    }

	   @Test(groups = { "cma"})
	   public void testRestWSDGValues() throws Exception {

			//Start the test and bring up the browser
			startupTest("ArrayFromRestWSDataGridDS.html",null);

			waitForElementVisible("id=datagridArray");

			waitForElementText("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}","10");

			WebElement row0Col0 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("10",row0Col0.getText());

			WebElement row0Col1 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("ACCOUNTING",row0Col1.getText());

			WebElement row0Col2 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("PORTLAND",row0Col2.getText());

			WebElement row0Col3 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("10 ACCOUNTING",row0Col3.getText());

			WebElement row0Col4 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("ACCOUNTING Hello!",row0Col4.getText());

			WebElement row1Col0 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("12",row1Col0.getText());

			WebElement row1Col1 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("name4534",row1Col1.getText());

			WebElement row1Col2 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("10",row1Col2.getText());

			WebElement row1Col3 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("12 name4534",row1Col3.getText());

			WebElement row1Col4 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("name4534 Hello!",row1Col4.getText());

	    }


	    @Test(groups = { "cma"})
			public void testSortingRestWSDG() throws Exception {
				String browsername = getBrowserName();

				//Webdriver has issues with 2 level menus on Firefox and so commenting it out.
				if ( !(browsername.equalsIgnoreCase("firefox")) ){

					//Start the test and bring up the browser
					startupTest("ArrayFromRestWSDataGridDS.html",null);

					waitForElementVisible("id=datagridArray");

					waitForElementText("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}","10");

					WebElement row0Col0 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("10",row0Col0.getText());

					WebElement row0Col1 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("ACCOUNTING",row0Col1.getText());

					WebElement row0Col2 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("PORTLAND",row0Col2.getText());

					WebElement row0Col3 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("10 ACCOUNTING",row0Col3.getText());

					WebElement row0Col4 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("ACCOUNTING Hello!",row0Col4.getText());

					WebElement row1Col0 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("12",row1Col0.getText());

					WebElement row1Col1 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("name4534",row1Col1.getText());

					WebElement row1Col2 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("10",row1Col2.getText());

					WebElement row1Col3 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("12 name4534",row1Col3.getText());

					WebElement row1Col4 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("name4534 Hello!",row1Col4.getText());



					//This does not work as sort icon is not visible
					//WebElement lastNameSortIcon = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-sort-icon\",\"axis\":\"column\",\"index\":\"0\"}");
					//log("lastNameSortIcon" + lastNameSortIcon);
					//lastNameSortIcon.click();

					//Verify Sort Descending

					String elemLocator = "{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
					String menuLocator = "id=ui-id-2";
					String subMenuLocator = "id=ui-id-8";

					rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);

					row0Col0 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("90",row0Col0.getText());

					row0Col1 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("Dept90",row0Col1.getText());

					row0Col2 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("LOC90",row0Col2.getText());

					row0Col3 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("90 Dept90",row0Col3.getText());

					row0Col4 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("Dept90 Hello!",row0Col4.getText());

					//Verify Sort Ascending
					elemLocator = "{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
					menuLocator = "id=ui-id-2";
					subMenuLocator = "id=ui-id-7";

					rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);

					row0Col0 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("10",row0Col0.getText());

					row0Col1 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("ACCOUNTING",row0Col1.getText());

					row0Col2 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("PORTLAND",row0Col2.getText());

					row0Col3 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("10 ACCOUNTING",row0Col3.getText());

					row0Col4 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("ACCOUNTING Hello!",row0Col4.getText());




					WebElement sortAscendingIcon = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-sort-ascending\",\"axis\":\"column\",\"index\":\"0\"}");
					sortAscendingIcon.click();


					//Need to wait for Sorting to finish
					waitForMilliseconds(1000L);

					row0Col0 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("90",row0Col0.getText());

					row0Col1 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("Dept90",row0Col1.getText());

					row0Col2 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("LOC90",row0Col2.getText());

					row0Col3 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("90 Dept90",row0Col3.getText());

					row0Col4 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("Dept90 Hello!",row0Col4.getText());


					WebElement sortDescendingIcon = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-sort-descending\",\"axis\":\"column\",\"index\":\"0\"}");
					sortDescendingIcon.click();


					waitForMilliseconds(1000L);

					row0Col0 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("10",row0Col0.getText());

					row0Col1 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("ACCOUNTING",row0Col1.getText());

					row0Col2 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("PORTLAND",row0Col2.getText());

					row0Col3 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("10 ACCOUNTING",row0Col3.getText());

					row0Col4 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("ACCOUNTING Hello!",row0Col4.getText());

				}



		}


		@Test(groups = { "cma"})
	    public void testRestWSDGCutPaste() throws Exception {

			//Start the test and bring up the browser
			startupTest("ArrayFromRestWSDataGridDS.html",null);

			waitForElementVisible("id=datagridArray");

			waitForElementText("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}","10");

			WebElement row0Col0 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("10",row0Col0.getText());

			WebElement row0Col1 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("ACCOUNTING",row0Col1.getText());

			WebElement row0Col2 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("PORTLAND",row0Col2.getText());

			WebElement row0Col3 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("10 ACCOUNTING",row0Col3.getText());

			WebElement row0Col4 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("ACCOUNTING Hello!",row0Col4.getText());

			row0Col0.click();

			rightClickAndSelectMenuOption("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}","id=ui-id-3");

			WebElement row2Col1 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"1\"}");
			row2Col1.click();

			rightClickAndSelectMenuOption("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"1\"}","id=ui-id-4");

			row0Col0 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("12",row0Col0.getText());

			row0Col1 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("name4534",row0Col1.getText());

			row0Col2 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("10",row0Col2.getText());

			row0Col3 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("12 name4534",row0Col3.getText());

			row0Col4 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("name4534 Hello!",row0Col4.getText());

			WebElement row1Col0 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("10",row1Col0.getText());

			WebElement row1Col1 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("ACCOUNTING",row1Col1.getText());

			WebElement row1Col2 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("PORTLAND",row1Col2.getText());

			WebElement row1Col3 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("10 ACCOUNTING",row1Col3.getText());

			WebElement row1Col4 = getElement("{\"element\":\"#datagridArray\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("ACCOUNTING Hello!",row1Col4.getText());

		}

		@Test(groups = { "cma"})
		public void testRestWSDGPagingHeader() throws Exception {

			//Start the test and bring up the browser
			startupTest("ArrayFromRestWSDataGridDS.html",null);

			waitForElementVisible("id=datagridArrayPaging");

			waitForElementVisible("id=paging");

			waitForElementText("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}","Department No");

			WebElement dgHeader0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}");
			Assert.assertEquals("Department No",dgHeader0.getText());

			WebElement dgHeader1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"1\"}");
			Assert.assertEquals("Department Name",dgHeader1.getText());

			WebElement dgHeader2 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"2\"}");
			Assert.assertEquals("Location",dgHeader2.getText());

			WebElement dgHeader3 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"3\"}");
			Assert.assertEquals("Property1",dgHeader3.getText());

			WebElement dgHeader4 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"4\"}");
			Assert.assertEquals("Property2",dgHeader4.getText());

		}


		@Test(groups = { "cma"})
	   public void testRestWSDGPagingValues() throws Exception {

			//Start the test and bring up the browser
			startupTest("ArrayFromRestWSDataGridDS.html",null);

			waitForElementVisible("id=datagridArrayPaging");

			waitForElementVisible("id=paging");
			waitForMilliseconds(3000L);

			waitForElementText("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}","10");

			WebElement row0Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("10",row0Col0.getText());

			WebElement row0Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("ACCOUNTING",row0Col1.getText());

			WebElement row0Col2 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("PORTLAND",row0Col2.getText());

			WebElement row0Col3 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("10 ACCOUNTING",row0Col3.getText());

			WebElement row0Col4 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("ACCOUNTING Hello!",row0Col4.getText());

			WebElement row1Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("12",row1Col0.getText());

			WebElement row1Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("name4534",row1Col1.getText());

			WebElement row1Col2 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("10",row1Col2.getText());

			WebElement row1Col3 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("12 name4534",row1Col3.getText());

			WebElement row1Col4 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("name4534 Hello!",row1Col4.getText());

			waitForElementVisible("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-page\",\"index\":\"1\"}");
			WebElement page2 = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-page\",\"index\":\"1\"}");
			log("page2 #"+ page2);
			page2.click();

			row0Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("20",row0Col0.getText());

			row0Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("RESEARCH",row0Col1.getText());

			row1Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("30",row1Col0.getText());

			row1Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("SALES12",row1Col1.getText());

			WebElement page3 = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-page\",\"index\":\"2\"}");
			page3.click();

			row0Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("40",row0Col0.getText());

			row0Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("OPERATIONS",row0Col1.getText());

			row1Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("50",row1Col0.getText());

			row1Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("MARKETING",row1Col1.getText());





	    }


	    @Test(groups = { "cma"})
			public void testSortingRestWSDGPaging() throws Exception {

				String browsername = getBrowserName();

				//Webdriver has issues with 2 level menus on Firefox and so commenting it out.
				if ( !(browsername.equalsIgnoreCase("firefox")) ){

					//Start the test and bring up the browser
					startupTest("ArrayFromRestWSDataGridDS.html",null);

					waitForElementVisible("id=datagridArrayPaging");

					waitForElementVisible("id=paging");

					waitForElementText("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}","10");

					WebElement row0Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("10",row0Col0.getText());

					WebElement row0Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("ACCOUNTING",row0Col1.getText());

					WebElement row0Col2 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("PORTLAND",row0Col2.getText());

					WebElement row0Col3 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("10 ACCOUNTING",row0Col3.getText());

					WebElement row0Col4 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("ACCOUNTING Hello!",row0Col4.getText());

					WebElement row1Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("12",row1Col0.getText());

					WebElement row1Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("name4534",row1Col1.getText());

					WebElement row1Col2 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("10",row1Col2.getText());

					WebElement row1Col3 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("12 name4534",row1Col3.getText());

					WebElement row1Col4 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("name4534 Hello!",row1Col4.getText());




					//This does not work as sort icon is not visible
					//WebElement lastNameSortIcon = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-sort-icon\",\"axis\":\"column\",\"index\":\"0\"}");
					//log("lastNameSortIcon" + lastNameSortIcon);
					//lastNameSortIcon.click();

					//Verify Sort Descending

					String elemLocator = "{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
					String menuLocator = "id=ui-id-10";
					String subMenuLocator = "ui-id-14";

					rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);


					row0Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("90",row0Col0.getText());

					row0Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("Dept90",row0Col1.getText());

					row0Col2 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("LOC90",row0Col2.getText());

					row0Col3 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("90 Dept90",row0Col3.getText());

					row0Col4 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("Dept90 Hello!",row0Col4.getText());

					//Verify Sort Ascending
					elemLocator = "{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
					menuLocator = "id=ui-id-10";
					subMenuLocator = "ui-id-13";

					rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);

					waitForMilliseconds(1000L);


					row0Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("10",row0Col0.getText());

					row0Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("ACCOUNTING",row0Col1.getText());

					row0Col2 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("PORTLAND",row0Col2.getText());

					row0Col3 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("10 ACCOUNTING",row0Col3.getText());

					row0Col4 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("ACCOUNTING Hello!",row0Col4.getText());




			}



		}


		@Test(groups = { "cma"})
		public void testSortingIcons() throws Exception{

				String browsername = getBrowserName();

				//Webdriver has issues with 2 level menus on Firefox and so commenting it out.
				if ( !(browsername.equalsIgnoreCase("firefox")) ){

					//Start the test and bring up the browser
					startupTest("ArrayFromRestWSDataGridDS.html",null);

					waitForElementVisible("id=datagridArrayPaging");

					waitForElementVisible("id=paging");

					waitForElementText("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}","10");

					WebElement row0Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("10",row0Col0.getText());

					WebElement row0Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("ACCOUNTING",row0Col1.getText());

					WebElement row0Col2 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("PORTLAND",row0Col2.getText());

					WebElement row0Col3 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("10 ACCOUNTING",row0Col3.getText());

					WebElement row0Col4 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("ACCOUNTING Hello!",row0Col4.getText());

					WebElement row1Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("12",row1Col0.getText());

					WebElement row1Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("name4534",row1Col1.getText());

					WebElement row1Col2 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("10",row1Col2.getText());

					WebElement row1Col3 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("12 name4534",row1Col3.getText());

					WebElement row1Col4 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("name4534 Hello!",row1Col4.getText());

					WebElement dgHeader0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}");
					dgHeader0.click();


					WebElement sortAscendingIcon = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-sort-ascending\",\"axis\":\"column\",\"index\":\"0\"}");
					sortAscendingIcon.click();

					sortAscendingIcon = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-sort-ascending\",\"axis\":\"column\",\"index\":\"0\"}");
					sortAscendingIcon.click();



					//Need to wait for Sorting to finish
					waitForMilliseconds(1000L);

					WebElement sortDescendingIcon = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-sort-descending\",\"axis\":\"column\",\"index\":\"0\"}");
					sortDescendingIcon.click();


					//Need to wait for Sorting to finish
					waitForMilliseconds(2000L);

					row0Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("90",row0Col0.getText());

					row0Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("Dept90",row0Col1.getText());

					row0Col2 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("LOC90",row0Col2.getText());

					row0Col3 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("90 Dept90",row0Col3.getText());

					row0Col4 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("Dept90 Hello!",row0Col4.getText());


					sortAscendingIcon = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-sort-ascending\",\"axis\":\"column\",\"index\":\"0\"}");
					sortAscendingIcon.click();

					waitForMilliseconds(2000L);

					row0Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("10",row0Col0.getText());

					row0Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("ACCOUNTING",row0Col1.getText());

					row0Col2 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("PORTLAND",row0Col2.getText());

					row0Col3 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("10 ACCOUNTING",row0Col3.getText());

					row0Col4 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("ACCOUNTING Hello!",row0Col4.getText());











			}



		}

		@Test(groups = { "cma"})
		public void testRestWSDGPagingInput() throws Exception {

				//Start the test and bring up the browser
				startupTest("ArrayFromRestWSDataGridDS.html",null);

				waitForElementVisible("id=datagridArrayPaging");

				waitForElementVisible("id=paging");

				waitForMilliseconds(3000L);

				waitForElementText("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}","10");

				WebElement row0Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("10",row0Col0.getText());

				WebElement row0Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("ACCOUNTING",row0Col1.getText());

				WebElement row0Col2 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("PORTLAND",row0Col2.getText());

				WebElement inputText = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-input\"}");
				log("inputText:"+ inputText);
				log("inputText:"+ inputText.getText());

				inputText.clear();
				inputText.sendKeys("2");
				waitForMilliseconds(1000L);

				row0Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("20",row0Col0.getText());

				row0Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("RESEARCH",row0Col1.getText());

				WebElement row1Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("30",row1Col0.getText());

				WebElement row1Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("SALES12",row1Col1.getText());

				inputText = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-input\"}");

				inputText.clear();
				inputText.sendKeys("3");
				waitForMilliseconds(1000L);

				row0Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("40",row0Col0.getText());

				row0Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("OPERATIONS",row0Col1.getText());

				row1Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("50",row1Col0.getText());

				row1Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("MARKETING",row1Col1.getText());

				WebElement maxInput = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-input-max\"}");
				log("Max: "+ maxInput.getText());

				Assert.assertTrue(maxInput.getText().contains("5"));

				WebElement inputSummary = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-input-summary\"}");
				log("inputSummary: "+ inputSummary.getText());

				Assert.assertTrue(inputSummary.getText().contains("5-6 of 10"));




		}

		@Test(groups = { "cma"})
		public void testRestWSDGPagingControls() throws Exception {

				//Start the test and bring up the browser
				startupTest("ArrayFromRestWSDataGridDS.html",null);

				waitForElementVisible("id=datagridArrayPaging");

				waitForElementVisible("id=paging");

				waitForElementText("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}","10");

				WebElement row0Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("10",row0Col0.getText());

				WebElement row0Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("ACCOUNTING",row0Col1.getText());

				WebElement row0Col2 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("PORTLAND",row0Col2.getText());

				WebElement next = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-next\"}");
				next.click();

				row0Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("20",row0Col0.getText());

				row0Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("RESEARCH",row0Col1.getText());

				WebElement row1Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("30",row1Col0.getText());

				WebElement row1Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("SALES12",row1Col1.getText());

				WebElement previous = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-previous\"}");
				previous.click();


				row0Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("10",row0Col0.getText());

				row0Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("ACCOUNTING",row0Col1.getText());

				row0Col2 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("PORTLAND",row0Col2.getText());


				WebElement last = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-last\"}");
				last.click();

				row0Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("80",row0Col0.getText());

				row0Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("ENGINEERING",row0Col1.getText());

				row1Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("90",row1Col0.getText());

				row1Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Dept90",row1Col1.getText());

				WebElement first = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-nav-first\"}");
				first.click();

				row0Col0 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("10",row0Col0.getText());

				row0Col1 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("ACCOUNTING",row0Col1.getText());

				row0Col2 = getElement("{\"element\":\"#datagridArrayPaging\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("PORTLAND",row0Col2.getText());






			}



    private void log(String log)
	{
		System.out.println(log);

		getLogger().fine("[RestWSDataGridDSTest] " + log);
	}


}

