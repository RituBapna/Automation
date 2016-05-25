package oj.tests.filmstrip;
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

public class CookbookFilmstripTest extends OJetBase {
    public CookbookFilmstripTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook" })
    public void filmstripWithNavigationArrowTest() throws Exception {
        startupTest("demo-filmStrip-filmStripNavArrows.html", null);
        verifyTitle("Incorrect page title;","Film Strip - FilmStrip with Navigation Arrows");
        waitForElementVisible("id=filmstrip-navarrows-example");
        waitForElementVisible("id=filmStrip");
        /*Process p=Runtime.getRuntime().exec("");
        p.waitFor();*/
        
        String orientation = evalJavascript("return $('#filmStrip').ojFilmStrip('option', 'orientation')");
        Assert.assertEquals("horizontal", orientation);
        //System.out.println("orientation :: "+ orientation);
        
        waitForElementVisible("id=overlayopt");
        
        //Checking for arrowPlacement value equal to 'adjacent'
        String arrowPlacement = evalJavascript("return $('#filmStrip').ojFilmStrip('option', 'arrowPlacement')");
        Assert.assertEquals("adjacent", arrowPlacement);
        
        //Changing arrowPlacement value to 'overlay'
        WebElement overlayopt = getElement("id=overlayopt");
        overlayopt.click();
        waitForMilliseconds(5000L);
        arrowPlacement= evalJavascript("return $('#filmStrip').ojFilmStrip('option', 'arrowPlacement')");
        //System.out.println("++++++++++++++++arrowPlacement+++++++++++++++++++++ :: "+ arrowPlacement);
        Assert.assertEquals("overlay", evalJavascript("return $('#filmStrip').ojFilmStrip('option', 'arrowPlacement')"));
        
        //Checking for arrowVisibility value equal to 'auto'
        String arrowVisibility="";
        arrowVisibility = evalJavascript("return $('#filmStrip').ojFilmStrip('option', 'arrowVisibility')");
        Assert.assertEquals("auto", arrowVisibility);
        
        //Changing arrowPlacement value to 'overlay'
        WebElement visibleopt = getElement("id=visibleopt");
        visibleopt.click();
        arrowVisibility = evalJavascript("return $('#filmStrip').ojFilmStrip('option', 'arrowVisibility')");
        Assert.assertEquals("visible", arrowVisibility);
        
        WebElement fsEndArrow = getElement("{\"element\":\"#filmStrip\",\"subId\":\"oj-filmstrip-end-arrow\"}");
        fsEndArrow.click();// click the end arrow to move forward filmstrip
        
        WebElement fsStartArrow = getElement("{\"element\":\"#filmStrip\",\"subId\":\"oj-filmstrip-start-arrow\"}");
       // fsStartArrow.click();// click the end arrow to move forward filmstrip
        Assert.assertEquals(false, fsStartArrow.isDisplayed());
        fsEndArrow.click();// will not move filmstrip since arrows are hidden.
         
    }

  @Test(groups = { "cookbook" })
    public void verticalFilmstripWithNavigationArrowTest() throws Exception {
        startupTest("demo-filmStrip-verticalFilmStripNavArrows.html", null);
        verifyTitle("Incorrect page title;","Film Strip - Vertical FilmStrip with Navigation Arrows");
        
        waitForElementVisible("id=filmstrip-verticalnavarrows-example");
        waitForElementVisible("id=filmStrip");
        WebElement filmStripDiv = getElement("id=filmStrip");
        WebElement fsBottomArrow = getElement("{\"element\":\"#filmStrip\",\"subId\":\"oj-filmstrip-bottom-arrow\"}");
        WebElement fsTopArrow = getElement("{\"element\":\"#filmStrip\",\"subId\":\"oj-filmstrip-top-arrow\"}");
        
        
        String filmstripFullText = getElement("id=filmStrip").getText();
        
        passNoOfArgsBasedOnStringLength("filmStrip");
        fsBottomArrow.click();
        waitForMilliseconds(2000L);
        
        passNoOfArgsBasedOnStringLength("filmStrip");
        
        fsBottomArrow.click();
        waitForMilliseconds(2000L);
        passNoOfArgsBasedOnStringLength("filmStrip");
        
        fsTopArrow.click();
        waitForMilliseconds(2000L);
        passNoOfArgsBasedOnStringLength("filmStrip");
        
        fsBottomArrow.click();
        waitForMilliseconds(2000L); 
       
        
    }
     @Test(groups = { "cookbook" })
    public void filmstripWithPagingControlTest() throws Exception {
        startupTest("demo-filmStrip-filmStripNavDots.html", null);
        verifyTitle("Incorrect page title;","Film Strip - FilmStrip with Paging Control");
       
        waitForElementVisible("id=filmstrip-navdots-example");
        waitForElementVisible("id=filmStrip");
        waitForElementVisible("id=pagingControl");
        
        String filmstripFullText = getElement("id=filmStrip").getText();
        
        passNoOfArgsBasedOnStringLength("filmStrip");
        goToPage("Go To Page 2");
        
        passNoOfArgsBasedOnStringLength("filmStrip");
        goToPage("Go To Page 3");
        
        passNoOfArgsBasedOnStringLength("filmStrip");
        goToPage("Go To Page 1");
        //evalJavascript("return $('#filmStrip').ojFilmStrip('getPagingModel').getWindow()");
    }
    
