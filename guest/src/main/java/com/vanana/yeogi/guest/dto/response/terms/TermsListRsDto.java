package com.vanana.yeogi.guest.dto.response.terms;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TermsListRsDto {
    private List<TermsRsDto> termsRsDtoList;
}
