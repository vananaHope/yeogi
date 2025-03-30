package com.vanana.yeogi.base.entity;

import com.vanana.yeogi.base.entity.common.BaseEt;
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
    // 복합키로 변경
    // 타이틀하고 버전 복합키로 변경
    // embeddable
    // 복합키 순서 고려
    // 유저가 사용한 게 최신 버전 확인
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terms_id")
    private Long termsId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    // 테이블에 없는 락을 쓸 객체를 만들고 거기에 낙관적 락 걸기
    // @Lock optimistic lock 사용
    // 혹은 수동으로 구현, 동작 방식 확인, 비즈니스적으로 생각해보기
    // os 레벨의 낙관적 락
    @Version
    private Integer version;

    @Column(name = "mandatory")
    private Boolean isMandatory;

    @Column(name = "use_yn")
    private Boolean isUsed;

    @Builder
    public TermsEt(String title, String content, Boolean isMandatory, Boolean isUsed) {
        this.title = title;
        this.content = content;
        this.isMandatory = isMandatory;
        this.isUsed = isUsed;
    }

    public void updateTerms(String title, String content, Boolean isMandatory, Boolean isUsed) {
        if(Objects.nonNull(title)){this.title = title;}
        if(Objects.nonNull(content)){this.content = content;}
        if(Objects.nonNull(isMandatory)){this.isMandatory = isMandatory;}
        if(Objects.nonNull(isUsed)){this.isUsed = isUsed;}
    }
}
