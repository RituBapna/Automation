package oj.tests.listview;

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

public class ListViewOptionTest extends OJetBase {
    private static final String TITLE = "Jet ojListView Test";
    private static final String CURRENTITEM_COMBOBOX_ID = "currentItemCombo"; //currentItem combo box id
    private static final String DRILLMODE_COMBOBOX_ID= "drillModeCombo";
    private static final String SELECTIONMODE_COMBOBOX_ID= "selectionModeId";
    public ListViewOptionTest() {
        super(new TestConfigBuilder().setContextRoot("datacollection").setAppRoot("listviewTest").build());
    }
    
    @Test(groups = { "listviewTest" })
    public void testListview() throws Exception {
       
        startupTest("ojlistviewOptionPage.html",null);
        getWebDriver().manage().window().maximize();
        String url = getBrowserUrl();
        containsStringCheck("Currentitem Value::amybartlet","optionOutputText");
        waitForMilliseconds(1000L);
        containsStringCheck("before collapse is fired!!","beforeCollapseOutputText");
        waitForMilliseconds(1000L);
        containsStringCheck("before expand is fired!!","beforeExpandOutputText");
        waitForMilliseconds(1000L);
        containsStringCheck("beforeCurrentItem is fired!!","beforeCurrentItemOutputText");
        waitForMilliseconds(1000L);
        containsStringCheck("expand event is fired!!","eventOutputText");
        waitForMilliseconds(1000L);
        
        /**
        * Selecting the item from combobox drop down
        * parameter #1: id of the element which need to be clicked
        * parameter #2: which item no from drop down to be selected
        * parameter #3: id of the outputtext field
        * parameter #4: value which is used in assertTrue to compare this valuewith the one displayed in output test area 
        */
        
        selectItemfromComboList(CURRENTITEM_COMBOBOX_ID,4,"currentitemOutputText","bobjones");
         waitForMilliseconds(500L);
       
        selectItemfromComboList(DRILLMODE_COMBOBOX_ID,0,"drillmodeOutputText","none");
         waitForMilliseconds(500L);
        selectItemfromComboList(SELECTIONMODE_COMBOBOX_ID,1,"selectionmodeOutputText","multiple");
        waitForMilliseconds(500L);
        /**
        * Selecting multiple option
        * selecting first item in the list
        * holdind shift key and selecting second list item
        */
        
        getWebDriver().findElement(By.id("amybartlet")).click();
        WebElement andyjones=getWebDriver().findElement(By.id("andyjones"));
        Actions action = new Actions(getWebDriver());
        action.keyDown(Keys.SHIFT).click(andyjones).keyUp(Keys.SHIFT).perform();
        waitForMilliseconds(500L);
        /** 
         * Checking class'oj-listview-item oj-selected' is applied to
         * 1.list element with id 'amybartlet'
         * 2. list element with id 'andyjones'
        */
        
        String selectedClassName=getWebDriver().findElement(By.id("amybartlet")).getAttribute("class");
        waitForMilliseconds(500L);
        Assert.assertTrue(selectedClassName.contains("oj-listview-focused-element"));
        
        selectedClassName=getWebDriver().findElement(By.id("andyjones")).getAttribute("class");
        System.out.println("Class name is +++++++++++++"+selectedClassName);
        waitForMilliseconds(500L);
        Assert.assertTrue(selectedClassName.contains("oj-listview-focused-element"));
       // WebElement ele = getWebDriver().findElement(By.className("oj-listview-item oj-selected"));
       // System.out.println("+++++++++++++ele is  taken +++++++++++++++" + className);
        waitForMilliseconds(1000L);
        
        /**
        * click on collapse node A button
        * checking that node A has oj-collapsed on it
        */
         selectItemfromComboList(DRILLMODE_COMBOBOX_ID,1,"none","none");
         waitForMilliseconds(1000L);
        
        /**
        * Toggleing nodes expand /collapse 
        * parameter #1: id of expand Node/collapse Node button
        * parameter #2: id of the node to be expanded/collapsed
        * parameter #3: name of the class applied to the node(oj-collapsed/oj-expanded)
        */
         toggleNodes("inputButtonA","a","oj-collapsed");
         waitForMilliseconds(500L);
        
         toggleNodes("inputButtonB","b","oj-collapsed");
         waitForMilliseconds(500L);
        
         toggleNodes("inputButtonAE","a","oj-expanded");
         waitForMilliseconds(500L);
        
         toggleNodes("inputButtonBE","b","oj-expanded");
         waitForMilliseconds(500L);
        
         toggleNodes("inputButtonCE","c","oj-expanded");
         waitForMilliseconds(500L);
         
    }
    
    public void containsStringCheck(String str1, String id) {
        String text=getElement("id="+id).getText();
        System.out.println("++++++++++++++++++++"+text);
       // Assert.assertTrue( text.contains(str1));
    }
    
    public void selectItemfromComboList(String itemIdToBeClicked, int itemNoToselect, String outputTextFiledId, String valueTocompare){

        WebElement comboboxInput =  getElement("{\"element\":\"#" +itemIdToBeClicked+"\",\"subId\":\"oj-combobox-arrow\"}");
        comboboxInput.click();
        WebElement resultset =  getElement("{\"element\":\"#" +itemIdToBeClicked + "\",\"subId\":\"oj-combobox-results\"}");
        List<WebElement> listItems = resultset.findElements(By.tagName("li"));
        listItems.get(itemNoToselect).click();
        if(valueTocompare !="none" && outputTextFiledId !="none" )
        containsStringCheck(valueTocompare,outputTextFiledId);
        waitForMilliseconds(500L);
    }
    public void toggleNodes(String buttonId,String nodeId,String appliedClass){
         getWebDriver().findElement(By.id(buttonId)).click();
         waitForMilliseconds(1000L);
         WebElement node=getWebDriver().findElement(By.id(nodeId));
        // System.out.println("+++++++++aNode++++++00" +node);
         //System.out.println("+++++++++aNodeClass++++++00" +node.getAttribute("class"));
         Assert.assertTrue(node.getAttribute("class").contains(appliedClass));
    
    }
}

