package com.vanana.yeogi.base.entity;

import com.vanana.yeogi.base.entity.common.BaseEt;
import com.vanana.yeogi.base.util.BooleanToYNConverter;
import jakarta.persistence.*;
import lombok.*;

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

    @Version
    private Integer version;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(name = "mandatory")
    private Boolean isMandatory;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(name = "use_yn")
    private Boolean isUseYn;

    /**
     * Entity 변경 감지를 위해 dto와 entity 정보를 일치시킴
     */
    public void syncWithDto(String title, String content, Boolean isMandatory, Boolean isUseYn) {
        this.title = title;
        this.content = content;
        this.isMandatory = isMandatory;
        this.isUseYn = isUseYn;
    }

}
