package com.zrx.first.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author zhangrxiang
 * @version 1.0
 * @since 2015/10/17 22:28
 */
public class Student implements Parcelable {
    private int id;
    private String name;
    private String password;
    private long number;

    public Student() {
    }

    public Student(String name, String password, long number) {
        this.name = name;
        this.password = password;
        this.number = number;
    }

    public Student(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Student(int id, String name, String password, long number) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.number = number;
    }

    protected Student(Parcel in) {
        id = in.readInt();
        name = in.readString();
        password = in.readString();
        number = in.readLong();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", number=" + number +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(password);
        dest.writeLong(number);
    }

    // 必须要创建一个名叫CREATOR的常量。
    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        //重写createFromParcel方法，创建并返回一个获得了数据的user对象
        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
