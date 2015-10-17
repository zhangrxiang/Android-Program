package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Hello extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public boolean onCreatOptionMenu(Menu menu){
    	menu.add(Menu.NONE,Menu.FIRST+1,1,"删除").setIcon(android.R.drawable.ic_delete);
    	menu.add(Menu.NONE,Menu.FIRST+2,2,"保存").setIcon(android.R.drawable.ic_secure);
    	menu.add(Menu.NONE,Menu.FIRST+3,3,"添加").setIcon(android.R.drawable.ic_input_add);
    	menu.add(Menu.NONE,Menu.FIRST+4,4,"帮助").setIcon(android.R.drawable.ic_dialog_info);
    	return true;

    }
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	
    	case Menu.FIRST+1:
    		Toast.makeText(this,"删除菜单",Toast.LENGTH_LONG).show();
    	break;
    	case Menu.FIRST+2:
    		Toast.makeText(this,"保存菜单",Toast.LENGTH_LONG).show();
    	break;
    	case Menu.FIRST+3:
    		Toast.makeText(this,"添加菜单", Toast.LENGTH_LONG).show();
    	break;
    	case Menu.FIRST+4:
    		Toast.makeText(this,"帮助菜单", Toast.LENGTH_LONG).show();
    	break;
    		
    	}
    	return false;
    }
    public void onOptionMenuClose(Menu menu){
    	Toast.makeText(this,"关闭菜单",Toast.LENGTH_LONG).show();
    }
    public boolean onPrepareOptionMenu(Menu menu){
    	Toast.makeText(this,"在菜单显示（onCreateOtionMenu()）方法前会调用此操作之中完成一些预处理操作",Toast.LENGTH_LONG).show();
    	return true;
    	
    	
    }

    		
    	}
