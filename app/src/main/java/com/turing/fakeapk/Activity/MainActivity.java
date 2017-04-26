package com.turing.fakeapk.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.turing.fakeapk.R;
import com.turing.fakeapk.Utils.ToastUtils;
import com.turing.fakeapk.service.ApkController;
import com.turing.fakeapk.service.DataMng;
import com.turing.fakeapk.service.HttpMng;
import com.turing.fakeapk.service.MobileInfoMng;
import com.turing.fakeapk.service.PackageMng;
import com.turing.fakeapk.service.PropertiesMng;
import org.xutils.x;

import java.io.DataOutputStream;
import java.util.HashMap;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private LinearLayout mTabNetData;
    private LinearLayout mTabFunction;
    private LinearLayout mTabMe;

    private HashMap<Integer, Fragment> fragments = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        upgradeRootPermission(getPackageCodePath());
        initBussi();// 初始化业务
        initFragments();
        initView();
        initEvent();
        setSelect(0);
    }

    private void initBussi() {
        MobileInfoMng.getInstance().init(this); // 设置环境
        PropertiesMng.getInstance().init(this);
        PackageMng.getInstance().init(this);
        ToastUtils.getInstance().init(this);// Toast 初始化
        DataMng.getInstance().init(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initFragments() {
        fragments.put(R.layout.tab_public, new PublicNetFragment());
        fragments.put(R.layout.tab_function, new FunctionAppFragment());
        fragments.put(R.layout.tab_set, new SetFragment());
    }

    private void initEvent() {
        mTabNetData.setOnClickListener(this);
        mTabFunction.setOnClickListener(this);
        mTabMe.setOnClickListener(this);

    }

    private void initView() {
        mTabNetData = (LinearLayout) findViewById(R.id.id_net_data);
        mTabFunction = (LinearLayout) findViewById(R.id.id_function);
        mTabMe = (LinearLayout) findViewById(R.id.id_me);
    }


    private void setSelect(int i) {
        //将所选图片变为亮色
        //切换对应的内容区域
        //首先拿到Fragment的内容管理器
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (i) {
            case 0:
                transaction.replace(R.id.id_container, fragments.get(R.layout.tab_public));
                break;
            case 1:
                transaction.replace(R.id.id_container, fragments.get(R.layout.tab_function));
                break;
            case 2:
                transaction.replace(R.id.id_container, fragments.get(R.layout.tab_set));
                break;
            default:
                break;
        }
        transaction.commit();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_net_data:
                //如果点击的是微信图标，就跳转到第一个tab
                setSelect(0);
                break;
            case R.id.id_function:
                setSelect(1);
                break;
            case R.id.id_me:
                setSelect(2);
                break;
            default:
                break;
        }
    }

    /**
     * 应用程序运行命令获取 Root权限，设备必须已破解(获得ROOT权限)
     *
     * @return 应用程序是/否获取Root权限
     */
    public boolean upgradeRootPermission(String pkgCodePath) {
        Process process = null;
        DataOutputStream os = null;
        try {
            String cmd = "chmod 777 " + pkgCodePath;
            process = Runtime.getRuntime().exec("su"); //切换到root帐号
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
