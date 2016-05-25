package oj.tests.translation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TranslationPageObject {
    private final WebDriver driver;
    private final Actions actions;
    By inputFieldLocator = By.id("inputText");
    By redLocator = By.id("testred");

    public By getRedLocator() {
        return redLocator;
    }
    By blueLocator = By.id("testblue");
    By greenLocator = By.id("testgreen");
    By buttonLocator = By.id("toggle");
    By inputLabelLocator = By.id("inputLabel");

    public TranslationPageObject(WebDriver driver) {
       this.driver = driver;
        this.actions = new Actions(driver);
       if(!"Oracle JET Translation Test".equals(driver.getTitle())){
           throw new IllegalStateException("This is not the right page");
       }
    }

    public By getInputFieldLocator() {
        return inputFieldLocator;
    }
    
    public String getGreenValue(){
        String greenVal=null;
        WebElement field = driver.findElement(greenLocator);  
        greenVal = field.getText();
        return greenVal;
    }
    
    public String getRedValue(){
        String redVal=null;
        WebElement field = driver.findElement(redLocator);  
        redVal = field.getText();
        return redVal;
    }
    public String getBlueValue(){
        String blueVal=null;
        WebElement field = driver.findElement(blueLocator);  
        blueVal = field.getText();
        return blueVal;
    }
    
    public void setInputField(String value ){     
            WebElement field = driver.findElement(inputFieldLocator);       
            setValue(field, value);     
    }
    
    void setValue(WebElement element, String value){
     element.sendKeys(value);
     //shift + tab
     actions.sendKeys(Keys.TAB).perform();
     //Also click pn green field to move focus away from input field as sendKeys(Keys.Tab) does not seem to work on all browsers
     WebElement green = driver.findElement(greenLocator);      
        actions.moveToElement(green).click().build().perform();
    // green.click();    
    }
    
    public void clearInputField(){
        WebElement field = driver.findElement(inputFieldLocator);
        field.clear();
       
        //tab
        actions.sendKeys(Keys.TAB).perform();
        //Also click pn green field to move focus away from input field as sendKeys(Keys.Tab) does not seem to work on all browsers
        WebElement green = driver.findElement(greenLocator);      
        actions.moveToElement(green).click().build().perform();
       // green.click();
    }
    
    public String getToggleButtonLabel(){
        String label=null;
        WebElement button = driver.findElement(buttonLocator);
        label = button.getAttribute("value");
        return label;
    }
    
    public String getInputTextLabel(){
        String label=null;
        WebElement field = driver.findElement(inputLabelLocator);
        actions.moveToElement(field).click().build().perform();
        label = field.getText();
        return label;
    }
    
    public String getInputTextPlaceholderValue(){
        String placeholder=null;
        WebElement field = driver.findElement(inputFieldLocator);
        placeholder = field.getAttribute("placeholder");
        return placeholder;
    }
    
    public String getInputTextTitleValue(){
        String title=null;
        WebElement field = driver.findElement(inputFieldLocator);
       // actions.moveToElement(field).click().build().perform();
       // actions.moveToElement(field).build().perform();
        
        title =  getTooltipContent(field);
        return title;
    }
    
    public void clickButtonToToggleLocale(){
        WebElement button = driver.findElement(buttonLocator);
        actions.moveToElement(button).click().build().perform();
       // button.click();
        System.out.println("***Button Clicked");
    }
    
    public String getValidationErrorMessage(){
        String errMsg = null;
        WebElement field = driver.findElement(inputFieldLocator);  
       
      
        errMsg =  getTooltipContent(field);
        return errMsg;
    }
    
    String getTooltipContent(WebElement field){
        String tooltip = null;
        actions.moveToElement(field).build().perform();
       String tooltipId =  field.getAttribute("aria-describedby");
       By tooltipLocator = By.id(tooltipId);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(tooltipLocator));
       WebElement tooltipField = driver.findElement(tooltipLocator);
       tooltip = tooltipField.getText();

      //  tooltip = field.getAttribute("title");
      System.out.println("** tooltip value: " + tooltip);
      return tooltip;
    }
}
