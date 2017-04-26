package com.turing.fakeapk.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.turing.fakeapk.Adapt.AppAdapter;
import com.turing.fakeapk.Adapt.CommonListAdapter;
import com.turing.fakeapk.R;
import com.turing.fakeapk.bean.AppInfo;
import com.turing.fakeapk.listen.DataListen;
import com.turing.fakeapk.service.DataMng;
import com.turing.fakeapk.service.PackageMng;

import java.util.List;

/**
 * Created by turingkuang on 2017/3/30.
 */
public class MoniterFileActivity extends Activity{
    private Button mSave = null;
    private Button mDelFile = null;
    private CheckBox mAllChoose = null;
    private ListView fileMoniterAppList = null;
    private ListView beMonitedFileList = null;
    private DataListen listen = null;
    private List<AppInfo> appList = null;
    private List<String> monitorFileList = null;
    private AppAdapter adapter = null;
    private CommonListAdapter monitorFileAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moniter_file_main);
        // 找到listview
        fileMoniterAppList = (ListView) findViewById(R.id.fileMoniterAppList);
        beMonitedFileList = (ListView) findViewById(R.id.beMonitedFileList);
        mSave = (Button) findViewById(R.id.save_moniter_list);
        mAllChoose = (CheckBox) findViewById(R.id.choose_all);
        mDelFile = (Button) findViewById(R.id.delMoniterFile);
        initAppList();
        initMonitorFile();
        setClickListen();
        listen = DataMng.getInstance();
    }
    private void setClickListen(){
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

        // 保存
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String[] list = new String[1000];
                    for(int index = 0; index < appList.size();index++){
                        if(appList.get(index).getState() == true) {
                            list[index] = appList.get(index).getPackageName();
                        }
                    }
                    listen.notifyFileRWApp(list);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        mDelFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DataMng.getInstance().delMoniterFiles();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void initAppList(){
        appList = getCurrentAppMoiterList();
        adapter = new AppAdapter(this, appList);
        fileMoniterAppList.setAdapter(adapter);
        fileMoniterAppList.setSelection(appList.size() - 1);
    }
    private void initMonitorFile(){
        monitorFileList = DataMng.getInstance().getBeMoniterFileList();
        if(null == monitorFileList || monitorFileList.size() == 0 ){
            mDelFile.setText("删除监控文件(共:"+ 0 +"个)");
        }else {
            monitorFileAdapter = new CommonListAdapter(this, monitorFileList);
            beMonitedFileList.setAdapter(monitorFileAdapter);
            beMonitedFileList.setSelection(monitorFileList.size() - 1);
            mDelFile.setText("删除监控文件(共:"+ monitorFileList.size() +"个)");
        }

    }
    private List<AppInfo> getCurrentAppMoiterList(){
        List<AppInfo> appList = PackageMng.getInstance().getMoniterInstalledPackages();
        String spStr = DataMng.getInstance().getMoniterFileList();
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
