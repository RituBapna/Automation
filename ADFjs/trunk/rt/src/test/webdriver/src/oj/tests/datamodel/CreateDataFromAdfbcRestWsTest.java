package oj.tests.datamodel;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;

public class CreateDataFromAdfbcRestWsTest extends OJetBase {
    private static final String ADD_BUTTON = "saveBtn";
    private static final String NEWDEPTID = "newDepartId";
    private static final String NEWDEPTID_VALUE = "555";    
    private static final String NEWDEPTNAME = "newDepartName";
    private static final String ROWS = "//tbody[@id='DepartmentsDetails']/tr";

    public CreateDataFromAdfbcRestWsTest() {
        super(new TestConfigBuilder().setContextRoot("datamodel").setAppRoot("CommonModelAPICreateApp").build());
    }
    
    // group a is the group fetch test belongs to
    // group b is added to make sure that update test is executed after fetch and create
    @Test (groups = { "commonModelAPI", "cma", "b", "c" }, dependsOnGroups = {"a"})
    public void testCreate() throws Exception {
        /*
         * For this app, IE immediately shows alert: "This page is accessing information that is not under its control."
         * which will cause the next command right after opening the page to fail. This includes verifyTitle and so
         * we cannot use startupTest() here but have to split the start of the browser and title verification.
         */
        // open page but do not verify title
        startupTest("index.html", null);
        CommonUtils.startupTest(this, isInternetExplorer());
        verifyTitle("Incorrect page title;", "Oracle JET Common Model - Create");

        //wait for the page to be displayed
        waitForElementVisible(NEWDEPTID);

        List<WebElement> rowElements = findElements(ROWS);
        verifyNotNull("Could not obtain list of rows;", rowElements);
        int numOfRowsBeforeAdd = rowElements.size();
        verifyGreaterThan("Table does not have any row;", 0, numOfRowsBeforeAdd);

        // clear input field and enter new value
        doType(NEWDEPTNAME, "New Deptname", true);
        // clear input field and enter new value
        doType(NEWDEPTID, NEWDEPTID_VALUE, true);
        // create new department entry
        click(ADD_BUTTON);
        this.waitForMilliseconds(1000);
        //deptid is also used as @id in span element -> use it to wait for the new entry to be displayed
        waitForElementVisible(NEWDEPTID_VALUE);
        getWebDriver().navigate().refresh();
        waitForElementVisible(NEWDEPTID);
        // verify it's the last row
        rowElements = findElements(ROWS);
        verifyNotNull("Could not obtain list of rows;", rowElements);
        verifyGreaterThan("Table does not have any row;", 0, rowElements.size());
        verifyEquals("Table does not have expected number of rows;", numOfRowsBeforeAdd + 1, rowElements.size());
        verifyEquals("Incorrect dept id in last row;", NEWDEPTID_VALUE,
                     getText("//table/tbody/tr[" + (numOfRowsBeforeAdd + 1) + "]/td[1]/span"));
    }
}
