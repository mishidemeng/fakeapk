package com.turing.fakeapk.Utils;

import android.util.Log;

import de.robv.android.xposed.XposedBridge;

/**
 * Created by turingkuang on 2017/1/7.
 */
public class KernelLogUtil {
    public static boolean logIsOpen = true;

    public static void LogXposed(String str) {
        if (true == logIsOpen) {
            XposedBridge.log(str);
        }
    }

    public static void LogTuring(String str) {
        if (true == logIsOpen && null != str) {
            Log.d("Turing", str);
        }
    }

    public static boolean isOK(String str) {
        if (null == str || str.equals(""))
            return false;
        return true;
    }
}
