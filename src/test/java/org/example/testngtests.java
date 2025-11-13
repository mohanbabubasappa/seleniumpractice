package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class testngtests {
    WebDriver driver=new ChromeDriver();
    @Test(priority=1)
    void launchbrowser(){
        driver.get("https://testautomationpractice.blogspot.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("")));
        driver.close();
    }
    @Test(priority=2)
    void handlingalerts(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.manage().window().maximize();
        Alert alert=driver.switchTo().alert();
        alert.accept();
        alert.dismiss();
        alert.sendKeys("");
    }
    @Test(priority=3)
    void actions(){
        Actions actions=new Actions(driver);
        driver.findElement(By.xpath("")).sendKeys("", Keys.ENTER);
        WebElement element1=driver.findElement(By.xpath(""));
        WebElement element2=driver.findElement(By.xpath(""));
        driver.findElement(By.xpath("")).sendKeys("", Keys.ENTER);
        actions.moveToElement(element1).click().perform();
        actions.moveToElement(element2).contextClick(); //right
        actions.moveToElement(element1).doubleClick(); //double
        actions.dragAndDrop(element1,element2).build().perform();
    }
}
