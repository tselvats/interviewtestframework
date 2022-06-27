package com.Interview.web.util;

import com.Interview.core.util.PropertyUtils;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListenerWeb implements ITestListener, StepLifecycleListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String shotName = "Failure_" + result.getName();
        SimpliScreenshot.captureViewport(shotName);
    }

    @Override
    public void beforeStepStop(StepResult result) {
        if (PropertyUtils.getPropertyBoolean("com.Interview.web.screenshot.everystep")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
            String now = LocalDateTime.now().format(formatter);
            String shotName = result.getName().replaceAll(" ", "_");
            shotName=shotName+"_"+now;
            SimpliScreenshot.captureViewport(shotName);
        }
    }
}
