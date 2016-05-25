package oj.tests.input.select;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import oj.tests.common.TestCompBase;
import oj.tests.table.table.TestTableBase;

public class TestSelectPopup extends TestSelectBase {

    protected static final String MYGROUP = "selectPopup";

    public TestSelectPopup() {
        super("select", "input", "Jet ojSelect Test", "testSelectPopup.html",
              "select", "btn_openpopup");
    }

    @Test(groups = { MYGROUP, INPUTGROUP, SELECTGROUP })
    public void createComp() throws Exception {
        super.createComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, SELECTGROUP })
    public void destroyComp() throws Exception {
        super.destroyComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, SELECTGROUP })
    public void testValue() throws Exception {
        super.testValue();
    }

  // ReadOnly not supported on Comboboxes or Select see Bug 20731253
  // public void testReadOnly() throws Exception {

    @Test(groups = { MYGROUP, INPUTGROUP, SELECTGROUP })
    public void testRequired() throws Exception {
        super.testRequired();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SELECTGROUP })
    public void testValidator() throws Exception {
        super.testValidator();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SELECTGROUP })
    public void testHelp() throws Exception {
        super.testHelp();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SELECTGROUP })
    public void testTitle() throws Exception {
        super.testTitle();
      }

      // Pattern not supported for combos

    @Test(groups = { MYGROUP, INPUTGROUP, SELECTGROUP })
    public void testWidget() throws Exception {
        super.testWidget();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SELECTGROUP })
    public void testGetNodeBySubId() throws Exception {
        super.testGetNodeBySubId();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SELECTGROUP })
    public void testMessages() throws Exception {
        super.testMessages();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SELECTGROUP })
    public void testEvents() throws Exception {
        super.testEvents();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SELECTGROUP })
    public void testNativeTheme() throws Exception {
        super.testNativeTheme();    setup();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SELECTGROUP })
    public void testMinCss() throws Exception {
        super.testMinCss();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SELECTGROUP })
    public void testLTR() throws Exception {
        super.testLTR();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SELECTGROUP })
    public void testLabel() throws Exception {
        super.testLabel();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, SELECTGROUP })
    public void testRootAttributes() throws Exception {
        super.testRootAttributes();
      }



}
