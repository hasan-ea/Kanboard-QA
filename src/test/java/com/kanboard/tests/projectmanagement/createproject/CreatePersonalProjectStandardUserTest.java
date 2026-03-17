package com.kanboard.tests.projectmanagement.createproject;

import com.kanboard.models.TestUser;
import com.kanboard.models.TestUsers;
import com.kanboard.pages.DashboardPage;
import com.kanboard.pages.NewPersonalProjectModal;
import com.kanboard.pages.ProjectSummaryPage;
import com.kanboard.tests.base.BaseUiTest;
import com.kanboard.utils.RandomDataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

// TC-PM-CP-002 için standard user personal project create testi
public class CreatePersonalProjectStandardUserTest extends BaseUiTest {

    @Test(
            description = "TC-PM-CP-002 | PM-CP-002 | Authenticated standard user personal project başarıyla oluşturabilmeli"
    )
    public void tcPmCp002_shouldCreatePersonalProject_withStandardUser() {

        TestUser user = TestUsers.standardUser();

        // Benzersiz proje adı
        String projectName = RandomDataUtils.generateProjectName("QA_PM_CP_PERSONAL_USER");

        // Login
        DashboardPage dashboardPage = loginAs(user);
        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard ekranı açılmadı."
        );

        // New personal project modalını aç
        NewPersonalProjectModal newPersonalProjectModal = dashboardPage.goToNewPersonalProjectModal();
        Assert.assertTrue(
                newPersonalProjectModal.isDisplayed(),
                "New personal project modalı açılmadı."
        );

        // Project oluştur
        ProjectSummaryPage projectSummaryPage = newPersonalProjectModal.createProject(projectName);

        // Sonuç doğrulama
        Assert.assertTrue(
                projectSummaryPage.isProjectSummaryPageDisplayed(),
                "Project summary ekranı açılmadı."
        );

        Assert.assertTrue(
                projectSummaryPage.isProjectNameDisplayed(projectName),
                "Oluşturulan personal project adı ekranda görünmüyor."
        );
    }
}