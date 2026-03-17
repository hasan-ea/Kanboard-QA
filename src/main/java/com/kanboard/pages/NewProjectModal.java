package com.kanboard.pages;

import com.kanboard.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Team / normal project create modalı
public class NewProjectModal extends BasePage {


    private final By projectNameInput = By.cssSelector("#form-name");
    private final By identifierInput = By.cssSelector("#form-identifier");
    private final By saveButton = By.cssSelector("#project-creation-form button[type='submit']");

    public NewProjectModal(WebDriver driver) {
        super(driver);
    }

    // Modal açıldı mı
    public boolean isDisplayed() {
        return isVisible(projectNameInput);
    }

    // Project name gir
    public void enterProjectName(String projectName) {
        type(projectNameInput, projectName);
    }

    // Identifier gir
    public void enterIdentifier(String identifier) {
        type(identifierInput, identifier);
    }

    // Kaydet
    public void clickSave() {
        click(saveButton);
    }

    // Sadece name ile project oluştur
    public ProjectSummaryPage createProject(String projectName) {
        enterProjectName(projectName);
        clickSave();
        return new ProjectSummaryPage(driver);
    }

    // Name + identifier ile project oluştur
    public ProjectSummaryPage createProject(String projectName, String identifier) {
        enterProjectName(projectName);
        enterIdentifier(identifier);
        clickSave();
        return new ProjectSummaryPage(driver);
    }
}