package oj.tests.input.textarea;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import oj.tests.common.TestCompBase;
import oj.tests.input.textarea.TestTextAreaBase;

public class TestTextAreaDialog extends TestTextAreaBase {

    private static final String MYGROUP = "textAreaDialog";

    public TestTextAreaDialog() {
        super("textarea", "input", "Jet ojTextArea Test", "testTextAreaDialog.html",
              "text-input", "btn_opendialog");
    }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void createComp() throws Exception {
        super.createComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void destroyComp() throws Exception {
        super.destroyComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testValue() throws Exception {
        super.testValue();
    }

    // Can't seem to get drag and drop to work at this point...
    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testDragAndDrop() throws Exception {
        super.testDragAndDrop();
    }


    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testDisabled() throws Exception {
        super.testDisabled();
      }


    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testReadOnly() throws Exception {
        super.testReadOnly();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testRequired() throws Exception {
        super.testRequired();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testValidator() throws Exception {
        super.testValidator();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testConverter() throws Exception {
        super.testConverter();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testContextMenu() throws Exception {
      super.testContextMenu();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testHelp() throws Exception {
        super.testHelp();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testTitle() throws Exception {
        super.testTitle();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testPattern() throws Exception {
        super.testPattern();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testWidget() throws Exception {
        super.testWidget();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testUpdates() throws Exception {
        super.testUpdates();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testGetNodeBySubId() throws Exception {
        super.testGetNodeBySubId();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testMessages() throws Exception {
        super.testMessages();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testEvents() throws Exception {
        super.testEvents();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testNativeTheme() throws Exception {
        super.testNativeTheme();    setup();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testMinCss() throws Exception {
        super.testMinCss();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testLTR() throws Exception {
        super.testLTR();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testLabel() throws Exception {
        super.testLabel();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, TEXTAREAGROUP })
    public void testRootAttributes() throws Exception {
        super.testRootAttributes();
      }



}
