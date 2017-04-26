package com.turing.fakeapk.fake;

import com.turing.fakeapk.Utils.KernelLogUtil;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.XposedHelpers.ClassNotFoundError;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class FakeRAM {
    public FakeRAM(LoadPackageParam sharePkgParam) {
        totalRam(sharePkgParam);
    }

    public void totalRam(LoadPackageParam loadPkgParam) {
        try {
            XposedHelpers.findAndHookMethod("com.blizzard.wtcg.hearthstone.MinSpecCheck", loadPkgParam.classLoader, "getTotalRAMMB", new XC_MethodHook() {

                @Override
                protected void afterHookedMethod(MethodHookParam param)
                        throws Throwable {
                    // TODO Auto-generated method stub
                    super.afterHookedMethod(param);
                    param.setResult(randomRam());
                }

            });
        } catch (ClassNotFoundError e) {
            KernelLogUtil.LogXposed("Fake totalRam ERROR: " + e.getMessage());
        }

    }

    private int randomRam() {
        return 3456543;
        //int[] Array = new int[]{AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, ItemAnimator.FLAG_MOVED, 3027, ItemAnimator.FLAG_APPEARED_IN_PRE_LAYOUT};
        //return Array[new Random().nextInt(Array.length)];
    }
}
