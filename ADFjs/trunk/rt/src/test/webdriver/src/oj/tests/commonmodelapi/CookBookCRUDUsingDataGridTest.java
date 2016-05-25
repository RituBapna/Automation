package oj.tests.commonmodelapi;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class CookBookCRUDUsingDataGridTest extends OJetBase {

    private static final String TITLE = "CRUD - CRUD Using Data Grid";

    public CookBookCRUDUsingDataGridTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook" })
    public void testCRUDUsingDataGrid() throws Exception {
        //Start the test and bring up the browser
        startupTest("demo-crud-CRUDGrid.html",null);

        //Verify the url
		String url = getBrowserUrl();
		log("URL##########"+ url);

		// Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE);

        // Make sure data is displayed
        waitForElementVisible("id=datagrid");

        WebElement datagrid = getElement("id=datagrid");


    }

     @Test(groups = { "cma"})
			public void testCRUDDataGridHeader() throws Exception {

				//Start the test and bring up the browser
				startupTest("demo-crud-CRUDGrid.html",null);

				waitForElementVisible("id=datagrid");

				getWebDriver().manage().window().maximize();

				waitForElementText("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}","First Name");

				WebElement dgHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}");
				Assert.assertEquals("First Name",dgHeader0.getText());

				WebElement dgHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"1\"}");
				Assert.assertEquals("Last Name",dgHeader1.getText());

				WebElement dgHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"2\"}");
				Assert.assertEquals("Date Hired",dgHeader2.getText());

				WebElement dgHeader3 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"3\"}");
				Assert.assertEquals("Salary",dgHeader3.getText());




		    }

		   @Test(groups = { "cma"})
		   public void testCRUDDataGridValues() throws Exception {

				//Start the test and bring up the browser
				startupTest("demo-crud-CRUDGrid.html",null);

				waitForElementVisible("id=datagrid");

				waitForElementText("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}","Steven");

				WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("Steven",row0Col0.getText());

				WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("King",row0Col1.getText());

				WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("Jun 17, 1987",row0Col2.getText());

				WebElement row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("Neena",row1Col0.getText());

				WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Kochhar",row1Col1.getText());

				WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("Sep 21, 1989",row1Col2.getText());

		    }


		    @Test(groups = { "cma"})
				public void testCRUDDataGridSorting() throws Exception {
					String browsername = getBrowserName();

					//Webdriver has issues with 2 level menus on Firefox and so commenting it out.
					if ( !(browsername.equalsIgnoreCase("firefox")) ){

							//Start the test and bring up the browser
							startupTest("demo-crud-CRUDGrid.html",null);

							waitForMilliseconds(3000L);


							waitForElementVisible("id=datagrid");

							waitForElementText("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}","Steven");

							WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
							Assert.assertEquals("Steven",row0Col0.getText());

							WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
							Assert.assertEquals("King",row0Col1.getText());

							WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
							Assert.assertEquals("Jun 17, 1987",row0Col2.getText());

							WebElement row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
							Assert.assertEquals("Neena",row1Col0.getText());

							WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
							Assert.assertEquals("Kochhar",row1Col1.getText());

							WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
							Assert.assertEquals("Sep 21, 1989",row1Col2.getText());

							//This does not work as sort icon is not visible
							//WebElement lastNameSortIcon = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-sort-icon\",\"axis\":\"column\",\"index\":\"0\"}");
							//log("lastNameSortIcon" + lastNameSortIcon);
							//lastNameSortIcon.click();

							//Verify Sort Descending

							String elemLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
							String menuLocator = "id=ui-id-10";
							String subMenuLocator = "id=ui-id-14";

							waitForMilliseconds(1000L);

							rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);

							waitForMilliseconds(1000L);

							row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
							Assert.assertEquals("Valli",row0Col0.getText());

							row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
							Assert.assertEquals("Pataballa",row0Col1.getText());

							row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
							Assert.assertEquals("Feb 5, 1998",row0Col2.getText());

							//Verify Sort Ascending
							elemLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
							menuLocator = "id=ui-id-10";
							subMenuLocator = "id=ui-id-13";

							waitForMilliseconds(1000L);

							rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);

							waitForMilliseconds(1000L);


							row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
							Assert.assertEquals("Alexander",row0Col0.getText());

							row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
							Assert.assertEquals("Hunold",row0Col1.getText());

							row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
							Assert.assertEquals("Jan 3, 1990",row0Col2.getText());


							WebElement sortAscendingIcon = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-sort-ascending\",\"axis\":\"column\",\"index\":\"0\"}");
							sortAscendingIcon.click();


							//Need to wait for Sorting to finish
							waitForMilliseconds(1000L);

							row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
							Assert.assertEquals("Valli",row0Col0.getText());

							row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
							Assert.assertEquals("Pataballa",row0Col1.getText());

							row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
							Assert.assertEquals("Feb 5, 1998",row0Col2.getText());

							WebElement sortDescendingIcon = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-sort-descending\",\"axis\":\"column\",\"index\":\"0\"}");
							sortDescendingIcon.click();


							//Need to wait for Sorting to finish
							waitForMilliseconds(1000L);

							row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
							Assert.assertEquals("Alexander",row0Col0.getText());

							row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
							Assert.assertEquals("Hunold",row0Col1.getText());

							row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
							Assert.assertEquals("Jan 3, 1990",row0Col2.getText());

						}



			}


		   @Test(groups = { "cma"})
		   public void testCRUDDataGrid() throws Exception {

				//Start the test and bring up the browser
				startupTest("demo-crud-CRUDGrid.html",null);

				getWebDriver().manage().window().maximize();

				waitForElementVisible("id=datagrid");

				waitForElementText("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}","Steven");

				WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("Steven",row0Col0.getText());

				WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("King",row0Col1.getText());

				WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("Jun 17, 1987",row0Col2.getText());

				WebElement addbutton = getElement("id=addbutton");
				addbutton.click();

				row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("Jane",row0Col0.getText());

				row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Doe",row0Col1.getText());

				row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
				log("row0Col2.getText()"+row0Col2.getText());
				Assert.assertEquals("$15,000.00",row0Col2.getText());


				//Click first row
				getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}").click();

				getElement("id=firstNameInput").sendKeys("Updated");
				getElement("id=lastNameInput").sendKeys("Updated");
				getElement("id=inputSalary").clear();
				getElement("id=inputSalary").sendKeys("20,000.00");

				WebElement updateButton = getElement("id=updateButton");
				updateButton.click();

				waitForMilliseconds(2000L);

				row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				log("row0Col0.getText()"+row0Col0.getText());
				Assert.assertEquals("JaneUpdated",row0Col0.getText());

				row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				log("row0Col1.getText()"+row0Col1.getText());
				Assert.assertEquals("DoeUpdated",row0Col1.getText());

				row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
				log("row0Col2.getText()"+row0Col2.getText());
				Assert.assertEquals("$20,000.00",row0Col2.getText());

				getElement("id=resetFields").click();

				getElement("id=removeButton").click();

				row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("Steven",row0Col0.getText());

				row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("King",row0Col1.getText());

				row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("Jun 17, 1987",row0Col2.getText());





		    }






	    private void log(String log)
		{
			System.out.println(log);

			getLogger().fine("[CollectionDataGridDSTest] " + log);
	}


}

