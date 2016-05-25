package oj.tests.datamodel.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreatePageObject {
    private final WebDriver driver;
   
    By tableLocator = By.xpath("//div[@id='deptList']/table");
    
    public CreatePageObject(WebDriver driver) {
        this.driver = driver;
        
        if (!"Oracle JET Common Model - Create".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the right page");
        }
    }   

    public void setTableLocator(By tableLocator) {
        this.tableLocator = tableLocator;
    }  

    public By getTableLocator() {
        return tableLocator;
    }
    //  By dnameDivLocator = By.xpath("//div[contains(.,'Administration')]");
    By dnameInputFieldLocator = By.xpath("//input[@id='newDepartName']");
    By deptnoInputFieldLocator = By.xpath("//input[@id='newDepartId']");
   
    By saveButtonLocator = By.xpath("//button[@id='saveBtn']");
    By tableRowsLocator = By.xpath("//table/tbody/tr");

    public void createRow(String deptnoValue, String dnameValue) {
        WebElement dnameInputField = driver.findElement(dnameInputFieldLocator);
        WebElement deptnoInputField = driver.findElement(deptnoInputFieldLocator);
        dnameInputField.clear();
        dnameInputField.sendKeys(dnameValue);
        deptnoInputField.clear();
        deptnoInputField.sendKeys(deptnoValue);
        WebElement saveBtn = driver.findElement(saveButtonLocator);
        saveBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By newRowLocator =  By.xpath("//span[contains(.,'555')]");
        wait.until(ExpectedConditions.elementToBeClickable(newRowLocator));
    }
    
    public String deptnoOfLastRow(){
        int rowCount = numberOfRowsInTable();
        By lastRowLocator = By.xpath("//table/tbody/tr["+rowCount+"]/td[1]/span"); 
        WebElement deptnoSpan = driver.findElement(lastRowLocator);
        String deptno = deptnoSpan.getText();
        System.out.println("***Last row deptno : " + deptno);
        return deptno;
        
    }
    
    public int numberOfRowsInTable(){
        List<WebElement> deptRows  = driver.findElements(tableRowsLocator);
        int numberOfRows = deptRows.size();
        System.out.println("count: " + numberOfRows);
        return numberOfRows;
    }
}
