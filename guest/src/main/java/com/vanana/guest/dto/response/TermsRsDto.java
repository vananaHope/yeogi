package com.vanana.guest.dto.response;

import lombok.Builder;

@Builder
public record TermsRsDto(
    Long termsId,
    String title,
    Integer version,
    Boolean isMandatory
) {}
