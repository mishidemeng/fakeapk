package com.turing.fakeapk.location;

/**
 * Created by turingkuang on 2017/2/28.
 */
public class BaiduContent {
    private String address;
    private BaiduAddressDetail address_detail;
    private BaiduPoint point;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BaiduAddressDetail getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(BaiduAddressDetail address_detail) {
        this.address_detail = address_detail;
    }

    public BaiduPoint getPoint() {
        return point;
    }

    public void setPoint(BaiduPoint point) {
        this.point = point;
    }
}
