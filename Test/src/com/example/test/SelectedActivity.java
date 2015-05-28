package com.example.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

@SuppressLint("ShowToast")
public class SelectedActivity extends Activity implements OnClickListener{
	String fname;
	Button call;
	TextView tv1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selected);
		fname=getIntent().getExtras().getString("fname");
		tv1=(TextView)findViewById(R.id.textView1);
		tv1.setText(fname);
		call=(Button)findViewById(R.id.call);
		call.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		if(v==call)
	Toast.makeText(getApplicationContext(), "calling "+fname, Toast.LENGTH_LONG).show();
		makecall();
	}
	private void makecall() {
    	try {    		
    		int x=961162012;
    	Intent makecallIntent = new Intent(Intent.ACTION_CALL);
    	makecallIntent.setData(Uri.parse("tel:"+x));
    	startActivity(makecallIntent);
    	} catch (ActivityNotFoundException activityException) {
    	Log.e("Telephony : Make call", "Call failed", activityException);
    	}
    
   
    }
}
