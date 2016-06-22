package com.t8y.lucanerlich.reporting;

/**
 * Created by lucan on 22.06.2016.
 */
public interface ErrorLevel {

    /**
     * Counts the number of successfull and failed test exectuions.
     *
     * @param testResult
     * @return
     */
    void isTestSuccessful(boolean testResult, String methodName);

    void printLog();
}
