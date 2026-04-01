package com.kanboard.tests.api.projectmanagement.createproject;

import com.kanboard.api.request.ProjectRequestBuilder;
import com.kanboard.tests.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

// TC-PM-CP-006 | Unauthorized user API ile team project oluşturamamalı
public class TC_PM_CP_006_CreateProjectUnauthorizedUserApiTest extends BaseApiTest {

    @Test(
            description = "TC-PM-CP-006 | PM-CP-006 | Unauthorized user API üzerinden yetkisiz project create isteğinde başarılı sonuç alamamalı ve kalıcı kayıt oluşmamalı"
    )
    public void tcPmCp006_shouldNotCreateProject_withUnauthorizedUser() {

        // Benzersiz proje adı
        String projectName = "QA_PM_CP_UNAUTH_API_" + System.currentTimeMillis();

        // Unauthorized create denemesi
        Response createResponse = apiClient.post(
                ProjectRequestBuilder.createProject(projectName),
                standardUser.getUsername(),
                standardUser.getPassword()
        );

        int statusCode = createResponse.getStatusCode();
        String createResponseBody = createResponse.asString();

        // Create isteği başarıyla tamamlanmamalı
        Assert.assertTrue(
                statusCode == 403 || createResponseBody.contains("\"error\""),
                "Unauthorized user should not be able to create project."
        );

        // Aynı proje adı sistemde oluşmuş mu kontrol et
        Response getResponse = apiClient.post(
                ProjectRequestBuilder.getProjectByName(projectName),
                standardUser.getUsername(),
                standardUser.getPassword()
        );

        String getResponseBody = getResponse.asString();

        // Yetkisiz create denemesi sonrası kalıcı kayıt olmamalı
        Assert.assertTrue(
                getResponseBody.contains("\"result\":false") || getResponseBody.contains("\"result\":null"),
                "Project should not exist in system after unauthorized create attempt."
        );
    }
}