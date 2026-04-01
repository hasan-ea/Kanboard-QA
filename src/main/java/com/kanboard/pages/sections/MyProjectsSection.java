package com.kanboard.pages.sections;

import com.kanboard.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Dashboard > My projects bölümündeki proje listesini yönetir.
 */
public class MyProjectsSection extends BasePage {

    private final By myProjectsSectionTitle =
            By.xpath("//h2[contains(normalize-space(),'My projects')]");
    private final By projectList =
            By.xpath("//h2[contains(normalize-space(),'My projects')]/following::div[contains(@class,'table-list')][1]");

    public MyProjectsSection(WebDriver driver) {
        super(driver);
    }

    /**
     * My Projects bölümünün görüntülendiğini kontrol eder.
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
        logger.info("My Projects listesinde proje aranıyor: {}", projectName);

        if (!isVisible(projectList)) {
            logger.info("My Projects listesi görünür değil");
            return false;
        }

        By projectLink = By.xpath(
                "//h2[contains(normalize-space(),'My projects')]/following::div[contains(@class,'table-list')][1]" +
                        "//span[contains(@class,'table-list-title')]//a[normalize-space()='" + projectName + "']"
        );

        boolean listed = isVisible(projectLink);
        logger.info("My Projects listesinde proje bulunma sonucu [{}]: {}", projectName, listed);
        return listed;
    }

    /**
     * Verilen project id için listede görünen proje adını döner.
     */
    public String getListedProjectNameByProjectId(int projectId) {
        logger.info("My Projects listesinde project id ile proje adı okunuyor: {}", projectId);

        By projectLink = By.xpath(
                "//h2[contains(normalize-space(),'My projects')]/following::div[contains(@class,'table-list')][1]" +
                        "//div[contains(@class,'table-list-row') and contains(normalize-space(.), '#" + projectId + "')]" +
                        "//span[contains(@class,'table-list-title')]//a"
        );

        if (driver.findElements(projectLink).isEmpty()) {
            logger.warn("Project id ile proje linki bulunamadı: {}", projectId);
            return "";
        }

        String projectName = driver.findElement(projectLink).getText();
        logger.info("My Projects listesinde okunan proje adı: [{}]", projectName);
        return projectName;
    }
}