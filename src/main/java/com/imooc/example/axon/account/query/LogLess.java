//package com.imooc.example.axon.account.query;
//
//import org.slf4j.LoggerFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * 用来减少打印重复日志 * @author https://www.chuckfang.com * @date Created on 2020/7/20 15:38
// */
//public class LogLess {
//    /**
//     * 每种日志的参数拼接成key，打印之前计算key的数目，达到指定数目才打印日志
//     */
//    private static Map<String, AtomicInteger> logNum = new HashMap<>(100);
//
//    public static void info(Class currentClass, Integer maxNum, String message, Object... args) {
//        String key = getKey(currentClass, args);
//        AtomicInteger count = logNum.get(key);
//        if (count == null) {
//            logNum.put(key, new AtomicInteger(1));
//        } else if (count.incrementAndGet() >= maxNum) {
//            LoggerFactory.getLogger(currentClass).info(message, args);
//            logNum.remove(key);
//        }
//    }
//
//    public static void warn(Class currentClass, Integer maxNum, String message, Object... args) {
//        String key = getKey(currentClass, args);
//        AtomicInteger count = logNum.get(key);
//        if (count == null) {
//            logNum.put(key, new AtomicInteger(1));
//        } else if (count.incrementAndGet() >= maxNum) {
//            LoggerFactory.getLogger(currentClass).warn(message, args);
//            logNum.remove(key);
//        }
//    }
//
//    private static String getKey(Class currentClass, Object[] args) {
//        StringBuilder stringBuffer = new StringBuilder(currentClass.getSimpleName());
//        for (Object arg : args) {
//            stringBuffer.append(arg);
//        }
//        return stringBuffer.toString();
//    }
//
//    public static void main(String[] args) {
//
//    }
//}