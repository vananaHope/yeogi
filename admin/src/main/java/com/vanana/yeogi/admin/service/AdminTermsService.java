package com.vanana.yeogi.admin.service;

import com.vanana.yeogi.admin.dto.AdminListRsDto;
import com.vanana.yeogi.admin.dto.request.AdminTermsRqDto;
import com.vanana.yeogi.admin.dto.response.AdminTermsRsDto;
import com.vanana.yeogi.admin.mapper.AdminTermsMapper;
import com.vanana.yeogi.base.entity.TermsEt;
import com.vanana.yeogi.base.repository.TermsRepository;
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
     * 약관 전체 조회
     * 페이징 및 버전별 그룹핑, 필터링
     */
    public AdminListRsDto getAllTerms(){
        List<TermsEt> termsEtList = termsRepository.findAll();
        List<AdminTermsRsDto> termsRsDtoList = adminTermsMapper.toAdminTermsRsDtoList(termsEtList);

        return AdminListRsDto.builder().rsDtoList(termsRsDtoList).build();
    }

    /**
     * 관리자 약관 추가
     * @param adminTermsRqDto 관리자 약관 요청 dto
     * @transaction 걸 때 확인하기, 트래픽 많을 때는 부담되는 경우 있음, 직접 transaction 로직 구현하는 경우도 있음
     * 필요한 지 판단해서 사용하기, msa/nosql 때문에 필요할때만 사용
     */
    @Transactional
    public AdminTermsRsDto addNewTerms(AdminTermsRqDto adminTermsRqDto) {
        TermsEt termsEt = adminTermsMapper.toTermsEt(adminTermsRqDto);
        return adminTermsMapper.toAdminTermsRsDto(termsRepository.save(termsEt));
    }

    /**
     * 관리자 약관 수정
     * @param termsId 약관 id
     * @param dto 관리자 약관 요청 dto
     */
    @Transactional
    public AdminTermsRsDto updateTerms(Long termsId, AdminTermsRqDto dto){
        try {
            TermsEt termsEt = termsRepository.findById(termsId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid terms id: " + termsId));

            termsEt.updateTerms(dto.title(), dto.content(), dto.isMandatory(), dto.isUsed());

            return adminTermsMapper.toAdminTermsRsDto(termsEt);
        }catch (OptimisticLockException e){
            throw new OptimisticLockException("약관 수정 충돌 발생");
        }
    }

}
