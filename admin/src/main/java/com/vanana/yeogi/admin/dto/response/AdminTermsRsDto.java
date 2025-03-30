package com.vanana.yeogi.admin.dto.response;

import lombok.Builder;

@Builder
public record AdminTermsRsDto(
    long termsId,
    String title,
    String content,
    int version,
    boolean isMandatory
) {}
