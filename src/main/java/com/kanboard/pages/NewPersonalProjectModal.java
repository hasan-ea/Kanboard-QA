package com.kanboard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Dashboard üzerinde açılan New personal project modalını yönetir.
public class NewPersonalProjectModal {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By modalTitle = By.cssSelector("div[class='page-header'] h2");
    private final By projectNameInput = By.cssSelector("#form-name");
    private final By identifierInput = By.cssSelector("#form-identifier");
    private final By saveButton = By.cssSelector("#project-creation-form > div.js-submit-buttons-rendered > div > button");

    public NewPersonalProjectModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(modalTitle)
        ).isDisplayed();
    }

    public void enterProjectName(String projectName) {
        WebElement nameElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(projectNameInput)
        );
        nameElement.clear();
        nameElement.sendKeys(projectName);
    }

    public void enterIdentifier(String identifier) {
        WebElement identifierElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(identifierInput)
        );
        identifierElement.clear();
        identifierElement.sendKeys(identifier);
    }

    public void clickSave() {
        wait.until(
                ExpectedConditions.elementToBeClickable(saveButton)
        ).click();
    }

    public ProjectSummaryPage createProject(String projectName) {
        enterProjectName(projectName);
        clickSave();
        return new ProjectSummaryPage(driver);
    }
}