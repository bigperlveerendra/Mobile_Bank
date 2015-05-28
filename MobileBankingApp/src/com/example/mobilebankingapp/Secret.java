package com.example.mobilebankingapp;

import java.io.DataInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.mobilebankingapp.MainActivity.MyTest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Secret extends Activity implements OnClickListener {

	RadioButton male, female;
	RadioGroup gender;
	EditText username, password, age, phone, secretText;
	Button register, secretButton,Btn_UID,Btn_UATH;
	TextView resultTextview;
	private EditText usernameEditText, passwordEditText;
	private Button loginButton;
	String SetServerString = "";
	private int secret_data;
	static private String sB;
	int intB;
	BigInteger B,A,Kum,p,m,g,Kuh,K;
	String sA,sp,sg,sm,sk, encKey, decKey,sessionKey,encunamepasswd,decunamepasswd;
	static String Ssecret_data;
	byte[] encrypted;
	byte[] decrypted;
	String enc,dec;


	public void decrypt(String sessionKey )
	{
		byte[] ByteencKey;
		byte[] BytedecKey = null;
		try {
			ByteencKey = encKey.getBytes("UTF-8");
			Encryption ob=new Encryption(sessionKey);
			BytedecKey=ob.decrypt(ByteencKey);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        decKey=new String(BytedecKey);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secret);
		initalize();
			
	}
	private void initalize() {
		male = (RadioButton) findViewById(R.id.radio0Male);
		female = (RadioButton) findViewById(R.id.radio1Female);
		gender = (RadioGroup) findViewById(R.id.radioGroup1);

		Btn_UATH = (Button) findViewById(R.id.button_UATH_RES);
		Btn_UATH.setOnClickListener(this);
		
		
		secretText=(EditText) findViewById(R.id.secretEditText);

		username = (EditText) findViewById(R.id.usernameEditText);
		password = (EditText) findViewById(R.id.editText1);
		age = (EditText) findViewById(R.id.ageEditText);
		phone = (EditText) findViewById(R.id.phoneEditText);
		usernameEditText = (EditText) findViewById(R.id.ET_UserName);
		passwordEditText = (EditText) findViewById(R.id.ET_Passwd);
		secret_data=getIntent().getExtras().getInt("secret");

				//intB=Integer.parseInt(B);
		

	}

	@SuppressLint("NewApi")
	class MyTest extends AsyncTask<String, Void, Boolean> {
		
		
		Context context;

		@Override
		protected Boolean doInBackground(String... params) {
			String username = params[0];
			String password = params[1];

			Boolean result = test(username,password);
			return result;

		}

		private Boolean test(String username, String password) {
			try {
				
				//Toast.makeText(getApplicationContext(), "inside tesr", 1000).show();
				String getRequest = "http://"+Config.getIP()+":8080/MobileBankingApp/AccountLogin?";
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
                encKey=arr[1];
                sessionKey=arr[2];
                //decrypt(sessionKey);
                //
                
                
               // Toast.makeText(getApplicationContext(), sessionKey, 4000).show();
               
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
				
				//decrypt(sessionKey);
				Toast.makeText(getApplicationContext(),"Account holder Login Succes:", 1000).show();
				//Toast.makeText(getApplicationContext(),decKey, 4000).show();
				Intent i = new Intent(Secret.this, com.example.mobilebankingapp.Account_Operation.class);
				//i.putExtra("secret",secret_data);
				//Bundle extras = new Bundle();
				/*extras.putString("B",B);
				//extras.putString("g",g);
				//extras.putString("p",p);
				extras.putString("m",m);
				extras.putString("k",k);*/
				//i.putExtras(extras);
				startActivity(i);
				finish();
			} else {
				Toast.makeText(getApplicationContext(), result+" invalid user", 1000).show();
			}

		}

	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	

	public void Encr(String message) throws Exception
	{

		       Encryption encrypter = new Encryption("Key");
		    
		    encrypted = encrypter.encrypt(message.getBytes("UTF-8"));
		    //System.out.println(new String (encrypted, "UTF-8"));
		    decrypted = encrypter.decrypt(encrypted);
		    //System.out.println(new String (decrypted, "UTF-8"));
	}
	@Override
	public void onClick(View v) {

		// make request
		// test();

		String username, password, age, gender = null, phone,secret;
		
		switch (v.getId()) {
		case R.id.button_UATH_RES:
			username = usernameEditText.getText().toString();
			password = passwordEditText.getText().toString();
			 String message=username+" "+password;
			 try {
				Encr(message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 try {
				 encunamepasswd=new String(encrypted, "UTF-8");
				dec=new String(decrypted, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Toast.makeText(this, encunamepasswd+"and "+dec, 3000).show();
			String[] array = { username,password };
			new MyTest().execute(array);
			break;
			
			//String[] array = new String[6];
			// { username, password, age, gender, phone };
			//array[0] = ;//secret;
			/*array[1] = password;
			array[2] = age;
			array[3] = gender;
			array[4] = phone;
			array[5] = secret;*/
			//uncomment below one
			//new MyTest().execute(array);
			
		//case R.id.button_UID_RES:
			/*Random r = new Random();
			int Low = 1;
			int High =23;
			int u = r.nextInt(High-Low) + Low;
			int Id=r.nextInt(High-Low)+Low;
			BigInteger d=BigInteger.valueOf(Id);
			A=g.pow(u).multiply(d);
			A=A.mod(p);
			Kum=B.pow(u).mod(p);
			Kuh=Kum.xor(K);
			String Array[]=new String[4];
			Array[0]=""+A;
			Array[1]=sm;
			Array[2]=""+Kum;
			Array[3]=""+p;*/
			//Toast.makeText(getApplicationContext(),"You have responded for user ID Response", 2000).show();
			//new MyTest().execute(Array);
			
			
		}
	}
	
	/*@SuppressLint("NewApi")
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
*/}
