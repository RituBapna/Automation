package oj.tests.datamodel;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.annotations.Test;

public class UpdateDataFromAdfbcRestWsTest extends OJetBase {
    private static final String ROWS = "//tbody[@id='DepartmentsDetails']/tr";
    private static final String NEWDEPTNAME = "newName";
    private static final String UPDATE_BUTTON = "updateBtn";
    public UpdateDataFromAdfbcRestWsTest() {
        super(new TestConfigBuilder().setContextRoot("datamodel").setAppRoot("CommonModelAPIUpdateApp").build());
    }
    
    //Group b makes sure that update is executed only after fetch and create
    //tests has been completed successfully
    //Group c is added to make sure that delete is executed only after fetch, create, and update
    @Test (groups = { "commonModelAPI", "c" }, dependsOnGroups = {"b"})
    public void testUpdate() throws Exception {
        /*
         * For this app, IE immediately shows alert: "This page is accessing information that is not under its control."
         * which will cause the next command right after opening the page to fail. This includes verifyTitle and so
         * we cannot use startupTest() here but have to split the start of the browser and title verification.
         */
        // open page but do not verify title
        startupTest("index.html", null);
        CommonUtils.startupTest(this, isInternetExplorer());
        verifyTitle("Incorrect page title;", "Oracle JET Common Model - Update");

        // the rows for departments use the value as @id
        waitForElementVisible("10");

        List<WebElement> rowElements = findElements(ROWS);
        verifyNotNull("Could not obtain list of rows;", rowElements);
        int numOfRows = rowElements.size();
        String deptNameInLastRowLocator = ROWS + "[" + numOfRows + "]/td[2]/div";
        
        // click into dept name of the last row to make it an input field
        click(deptNameInLastRowLocator);
        //waitForElementVisible(deptNameInLastRowLocator + "/input");
        
        // clear input field and enter new value
        doType(NEWDEPTNAME, "ValueUpdatedByTest", true);
        // press ENTER to submit updated value
       // new Actions(getWebDriver()).sendKeys(Keys.RETURN).perform();
       // update department entry
        click(UPDATE_BUTTON); 
        this.waitForMilliseconds(1000);
        //refresh the page 
        getWebDriver().navigate().refresh();
        waitForElementVisible("10");
        verifyEquals("Entered and submitted text do not match;", "ValueUpdatedByTest",
                     getText(deptNameInLastRowLocator));
    }
}
