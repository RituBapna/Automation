package oj.tests.dialog;
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
//import org.openqa.selenium.interactions.Windo;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
//import org.openqa.selenium.ActionsChains;
import org.openqa.selenium.JavascriptExecutor;


public class CookbookDialogTest extends OJetBase {
    
	private static final String MODAL_DIALOG_TITLE = "Dialog: Modal Dialog";
	//private static final String BASIC_DEMO = "This demo showcases a basic accordion.";
	
	
    public CookbookDialogTest() {
        super(new TestConfigBuilder().setContextRoot("components").setAppRoot("public_html").build());
    }
	@Test(groups = { "cookbook" })
	public void modalDialogMethod() throws Exception {
			//Start the test and bring up the browser
			startupTest("uiComponents-dialog-modal.html",null);
			verifyTitle("Incorrect page title;", "Dialog - Modal Dialog");
			waitForElementVisible("id=buttonOpener");
			WebElement modalDialogOpenerbutton = getElement("id=buttonOpener");
			//open the modal dialog after button click
			modalDialogOpenerbutton.click();
			waitForElementVisible("id=modalDialog1");
			WebElement modalDialogHeader = getElement("{\"element\":\"#modalDialog1\",\"subId\":\"oj-dialog-header\"}");
			Assert.assertEquals("Modal Dialog",modalDialogHeader.getText());
			WebElement modalDialogbody = getElement("{\"element\":\"#modalDialog1\",\"subId\":\"oj-dialog-body\"}");
			Assert.assertEquals("The dialog window can be moved, resized and closed with the 'x' icon. Arbitrary content can be added to the the oj-dialog-body and oj-dialog-footer sections.",
			modalDialogbody.getText());
			String str=evalJavascript("return $('#buttonOpener').ojButton('option','disabled')");
			
			//since it is the modal dialog, so nothing is click-able outside dialog
			//code to check modal doalog
			String notClickableTestStr=null;
			String isClickable=null;
			try{
				modalDialogOpenerbutton.click();
			}
			catch(Exception e){
				notClickableTestStr="Element is not clickable due to overlay effect";
				System.out.println("++++++++++ is clickable check +++++++++++");
				System.out.println( notClickableTestStr);
			} 
				
				if("Element is not clickable due to overlay effect".equals(notClickableTestStr))
					{
					isClickable="Element is not click-able since it is modal dialog";
					}
				 else
					{
					isClickable="Element is clickable";
					}
				Assert.assertEquals(isClickable,"Element is not click-able since it is modal dialog");
				WebElement okButton = getElement("id=okButton");
				okButton.click();
				 try{
					Thread.sleep(1000);
				}catch(Exception e){}
				modalDialogOpenerbutton.click();
				try{
					Thread.sleep(1000);
				}catch(Exception e){}
 		
		}
	@Test(groups = { "cookbook" })
	public void modelessDialogMethod() throws Exception {
			//Start the test and bring up the browser
			startupTest("uiComponents-dialog-modeless.html",null);
			verifyTitle("Incorrect page title;", "Dialog - Modeless Dialog");
			waitForElementVisible("id=buttonOpener");
			WebElement modelessDialogOpenerbutton = getElement("id=buttonOpener");
			//open the modal dialog after button click
			modelessDialogOpenerbutton.click();
			waitForElementVisible("id=modelessDialog1");
			WebElement modalDialogHeader = getElement("{\"element\":\"#modelessDialog1\",\"subId\":\"oj-dialog-header\"}");
			Assert.assertEquals("Modeless Dialog",modalDialogHeader.getText());
			WebElement modelessDialogbody = getElement("{\"element\":\"#modelessDialog1\",\"subId\":\"oj-dialog-body\"}");
			Assert.assertEquals("This is an example of a modeless dialog. Interaction with other window areas and components is possible when a modeless dialog is displayed.",
			modelessDialogbody.getText());
			String str=evalJavascript("return $('#buttonOpener').ojButton('option','disabled')");
			
			//since it is the modal dialog, so nothing is click-able outside dialog
			//code to check modal doalog
			String clickableTestStr=null;
			String isClickable=null;
			try{
				modelessDialogOpenerbutton.click();
				System.out.println("++++++++++ is clickable check--element is clickable when dialog is modeless +++++++++++");
				clickableTestStr="Element is clickable since it is a modeless dialog";
				System.out.println( clickableTestStr);
			}
			catch(Exception e){
				clickableTestStr="Element is not clickable due to overlay effect";
				System.out.println("++++++++++ is clickable check +++++++++++");
				System.out.println( clickableTestStr);
			} 
				
				if("Element is clickable since it is a modeless dialog".equals(clickableTestStr))
					{
					isClickable="clickable-Modeless Dialog";
					}
				 else
					{
					isClickable="non clickable-Modal Dialog";
					}
				Assert.assertEquals(isClickable,"clickable-Modeless Dialog");
				WebElement closeicon = getElement("{\"element\":\"#modelessDialog1\",\"subId\":\"oj-dialog-header-close-wrapper\"}");
				//WebElement okButton = getElement("id=dialog header-close icon");
				closeicon.click();
				 try{
					Thread.sleep(1000);
				}catch(Exception e){}
				modelessDialogOpenerbutton.click();
				try{
					Thread.sleep(1000);
				}catch(Exception e){}
				//System.Windows.Forms.SendKeys.SendWait("{ESC}");
				//System.Window.Forms.SendKeys.SendWait("{ESC}");
 		
		} 
@Test(groups = { "cookbook" })
	public void dialogCancelBehaviorMethod() throws Exception {
			//Start the test and bring up the browser
			startupTest("uiComponents-dialog-cancelBehavior.html",null);
			verifyTitle("Incorrect page title;", "Dialog - Dialog Cancel Behavior");
			waitForElementVisible("id=buttonOpener");
			WebElement dialogCancelBehaviorOpnerButton = getElement("id=buttonOpener");
			//open the modal dialog after button click
			dialogCancelBehaviorOpnerButton.click();
			waitForElementVisible("id=dialog1");
			WebElement dialogCancelBehaviorHeader = getElement("{\"element\":\"#dialog1\",\"subId\":\"oj-dialog-header\"}");
			Assert.assertEquals("Dialog - no Close Icon",dialogCancelBehaviorHeader.getText());
						
				try{
					Thread.sleep(1000);
				}catch(Exception e){}
				System.out.println("++++++++++ Getwebdriver Cancel Behavior Message +++++++++++");
				String cancelOption=evalJavascript("return $('#dialog1').ojDialog('option','cancelBehavior')");
                Assert.assertEquals("escape",cancelOption);
				new Actions(getWebDriver()).sendKeys(Keys.ESCAPE).build().perform();
				try{
					Thread.sleep(100);
				}catch(Exception e){}
				//
				//Sting cancelOption=evalJavascript("return $('" + locator + "').ojDialog('option','escape')"));
				dialogCancelBehaviorOpnerButton.click();
				try{
					Thread.sleep(100);
				}catch(Exception e){}
		
		WebElement slider = getElement("{\"element\":\"#dialog1\",\"subId\":\"oj-resizable-e\"}");
		
	 	try{
					Thread.sleep(1000);
				}catch(Exception e){}
				WebElement okButtonModelessDialog = getElement("id=okButton");
				//WebElement okButton = getElement("id=dialog header-close icon");
				okButtonModelessDialog.click();
				try{
					Thread.sleep(1000);
				}catch(Exception e){}
			
		} 
		
