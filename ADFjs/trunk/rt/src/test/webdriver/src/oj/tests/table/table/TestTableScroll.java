package oj.tests.table.table;

import com.thoughtworks.selenium.Selenium;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;

public class TestTableScroll extends TestTableBase {

    private static final String MYGROUP = "tableScroll";

    public TestTableScroll() {
        super("table", "table", "Jet ojTable Scroll Test", "testTableScroll.html", "table");
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
    public void verifyScroll() throws Exception {
        startupTest(myPage, null);
        waitForComp();
        testScroll(true);
    }

    @Test(groups = { MYGROUP })
    public void verifyScrollAfterSort() throws Exception {
        startupTest(myPage, null);
		  waitForComp();
        testScroll(true);
        sortTable(false, true,1);
		  waitForComp();
        testScroll(true);

    }


}

