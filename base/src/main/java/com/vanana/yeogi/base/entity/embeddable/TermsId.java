package com.vanana.yeogi.base.entity.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class TermsId implements Serializable {

    @Column(name = "title", updatable = false, nullable = false)
    private String title;

    @Column(name = "version", updatable = false, nullable = false)
    private String version;

    @Builder
    public TermsId(String title, String version) {
        this.title = title;
        this.version = version;
    }
}
