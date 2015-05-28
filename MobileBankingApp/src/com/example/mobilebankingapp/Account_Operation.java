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

@SuppressLint("NewApi")
public class Account_Operation extends Activity implements OnClickListener {

	private EditText usernameEditText, passwordEditText, SecretText;
	private Button WithdrawButton, enquiryButton, depositButton;
	private int regID;
	String SetServerString = "";
	int secret_data;
	String Third;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_operation);
		initializeComponenets();

	}

	private void initializeComponenets() {
		depositButton = (Button) findViewById(R.id.deposit_button);
		depositButton.setOnClickListener(this);
		WithdrawButton = (Button) findViewById(R.id.withdraw_button);
		WithdrawButton.setOnClickListener(this);
		enquiryButton = (Button) findViewById(R.id.enquiry_button);
		enquiryButton.setOnClickListener(this);
		//secret_data=getIntent().getExtras().getInt("secret");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
				//URL url = new URL(getRequest + params);

				//HttpURLConnection conn = (HttpURLConnection) url
				//		.openConnection();
				//conn.setRequestMethod("GET");
				//DataInputStream dis = new DataInputStream(conn.getInputStream());
				HttpGet httpget = new HttpGet(getRequest + params);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                SetServerString = Client.execute(httpget, responseHandler);
                String arr[] = SetServerString.split(" ", 3);
                String firstWord = arr[0];
                String Second=arr[1];
                Third=arr[2];
                secret_data=Integer.parseInt(Third);
                //B=arr[1];
                //g=arr[2];
                //p=arr[3];
                //m=arr[4];
                //k=arr[5];
               //Boolean b=dis.readBoolean();
              // Toast.makeText(getApplicationContext(),SetServerString+ " valid user", 3000).show();
				//dis.close();
                //Toast.makeText(getApplicationContext(), secondWord, 3000).show();
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
								
				//new MyRegID().execute(new Void[] {});//uncomment and delete frst one if it is nt workng
				//new Secret().execute(new Void[]{});	
				//Intent i = new Intent(getApplicationContext(), com.example.employeeworkapp.Secret.class);
				//i.putExtra("regID", regID);
				//startActivity(i);
				// need to add code
				Toast.makeText(getApplicationContext(),"valid user, moving to next ID Res & Auth Req and Secert:"+Third, 3000).show();
				Intent i = new Intent(getApplicationContext(), com.example.mobilebankingapp.Secret.class);
				Bundle extras = new Bundle();
				/*extras.putString("B",B);
				//extras.putString("g",g);
				//extras.putString("p",p);
				extras.putString("m",m);
				extras.putString("k",k);*/
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
		case R.id.deposit_button:
			// Toast.makeText(this,"you clicked on register", 2000).show();

			// opens a new activity
			Intent depositIntent = new Intent(this, Deposit.class);
			startActivity(depositIntent);
			
			break; 
		case R.id.withdraw_button:
			Intent WithdrawIntent = new Intent(this, Withdraw.class);
			startActivity(WithdrawIntent);
			//super.finish();
			break;
		case R.id.enquiry_button:
			Intent enqIntent = new Intent(this, Balanace.class);
			startActivity(enqIntent);
			//finishActivity(regID);
		}

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
