package com.atoa.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;

import com.atoa.qa.util.TestUtil;

public class TestBase {
    public static WebDriver driver;
    public static Properties prop;
  

    // Constructor to load properties
    public TestBase() {
        prop = new Properties();
        String configFilePath = System.getProperty("user.dir") + "/src/main/java/com/atoa/qa/config/config.properties";
        try (FileInputStream ip = new FileInputStream(configFilePath)) {
            prop.load(ip);
        } catch (IOException e) {
            System.err.println("Error loading properties: " + e.getMessage());
        }
    }

    // Method to initialize the WebDriver
   // @SuppressWarnings("deprecation")
	public static void initialization() {
        String browserName = prop.getProperty("browser", "chrome").toLowerCase();
        switch (browserName) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                System.err.println("Browser not supported: " + browserName);
                return;
        }

        // Browser configuration
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
 
        // Launch the URL
      //  driver.get(prop.getProperty("url", "http://default-url.com"));
        driver.get(prop.getProperty("url"));
    }

    // Method to quit the WebDriver
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Set driver to null after quitting
        }
    }
    
    @AfterSuite
    public static void sendEmail() {
    	
    	TestUtil.sendEmail();
    }
}
