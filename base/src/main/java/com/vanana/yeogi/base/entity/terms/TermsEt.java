package com.vanana.yeogi.base.entity.terms;

import com.vanana.yeogi.base.entity.common.BaseEt;
import com.vanana.yeogi.base.entity.embeddable.TermsId;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }
}
