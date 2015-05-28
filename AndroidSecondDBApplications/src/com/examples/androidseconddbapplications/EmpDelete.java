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


public class EmpDelete extends Activity implements OnClickListener {

	  EditText  t1;
	  Button b1;
	  int eid;
	  DBHelper helper;
	  String s;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_delete);
        helper = new DBHelper(this);  
        b1=(Button) findViewById(R.id.button1);
        b1.setOnClickListener(this);
    }

	public void onClick(View v) {
		t1=(EditText) findViewById(R.id.editText1);
	      eid=Integer.parseInt(t1.getText().toString());
           s=new String()+eid;
       long num=  deleteEmpData();
	    if(num>0){
	     Toast.makeText(this,"sucess",Toast.LENGTH_LONG).show();
	   }else{
	   	 Toast.makeText(this,"failure",Toast.LENGTH_LONG).show();
	  } 
		
	}

	 private long  deleteEmpData() 
		{   
	       long ab;
			SQLiteDatabase db = helper.getWritableDatabase(); 
			ContentValues values = new ContentValues(); 
			 values.put(DBHelper.EID,eid);  
			  ab=db.delete(DBHelper.TABLE,"eid=?",new String[]{s}); 
			  return ab;
		   
		  /*    String q1="delete from Employee where eid=?";
			    SQLiteDatabase db =helper.getWritableDatabase();
                SQLiteStatement stmt = db.compileStatement(q1);
                stmt.bindLong(1, eid);
                stmt.execute();
                Toast.makeText(this,"sucess",Toast.LENGTH_LONG).show();
          */
	 
			}
 

    
    
}
