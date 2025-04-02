package com.vanana.yeogi.admin.dto.response;

import lombok.Builder;

@Builder
public record AdminTermsDetailDto(
    String title,
    String content,
    String version
) {}
