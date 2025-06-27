package com.KotakBank.KotakBankApp.DTO.Response;

import com.KotakBank.KotakBankApp.model.Customer;

import java.util.List;

public class TransferMoney {
    private List<Customer> customerList;
    private String message;
    private boolean success;

    public TransferMoney(){}

    public TransferMoney(List<Customer> customerList, String message, boolean success) {
        this.customerList = customerList;
        this.message = message;
        this.success = success;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
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
