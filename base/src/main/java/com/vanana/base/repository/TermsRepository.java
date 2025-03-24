package com.vanana.base.repository;

import com.vanana.base.entity.TermsEt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsRepository extends JpaRepository<TermsEt, Long> {
}
