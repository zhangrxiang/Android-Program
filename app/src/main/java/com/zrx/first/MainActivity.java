package com.zrx.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.zrx.first.service.LoginService;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final String I = "MainActivity";
    private EditText username;
    private EditText password;
    private Button login;
    private Button reset;
    private CheckBox rememberPassword;
    private Map<String, String> map;
    boolean flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username_et);
        password = (EditText) findViewById(R.id.password_et);
        login = (Button) findViewById(R.id.login);
        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText(null);
                password.setText(null);
            }
        });
        rememberPassword = (CheckBox) findViewById(R.id.rememberPassword);
        //getBack(LoginService.getUserInfo(this));
        getBack(LoginService.getUserInfoBySharedPreferences(this));//表单回显
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(MainActivity.this, "用户名或密码为空，登陆失败", Toast.LENGTH_SHORT).show();
                } else {
                    if (!LoginService.verify(MainActivity.this, pwd)) {
                        Toast.makeText(MainActivity.this, "密码错误，登陆失败", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(MainActivity.this, "登陆", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this,LoginActivity.class);
                    MainActivity.this.startActivity(i);
                    if (rememberPassword.isChecked()) {
                        Log.i(I, name);
                        Log.i(I, pwd);
//                        boolean login = LoginService.login(MainActivity.this, name, pwd);
                        boolean login = LoginService.loginBySharedPreferences(MainActivity.this, name, pwd);
                        if (login && !flag) {
                            Toast.makeText(MainActivity.this, "登陆，保存用户信息", Toast.LENGTH_LONG).show();
                        } else if (!flag) {
                            Toast.makeText(MainActivity.this, "登陆，保存用户信息失败", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

    private void getBack(Map<String,String> userInfo) {
        if (userInfo != null) {
            Set<String> strings = userInfo.keySet();
            Iterator<String> iterator = strings.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                Log.i(I, next);
                String str = userInfo.get(next);
                Log.i(I, str);
            }
            username.setText(userInfo.get("username"));
            // password.setText(userInfo.get("password"));
            flag = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
