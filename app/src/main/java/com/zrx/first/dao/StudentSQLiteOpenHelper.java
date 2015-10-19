package com.zrx.first.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author zhangrxiang
 * @version 1.0
 * @since 2015/10/17 22:27
 */
public class StudentSQLiteOpenHelper extends SQLiteOpenHelper {
    public StudentSQLiteOpenHelper(Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table student(id integer primary key autoincrement,name varchar(20),password varchar(20)" +
                ",number integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