    @Test(groups = { "cookbook" })
    public void verticalFilmstripWithPagingControlTest() throws Exception {
        startupTest("demo-filmStrip-verticalFilmStripNavDots.html", null);
        verifyTitle("Incorrect page title;","Film Strip - Vertical FilmStrip with Paging Control");
        
        waitForElementVisible("id=filmstrip-verticalnavdots-example");
        waitForElementVisible("id=filmStrip");
        waitForElementVisible("id=pagingControl");
        
        passNoOfArgsBasedOnStringLength("filmStrip");
        goToPage("Go To Page 2");
        
        passNoOfArgsBasedOnStringLength("filmStrip");
        goToPage("Go To Page 3");
        
        passNoOfArgsBasedOnStringLength("filmStrip");
        goToPage("Go To Page 1");
        
        passNoOfArgsBasedOnStringLength("filmStrip");
        goToPage("Go To Page 4");
        
    }
    
    @Test(groups = { "cookbook" })
    public void masterDetailTest() throws Exception {
        startupTest("demo-filmStrip-masterDetailFilmStrip.html", null);
        verifyTitle("Incorrect page title;","Film Strip - Master-Detail");
        waitForElementVisible("id=filmstrip-masterdetail-example");
        waitForElementVisible("id=detailFilmStripDiv");
        waitForElementVisible("id=detailFilmStrip");
        waitForElementVisible("id=masterFilmStripDiv");
        waitForElementVisible("id=masterFilmStrip");
        
        WebElement detailEndArrow = getElement("{\"element\":\"#detailFilmStrip\",\"subId\":\"oj-filmstrip-end-arrow\"}");
        WebElement masterEndArrow = getElement("{\"element\":\"#masterFilmStrip\",\"subId\":\"oj-filmstrip-end-arrow\"}");
        
        Assert.assertEquals("Hydrogen", getElement("id=detailFilmStrip").getText());
        detailEndArrow.click();
        waitForMilliseconds(2000L); 
        
        Assert.assertEquals("Helium", getElement("id=detailFilmStrip").getText());
        detailEndArrow.click();
        waitForMilliseconds(2000L);
        
        Assert.assertEquals("Lithium", getElement("id=detailFilmStrip").getText());
        WebElement detailStartArrow = getElement("{\"element\":\"#detailFilmStrip\",\"subId\":\"oj-filmstrip-start-arrow\"}");
        detailStartArrow.click();
        waitForMilliseconds(2000L);
        
        Assert.assertEquals("Helium", getElement("id=detailFilmStrip").getText());
        detailStartArrow.click();
        waitForMilliseconds(2000L);
        
        String masterFullText = getElement("id=masterFilmStrip").getText();
        passNoOfArgsBasedOnStringLength("masterFilmStrip");
        
        masterEndArrow.click();
        waitForMilliseconds(2000L);
        
        WebElement masterStartArrow = getElement("{\"element\":\"#masterFilmStrip\",\"subId\":\"oj-filmstrip-start-arrow\"}");
    }
     @Test(groups = { "cookbook" })
    public void lazyRenderingTest() throws Exception {
        startupTest("demo-filmStrip-filmStripLazyRendering.html", null);
        verifyTitle("Incorrect page title;","Film Strip - Lazy Rendering");
        waitForElementVisible("id=filmstrip-lazyrendering-example");
        waitForElementVisible("id=filmStrip");
        waitForElementVisible("id=pagingControl");
        
        Assert.assertEquals("Hydrogen", getElement("id=filmStrip").getText());
        goToPage("Go To Page 2");
        
        Assert.assertEquals("Helium", getElement("id=filmStrip").getText());
        goToPage("Go To Page 6");
        
        Assert.assertEquals("Carbon", getElement("id=filmStrip").getText());
        goToPage("Go To Page 1");//stringContainsCheck("Boron","Carbon","Beryllium");
        
        Assert.assertEquals("Hydrogen", getElement("id=filmStrip").getText());
    }
    
