package com.zrx.first.service;

import com.zrx.first.domain.River;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangrxiang
 * @version 1.0
 * @since 2015/10/17 19:14
 */
public class RiverService {
    public static List<River> parserXMLPULL(InputStream is)
    {
        List<River> list = null;
        River river = null;
//	   XmlPullParserException
//	   XmlSerializer

//	   方式一：
//	   XmlPullParser parser = Xml.newPullParser();
//	   方法二：
        XmlPullParserFactory xpf = null;
        XmlPullParser parser = null;
        try {
            xpf = XmlPullParserFactory.newInstance();
            parser = xpf.newPullParser();
//          将XML文件以流的形式加入，并设置XML文件的编码方式
//			parser.setInput(InputStrean inputStream, String inputEncoding)
//          parser.setInput(Reader reader)
            parser.setInput(is, "UTF-8");
//          此时文档刚初始化，所以解析的位置在文档的开头
            int type = parser.getEventType();//此时返回０，也就是在START_DOCUMENT
//          返回类型START_DOCUMENT,END_DOCUMENT,START_TAG,END_TAG,TEXT
            while(type!= XmlPullParser.END_DOCUMENT)
            {
                switch(type)
                {
                    case XmlPullParser.START_DOCUMENT:
                        //做一些初始化工作
                        list = new ArrayList<River>();
                        break;
                    case XmlPullParser.START_TAG:
                        //rivers
                        String name = parser.getName();
                        if(name.equals("river"))
                        {
                            String attrName = parser.getAttributeValue(0);
                            int attrLen = Integer.parseInt(parser.getAttributeValue(1));
                            river = new River();
                            river.setName(attrName);
                            river.setLength(attrLen);
                        }
                        if(parser.getName().equals("introduction")&&river!=null)
                        {
                            String intro = parser.nextText();
                            river.setIntro(intro);
                        }
                        if("imageurl".equals(parser.getName())&&river!=null)
                        {
                            String url = parser.nextText();
                            river.setUrl(url);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String value = parser.getName();
                        if(value.equals("river")&&river!=null&&list!=null)
                        {
//					   添加对象到list中
                            list.add(river);
                            river = null;
                        }
                        break;
                }
                type = parser.next();//当前解析位置结束，指向下一个位置
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
}
