package com.bankx.transfer.feign;

import com.bankx.transfer.model.RemoteAccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "account-service", url = "${account.service.url}")
public interface AccountClient {
    @GetMapping("/accounts/{accNo}")
    RemoteAccountDto getByAccount(@PathVariable("accNo") String accNo);

    @PutMapping("/accounts/update")
    RemoteAccountDto update(RemoteAccountDto account);
}
