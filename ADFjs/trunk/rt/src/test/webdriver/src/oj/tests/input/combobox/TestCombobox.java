package oj.tests.input.combobox;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import oj.tests.common.TestCompBase;
import oj.tests.table.table.TestTableBase;

public class TestCombobox extends TestComboboxBase {

    private static final String MYGROUP = "combobox";

    public TestCombobox() {
        super("combobox", "input", "Jet ojCombobox Test", "testCombobox.html", "combobox");
    }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void createComp() throws Exception {
        super.createComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void destroyComp() throws Exception {
        super.destroyComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testValue() throws Exception {
        super.testValue();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testDoubleClickCopyPaste() throws Exception {
        super.testValue();
    }


    // Can't seem to get drag and drop to work at this point...
    public void testDragAndDrop() throws Exception {
        super.testDragAndDrop();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testDisabled() throws Exception {
        super.testDisabled();
      }


  // ReadOnly not supported on Comboboxes or Select see Bug 20731253
  // public void testReadOnly() throws Exception {

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testRequired() throws Exception {
        super.testRequired();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testValidator() throws Exception {
        super.testValidator();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testConverter() throws Exception {
        super.testConverter();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testContextMenu() throws Exception {
      super.testContextMenu();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testHelp() throws Exception {
        super.testHelp();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testTitle() throws Exception {
        super.testTitle();
      }

      // Pattern not supported for combos

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testWidget() throws Exception {
        super.testWidget();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testUpdates() throws Exception {
        super.testUpdates();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testGetNodeBySubId() throws Exception {
        super.testGetNodeBySubId();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testMessages() throws Exception {
        super.testMessages();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testEvents() throws Exception {
        super.testEvents();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testNativeTheme() throws Exception {
        super.testNativeTheme();    setup();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testMinCss() throws Exception {
        super.testMinCss();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testLTR() throws Exception {
        super.testLTR();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testLabel() throws Exception {
        super.testLabel();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, COMBOBOXGROUP })
    public void testRootAttributes() throws Exception {
        super.testRootAttributes();
      }



}
