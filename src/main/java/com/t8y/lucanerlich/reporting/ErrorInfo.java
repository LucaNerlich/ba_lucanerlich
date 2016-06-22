package com.t8y.lucanerlich.reporting;


import static org.junit.Assert.assertTrue;

/**
 * Created by lucan on 22.06.2016.
 */
public class ErrorInfo extends ErrorBase implements ErrorLevel {

    public ErrorInfo() {
        setLevelIdentifier("INFO: ");
    }

    public synchronized void isTestSuccessful(boolean testResult, String methodName) {
        try {
            assertTrue(testResult);
            System.out.println(levelIdentifier + "OK : @" + methodName);
        } catch (AssertionError error) {
            System.out.println(levelIdentifier + "FAIL : @" + methodName);
        }
    }

    public void printLog() {

    }
}
