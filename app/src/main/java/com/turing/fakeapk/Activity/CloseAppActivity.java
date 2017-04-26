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
import com.turing.fakeapk.Utils.KernelLogUtil;
import com.turing.fakeapk.Utils.ToastUtils;
import com.turing.fakeapk.bean.AppInfo;
import com.turing.fakeapk.listen.DataListen;
import com.turing.fakeapk.service.DataMng;
import com.turing.fakeapk.service.PackageMng;
import com.turing.fakeapk.service.RunningAppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by turingkuang on 2017/3/30.
 */
public class CloseAppActivity extends Activity {
    private Button mClose = null;
    private CheckBox mAllChoose = null;
    private DataListen listen = null;
    private List<AppInfo> appList = null;
    private ListView listView = null;
    private AppAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.close_app_main);
        // 找到listview
        listView = (ListView) findViewById(R.id.listView);
        mClose = (Button) findViewById(R.id.close_app);
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
        // 关闭
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<AppInfo> list = new ArrayList<AppInfo>();
                    for(int index = 0; index < appList.size();index++){
                        if(appList.get(index).getState() == true) {
                            list.add(appList.get(index));
                        }
                    }
                    listen.notifyCloseApp(list);
                    initAdapt();
                    ToastUtils.getInstance().showLong("关闭成功");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void initAdapt(){
        appList = getCurrentUserAppFilterList();
        adapter = new AppAdapter(this, appList);
        listView.setAdapter(adapter);
        listView.setSelection(appList.size() - 1);
    }
    private List<AppInfo> getCurrentUserAppFilterList(){
        List<AppInfo> appList = PackageMng.getInstance().getMoniterInstalledPackages();
        String spStr = DataMng.getInstance().getFilterAppList();
        for(AppInfo info : appList ){
            if(null != spStr && !spStr.equals("") && spStr.contains(info.getPackageName())){
                info.setState(true);
            }
        }
        return  appList;
    }

    private List<AppInfo> getCurrentRunningAppList(){
        List<RunningAppInfo> list = DataMng.getInstance().queryAllRunningAppInfo();
        List<AppInfo> returnAppList = new ArrayList<AppInfo>();
        List<AppInfo> getCurUserAppList = getCurrentUserAppFilterList();
        for(AppInfo user : getCurUserAppList) {
            for (RunningAppInfo info : list) {
                if (null != info && user.getPackageName().equalsIgnoreCase(info.getPkgName())) {
                    user.setPid(info.getPid());
                    user.setProcessName(info.getProcessName());
                    returnAppList.add(user);
                    break;
                }
            }
        }
        return  returnAppList;
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
}
