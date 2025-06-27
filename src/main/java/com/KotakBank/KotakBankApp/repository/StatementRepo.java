package com.KotakBank.KotakBankApp.repository;

import com.KotakBank.KotakBankApp.model.Statements;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepo extends JpaRepository<Statements,Long> {

    @Query(value = "select * from statements where account_number=?",nativeQuery = true)
    public List<Statements> getStatementList(long accountNumber);
}
