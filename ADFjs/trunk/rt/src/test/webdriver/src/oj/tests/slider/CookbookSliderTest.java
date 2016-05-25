package oj.tests.slider;
import java.util.List;
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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
//D:\OJET\ADFjs\trunk\rt\src\test\testUtils\src\oracle\ojet\automation\test
public class CookbookSliderTest extends SliderBase {
    public CookbookSliderTest() {
        super();
    }
    @Test(groups = { "cookbook" })
    public void sliderTest() throws Exception {
        startupTest("demo-slider-slider.html", null);
        verifyTitle("Incorrect page title;","Slider - Slider");
        waitForElementVisible("id=form1");
        waitForElementVisible("id=slider");
        
        Assert.assertEquals("0", evalJavascript("return $('#slider-id').ojSlider('option', 'min')"));
        Assert.assertEquals("200", evalJavascript("return $('#slider-id').ojSlider('option', 'max')"));
        Assert.assertEquals("100", evalJavascript("return $('#slider-id').ojSlider('option', 'value')"));
        Assert.assertEquals("10", evalJavascript("return $('#slider-id').ojSlider('option', 'step')"));
        
        // @oj-slider-bar-value :: moves the slider thumb backword and decreases the slider value
        WebElement sliderValueClickableIcon= getElement("{\"element\":\"#slider-id\",\"subId\":\"oj-slider-bar-value\"}");
        waitForMilliseconds(500L);
        sliderValueClickableIcon.click();
        
        Assert.assertEquals("50", evalJavascript("return $('#slider-id').ojSlider('option', 'value')"));
        waitForMilliseconds(500L);
        
        sliderValueClickableIcon.click();
        Assert.assertEquals("20", evalJavascript("return $('#slider-id').ojSlider('option', 'value')"));
        waitForMilliseconds(500L);
         
        // @oj-slider-bar :: moves the slider thumb forward and increase the slider value
        WebElement sliderBarClickableIcon= getElement("{\"element\":\"#slider-id\",\"subId\":\"oj-slider-bar\"}");
        sliderBarClickableIcon.click();
        Assert.assertEquals("100", evalJavascript("return $('#slider-id').ojSlider('option', 'value')"));
        waitForMilliseconds(500L);
        
        //sliderBarClickableIcon.click(); // this will throw error: element is not clickable at this movement
        sliderValueClickableIcon.click();
    }
    
