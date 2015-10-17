package com.zrx.first.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhang on 2015/10/16.
 */
public class LoginService {
    private static final String INFO = "LoginService";

    public static boolean verify(Context context,String password){
        Map<String, String> userInfo = getUserInfo(context);
         String pwd = userInfo.get("password");
        Log.i(INFO,pwd);
        Log.i(INFO,password);
        return pwd.equals(password);
    }
    public static Map<String, String> getUserInfoBySharedPreferences(Context context) {
        SharedPreferences config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        Map<String, String> all = (Map<String, String>) config.getAll();
        return all;
    }
    public static Map<String, String> getUserInfo(Context context) {
        File file = new File(context.getFilesDir(), "info.txt");
        Map<String,String> map = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String s = "";
            while ((s=bufferedReader.readLine())!=null) {
                String[] split = s.split(":");
                if (split==null)
                    return null;
                if (split.length!=2)
                    return null;
                map.put(split[0],split[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static boolean loginBySharedPreferences(Context context, String username, String password) {
        SharedPreferences config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = config.edit();
        edit.putString("username",username);
        edit.putString("password", password);
        boolean commit = edit.commit();
        return commit;
    }
    public static boolean login(Context context, String username, String password) {
//        File file = new File("/data/data/com.zrx.first/info.txt");
        File filesDir = context.getFilesDir();///data/data/com.zrx.first/files
        File file = new File(filesDir, "info.txt");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(("username:"+username + "\n" +"password:"+ password).getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