     @Test(groups = { "cookbook" })
    public void displayPagingInformationTest() throws Exception {
        startupTest("demo-filmStrip-filmStripPagingText.html", null);
        verifyTitle("Incorrect page title;","Film Strip - Display Paging Information");
        waitForElementVisible("id=filmstrip-pagingtext-example");
        waitForElementVisible("id=filmStrip");
        String startIndex=evalJavascript("return $('#filmStrip').ojFilmStrip('getPagingModel').getStartItemIndex()+1");
        String endIndex=evalJavascript("return $('#filmStrip').ojFilmStrip('getPagingModel').getEndItemIndex() +1");
    }
    
    public void passNoOfArgsBasedOnStringLength(String id){
            String filmstripFullText = getElement("id="+id).getText();
            String[] fsSplittedArray=filmstripFullText.split("\\r?\\n");
            System.out.println("++++++++++ 1st ++++++"+ fsSplittedArray[0]+"2nd item" +fsSplittedArray[1]);

            if(fsSplittedArray.length==1){
               variableArgs(filmstripFullText,fsSplittedArray[0]);  
            }
            else if(fsSplittedArray.length==2){
                 variableArgs(filmstripFullText,fsSplittedArray[0],fsSplittedArray[1]);
            }
            else if(fsSplittedArray.length==3){
                 variableArgs(filmstripFullText,fsSplittedArray[0],fsSplittedArray[1],fsSplittedArray[2]);
            }
             
             else if(fsSplittedArray.length==4){
                 variableArgs(filmstripFullText,fsSplittedArray[0],fsSplittedArray[1],fsSplittedArray[2],fsSplittedArray[3]);
            }
        
            else{
             variableArgs(filmstripFullText,fsSplittedArray[0],fsSplittedArray[1],fsSplittedArray[2],fsSplittedArray[3],fsSplittedArray[4]);
            }
        }
    
    public void variableArgs(String fullText,String... args) {
            for (String str : args) {
                Assert.assertTrue(fullText.contains(str));
            }
        }
    
    public void  goToPage(String title) {
        WebElement pagingControl = getElement("id=pagingControl");
        List<WebElement> anchorTags = pagingControl.findElements(By.tagName("a"));
        for (WebElement anchorTag : anchorTags) {
            if (anchorTag.getAttribute("title").trim().equals(title)) {
                anchorTag.click();
                waitForMilliseconds(2000L);
                return;
            }
           
        }
      
    }
   
   /* public void  stringContainsTest(String str1,String str2) {
        String filmstipText=getElement("id=filmStrip").getText();
        Assert.assertTrue( filmstipText.contains(str1));
        Assert.assertTrue( filmstipText.contains(str2));
    }*/
   
   /*  public void  displayValCheck(String element) {
        WebElement filmStripDiv = getElement("id=filmStrip");
        WebElement textSpan=null;
        WebElement displayVal=null;
        textSpan=filmStripDiv.findElement(By.xpath("//span[text()='"+element+"']"));
        displayVal=filmStripDiv.findElement(By.xpath("//span[text()='" +element+"'] //ancestor::div[contains(@class, 'demo-filmstrip-item oj-filmstrip-item')]"));
        Assert.assertEquals(element, textSpan.getText());
        Assert.assertEquals("block", displayVal.getCssValue("display"));
        //$('.dateField-'+index).append('<input type="button" class="btn btn-info applyClass" value="Apply" onClick="applyResource('+index+')">');
    }*/

}
