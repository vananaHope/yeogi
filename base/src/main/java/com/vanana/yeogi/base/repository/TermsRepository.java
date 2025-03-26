package com.vanana.yeogi.base.repository;

import com.vanana.yeogi.base.entity.TermsEt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TermsRepository extends JpaRepository<TermsEt, Long> {
}
