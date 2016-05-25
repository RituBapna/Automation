package oj.tests.publicsamples;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class JETCommonModelCrudTest extends OJetBase {

    private static final String TITLE = "Oracle JET Common Model - CRUD";

    public JETCommonModelCrudTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/public_samples").setAppRoot("JET-CommonModel-CRUD/public_html").build());
    }

    @Test(groups = { "cma"})
    public void bringUpCommonModelCreate() throws Exception {
        //Start the test and bring up the browser
        startupTest("index.html",null);

        //Verify the url
		String url = getBrowserUrl();
		log("URL##########"+ url);

		// Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE);

        // Make sure save button and table
        waitForElementVisible("id=saveBtn");

        waitForElementVisible("id=table");


    }

    @Test(groups={"cmCrud"})
    public void testSortingTable() throws Exception {

        String browsername = getBrowserName();

		//Webdriver has issues with 2 level menus on Firefox and so commenting it out.

		if ( !(browsername.equalsIgnoreCase("firefox")) ){
			startupTest("index.html", null);

			waitForElementVisible("id=table");

			WebElement row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("10",row0Col1.getText());

			WebElement row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("Administration",row0Col2.getText());

			//Verify Sort Descending

			String elemLocator = "{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"1\"}";
			String menuLocator = "id=ui-id-1";
			String subMenuLocator = "ui-id-3";

			rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);

			waitForMilliseconds(2000L);


			row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("140",row0Col1.getText());

			WebElement row1Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("130",row1Col1.getText());

			WebElement row2Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("110",row2Col1.getText());

			WebElement row3Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("100",row3Col1.getText());

			//Verify Sort Ascending

			elemLocator = "{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"1\"}";
			menuLocator = "id=ui-id-1";
			subMenuLocator = "ui-id-2";

			rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);

			waitForMilliseconds(2000L);


			row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("10",row0Col1.getText());

			row1Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("20",row1Col1.getText());

			row2Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("30",row2Col1.getText());

			row3Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("40",row3Col1.getText());


			WebElement sortAscendingIcon = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-sort-ascending\",\"index\":\"1\"}");
			sortAscendingIcon.click();

			//Need to wait for page to be loaded
			waitForMilliseconds(5000L);

			row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("140",row0Col1.getText());

			row1Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("130",row1Col1.getText());

			row2Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("110",row2Col1.getText());

			row3Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("100",row3Col1.getText());

			WebElement sortDescendingIcon = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-sort-descending\",\"index\":\"1\"}");
			sortDescendingIcon.click();

			//Need to wait for page to be loaded
			waitForMilliseconds(5000L);

			row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("10",row0Col1.getText());

			row1Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("20",row1Col1.getText());

			row2Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("30",row2Col1.getText());

			row3Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("40",row3Col1.getText());
		}




    }

    @Test(groups={"cmCrud"})
	    public void addDepartment() throws Exception {
	        startupTest("index.html", null);

	        waitForElementVisible("id=table");

			WebElement newDepartId = getElement("id=newDepartId");
			newDepartId.clear();
			newDepartId.sendKeys("9");

			WebElement newDepartName = getElement("id=newDepartName");
			log("newDepartName before: " + newDepartName);
			waitForMilliseconds(3000L);
			newDepartName.sendKeys("MyNewDept");

			waitForMilliseconds(3000L);

			WebElement saveBtn = getElement("id=saveBtn");
			saveBtn.click();

			//Need to wait for loading to finish
			waitForMilliseconds(3000L);

			WebElement row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("9",row0Col1.getText());

			WebElement row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
			log("DeptName: "+ row0Col2.getText());
			Assert.assertEquals("MyNewDept",row0Col2.getText());



	}

    @Test(groups={"cmCrud"})
	    public void updateDepartment() throws Exception {
	        String browsername = getBrowserName();

				//Webdriver has issues with table cell click on Firefox and so commenting it out.
				if ( !(browsername.equalsIgnoreCase("firefox")) ){
					startupTest("index.html", null);

				waitForElementVisible("id=table");

				WebElement row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("Administration",row0Col2.getText());

				row0Col2.click();

				waitForElementVisible("id=resetBtn");

				//Cancel the dialog
				getElement("id=resetBtn").click();

				//Bring up the dialog again
				row0Col2.click();

				//Verify dialog title
				WebElement dialogTitle = getElement("id=dialog-title-id");
				Assert.assertEquals(dialogTitle.getText(),"Change Department Name");

				WebElement newName = getElement("id=newName");
				newName.clear();
				newName.sendKeys("New Dept Name");

				getElement("id=submitBtn").click();

				waitForElementVisible("id=table");

				WebElement row0Col2New = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("New Dept Name",row0Col2New.getText());
			}


	}

	@Test(groups={"cmCrud"})
	    public void deleteDepartment() throws Exception {
	        startupTest("index.html", null);

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


	private void log(String log)
	{
		System.out.println(log);

		getLogger().fine("[JetCommonModelCrudTest] " + log);
	}


}
