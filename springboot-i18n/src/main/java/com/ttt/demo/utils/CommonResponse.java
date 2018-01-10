package com.ttt.demo.utils;

/**
 * Created by tutingting on 18-1-10.
 */
public class CommonResponse<T> {
    private int error;
    private T data;
    private String message;

    public static <T>CommonResponse<T> successResponse(T data){
        CommonResponse<T> response = new CommonResponse<>();
        response.setError(0);
        response.setMessage("success");
        response.setData(data);
        return response;
    }

    public static CommonResponse failedResponse(int error) {
        return failedResponse(error, "error");
    }

    public static CommonResponse failedResponse(int error, String msg) {
        CommonResponse response = new CommonResponse();
        response.setError(error);
        response.setMessage(msg);
        return response;
    }

    public static <T> CommonResponse<T> failedResponse(int error, String msg, T data) {
        CommonResponse<T> response = failedResponse(error,msg);
        response.setData(data);
        return response;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
