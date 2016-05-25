package oj.tests.commonmodelapi;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class ToDoSampleTest extends OJetBase {

    private static final String TITLE = "ToDo List";

    public ToDoSampleTest() {
        super(new TestConfigBuilder().setContextRoot("commonmodelapi").setAppRoot("application").build());
    }

    @Test(groups = { "cma"})
    public void bringUpCommonModelCreate() throws Exception {
        //Start the test and bring up the browser
        startupTest("ToDoFetchFromLocalJSON.html",null);

        //Verify the url
		String url = getBrowserUrl();
		log("URL##########"+ url);

		// Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE);

        // Make sure save button and table
        waitForElementVisible("id=addBtn");

        waitForElementVisible("id=table");


    }

    @Test(groups={"todo"})
    public void verifyTableData() throws Exception {

        startupTest("ToDoFetchFromLocalJSON.html",null);

        waitForElementVisible("id=table");

        WebElement row0Col0 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
		Assert.assertEquals("1",row0Col0.getText());

		WebElement row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
		Assert.assertEquals("Todo One",row0Col1.getText());

		WebElement row1Col0 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
		Assert.assertEquals("2",row1Col0.getText());

		WebElement row1Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
		Assert.assertEquals("Todo Two",row1Col1.getText());

    }

    @Test(groups={"todo"})
	    public void addOnTable() throws Exception {

	        startupTest("ToDoFetchFromLocalJSON.html",null);

	        waitForElementVisible("id=table");

	        //Test Add

	        // Make sure save button and table
        	waitForElementVisible("id=addBtn");

        	getElement("id=addBtn").click();

        	getElement("id=id").sendKeys("4");
        	getElement("id=title").sendKeys("Todo Four");
        	getElement("id=createdBy").sendKeys("JohnJ");

        	//log(""+ getElement("id=addClose"));

        	waitForElementVisible("id=addNewRow");

        	WebElement addNewRow = getElement("id=addNewRow");
        	log("addNewRow"+addNewRow);
        	addNewRow.click();

        	WebElement row4Col3 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("4",row4Col3.getText());

			WebElement row4Col4 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("Todo Four",row4Col4.getText());

			WebElement row4Col5 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("JohnJ",row4Col5.getText());


			waitForText("resultsFunction", "Changes after Add: 0,1,2,3");

		}


		@Test(groups={"todo"})
	    public void deleteOnTable() throws Exception {

	        startupTest("ToDoFetchFromLocalJSON.html",null);

	        waitForElementVisible("id=table");

			//Delete the row
			WebElement del1 = getElement("id=Del1");
			log("Del1: "+ del1);
			del1.click();



			boolean isDel1Displayed  = true;

			try{
				del1 = getElement("id=Del1");
				log("del1"+ del1);
				isDel1Displayed = del1.isDisplayed();
				log("isDel1Displayed" + isDel1Displayed);
			}catch(org.openqa.selenium.NoSuchElementException nse){
				isDel1Displayed = false;
			}

			Assert.assertEquals(isDel1Displayed, false);

			waitForText("resultsFunction", "Changes after delete: 0,1,2");

	}

		@Test(groups={"todo"})
		public void refetchOnTable() throws Exception {

			startupTest("ToDoFetchFromLocalJSON.html",null);

			waitForElementVisible("id=table");

			// Make sure save button and table
        	waitForElementVisible("id=addBtn");

        	getElement("id=addBtn").click();

        	getElement("id=id").sendKeys("4");
        	getElement("id=title").sendKeys("Todo Four");
        	getElement("id=createdBy").sendKeys("JohnJ");

        	//log(""+ getElement("id=addClose"));

        	waitForElementVisible("id=addNewRow");

        	WebElement addNewRow = getElement("id=addNewRow");
        	log("addNewRow"+addNewRow);
        	addNewRow.click();

        	WebElement row4Col3 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("4",row4Col3.getText());

			WebElement row4Col4 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("Todo Four",row4Col4.getText());

			WebElement row4Col5 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"3\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("JohnJ",row4Col5.getText());


			waitForText("resultsFunction", "Changes after Add: 0,1,2,3");

			//Delete the row
			WebElement del1 = getElement("id=Del1");
			log("Del1: "+ del1);
			del1.click();



			boolean isDel1Displayed  = true;

			try{
				del1 = getElement("id=Del1");
				log("del1"+ del1);
				isDel1Displayed = del1.isDisplayed();
				log("isDel1Displayed" + isDel1Displayed);
			}catch(org.openqa.selenium.NoSuchElementException nse){
				isDel1Displayed = false;
			}

			Assert.assertEquals(isDel1Displayed, false);

			waitForText("resultsFunction", "Changes after delete: 0,1,2,3");

			getElement("id=refetch").click();
			waitForMilliseconds(2000L);
			waitForText("resultsFunction", "Changes after Refetch: 0,1,2,3");

			getElement("id=refetch").click();
			waitForMilliseconds(2000L);
			waitForText("resultsFunction", "Changes after Refetch: 0,1,2");

		}



	@Test(groups={"todo"})
		    public void testUpdate() throws Exception {

		        startupTest("ToDoFetchFromLocalJSON.html",null);

		        waitForElementVisible("id=table");

		        getWebDriver().manage().window().maximize();

		        //Test Add

		        // Make sure save button and table
	        	waitForElementVisible("id=addBtn");

	        	//Test Update
				WebElement editIcon = getElement("id=Edit2");
				log("editIcon"+editIcon);
				editIcon.click();

				waitForMilliseconds(5000L);

				waitForElementVisible("id=edit1");

				WebElement titleEle = getElement("id=title11");
				titleEle.click();
				log("title:"+titleEle);
				log("visible: "+ titleEle.isDisplayed());

				waitForElementPresent("id=title11");

				titleEle.clear();
				titleEle.sendKeys("Todo Two Updated");
				getElement("id=createdBy11").clear();
				getElement("id=createdBy11").sendKeys("AnnHill  Updated");

				WebElement edit1 = getElement("id=edit1");
				log("edit1:"+ edit1);
				edit1.click();

				waitForElementVisible("id=table");


				WebElement row4Col4 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
				log("row4Col4.getText(): "+ row4Col4.getText());
				Assert.assertEquals("Todo Two Updated",row4Col4.getText());

				WebElement row4Col5 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
				Assert.assertEquals("AnnHill Updated",row4Col5.getText());

				waitForText("resultsFunction", "Changes after Edit: 0,1,2");
  }


    private void log(String log)
	{
		System.out.println(log);

		getLogger().fine("[ToDoSampleTest] " + log);
	}


}

