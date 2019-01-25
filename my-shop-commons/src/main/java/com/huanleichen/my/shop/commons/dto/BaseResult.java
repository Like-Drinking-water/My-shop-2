package com.huanleichen.my.shop.commons.dto;

import java.io.Serializable;

public class BaseResult implements Serializable {
    public static final int SUCCESS_STATUS = 200;
    public static final int FAIL_STATUS = 500;

    private int status;
    private String message;

    public static BaseResult successResult() {
        return createResult(SUCCESS_STATUS, "成功");
    }

    public static BaseResult successResult(String message) {
        return createResult(SUCCESS_STATUS, message);
    }

    public static BaseResult failResult() {
        return  createResult(FAIL_STATUS, "失败");
    }

    public static BaseResult failResult(String message) {
        return  createResult(FAIL_STATUS, message);
    }

    public static BaseResult failResult(int status, String message) {
        return  createResult(status, message);
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private static BaseResult createResult(int status, String message) {
        BaseResult result = new BaseResult();

        result.setStatus(status);
        result.setMessage(message);

        return  result;
    }
}
