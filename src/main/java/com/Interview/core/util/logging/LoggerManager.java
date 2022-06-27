package com.Interview.core.util.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerManager {
    private static ThreadLocal<Logger> threadLocalLogger = new ThreadLocal<>();

    private LoggerManager() {
        throw new IllegalStateException("LoggerManager");
    }

    static synchronized Logger get() {
        Logger logger = threadLocalLogger.get();

        if (logger == null){
            LoggerManager.set(LoggerFactory.getLogger("SimpliTest.NonTestLogger"));
            return get();
        }

        return logger;
    }

    public static synchronized void set(Logger logger) {
        threadLocalLogger.set(logger);
    }

    public static synchronized void remove(){
        threadLocalLogger.remove();
    }
}
