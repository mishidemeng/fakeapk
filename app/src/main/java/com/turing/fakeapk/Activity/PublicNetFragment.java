package com.turing.fakeapk.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import com.turing.fakeapk.R;
import com.turing.fakeapk.Utils.AESUtils;
import com.turing.fakeapk.Utils.ToastUtils;
import com.turing.fakeapk.bean.ChangeDeviceInfo;
import com.turing.fakeapk.bean.Const;
import com.turing.fakeapk.bean.PGResponse;
import com.turing.fakeapk.listen.NetListen;
import com.turing.fakeapk.service.DataMng;
import com.turing.fakeapk.service.FileMng;
import com.turing.fakeapk.service.HttpMng;
import com.turing.fakeapk.service.MobileInfoMng;

/**
 * Created by fxr on 16-9-23.
 */

public class PublicNetFragment extends Fragment implements NetListen {
    SparseArray<View> views;
    View rootView;
    private Button mNetData;
    private TextView text;
    private ChangeDeviceInfo realInfo = null; // 真机数据
    public PublicNetFragment() {

    }

    private void setButtonListen() {
        // 分辨率调试
        mNetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    HttpMng.getInstance().getFakeApkInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        views = new SparseArray<>();
        rootView = inflater.inflate(R.layout.tab_public, null);
        text = (TextView) rootView.findViewById(R.id.textView);
        mNetData = (Button) rootView.findViewById(R.id.useNetData);
        HttpMng.getInstance().setNetListen(this);
        setButtonListen();
        displayRealInfo();
        String user = DataMng.getInstance().getUserPassword();
        if(user == null || user.equals("") || user.equals("fafa,888888")){
            mNetData.setText("获取真机数据（免费版）");
        }else{
            mNetData.setText("获取真机数据");
        }
        String token = DataMng.getInstance().getLoginToken();
        if(null == token || token.equals("")){
            HttpMng.getInstance().login("fafa", "888888");
        }
        return rootView;
    }
    public void displayRealInfo() {
        realInfo = MobileInfoMng.getInstance().getRealInfo();
        String buf = MobileInfoMng.getInstance().getDisplayInfo(realInfo);
        text.setText(buf);
    }
    @Override
    public void callLogin(String str) {
        String fail = "登录失败,请联系QQ: 274413443";
        if (str == null) {
            ToastUtils.getInstance().showLong(fail);
            return;
        }
        PGResponse<String> rsp = JSON.parseObject(str, new TypeReference<PGResponse<String>>() {
        });
        if (null == rsp || rsp.getStatus() != Const.common_ok) {
            ToastUtils.getInstance().showLong(rsp.getData());
            return;
        }
        ToastUtils.getInstance().showLong("登录成功");
        DataMng.getInstance().saveLoginToken(AESUtils.decryptFromBase64(rsp.getData()));
    }

    @Override
    public void callBackGetFakeApkInfo(String str) {
        if (null == str || str.equals("")) {
            ToastUtils.getInstance().show("失败,请联系QQ:2559100932", 5);
            return;
        }
        PGResponse<String> rsp = JSON.parseObject(str, new TypeReference<PGResponse<String>>() {
        });
        if (null != rsp && rsp.getStatus() == 0) {
            ChangeDeviceInfo info = JSON.parseObject(AESUtils.decryptFromBase64(rsp.getData()), new TypeReference<ChangeDeviceInfo>() {
            });
            DataMng.getInstance().setChangedForXpose(info); // 使用后台下发的数据
            String buf = MobileInfoMng.getInstance().getDisplayInfo(info);
            text.setText(buf);
            ToastUtils.getInstance().show("获取成功", 5);
        } else {
            ToastUtils.getInstance().show("失败,请联系QQ:2559100932", 5);
        }
    }
}
