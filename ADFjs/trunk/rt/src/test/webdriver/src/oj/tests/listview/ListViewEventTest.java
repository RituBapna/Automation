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

public class ListViewEventTest extends OJetBase {
    private static final String TITLE = "Jet ojListViewEvent Test";
    private static final String CURRENTITEM_COMBOBOX_ID = "currentItemCombo"; //currentItem combo box id
    private static final String DRILLMODE_COMBOBOX_ID= "drillModeCombo";
    private static final String SELECTIONMODE_COMBOBOX_ID= "selectionModeId";
    public ListViewEventTest() {
        super(new TestConfigBuilder().setContextRoot("datacollection").setAppRoot("listviewTest").build());
    }
    
    @Test(groups = { "listviewTest" })
    public void testListview() throws Exception {
        startupTest("ojlistviewEventPage.html",null);
        getWebDriver().manage().window().maximize();
        String url = getBrowserUrl();
        
        getElement("{\"element\":\"#listview\",\"subId\":\"oj-listview-disclosure\",\"key\":\"a\"}").click();
         waitForMilliseconds(2000L);
        
        getElement("{\"element\":\"#listview\",\"subId\":\"oj-listview-disclosure\",\"key\":\"a\"}").click();
         waitForMilliseconds(2000L);
        
        containsStringCheck("Currentitem Value::amybartlet","optionOutputText");
        containsStringCheck("before collapse is fired!!","beforeCollapseOutputText");
        containsStringCheck("before expand is fired!!","beforeExpandOutputText");
        containsStringCheck("beforeCurrentItem is fired!!","beforeCurrentItemOutputText");
        containsStringCheck("expand event is fired!!","eventOutputText");
        
       
        
        /**
        * Toggleing nodes expand /collapse 
        * parameter #1: id of expand Node/collapse Node button
        * parameter #2: id of the span where event text will be displayed
        * parameter #3: text which is changed based on the event been fired)
        */
         catchEvent("inputButtonA","eventOutputText","collapse event is fired!!");
         waitForMilliseconds(500L);
        
         catchEvent("inputButtonAE","eventOutputText","expand event is fired!!");
         waitForMilliseconds(500L);
        
         catchEvent("inputButtonB","eventOutputText","collapse event is fired!!");
         waitForMilliseconds(500L);
        
         catchEvent("inputButtonBE","eventOutputText","expand event is fired!!");
         waitForMilliseconds(500L);
        
         catchEvent("destroyLV","eventOutputText","destroy event is fired!!");
         waitForMilliseconds(500L);
        
    }
    
    public void containsStringCheck(String str1, String id) {
        String text=getElement("id="+id).getText();
        Assert.assertTrue( text.contains(str1));
    }
    
    
    public void catchEvent(String buttonId, String textSpanId,String eventText){
         getWebDriver().findElement(By.id(buttonId)).click();
         
         String text=getElement("id="+textSpanId).getText();
         
         Assert.assertEquals(text, eventText);
    
    }
}

