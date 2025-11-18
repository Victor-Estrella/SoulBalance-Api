package br.com.fiap.SoulBalance.service;

import br.com.fiap.SoulBalance.dto.LoginRequest;
import br.com.fiap.SoulBalance.dto.TokenResponse;
import br.com.fiap.SoulBalance.entity.UsuarioEntity;
import br.com.fiap.SoulBalance.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public TokenResponse login(LoginRequest filter) {

        UsuarioEntity usuario = usuarioRepository.findByEmail(filter.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(filter.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }

        String token = jwtService.generateToken(usuario);

        return new TokenResponse(token);
    }
}
