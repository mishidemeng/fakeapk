package com.turing.fakeapk.bean;

public class Token {

    /**
     * @author turing
     */
    private String timestamp;    //时间long值
    private String random;       //10位数的随机值
    private String signature;    //数据签名
    private String accessToken;  //访问token,后台没有下发时填 ""


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }


    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
