package com.examples.androidseconddbapplications;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class GetEmp extends Activity implements OnClickListener{


	  EditText  t1;
	  Button b1;
	  int eid;
	  DBHelper helper;
	  String s;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_emp);
        helper = new DBHelper(this);  
        b1=(Button) findViewById(R.id.button1);
        b1.setOnClickListener(this);
    }


	
	public void onClick(View v) {
		  t1=(EditText) findViewById(R.id.editText1);
	      eid=Integer.parseInt(t1.getText().toString());
          s=new String()+eid;
	       getEmpData() ;
		
	}

 
	private void  getEmpData() 
	{   
	 
		   
	      SQLiteDatabase db=null ;
	    	db=openOrCreateDatabase("AndroidDB.db",SQLiteDatabase.CREATE_IF_NECESSARY,null);
		Cursor c=db.query(DBHelper.TABLE,new String[]{"ENAME","TECH"},"eid=?",new String[]{s},null,null,null);

		c.moveToFirst();
      while(!c.isAfterLast())
      {
              Toast.makeText(this,c.getString(0)+ " "+c.getString(1),Toast.LENGTH_LONG).show();
              c.moveToNext();
      }
   
      c.close();
		}


		
	}

    

