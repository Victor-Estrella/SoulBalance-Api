package br.com.fiap.SoulBalance.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class LoginRequest {

    private String email;
    private String senha;
}
