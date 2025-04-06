package com.vanana.yeogi.guest.dto.request.auth;

import com.vanana.yeogi.base.entity.embeddable.TermsId;
import lombok.Builder;

@Builder
public record UserAgreeRqDto(
        String title,
        String version
) {
    public TermsId toTermsId(){
        return TermsId.builder()
                .title(title)
                .version(version)
                .build();
    }
}
