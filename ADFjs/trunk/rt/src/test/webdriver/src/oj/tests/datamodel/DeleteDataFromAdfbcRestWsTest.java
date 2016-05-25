package oj.tests.datamodel;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;

public class DeleteDataFromAdfbcRestWsTest extends OJetBase {
    private static final String CHECKBOX = "//input[@id='555']";
    private static final String REMOVE_DEPARTMENT_BUTTON = "deleteDept_btn";
    private static final String ROWS = "//tbody[@id='DepartmentsDetails']/tr";

    public DeleteDataFromAdfbcRestWsTest() {
        super(new TestConfigBuilder().setContextRoot("datamodel").setAppRoot("CommonModelAPIDeleteApp").build());
    }

    @Test (groups = { "commonModelAPI", "cma" }, dependsOnGroups={"c"})
    public void testDelete() throws Exception {
        /*
         * For this app, IE immediately shows alert: "This page is accessing information that is not under its control."
         * which will cause the next command right after opening the page to fail. This includes verifyTitle and so
         * we cannot use startupTest() here but have to split the start of the browser and title verification.
         */
        // open page but do not verify title
        startupTest("index.html", null);
        CommonUtils.startupTest(this, isInternetExplorer());
        verifyTitle("Incorrect page title;", "Oracle JET Common Model - Delete");

        // the rows for departments use the value as @id
        waitForElementVisible("id="+REMOVE_DEPARTMENT_BUTTON);

        List<WebElement> rowElements = findElements(ROWS);
        verifyNotNull("Could not obtain list of rows;", rowElements);
        int numOfRowsBeforeRemove = rowElements.size();
        verifyGreaterThan("Table does not have any row;", 0, numOfRowsBeforeRemove);

        // select checkbox for department 555
        click(CHECKBOX);
        // and delete row
        click(REMOVE_DEPARTMENT_BUTTON);
        this.waitForMilliseconds(1000);
        // wait for row to vanish
        waitForElementNotPresent("555");
       
        //refresh the page 
        getWebDriver().navigate().refresh();
        waitForElementVisible("10");
      
        rowElements = findElements(ROWS);
        verifyNotNull("Could not obtain list of rows;", rowElements);
        verifyGreaterThan("Table does not have any row;", 0, rowElements.size());
        verifyEquals("Table does not have expected number of rows;", numOfRowsBeforeRemove - 1, rowElements.size());
    }
}
