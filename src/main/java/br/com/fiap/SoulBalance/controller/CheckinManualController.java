package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.CheckinManualRequestDto;
import br.com.fiap.SoulBalance.dto.CheckinManualResponseDto;
import br.com.fiap.SoulBalance.service.CheckinManualService;
import br.com.fiap.SoulBalance.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("checkin-manual")
public class CheckinManualController implements CheckinManualApi {

    @Autowired
    private CheckinManualService checkinManualService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public ResponseEntity<CheckinManualResponseDto> saveChekin(CheckinManualRequestDto filter) {
        try {
            CheckinManualResponseDto response = checkinManualService.saveChekin(filter);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            throw new org.springframework.web.server.ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<CheckinManualResponseDto> updateChekin(CheckinManualRequestDto filter, Long userId, Long chekinId) {
        try {
            // Supondo que exista um método updateChekin na service, se não existir, será necessário implementar
            CheckinManualResponseDto response = checkinManualService.updateChekin(filter, userId, chekinId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<CheckinManualResponseDto>> getAllByUsuario(Long idUsuario) {
        List<CheckinManualResponseDto> historico = checkinManualService.getAllByUsuario(idUsuario);
        return ResponseEntity.ok(historico);
    }

    @Override
    public ResponseEntity<List<CheckinManualResponseDto>> getAll() {
        List<CheckinManualResponseDto> chekinList = checkinManualService.getAll();
        return ResponseEntity.ok(chekinList);
    }

    @Override
    public ResponseEntity<Void> deleteChekins(Long userId, Long chekinId) {
        try {
            checkinManualService.deleteUserChekin(userId, chekinId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado");
        }
    }

    @GetMapping("/paginacao")
    public ResponseEntity<Page<CheckinManualResponseDto>> findAllPage(
            @RequestParam(value = "pagina", defaultValue = "0") Integer page,
            @RequestParam(value = "tamanho", defaultValue = "2") Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<CheckinManualResponseDto> pageAbrigo = checkinManualService.findAllPage(pageRequest);

        return ResponseEntity.ok(pageAbrigo);
    }

}