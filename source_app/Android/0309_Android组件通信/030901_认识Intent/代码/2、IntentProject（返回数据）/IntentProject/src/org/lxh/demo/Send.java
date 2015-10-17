package org.lxh.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Send extends Activity {
	private Button mybut = null ;
	private TextView msg = null ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.send_main);
		this.mybut = (Button) super.findViewById(R.id.mybut) ;
		this.msg = (TextView) super.findViewById(R.id.msg) ;
		this.mybut.setOnClickListener(new OnClickListenerImpl()) ;
	}
	private class OnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent it = new Intent(Send.this, Receive.class);
			it.putExtra("myinfo", "����ħ�ֿƼ����ѧԺ��www.mldnjava.cn��") ;
			Send.this.startActivityForResult(it, 1) ;
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(resultCode) {
		case RESULT_OK:
			Send.this.msg.setText("���ص������ǣ�" + data.getStringExtra("retmsg")) ;
			break ;
		case RESULT_CANCELED:
			Send.this.msg.setText("����ȡ����") ;
			break ;
		default:
			break;
		}
	}
	
}