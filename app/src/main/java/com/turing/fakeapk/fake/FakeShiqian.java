package com.turing.fakeapk.fake;

import com.turing.fakeapk.Utils.KernelLogUtil;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by turingkuang on 2017/3/23.
 */
public class FakeShiqian {

    public void FakeMainActivity(XC_LoadPackage.LoadPackageParam loadPkgParam) {
        try {
            XposedHelpers.findAndHookMethod("com.turing.fakeapk.Activity", loadPkgParam.classLoader, "getResult", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param)
                        throws Throwable {
                    // TODO Auto-generated method stub
                    super.beforeHookedMethod(param);
                    param.setResult(1);
                    KernelLogUtil.LogXposed("Fake com.turing.fakeapk.Activity: " + param.getResult());
                }
            });
        } catch (Exception e) {
            KernelLogUtil.LogXposed("Fake FakeNetWorkInfo ERROR: " + e.getMessage());
        }
    }
}
