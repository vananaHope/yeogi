package com.vanana.yeogi.admin.dto;

import com.vanana.yeogi.admin.dto.response.AdminTermsRsDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AdminListRsDto {
    private List<AdminTermsRsDto> rsDtoList;
}
