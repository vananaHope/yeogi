package com.vanana.yeogi.guest.dto;

import com.vanana.yeogi.guest.dto.response.TermsRsDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GuestListRsDto {
    private List<TermsRsDto> termsRsDtoList;
}