	@Test(groups = { "cookbook" })
	public void dialogWithUserDefinedHeaderMethod() throws Exception {
			//Start the test and bring up the browser
			startupTest("uiComponents-dialog-userDefinedHeader.html",null);
			verifyTitle("Incorrect page title;", "Dialog - Dialog with User Defined Header");
			waitForElementVisible("id=buttonOpener");
			WebElement dialogWithUserDefinedHeaderOpnerButton = getElement("id=buttonOpener");
			//open the modal dialog after button click
			dialogWithUserDefinedHeaderOpnerButton.click();
			waitForElementVisible("id=dialogWithUserDefinedHeader");
			WebElement dialogWithUserDefinedHeader = getElement("{\"element\":\"#dialogWithUserDefinedHeader\",\"subId\":\"oj-dialog-header\"}");
			Assert.assertEquals("User-defined Dialog Header",dialogWithUserDefinedHeader.getText());
			WebElement element = getWebDriver().findElement(By.className("oj-dialog-header"));
            element.getText();
			Assert.assertEquals(element.getText(), "User-defined Dialog Header");
			Assert.assertTrue(element != null);
			System.out.println("++++++++++ element By.className +++++++++++");
			System.out.println(element);
						
				try{
					Thread.sleep(1000);
				}catch(Exception e){}
			
		} 
		@Test(groups = { "cookbook" })
	public void dialogFooterMethod() throws Exception {
			//Start the test and bring up the browser
			startupTest("uiComponents-dialog-footer.html",null);
			verifyTitle("Incorrect page title;", "Dialog - Dialog Footer");
			WebElement footerAddCheckbox = getElement("id=checkboxSet");
			//getWebDriver().findElement(By.id("checkboxSet")).click();
			String boxCheckedOrNotTest=evalJavascript("return $('#checkboxSet').ojCheckboxset('option','value')");
			System.out.println("++++++++++ evalJavascript ojCheckboxset +++++++++++");
			System.out.println(boxCheckedOrNotTest);
			//Checking when checkbox is not checked, it should not have "oj-dialog-footer-separator" class on div
			try{
			WebElement checkboxUncheckedFooterseperatorClass = getWebDriver().findElement(By.className("oj-dialog-footer-separator"));
			System.out.println("++++++++++ findElement checkboxUncheckedFooterseperatorClass +++++++++++");
			System.out.println(checkboxUncheckedFooterseperatorClass);
			}
			catch(Exception e)
			{
			System.out.println("Cannot get id");
			}
			//When checkbox is checked, it should  have "oj-dialog-footer-separator" class on div
			getWebDriver().findElement(By.id("checkboxSet")).click();
			boxCheckedOrNotTest=evalJavascript("return $('#checkboxSet').ojCheckboxset('option','value')");
			System.out.println("++++++++++ evalJavascript ojCheckboxset +++++++++++");
			System.out.println(boxCheckedOrNotTest);
			WebElement checkboxFooterseperatorClass = getWebDriver().findElement(By.className("oj-dialog-footer-separator"));
			System.out.println("++++++++++ findElement checkboxFooterseperatorClass +++++++++++");
			System.out.println(checkboxFooterseperatorClass);
									
				try{
					Thread.sleep(1000);
				}catch(Exception e){}
			
		} 
		@Test(groups = { "cookbook" })
	public void dialogDimensionMethod() throws Exception {
			//Start the test and bring up the browser
			startupTest("uiComponents-dialog-dimensions.html",null);
			verifyTitle("Incorrect page title;", "Dialog - Dialog Dimensions");
			WebElement minMaxDialogDiv = getElement("id=minMaxDialog");
			String style = minMaxDialogDiv.getAttribute("style");
			System.out.println("++++++++++ minMaxDiv Style +++++++++++");
			System.out.println(style);
			String strRootAttr=evalJavascript("return $('#minMaxDialog').ojDialog('option','rootAttributes')");
			System.out.println("++++++++++ evalJavascript minMaxDialog +++++++++++");
			System.out.println(strRootAttr);
			//rootAttributes: { style: 'width: 300px; height: 200px; min-width 240px; max-width: 350px; min-height: 100px; max-height: 300px;'}
			String[] words = strRootAttr.split(";");
			System.out.println(words[2]);
			Assert.assertEquals(" min-width 240px",words[2]);
			System.out.println(words[2].substring(11,16));
			Assert.assertEquals("240px",words[2].substring(11,16));
			//((JavascriptExecutor)getWebDriver()).executeScript("document.getElementById('minMaxDialog').style.height = '200px';");
			Object elemen=((JavascriptExecutor)getWebDriver()).executeScript("document.getElementById('minMaxDialog').style;");
			System.out.println("---------------elemen----------");
			System.out.println(((JavascriptExecutor)getWebDriver()).executeScript("document.getElementById('minMaxDialog').style;"));
	
		} 
		//TO DO dialogPercentDimensionsMethod
		//TO DO dialogwithScrolledContentMethod
		@Test(groups = { "cookbook" })
		 public void nestedDialogMethod() throws Exception {
			//Start the test and bring up the browser
			startupTest("uiComponents-dialog-nested.html",null);
			verifyTitle("Incorrect page title;", "Dialog - Nested Dialog");
			waitForElementVisible("id=buttonOpener");
			 try{
					Thread.sleep(1000);
				}catch(Exception e){}
			WebElement outerDialogButton = getElement("id=buttonOpener");
			//open the outer dialog after button click
			outerDialogButton.click();
			WebElement outerDialogHeader = getElement("{\"element\":\"#outerDialog\",\"subId\":\"oj-dialog-header\"}");
			Assert.assertEquals("Outer Dialog",outerDialogHeader.getText());
			waitForElementVisible("id=outerDialog"); 
			WebElement innerDialogButton = getElement("id=buttonOpenerNested");
			innerDialogButton.click();
			WebElement innerDialogHeader = getElement("{\"element\":\"#innerDialog\",\"subId\":\"oj-dialog-header\"}");
			Assert.assertEquals("Dialog Inner",innerDialogHeader.getText());
			WebElement innerDialogBody = getElement("{\"element\":\"#innerDialog\",\"subId\":\"oj-dialog-body\"}");
			Assert.assertEquals("Inner Dialog",innerDialogBody.getText());
			try{
					Thread.sleep(1000);
				}catch(Exception e){}
		} 
	
