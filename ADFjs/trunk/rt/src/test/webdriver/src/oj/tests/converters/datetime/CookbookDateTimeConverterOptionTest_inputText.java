package oj.tests.converters.datetime;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookDateTimeConverterOptionTest_inputText extends OJetBase {
    private static final String TEST_PAGE = "demo-converters-builtInConvertersDatetime.html";
    private static final String TEST_PAGE_TITLE = "Converters - Built-in DateTime Converter";
    private static final String DATE1_ID = "datetime1";
    private static final String DATE2_ID = "text20";

    public CookbookDateTimeConverterOptionTest_inputText() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }


    @Test(groups = { "cookbook", "converter" })
    public void testDateConverter() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id=" + DATE2_ID );
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        input.sendKeys("xx");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(input);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "'xx' is not in the expected date and time format.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value in the same format as this example: '11/29/98 3:45 PM'");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input.sendKeys("7-23-15 11"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");

        //Verify the date
        String value = input.getAttribute("value");
        
         Assert.assertEquals("7/23/15 11:00 AM", value);
    }
    
    @Test(groups = { "cookbook", "converter" })
    public void testInvalidMonth() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id=" + DATE2_ID );
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        input.sendKeys("13/30/00 10 pm");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(input);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value '30' is out of range for the 'month'.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value between '1' and '12'.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input.sendKeys("12/30/00 10 pm"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");
        //Verify the date
        String value = input.getAttribute("value");
        
         Assert.assertEquals("12/30/00 10:00 PM", value);
        
    }
    
    @Test(groups = { "cookbook", "converter" })
    public void testInvalidDay() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id=" + DATE2_ID );
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        input.sendKeys("02/30/2016 9:00");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(input);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value '30' is out of range for the 'day'.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value between '1' and '29'.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input.sendKeys("02/29/2016 9:00"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");
        //Verify the date
        String value = input.getAttribute("value");
        
         Assert.assertEquals("2/29/16 9:00 AM", value);

    }
    
  
    @Test(groups = { "cookbook", "converter" })
    public void testInvalidHour() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id=" + DATE2_ID );
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        input.sendKeys("02/29/2016 14:00");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(input);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value '14' is out of range for the 'hour'.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value between '1' and '12'.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input.sendKeys("02/29/2016 12:00 PM"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");
        //Verify the date
        String value = input.getAttribute("value");
        
         Assert.assertEquals("2/29/16 12:00 PM", value);

    }
    
  
    
    @Test(groups = { "cookbook", "converter" })
    public void testInvalidMinute() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id=" + DATE2_ID );
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        input.sendKeys("02/29/2016 09:61");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(input);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value '61' is out of range for the 'minute'.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value between '0' and '59'.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input.sendKeys("02/29/2016 09:59 PM"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");
        //Verify the date
        String value = input.getAttribute("value");
        
         Assert.assertEquals("2/29/16 9:59 PM", value);

    }
    
    @Test(groups = { "cookbook", "converter" })
    public void testLenientParsing1() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id=" + DATE2_ID );
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        input.sendKeys("2016xx20xx12");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        //verify the date 
       // String value = evalJavascript("return $('#" + DATE1_ID+"').ojInputDate('option', 'value')");
       String value = input.getAttribute("value");
      
        Assert.assertEquals("12/20/16 12:00 AM", value);

    }
    
    @Test(groups = { "cookbook", "converter" })
    public void testLenientParsing2() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id=" + DATE2_ID );
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        input.sendKeys("2016, 30 Nov 10?:45 pm");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        //verify the date 
      //  String value = evalJavascript("return $('#" + DATE1_ID+"').ojInputDate('option', 'value')");
        String value = input.getAttribute("value");
        Assert.assertEquals("11/30/16 10:45 PM", value);

    }
}


