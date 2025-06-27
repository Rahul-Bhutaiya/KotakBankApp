package com.KotakBank.KotakBankApp.controller;

import com.KotakBank.KotakBankApp.DTO.Request.NewAccount;
import com.KotakBank.KotakBankApp.DTO.Request.Money;
import com.KotakBank.KotakBankApp.DTO.Request.Transfer;
import com.KotakBank.KotakBankApp.DTO.Response.StatementResponse;
import com.KotakBank.KotakBankApp.DTO.Response.TransferMoney;
import com.KotakBank.KotakBankApp.DTO.Response.UserResponse;
import com.KotakBank.KotakBankApp.model.Customer;
import com.KotakBank.KotakBankApp.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kotak")
public class BankController {

    @Autowired
    BankService bankService;

    @PostMapping("add-new-account")
    public UserResponse<Customer> addNewAccount(@RequestBody NewAccount newAccount){
        return bankService.addAccount(newAccount);
    }

    @PutMapping("withdraw")
    public UserResponse<Customer> withdrawMoney(@RequestBody Money money){
        return bankService.withdrawMoney(money);
    }

    @PutMapping("deposit")
    public UserResponse<Customer> depositMoney(@RequestBody Money money){
        return bankService.depositMoney(money);
    }

    @PutMapping("transfer")
    public TransferMoney transferMoney(@RequestBody Transfer transfer){
        return bankService.transferMoney(transfer);
    }

    @GetMapping("get-balance")
    public UserResponse<Customer> getUserBalance(@RequestHeader(name = "accountNumber") long accountNumber){
        return bankService.showBalance(accountNumber);
    }

    @GetMapping("statement")
    public StatementResponse getUserStatement(@RequestHeader(name = "accountNumber") long accountNumber){
        return bankService.getStatement(accountNumber);
    }

    @DeleteMapping("delete")
    public UserResponse<Customer> removeCustomer(@RequestHeader(name = "accountNumber") long accountNumber){
        return bankService.removeUser(accountNumber);
    }
}
