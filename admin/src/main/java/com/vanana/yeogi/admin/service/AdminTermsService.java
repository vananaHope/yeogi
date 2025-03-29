package com.vanana.yeogi.admin.service;

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
     */
    public List<AdminTermsRsDto> getAllTerms(){
        List<TermsEt> termsEtList = termsRepository.findAll();
        return adminTermsMapper.toAdminTermsRsDtoList(termsEtList);
    }

    /**
     * 관리자 약관 추가
     * @param adminTermsRqDto 관리자 약관 요청 dto
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

            termsEt.updateTerms(dto.title(), dto.content(), dto.isMandatory(), dto.isUseYn());

            // 동시성 테스트를 위해 강제로 수정 후 바로 flush를 통해 db에 반영
            entityManager.flush();

            return adminTermsMapper.toAdminTermsRsDto(termsEt);
        }catch (OptimisticLockException e){
            throw new OptimisticLockException("약관 수정 충돌 발생");
        }
    }
}
