package com.example.mobilebankingapp;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.R.array;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.worker.employment.MyWork;

public class SubscribedActivity extends Activity  implements OnItemClickListener{
	ListView lv;
	int regID;
	MyWork[] a=new MyWork [] {};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sucess_login);
		init();
		regID = getIntent().getExtras().getInt("regID");
		
		new FetchWork().execute(new Void[] {});
	}

	private void init() {
		lv = (ListView) findViewById(R.id.listView);
		lv.setOnItemClickListener(this);

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
						"http://"+Config.getIP()+":8080/WorkingEmployee/GetSubscribedWork");
				HttpURLConnection conn = (HttpURLConnection) address
						.openConnection();
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				DataOutputStream dos = new DataOutputStream(
						conn.getOutputStream());

				
				dos.writeInt(regID);
				dos.flush();
				dos.close();

				ObjectInputStream ois = new ObjectInputStream(
						conn.getInputStream());
				ArrayList<MyWork> array = (ArrayList<MyWork>) ois.readObject();
				ois.close();
				if (array != null) {
					Log.i("abhishek", "found some records!!");
					a = array.toArray(a);
					return a;

				} else {
					Log.i("abhishek", "no records found!!");
					return null;
				}

			} catch (Exception e) {
				e.printStackTrace();
				return null;

			}
			

		}

		@Override
		protected void onPostExecute(MyWork[] result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result != null) {

				
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
		Intent i=new Intent(getApplicationContext(),SubscriberWorkInfo.class);
		i.putExtra("work",m);
		i.putExtra("regID", regID);
		startActivity(i);
		
	}
}
