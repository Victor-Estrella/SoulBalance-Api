package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.UsuarioRequestDto;
import br.com.fiap.SoulBalance.dto.UsuarioResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
public interface UsuarioApi {

    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping()
    ResponseEntity<List<UsuarioResponseDto>> getAll();

    @Operation(summary = "Buscar usuário por ID", description = "Retorna os dados de um usuário específico pelo ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{idUsuario}")
    ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long idUsuario);

    @Operation(summary = "Cadastrar novo usuário", description = "Cria um novo usuário com os dados fornecidos.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para criação do usuário")
    })
    @PostMapping
    ResponseEntity<UsuarioResponseDto> save(@RequestBody UsuarioRequestDto filter) throws Exception;

    @Operation(summary = "Atualizar usuário", description = "Atualiza as informações de um usuário existente pelo ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para atualização"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/{idUsuario}")
    ResponseEntity<UsuarioResponseDto> update(@RequestBody UsuarioRequestDto filter, @PathVariable Long idUsuario);

    @Operation(summary = "Excluir usuário", description = "Remove um usuário existente pelo ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{idUsuario}")
    ResponseEntity<Void> delete(@PathVariable Long idUsuario);
}
