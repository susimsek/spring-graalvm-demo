package io.github.susimsek.springgraalvmdemo.controller;


import io.github.susimsek.springgraalvmdemo.dto.LoginRequestDTO;
import io.github.susimsek.springgraalvmdemo.dto.TokenDTO;
import io.github.susimsek.springgraalvmdemo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AuthenticationService authenticationService;


    @PostMapping("/token")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginRequestDTO loginRequest) {

        TokenDTO tokenResponse = authenticationService.authenticate(loginRequest);

        return ResponseEntity.ok(tokenResponse);
    }
}
