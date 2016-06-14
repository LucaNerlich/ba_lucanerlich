package com.t8y.lucanerlich.reporting.Exceptions;

/**
 * Created by lucan on 14.06.2016.
 */
public class CompareException extends Exception {

    public CompareException() {
    }

    public CompareException(String message) {
        super(message);
    }

    public CompareException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompareException(Throwable cause) {
        super(cause);
    }

    public CompareException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
