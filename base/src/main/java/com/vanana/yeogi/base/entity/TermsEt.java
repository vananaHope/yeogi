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
    @Column(name = "terms_id")
    private TermsId termsId;

    @Version
    @Column(name = "lock_version")
    private Integer lockVersion;

    @Column(name = "content")
    private String content;

    // 테이블에 없는 락을 쓸 객체를 만들고 거기에 낙관적 락 걸기
    // @Lock optimistic lock 사용
    // 혹은 수동으로 구현, 동작 방식 확인, 비즈니스적으로 생각해보기
    // os 레벨의 낙관적 락

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
