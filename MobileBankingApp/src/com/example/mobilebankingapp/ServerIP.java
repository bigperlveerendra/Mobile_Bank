package com.example.mobilebankingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ServerIP extends Activity {
	
	EditText address;
	Button submit;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.ip_set);
	address=(EditText)findViewById(R.id.serverIpEditText);
	submit=(Button)findViewById(R.id.button_submit);
	submit.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String ip=address.getText().toString();
			Config.setIP(ip);
			Intent i=new Intent(getApplicationContext(), MainActivity.class);
			startActivity(i);
			
		}
	});
}

}
