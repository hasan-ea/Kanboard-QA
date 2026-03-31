package com.kanboard.tests.ui.projectmanagement.createproject;

import com.kanboard.models.TestUser;
import com.kanboard.models.TestUsers;
import com.kanboard.pages.DashboardPage;
import com.kanboard.pages.NewProjectModal;
import com.kanboard.pages.ProjectEditPage;
import com.kanboard.pages.ProjectSummaryPage;
import com.kanboard.tests.base.BaseUiTest;
import com.kanboard.utils.RandomDataUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TC-PM-CP-008
 * Whitespace içeren project name girdilerinde create sonrası display ve edit davranışı tutarlı olmalı
 */
public class TC_PM_CP_008_CreateProjectWhitespaceConsistencyTest extends BaseUiTest {

    @DataProvider(name = "whitespaceProjectNameData")
    public Object[][] whitespaceProjectNameData() {
        return new Object[][]{
                {" ", "single-space-only"},
                {"     ", "multi-space-only"},
                {" %s", "leading-space"},
                {"%s ", "trailing-space"},
                {"%s   %s", "multiple-inner-spaces"},
                {"  %s   %s  ", "leading-trailing-and-inner-spaces"}
        };
    }

    @Test(
            dataProvider = "whitespaceProjectNameData",
            description = "TC-PM-CP-008 | Whitespace içeren project name girdilerinde create sonrası display ve edit davranışı tutarlı olmalı"
    )
    public void tcPmCp008_shouldShowConsistentProjectNameAcrossSummaryEditAndDashboard(String projectNameTemplate,
                                                                                       String caseLabel) {

        TestUser user = TestUsers.admin();
        String rawProjectName = buildProjectName(projectNameTemplate);

        // 1. Login
        DashboardPage dashboardPage = loginAs(user);
        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard ekranı açılmadı. Case: " + caseLabel
        );

        // 2. Create modalını aç
        NewProjectModal newProjectModal = dashboardPage.goToNewProjectModal();
        Assert.assertTrue(
                newProjectModal.isDisplayed(),
                "New Project modalı açılmadı. Case: " + caseLabel
        );

        // 3. Projeyi oluştur
        ProjectSummaryPage projectSummaryPage = newProjectModal.createProject(rawProjectName);
        Assert.assertTrue(
                projectSummaryPage.isProjectSummaryPageDisplayed(),
                "Project Summary ekranı açılmadı. Case: " + caseLabel
        );

        // 4. Summary ekranındaki görünen proje adını al
        String summaryName = projectSummaryPage.getProjectTitleText();
        Assert.assertNotNull(
                summaryName,
                "Summary ekranındaki proje adı null olmamalıdır. Case: " + caseLabel
        );

        String currentUrl = driver.getCurrentUrl();
        int projectId = extractProjectIdFromUrl(currentUrl);

        // 5. Edit project ekranına git
        ProjectEditPage projectEditPage = projectSummaryPage.goToEditProjectPage();
        Assert.assertTrue(
                projectEditPage.isProjectEditPageDisplayed(),
                "Edit project ekranı açılmadı. Case: " + caseLabel
        );

        // 6. Edit input değerini al
        String editInputValue = projectEditPage.getProjectNameInputValue();
        Assert.assertNotNull(
                editInputValue,
                "Edit project Name input değeri null olmamalıdır. Case: " + caseLabel
        );

        // 7. Dashboard'a dön
        DashboardPage returnedDashboardPage = projectEditPage.goToDashboardPage();
        Assert.assertTrue(
                returnedDashboardPage.isDashboardDisplayed(),
                "Dashboard ekranına dönülemedi. Case: " + caseLabel
        );

        // 8. Dashboard listesindeki proje adını proje id ile al
        String dashboardListedName = getDashboardProjectNameByProjectId(projectId);
        Assert.assertNotNull(
                dashboardListedName,
                "Dashboard listesindeki proje adı bulunamadı. Case: " + caseLabel
        );

        // 9. Karşılaştırmalar
        Assert.assertEquals(
                dashboardListedName,
                summaryName,
                "Dashboard listesi ve Summary ekranındaki proje adı aynı olmalıdır. Case: " + caseLabel
        );

        Assert.assertEquals(
                normalizeWhitespace(editInputValue),
                normalizeWhitespace(summaryName),
                "Edit input değeri ile Summary adı normalize edilmiş karşılaştırmada tutarlı olmalıdır. Case: " + caseLabel
        );

        // 10. Whitespace-only kabul edilmişse boş/anlamsız display üretme riski kontrolü
        if (rawProjectName.trim().isEmpty()) {
            Assert.assertFalse(
                    summaryName.trim().isEmpty(),
                    "Whitespace-only input kabul edildiyse Summary ekranında boş/anlamsız görünen proje adı olmamalıdır. Case: " + caseLabel
            );

            Assert.assertFalse(
                    dashboardListedName.trim().isEmpty(),
                    "Whitespace-only input kabul edildiyse Dashboard listesinde boş/anlamsız görünen proje adı olmamalıdır. Case: " + caseLabel
            );
        }

        // TODO: Cleanup helper hazırsa proje silme adımı eklenmeli.
    }

    private String buildProjectName(String template) {
        String token = RandomDataUtils.generateProjectName("QA_PM_CP_008");
        return String.format(template, token, token);
    }

    private int extractProjectIdFromUrl(String url) {
        Pattern pattern = Pattern.compile("/project/(\\d+)");
        Matcher matcher = pattern.matcher(url);

        if (!matcher.find()) {
            throw new IllegalStateException("Project id URL içinden çıkarılamadı. URL: " + url);
        }

        return Integer.parseInt(matcher.group(1));
    }

    private String getDashboardProjectNameByProjectId(int projectId) {
        By projectLink = By.xpath("//div[contains(@class,'table-list')]//a[@href='/project/" + projectId + "']");
        return driver.findElement(projectLink).getText().trim();
    }

    private String normalizeWhitespace(String value) {
        if (value == null) {
            return null;
        }
        return value.trim().replaceAll("\\s+", " ");
    }
}