package com.vanana.yeogi.admin.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record AdminListRsDto(
    List<AdminTermsSummaryDto> termsSummary
) {}
