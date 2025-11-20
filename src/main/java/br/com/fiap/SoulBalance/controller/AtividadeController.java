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
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;



    @PostMapping("/atividades")
    public ResponseEntity<AtividadeResponseDto> saveAtividade(
            @RequestBody @Valid AtividadeRequestDto atividadeRequestDto,
            @AuthenticationPrincipal br.com.fiap.SoulBalance.entity.UsuarioEntity usuarioLogado) {
        AtividadeResponseDto response = atividadeService.saveAtividade(atividadeRequestDto, usuarioLogado.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/atividades/historico")
    public ResponseEntity<List<AtividadeResponseDto>> buscarHistoricoPorPeriodo(
            @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim,
            @AuthenticationPrincipal br.com.fiap.SoulBalance.entity.UsuarioEntity usuarioLogado) {
        List<AtividadeResponseDto> historico = atividadeService.buscarHistoricoPorPeriodo(inicio, fim, usuarioLogado.getId());
        return ResponseEntity.ok(historico);
    }

    @GetMapping("/atividades")
    public ResponseEntity<List<AtividadeResponseDto>> getAll() {
        List<AtividadeResponseDto> atividadeList = atividadeService.getAll();

        return ResponseEntity.ok(atividadeList);
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