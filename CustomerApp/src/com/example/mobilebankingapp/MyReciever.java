package com.example.mobilebankingapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class MyReciever extends BroadcastReceiver {

	@Override
	public void onReceive(Context c, Intent i) {
		Log.i("abhishek", i.getExtras().getString("message"));
		// launch notification

		Intent intent = new Intent("com.my.newsignal");
		PendingIntent pi = PendingIntent.getActivity(c, 0, intent, 0);

		NotificationManager nm = (NotificationManager) c
				.getSystemService(c.NOTIFICATION_SERVICE);
		Notification n = new Notification(R.drawable.ic_launcher,
				"registration", System.currentTimeMillis());
		n.setLatestEventInfo(c, "registration", "sucess registring", pi);

		nm.notify(1, n);

	}

}
