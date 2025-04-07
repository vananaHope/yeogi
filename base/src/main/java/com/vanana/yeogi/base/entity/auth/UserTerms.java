package com.vanana.yeogi.base.entity.auth;

import com.vanana.yeogi.base.entity.common.BaseEt;
import com.vanana.yeogi.base.entity.terms.TermsEt;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class UserTerms extends BaseEt {
    @Id
    @GeneratedValue
    @Column(name = "user_terms_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEt user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "title", referencedColumnName = "title"),
        @JoinColumn(name = "version", referencedColumnName = "version")
    })
    private TermsEt terms;

}
