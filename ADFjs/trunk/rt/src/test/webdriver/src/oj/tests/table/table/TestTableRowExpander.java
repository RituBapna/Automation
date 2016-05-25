package oj.tests.table.table;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;


public class TestTableRowExpander extends TestTableBase {

    private static final String MYGROUP = "tableRowExpander";

    public TestTableRowExpander() {
        super("table", "table", "Jet ojTable ojRowExpander Test", "testTableRowExpander.html", "table");
    }

    @Test(groups = { MYGROUP })
    public void createTable() throws Exception {
        super.createTable();
    }

    // Override for RowExpander since it seems to not find exposed status    
    public void verifyCreateComp(String ... expected) throws Exception {
        
        waitForMilliseconds(2000L);
                        
        assertMsgLog(expected);
    }


    @Test(groups = { MYGROUP })
    public void destroyTable() throws Exception {
        super.destroyTable(
        "Component destroyed : t4", "Component destroyed : t3", "Component destroyed : t2", 
        "Component destroyed : t1", "Component destroyed : table");
        // Each row has row expander that needs destroying at table destruction time
    }

    @Test(groups = { MYGROUP })
    public void testTableHeaders() throws Exception {
        super.testTableHeaders("Task Name","Resource","Start Date","End Date");
    }

//    @Test(groups = { MYGROUP })
    public void testTableFooters() throws Exception {
        super.testTableFooters();
    }

    @Test(groups = { MYGROUP })
    public void testTableRowExpander() throws Exception {
        startupTest(myPage, null);
        waitForElementVisible(compLocator());
        verifyTableValues(new String[] { "Task 1", "Larry", "1/1/2014", "10/1/2014" }, 
                          new String[] { "Task 2", "Larry", "4/1/2014", "12/1/2014" },
                          new String[] { "Task 3", "Larry", "5/1/2014", "11/1/2014" },
                          new String[] { "Task 4", "Larry", "11/1/2014", "12/1/2014" });
        WebElement t1Icon = getElement("{\"element\":\"#t1\",\"subId\":\"oj-rowexpander-icon\"}");
        t1Icon.click();
        verifyTableValues(new String[] { "Task 1", "Larry", "1/1/2014", "10/1/2014" }, 
                            new String[] { "Task 1-1", "Larry", "1/1/2014", "3/1/2014" }, 
                            new String[] { "Task 1-2", "Larry", "3/1/2014", "6/1/2014" }, 
                            new String[] { "Task 1-3", "Larry", "6/1/2014", "8/1/2014" }, 
                            new String[] { "Task 1-4", "Larry", "8/1/2014", "10/1/2014" }, 
                          new String[] { "Task 2", "Larry", "4/1/2014", "12/1/2014" },
                          new String[] { "Task 3", "Larry", "5/1/2014", "11/1/2014" },
                          new String[] { "Task 4", "Larry", "11/1/2014", "12/1/2014" });                
        t1Icon.click();
        verifyTableValues(new String[] { "Task 1", "Larry", "1/1/2014", "10/1/2014" }, 
                          new String[] { "Task 2", "Larry", "4/1/2014", "12/1/2014" },
                          new String[] { "Task 3", "Larry", "5/1/2014", "11/1/2014" },
                          new String[] { "Task 4", "Larry", "11/1/2014", "12/1/2014" });                

    }


}


