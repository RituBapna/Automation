package oj.tests.commonmodelapi;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CollectionFunctionVirtualTest extends OJetBase {

    private static final String TITLE = "Collection Functions";
    private static final String RESULTS_CONTAINER = "resultsFunction";

    public CollectionFunctionVirtualTest() {
        super(new TestConfigBuilder().setContextRoot("commonmodelapi").setAppRoot("application").build());
    }

    @Test(groups = { "cma"})
    public void testrestCollFunc() throws Exception {
        //Start the test and bring up the browser
        startupTest("CollectionFunctionsVirtual.html",null);

        //Verify the url
		String url = getBrowserUrl();
		log("URL##########"+ url);

		// Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE);

        // Make sure datagrid is displayed
        waitForElementVisible("id=deptTable");


    }

     @Test(groups = { "cma"}, dependsOnMethods = { "testrestCollFunc"})
	public void testGetCollectionProperties() throws Exception {

				//Start the test and bring up the browser
				startupTest("CollectionFunctionsVirtual.html",null);

				waitForElementVisible("id=deptTable");

				//Need to wait for page to be loaded
				waitForMilliseconds(5000L);

				WebElement collPropBtn = getElement("id=displayProperties");
				collPropBtn.click();

				waitForText(RESULTS_CONTAINER, "Comparator: Dname, fetchSize: 5");
				waitForText(RESULTS_CONTAINER, "lastFetchCount: 5, lastFetchSize: 5");
				waitForText(RESULTS_CONTAINER, "length: 10, modelLimit: -1");


		}

		@Test(groups = { "cma"}, dependsOnMethods = { "testGetCollectionProperties"})
			   public void testModelProperties() throws Exception {

					//Start the test and bring up the browser
					startupTest("CollectionFunctionsVirtual.html",null);

					waitForElementVisible("id=deptTable");

					//Need to wait for page to be loaded
					waitForMilliseconds(5000L);

					WebElement modelPropBtn = getElement("id=displayModelProperties");
					modelPropBtn.click();

					waitForMilliseconds(2000L);

					waitForText(RESULTS_CONTAINER, "Attributes: Deptno: 12, Dname: name4534, Loc:10");

					WebElement modelAttributes = getElement("id=modelAttributes");
					modelAttributes.click();

					waitForText(RESULTS_CONTAINER, "Attributes: Deptno: 30, Dname: SALES, Loc:CHICAGO");
					waitForText(RESULTS_CONTAINER, "[\"Deptno\",\"30\"] [\"Dname\",\"SALES\"] [\"Loc\",\"CHICAGO\"]");

					waitForText(RESULTS_CONTAINER, "\"Deptno\": 30 \"Dname\": SALES \"Loc\": CHICAGO");
					waitForText(RESULTS_CONTAINER, "{\"Deptno\":\"30\",\"Dname\":\"SALES\"}");


					WebElement testlastIndexOf = getElement("id=testlastIndexOf");
					testlastIndexOf.click();

					waitForText(RESULTS_CONTAINER, "Message: lastIndexOf not valid on a virtual Collection");

					WebElement findWhereId = getElement("id=findWhereId");
					findWhereId.click();

					waitForText(RESULTS_CONTAINER, "{\"Deptno\":12,\"Dname\":\"name4534\",\"Loc\":\"10\",");



		}


	   @Test(groups = { "cma"}, dependsOnMethods = { "testModelProperties"})
	   public void deleteModelFromColl() throws Exception {

			//Start the test and bring up the browser
			startupTest("CollectionFunctionsVirtual.html",null);

			waitForElementVisible("id=deptTable");

			//Need to wait for page to be loaded
			waitForMilliseconds(3000L);

			WebElement row0Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("12",row0Col0.getText());

			WebElement row0Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("name4534",row0Col1.getText());

			WebElement row0Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("10",row0Col2.getText());


			WebElement removeDept = getElement("id=removeDept");
			removeDept.click();

			waitForMilliseconds(3000L);

			waitForText(RESULTS_CONTAINER, "Length: 9");

			//Remove this when bug is fixed - table doesnt get updated - Bug 21163805

			/*

			row0Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("30",row0Col0.getText());

			row0Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("SALES12",row0Col1.getText());

			row0Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("1CHICAGO",row0Col2.getText());

			*/

		}


	   @Test(groups = { "cma"}, dependsOnMethods = { "deleteModelFromColl"})
	   public void unSetandSetFromColl() throws Exception {

			//Start the test and bring up the browser
			startupTest("CollectionFunctionsVirtual.html",null);

			waitForElementVisible("id=deptTable");

			//Need to wait for page to be loaded
			waitForMilliseconds(5000L);

			WebElement unSetandSet = getElement("id=unSetandSet");
			unSetandSet.click();

			waitForMilliseconds(3000L);

			log("text"+ getElement("id=resultsFunction").getText());

			waitForText(RESULTS_CONTAINER, "Loc has changed originally: false");
			waitForText(RESULTS_CONTAINER, "Loc has changed initially: true");
			waitForText(RESULTS_CONTAINER, "Loc has changed afterwards: true");
		}


		@Test(groups = { "cma"}, dependsOnMethods = { "unSetandSetFromColl"})
	   public void testFewModelFunctions() throws Exception {

			//Start the test and bring up the browser
			startupTest("CollectionFunctionsVirtual.html",null);

			waitForElementVisible("id=deptTable");

			//Need to wait for page to be loaded
			waitForMilliseconds(5000L);

			WebElement getModelAt = getElement("id=getModelAt");
			getModelAt.click();

			waitForMilliseconds(2000L);

			waitForText(RESULTS_CONTAINER, "{\"Deptno\":12,\"Dname\":\"name4534\",\"Loc\":\"10\"");

			WebElement index = getElement("id=index");
			index.clear();
			index.sendKeys("2");

			getModelAt.click();
			waitForText(RESULTS_CONTAINER, "{\"Deptno\":20,\"Dname\":\"RESEARCH\",\"Loc\":\"DALLAS\"");

			WebElement eachFunction = getElement("id=eachFunction");
			eachFunction.click();
			waitForMilliseconds(2000L);

			waitForText(RESULTS_CONTAINER, "Message: each not valid on a virtual Collection");

			WebElement filterFunction = getElement("id=filterFunction");
			filterFunction.click();
			waitForMilliseconds(2000L);

			waitForText(RESULTS_CONTAINER, "Message: filter not valid on a virtual Collection");

			WebElement getFirst = getElement("id=getFirst");
			getFirst.click();
			waitForMilliseconds(2000L);
			waitForText(RESULTS_CONTAINER, "{\"Deptno\":12,\"Dname\":\"name4534\"");


			WebElement getFirst5Model = getElement("id=getFirst5Model");
			getFirst5Model.click();
			waitForMilliseconds(2000L);
			waitForText(RESULTS_CONTAINER, ",12,30,20,40,50");

			WebElement getLast = getElement("id=getLast");
			getLast.click();
			waitForMilliseconds(2000L);
			waitForText(RESULTS_CONTAINER, "{\"Deptno\":10,\"Dname\":\"ACCOUNTING\"");


			WebElement getLast5Model = getElement("id=getLast5Model");
			getLast5Model.click();
			waitForMilliseconds(2000L);
			waitForText(RESULTS_CONTAINER, ",80,60,90,70,10");

			WebElement getMax = getElement("id=getMax");
			getMax.click();
			waitForText(RESULTS_CONTAINER, "Message: max not valid on a virtual Collection");

			WebElement getMin = getElement("id=getMin");
			getMin.click();
			waitForText(RESULTS_CONTAINER, "Message: min not valid on a virtual Collection");

			WebElement pluckAllDeptByName = getElement("id=pluckAllDeptByName");
			pluckAllDeptByName.click();
			waitForText(RESULTS_CONTAINER,"Message: pluck not valid on a virtual Collection");



		}

		@Test(groups = { "cma"}, dependsOnMethods = { "testFewModelFunctions"})
	   public void testPushPopShiftSlice() throws Exception {

			//Start the test and bring up the browser
			startupTest("CollectionFunctionsVirtual.html",null);

			waitForElementVisible("id=deptTable");

			//Need to wait for page to be loaded
			waitForMilliseconds(5000L);

			//File a bug here for table columns not looking correct - 21164035
			WebElement pushNewDept = getElement("id=pushNewDept");
			pushNewDept.click();
			waitForMilliseconds(2000L);
			waitForText(RESULTS_CONTAINER, "Department Pushed at index 11: {\"Deptno\":\"15\",\"Dname\":\"DEFAULT PLUCK DEPT\",\"Loc\":\"DEFAULT LOC\"");

			WebElement popNewDept = getElement("id=popNewDept");
			log("popNewDept" + popNewDept);
			popNewDept.click();
			waitForMilliseconds(2000L);
			waitForText(RESULTS_CONTAINER, "Department popped: {\"Deptno\":\"15\",\"Dname\":\"DEFAULT PLUCK DEPT\"");

			WebElement shiftDept = getElement("id=shiftDept");
			shiftDept.click();
			waitForMilliseconds(2000L);
			waitForText(RESULTS_CONTAINER, "Department Shifted:");
			waitForText(RESULTS_CONTAINER, "{\"Deptno\":12,\"Dname\":\"name4534\",\"Loc\":\"10\"");

			WebElement unShiftDept = getElement("id=unShiftDept");
			unShiftDept.click();
			waitForMilliseconds(2000L);
			waitForText(RESULTS_CONTAINER, "Department UnShifted:");
			waitForText(RESULTS_CONTAINER, "{\"Deptno\":30,\"Dname\":\"SALES12\",\"Loc\":\"1CHICAGO\"");

			//bug - slice Dept shows 50 twice -- 21164090

			WebElement sliceDept = getElement("id=sliceDept");
			sliceDept.click();
			waitForMilliseconds(2000L);
			log("text: "+ getElement("id=resultsFunction").getText());
		//	waitForText(RESULTS_CONTAINER, "Department Sliced: 40,50,80,60,90,70,");

			WebElement withoutDept30 = getElement("id=withoutDept30");
			withoutDept30.click();
			waitForMilliseconds(2000L);
			waitForText(RESULTS_CONTAINER, "Message: without not valid on a virtual Collection");
		}



		@Test(groups = { "cma"}, dependsOnMethods = { "testPushPopShiftSlice"})
	   public void changeSortDirection() throws Exception {

			//Start the test and bring up the browser
			startupTest("CollectionFunctionsVirtual.html",null);

			waitForElementVisible("id=deptTable");

			//Need to wait for page to be loaded
			waitForMilliseconds(5000L);

			WebElement row0Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("12",row0Col0.getText());

			WebElement row0Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("name4534",row0Col1.getText());

			WebElement row0Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("10",row0Col2.getText());

			WebElement row1Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("30",row1Col0.getText());

			WebElement row1Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("SALES12",row1Col1.getText());

			WebElement row1Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("1CHICAGO",row1Col2.getText());

			WebElement changeSortDirection = getElement("id=changeSortDirection");
			changeSortDirection.click();
			waitForMilliseconds(2000L);

			row0Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("10",row0Col0.getText());

			row0Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("ACCOUNTING",row0Col1.getText());

			row0Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("PORTLAND",row0Col2.getText());

			row1Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("70",row1Col0.getText());

			row1Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("DESIGN",row1Col1.getText());

			row1Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("St LOUIS",row1Col2.getText());




		}

		@Test(groups = { "cma"}, dependsOnMethods = { "changeSortDirection"})
		  public void changeSortComparator() throws Exception {

				//Start the test and bring up the browser
				startupTest("CollectionFunctionsVirtual.html",null);

				waitForElementVisible("id=deptTable");

				//Need to wait for page to be loaded
				waitForMilliseconds(5000L);

				WebElement row0Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("12",row0Col0.getText());

				WebElement row0Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
				Assert.assertEquals("name4534",row0Col1.getText());

				WebElement row0Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
				Assert.assertEquals("10",row0Col2.getText());

				//Change Sort Comparator to Deptno
				getElement("id=oj-combobox-input-comboboxSortComp").clear();
				getElement("id=oj-combobox-input-comboboxSortComp").sendKeys("Deptno");
				getElement("id=oj-combobox-input-comboboxSortComp").click();

				getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}").click();
				waitForMilliseconds(2000L);

				row0Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("90",row0Col0.getText());

				row0Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
				Assert.assertEquals("Dept90",row0Col1.getText());

				row0Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
				Assert.assertEquals("LOC90",row0Col2.getText());

				//Change Sort Comparator to Dname
				getElement("id=oj-combobox-input-comboboxSortComp").clear();
				getElement("id=oj-combobox-input-comboboxSortComp").sendKeys("Dname");
				getElement("id=oj-combobox-input-comboboxSortComp").click();

				getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}").click();
				waitForMilliseconds(2000L);

				row0Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("12",row0Col0.getText());

				row0Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
				Assert.assertEquals("name4534",row0Col1.getText());

				row0Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
				Assert.assertEquals("10",row0Col2.getText());

				//Change Sort Comparator to Loc
				getElement("id=oj-combobox-input-comboboxSortComp").clear();
				getElement("id=oj-combobox-input-comboboxSortComp").sendKeys("Loc");
				getElement("id=oj-combobox-input-comboboxSortComp").click();

				getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}").click();
				waitForMilliseconds(2000L);

				row0Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("70",row0Col0.getText());

				row0Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
				Assert.assertEquals("DESIGN",row0Col1.getText());

				row0Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
				Assert.assertEquals("St LOUIS",row0Col2.getText());


		}


		@Test(groups = { "cma"}, dependsOnMethods = { "changeSortComparator"})
		  public void testSetRangeLocal() throws Exception {

				//Start the test and bring up the browser
				startupTest("CollectionFunctionsVirtual.html",null);

				waitForElementVisible("id=deptTable");

				//Need to wait for page to be loaded
				waitForMilliseconds(5000L);

				WebElement testSetRangeLocal = getElement("id=testSetRangeLocal");
				testSetRangeLocal.click();
				waitForMilliseconds(2000L);
				log("text: "+ getElement("id=resultsFunction").getText());
				waitForText(RESULTS_CONTAINER, "Did not find model 7 and deleting it");


		}

	   @Test(groups = { "cma"}, dependsOnMethods = { "testSetRangeLocal"})
	   public void testSortByIndex() throws Exception {

			//Start the test and bring up the browser
			startupTest("CollectionFunctionsVirtual.html",null);

			waitForElementVisible("id=deptTable");

			//Need to wait for page to be loaded
			waitForMilliseconds(5000L);

			WebElement sortWithIndex = getElement("id=sortWithIndex");
			sortWithIndex.click();
			waitForMilliseconds(2000L);
			String strResult = getElement("id=resultsFunction").getText();
			log("text: "+ strResult);
			Assert.assertTrue(strResult.contains("Model Length: 3"));
			Assert.assertTrue(strResult.contains("LastFetchCount: 3"));

		}


		@Test(groups = { "cma"}, dependsOnMethods = { "testSortByIndex"})
		public void testSyncMethodOptions() throws Exception {

				//Start the test and bring up the browser
				startupTest("CollectionFunctionsVirtual.html",null);

				getWebDriver().manage().window().maximize();

				waitForElementVisible("id=deptTable");

				//Need to wait for page to be loaded
				waitForMilliseconds(4000L);

				getElement("testSyncMethodOptions").click();

				waitForMilliseconds(3000L);

				String strResult = getElement("id=resultsFunction").getText();
				log("text: "+ strResult);
				Assert.assertTrue(strResult.contains("FetchSize:5"));
				Assert.assertTrue(strResult.contains("Sort:Dname"));
				Assert.assertTrue(strResult.contains("SortDir:desc"));


		}



		@Test(groups = { "cma"}, dependsOnMethods = { "testSyncMethodOptions"})
		public void testHasMoreWithNoTotalResults() throws Exception {

				//Start the test and bring up the browser
				startupTest("CollectionFunctionsVirtual.html",null);

				getWebDriver().manage().window().maximize();

				waitForElementVisible("id=deptTable");

				//Need to wait for page to be loaded
				waitForMilliseconds(4000L);

				getElement("hasMoreWithNoTotalResults").click();

				waitForMilliseconds(3000L);

				String strResult = getElement("id=resultsFunction").getText();
				log("text: "+ strResult);
				Assert.assertTrue(strResult.contains("HasMore: false, TotalResults: 10"));



		}



	@Test(groups = { "cma"}, dependsOnMethods = { "testHasMoreWithNoTotalResults"})
		public void setNewDataInCollection() throws Exception {

				//Start the test and bring up the browser
				startupTest("CollectionFunctionsVirtual.html",null);

				waitForElementVisible("id=deptTable");

				//Need to wait for page to be loaded
				waitForMilliseconds(4000L);

				WebElement loadMore = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-load-more-link\"}");
				loadMore.click();

				waitForMilliseconds(3000L);

				getElement("id=setNewDataInCollection").click();

				waitForMilliseconds(3000L);

				WebElement row0Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"10\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("11",row0Col0.getText());

				WebElement row0Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"10\",\"columnIndex\":\"3\"}");
				Assert.assertEquals("name",row0Col1.getText());

				WebElement row0Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"10\",\"columnIndex\":\"4\"}");
				Assert.assertEquals("location",row0Col2.getText());




		}





		/*
		This does not work because of the combox selection.

		@Test(groups = { "cma"})
		  public void testSortBy() throws Exception {

				//Start the test and bring up the browser
				startupTest("CollectionFunctionsVirtual.html",null);

				waitForElementVisible("id=deptTable");

				//Need to wait for page to be loaded
				try{
					Thread.sleep(3000);
				}catch(Exception e){}

				WebElement row0Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("12",row0Col0.getText());

				WebElement row0Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
				Assert.assertEquals("name4534",row0Col1.getText());

				WebElement row0Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
				Assert.assertEquals("10",row0Col2.getText());

				WebElement row1Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("30",row1Col0.getText());

				WebElement row1Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
				Assert.assertEquals("SALES12",row1Col1.getText());

				WebElement row1Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
				Assert.assertEquals("1CHICAGO",row1Col2.getText());


				//WebElement ele = getElement("{\"element\":\"#comboboxSortBy\",\"subId\":\"oj-combobox-results\"}");
				//log("ele[0]"+ele);


				//Change Sort Comparator to Deptno
				WebElement input2 = getElement("id=oj-combobox-input-2");
				input2.clear();
				input2.sendKeys("Deptno");

				try{
					Thread.sleep(1000);
				}catch(Exception e){}

				WebElement e = getElement("{\"element\":\"#comboboxSortBy\",\"subId\":\"oj-combobox-results\"}");
				log("e"+e);
				//log("1:"+ getElement("{\"element\":\"#oj-combobox-input-2\",\"subId\":\"oj-combobox-results\",\"index\":\"1\"}").getText());
				//log("2:"+ getElement("{\"element\":\"#oj-combobox-input-2\",\"subId\":\"oj-combobox-results\",\"index\":\"2\"}").getText());




				waitForText(RESULTS_CONTAINER, "90,80,70,60,50,40,30,20,12,10,");


				//Change Sort Comparator to Dname
				input2 = getElement("id=oj-combobox-input-2");
				input2.clear();
				input2.sendKeys("Dname");


				waitForText(RESULTS_CONTAINER, "12,30,20,40,50,80,60,90,70,10,");

				//Change Sort Comparator to Loc
				input2 = getElement("id=oj-combobox-input-2");
				input2.clear();
				input2.sendKeys("Loc");


				waitForText(RESULTS_CONTAINER, "70,50,80,10,90,20,60,40,30,12,");

		}

		*/


 @Test(groups = { "cma"}, dependsOnMethods = { "testHasMoreWithNoTotalResults"})
		public void testrestCollFuncHeader() throws Exception {

			//Start the test and bring up the browser
			startupTest("CollectionFunctionsVirtual.html",null);

			waitForElementVisible("id=deptTable");

			//Need to wait for page to be loaded
			waitForMilliseconds(5000L);

			WebElement dgHeader0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-header\",\"index\":\"0\"}");
			Assert.assertEquals("Delete",dgHeader0.getText());

			WebElement dgHeader1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-header\",\"index\":\"1\"}");
			Assert.assertEquals("Edit",dgHeader1.getText());

			WebElement dgHeader2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-header\",\"index\":\"2\"}");
			Assert.assertEquals("Deptno",dgHeader2.getText());

			WebElement dgHeader3 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-header\",\"index\":\"3\"}");
			Assert.assertEquals("Dname",dgHeader3.getText());

			WebElement dgHeader4 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-header\",\"index\":\"4\"}");
			Assert.assertEquals("Loc",dgHeader4.getText());

			WebElement dgHeader5 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-header\",\"index\":\"5\"}");
			Assert.assertEquals("ShowEmployees",dgHeader5.getText());




	    }

	   @Test(groups = { "cma"}, dependsOnMethods = { "testrestCollFuncHeader"})
	   public void testRestCollFuncValues() throws Exception {

			//Start the test and bring up the browser
			startupTest("CollectionFunctionsVirtual.html",null);

			waitForElementVisible("id=deptTable");

			//Need to wait for page to be loaded
			waitForMilliseconds(5000L);

			WebElement row0Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("12",row0Col0.getText());

			WebElement row0Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("name4534",row0Col1.getText());

			WebElement row0Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("10",row0Col2.getText());

			WebElement row1Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
			Assert.assertEquals("30",row1Col0.getText());

			WebElement row1Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
			Assert.assertEquals("SALES12",row1Col1.getText());

			WebElement row1Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
			Assert.assertEquals("1CHICAGO",row1Col2.getText());

	    }

	    @Test(groups = { "cma"}, dependsOnMethods = { "testRestCollFuncValues"})
			   public void testShowMore() throws Exception {

					//Start the test and bring up the browser
					startupTest("CollectionFunctionsVirtual.html",null);

					waitForElementVisible("id=deptTable");

					//Need to wait for page to be loaded
					waitForMilliseconds(5000L);

					WebElement row0Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("12",row0Col0.getText());

					WebElement row0Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("name4534",row0Col1.getText());

					WebElement row0Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("10",row0Col2.getText());

					WebElement row1Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("30",row1Col0.getText());

					WebElement row1Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("SALES12",row1Col1.getText());

					WebElement row1Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("1CHICAGO",row1Col2.getText());


					WebElement loadMoreRange = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-load-more-range\"}");
					log("loadMoreRange"+loadMoreRange.getText());
					Assert.assertEquals("1-5 of 10 items",loadMoreRange.getText());
					WebElement loadMoreRangeCurrent = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-load-more-range-current\"}");
					log("loadMoreRangeCurrent"+loadMoreRangeCurrent.getText());
					Assert.assertEquals("1-5",loadMoreRangeCurrent.getText());

					WebElement loadMoreRangeMax = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-load-more-range-max\"}");
					log("loadMoreRangeMax"+loadMoreRangeMax.getText());
					Assert.assertEquals("10",loadMoreRangeMax.getText());

					// Fails with the subid: 21163979
				//	WebElement loadMoreMaxRows = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-load-more-max-rows\"}");
				//	log("loadMoreMaxRows"+loadMoreMaxRows.getText());


					WebElement loadMore = getElement("{\"element\":\"#paging\",\"subId\":\"oj-pagingcontrol-load-more-link\"}");
					loadMore.click();

					waitForMilliseconds(3000L);


					WebElement row6Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"6\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("60",row6Col0.getText());

					WebElement row6Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"6\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("EDUCATION",row6Col1.getText());

					WebElement row6Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"6\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("COLORADO",row6Col2.getText());

					WebElement row9Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"9\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("10",row9Col0.getText());

					WebElement row9Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"9\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("ACCOUNTING",row9Col1.getText());

					WebElement row9Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"9\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("PORTLAND",row9Col2.getText());

	    }



		@Test(groups = { "cma"}, dependsOnMethods = { "testShowMore"})
		public void crudDepartment() throws Exception {

				//Start the test and bring up the browser
				startupTest("CollectionFunctionsVirtual.html",null);

				getWebDriver().manage().window().maximize();

				waitForElementVisible("id=deptTable");

				//Need to wait for page to be loaded
				waitForMilliseconds(5000L);

				WebElement row0Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("12",row0Col0.getText());

				WebElement row0Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
				Assert.assertEquals("name4534",row0Col1.getText());

				WebElement row0Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
				Assert.assertEquals("10",row0Col2.getText());

				WebElement row1Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("30",row1Col0.getText());

				WebElement row1Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
				Assert.assertEquals("SALES12",row1Col1.getText());

				WebElement row1Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
				Assert.assertEquals("1CHICAGO",row1Col2.getText());

				getElement("id=newDepartId").clear();
				getElement("id=newDepartId").sendKeys("9");
				getElement("id=newDepartName").sendKeys("abc123");
				getElement("id=newLoc").sendKeys("abcLoc");

				getElement("id=saveBtnDept").click();
				waitForMilliseconds(2000L);

				WebElement deleteIcon = null;

				try{
					row1Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("9",row1Col0.getText());

					row1Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("abc123",row1Col1.getText());

					row1Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("abcLoc",row1Col2.getText());
				}catch(Exception e){
							deleteIcon = getElement("id=delete9");
							deleteIcon.click();
				}

				try{

					//Edit Added Dept
					WebElement editIcon = getElement("id=edit9");
					editIcon.click();

					getElement("id=dname1").sendKeys("Updated");
					getElement("id=loc1").sendKeys("Updated");

					getElement("id=updateBtnDept").click();

					//Need to wait for page to be loaded

					waitForMilliseconds(5000L);


					row1Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("9",row1Col0.getText());

					row1Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("abc123Updated",row1Col1.getText());

					row1Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("abcLocUpdated",row1Col2.getText());
				}catch(Exception e){
						//WebElement deleteIcon1 = getElement("id=delete9");
						log("I am here in exception from edit crud virtual");
						//deleteIcon1.click();
				}



				try{
					deleteIcon = getElement("id=delete9");
					deleteIcon.click();
				}catch(Exception e){
						deleteIcon = getElement("id=delete9");
						deleteIcon.click();
				}

				//Need to wait for page to be loaded
				waitForMilliseconds(5000L);

				row1Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
				log("Value:"+ row1Col0.getText());
				Assert.assertEquals("30",row1Col0.getText());

				row1Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
				Assert.assertEquals("SALES12",row1Col1.getText());

				row1Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
				Assert.assertEquals("1CHICAGO",row1Col2.getText());



		}


		@Test(groups = { "cma"})
		public void crudEmployee() throws Exception {

				//Start the test and bring up the browser
				startupTest("CollectionFunctionsVirtual.html",null);

				waitForElementVisible("id=deptTable");

				//Need to wait for page to be loaded
				waitForMilliseconds(5000L);

				WebElement row0Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("12",row0Col0.getText());

				WebElement row0Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
				Assert.assertEquals("name4534",row0Col1.getText());

				WebElement row0Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
				Assert.assertEquals("10",row0Col2.getText());

				WebElement row1Col0 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("30",row1Col0.getText());

				WebElement row1Col1 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"3\"}");
				Assert.assertEquals("SALES12",row1Col1.getText());

				WebElement row1Col2 = getElement("{\"element\":\"#deptTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"4\"}");
				Assert.assertEquals("1CHICAGO",row1Col2.getText());

				WebElement showEmployee = getElement("id=ShowEmp12");
				showEmployee.click();

				//Need to wait for Employee table to be loaded
				waitForMilliseconds(5000L);

				WebElement emprow0Col0 = getElement("{\"element\":\"#empTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("7",emprow0Col0.getText());

				WebElement emprow0Col1 = getElement("{\"element\":\"#empTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"3\"}");
				Assert.assertEquals("james R",emprow0Col1.getText());

				WebElement emprow0Col2 = getElement("{\"element\":\"#empTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"4\"}");
				Assert.assertEquals("CLERK",emprow0Col2.getText());

				getElement("id=newEmpId").clear();
				getElement("id=newEmpId").sendKeys("9");
				getElement("id=newEmpName").sendKeys("abcName");
				getElement("id=newJob").clear();
				getElement("id=newJob").sendKeys("SALE");

				getElement("id=saveBtnEmp").click();

				waitForMilliseconds(5000L);


				WebElement emprow3Col0 = getElement("{\"element\":\"#empTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"2\"}");
				Assert.assertEquals("9",emprow3Col0.getText());

				WebElement emprow3Col1 = getElement("{\"element\":\"#empTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"3\"}");
				Assert.assertEquals("abcName",emprow3Col1.getText());

				WebElement emprow3Col2 = getElement("{\"element\":\"#empTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"4\"}");
				Assert.assertEquals("SALE",emprow3Col2.getText());


				try{

					//Edit Added Dept
					WebElement editIcon = getElement("id=editEmp9");
					editIcon.click();

					getElement("id=ename").sendKeys("1");
					getElement("id=job").sendKeys("S");

					getElement("id=updateBtnEmp").click();

					waitForMilliseconds(5000L);

					emprow3Col0 = getElement("{\"element\":\"#empTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"2\"}");
					Assert.assertEquals("9",emprow3Col0.getText());

					emprow3Col1 = getElement("{\"element\":\"#empTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"3\"}");
					Assert.assertEquals("abcName1",emprow3Col1.getText());

					emprow3Col2 = getElement("{\"element\":\"#empTable\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"2\",\"columnIndex\":\"4\"}");
					Assert.assertEquals("SALES",emprow3Col2.getText());
				}catch(Exception e){
					log("Edit Emp Failed" + e.getMessage());
				}



				WebElement deleteIcon = getElement("id=deleteEmp9");
				deleteIcon.click();

				waitForMilliseconds(5000L);

				//showEmployee = getElement("id=ShowEmp12");
				//showEmployee.click();

				//waitForMilliseconds(5000L);

				boolean isDisplayed = true;

				try{
					isDisplayed = isElementPresent(By.id("id=deleteEmp9"));

				}catch(Exception e){
					log("in catch");


				}

				Assert.assertFalse(isDisplayed);



		}











    private void log(String log)
	{
		System.out.println(log);

		getLogger().fine("[CollectionFunctionVirtualTest] " + log);
	}


}

