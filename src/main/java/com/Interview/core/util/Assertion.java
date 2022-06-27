package com.Interview.core.util;
import com.Interview.core.util.logging.SimpliLogger;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

public class Assertion {

    public static void assertTrue(Boolean status, String message) {
        if(status==false) {
            SimpliLogger.trace(message+" should displayed");
            Assert.fail(message+" should displayed");
        }
    }

    public static void assertTrue(String actual, String expected) {
        if(!actual.equalsIgnoreCase(expected)){
            SimpliLogger.trace(actual+" text is not equal to "+ expected);
            Assert.fail(actual+" text is not equal to "+ expected);
        }
    }

    public static void assertequals(String actual, String expected) {
        if(!actual.equals(expected)){
            SimpliLogger.trace(actual+" text is not equal to "+ expected);
            Assert.fail(actual+" text is not equal to "+ expected);
        }
    }
    public static void assertnotequals(String actual, String expected) {
        if(actual.equals(expected)){
            SimpliLogger.trace(actual+" text is not equal to "+ expected);
            Assert.fail(actual+" text is equal to "+ expected);
        }
    }
    public static void assertFalse(String actual, String expected) {
        if(actual.equalsIgnoreCase(expected)){
            SimpliLogger.trace(actual+" text is not equal to "+ expected);
            Assert.fail(actual+" text is equal to "+ expected);
        }
    }
    public static void assertFalse(Boolean status, String message) {
        if(status==true) {
            SimpliLogger.trace(message+" is displayed");
            Assert.fail(message+" is displayed");
        }
    }

    public static void assertContains(String actual,String expected){
        if(!actual.contains(expected)){
            SimpliLogger.trace(actual+" text does not contain "+ expected);
            Assert.fail(actual+" text does not contain "+ expected);
        }
    }
    public static void assertContainsText(String actual,String expected){
        if(!StringUtils.containsAny(actual, expected)){
            SimpliLogger.trace(actual+" text does not contain "+ expected);
            Assert.fail(actual+" text does not contain "+ expected);
        }
    }

    public static void assertNotContains(String actual,String expected){
        if(actual.contains(expected)){
            SimpliLogger.trace(actual+" text is contains "+ expected);
            Assert.fail(actual+" text is contains "+ expected);
        }
    }

    public static void assertContainsOR(String actual,String expected1, String expected2){
        if(!(actual.contains(expected1)|| actual.contains(expected2))){
            SimpliLogger.trace(actual+" text does not contain either "+ expected1 + " or " + expected2);
            Assert.fail(actual+" text does not contain either "+ expected1 + " or " + expected2);
        }
    }
    public static void assertContainsORthree(String actual,String expected1, String expected2, String expected3){
        if(!(actual.contains(expected1)|| actual.contains(expected2) || actual.contains(expected3))){
            SimpliLogger.trace(actual+" text does not contain either "+ expected1 + " or " + expected2 + " or " + expected3);
            Assert.fail(actual+" text does not contain either "+ expected1 + " or " + expected2 + " or " + expected3);
        }
    }
    public static void assertequalsignorecase(String actual, String expected) {
        if(!actual.equalsIgnoreCase(expected)){
            SimpliLogger.trace(actual+" text is not equal to "+ expected);
            Assert.fail(actual+" text is not equal to "+ expected);
        }
    }

}
