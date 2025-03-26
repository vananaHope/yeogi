package com.vanana.yeogi.guest.service;

import com.vanana.yeogi.base.entity.TermsEt;
import com.vanana.yeogi.base.repository.TermsQueryRepository;
import com.vanana.yeogi.base.repository.TermsRepository;
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

    public List<TermsRsDto> getAllRecentTerms() {
        return termsMapper.toTermsRsDtoList(termsQueryRepository.findAllRecentTerms());
    }

    public List<TermsRsDto> getRecentTermsDetail(Long termsId){
        return termsMapper.toTermsRsDtoList(termsQueryRepository.findRecentTermsDetail(termsId));
    }

}
