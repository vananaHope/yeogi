package com.vanana.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEt {

    @CreatedBy
    @Column(name = "INPUT_ID", updatable = false, columnDefinition = "VARCHAR(36)")
    private String inputId;

    @CreatedDate
    @Column(name = "input_dt")
    private LocalDateTime inputDt;

    @LastModifiedBy
    @Column(name = "MODIFY_ID", columnDefinition = "VARCHAR(36)")
    private String modifyId;

    @LastModifiedDate
    @Column(name = "modify_dt")
    private LocalDateTime modifyDt;

}
