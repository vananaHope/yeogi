package com.vanana.yeogi.guest.service;

import com.vanana.yeogi.base.entity.TermsEt;
import com.vanana.yeogi.base.repository.TermsQueryRepository;
import com.vanana.yeogi.guest.dto.GuestListRsDto;
import com.vanana.yeogi.guest.dto.response.TermsRsDto;
import com.vanana.yeogi.guest.mapper.TermsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TermsService {
    private final TermsQueryRepository termsQueryRepository;
    private final TermsMapper termsMapper;

    /**
     * 최신 약관 목록 조회
     */
    public GuestListRsDto getAllRecentTerms() {
        List<TermsEt> allRecentTerms = termsQueryRepository.findAllRecentTerms();
        List<TermsRsDto> termsRsDtoList = termsMapper.toTermsRsDtoList(allRecentTerms);

        return GuestListRsDto.builder().termsRsDtoList(termsRsDtoList).build();
    }

    /**
     * 최신 약관 디테일 정보 조회 - 버전 상관없이 최신 버전만
     * @param title 약관 제목
     */
    public GuestListRsDto getRecentTermsDetail(String title){
        List<TermsEt> recentTermsDetail = termsQueryRepository.findRecentTermsDetail(title);
        List<TermsRsDto> termsRsDtoList = termsMapper.toTermsRsDtoList(recentTermsDetail);

        return GuestListRsDto.builder().termsRsDtoList(termsRsDtoList).build();
    }

}
