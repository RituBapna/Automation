package oj.tests.input.checkbox;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import oj.tests.common.TestCompBase;
import oj.tests.table.table.TestTableBase;

public class TestCheckboxsetDialog extends TestCheckboxsetBase {

    private static final String MYGROUP = "checkboxsetDialog";

    public TestCheckboxsetDialog() {
        super("checkbox", "input", "Jet ojCheckboxset Test", "testCheckboxsetDialog.html",
              "checkboxset", "btn_opendialog");
    }

    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void createComp() throws Exception {
        super.createComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void destroyComp() throws Exception {
        super.destroyComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void testValue() throws Exception {
        super.testValue();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void testDisabled() throws Exception {
        super.testDisabled();
      }


    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void testRequired() throws Exception {
        super.testRequired();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void testValidator() throws Exception {
        super.testValidator();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void testContextMenu() throws Exception {
      super.testContextMenu();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void testHelp() throws Exception {
        super.testHelp();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void testTitle() throws Exception {
        super.testTitle();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void testWidget() throws Exception {
        super.testWidget();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void testGetNodeBySubId() throws Exception {
        super.testGetNodeBySubId();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void testMessages() throws Exception {
        super.testMessages();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void testEvents() throws Exception {
        super.testEvents();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void testNativeTheme() throws Exception {
        super.testNativeTheme();    setup();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void testMinCss() throws Exception {
        super.testMinCss();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void testLTR() throws Exception {
        super.testLTR();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void testLabel() throws Exception {
        super.testLabel();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, CHECKBOXSETGROUP })
    public void testRootAttributes() throws Exception {
        super.testRootAttributes();
      }



}
