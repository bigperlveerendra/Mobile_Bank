package com.example.mobilebankingapp;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Checkable;
import android.widget.Toast;

public class MyService extends Service {
private int regID;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	
		
		regID=intent.getExtras().getInt("regID");
		new GetCount().execute(new Void[] {});
		
		
		
		
		return START_STICKY;
	}
	
	

	private int readPref()
	{
		SharedPreferences rp =PreferenceManager.getDefaultSharedPreferences(this);
		int a=rp.getInt("rows", 0);
		//Toast.makeText(this, a+"", 1000).show();
		return a;
		
	}
	
	private void setPref(int a)
	{
		SharedPreferences sp =PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Editor e=sp.edit();
		e.putInt("rows",a);
		e.commit();
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressLint("NewApi")
	class GetCount extends AsyncTask<Void, Integer, Integer> {

		@Override
		protected Integer doInBackground(Void... arg0) {
			try {
				Log.i("abhishek","insied the background");
				
				URL address = new URL(
						"http://"+Config.getIP()+":8080/WorkingEmployee/GetNumberOfRows");
				HttpURLConnection conn = (HttpURLConnection) address
						.openConnection();
				conn.setRequestMethod("POST");
				conn.setDoInput(true);
				
			  DataInputStream dis=new DataInputStream(conn.getInputStream());
			  

				
				return Integer.parseInt(dis.readLine());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}

		}

		
		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			

				//setPref(5);
					if(readPref()!=result)
					{
						Context c=getApplicationContext();
						Intent intent = new Intent("com.my.getlist"); 
						intent.putExtra("regID", regID);
						PendingIntent pi = PendingIntent.getActivity(c, 0, intent,0);
						setPref(result);
						
						
						NotificationManager nm=(NotificationManager) c.getSystemService(c.NOTIFICATION_SERVICE);
						Notification n=new Notification(R.drawable.ic_launcher,"registration",System.currentTimeMillis());
					    n.setLatestEventInfo(c, "NEW WORK FOUND","check the work list", pi);
					    
					    
					    nm.notify(1, n);
					    
					}
				
				
				
			 else {
				//System.out.println("no list is found");
//				Toast.makeText(getApplicationContext(), result+"no change",
//						1000).show();
			}
		}

	}
	

}
