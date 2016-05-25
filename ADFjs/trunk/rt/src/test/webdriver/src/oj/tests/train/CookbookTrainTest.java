package oj.tests.train;

import java.util.NoSuchElementException;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

public class CookbookTrainTest extends OJetBase {
    public CookbookTrainTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook", "train" })
    public void testBasicTrainStopSwitching() throws Exception {
        startupTest("demo-train-basic.html", null);
        verifyTitle("Incorrect page title;", "Train - Basic");
        waitForElementVisible("id=train");
        waitForElementVisible("{\"element\":\"#train\",\"index\":1,\"subId\":\"oj-train-button\"}");
        WebElement secondTrainStopButton =
            getElement("{\"element\":\"#train\",\"index\":1,\"subId\":\"oj-train-button\"}");
        // Actions actions = new Actions(getWebDriver());
        // actions.moveToElement(secondTrainStopButton).click().perform();
        //  String  script = "var node = $('#train').ojTrain('getNodeBySubId', {index: 1, subId: 'oj-train-button'});"+
        //                   "node.click();"  ;
        // ((JavascriptExecutor) getWebDriver()).executeScript(script);
        secondTrainStopButton.click();
        //id of the current selected steps

        String stepId = evalJavascript("return $('#train').ojTrain('option', 'selected')");
        //Second tab is selected
        Assert.assertEquals(stepId, "stp2");

        //Verify that heading is pointing to label of second train stop
        WebElement label = getElement("id=currentStepText");
        Assert.assertEquals(label.getText(), "You are on Step Two");

        //click on train stop 5
        WebElement fifthTrainStopButton =
            getElement("{\"element\":\"#train\",\"index\":4,\"subId\":\"oj-train-button\"}");

        fifthTrainStopButton.click();

        //id of the current selected steps

        stepId = evalJavascript("return $('#train').ojTrain('option', 'selected')");
        //Second tab is selected
        Assert.assertEquals(stepId, "stp5");

        //Verify that heading is pointing to label of second train stop
        label = getElement("id=currentStepText");
        Assert.assertEquals(label.getText(), "You are on Step Five");

        //Clicking on already visted train stop (stop 1)

        WebElement firstTrainStopButton =
            getElement("{\"element\":\"#train\",\"index\":0,\"subId\":\"oj-train-button\"}");

        firstTrainStopButton.click();

        //id of the current selected steps

        stepId = evalJavascript("return $('#train').ojTrain('option', 'selected')");
        //Second tab is selected
        Assert.assertEquals(stepId, "stp1");

        //Verify that heading is pointing to label of second train stop
        label = getElement("id=currentStepText");
        Assert.assertEquals(label.getText(), "You are on Step One");

    }

    @Test(groups = { "cookbook", "train" })
    public void testDisabledTrainStop() throws Exception {
        startupTest("demo-train-messageTypes.html", null);
        verifyTitle("Incorrect page title;", "Train - Message Types");
        waitForElementVisible("id=train");
        //click on stop 2 which is disabled
        WebElement secondTrainStopButton =
            getElement("{\"element\":\"#train\",\"index\":1,\"subId\":\"oj-train-button\"}");

        secondTrainStopButton.click();

        //id of the current selected steps

        String stepId = evalJavascript("return $('#train').ojTrain('option', 'selected')");
        //Check that train stop selection has not changed. First tab is selected
        Assert.assertEquals(stepId, "stp1");

        //Verify that heading is pointing to label of First train stop
        WebElement label = getElement("id=currentStepText");
        Assert.assertEquals(label.getText(), "You are on Step One");


    }

    @Test(groups = { "cookbook", "train" })
    public void testTrainWithMessageTypes() throws Exception {
        startupTest("demo-train-messageTypes.html", null);
        verifyTitle("Incorrect page title;", "Train - Message Types");
        waitForElementVisible("id=train");

        // 3rd train stop
        WebElement trainStopButton = getElement("{\"element\":\"#train\",\"index\":2,\"subId\":\"oj-train-button\"}");
        trainStopButton.click();
        WebElement trainStopMsgIcon = getElement("{\"element\":\"#train\",\"index\":0,\"subId\":\"oj-train-icon\"}");
        String classes = trainStopMsgIcon.getAttribute("class");
        //first train stop has confirmation message icon
        Boolean hasClass = classes.indexOf("oj-confirmation") > -1;
        Assert.assertTrue(hasClass, "first train stop has oj-confirmation icon");

        //choose a error radio button (3rd)
        WebElement radioButton = getElement("id=error");
        radioButton.click();

        //click on 4th train stop
        trainStopButton = getElement("{\"element\":\"#train\",\"index\":3,\"subId\":\"oj-train-button\"}");
        trainStopButton.click();
        trainStopMsgIcon = getElement("{\"element\":\"#train\",\"index\":2,\"subId\":\"oj-train-icon\"}");
        classes = trainStopMsgIcon.getAttribute("class");
        //third train stop has error message icon
        hasClass = classes.indexOf("oj-error") > -1;
        Assert.assertTrue(hasClass, "third train stop has oj-error icon");

        //choose a fatal radio button )
        radioButton = getElement("id=fatal");
        radioButton.click();
        //click on 5th train stop
        trainStopButton = getElement("{\"element\":\"#train\",\"index\":4,\"subId\":\"oj-train-button\"}");
        trainStopButton.click();
        trainStopMsgIcon = getElement("{\"element\":\"#train\",\"index\":3,\"subId\":\"oj-train-icon\"}");
        classes = trainStopMsgIcon.getAttribute("class");
        //fourth train stop has fatal message icon
        hasClass = classes.indexOf("oj-error") > -1;
        Assert.assertTrue(hasClass, "4th train stop has oj-error icon");

        //choose a info radio button )
        radioButton = getElement("id=info");
        radioButton.click();
        //click on 1st train stop
        trainStopButton = getElement("{\"element\":\"#train\",\"index\":0,\"subId\":\"oj-train-button\"}");
        trainStopButton.click();
        trainStopMsgIcon = getElement("{\"element\":\"#train\",\"index\":4,\"subId\":\"oj-train-icon\"}");
        classes = trainStopMsgIcon.getAttribute("class");
        //5th train stop has fatal message icon
        hasClass = classes.indexOf("oj-info") > -1;
        Assert.assertTrue(hasClass, "5th train stop has oj-info icon");

        //choose a warning radio button )
        radioButton = getElement("id=warning");
        radioButton.click();
        //click on 3rd train stop
        trainStopButton = getElement("{\"element\":\"#train\",\"index\":2,\"subId\":\"oj-train-button\"}");
        trainStopButton.click();
        trainStopMsgIcon = getElement("{\"element\":\"#train\",\"index\":0,\"subId\":\"oj-train-icon\"}");
        classes = trainStopMsgIcon.getAttribute("class");
        //1st train stop has fatal message icon
        hasClass = classes.indexOf("oj-warning") > -1;
        Assert.assertTrue(hasClass, "1st train stop has oj-warning icon");


    }

