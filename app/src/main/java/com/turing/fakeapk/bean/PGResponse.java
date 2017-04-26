package com.turing.fakeapk.bean;

public class PGResponse<T> {
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 0成功
     *
     * @param status
     * @param msg
     * @param data
     * @author turing
     */

    public void setAllData(int status, String msg, T data) {
        this.status = status;
        this.msg = "";//SpringUtils.getMessage(msg, args);
        this.data = data;
    }

    public void setAllData(int status, String msg) {
        this.setAllData(status, msg, null);
    }

    public void setOriginalData(int status, String msg) {
        this.setOriginalData(status, msg, null);
    }

    public void setOriginalData(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private int status = Const.common_unknown;
    private String msg = "";//SpringUtils.getMessage("common_unknown");
    private T data;
}
