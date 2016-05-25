package oj.tests.table.table;

import java.awt.Component;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

public class TestTableRowMenu extends TestTableBase {

    private static final String MYGROUP = "tableComponents";

    public TestTableRowMenu() {
        super("table", "table", "Jet ojTable with Row Menu Test", "testTableRowMenu.html", "table");
    }

    @Test(groups = { MYGROUP })
    public void createTable() throws Exception {
        super.createTable();
    }

    @Test(groups = { MYGROUP })
    public void destroyTable() throws Exception {
        super.destroyTable(
        "Component destroyed : mbid_3",
        "Component destroyed : mbid_2",
        "Component destroyed : mbid_1",
        "Component destroyed : mbid_0",
        "Component destroyed : table");
        // each row in table has checkbox item, so four of those to destroy 
    }
    
    @Test(groups = { MYGROUP })
    public void testMenuButton() throws Exception {
        //Start the test and bring up the browser
        startupTest(myPage, null);

        // Click on clear log
        click("id=btn_clear");
        // Click on menu button for first row
        // and click on first item in list
        clickAndSelectMenuOption("id=mbid_0","id=ui-id-1");

        waitForMilliseconds(3000L);
            
        // Verify that items shown in log
        assertMsgLog(
        "menu item selected = Item 1",
        "menu opened by = mbid_0",
        "Triggered ojoptionchange event for selection: ",
        "Row Selection",
        "start row index: 0, end row index: 0",
        "start row key: 15, end row key: 15",
        "Triggered ojbeforecurrentrow event: ", 
        "previousCurrentRow: null ",
        "currentRow: {rowIndex: 0 rowKey: 15} ");

    }

    @Test(groups = { MYGROUP })
    public void testTableHeaders() throws Exception {        
        testTableHeaders("Menu","Department Id", "Department Name", "Location Id", "Manager Id");
    }

    @Test(groups = { MYGROUP })
    public void testTableFooters() throws Exception {
        testTableFooters("","Rows", "", "", "4");
    }

    @Test(groups = { MYGROUP })
    public void testTableValues() throws Exception {
        testTableValues(new String[] { "menu button", "15", "QA", "200", "300" }, 
                          new String[] { "menu button", "25", "Dev", "200", "300"},
                          new String[] { "menu button", "35", "Marketing", "200", "300" },
                          new String[] { "menu button", "45", "Shipping", "200", "300" });

    }


}

