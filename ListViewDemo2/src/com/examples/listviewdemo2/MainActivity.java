package com.examples.listviewdemo2;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;

public class MainActivity extends Activity  implements OnItemClickListener {

    ListView states;
    List<String> names;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		names=new ArrayList<String>();
        names.add("Andhra Pradesh");
        names.add("Karnataka");
        names.add("Kerala");
        names.add("Tamil Nadu");
        names.add("Telangana");
         states=(ListView) findViewById(R.id.listView1);
        states.setOnItemClickListener(this);
        
  //    ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
       ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,names);
     states.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        states.setAdapter(adapter);
    }
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Toast.makeText(this, 
                "On Item Clicked : \n" + arg0.getItemAtPosition(arg2).toString(),
                Toast.LENGTH_LONG).show();
	
	}


    
}
