package com.example.mobilebankingapp;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class Random_no extends Activity implements OnClickListener {

	EditText secret;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_random_no);
		secret=(EditText)findViewById(R.id.secretEditText);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@SuppressLint("NewApi")
	class MyTest extends AsyncTask<String, Void, Boolean> {

		@Override
		protected Boolean doInBackground(String... params) {
			String secret = params[0];
			
			Boolean result = test(secret);
			return result;

		}

		private Boolean test(String secret) {
			try {
				
				String getRequest = "http://"+Config.getIP()+":8080/WorkingEmployee/SecretServlet?";
				String params = "secret='" + secret +"'";
				HttpClient Client = new DefaultHttpClient();
				//URL url = new URL(getRequest + params);

				//HttpURLConnection conn = (HttpURLConnection) url
				//		.openConnection();
				//conn.setRequestMethod("GET");
				//DataInputStream dis = new DataInputStream(conn.getInputStream());
				HttpGet httpget = new HttpGet(getRequest + params);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                String SetServerString = Client.execute(httpget, responseHandler);
                String arr[] = SetServerString.split(" ", 2);
                String firstWord = arr[0];
               //Boolean b=dis.readBoolean();
               // Toast.makeText(getApplicationContext(),SetServerString+ " valid user", 3000).show();
				//dis.close();
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
				//Toast.makeText(getApplicationContext(),SetServerString+" valid user", 3000).show();
				//	new MyRegID().execute(new Void[] {});
									
			}
			
			else 
			{
				Toast.makeText(getApplicationContext(), "invalid secret", 3000).show();
			}

		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String code = secret.getText().toString();
		String[] array = { code };
		new MyTest().execute(array);
		
	}

}
