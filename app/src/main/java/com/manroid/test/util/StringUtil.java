package com.manroid.test.util;

public class StringUtil {

    public static String replaceString(String phone, String beginNumber) {
        StringBuffer buf = new StringBuffer(phone);
        int start = 0;
        int end = 4;
        buf.replace(start, end, beginNumber);
        return buf.toString();
    }

}
