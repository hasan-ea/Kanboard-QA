package com.kanboard.tests.base;

import com.kanboard.driver.DriverFactory;
import com.kanboard.utils.PowerShellUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

// Ortak test hazırlık ve kapanış işlemlerini yönetir.
public abstract class BaseTest {

    protected WebDriver driver;

    private static final String RESTORE_SCRIPT =
            System.getProperty("user.dir") + "\\scripts\\restore-baseline.ps1";

    @BeforeClass(alwaysRun = true)
    public void restoreDatabaseBeforeClass() {
        restoreBaseline();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpDriver() {
        driver = DriverFactory.createDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass(alwaysRun = true)
    public void restoreDatabaseAfterClass() {
        restoreBaseline();
    }

    protected void restoreBaseline() {
        PowerShellUtils.runScript(RESTORE_SCRIPT);
    }
}