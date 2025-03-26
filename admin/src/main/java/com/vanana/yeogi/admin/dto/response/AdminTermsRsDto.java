package com.vanana.yeogi.admin.dto.response;

import lombok.Builder;

@Builder
public record AdminTermsRsDto(
    Long termsId,
    String title,
    String content,
    Integer version,
    Boolean isMandatory
) {}
