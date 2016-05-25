package oj.tests.table.table;

import java.awt.Component;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

public class TestTableComponents extends TestTableBase {

    private static final String MYGROUP = "tableComponents";

    public TestTableComponents() {
        super("table", "table", "Jet ojTable with Jet Components Test", "testTableComponents.html", "table");
    }

    @Test(groups = { MYGROUP })
    public void createTable() throws Exception {
        super.createTable();
    }

    @Test(groups = { MYGROUP })
    public void destroyTable() throws Exception {
        super.destroyTable(
        "Component destroyed : cbopt",
        "Component destroyed : cbopt",
        "Component destroyed : cbopt",
        "Component destroyed : cbopt", 
        "Component destroyed : table");
        // each row in table has checkbox item, so four of those to destroy 
    }

    @Test(groups = { MYGROUP })
    public void testTableHeaders() throws Exception {        
        testTableHeaders("Checkbox","Department Id", "Department Name", "Location Id", "Manager Id");
    }

    @Test(groups = { MYGROUP })
    public void testTableFooters() throws Exception {
        super.testTableFooters("","Rows", "", "", "4");
    }

    @Test(groups = { MYGROUP })
    public void testTableValues() throws Exception {
        testTableValues(new String[] { "", "15", "QA", "200", "300" }, 
                          new String[] { "", "25", "Dev", "200", "300" },
                          new String[] { "", "35", "Marketing", "200", "300" },
                          new String[] { "", "45", "Shipping", "200", "300" });

    }


}

