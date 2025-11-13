package com.bankx.transfer.controller;

import com.bankx.transfer.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> transfer(@RequestParam String from,
                                           @RequestParam String to,
                                           @RequestParam BigDecimal amount) {
        var res = service.transfer(from, to, amount);
        if ("OK".equals(res)) return ResponseEntity.ok("Transfer successful");
        return ResponseEntity.badRequest().body(res);
    }

    @GetMapping("/recent/{acc}")
    public ResponseEntity<List<String>> recent(@PathVariable String acc) {
        return ResponseEntity.ok(service.recentSummary(acc));
    }
}
