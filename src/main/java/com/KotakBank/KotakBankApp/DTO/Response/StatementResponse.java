package com.KotakBank.KotakBankApp.DTO.Response;

import com.KotakBank.KotakBankApp.model.Statements;

import java.util.List;

public class StatementResponse {
    private List<Statements> customerList;
    private String message;
    private boolean success;

    public StatementResponse(List<Statements> customerList, String message, boolean success) {
        this.customerList = customerList;
        this.message = message;
        this.success = success;
    }

    public List<Statements> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Statements> customerList) {
        this.customerList = customerList;
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
