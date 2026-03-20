package com.kanboard.pages;

import com.kanboard.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Yeni proje oluşturma modalını yönetir.
 */
public class NewProjectModal extends BasePage {

    private final By projectNameInput = By.cssSelector("#form-name");
    private final By identifierInput = By.cssSelector("#form-identifier");
    private final By saveButton = By.cssSelector("#project-creation-form button[type='submit']");

    public NewProjectModal(WebDriver driver) {
        super(driver);
    }

    /**
     * Modalın görüntülendiğini kontrol eder.
     */
    public boolean isDisplayed() {
        logger.info("Yeni proje oluşturma modalının görüntülendiği doğrulanıyor");
        boolean displayed = isVisible(projectNameInput);
        logger.info("Yeni proje oluşturma modalı görünürlük sonucu: {}", displayed);
        return displayed;
    }

    /**
     * Proje adını girer.
     */
    public void enterProjectName(String projectName) {
        logger.info("Proje adı giriliyor: {}", projectName);
        type(projectNameInput, projectName);
    }

    /**
     * Proje identifier alanını doldurur.
     */
    public void enterIdentifier(String identifier) {
        logger.info("Proje identifier giriliyor: {}", identifier);
        type(identifierInput, identifier);
    }

    /**
     * Projeyi kaydeder.
     */
    public void clickSave() {
        logger.info("Proje kaydetme işlemi başlatılıyor");
        click(saveButton);
    }

    /**
     * Yalnızca proje adıyla proje oluşturur.
     */
    public ProjectSummaryPage createProject(String projectName) {
        logger.info("Sadece proje adı ile proje oluşturuluyor: {}", projectName);
        enterProjectName(projectName);
        clickSave();
        return new ProjectSummaryPage(driver);
    }

    /**
     * Proje adı ve identifier ile proje oluşturur.
     */
    public ProjectSummaryPage createProject(String projectName, String identifier) {
        logger.info("Proje adı ve identifier ile proje oluşturuluyor. Proje adı: {}", projectName);
        enterProjectName(projectName);
        enterIdentifier(identifier);
        clickSave();
        return new ProjectSummaryPage(driver);
    }
}