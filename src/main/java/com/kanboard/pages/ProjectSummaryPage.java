package com.kanboard.pages;

import com.kanboard.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

// Proje oluşturulduktan sonra açılan proje ana/özet ekranını yönetir.
public class ProjectSummaryPage extends BasePage {

    private final By projectTitle = By.cssSelector("div.title-container");
    private final By summarySectionTitle = By.cssSelector("section h2");

    public ProjectSummaryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProjectSummaryPageDisplayed() {
        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(summarySectionTitle, "Summary")
        );
    }

    public boolean isProjectNameDisplayed(String projectName) {
        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(projectTitle, projectName)
        );
    }
}