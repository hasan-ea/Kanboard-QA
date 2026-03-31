package com.kanboard.api.model;

public class JsonRpcRequest<T> {

    private final String jsonrpc = "2.0";
    private final String method;
    private final int id;
    private final T params;

    public JsonRpcRequest(String method, int id, T params) {
        this.method = method;
        this.id = id;
        this.params = params;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public int getId() {
        return id;
    }

    public T getParams() {
        return params;
    }
}