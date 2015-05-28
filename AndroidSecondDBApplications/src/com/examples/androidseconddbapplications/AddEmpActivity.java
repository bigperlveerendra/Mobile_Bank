package com.examples.androidseconddbapplications;





import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddEmpActivity extends Activity  implements OnClickListener{

	    EditText eid;
	    EditText name;
	    EditText tech;
	    Button register;
	    DBHelper helper;
	    int id;
	    String ename;
	    String technology;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emp);
        eid=(EditText) findViewById(R.id.editText1);
        name=(EditText) findViewById(R.id.editText2);
        tech=(EditText) findViewById(R.id.editText3);
        register=(Button) findViewById(R.id.button1);
        helper = new DBHelper(this);  
        register.setOnClickListener(this);
    }
	public void onClick(View v) {
		id=Integer.parseInt(eid.getText().toString());
	    ename=name.getText().toString();
	    technology=tech.getText().toString();
	    long num=insertEmpData();
	    eid.setText(" ");
	    name.setText(" ");
	    tech.setText(" ");
	     if(num>0){
	     Toast.makeText(this,"sucess",Toast.LENGTH_LONG).show();
	     }else{
	    	 Toast.makeText(this,"failure",Toast.LENGTH_LONG).show();
	     }
		
	}

	 private long  insertEmpData() 
		{   
		    long ab;
			SQLiteDatabase db = helper.getWritableDatabase(); 
			ContentValues values = new ContentValues(); 
			values.put(DBHelper.EID,id);  
			values.put(DBHelper.ENAME,ename);  
			values.put(DBHelper.TECH,technology);   
			ab=db.insert(DBHelper.TABLE, null, values); 
			return ab;
			}


  

    
}
