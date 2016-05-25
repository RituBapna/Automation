package oj.tests.masonaryLayout;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class MasonaryLayoutCookBookTest extends OJetBase {

    private static final String TITLE = "Masonry Layout - Flip Tiles";

    public MasonaryLayoutCookBookTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "masonaryLayout" })
    public void testMasonaryFlipTiles() throws Exception {
        //Start the test and bring up the browser
        startupTest("demo-masonryLayout-flipTilesMasonryLayout.html",null);

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

       //log("innerHTML :"+ tile1.getAttribute("innerHTML"));
       // log("outerHTML :"+ tile1.getAttribute("outerHTML"));

        WebElement flipButton1 = getElement("id=flipButton1");
        flipButton1.click();

        waitForMilliseconds(2000L);


		tile1 = getElement("id=tile1");
        tile1Text = tile1.getText();
        Assert.assertTrue(tile1Text.contains("H"));

        flipButton1 = getElement("id=flipButton1");
		flipButton1.click();

		waitForMilliseconds(2000L);


		tile1 = getElement("id=tile1");
		tile1Text = tile1.getText();
        Assert.assertTrue(tile1Text.contains("Hydrogen"));


        WebElement tile3 = getElement("id=tile3");
		String tile3Text = tile3.getText();
		Assert.assertTrue(tile3Text.contains("Lithium"));

	    WebElement flipButton3 = getElement("id=flipButton3");
		flipButton3.click();

		waitForMilliseconds(2000L);


		tile3 = getElement("id=tile3");
		tile3Text = tile3.getText();
		Assert.assertTrue(tile3Text.contains("Li"));

		flipButton3 = getElement("id=flipButton3");
		flipButton3.click();

		waitForMilliseconds(2000L);


		tile3 = getElement("id=tile3");
		tile3Text = tile3.getText();
		Assert.assertTrue(tile3Text.contains("Lithium"));


    }

	@Test(groups = { "masonaryLayout" })
	public void testMasonaryFilmStripTiles() throws Exception {
		//Start the test and bring up the browser
		startupTest("demo-masonryLayout-filmStripMasonryLayout.html",null);

		//Verify the url
		String url = getBrowserUrl();
		log("URL##########"+ url);

		// Verify if the title of the page is correct
		verifyTitle("Incorrect page title;", "Masonry Layout - FilmStrip in Tile");

		// Make sure data is displayed
		waitForElementVisible("id=tile1");

		WebElement tile1 = getElement("id=tile1");
		String tile1Text = tile1.getText();
		Assert.assertTrue(tile1Text.contains("Hydrogen"));
		Assert.assertTrue(tile1Text.contains("H2O"));
		Assert.assertTrue(tile1Text.contains("Glucose"));
		Assert.assertTrue(tile1Text.contains("Hydrogen chloride"));


		waitForElementVisible("{\"element\":\"#pagingControl\",\"subId\":\"oj-pagingcontrol-nav-page\",\"index\":\"1\"}");
		WebElement page2 = getElement("{\"element\":\"#pagingControl\",\"subId\":\"oj-pagingcontrol-nav-page\",\"index\":\"1\"}");
		page2.click();

		waitForMilliseconds(3000L);

		tile1 = getElement("id=tile1");
		tile1Text = tile1.getText();

		Assert.assertTrue(tile1Text.contains("Hydrogen sulphide"));
		Assert.assertTrue(tile1Text.contains("Ammonia"));
		Assert.assertTrue(tile1Text.contains("Hydrogen fluoride"));

		waitForElementVisible("{\"element\":\"#pagingControl\",\"subId\":\"oj-pagingcontrol-nav-page\",\"index\":\"0\"}");
		WebElement page1 = getElement("{\"element\":\"#pagingControl\",\"subId\":\"oj-pagingcontrol-nav-page\",\"index\":\"0\"}");
		page1.click();

		waitForMilliseconds(3000L);


		tile1 = getElement("id=tile1");
		tile1Text = tile1.getText();
		log("tile1Text"+tile1Text);
		Assert.assertTrue(tile1Text.contains("Hydrogen"));
		Assert.assertTrue(tile1Text.contains("H2O"));
		Assert.assertTrue(tile1Text.contains("Glucose"));
		Assert.assertTrue(tile1Text.contains("Hydrogen chloride"));


	}

	@Test(groups = { "masonaryLayout" })
		public void testMasonaryZoomTiles() throws Exception {
			//Start the test and bring up the browser
			startupTest("demo-masonryLayout-zoomTilesMasonryLayout.html",null);

			//Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
			verifyTitle("Incorrect page title;", "Masonry Layout - Zoom Tiles");

			// Make sure data is displayed
			waitForElementVisible("id=tile1");

			WebElement tile1 = getElement("id=tile1");
			String tile1Text = tile1.getText();
			Assert.assertTrue(tile1Text.contains("Hydrogen"));

			getElement("zoomButton1").click();

			waitForMilliseconds(3000L);

			String dialogTitle = getElement("dialogTitleId").getText();
			log("dialogTitle"+dialogTitle);
			Assert.assertTrue(dialogTitle.contains("Hydrogen"));

			pressEscapeKey();

			tile1 = getElement("id=tile1");
			tile1Text = tile1.getText();
			Assert.assertTrue(tile1Text.contains("Hydrogen"));

	}




		private void log(String log)
		{
			System.out.println(log);
			getLogger().fine("[MasonaryLayoutCookBookTest] " + log);
		}


}

