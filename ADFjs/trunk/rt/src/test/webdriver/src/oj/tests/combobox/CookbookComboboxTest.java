package oj.tests.combobox;

import java.util.List;


import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CookbookComboboxTest extends OJetBase {
     private static final String TEST_PAGE = "uiComponents-combobox-single.html";
     private static final String TEST_PAGE_TITLE = "Combobox - Single Select";
     private static final String COMBOBOX_ID = "combobox"; //inputText
     private static final String SPAN_ID = "curr-value"; //inputText



     public CookbookComboboxTest() {
         super(new
TestConfigBuilder().setContextRoot("built/apps/components").setAppRoot("public_html").build());
     }


     @Test(groups = { "cookbook", "combobox" })
     public void testInput() throws Exception {
         startupTest(TEST_PAGE, null);
         verifyTitle("Incorrect page title;", TEST_PAGE_TITLE);
         waitForElementVisible("id=" + SPAN_ID);


         WebElement comboboxInput =  getElement("{\"element\":\"#" + 
COMBOBOX_ID +
                    "\",\"subId\":\"oj-combobox-input\"}");

        WebElement span = getElement("id=" + SPAN_ID);
         comboboxInput.click();
         this.waitForMilliseconds(1000);

         WebElement resultset =  getElement("{\"element\":\"#" + 
COMBOBOX_ID +
                    "\",\"subId\":\"oj-combobox-results\"}");

         List<WebElement> listItems = 
resultset.findElements(By.tagName("li"));

         System.out.println("*** #####: " + listItems.size());

         listItems.get(4).click();
         this.waitForMilliseconds(10000);
         String value = span.getText();
         Assert.assertEquals("[\"Safari\"]", value);

     }
}


