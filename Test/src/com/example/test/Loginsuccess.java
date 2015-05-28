package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Loginsuccess extends Activity implements OnClickListener {
	private Button add,view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginsuccess);
		initializeComponenets();

	}

	private void initializeComponenets() {
		add= (Button) findViewById(R.id.add);
		add.setOnClickListener(this);
		view = (Button) findViewById(R.id.view);
		view.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.add:
						Intent register = new Intent(this, RegisterActivity.class);
			startActivity(register);

			break; 
		case R.id.view:
			Intent view_record = new Intent(this, ViewActivity.class);
			startActivity(view_record);
			break;
		
	}
}
	

}
