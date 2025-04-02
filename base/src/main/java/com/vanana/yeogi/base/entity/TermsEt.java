package com.vanana.yeogi.base.entity;

import com.vanana.yeogi.base.entity.common.BaseEt;
import com.vanana.yeogi.base.entity.embeddable.TermsId;
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

    @EmbeddedId
    private TermsId termsId;

    @Version
    @Column(name = "lock_version")
    private Integer lockVersion;

    @Column(name = "content")
    private String content;

    @Column(name = "mandatory")
    private Boolean isMandatory;

    @Column(name = "use_yn")
    private Boolean isUsed;

    @Builder
    public TermsEt(TermsId termsId, String content, Boolean isMandatory, Boolean isUsed) {
        this.termsId = termsId;
        this.content = content;
        this.isMandatory = isMandatory;
        this.isUsed = isUsed;
    }

    public void updateTerms(TermsId termsId, String content, Boolean isMandatory, Boolean isUsed) {
        if(Objects.nonNull(termsId)){this.termsId = termsId;}
        if(Objects.nonNull(content)){this.content = content;}
        if(Objects.nonNull(isMandatory)){this.isMandatory = isMandatory;}
        if(Objects.nonNull(isUsed)){this.isUsed = isUsed;}
    }
}
