package com.huanleichen.my.shop.commons.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResult implements Serializable {
    public static final int SUCCESS_STATUS = 200;
    public static final int FAIL_STATUS = 500;

    private int status;
    private String message;
    private Object data;

    public static BaseResult successResult() {
        return createResult(SUCCESS_STATUS, "成功", null);
    }

    public static BaseResult successResult(String message) {
        return createResult(SUCCESS_STATUS, message, null);
    }

    public static BaseResult successResult(String message, Object data) {
        return createResult(SUCCESS_STATUS, message, data);
    }

    public static BaseResult failResult() {
        return  createResult(FAIL_STATUS, "失败", null);
    }

    public static BaseResult failResult(String message) {
        return  createResult(FAIL_STATUS, message, null);
    }

    public static BaseResult failResult(int status, String message) {
        return  createResult(status, message, null);
    }

    private static BaseResult createResult(int status, String message, Object data) {
        BaseResult result = new BaseResult();

        result.setStatus(status);
        result.setMessage(message);
        result.setData(data);

        return  result;
    }
}
