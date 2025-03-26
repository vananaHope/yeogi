package com.vanana.yeogi.admin.dto.request;

import lombok.Builder;

@Builder
public record AdminTermsRqDto(
    String title,
    String content,
    Boolean isMandatory,
    Boolean isUseYn
) {}
