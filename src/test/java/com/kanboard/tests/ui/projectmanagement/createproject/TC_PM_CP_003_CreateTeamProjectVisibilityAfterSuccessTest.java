package com.kanboard.tests.ui.projectmanagement.createproject;

import com.kanboard.models.TestUser;
import com.kanboard.models.TestUsers;
import com.kanboard.pages.DashboardPage;
import com.kanboard.pages.sections.MyProjectsSection;
import com.kanboard.pages.NewProjectModal;
import com.kanboard.pages.ProjectSummaryPage;
import com.kanboard.tests.base.BaseUiTest;
import com.kanboard.utils.RandomDataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

// TC-PM-CP-003 için create sonrası redirect ve visibility testi
public class TC_PM_CP_003_CreateTeamProjectVisibilityAfterSuccessTest extends BaseUiTest {

    @Test(
            description = "TC-PM-CP-003 | PM-CP-003 | Başarılı create sonrası kullanıcı doğru sonuca yönlendirilmeli ve kayıt görünür olmalı"
    )
    public void tcPmCp003_shouldRedirectToSummaryAndVerifyProjectVisibilityOnDashboardAndProjectsPage() {

        TestUser user = TestUsers.admin();
        String projectName = RandomDataUtils.generateProjectName("QA_PM_CP_VISIBILITY");

        // Login
        DashboardPage dashboardPage = loginAs(user);
        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard ekranı açılmadı."
        );

        // Create modalını aç
        NewProjectModal newProjectModal = dashboardPage.goToNewProjectModal();
        Assert.assertTrue(
                newProjectModal.isDisplayed(),
                "New project modalı açılmadı."
        );

        // Project oluştur
        ProjectSummaryPage projectSummaryPage = newProjectModal.createProject(projectName);

        // Summary doğrulama
        Assert.assertTrue(
                projectSummaryPage.isProjectSummaryPageDisplayed(),
                "Project Summary ekranı açılmadı."
        );

        Assert.assertTrue(
                projectSummaryPage.isProjectNameDisplayed(projectName),
                "Oluşturulan proje adı summary ekranında görünmüyor."
        );

        // Önce dashboard'a dön
        DashboardPage returnedDashboardPage = projectSummaryPage.goToDashboardPage();
        Assert.assertTrue(
                returnedDashboardPage.isDashboardDisplayed(),
                "Summary ekranından Dashboard ekranına geçilemedi."
        );

        // Dashboard liste sayfalarında görünürlük doğrulama
        Assert.assertTrue(
                returnedDashboardPage.isProjectListedInDashboardPages(projectName),
                "Oluşturulan proje Dashboard liste sayfalarında görünmüyor."
        );

        // Dashboard'dan projects sayfasına git
        MyProjectsSection projectsPage = returnedDashboardPage.goToMyProjectsSection();
        Assert.assertTrue(
                projectsPage.isSectionDisplayed(),
                "Projects sayfası açılmadı."
        );

        // Projects listesinde görünürlük doğrulama
        Assert.assertTrue(
                projectsPage.isProjectListed(projectName),
                "Oluşturulan proje Projects listesinde görünmüyor."
        );
    }
}