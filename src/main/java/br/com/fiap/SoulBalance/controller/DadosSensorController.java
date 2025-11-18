package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.DadosSensorRequestDto;
import br.com.fiap.SoulBalance.dto.DadosSensorResponseDto;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.enun.TipoDadoSensor;
import br.com.fiap.SoulBalance.service.DadosSensorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("dados-sensor")
public class DadosSensorController {

    @Autowired
    private DadosSensorService dadosSensorService;

    @PostMapping
    public ResponseEntity<DadosSensorResponseDto> saveDado(
            @RequestBody @Valid DadosSensorRequestDto filter,
            @AuthenticationPrincipal UsuarioEntity usuarioLogado) {

        DadosSensorResponseDto dadosSensorResponseDto = dadosSensorService.saveDado(filter, usuarioLogado.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).body(dadosSensorResponseDto);
    }

    @GetMapping()
    public ResponseEntity<List<DadosSensorResponseDto>> getAllByUsuario(
            @AuthenticationPrincipal UsuarioEntity usuarioLogado) {

        List<DadosSensorResponseDto> dados = dadosSensorService.getAll(usuarioLogado.getUserId());

        return ResponseEntity.ok(dados);
    }

    @GetMapping("/agregados")
    public ResponseEntity<Map<TipoDadoSensor, Double>> agregarDadosDiarios(
            @AuthenticationPrincipal UsuarioEntity usuarioLogado,
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {

        Map<TipoDadoSensor, Double> agregados = dadosSensorService.agregarDadosDiarios(
                usuarioLogado.getUserId(),
                data
        );

        return ResponseEntity.ok(agregados);
    }

    @DeleteMapping("/{idDadoSensor}")
    public void delete(Long idDadoSensor) {
        dadosSensorService.delete(idDadoSensor);
    }

}