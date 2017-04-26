package com.turing.fakeapk.service;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.turing.fakeapk.Utils.KernelLogUtil;
import com.turing.fakeapk.Utils.RandomUtils;
import com.turing.fakeapk.Utils.ToastUtils;
import com.turing.fakeapk.bean.AppInfo;
import com.turing.fakeapk.bean.ChangeDeviceInfo;
import com.turing.fakeapk.fake.SharedPref;
import com.turing.fakeapk.Utils.WriteStreamAppend;
import com.turing.fakeapk.listen.DataListen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by turingkuang on 2017/3/31.
 */
public class DataMng implements DataListen{
    private static DataMng mng = new DataMng();
    private SharedPref mySP = null;
    private Context mContext = null;
    private List<RunningAppInfo> mlistAppInfo = null;
    private PackageManager pm;
    // 包含这些关键字的包过滤掉
    public String[] FAFA_FITER_KEYWORD_LIST = new String[]{"supersu", "superuser", "Superuser",
            "noshufou", "xposed", "rootcloak", "haima", "touchsprite", "org.tencent", "kuang.jun.qing",
            "com.turing.fakeapk",
            "apk008v",
            "chainfire", "titanium", "Titanium",
            "substrate", "greenify", "daemonsu",
            "root", "busybox", "titanium",
            ".tmpsu", "su", "rootcloak2"};

    public String[] mUserFilterAppList = new String[]{};

    // -- 这些app可以不受rootCloak影响
    public String[] CAN_GET_ALLAPPS_LIST = {
            "com.turing.fakeapk",
            "com.soft.apk008v",
            "me.haima.helpcenter",
            "me.haima.androidassist",
            "de.robv.android.xposed.installer",
            "com.touchsprite.android",
            "eu.chainfire.supersu",
            "kuang.jun.qing"
    };

    public static DataMng getInstance() {
        if (null == mng)
            return new DataMng();
        return mng;
    }
    public void init(Context cnt){
        mContext = cnt;
        mySP = new SharedPref(cnt.getApplicationContext());
        initFilterAppList();
    }
    public String getFilterAppList(){
        String words = SharedPref.getValue("MustFiterKeyWordList");
        return words;
    }
    public String getMoniterFileList(){
        String pack = SharedPref.getValue("MonitorPackage");
        return pack;
    }
    public String getMustFilterKeyWords() {
        String ret = "";
        for (String tmp : FAFA_FITER_KEYWORD_LIST) {
            ret = ret + tmp + ",";
        }
        for (String tmp : mUserFilterAppList) {
            ret = ret + tmp + ",";
        }
        return ret;
    }

    // 可以获得所有包名的资格，其他的要接受 过滤
    public String getCanGetAllAppList() {
        String ret = "";
        for (String tmp : CAN_GET_ALLAPPS_LIST) {
            ret = ret + tmp + ",";
        }
        return ret;
    }
    public void savaUserPassword(String user,String pass){
        mySP.setSharedPref("UserPass", user+","+pass);
    }
    public String getUserPassword(){
        return (null == SharedPref.getValue("UserPass")) ? "" : SharedPref.getValue("UserPass");
    }

    public void saveLoginToken(String token) {
        mySP.setSharedPref("fafaToken", token);
    }
    public String getLoginToken() {
        return (null == SharedPref.getValue("fafaToken")) ? "" : SharedPref.getValue("fafaToken");
    }

    public void initFilterAppList() {
        String words = SharedPref.getValue("MustFiterKeyWordList");
        String ret = "";
        if (null == words || words.equals("")) {
            for (String str : FAFA_FITER_KEYWORD_LIST) {
                ret = ret + str + ",";
            }
            mySP.setSharedPref("MustFiterKeyWordList", ret);
        }
    }
    public void setMonitorFile(String taskPack) {
        String pack = SharedPref.getValue("MonitorPackage");
        if(null != pack && !pack.contains(taskPack)){
            pack = pack + taskPack + ",";
            mySP.setSharedPref("MonitorPackage",pack);
        }
    }
    public void setMonitorFile(String[] packList) {
        String tmpList = "";
        for(String pack : packList ){
            tmpList = tmpList + pack + ",";
        }
        mySP.setSharedPref("MonitorPackage", tmpList);
    }

