package com.example.test;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
	EditText username, password, email, phone;
	Button register;
	TextView resultTextview;
	String fname,lname,mail,strgender;
	int sphone;
	DBHelper helper;
	long ab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regsiter_layout);
		initalize();

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
		
		username = (EditText) findViewById(R.id.usernameEditText);
		password = (EditText) findViewById(R.id.editText1);
		email = (EditText) findViewById(R.id.ageEditText);
		phone = (EditText) findViewById(R.id.phoneEditText);
		helper = new DBHelper(this);  
        register.setOnClickListener(this);
        
	}

	
	@Override
	public void onClick(View v) {

		// make request
		// test();

		switch (v.getId()) {
		case R.id.RegisterButton:

			fname = this.username.getText().toString();
			lname = this.password.getText().toString();
			mail = this.email.getText().toString();
			sphone = Integer.parseInt(phone.getText().toString());
			if (this.gender.getCheckedRadioButtonId() == R.id.radio0Male)
				strgender = "male";
			if (this.gender.getCheckedRadioButtonId() == R.id.radio1Female)
				strgender = "female";
			long num=insertEmpData();
			if(num>0){
			     Toast.makeText(this,"saved sucessfully",Toast.LENGTH_LONG).show();
			     }else{
			    	 Toast.makeText(this,"unable to save",Toast.LENGTH_LONG).show();
			     }
			Intent login=new Intent(this,Loginsuccess.class);
			startActivity(login);
			break;

		}
	}
	private long  insertEmpData() 
	{   
	    long ab;
	    SQLiteDatabase db = helper.getWritableDatabase(); 
		ContentValues values = new ContentValues(); 
		values.put(DBHelper.FNAME,fname);  
		values.put(DBHelper.LNAME,lname);  
		values.put(DBHelper.EMAIL,mail);
		values.put(DBHelper.PHONE,sphone);
		values.put(DBHelper.GENDER,strgender);
		ab=db.insert(DBHelper.TABLE, null, values); 
		return ab;
		}
}
