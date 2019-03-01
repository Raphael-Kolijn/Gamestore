package domain.controllers.response;

public class Response {
    public Response() {
    }

    public Response(boolean success) {
        this.success = success;
    }

    public Response(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private boolean success;
    private Object data;


}
