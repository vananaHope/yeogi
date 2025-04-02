package com.vanana.yeogi.base.repository;

import com.vanana.yeogi.base.entity.TermsEt;
import com.vanana.yeogi.base.entity.embeddable.TermsId;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;


public interface TermsRepository extends JpaRepository<TermsEt, TermsId> {

    @Lock(LockModeType.OPTIMISTIC)
    Optional<TermsEt> findByTermsId(TermsId id);

}
