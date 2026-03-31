package com.kanboard.api.request;

import com.kanboard.api.model.JsonRpcRequest;

import java.util.HashMap;
import java.util.Map;

public class ProjectRequestBuilder {

    public static JsonRpcRequest<Map<String, Object>> createProject(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);

        return new JsonRpcRequest<>("createProject", 1, params);
    }

    public static JsonRpcRequest<Map<String, Object>> getProjectByName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);

        return new JsonRpcRequest<>("getProjectByName", 2, params);
    }
}