package com.kanboard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Login sonrası açılan dashboard ekranındaki temel aksiyonları yönetir.
public class DashboardPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By dashboardTitle = By.cssSelector("div.title-container");
    private final By newPersonalProjectLink = By.cssSelector("div[class='page-header'] li:nth-child(1) a:nth-child(1)");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isDashboardDisplayed() {
        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(dashboardTitle, "Dashboard")
        );
    }

    public void clickNewPersonalProject() {
        wait.until(
                ExpectedConditions.elementToBeClickable(newPersonalProjectLink)
        ).click();
    }

    public NewPersonalProjectModal goToNewPersonalProjectModal() {
        clickNewPersonalProject();
        return new NewPersonalProjectModal(driver);
    }
}