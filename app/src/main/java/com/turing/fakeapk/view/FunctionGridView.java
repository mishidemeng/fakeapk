package com.turing.fakeapk.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
/**
 * @Description:解决在scrollview中只显示第一行数据的问题
 * @author http://blog.csdn.net/finddreams
 */
public class FunctionGridView extends GridView {
    public FunctionGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FunctionGridView(Context context) {
        super(context);
    }

    public FunctionGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
