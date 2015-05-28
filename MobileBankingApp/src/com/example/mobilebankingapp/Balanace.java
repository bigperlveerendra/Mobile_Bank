package com.example.mobilebankingapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

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

public class Balanace extends Activity implements OnClickListener {

	private EditText usernameEditText, passwordEditText, SecretText;
	private Button WithdrawButton, enquiryButton, BalanceButton;
	private int regID;
	String SetServerString = "";
	int secret_data;
	String Third;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.balance);
		initializeComponenets();

	}

	private void initializeComponenets() {
		BalanceButton = (Button) findViewById(R.id.balance_button);
		BalanceButton.setOnClickListener(this);
		
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
			String username = params[0];
			String password = params[1];

			Boolean result = test(username, password);
			return result;

		}

		private Boolean test(String username, String password) {
			try {
				
				String getRequest = "http://"+Config.getIP()+":8080/MobileBankingApp/LoginServlet?";
				String params = "username='" + username + "'&password='"
						+ password + "'";
				HttpClient Client = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(getRequest + params);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                SetServerString = Client.execute(httpget, responseHandler);
                String arr[] = SetServerString.split(" ", 3);
                String firstWord = arr[0];
                String Second=arr[1];
                Third=arr[2];
                secret_data=Integer.parseInt(Third);
                if(firstWord.equalsIgnoreCase("true"))
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
				
				Toast.makeText(getApplicationContext(),"valid user", 3000).show();
				Intent i = new Intent(getApplicationContext(), com.example.mobilebankingapp.Secret.class);
				Bundle extras = new Bundle();
				i.putExtras(extras);
				startActivity(i);

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
		case R.id.balance_button:

				String getRequest = "http://"+Config.getIP()+":8080/MobileBankingApp/Balance?";
				String params ="";
				HttpClient Client = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(getRequest + params);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
			try {
				SetServerString = Client.execute(httpget, responseHandler);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                
                
			Toast.makeText(this,"Balance is:"+SetServerString, 4000).show();
			break;
		}

	}
	public void onBackPressed() {
		Intent i = new Intent(Balanace.this, com.example.mobilebankingapp.Account_Operation.class);
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
