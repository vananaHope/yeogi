package com.vanana.guest.mapper;

import com.vanana.base.entity.TermsEt;
import com.vanana.guest.dto.response.TermsRsDto;
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

    List<TermsRsDto> toTermsRsDtoList(List<TermsEt> termsEtList);
}
