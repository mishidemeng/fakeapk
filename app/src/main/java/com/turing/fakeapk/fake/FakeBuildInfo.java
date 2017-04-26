package com.turing.fakeapk.fake;

import android.content.ContentResolver;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.Parcel;
import android.provider.Settings;
import android.text.TextUtils;
import android.webkit.WebView;

import com.turing.fakeapk.Utils.KernelLogUtil;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.util.Map;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.XposedHelpers.ClassNotFoundError;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class FakeBuildInfo {


    public FakeBuildInfo(LoadPackageParam sharePkgParam) {
        FakeGPS(sharePkgParam);
        FakeAndroidID(sharePkgParam);
        FakeAndroidSerial(sharePkgParam);
        FakeNetWorkInfo(sharePkgParam);
        FakeIMEI(sharePkgParam);
        FakeBaseBand(sharePkgParam);
        //FakeBuildProp(sharePkgParam);
        FakeJaveUserAgent(sharePkgParam);
        FakeUserAgent(sharePkgParam);
        FakeGoogleAdsID(sharePkgParam);
    }

    public void FakeNetWorkInfo(LoadPackageParam loadPkgParam) {
        try {
            XposedHelpers.findAndHookMethod("android.net.NetworkInfo", loadPkgParam.classLoader, "getType", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param)
                        throws Throwable {
                    // TODO Auto-generated method stub
                    super.beforeHookedMethod(param);
                    if (!TextUtils.isEmpty(SharedPref.getXValue("NetWorkType"))) {
                        param.setResult(Integer.parseInt(SharedPref.getXValue("NetWorkType")));
                    }
                }
            });
            XposedHelpers.findAndHookMethod("android.net.NetworkInfo", loadPkgParam.classLoader, "getTypeName", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param)
                        throws Throwable {
                    // TODO Auto-generated method stub
                    super.beforeHookedMethod(param);
                    if (!TextUtils.isEmpty(SharedPref.getXValue("NetWorkTypeName"))) {
                        param.setResult(SharedPref.getXValue("NetWorkTypeName"));
                    }
                }

            });
            XposedHelpers.findAndHookMethod("android.net.NetworkInfo", loadPkgParam.classLoader, "getSubtype", new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param)
                        throws Throwable {
                    // TODO Auto-generated method stub
                    super.beforeHookedMethod(param);
                    if (!TextUtils.isEmpty(SharedPref.getXValue("NetWorkSubType"))) {
                        param.setResult(Integer.parseInt(SharedPref.getXValue("NetWorkSubType")));
                    }
                }

            });

            XposedHelpers.findAndHookMethod("android.net.NetworkInfo", loadPkgParam.classLoader, "getSubtypeName", new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param)
                        throws Throwable {
                    // TODO Auto-generated method stub
                    super.beforeHookedMethod(param);
                    if (!TextUtils.isEmpty(SharedPref.getXValue("NetWorkSubTypeName"))) {
                        param.setResult(SharedPref.getXValue("NetWorkSubTypeName"));
                    }
                }

            });
        } catch (Exception e) {
            KernelLogUtil.LogXposed("Fake FakeNetWorkInfo ERROR: " + e.getMessage());
        }
    }

    public void FakeUserAgent(LoadPackageParam loadPkgParam) {
        try {
            XposedHelpers.findAndHookMethod("com.android.webview.chromium.ContentSettingsAdapter", loadPkgParam.classLoader, "setUserAgentString", String.class, new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param)
                        throws Throwable {
                    // TODO Auto-generated method stub
                    super.beforeHookedMethod(param);
                    if (!TextUtils.isEmpty(SharedPref.getXValue("UserAgent"))) {
                        param.args[0] = SharedPref.getXValue("UserAgent");
                    }
                }

            });
        } catch (ClassNotFoundError e) {
            KernelLogUtil.LogXposed("Fake UA ERROR: " + e.getMessage());
        }

        try {
            Method loadUrl1 = WebView.class.getDeclaredMethod("loadUrl", new Class[]{String.class});
            Method loadUrl2 = WebView.class.getDeclaredMethod("loadUrl", new Class[]{String.class, Map.class});

            XposedBridge.hookMethod(loadUrl1, new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param)
                        throws Throwable {
                    // TODO Auto-generated method stub
                    super.beforeHookedMethod(param);
                    KernelLogUtil.LogXposed("Load Url: " + param.args[0]);
                    if (param.args.length > 0 && (param.thisObject instanceof WebView)) {
                        String ua = SharedPref.getXValue("UserAgent");
                        WebView webView = (WebView) param.thisObject;
                        if (webView.getSettings() != null) {
                            webView.getSettings().setUserAgentString(ua);
                        }
                    }
                }

            });
            XposedBridge.hookMethod(loadUrl2, new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param)
                        throws Throwable {
                    // TODO Auto-generated method stub
                    super.beforeHookedMethod(param);
                    KernelLogUtil.LogXposed("load url: " + param.args[0]);
                    if (param.args.length > 0 && (param.thisObject instanceof WebView)) {
                        if (!TextUtils.isEmpty(SharedPref.getXValue("UserAgent"))) {
                            String ua = SharedPref.getXValue("UserAgent");
                            WebView webView = (WebView) param.thisObject;
                            if (webView.getSettings() != null) {
                                webView.getSettings().setUserAgentString(ua);
                            }
                        }
                    }
                }

            });

        } catch (Exception e) {
            KernelLogUtil.LogXposed("Fake User Agent ERROR: " + e.getMessage());
        }
        //}
    }

    private static <T> T unsafeCast(Object o) {
        return (T) o;
    }

    public void FakeJaveUserAgent(LoadPackageParam param) {
        KernelLogUtil.LogXposed("FakeJaveUserAgent --- 0");
        XposedHelpers.findAndHookMethod("java.net.URL", param.classLoader, "openConnection", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                String ua = "Dalvik/1.6.0 (Linux; U; Android 4.2.2; xiaomi Build/KUANG)";
                HttpURLConnection cnc = unsafeCast(param.getResult());
                KernelLogUtil.LogXposed("FakeJaveUserAgent --- before" + cnc.getRequestProperty("user-agent"));

                if (!TextUtils.isEmpty(SharedPref.getXValue("UserAgent"))) {
                    cnc.setRequestProperty("user-agent", SharedPref.getXValue("UserAgent"));
                } else {
                    cnc.setRequestProperty("user-agent", ua);
                }
                KernelLogUtil.LogXposed("FakeJaveUserAgent --- after" + cnc.getRequestProperty("user-agent"));
            }
        });
    }

    public void FakeGPS(LoadPackageParam loadPkgParam) {
        try {

            XposedHelpers.findAndHookMethod("android.location.Location", loadPkgParam.classLoader, "getLatitude", new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param)
                        throws Throwable {
                    // TODO Auto-generated method stub
                    super.beforeHookedMethod(param);
                    KernelLogUtil.LogXposed("getLatitude=" + SharedPref.getXValue("Latitude"));
                    if (!TextUtils.isEmpty(SharedPref.getXValue("Latitude"))) {
                        param.setResult(Float.valueOf(Float.parseFloat(SharedPref.getXValue("Latitude"))));
                    }
                }

            });
            XposedHelpers.findAndHookMethod("android.location.Location", loadPkgParam.classLoader, "getLongitude", new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param)
                        throws Throwable {
                    // TODO Auto-generated method stub
                    super.beforeHookedMethod(param);
                    KernelLogUtil.LogXposed("getLongitude=" + SharedPref.getXValue("Longitude"));
                    if (!TextUtils.isEmpty(SharedPref.getXValue("Longitude"))) {
                        param.setResult(Float.valueOf(Float.parseFloat(SharedPref.getXValue("Longitude"))));
                    }
                }

            });
            XposedHelpers.findAndHookMethod("android.location.Location", loadPkgParam.classLoader, "getAccuracy", new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param)
                        throws Throwable {
                    // TODO Auto-generated method stub
                    super.beforeHookedMethod(param);
                    KernelLogUtil.LogXposed("getAccuracy=" + SharedPref.getXValue("Alt"));
                    if (!TextUtils.isEmpty(SharedPref.getXValue("Alt"))) {
                        param.setResult(Float.valueOf(Float.parseFloat(SharedPref.getXValue("Alt"))));
                    }
                }

            });
            XposedHelpers.findAndHookMethod("android.location.Location", loadPkgParam.classLoader, "getAltitude", new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param)
                        throws Throwable {
                    // TODO Auto-generated method stub
                    super.beforeHookedMethod(param);
                    KernelLogUtil.LogXposed("getAltitude=" + SharedPref.getXValue("Alt"));
                    if (!TextUtils.isEmpty(SharedPref.getXValue("Alt"))) {
                        param.setResult(Float.valueOf(Float.parseFloat(SharedPref.getXValue("Alt"))));
                    }
                }

            });
            XposedHelpers.findAndHookMethod("android.location.Location", loadPkgParam.classLoader, "getSpeed", new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param)
                        throws Throwable {
                    // TODO Auto-generated method stub
                    super.beforeHookedMethod(param);
                    KernelLogUtil.LogXposed("getSpeed=" + SharedPref.getXValue("Speed"));
                    if (!TextUtils.isEmpty(SharedPref.getXValue("Speed"))) {
                        param.setResult(Float.valueOf(Float.parseFloat(SharedPref.getXValue("Speed"))));
                    }
                }

            });


        } catch (Exception e) {
            KernelLogUtil.LogXposed("Fake GPS ERROR: " + e.getMessage());
        }
    }

    public void FakeAndroidID(LoadPackageParam loadPkgParam) {
        try {
            XposedHelpers.findAndHookMethod("android.provider.Settings.Secure", loadPkgParam.classLoader, "getString", ContentResolver.class, String.class, new XC_MethodHook() {

                @Override
                protected void afterHookedMethod(MethodHookParam param)
                        throws Throwable {

                    if (param.args[1].equals(Settings.Secure.ANDROID_ID)) {
                        if (!TextUtils.isEmpty(SharedPref.getXValue("AndroidID"))) {
                            param.setResult(SharedPref.getXValue("AndroidID"));
                        }
                    }
                }
            });

        } catch (Exception ex) {
            KernelLogUtil.LogXposed("Fake Android ID ERROR: " + ex.getMessage());
        }
    }

    public void FakeAndroidSerial(LoadPackageParam loadPkgParam) {
        try {
            Class<?> classBuild = XposedHelpers.findClass("android.os.Build",
                    loadPkgParam.classLoader);
            XposedHelpers.setStaticObjectField(classBuild, "SERIAL",
                    SharedPref.getXValue("AndroidSerial"));
            Class<?> classSysProp = Class
                    .forName("android.os.SystemProperties");
            XposedHelpers.findAndHookMethod(classSysProp, "get", String.class,
                    new XC_MethodHook() {

                        @Override
                        protected void afterHookedMethod(MethodHookParam param)
                                throws Throwable {
                            // TODO Auto-generated method stub
                            super.afterHookedMethod(param);

                            String serialno = (String) param.args[0];
                            if (serialno.equals("ro.serialno")
                                    || serialno.equals("ro.boot.serialno")
                                    || serialno.equals("ril.serialnumber")
                                    || serialno.equals("sys.serialnumber")) {
                                param.setResult(SharedPref.getXValue("AndroidSerial"));
                            }
                        }

                    });
            XposedHelpers.findAndHookMethod(classSysProp, "get", String.class,
                    String.class, new XC_MethodHook() {

                        @Override
                        protected void afterHookedMethod(MethodHookParam param)
                                throws Throwable {
                            // TODO Auto-generated method stub
                            super.afterHookedMethod(param);

                            String serialno = (String) param.args[0];
                            if (serialno.equals("ro.serialno")
                                    || serialno.equals("ro.boot.serialno")
                                    || serialno.equals("ril.serialnumber")
                                    || serialno.equals("sys.serialnumber")) {
                                param.setResult(SharedPref.getXValue("AndroidSerial"));
                            }
                        }

                    });
            return;

        } catch (IllegalArgumentException ex) {
            KernelLogUtil.LogXposed("Fake AndroidSerial ERROR: " + ex.getMessage());
            return;
        } catch (ClassNotFoundException ex) {
            KernelLogUtil.LogXposed("Fake AndroidSerial ERROR: " + ex.getMessage());
        }
    }

    public void FakeIMEI(LoadPackageParam loadPkgParam) {
        try {
            XposedHelpers.findAndHookMethod("android.telephony.TelephonyManager", loadPkgParam.classLoader, "getDeviceId", XC_MethodReplacement.returnConstant(SharedPref.getXValue("IMEI")));
            XposedHelpers.findAndHookMethod("com.android.internal.telephony.PhoneSubInfo", loadPkgParam.classLoader, "getDeviceId", XC_MethodReplacement.returnConstant(SharedPref.getXValue("IMEI")));

            if (VERSION.SDK_INT < 22) {
                XposedHelpers.findAndHookMethod("com.android.internal.telephony.gsm.GSMPhone", loadPkgParam.classLoader, "getDeviceId", XC_MethodReplacement.returnConstant(SharedPref.getXValue("IMEI")));
                XposedHelpers.findAndHookMethod("com.android.internal.telephony.PhoneProxy", loadPkgParam.classLoader, "getDeviceId", XC_MethodReplacement.returnConstant(SharedPref.getXValue("IMEI")));
            }
        } catch (Exception ex) {
            KernelLogUtil.LogXposed("Fake IMEI ERROR: " + ex.getMessage());
        }
    }

    public void FakeGoogleAdsID(LoadPackageParam loadPkgParam) {
        try {
            XposedHelpers.findAndHookMethod("android.os.Binder", loadPkgParam.classLoader, "execTransact", Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param)
                        throws Throwable {

                    super.beforeHookedMethod(param);
                    if (((IBinder) param.thisObject)
                            .getInterfaceDescriptor()
                            .equals("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService")
                            && ((Integer) param.args[0]).intValue() == 1) {
                        Parcel reply = null;
                        try {

                            Method methodObtain = Parcel.class.getDeclaredMethod("obtain", VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP ? int.class : long.class);
                            methodObtain.setAccessible(true);
                            reply = (Parcel) methodObtain.invoke(null,
                                    param.args[2]);
                        } catch (NoSuchMethodException ex) {
                            KernelLogUtil.LogXposed("Fake Google Ads NoSuchMethodException ERROR: " + ex.getMessage());
                        } catch (NullPointerException e) {
                            KernelLogUtil.LogXposed("Fake Google Ads NullPointerException ERROR: " + e.getMessage());
                        }
                        if (reply == null) {

                        } else {
                            reply.setDataPosition(0);
                            reply.writeNoException();
                            reply.writeString(SharedPref.getXValue("GoogleAdsID"));
                        }

                        param.setResult(Boolean.valueOf(true));
                    }

                }

            });

        } catch (Exception ex) {
            KernelLogUtil.LogXposed("Fake Google Ads ID ERROR: " + ex.getMessage());
        }
    }

    public void FakeBaseBand(LoadPackageParam loadPkgParam) {
        try {
            if (VERSION.SDK_INT <= 14) {
                Class<?> classBuild = XposedHelpers.findClass(
                        "android.os.Build", loadPkgParam.classLoader);
                XposedHelpers.setStaticObjectField(classBuild, "RADIO", SharedPref.getXValue("BaseBand"));
            } else {
                XposedHelpers.findAndHookMethod("android.os.Build",
                        loadPkgParam.classLoader, "getRadioVersion", new XC_MethodHook() {

                            @Override
                            protected void afterHookedMethod(MethodHookParam param)
                                    throws Throwable {
                                param.setResult(SharedPref.getXValue("BaseBand"));
                            }

                        });
            }
        } catch (Exception e) {
            KernelLogUtil.LogXposed("Fake BaseBand ERROR: " + e.getMessage());
        }


    }

    public void FakeBuildProp(LoadPackageParam loadPkgParam) {
        try {
            XposedHelpers.findField(Build.class, "BOARD").set(null, SharedPref.getXValue("BOARD"));
            XposedHelpers.findField(Build.class, "BRAND").set(null, SharedPref.getXValue("BRAND"));
            XposedHelpers.findField(Build.class, "CPU_ABI").set(null, SharedPref.getXValue("ABI"));
            XposedHelpers.findField(Build.class, "CPU_ABI2").set(null, SharedPref.getXValue("ABI2"));
            XposedHelpers.findField(Build.class, "DEVICE").set(null, SharedPref.getXValue("DEVICE"));
            XposedHelpers.findField(Build.class, "DISPLAY").set(null, SharedPref.getXValue("DISPLAY"));
            XposedHelpers.findField(Build.class, "FINGERPRINT").set(null, SharedPref.getXValue("FINGERPRINT"));
            XposedHelpers.findField(Build.class, "HARDWARE").set(null, SharedPref.getXValue("NAME"));
            XposedHelpers.findField(Build.class, "ID").set(null, SharedPref.getXValue("ID"));
            XposedHelpers.findField(Build.class, "MANUFACTURER").set(null, SharedPref.getXValue("Manufacture"));
            XposedHelpers.findField(Build.class, "MODEL").set(null, SharedPref.getXValue("MODEL"));
            XposedHelpers.findField(Build.class, "PRODUCT").set(null, SharedPref.getXValue("DEVICE"));
            XposedHelpers.findField(Build.class, "BOOTLOADER").set(null, SharedPref.getXValue("BOOTLOADER"));
            XposedHelpers.findField(Build.class, "HOST").set(null, SharedPref.getXValue("HOST"));
            XposedHelpers.findField(VERSION.class, "INCREMENTAL").set(null, SharedPref.getXValue("BOOTLOADER"));
            XposedHelpers.findField(VERSION.class, "RELEASE").set(null, SharedPref.getXValue("AndroidVer"));
            XposedHelpers.findField(VERSION.class, "SDK").set(null, SharedPref.getXValue("API"));
            XposedHelpers.findField(VERSION.class, "CODENAME").set(null, "REL");

        } catch (IllegalAccessException e) {
            KernelLogUtil.LogXposed("Fake BuilProp ERROR: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            KernelLogUtil.LogXposed("Fake BuilProp ERROR: " + e.getMessage());
        }

        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            if (cls != null) {
                for (Member mem : cls.getDeclaredMethods()) {
                    XposedBridge.hookMethod(mem, new XC_MethodHook() {

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param)
                                throws Throwable {
                            // TODO Auto-generated method stub
                            super.beforeHookedMethod(param);

                            if (param.args.length > 0 && param.args[0] != null && param.args[0].equals("ro.build.description")) {
                                param.setResult(SharedPref.getXValue("DESCRIPTION"));
                            }
                        }
                    });
                }
            }

        } catch (ClassNotFoundException e) {
            KernelLogUtil.LogXposed("Fake DESCRIPTION ERROR: " + e.getMessage());
        }
    }
}
