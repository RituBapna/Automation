package oj.tests.converters.number;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookPercentageConverterTest extends OJetBase {
    private static final String TEST_PAGE = "demo-converters-builtInConvertersNumber.html";
    private static final String TEST_PAGE_TITLE = "Converters - Built-in Number Converter";
    private static final String INPUT1_ID = "percent11";
    private static final String INPUT2_ID = "percent10";
   

    public CookbookPercentageConverterTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }


    @Test(groups = { "cookbook", "converter" })
    public void testNumberConverter_withValue1() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input1 =
        getElement("{\"element\":\"#" + INPUT1_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
       
        WebElement input2 = getElement("id="+ INPUT2_ID);
      
        
        input1.sendKeys("45.0%");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
       
        //Verify the value in every field 
        String value = input1.getAttribute("value");      
         Assert.assertEquals("45%", value);
         
        value = input2.getAttribute("value");      
        Assert.assertEquals("45%", value);
       
        
    }
    
    @Test(groups = { "cookbook", "converter" })
    public void testNumberConverter_withValue2() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input1 =
        getElement("{\"element\":\"#" + INPUT1_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
      
        WebElement input2 = getElement("id="+ INPUT2_ID);
        
        input1.sendKeys("60.5666");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
       
        //Verify the value in every field 
        String value = input1.getAttribute("value");      
         Assert.assertEquals("60.57%", value);
         
        value = input2.getAttribute("value");      
        Assert.assertEquals("60.57%", value);
        
    }
    
    
    @Test(groups = { "cookbook", "converter" })
    public void testNumberConverter_incorrectValue() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input1 =
        getElement("{\"element\":\"#" + INPUT1_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
       
        WebElement input2 = getElement("id="+ INPUT2_ID);
        
        input1.sendKeys("x");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
       
       //verify the error
       WebElement parentElem = getParentElement(getParentElement(input1));
       String classes = parentElem.getAttribute("class");
       boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
       Assert.assertTrue(hasValidationErr);
       WebElement msg = getMessagingContentNodeBySubId(input1, "oj-messaging-inline-message-summary", 0);
       Assert.assertEquals(msg.getText().trim(), "'x' is not in the expected percent format.");
       msg = getMessagingContentNodeBySubId(input1, "oj-messaging-inline-message-detail", 0);
       Assert.assertEquals(msg.getText().trim(), "Enter a value in the same format as this example: '1234598.76%'");

       //Change the value to correct value and move focus to next input date field

       actions.moveToElement(input1).click().perform(); // move focus to input date field
       input1.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
       input1.sendKeys("-7"); //enter the new value
       actions.moveToElement(input2).click().perform(); // move focus to next input date field
       //Verify that error is removed
       classes = parentElem.getAttribute("class");
       hasValidationErr = classes.indexOf("oj-invalid") > -1;
       Assert.assertFalse(hasValidationErr); //no decoration
       msg = getMessagingContentNodeBySubId(input1, "oj-messaging-inline-message-summary", 0);
       Assert.assertNull(msg, "No error message window is present");
        
        //Verify the value in every field 
        String value = input1.getAttribute("value");      
         Assert.assertEquals("-7.00%", value);
         
        value = input2.getAttribute("value");      
        Assert.assertEquals("-7.00%", value);
        
        
        
    }
}

