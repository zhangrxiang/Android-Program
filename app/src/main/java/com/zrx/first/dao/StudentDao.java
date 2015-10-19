package com.zrx.first.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zrx.first.domain.Student;
import com.zrx.first.exception.StudentExistException;

import java.util.ArrayList;

/**
 * @author zhangrxiang
 * @version 1.0
 * @since 2015/10/17 22:48
 */
public class StudentDao {
    StudentSQLiteOpenHelper helper = null;

    public StudentDao(Context context) {
        super();
        this.helper = new StudentSQLiteOpenHelper(context);
    }

    public void add(Student student) throws StudentExistException {
//        SQLiteDatabase db = this.helper.getWritableDatabase();
//        db.execSQL("insert into student(name,password,number) values(?,?,?)", new Object[]{
//               student.getName(),student.getPassword(),student.getNumber()});
//        db.close();
        if (find(student.getName())) {
            throw new StudentExistException();
        } else {
            add(student.getName(), student.getPassword(), student.getNumber());
        }
    }

    public void add(String name, String password, long number) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        db.execSQL("insert into student(name,password,number) values(?,?,?)", new Object[]{
                name, password, number});
        db.close();
    }

    public Student find(Student student) {
        Student s = null;
        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from student where name=? and password = ?",
                new String[]{student.getName(), student.getPassword()});
        if (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            long number = cursor.getLong(cursor.getColumnIndex("number"));
            s = new Student(id, name, password, number);
        }
        cursor.close();
        db.close();
        return s;
    }

    public boolean find(String name) {
        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from student where name=?",
                new String[]{name});
        boolean result = cursor.moveToNext();
        cursor.close();
        db.close();
        return result;
    }

    public void update(String name, String newNumber) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        db.execSQL("update student set number=? where name=?", new Object[]{
                newNumber, name});
        db.close();
    }

    public void delete(String name) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        db.execSQL("delete from student where name=?", new Object[]{name});
        db.close();
    }

    public ArrayList<Student> findAll() {
        SQLiteDatabase db = this.helper.getReadableDatabase();
        ArrayList<Student> students = new ArrayList<Student>();
        Cursor cursor = db.rawQuery("select * from student", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            long number = cursor.getLong(cursor.getColumnIndex("number"));
            Student student = new Student(id, name, password, number);
            students.add(student);
        }
        cursor.close();
        db.close();
        return students;

    }


}
