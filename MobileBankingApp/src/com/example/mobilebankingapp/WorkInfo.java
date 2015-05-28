package com.example.mobilebankingapp;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.worker.employment.MyRegisteredWork;
import com.worker.employment.MyWork;

public class WorkInfo extends Activity implements OnClickListener {

	LinearLayout description, days, money, startDate;
	TextView descText, daysText, moneyText, startDateText;
	Button register;
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

		register = (Button) findViewById(R.id.RegsiterButton1);
		register.setOnClickListener(this);
//		workComplete = (Button) findViewById(R.id.CompletedButton);
//
//		workComplete.setClickable(false);

	}

	@SuppressLint("NewApi")
	class RegisterTask extends AsyncTask<Void, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				String request = "http://"+Config.getIP()+":8080/WorkingEmployee/RegisterForWork";
				URL url = new URL(request);

				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				ObjectOutputStream oos = new ObjectOutputStream(
						conn.getOutputStream());
				MyRegisteredWork m = new MyRegisteredWork(regID, mw.id);
				oos.writeObject(m);
				oos.flush();
				oos.close();

				DataInputStream dis = new DataInputStream(conn.getInputStream());
				String s = dis.readLine();
				Log.i("abhsihek", s);
				return new Boolean(s);// reading true r false
				// dis.close();

			} catch (Exception e) {
				e.printStackTrace();

			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (!result) {
				register.setClickable(false);
				Toast.makeText(getApplicationContext(), "alreadt registerd",
						500).show();
			}
			if (result) {
				Context c = getApplicationContext();
				NotificationManager nm = (NotificationManager) c
						.getSystemService(c.NOTIFICATION_SERVICE);
				Notification n = new Notification(R.drawable.ic_launcher,
						"work registration", System.currentTimeMillis());
				n.setLatestEventInfo(c, "registration",
						"sucess registring for job", null);

				nm.notify(2, n);
			}

		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.RegsiterButton1:
			new RegisterTask().execute(new Void[] {});
			register.setClickable(false);
			//workComplete.setClickable(true);

			break;
		
		}

	}

}
