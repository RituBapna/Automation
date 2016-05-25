package oj.tests.table.psr;

import oj.tests.table.table.TestTableBase;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import oj.tests.table.paging.TestTablePagingBase;
import oj.tests.table.table.TestTableBase;

public class TestTablePsr2 extends TestTableBase {

    private static final String MYGROUP = "tablePsr2";

    public TestTablePsr2() {
        super("psr", "table", "Jet Table Psr2 Test", "testTablePsr2.html", "table");
    }


    @Test(groups = { MYGROUP })
    public void createTable() throws Exception {
        super.createTable();
    }

    @Test(groups = { MYGROUP })
    public void testTableHeaders() throws Exception {
        super.testTableHeaders("Remove","Department Id","Department Name","Location Id",
                               "Manager Id", "Mentor", "Supervisor", "HR", "Logo", "Rating");
    }

    // In each test all values

    // Test Values Array
    // -- add single
    // -- modify single
    // -- delete single
    // -- add 200
    // -- delete 200
    // -- modify 200
    
    // Test Values Collection
    // -- add single
    // -- modify single
    // -- delete single
    // -- add 200
    // -- delete 200
    // -- modify 200
    
    // Test Value ObsArray
    // -- add single
    // -- modify single
    // -- delete single
    // -- add 200
    // -- delete 200
    // -- modify 200        

}
