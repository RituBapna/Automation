package oj.tests.converters.datetime;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookDefaultDateTimeConverterTest extends OJetBase {
    private static final String TEST_PAGE = "demo-converters-builtInConvertersDatetime.html";
    private static final String TEST_PAGE_TITLE = "Converters - Built-in DateTime Converter";
    private static final String DATE1_ID = "datetime1";
    private static final String DATE2_ID = "datetime2";

    public CookbookDefaultDateTimeConverterTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }


    @Test(groups = { "cookbook", "converter" })
    public void testDefaultDateConverter() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("{\"element\":\"#" + DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        Actions actions = new Actions(getWebDriver());
        actions.sendKeys(input, Keys.ESCAPE);
        actions.sendKeys(input, "xx");
      
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(getParentElement(input));
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "'xx' is not in the expected date and time format.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value in the same format as this example: '11/29/98 03:45 PM'");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        actions.sendKeys(input, Keys.ESCAPE);
        actions.sendKeys(input, Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        actions.sendKeys(input, "7-22-2015"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        actions.sendKeys(input2, Keys.ESCAPE);
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");
        // verify the value
        String value = input.getAttribute("value");
        Assert.assertEquals("07/22/15 12:00 AM", value);
        
    }
    
    @Test(groups = { "cookbook", "converter" })
    public void testInvalidMonth() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("{\"element\":\"#" + DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        Actions actions = new Actions(getWebDriver());
        actions.sendKeys(input, Keys.ESCAPE);
        actions.sendKeys(input, "23-23-2016");
        
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        actions.sendKeys(input2, Keys.ESCAPE);
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(getParentElement(input));
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value '23' is out of range for the 'month'.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value between '1' and '12'.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        actions.sendKeys(input, Keys.ESCAPE);
        actions.sendKeys(input, Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        actions.sendKeys(input, "12-23-2016"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        actions.sendKeys(input2, Keys.ESCAPE);
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");
        
        // verify the value
        String value = input.getAttribute("value");
        Assert.assertEquals("12/23/16 12:00 AM", value);

    }
    
    @Test(groups = { "cookbook", "converter" })
    public void testInvalidDay() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("{\"element\":\"#" + DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        Actions actions = new Actions(getWebDriver());
        actions.sendKeys(input, Keys.ESCAPE);
        actions.sendKeys(input, "12-32-2016");
        
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        actions.sendKeys(input2, Keys.ESCAPE);
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(getParentElement(input));
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value '32' is out of range for the 'day'.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value between '1' and '31'.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        actions.sendKeys(input, Keys.ESCAPE);
        actions.sendKeys(input, Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        actions.sendKeys(input, "12-23-2016"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        actions.sendKeys(input2, Keys.ESCAPE);
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");
        // verify the value
        String value = input.getAttribute("value");
        Assert.assertEquals("12/23/16 12:00 AM", value);

    }
    
    @Test(groups = { "cookbook", "converter" })
    public void testLenientParsing1() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("{\"element\":\"#" + DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        Actions actions = new Actions(getWebDriver());
        actions.sendKeys(input, Keys.ESCAPE);
        actions.sendKeys(input, "December 10 2015");
        
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        actions.sendKeys(input2, Keys.ESCAPE);
        //verify the date 
       // String value = evalJavascript("return $('#" + DATE1_ID+"').ojInputDate('option', 'value')");
       String value = input.getAttribute("value");
      
        Assert.assertEquals("12/10/15 12:00 AM", value);

    }
    
    @Test(groups = { "cookbook", "converter" })
    public void testLenientParsing2() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("{\"element\":\"#" + DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
         Actions actions = new Actions(getWebDriver());
         actions.sendKeys(input, Keys.ESCAPE);
        actions.sendKeys(input, "30xx11xx2015");
       
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        actions.sendKeys(input2, Keys.ESCAPE);
        //verify the date 
      //  String value = evalJavascript("return $('#" + DATE1_ID+"').ojInputDate('option', 'value')");
        String value = input.getAttribute("value");
        Assert.assertEquals("11/30/15 12:00 AM", value);

    }
}

