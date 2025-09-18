package runner;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import utils.EventHandler;

public class ConfigTest {
    public static WebDriver driver;
    public static Properties prop;

    @BeforeMethod
    public void openBrowser() throws MalformedURLException {
        // Load properties from resources/config folder
        prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config/config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print properties to console
        System.out.println("URL: " + prop.getProperty("url"));
        System.out.println("Username: " + prop.getProperty("username"));
        System.out.println("Password: " + prop.getProperty("password"));

        // Setup Selenium Remote WebDriver
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/"), options);

        // Event listener
        WebDriverListener listener = new EventHandler();
        driver = new EventFiringDecorator<>(listener).decorate(driver);
    }

    @Test
    public void openWebsite() {
        driver.get(prop.getProperty("url"));
        System.out.println("Page title is: " + driver.getTitle());
    }

    @AfterMethod
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}