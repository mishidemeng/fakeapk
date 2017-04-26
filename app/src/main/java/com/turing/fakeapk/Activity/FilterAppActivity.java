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
import com.turing.fakeapk.bean.AppInfo;
import com.turing.fakeapk.listen.DataListen;
import com.turing.fakeapk.service.DataMng;
import com.turing.fakeapk.service.PackageMng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by turingkuang on 2017/3/30.
 */
public class FilterAppActivity extends Activity {
    private Button mSave = null;
    private CheckBox mAllChoose = null;
    private DataListen listen = null;
    private List<AppInfo> appList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_filter_main);
        // 找到listview
        ListView listView = (ListView) findViewById(R.id.listView);
        mSave = (Button) findViewById(R.id.save_fiter_list);
        mAllChoose = (CheckBox) findViewById(R.id.all_choose);
        listen = DataMng.getInstance();
        appList = getCurrentAppFilterList();
        final AppAdapter adapter2 = new AppAdapter(this, appList);
        listView.setAdapter(adapter2);
        listView.setSelection(appList.size() - 1);
        mAllChoose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // 全选
                for (AppInfo app : appList) {
                    app.setState(isChecked);
                }
                // 通知适配器更新数据
                adapter2.notifyDataSetChanged();
            }
        });
        // 保存
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<String> list = new ArrayList<String>();
                    for(int index = 0; index < appList.size();index++){
                        if(appList.get(index).getState() == true) {
                            list.add(appList.get(index).getPackageName());
                        }
                    }
                    listen.notifyFilterApp(list);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private List<AppInfo> getCurrentAppFilterList(){
        List<AppInfo> appList = PackageMng.getInstance().getInstalledPackages();
        String spStr = DataMng.getInstance().getFilterAppList();
        String[] spList = spStr.split(",");
        for(AppInfo info : appList ){
            if(null != spStr && !spStr.equals("")){
                for(String tmp :spList ){
                    if(info.getPackageName().contains(tmp)) {
                        info.setState(true);
                        break;
                    }
                }
            }
        }
        return  appList;
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
}
