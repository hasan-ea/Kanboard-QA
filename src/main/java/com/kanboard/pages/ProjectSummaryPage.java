package com.kanboard.pages;

import com.kanboard.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Proje oluşturulduktan sonra açılan proje özet ekranını yönetir.
 */
public class ProjectSummaryPage extends BasePage {

    private final By projectTitle = By.cssSelector("span.title");
    private final By summarySectionTitle = By.cssSelector("section h2");
    private final By dashboardLink = By.cssSelector("a[href=\"/dashboard\"]");
    private final By editProjectLink = By.linkText("Edit project");

    public ProjectSummaryPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Proje özet sayfasının görüntülendiğini kontrol eder.
     */
    public boolean isProjectSummaryPageDisplayed() {
        logger.info("Project Summary sayfasının görüntülendiği doğrulanıyor");
        boolean displayed = wait.until(
                ExpectedConditions.textToBePresentInElementLocated(summarySectionTitle, "Summary")
        );
        logger.info("Project Summary sayfası görünürlük sonucu: {}", displayed);
        return displayed;
    }

    /**
     * Proje adının ekranda görüntülendiğini kontrol eder.
     */
    public boolean isProjectNameDisplayed(String projectName) {
        logger.info("Project Summary sayfasında proje adının görüntülendiği doğrulanıyor: {}", projectName);
        boolean displayed = wait.until(
                ExpectedConditions.textToBePresentInElementLocated(projectTitle, projectName)
        );
        logger.info("Project Summary proje adı görünürlük sonucu [{}]: {}", projectName, displayed);
        return displayed;
    }

    /**
     * Sayfadaki proje başlığını döner.
     */
    public String getProjectTitleText() {
        logger.info("Project Summary sayfasındaki proje başlığı okunuyor");
        String title = getText(projectTitle).trim();
        logger.info("Okunan proje başlığı: {}", title);
        return title;
    }

    /**
     * Edit project ekranına gider.
     */
    public ProjectEditPage goToEditProjectPage() {
        logger.info("Edit project ekranına gidiliyor");
        click(editProjectLink);
        return new ProjectEditPage(driver);
    }

    /**
     * Dashboard sayfasına gider.
     */
    public DashboardPage goToDashboardPage() {
        logger.info("Dashboard sayfasına gidiliyor");
        click(dashboardLink);
        return new DashboardPage(driver);
    }
}