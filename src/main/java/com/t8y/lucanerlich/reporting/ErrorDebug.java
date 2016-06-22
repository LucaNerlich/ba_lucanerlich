package com.t8y.lucanerlich.reporting;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

/**
 * Created by lucan on 12.06.2016.
 */
public class ErrorDebug extends ErrorBase implements ErrorLevel{

    public ErrorDebug() {
        setLevelIdentifier("DEBUG: ");
    }

    public synchronized void isTestSuccessful(boolean testResult, String methodName) {
        try {
            assertTrue(testResult);
            successfullAssertions++;
            System.out.println(levelIdentifier + "OK : @" + methodName+ " : " + LocalDate.now());
        } catch (AssertionError error) {
            failedAssertions++;
            error.printStackTrace();
            System.out.println(levelIdentifier + "FAIL : @" + methodName+ " : " + LocalDate.now());
        }
    }
}
