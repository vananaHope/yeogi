package com.vanana.yeogi.guest.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginRqDto(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
