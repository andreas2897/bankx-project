package com.bankx.account.repository;

import com.bankx.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT * FROM accounts WHERE account_number = :accNo", nativeQuery = true)
    Optional<Account> findByAccountNumberNative(@Param("accNo") String accNo);

    @Query(value = "SELECT * FROM accounts WHERE balance > :minBalance", nativeQuery = true)
    List<Account> findRichAccounts(@Param("minBalance") double minBalance);
}
