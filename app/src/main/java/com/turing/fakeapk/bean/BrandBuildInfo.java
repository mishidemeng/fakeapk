package com.turing.fakeapk.bean;

public class BrandBuildInfo {

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getBootloader() {
        return bootloader;
    }

    public void setBootloader(String bootloader) {
        this.bootloader = bootloader;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCpu_abi() {
        return cpu_abi;
    }

    public void setCpu_abi(String cpu_abi) {
        this.cpu_abi = cpu_abi;
    }

    public String getCpu_abi2() {
        return cpu_abi2;
    }

    public void setCpu_abi2(String cpu_abi2) {
        this.cpu_abi2 = cpu_abi2;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getRadioVersion() {
        return radioVersion;
    }

    public void setRadioVersion(String radioVersion) {
        this.radioVersion = radioVersion;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getHardWare() {
        return hardWare;
    }

    public void setHardWare(String hardWare) {
        this.hardWare = hardWare;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSdk() {
        return sdk;
    }

    public void setSdk(String sdk) {
        this.sdk = sdk;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIncrement() {
        return increment;
    }

    public void setIncrement(String increment) {
        this.increment = increment;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    private String board = "";
    private String bootloader = "";
    private String brand = "";
    private String cpu_abi = "";
    private String cpu_abi2 = "";
    private String device = "";
    private String display = "";
    private String radioVersion = "";
    private String increment = "";
    private String fingerPrint = "";
    private String hardWare = "";
    private String host = "";
    private String id = "";
    private String manufacture = "";
    private String serial = "";
    private String product = "";
    private String tags = "";
    private String time = "";
    private String type = "";
    private String user = "";
    private String sdk = "";
    private String model = "";
    private String osName = "";
    private String osArch = "";
    private String osVersion = "";
    private String androidVersion = "";
    /** * 获取指定字段信息 * @return
     private String getDeviceInfo(){
     StringBuffer sb =new StringBuffer();
     sb.append("主板："+Build.BOARD);
     sb.append("\n系统启动程序版本号："+ Build.BOOTLOADER);
     sb.append("\n系统定制商："+Build.BRAND);
     sb.append("\ncpu指令集："+Build.CPU_ABI);
     sb.append("\ncpu指令集2："+Build.CPU_ABI2);
     sb.append("\n设置参数："+Build.DEVICE);
     sb.append("\n显示屏参数："+Build.DISPLAY);
     sb.append("\n无线电固件版本："+Build.getRadioVersion());
     sb.append("\n硬件识别码："+Build.FINGERPRINT);
     sb.append("\n硬件名称："+Build.HARDWARE);
     sb.append("\nHOST:"+Build.HOST);
     sb.append("\n修订版本列表："+Build.ID);
     sb.append("\n硬件制造商："+Build.MANUFACTURER);
     sb.append("\n版本："+Build.MODEL);
     sb.append("\n硬件序列号："+Build.SERIAL);
     sb.append("\n手机制造商："+Build.PRODUCT);
     sb.append("\n描述Build的标签："+Build.TAGS);
     sb.append("\nTIME:"+Build.TIME);
     sb.append("\nbuilder类型："+Build.TYPE);
     sb.append("\nUSER:"+Build.USER);
     return sb.toString();
     }*/
}