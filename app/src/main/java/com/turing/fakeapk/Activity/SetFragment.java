package com.turing.fakeapk.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import com.turing.fakeapk.R;
import com.turing.fakeapk.Utils.AESUtils;
import com.turing.fakeapk.Utils.ToastUtils;
import com.turing.fakeapk.bean.Const;
import com.turing.fakeapk.bean.PGResponse;
import com.turing.fakeapk.listen.NetListen;
import com.turing.fakeapk.service.DataMng;
import com.turing.fakeapk.service.HttpMng;
import com.turing.fakeapk.service.MobileInfoMng;

/**
 * Created by fxr on 16-9-23.
 */

public class SetFragment extends Fragment{

    private int layoutID;
    SparseArray<View> views;
    View rootView;
    private Button mLogin;
    private EditText user_name;
    private EditText password;
    private TextView note;
    public SetFragment() {

    }

    private void setButtonListen() {
        // 分辨率调试
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DataMng.getInstance().savaUserPassword(user_name.getText().toString(),password.getText().toString());
                    HttpMng.getInstance().login(user_name.getText().toString(), password.getText().toString());
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
        rootView = inflater.inflate(R.layout.tab_set, null);
        mLogin = (Button) rootView.findViewById(R.id.btn_login);
        user_name = (EditText) rootView.findViewById(R.id.user_name);
        password = (EditText) rootView.findViewById(R.id.password);
        note = (TextView) rootView.findViewById(R.id.note);
        setButtonListen();
        initNote();
        return rootView;
    }
    private void initNote(){
        StringBuffer buf = new StringBuffer();
        buf.append("注:所有数据真实准确 \n");
        buf.append("免费版: 享50款真机数据，无需登录\n");
        buf.append("VIP版:  享2000款真机数据，需账号登录，账号联系QQ:274413443");
        note.setText(buf.toString());
        String userPass = DataMng.getInstance().getUserPassword();
        if(null == userPass || userPass.equals("")){
            user_name.setText("fafa");
            password.setText("888888");
        }else{
            String[] str =  userPass.split(",");
            user_name.setText(str[0]);
            password.setText(str[1]);
        }
    }
}