    @Override
    public void notifyFilterApp(List<String> packageList) {
        if(null == packageList || 0 == packageList.size()){
            return ;
        }
        String[] tmpList = new String[packageList.size()];
        for(int index = 0; index < packageList.size();index++){
            tmpList[index] = packageList.get(index);
        }
        mUserFilterAppList = tmpList;
        mySP.setSharedPref("CanGetAllPackgeAppList", getCanGetAllAppList());
        mySP.setSharedPref("MustFiterKeyWordList", getMustFilterKeyWords());
    }

    @Override
    public void notifyFileRWApp(String[] packageList) {
        setMonitorFile(packageList);
    }

    @Override
    public void notifyChooseSDK(String[] sdkList) {

    }

    @Override
    public void notifyChooseResolution(String[] whList) {

    }

    @Override
    public void notifyCloseApp(List<AppInfo> packageList) {
        if(packageList == null || packageList.size() == 0)
            return;
        ActivityManager am = (ActivityManager) mContext.getSystemService(mContext.ACTIVITY_SERVICE);
        for(AppInfo pack : packageList) {
            try {
                //Method method = Class.forName("android.app.ActivityManager").getMethod("forceStopPackage", String.class);
                //method.setAccessible(true);
                //method.invoke(am, pack.getPackageName());  //packageName是需要强制停止的应用程序包名
                am.killBackgroundProcesses(pack.getPackageName());
                //Process.killProcess(pack.getPid());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void notifyUninstallApp(List<String> packageList) {
        if(packageList == null || packageList.size() == 0)
            return;
        for(String pack : packageList) {
            if(null != pack) {
                boolean ret = ApkController.uninstall(pack, mContext);//"org.kyf.denis.set"
                if (ret) {
                    ToastUtils.getInstance().showLong("成功卸载:" + pack);
                } else {
                    ToastUtils.getInstance().showLong("卸载失败:" + pack);
                }
            }
        }
    }

    public void delCateloge() {
        String[] delCate = {"/mnt/sdcard/w/", "/mnt/sdcard/ycyidfile/", "/mnt/sdcard/download/yccp/", "/mnt/sdcard/Android/azb/"};
        for (String str : delCate) {
            FileMng.getInstance().deleteDirectory(str);
        }
    }
    public List<String> getBeMoniterFileList(){
        String str = FileMng.getInstance().readFileSdcardFile(WriteStreamAppend.mFile);// 先读，删除再写
        List<String> pathList = new ArrayList<String>();
        int flag = 0;
        if (null == str || str.equals(""))
            return null;
        String[] fileCateList = str.split("\n");
        int index = 0;
        for (String path : fileCateList) {
            if (null != path) {
                String[] pathArray = path.split(":");
                if (pathArray.length != 2) {
                    continue;
                }
                if (pathArray[1].contains("/mnt/sdcard") || pathArray[1].contains("/mnt/sdcard2")) {
                    flag = 0;
                    for (String str1 : pathList) {
                        if (pathArray[1].equals(str1)) {
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        pathList.add(pathArray[1]);
                    }
                }
            }
        }
        return pathList;
    }
    public void delMoniterFiles() {
        FileMng.getInstance().createFile(WriteStreamAppend.mFile);
        List<String> pathList = getBeMoniterFileList();
        if(null == pathList || pathList.size() == 0)
            return;

        for (String str2 : pathList) {
            FileMng.getInstance().deleteFileParent(str2);
        }
        ToastUtils.getInstance().showLong("删除应用之前遗留的痕迹，共" + pathList.size() + "个文件");
        FileMng.getInstance().writeFileSdcardFile(WriteStreamAppend.mFile, ""); // 写空
    }
    public void setChangedForXpose(ChangeDeviceInfo info) {
        mySP.setSharedPref("PhoneNumber", info.getPhone());
        mySP.setSharedPref("WifiName", info.getSsid());
        mySP.setSharedPref("WifiMAC", info.getMac());
        mySP.setSharedPref("BSSID", info.getBssid());
        mySP.setSharedPref("AndroidID", info.getAndroidId());
        mySP.setSharedPref("AndroidSerial", info.getAndroidSerial());
        mySP.setSharedPref("IMEI", info.getImei());
        mySP.setSharedPref("IMSI", info.getImsi());
        mySP.setSharedPref("Width", info.getWidth());
        mySP.setSharedPref("Height", info.getHeight());
        mySP.setSharedPref("NetWorkType", info.getNetworkType().toString());
        mySP.setSharedPref("NetWorkTypeName", info.getNetworkTypeName());
        mySP.setSharedPref("NetWorkSubType", info.getNetworkSubType().toString());
        mySP.setSharedPref("NetWorkSubTypeName", info.getNetworkSubTypeName());
        mySP.setSharedPref("SimSerial", info.getSimSerial());
        mySP.setSharedPref("SimState", info.getSimStatus());
        mySP.setSharedPref("UserAgent", info.getUa());
        mySP.setSharedPref("Carrier", info.getCarrier());
        mySP.setSharedPref("CarrierCode", info.getCarrierCode());
        mySP.setSharedPref("CountryCode", info.getCountryCode());
        mySP.setSharedPref("Longitude", info.getLongitude().toString());
        mySP.setSharedPref("Latitude", info.getLatitude().toString());
        //---------------------------------------------------------------------------


        mySP.setSharedPref("OSName", info.getBuildInfo().getOsName());
        mySP.setSharedPref("OSArch", info.getBuildInfo().getOsArch());
        mySP.setSharedPref("OSVersion", info.getBuildInfo().getOsVersion());

        mySP.setSharedPref("Build.MANUFACTURER", info.getBuildInfo().getManufacture());
        mySP.setSharedPref("Build.BRAND", info.getBuildInfo().getBrand());
        mySP.setSharedPref("Build.MODEL", info.getBuildInfo().getModel());
        mySP.setSharedPref("Build.HARDWARE", info.getBuildInfo().getHardWare());
        mySP.setSharedPref("Build.DEVICE", info.getBuildInfo().getDevice());
        mySP.setSharedPref("Build.PRODUCT", info.getBuildInfo().getProduct());
        mySP.setSharedPref("Build.BOARD", info.getBuildInfo().getBoard());
        mySP.setSharedPref("Build.DISPLAY", info.getBuildInfo().getDisplay());
        mySP.setSharedPref("Build.ID", info.getBuildInfo().getId());
        mySP.setSharedPref("Build.CPU_ABI", info.getBuildInfo().getCpu_abi());
        mySP.setSharedPref("Build.BOOTLOADER", info.getBuildInfo().getBootloader());
        mySP.setSharedPref("Build.FINGERPRINT", info.getBuildInfo().getFingerPrint());
        mySP.setSharedPref("Build.HOST", info.getBuildInfo().getHost());
        mySP.setSharedPref("Build.TAGS", info.getBuildInfo().getTags());
        mySP.setSharedPref("Build.TYPE", info.getBuildInfo().getType());
        mySP.setSharedPref("Build.SERIAL", info.getBuildInfo().getSerial());
        mySP.setSharedPref("Build.USER", info.getBuildInfo().getUser());
        //-------------------------------------
        mySP.setSharedPref("Build.VERSION.INCREMENTAL", info.getBuildInfo().getIncrement());
        mySP.setSharedPref("Build.VERSION.API", info.getBuildInfo().getSdk());
        mySP.setSharedPref("Build.VERSION.RELEASE", info.getBuildInfo().getAndroidVersion());
        mySP.setSharedPref("Build.VERSION.CODENAME", "REL");
        mySP.setSharedPref("GoogleAdsID", RandomUtils.getRandomNumbersAndLetters(32));
/*
        mySP.setSharedPref("AndroidVer", "4.3.2");
        mySP.setSharedPref("API", "18");
        mySP.setSharedPref("PhoneNumber", "84962439943");
        mySP.setSharedPref("WifiName", "MyWifi");
        mySP.setSharedPref("WifiMAC", "6C:C4:08:BB:B1:28");
        mySP.setSharedPref("BSSID", "6C:C4:08:BB:B1:28");
        mySP.setSharedPref("AndroidID", "6c0bb208c33b8c43");
        mySP.setSharedPref("AndroidSerial", "6c0bb208c33b");
        mySP.setSharedPref("IMEI", "506066104722640");
        mySP.setSharedPref("IMSI", "457686544566664");
        mySP.setSharedPref("Width","1280");
        mySP.setSharedPref("Height","1920");
        mySP.setSharedPref("NetWorkType","1");
        mySP.setSharedPref("NetWorkTypeName","WIFI");
        mySP.setSharedPref("NetWorkSubType","13");
        mySP.setSharedPref("NetWorkSubTypeName","TD-CDMA");
        mySP.setSharedPref("SimSerial", "36066104722647215170");
        mySP.setSharedPref("SimState", "5");
        mySP.setSharedPref("MANUFACTURER", "Meizu");
        mySP.setSharedPref("BRAND", "Meizu");
        mySP.setSharedPref("MODEL", "M351");
        mySP.setSharedPref("HARDWARE", "mx3");
        mySP.setSharedPref("DEVICE", "mx3");
        mySP.setSharedPref("PRODUCT", "meizu_mx3");
        mySP.setSharedPref("BOARD", "meizu_mx3");
        mySP.setSharedPref("DISPLAY", "Flyme OS 4.1.3.5A");
        mySP.setSharedPref("ID", "KTU84P");
        mySP.setSharedPref("CPU_ABI", "armeabi-v7a");
        mySP.setSharedPref("BOOTLOADER", "unknown");
        mySP.setSharedPref("FINGERPRINT", "Meizu/meizu_mx3/mx3:4.4.4/KTU84P/m35x.Flyme_OS_4.1.3.5.20150111061013:user/release-keys");
        mySP.setSharedPref("INCREMENTAL","m35x.Flyme_OS_4.1.3.5.20150111061013");
        mySP.setSharedPref("UserAgent", "Mozilla/5.0 (Linux; Android 4.4.2; Build/16.0.A.0.36) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/51.0.2704 Mobile Safari/537.36");
        mySP.setSharedPref("Carrier", "Mobifone");
        mySP.setSharedPref("CarrierCode", "45201");
        mySP.setSharedPref("CountryCode", "VN");
        mySP.setSharedPref("OSName", "Linux");
        mySP.setSharedPref("OSArch", "armv7l");
        mySP.setSharedPref("OSVersion", "3.4.0-gd59db4e");
        mySP.setSharedPref("Longitude", "125.06788613");
        mySP.setSharedPref("Latitude", "27.82516672");
        mySP.setSharedPref("REL", "REL");
        //-------------------------------------------------------------------------------------
        mySP.setSharedPref("BaseBand", "eng.administrator.1373289311");
        mySP.setSharedPref("GoogleAdsID", "f741b85f-fbab-4eb3-8e44-358e07c3bc50");
        mySP.setSharedPref("FakeEmailPackge", "com.alibaba.aliexpresshd");
        mySP.setSharedPref("HideRootPackge", "com.alibaba.aliexpresshd");
        mySP.setSharedPref("Email", "hl.46000@gmail.com");
        mySP.setSharedPref("Alt", "125.06");
        mySP.setSharedPref("Speed", "3.7");
        mySP.setSharedPref("DPI", "320");
        mySP.setSharedPref("CPUId", "Qualcomm");
        mySP.setSharedPref("UId", "Qualcomm");
        mySP.setSharedPref("PId", "Qualcomm");
        mySP.setSharedPref("GLRenderer", "Adreno (TM) 330");
        mySP.setSharedPref("GLVendor", "Qualcomm");
        mySP.setSharedPref("Temp", "350");
        mySP.setSharedPref("Level", "35");
        mySP.setSharedPref("TimeZone", "Africa/Abidjan");
        mySP.setSharedPref("DESCRIPTION", "jfltexx-user 4.3 JSS15J I9505XXUEML1 release-keys");
        mySP.setSharedPref("CPU_ABI2", "armeabi");
        */
        // PRODUCT = device INCREMENTAL = BOOTLOADER
    }

    // 查询所有正在运行的应用程序信息： 包括他们所在的进程id和进程名
    // 这儿我直接获取了系统里安装的所有应用程序，然后根据报名pkgname过滤获取所有真正运行的应用程序
    public List<RunningAppInfo> queryAllRunningAppInfo() {
        pm = mContext.getPackageManager();
        // 查询所有已经安装的应用程序
        List<ApplicationInfo> listAppcations = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        Collections.sort(listAppcations,new ApplicationInfo.DisplayNameComparator(pm));// 排序

        // 保存所有正在运行的包名 以及它所在的进程信息
        Map<String, ActivityManager.RunningAppProcessInfo> pgkProcessAppMap = new HashMap<String, ActivityManager.RunningAppProcessInfo>();

        ActivityManager mActivityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        // 通过调用ActivityManager的getRunningAppProcesses()方法获得系统里所有正在运行的进程
        List<ActivityManager.RunningAppProcessInfo> appProcessList = mActivityManager
                .getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcessList) {
            int pid = appProcess.pid; // pid
            String processName = appProcess.processName; // 进程名
            KernelLogUtil.LogTuring("processName: " + processName + "  pid: " + pid);

            String[] pkgNameList = appProcess.pkgList; // 获得运行在该进程里的所有应用程序包

            // 输出所有应用程序的包名
            for (int i = 0; i < pkgNameList.length; i++) {
                String pkgName = pkgNameList[i];
                KernelLogUtil.LogTuring("packageName " + pkgName + " at index " + i+ " in process " + pid);
                // 加入至map对象里
                pgkProcessAppMap.put(pkgName, appProcess);
            }
        }
        // 保存所有正在运行的应用程序信息
        List<RunningAppInfo> runningAppInfos = new ArrayList<RunningAppInfo>(); // 保存过滤查到的AppInfo

        for (ApplicationInfo app : listAppcations) {
            // 如果该包名存在 则构造一个RunningAppInfo对象
            if (pgkProcessAppMap.containsKey(app.packageName)) {
                // 获得该packageName的 pid 和 processName
                int pid = pgkProcessAppMap.get(app.packageName).pid;
                String processName = pgkProcessAppMap.get(app.packageName).processName;
                runningAppInfos.add(getAppInfo(app, pid, processName));
            }
        }

        return runningAppInfos;
    }
    // 构造一个RunningAppInfo对象 ，并赋值
    private RunningAppInfo getAppInfo(ApplicationInfo app, int pid, String processName) {
        RunningAppInfo appInfo = new RunningAppInfo();
        appInfo.setAppLabel((String) app.loadLabel(pm));
        appInfo.setAppIcon(app.loadIcon(pm));
        appInfo.setPkgName(app.packageName);
        appInfo.setPid(pid);
        appInfo.setProcessName(processName);
        return appInfo;
    }
}
