package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.AtividadeRequestDto;
import br.com.fiap.SoulBalance.dto.AtividadeResponseDto;
import br.com.fiap.SoulBalance.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
public class AtividadeController implements AtividadeApi {

    @Autowired
    private AtividadeService atividadeService;


    @PostMapping()
    public ResponseEntity<AtividadeResponseDto> saveAtividade(@RequestBody @Valid AtividadeRequestDto atividadeRequestDto) {
        AtividadeResponseDto response = atividadeService.saveAtividade(atividadeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<AtividadeResponseDto> updateAtividade(AtividadeRequestDto atividadeRequestDto, Long atividadeId) {
        try {
            AtividadeResponseDto response = atividadeService.updateAtividade(atividadeRequestDto, atividadeId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/users/{userId}/{atividadeId}/historico")
    public ResponseEntity<AtividadeResponseDto> buscarHistoricoPorPeriodo(
            @PathVariable Long userId, @PathVariable Long atividadeId) {
        AtividadeResponseDto historico = atividadeService.buscarHistoricoPorPeriodo(userId, atividadeId);
        if (historico == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Atividade não encontrada");
        }
        return ResponseEntity.ok(historico);
    }

    @Override
    public ResponseEntity<List<AtividadeResponseDto>> getAll() {
        List<AtividadeResponseDto> atividades = atividadeService.getAll();
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/paginacao")
    public ResponseEntity<Page<AtividadeResponseDto>> findAllPage(
            @RequestParam(value = "pagina", defaultValue = "0") Integer page,
            @RequestParam(value = "tamanho", defaultValue = "2") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<AtividadeResponseDto> pageAbrigo = atividadeService.findAllPage(pageRequest);
        return ResponseEntity.ok(pageAbrigo);
    }

}