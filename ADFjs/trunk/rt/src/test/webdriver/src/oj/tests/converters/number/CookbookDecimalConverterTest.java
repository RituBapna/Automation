package oj.tests.converters.number;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookDecimalConverterTest extends OJetBase {
    private static final String TEST_PAGE = "demo-converters-builtInConvertersNumber.html";
    private static final String TEST_PAGE_TITLE = "Converters - Built-in Number Converter";
    private static final String INPUT1_ID = "decimal10";
    private static final String INPUT2_ID = "decimal11";
    private static final String INPUT3_ID = "decimal12";
    private static final String INPUT4_ID = "decimal13";
    private static final String INPUT5_ID = "decimal14";
    private static final String INPUT6_ID = "decimal15";

    public CookbookDecimalConverterTest() {
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
        WebElement input2 =
        getElement("{\"element\":\"#" + INPUT2_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
        WebElement input3 = getElement("id="+ INPUT3_ID);
        WebElement input4 = getElement("id="+ INPUT4_ID);
        WebElement input5 = getElement("id="+ INPUT5_ID);
        WebElement input6 = getElement("id="+ INPUT6_ID);
        
        input1.sendKeys("12345");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
       
        //Verify the value in every field 
        String value = input1.getAttribute("value");      
         Assert.assertEquals("12,345", value);
         
        value = input2.getAttribute("value");      
        Assert.assertEquals("12345.0", value);
        
        value = input3.getAttribute("value");      
        Assert.assertEquals("12345.0", value);
        
        value = input4.getAttribute("value");      
        Assert.assertEquals("12.345K", value);
        
        value = input5.getAttribute("value");      
        Assert.assertEquals("12.3K", value);
        
        value = input6.getAttribute("value");      
        Assert.assertEquals("12.345 thousand", value);
        
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
        WebElement input2 =
        getElement("{\"element\":\"#" + INPUT2_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
        WebElement input3 = getElement("id="+ INPUT3_ID);
        WebElement input4 = getElement("id="+ INPUT4_ID);
        WebElement input5 = getElement("id="+ INPUT5_ID);
        WebElement input6 = getElement("id="+ INPUT6_ID);
        
        input1.sendKeys("8,000,000");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
       
        //Verify the value in every field 
        String value = input1.getAttribute("value");      
         Assert.assertEquals("8,000,000", value);
         
        value = input2.getAttribute("value");      
        Assert.assertEquals("8000000.0", value);
        
        value = input3.getAttribute("value");      
        Assert.assertEquals("8000000.0", value);
        
        value = input4.getAttribute("value");      
        Assert.assertEquals("8M", value);
        
        value = input5.getAttribute("value");      
        Assert.assertEquals("08.0M", value);
        
        value = input6.getAttribute("value");      
        Assert.assertEquals("8 million", value);
        
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
        WebElement input2 =
        getElement("{\"element\":\"#" + INPUT2_ID +
                   "\",\"index\":1,\"subId\":\"oj-inputnumber-input\"}");
        WebElement input3 = getElement("id="+ INPUT3_ID);
        WebElement input4 = getElement("id="+ INPUT4_ID);
        WebElement input5 = getElement("id="+ INPUT5_ID);
        WebElement input6 = getElement("id="+ INPUT6_ID);
        
        input1.sendKeys("x");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
       
       //verify the error
       WebElement parentElem = getParentElement(getParentElement(input1));
       String classes = parentElem.getAttribute("class");
       boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
       Assert.assertTrue(hasValidationErr);
       WebElement msg = getMessagingContentNodeBySubId(input1, "oj-messaging-inline-message-summary", 0);
       Assert.assertEquals(msg.getText().trim(), "'x' is not in the expected number format.");
       msg = getMessagingContentNodeBySubId(input1, "oj-messaging-inline-message-detail", 0);
       Assert.assertEquals(msg.getText().trim(), "Enter a value in the same format as this example: '12,345.988'");

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
         Assert.assertEquals("-7", value);
         
        value = input2.getAttribute("value");      
        Assert.assertEquals("-07.0", value);
        
        value = input3.getAttribute("value");      
        Assert.assertEquals("-07.0", value);
        
        value = input4.getAttribute("value");      
        Assert.assertEquals("-7", value);
        
        value = input5.getAttribute("value");      
        Assert.assertEquals("-07.0", value);
        
        value = input6.getAttribute("value");      
        Assert.assertEquals("-7", value);
        
    }
}
