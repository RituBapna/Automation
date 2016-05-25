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

public class ListViewMethodTest extends OJetBase {
    private static final String TITLE = "Jet ojListViewEvent Test";
    private static final String componentId = "listview"; //currentItem combo box id
    private static final String subId= "oj-listview-icon";
    private static final String Key= "a";
    public ListViewMethodTest() {
        super(new TestConfigBuilder().setContextRoot("datacollection").setAppRoot("listviewTest").build());
    }
    
    @Test(groups = { "listviewTest" })
    public void testListview() throws Exception {
        startupTest("ojlistviewMethodPage.html",null);
        getWebDriver().manage().window().maximize();
        waitForMilliseconds(5000L);
        waitForElementVisible("id=contentSelectionwrapperDiv");
        waitForMilliseconds(500L);
        waitForElementVisible("id=contentDiv");
        waitForMilliseconds(500L);
        waitForElementVisible("id=listview");
        waitForMilliseconds(500L);
         waitForMilliseconds(5000L);
        // getElement("{\"element\":\"#listview\",\"subId\":\"oj-listview-disclosure\",\"key\":\"b\"}").click();
        String str= evalJavascript("return $('#listview').ojListView('collapse', 'a')");
         System.out.println("+++++++++++++0"+str);
    }
    
    
}

