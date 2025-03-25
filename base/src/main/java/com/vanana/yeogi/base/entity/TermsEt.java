package com.vanana.yeogi.base.entity;

import com.vanana.yeogi.base.entity.common.BaseEt;
import com.vanana.yeogi.base.util.BooleanToYNConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "terms")
public class TermsEt extends BaseEt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terms_id")
    private Long termsId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "version")
    private Long version;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(name = "mandatory")
    private Boolean isMandatory;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(name = "use_yn")
    private Boolean isUseYn;

}
