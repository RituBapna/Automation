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
public class TestTablePagingDialog extends TestTablePagingBase {

    private static final String MYGROUP = "tablePagingDialog";

    public TestTablePagingDialog() {
        super("paging", "table", "Jet ojTable ojPagingControl ojDialog Test", "testTablePagingDialog.html", 
              "table","paging","btn_opendialog");
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
    
}


