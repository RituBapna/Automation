package oj.tests.slider;
import java.util.List;
import java.util.NoSuchElementException;

import oracle.ojet.automation.test.OJetBase;// locationD:\OJET\ADFjs\trunk\built\test\webdriver\classes\oracle\ojet\automation
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

public class SliderThumbMisplaced extends OJetBase {
    private static final String SLIDER_ORIENTATION_COMBOBOX_ID = "orientationComboId"; //currentItem combo box id
    public SliderThumbMisplaced() {
      //  super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
       super(new TestConfigBuilder().setContextRoot("input").setAppRoot("slider").build());
    } 
 
    @Test(groups = { "sliderThumb" })
    public void sliderThumbMispaced()throws Exception
    {
        String orientationVal=null;
        String currentValue=null;
        startupTest("sliderThumbMisplacePage.html",null);
        maximizeWindow();
        this.waitForMilliseconds(1000);

        selectItemfromComboList(SLIDER_ORIENTATION_COMBOBOX_ID,0);
        this.waitForMilliseconds(1000);
        
        orientationVal=getElement("id=sliderOrientationOutputText").getText();
        Assert.assertTrue(orientationVal.contains("vertical"));
        this.waitForMilliseconds(1000);

        getElement("id=changeOrientationValButton").click();
        this.waitForMilliseconds(1000);  
        
        currentValue=getElement("id=curr-value").getText();
        System.out.println("++++++currentValue++++++++"+currentValue);
        Assert.assertTrue(currentValue.contains("0"));

        selectItemfromComboList(SLIDER_ORIENTATION_COMBOBOX_ID,1);
        this.waitForMilliseconds(1000);
        
        orientationVal=getElement("id=sliderOrientationOutputText").getText();
        Assert.assertTrue(orientationVal.contains("horizontal"));
        this.waitForMilliseconds(1000);
        
    }
    
    /**********************
    1.) SLIDER_ORIENTATION_COMBOBOX_ID=itemIdToBeClicked
    2.) 0 =itemNoToselect
    3.) sliderOrientationOutputText=outputTextFiledId
    *******************/
    public void selectItemfromComboList(String itemIdToBeClicked, int itemNoToselect){

        WebElement comboboxInput =  getElement("{\"element\":\"#" +itemIdToBeClicked+"\",\"subId\":\"oj-combobox-arrow\"}");
        comboboxInput.click();
        WebElement resultset =  getElement("{\"element\":\"#" +itemIdToBeClicked + "\",\"subId\":\"oj-combobox-results\"}");
        List<WebElement> listItems = resultset.findElements(By.tagName("li"));
        listItems.get(itemNoToselect).click();
    }
    
}
