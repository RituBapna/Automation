package oj.tests.practice;

import java.io.File;
import java.lang.*;
import java.util.List;
import java.util.NoSuchElementException;

import oracle.ojet.automation.test.OJetBase;
import oracle.ojet.automation.test.TestConfigBuilder;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//D:\OJET\ADFjs\trunk\rt\src\test\testUtils\src\oracle\ojet\automation\test

public class NavigationTest extends OJetBase {
    public NavigationTest() {
        super(new TestConfigBuilder().setContextRoot("built/apps/components").setAppRoot("public_html").build());
    }

      @Test(groups = { "cookbook" })
         public void screenshotTest() throws Exception {
            //startupTest("uiComponents-listView-staticListView.html", null);
            //verifyTitle("Incorrect page title;","List View - Static Content");
            //waitForElementVisible("id=listview");
            
            getWebDriver().manage().window().maximize();
              getWebDriver().get("https://www.google.co.in");
             try{Thread.sleep(1000);}catch(Exception e){System.out.println("Failure to take screenshot "+e);}
             
            //getWebDriver().navigate().refresh();
             //try{Thread.sleep(1000);}catch(Exception e){System.out.println("Failure to take screenshot "+e);}
             
             //getWebDriver().navigate().back();
             // try{Thread.sleep(1000);}catch(Exception e){System.out.println("Failure to take screenshot "+e);}
              
              //getWebDriver().navigate().forward();
              // try{Thread.sleep(1000);}catch(Exception e){System.out.println("Failure to take screenshot "+e);}
             
             getWebDriver().navigate().to("http://mail.google.com");
              try{Thread.sleep(100);}catch(Exception e){System.out.println("Failure to take screenshot "+e);}
              
              
             WebElement email= getWebDriver().findElement(By.id("Email"));
             email.sendKeys("rits77.jain@gmail.com");
             WebElement next = getWebDriver().findElement(By.id("next"));
             waitForMilliseconds(500L);
             next.click();
             waitForMilliseconds(500L);
             WebElement pass = getWebDriver().findElement(By.id("Passwd"));
             pass.sendKeys("diarituyash173003");
             waitForMilliseconds(500L);
             WebElement signIn =getWebDriver().findElement(By.id("signIn"));
             signIn.click();
             waitForMilliseconds(4000L);
             //driver.findElement(By.xpath("//div[text()='COMPOSE']")).click();
             getWebDriver().findElement(By.xpath("//div[contains(text(),'COMPOSE')]")).click();
             waitForMilliseconds(3000L);
             //getWebDriver().findElement(By.xpath("//div[contains(text(),'Recipients')]")).click();
             // waitForMilliseconds(1000L);
             getWebDriver().findElement(By.xpath("//textarea[@name='to']")).sendKeys("rits77.jain@gmail.com");
             waitForMilliseconds(1000L);
            getWebDriver().findElement(By.xpath("//input[@name='subjectbox']")).sendKeys("Email through Automation");

            waitForMilliseconds(1000L);
             //getWebDriver().findElement(By.xpath("//span[contains(text(),'Cc')]")).click();
            // getWebDriver().findElement(By.xpath("//div[@class='aA6']/span/span/span[@class='aB gQ pE']")).click();
             getWebDriver().findElement(By.xpath("//table[@class='IZ']//div[@class='a1 aaA aMZ']")).click();
             
             //WebElement attachFile = getWebDriver().findElement(By.xpath("//div[@class='a1 aaA aMZ']")).click();
             waitForMilliseconds(1000L);
                    /* Process p=Runtime.getRuntime().exec("");
                    p.waitFor();*/
             Process p=Runtime.getRuntime().exec("D:\\OJET\\ADFjs\\trunk\\rt\\src\\test\\webdriver\\src\\oj\\tests\\practice\\upload.exe");
             p.waitFor();
             waitForMilliseconds(4000L);

            WebElement element = getWebDriver().findElement(By.xpath("//div[@class='Ar Au']//div"));
            
            element.click();
            element.sendKeys("Hi ");
            waitForMilliseconds(1000L);
             getWebDriver().findElement(By.xpath("//div[contains(text(),'Send')]")).click();
             waitForMilliseconds(2000L);

         }                                      
}
