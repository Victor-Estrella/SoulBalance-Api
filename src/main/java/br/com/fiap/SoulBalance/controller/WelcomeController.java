package br.com.fiap.SoulBalance.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class WelcomeController implements WelcomeApi {

    @Override
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to SoulBalance API!");
    }
}
