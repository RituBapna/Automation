package oj.tests.converters.datetime;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookTimeConverterOptionTest_time extends OJetBase {
    private static final String TEST_PAGE = "demo-converters-builtInConvertersDatetime.html";
    private static final String TEST_PAGE_TITLE = "Converters - Built-in DateTime Converter";
    private static final String DATE1_ID = "time3";
    private static final String DATE2_ID = "time1";

    public CookbookTimeConverterOptionTest_time() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }


    @Test(groups = { "cookbook", "converter" })
    public void testTimeConverter() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("{\"element\":\"#" + DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-time-input\"}");
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-time-input\"}");
        Actions actions = new Actions(getWebDriver());
        actions.sendKeys(input, Keys.ESCAPE);
        actions.sendKeys(input, "xx");
       
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        actions.sendKeys(input2, Keys.ESCAPE);
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(getParentElement(input));
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "'xx' is not in the expected time format.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value in the same format as this example: '03:45:31 PM'");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        actions.sendKeys(input, Keys.ESCAPE);
        actions.sendKeys(input, Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        actions.sendKeys(input, "7:15 pm"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        actions.sendKeys(input2, Keys.ESCAPE);
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");

        //Verify the date
        String value = input.getAttribute("value");
        
         Assert.assertEquals("07:15:00 PM", value);
    }
    
    @Test(groups = { "cookbook", "converter" })
    public void testInvalidHour() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("{\"element\":\"#" + DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-time-input\"}");
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-time-input\"}");
        Actions actions = new Actions(getWebDriver());
        actions.sendKeys(input, Keys.ESCAPE);
        actions.sendKeys(input, "14:00:1");
       
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        actions.sendKeys(input2, Keys.ESCAPE);
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(getParentElement(input));
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value '14' is out of range for the 'hour'.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value between '1' and '12'.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        actions.sendKeys(input, Keys.ESCAPE);
        actions.sendKeys(input, Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        actions.sendKeys(input, "12 PM"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        actions.sendKeys(input2, Keys.ESCAPE);
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");
        //Verify the date
        String value = input.getAttribute("value");
        
         Assert.assertEquals("12:00:00 PM", value);

    }
    
    
    
    @Test(groups = { "cookbook", "converter" })
    public void testInvalidMinute() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("{\"element\":\"#" + DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-time-input\"}");
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-time-input\"}");
        Actions actions = new Actions(getWebDriver());
        actions.sendKeys(input, Keys.ESCAPE);
        actions.sendKeys(input, "9:61:5");
      
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        actions.sendKeys(input2, Keys.ESCAPE);
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(getParentElement(input));
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value '61' is out of range for the 'minute'.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value between '0' and '59'.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        actions.sendKeys(input, Keys.ESCAPE);
        actions.sendKeys(input, Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        actions.sendKeys(input, "9:59:5 PM"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        actions.sendKeys(input2, Keys.ESCAPE);
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");
        //Verify the date
        String value = input.getAttribute("value");
        
         Assert.assertEquals("09:59:05 PM", value);

    }
    
    @Test(groups = { "cookbook", "converter" })
    public void testInvalidSecond() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("{\"element\":\"#" + DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-time-input\"}");
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-time-input\"}");
        Actions actions = new Actions(getWebDriver());
        actions.sendKeys(input, Keys.ESCAPE);
        actions.sendKeys(input, "9:59:65");
     
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        actions.sendKeys(input2, Keys.ESCAPE);
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(getParentElement(input));
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value '65' is out of range for the 'second'.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value between '0' and '59'.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        actions.sendKeys(input, Keys.ESCAPE);
        actions.sendKeys(input, Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        actions.sendKeys(input, "09:59:59"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        actions.sendKeys(input2, Keys.ESCAPE);
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");
        //Verify the date
        String value = input.getAttribute("value");
        
         Assert.assertEquals("09:59:59 AM", value);

    }
    
    @Test(groups = { "cookbook", "converter" })
    public void testLenientParsing1() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("{\"element\":\"#" + DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-time-input\"}");
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-time-input\"}");
        Actions actions = new Actions(getWebDriver());
        actions.sendKeys(input, Keys.ESCAPE);
        actions.sendKeys(input, "2xx4xx6xxam");
    
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        actions.sendKeys(input2, Keys.ESCAPE);
        //verify the date 
       // String value = evalJavascript("return $('#" + DATE1_ID+"').ojInputDate('option', 'value')");
       String value = input.getAttribute("value");
      
        Assert.assertEquals("02:04:06 AM", value);

    }
    
  
}
