package com.t8y.lucanerlich.tests;

import com.t8y.lucanerlich.tests.volkswagencarnet.com.TestKundenportal;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by lucan on 22.06.2016.
 */

@RunWith(Suite.class)

// specify an array of test classes

@Suite.SuiteClasses({
        TestKundenportal.class
        //,ExpectedExceptionTest1.class
}
)

public class TestSuite {
}
