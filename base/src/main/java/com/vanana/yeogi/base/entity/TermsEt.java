package com.vanana.yeogi.base.entity;

import com.vanana.yeogi.base.entity.common.BaseEt;
import com.vanana.yeogi.base.util.BooleanToYNConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
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

    @Version
    private Integer version;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(name = "mandatory")
    private Boolean isMandatory;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(name = "use_yn")
    private Boolean isUseYn;

    @Builder
    public TermsEt(String title, String content, Boolean isMandatory, Boolean isUseYn) {
        this.title = title;
        this.content = content;
        this.isMandatory = isMandatory;
        this.isUseYn = isUseYn;
    }

    public void updateTerms(String title, String content, Boolean isMandatory, Boolean isUseYn) {
        if(Objects.nonNull(title)){this.title = title;}
        if(Objects.nonNull(content)){this.content = content;}
        if(Objects.nonNull(isMandatory)){this.isMandatory = isMandatory;}
        if(Objects.nonNull(isUseYn)){this.isUseYn = isUseYn;}
    }
}
