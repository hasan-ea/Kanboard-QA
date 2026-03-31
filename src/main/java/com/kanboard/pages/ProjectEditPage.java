package com.kanboard.pages;

import com.kanboard.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Proje düzenleme ekranını yönetir.
 */
public class ProjectEditPage extends BasePage {

    private final By editProjectSectionTitle = By.xpath("//h2[normalize-space()='Edit project']");
    private final By projectNameInput = By.cssSelector("#form-name");
    private final By dashboardLink = By.cssSelector("a[href=\"/dashboard\"]");

    public ProjectEditPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Edit project ekranının görüntülendiğini kontrol eder.
     */
    public boolean isProjectEditPageDisplayed() {
        logger.info("Project Edit sayfasının görüntülendiği doğrulanıyor");
        boolean displayed = wait.until(
                ExpectedConditions.visibilityOfElementLocated(editProjectSectionTitle)
        ).isDisplayed() && isVisible(projectNameInput);

        logger.info("Project Edit sayfası görünürlük sonucu: {}", displayed);
        return displayed;
    }

    /**
     * Name input içindeki mevcut değeri döner.
     */
    public String getProjectNameInputValue() {
        logger.info("Edit project ekranındaki Name input değeri okunuyor");
        String value = getAttribute(projectNameInput, "value");
        logger.info("Edit project input değeri: [{}]", value);
        return value;
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