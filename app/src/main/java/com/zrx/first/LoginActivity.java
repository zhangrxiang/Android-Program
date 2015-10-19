package com.zrx.first;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.zrx.first.domain.River;
import com.zrx.first.domain.SmsInfo;
import com.zrx.first.domain.Student;
import com.zrx.first.service.RiverService;
import com.zrx.first.service.SmsInfoService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final String I = "LoginActivity";
    private EditText sd_total;
    private EditText sd_available;
    private EditText in_total;
    private EditText in_available;
    private TextView showText;
    private ListView listViewMyselfInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sd_total = (EditText) findViewById(R.id.sd_total_et);
        sd_available = (EditText) findViewById(R.id.sd_available_et);
        in_total = (EditText) findViewById(R.id.in_total_et);
        in_available = (EditText) findViewById(R.id.in_available_et);
//         showText = (TextView) findViewById(R.id.show);
        getSDSize();
        getROMInfo();
        //  showInfo();
    }

    public void myselfInfo(View view) {
        Intent intent = getIntent();
        Student student = intent.getExtras().getParcelable("student");
        Log.i(I, student.toString());
        listViewMyselfInfo = (ListView) findViewById(R.id.lv_myself_info);
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> s = new HashMap<String, Object>();
        s.put("id", student.getId());
        s.put("name", student.getName());
        s.put("password", student.getPassword());
        s.put("number", student.getNumber());
        data.add(s);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.listview_item,
                new String[]{"id", "name", "password", "number"}, new int[]{R.id.tv_id, R.id.tv_name, R.id.tv_password, R.id.number});
//        String[] str = {String.valueOf(student.getId()),student.getName(), student.getPassword(),String.valueOf(student.getNumber())};
//        ListAdapter arrayAdapter = new ArrayAdapter<String>(this,R.layout.listview_item,R.id.lv_myself_info,str);
//        listViewMyselfInfo.setAdapter(arrayAdapter);
        listViewMyselfInfo.setAdapter(simpleAdapter);

    }

//    private void showRiverInfo() {
//        InputStream is = getClass().getClassLoader().getResourceAsStream("river.xml");
//        List<River> rivers = RiverService.parserXMLPULL(is);
//        StringBuffer stringBuffer = new StringBuffer();
//        for (River river : rivers){
//            stringBuffer.append(river);
//        }
//        showText.setText(stringBuffer.toString());
//    }
//    private void showInfo() {
//        ArrayList<SmsInfo> smsInfos = SmsInfoService.getSmsInfo(this);
//        StringBuffer stringBuffer = new StringBuffer();
//        for (SmsInfo smsInfo: smsInfos){
//            stringBuffer.append(smsInfo);
//        }
//        showText.setText(stringBuffer.toString());
//    }

//    public void saveInfo(View view) {
//        SmsInfoService.saveSmsInfo(this);
//        showInfo();
//        showRiverInfo();
//        showText.setText(SmsInfoService.test(this));
//    }

    private void getSDSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        long availableBlocks = stat.getAvailableBlocks();

        long totalSize = blockSize * totalBlocks;
        long availSize = blockSize * availableBlocks;

        String totalStr = Formatter.formatFileSize(this, totalSize);
        String availStr = Formatter.formatFileSize(this, availSize);
        sd_total.setText(totalStr);
        sd_available.setText(availStr);
        // return "cdcard总内存：" + totalStr + "\n" + "可用内存：" + availStr;
    }

    public String getROMInfo() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        long availableBlocks = stat.getAvailableBlocks();

        long totalSize = blockSize * totalBlocks;
        long availSize = blockSize * availableBlocks;

        String totalStr = Formatter.formatFileSize(this, totalSize);
        String availStr = Formatter.formatFileSize(this, availSize);
        in_total.setText(totalStr);
        in_available.setText(availStr);
        return "手机总内存：" + totalStr + "\n" + "可用内存：" + availStr;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
