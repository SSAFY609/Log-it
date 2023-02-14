package com.ssafy.logit.exception;

public class NotPassException extends RuntimeException{
    public NotPassException() {
        super("모든 채용전형이 완료되어야 합니다.");
    }

    public NotPassException(String message) {
        super(message);
    }

    public NotPassException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotPassException(Throwable cause) {
        super(cause);
    }

    protected NotPassException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
