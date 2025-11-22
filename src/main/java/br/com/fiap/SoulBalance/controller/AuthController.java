package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.LoginRequest;
import br.com.fiap.SoulBalance.dto.TokenResponse;
import br.com.fiap.SoulBalance.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController implements AuthApi {

    @Autowired
    private AuthService authService;

    @PostMapping()
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest filter) {
        TokenResponse token = authService.login(filter);

        return ResponseEntity.ok(token);
    }
}
