package ru.gpbf.middle.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gpbf.middle.application.service.UserRegisterService;
import ru.gpbf.middle.dto.CreateUserRequest;
import ru.gpbf.middle.dto.ErrorResponseTo;
import ru.gpbf.middle.exception.ABSRequestError;

import java.util.Optional;

@RestController
@RequestMapping("/v1/api/auth")
@Tag(name = "Контроллер регистрации", description = "Регистрирование клиента в системе")
public class AuthController {
    private final UserRegisterService userRegisterService;
    private final ModelMapper mapper;

    public AuthController(UserRegisterService userRegisterService, ModelMapper mapper) {
        this.userRegisterService = userRegisterService;
        this.mapper = mapper;
    }


    @Operation(
            summary = "Регистрирует клиента",
            description = "При успешно регистрации код 200 с пустым телом, при НЕ успешной код 400 с ошибкой в теле"
    )

    @ApiResponse(responseCode = "400", description = "ошибка регистрации", content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponseTo.class))})
    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Valid CreateUserRequest user) {
        Optional<ABSRequestError> result = userRegisterService.register(user);
        if (result.isPresent()) {
            return new ResponseEntity<>(mapper.map(result.get(), ErrorResponseTo.class), HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
