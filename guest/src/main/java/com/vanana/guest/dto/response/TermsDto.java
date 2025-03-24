package com.vanana.guest.dto.response;

import lombok.Builder;

@Builder
public record TermsDto(
    Long termsId,
    String title,
    Integer version,
    Boolean isMandatory
) {}
