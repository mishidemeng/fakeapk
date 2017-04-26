package com.turing.fakeapk.bean;

/**
 * Created by turingkuang on 2017/3/17.
 */
public class PlatformResolution {
    private String width = "";
    private String height = "";

    public PlatformResolution(String width, String hight) {
        this.width = width;
        this.height = hight;
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
}
