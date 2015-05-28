package com.example.test;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class SignUp extends Activity implements OnClickListener{
	EditText username,password;
	TextView textView1,unametv,passwdtv;
	Button signup;
	String uname,passwd;
	DBHelper2 helper;
	public static int index;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		textView1=(TextView)findViewById(R.id.textView1);
		unametv=(TextView)findViewById(R.id.passwd);
		passwdtv=(TextView)findViewById(R.id.username);
		username=(EditText)findViewById(R.id.editText1);
		password=(EditText) findViewById(R.id.editText2);
		signup=(Button) findViewById(R.id.button1);
		signup.setOnClickListener(this);
		helper=new DBHelper2(this);
		
	}
	@Override
	public void onClick(View v) {
		index=13;
		uname=this.username.getText().toString();
		passwd=this.password.getText().toString();
		long num=insertEmpData();
		if(num>0){
		     Toast.makeText(this,"Registered sucessfully",Toast.LENGTH_LONG).show();
		     }
		else{
		    	 Toast.makeText(this,"unable to register",Toast.LENGTH_LONG).show();
		     }
		Intent mainint=new Intent(this,MainActivity.class);
		startActivity(mainint);
		
	}
	private long  insertEmpData() 
	{   
	    long ab;
	    SQLiteDatabase db = helper.getWritableDatabase(); 
		ContentValues values = new ContentValues(); 
		values.put(DBHelper2.PASSWD,passwd);
		//values.put(DBHelper2.EID,index);
		values.put(DBHelper2.UNAME,uname);  
		
		ab=db.insert(DBHelper2.USERSTABLE, null, values); 
		return ab;
		}
}
