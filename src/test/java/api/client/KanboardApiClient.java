package api.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class KanboardApiClient {

    private String baseUrl = "http://localhost:8080/jsonrpc.php";

    public Response post(Object body, String username, String password) {
        return RestAssured
                .given()
                .auth().preemptive().basic(username, password)
                .header("Content-Type", "application/json")
                .body(body)
                .post(baseUrl);
    }
}