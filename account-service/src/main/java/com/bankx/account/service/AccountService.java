package com.bankx.account.service;

import com.bankx.account.model.Account;
import com.bankx.account.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public Optional<Account> findByAccountNumber(String accNo) {
        return repo.findByAccountNumberNative(accNo);
    }

    @Transactional
    public Account saveOrUpdate(Account account) {
        return repo.save(account);
    }

    public List<String> getHighBalanceAccountNames(double threshold) {
        return repo.findRichAccounts(threshold)
                   .stream()
                   .map(Account::getName)
                   .collect(Collectors.toList());
    }
}
