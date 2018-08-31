package com.manroid.test.util;

import android.util.Log;

public class Logger {

    private static final String LOGGER_NAME = "DEBUG_NUMBER";

    public static void d(String log) {
        Log.d(LOGGER_NAME, log);
    }

}
