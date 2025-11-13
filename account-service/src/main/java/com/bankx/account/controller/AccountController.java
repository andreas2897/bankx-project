package com.bankx.account.controller;

import com.bankx.account.model.Account;
import com.bankx.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> get(@PathVariable String accountNumber) {
        return service.findByAccountNumber(accountNumber)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<Account> update(@RequestBody Account acc) {
        var saved = service.saveOrUpdate(acc);
        return ResponseEntity.ok(saved);
    }
}
