package com.ssafy.logit.exception;

public class WrongCategoryException extends  RuntimeException{


    public WrongCategoryException() {
        super();
    }

    public WrongCategoryException(String message) {
        super(message);
    }

    public WrongCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongCategoryException(Throwable cause) {
        super(cause);
    }

    protected WrongCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
