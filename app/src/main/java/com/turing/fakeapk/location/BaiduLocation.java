package com.turing.fakeapk.location;

/**
 * Created by turingkuang on 2017/2/28.
 */
public class BaiduLocation {
    private String address;
    private BaiduContent content;
    private String status;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BaiduContent getContent() {
        return content;
    }

    public void setContent(BaiduContent content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
