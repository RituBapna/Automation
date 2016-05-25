package oj.tests.validation.validators.daterestriction;

import oj.tests.validation.validators.datetimerange.CookbookDateTimeRangeValidator;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.Select;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookDateRestrictionValidator extends OJetBase {
    private static final String TEST_PAGE = "demo-validators-builtInValidatorDateRestriction.html";
    private static final String TEST_PAGE_TITLE = "Validators - Built-in DateRestriction Validator";
    private static final String INPUT_TEXT_ID = "foolsErrand1";
    private static final String INPUT_DATE_VALIDATOR_ID = "foolsErrand3";
    private static final String INPUT_DATE_DAYMETADATA_ID = "workdays2";
    private static final String INPUT_DATE_DAYFORMATTER_ID = "workdays1";

    public CookbookDateRestrictionValidator() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }


    @Test(groups = { "cookbook", "validation" })
    public void testValidatorOptionInInputText() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_TEXT_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("id=" + INPUT_TEXT_ID);
        WebElement input2 =
            getElement("{\"element\":\"#" + INPUT_DATE_VALIDATOR_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input).click().perform(); // move focus to input text  field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input.sendKeys("4-1-99");
      
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(input);
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Date April 1, 1999 is of a disabled entry.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Date April 1, 1999 should not be of a disabled entry.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input.sendKeys("7-22-2015"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");


    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testValidatorOptionInDate_valueTyped() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_DATE_VALIDATOR_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("{\"element\":\"#" + INPUT_DATE_VALIDATOR_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        WebElement input2 =
            getElement("id=" + INPUT_TEXT_ID);
        Actions actions = new Actions(getWebDriver());
        input.sendKeys("4-1-99");     
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(getParentElement(input));
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Date 04/01/99 is of a disabled entry.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Date 04/01/99 should not be of a disabled entry.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input.sendKeys("7-22-2015"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");


    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testValidatorOptionInDate_valuePicked() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_DATE_VALIDATOR_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("{\"element\":\"#" + INPUT_DATE_VALIDATOR_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        WebElement datepicker =
            getElement("{\"element\":\"#" + INPUT_DATE_VALIDATOR_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-calendar-icon\"}");
        datepicker.click();
        
        //set month to April
        setMonthInCalendar(INPUT_DATE_VALIDATOR_ID, 3); //3 is for April
        //set year to 2015
        setYearInCalendar(INPUT_DATE_VALIDATOR_ID, 2015); 
        //click on aptil 1st
        String numberToUseInId = getNumberForDatePickerValueSelection(INPUT_DATE_VALIDATOR_ID);
        WebElement date = getElement("id=oj-dp-"+numberToUseInId+"-0-3-0-0"); // id for April 1, 2015
        
        WebElement clickabledate = date.findElement(By.tagName("a"));
        clickabledate.click();
        
      
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(getParentElement(input));
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Date 04/01/15 is of a disabled entry.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Date 04/01/15 should not be of a disabled entry.");

        //Change the value to correct value and move focus to next input date field
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input.sendKeys("7-22-2015"); //enter the new value
        WebElement input2 =
            getElement("id=" + INPUT_TEXT_ID);
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");


    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testDayFormatter_valueTyped() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_DATE_DAYFORMATTER_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("{\"element\":\"#" + INPUT_DATE_DAYFORMATTER_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        WebElement input2 =
            getElement("{\"element\":\"#" + INPUT_DATE_DAYMETADATA_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        Actions actions = new Actions(getWebDriver());
        input.sendKeys("11/17/2014");     //this date should gice an error
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(getParentElement(input));
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Date 11/17/14 is of a disabled entry.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Date 11/17/14 should not be of a disabled entry.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input.sendKeys("7-24-2015"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");


    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testDayFormatter_dateDisabledInPicker() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_DATE_DAYFORMATTER_ID);
        //open date picker
        WebElement datepicker =
            getElement("{\"element\":\"#" + INPUT_DATE_DAYFORMATTER_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-calendar-icon\"}");
        datepicker.click();
        
        //set month to November
        setMonthInCalendar(INPUT_DATE_DAYFORMATTER_ID, 10); //10 is for November
        //set year to 2015
        setYearInCalendar(INPUT_DATE_DAYFORMATTER_ID, 2015); 
        //get element for Nov 11th
        String numberToUseInId = getNumberForDatePickerValueSelection(INPUT_DATE_DAYFORMATTER_ID);
        WebElement date = getElement("id=oj-dp-"+numberToUseInId+"-2-2-0-0"); // id for Nov 17th, 2015
        
        String classes = date.getAttribute("class");
        System.out.println("classes: " + classes);
        boolean isDisabled = classes.indexOf("oj-disabled") > -1;
        Assert.assertTrue(isDisabled);
      

    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testDayMetadata_valueTyped() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_DATE_DAYMETADATA_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("{\"element\":\"#" + INPUT_DATE_DAYMETADATA_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        WebElement input2 =
            getElement("{\"element\":\"#" + INPUT_DATE_DAYFORMATTER_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        Actions actions = new Actions(getWebDriver());
        input.sendKeys("11/17/2014");     //this date should gice an error
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(getParentElement(input));
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Date 11/17/14 is of a disabled entry.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Date 11/17/14 should not be of a disabled entry.");

        //Change the value to correct value and move focus to next input date field

        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input.sendKeys("7-24-2015"); //enter the new value
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");


    }
    
    @Test(groups = { "cookbook", "validation" })
    public void testDayMetadata_dateDisabledInPicker() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_DATE_DAYMETADATA_ID);
        //open date picker
        WebElement datepicker =
            getElement("{\"element\":\"#" + INPUT_DATE_DAYMETADATA_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-calendar-icon\"}");
        datepicker.click();
        
        //set month to November
        setMonthInCalendar(INPUT_DATE_DAYMETADATA_ID, 10); //10 is for November
        //set year to 2015
        setYearInCalendar(INPUT_DATE_DAYMETADATA_ID, 2015); 
        //get element for Nov 11th
        String numberToUseInId = getNumberForDatePickerValueSelection(INPUT_DATE_DAYMETADATA_ID);
        WebElement date = getElement("id=oj-dp-"+numberToUseInId+"-2-2-0-0"); // id for Nov 17th, 2015
        
        String classes = date.getAttribute("class");
        System.out.println("classes: " + classes);
        boolean isDisabled = classes.indexOf("oj-disabled") > -1;
        Assert.assertTrue(isDisabled);
      

    }
    
    String getNumberForDatePickerValueSelection(String dateComponentId){
        //get the correct calender id
        WebElement calendarContent =
            getElement("{\"element\":\"#" + dateComponentId + "\",\"index\":1,\"subId\":\"oj-datepicker-content\"}");
        String id = calendarContent.getAttribute("id");
        System.out.println("content ID: "+ id);
        int indexOfFirstUnderscore = id.indexOf("_");
        System.out.println("Index: "+ indexOfFirstUnderscore);
        String numberToUseInId = id.substring(0, indexOfFirstUnderscore);
        System.out.println("Number to use content ID: "+ numberToUseInId);
        return numberToUseInId;
    }
    
    void setMonthInCalendar(String dateComponentId, int month){
      
        Select monthSelect = 
            new Select(getElement("{\"element\":\"#" + dateComponentId + "\",\"index\":1,\"subId\":\"oj-datepicker-month\"}"));
        
        monthSelect.selectByValue(String.valueOf(month));
  
    }
    
    void setYearInCalendar(String dateComponentId, int year){
      
        Select yearSelect = 
            new Select(getElement("{\"element\":\"#" + dateComponentId + "\",\"index\":1,\"subId\":\"oj-datepicker-year\"}"));
        
        yearSelect.selectByValue(String.valueOf(year));
    
    }
}
