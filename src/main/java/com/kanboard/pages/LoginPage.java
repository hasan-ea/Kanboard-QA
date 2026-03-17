package com.kanboard.pages;

import com.kanboard.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameInput = By.cssSelector("#form-username");
    private final By passwordInput = By.cssSelector("#form-password");
    private final By signInButton = By.cssSelector("button[type='submit']");
    private final By errorMessageBox = By.cssSelector(".alert.alert-error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = System.getProperty("base.url");
        driver.get(baseUrl);
    }

    public boolean isLoginPageDisplayed() {
        return isVisible(usernameInput);
    }

    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickSignIn() {
        click(signInButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSignIn();
    }

    public String getErrorMessage() {
        return getText(errorMessageBox).trim();
    }
}