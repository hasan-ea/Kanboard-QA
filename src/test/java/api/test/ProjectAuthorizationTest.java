package api.test;

import api.client.KanboardApiClient;
import api.request.ProjectRequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectAuthorizationTest {

    KanboardApiClient client = new KanboardApiClient();

    String USER = "U_USER";
    String PASSWORD = "password";

    @Test
    public void test_UnauthorizedUser_ShouldNotCreateProject() {

        String projectName = "QA_PM_CP_UNAUTH_API_" + System.currentTimeMillis();

        // 1. createProject
        Response createResponse = client.post(
                ProjectRequestBuilder.createProject(projectName),
                USER,
                PASSWORD
        );

        int statusCode = createResponse.getStatusCode();

        // Assertion 1: Authorization fail
        Assert.assertTrue(statusCode == 403 || createResponse.asString().contains("error"));

        // 2. getProjectByName
        Response getResponse = client.post(
                ProjectRequestBuilder.getProjectByName(projectName),
                USER,
                PASSWORD
        );

        String responseBody = getResponse.asString();

        // Assertion 2: Data should NOT exist
        Assert.assertTrue(
                responseBody.contains("null") || responseBody.contains("false"),
                "Project should not be created but found in system!"
        );
    }
}