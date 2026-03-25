package api.model;

public class JsonRpcRequest<T> {

    private String jsonrpc = "2.0";
    private String method;
    private int id;
    private T params;

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