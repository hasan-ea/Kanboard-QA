package com.kanboard.pages;

import com.kanboard.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Project settings ekranını yönetir.
 */
public class ProjectSettingsPage extends BasePage {

    private final By editProjectLink = By.xpath("//a[text()='Edit project']");
    private final By projectNameInput = By.cssSelector("#form-name");
    private final By dashboardLink = By.cssSelector("a[href=\"/dashboard\"]");

    public ProjectSettingsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        logger.info("Project settings ekranının görüntülendiği doğrulanıyor");
        return isVisible(editProjectLink) || isVisible(projectNameInput);
    }

    public void openEditProject() {
        logger.info("Edit project ekranı açılıyor");
        if (isVisible(editProjectLink)) {
            click(editProjectLink);
        }
    }

    public String getProjectNameInputValue() {
        logger.info("Edit project ekranındaki project name input değeri okunuyor");
        return getAttribute(projectNameInput, "value");
    }

    public DashboardPage goToDashboardPage() {
        logger.info("Dashboard ekranına dönülüyor");
        click(dashboardLink);
        return new DashboardPage(driver);
    }
}