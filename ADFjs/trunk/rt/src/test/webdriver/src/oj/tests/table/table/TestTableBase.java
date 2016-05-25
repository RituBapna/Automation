package oj.tests.table.table;

import oj.tests.common.TestCompBase;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriverException;

import org.testng.annotations.Test;

public abstract class TestTableBase extends TestCompBase {


    public TestTableBase(String myAppRoot, String myContextRoot, 
                         String pageTitle, String myPage, 
                         String myComp) {
        super(myAppRoot,myContextRoot,pageTitle,myPage,myComp);
    }

    public TestTableBase(String myAppRoot, String myContextRoot, 
                         String pageTitle, String myPage, 
                         String myComp, String exposerId) {
        super(myAppRoot,myContextRoot,pageTitle,myPage,myComp,exposerId);
    }

    // Redirected to base class, to avoid rewriting all the table tests    
    public void createTable(String ... expected) throws Exception {
        super.createComp(expected);
    }

    public void verifyCreateTable(String ... expected) throws Exception {
        super.verifyCreateComp(expected);
    }

    public void destroyTable(String ... expected) throws Exception {
        super.destroyComp(expected);
    }

    public void verifyDestroyTable(String ... expected) throws Exception {
        super.verifyDestroyComp(expected);
    }

    // Default 
    public void createTable() throws Exception {
        createTable("Component created : table");
    }

    // Default 
    public void destroyTable() throws Exception {
        destroyTable("Component destroyed : table");
    }

    // Default
    public void testTableFooters() throws Exception {
        testTableFooters("Rows", "", "", "4");
    }


    public void testTableHeaders(String ... headers) throws Exception {
        //Start the test and bring up the browser
        startupTest(myPage, null);
        showComponent();
        waitForComp();
        verifyTableHeaders(headers);                
    }

    // Default
    public void testTableHeaders() throws Exception {    
        testTableHeaders("Department Id", "Department Name", "Location Id", "Manager Id");
    }


    public void verifyTableHeaders(String ... headers) throws Exception {

        
        WebElement table = getElement(compLocator());
        log("###### table"+ table);
        
        int i = 0;
        for(String header : headers ) {
            WebElement dgHeader = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"" +i+ "\"}");
            String filtered = stripMsg(dgHeader.getText(),"\nSort Ascending");
            log("filtered header = " + filtered);
            Assert.assertEquals(filtered,header);  
            i++;
        }                                                
    }


    public void testTableValues(String[] ... rows) throws Exception {
        //Start the test and bring up the browser
        startupTest(myPage, null);
        showComponent();
        verifyTableValues(rows);                
    }

    // Default implementation
    public void testTableValues() throws Exception {
        testTableValues(new String[] { "15", "QA", "200", "300" }, 
                          new String[] { "25", "Dev", "200", "300" },
                          new String[] { "35", "Marketing", "200", "300" },
                          new String[] { "45", "Shipping", "200", "300" });
    }


    public void verifyTableValues(String[] ...rows) throws Exception {
        
        WebElement table = getElement(compLocator());
        log("###### table"+ table);
        
        int i = 0;
        for(String[] row : rows ) {
            testTableRowValues(i,row);
            i++;
        }
        
    }


    public void testTableRowValues(int row, String[] cells) {

        int i = 0;
        for(String cell : cells ) {
            WebElement rowNCol0 = getElement("{\"element\":\"#table\",\"subId\":\"oj-table-cell\",\"rowIndex\":\"" + row + "\",\"columnIndex\":\"" + i + "\"}");
            Assert.assertEquals(rowNCol0.getText(),cell);
            i++;
        }                
    }

    public void testTableFooters(String ... footers) throws Exception {
        //Start the test and bring up the browser
        startupTest(myPage, null);
        showComponent();
        verifyTableFooters(footers);
                        
    }
    
    
    public void verifyTableFooters(String ...footers) throws Exception {
        
        logTableFooters(footers);
        
        int i = 0;
        for(i = footers.length - 1; i >= 0; i--) {
            String els = "{\"element\":\"#table\",\"subId\":\"oj-table-footer\",\"index\":\"" +i+ "\"}";
            log("getElement = " + els);
            WebElement dgHeader = getElement(els);
            log("footer[" + i + "] innerHTML:" + dgHeader.getAttribute("innerHTML") + ", expecting: " + footers[i]);  
            log("footer[" + i + "]:" + dgHeader.getText() + ", expecting: " + footers[i]);  
            Assert.assertEquals(dgHeader.getText(),footers[i]);  
            Assert.assertEquals(dgHeader.getAttribute("innerHTML"),footers[i]);  
        }
    
    }

    public void logTableFooters(String ...footers) throws Exception {
        
        int i = 0;
        for(i = footers.length - 1; i >= 0; i--) {
            String els = "{\"element\":\"#table\",\"subId\":\"oj-table-footer\",\"index\":\"" +i+ "\"}";
            log("getElement = " + els);
            WebElement dgHeader = getElement(els);
            log("footer[" + i + "]:" + dgHeader.getText() + ", expecting: " + footers[i]);  
        }
    
    }

    
