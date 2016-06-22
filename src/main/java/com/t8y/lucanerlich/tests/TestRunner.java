package com.t8y.lucanerlich.tests;

import com.t8y.lucanerlich.reporting.ErrorBase;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by lucan on 22.06.2016.
 */
public class TestRunner extends ErrorBase{

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestSuite.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
        System.out.println(successfullAssertions);
        System.out.println(failedAssertions);
    }
}
