package com.ssafy.logit.exception;

public class DifferentUserException extends  RuntimeException{


    public DifferentUserException() {
        super();
    }

    public DifferentUserException(String message) {
        super(message);
    }

    public DifferentUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public DifferentUserException(Throwable cause) {
        super(cause);
    }

    protected DifferentUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
