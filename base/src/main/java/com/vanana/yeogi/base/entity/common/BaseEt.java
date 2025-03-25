package com.vanana.yeogi.base.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEt {

    @CreatedDate
    @Column(name = "input_dt")
    private LocalDateTime inputDt;

    @LastModifiedDate
    @Column(name = "modify_dt")
    private LocalDateTime modifyDt;

}
