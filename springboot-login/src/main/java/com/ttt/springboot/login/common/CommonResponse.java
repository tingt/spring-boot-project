package com.ttt.springboot.login.common;

/**
 * Created by tutingting on 17-12-27.
 */
public class CommonResponse<T> {
    private int error;
    private String message;
    private T data;

    public static <T> CommonResponse<T> successResponse(T data) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setError(0);
        response.setMessage(Constants.SUCCESS_MSG);
        response.setData(data);
        return response;
    }

    public static CommonResponse failedResponse(int error) {
        return failedResponse(error, Constants.ERROR_MSG);
    }

    public static CommonResponse failedResponse(int error, String msg) {
        CommonResponse response = new CommonResponse();
        response.setError(error);
        response.setMessage(msg);
        return response;
    }

    public static <T> CommonResponse<T> response(int error, String msg, T data) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setError(error);
        response.setMessage(msg);
        response.setData(data);
        return response;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
