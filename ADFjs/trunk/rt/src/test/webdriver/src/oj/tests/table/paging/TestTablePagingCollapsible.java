package oj.tests.table.paging;

import oj.tests.table.table.TestTableBase;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

import oj.tests.table.table.TestTableBase;
public class TestTablePagingCollapsible extends TestTablePagingBase {

    private static final String MYGROUP = "tablePagingCollapsible";

    public TestTablePagingCollapsible() {
        super("paging", "table", "Jet ojPagingControl Collapsible Test", "testTablePagingCollapsible.html", 
              "table","paging","collapsiblePage");
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
    public void testPagingTable() throws Exception {
        super.testPagingTable();
    }

    @Test(groups = { MYGROUP })
    public void testPagingLargeTable() throws Exception {
        super.testPagingLargeTable();
    }

    @Test(groups = { MYGROUP })
    public void testTableHeaders() throws Exception {
        super.testTableHeaders();
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
        log("Exposing component using collapsible");
        String locString = "{\"element\":\"#" + exposerId + "\",\"subId\":\"oj-collapsible-header-icon\"}";
        log(locString);
        waitForElementVisible(exposerId);
        WebElement collapsibleDiv = getElement(exposerId);
        WebElement header1 = getElement(locString);
        header1.click();
        waitForMilliseconds(1000L);  // let data load
    }

}

