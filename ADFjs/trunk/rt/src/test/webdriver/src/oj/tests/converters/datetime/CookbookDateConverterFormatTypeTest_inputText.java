package oj.tests.converters.datetime;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookDateConverterFormatTypeTest_inputText extends OJetBase {
    private static final String TEST_PAGE = "demo-converters-builtInConvertersDatetime.html";
    private static final String TEST_PAGE_TITLE = "Converters - Built-in DateTime Converter";
    private static final String DATE1_ID = "text11";
    private static final String DATE2_ID = "date2";

    public CookbookDateConverterFormatTypeTest_inputText() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }


    @Test(groups = { "cookbook", "converter" })
    public void testDateConverter() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id=" + DATE1_ID );
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE2_ID +
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
        Assert.assertEquals(msg.getText().trim(), "'xx' is not in the expected date format.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value in the same format as this example: 'Nov 29, 1998'");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input.sendKeys("7-23-15"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");

        //Verify the date
        String value = input.getAttribute("value");
        
         Assert.assertEquals("Jul 23, 2015", value);
    }
    
    @Test(groups = { "cookbook", "converter" })
    public void testInvalidMonth() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id=" + DATE1_ID );
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        input.sendKeys("jly 20, 15");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(input);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Value 'jly' is out of range for the 'month name'.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a value between 'Jan' and 'Dec'.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input.sendKeys("12-23-2016"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");
        //Verify the date
        String value = input.getAttribute("value");
        
         Assert.assertEquals("Dec 23, 2016", value);
        
    }
    
    @Test(groups = { "cookbook", "converter" })
    public void testInvalidDay() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id=" + DATE1_ID );
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        input.sendKeys("Feb 30, 2016");
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
        input.sendKeys("February 23, 2016"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");
        //Verify the date
        String value = input.getAttribute("value");
        
         Assert.assertEquals("Feb 23, 2016", value);

    }
    
    @Test(groups = { "cookbook", "converter" })
    public void testLenientParsing1() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id=" + DATE1_ID );
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        input.sendKeys("2016xx20xx12");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        //verify the date 
       // String value = evalJavascript("return $('#" + DATE1_ID+"').ojInputDate('option', 'value')");
       String value = input.getAttribute("value");
      
        Assert.assertEquals("Dec 20, 2016", value);

    }
    
    @Test(groups = { "cookbook", "converter" })
    public void testLenientParsing2() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id=" + DATE1_ID );
        WebElement input2 =
            getElement("{\"element\":\"#" + DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        input.sendKeys("2016, 30 November");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        //verify the date 
      //  String value = evalJavascript("return $('#" + DATE1_ID+"').ojInputDate('option', 'value')");
        String value = input.getAttribute("value");
        Assert.assertEquals("Nov 30, 2016", value);

    }
}
