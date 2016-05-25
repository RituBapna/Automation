package oj.tests.masonaryLayout;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;


public class MasonaryLayoutTest extends OJetBase {

    private static final String TITLE = "Basic Masonary Layout";

    public MasonaryLayoutTest() {
        super(new TestConfigBuilder().setContextRoot("layout").setAppRoot("masonaryLayout").build());
    }

    @Test(groups = { "masonaryLayout" })
    public void testMasonaryTiles() throws Exception {
        //Start the test and bring up the browser
        startupTest("ojMasonaryLayoutBasic.html",null);

        //Verify the url
		String url = getBrowserUrl();
		log("URL##########"+ url);

		// Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE);

        // Make sure data is displayed
        waitForElementVisible("id=tile1");

        WebElement tile1 = getElement("id=tile1");
        String tile1Text = tile1.getText();

        Assert.assertTrue(tile1Text.contains("Hydrogen"));
        Assert.assertTrue(tile1Text.contains("Collapsed content"));

       //log("innerHTML :"+ tile1.getAttribute("innerHTML"));
       // log("outerHTML :"+ tile1.getAttribute("outerHTML"));

        WebElement label1 = getElement("id=label1");
        Assert.assertEquals("Hydrogen", label1.getText());


        WebElement resizeButton1 = getElement("id=resizeButton1");
        resizeButton1.click();

        waitForMilliseconds(2000L);


		tile1 = getElement("id=tile1");
        tile1Text = tile1.getText();
		Assert.assertTrue(tile1Text.contains("Expanded content"));

        WebElement table = getElement("id=table");

        WebElement dgHeader0 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"0\"}");
		Assert.assertEquals("Department Id",dgHeader0.getText());

