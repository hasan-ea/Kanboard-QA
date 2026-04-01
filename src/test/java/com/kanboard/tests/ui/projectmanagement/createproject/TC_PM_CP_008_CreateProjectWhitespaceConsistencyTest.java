package com.kanboard.tests.ui.projectmanagement.createproject;

import com.kanboard.models.TestUser;
import com.kanboard.models.TestUsers;
import com.kanboard.pages.DashboardPage;
import com.kanboard.pages.NewProjectModal;
import com.kanboard.pages.ProjectEditPage;
import com.kanboard.pages.ProjectSummaryPage;
import com.kanboard.pages.sections.MyProjectsSection;
import com.kanboard.tests.base.BaseUiTest;
import com.kanboard.utils.RandomDataUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TC-PM-CP-008
 * Whitespace içeren project name girdilerinde create sonrası davranış kontrollü ve tutarlı olmalı
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
            description = "TC-PM-CP-008 | Whitespace içeren project name girdilerinde create sonrası davranış kontrollü ve tutarlı olmalı"
    )
    public void tcPmCp008_shouldHandleWhitespaceInputInControlledAndConsistentWay(String projectNameTemplate,
                                                                                  String caseLabel) {

        TestUser user = TestUsers.admin();
        String rawProjectName = buildProjectName(projectNameTemplate);

        DashboardPage dashboardPage = loginAs(user);
        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard ekranı açılmadı. Case: " + caseLabel
        );

        NewProjectModal newProjectModal = dashboardPage.goToNewProjectModal();
        Assert.assertTrue(
                newProjectModal.isDisplayed(),
                "New Project modalı açılmadı. Case: " + caseLabel
        );

        ProjectSummaryPage projectSummaryPage = newProjectModal.createProject(rawProjectName);
        Assert.assertTrue(
                projectSummaryPage.isProjectSummaryPageDisplayed(),
                "Project Summary ekranı açılmadı. Case: " + caseLabel
        );

        String summaryName = projectSummaryPage.getProjectTitleText();
        Assert.assertNotNull(
                summaryName,
                "Summary ekranındaki proje adı null olmamalıdır. Case: " + caseLabel
        );

        int projectId = extractProjectIdFromUrl(driver.getCurrentUrl());

        ProjectEditPage projectEditPage = projectSummaryPage.goToEditProjectPage();
        Assert.assertTrue(
                projectEditPage.isProjectEditPageDisplayed(),
                "Edit project ekranı açılmadı. Case: " + caseLabel
        );

        String editInputValue = projectEditPage.getProjectNameInputValue();
        Assert.assertNotNull(
                editInputValue,
                "Edit project Name input değeri null olmamalıdır. Case: " + caseLabel
        );

        DashboardPage returnedDashboardPage = projectEditPage.goToDashboardPage();
        Assert.assertTrue(
                returnedDashboardPage.isDashboardDisplayed(),
                "Dashboard ekranına dönülemedi. Case: " + caseLabel
        );

        MyProjectsSection myProjectsSection = returnedDashboardPage.goToMyProjectsSection();
        Assert.assertTrue(
                myProjectsSection.isSectionDisplayed(),
                "My Projects ekranı / listesi görüntülenemedi. Case: " + caseLabel
        );

        String listedProjectName = myProjectsSection.getListedProjectNameByProjectId(projectId);
        Assert.assertNotNull(
                listedProjectName,
                "My Projects listesindeki proje adı null olmamalıdır. Case: " + caseLabel
        );

        boolean whitespaceOnlyInput = rawProjectName.trim().isEmpty();

        if (whitespaceOnlyInput) {
            Assert.assertFalse(
                    summaryName.trim().isEmpty(),
                    "Whitespace-only input kabul edildiyse Summary ekranında boş/anlamsız görünen proje adı olmamalıdır. Case: " + caseLabel
            );

            Assert.assertFalse(
                    listedProjectName.trim().isEmpty(),
                    "Whitespace-only input kabul edildiyse My Projects listesinde boş/anlamsız görünen proje adı olmamalıdır. Case: " + caseLabel
            );
        } else {
            Assert.assertFalse(
                    summaryName.trim().isEmpty(),
                    "Create kabul edilmişse Summary ekranındaki proje adı boş olmamalıdır. Case: " + caseLabel
            );

            Assert.assertFalse(
                    listedProjectName.trim().isEmpty(),
                    "Create kabul edilmişse My Projects listesindeki proje adı boş olmamalıdır. Case: " + caseLabel
            );

            Assert.assertEquals(
                    listedProjectName,
                    summaryName,
                    "My Projects listesi ve Summary ekranındaki proje adı aynı olmalıdır. Case: " + caseLabel
            );

            Assert.assertFalse(
                    editInputValue.trim().isEmpty(),
                    "Create kabul edilmişse Edit input değeri boş olmamalıdır. Case: " + caseLabel
            );
        }
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
}