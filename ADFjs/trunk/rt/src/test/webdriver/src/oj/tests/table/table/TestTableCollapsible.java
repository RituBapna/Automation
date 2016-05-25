package oj.tests.table.table;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

public class TestTableCollapsible extends TestTableBase {

    private static final String MYGROUP = "tableCollapsible";

    public TestTableCollapsible() {
        super("table", "table", "Jet ojTable ojCollapsible Test", "testTableCollapsible.html", 
              "table","collapsiblePage");
    }

    @Test(groups = { MYGROUP })
    public void createTable() throws Exception {
        super.createTable();
    }

    @Test(groups = { MYGROUP })
    public void destroyTable() throws Exception {
        super.destroyTable();
    }

    @Test(groups = { MYGROUP })
    public void testTableHeaders() throws Exception {
        super.testTableHeaders();
    }

    @Test(groups = { MYGROUP })
    public void testTableValues() throws Exception {
        super.testTableValues();
    }

    @Test(groups = { MYGROUP })
    public void testTableFooters() throws Exception {
        super.testTableFooters();
    }

    @Test(groups = { MYGROUP })
    public void testSort() throws Exception {
        super.testSort();
    }

    // @Test(groups = { MYGROUP })
	 // ContextMenu testing fails due to bug 20339292 
    public void testSortContextMenu() throws Exception {
        super.testSortContextMenu();
    }


    public void showComponent() {
        // super.showComponent(); overwrite base
        log("Exposing component using collapsible");
        String locString = "{\"element\":\"#" + exposerId + "\",\"subId\":\"oj-collapsible-header-icon\"}";
//        waitForElementVisible(exposerId);
//         WebElement collapsibleDiv = getElement(exposerId);
        WebElement header1 = getElement(locString);
        header1.click();
		  waitForElementVisible(compLocator());
        waitForMilliseconds(2000L);
    }
}

