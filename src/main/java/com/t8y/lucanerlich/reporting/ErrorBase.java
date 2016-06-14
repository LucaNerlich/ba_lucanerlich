package com.t8y.lucanerlich.reporting;

/**
 * Created by lucan on 12.06.2016.
 *
 * Use as base for error. Store report text body etc.
 * Groups are used to manage log / reporting level
 */
public class ErrorBase {

    private String message;

    public ErrorBase(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
