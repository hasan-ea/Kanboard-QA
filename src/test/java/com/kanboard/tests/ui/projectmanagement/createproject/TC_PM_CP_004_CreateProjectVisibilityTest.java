package com.kanboard.tests.ui.projectmanagement.createproject;

import com.kanboard.models.TestUser;
import com.kanboard.models.TestUsers;
import com.kanboard.pages.DashboardPage;
import com.kanboard.tests.base.BaseUiTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// TC-PM-CP-004 için rol bazlı create option visibility testi
public class TC_PM_CP_004_CreateProjectVisibilityTest extends BaseUiTest {

    @DataProvider(name = "createProjectVisibilityMatrix")
    public Object[][] createProjectVisibilityMatrix() {
        return new Object[][]{
                {TestUsers.admin(), true, true},
                {TestUsers.manager(), true, true},
                {TestUsers.standardUser(), false, true}
        };
    }

    @Test(
            dataProvider = "createProjectVisibilityMatrix",
            description = "TC-PM-CP-004 | PM-CP-004 | Create ekranında rol bazlı proje tipi seçenekleri matrix mantığıyla doğrulanmalı"
    )
    public void tcPmCp004_shouldDisplayCreateProjectOptionsAccordingToApplicationRole(
            TestUser user,
            boolean expectedNewProjectVisible,
            boolean expectedNewPersonalProjectVisible
    ) {
        DashboardPage dashboardPage = loginAs(user);

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard ekranı açılmadı."
        );

        Assert.assertEquals(
                dashboardPage.isNewProjectVisible(),
                expectedNewProjectVisible,
                "'New project' görünürlüğü beklenen rol davranışı ile eşleşmiyor."
        );

        Assert.assertEquals(
                dashboardPage.isNewPersonalProjectVisible(),
                expectedNewPersonalProjectVisible,
                "'New personal project' görünürlüğü beklenen rol davranışı ile eşleşmiyor."
        );
    }
}