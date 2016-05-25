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


public class FilmstripOptionTest extends OJetBase {

    private static final String TITLE = "Jet ojFilmstrip Test";

    public FilmstripOptionTest() {
        super(new TestConfigBuilder().setContextRoot("layoutNavigation").setAppRoot("filmstripTest").build());
    }
    
    private static final String TEST_PAGE = "demo-combobox-single.html";
    private static final String TEST_PAGE_TITLE = "Combobox - Single Select";
    private static final String COMBOBOX_ID = "combobox"; //inputText
    private static final String SPAN_ID = "currItemValSpanID"; //inputText


    @Test(groups = { "filmstripTest" })
    public void testFilmstrip() throws Exception {
        //Start the test and bring up the browser
        startupTest("filmstripOptionTest.html",null);
        getWebDriver().manage().window().maximize();
        waitForMilliseconds(500L);
		String url = getBrowserUrl();

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE);
        waitForElementVisible("id=filmStripArrow");
        /*
        Checking for arrow placement value 
        */
        String arrowPlacement = evalJavascript("return $('#filmStripArrow').ojFilmStrip('option', 'arrowPlacement')");
        Assert.assertEquals("adjacent", arrowPlacement);
        
        /*
        Setting arrow placement value to overlay
        */
        WebElement overlayopt = getElement("id=overlayopt");
        overlayopt.click();
        waitForMilliseconds(500L);
        Assert.assertEquals("overlay", evalJavascript("return $('#filmStripArrow').ojFilmStrip('option', 'arrowPlacement')"));
        
        /*
        Checking arrow visibility value 
        */
        
        String arrowVisibility="";
        arrowVisibility = evalJavascript("return $('#filmStripArrow').ojFilmStrip('option', 'arrowVisibility')");
        Assert.assertEquals("visible", arrowVisibility);
        
        /*
        Setting arrow visibility value to auto
        */
        WebElement autoopt = getElement("id=autoopt");
        autoopt.click();
        waitForMilliseconds(500L);
        arrowVisibility = evalJavascript("return $('#filmStripArrow').ojFilmStrip('option', 'arrowVisibility')");
        Assert.assertEquals("auto", arrowVisibility);
        waitForMilliseconds(500L);
                
        /*
        Checking currentItem value
        */
        
        String currentItem="";
        currentItem = evalJavascript("return $('#filmStripArrow').ojFilmStrip('option', 'currentItem')");
        Assert.assertEquals("0", currentItem);
        waitForMilliseconds(500L);
        
        /*
        Clicking on next button to move to next page
        */
        WebElement nextIconDiv=getWebDriver().findElement(By.xpath("//div[contains(@title,'Next')]"));
        nextIconDiv.click();
        
        /*
        Setting maxItemsPerPage to 5
        */
        String maxItemPerPage=evalJavascript("return $('#filmStripArrow').ojFilmStrip('option', 'maxItemsPerPage',5)");
        waitForMilliseconds(200L);
        
        /*
        Checking maxItemPerPage value is 5
        */
        System.out.println("*** #####:Max item per page is  " + maxItemPerPage);
        maxItemPerPage = evalJavascript("return $('#filmStripArrow').ojFilmStrip('option', 'maxItemsPerPage')");
        Assert.assertEquals("5", maxItemPerPage);
        
        /*
        Calling method containsString
        Method checks items are visible on the page currently
        */
        WebElement textValues = getElement("id=filmStripArrow");
        containsString(textValues.getText());
        waitForMilliseconds(400L);
        
        /*
        Method call
        Getting focus into como input elemet and clicking on it
        fetching listItems, that is returning the list of elements in the combobox
        Clicking on the desired item index to select the element
        Checking for selected list item text value
        
        */
        
       // changeValueForCombo("currentItemComboId","curr-value");
        
        changeValueForCombo("maxItemsPerPageComboId","maxPage");
        
        changeValueForCombo("orientationComboId","orientation");
        
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
    //System.out.println("***resultset #####: " + listItems.size());
      /*if(spanId.equals("orientation"))
      {
        listItems.get(0).click();
        System.out.println("***  Inside if: " + spanId);
      }
      else*/
          listItems.get(0).click();
    this.waitForMilliseconds(1000);
    String spanText = span.getText();
    //System.out.println("*** #####:spanIdspanId spanId " + span.getText());
    //Assert.assertEquals("[\"3\"]", spanText);
  }  
    
}

