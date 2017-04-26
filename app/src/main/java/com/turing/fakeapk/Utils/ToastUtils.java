package com.turing.fakeapk.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast统一管理类
 */

/**
 * Created by turingkuang on 2017/1/20.
 */
public class ToastUtils {

    public static boolean isShow = true;
    public static Context context = null;
    private static ToastUtils mInstance = new ToastUtils();

    public static ToastUtils getInstance() {
        if (null == mInstance)
            mInstance = new ToastUtils();
        return mInstance;
    }

    public void init(Context context) {
        this.context = context;
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public void showShort(CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public void showShort(int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public void showLong(CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public void showLong(int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public void show(CharSequence message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public void show(int message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

}