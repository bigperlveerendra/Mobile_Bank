package com.example.mobilebankingapp;

import com.worker.employment.MyWork;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SubscriberWorkInfo extends Activity implements OnClickListener{
	LinearLayout description, days, money, startDate;
	TextView descText, daysText, moneyText, startDateText;
	Button completed;
	int regID;
	MyWork mw = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.work_info);

		Intent i = getIntent();
		mw = (MyWork) i.getExtras().getSerializable("work");

		regID = i.getExtras().getInt("regID");
		init(mw);
	}

	private void init(MyWork m) {
		// TODO Auto-generated method stub
		description = (LinearLayout) findViewById(R.id.descriptionLayout);
		days = (LinearLayout) findViewById(R.id.daysLayout);
		money = (LinearLayout) findViewById(R.id.moneyLayout);
		startDate = (LinearLayout) findViewById(R.id.startDateLayout);

		descText = (TextView) findViewById(R.id.descriptionTextView);
		daysText = (TextView) findViewById(R.id.daysTextView);
		moneyText = (TextView) findViewById(R.id.moneyTextView);
		startDateText = (TextView) findViewById(R.id.startDateTextView);

		descText.setText(m.description);
		daysText.setText(m.days + "");
		moneyText.setText(m.money + "/-");
		startDateText.setText(String.valueOf(m.startDate));

		completed = (Button) findViewById(R.id.RegsiterButton1);
		completed.setText("Work completed");
		completed.setOnClickListener(this);


	}

	@Override
	public void onClick(View v) {
		Context c=getApplicationContext();
		NotificationManager nm=(NotificationManager) c.getSystemService(c.NOTIFICATION_SERVICE);
		Notification n=new Notification(R.drawable.ic_launcher,"registration",System.currentTimeMillis());
	    n.setLatestEventInfo(c, "Pay Slip","sucessfull transaction for work", null);
	    
	    
	    nm.notify(3, n);
	}
	

}
