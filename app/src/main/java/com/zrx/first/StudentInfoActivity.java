package com.zrx.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.zrx.first.domain.Student;
import com.zrx.first.service.StudentService;

import java.util.ArrayList;

public class StudentInfoActivity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private EditText number;
    private ListView listViewInfo;
    private StudentService studentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        number = (EditText) findViewById(R.id.number);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String nameStr = extras.getString("name");
        String passwordStr = extras.getString("password");
        long numberLong = extras.getLong("number");
        name.setText(nameStr);
        password.setText(passwordStr);
        number.setText(String.valueOf(numberLong));
        listViewInfo = (ListView) findViewById(R.id.lv_info);
        studentService = new StudentService(this);
        listViewInfo.setAdapter(new BaseAdapter() {
            ArrayList<Student> all = studentService.findAll();

            @Override
            public int getCount() {
                return all.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.i("MyAdapter", "获取第" + position + "位置！");
                Student student = all.get(position);
                View view = View.inflate(getApplicationContext(),
                        R.layout.listview_item, null);
                TextView tv_id = (TextView) view.findViewById(R.id.tv_id);
                TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
                TextView tv_password = (TextView) view.findViewById(R.id.tv_password);
                TextView tv_number = (TextView) view.findViewById(R.id.tv_number);
                tv_id.setText(student.getId() + "");
                tv_name.setText(student.getName());
                tv_password.setText(student.getPassword());
                tv_number.setText(student.getNumber() + "");
                return view;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_info, menu);
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
