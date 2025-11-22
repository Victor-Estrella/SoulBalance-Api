package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.AtividadeRequestDto;
import br.com.fiap.SoulBalance.dto.AtividadeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/atividade")
@Tag(name = "Atividades", description = "Operações relacionadas às atividades do usuário")
public interface AtividadeApi {

    @Operation(summary = "Cadastrar atividade", description = "Registra uma nova atividade para o usuário.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Atividade cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para cadastro")
    })
    @PostMapping()
    ResponseEntity<AtividadeResponseDto> saveAtividade(@RequestBody AtividadeRequestDto atividadeRequestDto);

    @Operation(summary = "Atualizar atividade", description = "Atualiza uma atividade existente pelo ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Atividade atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização"),
        @ApiResponse(responseCode = "404", description = "Atividade não encontrada")
    })
    @PutMapping("/{atividadeId}")
    ResponseEntity<AtividadeResponseDto> updateAtividade(@RequestBody AtividadeRequestDto atividadeRequestDto, @PathVariable Long atividadeId);
    @Operation(summary = "Buscar histórico de atividade", description = "Retorna o histórico de uma atividade específica de um usuário.")
    @ApiResponse(responseCode = "200", description = "Histórico retornado com sucesso")
    @GetMapping("/users/{userId}/{atividadeId}/historico")
    ResponseEntity<AtividadeResponseDto> buscarHistoricoPorPeriodo(@PathVariable Long userId, @PathVariable Long atividadeId);

    @Operation(summary = "Listar atividades", description = "Retorna todas as atividades cadastradas.")
    @ApiResponse(responseCode = "200", description = "Lista de atividades retornada com sucesso")
    @GetMapping()
    ResponseEntity<List<AtividadeResponseDto>> getAll();

    @Operation(summary = "Paginar atividades", description = "Retorna atividades paginadas.")
    @ApiResponse(responseCode = "200", description = "Página de atividades retornada com sucesso")
    @GetMapping("/paginacao")
    ResponseEntity<Page<AtividadeResponseDto>> findAllPage(@RequestParam(value = "pagina", defaultValue = "0") Integer page,
                                                          @RequestParam(value = "tamanho", defaultValue = "2") Integer size);
}
