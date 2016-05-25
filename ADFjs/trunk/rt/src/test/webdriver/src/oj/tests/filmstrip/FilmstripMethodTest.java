package oj.tests.filmstrip;

import java.util.List;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.Select;

//http://localhost:7101/layoutNavigation/filmstripTest/filmStripMethodTest.html
public class FilmstripMethodTest extends OJetBase {

    private static final String TITLE = "Jet ojFilmstrip Test";

    public FilmstripMethodTest() {
        super(new TestConfigBuilder().setContextRoot("layoutNavigation").setAppRoot("filmstripTest").build());
    }
    
    
    @Test(groups = { "filmstripTest" })
    public void testFilmstrip() throws Exception {
        //Start the test and bring up the browser
        startupTest("filmstripMethodTest.html",null);
        getWebDriver().manage().window().maximize();
        waitForMilliseconds(5000L);
		String url = getBrowserUrl();

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE);
        waitForElementVisible("id=filmStripArrow");
        waitForElementVisible("id=selectionDiv");
        /*
        getting items per page
        */
        WebElement getItemPerPageButton = getElement("id=inputButton-4");
        getItemPerPageButton.click();
        WebElement spanIdForgetItemsPerPage = getElement("id=getItemsPerPageId");
        Assert.assertTrue(spanIdForgetItemsPerPage.getText().contains("No of items per page :: 2"));
        waitForMilliseconds(1000L);//Start Page Index :: 0 End Page Index :: 1
        
        /*
        getting start and end page index
        */
        WebElement getPageCountButton = getElement("id=inputButton-5");
        getPageCountButton.click();
        WebElement spanIdgetrPageCount = getElement("id=getPageCountId");
        Assert.assertTrue(spanIdgetrPageCount.getText().contains("No of page count :: 6"));
        waitForMilliseconds(1000L);
        
        /*
        getting start and end page index
        */
        WebElement getPageIndexButton = getElement("id=inputButton-6");
        getPageIndexButton.click();
        WebElement spanIdgetrPageIndex = getElement("id=getPageIndexId");
        Assert.assertTrue(spanIdgetrPageIndex.getText().contains("Start Page Index :: 0 End Page Index :: 1"));
        waitForMilliseconds(1000L);
         /*
        goto next page
        */
        WebElement goToPageButton = getElement("id=inputButton-7");
        goToPageButton.click();
        WebElement spanIdgoToPage = getElement("id=goToPageId");
        Assert.assertTrue(spanIdgoToPage.getText().contains("Current Page is:: 0"));
        waitForMilliseconds(1000L);
        
       
            
    }
    
    public void containsString(String params) {
    String[] splitedStrWithNewLine = params.split("\\n");
        for (int i=0;i<splitedStrWithNewLine.length;i++){
                Assert.assertTrue(params.contains(splitedStrWithNewLine[i]));
        }
    }
  public void changeValueForCombo(String comboId,String spanId) {
      System.out.println("***  spanId for orientation #####: " + comboId);
    waitForElementVisible("id="+spanId);
    WebElement comboInput =  getElement("{\"element\":\"#"+comboId+"\",\"subId\":\"oj-combobox-input\"}");
    WebElement span = getElement("id="+spanId);
    comboInput.click();
    this.waitForMilliseconds(1000);
    WebElement resultset=getElement("{\"element\":\"#"+comboId+"\",\"subId\":\"oj-combobox-results\"}");
    List<WebElement> listItems = resultset.findElements(By.tagName("li"));
    
          listItems.get(0).click();
    this.waitForMilliseconds(1000);
    String spanText = span.getText();
    //System.out.println("*** #####:spanIdspanId spanId " + span.getText());
    //Assert.assertEquals("[\"3\"]", spanText);
  }  
    
}

