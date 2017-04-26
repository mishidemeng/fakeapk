package com.turing.fakeapk.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;

import com.turing.fakeapk.Adapt.FunctionGridAdapter;
import com.turing.fakeapk.R;
import com.turing.fakeapk.view.FunctionGridView;

/**
 * Created by fxr on 16-9-23.
 */

public class FunctionAppFragment extends Fragment {

    private int layoutID;
    SparseArray<View> views;
    View rootView;
    Button mSave = null;
    CheckBox mAllChoose = null;
    private FunctionGridView gridview;
    public  FunctionAppFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_function, null);
        views = new SparseArray<>();
        gridview = (FunctionGridView) rootView.findViewById(R.id.gridview);
        gridview.setAdapter(new FunctionGridAdapter(this.getContext()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0://屏蔽应用
                        Intent filterApp = new Intent(FunctionAppFragment.this.getContext(), FilterAppActivity.class);
                        startActivity(filterApp);
                        break;
                    case 1://监控文件
                        Intent moniterFile = new Intent(FunctionAppFragment.this.getContext(), MoniterFileActivity.class);
                        startActivity(moniterFile);
                        break;
                    case 2://一键关闭应用
                        Intent closeApp = new Intent(FunctionAppFragment.this.getContext(), CloseAppActivity.class);
                        startActivity(closeApp);
                        break;
                    case 3://一键卸载应用
                        Intent uninstallApp = new Intent(FunctionAppFragment.this.getContext(), UninstallAppActivity.class);
                        startActivity(uninstallApp);
                        break;
                    case 4://设置SDK
                        break;
                    case 5://设置分辨率
                        break;
                    case 6://捐赠
                        Intent donation = new Intent(FunctionAppFragment.this.getContext(), DonationActivity.class);
                        startActivity(donation);
                        break;
                }
            }
        });
        return rootView;
    }
}
