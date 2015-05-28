package com.example.mobilebankingapp;

import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.R.color;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.worker.employment.MyWork;

public class SucessLogin extends Activity implements OnItemClickListener {

	ListView lv;
	private int no_of_rows;
	private MyWork[] a = {};
	private int regID,workID;
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		//getMenuInflater().inflate(R.menu.main, menu);
		menu.add("Registered Works");
		menu.add("Payment Information");
		//menu.add("");
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		 super.onOptionsItemSelected(item);
		if(item.getTitle().toString().contains("Registered Works"))
		{
			//Toast.makeText(getApplicationContext(), "you clicked eme", 450).show();
			Intent i=new Intent(this, SubscribedActivity.class);
			i.putExtra("regID", regID);
			startActivity(i);
			return true;
		}
		return true;
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sucess_login);

		regID=getIntent().getExtras().getInt("regID");
		init();

		new FetchWork().execute(new Void[] {});

		Intent i=new Intent(getApplicationContext(), MyService.class);
		
		i.putExtra("regID", regID);
		
		startService(i);

	}

	private void init() {
		lv = (ListView) findViewById(R.id.listView);
		lv.setOnItemClickListener(this);

	}

	private void setPref(int a) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		Editor e = sp.edit();
		e.putInt("rows", a);
		e.commit();
	}

	class MyArrayAdapter extends ArrayAdapter<MyWork> {
		MyWork[] values;

		public MyArrayAdapter(Context context, int resource, MyWork[] objects) {
			super(context, resource, objects);
			values = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// return super.getView(position, convertView, parent);
			View row = getLayoutInflater().inflate(R.layout.list_work_layout,
					parent, false);
			TextView tv1 = (TextView) (row
					.findViewById(R.id.descriptionTextView));
			TextView tv2 = (TextView) (row.findViewById(R.id.moneyTextView));
			tv1.setText(values[position].description);
			tv2.setText(values[position].money + "");
			return row;

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return values.length;
		}

	}

	@SuppressLint("NewApi")
	class FetchWork extends AsyncTask<Void, Integer, MyWork[]> {

		@Override
		protected MyWork[] doInBackground(Void... arg0) {
			try {
				Log.i("abhishek", "insied the background");
				URL address = new URL(
						"http://"+Config.getIP()+":8080/WorkingEmployee/GetWorksList");
				HttpURLConnection conn = (HttpURLConnection) address
						.openConnection();
				conn.setRequestMethod("POST");
				conn.setDoInput(true);

				ObjectInputStream ois = new ObjectInputStream(
						conn.getInputStream());
				ArrayList<MyWork> list = (ArrayList<MyWork>) ois.readObject();
				
				a = list.toArray(a);
				ois.close();
				Log.i("abhishek", a.length + "");
				return a;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

		}

		@Override
		protected void onPostExecute(MyWork[] result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result != null) {

				// Toast.makeText(getApplicationContext(),
				// "list is found!!!" + result.length, 1000).show();

				MyArrayAdapter adapter = new MyArrayAdapter(
						getApplicationContext(), R.layout.list_work_layout,
						result);
				adapter.notifyDataSetChanged();

				lv.setAdapter(adapter);
				// setPref(result.length);

			} else {
				// System.out.println("no list is found");
				Toast.makeText(getApplicationContext(), "no list is found",
						1000).show();
			}
		}

	}

	@Override
	public void onItemClick(AdapterView<?> av, View v, int pos, long arg3) {
		
		MyWork m=a[pos];
	Intent i=new Intent(getApplicationContext(), WorkInfo.class);
	i.putExtra("work",m);
	i.putExtra("regID", regID);
	startActivity(i);
		
		
	}

}
