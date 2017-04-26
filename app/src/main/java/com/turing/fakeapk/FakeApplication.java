package com.turing.fakeapk;

import android.app.Application;

import org.xutils.x;

/**
 * Created by wyouflf on 15/10/28.
 */
public class FakeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        //x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能
        // 信任所有https域名
        /*HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });*/
    }
}
