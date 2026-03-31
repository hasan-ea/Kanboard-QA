package com.kanboard.tests.ui.projectmanagement.createproject;

import com.kanboard.models.TestUser;
import com.kanboard.models.TestUsers;
import com.kanboard.pages.DashboardPage;
import com.kanboard.pages.NewProjectModal;
import com.kanboard.tests.base.BaseUiTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TC-PM-CP-007
 * Project name boş bırakıldığında create engellenmeli
 */
public class TC_PM_CP_007_CreateProjectEmptyNameValidationTest extends BaseUiTest {

    @Test(description = "TC-PM-CP-007 | Project name boş bırakıldığında create engellenmeli")
    public void tcPmCp007_shouldBlockCreate_whenProjectNameIsEmpty() {

        // Test için create ekranına erişebilen bir kullanıcı seçiyoruz
        TestUser user = TestUsers.admin();

        // 1. Login
        DashboardPage dashboardPage = loginAs(user);
        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard ekranı açılmadı."
        );

        // 2. New project modalını aç
        NewProjectModal newProjectModal = dashboardPage.goToNewProjectModal();
        Assert.assertTrue(
                newProjectModal.isDisplayed(),
                "New project modalı açılmadı."
        );

        // 3. Submit öncesi URL'yi sakla
        String urlBeforeSubmit = driver.getCurrentUrl();

        // 4. Project name alanını boş bırakıp save'e bas
        newProjectModal.clickSave();

        // 5. Validation message'i al
        String validationMessage = newProjectModal.getProjectNameValidationMessage();

        // 6. Validation gerçekten üretildi mi?
        Assert.assertNotNull(
                validationMessage,
                "Validation message null olmamalıdır."
        );

        Assert.assertFalse(
                validationMessage.trim().isEmpty(),
                "Validation message boş olmamalıdır."
        );

        // 7. Başarılı create redirect'i olmamalı
        Assert.assertEquals(
                driver.getCurrentUrl(),
                urlBeforeSubmit,
                "Boş project name ile başarılı redirect olmamalıdır."
        );

        // 8. Modal hala açık kalmalı
        Assert.assertTrue(
                newProjectModal.isDisplayed(),
                "Boş project name ile submit sonrası create modalı açık kalmalıdır."
        );
    }
}
