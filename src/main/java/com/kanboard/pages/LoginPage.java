package com.kanboard.pages;

import com.kanboard.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Login sayfasındaki işlemleri yönetir.
 */
public class LoginPage extends BasePage {

    private final By usernameInput = By.cssSelector("#form-username");
    private final By passwordInput = By.cssSelector("#form-password");
    private final By signInButton = By.cssSelector("button[type='submit']");
    private final By errorMessageBox = By.cssSelector(".alert.alert-error");

    /**
     * Sayfa nesnesini oluşturur.
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Login sayfasını açar.
     */
    public void open() {
        String baseUrl = System.getProperty("base.url");
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException("base.url system property tanımlı değil.");
        }

        logger.info("Login sayfası açılıyor");
        driver.get(baseUrl);
    }

    /**
     * Login sayfasının görüntülendiğini kontrol eder.
     */
    public boolean isLoginPageDisplayed() {
        return isVisible(usernameInput);
    }

    /**
     * Kullanıcı adı alanını doldurur.
     */
    public void enterUsername(String username) {
        logger.info("Kullanıcı adı giriliyor");
        type(usernameInput, username);
    }

    /**
     * Parola alanını doldurur.
     */
    public void enterPassword(String password) {
        logger.info("Parola giriliyor");
        typeSecret(passwordInput, password);
    }

    /**
     * Formu gönderir.
     */
    public void clickSignIn() {
        logger.info("Login formu gönderiliyor");
        click(signInButton);
    }

    /**
     * Verilen bilgilerle giriş yapar.
     */
    public void login(String username, String password) {
        logger.info("Login işlemi başlatıldı");
        enterUsername(username);
        enterPassword(password);
        clickSignIn();
    }

    /**
     * Hata mesajını döner.
     */
    public String getErrorMessage() {
        return getText(errorMessageBox).trim();
    }
}