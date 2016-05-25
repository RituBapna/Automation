package oj.tests.table.table;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;


public class TestTableCellRender extends TestTableBase {

    private static final String MYGROUP = "tableCellRender";

    public TestTableCellRender() {
        super("table", "table", "Jet ojTable Custom Cell Renderer Test", "testTableCellRender.html", "table");
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
        super.testTableHeaders("Department Id", "Department Name", "Location Id", "Manager Id","Oracle Link");
    }

    @Test(groups = { MYGROUP })
    public void testTableValues() throws Exception {
        testTableValues(new String[] { "QA", "QA", "200", "300","Oracle" }, 
                          new String[] { "Dev", "Dev", "200", "300","Oracle" },
                          new String[] { "Marketing", "Marketing", "200", "300","Oracle" },
                          new String[] { "Shipping", "Shipping", "200", "300","Oracle" });

    }

    @Test(groups = { MYGROUP })
    public void testTableFooters() throws Exception {
        super.testTableFooters("Rows", "", "", "4","");
    }

    @Test(groups = { MYGROUP })
    public void testSort() throws Exception {
        startupTest(myPage, null);
        waitForElementVisible(compLocator());
        testSort(true, false, 1, new String[] { "Dev", "Dev", "200", "300","Oracle"},
                       new String[] { "Marketing", "Marketing", "200", "300","Oracle" }, 
                       new String[] { "QA", "QA", "200", "300","Oracle" },
                       new String[] { "Shipping", "Shipping", "200", "300","Oracle" });        
    }

    // @Test(groups = { MYGROUP })
	 // ContextMenu testing fails due to bug 20339292 
    public void testSortContextMenu() throws Exception {
        startupTest(myPage, null);
        waitForElementVisible(compLocator());
        testSort(true, true, 1, new String[] { "Dev", "Dev", "200", "300","Oracle" },
                       new String[] { "Marketing", "Marketing", "200", "300","Oracle" }, 
                       new String[] { "QA", "QA", "200", "300","Oracle" },
                       new String[] { "Shipping", "Shipping", "200", "300","Oracle" });
        testSort(false, true, 1, new String[] { "Shipping", "Shipping", "200", "300","Oracle" },
                       new String[] { "QA", "QA", "200", "300","Oracle" }, 
                       new String[] { "Marketing", "Marketing", "200", "300","Oracle" },
                       new String[] { "Dev", "Dev", "200", "300","Oracle" });
    }

}
