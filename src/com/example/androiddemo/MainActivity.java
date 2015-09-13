package com.example.androiddemo;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText edphoneNumber;//�绰��
	private EditText edsmsContent;//��������
	private Button   sendBtn;		//��ť�ؼ�
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //�����ʼ��
        this.edphoneNumber=(EditText) this.findViewById(R.id.edphoneNum);//�õ��绰�ſؼ�
        this.edsmsContent=(EditText) this.findViewById(R.id.edcontent);//�õ����ݿؼ�
        this.sendBtn=(Button) this.findViewById(R.id.sendBtn);//�õ���ť�ؼ�
        
        
        //btn��click�¼�
        this.sendBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//�õ��û�����ĵ绰��
				String telnum=edphoneNumber.getText().toString();
				//�õ��û�����Ķ�������
				String content=edsmsContent.getText().toString();
				
				//���Ź����� ��д�õ���  
				//����  ��import android.telephony.SmsManager;
				SmsManager smsnager=SmsManager.getDefault();
				//����������ͨ�����Ź����ڴ��� ����list<String>���ͼ���
				if(content.length()>70){
					//���ȴ���70���
					List<String> smscontent=smsnager.divideMessage(content);
					for(String s:smscontent){
						//�����˺Ͷ�������
						smsnager.sendTextMessage(telnum.trim(), null, s, null, null);
					}
				}else{
					//ֱ�ӷ��Ͳ����
					smsnager.sendTextMessage(telnum.trim(), null, "���"+content, null, null);
				}
				//��ʾһ����
				Toast.makeText(getApplicationContext(), "���ŷ��ͳɹ�", 3000).show();
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
