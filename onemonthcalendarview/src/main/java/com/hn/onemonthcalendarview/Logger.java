package com.hn.onemonthcalendarview;

import android.text.TextUtils;
import android.util.Log;

public class Logger {

    enum MODE{VERBOSE, ERROR, WARNING, INFO, DEBUG}


    public static void error(Class<?> cls, String message) {

        Exception ex = new Exception();
        StackTraceElement stackElement = ex.getStackTrace()[1];
        log(stackElement.getClassName() + "(" + stackElement.getLineNumber() + ")", MODE.ERROR, message);
    }
    public static void debug(String message) {
        Exception ex = new Exception();
        StackTraceElement stackElement = ex.getStackTrace()[1];
        log(stackElement.getClassName() + "->line:" + stackElement.getLineNumber(), MODE.DEBUG,  message);
    }

    public static void debug(String TAG, String message) {
        Exception ex = new Exception();
        StackTraceElement stackElement = ex.getStackTrace()[1];
        log(stackElement.getClassName() + "->line:" + stackElement.getLineNumber(), MODE.DEBUG,  message);
    }

    public static void debug( Exception e) {

        Exception ex = new Exception();
        StackTraceElement stackElement = ex.getStackTrace()[1];
        log(stackElement.getClassName() + "->line:" + stackElement.getLineNumber(), MODE.DEBUG,  e.toString());
    }

    public static void error(String message) {

        Exception ex = new Exception();
        StackTraceElement stackElement = ex.getStackTrace()[1];
        log(stackElement.getClassName() + "->line:" + stackElement.getLineNumber(), MODE.ERROR,  message);
    }

    public static void error(String TAG, String message) {

        Exception ex = new Exception();
        StackTraceElement stackElement = ex.getStackTrace()[1];

        log(stackElement.getClassName() + "->Line:" + stackElement.getLineNumber(), MODE.ERROR,  message);
    }


    private static void log(String TAG, MODE level, String msg) {
        if (shouldLog() && !TextUtils.isEmpty(msg)) {// only show valid Log
            switch (level) {
                case VERBOSE:
                    Log.v(TAG, msg);
                    break;
                case ERROR:
                    Log.e(TAG, msg);
                    break;
                case WARNING:
                    Log.w(TAG, msg);
                    break;
                case INFO:
                    Log.i(TAG, msg);
                    break;
                case DEBUG:
                    Log.d(TAG, msg);
                    break;
                default:
                    Log.v(TAG, msg);
                    break;
            }
        }
    }

    private static boolean shouldLog() {
        return true;
    }
}
