package com.kanboard.api.client;

import com.kanboard.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class KanboardApiClient {

    private final String jsonRpcUrl =
            ConfigReader.getProperty("base.url") + "/jsonrpc.php";

    public Response post(Object body, String username, String password) {
        return RestAssured
                .given()
                .auth().preemptive().basic(username, password)
                .header("Content-Type", "application/json")
                .body(body)
                .post(jsonRpcUrl);
    }
}