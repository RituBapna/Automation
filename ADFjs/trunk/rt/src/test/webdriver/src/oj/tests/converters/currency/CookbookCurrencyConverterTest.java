package oj.tests.converters.currency;

import oj.tests.converters.number.CookbookPercentageConverterTest;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookCurrencyConverterTest extends OJetBase {
    private static final String TEST_PAGE = "demo-converters-builtInConvertersNumber.html";
    private static final String TEST_PAGE_TITLE = "Converters - Built-in Number Converter";
    private static final String INPUT11_ID = "currency11"; //inputText
    private static final String INPUT12_ID = "currency12"; //inputNumber
    private static final String INPUT13_ID = "currency13"; //InputNumber
    private static final String INPUT14_ID = "currency14"; //inputtext
    private static final String EURO = "\u20ac";

    public CookbookCurrencyConverterTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }


    @Test(groups = { "cookbook", "converter" })
    public void testNumberConverter_withValue1() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT11_ID);

        WebElement input1 = getElement("id="+ INPUT11_ID);  
        WebElement input2 =  getElement("{\"element\":\"#" + INPUT12_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
        WebElement input3 =  getElement("{\"element\":\"#" + INPUT13_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
        WebElement input4 = getElement("id="+ INPUT14_ID);
      
        
        input1.sendKeys(EURO + "12345");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
       
        //Verify the value in every field 
        String value = input1.getAttribute("value");      
         Assert.assertEquals(EURO + "12,345.00", value);
         
        value = input2.getAttribute("value");      
        Assert.assertEquals(EURO + "12,345.00", value);
        
        value = input3.getAttribute("value");      
        Assert.assertEquals("USD 12,345.00", value);
        
        value = input4.getAttribute("value");      
        Assert.assertEquals("USD 12,345.00", value);
       
        
    }
    
    @Test(groups = { "cookbook", "converter" })
    public void testNumberConverter_withValue2() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT11_ID);

        WebElement input1 = getElement("id="+ INPUT11_ID);  
        WebElement input2 =  getElement("{\"element\":\"#" + INPUT12_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
        WebElement input3 =  getElement("{\"element\":\"#" + INPUT13_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
        WebElement input4 = getElement("id="+ INPUT14_ID);
        
        
        input1.sendKeys("6,000.5666");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
       
        //Verify the value in every field 
        String value = input1.getAttribute("value");      
         Assert.assertEquals(EURO + "6,000.57", value);
         
        value = input2.getAttribute("value");      
        Assert.assertEquals(EURO + "6,000.57", value);
        
        
        value = input3.getAttribute("value");      
        Assert.assertEquals("USD 6,000.57", value);
        
        value = input4.getAttribute("value");      
        Assert.assertEquals("USD 6,000.57", value);
        
    }
    
    
    @Test(groups = { "cookbook", "converter" })
    public void testNumberConverter_incorrectValue() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT11_ID);

        WebElement input1 = getElement("id="+ INPUT11_ID);  
        WebElement input2 =  getElement("{\"element\":\"#" + INPUT12_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
        WebElement input3 =  getElement("{\"element\":\"#" + INPUT13_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
        WebElement input4 = getElement("id="+ INPUT14_ID);
        
        input1.sendKeys("x");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
       
       //verify the error
       WebElement parentElem = getParentElement(input1);
       String classes = parentElem.getAttribute("class");
       boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
       Assert.assertTrue(hasValidationErr);
       WebElement msg = getMessagingContentNodeBySubId(input1, "oj-messaging-inline-message-summary", 0);
       Assert.assertEquals(msg.getText().trim(), "'x' is not in the expected currency format.");
       msg = getMessagingContentNodeBySubId(input1, "oj-messaging-inline-message-detail", 0);
       Assert.assertEquals(msg.getText().trim(), "Enter a value in the same format as this example: '" + EURO + "12,345.99'");

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
         Assert.assertEquals("-" + EURO + "7.00", value);
         
        value = input2.getAttribute("value");      
        Assert.assertEquals("-" + EURO + "7.00", value);
        
        value = input3.getAttribute("value");      
        Assert.assertEquals("-USD 7.00", value);
        
        value = input4.getAttribute("value");      
        Assert.assertEquals("-USD 7.00", value);
        
    }
}


