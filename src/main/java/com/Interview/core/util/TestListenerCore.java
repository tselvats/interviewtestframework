package com.Interview.core.util;

import com.Interview.core.util.logging.SimpliLogger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListenerCore implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        SimpliLogger.startTestCase(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        SimpliLogger.endTestCase(result.getMethod().getMethodName(), "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        SimpliLogger.endTestCase(result.getMethod().getMethodName(), "FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        SimpliLogger.endTestCase(result.getMethod().getMethodName(), "SKIPPED");
    }
}
