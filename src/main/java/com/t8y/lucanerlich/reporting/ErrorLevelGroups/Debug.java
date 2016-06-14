package com.t8y.lucanerlich.reporting.ErrorLevelGroups;

import com.t8y.lucanerlich.reporting.ErrorLevel;

/**
 * Created by lucan on 14.06.2016.
 */
public class Debug implements ErrorLevel {
    public String printTestMethodName(String message) {
        return message;
    }

    public boolean isTestSuccessful(boolean testResult) {
        return testResult;
    }
}
