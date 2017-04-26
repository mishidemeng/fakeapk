package com.turing.fakeapk.bean;

/**
 * Created by turingkuang on 2017/1/7.
 */
public class ChangeDeviceInfo {

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneStatus() {
        return phoneStatus;
    }

    public void setPhoneStatus(String phoneStatus) {
        this.phoneStatus = phoneStatus;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getSimStatus() {
        return simStatus;
    }

    public void setSimStatus(String simStatus) {
        this.simStatus = simStatus;
    }

    public String getSimSerial() {
        return simSerial;
    }

    public void setSimSerial(String simSerial) {
        this.simSerial = simSerial;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getAndroidSerial() {
        return androidSerial;
    }

    public void setAndroidSerial(String androidSerial) {
        this.androidSerial = androidSerial;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getNetworkType() {
        return networkType;
    }

    public void setNetworkType(Integer networkType) {
        this.networkType = networkType;
    }

    public String getNetworkTypeName() {
        return networkTypeName;
    }

    public void setNetworkTypeName(String networkTypeName) {
        this.networkTypeName = networkTypeName;
    }

    public Integer getNetworkSubType() {
        return networkSubType;
    }

    public void setNetworkSubType(Integer networkSubType) {
        this.networkSubType = networkSubType;
    }

    public String getNetworkSubTypeName() {
        return networkSubTypeName;
    }

    public void setNetworkSubTypeName(String networkSubTypeName) {
        this.networkSubTypeName = networkSubTypeName;
    }

    public String getBlueTooth() {
        return blueTooth;
    }

    public void setBlueTooth(String blueTooth) {
        this.blueTooth = blueTooth;
    }

    public BrandBuildInfo getBuildInfo() {
        return buildInfo;
    }

    public void setBuildInfo(BrandBuildInfo buildInfo) {
        this.buildInfo = buildInfo;
    }

    private String imei = "";//TAC + FAC + SNR + SP = 15
    private String imsi = "";// MCC+MNC+MSIN
    private String mac = "";//"6C:C4:08:BB:B1:28"
    private String bssid = "";//
    private String ssid = "";// wifi name
    private String phone = "";
    private String phoneStatus = ""; // 手机状态
    private String width = "";
    private String height = "";
    private String carrier = "";
    private String carrierCode = "";
    private String countryCode = "CN";
    private Double latitude = 0D; // 纬度
    private Double longitude = 0D;// 经度
    private String simStatus = ""; // 手机卡状态
    private String simSerial = "";
    private String androidId = "";
    private String androidSerial = "";
    private String ua = "";
    private String ip = "";
    private Integer networkType = 0; // 网络类型，wifi,mobile
    private String networkTypeName = ""; // wifi,mobile
    private Integer networkSubType = 0;
    private String networkSubTypeName = "";
    private String blueTooth = ""; //蓝牙地址
    private BrandBuildInfo buildInfo = new BrandBuildInfo();
}