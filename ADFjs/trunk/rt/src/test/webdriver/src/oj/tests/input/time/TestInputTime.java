package oj.tests.input.time;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import oj.tests.common.TestCompBase;
import oj.tests.input.TestInputBase;
import oj.tests.input.datetime.TestInputDateTime;
import oj.tests.input.datetime.TestInputDateTimeBase;
import oj.tests.table.table.TestTableBase;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;


public class TestInputTime extends TestInputTimeBase {

    private static final String MYGROUP = "inputTime";

    public TestInputTime() {
        super("time", "input", "Jet ojInputTime Test", "testInputTime.html", "text-input");
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void createComp() throws Exception {
        super.createComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void destroyComp() throws Exception {
        super.destroyComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void testValue() throws Exception {
        super.testValue();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void testPicker() throws Exception {
        super.testPicker();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void testReadOnly() throws Exception {
        super.testReadOnly();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void testRequired() throws Exception {
        super.testRequired();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void testValidator() throws Exception {
        super.testValidator();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void testHelp() throws Exception {
        super.testHelp();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void testTitle() throws Exception {
        super.testTitle();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void testWidget() throws Exception {
        super.testWidget();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void testGetNodeBySubId() throws Exception {
        super.testGetNodeBySubId();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void testMessages() throws Exception {
        super.testMessages();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void testEvents() throws Exception {
        super.testEvents();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void testNativeTheme() throws Exception {
        super.testNativeTheme();    setup();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void testMinCss() throws Exception {
        super.testMinCss();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void testLTR() throws Exception {
        super.testLTR();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void testLabel() throws Exception {
        super.testLabel();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTIMEGROUP })
    public void testRootAttributes() throws Exception {
        super.testRootAttributes();
      }


}
