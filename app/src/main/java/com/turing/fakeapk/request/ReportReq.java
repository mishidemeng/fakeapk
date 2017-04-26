package com.turing.fakeapk.request;

import com.turing.fakeapk.bean.ChangeDeviceInfo;

public class ReportReq extends BaseReq {
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public ChangeDeviceInfo getDevice() {
        return device;
    }

    public void setDevice(ChangeDeviceInfo device) {
        this.device = device;
    }

    public int getDevice_type() {
        return device_type;
    }

    public void setDevice_type(int device_type) {
        this.device_type = device_type;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public int getIs_remain() {
        return is_remain;
    }

    public void setIs_remain(int is_remain) {
        this.is_remain = is_remain;
    }

    private String accessToken; // 访问token,前期为空
    private Long appId;
    private ChangeDeviceInfo device;
    private int device_type; // 0: 模拟器 ，1:真机
    private int is_active;    // 1:active 0:inactive
    private int is_remain;    // 1:为留存数据 0:不为留存数据
}
