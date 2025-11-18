package org.example;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class testaments {
    WebDriver driver=new ChromeDriver();

    @Test(enabled = false)
    public void skippedTest() {  }  // won’t execute

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][] {
                {"admin", "1234"},
                {"user", "abcd"}
        };
    }
    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        System.out.println("Testing with: " + username + " / " + password);
    }
    @Test
    @Parameters("browser")
    public void launchBrowser(String browser) {
        System.out.println("Launching browser: " + browser);
    }
    @Test(priority=1,dependsOnMethods = {"launchBrowser"})
    void driverlaunch(){
        driver.get("https://testautomationpractice.blogspot.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("")));
        driver.quit();
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
    @Test(priority=4)
    void screenshots() {
        TakesScreenshot ts=(TakesScreenshot) driver;
        File srcfile=ts.getScreenshotAs(OutputType.FILE);
        File destfile=new File("");
        //FileUtils.copyFile(srcfile,destfile);
    }
    @Test(priority=4)
    void javascript() {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()",driver.findElement(By.xpath("")));
        js.executeScript("arguments[0].setAttribute('value','mohan'')",driver.findElement(By.xpath("")));
        js.executeScript("window.scrollBy(0,1000)");
    }
    @Test(priority = 5)
    void brokenLinks() throws IOException {
        List<WebElement> elements=driver.findElements(By.tagName("a"));
        for(WebElement element:elements){
            String urlLink=element.getAttribute("href");
            URL link=new URL(urlLink);
            HttpURLConnection httpURLConnection=(HttpURLConnection)link.openConnection();
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==200){
                System.out.println("NOT broken link");
            }
            else{
                System.out.println("BROKEN link");
            }
        }
    }
    @Test(priority = 6)
    void excelData() throws IOException {
        FileInputStream fis=new FileInputStream("");
        XSSFWorkbook workBook=new XSSFWorkbook(fis);
        XSSFSheet sheet=workBook.getSheet("");
        int rows=sheet.getLastRowNum();
        int cells=sheet.getRow(0).getLastCellNum();
        for(int r=0;r<rows;r++){
            XSSFRow row=sheet.getRow(r);
            for(int c=0;c<cells;c++) {
                XSSFCell cell = row.getCell(c);
                String cellValue = cell.toString();
                System.out.println(cellValue);
            }
        }
        workBook.close();
        fis.close();
    }
}
