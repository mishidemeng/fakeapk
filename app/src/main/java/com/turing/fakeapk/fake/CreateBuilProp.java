package com.turing.fakeapk.fake;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.turing.fakeapk.bean.ChangeDeviceInfo;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

@SuppressLint("ShowToast")
public class CreateBuilProp {
    ChangeDeviceInfo device;
    Context myContext;

    public CreateBuilProp(Context ctx, ChangeDeviceInfo d) {
        this.myContext = ctx;
        this.device = d;
    }

    public void newBuild() {
        try {
            Writer out = new OutputStreamWriter(new FileOutputStream("/mnt/sdcard/TouchSprite/lua/json/build.prop"));
            out.write(new BuilInfo(this.device).CreateBuild());
            out.close();

            Runtime.getRuntime().exec("su -c cp data/data/org.tencent.android.kerne/build.prop.new  system/build.prop");
        } catch (Exception e) {
            Toast.makeText(this.myContext, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
