package com.vanana.yeogi.guest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TermsRsDto(
    Long termsId,
    String title,
    String content,
    Integer version,
    Boolean isMandatory
) {}
