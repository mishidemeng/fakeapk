package com.turing.fakeapk.fake;

import android.content.pm.ApplicationInfo;
import com.turing.fakeapk.fake.FakeBattery;
import com.turing.fakeapk.fake.FakeBuildInfo;
import com.turing.fakeapk.fake.FakeCPU;
import com.turing.fakeapk.fake.FakeEmail;
import com.turing.fakeapk.fake.FakeHardwareInfo;
import com.turing.fakeapk.fake.FakeLocation;
import com.turing.fakeapk.fake.FakeOpenGL;
import com.turing.fakeapk.fake.FakeRAM;
import com.turing.fakeapk.fake.RootCloak;
import com.turing.fakeapk.fake.SystemPropertiesHook;
import com.turing.fakeapk.Utils.KernelLogUtil;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class MainHook implements IXposedHookLoadPackage {

    //判断应用程序是否是用户程序
    public static boolean filterApp(XC_LoadPackage.LoadPackageParam info) {
        //原来是系统应用，用户手动升级
        //KernelLogUtil.LogXposed("XModule -- 包名" +  info.packageName);
        if (info.packageName.equals("com.touchsprite.android") ||
                info.packageName.contains("xposed")) {
            return false;
        }
        if (null == info || info.appInfo == null) {
            KernelLogUtil.LogXposed("XModule -- info.appInfo 为空");
            return false;
        }
        KernelLogUtil.LogXposed("XModule -- 进程名" + info.appInfo.processName);
        if ((info.appInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
            return true;
            //用户自己安装的应用程序
        } else if ((info.appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void handleLoadPackage(LoadPackageParam sharePkgParam) throws Throwable {
        // TODO Auto-generated method stub
        if (filterApp(sharePkgParam)) {
            SystemPropertiesHook systemPropertiesHook = new SystemPropertiesHook();
            //systemProperties hook
            XposedHelpers.findAndHookMethod("android.os.SystemProperties", sharePkgParam.classLoader, "get", String.class, String.class, systemPropertiesHook);
            new FakeBattery().fakePinStt(sharePkgParam);
            new FakeHardwareInfo(sharePkgParam);
            new FakeBuildInfo(sharePkgParam);
            new FakeOpenGL(sharePkgParam);
            new FakeEmail().fakeGmail(sharePkgParam);
            new RootCloak().handleLoadPackage(sharePkgParam);
            new FakeCPU(sharePkgParam);
            new FakeRAM(sharePkgParam);
            new FakeLocation().handleLoadPackage(sharePkgParam);
            //new FakeShiqian().FakeMainActivity(sharePkgParam);
        }
    }
}
