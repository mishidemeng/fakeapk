package com.turing.fakeapk.service;

import android.content.Context;
import android.widget.Toast;

import com.turing.fakeapk.Utils.AESUtils;
import com.turing.fakeapk.bean.PGResponse;
import com.turing.fakeapk.listen.NetListen;
import com.turing.fakeapk.location.BaiduPoint;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by turingkuang on 2017/2/28.
 */
public class HttpMng {
    private final String host = "http://pangu.u-app.cn/common/";
    //private final String host = "http://192.168.2.149:8080/common/";
    private Context mContext = null;
    private List<NetListen> list = new ArrayList<NetListen>();

    public void setNetListen(NetListen lst) {
        list.add(lst);
    }

    private static HttpMng mInstance = new HttpMng();

    public static HttpMng getInstance() {
        if (null == mInstance) {
            mInstance = new HttpMng();
        }
        return mInstance;
    }

    public void setContext(Context cnt) {
        if (null == mContext) {
            mContext = cnt;
        }
    }

    //http://api.map.baidu.com/location/ip?ak=WjjGWy6piQ7XuOcjGCwx3slTErxhOr7g&coor=bd09ll
    public BaiduPoint getLocation() {
        // 先取Ip ,再取经纬度
        String serverURL = "http://api.map.baidu.com/location/ip?ak=WjjGWy6piQ7XuOcjGCwx3slTErxhOr7g&coor=bd09ll";
        return null;
    }

    public void login(String user, String password) {
        String ret = null;
        PGResponse<String> rsp = null;
        String userAndPass = user + "," + password;
        String desKey = AESUtils.encryptToBase64(userAndPass);
        String serverURL = host + "login.pangu?info=" + desKey;
        RequestParams params = new RequestParams(serverURL);
        Callback.Cancelable cancelable
                = x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        for (NetListen lst : list) {
                            lst.callLogin(result);
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                        if (ex instanceof HttpException) { // 网络错误
                            HttpException httpEx = (HttpException) ex;
                            int responseCode = httpEx.getCode();
                            String responseMsg = httpEx.getMessage();
                            String errorResult = httpEx.getResult();
                            // ...
                        } else { // 其他错误
                            // ...
                        }
                        for (NetListen lst : list) {
                            lst.callLogin(null);
                        }
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        for (NetListen lst : list) {
                            lst.callLogin(null);
                        }
                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }

    public void getFakeApkInfo() {
        String token = AESUtils.encryptToBase64(DataMng.getInstance().getLoginToken());
        String serverURL = host + "getFakeApkInfo.pangu?info=" + token;
        RequestParams params = new RequestParams(serverURL);
        Callback.Cancelable cancelable
                = x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        for (NetListen lst : list) {
                            lst.callBackGetFakeApkInfo(result);
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                        if (ex instanceof HttpException) { // 网络错误
                            HttpException httpEx = (HttpException) ex;
                            int responseCode = httpEx.getCode();
                            String responseMsg = httpEx.getMessage();
                            String errorResult = httpEx.getResult();
                            // ...
                        } else { // 其他错误
                            // ...
                        }
                        for (NetListen lst : list) {
                            lst.callBackGetFakeApkInfo(null);
                        }
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
                        for (NetListen lst : list) {
                            lst.callBackGetFakeApkInfo(null);
                        }
                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }
}
