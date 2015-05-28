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

	
	EditText name,address,phone,email;
	Button submit;
	TextView nametv,addstv,phonetv,emailtv;
	GPSTracker gps;
	double latitude = 0,longitude = 0;
	LocationAddress locationAddress;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regsiter_layout);
		gps = new GPSTracker(RegisterActivity.this);
		if(gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            locationAddress = new LocationAddress();
            locationAddress.getAddressFromLocation(latitude, longitude,getApplicationContext(), new GeocoderHandler());

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude+" and your place"+GeocoderHandler.locationAddress, Toast.LENGTH_LONG).show();
        } else {
            // Can't get location.
            // GPS or network is not enabled.
            // Ask user to enable GPS/network in settings.
            gps.showSettingsAlert();
        }
		initalize();

	}

	@SuppressLint("NewApi")
	class MyTest extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			String name = params[0];
			String address = params[1];
			String phone = params[2];
			String email = params[3];
			String latitude = params[4];
			String longitude = params[5];
			String gpsaddress =params[6];
			//String phone = params[4];
			
			String result = test(name, address, phone, email, latitude,longitude, gpsaddress);
			return result;

		}

		public String test(String name, String address, String phone,String email, String latitude, String longitude, String gpsaddress) 
		{

			try {
				String getRequest = "http://"+Config.getIP()+":8080/CustomerApp/Register?";
				String params = "name='" + name + "'&address='" + address + "'&phone='" + phone + "'&email='" + email + "'&latitude='" + latitude +"'&longitude='" + longitude +"'&gpsaddress='" + gpsaddress + "'";

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
		protected void onPostExecute(String result) {
			
			if (result.contains("registered")) {
				Toast.makeText(getApplicationContext(), result+" please login", 3000).show();
//				Intent i = new Intent(getApplicationContext(),
//						MainActivity.class);
//				startActivity(i);
				/*Intent i=new Intent("com.my.signal");
				i.putExtra("message","sucessful registration");
				getApplicationContext().sendBroadcast(i);
				
				*/
				
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
		

		submit = (Button) findViewById(R.id.submit);
		submit.setOnClickListener(this);

		name = (EditText) findViewById(R.id.NameET);
		address = (EditText) findViewById(R.id.AddressET);
		email = (EditText) findViewById(R.id.EmailET);
		phone = (EditText) findViewById(R.id.PhoneET);
		

	}

	@Override
	public void onClick(View v) {

		// make request
		// test();
		
		String name, address, phone, email;
		
		switch (v.getId()) {
		case R.id.submit:

			name = this.name.getText().toString();
			address = this.address.getText().toString();
			email = this.email.getText().toString();
			phone = this.phone.getText().toString();
			 

			//Toast.makeText(getApplicationContext(), result, 2000).show();

			String[] array = new String[7];
			// { username, password, age, gender, phone };
			array[0] = name;
			array[1] = address;
			array[2] = phone;
			array[3] = email;
			array[4] = String.valueOf(latitude);
			array[5] = String.valueOf(longitude);
			array[6] = ""+GeocoderHandler.locationAddress;
			new MyTest().execute(array);
			break;

		}
	}
}
