package com.turing.fakeapk.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by turingkuang on 2017/3/31.
 */
public class FFListView extends ListView {

    public FFListView(Context context) {
        super(context);
    }

    public FFListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FFListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }

}
