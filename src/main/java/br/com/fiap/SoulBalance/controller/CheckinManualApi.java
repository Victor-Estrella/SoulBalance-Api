package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.CheckinManualRequestDto;
import br.com.fiap.SoulBalance.dto.CheckinManualResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/checkin-manual")
@Tag(name = "Check-in Manual", description = "Operações de check-in manual do usuário")
public interface CheckinManualApi {

    @Operation(summary = "Registrar check-in manual", description = "Registra um novo check-in manual para o usuário.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Check-in registrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para check-in")
    })
    @PostMapping
    ResponseEntity<CheckinManualResponseDto> saveChekin(@RequestBody CheckinManualRequestDto filter);

    @Operation(summary = "Atualizar check-in manual", description = "Atualiza um check-in manual existente pelo ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Check-in atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização"),
        @ApiResponse(responseCode = "404", description = "Check-in não encontrado")
    })
    @PutMapping("/users/{userId}/{chekinId}")
    
    ResponseEntity<CheckinManualResponseDto> updateChekin(@RequestBody CheckinManualRequestDto filter, @PathVariable Long userId, @PathVariable Long chekinId);
    @Operation(summary = "Listar histórico de check-ins", description = "Retorna o histórico de check-ins manuais de um usuário.")
    @ApiResponse(responseCode = "200", description = "Histórico retornado com sucesso")
    @GetMapping("/historico/{idUsuario}")
    ResponseEntity<List<CheckinManualResponseDto>> getAllByUsuario(@PathVariable Long idUsuario);

    @Operation(summary = "Listar todos os check-ins", description = "Retorna todos os check-ins manuais cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de check-ins retornada com sucesso")
    @GetMapping()
    ResponseEntity<List<CheckinManualResponseDto>> getAll();
    
    @Operation(summary = "Excluir check-in manual", description = "Remove um check-in manual de um usuário pelo ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Check-in excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Check-in não encontrado")
    })
    @DeleteMapping("/users/{userId}/{chekinId}/chekins")
    ResponseEntity<Void> deleteChekins(@PathVariable Long userId, @PathVariable Long chekinId);
}
