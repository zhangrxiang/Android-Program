package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyClickDemo extends Activity {
	private TextView showView = null;
	private TextView note = null;
	private EditText editNum1 = null;
	private EditText editNum2 = null;
	private Button butAdd = null;
	private Button butSub = null;
	private Button butMul = null;
	private Button butDiv = null;
	private int num1 = 0; // �������������
	private int num2 = 0; // �������������

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		this.showView = (TextView) super.findViewById(R.id.mytext); // ȡ����ʾ���
		this.editNum1 = (EditText) super.findViewById(R.id.myeda); // ȡ�����������
		this.editNum2 = (EditText) super.findViewById(R.id.myedb); // ȡ�����������
		this.butAdd = (Button) super.findViewById(R.id.mybutadd); // ȡ�ò������ܰ�ť
		this.butSub = (Button) super.findViewById(R.id.mybutsub); // ȡ�ò������ܰ�ť
		this.butMul = (Button) super.findViewById(R.id.mybutmul); // ȡ�ò������ܰ�ť
		this.butDiv = (Button) super.findViewById(R.id.mybutdiv); // ȡ�ò������ܰ�ť
		this.note = (TextView) super.findViewById(R.id.note); // ȡ�ò�������ʽ
		this.butAdd.setOnClickListener(new AddListener());
		this.butSub.setOnClickListener(new SubListener());
		this.butMul.setOnClickListener(new MulListener());
		this.butDiv.setOnClickListener(new DivListener());
		this.editNum1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				MyClickDemo.this.editNum1.setText("");
			}

		});
		this.editNum2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				MyClickDemo.this.editNum2.setText("");
			}

		});
	}

	private class AddListener implements OnClickListener {

		public void onClick(View v) {
			MyClickDemo.this.num1 = Integer.parseInt(MyClickDemo.this.editNum1
					.getText().toString()); // ȡ�õ�һ������
			MyClickDemo.this.num2 = Integer.parseInt(MyClickDemo.this.editNum2
					.getText().toString()); // ȡ�õ�һ������
			MyClickDemo.this.note.setText(" + "); // ���²����ķ���
			MyClickDemo.this.showView.setText(String.valueOf(num1 + num2)); // ��ʾ���
		} // �ӷ�����

	}

	private class SubListener implements OnClickListener {

		public void onClick(View v) {
			MyClickDemo.this.num1 = Integer.parseInt(MyClickDemo.this.editNum1
					.getText().toString()); // ȡ�õ�һ������
			MyClickDemo.this.num2 = Integer.parseInt(MyClickDemo.this.editNum2
					.getText().toString()); // ȡ�õ�һ������
			MyClickDemo.this.note.setText(" - "); // ���²����ķ���
			MyClickDemo.this.showView.setText(String.valueOf(num1 - num2)); // ��ʾ���
		} // �ӷ�����

	}

	private class MulListener implements OnClickListener {

		public void onClick(View v) {
			MyClickDemo.this.num1 = Integer.parseInt(MyClickDemo.this.editNum1
					.getText().toString()); // ȡ�õ�һ������
			MyClickDemo.this.num2 = Integer.parseInt(MyClickDemo.this.editNum2
					.getText().toString()); // ȡ�õ�һ������
			MyClickDemo.this.note.setText(" * "); // ���²����ķ���
			MyClickDemo.this.showView.setText(String.valueOf(num1 * num2)); // ��ʾ���
		} // �ӷ�����

	}

	private class DivListener implements OnClickListener {

		public void onClick(View v) {
			MyClickDemo.this.num1 = Integer.parseInt(MyClickDemo.this.editNum1
					.getText().toString()); // ȡ�õ�һ������
			MyClickDemo.this.num2 = Integer.parseInt(MyClickDemo.this.editNum2
					.getText().toString()); // ȡ�õ�һ������
			MyClickDemo.this.note.setText(" �� "); // ���²����ķ���
			MyClickDemo.this.showView.setText(String.valueOf(num1 / num2)); // ��ʾ���
		} // �ӷ�����

	}
}