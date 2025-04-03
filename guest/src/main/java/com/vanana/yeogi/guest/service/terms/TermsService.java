package com.vanana.yeogi.guest.service.terms;

import com.vanana.yeogi.base.entity.terms.TermsEt;
import com.vanana.yeogi.base.repository.terms.TermsQueryRepository;
import com.vanana.yeogi.guest.dto.response.terms.TermsListRsDto;
import com.vanana.yeogi.guest.dto.response.terms.TermsRsDto;
import com.vanana.yeogi.guest.mapper.terms.TermsMapper;
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
    public TermsListRsDto getAllRecentTerms() {
        List<TermsEt> allRecentTerms = termsQueryRepository.findAllRecentTerms();
        List<TermsRsDto> termsRsDtoList = termsMapper.toTermsRsDtoList(allRecentTerms);

        return TermsListRsDto.builder().termsRsDtoList(termsRsDtoList).build();
    }

    /**
     * 최신 약관 디테일 정보 조회 - 버전 상관없이 최신 버전만
     * @param title 약관 제목
     */
    public TermsListRsDto getRecentTermsDetail(String title){
        List<TermsEt> recentTermsDetail = termsQueryRepository.findRecentTermsDetail(title);
        List<TermsRsDto> termsRsDtoList = termsMapper.toTermsRsDtoList(recentTermsDetail);

        return TermsListRsDto.builder().termsRsDtoList(termsRsDtoList).build();
    }

}
