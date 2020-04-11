package com.spring.pontointeligente.api.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class JwtAuthenticationDto {

    @NotEmpty(message = "Email não pode ser vazio.")
    @Email(message = "Email inválido.")
    private String email;
    @NotEmpty(message = "Senha não pode ser vazia.")
    private String senha;

    public JwtAuthenticationDto(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "JwtAuthenticationDto{" +
                "email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
