package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.ExtentReports;

import utils.*;

public class HybridTest extends Base {
   WebDriverHelper helper;
   ExtentReports reports;
   ExtentTest test;
   @BeforeMethod
   public void setUp(){
    openBrowser();
    helper=new WebDriverHelper(driver);
    reports=Reporter.generateExtentReport("Demo");
   }    
   
   @Test
   public void f(){
       test=reports.createTest("f");
       helper.hoverOverElement(By.xpath("(//span[text()='Company'])[1]"));
       helper.clickOnElement(By.linkText("About Us"));
     LoggerHandler.info("click on about us");
     test.pass("click on about us");
     test.log(Status.PASS, "click on about us");
     helper.javascriptScroll(By.xpath("(//img[@alt='Footer Brands'])[6]"));
     helper.clickOnElement(By.xpath("(//img[@alt='Footer Brands'])[6]"));
     for(int i=0;i<=10;i++)
     LoggerHandler.info("click on ginger");
     helper.switchToNewWindow();
     helper.clickOnElement(By.xpath("//div[text()=\"Book Your Stay\"]"));
     Screenshot.captureScreenShot("filee");
     Reporter.attachScreenshotToReport("filee", test, "filee");
    }
    @Test
    public void f2(){
        test=reports.createTest("f2");
        helper.hoverOverElement(By.xpath("//span[text()=\"Contact us\"]"));
        helper.clickOnElement(By.xpath("//span[text()='Contact us']"));
        Assert.assertEquals(helper.getText(By.xpath("//h2[text()='Worldwide Reservations Centre']")), "Worldwide Reservations Centre");
        Screenshot.captureScreenShot("contact_screenshot");
        Reporter.attachScreenshotToReport("contact_screenshot", test, "contact_screenshot");
    }
    @Test
    public void f3(){
        test=reports.createTest("f3");
        helper.javascriptScroll(By.xpath("//a[text()=\"Privacy Policy\"]"));
        helper.clickOnElement(By.xpath("//a[text()=\"Privacy Policy\"]"));
        helper.switchToNewWindow();
        helper.javascriptScroll(By.xpath("//p[text()=\"Subscribe\"]"));
        helper.clickOnElement(By.xpath("//p[text()='Subscribe']"));
        Assert.assertEquals(helper.getText(By.xpath("//p[text()='Subscribe to our Newsletter']")),"Subscribe to our Newsletter");
        Screenshot.captureScreenShot("contact_screenshot1");
        Reporter.attachScreenshotToReport("contact_screenshot1", test, "contact_screenshot1");
        
   }

   @AfterMethod
   public void close(){

    driver.quit();
    reports.flush();

   }
}