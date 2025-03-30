package com.vanana.yeogi.guest.service;

import com.vanana.yeogi.base.entity.TermsEt;
import com.vanana.yeogi.base.repository.TermsQueryRepository;
import com.vanana.yeogi.base.repository.TermsRepository;
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
    // 가독성 확인
    // 중요한 건 변수 따로 선언
    // 널 체크 끝난 경우 그냥 기본 타입
    public GuestListRsDto getAllRecentTerms() {
        List<TermsEt> allRecentTerms = termsQueryRepository.findAllRecentTerms();
        List<TermsRsDto> termsRsDtoList = termsMapper.toTermsRsDtoList(allRecentTerms);

        return GuestListRsDto.builder().termsRsDtoList(termsRsDtoList).build();
    }

    public GuestListRsDto getRecentTermsDetail(long termsId){
        List<TermsEt> recentTermsDetail = termsQueryRepository.findRecentTermsDetail(termsId);
        List<TermsRsDto> termsRsDtoList = termsMapper.toTermsRsDtoList(recentTermsDetail);

        return GuestListRsDto.builder().termsRsDtoList(termsRsDtoList).build();
    }

}
