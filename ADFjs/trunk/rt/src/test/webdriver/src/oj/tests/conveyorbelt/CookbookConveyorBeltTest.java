package oj.tests.conveyorbelt;
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

public class CookbookConveyorBeltTest extends OJetBase {

	private static final String ACCORDION_TITLE = "Accordion: Accordion";
	private static final String BASIC_DEMO = "This demo showcases a basic accordion.";
	
    public CookbookConveyorBeltTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components").setAppRoot("public_html").build());
    }
	@Test(groups = { "cookbook" })
	public void horizontalConveyorBeltMethod() throws Exception {

			//Start the test and bring up the browser
			

			startupTest("uiComponents-conveyorBelt-horizontalConveyorBelt.html",null);
			verifyTitle("Incorrect page title;", "Conveyor Belt - Horizontal");
			waitForElementVisible("id=conveyorbelt-horizontal-example");
			/* WebElement conveeyorBeltDiv = getElement("id=conveyorbelt-horizontal-example");
			String stylevar = conveeyorBeltDiv.getAttribute("style"); 
			System.out.println("#######Style######" + stylevar);*/
			WebElement header1 = getElement("{\"element\":\"#conveyorbelt-horizontal-example\",\"subId\":\"oj-conveyorbelt-end-overflow-indicator\"}");
			header1.click();
			
			try{
				Thread.sleep(100000);
			}catch(Exception e){}
			/*WebElement content1 = getElement("{\"element\":\"#collapsiblePage\",\"subId\":\"oj-collapsible-content\"}");
			Assert.assertEquals("I'm a Collapsible.",content1.getText());
			Assert.assertTrue(content1.isDisplayed());
			//Collapse first header-icon
			header1.click(); */
		} 
		 
	
	
	private void log(String log)
	    {
	        System.out.println(log);
			getLogger().fine("[collapsiblePage] " + log);
    }

    

}
