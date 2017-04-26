package com.turing.fakeapk.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by turingkuang on 2017/1/6.
 */
public class UserAgentUtil {
    private Context mContext;
    private String uri;
    private byte[] picByte;
    private String javaUserAgent = "";
    private static UserAgentUtil mUserAgent = new UserAgentUtil();

    public static UserAgentUtil getInstance() {
        if (null == mUserAgent)
            return new UserAgentUtil();

        return mUserAgent;
    }

    public String getChromeUserAgent(Context cnt) {
        //WebView view = new WebView(cnt);
        //chromeUserAgent = view.getSettings().getUserAgentString();
        //view.destroy();
        return "";
    }

    private String generateUserAgent(String version, String brand) {
        String[] pre = {"Dalvik/1.6.0", "Dalvik/1.4.0"};
        int index = (int) Math.random() * 10;
        if (index > 4) {
            return pre[0] + "(Linux; U; " + "version" + "; " + brand + " Build/JDQ39E";
        } else {
            return pre[1] + "(Linux; U; " + "version" + "; " + brand + " Build/JDQ39E";
        }
    }

    public void getPicture(String uri) {
        this.uri = uri;
        new Thread(runnable).start();
    }

    public String getJavaUserAgent() {
        getPicture("http://mat1.gtimg.com/www/images/qq2012/qqlogo_1x.png");
        try {
            Thread.sleep(1000);
            KernelLogUtil.LogTuring("getJavaUserAgent -- " + javaUserAgent);
            return javaUserAgent;
        } catch (Exception e) {
        }
        return javaUserAgent;
    }

    @SuppressLint("HandlerLeak")
    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
            }
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL(uri);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                javaUserAgent = conn.getRequestProperty("user-agent");
                //KernelLogUtil.LogTuring("runnable -- " + javaUserAgent );
                conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}
