package runner;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import com.aventstack.extentreports.ExtentTest;

import objectRepository.HomePageObjects;
import utils.*;

public class PageObjectTest extends Base {
    WebDriverHelper wb;
    ExtentTest test;

    @BeforeMethod
    public void setup() {
        openBrowser();
        wb = new WebDriverHelper(driver);
    }

    @Test
    public void testSignInFlow() throws Exception {
        test = Reporter.generateExtentReport("Demo_Report").createTest("SignInFlow");

        wb.clickOnElement(HomePageObjects.signInBtn);
        LoggerHandler.info("Clicked on Sign In");

        wb.clickOnElement(HomePageObjects.imageLink);
        wb.switchToNewWindow();

        wb.clickOnElement(HomePageObjects.blogLink);
        LoggerHandler.info("Clicked on Blog");

        Screenshot.captureScreenShot("signin_flow");
        test.addScreenCaptureFromPath(System.getProperty("user.dir")+"/screenshots/signin_flow.png");
    }

    @AfterMethod
    public void tearDown() {
        if(driver != null) driver.quit();
    }
}