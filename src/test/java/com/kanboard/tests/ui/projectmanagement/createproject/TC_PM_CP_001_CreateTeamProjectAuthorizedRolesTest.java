package com.kanboard.tests.ui.projectmanagement.createproject;

import com.kanboard.models.TestUser;
import com.kanboard.models.TestUsers;
import com.kanboard.pages.DashboardPage;
import com.kanboard.pages.NewProjectModal;
import com.kanboard.pages.ProjectSummaryPage;
import com.kanboard.tests.base.BaseUiTest;
import com.kanboard.utils.RandomDataUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// TC-PM-CP-001 için authorized role matrix testi
public class TC_PM_CP_001_CreateTeamProjectAuthorizedRolesTest extends BaseUiTest {

    // Yetkili roller
    @DataProvider(name = "authorizedTeamProjectRoleData")
    public Object[][] authorizedTeamProjectRoleData() {
        return new Object[][]{
                {TestUsers.admin()},
                {TestUsers.manager()}
        };
    }

    @Test(
            dataProvider = "authorizedTeamProjectRoleData",
            description = "TC-PM-CP-001 | PM-CP-001 | Authorized application role matrix ile team project başarıyla oluşturulabilmeli"
    )
    public void tcPmCp001_shouldCreateTeamProject_withAuthorizedRoles(TestUser user) {

        // Her koşum için benzersiz proje adı
        String projectName = RandomDataUtils.generateProjectName(
                "QA_PM_CP_TEAM_AUTH_" + user.getAlias()
        );

        // Login
        DashboardPage dashboardPage = loginAs(user);
        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard ekranı açılmadı."
        );

        // New project modalını aç
        NewProjectModal newProjectModal = dashboardPage.goToNewProjectModal();
        Assert.assertTrue(
                newProjectModal.isDisplayed(),
                "New project modalı açılmadı."
        );

        // Project oluştur
        ProjectSummaryPage projectSummaryPage = newProjectModal.createProject(projectName);

        // Sonuç doğrulama
        Assert.assertTrue(
                projectSummaryPage.isProjectSummaryPageDisplayed(),
                "Project summary ekranı açılmadı."
        );

        Assert.assertTrue(
                projectSummaryPage.isProjectNameDisplayed(projectName),
                "Oluşturulan proje adı ekranda görünmüyor."
        );
    }
}