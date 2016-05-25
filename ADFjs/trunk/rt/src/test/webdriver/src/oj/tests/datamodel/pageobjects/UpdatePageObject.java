package oj.tests.datamodel.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpdatePageObject {

    private final WebDriver driver;
    private final Actions actions;
    By tableLocator = By.xpath("//div[@id='mainContent']/table");

    public void setTableLocator(By tableLocator) {
        this.tableLocator = tableLocator;
    }

    public By getTableLocator() {
        return tableLocator;
    }
    //  By dnameDivLocator = By.xpath("//div[contains(.,'Administration')]");
    By dnameDivLocator = By.xpath("//div[@id='mainContent']/table/tbody/tr[28]/td[2]/div");

    public void setDnameDivLocator(By dnameDivLocator) {
        this.dnameDivLocator = dnameDivLocator;
    }

    public By getDnameDivLocator() {
        return dnameDivLocator;
    }

    public UpdatePageObject(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        if (!"Oracle JET Common Model - Update".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the right page");
        }
    }

    public String updateLastRow(String newValue, String browser) {
       
        WebElement dnameDiv = driver.findElement(dnameDivLocator);
        String originalValue = dnameDiv.getText();
        /*
        if (browser.equals("internet explorer")) {
            driver.getWindowHandle();
            dnameDiv.sendKeys("");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
            }
            System.out.println("**perform Enter for IE***");
            dnameDiv.sendKeys(Keys.chord(Keys.ENTER));
        } else {
            System.out.println("**perform click  for other browsers***");
            dnameDiv.click();
        }
*/
        actions.moveToElement(dnameDiv).click().build().perform();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // By dnameInputFieldLocator = By.xpath("//input[contains(.,'Administration')]");
        By dnameInputFieldLocator = By.xpath("//div[@id='mainContent']/table/tbody/tr[28]/td[2]/div/input");

        wait.until(ExpectedConditions.elementToBeClickable(dnameInputFieldLocator));

        WebElement dnameInputField = driver.findElement(dnameInputFieldLocator);
        //clear out the current value
        dnameInputField.clear();
        dnameInputField.sendKeys(newValue);
        //dnameInputField.sendKeys("\n");
        //actions.moveToElement(dnameInputField).click().build().perform();
        actions.sendKeys(Keys.RETURN).perform();
        return originalValue;
    }

    public void resetValueOfFirstRow(String browser) {
        WebElement dnameDiv = driver.findElement(dnameDivLocator);
      /*
        if (browser.equals("internet explorer")) {
            driver.getWindowHandle();
            dnameDiv.sendKeys("");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
            }
            System.out.println("**perform Enter for IE***");
            dnameDiv.sendKeys(Keys.chord(Keys.ENTER));
        } else {
            System.out.println("**perform click  for other browsers***");
            dnameDiv.click();
        }
*/
        actions.moveToElement(dnameDiv).click().build().perform();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        // By dnameInputFieldLocator = By.xpath("//input[contains(.,'Administration')]");
        By dnameInputFieldLocator = By.xpath("//div[@id='mainContent']/table/tbody/tr[1]/td[2]/div/input");

        wait.until(ExpectedConditions.elementToBeClickable(dnameInputFieldLocator));

        WebElement dnameInputField = driver.findElement(dnameInputFieldLocator);
        //clear out the current value
        actions.moveToElement(dnameInputField).click().build().perform();
        dnameInputField.clear();
      
        dnameInputField.sendKeys("Administration");
        dnameInputField.sendKeys(Keys.RETURN);
    }

    public String getLastRowValue() {
        String depatmentNameValue = null;
        WebElement dnameDiv = driver.findElement(dnameDivLocator);
        depatmentNameValue = dnameDiv.getText();
        return depatmentNameValue;
    }
}
