package com.example.mobilebankingapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.mobilebankingapp.Deposit.MyTest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Withdraw extends Activity implements OnClickListener {

	private EditText usernameEditText, passwordEditText, SecretText,WithDrawAmount;
	private Button WithdrawButton, enquiryButton, depositButton;
	private int regID;
	String SetServerString = "";
	int secret_data;
	String Third;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.withdraw);
		initializeComponenets();

	}

	private void initializeComponenets() {
		
		WithdrawButton = (Button) findViewById(R.id.WithdrawButton);
		WithdrawButton.setOnClickListener(this);
		WithDrawAmount=(EditText)findViewById(R.id.ET_amount);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account, menu);
		return false;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@SuppressLint("NewApi")
	class MyTest extends AsyncTask<String, Void, Boolean> {
		
		
		Context context;

		@Override
		protected Boolean doInBackground(String... params) {
			String Amount = params[0];
			//String password = params[1];

			boolean result = test(Amount);
			return result;

		}

		private boolean test(String Amount) {
			try {
				
				String getRequest = "http://"+Config.getIP()+":8080/MobileBankingApp/WithDraw?";
				String params = "Amount='" + Amount +"'";
				HttpClient Client = new DefaultHttpClient();
				//URL url = new URL(getRequest + params);

				//HttpURLConnection conn = (HttpURLConnection) url
				//		.openConnection();
				//conn.setRequestMethod("GET");
				//DataInputStream dis = new DataInputStream(conn.getInputStream());
				HttpGet httpget = new HttpGet(getRequest + params);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                SetServerString = Client.execute(httpget, responseHandler);
                if(SetServerString.equalsIgnoreCase("Withdrawn"))
				return true;// true
                else
                return false;

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		}


		@Override
		protected void onPostExecute(Boolean result) {

			if (result) {
				
				Toast.makeText(getApplicationContext(),"Successfully Withdrawn", 3000).show();
				
			} else {
				Toast.makeText(getApplicationContext(), result+" invalid user", 3000).show();
			}

		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.registerButton:
			// Toast.makeText(this,"you clicked on register", 2000).show();

			// opens a new activity
			Intent i = new Intent(this, RegisterActivity.class);
			startActivity(i);

			break; 
		case R.id.WithdrawButton:
			String Amount = WithDrawAmount.getText().toString();
			//String password = passwordEditText.getText().toString();
			String[] array = { Amount };
			new MyTest().execute(array);
			
			// Toast.makeText(this,"you clicked on login", 2000).show();
			break;
		}

	}
	public void onBackPressed() {
		Intent i = new Intent(Withdraw.this, com.example.mobilebankingapp.Account_Operation.class);
		startActivity(i);
		}
	@SuppressLint("NewApi")
	class MyRegID extends AsyncTask<Void, Integer, Integer>
	{

		@SuppressWarnings("deprecation")
		@Override
		protected Integer doInBackground(Void... arg0) {
			try
			{
			String request = "http://"+Config.getIP()+":8080/WorkingEmployee/GetRegistrationID?username='"+usernameEditText.getText().toString()+"'";
			URL url = new URL(request);

			HttpClient Client = new DefaultHttpClient();
			
			HttpGet httpget = new HttpGet(request);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();


            SetServerString = Client.execute(httpget, responseHandler);
            String arr[] = SetServerString.split(" ", 2);
            String firstWord = arr[0];
            int y = Integer.parseInt(firstWord);
			return y;
			//return Integer.parseInt(dis.readLine());
			}
			catch(Exception e)
			{
			e.printStackTrace();
			return 0;
			}
		}
		
		@SuppressLint("NewApi")
		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			regID=result;
			Toast.makeText(getApplicationContext(),result+" check", 1000).show();
			
			Intent i=new Intent(getApplicationContext(), SucessLogin.class);
			i.putExtra("regID", regID);
			startActivity(i);
		}
		
	}
	@SuppressLint("NewApi")
	class Secret extends AsyncTask<Void, Integer, Integer>
	{

		@SuppressWarnings("deprecation")
		@Override
		protected Integer doInBackground(Void... arg0) {
			try
			{
			String request = "http://"+Config.getIP()+":8080/WorkingEmployee/GetRegistrationID?username='"+usernameEditText.getText().toString()+"'";
			URL url = new URL(request);

//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			conn.setDoOutput(true);
//			DataOutputStream dos=new DataOutputStream(conn.getOutputStream());
//			dos.writeChars(usernameEditText.getText().toString());
//			dos.flush();
//			dos.close();
			
//			DataInputStream dis=new DataInputStream(conn.getInputStream());
			
			HttpClient Client = new DefaultHttpClient();
			
			HttpGet httpget = new HttpGet(request);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();


            SetServerString = Client.execute(httpget, responseHandler);
            String arr[] = SetServerString.split(" ", 2);
            String firstWord = arr[0];
            int y = Integer.parseInt(firstWord);
			return y;
			//return Integer.parseInt(dis.readLine());
			}
			catch(Exception e)
			{
			e.printStackTrace();
			return 0;
			}
		}
		
		@SuppressLint("NewApi")
		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			regID=result;
			Toast.makeText(getApplicationContext(),result+" check", 1000).show();
			
			Intent i=new Intent(getApplicationContext(), SucessLogin.class);
			i.putExtra("regID", regID);
			startActivity(i);
		}
		
	}
}
