package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.LoginRequest;
import br.com.fiap.SoulBalance.dto.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
@Tag(name = "Autenticação", description = "Operações de login e autenticação")
public interface AuthApi {

    @Operation(summary = "Login do usuário", description = "Autentica o usuário e retorna um token JWT.")
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso")
    @PostMapping()
    ResponseEntity<TokenResponse> login(@RequestBody LoginRequest filter);
}
