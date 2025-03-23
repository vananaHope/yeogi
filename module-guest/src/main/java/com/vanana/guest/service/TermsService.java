package com.vanana.guest.service;

import com.vanana.base.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TermsService {
    private TermsRepository termsRepository;
}
