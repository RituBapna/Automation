package oj.tests.table.table;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

public class TestTableDialog extends TestTableBase {

    private static final String MYGROUP = "tableDialog";

    public TestTableDialog() {
        super("table", "table", "Jet ojTable ojDialog Test", "testTableDialog.html", "table","btn_opendialog");
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

}
