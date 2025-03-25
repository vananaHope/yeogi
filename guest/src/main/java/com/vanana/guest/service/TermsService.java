package com.vanana.guest.service;

import com.vanana.base.repository.TermsQueryRepository;
import com.vanana.guest.dto.response.TermsRsDto;
import com.vanana.guest.mapper.TermsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermsService {
    private TermsQueryRepository termsQueryRepository;
    private TermsMapper termsMapper;

    public List<TermsRsDto> getAllRecentTerms() {
        List<TermsRsDto> termsRsDtoList = termsMapper.toTermsRsDtoList(termsQueryRepository.findAllRecentTerms());

        return termsRsDtoList;
    }
}
