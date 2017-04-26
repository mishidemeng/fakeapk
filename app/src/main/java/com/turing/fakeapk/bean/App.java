/**
 * Title：App
 * Copyright: Copyright (c) 2016
 * Company: turing
 *
 * @author turing
 * @version 1.0, 2017年01月24日
 * @since 2017年01月24日
 */

package com.turing.fakeapk.bean;

import java.util.Date;

/**App*/
public class App {

    private Long id;

    /***/
    private Long userId;

    /***/
    private Long platformId;

    /***/
    private String appKey;

    /***/
    private String channel;

    /***/
    private String name;

    /***/
    private String packageName;

    /***/
    private String apkPath;

    /***/
    private String token;

    /***/
    private Integer isCanRun;

    /***/
    private Integer isClose;

    /***/
    private Date createDate;

    /***/
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getApkPath() {
        return apkPath;
    }

    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getIsCanRun() {
        return isCanRun;
    }

    public void setIsCanRun(Integer isCanRun) {
        this.isCanRun = isCanRun;
    }

    public Integer getIsClose() {
        return isClose;
    }

    public void setIsClose(Integer isClose) {
        this.isClose = isClose;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
