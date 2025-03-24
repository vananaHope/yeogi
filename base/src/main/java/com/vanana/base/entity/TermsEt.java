package com.vanana.base.entity;

import com.vanana.base.util.BooleanToYNConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "terms")
public class TermsEt extends BaseEt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terms_id")
    private Long id;

    @Column(name = "terms_title", length = 200)
    private String title;

    @Lob
    @Column(name = "terms_content", length = 3000)
    private String content;

    @Column(name = "terms_version")
    private int version;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(name = "terms_mandatory")
    private Boolean isMandatory;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(name = "use_yn")
    private Boolean isUseYn;

}
