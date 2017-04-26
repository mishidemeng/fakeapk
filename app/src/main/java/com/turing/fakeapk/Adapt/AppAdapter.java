package com.turing.fakeapk.Adapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.turing.fakeapk.R;
import com.turing.fakeapk.bean.AppInfo;

import java.util.List;

public class AppAdapter extends BaseAdapter {
    private List<AppInfo> list = null;
    private LayoutInflater inflater = null;
    private View view = null;

    public AppAdapter(Context context, List<AppInfo> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public List<AppInfo> getList() {
        return list;
    }

    // 返回listView数据的条数
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        // 获取布局
        view = inflater.inflate(R.layout.choose_app_list, null);
        TextView appName = (TextView) view.findViewById(R.id.appName);
        final CheckBox box = (CheckBox) view.findViewById(R.id.check);
        final AppInfo app = list.get(position);
        box.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                app.setState(isChecked);
            }
        });
        // 控件和数据的匹配
        appName.setText(app.getAppName());
        box.setChecked(app.getState());
        return view;
    }

}