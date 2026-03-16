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

        // Şimdilik sadece Chrome destekleniyor.
        if (!browser.equalsIgnoreCase("chrome")) {
            throw new IllegalArgumentException("Currently only chrome is supported. Actual: " + browser);
        }

        ChromeOptions options = new ChromeOptions();

        // İstenirse tarayıcı görünmeden çalıştırılır.
        if (headless) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);

        // Sayfa yüklenmesi için üst sınır.
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        // Explicit wait kullandığımız için implicit wait kapalı tutuldu.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        return driver;
    }

    public static void quitDriver(WebDriver driver) {
        // Null kontrolü ile güvenli kapatma yapılır.
        if (driver != null) {
            driver.quit();
        }
    }
}