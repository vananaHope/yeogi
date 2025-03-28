package com.vanana.yeogi.admin.mapper;

import com.vanana.yeogi.admin.dto.request.AdminTermsRqDto;
import com.vanana.yeogi.admin.dto.response.AdminTermsRsDto;
import com.vanana.yeogi.base.entity.TermsEt;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring"
)
public interface AdminTermsMapper {
    AdminTermsMapper INSTANCE = Mappers.getMapper(AdminTermsMapper.class);

    /**
     * Dto To Entity
     */
     TermsEt toTermsEt(AdminTermsRqDto dto);

    /**
     * Entity To Dto
     */
     AdminTermsRsDto toAdminTermsRsDto(TermsEt termsEt);
}
