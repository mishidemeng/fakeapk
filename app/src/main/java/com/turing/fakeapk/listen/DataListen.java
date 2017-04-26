package com.turing.fakeapk.listen;

import com.turing.fakeapk.bean.AppInfo;

import java.util.List;

/**
 * Created by turingkuang on 2017/3/30.
 */
public interface DataListen {
    public void notifyFilterApp(List<String> packageList);
    public void notifyFileRWApp(String[] packageList);
    public void notifyCloseApp(List<AppInfo> packageList);
    public void notifyUninstallApp(List<String> packageList);
    public void notifyChooseSDK(String[] sdkList);
    public void notifyChooseResolution(String[] whList);
}
