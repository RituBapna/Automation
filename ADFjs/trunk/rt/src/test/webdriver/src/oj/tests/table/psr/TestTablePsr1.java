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

public class TestTablePsr1 extends TestTablePagingBase {

    private static final String MYGROUP = "tablePsr1";

    public TestTablePsr1() {
        super("psr", "table", "Jet Table Psr1 Test", "testTablePsr1.html", "table", "paging");
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


    @Test(groups = { MYGROUP })
    public void testAddRowCollection() throws Exception {
        startupTest(myPage, null);
        waitForComp();
        WebElement collectionButton = getElement("id=buttonId3");
        WebElement addRowButton = getElement("id=buttonId5");
        
        collectionButton.click();
        waitForComp();
        addRowButton.click();
        waitForComp();
        
        verifyTableValues(new String[] { "", "200", "QA", "200", "300", "300","300","300","","" }, 
                          new String[] { "", "201", "QA", "200", "300", "300","300","300","","" },
                          new String[] { "", "202", "QA", "200", "300", "300","300","300","","" },
                          new String[] { "", "203", "QA", "200", "300", "300","300","300","","" },
                          new String[] { "", "204", "QA", "200", "300", "300","300","300","","" });                


    }
    // In each test all values, and paging to all values

    // Test Values Array
    // -- add single
    // -- modify single
    // -- delete single
    // -- add 200
    // -- delete 200
    // -- modify 200
    // -- loadMore Mode ?count=20&mode=loadMore
    // ---- add single
    // ---- modify single
    // ---- delete single
    // ---- add 200
    // ---- delete 200
    // ---- modify 200
    
    // Test Values Collection
    // -- add single
    // -- modify single
    // -- delete single
    // -- add 200
    // -- delete 200
    // -- modify 200
    // -- loadMore Mode ?count=20&mode=loadMore
    // ---- add single
    // ---- modify single
    // ---- delete single
    // ---- add 200
    // ---- delete 200
    // ---- modify 200
    
    // Test Value ObsArray
    // -- add single
    // -- modify single
    // -- delete single
    // -- add 200
    // -- delete 200
    // -- modify 200
    // -- loadMore Mode ?count=20&mode=loadMore
    // ---- add single
    // ---- modify single
    // ---- delete single
    // ---- add 200
    // ---- delete 200
    // ---- modify 200

    
    

}
