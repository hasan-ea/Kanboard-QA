package com.kanboard.pages.sections;

import com.kanboard.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * My projects sayfasındaki proje listesini yönetir.
 */
public class MyProjectsSection extends BasePage {

    private final By myProjectsSectionTitle =
            By.xpath("//div[contains(@class,'sidebar-content')]//*[normalize-space()='My projects']");
    private final By projectList = By.cssSelector("div.sidebar-content div.table-list");

    public MyProjectsSection(WebDriver driver) {
        super(driver);
    }

    /**
     * Sayfanın görüntülendiğini kontrol eder.
     */
    public boolean isSectionDisplayed() {
        logger.info("My Projects section görünürlüğü doğrulanıyor");
        boolean displayed = isVisible(myProjectsSectionTitle) && isVisible(projectList);
        logger.info("My Projects section görünürlük sonucu: {}", displayed);
        return displayed;
    }

    /**
     * Verilen projenin listede yer aldığını kontrol eder.
     */
    public boolean isProjectListed(String projectName) {
        logger.info("Projects listesinde projenin bulunduğu doğrulanıyor: {}", projectName);

        if (!isVisible(projectList)) {
            logger.info("Projects listesi görünür değil, proje doğrulaması yapılamadı");
            return false;
        }

        By projectLink = By.xpath(
                "//div[contains(@class,'sidebar-content')]//div[contains(@class,'table-list')]//span[contains(@class,'table-list-title')]//a[normalize-space()='" + projectName + "']"
        );

        boolean listed = isVisible(projectLink);
        logger.info("Projects listesinde proje bulunma sonucu [{}]: {}", projectName, listed);
        return listed;
    }

    public String getListedProjectNameByProjectId(int projectId) {
        logger.info("My projects listesinde project id ile proje adı okunuyor: {}", projectId);

        By projectLink = By.xpath("//a[@href='/project/" + projectId + "']");

        if (driver.findElements(projectLink).isEmpty()) {
            logger.warn("Project id ile proje linki bulunamadı: {}", projectId);
            return "";
        }

        String projectName = driver.findElement(projectLink).getText();
        logger.info("My projects listesinde okunan proje adı: [{}]", projectName);
        return projectName;
    }
}