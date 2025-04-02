package com.vanana.yeogi.admin.service;

import com.vanana.yeogi.admin.dto.response.AdminListRsDto;
import com.vanana.yeogi.admin.dto.request.AdminTermsRqDto;
import com.vanana.yeogi.admin.dto.response.AdminTermsSummaryDto;
import com.vanana.yeogi.admin.mapper.AdminTermsMapper;
import com.vanana.yeogi.base.entity.TermsEt;
import com.vanana.yeogi.base.entity.embeddable.TermsId;
import com.vanana.yeogi.base.exceptions.CustomException;
import com.vanana.yeogi.base.exceptions.ErrorType;
import com.vanana.yeogi.base.repository.TermsRepository;
import com.vanana.yeogi.base.value.LogLevel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminTermsService {
    private final TermsRepository termsRepository;
    private final AdminTermsMapper adminTermsMapper;
    private final EntityManager entityManager;

    /**
     * 관리자용 약관 전체 목록 조회
     */
    public AdminListRsDto getAllTerms(){
        List<TermsEt> termsEtList = termsRepository.findAll();
        List<AdminTermsSummaryDto> termsSummaryList = adminTermsMapper.toAdminTermsSummaryDtoList(termsEtList);

        return AdminListRsDto.builder()
                .termsSummary(termsSummaryList)
                .build();
    }

    /**
     * 관리자 약관 추가
     * @param adminTermsRqDto 관리자 약관 요청 dto
     */
    @Transactional
    public TermsId addNewTerms(AdminTermsRqDto adminTermsRqDto) {
        TermsEt termsEt = adminTermsMapper.toTermsEt(adminTermsRqDto);
        TermsEt saved = termsRepository.save(termsEt);

        return saved.getTermsId();
    }

    /**
     * 관리자 약관 수정
     * @param dto 관리자 약관 요청 dto
     */
    @Transactional
    public TermsId updateTerms(AdminTermsRqDto dto){
        try {
            TermsEt termsEt = termsRepository.findByTermsId(dto.toTermsId())
                    .orElseThrow(()-> CustomException.builder()
                                            .errorType(ErrorType.TERMS_ERROR)
                                            .logLevel(LogLevel.ERROR)
                                            .build());

            termsEt.updateTerms(dto.toTermsId(), dto.content(), dto.isMandatory(), dto.isUsed());

            return termsEt.getTermsId();
        }catch (OptimisticLockException e){
            throw CustomException.builder()
                    .errorType(ErrorType.TERMS_UPDATE_ERROR)
                    .logLevel(LogLevel.WARN)
                    .orgException(e)
                    .build();
        }
    }

}
