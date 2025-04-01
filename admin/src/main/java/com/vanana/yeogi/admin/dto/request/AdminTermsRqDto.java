package com.vanana.yeogi.admin.dto.request;

import com.vanana.yeogi.base.entity.embeddable.TermsId;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AdminTermsRqDto(
    @NotBlank
    String title,
    @NotBlank
    String version,
    @NotBlank
    String content,
    @NotBlank
    Boolean isMandatory,
    @NotBlank
    Boolean isUsed
) {

    public TermsId toTermsId() {
        return TermsId.builder()
                .title(title)
                .version(version)
                .build();
    }
}
