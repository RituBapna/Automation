package oj.tests.input.number;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import oj.tests.common.TestCompBase;

public class TestInputNumberPopup extends TestInputNumberBase {

    protected static final String MYGROUP = "inputNumberPopup";

    public TestInputNumberPopup() {
        super("number", "input", "Jet ojInputNumber Test", "testInputNumberPopup.html",
              "inputnumber-id", "btn_openpopup");
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void createComp() throws Exception {
        super.createComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void destroyComp() throws Exception {
        super.destroyComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testValue() throws Exception {
        super.testValue();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testSpinner() throws Exception {
        super.testSpinner();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testDisabled() throws Exception {
        super.testDisabled();
      }


    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testReadOnly() throws Exception {
        super.testReadOnly();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testRequired() throws Exception {
        super.testRequired();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testValidator() throws Exception {
        super.testValidator();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testConverter() throws Exception {
        super.testConverter();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testContextMenu() throws Exception {
      super.testContextMenu();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testHelp() throws Exception {
        super.testHelp();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testTitle() throws Exception {
        super.testTitle();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testWidget() throws Exception {
        super.testWidget();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testUpdates() throws Exception {
        super.testUpdates();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testGetNodeBySubId() throws Exception {
        super.testGetNodeBySubId();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testMessages() throws Exception {
        super.testMessages();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testEvents() throws Exception {
        super.testEvents();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testNativeTheme() throws Exception {
        super.testNativeTheme();    setup();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testMinCss() throws Exception {
        super.testMinCss();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testLTR() throws Exception {
        super.testLTR();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testLabel() throws Exception {
        super.testLabel();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTNUMBERGROUP })
    public void testRootAttributes() throws Exception {
        super.testRootAttributes();
      }

}
