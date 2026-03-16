package com.kanboard.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By usernameInput = By.cssSelector("#form-username");
    private final By passwordInput = By.cssSelector("#form-password");
    private final By signInButton = By.cssSelector("button[type='submit']");
    private final By errorMessageBox = By.cssSelector(".alert.alert-error");

    public void open() {
        String baseUrl = System.getProperty("base.url");
        driver.get(baseUrl);
    }

    public boolean isLoginPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameInput)
        ).isDisplayed();
    }

    public void enterUsername(String username) {
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        usernameElement.clear();
        usernameElement.sendKeys(username);
    }
    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }
    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSignIn();
    }
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageBox)).getText().trim();
    }




}
