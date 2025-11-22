package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.DadosSensorRequestDto;
import br.com.fiap.SoulBalance.dto.DadosSensorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dados-sensor")
@Tag(name = "Dados do Sensor", description = "Operações relacionadas aos dados dos sensores")
public interface DadosSensorApi {

    @Operation(summary = "Cadastrar dado do sensor", description = "Registra um novo dado de sensor.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Dado cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para cadastro")
    })
    @PostMapping
    ResponseEntity<DadosSensorResponseDto> saveDado(@RequestBody DadosSensorRequestDto filter);

    @Operation(summary = "Atualizar dado do sensor", description = "Atualiza um dado de sensor existente pelo ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Dado atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização"),
        @ApiResponse(responseCode = "404", description = "Dado não encontrado")
    })
    @PutMapping("/{idDadoSensor}")
    ResponseEntity<DadosSensorResponseDto> updateDado(@RequestBody DadosSensorRequestDto filter, @PathVariable Long idDadoSensor);
    @Operation(summary = "Listar dados do sensor", description = "Retorna todos os dados de sensores cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de dados retornada com sucesso")
    @GetMapping()
    ResponseEntity<List<DadosSensorResponseDto>> getAllByUsuario();
    @Operation(summary = "Excluir dado do sensor", description = "Remove um dado de sensor pelo ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Dado excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Dado não encontrado")
    })
    @DeleteMapping("/{idDadoSensor}")
    ResponseEntity<Void> delete(@PathVariable Long idDadoSensor);
}
