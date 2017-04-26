package com.turing.fakeapk.Adapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.turing.fakeapk.R;

import java.util.List;

public class CommonListAdapter extends BaseAdapter {
    private List<String> list = null;
    private LayoutInflater inflater = null;
    private View view = null;

    public CommonListAdapter(Context context, List<String> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public List<String> getList() {
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
        view = inflater.inflate(R.layout.monitor_list, null);
        TextView appName = (TextView) view.findViewById(R.id.filePath);
        final String packName = list.get(position);
        // 控件和数据的匹配
        appName.setText(packName);
        return view;
    }

}