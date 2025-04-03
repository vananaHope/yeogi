package com.vanana.yeogi.guest.dto.response.terms;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TermsRsDto(
    String title,
    String content,
    String version,
    boolean isMandatory
) {}
