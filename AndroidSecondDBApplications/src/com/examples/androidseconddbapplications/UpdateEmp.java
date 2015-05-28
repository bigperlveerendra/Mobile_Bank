package com.examples.androidseconddbapplications;




import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UpdateEmp extends Activity  implements OnClickListener {

	EditText eid;
    EditText tech;
    Button update;
    DBHelper helper;
    int id;
    String name;
    String s;
    SQLiteDatabase db; 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_emp);
        eid=(EditText) findViewById(R.id.editText1);
        tech=(EditText) findViewById(R.id.editText2);
        update=(Button) findViewById(R.id.button1);
        helper = new DBHelper(this); 
        update.setOnClickListener(this);
    }

	public void onClick(View v) {
		db= helper.getWritableDatabase(); 
		id=Integer.parseInt(eid.getText().toString());
		 s=new String()+id;
		name=tech.getText().toString();
		updateEmpData(db);
		   db.close();
		Toast.makeText(this,"sucess",Toast.LENGTH_LONG).show();
	}

	 private void  updateEmpData(SQLiteDatabase db) 
		{   
		    
		   
		    ContentValues values = new ContentValues();
		    values.put("ename", name);
			db.update(DBHelper.TABLE,values , "eid=?", new String[]{s});
			}

	    /*   String q1="update Employee set name=? where eid=?";
	           SQLiteDatabase db =helper.getWritableDatabase();
              SQLiteStatement stmt = db.compileStatement(q1);
              
               stmt.bindLong(1, sname);
                stmt.bindLong(2, eid);
               stmt.execute();
               Toast.makeText(this,"sucess",Toast.LENGTH_LONG).show();
     
*/
    
}
