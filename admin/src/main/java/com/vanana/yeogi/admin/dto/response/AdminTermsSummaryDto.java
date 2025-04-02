package com.vanana.yeogi.admin.dto.response;

import lombok.Builder;

@Builder
public record AdminTermsSummaryDto(
        String title,
        String version,
        boolean isMandatory,
        boolean isUsed
) {
}
