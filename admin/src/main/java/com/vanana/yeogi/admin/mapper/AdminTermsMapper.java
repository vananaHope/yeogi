package com.vanana.yeogi.admin.mapper;

import com.vanana.yeogi.admin.dto.request.AdminTermsRqDto;
import com.vanana.yeogi.admin.dto.response.AdminTermsRsDto;
import com.vanana.yeogi.base.entity.TermsEt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface AdminTermsMapper {
    AdminTermsMapper INSTANCE = Mappers.getMapper(AdminTermsMapper.class);

    /**
     * Dto To Entity
     */
     @Mapping(source = "title", target = "termsId.title")
     @Mapping(source = "version", target = "termsId.version")
     TermsEt toTermsEt(AdminTermsRqDto dto);

    /**
     * Entity To Dto
     */
     @Mapping(source = "termsId.title", target = "title")
     @Mapping(source = "termsId.version", target = "version")
     AdminTermsRsDto toAdminTermsRsDto(TermsEt termsEt);

     List<AdminTermsRsDto> toAdminTermsRsDtoList(List<TermsEt> termsEtList);
}
