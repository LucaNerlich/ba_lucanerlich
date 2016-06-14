package com.t8y.lucanerlich.reporting;

/**
 * Created by lucan on 14.06.2016.
 */
public interface ErrorLevel {

    String printTestMethodName(String message);
    boolean isTestSuccessful(boolean testResult);
}
