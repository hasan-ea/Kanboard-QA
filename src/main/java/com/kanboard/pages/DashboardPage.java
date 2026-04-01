package com.kanboard.pages;

import com.kanboard.base.BasePage;
import com.kanboard.pages.sections.MyProjectsSection;
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
     * Projeler sayfasını açar.
     */
    public MyProjectsSection goToMyProjectsSection() {
        logger.info("Projeler sayfası açılıyor");
        click(myProjectsLink);
        return new MyProjectsSection(driver);
    }
}