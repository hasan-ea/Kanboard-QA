package com.kanboard.base;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tüm page object sınıfları için ortak UI yardımcılarını sağlar.
 */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Driver ve explicit wait nesnelerini hazırlar.
     */
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Element görünür olana kadar bekler.
     */
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Element tıklanabilir olana kadar bekler.
     */
    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Element DOM içinde mevcut olana kadar bekler.
     */
    protected WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Elemente tıklar.
     */
    protected void click(By locator) {
        waitForClickable(locator).click();
    }

    /**
     * Alana metin girer.
     */
    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Hassas veri içeren alana gerçek değeri loglamadan giriş yapar.
     */
    protected void typeSecret(By locator, String value) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Element metnini döner.
     */
    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    /**
     * Element attribute değerini döner.
     */
    protected String getAttribute(By locator, String attributeName) {
        return waitForPresence(locator).getAttribute(attributeName);
    }

    /**
     * Element görünürse true döner.
     */
    protected boolean isVisible(By locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (StaleElementReferenceException exception) {
            logger.debug("Element stale oldu, tekrar kontrol ediliyor: {}", locator);
            try {
                return waitForVisibility(locator).isDisplayed();
            } catch (TimeoutException | StaleElementReferenceException retryException) {
                logger.debug("Element görünür değil: {}", locator);
                return false;
            }
        } catch (TimeoutException exception) {
            logger.debug("Element görünür değil: {}", locator);
            return false;
        }
    }

    /**
     * Element mevcutsa true döner.
     */
    protected boolean isPresent(By locator) {
        try {
            return waitForPresence(locator) != null;
        } catch (TimeoutException exception) {
            logger.debug("Element bulunamadı: {}", locator);
            return false;
        }
    }

    /**
     * Mevcut sayfanın URL bilgisini döner.
     */
    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Mevcut sayfanın başlığını döner.
     */
    protected String getPageTitle() {
        return driver.getTitle();
    }
}