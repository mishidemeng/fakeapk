package com.turing.fakeapk.bean;

import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by turingkuang on 2017/3/16.
 */
public class AppInfo {
    public String appName = "";
    public String packageName = "";
    public String versionName = "";
    public int versionCode = 0;
    public Drawable appIcon = null;
    public boolean state;
    private int pid ;  //该应用程序所在的进程号
    private String processName ;  // 该应用程序所在的进程名

    public AppInfo(String appName, String packageName, String versionName, boolean state) {
        this.appName = appName;
        this.packageName = packageName;
        this.versionName = versionName;
        this.state = state;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public void print() {
        Log.v("app", "Name:" + appName + " Package:" + packageName);
        Log.v("app", "Name:" + appName + " versionName:" + versionName);
        Log.v("app", "Name:" + appName + " versionCode:" + versionCode);
    }
}
