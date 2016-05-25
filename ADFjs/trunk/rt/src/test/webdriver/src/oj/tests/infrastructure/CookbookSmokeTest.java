package oj.tests.infrastructure;

import java.util.List;

import java.io.File;

import java.io.FilenameFilter;


import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;

public class CookbookSmokeTest extends OJetBase {

    private static final String TITLE = "Home";

    public CookbookSmokeTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components/public_html").setAppRoot("demo").build());
    }

    @Test(groups = { "cookbook"})
    public void cookbookPages() throws Exception {
        if (isGoogleChrome()) {
            String[] pages = _getAllHTML();
            String errorText = "";
            for (String page : pages) {
                //Start the test and bring up the browser
                startupTest(page, null);            
                waitForElementVisible("id=cookbookBody");
                Logs logs = getWebDriver().manage().logs();
                LogEntries logEntries = logs.get(LogType.BROWSER);

                for (LogEntry logEntry : logEntries) {
                    getLogger().severe(logEntry.getMessage());
                    errorText +=  page + "\n" + logEntry.getMessage() + "\n\n\n";                
                }            
            }
            // We had errors? fail the test
            Assert.assertTrue(errorText.equals(""), errorText);
        }
    }
    
    @Test(groups = { "cookbook" })
    public void bringUpCookbook() throws Exception {
        //Start the test and bring up the browser
        startupTest("index.html",null);

        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", TITLE);
        
        // Click settings dialog
        click("id=buttonImage");
        
        waitForElementVisible("id=settings_dialog");
                                                    
        // Close it
        WebElement closeModalDialog = getElement("{\"element\":\"#settings_dialog\",\"subId\":\"oj-dialog-header-close-wrapper\"}");
        closeModalDialog.click();        
    }
    
    @Test(groups={"cookbook"})
    public void testDateTimePages() throws Exception {
        startupTest("demo-dateInputAndPicker-inline.html", null);
        
        // Verify if the title of the page is correct
        verifyTitle("Incorrect page title;", "Input Date and Time - Inline DateTime");
        
        WebElement timeIcon = getElement("{\"element\":\"#inline\",\"subId\":\"oj-inputdatetime-time-icon\"}");
        timeIcon.click();
        
        waitForElementVisible("{\"element\":\"#inline\",\"subId\":\"oj-listbox-drop\"}");
        
        WebElement drop = getElement("{\"element\":\"#inline\",\"subId\":\"oj-listbox-drop\"}");
        WebElement child = drop.findElement(By.xpath(".//*"));
        List<WebElement> children = child.findElements(By.cssSelector(".oj-listbox-result-label"));
        for (WebElement element : children) {
            Object val = element.getAttribute("data-value");
            if (val.equals("03:00 AM")) {
                element.click();
                WebElement label = getElement("id=selected-value");
                Assert.assertEquals(label.getText(), "2013-01-01T03:00:00");
                break;
            }
        }
                
    }
    
    private String[] _getAllHTML() {
        File dir = new File("");
        System.out.println("PATH TOP ====================" + dir.getAbsolutePath());
        String path = dir.getAbsolutePath();
        
        // Check for grunt wd vs. old
        File f = null;
        if (path.endsWith(File.separator + "ant")) {
            // In ant, go up three levels--a bit ugly
            String filedir = ".." + File.separator + ".." + File.separator + "..";
            f = new File(filedir+File.separator+"built"+File.separator+"apps"+File.separator+"components"+File.separator+"public_html"+File.separator+"demo");            
        }
        else {
            f = new File("built"+File.separator+"apps"+File.separator+"components"+File.separator+"public_html"+File.separator+"demo");        
        }
        String[] list = f.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                // Return files that begin with demo- and end with .html--those are demo pages currently
                return (name.toLowerCase().endsWith(".html")) && (name.toLowerCase().startsWith("demo-"));
            }
        });
        return list;
    }
}
