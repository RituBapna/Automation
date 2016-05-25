package oj.tests.input.datetime;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import oj.tests.common.TestCompBase;
import oj.tests.input.datetime.TestInputDateTimeBase;

public class TestInputDateTimeDialog extends TestInputDateTimeBase {

    private static final String MYGROUP = "inputDateTimeDialog";

    public TestInputDateTimeDialog() {
        super("datetime", "input", "Jet ojInputDateTime Test", "testInputDateTimeDialog.html",
              "text-input", "btn_opendialog");
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void createComp() throws Exception {
        super.createComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void destroyComp() throws Exception {
        super.destroyComp();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void testValue() throws Exception {
        super.testValue();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void testPicker() throws Exception {
        super.testPicker();
    }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void testReadOnly() throws Exception {
        super.testReadOnly();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void testRequired() throws Exception {
        super.testRequired();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void testValidator() throws Exception {
        super.testValidator();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void testHelp() throws Exception {
        super.testHelp();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void testTitle() throws Exception {
        super.testTitle();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void testWidget() throws Exception {
        super.testWidget();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void testGetNodeBySubId() throws Exception {
        super.testGetNodeBySubId();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void testMessages() throws Exception {
        super.testMessages();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void testEvents() throws Exception {
        super.testEvents();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void testNativeTheme() throws Exception {
        super.testNativeTheme();    setup();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void testMinCss() throws Exception {
        super.testMinCss();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void testLTR() throws Exception {
        super.testLTR();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void testLabel() throws Exception {
        super.testLabel();
      }

    @Test(groups = { MYGROUP, INPUTGROUP, INPUTDATETIMEGROUP })
    public void testRootAttributes() throws Exception {
        super.testRootAttributes();
      }



}
