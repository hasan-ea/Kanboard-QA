package com.kanboard.tests.projectmanagement.createproject;

import com.kanboard.models.TestUser;
import com.kanboard.models.TestUsers;
import com.kanboard.pages.DashboardPage;
import com.kanboard.pages.NewProjectModal;
import com.kanboard.pages.ProjectsPage;
import com.kanboard.tests.base.BaseUiTest;
import com.kanboard.utils.RandomDataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

// TC-PM-CP-005 için yetkisiz user team project create engelleme testi
public class CreateUnauthorizedTeamProjectTest extends BaseUiTest {

    @Test(
            description = "TC-PM-CP-005 | PM-CP-005 | Unauthorized user UI üzerinden team project oluşturamamalı"
    )
    public void tcPmCp005_shouldNotAllowStandardUserToCreateTeamProject() {
        TestUser user = TestUsers.standardUser();
        String projectName = RandomDataUtils.generateProjectName("QA_PM_CP_UNAUTH_UI");
        String projectIdentifier = RandomDataUtils.generateProjectName("qa-pm-cp-unauth-ui")
                .toLowerCase()
                .replace("_", "-");

        DashboardPage dashboardPage = loginAs(user);

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard ekranı açılmadı."
        );

        boolean teamCreateVisible = dashboardPage.isNewProjectVisible();

        if (!teamCreateVisible) {
            Assert.assertTrue(
                    dashboardPage.isNewPersonalProjectVisible(),
                    "Standart kullanıcı için personal project create seçeneği görünür olmalıdır."
            );
            return;
        }

        NewProjectModal newProjectModal = dashboardPage.goToNewProjectModal();

        Assert.assertTrue(
                newProjectModal.isDisplayed(),
                "Team project create yüzeyi görünür olmasına rağmen create modalı açılmadı."
        );

        newProjectModal.createProject(projectName, projectIdentifier);

        ProjectsPage projectsPage = dashboardPage.goToProjectsPage();

        Assert.assertTrue(
                projectsPage.isDisplayed(),
                "Projects sayfası açılmadı."
        );

        Assert.assertFalse(
                projectsPage.isProjectListed(projectName),
                "Yetkisiz kullanıcı için team project kaydı oluşmamalıydı: " + projectName
        );
    }
}