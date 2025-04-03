package com.vanana.yeogi.base.repository.terms;

import com.vanana.yeogi.base.entity.terms.TermsEt;
import com.vanana.yeogi.base.entity.embeddable.TermsId;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface TermsRepository extends JpaRepository<TermsEt, TermsId> {

    @Lock(LockModeType.OPTIMISTIC)
    Optional<TermsEt> findByTermsId(TermsId id);

    @Query(
            "select t.termsId.version " +
                    "from TermsEt t " +
                    "where t.termsId.title = :title"
    )
    List<String> findVersionByTitle(String title);

}
