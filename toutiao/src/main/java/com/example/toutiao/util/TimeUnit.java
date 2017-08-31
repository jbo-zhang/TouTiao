package com.example.toutiao.util;

/**
 * Created by zhangjinbo on 17-8-29.
 */

public class TimeUnit {
    public static String getCurrentTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
}
