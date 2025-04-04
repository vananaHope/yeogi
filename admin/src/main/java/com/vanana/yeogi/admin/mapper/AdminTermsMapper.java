package com.vanana.yeogi.admin.mapper;

import com.vanana.yeogi.admin.dto.request.AdminTermsRqDto;
import com.vanana.yeogi.admin.dto.response.AdminTermsDetailDto;
import com.vanana.yeogi.admin.dto.response.AdminTermsSummaryDto;
import com.vanana.yeogi.base.entity.terms.TermsEt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface AdminTermsMapper {
    /**
     * 다른 Mapper를 활용하거나 다른 클래스를 참조해야되는 경우
     * 상단 @Mapper 안에 uses를 통해 참조할 객체 선언
     */
    AdminTermsMapper INSTANCE = Mappers.getMapper(AdminTermsMapper.class);

    /**
     * Dto To Entity
     * 맵핑 시 메서드 활용해야 될 경우 expression = java() 로 선언하여 사용
     */
     @Mapping(target = "termsId", expression = "java(dto.toTermsId())")
     TermsEt toTermsEt(AdminTermsRqDto dto);

    /**
     * Entity To Dto
     * 기본 변환 메서드 선언해놓으면 list 변환할 때
     * 자동으로 기본 변환 메서드 활용해서 list 변환해줌
     */
     @Mapping(target = "title", source = "termsId.title")
     @Mapping(target = "version", source = "termsId.version")
     AdminTermsDetailDto toAdminTermsDetailDto(TermsEt termsEt);

     @Mapping(target = "title", source = "termsId.title")
     @Mapping(target = "version", source = "termsId.version")
     AdminTermsSummaryDto toAdminTermsSummaryDto(TermsEt termsEt);

     List<AdminTermsSummaryDto> toAdminTermsSummaryDtoList(List<TermsEt> termsEtList);
}
