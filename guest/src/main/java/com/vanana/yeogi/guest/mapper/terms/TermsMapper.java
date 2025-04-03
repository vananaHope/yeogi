package com.vanana.yeogi.guest.mapper.terms;

import com.vanana.yeogi.base.entity.terms.TermsEt;
import com.vanana.yeogi.guest.dto.response.terms.TermsRsDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TermsMapper {
    TermsMapper INSTANCE = Mappers.getMapper(TermsMapper.class);

    /**
     * Entity To Dto
     */
    List<TermsRsDto> toTermsRsDtoList(List<TermsEt> termsEtList);
    TermsRsDto toTermsRsDto(TermsEt termsEt);

    /**
     * Dto To Entity
     */
    TermsEt toTermsEt(TermsRsDto dto);
}