    @Test(groups = { "cookbook", "train" })
    public void testNoIconWithNullMessageType() throws Exception {
        startupTest("demo-train-messageTypes.html", null);
        verifyTitle("Incorrect page title;", "Train - Message Types");
        waitForElementVisible("id=train");
        waitForElementVisible("{\"element\":\"#train\",\"index\":2,\"subId\":\"oj-train-button\"}");

        //Choose null message type
        //choose a error radio button (3rd)
        WebElement radioButton = getElement("id=null");
        radioButton.click();
        // 3rd train stop
        WebElement trainStopButton = getElement("{\"element\":\"#train\",\"index\":2,\"subId\":\"oj-train-button\"}");
        trainStopButton.click();

        //get the first train stop to check the message icon
        WebElement firstTrainStopButton =
            getElement("{\"element\":\"#train\",\"index\":0,\"subId\":\"oj-train-button\"}");
        Boolean childrenDoesNotExist = firstTrainStopButton.findElements(By.tagName("div")).size() == 0;

        Assert.assertTrue(childrenDoesNotExist, "1st train stop has no icon");


    }

    @Test(groups = { "cookbook", "train" })
    public void testNoOpPreviousOnFirstStop() throws Exception {
        startupTest("demo-train-buttonNavigation.html", null);
        verifyTitle("Incorrect page title;", "Train - Button Navigation");
        waitForElementVisible("id=train");

        //get the id of current train stop
        //id of the current selected steps

        String currentStepId = evalJavascript("return $('#train').ojTrain('option', 'selected')");

        //get the button element that calls PreviousStep method
        WebElement prevButton = getElement("id=PreviousStep");
        prevButton.click();
        // get the current current train stop Id after the previousStep method call

        String stepIdAfterPreviousStep = evalJavascript("return $('#train').ojTrain('option', 'selected')");

        //verify that current step does not change
        Assert.assertEquals(currentStepId, stepIdAfterPreviousStep);


    }

    @Test(groups = { "cookbook", "train" }, dependsOnMethods = { "testNoOpPreviousOnFirstStop" })
    public void testPreviousAndNextStep() throws Exception {
        startupTest("demo-train-buttonNavigation.html", null);
        verifyTitle("Incorrect page title;", "Train - Button Navigation");
        waitForElementVisible("id=train");

        //get the button element that calls NextStep method
        WebElement nextButton = getElement("id=NextStep");
        nextButton.click();
        this.waitForMilliseconds(1000);
        // get the current  train stop Id after the nextStep method call. NextStep skips the the second disabled step

        String stepIdAfterMethodCall = evalJavascript("return $('#train').ojTrain('option', 'selected')");
        Assert.assertEquals(stepIdAfterMethodCall, "stp3");

        //get the button element that calls PreviousStep method
        WebElement prevButton = getElement("id=PreviousStep");
        prevButton.click();
        this.waitForMilliseconds(1000);
        // get the current current train stop Id after the previousStep method call
        stepIdAfterMethodCall = evalJavascript("return $('#train').ojTrain('option', 'selected')");
        Assert.assertEquals(stepIdAfterMethodCall, "stp1");


    }

    @Test(groups = { "cookbook" , "train"}, dependsOnMethods = { "testPreviousAndNextStep" })
    public void testNoOpNextOnLastStop() throws Exception {
        startupTest("demo-train-buttonNavigation.html", null);
        verifyTitle("Incorrect page title;", "Train - Button Navigation");
        waitForElementVisible("id=train");


        //get the button element that calls NextStep method.
        WebElement nextButton = getElement("id=NextStep");
        nextButton.click(); //go to third train stop. Second train stop is empty
        nextButton.click(); //go to fourth train stop.
        nextButton.click(); //go to fifth and last train stop.

        //Verify that calling nextStep on last setp is noop
        nextButton.click(); //stays on fifth and last train stop

        String stepIdAfterMethodCall = evalJavascript("return $('#train').ojTrain('option', 'selected')");
        Assert.assertEquals(stepIdAfterMethodCall, "stp5");

    }
    /*
    @Test(groups = { "cookbook" })
    public void testTrainStrech() throws Exception {
        startupTest("demo-train-stretch.html", null);
        verifyTitle("Incorrect page title;", "Train - Stretched Train");

        waitForElementVisible("id=train2");



        //get the button element that calls NextStep method.
         WebElement train = getElement("id=train2");
         Dimension size = train.getSize();
         int width = size.getWidth();


        Assert.assertEquals(width, 700);

    }
*/

}
