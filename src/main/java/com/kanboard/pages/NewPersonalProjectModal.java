package com.kanboard.pages;

import com.kanboard.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Dashboard üzerinde açılan New personal project modalını yönetir.
public class NewPersonalProjectModal extends BasePage {

    private final By projectNameInput = By.cssSelector("#form-name");
    private final By identifierInput = By.cssSelector("#form-identifier");
    private final By saveButton = By.cssSelector("#project-creation-form > div.js-submit-buttons-rendered > div > button");

    public NewPersonalProjectModal(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return isVisible(projectNameInput);
    }

    public void enterProjectName(String projectName) {
        type(projectNameInput, projectName);
    }

    public void enterIdentifier(String identifier) {
        type(identifierInput, identifier);
    }

    public void clickSave() {
        click(saveButton);
    }

    public ProjectSummaryPage createProject(String projectName) {
        enterProjectName(projectName);
        clickSave();
        return new ProjectSummaryPage(driver);
    }
}