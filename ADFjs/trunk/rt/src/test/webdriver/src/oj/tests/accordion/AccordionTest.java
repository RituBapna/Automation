package oj.tests.accordion;
//@ Ritu Bapna
//what package should I put in this file
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

public class AccordionTest extends OJetBase {

	private static final String ACCORDION_TITLE = "Accordion: Accordion";
	private static final String BASIC_DEMO = "This demo showcases a basic accordion.";
	
    public AccordionTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }
	@Test(groups = { "cookbook" })
	public void loadPage() throws Exception {

			//Start the test and bring up the browser
			startupTest("demo-accordion-basicAccordion.html",null);

			//Verify the url
			String url = getBrowserUrl();
			log("URL##########"+ url);

			// Verify if the title of the page is correct
			//verifyTitle("Incorrect page title;", ACCORDION_TITLE);

			waitForElementVisible("id=accordionPage");

    }
	
	public void titleDiaplayTest() throws Exception {

			verifyTitle("Incorrect basic demo text;", BASIC_DEMO);
			waitForElementVisible("id=accordionPage");
    }
	
	@Test(groups = { "cookbook" })
	public void accordionExpandCollapseTest() throws Exception {

		//Start the test and bring up the browser
		startupTest("demo-accordion-basicAccordion.html",null);
		
        //Expand and first header-icon
		waitForElementVisible("id=accordionPage");
		WebElement header1 = getElement("{\"element\":\"#c1\",\"subId\":\"oj-collapsible-header-icon\"}");
		header1.click();
		
		WebElement content1 = getElement("{\"element\":\"#c1\",\"subId\":\"oj-collapsible-content\"}");
		//Assert.assertEquals("Content 1.",content1.getText());
		//Assert.assertTrue(content1.isDisplayed());
		
		//Collapse first header-icon
		header1.click();
		try{
			Thread.sleep(1000);
		}catch(Exception e){}
		
		//Expand second header-icon		
		WebElement header3 = getElement("{\"element\":\"#c3\",\"subId\":\"oj-collapsible-header-icon\"}");
		header3.click();
		try{
			Thread.sleep(1000);
		}catch(Exception e){}
		waitForElementVisible("id=radiosetSetBasicDemoId");
		WebElement content3 = getElement("{\"element\":\"#radiosetSetBasicDemoId\",\"subId\":\"oj-radioset-inputs\"}");
		
		// Blue option radio selected
		WebElement blueoptRadion = getElement("id=blueopt");
		blueoptRadion.click();
		try{
			Thread.sleep(1000);
		}catch(Exception e){}
		
		//Green option radio button selected
		WebElement greenoptRadion = getElement("id=greenopt");
		greenoptRadion.click();
		try{
			Thread.sleep(100);
		}catch(Exception e){}
		
		// Collapse second header-icon 
		header3.click();
		try{
			Thread.sleep(100);
		}catch(Exception e){}
		
		//Inner Accordion
		WebElement outerNestedAccordion = getElement("id=c4");
		outerNestedAccordion.click();
		try{
			Thread.sleep(1000);
		}catch(Exception e){}
		WebElement headerIcon4 = getElement("{\"element\":\"#ic1\",\"subId\":\"oj-collapsible-header-icon\"}");
		headerIcon4.click();
		//waitForElementVisible("id=nestedAccordion");
		//waitForElementVisible("id=ic1");
		//WebElement innerHeader1 = getElement("{\"element\":\"#ic1\",\"subId\":\"oj-collapsible-header-icon\"}");
		try{
			Thread.sleep(1000);
		}catch(Exception e){}
		//innerHeader1.click();
		try{
			Thread.sleep(1000);
		}catch(Exception e){}
		
		
    }
	
	@Test(groups = { "cookbook" })
	public void multipleExpansionAccordionTest() throws Exception {

		//Start the test and bring up the browser
		startupTest("demo-accordion-multiExpandAccordion.html",null);
		verifyTitle("Incorrect page title;", "Accordion - Multiple Expansions");
		waitForElementVisible("id=accordionPage");
        //Expand and first header-icon
		WebElement header1 = getElement("{\"element\":\"#c1\",\"subId\":\"oj-collapsible-header-icon\"}");
		header1.click();
		try{
			Thread.sleep(1000);
		}catch(Exception e){}
		
		WebElement content1 = getElement("{\"element\":\"#c1\",\"subId\":\"oj-collapsible-content\"}");
		Assert.assertEquals("Content 1.",content1.getText());
		Assert.assertTrue(content1.isDisplayed());
		
		//Check for content of header2 whether isDisplayed is true?
		WebElement content2 = getElement("{\"element\":\"#c2\",\"subId\":\"oj-collapsible-content\"}");
		Assert.assertTrue(content2.isDisplayed());
		
		//Check for content of header3 whether isDisplayed is true?
		WebElement content3 = getElement("{\"element\":\"#c3\",\"subId\":\"oj-collapsible-content\"}");
		Assert.assertTrue(content3.isDisplayed());
		
		//Expand inner accordion of header3
		WebElement innerAccordion1 = getElement("{\"element\":\"#ic1\",\"subId\":\"oj-collapsible-header-icon\"}");
		innerAccordion1.click();
		try{
			Thread.sleep(1000);
		}catch(Exception e){}
		WebElement innerAccordionContent1 = getElement("{\"element\":\"#ic1\",\"subId\":\"oj-collapsible-content\"}");
		Assert.assertEquals("I'm collapsible inside an inner accordion.",innerAccordionContent1.getText());
		Assert.assertTrue(innerAccordionContent1.isDisplayed());
	}
	
	@Test(groups = { "cookbook" })
	public void eventAccordion() throws Exception {

		//Start the test and bring up the browser
		startupTest("demo-accordion-events.html",null);
		verifyTitle("Incorrect page title;", "Accordion - Events");
		waitForElementVisible("id=a1");
		WebElement eventAccordionDiv = getElement("{\"element\":\"#c1\",\"subId\":\"oj-collapsible-header-icon\"}");
		//Expand the  accordion 
		eventAccordionDiv.click(); 
		try{
			Thread.sleep(1000);
		}catch(Exception e){}
		
		waitForElementVisible("id=eventlog");
		String eventLog = evalJavascript("return $('#eventlog').ojTextArea('option', 'value')");
		//Get event log
		// WebElement eventLog = getElement("id=eventlog");
		System.out.println("####### eventLog: " + eventLog);
		
		
		
		
		boolean beforecollapse = eventLog.indexOf("ojbeforecollapse: a1 data { fromCollapsible: c3 toCollapsible: c1}") > -1;
		boolean beforeExpandLog = eventLog.indexOf("ojbeforeexpand: a1 data { fromCollapsible: c3 toCollapsible: c1}") > -1;
		boolean collapselog = eventLog.indexOf("ojcollapse: a1 data { fromCollapsible: c3 toCollapsible: c1}") > -1;
		boolean expandlog = eventLog.indexOf("ojexpand: a1 data { fromCollapsible: c3 toCollapsible: c1}") > -1;
		Assert.assertTrue(beforecollapse, "ojbeforecollapse event called with correct parameters");
		Assert.assertTrue(beforeExpandLog, "ojbeforeExpandLog event called with correct parameters");
		Assert.assertTrue(collapselog, "ojcollapselog event called with correct parameters");
		Assert.assertTrue(expandlog, "ojexpandlog event called with correct parameters");
		
		//Collapse the  collapsible 
		eventAccordionDiv.click(); 
		
		try{
			Thread.sleep(1000);
		}catch(Exception e){}
		
		
		//ClearLog
		WebElement div = getElement("id=a1p");
		WebElement clearLog = null;
		List<WebElement> buttons = div.findElements(By.tagName("button"));

		for (WebElement btn : buttons) {
			WebElement span = btn.findElement(By.tagName("span"));
			String btnLabel = span.getText();
			System.out.println("#######***** BUTTON LABEL: " + btnLabel);
			switch (btnLabel) {

			case "Clear log":
				clearLog = btn;
				break;
			default:
				break;
			}

		}
		clearLog.click();
		try{
				Thread.sleep(1000);
			}catch(Exception e){} 
			
	} 
	private void log(String log)
	    {
	        System.out.println(log);
			getLogger().fine("[AccordionTest] " + log);
    }

    

}
