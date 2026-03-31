package com.kanboard.pages;

import com.kanboard.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Dashboard sayfasındaki temel işlemleri yönetir.
 */
public class DashboardPage extends BasePage {

    private final By dashboardTitle = By.cssSelector("div.title-container");
    private final By newProjectLink = By.cssSelector("div.page-header a[href=\"/project/create\"]");
    private final By newPersonalProjectLink = By.cssSelector("div.page-header a[href='/project/create/personal']");
    private final By myProjectsLink = By.linkText("My projects");
    private final By dashboardProjectList = By.cssSelector("div.table-list");
    private final By nextPageLink = By.cssSelector("a[title='Next']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Dashboard sayfasının görüntülendiğini kontrol eder.
     */
    public boolean isDashboardDisplayed() {
        logger.info("Dashboard sayfasının görüntülendiği doğrulanıyor");
        boolean displayed = isVisible(dashboardTitle) && getText(dashboardTitle).contains("Dashboard");
        logger.info("Dashboard görünürlük sonucu: {}", displayed);
        return displayed;
    }

    /**
     * Yeni proje butonunun görünür olduğunu kontrol eder.
     */
    public boolean isNewProjectVisible() {
        logger.info("'New project' butonunun görünürlüğü kontrol ediliyor");
        return isVisible(newProjectLink);
    }

    /**
     * Yeni kişisel proje butonunun görünür olduğunu kontrol eder.
     */
    public boolean isNewPersonalProjectVisible() {
        logger.info("'New personal project' butonunun görünürlüğü kontrol ediliyor");
        return isVisible(newPersonalProjectLink);
    }

    /**
     * Yeni proje oluşturma ekranını açar.
     */
    public void clickNewProject() {
        logger.info("Yeni proje oluşturma ekranı açılıyor");
        click(newProjectLink);
    }

    /**
     * Yeni proje modalını açar.
     */
    public NewProjectModal goToNewProjectModal() {
        clickNewProject();
        return new NewProjectModal(driver);
    }

    /**
     * Yeni kişisel proje oluşturma ekranını açar.
     */
    public void clickNewPersonalProject() {
        logger.info("Yeni kişisel proje oluşturma ekranı açılıyor");
        click(newPersonalProjectLink);
    }

    /**
     * Yeni kişisel proje modalını açar.
     */
    public NewPersonalProjectModal goToNewPersonalProjectModal() {
        clickNewPersonalProject();
        return new NewPersonalProjectModal(driver);
    }

    /**
     * Dashboard sayfalarında projeyi arar.
     */
    public boolean isProjectListedInDashboardPages(String projectName) {
        logger.info("Dashboard sayfalarında proje aranıyor: {}", projectName);

        while (true) {
            if (isProjectListedInCurrentDashboardPage(projectName)) {
                logger.info("Proje dashboard sayfalarında bulundu: {}", projectName);
                return true;
            }

            if (!isVisible(nextPageLink)) {
                logger.info("Sonraki dashboard sayfası yok. Proje bulunamadı: {}", projectName);
                return false;
            }

            logger.info("Proje mevcut dashboard sayfasında bulunamadı, sonraki sayfaya geçiliyor");
            click(nextPageLink);
        }
    }

    /**
     * Mevcut dashboard sayfasında projenin listelendiğini kontrol eder.
     */
    public boolean isProjectListedInCurrentDashboardPage(String projectName) {
        logger.info("Mevcut dashboard sayfasında proje aranıyor: {}", projectName);

        if (!isVisible(dashboardProjectList)) {
            logger.info("Dashboard project list görünür değil");
            return false;
        }

        By projectLinkInDashboardList = By.xpath(
                "//div[contains(@class,'table-list')]//span[contains(@class,'table-list-title')]//a[normalize-space()='" + projectName + "']"
        );

        boolean listed = isVisible(projectLinkInDashboardList);
        logger.info("Mevcut dashboard sayfasında proje bulunma sonucu [{}]: {}", projectName, listed);
        return listed;
    }

    /**
     * Dashboard sayfalarında verilen proje adının listede görünen halini döner.
     */
    public String getListedProjectNameInDashboardPages(String projectName) {
        logger.info("Dashboard sayfalarında proje adının listede görünen hali okunuyor: {}", projectName);

        while (true) {
            String listedName = getListedProjectNameInCurrentDashboardPage(projectName);
            if (listedName != null) {
                logger.info("Dashboard listesinde okunan proje adı: {}", listedName);
                return listedName;
            }

            if (!isVisible(nextPageLink)) {
                throw new RuntimeException("Dashboard listesinde proje bulunamadı: " + projectName);
            }

            logger.info("Proje mevcut dashboard sayfasında bulunamadı, sonraki sayfaya geçiliyor");
            click(nextPageLink);
        }
    }

    /**
     * Mevcut dashboard sayfasında verilen proje adının listede görünen halini döner.
     */
    public String getListedProjectNameInCurrentDashboardPage(String projectName) {
        logger.info("Mevcut dashboard sayfasında proje adının listede görünen hali okunuyor: {}", projectName);

        if (!isVisible(dashboardProjectList)) {
            logger.info("Dashboard project list görünür değil");
            return null;
        }

        By projectLinkInDashboardList = By.xpath(
                "//div[contains(@class,'table-list')]//span[contains(@class,'table-list-title')]//a[normalize-space()='" + projectName + "']"
        );

        if (!isVisible(projectLinkInDashboardList)) {
            logger.info("Proje mevcut dashboard sayfasında görünmüyor: {}", projectName);
            return null;
        }

        String listedName = getText(projectLinkInDashboardList).trim();
        logger.info("Mevcut dashboard sayfasında okunan proje adı: {}", listedName);
        return listedName;
    }

    /**
     * Projeler sayfasını açar.
     */
    public ProjectsPage goToProjectsPage() {
        logger.info("Projeler sayfası açılıyor");
        click(myProjectsLink);
        return new ProjectsPage(driver);
    }
}