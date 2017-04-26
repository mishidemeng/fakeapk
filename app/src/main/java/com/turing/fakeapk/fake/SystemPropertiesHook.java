package com.turing.fakeapk.fake;

import android.text.TextUtils;


import com.turing.fakeapk.Utils.KernelLogUtil;

import java.lang.reflect.Field;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by turingkuang on 2017/1/9.
 */
public class SystemPropertiesHook extends XC_MethodHook {
    public SystemPropertiesHook() {
        super();
    }

    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        String methodName = param.method.getName();
        if (methodName.startsWith("get")) {
            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.HARDWARE"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.HARDWARE- " + SharedPref.getXValue("Build.HARDWARE"));
                XposedHelpers.setStaticObjectField(android.os.Build.class, "HARDWARE", SharedPref.getXValue("Build.HARDWARE"));
            }

            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.PRODUCT"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.PRODUCT- " + SharedPref.getXValue("Build.PRODUCT"));
                XposedHelpers.setStaticObjectField(android.os.Build.class, "PRODUCT", SharedPref.getXValue("Build.PRODUCT"));
            }

            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.DEVICE"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.DEVICE- " + SharedPref.getXValue("Build.DEVICE"));
                XposedHelpers.setStaticObjectField(android.os.Build.class, "DEVICE", SharedPref.getXValue("Build.DEVICE"));
            }

            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.CPU_ABI"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.CPU_ABI- " + SharedPref.getXValue("Build.CPU_ABI"));
                XposedHelpers.setStaticObjectField(android.os.Build.class, "CPU_ABI", SharedPref.getXValue("Build.CPU_ABI"));
            }


            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.MODEL"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.MODEL- " + SharedPref.getXValue("Build.MODEL"));
                XposedHelpers.setStaticObjectField(android.os.Build.class, "MODEL", SharedPref.getXValue("Build.MODEL"));
            }

            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.MANUFACTURER"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.MANUFACTURER- " + SharedPref.getXValue("Build.MANUFACTURER"));
                XposedHelpers.setStaticObjectField(android.os.Build.class, "MANUFACTURER", SharedPref.getXValue("Build.MANUFACTURER"));
            }

            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.BOARD"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.BOARD- " + SharedPref.getXValue("Build.BOARD"));
                XposedHelpers.setStaticObjectField(android.os.Build.class, "BOARD", SharedPref.getXValue("Build.BOARD"));
            }

            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.BRAND"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.BRAND- " + SharedPref.getXValue("Build.BRAND"));
                XposedHelpers.setStaticObjectField(android.os.Build.class, "BRAND", SharedPref.getXValue("Build.BRAND"));
            }

            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.DISPLAY"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.DISPLAY- " + SharedPref.getXValue("Build.DISPLAY"));
                XposedHelpers.setStaticObjectField(android.os.Build.class, "DISPLAY", SharedPref.getXValue("Build.DISPLAY"));
            }

            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.HOST"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.HOST- " + SharedPref.getXValue("Build.HOST"));
                XposedHelpers.setStaticObjectField(android.os.Build.class, "HOST", SharedPref.getXValue("Build.HOST"));
            }

            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.FINGERPRINT"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.FINGERPRINT- " + SharedPref.getXValue("Build.FINGERPRINT"));
                XposedHelpers.setStaticObjectField(android.os.Build.class, "FINGERPRINT", SharedPref.getXValue("Build.FINGERPRINT"));
            }

            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.BOOTLOADER"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.BOOTLOADER- " + SharedPref.getXValue("Build.BOOTLOADER"));
                XposedHelpers.setStaticObjectField(android.os.Build.class, "BOOTLOADER", SharedPref.getXValue("Build.BOOTLOADER"));
            }

            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.TAGS"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.TAGS- " + SharedPref.getXValue("Build.TAGS"));
                XposedHelpers.setStaticObjectField(android.os.Build.class, "TAGS", SharedPref.getXValue("Build.TAGS"));
            }
            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.TYPE"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.TYPE- " + SharedPref.getXValue("Build.TYPE"));
                XposedHelpers.setStaticObjectField(android.os.Build.class, "TYPE", SharedPref.getXValue("Build.TYPE"));
            }
            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.SERIAL"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.SERIAL- " + SharedPref.getXValue("Build.SERIAL"));
                XposedHelpers.setStaticObjectField(android.os.Build.class, "SERIAL", SharedPref.getXValue("Build.SERIAL"));
            }
            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.USER"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.USER- " + SharedPref.getXValue("Build.USER"));
                XposedHelpers.setStaticObjectField(android.os.Build.class, "USER", SharedPref.getXValue("Build.USER"));
            }
            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.ID"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.ID- " + SharedPref.getXValue("Build.ID"));
                XposedHelpers.setStaticObjectField(android.os.Build.class, "ID", SharedPref.getXValue("Build.ID"));
            }
            // ------------------------------------------------------------------------------------------------------------
            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.VERSION.RELEASE"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.VERSION.RELEASE- " + SharedPref.getXValue("Build.VERSION.RELEASE"));
                XposedHelpers.setStaticObjectField(android.os.Build.VERSION.class, "RELEASE", SharedPref.getXValue("Build.VERSION.RELEASE"));
            }

            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.VERSION.INCREMENTAL"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.VERSION.INCREMENTAL- " + SharedPref.getXValue("Build.VERSION.INCREMENTAL"));
                XposedHelpers.setStaticObjectField(android.os.Build.VERSION.class, "INCREMENTAL", SharedPref.getXValue("Build.VERSION.INCREMENTAL"));
            }

            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.VERSION.API"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.VERSION.API- " + SharedPref.getXValue("Build.VERSION.API"));
                XposedHelpers.setStaticObjectField(android.os.Build.VERSION.class, "SDK", SharedPref.getXValue("Build.VERSION.API"));
                XposedHelpers.setStaticObjectField(android.os.Build.VERSION.class, "SDK_INT", Integer.valueOf(SharedPref.getXValue("Build.VERSION.API")));
            }

            if (!TextUtils.isEmpty(SharedPref.getXValue("Build.VERSION.CODENAME"))) {
                KernelLogUtil.LogXposed("SystemPropertiesHook -Build.VERSION.CODENAME- " + SharedPref.getXValue("Build.VERSION.CODENAME"));
                XposedHelpers.setStaticObjectField(android.os.Build.VERSION.class, "CODENAME", SharedPref.getXValue("Build.VERSION.CODENAME"));
            }
        }
    }

    private boolean isOK(String str) {
        if (null == str || str.equals(""))
            return false;

        return true;
    }

    private void setStaticObjectField(Class<?> cls, String field, String value) {
        XposedHelpers.setStaticObjectField(cls, field, value);
        try {
            Field fd = cls.getDeclaredField(field);
            fd.setAccessible(true);
        } catch (Exception e) {
            KernelLogUtil.LogXposed("setStaticObjectField -error" + e.toString());
        }
    }
}