     @Test(groups = { "cookbook" })
    public void sliderVerticalTest() throws Exception {
        startupTest("demo-slider-sliderVertical.html", null);
        verifyTitle("Incorrect page title;","Slider - Vertical");
        waitForElementVisible("id=form1");
        waitForElementVisible("id=slider");
        
        Assert.assertEquals("0", evalJavascript("return $('#slider-id').ojSlider('option', 'min')"));
        Assert.assertEquals("200", evalJavascript("return $('#slider-id').ojSlider('option', 'max')"));
        Assert.assertEquals("100", evalJavascript("return $('#slider-id').ojSlider('option', 'value')"));
        Assert.assertEquals("10", evalJavascript("return $('#slider-id').ojSlider('option', 'step')"));
        Assert.assertEquals("vertical", evalJavascript("return $('#slider-id').ojSlider('option', 'orientation')"));
        
        // @oj-slider-bar-value :: moves the slider thumb backword and decreases the slider value
        WebElement sliderValueClickableIcon= getElement("{\"element\":\"#slider-id\",\"subId\":\"oj-slider-bar-value\"}");
        
        sliderValueClickableIcon.click();//50
        Assert.assertEquals("50", evalJavascript("return $('#slider-id').ojSlider('option', 'value')"));
        waitForMilliseconds(1000L);
        
        sliderValueClickableIcon.click();//30
        Assert.assertEquals("30", evalJavascript("return $('#slider-id').ojSlider('option', 'value')"));
        
        waitForMilliseconds(500L);
        
        // @oj-slider-bar :: moves the slider thumb forward and increase the slider value
        WebElement sliderBarClickableIcon= getElement("{\"element\":\"#slider-id\",\"subId\":\"oj-slider-bar\"}");
        sliderBarClickableIcon.click();
        Assert.assertEquals("100", evalJavascript("return $('#slider-id').ojSlider('option', 'value')"));
        waitForMilliseconds(500L);
    }
     @Test(groups = { "cookbook" })
    public void sliderRangeTest() throws Exception {
        startupTest("demo-slider-sliderRange.html", null);
        verifyTitle("Incorrect page title;","Slider - Range");
        waitForElementVisible("id=form1");
        waitForElementVisible("id=slider");
        
        Assert.assertEquals("0", evalJavascript("return $('#slider-id').ojSlider('option', 'min')"));
        Assert.assertEquals("500", evalJavascript("return $('#slider-id').ojSlider('option', 'max')"));
        Assert.assertEquals("25", evalJavascript("return $('#slider-id').ojSlider('option', 'step')"));
        Assert.assertEquals("horizontal", evalJavascript("return $('#slider-id').ojSlider('option', 'orientation')"));
        
        Assert.assertEquals("[100, 400]", evalJavascript("return $('#slider-id').ojSlider('option', 'value')"));
        Assert.assertEquals("100", evalJavascript("return $('#slider-id').ojSlider('option', 'value')[0]"));
        Assert.assertEquals("400", evalJavascript("return $('#slider-id').ojSlider('option', 'value')[1]"));
        
        // @oj-slider-bar-value :: moves the slider thumb backword and decreases the slider value
        WebElement sliderClickableValIcon= getElement("{\"element\":\"#slider-id\",\"subId\":\"oj-slider-bar-value\"}");//
        sliderClickableValIcon.click();//250
        Assert.assertEquals("250", evalJavascript("return $('#slider-id').ojSlider('option', 'value')[0]"));
        
        sliderClickableValIcon.click();//325
        Assert.assertEquals("325", evalJavascript("return $('#slider-id').ojSlider('option', 'value')[0]"));
        
        sliderClickableValIcon.click();//350
        
        WebElement sliderBarlIcon= getElement("{\"element\":\"#slider-id\",\"subId\":\"oj-slider-bar\"}");//
        sliderBarlIcon.click();//350
        Assert.assertEquals("250", evalJavascript("return $('#slider-id').ojSlider('option', 'value')[0]"));
        
        waitForMilliseconds(500L);
    }
    @Test(groups = { "cookbook" })
    public void sliderIconsTest() throws Exception {
        startupTest("demo-slider-sliderIcons.html", null);
        verifyTitle("Incorrect page title;","Slider - Icons");
        waitForElementVisible("id=form1");
        waitForElementVisible("id=slider");
        
        Assert.assertEquals("0", evalJavascript("return $('#slider-id').ojSlider('option', 'min')"));
        Assert.assertEquals("200", evalJavascript("return $('#slider-id').ojSlider('option', 'max')"));
        Assert.assertEquals("10", evalJavascript("return $('#slider-id').ojSlider('option', 'step')"));
        Assert.assertEquals("horizontal", evalJavascript("return $('#slider-id').ojSlider('option', 'orientation')"));
        Assert.assertEquals("100", evalJavascript("return $('#slider-id').ojSlider('option', 'value')"));
        
        // @oj-slider-bar-value :: moves the slider thumb backword and decreases the slider value
        WebElement sliderClickableValIcon= getElement("{\"element\":\"#slider-id\",\"subId\":\"oj-slider-bar-value\"}");//
        sliderClickableValIcon.click();//250
        waitForMilliseconds(500L);
        Assert.assertEquals(getElement("id=curr-value").getText(), evalJavascript("return $('#slider-id').ojSlider('option', 'value')"));
        Assert.assertEquals("50", evalJavascript("return $('#slider-id').ojSlider('option', 'value')"));
       
        sliderClickableValIcon.click();//325
        waitForMilliseconds(500L);
        Assert.assertEquals(getElement("id=curr-value").getText(), evalJavascript("return $('#slider-id').ojSlider('option', 'value')"));
        Assert.assertEquals("20", evalJavascript("return $('#slider-id').ojSlider('option', 'value')"));

        WebElement sliderBarlIcon= getElement("{\"element\":\"#slider-id\",\"subId\":\"oj-slider-bar\"}");//
        sliderBarlIcon.click();//350
        Assert.assertEquals(getElement("id=curr-value").getText(), evalJavascript("return $('#slider-id').ojSlider('option', 'value')"));
        Assert.assertEquals("100", evalJavascript("return $('#slider-id').ojSlider('option', 'value')"));
    }
    
    @Test(groups = { "cookbook" })
    public void sliderDisabledTest() throws Exception {
        startupTest("demo-slider-sliderDisabled.html", null);
        verifyTitle("Incorrect page title;","Slider - Disabled");
        waitForElementVisible("id=form1");
        waitForElementVisible("id=slider");
        Assert.assertTrue(Boolean.parseBoolean(evalJavascript("return $('#slider-id').ojSlider('option', 'disabled')")));
    }
}

