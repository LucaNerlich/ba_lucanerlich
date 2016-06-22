package com.t8y.lucanerlich.reporting;

import static org.junit.Assert.assertTrue;

/**
 * Created by lucan on 12.06.2016.
 * <p>
 * Use as base for error. Store report text body etc.
 * Groups are used to manage log / reporting level
 */
public class ErrorBase {

    protected String levelIdentifier;
    protected static int successfullAssertions = 0;
    protected static int failedAssertions = 0;

    public ErrorBase() {

    }

    public String getLevelIdentifier() {
        return levelIdentifier;
    }

    public void setLevelIdentifier(String levelIdentifier) {
        this.levelIdentifier = levelIdentifier;
    }

    public int getSuccessfullAssertions() {
        return successfullAssertions;
    }

    public int getFailedAssertions() {
        return failedAssertions;
    }
}
