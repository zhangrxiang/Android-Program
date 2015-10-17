package com.zrx.first.domain;

/**
 * @author zhangrxiang
 * @version 1.0
 * @since 2015/10/17 19:13
 */
public class River {
    private String name;
    private int length;
    private String intro;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "River{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", intro='" + intro + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}


