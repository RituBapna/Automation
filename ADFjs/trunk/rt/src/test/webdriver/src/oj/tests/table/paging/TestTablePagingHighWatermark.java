package oj.tests.table.paging;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import oj.tests.table.table.TestTableBase;

public class TestTablePagingHighWatermark extends TestTablePagingBase {

    private static final String MYGROUP = "tablePagingHighWatermark";

    public TestTablePagingHighWatermark() {
        super("paging", "table", "Jet ojPagingControl HighWatermark loadMore Test", "testTablePagingHighWatermark.html",
              "table", "paging");
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
    public void testPagingHighWatermarkTable() throws Exception {
        startupTest(myPage, null);
        goLargeData();
        /*   Show More...1-10 of 45 items*/
        verifyLoadMore("1-10 of 45 items", "45");
        verifyTableValues(new String[] { "10015", "ADFPM 1001 neverending", "200", "300" },
                          new String[] { "556", "BB", "200", "300" },
                          new String[] { "10", "Administration", "200", "300" },
                          new String[] { "20", "Marketing", "200", "300" },
                          new String[] { "30", "Purchasing", "200", "300" },
                          new String[] { "40", "Human Resources1", "200", "300" },
                          new String[] { "50", "Administration2", "200", "300" },
                          new String[] { "60", "Marketing3", "200", "300" },
                          new String[] { "70", "Purchasing4", "200", "300" },
                          new String[] { "80", "Human Resources5", "200", "300" });
        clickLoadMore();
        verifyLoadMore("1-20 of 45 items", "45");
        verifyTableValues(new String[] { "10015", "ADFPM 1001 neverending", "200", "300" },
                          new String[] { "556", "BB", "200", "300" },
                          new String[] { "10", "Administration", "200", "300" },
                          new String[] { "20", "Marketing", "200", "300" },
                          new String[] { "30", "Purchasing", "200", "300" },
                          new String[] { "40", "Human Resources1", "200", "300" },
                          new String[] { "50", "Administration2", "200", "300" },
                          new String[] { "60", "Marketing3", "200", "300" },
                          new String[] { "70", "Purchasing4", "200", "300" },
                          new String[] { "80", "Human Resources5", "200", "300" },
                          new String[] { "90", "Human Resources11", "200", "300" },
                          new String[] { "100", "Administration12", "200", "300" },
                          new String[] { "110", "Marketing13", "200", "300" },
                          new String[] { "120", "Purchasing14", "200", "300" },
                          new String[] { "130", "Human Resources15", "200", "300" },
                          new String[] { "1001", "ADFPM 1001 neverending", "200", "300" },
                          new String[] { "55611", "BB", "200", "300" },
                          new String[] { "1011", "Administration", "200", "300" },
                          new String[] { "2011", "Marketing", "200", "300" },
                          new String[] { "3011", "Purchasing", "200", "300" });
    }

    @Test(groups = { MYGROUP })
    public void testTableHeaders() throws Exception {
        super.testTableHeaders();
    }

    @Test(groups = { MYGROUP })
    public void testTableFooters() throws Exception {
        startupTest(myPage, null);
        goLargeData();
        verifyTableFooters("Rows", "", "", "45");
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


