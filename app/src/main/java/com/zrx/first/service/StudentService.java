package com.zrx.first.service;

import android.content.Context;
import android.util.Log;

import com.zrx.first.dao.StudentDao;
import com.zrx.first.domain.Student;
import com.zrx.first.exception.StudentExistException;

import java.util.ArrayList;

/**
 * @author zhangrxiang
 * @version 1.0
 * @since 2015/10/17 23:54
 */
public class StudentService {
    private static final String I = "StudentService";
    private StudentDao studentDao;

    public StudentService(Context context) {
        studentDao = new StudentDao(context);
    }

    public Student login(Student student) {
        return studentDao.find(student);

    }

    public ArrayList<Student> findAll() {
        ArrayList<Student> all = studentDao.findAll();
        return all;
    }

    public boolean register(Student student) {
        try {
            studentDao.add(student);
        } catch (StudentExistException e) {
            e.printStackTrace();
            Log.i(I, "用户名已经存在");
            return false;
        }
        return true;
    }

    public boolean register() {
        return false;
    }


}
