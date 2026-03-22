package com.marketplace.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record UserProfileRequest(
        @NotBlank(message = "O nome é obrigatório.")
        String name,

        @CPF(message = "CPF inválido.")
        String cpf,

        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Telefone em formato inválido.")
        String phone,

        String address
) {}
