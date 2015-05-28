package com.examples.androidseconddbapplications;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class IndexActivity extends Activity implements OnClickListener {

	Button b1;
	Button b2;
	Button b3;
	Button b4;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        b1=(Button) findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button) findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        
        b1.setOnClickListener(this);
     		b2.setOnClickListener(this);	
     		b3.setOnClickListener(this);
     		b4.setOnClickListener(this);	
    }

	public void onClick(View v) {
		if(v==b1){
			Intent intent1=new Intent(this,AddEmpActivity.class);
			startActivity(intent1);
		}
		if(v==b2){
			Intent intent2=new Intent(this,GetEmp.class);
			startActivity(intent2);
		}
		if(v==b3){
			Intent intent3=new Intent(this,EmpDelete.class);
			startActivity(intent3);
		}
		if(v==b4){
			Intent intent4=new Intent(this,UpdateEmp.class);
			startActivity(intent4);
		}
		
	
		
	}

  

    
}
