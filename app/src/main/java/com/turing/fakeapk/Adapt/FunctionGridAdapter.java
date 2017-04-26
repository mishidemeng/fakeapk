package com.turing.fakeapk.Adapt;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.turing.fakeapk.Activity.FilterAppActivity;
import com.turing.fakeapk.Holder.BaseViewHolder;
import com.turing.fakeapk.R;

/**
 * @author http://blog.csdn.net/finddreams
 * @Description:gridview的Adapter
 */
public class FunctionGridAdapter extends BaseAdapter {
    private Context mContext;

    public String[] img_text = {"屏蔽应用", "监控文件","一键关闭应用", "一键卸载应用","设置SDK", "设置分辨率","捐赠"};
    public int[] imgs = {R.drawable.app_phonecharge, R.drawable.app_phonecharge,
            R.drawable.app_phonecharge, R.drawable.app_phonecharge,
            R.drawable.app_phonecharge, R.drawable.app_phonecharge,R.drawable.app_phonecharge};

    public FunctionGridAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return img_text.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.grid_item, parent, false);
        }
        TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
        ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);
        iv.setBackgroundResource(imgs[position]);
        tv.setText(img_text[position]);
        return convertView;
    }

}