// Utility routines to test various aspects of tables
// Child classes can call these, but must first have page started, ie startupTest()
    
    public void testSort(Boolean ascending, Boolean useContextMenu, int index, String[] ... rows) throws Exception {

        sortTable(ascending, useContextMenu,index);
            
        int i = 0;
        for(String[] row : rows ) {
            testTableRowValues(i,row);
            i++;
            }                        
    }

    
    public void sortTable(Boolean ascending, Boolean useContextMenu, int index) throws Exception {
        String sortLocator;
        String subMenuLocator;
        String menuLocator = "id=ui-id-12";  // depends on way page built
        String ascendLocator = "{\"element\":\"#table\",\"subId\":\"oj-table-sort-ascending\",\"index\":\"" + index + "\"}";
        String descendLocator = "{\"element\":\"#table\",\"subId\":\"oj-table-sort-descending\",\"index\":\"" + index + "\"}";
            
            
        if(ascending) {
            sortLocator = ascendLocator;
            subMenuLocator = "id=ui-id-13";            
        } else {
            sortLocator = descendLocator;
            subMenuLocator = "id=ui-id-14";
        }

        if(useContextMenu){
            String elemLocator = "{\"element\":\"#table\",\"subId\":\"oj-table-header\",\"index\":\"" + index + "\"}";
            rightClickAndSelectSubMenuOption(elemLocator,menuLocator,subMenuLocator);                    
        } else {
            // The sort icon on the col header doesn't show up until you hover over it
            Actions actions = new Actions(getWebDriver());
            WebElement sortIcon = getElement(sortLocator);                
            actions.moveToElement(sortIcon);
            actions.click();
            actions.perform();            
            if(!ascending) {
                actions = new Actions(getWebDriver());
                sortIcon = getElement(descendLocator);                
                actions.moveToElement(sortIcon);
                actions.click();
                actions.perform();                            
            }
        }
    }

    // Default        
    public void testSort() throws Exception {
        //Start the test and bring up the browser
        startupTest(myPage, null);
        showComponent();        
        testSort(true, false, 1, new String[] { "25", "Dev", "200", "300" },
                       new String[] { "35", "Marketing", "200", "300" }, 
                       new String[] { "15", "QA", "200", "300" },
                       new String[] { "45", "Shipping", "200", "300" });
    /*        super.testSort(false, false, 1, new String[] { "45", "Shipping", "200", "300" },
                       new String[] { "15", "QA", "200", "300" }, 
                       new String[] { "35", "Marketing", "200", "300" },
                       new String[] { "25", "Dev", "200", "300" });    */
    }

    // Default
    public void testSortContextMenu() throws Exception {
        //Start the test and bring up the browser
        startupTest(myPage, null);
        showComponent();
        testSort(true, true, 1, new String[] { "25", "Dev", "200", "300" },
                       new String[] { "35", "Marketing", "200", "300" }, 
                       new String[] { "15", "QA", "200", "300" },
                       new String[] { "45", "Shipping", "200", "300" });
        testSort(false, true, 1, new String[] { "45", "Shipping", "200", "300" },
                       new String[] { "15", "QA", "200", "300" }, 
                       new String[] { "35", "Marketing", "200", "300" },
                       new String[] { "25", "Dev", "200", "300" });
    }

        
        
    public void testScroll(boolean shown) throws Exception {
        

        WebElement t1 = getElement(compLocator());

        Dimension size = t1.getSize();
        
        String sh = t1.getAttribute("scrollHeight"); 
        String of = t1.getAttribute("offsetHeight"); // includes scrollbar, border
        String ch = t1.getAttribute("clientHeight");
        int clientHeight = Integer.parseInt(ch);
        int scrollHeight = Integer.parseInt(sh);
        
        log("size = " + size);
        log("scrollHeight = " + sh);
        log("clientHeight = " + ch);
        log("offsetHeight = " + of);

        if(shown)                        
            Assert.assertEquals(clientHeight, scrollHeight, "Vertical scrollbar not shown (offsetHeight=" + of + ")");
        else
            Assert.assertNotEquals(clientHeight, scrollHeight, "Vertical scrollbar shown (offsetHeight=" + of + ")");
        }
    

}
