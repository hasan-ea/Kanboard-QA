package com.kanboard.pages;

import com.kanboard.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Yeni personal project oluşturma modalını yönetir.
 */
public class NewPersonalProjectModal extends BasePage {

    private final By projectNameInput = By.cssSelector("#form-name");
    private final By saveButton = By.cssSelector("#project-creation-form button[type='submit']");

    public NewPersonalProjectModal(WebDriver driver) {
        super(driver);
    }

    /**
     * Modalın görüntülendiğini kontrol eder.
     */
    public boolean isDisplayed() {
        logger.info("Yeni personal project modalının görüntülendiği doğrulanıyor");
        boolean displayed = isVisible(projectNameInput);
        logger.info("Yeni personal project modalı görünürlük sonucu: {}", displayed);
        return displayed;
    }

    /**
     * Proje adını girer.
     */
    public void enterProjectName(String projectName) {
        logger.info("Personal project adı giriliyor: {}", projectName);
        type(projectNameInput, projectName);
    }

    /**
     * Projeyi kaydeder.
     */
    public void clickSave() {
        logger.info("Personal project kaydetme işlemi başlatılıyor");
        click(saveButton);
    }

    /**
     * Personal project oluşturur.
     */
    public ProjectSummaryPage createPersonalProject(String projectName) {
        logger.info("Personal project oluşturuluyor: {}", projectName);
        enterProjectName(projectName);
        clickSave();
        return new ProjectSummaryPage(driver);
    }
}