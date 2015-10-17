package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MyMenuDemo extends Activity {
	private String data[] = new String[] { "����ħ�ֿƼ�", "www.mldnjava.cn",
			"��ʦ�����˻�", "�й���У����", "www.jiangker.com" }; // ����ListView����ʾ��
	private ListView listView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.listView = new ListView(this);
		this.listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, this.data));
		super.setContentView(this.listView);
		super.registerForContextMenu(this.listView) ;
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Menu.FIRST + 1:
			Toast.makeText(this, "��ѡ����ǡ������ϵ�ˡ���", Toast.LENGTH_LONG).show();
			break;
		case Menu.FIRST + 2:
			Toast.makeText(this, "��ѡ����ǡ��鿴���顱��", Toast.LENGTH_LONG).show();
			break;
		case Menu.FIRST + 3:
			Toast.makeText(this, "��ѡ����ǡ�ɾ����Ϣ����", Toast.LENGTH_LONG).show();
			break;
		case Menu.FIRST + 4:
			Toast.makeText(this, "��ѡ����ǡ����Ϊ����", Toast.LENGTH_LONG).show();
			break;
		case Menu.FIRST + 5:
			Toast.makeText(this, "��ѡ����ǡ��༭����", Toast.LENGTH_LONG).show();
			break;
		}
		return false;
	}

	@Override
	public void onContextMenuClosed(Menu menu) {
		Toast.makeText(this, "�����Ĳ˵��ر���", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("��Ϣ����");
		super.getMenuInflater().inflate(R.menu.mymenu, menu) ;
	}
}