package com.vanana.yeogi.base.entity.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Version;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class TermsId implements Serializable {

    @Column(name = "title")
    private String title;

    @Version
    private Integer version;
}
