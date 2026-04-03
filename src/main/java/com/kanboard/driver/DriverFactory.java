package com.kanboard.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

// Tarayıcı oluşturma ve kapatma işlemlerini tek yerden yönetir.
public class DriverFactory {

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        if (!browser.equalsIgnoreCase("chrome")) {
            throw new IllegalArgumentException("Currently only chrome is supported. Actual: " + browser);
        }

        ChromeOptions options = new ChromeOptions();

        String chromeBinary = System.getenv("CHROME_BIN");
        if (chromeBinary != null && !chromeBinary.isBlank()) {
            options.setBinary(chromeBinary);
        }

        if (headless) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        return driver;
    }

    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}