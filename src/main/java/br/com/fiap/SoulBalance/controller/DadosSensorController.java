package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.DadosSensorRequestDto;
import br.com.fiap.SoulBalance.dto.DadosSensorResponseDto;
import br.com.fiap.SoulBalance.service.DadosSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dados-sensor")
public class DadosSensorController implements DadosSensorApi {

    @Autowired
    private DadosSensorService dadosSensorService;

    @Override
    public ResponseEntity<DadosSensorResponseDto> saveDado(DadosSensorRequestDto filter) {
        try {
            DadosSensorResponseDto response = dadosSensorService.saveDado(filter);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            throw new org.springframework.web.server.ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<DadosSensorResponseDto> updateDado(DadosSensorRequestDto filter, Long idDadoSensor) {
        try {
            DadosSensorResponseDto response = dadosSensorService.updateDado(filter, idDadoSensor);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<DadosSensorResponseDto>> getAllByUsuario() {
        List<DadosSensorResponseDto> dados = dadosSensorService.getAll();
        return ResponseEntity.ok(dados);
    }

    @Override
    public ResponseEntity<Void> delete(Long idDadoSensor) {
        try {
            dadosSensorService.delete(idDadoSensor);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado");
        }
    }

    @GetMapping("/paginacao")
    public ResponseEntity<Page<DadosSensorResponseDto>> findAllPage(
            @RequestParam(value = "pagina", defaultValue = "0") Integer page,
            @RequestParam(value = "tamanho", defaultValue = "2") Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<DadosSensorResponseDto> pageAbrigo = dadosSensorService.findAllPage(pageRequest);

        return ResponseEntity.ok(pageAbrigo);
    }

}