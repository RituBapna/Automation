package oj.tests.table.paging;

import java.util.List;

import oj.tests.table.table.TestTableBase;

import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestTablePagingBase extends TestTableBase {

    public TestTablePagingBase(String myAppRoot, String myContextRoot, 
                         String pageTitle, String myPage, 
                         String myComp, String myPagingComp) {
        super(myAppRoot,myContextRoot,pageTitle,myPage,myComp);
        this.myPagingComp = myPagingComp;
    }

    public TestTablePagingBase(String myAppRoot, String myContextRoot, 
                         String pageTitle, String myPage, 
                         String myComp, String myPagingComp, String exposerId) {
        super(myAppRoot,myContextRoot,pageTitle,myPage,myComp,exposerId);
        this.myPagingComp = myPagingComp;
    }

    public void testPagingTable() throws Exception {
        //Start the test and bring up the browser
        startupTest(myPage, null);
        testPagingSingleTable();
    }

    public void testPagingLargeTable() throws Exception {
        startupTest(myPage, null);
        testPagingMultipleTable();
    }

    // default
    public void createTable() throws Exception {
        // super.createTable("Component created : paging","Component created : table");
        super.createTable("Component created : paging",
                          "Component created : table");
    }

    // default
    public void destroyTable() throws Exception {
        super.destroyTable("Component destroyed : paging","Component destroyed : table");
    }


    public void verifyPagingControl(String input, String max, String summary,
                                  String summaryCurrent, String summaryMax) throws Exception {
        
        waitForElementVisible(compLocator());
        
        waitForElementVisible(myPagingCompLocator());
                                
        // Now check that paging control shown and shows correct values
        Assert.assertEquals(getPagingInputMax(),max);
        Assert.assertEquals(getPagingSummary(),summary);
        Assert.assertEquals(getPagingSummaryCurrent(),summaryCurrent);
        Assert.assertEquals(getPagingSummaryMax(),summaryMax);
        // Assert.assertEquals(getPagingInput(),input);  Not working bug 20312841  try workaround here:        
        Assert.assertEquals(getPagingInputByCss(),input);                    
    }

    /*   Show More...1-10 of 45 items
    oj-pagingcontrol-load-more-link: load more link
    oj-pagingcontrol-load-more-range: load more range
    oj-pagingcontrol-load-more-max-rows: load more max message
    */
    
    public void verifyLoadMore(String range, String maxRows) throws Exception {
        waitForElementVisible(compLocator());       
                                
        // Now check that paging control shown and shows correct values        
        Assert.assertEquals(getLoadMoreRange(),range);
        // Assert.assertEquals(getLoadMoreMaxRows(),maxRows);
    }

    
    public void clickLoadMore() throws Exception {
        WebElement element = getPagingSubId("oj-pagingcontrol-load-more-link");        
        element.click();
    }

    public String getLoadMoreRange() throws Exception {
        WebElement element = getPagingSubId("oj-pagingcontrol-load-more-range");        
        return element.getText();
    }

    public String getLoadMoreMaxRows() throws Exception {
        WebElement element = getPagingSubId("oj-pagingcontrol-load-more-max-rows");        
        return element.getText();
    }


    public void testPagingSingleTable() throws Exception {
        // If inside collapsible, dialog, or popup, expose table first
        showComponent();
        
        verifyPagingControl("1","of 1","(1-4 of 4 items)","1-4","4");
        verifyTableValues(new String[] { "15", "QA", "200", "300" }, 
                              new String[] { "25", "Dev", "200", "300" },
                              new String[] { "35", "Marketing", "200", "300" },
                              new String[] { "45", "Shipping", "200", "300" });
    }

    public void goLargeData() throws Exception {
        // one click on toggleData button gives us 45 rows and 5 page of 10
        waitForElementVisible("id=buttonId1");        
        clickOn("id=buttonId1");
        waitForElementVisible(compLocator());        
        waitForElementVisible(myPagingCompLocator());        
        waitForComp();
//        waitForMilliseconds(1000L);  // let data load        
    }

    public void testPagingMultipleTable() throws Exception {
        showComponent();
        goLargeData();
        verifyPagingControl("1","of 5","(1-10 of 45 items)","1-10","45");
        verifyTableValues(new String[] { "10015", "ADFPM 1001 neverending", "200", "300" }, 
                              new String[] { "556", "BB", "200", "300" },
                              new String[] { "10", "Administration", "200", "300" },
                              new String[] { "20", "Marketing", "200", "300" },
                              new String[] { "30", "Purchasing", "200", "300" },
                              new String[] { "40", "Human Resources1", "200", "300" },
                              new String[] { "50", "Administration2", "200", "300" },
                              new String[] { "60", "Marketing3", "200", "300" },
                              new String[] { "70", "Purchasing4", "200", "300" },
                              new String[] { "80", "Human Resources5", "200", "300" });
        setPagingInput(5); 
        verifyPagingControl("5","of 5","(41-45 of 45 items)","41-45","45");
        /*
        verifyTableValues(new String[] {  "9022", "Human Resources11", "200", "300" },
                              new String[] { "10022", "Administration12", "200", "300" },
                              new String[] { "11022", "Marketing13", "200", "300" },
                              new String[] { "12022", "Purchasing14", "200", "300" },
                              new String[] { "13022", "Human Resources15", "200", "300" });*/
        clickPagingPrevious();
        verifyPagingControl("4","of 5","(31-40 of 45 items)","31-40","45");
/*        verifyTableValues(new String[] { "100111", "ADFPM 1001 neverending", "200", "300" }, 
                              new String[] { "55622", "BB", "200", "300" },
                              new String[] { "1022", "Administration", "200", "300" },
                              new String[] { "2022", "Marketing", "200", "300" },
                              new String[] { "3022", "Purchasing", "200", "300" },
                              new String[] { "4022", "Human Resources1", "200", "300" },
                              new String[] { "5022", "Administration2", "200", "300" },
                              new String[] { "6022", "Marketing3", "200", "300" },
                              new String[] { "7022", "Purchasing4", "200", "300" },
                              new String[] { "8022", "Human Resources5", "200", "300" }); */
        clickPagingNext();
        verifyPagingControl("5","of 5","(41-45 of 45 items)","41-45","45");
/*        verifyTableValues(new String[] {  "9022", "Human Resources11", "200", "300" },
                              new String[] { "10022", "Administration12", "200", "300" },
                              new String[] { "11022", "Marketing13", "200", "300" },
                              new String[] { "12022", "Purchasing14", "200", "300" },
                              new String[] { "13022", "Human Resources15", "200", "300" });        */
        clickPagingFirst();
        verifyPagingControl("1","of 5","(1-10 of 45 items)","1-10","45");
/*        verifyTableValues(new String[] { "10015", "ADFPM 1001 neverending", "200", "300" }, 
                              new String[] { "556", "BB", "200", "300" },
                              new String[] { "10", "Administration", "200", "300" },
                              new String[] { "20", "Marketing", "200", "300" },
                              new String[] { "30", "Purchasing", "200", "300" },
                              new String[] { "40", "Human Resources1", "200", "300" },
                              new String[] { "50", "Administration2", "200", "300" },
                              new String[] { "60", "Marketing3", "200", "300" },
                              new String[] { "70", "Purchasing4", "200", "300" },
                              new String[] { "80", "Human Resources5", "200", "300" }); */
        clickPagingLast();
        verifyPagingControl("5","of 5","(41-45 of 45 items)","41-45","45");
/*        verifyTableValues(new String[] {  "9022", "Human Resources11", "200", "300" },
                              new String[] { "10022", "Administration12", "200", "300" },
                              new String[] { "11022", "Marketing13", "200", "300" },
                              new String[] { "12022", "Purchasing14", "200", "300" },
                              new String[] { "13022", "Human Resources15", "200", "300" });        */

    }

    // Not overriding base, as no expected passed in, this tests footers in paged results
    public void testTableFooters() throws Exception {
        startupTest(myPage, null);
        showComponent();
        goLargeData();
        clickPagingLast();
        verifyTableFooters("Rows", "", "", "45");
    }

    public void testSort() throws Exception {
        //Start the test and bring up the browser
        startupTest(myPage, null);
        showComponent();
        goLargeData();
        waitForElementVisible(compLocator());
        super.testSort(true, false, 1, new String[] { "10015", "ADFPM 1001 neverending", "200", "300" },
                       new String[] { "1001", "ADFPM 1001 neverending", "200", "300" }, 
                       new String[] { "100111", "ADFPM 1001 neverending", "200", "300" });
    /*        super.testSort(false, false, 1, new String[] { "45", "Shipping", "200", "300" },
                       new String[] { "15", "QA", "200", "300" }, 
                       new String[] { "35", "Marketing", "200", "300" },
                       new String[] { "25", "Dev", "200", "300" });    */
    }


    public void testSortContextMenu() throws Exception {
        //Start the test and bring up the browser
        startupTest(myPage, null);
        showComponent();
        goLargeData();
        waitForElementVisible(compLocator());
        super.testSort(true, true, 1, new String[] { "10015", "ADFPM 1001 neverending", "200", "300" },
                       new String[] { "1001", "ADFPM 1001 neverending", "200", "300" }, 
                       new String[] { "100111", "ADFPM 1001 neverending", "200", "300" },
                       new String[] { "1011", "Administration", "200", "300" });
        super.testSort(false, true, 1, new String[] { "7022", "Purchasing4", "200", "300" },
                       new String[] { "70", "Purchasing4", "200", "300" }, 
                       new String[] { "7011", "Purchasing4", "200", "300" },
                       new String[] { "12022", "Purchasing14", "200", "300" });
    }



    public void logPagingInfo() throws Exception {
        getPagingSubId("oj-pagingcontrol-nav-input");    
        getPagingSubId("oj-pagingcontrol-nav-input-max");    
        getPagingSubId("oj-pagingcontrol-nav-input-summary");    
        getPagingSubId("oj-pagingcontrol-nav-input-summary-current");    
        getPagingSubId("oj-pagingcontrol-nav-input-summary-max");            
    }
    
    public WebElement getPagingSubId(String subId) throws Exception {
        return super.getSubId(myPagingComp,subId);
    }
    
    public String getPagingInput() throws Exception {
        WebElement element = getSubId("oj-pagingcontrol-nav-input");        
        return element.getText();
    }
    
    public String getPagingInputByCss() throws Exception {
        // $(".oj-pagingcontrol-nav-page").filter(".oj-selected").contents()[1]        
        // xpath variation is 
        // '//div[contains(@class,'oj-selected') and contains(@class,'oj-pagingcontrol-nav-page')]/text()'
        List<WebElement> elts = findElements("css=.oj-selected.oj-pagingcontrol-nav-page"); 
        WebElement pageNumElt = elts.get(0);
        String pageNum = pageNumElt.getText();
        // Strip "Page " from it
        String page = pageNum.substring(5);
        return page;
    }

    public void setPagingInput(int page) throws Exception {
        WebElement element = getPagingSubId("oj-pagingcontrol-nav-input");
        element.clear();
        element.sendKeys(String.valueOf(page));
        waitForMilliseconds(1000L);  // let data load        
        element = getPagingSubId("oj-pagingcontrol-nav-input");
        element.sendKeys(Keys.TAB);  // do this to dismiss popup which may hide table
        waitForMilliseconds(1000L);  // let window dismiss
    }

    public String getPagingInputMax() throws Exception {
        WebElement inputMax = getPagingSubId("oj-pagingcontrol-nav-input-max");                    
        return inputMax.getText();
    }

    public String getPagingSummary() throws Exception {
        WebElement inputSummary = getPagingSubId("oj-pagingcontrol-nav-input-summary");            
        return inputSummary.getText();
    }

    public String getPagingSummaryCurrent() throws Exception {
        WebElement inputSummaryCurrent = getPagingSubId("oj-pagingcontrol-nav-input-summary-current");            
        return inputSummaryCurrent.getText();
    }

    public String getPagingSummaryMax() throws Exception {
        WebElement inputSummaryMax = getPagingSubId("oj-pagingcontrol-nav-input-summary-max");
        return inputSummaryMax.getText();
    }

    public void clickPagingNext() throws Exception {
        WebElement next = getPagingSubId("oj-pagingcontrol-nav-next");
        next.click();
        waitForMilliseconds(1000L);  // let data load        
    }

    public void clickPagingPrevious() throws Exception {
        WebElement previous = getPagingSubId("oj-pagingcontrol-nav-previous");
        previous.click();
        waitForMilliseconds(1000L);  // let data load        
    }

    public void clickPagingLast() throws Exception {        
        WebElement last = getPagingSubId("oj-pagingcontrol-nav-last");
        last.click();
        waitForMilliseconds(1000L);  // let data load        
    }

    public void clickPagingFirst() throws Exception {        
        WebElement first = getPagingSubId("oj-pagingcontrol-nav-first");
        first.click();
        waitForMilliseconds(1000L);  // let data load        
    }

    public void clickPagingPage(int page) throws Exception {        
        WebElement navPage = getElement("{\"element\":\"#" + myPagingComp + "\",\"subId\":\"oj-pagingcontrol-nav-page\",\"index\":\"" + page + "\"}");
        navPage.click();
        waitForMilliseconds(1000L);  // let data load        
    }


    public void verifyCreateTable(String ... expected) throws Exception {
        super.verifyCreateTable(expected);
        
        waitForElementVisible(myPagingCompLocator());        
    }


    public void verifyDestroyTable(String ... expected) throws Exception {
        super.verifyDestroyTable(expected);
        
        boolean pagingCompShown = true;

        try{
                WebElement t1 = getElement(myPagingCompLocator());
                pagingCompShown = t1.isDisplayed();
        }catch(Exception e) {
                pagingCompShown = false;
        }

        Assert.assertFalse(pagingCompShown);
    }



    public String myPagingCompLocator() {
        return "id=" + myPagingComp;
    }


    protected String myPagingComp = "paging";
 
}
