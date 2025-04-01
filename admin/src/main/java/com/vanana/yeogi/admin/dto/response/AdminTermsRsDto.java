package com.vanana.yeogi.admin.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record AdminTermsRsDto(
    String title,
    String content,
    String version,
    List<String> versionList,
    boolean isMandatory
) {}
