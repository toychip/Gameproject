package com.gameproject.flash.response;
/*
{
"code" : "400"
"message" : "잘못된 요청입니다."
"validation" : {
    "title" : "값을 입력해주세요"
    }
}
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
// @RequiredArgsConstructor
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY) // 비어있는 값을 빼달라고 하면 이렇게 하면됨.
public class ErrorResponse {

    private final String code;
    private final String message;
    private final Map<String, String> validation;

    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validaion) {
        this.code = code;
        this.message = message;
        this.validation = validaion !=null ? validaion : new HashMap<>();
    }

    public void addValidation(String field, String defaultMessage) {
        this.validation.put(field, defaultMessage);
    }

}
