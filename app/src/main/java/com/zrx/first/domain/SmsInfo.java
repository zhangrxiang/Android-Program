package com.zrx.first.domain;

/**
 * @author zhangrxiang
 * @version 1.0
 * @since 2015/10/17 13:40
 */
public class SmsInfo {
    private int id;
    private long date;
    private String body;
    private String address;

    public SmsInfo() {
        super();
    }

    public SmsInfo(int id, long date, String body, String address) {
        super();
        this.id = id;
        this.date = date;
        this.body = body;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SmsInfo{" +
                "id=" + id +
                ", date=" + date +
                ", body='" + body + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
