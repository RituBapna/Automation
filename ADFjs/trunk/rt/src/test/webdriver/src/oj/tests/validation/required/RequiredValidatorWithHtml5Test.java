package oj.tests.validation.required;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;

public class RequiredValidatorWithHtml5Test extends OJetBase {
    private static final String inputTextDeptidLocator = "deptid";
    private static final String inputTextFirstnameLocator = "firstname";
    private static final String inputTextLastnameLocator = "lastname";
    private static final String inputPasswordLocator = "password";
    private static final String textareaLocator = "generalInfo";
    private static final String inputWithDatalistLocator = "title";
    private static final String singleSelectLocator = "pskill";
    private static final String TOOLTIP_TEXT = "Error: A value is required.\nYou must enter a value.";

    public RequiredValidatorWithHtml5Test() {
        super(new TestConfigBuilder().setContextRoot("validation/required").setAppRoot("Required_RegexpValidatorPrj").
                    setHomePage("testHTML5Page.html").setHomePageTitle("HTML5 element required validator test").build());
    }

    @Test(groups = { "validation" })
    public void testRequiredInputText() throws Exception {
        doTestTextComponent(inputTextFirstnameLocator, inputTextLastnameLocator);
    }

    @Test(groups = { "validation" })
    public void testRequiredTextarea() throws Exception {
        doTestTextComponent(textareaLocator, inputTextDeptidLocator);
    }

    @Test (groups = { "validation" })
    public void testRequiredInputPassword() throws Exception {
        doTestTextComponent(inputPasswordLocator, inputTextLastnameLocator);
    }
    
    private void doTestTextComponent(String locator, String clickLocator) {
        startupTest();
        waitForElementVisible(locator);

        sendKeys(locator, "some Value");
        // click into other field to trigger input evaluation
        click(clickLocator);
        String classValue = getAttribute(locator + "@class");
        if (classValue != null) {
            verifyFalse("Unexpectedly found CSS style 'oj-invalid';", classValue.contains("oj-invalid"));
        }
        clear(locator);

        classValue = getAttribute(locator + "@class");
        verifyNotNull("Could not get attribute @class", classValue);
        verifyTrue("Could not find CSS style 'oj-invalid';", classValue.contains("oj-invalid"));

        String tooltipText = getTooltip(locator);
        verifyNotNull("No tooltip found;", tooltipText);
        verifyEquals("Incorrect tooltip found;", TOOLTIP_TEXT, tooltipText);
        skipBrowserRestart();
    }

    @Test (groups = { "validation" })
    public void testRequiredInputWithDatalist() throws Exception {
        startupTest();
        waitForElementVisible(inputWithDatalistLocator);

        click(inputWithDatalistLocator);
        sendKeys(inputWithDatalistLocator, "Mr.");

        // click into other field to trigger input evaluation
        click(inputTextLastnameLocator);
        String classValue = getAttribute(inputWithDatalistLocator + "@class");
        if (classValue != null) {
            verifyFalse("Unexpectedly found CSS style 'oj-invalid';", classValue.contains("oj-invalid"));
        }
        clear(inputWithDatalistLocator);

        // click into other field to trigger input evaluation
        click(inputTextLastnameLocator);

        classValue = getAttribute(inputWithDatalistLocator + "@class");
        verifyNotNull("Could not get attribute @class", classValue);
        verifyTrue("Could not find CSS style 'oj-invalid';", classValue.contains("oj-invalid"));

        String tooltipText = getTooltip(inputWithDatalistLocator);
        verifyNotNull("No tooltip found;", tooltipText);
        System.out.println("Tooltip: " + tooltipText);
        verifyEquals("Incorrect tooltip found;", TOOLTIP_TEXT, tooltipText);
        skipBrowserRestart();
    }

    @Test (groups = { "validation" })
    public void testRequiredSingleSelect() throws Exception {
        startupTest();
        waitForElementVisible(singleSelectLocator);

        // select 2nd item "DB Administrator"
        select(singleSelectLocator, "index=1");

        // click into other field to trigger input evaluation
        click(inputTextLastnameLocator);

        String classValue = getAttribute(singleSelectLocator + "@class");
        if (classValue != null) {
            verifyFalse("Unexpectedly found CSS style 'oj-invalid';", classValue.contains("oj-invalid"));
        }
        // select 1st item "Select primary skill..."
        select(singleSelectLocator, "index=0");

        // click into other field to trigger input evaluation
        click(inputTextLastnameLocator);

        classValue = getAttribute(singleSelectLocator + "@class");
        verifyNotNull("Could not get attribute @class", classValue);
        verifyTrue("Could not find CSS style 'oj-invalid';", classValue.contains("oj-invalid"));

        String tooltipText = getTooltip(singleSelectLocator);
        verifyNotNull("No tooltip found;", tooltipText);
        System.out.println("Tooltip: " + tooltipText);
        verifyEquals("Incorrect tooltip found;", TOOLTIP_TEXT, tooltipText);
        skipBrowserRestart();
    }
}
