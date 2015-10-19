package com.zrx.first;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.zrx.first.dao.StudentSQLiteOpenHelper;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    public void test(){
        System.out.println("hehe");
        Log.i("AndroidTest", "这是一个测试");
    }

    public void testDB() {
        StudentSQLiteOpenHelper studentSQLiteOpenHelper = new StudentSQLiteOpenHelper(getContext());
        studentSQLiteOpenHelper.getWritableDatabase();
    }
}