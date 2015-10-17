package com.zrx.first.service;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import com.zrx.first.domain.SmsInfo;

import org.apache.commons.io.IOUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zhangrxiang
 * @version 1.0
 * @since 2015/10/17 13:41
 */
public class SmsInfoService {
    private static final String I = "SmsInfoService";
    private static ArrayList<SmsInfo> infos = new ArrayList<SmsInfo>();

    public static void setSmsInfo() {
        int number = 135000;
        for (int i = 0; i < 10; i++) {
            infos.add(new SmsInfo(i, System.currentTimeMillis(), "短信" + i,
                    number + i + ""));
        }
    }

    public static String test(Context context) {
        String str = "";
        File filesDir = context.getFilesDir();
        File file = new File(filesDir, "sms.xml");
        boolean exists = file.exists();
        boolean b = file.canRead();
        Log.i(I, "文件是否存在？" + exists);
        Log.i(I, "文件是否可读？" + b);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            // byte[] bytes = new byte[1024];
            // fileInputStream.read(bytes, 0, bytes.length);
            str = IOUtils.toString(fileInputStream);
            // String string = Arrays.toString(bytes);
            // Log.i(I,string);
            Log.i(I, str);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static ArrayList<SmsInfo> getSmsInfo(Context context) {
        ArrayList<SmsInfo> smsInfos = null;
        SmsInfo smsInfo = null;
        File filesDir = context.getFilesDir();
        File file = new File(filesDir, "sms.xml");
        InputStream inputStream = null;
        XmlPullParser xmlPullParser = null;
        try {
            inputStream = new FileInputStream(file);
            xmlPullParser = Xml.newPullParser();
            xmlPullParser.setInput(inputStream, "utf-8");
            // String string = IOUtils.toString(inputStream);
            //Log.i(I,string);
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if ("smss".equals(xmlPullParser.getName())) {
                            smsInfos = new ArrayList<SmsInfo>();
                        } else if ("sms".equals(xmlPullParser.getName())) {
                            smsInfo = new SmsInfo();
                            smsInfo.setId(Integer.parseInt(xmlPullParser.getAttributeValue(0)));
                        } else if ("date".equals(xmlPullParser.getName())) {
                            smsInfo.setDate(Long.parseLong(xmlPullParser.nextText()));
                        } else if ("address".equals(xmlPullParser.getName())) {
                            smsInfo.setAddress(xmlPullParser.nextText());
                        } else if ("body".equals(xmlPullParser.getName())) {
                            smsInfo.setBody(xmlPullParser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("sms".equals(xmlPullParser.getName())) {
                            smsInfos.add(smsInfo);
                            smsInfo = null;
                            break;
                        }
                }
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            Toast.makeText(context, "显示备份失败", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, "显示备份失败", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "显示备份失败", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(context, "显示备份", Toast.LENGTH_SHORT).show();
        return smsInfos;
    }

    public static void saveSmsInfo(Context context) {
        setSmsInfo();
        XmlSerializer xmlSerializer = Xml.newSerializer();
        File file = new File(Environment.getExternalStorageDirectory(),
                "config.xml");
        file = new File(context.getFilesDir(), "sms.xml");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            xmlSerializer.setOutput(fos, "utf-8");
            xmlSerializer.startDocument("utf-8", true);

            xmlSerializer.startTag(null, "smss");

            for (SmsInfo info : infos) {
                xmlSerializer.startTag(null, "sms");
                xmlSerializer.attribute(null, "id", info.getId() + "");

                xmlSerializer.startTag(null, "date");
                xmlSerializer.text(info.getDate() + "");
                xmlSerializer.endTag(null, "date");

                xmlSerializer.startTag(null, "address");
                xmlSerializer.text(info.getAddress());
                xmlSerializer.endTag(null, "address");

                xmlSerializer.startTag(null, "body");
                xmlSerializer.text(info.getBody());
                xmlSerializer.endTag(null, "body");

                xmlSerializer.endTag(null, "sms");
            }
            xmlSerializer.endTag(null, "smss");

            xmlSerializer.endDocument();
            fos.close();
            Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "保存失败", Toast.LENGTH_SHORT).show();
        }

    }

}
