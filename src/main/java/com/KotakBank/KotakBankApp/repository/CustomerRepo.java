package com.KotakBank.KotakBankApp.repository;

import com.KotakBank.KotakBankApp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
    @Query(value = "select * from customer where account_numbe=163544478",nativeQuery = true)
    Customer getCustomer();

}
