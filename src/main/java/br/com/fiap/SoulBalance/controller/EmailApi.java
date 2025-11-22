package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.EmailRequestDto;
import br.com.fiap.SoulBalance.entity.EmailEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/email")
@Tag(name = "E-mails", description = "Operações de envio e consulta de e-mails")
public interface EmailApi {

    @Operation(summary = "Enviar e-mail", description = "Envia um novo e-mail e armazena no banco de dados.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "E-mail enviado e salvo com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para envio de e-mail")
    })
    @PostMapping("/enviar-email")
    ResponseEntity<EmailEntity> sendEmail(@RequestBody EmailRequestDto filter);

    @Operation(summary = "Listar e-mails", description = "Retorna todos os e-mails cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de e-mails retornada com sucesso")
    @GetMapping
    ResponseEntity<List<EmailEntity>> getAll();
}
