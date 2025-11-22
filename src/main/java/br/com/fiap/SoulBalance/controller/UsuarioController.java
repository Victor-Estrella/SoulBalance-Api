package br.com.fiap.SoulBalance.controller;

import br.com.fiap.SoulBalance.dto.UsuarioRequestDto;
import br.com.fiap.SoulBalance.dto.UsuarioResponseDto;
import br.com.fiap.SoulBalance.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements UsuarioApi {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        List<UsuarioResponseDto> usuarioList = usuarioService.getlAll();
        return ResponseEntity.ok(usuarioList);
    }

    @Override
    public ResponseEntity<UsuarioResponseDto> getById(Long idUsuario) {
        try {
            UsuarioResponseDto usuario = usuarioService.getById(idUsuario);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            throw new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado");
        }
    }

    @Override
    public ResponseEntity<UsuarioResponseDto> save(UsuarioRequestDto filter) throws Exception {
        try {
            UsuarioResponseDto response = usuarioService.save(filter);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<UsuarioResponseDto> update(UsuarioRequestDto filter, Long idUsuario) {
        try {
            UsuarioResponseDto response = usuarioService.update(filter, idUsuario);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Void> delete(Long idUsuario) {
        try {
            usuarioService.delete(idUsuario);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado");
        }
    }

    @GetMapping("/paginacao")
    public ResponseEntity<Page<UsuarioResponseDto>> findAllPage(
            @RequestParam(value = "pagina", defaultValue = "0") Integer page,
            @RequestParam(value = "tamanho", defaultValue = "2") Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<UsuarioResponseDto> pageAbrigo = usuarioService.findAllPage(pageRequest);

        return ResponseEntity.ok(pageAbrigo);
    }


}
