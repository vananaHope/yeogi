package com.vanana.yeogi.admin.dto.response;

import lombok.Builder;

@Builder
public record AdminTermsRsDto(
    String title,
    String content,
    String version,
    boolean isMandatory
) {}
