package com.turing.fakeapk.service;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.turing.fakeapk.fake.SharedPref;

import com.turing.fakeapk.Utils.KernelLogUtil;
import com.turing.fakeapk.bean.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by turingkuang on 2017/3/15.
 */
public class PackageMng {
    private static PackageMng mng = new PackageMng();
    private Context mContext = null;
    private PackageManager pm;
    public static PackageMng getInstance() {
        if (null == mng)
            return new PackageMng();

        return mng;
    }

    public void init(Context cnt) {
        if (null == mContext) {
            mContext = cnt;
        }
    }

    public void printRunningTaskPackages() {
        //用来存储获取的应用信息数据
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(10);
        for (RunningTaskInfo info : list) {
            KernelLogUtil.LogTuring("RunningTask:" + info.baseActivity);
        }
    }
    // 获取除掉白名单之外的已应用包
    public List<AppInfo> getMoniterInstalledPackages() {
        //用来存储获取的应用信息数据
        ArrayList<AppInfo> appList = new ArrayList<AppInfo>();
        String[] canGetAppList = DataMng.getInstance().CAN_GET_ALLAPPS_LIST;
        int flag = 0;
        List<PackageInfo> packages = mContext.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            AppInfo tmpInfo = new AppInfo("", "", "", false);
            tmpInfo.appName = packageInfo.applicationInfo.loadLabel(mContext.getPackageManager()).toString();
            tmpInfo.packageName = packageInfo.packageName;
            tmpInfo.versionName = packageInfo.versionName;
            tmpInfo.versionCode = packageInfo.versionCode;
            tmpInfo.appIcon = packageInfo.applicationInfo.loadIcon(mContext.getPackageManager());
            //Only display the non-system app info
            flag = 0;
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                for(String tmp:canGetAppList){
                    if (tmp.equalsIgnoreCase(tmpInfo.getPackageName())) {
                        flag = 1;
                        break; // 不监控发发系列应用读写
                    }
                }
                if(flag == 0){
                    appList.add(tmpInfo);//如果非系统应用，则添加至appList
                    tmpInfo.print();
                }
            }
        }
        return appList;
    }

    public List<AppInfo> getInstalledPackages() {
        //用来存储获取的应用信息数据
        String apps = SharedPref.getValue("MustFiterKeyWordList");
        List<PackageInfo> packages = mContext.getPackageManager().getInstalledPackages(0);
        ArrayList<AppInfo> appList = new ArrayList<AppInfo>();
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            AppInfo tmpInfo = new AppInfo("", "", "", false);
            tmpInfo.appName = packageInfo.applicationInfo.loadLabel(mContext.getPackageManager()).toString();
            tmpInfo.packageName = packageInfo.packageName;
            tmpInfo.versionName = packageInfo.versionName;
            tmpInfo.versionCode = packageInfo.versionCode;
            tmpInfo.appIcon = packageInfo.applicationInfo.loadIcon(mContext.getPackageManager());
            //Only display the non-system app info
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                if (apps.contains(tmpInfo.packageName)) {
                    tmpInfo.state = true;
                }
                appList.add(tmpInfo);//如果非系统应用，则添加至appList
                tmpInfo.print();
            }
        }
        return appList;
    }
}
