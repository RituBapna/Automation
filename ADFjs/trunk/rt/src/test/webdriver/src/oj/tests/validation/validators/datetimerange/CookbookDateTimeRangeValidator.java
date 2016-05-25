package oj.tests.validation.validators.datetimerange;

import oj.tests.validation.componentlevel.CookbookComponentCreationTest;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CookbookDateTimeRangeValidator extends OJetBase {
    private static final String TEST_PAGE = "demo-validators-builtInValidatorDateTimeRange.html";
    private static final String TEST_PAGE_TITLE = "Validators - Built-in DateTimeRange Validator";
    private static final String INPUT_DATE1_ID = "dateTimeRange1";
    private static final String INPUT_DATE2_ID = "dateTimeRange2";

    public CookbookDateTimeRangeValidator() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }


    @Test(groups = { "cookbook", "validation" })
    public void testMinAttributeMaxOption() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("{\"element\":\"#" + INPUT_DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        WebElement input2 =
            getElement("{\"element\":\"#" + INPUT_DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        input.sendKeys("12-12-99");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to input date field 2
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(getParentElement(input));
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Date and time is earlier than the minimum date.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Date and time must be on or later than 01/01/00.");

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
    public void testDateTimeRangeDefaultHint() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_DATE1_ID);

        //move focus on input date field
        WebElement input =
            getElement("{\"element\":\"#" + INPUT_DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input).click().perform();

        //verify the default hint
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-popup-validator-hint", 0);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        Date date = new Date();
        String time = sdf.format(date);
        Assert.assertEquals(msg.getText().trim(), "Enter a date and time between 01/01/00 and " + time+".");

    }

    @Test(groups = { "cookbook", "validation" })
    public void testRestrictedDateSelection() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_DATE1_ID);

        //move focus on input date field
        WebElement datepicker =
            getElement("{\"element\":\"#" + INPUT_DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-calendar-icon\"}");
        //open date picker
        datepicker.click();

        //verify that next icon in calendar popup is disabled
        WebElement nexticon =
            getElement("{\"element\":\"#" + INPUT_DATE1_ID + "\",\"index\":1,\"subId\":\"oj-datepicker-next-icon\"}");

        String classes = nexticon.getAttribute("class");
        boolean isDisabled = classes.indexOf("oj-disabled") > -1;
        Assert.assertTrue(isDisabled);


    }

    @Test(groups = { "cookbook", "validation" })
    public void testDateTimeRageTypeInValidatorOption() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_DATE1_ID);

        //enter invalid date value in field and click on next input date field
        WebElement input =
            getElement("{\"element\":\"#" + INPUT_DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        WebElement input2 =
            getElement("{\"element\":\"#" + INPUT_DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        input.sendKeys("12-12-99");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input2).click().perform(); // move focus to other input date field
        //verify that validation error is displayed
        WebElement parentElem = getParentElement(getParentElement(input));
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Date and time is earlier than the minimum date.");
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-detail", 0);
        Assert.assertEquals(msg.getText().trim(), "Date and time must be on or later than 01/01/00.");

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
    public void testDateTimeRangeCustomHint() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_DATE1_ID);

        //move focus on input date field
        WebElement input =
            getElement("{\"element\":\"#" + INPUT_DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input).click().perform();

        //verify the default hint
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-popup-validator-hint", 0);
        Assert.assertEquals(msg.getText().trim(), "Enter a date that falls in the current millennium.");

        //*[@id="oj-dp-3-3-5-0-0"]
    }

    @Test(groups = { "cookbook", "validation" })
    public void testValidationErrorUsingDatePicker() throws Exception {
        startupTest(TEST_PAGE, null);
        verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
        waitForElementVisible("id=" + INPUT_DATE2_ID);

        //Click on Date picker of input date 2
        WebElement datepicker =
            getElement("{\"element\":\"#" + INPUT_DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-calendar-icon\"}");
        datepicker.click();

        //Click on next icon in calendar popup is disabled
        WebElement nexticon =
            getElement("{\"element\":\"#" + INPUT_DATE2_ID + "\",\"index\":1,\"subId\":\"oj-datepicker-next-icon\"}");
        nexticon.click();

        //Set the date by clicking on calendar date
        //get the correct calender id
        WebElement calendarContent =
            getElement("{\"element\":\"#" + INPUT_DATE2_ID + "\",\"index\":1,\"subId\":\"oj-datepicker-content\"}");
        String id = calendarContent.getAttribute("id");
        System.out.println("content ID: "+ id);
        int indexOfFirstUnderscore = id.indexOf("_");
        System.out.println("Index: "+ indexOfFirstUnderscore);
        String numberToUseInId = id.substring(0, indexOfFirstUnderscore);
        System.out.println("Number to use content ID: "+ numberToUseInId);
      
        WebElement date = getElement("id=oj-dp-"+numberToUseInId+"-1-0-0-0");
       
        WebElement clickabledate = date.findElement(By.tagName("a"));
        clickabledate.click();
        //Verify error
        WebElement input =
            getElement("{\"element\":\"#" + INPUT_DATE2_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        //
        WebElement parentElem = getParentElement(getParentElement(input));
        String classes = parentElem.getAttribute("class");
        boolean hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertTrue(hasValidationErr);
        WebElement msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertEquals(msg.getText().trim(), "Date and time is later than the maximum date.");

        //Change the value to correct value and move focus to next input date field

        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(input).click().perform(); // move focus to input date field
        input.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE); //delete the existing value
        input.sendKeys("7-22-2015"); //enter the new value
        //other input field on the page
        WebElement input2 =
            getElement("{\"element\":\"#" + INPUT_DATE1_ID +
                       "\",\"index\":1,\"subId\":\"oj-inputdatetime-date-input\"}");
        actions.moveToElement(input2).click().perform(); // move focus to next input date field
        //Verify that error is removed
        classes = parentElem.getAttribute("class");
        hasValidationErr = classes.indexOf("oj-invalid") > -1;
        Assert.assertFalse(hasValidationErr); //no decoration
        msg = getMessagingContentNodeBySubId(input, "oj-messaging-inline-message-summary", 0);
        Assert.assertNull(msg, "No error message window is present");


    }
}
