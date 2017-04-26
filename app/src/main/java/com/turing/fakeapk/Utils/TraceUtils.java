package com.turing.fakeapk.Utils;

public class TraceUtils {
    public static String getTraceInfo() {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        int stacksLen = stacks.length;
        String str = "";
        for (int index = 0; index < stacksLen; index++) {
            str = sb.append("class: ").append(stacks[index].getClassName()).append("; method: ").append(stacks[index].getMethodName()).append("; number: ").append(stacks[index].getLineNumber()).toString();
            KernelLogUtil.LogTuring("\n" + str);
        }
        return "";
    }
}