package oj.tests.formlayout;

import java.util.NoSuchElementException;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;


import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import oj.tests.responsive.CookbookResponsiveTestUtils;

public class CookbookFormLayoutTest extends OJetBase {
    public CookbookFormLayoutTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "responsive", "formlayout" })
    public void testStacked() throws Exception {
        startupTest("demo-forms-formvertical.html", null);
        verifyTitle("Incorrect page title;", "Form Layout - Stacked");

        waitForElementVisible("id=componentDemo");


        //check the top offset of the first label
        int labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));

        //check the top offset of the first field
        int fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));

        // the field should be at least 10px under the label
        boolean fieldUnder = (fieldTop - labelTop) > 10;


        Assert.assertTrue(fieldUnder, "field not under label");


    }


    @Test(groups = { "cookbook", "responsive", "formlayout" })
    public void testInline() throws Exception {
        startupTest("demo-forms-form1colinline.html", null);
        verifyTitle("Incorrect page title;", "Form Layout - Inline");

        waitForElementVisible("id=componentDemo");


        //check the top offset of the first label
        int labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));
        log("labelTop:"+labelTop);

        //check the top offset of the first field
        int fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));
        log("fieldTop:"+fieldTop);


        // the field should be at least 10px under the label
        boolean fieldUnder = (fieldTop - labelTop) > 10;

        Assert.assertFalse(fieldUnder, "field is under label");

        WebDriver driver = getWebDriver();

        //-------------- Verify Label changes ------------

		driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_SM, CookbookResponsiveTestUtils.TEST_HEIGHT));

		//check the top offset of the first label
		labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));

		//check the top offset of the first field
		fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));


		// the field should be at least 10px under the label
		fieldUnder = (fieldTop - labelTop) > 10;

		Assert.assertTrue(fieldUnder, "field under label");

		//------------------------------------------
		// set the screen width to medium
		driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_MD, CookbookResponsiveTestUtils.TEST_HEIGHT));

		//check the top offset of the first label
		labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));

		//check the top offset of the first field
		fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));


		// the field should be at least 10px under the label
		fieldUnder = (fieldTop - labelTop) > 10;

		Assert.assertFalse(fieldUnder, "field not under label");

		//------------------------------------------
		// set the screen width to large
		driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, CookbookResponsiveTestUtils.TEST_HEIGHT));

		labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));
		log("labelTop:"+labelTop);

		//check the top offset of the first field
		fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));

		// the field should be at least 10px under the label
		fieldUnder = (fieldTop - labelTop) > 10;

		Assert.assertFalse(fieldUnder, "field not under the label");


    }

    @Test(groups = { "cookbook", "responsive", "formlayout" })
	    public void testColumn() throws Exception {
	        startupTest("demo-forms-form2colsdowncss.html", null);
	        verifyTitle("Incorrect page title;", "Form Layout - Columns");

	        waitForElementVisible("id=form-container");

			//------------------------------------------
			// set the screen width to small
			WebDriver driver = getWebDriver();
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_SM, CookbookResponsiveTestUtils.TEST_HEIGHT));

			//-------------- Verify Label changes ------------

			//check the top offset of the first label
			int labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));

			//check the top offset of the first field
			int fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));


			// the field should be at least 10px under the label
			boolean fieldUnder = (fieldTop - labelTop) > 10;

	        Assert.assertTrue(fieldUnder, "field not under label");

	        //----------------Verify column variation-------------

	        int firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().left)"));
			log("firstField:"+firstField);
			int lastField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(6).find('.oj-col').eq(1).offset().left)"));
			log("lastField:"+lastField);

			Assert.assertEquals(firstField, lastField);


			//------------------------------------------
			// set the screen width to medium
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_MD, CookbookResponsiveTestUtils.TEST_HEIGHT));

			//check the top offset of the first label
		  	labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));

			//check the top offset of the first field
			fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));


			// the field should be at least 10px under the label
			fieldUnder = (fieldTop - labelTop) > 10;

			Assert.assertFalse(fieldUnder, "field under label");



			//----------------Verify column variation-------------

	        firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().left)"));
			lastField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(6).find('.oj-col').eq(1).offset().left)"));

			boolean twoColumns = (lastField - firstField) > 100;

	        Assert.assertTrue(twoColumns, "did not break into two columns");

			//------------------------------------------
			// set the screen width to large
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, CookbookResponsiveTestUtils.TEST_HEIGHT));

			labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));
			log("labelTop:"+labelTop);

			//check the top offset of the first field
			fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));

			// the field should be at least 10px under the label
			fieldUnder = (fieldTop - labelTop) > 10;

			Assert.assertFalse(fieldUnder, "field not to the side of label");


			//----------------Verify column variation-------------

	        firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().left)"));
			lastField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(6).find('.oj-col').eq(1).offset().left)"));

			twoColumns = (lastField - firstField) > 100;
	        Assert.assertTrue(twoColumns, "did not break into two columns");


    }


    @Test(groups = { "cookbook", "responsive", "formlayout" })
	    public void testColumnVariation() throws Exception {
	        startupTest("demo-forms-form2colsdowncss2.html", null);
	        verifyTitle("Incorrect page title;", "Form Layout - Columns Variation");

	        waitForElementVisible("id=form-container");

			//------------------------------------------
			// set the screen width to small
			WebDriver driver = getWebDriver();
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_SM, CookbookResponsiveTestUtils.TEST_HEIGHT));

			//-------------- Verify Label changes ------------

			//check the top offset of the first label
			int labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));

			//check the top offset of the first field
			int fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));


			// the field should be at least 10px under the label
			boolean fieldUnder = (fieldTop - labelTop) > 10;

	        Assert.assertTrue(fieldUnder, "field not under label");

	        //----------------Verify column variation-------------

	        int firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().left)"));
			log("firstField:"+firstField);
			int lastField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(6).find('.oj-col').eq(1).offset().left)"));
			log("lastField:"+lastField);

			Assert.assertEquals(firstField, lastField);


			//------------------------------------------
			// set the screen width to medium
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_MD, CookbookResponsiveTestUtils.TEST_HEIGHT));

			//check the top offset of the first label
		  	labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));

			//check the top offset of the first field
			fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));


			// the field should be at least 10px under the label
			fieldUnder = (fieldTop - labelTop) > 10;

			Assert.assertTrue(fieldUnder, "field not under label");



			//----------------Verify column variation-------------

	        firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().left)"));
			lastField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(6).find('.oj-col').eq(1).offset().left)"));

			boolean twoColumns = (lastField - firstField) > 100;

	        Assert.assertTrue(twoColumns, "did not break into two columns");

			//------------------------------------------
			// set the screen width to large
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, CookbookResponsiveTestUtils.TEST_HEIGHT));

			labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));
			log("labelTop:"+labelTop);

			//check the top offset of the first field
			fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));

			// the field should be at least 10px under the label
			fieldUnder = (fieldTop - labelTop) > 10;

			Assert.assertFalse(fieldUnder, "field not to the side of label");


			//----------------Verify column variation-------------

	        firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().left)"));
			lastField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(6).find('.oj-col').eq(1).offset().left)"));

			twoColumns = (lastField - firstField) > 100;
	        Assert.assertTrue(twoColumns, "did not break into two columns");


    }

    @Test(groups = { "cookbook", "responsive", "formlayout" })
	    public void testColumnsGrid() throws Exception {
	        startupTest("demo-forms-form2colsdown.html", null);
	        verifyTitle("Incorrect page title;", "Form Layout - Columns Grid");

	        waitForElementVisible("id=form-container");

			//------------------------------------------
			// set the screen width to small
			WebDriver driver = getWebDriver();
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_SM, CookbookResponsiveTestUtils.TEST_HEIGHT));

			//-------------- Verify Label changes ------------

			//check the top offset of the first label
			int labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));

			//check the top offset of the first field
			int fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));

			log("labelTop"+labelTop);
		  	log("fieldTop"+fieldTop);



			// the field should be at least 10px under the label
			boolean fieldUnder = (fieldTop - labelTop) > 10;

	        Assert.assertTrue(fieldUnder, "field not under label");

	        //----------------Verify column variation-------------

	        int firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().left)"));
			log("firstField:"+firstField);
			int lastField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(5).find('.oj-col').eq(1).offset().left)"));
			log("lastField:"+lastField);

			Assert.assertEquals(firstField, lastField);


			//------------------------------------------
			// set the screen width to medium
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_MD, CookbookResponsiveTestUtils.TEST_HEIGHT));

			//check the top offset of the first label
		  	labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));


			//check the top offset of the first field
			fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));


		  	log("labelTop"+labelTop);
		  	log("fieldTop"+fieldTop);

			// the field should be at least 10px under the label
			fieldUnder = (fieldTop - labelTop) > 10;

			Assert.assertFalse(fieldUnder, "field not to the side of label");


			//----------------Verify column variation-------------

	        firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().left)"));
			lastField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(5).find('.oj-col').eq(1).offset().left)"));

			log("firstField"+firstField);
			log("lastField"+lastField);

			boolean twoColumns = (lastField - firstField) > 100;

	        Assert.assertEquals(firstField, lastField, "broke into two columns");

			//------------------------------------------
			// set the screen width to large
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, CookbookResponsiveTestUtils.TEST_HEIGHT));

			labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));

			//check the top offset of the first field
			fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));

			// the field should be at least 10px under the label
			fieldUnder = (fieldTop - labelTop) > 10;

			Assert.assertFalse(fieldUnder, "field not to the side of label");


			//----------------Verify column variation-------------

	        firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(0).offset().left)"));
			lastField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(5).find('.oj-col').eq(0).offset().left)"));

			log("firstField"+firstField);
			log("lastField"+lastField);

			twoColumns = (lastField - firstField) > 100;
	        Assert.assertTrue(twoColumns, "did not break into two columns");


    }

    @Test(groups = { "cookbook", "responsive", "formlayout" })
	    public void testColumnsGridVariation() throws Exception {
	        startupTest("demo-forms-form2colsdown2.html", null);
	        verifyTitle("Incorrect page title;", "Form Layout - Columns Grid Variation");

	        waitForElementVisible("id=form-container");

			//------------------------------------------
			// set the screen width to small
			WebDriver driver = getWebDriver();
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_SM, CookbookResponsiveTestUtils.TEST_HEIGHT));

			//-------------- Verify Label changes ------------

			//check the top offset of the first label
			int labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));

			//check the top offset of the first field
			int fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));

			log("labelTop"+labelTop);
		  	log("fieldTop"+fieldTop);



			// the field should be at least 10px under the label
			boolean fieldUnder = (fieldTop - labelTop) > 10;

	        Assert.assertTrue(fieldUnder, "field not under label");

	        //----------------Verify column variation-------------

	        int firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().left)"));
			log("firstField:"+firstField);
			int lastField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(5).find('.oj-col').eq(1).offset().left)"));
			log("lastField:"+lastField);

			Assert.assertEquals(firstField, lastField);


			//------------------------------------------
			// set the screen width to medium
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_MD, CookbookResponsiveTestUtils.TEST_HEIGHT));

			//check the top offset of the first label
		  	labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));


			//check the top offset of the first field
			fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));


		  	log("labelTop"+labelTop);
		  	log("fieldTop"+fieldTop);

			// the field should be at least 10px under the label
			fieldUnder = (fieldTop - labelTop) > 10;

			Assert.assertTrue(fieldUnder, "field not to the top of label");


			//----------------Verify column variation-------------

	        firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().left)"));
			lastField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(5).find('.oj-col').eq(1).offset().left)"));

			log("firstField"+firstField);
			log("lastField"+lastField);

			boolean twoColumns = (lastField - firstField) > 100;

	        Assert.assertTrue(twoColumns, "did not break into two columns");

			//------------------------------------------
			// set the screen width to large
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, CookbookResponsiveTestUtils.TEST_HEIGHT));

			labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));

			//check the top offset of the first field
			fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));

			// the field should be at least 10px under the label
			fieldUnder = (fieldTop - labelTop) > 10;

			Assert.assertFalse(fieldUnder, "field not to the side of label");


			//----------------Verify column variation-------------

	        firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().left)"));
			lastField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(5).find('.oj-col').eq(1).offset().left)"));

			log("firstField"+firstField);
			log("lastField"+lastField);

			twoColumns = (lastField - firstField) > 100;
	        Assert.assertTrue(twoColumns, "did not break into two columns");


    }

    @Test(groups = { "cookbook", "responsive", "formlayout" })
	    public void testColumnsAcross() throws Exception {

	        startupTest("demo-forms-form2colsacross.html", null);
	        verifyTitle("Incorrect page title;", "Form Layout - Across");

	        waitForElementVisible("id=form-container");

			//------------------------------------------
			// set the screen width to small
			WebDriver driver = getWebDriver();
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_SM, CookbookResponsiveTestUtils.TEST_HEIGHT));

			//-------------- Verify Label changes ------------

			//check the top offset of the first label
			int labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));

			//check the top offset of the first field
			int fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));

			log("labelTop"+labelTop);
		  	log("fieldTop"+fieldTop);

			waitForMilliseconds(5000L);

			// the field should be at least 10px under the label
			boolean fieldUnder = (fieldTop - labelTop) > 10;

	        Assert.assertTrue(fieldUnder, "field not under label");

	        //----------------Verify column variation-------------

	        int firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().left)"));
			log("firstField:"+firstField);
			int lastField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(2).find('.oj-col').eq(3).offset().left)"));
			log("lastField:"+lastField);

			Assert.assertEquals(firstField, lastField,"broke into two columns");

		//	log("Label"+ evalJavascript("return $('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).find('.oj-inputtext-label')"));
		// Need to verify labels


			//------------------------------------------
			// set the screen width to medium
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_MD, CookbookResponsiveTestUtils.TEST_HEIGHT));

			//check the top offset of the first label
		  	labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));


			//check the top offset of the first field
			fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));


		  	log("labelTop"+labelTop);
		  	log("fieldTop"+fieldTop);

			// the field should be at least 10px under the label
			fieldUnder = (fieldTop - labelTop) > 10;

			Assert.assertFalse(fieldUnder, "label not to the side of field");


			//----------------Verify column variation-------------

	        firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().left)"));
			lastField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(2).find('.oj-col').eq(3).offset().left)"));

			log("firstField"+firstField);
			log("lastField"+lastField);

			boolean twoColumns = (lastField - firstField) > 100;

	        Assert.assertEquals(firstField, lastField,"broke into two columns");

			//------------------------------------------
			// set the screen width to large
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, CookbookResponsiveTestUtils.TEST_HEIGHT));

			labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));

			//check the top offset of the first field
			fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));

			// the field should be at least 10px under the label
			fieldUnder = (fieldTop - labelTop) > 10;

			Assert.assertFalse(fieldUnder, "field not to the side of label");


			//----------------Verify column variation-------------

	        firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().left)"));
			lastField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(2).find('.oj-col').eq(3).offset().left)"));

			log("firstField"+firstField);
			log("lastField"+lastField);

			twoColumns = (lastField - firstField) > 100;
	        Assert.assertTrue(twoColumns, "did not break into two columns");


    }

    @Test(groups = { "cookbook", "responsive", "formlayout" })
	    public void testColumnsNested() throws Exception {

	        startupTest("demo-forms-formnested.html", null);
	        verifyTitle("Incorrect page title;", "Form Layout - Nested");

	        waitForElementVisible("id=form-container");

			//------------------------------------------
			// set the screen width to small
			WebDriver driver = getWebDriver();
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_SM, CookbookResponsiveTestUtils.TEST_HEIGHT));

			//-------------- Verify Label changes ------------

			//check the top offset of the first label
			int labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));

			//check the top offset of the first field
			int fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));

			waitForMilliseconds(5000L);

			// the field should be at least 10px under the label
			boolean fieldUnder = (fieldTop - labelTop) > 10;

	        Assert.assertTrue(fieldUnder, "field not under label");

	        //----------------Verify label for outer form and inner form-------------

	        int firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(0).offset().left)"));
			int innerFormFirstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).find('.oj-label').eq(0).offset().left)"));
			int innerFormSecondField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).find('.oj-label').eq(1).offset().left)"));

			Assert.assertEquals(firstField, innerFormFirstField,"broke into two columns");
			Assert.assertEquals(innerFormFirstField,innerFormSecondField,"broke into two columns");




			//------------------------------------------
			// set the screen width to medium
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_MD, CookbookResponsiveTestUtils.TEST_HEIGHT));

			//check the top offset of the first label
		  	labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));


			//check the top offset of the first field
			fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));


		  	// the field should be at least 10px under the label
			fieldUnder = (fieldTop - labelTop) > 10;

			Assert.assertFalse(fieldUnder, "label not to the side of field");


			//----------------Verify column variation-------------


			firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(0).offset().left)"));

			innerFormFirstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).find('.oj-label').eq(0).offset().left)"));

			innerFormSecondField =  Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).find('.oj-label').eq(1).offset().left)"));

			boolean innerFormUnder = (innerFormSecondField - innerFormFirstField) > 100;

			Assert.assertEquals(innerFormFirstField,innerFormSecondField);

			//------------------------------------------
			// set the screen width to large
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, CookbookResponsiveTestUtils.TEST_HEIGHT));

			labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));

			//check the top offset of the first field
			fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));

			// the field should be at least 10px under the label
			fieldUnder = (fieldTop - labelTop) > 10;

			Assert.assertFalse(fieldUnder, "field not to the side of label");


			//----------------Verify column variation-------------
			firstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(0).offset().left)"));

			innerFormFirstField = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).find('.oj-label').eq(0).offset().left)"));

			innerFormSecondField =  Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).find('.oj-label').eq(1).offset().left)"));

			innerFormUnder = (innerFormSecondField - innerFormFirstField) > 100;

			Assert.assertEquals(innerFormFirstField,innerFormSecondField);



    }

     @Test(groups = { "cookbook", "responsive", "formlayout" })
	    public void testColumnsLabelWrap() throws Exception {

	        startupTest("demo-forms-formlabelwrap.html", null);
	        verifyTitle("Incorrect page title;", "Form Layout - Label Wrapping");

	        waitForElementVisible("id=form-container");

			//------------------------------------------
			// set the screen width to small
			WebDriver driver = getWebDriver();
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_SM, CookbookResponsiveTestUtils.TEST_HEIGHT));

			//-------------- Verify Label changes ------------

			//check the top offset of the first label
			int labelTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-label').offset().top)"));

			//check the top offset of the first field
			int fieldTop = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-inputtext').offset().top)"));

			waitForMilliseconds(5000L);

			// the field should be at least 10px under the label
			boolean fieldUnder = (fieldTop - labelTop) > 10;

	        Assert.assertTrue(fieldUnder, "field not under label");

	        //----------------Verify label for outer form and inner form-------------

	        int firstLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(0).height())"));
			int secondLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(0).height())"));
			int thirdLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(2).find('.oj-col').eq(0).height())"));

			int fourLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(3).find('.oj-col').eq(0).height())"));
			int fiveLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(4).find('.oj-col').eq(0).height())"));
			int sixLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(5).find('.oj-col').eq(0).height())"));

			int sevenLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(6).find('.oj-col').eq(0).height())"));
			int eightLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(7).find('.oj-col').eq(0).height())"));
			int nineLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(8).find('.oj-col').eq(0).height())"));

			int tenLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(9).find('.oj-col').eq(0).height())"));
			int elevenLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(10).find('.oj-col').eq(0).height())"));
			int twelveLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(11).find('.oj-col').eq(0).height())"));

			boolean result = (firstLabelHeight == secondLabelHeight ) && (secondLabelHeight == thirdLabelHeight);
			Assert.assertTrue(result);

			result = (sevenLabelHeight == eightLabelHeight ) && (eightLabelHeight == nineLabelHeight);
			Assert.assertTrue(result);

			result = (fourLabelHeight == fiveLabelHeight ) && (fiveLabelHeight == sixLabelHeight);
			Assert.assertTrue(result);

			result = (tenLabelHeight == elevenLabelHeight ) && (elevenLabelHeight == twelveLabelHeight);
			Assert.assertTrue(result);



			//------------------------------------------
			// set the screen width to medium
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_MD, CookbookResponsiveTestUtils.TEST_HEIGHT));

			firstLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(0).height())"));
			secondLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(0).height())"));
			thirdLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(2).find('.oj-col').eq(0).height())"));

			fourLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(3).find('.oj-col').eq(0).height())"));
			fiveLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(4).find('.oj-col').eq(0).height())"));
			sixLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(5).find('.oj-col').eq(0).height())"));


			sevenLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(6).find('.oj-col').eq(0).height())"));
			eightLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(7).find('.oj-col').eq(0).height())"));
			nineLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(8).find('.oj-col').eq(0).height())"));



			tenLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(9).find('.oj-col').eq(0).height())"));
			elevenLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(10).find('.oj-col').eq(0).height())"));
			twelveLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(11).find('.oj-col').eq(0).height())"));



			result = (firstLabelHeight == secondLabelHeight) && (thirdLabelHeight == eightLabelHeight);
			Assert.assertTrue(result);

			result = (fourLabelHeight == fiveLabelHeight) && (sixLabelHeight == sevenLabelHeight) && (sevenLabelHeight == nineLabelHeight);
			Assert.assertTrue(result);

			result = (tenLabelHeight == elevenLabelHeight) && ( elevenLabelHeight == twelveLabelHeight);
			Assert.assertTrue(result);



			//------------------------------------------
			// set the screen width to large
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, CookbookResponsiveTestUtils.TEST_HEIGHT));

			firstLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(0).height())"));
			secondLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(0).height())"));
			thirdLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(2).find('.oj-col').eq(0).height())"));

			fourLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(3).find('.oj-col').eq(0).height())"));
			fiveLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(4).find('.oj-col').eq(0).height())"));
			sixLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(5).find('.oj-col').eq(0).height())"));


			sevenLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(6).find('.oj-col').eq(0).height())"));
			eightLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(7).find('.oj-col').eq(0).height())"));
			nineLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(8).find('.oj-col').eq(0).height())"));


			tenLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(9).find('.oj-col').eq(0).height())"));
			elevenLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(10).find('.oj-col').eq(0).height())"));
			twelveLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(11).find('.oj-col').eq(0).height())"));

			result = (firstLabelHeight == secondLabelHeight) && (thirdLabelHeight == eightLabelHeight);
			Assert.assertTrue(result);

			result = (fourLabelHeight == fiveLabelHeight) && (sixLabelHeight == sevenLabelHeight) && (sevenLabelHeight == nineLabelHeight);
			Assert.assertTrue(result);

			result = (tenLabelHeight == elevenLabelHeight) && ( elevenLabelHeight == twelveLabelHeight);
			Assert.assertTrue(result);


    }

    @Test(groups = { "cookbook", "responsive", "formlayout" })
	    public void testColumnsGroupingAcross() throws Exception {

	        startupTest("demo-forms-form2colsacrosssep.html", null);
	        verifyTitle("Incorrect page title;", "Form Layout - Grouping Across");

	        waitForElementVisible("id=form-container");

			//------------------------------------------
			// set the screen width to small
			WebDriver driver = getWebDriver();
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_SM, CookbookResponsiveTestUtils.TEST_HEIGHT));

			int headerHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-header-border').offset().top)"));
			int secondLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(0).offset().top)"));
			int row1col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).offset().top)"));
			int row2col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(2).find('.oj-col').eq(1).offset().top)"));

			boolean result = (headerHeight < secondLabelHeight);
			Assert.assertTrue(result);

			result = (secondLabelHeight < row1col1Height);
			Assert.assertTrue(result);

			result = (row1col1Height < row2col1Height);
			Assert.assertTrue(result);

			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_MD, CookbookResponsiveTestUtils.TEST_HEIGHT));

			headerHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-header-border').offset().top)"));
			secondLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(0).offset().top)"));
			row1col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).offset().top)"));
			row2col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(2).find('.oj-col').eq(1).offset().top)"));

			result = (headerHeight < secondLabelHeight);
			Assert.assertTrue(result);

			result = (secondLabelHeight == row1col1Height);
			Assert.assertTrue(result);

			result = (row1col1Height < row2col1Height);
			Assert.assertTrue(result);


			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, CookbookResponsiveTestUtils.TEST_HEIGHT));

			headerHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form .oj-header-border').offset().top)"));
			secondLabelHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(0).offset().top)"));
			row1col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).offset().top)"));
			row2col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(2).find('.oj-col').eq(1).offset().top)"));

			result = (headerHeight < secondLabelHeight);
			Assert.assertTrue(result);

			result = (secondLabelHeight == row1col1Height);
			Assert.assertTrue(result);

			result = (row1col1Height < row2col1Height);
			Assert.assertTrue(result);

	}

	 @Test(groups = { "cookbook", "responsive", "formlayout" })
		public void testColumnsGroupingDown() throws Exception {

			startupTest("demo-forms-form2colsdownsep.html", null);
			verifyTitle("Incorrect page title;", "Form Layout - Grouping Down");

			waitForElementVisible("id=form-container");

			//------------------------------------------
			// set the screen width to small
			WebDriver driver = getWebDriver();
			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_SM, CookbookResponsiveTestUtils.TEST_HEIGHT));

			int firstHeaderHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-header-border').eq(0).offset().top)"));
			int thirdHeaderHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-header-border').eq(2).offset().top)"));
			int secondHeaderHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-header-border').eq(1).offset().top)"));

			int row0col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().top)"));
			int row1col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).offset().top)"));
			int row2col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(2).find('.oj-col').eq(1).offset().top)"));

			int row3col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(3).find('.oj-col').eq(1).offset().top)"));
			int row4col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(4).find('.oj-col').eq(1).offset().top)"));
			int row5col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(5).find('.oj-col').eq(1).offset().top)"));

			boolean result =  (firstHeaderHeight < secondHeaderHeight);
			Assert.assertTrue(result);
			result =  (secondHeaderHeight < thirdHeaderHeight);
			Assert.assertTrue(result);

			result =  (row0col1Height < row1col1Height) && (row2col1Height < row3col1Height)
						&& (row3col1Height < row4col1Height) && (row4col1Height < row5col1Height);
			Assert.assertTrue(result);


			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_MD, CookbookResponsiveTestUtils.TEST_HEIGHT));
			firstHeaderHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-header-border').eq(0).offset().top)"));
			thirdHeaderHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-header-border').eq(2).offset().top)"));
			secondHeaderHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-header-border').eq(1).offset().top)"));

			row0col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().top)"));
			row1col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).offset().top)"));
			row2col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(2).find('.oj-col').eq(1).offset().top)"));

			row3col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(3).find('.oj-col').eq(1).offset().top)"));
			row4col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(4).find('.oj-col').eq(1).offset().top)"));
			row5col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(5).find('.oj-col').eq(1).offset().top)"));

			result =  (firstHeaderHeight < secondHeaderHeight);
			Assert.assertTrue(result);
			result =  (secondHeaderHeight < thirdHeaderHeight);
			Assert.assertTrue(result);

			result =  (row0col1Height < row1col1Height) && (row2col1Height < row3col1Height)
						&& (row3col1Height < row4col1Height) && (row4col1Height < row5col1Height);
			Assert.assertTrue(result);

			driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, CookbookResponsiveTestUtils.TEST_HEIGHT));

			firstHeaderHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-header-border').eq(0).offset().top)"));
			thirdHeaderHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-header-border').eq(2).offset().top)"));
			secondHeaderHeight = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-header-border').eq(1).offset().top)"));

			row0col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().top)"));
			row1col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).offset().top)"));
			row2col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(2).find('.oj-col').eq(1).offset().top)"));

			row3col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(3).find('.oj-col').eq(1).offset().top)"));
			row4col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(4).find('.oj-col').eq(1).offset().top)"));
			row5col1Height = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(5).find('.oj-col').eq(1).offset().top)"));

			result =  (firstHeaderHeight < secondHeaderHeight);
			Assert.assertTrue(result);
			result =  (firstHeaderHeight == thirdHeaderHeight);
			Assert.assertTrue(result);
			result =  (secondHeaderHeight > thirdHeaderHeight);
			Assert.assertTrue(result);

			result =  (row0col1Height < row1col1Height) && (row1col1Height < row2col1Height);
			Assert.assertTrue(result);
			result =  (row3col1Height < row2col1Height);
			Assert.assertTrue(result);
			result = (row3col1Height < row4col1Height) && (row4col1Height < row5col1Height);
			Assert.assertTrue(result);

	}


	@Test(groups = { "cookbook", "responsive", "formlayout" })
	public void testColumnsSpan() throws Exception {

		startupTest("demo-forms-form1colspan.html", null);
		verifyTitle("Incorrect page title;", "Form Layout - Colspan");

		waitForElementVisible("id=form-container");

		//------------------------------------------
		// set the screen width to small
		WebDriver driver = getWebDriver();
		driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_SM, CookbookResponsiveTestUtils.TEST_HEIGHT));


		int row0col1Width = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).width())"));
		int row1col1Width = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).width())"));
		int row2col1Width = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(2).find('.oj-col').eq(1).width())"));

		boolean result = (row0col1Width == row1col1Width) && (row1col1Width == row2col1Width);
		Assert.assertTrue(result);

		int row1Label = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-label').eq(1).offset().top)"));

		int row1TextArea = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-textarea').eq(0).offset().top)"));

		result = (row1Label < row1TextArea);
		Assert.assertTrue(result);

		log("row1Label"+row1Label);
		log("row1TextArea"+row1TextArea);


		int row0TextBoxLO = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().left)"));
		int row1TextAreaLO = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).offset().left)"));

		result = (row0TextBoxLO == row1TextAreaLO);
		Assert.assertTrue(result);

		driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_MD, CookbookResponsiveTestUtils.TEST_HEIGHT));


		row0col1Width = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).width())"));
		row1col1Width = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).width())"));
		row2col1Width = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(2).find('.oj-col').eq(1).width())"));

		result = (row0col1Width < row1col1Width) && (row1col1Width == row2col1Width);
		Assert.assertTrue(result);

		log("row0col1Width"+row0col1Width);
		log("row1col1Width"+row1col1Width);
		log("row2col1Width"+row2col1Width);

		row1Label = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-label').eq(1).offset().top)"));

		row1TextArea = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-textarea').eq(0).offset().top)"));

		result = (row1Label < row1TextArea);
		Assert.assertTrue(result);

		log("row1Label"+row1Label);
		log("row1TextArea"+row1TextArea);


		row0TextBoxLO = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().left)"));
		row1TextAreaLO = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).offset().left)"));

		log("row0TextBoxLO"+row0TextBoxLO);
		log("row1TextAreaLO"+row1TextAreaLO);

		result = (row0TextBoxLO - row1TextAreaLO) > 200;
		Assert.assertTrue(result);

		driver.manage().window().setSize(new Dimension(CookbookResponsiveTestUtils.WIDTH_LG, CookbookResponsiveTestUtils.TEST_HEIGHT));


		row0col1Width = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).width())"));
		row1col1Width = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).width())"));
		row2col1Width = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(2).find('.oj-col').eq(1).width())"));

		row1Label = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-label').eq(1).offset().top)"));

		row1TextArea = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-textarea').eq(0).offset().top)"));

		result = (row1Label < row1TextArea);
		Assert.assertTrue(result);

		log("row1Label"+row1Label);
		log("row1TextArea"+row1TextArea);

		row0TextBoxLO = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(0).find('.oj-col').eq(1).offset().left)"));
		row1TextAreaLO = Integer.parseInt(evalJavascript("return parseInt($('.oj-form').find('.oj-row').eq(1).find('.oj-col').eq(1).offset().left)"));

		log("row0TextBoxLO"+row0TextBoxLO);
		log("row1TextAreaLO"+row1TextAreaLO);

		result = (row0TextBoxLO - row1TextAreaLO) > 200;
		Assert.assertTrue(result);

	}


	private void log(String log)
	{
			System.out.println(log);
			getLogger().fine("[CookbookFormLayoutTest] " + log);
	}

}
