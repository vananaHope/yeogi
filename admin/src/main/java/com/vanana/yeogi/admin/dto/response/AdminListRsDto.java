package com.vanana.yeogi.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminListRsDto {
    private List<AdminTermsSummaryDto> termsSummary;
    private List<String> versionList;
}
