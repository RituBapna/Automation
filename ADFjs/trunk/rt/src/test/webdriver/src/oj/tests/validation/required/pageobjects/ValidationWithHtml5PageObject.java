package oj.tests.validation.required.pageobjects;

import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ValidationWithHtml5PageObject {
    private final WebDriver driver;
    private final Actions actions;
    
    By formLocator = By.xpath("//div[@id='mainContent']/form");
    //All browsers
    By inputTextEmpidLocator = By.id("empid");
    By inputTextDeptidLocator = By.id("deptid");
    By inputTextFirstnameLocator = By.id("firstname");
    By inputPasswordLocator = By.id("password");
    By textareaLocator = By.id("generalInfo");
    By inputWithDatalistLocator = By.id("title");
    By singleSelectLocator = By.id("pskill");
    By inputFileLocator = By.id("image_file");
    
    //Chrome, Opera ans Safari browsers 
    By hiredateLocator = By.id("hiredate");
    By inputNumberLocator = By.id("comm");
    By inputEmailLocator = By.id("email");
    By inputUrlLocator = By.id("url");
    By inputRangeLocator = By.id("range");
    
        

   

    public By getInputFieldLocator() {
        return inputTextFirstnameLocator;
    }
   
    public void setMandatoryField(String value){
        WebElement empidField = driver.findElement(inputTextEmpidLocator);       
         setValue(empidField, value);
       
    }
    //Firstname input type="text
    public void setInputTextValue(String value){
        WebElement firstnameField = driver.findElement(inputTextFirstnameLocator);       
        //setValue(firstnameField, value);
        firstnameField.sendKeys(value);
        //shift + tab
        //  actions.sendKeys(Keys.TAB).perform();
        WebElement deptidField = driver.findElement(inputTextDeptidLocator);
        actions.click(deptidField).perform();
       
    }
    
    public void clearInputTextValue(){
        WebElement firstnameField = driver.findElement(inputTextFirstnameLocator);
        //clearValue(firstnameField);
        firstnameField.clear();
        
        WebElement deptidField = driver.findElement(inputTextDeptidLocator);      
        // actions.moveToElement(empidField).click().build().perform();
        actions.click(deptidField).perform();
    }
    
    public String getClassForInputText(){
        WebElement firstnameField = driver.findElement(inputTextFirstnameLocator);
        return getClassAttribute(firstnameField);
                   
    }
    
    public boolean isToolipPresentForInputText(){
        WebElement firstnameField = driver.findElement(inputTextFirstnameLocator);
       // actions.moveToElement(firstnameField).click().perform();
       // actions.moveToElement(firstnameField).perform();
        return isTooltipPresent(firstnameField);
                   
    }
    
    public String getToolipContentForInputText(){
        WebElement firstnameField = driver.findElement(inputTextFirstnameLocator);
     //   actions.moveToElement(firstnameField).click().perform();
        actions.moveToElement(firstnameField).perform();
        return getTooltipContent(firstnameField);
                   
    }
    
    //methods for input type="password"
    public void setInputPasswordValue(String value){
        WebElement passwordField = driver.findElement(inputPasswordLocator);       
       // setValue(passwordField, value);
       // System.out.println("Value after set: " + passwordField.getAttribute("value"));
       passwordField.sendKeys(value);
       //shift + tab
       //  actions.sendKeys(Keys.TAB).perform();
       WebElement deptidField = driver.findElement(inputTextDeptidLocator);
       actions.click(deptidField).perform();
    }
    
    public void clearInputPasswordValue(){
        WebElement passwordField = driver.findElement(inputPasswordLocator);  
        //clearValue(passwordField);
        passwordField.clear();
      
        WebElement deptidField = driver.findElement(inputTextDeptidLocator);      
        // actions.moveToElement(empidField).click().build().perform();
        actions.click(deptidField).perform();
    }
    
    public String getClassForInputPassword(){
        WebElement passwordField = driver.findElement(inputPasswordLocator);  
        return getClassAttribute(passwordField);
                   
    }
    
    public boolean isToolipPresentForInputPassword(){
        WebElement passwordField = driver.findElement(inputPasswordLocator);  
      //  actions.moveToElement(passwordField).click().perform();
        //actions.moveToElement(passwordField).perform();
        return isTooltipPresent(passwordField);
                   
    }
    
    public String getToolipContentForInputPassword(){
        WebElement passwordField = driver.findElement(inputPasswordLocator);  
      //  actions.moveToElement(passwordField).click().perform();
        actions.moveToElement(passwordField).perform();
        return getTooltipContent(passwordField);
                   
    }
    
    //methods for textarea
    public void setTextareaValue(String value){
        WebElement textarea = driver.findElement(textareaLocator);       
        setValue(textarea, value);
       
    }
    
    public void clearTextareaValue(){
        WebElement textarea = driver.findElement(textareaLocator); 
        clearValue(textarea);
    }
    
    public String getClassForTextarea(){
        WebElement textarea = driver.findElement(textareaLocator);
        return getClassAttribute(textarea);
                   
    }
    
    public boolean isToolipPresentForTextarea(){
        WebElement textarea = driver.findElement(textareaLocator);
      //  actions.moveToElement(textarea).click().perform();
        actions.moveToElement(textarea).perform();
        return isTooltipPresent(textarea);
                   
    }
    
    public String getToolipContentForTextarea(){
        WebElement textarea = driver.findElement(textareaLocator);
       // actions.moveToElement(textarea).click().perform();
        actions.moveToElement(textarea).perform();
        return getTooltipContent(textarea);
                   
    }
    
    //methods for input with datalist
    public void setInputWithDataListValue(String value){
        WebElement input = driver.findElement(inputWithDatalistLocator);
        actions.click(input).sendKeys(value).perform();
        
        /*
        WebElement datalist = driver.findElement(By.tagName("datalist"));
        List<WebElement> allOptions = datalist.findElements(By.tagName("option"));
        System.out.println("#of datalist options: " + allOptions.size());
        System.out.println("#of datalist options: " + allOptions.get(1));
       // actions.moveToElement(allOptions.get(0)).click().perform();
       actions.click(allOptions.get(1)).perform();
*/
        //shift + tab
          // actions.sendKeys(Keys.TAB).perform();
          WebElement empidField = driver.findElement(inputTextEmpidLocator);      
          actions.click(empidField).perform();
       
    }
    
    public void clearInputWithDataListValue(){
        WebElement input = driver.findElement(inputWithDatalistLocator);       
        clearValue(input);
    }
    
    public String getClassForInputWithDataList(){
        WebElement input = driver.findElement(inputWithDatalistLocator);       
        return getClassAttribute(input);
                   
    }
    
    public boolean isToolipPresentForInputWithDataList(){
        WebElement input = driver.findElement(inputWithDatalistLocator);       
      //  actions.moveToElement(input).click().perform();
        actions.moveToElement(input).perform();
        return isTooltipPresent(input);
                   
    }
    
    public String getToolipContentForInputWithDataList(){
        WebElement input = driver.findElement(inputWithDatalistLocator);   
       // actions.moveToElement(input).click().perform();
        actions.moveToElement(input).perform();
        return getTooltipContent(input);
                   
    }
    
    //methods for single select
    public void setSingleSelectValue(String value){
        WebElement select = driver.findElement(singleSelectLocator);
        Select sele = new Select(select);
        sele.selectByIndex(1);
        /*
        actions.click(select).sendKeys(value).build().perform();
       // WebElement datalist = driver.findElement(By.tagName("datalist"));
        
        List<WebElement> allOptions = select.findElements(By.tagName("option"));
        System.out.println("#of datalist options: " + allOptions.size());
        actions.moveToElement(allOptions.get(1)).click().build().perform();
       */
        //shift + tab
        //   actions.sendKeys(Keys.TAB).perform();
        WebElement empidField = driver.findElement(inputTextEmpidLocator);      
        actions.click(empidField).perform();
       
    }
    
    public void clearSingleSelectValue(){
        WebElement select = driver.findElement(singleSelectLocator);  
        Select sele = new Select(select);
        sele.selectByIndex(0);
        /*
        actions.click(select).build().perform();
        // WebElement datalist = driver.findElement(By.tagName("datalist"));
        List<WebElement> allOptions = select.findElements(By.tagName("option"));
        System.out.println("#of datalist options: " + allOptions.size());
        actions.moveToElement(allOptions.get(0)).click().build().perform();
        */
        //shift + tab
        //   actions.sendKeys(Keys.TAB).perform();
        WebElement empidField = driver.findElement(inputTextEmpidLocator);      
        actions.click(empidField).perform();
    }
    
    public String getClassForSingleSelect(){
        WebElement select = driver.findElement(singleSelectLocator);       
        return getClassAttribute(select);
                   
    }
    
    public boolean isToolipPresentForSingleSelect(){
          WebElement select = driver.findElement(singleSelectLocator);   
        
      
        Select sele = new Select(select);
        sele.selectByIndex(0);
       
        return isTooltipPresent(select);
                   
    }
    
    public String getToolipContentForSingleSelect(){
        WebElement select = driver.findElement(singleSelectLocator);
       // actions.moveToElement(select).click().perform();
        actions.moveToElement(select).perform();
      
        return getTooltipContent(select);
                   
    }
    
    //picture input type="file"
    public void setInputFileValue(String value){
        WebElement fileField = driver.findElement(inputFileLocator);       
        setValue(fileField, value);
       
    }
    
    public void clearInputFileValue(){
        WebElement fileField = driver.findElement(inputFileLocator);       
        clearValue(fileField);
    }
    
    public String getClassForInputFile(){
        WebElement fileField = driver.findElement(inputFileLocator);       
        return getClassAttribute(fileField);
                   
    }
    
    public boolean isToolipPresentForInputFile(){
        WebElement fileField = driver.findElement(inputFileLocator);       
      //  actions.moveToElement(fileField).click().perform();
        actions.moveToElement(fileField).perform();
        return isTooltipPresent(fileField);
                   
    }
    
    public String getToolipContentForInputFile(){
        WebElement fileField = driver.findElement(inputFileLocator);       
        //actions.moveToElement(fileField).click().perform();
        actions.moveToElement(fileField).perform();
        return getTooltipContent(fileField);
                   
    }
    
     boolean isTooltipPresent(WebElement field){
       // boolean tooltip = false;
       actions.moveToElement(field).build().perform();
        String tooltipId =  field.getAttribute("aria-describedby");
        if (tooltipId!=null)
            return true;
        else
        return false;
        /*
        By tooltipLocator = By.className("ui-tooltip-content");
        List <WebElement>  tooltipList = driver.findElements(tooltipLocator);
        if (!tooltipList.isEmpty())
          return true;
        else return false;
    */
    }
    
     String getTooltipContent(WebElement field){
         /*
        By tooltipLocator = By.className("ui-tooltip-content");
        List <WebElement>  tooltipList = driver.findElements(tooltipLocator);
        System.out.println("*** In get tooltipcontent Tooltip List size: " + tooltipList.size());
        System.out.println("*** In get tooltipcontent Tooltip  data: " + tooltipList.get(0).getText());
        if (!tooltipList.isEmpty())
          return tooltipList.get(0).getText();
        else return null;
    */
         String tooltip = null;
         String tooltipId =  field.getAttribute("aria-describedby");
         By tooltipLocator = By.id(tooltipId);
         WebElement tooltipField = driver.findElement(tooltipLocator);
         tooltip = tooltipField.getText();
         System.out.println("** tooltip value: " + tooltip);
         return tooltip;
    }
    
    void setValue(WebElement element, String value){
        element.sendKeys(value);
     //shift + tab
      //  actions.sendKeys(Keys.TAB).perform();
      WebElement empidField = driver.findElement(inputTextEmpidLocator);      
      actions.click(empidField).perform();
     
    }
    
        void clearValue(WebElement element){
            element.clear();
            System.out.println("*** Value after clear: " + element.getAttribute("value"));
            //shift + tab
               //actions.keyDown(Keys.SHIFT).sendKeys(Keys.TAB).keyUp(Keys.SHIFT).perform();
            //tab
             //  actions.sendKeys(Keys.TAB).perform();
            WebElement empidField = driver.findElement(inputTextEmpidLocator);      
           // actions.moveToElement(empidField).click().build().perform();
           actions.click(empidField).perform();
           // empidField.click();
            System.out.println("*** Value after clear + tab out: " + element.getAttribute("class"));
            
        }
        
         String getClassAttribute(WebElement element) {
            return element.getAttribute("class");
        }
  
    public ValidationWithHtml5PageObject(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        if(!"HTML5 element required validator test".equals(driver.getTitle())){
            throw new IllegalStateException("This is not the right page");
        }
    }
    
    
    //Chrome, Opera ans Safari browsers 
   
    //Hiredate input type="date"
    public void setInputDateValue(String value){
        WebElement hiredateField = driver.findElement(hiredateLocator); 
       
      setValue(hiredateField, value);
     
    }
    
    public void clearInputDateValue(){
        ((JavascriptExecutor) driver).executeScript("document.getElementById('hiredate').removeAttribute('readonly');");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('hiredate').value='';");
        WebElement hiredateField = driver.findElement(hiredateLocator);  
       // hiredateField.click();
      //  hiredateField.sendKeys(Keys.DELETE);
       // actions.sendKeys( Keys.BACK_SPACE).perform();
      // setValue(hiredateField, "yyyy-mm-dd");
        clearValue(hiredateField);
     //  hiredateField.sendKeys(Keys.TAB);
      //  WebElement empidField = driver.findElement(inputTextEmpidLocator);      
      //  empidField.click();
       // System.out.println("*** Value after clear + tab out: " + element.getAttribute("class"));
    }
    
    public String getClassForInputDate(){
        WebElement hiredateField = driver.findElement(hiredateLocator); 
        return getClassAttribute(hiredateField);
                   
    }
    
    public boolean isToolipPresentForInputDate(){
        WebElement hiredateField = driver.findElement(hiredateLocator);  
      //  actions.moveToElement(hiredateField).click().perform();
        actions.moveToElement(hiredateField).perform();
        return isTooltipPresent(hiredateField);
                   
    }
    
    public String getToolipContentForInputDate(){
        WebElement hiredateField = driver.findElement(hiredateLocator);  
     //   actions.moveToElement(hiredateField).click().perform();
        actions.moveToElement(hiredateField).perform();
        return getTooltipContent(hiredateField);
                   
    }
    
    //Comm input type="number"
    public void setInputNumberValue(String value){
        WebElement commField = driver.findElement(inputNumberLocator);       
        setValue(commField, value);
       
    }
    
    public void clearInputNumberValue(){
        WebElement commField = driver.findElement(inputNumberLocator);       
        clearValue(commField);
    }
    
    public String getClassForInputNumber(){
        WebElement commField = driver.findElement(inputNumberLocator);       
        return getClassAttribute(commField);
                   
    }
    
    public boolean isToolipPresentForInputNumber(){
        WebElement commField = driver.findElement(inputNumberLocator);       
        //actions.moveToElement(commField).click().perform();
        actions.moveToElement(commField).perform();
        return isTooltipPresent(commField);
                   
    }
    
    public String getToolipContentForInputNumber(){
        WebElement commField = driver.findElement(inputNumberLocator);       
    //    actions.moveToElement(commField).click().perform();
        actions.moveToElement(commField).perform();
        return getTooltipContent(commField);
                   
    }
    
    //Email input type="email"
    public void setInputEmailValue(String value){
        WebElement emailField = driver.findElement(inputEmailLocator);       
        setValue(emailField, value);
       
    }
    
    public void clearInputEmailValue(){
        WebElement emailField = driver.findElement(inputEmailLocator);  
        clearValue(emailField);
    }
    
    public String getClassForInputEmail(){
        WebElement emailField = driver.findElement(inputEmailLocator);         
        return getClassAttribute(emailField);
                   
    }
    
    public boolean isToolipPresentForInputEmail(){
        WebElement emailField = driver.findElement(inputEmailLocator);        
       // actions.moveToElement(emailField).click().perform();
        actions.moveToElement(emailField).build().perform();
        return isTooltipPresent(emailField);
                   
    }
    
    public String getToolipContentForInputEmail(){
        WebElement emailField = driver.findElement(inputEmailLocator);        
    //    actions.moveToElement(emailField).click().perform();
        actions.moveToElement(emailField).perform();
        return getTooltipContent(emailField);
                   
    }
    
    //Personal Address input type="url"
    public void setInputUrlValue(String value){
        WebElement urlField = driver.findElement(inputUrlLocator);       
        setValue(urlField, value);
       
    }
    
    public void clearInputUrlValue(){
        WebElement urlField = driver.findElement(inputUrlLocator);       
        clearValue(urlField);
    }
    
    public String getClassForInputUrl(){
        WebElement urlField = driver.findElement(inputUrlLocator);       
        return getClassAttribute(urlField);
                   
    }
    
    public boolean isToolipPresentForInputUrl(){
        WebElement urlField = driver.findElement(inputUrlLocator);       
      //  actions.moveToElement(urlField).click().perform();
        actions.moveToElement(urlField).perform();
        return isTooltipPresent(urlField);
                   
    }
    
    public String getToolipContentForInputUrl(){
        WebElement urlField = driver.findElement(inputUrlLocator);       
       // actions.moveToElement(urlField).click().perform();
        actions.moveToElement(urlField).perform();
        return getTooltipContent(urlField);
                   
    }
    
    // input type="range"
    public void setInputRangeValue(String value){
        WebElement rangeField = driver.findElement(inputRangeLocator);       
        setValue(rangeField, value);
       
    }
    
    public void clearInputRangeValue(){
        WebElement rangeField = driver.findElement(inputRangeLocator);       
        clearValue(rangeField);
    }
    
    public String getClassForInputRange(){
        WebElement rangeField = driver.findElement(inputRangeLocator);       
        return getClassAttribute(rangeField);
                   
    }
    
    public boolean isToolipPresentForInputRange(){
        WebElement rangeField = driver.findElement(inputRangeLocator);       
      //  actions.moveToElement(rangeField).click().perform();
        actions.moveToElement(rangeField).perform();
        return isTooltipPresent(rangeField);
                   
    }
    
    public String getToolipContentForInputRange(){
        WebElement rangeField = driver.findElement(inputRangeLocator);       
     //   actions.moveToElement(rangeField).click().perform();
        actions.moveToElement(rangeField).perform();
        return getTooltipContent(rangeField);
                   
    }
    
}
