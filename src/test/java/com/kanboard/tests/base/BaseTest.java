package com.kanboard.tests.base;

import com.kanboard.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

// Ortak test hazırlık ve kapanış işlemlerini yönetir.
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.createDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver(driver);
    }
}