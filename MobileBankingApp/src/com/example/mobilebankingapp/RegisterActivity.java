package com.example.mobilebankingapp;

import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener {

	RadioButton male, female;
	RadioGroup gender;
	EditText username, password, age, phone;
	Button register;
	TextView resultTextview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regsiter_layout);
		initalize();

	}

	@SuppressLint("NewApi")
	class MyTest extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			String username = params[0];
			String password = params[1];
			String age = params[2];
			String gender = params[3];
			String phone = params[4];
			;
			String result = test(username, password, age, gender, phone);
			return result;

		}

		@Override
		protected void onPostExecute(String result) {
			
			if (result.contains("registered")) {
				Toast.makeText(getApplicationContext(), result+" please login", 3000).show();
//				Intent i = new Intent(getApplicationContext(),
//						MainActivity.class);
//				startActivity(i);
				Intent i=new Intent("com.my.signal");
				i.putExtra("message","sucessful registration");
				getApplicationContext().sendBroadcast(i);
				
				
				
			} else {
				Toast.makeText(getApplicationContext(), result, 3000).show();
			}

			
		}

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	private void initalize() {
		male = (RadioButton) findViewById(R.id.radio0Male);
		female = (RadioButton) findViewById(R.id.radio1Female);
		gender = (RadioGroup) findViewById(R.id.radioGroup1);

		register = (Button) findViewById(R.id.RegisterButton);
		register.setOnClickListener(this);

		username = (EditText) findViewById(R.id.usernameEditText);
		password = (EditText) findViewById(R.id.editText1);
		age = (EditText) findViewById(R.id.ageEditText);
		phone = (EditText) findViewById(R.id.phoneEditText);

	}

	public String test(String username, String password, String age,
			String phone, String gender) {

		try {
			String getRequest = "http://"+Config.getIP()+":8080/MobileBankingApp/Register?";
			String params = "username='" + username + "'&password='" + password
					+ "'&age='" + age + "'&phone='" + phone + "'&gender='"
					+ gender + "'";

			// String
			// u="http://localhost:8080/WorkingEmployee/Register?username=kaushik&passowrd=abhi&phone=8095&gender=male";
			URL url = new URL(getRequest + params);
			// url = new
			// URL("http://192.168.1.2:8080/WorkingEmployee/Register");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			DataInputStream dis = new DataInputStream(conn.getInputStream());
			String line = dis.readLine();

			// dis.close();
			return line;// registered

		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}

	}

	@Override
	public void onClick(View v) {

		// make request
		// test();

		String username, password, age, gender = null, phone;

		switch (v.getId()) {
		case R.id.RegisterButton:

			username = this.username.getText().toString();
			password = this.password.getText().toString();
			age = this.age.getText().toString();
			phone = this.phone.getText().toString();
			if (this.gender.getCheckedRadioButtonId() == R.id.radio0Male)
				gender = "male";
			if (this.gender.getCheckedRadioButtonId() == R.id.radio1Female)
				gender = "female";

			String result = username + " " + password + " " + age + " " + phone
					+ " " + gender;

			Toast.makeText(getApplicationContext(), result, 2000).show();

			String[] array = new String[5];
			// { username, password, age, gender, phone };
			array[0] = username;
			array[1] = password;
			array[2] = age;
			array[3] = gender;
			array[4] = phone;
			new MyTest().execute(array);
			break;

		}
	}
}
