package com.example.ez_4;

public class Utils {
    public static final String IP = "172.20.10.4";
    public static void sleep(int milli) {
        try {
            Thread.sleep(milli);
        } catch (Exception e) {
            // Do nothing
        }
    }
}
