package com.turing.fakeapk.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.turing.fakeapk.Adapt.AppAdapter;
import com.turing.fakeapk.R;
import com.turing.fakeapk.Utils.ToastUtils;
import com.turing.fakeapk.bean.AppInfo;
import com.turing.fakeapk.listen.DataListen;
import com.turing.fakeapk.service.DataMng;
import com.turing.fakeapk.service.PackageMng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by turingkuang on 2017/3/30.
 */
public class UninstallAppActivity extends Activity {
    private Button btn_uninstall = null;
    private CheckBox mAllChoose = null;
    private DataListen listen = null;
    private List<AppInfo> appList = null;
    private ListView listView = null;
    private AppAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uninstall_app_main);
        // 找到listview
        listView = (ListView) findViewById(R.id.listView);
        btn_uninstall = (Button) findViewById(R.id.btn_uninstall);
        mAllChoose = (CheckBox) findViewById(R.id.all_choose);
        listen = DataMng.getInstance();
        initAdapt();
        mAllChoose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // 全选
                for (AppInfo app : appList) {
                    app.setState(isChecked);
                }
                // 通知适配器更新数据
                adapter.notifyDataSetChanged();
            }
        });
        // 卸载
        btn_uninstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<String> list = new ArrayList<String>();
                    for(int index = 0; index < appList.size();index++){
                        if(appList.get(index).getState() == true) {
                            list.add(appList.get(index).getPackageName());
                        }
                    }
                    listen.notifyUninstallApp(list);
                    initAdapt();
                    ToastUtils.getInstance().showLong("卸载成功");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void initAdapt(){
        appList = getCurrentAppFilterList();
        adapter = new AppAdapter(this, appList);
        listView.setAdapter(adapter);
        listView.setSelection(appList.size() - 1);
    }
    private List<AppInfo> getCurrentAppFilterList(){
        List<AppInfo> appList = PackageMng.getInstance().getMoniterInstalledPackages();
        String spStr = DataMng.getInstance().getFilterAppList();
        for(AppInfo info : appList ){
            if(null != spStr && !spStr.equals("") && spStr.contains(info.getPackageName())){
                info.setState(true);
            }
        }
        return  appList;
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
}
