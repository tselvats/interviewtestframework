package com.Interview.core;

import com.Interview.core.util.PropertyLoader;
import com.Interview.core.util.PropertyUtils;
import com.Interview.core.util.TestListenerCore;
import com.Interview.core.util.logging.LoggerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;


import java.io.IOException;
import java.lang.reflect.Method;

@Listeners(TestListenerCore.class)
public abstract class BaseTest {

    @BeforeSuite(alwaysRun = true)
    protected void beforeEachSuite() {
        new PropertyLoader().load();
    }

    @BeforeMethod(alwaysRun = true)
    protected void beforeEachMethod(Method method){
        String loggerName = this.getClass().getSimpleName() + "." + method.getName();
        Logger logger = LoggerFactory.getLogger(loggerName);
        LoggerManager.set(logger);
    }

    @AfterMethod(alwaysRun = true)
    protected void afterEachMethod(){
        LoggerManager.remove();
    }

    @AfterSuite(alwaysRun = true)
    protected void afterEachSuite(){
        String loggerName = this.getClass().getSimpleName() + ".AfterSuite";
        Logger logger = LoggerFactory.getLogger(loggerName);
        LoggerManager.set(logger);
    }

}
