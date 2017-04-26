package com.turing.fakeapk.service;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.turing.fakeapk.Utils.KernelLogUtil;
import com.turing.fakeapk.Utils.RandomUtils;
import com.turing.fakeapk.Utils.UserAgentUtil;
import com.turing.fakeapk.bean.ChangeDeviceInfo;
import com.turing.fakeapk.bean.PlatformResolution;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by turingkuang on 2017/1/7.
 */
public class MobileInfoMng {
    private static MobileInfoMng mng = new MobileInfoMng();
    private Context mContext = null;
    List<PlatformResolution> mResolutionList = new ArrayList<PlatformResolution>();
    private int resIndex = 0;

    public static MobileInfoMng getInstance() {
        if (null == mng)
            return new MobileInfoMng();

        return mng;
    }

    /**
     * 查询手机内非系统应用
     *
     * @return
     */
    public List<PackageInfo> getAllApps() {
        List<PackageInfo> apps = new ArrayList<PackageInfo>();
        PackageManager pManager = mContext.getPackageManager();
        //获取手机内所有应用
        List<PackageInfo> paklist = pManager.getInstalledPackages(0);
        for (int i = 0; i < paklist.size(); i++) {
            PackageInfo pak = (PackageInfo) paklist.get(i);
            KernelLogUtil.LogTuring("packageName: " + pak.packageName + "\n");
            KernelLogUtil.LogTuring("className: " + pak.applicationInfo.className + "\n");
            KernelLogUtil.LogTuring("processName: " + pak.applicationInfo.processName + "\n");
            KernelLogUtil.LogTuring("-----------------------------------");
            //判断是否为非系统预装的应用程序
            if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) {
                // customs applications
                apps.add(pak);
            }
        }
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        KernelLogUtil.LogTuring("WindowManager--" + width + "x" + height);
        return apps;
    }

    public void init(Context cnt) {
        if (null == mContext) {
            mContext = cnt;
        }
    }

    public static String getRandomPackage() {
        String str = "";
        str = "com." + RandomUtils.getRandomLowerCaseLetters(3) + "." + RandomUtils.getRandomLowerCaseLetters(3);
        return str;
    }
    public void updateResolution() {
        /*
        if(mResolutionList.size() == 0) {
            PlatformResolution R720X1280 = new PlatformResolution("720", "1280");// 690,338
            PlatformResolution R540X960 = new PlatformResolution("540", "960"); //690,338
            PlatformResolution R480X800 = new PlatformResolution("480", "800");//653,373
            PlatformResolution R320X480 = new PlatformResolution("320", "480");//690,338
            PlatformResolution R1080X1920 = new PlatformResolution("1080", "1920");//600,426
            PlatformResolution R1200X1920 = new PlatformResolution("1200", "1920");//600,426
            PlatformResolution R1280X800 = new PlatformResolution("1280", "800");//600,426
            PlatformResolution R480X854 = new PlatformResolution("480", "854");//690,338
            PlatformResolution R480X960 = new PlatformResolution("480", "960");//600,426
            PlatformResolution R256X320 = new PlatformResolution("256", "320");//645,382
            PlatformResolution R544X960 = new PlatformResolution("544", "960");//690,338
            PlatformResolution R600X1024 = new PlatformResolution("600", "1024");//600,426
            PlatformResolution R768X1024 = new PlatformResolution("768", "1024");//600,426
            PlatformResolution R800X1280 = new PlatformResolution("800", "1280");//645,382
            mResolutionList.add(R720X1280);
            mResolutionList.add(R540X960);
            mResolutionList.add(R480X800);
            mResolutionList.add(R320X480);
            mResolutionList.add(R1080X1920);
            mResolutionList.add(R1200X1920);
            mResolutionList.add(R1280X800);
            mResolutionList.add(R480X854);
            mResolutionList.add(R480X960);
            mResolutionList.add(R256X320);
            mResolutionList.add(R544X960);
            mResolutionList.add(R600X1024);
            mResolutionList.add(R768X1024);
            mResolutionList.add(R800X1280);
        }
        PlatformResolution res = mResolutionList.get(resIndex++%mResolutionList.size());
        ToastUtils.getInstance().showLong(res.getWidth() + "X" + res.getHeight());
        mySP.setSharedPref("Width",res.getWidth());
        mySP.setSharedPref("Height",res.getHeight());
        */
        isRooted();
    }

    public DataInputStream Terminal(String command) throws Exception {
        Process process = Runtime.getRuntime().exec("su");
        //执行到这，Superuser会跳出来，选择是否允许获取最高权限
        OutputStream outstream = process.getOutputStream();
        DataOutputStream DOPS = new DataOutputStream(outstream);
        InputStream instream = process.getInputStream();
        DataInputStream DIPS = new DataInputStream(instream);
        String temp = command + "\n";
        //加回车
        DOPS.writeBytes(temp);
        //执行
        DOPS.flush();
        //刷新，确保都发送到outputstream
        DOPS.writeBytes("exit\n");
        //退出
        DOPS.flush();
        process.waitFor();
        return DIPS;
    }

    public boolean isRooted() {
        //检测是否ROOT过
        DataInputStream stream;
        boolean flag = false;
        try {
            stream = Terminal("ls /data/");
            //目录哪都行，不一定要需要ROOT权限的
            if (stream.readLine() != null) {
                flag = true;
            }
            //根据是否有返回来判断是否有root权限
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();

        }

        return flag;
    }

    public String getDisplayInfo(ChangeDeviceInfo info) {
        StringBuffer buf = new StringBuffer();
        if (null == info)
            return "";
        buf.append("\n imei: " + info.getImei());
        buf.append("\n imsi: " + info.getImsi());
        buf.append("\n 宽X高:" + info.getWidth() + "X" + info.getHeight());
        buf.append("\n 经度: " + info.getLongitude());
        buf.append("\n 纬度: " + info.getLatitude());
        buf.append("\n wifi名: " + info.getSsid());
        buf.append("\n 网络类型:" + info.getNetworkTypeName());
        buf.append("\n Mac地址: " + info.getMac());
        buf.append("\n 手机号: " + info.getPhone());
        buf.append("\n 手机状态: " + info.getPhoneStatus());
        buf.append("\n SIM卡号: " + info.getSimSerial());
        buf.append("\n SIM卡状态: " + readSIMCard(Integer.valueOf(info.getSimStatus())));
        buf.append("\n 品牌名: " + info.getBuildInfo().getBrand());
        buf.append("\n 制造商: " + info.getBuildInfo().getManufacture());
        buf.append("\n 手机的型号: " + info.getBuildInfo().getModel());
        buf.append("\n 版本包: " + info.getBuildInfo().getDisplay());
        buf.append("\n 设备基板: " + info.getBuildInfo().getBoard());
        buf.append("\n 运营商: " + info.getCarrier());
        buf.append("\n 运营商编号: " + info.getCarrierCode());
        buf.append("\n 国家号: " + info.getCountryCode());
        buf.append("\n 系统版本:" + info.getBuildInfo().getAndroidVersion());
        buf.append("\n DEVICE: " + info.getBuildInfo().getDevice());
        buf.append("\n CPU_ABI: " + info.getBuildInfo().getCpu_abi());
        buf.append("\n PRODUCT: " + info.getBuildInfo().getProduct());
        buf.append("\n BOOTLOADER: " + info.getBuildInfo().getBootloader());
        buf.append("\n SDK: " + info.getBuildInfo().getSdk());
        buf.append("\n AndroidId: " + info.getAndroidId());
        buf.append("\n AndroidSerial: " + info.getAndroidSerial());
        buf.append("\n os.version: " + info.getBuildInfo().getOsVersion());
        buf.append("\n os.arch: " + info.getBuildInfo().getOsArch());
        buf.append("\n os.name: " + info.getBuildInfo().getOsName());
        buf.append("\n Build.FingerPrint: " + info.getBuildInfo().getFingerPrint());
        buf.append("\n Build.HardWare: " + info.getBuildInfo().getHardWare());
        buf.append("\n Build.Id: " + info.getBuildInfo().getId());
        buf.append("\n Build.Host: " + info.getBuildInfo().getHost());
        buf.append("\n Build.Increment: " + info.getBuildInfo().getIncrement());
        buf.append("\n User-Agent: " + info.getUa());
        return buf.toString();
    }

    public String getDeviceSerial() {
        String serial = "unknown";
        try {
            Class clazz = Class.forName("android.os.Build");
            Class paraTypes = Class.forName("java.lang.String");
            Method method = clazz.getDeclaredMethod("getString", paraTypes);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            serial = (String) method.invoke(new Build(), "ro.serialno");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return serial;
    }

    public String GetNetworkType() {
        String strNetworkType = "";
        NetworkInfo networkInfo = ((ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                strNetworkType = "WIFI";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String _strSubTypeName = networkInfo.getSubtypeName();

                Log.e("cocos2d-x", "Network getSubtypeName : " + _strSubTypeName);

                // TD-SCDMA   networkType is 17
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        strNetworkType = "2G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        strNetworkType = "3G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        strNetworkType = "4G";
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            strNetworkType = "3G";
                        } else {
                            strNetworkType = _strSubTypeName;
                        }
                        break;
                }
                Log.e("cocos2d-x", "Network getSubtype : " + Integer.valueOf(networkType).toString());
            }
        }
        Log.e("cocos2d-x", "Network Type : " + strNetworkType);
        return strNetworkType;
    }

    /**
     * 获取真实的手机展示
     */
    public ChangeDeviceInfo getRealInfo() {
        ChangeDeviceInfo info = new ChangeDeviceInfo();
        LocationManager locationManager;
        String locationProvider = "";
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        NetworkInfo networkInfo = ((ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        Display display = wm.getDefaultDisplay();
        Integer width = outMetrics.widthPixels;
        Integer height = outMetrics.heightPixels;
        //--------------------------------------------
        //获取地理位置管理器
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(mContext, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
        }
        //获取Location
        Location location = locationManager.getLastKnownLocation(locationProvider);
        if (null != location) {
            info.setLatitude(location.getLatitude());
            info.setLongitude(location.getLongitude());
        }
        //--------------------------------------------
        info.setNetworkType(networkInfo.getType());
        info.setNetworkTypeName(networkInfo.getTypeName());
        info.setNetworkSubType(networkInfo.getSubtype());
        info.setNetworkSubTypeName(networkInfo.getSubtypeName());
        info.setWidth(width.toString());
        info.setHeight(height.toString());
        info.setImei(telephonyManager.getDeviceId());
        info.setImsi(telephonyManager.getSubscriberId());
        info.setPhone(telephonyManager.getLine1Number());
        info.setPhoneStatus(Integer.valueOf(telephonyManager.getPhoneType()).toString());
        info.setSimSerial(telephonyManager.getSimSerialNumber());
        info.setCarrierCode(telephonyManager.getNetworkOperator());
        info.setCarrier(telephonyManager.getNetworkOperatorName());
        info.setCountryCode(telephonyManager.getNetworkCountryIso());
        info.setSimStatus(Integer.valueOf(telephonyManager.getSimState()).toString());
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        info.setMac(wifiInfo.getMacAddress());
        info.setBssid(wifiInfo.getBSSID());
        info.setSsid(wifiInfo.getSSID());
        info.getBuildInfo().setBrand(Build.BRAND);
        info.getBuildInfo().setBoard(Build.BOARD);
        info.getBuildInfo().setManufacture(Build.MANUFACTURER);
        info.getBuildInfo().setDisplay(Build.DISPLAY);
        info.getBuildInfo().setModel(Build.MODEL);
        info.getBuildInfo().setDevice(Build.DEVICE);
        info.getBuildInfo().setCpu_abi(Build.CPU_ABI);
        info.getBuildInfo().setProduct(Build.PRODUCT);
        info.getBuildInfo().setBootloader(Build.BOOTLOADER);
        info.getBuildInfo().setFingerPrint(Build.FINGERPRINT);
        info.getBuildInfo().setHardWare(Build.HARDWARE);
        info.getBuildInfo().setId(Build.ID);
        info.getBuildInfo().setHost(Build.HOST);
        info.getBuildInfo().setTags(Build.TAGS);
        info.getBuildInfo().setType(Build.TYPE);
        info.getBuildInfo().setSerial(Build.SERIAL);
        info.getBuildInfo().setUser(Build.USER);
        info.getBuildInfo().setIncrement(Build.VERSION.INCREMENTAL);
        info.getBuildInfo().setOsVersion(System.getProperty("os.version"));
        info.getBuildInfo().setOsArch(System.getProperty("os.arch"));
        info.getBuildInfo().setOsName(System.getProperty("os.name"));
        info.getBuildInfo().setSdk(Integer.valueOf(Build.VERSION.SDK_INT).toString());
        info.getBuildInfo().setAndroidVersion(Build.VERSION.RELEASE);
        info.setAndroidSerial(getDeviceSerial());
        info.setAndroidId(Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID));
        info.setUa(UserAgentUtil.getInstance().getJavaUserAgent());
        return info;
    }

    public String readSIMCard(int state) {
        StringBuffer sb = new StringBuffer();
        switch (state) { //getSimState()取得sim的状态  有下面6中状态
            case TelephonyManager.SIM_STATE_ABSENT:
                sb.append("无卡");
                break;
            case TelephonyManager.SIM_STATE_UNKNOWN:
                sb.append("未知状态");
                break;
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                sb.append("需要NetworkPIN解锁");
                break;
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                sb.append("需要PIN解锁");
                break;
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                sb.append("需要PUK解锁");
                break;
            case TelephonyManager.SIM_STATE_READY:
                sb.append("良好");
                break;
        }
        return sb.toString();
    }

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
            //如果位置发生变化,重新显示
            System.out.print("getLatitude:" + location.getLatitude());
            System.out.print("getLongitude" + location.getLongitude());
        }
    };
}
