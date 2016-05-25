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

public class ListViewDnDTest extends OJetBase {
    private static final String TITLE = "Jet ojListViewEvent Test";
    private static final String CURRENTITEM_COMBOBOX_ID = "currentItemCombo"; //currentItem combo box id
    private static final String DRILLMODE_COMBOBOX_ID= "drillModeCombo";
    private static final String SELECTIONMODE_COMBOBOX_ID= "selectionModeId";
    public ListViewDnDTest() {
        super(new TestConfigBuilder().setContextRoot("datacollection").setAppRoot("listviewTest").build());
    }
    
    @Test(groups = { "listviewDnDTest" })
    public void testListview() throws Exception {
        startupTest("listviewDnDPage.html",null);
        getWebDriver().manage().window().maximize();
        String url = getBrowserUrl();
        verifyTitle("Incorrect page title;","Listview - Drap and Drop with Table");
       
        waitForMilliseconds(5000L);
        waitForElementVisible("id=table2");
        waitForElementVisible("id=listview2");
        
        waitForMilliseconds(5000L);
        //Actions builder = new Actions(getWebDriver());
        getWebDriver().findElement(By.id("emp0")).click();
        waitForMilliseconds(5000L);
        
        Actions action = new Actions(getWebDriver());
        action.dragAndDrop(getWebDriver().findElement(By.id("emp0")), getWebDriver().findElement(By.id("table2"))).build().perform();
       // Action dragAndDrop = builder.clickAndHold(getWebDriver().findElement(By.id("emp0")))
        //   .moveToElement(getWebDriver().findElement(By.id("table2")))
        //   .release(getWebDriver().findElement(By.id("table2")))
        //   .build();

      //  dragAndDrop.perform();
         waitForMilliseconds(5000L);
    }
}

