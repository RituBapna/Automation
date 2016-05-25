package oj.tests.datamodel.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DeletePageObject {
    private final WebDriver driver;
    private final Actions actions;
    By tableLocator = By.xpath("//div[@id='mainContent']/table");

    public void setTableLocator(By tableLocator) {
        this.tableLocator = tableLocator;
    }

    public DeletePageObject(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        if (!"Oracle JET Common Model - Delete".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the right page");
        }
    }

    public By getTableLocator() {
        return tableLocator;
    }
    //  By dnameDivLocator = By.xpath("//div[contains(.,'Administration')]");
    //This tests is dependednt on Create tests which inserts a row at the end with id =555
    By checkboxInFirstRowLocator = By.xpath("//input[@id='10']");
    By checkboxInLastLocator = By.xpath("//input[@id='555']");
    By deleteButtonLocator = By.xpath("//button[@id='deleteDept_btn']");
    By tableRowsLocator = By.xpath("//table/tbody/tr");

    public void deleteRow(By rowLocator) {
        WebElement checkbox = driver.findElement(rowLocator);
        checkbox.click();
        //WebDriverWait wait = new WebDriverWait(driver, 10);
       // wait.until(ExpectedConditions.elementToBeClickable(deleteButtonLocator));
        WebElement deleteBtn = driver.findElement(deleteButtonLocator);
        System.out.println("Button is enabled: " + deleteBtn.isEnabled());
        deleteBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By newRowLocator =  By.xpath("//input[@id='10']");
        wait.until(ExpectedConditions.elementToBeClickable(newRowLocator));   
    }

    public void deleteLastRows(String browser) {
       // deleteRow(checkboxInFirstRowLocator);
        deleteRow(checkboxInLastLocator);
    }
    
    public int numberOfRowsInTable(){
        List<WebElement> deptRows  = driver.findElements(tableRowsLocator);
        int numberOfRows = deptRows.size();
        System.out.println("count: " + numberOfRows);
        return numberOfRows;
    }

}
