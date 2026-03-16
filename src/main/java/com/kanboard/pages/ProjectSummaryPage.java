package com.kanboard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Proje oluşturulduktan sonra açılan proje ana/özet ekranını yönetir.
public class ProjectSummaryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By projectTitle = By.cssSelector("div.title-container");
    private final By summarySectionTitle = By.cssSelector("section h2");

    public ProjectSummaryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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