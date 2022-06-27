package com.Interview.core.util.logging;

public class SimpliLogger {

    private SimpliLogger() {
        throw new IllegalStateException("SimpliLogger");
    }

    private static final String LOGGING_HEADER_LEFT = "! -- ";
    private static final String LOGGING_HEADER_RIGHT = " -- !";

    public static void info(String message, Throwable t) {
        LoggerManager.get().info(message, t);
    }

    public static void info(String message) {
        info(message, null);
    }

    public static void warn(String message, Throwable t) {
        LoggerManager.get().warn(message, t);
    }

    public static void warn(String message) {
        warn(message, null);
    }

    public static void error(String message, Throwable t) {
        LoggerManager.get().error(message, t);
    }

    public static void error(String message) {
        error(message, null);
    }

    public static void debug(String message, Throwable t) {
        LoggerManager.get().debug(message, t);
    }

    public static void debug(String message) {
        debug(message, null);
    }

    public static void trace(String message, Throwable t) {
        LoggerManager.get().trace(message, t);
    }

    public static void trace(String message) {
        trace(message, null);
    }

    private static String addLogHeader(String input){
        return LOGGING_HEADER_LEFT + input + LOGGING_HEADER_RIGHT;
    }

    public static void startTestCase(String testCaseName){
        info(addLogHeader("Start of Test Case: " + testCaseName));
    }

    public static void endTestCase(String testCaseName){
        info(addLogHeader("End of Test Case: "+ testCaseName));
    }

    public static void endTestCase(String testCaseName, String result){
        endTestCase(testCaseName + " | Result: " + result);
    }
}
