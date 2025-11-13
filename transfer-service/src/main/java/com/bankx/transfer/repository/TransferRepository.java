package com.bankx.transfer.repository;

import com.bankx.transfer.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

    @Query(value = "SELECT * FROM transfers WHERE from_account = :acc OR to_account = :acc ORDER BY timestamp DESC LIMIT 10", nativeQuery = true)
    List<Transfer> findRecentTransactions(@Param("acc") String accountNumber);
}
