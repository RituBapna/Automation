package oj.tests.commonmodelapi;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class CookBookCommonModelTest extends OJetBase {

    private static final String TITLE = "CRUD - Fetching Using a Model";
    private static final String TITLE1 = "Customizations - Custom Sync Using Browser Local Storage";
    private static final String TITLE2 = "Customizations - Custom oj.ajax Function";

    public CookBookCommonModelTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook" })
    public void testFetchUsingModel() throws Exception {
        //Start the test and bring up the browser
        startupTest("demo-crud-model.html",null);

        //Verify the url
		String url = getBrowserUrl();
		log("URL##########"+ url);

		// Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE);

        // Make sure data is displayed
        waitForElementVisible("id=deptIdURL");

        WebElement deptIdURL = getElement("id=deptIdURL");
        String directURL = getElement("id=directURL").getText();

        log("directURL from the testFetchUsingModel:"+directURL);

        Assert.assertEquals(deptIdURL.getText(), "10");
        Assert.assertEquals(getElement("id=deptNameURL").getText(), "Administration");

		Assert.assertTrue(directURL.contains("Fetched using URL set on model:"));
		Assert.assertTrue(directURL.contains("Department Id: 10"));
		Assert.assertTrue(directURL.contains("Department Name: Administration"));

		waitForElementVisible("id=deptId");

		WebElement deptId = getElement("id=deptId");
		String directModel = getElement("id=directModel").getText();

		Assert.assertEquals(deptId.getText(), "10");
		Assert.assertEquals(getElement("id=deptName").getText(), "Administration");

		Assert.assertTrue(directModel.contains("Fetched using id set on model:"));
		Assert.assertTrue(directModel.contains("Department Id: 10      Department Name: Administration"));


    }


     @Test(groups = { "cookbook" })
    public void testCustomURLGeneration() throws Exception {
        //Start the test and bring up the browser
        startupTest("demo-custom-customURL.html",null);

        //Verify the url
		String url = getBrowserUrl();
		log("URL##########"+ url);

		// Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", "Customizations - Custom URL Generation");

        // Make sure data is displayed
        waitForElementVisible("id=table");

        WebElement dgHeader0 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"0\"}");
		Assert.assertEquals("Department Id",dgHeader0.getText());

		WebElement dgHeader1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"1\"}");
		Assert.assertEquals("Department Name",dgHeader1.getText());

		WebElement row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
		Assert.assertEquals("10",row0Col1.getText());

		WebElement row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
		Assert.assertEquals("Administration",row0Col2.getText());

		WebElement row1Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
		Assert.assertEquals("20",row1Col1.getText());

		WebElement row7Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"0\"}");
		Assert.assertEquals("80",row7Col1.getText());

		WebElement row7Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"1\"}");
		Assert.assertEquals("Sales",row7Col2.getText());





	}


    @Test(groups = { "cookbook" })
	public void testCustomSyncLocalStorageHeader() throws Exception {

		//Start the test and bring up the browser
		startupTest("demo-custom-localStorage.html", null);

		waitForElementVisible("id=table");

		// Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE1);

		WebElement table = getElement("id=table");

		WebElement dgHeader0 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"0\"}");
		Assert.assertEquals("Remove",dgHeader0.getText());

		WebElement dgHeader1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"1\"}");
		Assert.assertEquals("Department Id",dgHeader1.getText());

		WebElement dgHeader2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"2\"}");
		Assert.assertEquals("Department Name",dgHeader2.getText());

		WebElement dgHeader3 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"3\"}");
		Assert.assertEquals("Location Id",dgHeader3.getText());

		WebElement dgHeader4 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"4\"}");
		Assert.assertEquals("Manager Id",dgHeader4.getText());

    }


		@Test(groups={"cookbook"})
	    public void testCustomSyncLocalStorageValues() throws Exception {

	        startupTest("demo-custom-localStorage.html", null);

	        waitForElementVisible("id=table");

			WebElement row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("10",row0Col1.getText());

			WebElement row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("Administration",row0Col2.getText());

			WebElement row1Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("20",row1Col1.getText());

			WebElement row7Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("80",row7Col1.getText());

			WebElement row7Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("Sales",row7Col2.getText());

			WebElement row7Col3 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("2500",row7Col3.getText());

			WebElement row7Col4 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("145",row7Col4.getText());

	    }

	    @Test(groups={"cookbook"})
		public void testCustomSyncLocalStorageAddDepartment() throws Exception {
		        startupTest("demo-custom-localStorage.html", null);

		        waitForElementVisible("id=table");

				WebElement newDepartName = getElement("id=newDepartName");
				newDepartName.sendKeys("MyNewDept");

				WebElement saveBtn = getElement("id=saveBtn");
				saveBtn.click();

				//Need to wait for loading to finish
				waitForMilliseconds(1000L);

				WebElement row12Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"12\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("141",row12Col1.getText());

				WebElement row12Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"12\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("MyNewDept",row12Col2.getText());

		}


	    @Test(groups={"cookbook"})
		public void testCustomSyncLocalStorageUpdateDepartment() throws Exception {
		        String browsername = getBrowserName();

				//Webdriver has issues with table cell click on Firefox and so commenting it out.
				if ( !(browsername.equalsIgnoreCase("firefox")) ){
					startupTest("demo-custom-localStorage.html", null);

					waitForElementVisible("id=table");

					WebElement row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("Administration",row0Col2.getText());

					//row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
					log("row0Col2:"+row0Col2);
					row0Col2.click();

					//Cancel the dialog
					waitForElementVisible("id=resetBtn");
					WebElement reset = getElement("id=resetBtn");
					reset.click();

					//Bring up the dialog again
					row0Col2.click();

					//Verify dialog title
					WebElement dialogTitle = getElement("id=dialog-title-id");
					Assert.assertEquals(dialogTitle.getText(),"Change Department Name");

					WebElement newName = getElement("id=newName");
					newName.clear();
					newName.sendKeys("New Dept Name");

					WebElement submit = getElement("id=submitBtn");
					log("SubmitBtn:  "+ submit);
					submit.click();

					waitForElementVisible("id=table");

					waitForMilliseconds(1000L);

					WebElement row0Col2New = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("New Dept Name",row0Col2New.getText());
				}


		}

		@Test(groups={"cookbook"})
		    public void testCustomSyncLocalStorageDeleteDepartment() throws Exception {
		        startupTest("demo-custom-localStorage.html", null);

		        waitForElementVisible("id=table");

				WebElement row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("Administration",row0Col2.getText());

				//check the first checkbox
				getElement("id=10").click();

				//Verify dialog title
				WebElement deleteDept_btn = getElement("id=deleteDept_btn");
				deleteDept_btn.click();

				WebElement row0Col1New = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("20",row0Col1New.getText());

				WebElement row0Col2New = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("Marketing",row0Col2New.getText());


	}

	 @Test(groups = { "cookbook" })
		public void testCustomAjaxHeader() throws Exception {

			//Start the test and bring up the browser
			startupTest("demo-custom-ajax.html", null);

			waitForElementVisible("id=table");

			// Verify if the title of the page is correct
			verifyTitle("Incorrect page title;", TITLE2);

			WebElement table = getElement("id=table");

			WebElement dgHeader0 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"0\"}");
			Assert.assertEquals("Department Id",dgHeader0.getText());

			WebElement dgHeader1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"1\"}");
			Assert.assertEquals("Department Name",dgHeader1.getText());

		}


		@Test(groups={"cookbook"})
	    public void testCustomAjaxValues() throws Exception {

	        startupTest("demo-custom-ajax.html", null);

	        waitForElementVisible("id=table");

			WebElement row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("10",row0Col1.getText());

			WebElement row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("Administration",row0Col2.getText());

			WebElement row1Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("20",row1Col1.getText());

			WebElement row7Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("80",row7Col1.getText());

			WebElement row7Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("Sales",row7Col2.getText());

	    }

	     @Test(groups = { "cookbook" })
		    public void testFilterFullyFetchedCollection() throws Exception {
		        //Start the test and bring up the browser
		        startupTest("demo-filter-full.html",null);

		        //Verify the url
				String url = getBrowserUrl();
				log("URL##########"+ url);

				// Verify if the title of the page is correct
		        verifyTitle("Incorrect page title;", "Filtering - Fully-fetched Collection");

		        // Make sure data is displayed
		        waitForElementVisible("id=table");

		        WebElement dgHeader0 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"0\"}");
				Assert.assertEquals("Department Id",dgHeader0.getText());

				WebElement dgHeader1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"1\"}");
				Assert.assertEquals("Department Name",dgHeader1.getText());

				WebElement row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("10",row0Col1.getText());

				WebElement row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Administration",row0Col2.getText());

				WebElement row1Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("20",row1Col1.getText());

				WebElement row7Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("80",row7Col1.getText());

				WebElement row7Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Sales",row7Col2.getText());

				getElement("id=filterDepartId").sendKeys("70,80");
				getElement("id=filterBtn").click();

				//Verify filter data
				row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("70",row0Col1.getText());

				row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Inventory",row0Col2.getText());

				row1Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("80",row1Col1.getText());

				WebElement row1Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Sales",row1Col2.getText());


				getElement("id=resetBtn").click();

				getElement("id=filterDepartId").clear();
				getElement("id=filterDepartId").sendKeys("30,40");
				getElement("id=filterBtn").click();

				//Verify filter data
				row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("30",row0Col1.getText());

				row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Transportation",row0Col2.getText());

				row1Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("40",row1Col1.getText());

				row1Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Shipping",row1Col2.getText());



			}


			@Test(groups = { "cookbook" })
			public void testFilterVirtualCollection() throws Exception {
				//Start the test and bring up the browser
				startupTest("demo-filter-virtual.html",null);

				//Verify the url
				String url = getBrowserUrl();
				log("URL##########"+ url);

				// Verify if the title of the page is correct
				verifyTitle("Incorrect page title;", "Filtering - Virtual Collection");

				// Make sure data is displayed
				waitForElementVisible("id=datagrid");

				WebElement dgHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}");
				Assert.assertEquals("DepartmentId",dgHeader0.getText());

				WebElement dgHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"1\"}");
				Assert.assertEquals("DepartmentName",dgHeader1.getText());

				WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("10",row0Col1.getText());

				WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Administration",row0Col2.getText());

				WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("20",row1Col1.getText());

				WebElement row7Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("80",row7Col1.getText());

				WebElement row7Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Sales",row7Col2.getText());

				getElement("id=filterDepartId").sendKeys("70,80");
				getElement("id=filterBtn").click();

				waitForMilliseconds(2000L);

				//Verify filter data
				row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("70",row0Col1.getText());

				row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Inventory",row0Col2.getText());

				row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("80",row1Col1.getText());

				WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Sales",row1Col2.getText());


				getElement("id=resetBtn").click();

				getElement("id=filterDepartId").clear();
				getElement("id=filterDepartId").sendKeys("130,140");
				getElement("id=filterBtn").click();

				waitForMilliseconds(2000L);

				//Verify filter data
				row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("130",row0Col1.getText());

				row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Billing",row0Col2.getText());

				row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("140",row1Col1.getText());

				row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Control And Credit",row1Col2.getText());



			}



			@Test(groups = { "cookbook" })
			public void testSorting() throws Exception {
				//Start the test and bring up the browser
				startupTest("demo-sort-sortopt.html",null);

				//Verify the url
				String url = getBrowserUrl();
				log("URL##########"+ url);

				// Verify if the title of the page is correct
				verifyTitle("Incorrect page title;", "Sorting - Sorting Options");

				// Make sure data is displayed
		        waitForElementVisible("id=table");

		        WebElement dgHeader0 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"0\"}");
				Assert.assertEquals("Department Id",dgHeader0.getText());

				WebElement dgHeader1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"1\"}");
				Assert.assertEquals("Department Name",dgHeader1.getText());

				WebElement row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("10",row0Col1.getText());

				WebElement row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Administration",row0Col2.getText());

				WebElement row1Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("130",row1Col1.getText());

				WebElement row7Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("20",row7Col1.getText());

				WebElement row7Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Marketing",row7Col2.getText());

				//Sort Descending
				getElement("id=dsc").click();

				row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("30",row0Col1.getText());

				row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Transportation",row0Col2.getText());

				row1Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("40",row1Col1.getText());

				row7Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("100",row7Col1.getText());

				row7Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Finance",row7Col2.getText());

				//Sort Ascending
				getElement("id=asc").click();

				row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("10",row0Col1.getText());

				row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Administration",row0Col2.getText());

				row1Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("130",row1Col1.getText());

				row7Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("20",row7Col1.getText());

				row7Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Marketing",row7Col2.getText());

				getElement("id=function").click();

				row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("140",row0Col1.getText());

				row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Control And Credit",row0Col2.getText());

				row1Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("130",row1Col1.getText());

				row7Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("50",row7Col1.getText());

				row7Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Human Resources",row7Col2.getText());

				getElement("id=attribute").click();

				row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("10",row0Col1.getText());

				row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Administration",row0Col2.getText());

				row1Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("130",row1Col1.getText());

				row7Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("20",row7Col1.getText());

				row7Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Marketing",row7Col2.getText());








			}






		private void log(String log)
		{
			System.out.println(log);
			getLogger().fine("[CookBookCommonModelTest] " + log);
		}


}

