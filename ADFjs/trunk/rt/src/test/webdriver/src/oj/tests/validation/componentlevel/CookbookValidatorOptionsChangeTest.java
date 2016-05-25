package oj.tests.validation.componentlevel;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;


public class CookbookValidatorOptionsChangeTest extends OJetBase {
    public CookbookValidatorOptionsChangeTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    //failing because of bug 21316610 
    @Test(groups = { "cookbook", "validation" })
    public void testAppSetRequiredOption() throws Exception {
        startupTest("demo-validationUsecases-validatorsOption.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Validators Option Changes");
        waitForElementVisible("id=username");
         
        //Move focus on username
        Actions actions = new Actions(getWebDriver());
        WebElement username = getElement("id=username");
        actions.moveToElement(username).click().perform();

        //Verify validator hint (atleast 4 charaters)
        WebElement msg = getMessagingContentNodeBySubId(username, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter at least 4 letters. No numbers are allowed.");

        //Verify pacehiolder on username
        String placeholder = username.getAttribute("placeholder");

        Assert.assertEquals(placeholder, "at least 4 letters", "placeholder value.");

        //Click on change validators on Username button.
        WebElement changePatternBtn = getElement("id=changePattern");
        changePatternBtn.click();

        //Verify that validator hint changes (atleast 3 charaters)
        actions.moveToElement(username).click().perform();

        //Verify validator hint (atleast 3 charaters)
        msg = getMessagingContentNodeBySubId(username, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter at least 3 letters. No numbers are allowed.");
        //Verify placeholder value has changed
        placeholder = username.getAttribute("placeholder");

        Assert.assertEquals(placeholder, "at least 3 letters", "placeholder value.");

        // deferred validation failure indicator is still present
        WebElement status = getElement("id=usernameStatus");
        String style = status.getAttribute("style");
        boolean isDisplayed = style.indexOf("inline") > -1;
        Assert.assertTrue(isDisplayed, "deferred validator indicatotr is still present");

        // field is not decorated with any errro

        WebElement parentElem = getParentElement(username);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is not applied");


    }

    @Test(groups = { "cookbook" , "validation"})
    public void testValidatorOptionChangesMakingValInvalid() throws Exception {
        startupTest("demo-validationUsecases-validatorsOption.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Validators Option Changes");
        waitForElementVisible("id=weight");
        //refresh the page

        getWebDriver().navigate().refresh();
        //Move focus on weight
        Actions actions = new Actions(getWebDriver());
        WebElement weight = getElement("id=weight");
        actions.moveToElement(weight).click().perform();

        //Verify validator hint
        WebElement msg = getMessagingContentNodeBySubId(weight, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value greater than 100");

        //Click on change validators on weight button.
        WebElement changeMinBtn = getElement("id=changeMin");
        changeMinBtn.click();

        //Verify validator hint changes
        actions.moveToElement(weight).click().perform();
        msg = getMessagingContentNodeBySubId(weight, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value greater than 150");

        //No error is flagged on the field.

        WebElement parentElem = getParentElement(weight);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is not applied");

    }

    @Test(groups = { "cookbook", "validation" })
    public void testValidatorOptionChangesForCompWithErr() throws Exception {
        startupTest("demo-validationUsecases-validatorsOption.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Validators Option Changes");
        waitForElementVisible("id=weight");
        //refresh the page

        getWebDriver().navigate().refresh();

        //Add application error on weight
        WebElement addCustomMsgBtn = getElement("id=addCustomMsgBtn");
        addCustomMsgBtn.click();

        //Move focus on weight and verify that err is displayed
        Actions actions = new Actions(getWebDriver());
        WebElement weight = getElement("id=weight");
        actions.moveToElement(weight).click().perform();

        //Verify custom error
        WebElement msg = getMessagingContentNodeBySubId(weight, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Your weight barely qualifies our minimum criteria");
        WebElement parentElem = getParentElement(weight);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "validation error theme is  applied");

        //Click on change validators on weight button.
        WebElement changeMinBtn = getElement("id=changeMin");
        changeMinBtn.click();

        //Verify that now two messages are displayed
       // weight.sendKeys(Keys.TAB);
       

        msg = getMessagingContentNodeBySubId(weight, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Your weight barely qualifies our minimum criteria");
        msg = getMessagingContentNodeBySubId(weight, "oj-messaging-inline-message-summary", 1);
        Assert.assertEquals(msg.getText().trim(), "Number is too low.");
        //validator hint
        actions.moveToElement(weight).click().perform();
        this.waitForMilliseconds(1000);
        msg = getMessagingContentNodeBySubId(weight, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value greater than 150");

        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "validation error theme is  applied");


    }
    
    @Test(groups = { "cookbook" , "validation"})
    public void testValidatorOptionChangeMakingValValid() throws Exception {
        startupTest("demo-validationUsecases-validatorsOption.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Validators Option Changes");
        waitForElementVisible("id=username");
      
       //Set invalid value for username
        Actions actions = new Actions(getWebDriver());
        WebElement username = getElement("id=username");
        username.sendKeys("abc");
        username.sendKeys(Keys.TAB);
        
        //Move focus on username
        actions.moveToElement(username).click().perform();

        //Verify validator err (atleast 4 charaters)
        WebElement msg = getMessagingContentNodeBySubId(username, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Format is incorrect.");

        //Verify that view model value is not updated
        WebElement nameVal = getElement("id=usernameVal");
        Assert.assertEquals(nameVal.getText(), "[Component Value: undefined]");
       
        //Click on change validators on Username button.
        WebElement changePatternBtn = getElement("id=changePattern");
        changePatternBtn.click();

        //Verify that validation errro is cleared 
        actions.moveToElement(username).click().perform();
        WebElement parentElem = getParentElement(username);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr, "validation error theme is not applied");
        
        //Verify that view model value is updated
        Assert.assertEquals(nameVal.getText(), "[Component Value: abc]");
        
     
    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testValidatorOptionChangeWithReqValidationErr() throws Exception {
        startupTest("demo-validationUsecases-validatorsOption.html", null);
        verifyTitle("Incorrect page title;", "Component Validation - Validators Option Changes");
        waitForElementVisible("id=username");

        //refresh the page
        getWebDriver().navigate().refresh();
        
       //Set invalid value for username
        Actions actions = new Actions(getWebDriver());
        WebElement username = getElement("id=username");
        username.sendKeys("abcd");
        username.sendKeys(Keys.TAB);
        //Verify that view model value is updated
        WebElement nameVal = getElement("id=usernameVal");
        Assert.assertEquals(nameVal.getText(), "[Component Value: abcd]");
        
        //Deleted the username value and tab off
        actions.moveToElement(username).click().perform();
        username.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
        username.sendKeys(Keys.TAB);
        
        //Verify required error is displayed
        actions.moveToElement(username).click().perform();
        WebElement msg = getMessagingContentNodeBySubId(username, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value is required.");

        WebElement parentElem = getParentElement(username);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr, "validation error theme is  applied");
        
        //Click on change validators on Username button.
        WebElement changePatternBtn = getElement("id=changePattern");
        changePatternBtn.click();  
        
      //Verify that required error continue to display
      actions.moveToElement(username).click().perform();
       msg = getMessagingContentNodeBySubId(username, "oj-messaging-inline-message-summary", 0);
      Assert.assertEquals(msg.getText().trim(), "Value is required.");

     
       classes = parentElem.getAttribute("class");
       hasValidationErr = classes.indexOf("oj-invalid") > -1;
      Assert.assertTrue(hasValidationErr, "validation error theme is  applied"); 
      
      //verify that view model value is still adcd
      Assert.assertEquals(nameVal.getText(), "[Component Value: abcd]");
     
    }
    
}
