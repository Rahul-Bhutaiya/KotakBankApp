package com.KotakBank.KotakBankApp.DTO.Request;

public class Transfer {
    private long from;
    private long to;
    private double amount;

    public Transfer(long from, long to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
