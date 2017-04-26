package com.turing.fakeapk.service;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.turing.fakeapk.Utils.PropertiesUtils;
import com.turing.fakeapk.bean.ChangeDeviceInfo;

import java.io.DataOutputStream;
import java.util.Properties;

/**
 * Created by turingkuang on 2017/3/15.
 */
public class PropertiesMng {
    private static PropertiesMng mng = new PropertiesMng();
    private Context mContext = null;
    private Properties prop;
    private String buildPropPath = "/mnt/sdcard/TouchSprite/lua/json/build.prop";

    public static PropertiesMng getInstance() {
        if (null == mng)
            return new PropertiesMng();
        return mng;
    }

    public void init(Context cnt) {
        if (null == mContext) {
            mContext = cnt;
        }
    }

    private void transferFileToSystem(String path) {
        Process process = null;
        DataOutputStream os = null;

        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("mount -o remount,rw -t yaffs2 /dev/block/mtdblock4 /system\n");
            os.writeBytes("mv -f /system/build.prop /system/build.prop.bak\n");
            os.writeBytes("busybox cp -f " + path + " /system/build.prop\n");
            os.writeBytes("chmod 644 /system/build.prop\n");
            //os.writeBytes("mount -o remount,ro -t yaffs2 /dev/block/mtdblock4 /system\n");
            //os.writeBytes("rm " + propReplaceFile);
            //os.writeBytes("rm " + tempFile);
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            Toast.makeText(mContext, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
                Toast.makeText(mContext, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        Toast.makeText(mContext, "Edit saved and a backup was made at " + Environment.getExternalStorageDirectory().getAbsolutePath() + "/build.prop.bak", Toast.LENGTH_SHORT).show();
    }

    public void modifyBuildPro(ChangeDeviceInfo info) {
        boolean b = false;
        String s = "";
        int i = 0;
        prop = PropertiesUtils.loadConfig(mContext, buildPropPath);
        if (prop == null) {//配置文件不存在的时候创建配置文件 初始化配置信息
            prop = new Properties();
        }
        try {
            prop.put("ro.build.id", info.getBuildInfo().getId());
            prop.put("ro.build.display.id", info.getBuildInfo().getIncrement());
            prop.put("ro.product.locale.language", "zh");
            prop.put("ro.product.locale.region", "CN");
            prop.put("ro.wifi.channels", info.getSsid());
            prop.put("ro.build.version.incremental", info.getBuildInfo().getIncrement());
            prop.put("ro.build.version.sdk", info.getBuildInfo().getSdk());
            prop.put("ro.build.version.codename", "REL");
            prop.put("ro.build.version.release", info.getBuildInfo().getAndroidVersion());
            prop.put("ro.build.type", info.getBuildInfo().getType());
            prop.put("ro.build.user", info.getBuildInfo().getUser());
            prop.put("ro.build.host", info.getBuildInfo().getHost());
            prop.put("ro.build.tags", info.getBuildInfo().getTags());
            prop.put("ro.product.brand", info.getBuildInfo().getBrand());
            prop.put("ro.product.name", info.getBuildInfo().getDevice());
            prop.put("ro.product.device", info.getBuildInfo().getDevice());
            prop.put("ro.product.board", info.getBuildInfo().getBoard());
            prop.put("ro.product.cpu.abi", info.getBuildInfo().getCpu_abi());
            prop.put("ro.product.cpu.abi2", info.getBuildInfo().getCpu_abi());
            prop.put("ro.build.product", info.getBuildInfo().getProduct());
            prop.put("ro.build.description", info.getBuildInfo().getFingerPrint());
            prop.put("ro.build.fingerprint", info.getBuildInfo().getFingerPrint());
            prop.put("ro.product.model", info.getBuildInfo().getModel());
            PropertiesUtils.saveConfig(mContext, buildPropPath, prop);
            //transferFileToSystem(buildPropPath);
            //Runtime.getRuntime().exec("mount -o rw,remount -t yaffs2 /dev/block/mtdblock3 /system");
            //Runtime.getRuntime().exec("su -c cp /system/build.prop  /system/build1.prop");
            //Runtime.getRuntime().exec("su -c cp /mnt/sdcard/TouchSprite/lua/json/build.prop  /system/build.prop");
            //Runtime.getRuntime().exec("chmod 644 /system/build.prop");
            //ToastUtils.getInstance().showLong("copy build.prop");
        } catch (Exception e) {
            Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