	@Test(groups = { "cookbook" })
		 public void progressbarWithinDialogMethod() throws Exception {
			//Start the test and bring up the browser
			startupTest("uiComponents-dialog-progressbar.html",null);
			verifyTitle("Incorrect page title;", "Dialog - Progressbar within Dialog");
			waitForElementVisible("id=buttonOpener");
			 try{
					Thread.sleep(1000);
				}catch(Exception e){}
			WebElement progressbarDialogButton = getElement("id=buttonOpener");
			//open the outer dialog after button click
			progressbarDialogButton.click();
			WebElement progressbarDialogHeader = getElement("{\"element\":\"#dialog1\",\"subId\":\"oj-dialog-header\"}");
			Assert.assertEquals("Dialog with Progress Bar",progressbarDialogHeader.getText());
			waitForElementVisible("id=progressbar");
			//waitForElementVisible("id=progressbar"); 
			System.out.println("+++++ progressbar value +++++++++");
			//System.out.println(getWebDriver().findElement(By.id("progressbar")).isDisplayed());
			WebElement checkboxFooterseperatorClass = getWebDriver().findElement(By.className("oj-dialog-body-header"));
			System.out.println(checkboxFooterseperatorClass);
			//waitForElementVisible("id=progressbar");
			//String val = evalJavascript("return $('#progressbar').ojProgressbar('option','value')");
			try{
					Thread.sleep(1000);
				}catch(Exception e){}
		} 
	
	private void log(String log)
	    {
	        System.out.println(log);
			getLogger().fine("[dialogPage] " + log);
    }

    

}
