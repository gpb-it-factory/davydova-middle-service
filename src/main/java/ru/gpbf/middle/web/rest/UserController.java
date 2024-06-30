package ru.gpbf.middle.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gpbf.middle.application.service.BalanceService;
import ru.gpbf.middle.dto.BalanceResponse;

@RestController
@RequestMapping("/v1/api/users")
public class UserController {
    private final BalanceService balanceService;

    public UserController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<?> getBalance(@PathVariable Long userId) {
        return new ResponseEntity<>(new BalanceResponse(balanceService.getBalance(userId)), HttpStatus.OK);
    }
}
