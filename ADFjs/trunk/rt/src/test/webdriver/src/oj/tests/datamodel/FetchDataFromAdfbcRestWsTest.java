package oj.tests.datamodel;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;

public class FetchDataFromAdfbcRestWsTest extends OJetBase {
    private static final String TABLE = "//div[@id='mainContent']/table";
    private static final String ROWS = "//tbody[@id='DepartmentsDetails']/tr";

    public FetchDataFromAdfbcRestWsTest() {
        super(new TestConfigBuilder().setContextRoot("datamodel").setAppRoot("CommonModelAPIFetchApp").build());
    }

    //groups a, b and c are added to make sure fetch, create, update and delete are executed in that order
    @Test (groups = { "commonModelAPI", "cma", "a", "b", "c" })
    public void testFetch() throws Exception {
        /*
         * For this app, IE immediately shows alert: "This page is accessing information that is not under its control."
         * which will cause the next command right after opening the page to fail. This includes verifyTitle and so
         * we cannot use startupTest() here but have to split the start of the browser and title verification.
         */
        // open page but do not verify title
        startupTest("index.html", null);
        CommonUtils.startupTest(this, isInternetExplorer());
        verifyTitle("Incorrect page title;", "Oracle JET Common Model - Fetch");

        //wait for the table to be displayed
        waitForElementVisible(TABLE);
        waitForElementPresent("Administration");
        List<WebElement> rowElements = findElements(ROWS);
        verifyNotNull("Could not obtain list of rows;", rowElements);
        verifyGreaterThan("Table does not have any row;", 0, rowElements.size());
        verifyEquals("Table does not have expected number of rows;", 27, rowElements.size());
    }
}
