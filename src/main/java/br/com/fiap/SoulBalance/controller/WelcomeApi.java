package br.com.fiap.SoulBalance.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/welcome")
@Tag(name = "Welcome", description = "Endpoint de boas-vindas da API")
public interface WelcomeApi {

    @Operation(summary = "Boas-vindas", description = "Endpoint simples de boas-vindas para testar a API.")
    @ApiResponse(responseCode = "200", description = "Mensagem de boas-vindas retornada com sucesso")
    @GetMapping
    ResponseEntity<String> welcome();
}
