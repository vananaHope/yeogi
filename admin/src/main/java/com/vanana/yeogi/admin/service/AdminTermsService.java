package com.vanana.yeogi.admin.service;

import com.vanana.yeogi.admin.dto.request.AdminTermsRqDto;
import com.vanana.yeogi.admin.dto.response.AdminListRsDto;
import com.vanana.yeogi.admin.dto.response.AdminTermsDetailDto;
import com.vanana.yeogi.admin.dto.response.AdminTermsSummaryDto;
import com.vanana.yeogi.admin.mapper.AdminTermsMapper;
import com.vanana.yeogi.base.entity.terms.TermsEt;
import com.vanana.yeogi.base.entity.embeddable.TermsId;
import com.vanana.yeogi.base.exceptions.CustomException;
import com.vanana.yeogi.base.exceptions.ErrorType;
import com.vanana.yeogi.base.repository.terms.TermsQueryRepository;
import com.vanana.yeogi.base.repository.terms.TermsRepository;
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
    private final TermsQueryRepository termsQueryRepository;
    private final AdminTermsMapper adminTermsMapper;
    private final EntityManager entityManager;

    /**
     * 관리자 약관 전체 목록 조회
     * 사용 여부 상관 없이 모두 조회
     */
    public AdminListRsDto getAllTerms(){
        List<TermsEt> termsEtList = termsQueryRepository.findAllVersionTerms();
        List<AdminTermsSummaryDto> termsSummaryList = adminTermsMapper.toAdminTermsSummaryDtoList(termsEtList);

        return AdminListRsDto.builder()
                .termsSummary(termsSummaryList)
                .build();
    }

    /**
     * 관리자 약관 상세 내용 조회
     */
    public AdminTermsDetailDto getDetailTerms(TermsId termsId){
        TermsEt termsDetail = termsRepository.findByTermsId(termsId)
                .orElseThrow(()-> CustomException.builder()
                                    .errorType(ErrorType.TERMS_ERROR)
                                    .logLevel(LogLevel.WARN)
                                    .build());

        return adminTermsMapper.toAdminTermsDetailDto(termsDetail);
    }

    /**
     * 관리자 약관 버전 리스트 조회
     */
    public List<String> getVersionList(String title){
        List<String> versionByTitle = termsRepository.findVersionByTitle(title);
        return versionByTitle;
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
            // 이전 버전 약관 미사용으로 변경
            termsEt.setUsed(false);

            // 수정 내용으로 새 버전 entity 생성
            TermsEt insertTerms = TermsEt.builder()
                    .termsId(TermsId.builder()
                            .title(dto.title())
                            .version(getNextVersion(dto.version()))
                            .build())
                    .content(dto.content())
                    .isUsed(true)
                    .isMandatory(dto.isMandatory())
                    .build();

            // 이전 버전 약관 미사용으로 업데이트
            // 이 시점에 낙관적 락 충돌 확인
            termsRepository.save(termsEt);

            // 새 버전 약관 저장
            termsRepository.save(insertTerms);

            return insertTerms.getTermsId();
        }catch (OptimisticLockException e){
            throw CustomException.builder()
                    .errorType(ErrorType.TERMS_UPDATE_ERROR)
                    .logLevel(LogLevel.WARN)
                    .orgException(e)
                    .build();
        }
    }

    /**
     * 업데이트 시 이전 버전 + 1 해서 반환
     * @param beforeVersion 이전 버전
     */
    public String getNextVersion(String beforeVersion){
        int version = Integer.parseInt(beforeVersion) + 1;
        return String.valueOf(version);
    }

}
