package com.vanana.guest.service;

import com.vanana.base.entity.TermsEt;
import com.vanana.base.repository.TermsQueryRepository;
import com.vanana.guest.dto.response.TermsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermsService {
    private TermsQueryRepository termsQueryRepository;
}
