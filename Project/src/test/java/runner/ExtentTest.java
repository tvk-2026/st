package runner;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.EventHandler;
import utils.ExcelReader;
import utils.ExtentManager;

import java.net.URL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

public class ExtentTest {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;
    String excelPath = System.getProperty("user.dir") + "/testdata/LoginData.xlsx";

    @BeforeTest
    public void startReport() {
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setup() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), new ChromeOptions());
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        WebDriverListener listener = new EventHandler();
        driver = new EventFiringDecorator<>(listener).decorate(driver);
    }

    @Test
    public void validLoginTest() throws IOException {
        test = extent.createTest("Valid Login Test");

        driver.get("https://opensource-demo.orangehrmlive.com/");
        String username = ExcelReader.getCelData(excelPath, "Sheet1", 1, 0);
        String password = ExcelReader.getCelData(excelPath, "Sheet1", 1, 1);

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        boolean isDashboard = driver.getPageSource().contains("Dashboard");
        Assert.assertTrue(isDashboard, "Dashboard not found!");
        test.pass("Login successful and Dashboard found.");
    }

    @Test
    public void invalidLoginTest() throws IOException {
        test = extent.createTest("Invalid Login Test");

        driver.get("https://opensource-demo.orangehrmlive.com/");
        String username = ExcelReader.getCelData(excelPath, "Sheet1", 2, 0);
        String password = ExcelReader.getCelData(excelPath, "Sheet1", 2, 1);

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        boolean isError = driver.getPageSource().contains("Invalid credentials");
        Assert.assertTrue(isError, "Invalid credentials message not found!");
        test.pass("Proper error message displayed for invalid login.");
    }
    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @AfterTest
    public void endReport() {
        extent.flush();
    }
//     @BeforeMethod
//     public void setup() throws MalformedURLException {

//         WebDriver driver;
//         driver = new RemoteWebDriver(new URL("http://localhost:4444"), new ChromeOptions());
//         driver.manage().window().maximize();
//         driver.get("");
//         driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
//         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//         WebDriverListener listener = new EventHandler();
//         driver = new EventFiringDecorator<>(listener).decorate(driver);
//     }

// //    @Test 

//     @AfterMethod
//     public void teardown()
//     {
//         //quit your driver
//         //flush your reports
//     }
}