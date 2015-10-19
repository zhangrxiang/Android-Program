package com.zrx.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zrx.first.domain.Student;
import com.zrx.first.service.StudentService;

public class RegisterActivity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private EditText number;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        number = (EditText) findViewById(R.id.number);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr = name.getText().toString().trim();
                String passwordStr = password.getText().toString().trim();
                String numberStr = number.getText().toString().trim();
//                if (nameStr != null && passwordStr != null && numberStr != null&&!nameStr.equals("") && !passwordStr.equals("") && !numberStr.equals("")) {
                if (nameStr.equals("")) {
                    Toast.makeText(RegisterActivity.this, "用户名为空", Toast.LENGTH_SHORT).show();
                } else {
                    long numberLong = Long.parseLong(numberStr);
                    Toast.makeText(RegisterActivity.this, nameStr + passwordStr + numberLong, Toast.LENGTH_SHORT).show();
                    StudentService studentService = new StudentService(RegisterActivity.this);
                    boolean register = studentService.register(new Student(nameStr, passwordStr, numberLong));
                    if (register) {
                        Intent i = new Intent(RegisterActivity.this, StudentInfoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name", nameStr);
                        bundle.putString("password", passwordStr);
                        bundle.putLong("number", numberLong);
                        i.putExtras(bundle);
                        startActivity(i);
                    } else {
                        Toast.makeText(RegisterActivity.this, "注册失败，用户名已经存在", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
