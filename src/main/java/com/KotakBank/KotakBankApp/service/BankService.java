package com.KotakBank.KotakBankApp.service;

import com.KotakBank.KotakBankApp.DTO.Request.NewAccount;
import com.KotakBank.KotakBankApp.DTO.Request.Money;
import com.KotakBank.KotakBankApp.DTO.Request.Transfer;
import com.KotakBank.KotakBankApp.DTO.Response.StatementResponse;
import com.KotakBank.KotakBankApp.DTO.Response.TransferMoney;
import com.KotakBank.KotakBankApp.DTO.Response.UserResponse;
import com.KotakBank.KotakBankApp.Properties.TransactionType;
import com.KotakBank.KotakBankApp.model.Customer;
import com.KotakBank.KotakBankApp.model.Statements;
import com.KotakBank.KotakBankApp.repository.CustomerRepo;
import com.KotakBank.KotakBankApp.repository.StatementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BankService {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    StatementRepo statementRepo;

    public UserResponse<Customer> addAccount(NewAccount newAccount){
        try{
            Customer newCustomer = new Customer(generateAccountNumber(), newAccount.getName(), 500);
            customerRepo.save(newCustomer);
            Statements newStatement = new Statements(newCustomer, TransactionType.DEPOSIT, newCustomer.getBalance());
            statementRepo.save(newStatement);

            return new UserResponse<Customer>(newCustomer,"New Customer Added Successfully",true);
        } catch (Exception e) {
            return new UserResponse<Customer>(null,e.getMessage(),false);
        }
    }

    public UserResponse<Customer> withdrawMoney(Money money){
        try{
            Customer customer = customerRepo.findById(money.getAccountNumber()).orElse(null);
            if(customer==null){
                throw new Exception("Customer Not Fount");
            }
            else{
                double balance = customer.getBalance();
                double amount = money.getAmount();
                if(amount<=balance){
                    customer.setBalance(balance-amount);
                    customerRepo.save(customer);
                    Statements newStatement = new Statements(customer,TransactionType.WITHDRAW,amount);
                    statementRepo.save(newStatement);
                    return new UserResponse<>(customer,"Amount Withdraw Successfully",true);
                }
                else{
                    throw new Exception("Insufficient Balance in User Account");
                }
            }
        } catch (Exception e) {
            return new UserResponse<>(null,e.getMessage(),false);
        }
    }

    public UserResponse<Customer> depositMoney(Money money){
        try{
            Customer customer = customerRepo.findById(money.getAccountNumber()).orElse(null);
            if(customer==null){
                throw new Exception("Customer Not Fount");
            }
            else{
                double balance = customer.getBalance();
                customer.setBalance(balance + money.getAmount());
                customerRepo.save(customer);
                Statements newStatement = new Statements(customer,TransactionType.DEPOSIT, money.getAmount());
                statementRepo.save(newStatement);
                return new UserResponse<>(customer,"Amount Deposit Successfully",true);
            }
        } catch (Exception e) {
            return new UserResponse<>(null,e.getMessage(),false);
        }
    }
        @Transactional(isolation = Isolation.READ_COMMITTED)
        public TransferMoney transferMoney(Transfer transfer){
            TransferMoney tm  = new TransferMoney();
//            try{
                Customer fromCustomer = customerRepo.findById(transfer.getFrom()).orElse(null);
                Customer toCustomer = customerRepo.findById(transfer.getTo()).orElse(null);
//                if(fromCustomer==null){
//                    throw new Exception("From Account Number is Invalid");
//                }
//                else if(toCustomer==null){
//                    throw new Exception("To Account Number is Invalid");
//                }

                double fromBalance = fromCustomer.getBalance();
                double toBalance = toCustomer.getBalance();

                if(fromBalance >= transfer.getAmount()){

                    fromCustomer.setBalance(fromBalance - transfer.getAmount());
                    customerRepo.save(fromCustomer);

//                    int a= 10/0;
//                    customerRepo.getCustomer();
                    toCustomer.setBalance(toBalance + transfer.getAmount());
                    customerRepo.save(toCustomer);



                    Statements fromStatement = new Statements(fromCustomer,TransactionType.WITHDRAW, transfer.getAmount());
                    Statements toStatement = new Statements(toCustomer,TransactionType.DEPOSIT, transfer.getAmount());

                    statementRepo.save(fromStatement);
                    statementRepo.save(toStatement);

                    List<Customer> customerList = new ArrayList<>();
                    customerList.add(fromCustomer);
                    customerList.add(toCustomer);

                    tm = new TransferMoney(customerList,"Money Transfered Successfully",true);
                }
//                else{
//                    throw new Exception("Insufficient Balance in From Account");
//                }

//            } catch (Exception e) {
//                tm = new TransferMoney(null,e.getMessage(),false);
//            }
            return tm;
        }

    public UserResponse<Customer> showBalance(long accountNumber){
        try{
            Customer customer = customerRepo.findById(accountNumber).orElse(null);
            if(customer==null){
                throw new Exception("Customer not found");
            }
            else{
                return new UserResponse<>(customer,"User Found Successfully",true);
            }
        } catch (Exception e) {
            return new UserResponse<>(null,e.getMessage(),false);
        }

    }

    public StatementResponse getStatement(long accountNumber) {
        try{
            List<Statements> statementsList = statementRepo.getStatementList(accountNumber);
            if(statementsList.isEmpty()){
                throw new Exception("Invalid Account Number");
            }
            return new StatementResponse(statementsList,"Statements Found Successfully",true);
        } catch (Exception e) {
            return new StatementResponse(null,e.getMessage(),false);
        }
    }

    public UserResponse<Customer> removeUser(long accountNumber){
        try{
            customerRepo.deleteById(accountNumber);
            return new UserResponse<>(null,"Account Deleted Successfully",true);
        } catch (Exception e) {
            return new UserResponse<>(null,e.getMessage(),false);
        }
    }

    private long generateAccountNumber() {
        return 1000000000L + new Random().nextInt(900000000);
    }
}
