package oj.tests.datamodel.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class BrowsePageObject {
    private final WebDriver driver;
    By tableLocator = By.xpath("//div[@id='mainContent']/table");

    public void setTableLocator(By tableLocator) {
        this.tableLocator = tableLocator;
    }

    public By getTableLocator() {
        return tableLocator;
    }
    By tableRowsLocator = By.xpath("//table/tbody/tr");
    public BrowsePageObject(WebDriver driver) {
       this.driver = driver;
       if(!"Oracle JET Common Model - Fetch".equals(driver.getTitle())){
           throw new IllegalStateException("This is not the right page");
       }
    }
    
    public int getRowCount()    {
        List<WebElement> deptRows  = driver.findElements(tableRowsLocator);
        int numberOfRows = deptRows.size();
        System.out.println("count: " + numberOfRows);
        return numberOfRows;
    }
}
