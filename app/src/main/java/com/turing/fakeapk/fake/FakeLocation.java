package com.turing.fakeapk.fake;
/**
 * Created by turingkuang on 2017/3/13.
 */

import android.content.Context;
import android.text.TextUtils;

import com.turing.fakeapk.Utils.KernelLogUtil;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by 蔡小木 on 2016/4/17 0017.
 */
public class FakeLocation implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        final Object activityThread = XposedHelpers.callStaticMethod(XposedHelpers.findClass("android.app.ActivityThread", null), "currentActivityThread");
        final Context systemContext = (Context) XposedHelpers.callMethod(activityThread, "getSystemContext");
        double latitude = 0.0;
        double longitude = 0.0;
        if (!TextUtils.isEmpty(SharedPref.getXValue("Latitude"))) {
            latitude = Double.parseDouble(SharedPref.getXValue("Latitude"));
            longitude = Double.parseDouble(SharedPref.getXValue("Longitude"));
        }
        int lac = 10;
        int cid = 10;
        KernelLogUtil.LogXposed("模拟位置:" + loadPackageParam.packageName + "," + latitude + "," + longitude + "," + lac + "," + cid);
        LocationHookUtils.HookAndChange(loadPackageParam.classLoader, latitude, longitude, lac, cid);
    }
}
