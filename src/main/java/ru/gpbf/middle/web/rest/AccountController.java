package ru.gpbf.middle.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gpbf.middle.application.service.AccountRegisterService;
import ru.gpbf.middle.dto.CreateAccountRequest;
import ru.gpbf.middle.dto.ErrorResponseTo;

@RestController
@RequestMapping("/v1/api/accounts")
public class AccountController {
    private final AccountRegisterService accountRegisterService;

    public AccountController(AccountRegisterService accountRegisterService) {
        this.accountRegisterService = accountRegisterService;
    }

    @Operation(
            summary = "Создает новый счет для клиента",
            description = "на данный момент доступны следующие accountName в теле запроса: " +
                    "1. promotion - создание акционного счета. " +
                    "При успешно регистрации код 200 с пустым телом, при НЕ успешной код 400 с ошибкой в теле."
    )

    @ApiResponse(responseCode = "400", description = "ошибка создания счета", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponseTo.class))})
    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Valid CreateAccountRequest accountRequest) {
        accountRegisterService.register(accountRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
