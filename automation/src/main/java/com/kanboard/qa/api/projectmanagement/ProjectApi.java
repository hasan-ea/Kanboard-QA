package com.kanboard.qa.api.projectmanagement;

import com.kanboard.qa.api.client.JsonRpcClient;
import com.kanboard.qa.models.ProjectData;

public class ProjectApi {
    private final JsonRpcClient jsonRpcClient = new JsonRpcClient();

    public String createProject(ProjectData projectData) {
        return jsonRpcClient.post("createProject", projectData);
    }
}
