package com.kanboard.pages;

import com.kanboard.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Login sonrası açılan dashboard ekranındaki temel aksiyonları yönetir.
public class DashboardPage extends BasePage {

    private final By dashboardTitle = By.cssSelector("div.title-container");
    private final By newProjectLink = By.cssSelector("#main > div > ul > li:nth-child(1) > a");
    private final By newPersonalProjectLink = By.cssSelector("a[href='/project/create/personal']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // Dashboard açıldı mı
    public boolean isDashboardDisplayed() {
        return getText(dashboardTitle).contains("Dashboard");
    }

    // New project linkine tıkla
    public void clickNewProject() {
        click(newProjectLink);
    }

    // New project modalını aç
    public NewProjectModal goToNewProjectModal() {
        clickNewProject();
        return new NewProjectModal(driver);
    }

    // New personal project linkine tıkla
    public void clickNewPersonalProject() {
        click(newPersonalProjectLink);
    }

    // New personal project modalını aç
    public NewPersonalProjectModal goToNewPersonalProjectModal() {
        clickNewPersonalProject();
        return new NewPersonalProjectModal(driver);
    }
}