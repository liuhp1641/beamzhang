package com.cm.user.exception;

import java.io.Serializable;

public class UserException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -4365630128856068164L;


    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
