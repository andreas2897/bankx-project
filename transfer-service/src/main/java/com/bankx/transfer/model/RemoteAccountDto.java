package com.bankx.transfer.model;

import java.math.BigDecimal;

public class RemoteAccountDto {
    private Long id;
    private String accountNumber;
    private String name;
    private BigDecimal balance;

    public RemoteAccountDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}
