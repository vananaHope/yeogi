package com.vanana.yeogi.admin.service;

import com.vanana.yeogi.admin.dto.request.AdminTermsRqDto;
import com.vanana.yeogi.admin.dto.response.AdminTermsRsDto;
import com.vanana.yeogi.admin.mapper.AdminTermsMapper;
import com.vanana.yeogi.base.entity.TermsEt;
import com.vanana.yeogi.base.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminTermsService {
    private final TermsRepository termsRepository;
    private final AdminTermsMapper adminTermsMapper;

    @Transactional
    public AdminTermsRsDto addNewTerms(AdminTermsRqDto adminTermsRqDto) {
        TermsEt termsEt = adminTermsMapper.toTermsEt(adminTermsRqDto);
        return adminTermsMapper.toAdminTermsRsDto(termsRepository.save(termsEt));
    }

    @Transactional
    public AdminTermsRsDto updateTerms(Long id, AdminTermsRqDto dto) {
        TermsEt termsEt = termsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid terms id: " + id));

        termsEt.syncWithDto(dto.title(),dto.content(),dto.isMandatory(),dto.isUseYn());

        return adminTermsMapper.toAdminTermsRsDto(termsEt);
    }
}
