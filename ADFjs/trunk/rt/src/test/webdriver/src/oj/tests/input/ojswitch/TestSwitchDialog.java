package oj.tests.input.ojswitch;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import oj.tests.common.TestCompBase;
import oj.tests.table.table.TestTableBase;

public class TestSwitchDialog extends TestSwitchBase {

    private static final String MYGROUP = "switchDialog";

    public TestSwitchDialog() {
        super("switch", "input", "Jet ojSwitch Test", "testSwitchDialog.html",
              "switch", "btn_opendialog");
    }

    @Test(groups = { MYGROUP, INPUTGROUP, SWITCHGROUP })
    public void createComp() throws Exception {
        super.createComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, SWITCHGROUP })
    public void destroyComp() throws Exception {
        super.destroyComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, SWITCHGROUP })
    public void testValue() throws Exception {
        super.testValue();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, SWITCHGROUP })
    public void testReadOnly() throws Exception {
        super.testReadOnly();
      }

      // no required for switches

    @Test(groups = { MYGROUP, INPUTGROUP, SWITCHGROUP })
    public void testHelp() throws Exception {
        super.testHelp();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SWITCHGROUP })
    public void testTitle() throws Exception {
        super.testTitle();
      }

      // No pattern for switches

    @Test(groups = { MYGROUP, INPUTGROUP, SWITCHGROUP })
    public void testWidget() throws Exception {
        super.testWidget();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SWITCHGROUP })
    public void testGetNodeBySubId() throws Exception {
        super.testGetNodeBySubId();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SWITCHGROUP })
    public void testMessages() throws Exception {
        super.testMessages();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SWITCHGROUP })
    public void testEvents() throws Exception {
        super.testEvents();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SWITCHGROUP })
    public void testNativeTheme() throws Exception {
        super.testNativeTheme();    setup();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SWITCHGROUP })
    public void testMinCss() throws Exception {
        super.testMinCss();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SWITCHGROUP })
    public void testLTR() throws Exception {
        super.testLTR();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SWITCHGROUP })
    public void testLabel() throws Exception {
        super.testLabel();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SWITCHGROUP })
    public void testRootAttributes() throws Exception {
        super.testRootAttributes();
      }



}
