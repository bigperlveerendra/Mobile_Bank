package com.example.test;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewActivity extends Activity implements OnItemClickListener {

    ListView fnames;
    int row;
    String enames[];
    List<String> names;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
		names=new ArrayList<String>();
		getEmpData();
		fnames=(ListView) findViewById(R.id.listView1);
		fnames.setOnItemClickListener(this);
        
  
       ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
       //fnames.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
       fnames.setAdapter(adapter);
    }
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
	{
		Toast.makeText(this,"Selected Employee : \n" + arg0.getItemAtPosition(arg2).toString(),1000).show();
		String efname=arg0.getItemAtPosition(arg2).toString();
		Intent selectedInt=new Intent(this,SelectedActivity.class);
		selectedInt.putExtra("fname",efname );
		startActivity(selectedInt);
	}
	private void  getEmpData() 
	{   
	 
		   
	      SQLiteDatabase db=null ;
	    	db=openOrCreateDatabase("Workshop1.db",SQLiteDatabase.CREATE_IF_NECESSARY,null);
		Cursor c=db.query(DBHelper.TABLE,new String[]{"FNAME","LNAME","EMAIL"},null,null,null,null,null);

		
		row=c.getCount();
		
		if(row>0)
		{
			c.moveToFirst();
			while(!c.isAfterLast())
			{
           	  names.add(c.getString(0));
              c.moveToNext();
			}
		}
		else
		{
			Toast.makeText(this,"No Records found" , Toast.LENGTH_LONG).show();
			Intent goingback=new Intent(this,Loginsuccess.class);
			startActivity(goingback);
		}
   
      c.close();
		}

    
}
