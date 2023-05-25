package com.gameproject.flash.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SmallNameRequest {

    private String smallCode;
    public SmallNameRequest(String smallCode) {
        this.smallCode = smallCode;
    }
}
