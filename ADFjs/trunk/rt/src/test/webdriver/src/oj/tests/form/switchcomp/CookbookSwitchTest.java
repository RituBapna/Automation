package oj.tests.form.switchcomp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import java.util.NoSuchElementException;
import java.util.List;
import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

public class CookbookSwitchTest extends OJetBase {

	private static final String SWITCH_TITLE = "Switch: Switch";
	private static final String BASIC_DEMO = "This demo showcases a basic Switch.";
	
    public CookbookSwitchTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }
	@Test(groups = { "cookbook" })
	public void switchTest() throws Exception {
        //Start the test and bring up the browser
        startupTest("demo-switch-switchcomponent.html",null);
        verifyTitle("Incorrect page title;", "Switch - Switch");
        waitForElementVisible("id=formId");
        WebElement switchDiv = getElement("id=switch");
        WebElement switchIcon = getElement("{\"element\":\"#switch\",\"subId\":\"oj-switch-track\"}");
        
        switchIcon.click();// enable the switch
        String isChecked=switchDiv.getAttribute("checked");
        Assert.assertEquals("true", isChecked);
        
        //Checking for span text:ojSwitch is ON
        String spanText=getSpanText();
        Assert.assertEquals(spanText, "ON");
        
        
        switchIcon.click();//disable the switch
        Assert.assertEquals(null, switchDiv.getAttribute("checked"));
        
        //Checking for span text:ojSwitch is OFF
        spanText=getSpanText();
        Assert.assertEquals(spanText, "OFF");
        
       
        WebElement element;
        String str=switchDiv.getAttribute("class");
        try{
			Thread.sleep(1000);
		}catch(Exception e){}

    }
	
	@Test(groups = { "cookbook" })
	public void disableSwitchTest() throws Exception {
        
        startupTest("demo-switch-switchdisabled.html",null);
        verifyTitle("Incorrect page title;", "Switch - Disabled");
        waitForElementVisible("id=formId");
        WebElement form = getElement("id=formId");
        WebElement switchDiv = getElement("id=switch");
        WebElement switchIcon = getElement("{\"element\":\"#switch\",\"subId\":\"oj-switch-track\"}");
        
        
        // **** xpath used to make sure switch is not made with disble *****//
        WebElement thumbWithoutDisable =form.findElement(By.xpath("//div[@class='oj-switch-thumb']"));
        Assert.assertEquals(null, thumbWithoutDisable.getAttribute("aria-disabled"));
        
        switchIcon.click();// switch selected/active
        Assert.assertEquals("true", switchDiv.getAttribute("checked"));
        
        switchIcon.click();//switch de selected/inactive
        Assert.assertEquals(null, switchDiv.getAttribute("checked"));
        
        // makeing the switch disable; clicking the toggle switch button
        WebElement toggleWitchButton= getElement("id=inputButton");
        toggleWitchButton.click();
        
        //********* xpath used to make sure that switch is disbled  *******
        WebElement thumbWithDisable =form.findElement(By.xpath("//div[@class='oj-switch-thumb']"));
        Assert.assertEquals("true", thumbWithDisable.getAttribute("aria-disabled"));
        
    }
    
    @Test(groups = { "cookbook" })
	public void inlineSwitchTest() throws Exception {
        
        startupTest("demo-switch-switchinline.html",null);
        verifyTitle("Incorrect page title;", "Switch - Inline");
        waitForElementVisible("id=formId");
        WebElement form = getElement("id=formId");
        WebElement switchDiv = getElement("id=switch");
        WebElement switchIcon = getElement("{\"element\":\"#switch\",\"subId\":\"oj-switch-track\"}");
               
        // **** xpath used to make sure switch is not made with disble *****//
        WebElement inlineSwitch =form.findElement(By.xpath("//div[@class='oj-label oj-component oj-switch-label oj-label-inline']"));
        
        
        Assert.assertEquals("oj-label oj-component oj-switch-label oj-label-inline", inlineSwitch.getAttribute("class"));
        
        // Makeing sure currently switch is off
        WebElement offSwitch =form.findElement(By.xpath("//span[contains(text(),'OFF')]"));
        Assert.assertEquals("OFF", offSwitch.getText());
        
       // WebElement switchIcon = getElement("{\"element\":\"#switch\",\"subId\":\"oj-switch-track\"}");
        switchIcon.click();// switch selected/active
        WebElement onSwitch =form.findElement(By.xpath("//span[contains(text(),'ON')]"));
        Assert.assertEquals("ON", onSwitch.getText());
    }
    
    public String  getSpanText() {
        WebElement switchDiv = getElement("id=formId");
        List<WebElement> spans = switchDiv.findElements(By.tagName("span"));
        String spanText="";
        for (WebElement span : spans) {
            spanText = span.getText();
            System.out.println("#### spanText : *" + spanText + "*");
            if (spanText.trim().equals("ON")) {
                spanText="ON";
                
            }
            else if (spanText.trim().equals("OFF")) {
                spanText="OFF";
            }

        }
        return spanText;
    }
	
}
