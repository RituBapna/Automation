package oj.tests.commonmodelapi;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class CollectionDataGridDSTest extends OJetBase {

    private static final String TITLE = "DataGrid using Collection";

    public CollectionDataGridDSTest() {
        super(new TestConfigBuilder().setContextRoot("commonmodelapi").setAppRoot("application").build());
    }

    @Test(groups = { "cma"})
    public void testrestCollDS() throws Exception {
        //Start the test and bring up the browser
        startupTest("CollectionDataGridDS.html",null);

        //Verify the url
		String url = getBrowserUrl();
		log("URL##########"+ url);

		// Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE);

        // Make sure datagrid is displayed
        waitForElementVisible("id=datagrid");


    }


    @Test(groups = { "cma"})
		public void testrestCollDSHeader() throws Exception {

			//Start the test and bring up the browser
			startupTest("CollectionDataGridDS.html",null);

			waitForElementVisible("id=datagrid");

			waitForElementText("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}","Deptno");

			WebElement dgHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}");
			Assert.assertEquals("Deptno",dgHeader0.getText());

			WebElement dgHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"1\"}");
			Assert.assertEquals("Dname",dgHeader1.getText());

			WebElement dgHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"2\"}");
			Assert.assertEquals("Loc",dgHeader2.getText());




	    }

	   @Test(groups = { "cma"})
	   public void testrestCollDSValues() throws Exception {

			//Start the test and bring up the browser
			startupTest("CollectionDataGridDS.html",null);

			waitForElementVisible("id=datagrid");

			waitForElementText("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}","10");

			WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("10",row0Col0.getText());

			WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("ACCOUNTING",row0Col1.getText());

			WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("PORTLAND",row0Col2.getText());

			WebElement row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("12",row1Col0.getText());

			WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("name4534",row1Col1.getText());

			WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("10",row1Col2.getText());

	    }


	    @Test(groups = { "cma"})
			public void testSortingRestCollDS() throws Exception {
				String browsername = getBrowserName();

				//Webdriver has issues with 2 level menus on Firefox and so commenting it out.
				if ( !(browsername.equalsIgnoreCase("firefox")) ){

						//Start the test and bring up the browser
						startupTest("CollectionDataGridDS.html",null);

						waitForMilliseconds(3000L);


						waitForElementVisible("id=datagrid");

						waitForElementText("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}","10");

						WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
						Assert.assertEquals("10",row0Col0.getText());

						WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
						Assert.assertEquals("ACCOUNTING",row0Col1.getText());

						WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
						Assert.assertEquals("PORTLAND",row0Col2.getText());

						WebElement row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
						Assert.assertEquals("12",row1Col0.getText());

						WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
						Assert.assertEquals("name4534",row1Col1.getText());

						WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
						Assert.assertEquals("10",row1Col2.getText());


						//This does not work as sort icon is not visible
						//WebElement lastNameSortIcon = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-sort-icon\",\"axis\":\"column\",\"index\":\"0\"}");
						//log("lastNameSortIcon" + lastNameSortIcon);
						//lastNameSortIcon.click();

						//Verify Sort Descending

						String elemLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
						String menuLocator = "id=ui-id-1";
						String subMenuLocator = "id=ui-id-3";

						rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);

						row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
						Assert.assertEquals("90",row0Col0.getText());

						row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
						Assert.assertEquals("Dept90",row0Col1.getText());

						row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
						Assert.assertEquals("LOC90",row0Col2.getText());

						//Verify Sort Ascending
						elemLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
						menuLocator = "id=ui-id-1";
						subMenuLocator = "id=ui-id-2";

						rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);


						row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
						Assert.assertEquals("10",row0Col0.getText());

						row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
						Assert.assertEquals("ACCOUNTING",row0Col1.getText());

						row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
						Assert.assertEquals("PORTLAND",row0Col2.getText());


						WebElement sortAscendingIcon = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-sort-ascending\",\"axis\":\"column\",\"index\":\"0\"}");
						sortAscendingIcon.click();


						//Need to wait for Sorting to finish
						waitForMilliseconds(1000L);

						row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
						Assert.assertEquals("90",row0Col0.getText());

						row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
						Assert.assertEquals("Dept90",row0Col1.getText());

						row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
						Assert.assertEquals("LOC90",row0Col2.getText());

						WebElement sortDescendingIcon = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-sort-descending\",\"axis\":\"column\",\"index\":\"0\"}");
						sortDescendingIcon.click();


						//Need to wait for Sorting to finish
						waitForMilliseconds(1000L);

						row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
						Assert.assertEquals("10",row0Col0.getText());

						row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
						Assert.assertEquals("ACCOUNTING",row0Col1.getText());

						row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
						Assert.assertEquals("PORTLAND",row0Col2.getText());
					}



		}






    private void log(String log)
	{
		System.out.println(log);

		getLogger().fine("[CollectionDataGridDSTest] " + log);
	}


}

