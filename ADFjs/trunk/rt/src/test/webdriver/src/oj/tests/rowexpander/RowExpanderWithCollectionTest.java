package oj.tests.rowexpander;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class RowExpanderWithCollectionTest extends OJetBase {

	private static final String TITLE_REX = "RowExpander with Collection";



    public RowExpanderWithCollectionTest() {
        super(new TestConfigBuilder().setContextRoot("rowexpander").setAppRoot("rowexpanderTest").build());
    }


    @Test(groups = { "rex" })
	public void loadPage() throws Exception {

			//Start the test and bring up the browser
			startupTest("rowExpanderWithCollection.html",null);

			//Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
			verifyTitle("Incorrect page title;", TITLE_REX);

			waitForElementVisible("id=datagrid");

    }


    @Test(groups = { "rex" })
	public void testRowExHeader() throws Exception {

		//Start the test and bring up the browser
		startupTest("rowExpanderWithCollection.html",null);

		waitForElementVisible("id=datagrid");

		WebElement dg = getElement("id=datagrid");
		log("###### dg"+ dg);

		WebElement dgHeader0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}");
		Assert.assertEquals("Last Name",dgHeader0.getText());

		WebElement dgHeader1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"1\"}");
		Assert.assertEquals("First Name",dgHeader1.getText());

		WebElement dgHeader2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"2\"}");
		Assert.assertEquals("Salary",dgHeader2.getText());





    }

    @Test(groups = { "rex" })
		public void testRowExValues() throws Exception {

			//Start the test and bring up the browser
			startupTest("rowExpanderWithCollection.html",null);

			waitForElementVisible("id=datagrid");

			WebElement dg = getElement("id=datagrid");

			WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("King",row0Col0.getText());

			WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("Steven",row0Col1.getText());

			WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("24000",row0Col2.getText());

			WebElement row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("Robert",row1Col0.getText());

			WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
			Assert.assertEquals("James",row1Col1.getText());

			WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("3000",row1Col2.getText());

			log("row expander icon element " + getElement("id=100"));


			WebElement t1Icon = getElement("{\"element\":\"#100\",\"subId\":\"oj-rowexpander-disclosure\"}");
			t1Icon.click();

			row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("Hartstein",row1Col0.getText());

			//Need to wait for loading to finish
			waitForMilliseconds(1000L);

			WebElement t2Icon = getElement("{\"element\":\"#201\",\"subId\":\"oj-rowexpander-disclosure\"}");
			t2Icon.click();

			WebElement row2Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
			Assert.assertEquals("Fay",row2Col0.getText());

    }

    @Test(groups = { "rex" })
		public void testSortingRowEx() throws Exception {
			String browsername = getBrowserName();

			//Webdriver has issues with 2 level menus on Firefox and so commenting it out.
			if ( !(browsername.equalsIgnoreCase("firefox")) ){

				//Start the test and bring up the browser
				startupTest("rowExpanderWithCollection.html",null);

				waitForElementVisible("id=datagrid");

				WebElement dg = getElement("id=datagrid");

				WebElement row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("King",row0Col0.getText());

				WebElement row0Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("Steven",row0Col1.getText());

				WebElement row0Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("24000",row0Col2.getText());

				WebElement row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("Robert",row1Col0.getText());

				WebElement row1Col1 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"1\"}");
				Assert.assertEquals("James",row1Col1.getText());

				WebElement row1Col2 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("3000",row1Col2.getText());


				//This does not work as sort icon is not visible
				//WebElement lastNameSortIcon = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-sort-icon\",\"axis\":\"column\",\"index\":\"0\"}");
				//log("lastNameSortIcon" + lastNameSortIcon);
				//lastNameSortIcon.click();

				//Verify Sort Ascending

				String elemLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
				String menuLocator = "id=ui-id-2";
				String subMenuLocator = "id=ui-id-5";

				rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);

				waitForMilliseconds(2000L);

				row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("Hanes",row0Col0.getText());

				row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("King",row1Col0.getText());

				WebElement row2Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("Robert",row2Col0.getText());


				//Verify Sort Descending

				elemLocator = "{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-header\",\"axis\":\"column\",\"index\":\"0\"}";
				menuLocator = "id=ui-id-2";
				subMenuLocator = "id=ui-id-6";

				rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);

				waitForMilliseconds(2000L);

				row0Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("Robert",row0Col0.getText());

				row1Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("King",row1Col0.getText());

				row2Col0 = getElement("{\"element\":\"#datagrid\",\"subId\":\"oj-datagrid-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"0\"}");
				Assert.assertEquals("Hanes",row2Col0.getText());
			}




		}





	private void log(String log)
	    {
	        System.out.println(log);

			getLogger().fine("[RowExpanderWithCollectionTest] " + log);
    }


}
