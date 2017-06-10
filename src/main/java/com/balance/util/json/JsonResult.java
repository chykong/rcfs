package com.balance.util.json;

/**
 * Author  孔垂云
 * Date  2017/6/10.
 */
public class JsonResult {
    private boolean success;//是否成功
    private String message;//消息
    private Object data;//额外的数据

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public JsonResult(boolean success, String message, Object data) {
        this(success, message);
        this.data = data;
    }

    public JsonResult() {
    }

    public JsonResult(boolean success) {
        this.success = success;
    }

    public JsonResult(boolean success, String message) {
        this(success);
        this.message = message;
    }

    /**
     * 成功的Json结果
     */
    private static final JsonResult success_instance = new JsonResult(true);

    /**
     * 获取成功的Json结果
     *
     * @return 成功的Json结果
     */
    public static JsonResult getSuccess_instance() {
        return success_instance;
    }
}
