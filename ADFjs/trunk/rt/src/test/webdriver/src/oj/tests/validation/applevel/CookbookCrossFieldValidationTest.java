package oj.tests.validation.applevel;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;



public class CookbookCrossFieldValidationTest extends OJetBase {
    public CookbookCrossFieldValidationTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "validation" })
    public void testInitialComponentsState() throws Exception {
        startupTest("demo-appLevelValidation-crossFieldValidation.html", null);
        verifyTitle("Incorrect page title;", "App Level Validation - Cross-Field Validation");
        waitForElementVisible("id=radioSetId");

        // Make sure that email field does not have validation error.
        WebElement radiobtn = getElement("id=radioSetId");
        WebElement parentElem = getParentElement(radiobtn);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "No inital validation error on required field");

        // Make sure that phone number field is disabled.
        Object isDisabled = evalJavascript("return $('#telNum').ojInputText('option', 'disabled')");

        Assert.assertEquals(isDisabled.toString(), "true", "Initally phone number field is disabled");

        // make sure that Create button is enabled
        isDisabled = evalJavascript("return $('#create').ojButton('option', 'disabled')");

        Assert.assertEquals(isDisabled.toString(), "false", "Initally Create button is enabled");

    }


    @Test(groups = { "cookbook", "validation" }, dependsOnMethods = { "testInitialComponentsState" })
    public void testEnablingDisablingOfSubmitButtonBasedOnValidationErr() throws Exception {
        startupTest("demo-appLevelValidation-crossFieldValidation.html", null);
        verifyTitle("Incorrect page title;", "App Level Validation - Cross-Field Validation");
        waitForElementVisible("id=radioSetId");

        // Click on Create button and note that error is displayed fror email input field
        WebElement createbtn = getElement("id=create");
        createbtn.click();

        WebElement email = getElement("id=emailId");
        WebElement parentElem = getParentElement(email);
        String classes = parentElem.getAttribute("class");
        boolean hasError = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasError, "validation error should be present");

        //check the validation error msg
        WebElement msg = getMessagingContentNodeBySubId(email, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Email Address Required");
        msg = getMessagingContentNodeBySubId(email, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "You must enter a value for field Email Address");

        //Make sure that create button is disabled
        Object isDisabled = evalJavascript("return $('#create').ojButton('option', 'disabled')");
        Assert.assertEquals(isDisabled.toString(), "true", "Create button is disabled on  validation err");

        //enter email value and tab out.
        //Verify - error cleared and Create buittom enabled
        email.sendKeys("foo@test.com");
        email.sendKeys(Keys.TAB);
        parentElem = getParentElement(email);
        classes = parentElem.getAttribute("class");
        hasError = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasError, "validation error is cleared after value is added");
        isDisabled = evalJavascript("return $('#create').ojButton('option', 'disabled')");
        Assert.assertEquals(isDisabled.toString(), "false", "Create button is enabled after validation err is cleared");

        //Click on Create button now and note no validation error.
        createbtn.click();
        classes = parentElem.getAttribute("class");
        hasError = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasError, "No validation error on submit after value entry");


    }

    @Test(groups = { "cookbook", "validation" }, dependsOnMethods = { "testEnablingDisablingOfSubmitButtonBasedOnValidationErr" })
    public void testCrossFieldValidation() throws Exception {
        startupTest("demo-appLevelValidation-crossFieldValidation.html", null);
        verifyTitle("Incorrect page title;", "App Level Validation - Cross-Field Validation");
        waitForElementVisible("id=radioSetId");

        //switch to Phone radio button
        List<WebElement> radiosetInputs =
            findElements("{\"element\":\"#radioSetId\",\"subId\":\"oj-radioset-inputs\"}");
        // WebElement phoneRadioBtn = getElement("id=opt2");
        radiosetInputs.get(1).click();
        waitForMilliseconds(1000);
        //Make sure that phone number input field is enabled
        Object isDisabled = evalJavascript("return $('#telNum').ojInputText('option', 'disabled')");
        Assert.assertEquals(isDisabled.toString(), "false",
                            "phone number field is enabled when phone radio option is selected");

        // Click on Create button and note that error is displayed fror phone number input field
        WebElement createbtn = getElement("id=create");
        createbtn.click();

        WebElement phonenum = getElement("id=telNum");
        WebElement parentElem = getParentElement(phonenum);
        String classes = parentElem.getAttribute("class");
        boolean hasError = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasError, "validation error should be present");

        //check the validation error msg
        WebElement msg = getMessagingContentNodeBySubId(phonenum, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Phone Number Required");
        msg = getMessagingContentNodeBySubId(phonenum, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "You must enter a value for field Phone Number");

        //Make sure that create button is disabled
        isDisabled = evalJavascript("return $('#create').ojButton('option', 'disabled')");
        Assert.assertEquals(isDisabled.toString(), "true", "Create button is disabled on  validation err");

        //TAB away
        phonenum.sendKeys(Keys.TAB);
        //switch to email radio option
        // WebElement emailRadioOpt = getElement("id=opt1");
        // emailRadioOpt.click();
        radiosetInputs.get(0).click();
        waitForMilliseconds(5000);
        //Verify err is cleared from phone number field
        phonenum = getElement("id=telNum");
        classes = parentElem.getAttribute("class");
        System.out.println("*****#### classes: " + classes);
        hasError = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasError, "validation error is cleared");

        //verify that create button is enabled again
        isDisabled = evalJavascript("return $('#create').ojButton('option', 'disabled')");
        Assert.assertEquals(isDisabled.toString(), "false", "Create button is enable after validation err is cleared");
    }


    public WebElement getParentElement(WebElement elem) {
        WebElement parentElem = null;
        if (elem != null) {
            parentElem = elem.findElement(By.xpath(".."));
        }
        return parentElem;
    }

    public WebElement getButton(WebElement parent, String label) {
        List<WebElement> buttons = parent.findElements(By.tagName("button"));
        System.out.println(" Button size:  " + buttons.size());
        WebElement button = null;
        for (WebElement btn : buttons) {
            WebElement span = btn.findElement(By.tagName("span"));
            String btnLabel = span.getText();
            System.out.println("#### Button Label : *" + btnLabel + "*");
            if (btnLabel.trim() == label) {
                button = btn;
            }

        }
        System.out.println("**** button: " + button.getAttribute("disabled"));
        return button;
    }
}
