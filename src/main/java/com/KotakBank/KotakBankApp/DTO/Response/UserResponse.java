package com.KotakBank.KotakBankApp.DTO.Response;

import com.KotakBank.KotakBankApp.model.Customer;

public class UserResponse<T>{

    private T response;
    private String message;
    private boolean success;

    public UserResponse(){}

    public UserResponse(T response, String message, boolean success) {
        this.response = response;
        this.message = message;
        this.success = success;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
