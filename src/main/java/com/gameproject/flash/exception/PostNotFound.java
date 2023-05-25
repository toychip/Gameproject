package com.gameproject.flash.exception;

//정책상 오류를 404로 내려줘야 함
public class PostNotFound extends FlashGame {

    private static final String MESSAGE = "존재하지 않는 글입니다.";

    public PostNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }

//    public PostNotFound(Throwable cause) {
//        super(MESSAGE, cause);
//    }
}
