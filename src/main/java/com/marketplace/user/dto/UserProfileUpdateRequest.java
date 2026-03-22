package com.marketplace.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserProfileUpdateRequest(
        @NotBlank(message = "O nome não pode estar em branco.")
        String name,

        @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Telefone em formato inválido.")
        String phone,

        String address
) {}
