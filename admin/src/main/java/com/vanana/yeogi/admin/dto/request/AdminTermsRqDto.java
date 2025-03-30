package com.vanana.yeogi.admin.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AdminTermsRqDto(
    @NotBlank
    String title,

    @NotBlank
    String content,

    @NotBlank
    Boolean isMandatory,

    @NotBlank
    Boolean isUsed
) {}
