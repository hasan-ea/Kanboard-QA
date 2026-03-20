package com.kanboard.pages;

import com.kanboard.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * My projects sayfasındaki proje listesini yönetir.
 */
public class ProjectsPage extends BasePage {

    private final By myProjectsSectionTitle =
            By.xpath("//div[contains(@class,'sidebar-content')]//*[normalize-space()='My projects']");
    private final By projectList = By.cssSelector("div.sidebar-content div.table-list");

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Sayfanın görüntülendiğini kontrol eder.
     */
    public boolean isDisplayed() {
        logger.info("Projects sayfasının görüntülendiği doğrulanıyor");
        boolean displayed = isVisible(myProjectsSectionTitle) && isVisible(projectList);
        logger.info("Projects sayfası görünürlük sonucu: {}", displayed);
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
}