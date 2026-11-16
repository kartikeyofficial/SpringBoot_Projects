package com.aop.service;

public class LoggingServiceUtil {
    public static void logStart(String className, String methodName){
        System.out.println("Executive-> "+className+" : "+methodName);

    }

}