		WebElement dgHeader1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"1\"}");
		Assert.assertEquals("Department Name",dgHeader1.getText());

		WebElement row0Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"0\"}");
		Assert.assertEquals("1001",row0Col1.getText());

		WebElement row0Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"0\",\"columnIndex\":\"1\"}");
		Assert.assertEquals("ADFPM 1001 neverending",row0Col2.getText());

		WebElement row1Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"1\",\"columnIndex\":\"0\"}");
		Assert.assertEquals("556",row1Col1.getText());

		WebElement row7Col1 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"0\"}");
		Assert.assertEquals("60",row7Col1.getText());

		WebElement row7Col2 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"7\",\"columnIndex\":\"1\"}");
		Assert.assertEquals("Marketing3",row7Col2.getText());

		resizeButton1 = getElement("id=resizeButton1");
        resizeButton1.click();

        waitForMilliseconds(2000L);

        //Make sure tile is displayed before remove
        tile1 = getElement("id=tile1");
        Assert.assertTrue(tile1.isDisplayed());

        //Remove the tile
        WebElement removeButton1 = getElement("id=remove1");
		removeButton1.click();

		waitForMilliseconds(2000L);

		//Make sure tile is NOT displayed after remove
		tile1 = getElement("id=tile1");
		Assert.assertFalse(tile1.isDisplayed());

    }


 	@Test(groups = { "masonaryLayout" })
    public void testMasonaryTileReorder() throws Exception {
        //Start the test and bring up the browser
        startupTest("ojMasonaryLayoutBasic.html",null);

        //Verify the url
		String url = getBrowserUrl();
		log("URL##########"+ url);

		// Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE);

        // Make sure data is displayed
        waitForElementVisible("id=tile1");

        WebElement tile1 = getElement("id=tile1");
        String tile1Text = tile1.getText();

        Assert.assertTrue(tile1Text.contains("Hydrogen"));
        Assert.assertTrue(tile1Text.contains("Collapsed content"));

		rightClickAndSelectMenuOption("id=dragHandle1", "id=ui-id-1");

		rightClickAndSelectMenuOption("id=dragHandle2", "id=ui-id-3");

		waitForMilliseconds(3000L);

		tile1 = getElement("id=tile1");
		tile1Text = tile1.getText();

		log("tile1Text"+tile1Text);

        String index = getElement("id=index").getText();
		log("index"+index);
		Assert.assertTrue(index.contains("4"));

		rightClickAndSelectMenuOption("id=dragHandle3", "id=ui-id-1");

	    rightClickAndSelectMenuOption("id=dragHandle2", "id=ui-id-2");

	    waitForMilliseconds(3000L);
	    index = getElement("id=index").getText();
		log("index"+index);
		Assert.assertTrue(index.contains("3"));

    }

  /*

  @Test(groups = { "masonaryLayout" })
	    public void testMasonaryTileDragAndDrop() throws Exception {
	        //Start the test and bring up the browser
	        startupTest("ojMasonaryLayoutBasic.html",null);

	        //Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
	        verifyTitle("Incorrect page title;", TITLE);

	        // Make sure data is displayed
	        waitForElementVisible("id=tile1");

	        WebElement tile1 = getElement("id=tile1");
	        String tile1Text = tile1.getText();

	        Assert.assertTrue(tile1Text.contains("Hydrogen"));

	        WebElement source = getElement("id=dragHandle1");
		    WebElement target = getElement("id=dragHandle2");

		 //   Actions action = new Actions(getWebDriver());
    	 //	Action dragDrop = action.dragAndDrop(source, target).build().perform();

    		Actions dragAndDrop = new Actions(getWebDriver());
		    dragAndDrop.clickAndHold(source);
		    dragAndDrop.moveToElement(target);
		    dragAndDrop.release(target);
		    dragAndDrop.build().perform();


            waitForMilliseconds(3000L);
		    String index = getElement("id=index").getText();
			log("index"+index);
			Assert.assertTrue(index.contains("4"));
	}


*/

	@Test(groups = { "masonaryLayout" })
    public void testMasonaryTileInsertDelete() throws Exception {
        //Start the test and bring up the browser
        startupTest("ojMasonaryLayoutBasic.html",null);

        //Verify the url
		String url = getBrowserUrl();
		log("URL##########"+ url);

		// Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE);

        // Make sure data is displayed
        waitForElementVisible("id=tile1");

        WebElement tile1 = getElement("id=tile1");
        String tile1Text = tile1.getText();

        Assert.assertTrue(tile1Text.contains("Hydrogen"));
        Assert.assertTrue(tile1Text.contains("Collapsed content"));

        //Remove the first tile
        getElement("id=remove1").click();

		waitForMilliseconds(3000L);

		Assert.assertFalse(getElement("id=tile1").isDisplayed());

		WebElement anchorHydro = getElement("id=Hydrogen");
		anchorHydro.click();

		waitForMilliseconds(3000L);

		Assert.assertTrue(getElement("id=tile1").isDisplayed());

		getElement("id=remove2").click();

		waitForMilliseconds(3000L);

		Assert.assertFalse(getElement("id=tile2").isDisplayed());

		WebElement anchorHelium = getElement("id=Helium");
		anchorHelium.click();

		waitForMilliseconds(3000L);

		Assert.assertTrue(getElement("id=tile2").isDisplayed());



    }

    @Test(groups = { "masonaryLayout" })
	    public void testMasonaryTileDestroy() throws Exception {
	        //Start the test and bring up the browser
	        startupTest("ojMasonaryLayoutBasic.html",null);

	        //Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
	        verifyTitle("Incorrect page title;", TITLE);

	        // Make sure data is displayed
	        waitForElementVisible("id=tile1");

	        WebElement tile1 = getElement("id=tile1");
	        String tile1Text = tile1.getText();

	        Assert.assertTrue(tile1Text.contains("Hydrogen"));

	        getElement("id=destroy").click();

	        waitForMilliseconds(1000L);

	        Assert.assertFalse(getElement("id=tile1").isDisplayed());
	        Assert.assertFalse(getElement("id=tile2").isDisplayed());


  	}




		private void log(String log)
		{
			System.out.println(log);
			getLogger().fine("[MasonaryLayoutTest] " + log);
		}


}

