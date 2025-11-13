package com.bankx.transfer.service;

import com.bankx.transfer.feign.AccountClient;
import com.bankx.transfer.model.Transfer;
import com.bankx.transfer.model.RemoteAccountDto;
import com.bankx.transfer.repository.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

    private final TransferRepository repo;
    private final AccountClient accountClient;

    public TransferService(TransferRepository repo, AccountClient accountClient) {
        this.repo = repo;
        this.accountClient = accountClient;
    }

    @Transactional
    public String transfer(String from, String to, BigDecimal amount) {
        var fromAcc = accountClient.getByAccount(from);
        var toAcc = accountClient.getByAccount(to);

        if (fromAcc == null || toAcc == null) return "Account not found";
        if (fromAcc.getBalance().compareTo(amount) < 0) return "Insufficient balance";

        fromAcc.setBalance(fromAcc.getBalance().subtract(amount));
        toAcc.setBalance(toAcc.getBalance().add(amount));

        accountClient.update(fromAcc);
        accountClient.update(toAcc);

        var t = new Transfer();
        t.setFromAccount(from);
        t.setToAccount(to);
        t.setAmount(amount);
        repo.save(t);

        return "OK";
    }

    public List<String> recentSummary(String acc) {
        return repo.findRecentTransactions(acc)
                   .stream()
                   .map(t -> String.format("%s -> %s : %s", t.getFromAccount(), t.getToAccount(), t.getAmount()))
                   .collect(Collectors.toList());
    }
}
