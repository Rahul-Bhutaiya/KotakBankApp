package com.KotakBank.KotakBankApp.model;

import com.KotakBank.KotakBankApp.Properties.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "statements")
public class Statements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statement_id")
    private long statementId;

    @ManyToOne
    @JoinColumn(name = "account_number",nullable = false)
    @JsonIgnore
    private Customer customer;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private double amount;

    @Column(name = "date_time")
    private LocalDateTime localDateTime;

    public Statements(){}

    public Statements(Customer customer, TransactionType transactionType, double amount) {
        this.customer = customer;
        this.transactionType = transactionType;
        this.amount = amount;
        this.localDateTime = LocalDateTime.now();
    }

    public long getStatementId() {
        return statementId;
    }

    public void setStatementId(long statementId) {
        this.statementId = statementId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
