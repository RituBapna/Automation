package oj.tests.input.text;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import oj.tests.common.TestCompBase;
import oj.tests.table.table.TestTableBase;

public class TestInputTextPopup extends TestInputTextBase {

    protected static final String MYGROUP = "inputTextPopup";

    public TestInputTextPopup() {
        super("text", "input", "Jet ojInputText Test", "testInputTextPopup.html",
              "text-input", "btn_openpopup");
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void createComp() throws Exception {
        super.createComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void destroyComp() throws Exception {
        super.destroyComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testValue() throws Exception {
        super.testValue();
    }

    // Can't seem to get drag and drop to work at this point...
    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testDragAndDrop() throws Exception {
        super.testDragAndDrop();
    }


    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testDisabled() throws Exception {
        super.testDisabled();
      }


    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testReadOnly() throws Exception {
        super.testReadOnly();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testRequired() throws Exception {
        super.testRequired();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testValidator() throws Exception {
        super.testValidator();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testConverter() throws Exception {
        super.testConverter();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testContextMenu() throws Exception {
      super.testContextMenu();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testHelp() throws Exception {
        super.testHelp();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testTitle() throws Exception {
        super.testTitle();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testPattern() throws Exception {
        super.testPattern();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testWidget() throws Exception {
        super.testWidget();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testUpdates() throws Exception {
        super.testUpdates();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testGetNodeBySubId() throws Exception {
        super.testGetNodeBySubId();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testMessages() throws Exception {
        super.testMessages();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testEvents() throws Exception {
        super.testEvents();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testNativeTheme() throws Exception {
        super.testNativeTheme();    setup();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testMinCss() throws Exception {
        super.testMinCss();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testLTR() throws Exception {
        super.testLTR();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testLabel() throws Exception {
        super.testLabel();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTTEXTGROUP })
    public void testRootAttributes() throws Exception {
        super.testRootAttributes();
      }


}
