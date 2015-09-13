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

	private EditText edphoneNumber;//电话号
	private EditText edsmsContent;//短信内容
	private Button   sendBtn;		//按钮控件
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //对象初始化
        this.edphoneNumber=(EditText) this.findViewById(R.id.edphoneNum);//拿到电话号控件
        this.edsmsContent=(EditText) this.findViewById(R.id.edcontent);//拿到内容控件
        this.sendBtn=(Button) this.findViewById(R.id.sendBtn);//拿到按钮控件
        
        
        //btn绑定click事件
        this.sendBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//得到用户输入的电话号
				String telnum=edphoneNumber.getText().toString();
				//得到用户输入的短信内容
				String content=edsmsContent.getText().toString();
				
				//短信管理类 ：写好的了  
				//导包  ：import android.telephony.SmsManager;
				SmsManager smsnager=SmsManager.getDefault();
				//将短信内容通过短信管理内处理 返回list<String>类型集合
				if(content.length()>70){
					//长度大于70拆分
					List<String> smscontent=smsnager.divideMessage(content);
					for(String s:smscontent){
						//发送人和敦信内容
						smsnager.sendTextMessage(telnum.trim(), null, s, null, null);
					}
				}else{
					//直接发送不拆分
					smsnager.sendTextMessage(telnum.trim(), null, "徐斌："+content, null, null);
				}
				//提示一个框
				Toast.makeText(getApplicationContext(), "短信发送成功", 3000).show();
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
