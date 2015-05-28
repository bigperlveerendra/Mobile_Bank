package com.example.test;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private EditText usernameEditText, passwordEditText;
	private Button loginButton, signupButton;
	String SetServerString = "";
	String uname,passwd,username;
	int secret_data;
	String Third;
	DBHelper2 helper;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Login");
		setContentView(R.layout.activity_main);
		initializeComponenets();

	}

	private void initializeComponenets() {
		loginButton = (Button) findViewById(R.id.submitButton);
		loginButton.setOnClickListener(this);
		signupButton=(Button) findViewById(R.id.signup);
		signupButton.setOnClickListener(this);
		usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		helper=new DBHelper2(this);

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


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submitButton:
			username = usernameEditText.getText().toString();
			String password = passwordEditText.getText().toString();
			getEmpData();
			if(password.equalsIgnoreCase(uname))
			{
				Intent main = new Intent(MainActivity.this,Loginsuccess.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(main);
			}
			else{			
			 Toast.makeText(this,"invalid user", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.signup:
			//Toast.makeText(this, "moving", 2000).show();
			Intent signupint=new Intent(this,SignUp.class);
			startActivity(signupint);
			break;
		}

	
		
	}
	private void  getEmpData() 
	{   
	 
		   
	      SQLiteDatabase db=null ;
	    	db=openOrCreateDatabase("Workshop2.db",SQLiteDatabase.CREATE_IF_NECESSARY,null);
		Cursor c=db.query(DBHelper2.USERSTABLE,new String[]{"UNAME","PASSWD",},"uname=?",new String[]{username},null,null,null);

			c.moveToFirst();
			uname= c.getString(0);
     }
}

