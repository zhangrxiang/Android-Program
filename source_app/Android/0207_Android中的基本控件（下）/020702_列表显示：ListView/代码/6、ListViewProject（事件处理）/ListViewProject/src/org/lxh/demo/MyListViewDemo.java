package org.lxh.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MyListViewDemo extends Activity {
	private String data[][] = { { "001", "android1" },
			{ "002", "android2" }, { "003", "android3" },
			{ "004", "android4" }, { "005", "android5" },
			{ "006", "android" }, { "007", "android" },
			{ "008", "android" }, { "009", "android" },
			{ "010", "android" } ,
			{ "002", "android2" }, { "003", "android3" },
			{ "004", "android4" }, { "005", "android5" },
			{ "006", "android" }, { "007", "android" },
			{ "008", "android" }, { "009", "android" },
			{ "010", "android" } ,
			{ "002", "android2" }, { "003", "android3" },
			{ "004", "android4" }, { "005", "android5" },
			{ "006", "android" }, { "007", "android" },
			{ "008", "android" }, { "009", "android" },
			{ "010", "android" } ,
			{ "002", "android2" }, { "003", "android3" },
			{ "004", "android4" }, { "005", "android5" },
			{ "006", "android" }, { "007", "android" },
			{ "008", "android" }, { "009", "android" },
			{ "010", "android" } }; 
	// ׼�������ɸ���Ϣ����Щ��Ϣ�Ժ�ͨ��������뵽��Ƕ�����Բ����ļ�֮��
	private ListView datalist = null; // ����ListView���
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>(); // ������ʾ�����ݰ�װ
	private SimpleAdapter simpleAdapter = null; // �������ݵ�ת������
	private TextView info = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.datalist = (ListView) super.findViewById(R.id.datalist); // ȡ�����
		this.info = (TextView) super.findViewById(R.id.info); // ȡ�����
		for (int x = 0; x < this.data.length; x++) {
			Map<String, String> map = new HashMap<String, String>(); // ����Map���ϣ�����ÿһ������
			map.put("_id", this.data[x][0]); // ��data_list.xml�е�TextView���ƥ��
			map.put("name", this.data[x][1]); // ��data_list.xml�е�TextView���ƥ��
			this.list.add(map); // ���������е�������
		}
		this.simpleAdapter = new SimpleAdapter(this, this.list,
				R.layout.data_list, new String[] { "_id", "name" } // Map�е�key������
				, new int[] { R.id._id, R.id.name }); // ��data_list.xml�ж�����������ԴID
		this.datalist.setAdapter(this.simpleAdapter);
		this.datalist.setOnItemClickListener(new OnItemClickListenerImpl()); // ����ѡ��
	}

	private class OnItemClickListenerImpl implements OnItemClickListener {

		@SuppressWarnings("unchecked")
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Map<String, String> map = (Map<String, String>) MyListViewDemo.this.simpleAdapter
					.getItem(position);
			String _id = map.get("_id");
			String name = map.get("name");
			MyListViewDemo.this.info.setText("ѡ�е�������ID�ǣ�" + _id + "�������ǣ�"
					+ name);
		}
	}
}