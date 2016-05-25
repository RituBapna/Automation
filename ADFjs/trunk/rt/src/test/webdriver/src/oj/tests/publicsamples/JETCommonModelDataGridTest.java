package oj.tests.publicsamples;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class JETCommonModelDataGridTest extends OJetBase {

    private static final String TITLE = "Oracle JET Common Model - Datagrid";

    public JETCommonModelDataGridTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/public_samples").setAppRoot("JET-CommonModel-Datagrid/public_html").build());
    }

    @Test(groups = { "cma"})
    public void bringUpCommonModelDataGrid() throws Exception {
        //Start the test and bring up the browser
        startupTest("index.html",null);

        //Verify the url
		String url = getBrowserUrl();
		log("URL##########"+ url);

		// Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE);

        // Make sure save button and table
        waitForElementVisible("id=datagrid");

    }

   @Test(groups = { "cma"})
   		public void testcmDataGridHeader() throws Exception {

   			//Start the test and bring up the browser
   			startupTest("index.html",null);

   			waitForElementVisible("id=datagrid");

   			waitForElementText("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}","DepartmentId");

   			WebElement dgHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}");
   			Assert.assertEquals("DepartmentId",dgHeader0.getText());

   			WebElement dgHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"1\"}");
   			Assert.assertEquals("DepartmentName",dgHeader1.getText());

   	    }

   	   @Test(groups = { "cma"})
   	   public void testcmDataGridValues() throws Exception {

   			//Start the test and bring up the browser
   			startupTest("index.html",null);

   			waitForElementVisible("id=datagrid");

   			waitForElementText("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}","0");

   			WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
   			Assert.assertEquals("0",row0Col0.getText());

   			WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
   			Assert.assertEquals("Department 0",row0Col1.getText());

   			WebElement row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
   			Assert.assertEquals("1",row1Col0.getText());

   			WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
   			Assert.assertEquals("Department 1",row1Col1.getText());


   	    }


   	    @Test(groups = { "cma"})
   			public void testSortingcmDataGrid() throws Exception {

   				String browsername = getBrowserName();

				//Webdriver has issues with 2 level menus on Firefox and so commenting it out.
				if ( !(browsername.equalsIgnoreCase("firefox")) ){

						//Start the test and bring up the browser

   					startupTest("index.html",null);

   					waitForElementVisible("id=datagrid");

   					waitForElementText("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}","0");

   					WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("0",row0Col0.getText());

					WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("Department 0",row0Col1.getText());

					WebElement row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("1",row1Col0.getText());

					WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("Department 1",row1Col1.getText());



   					//Verify Sort Descending

   					String elemLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
   					String menuLocator = "id=ui-id-1";
   					String subMenuLocator = "id=ui-id-3";

   					waitForMilliseconds(2000L);

   					rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);
   					waitForMilliseconds(2000L);

   					row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
   					Assert.assertEquals("999",row0Col0.getText());

   					row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
   					Assert.assertEquals("Department 999",row0Col1.getText());

   					row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
   					Assert.assertEquals("998",row1Col0.getText());

   					row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
   					Assert.assertEquals("Department 998",row1Col1.getText());

   					//Verify Sort Ascending
   					elemLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
   					menuLocator = "id=ui-id-1";
   					subMenuLocator = "id=ui-id-2";

   					waitForMilliseconds(1000L);
   					rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);
   					waitForMilliseconds(1000L);

   					row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("0",row0Col0.getText());

					row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("Department 0",row0Col1.getText());

					row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("1",row1Col0.getText());

					row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("Department 1",row1Col1.getText());

					WebElement sortAscendingIcon = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-sort-ascending\",\"axis\":\"column\",\"index\":\"0\"}");
   					sortAscendingIcon.click();


   					//Need to wait for Sorting to finish
   					waitForMilliseconds(1000L);

   					row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
   					Assert.assertEquals("999",row0Col0.getText());

   					row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
   					Assert.assertEquals("Department 999",row0Col1.getText());

   					row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
   					Assert.assertEquals("998",row1Col0.getText());

   					row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
   					Assert.assertEquals("Department 998",row1Col1.getText());

   					WebElement sortDescendingIcon = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-sort-descending\",\"axis\":\"column\",\"index\":\"0\"}");
   					sortDescendingIcon.click();


   					waitForMilliseconds(1000L);

   					row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("0",row0Col0.getText());

					row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("Department 0",row0Col1.getText());

					row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
					Assert.assertEquals("1",row1Col0.getText());

					row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
					Assert.assertEquals("Department 1",row1Col1.getText());

			}

   		}



	private void log(String log)
	{
		System.out.println(log);

		getLogger().fine("[JETCommonModelDataGridTest] " + log);
	}


}
