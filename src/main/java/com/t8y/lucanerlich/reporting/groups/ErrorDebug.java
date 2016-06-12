package com.t8y.lucanerlich.reporting.groups;

import com.t8y.lucanerlich.reporting.ErrorBase;

import java.time.LocalDate;

/**
 * Created by lucan on 12.06.2016.
 */
public class ErrorDebug extends ErrorBase {

    private LocalDate timestamp = LocalDate.now();

    public ErrorDebug(String message) {
        super(message);
    }
}
