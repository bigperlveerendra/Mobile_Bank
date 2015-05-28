package com.example.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

public class DBHelper2 extends SQLiteOpenHelper 
{	
	private static final String DATABASE_NAME = "Workshop2.db";
	private static final int DATABASE_VERSION = 1;	
	// Table name
	public static final String USERSTABLE = "signup";	
	// Columns	
	public static final String UNAME = "uname";
	public static final String EID = "id";
	public static final String PASSWD = "passwd";
	
	
	public DBHelper2(Context context) 
		{	
		super(context, DATABASE_NAME, null, DATABASE_VERSION);	
		}	
	@Override
	public void onCreate(SQLiteDatabase db)
	{		
		String sql = "create table " + USERSTABLE + "( " + UNAME + " text not null, "	+ PASSWD + " text not null);";	
		Log.d("EmployeeData", "onCreate: " + sql);	
		db.execSQL(sql);
		}	
	@Override	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{		
			if (oldVersion >= newVersion)	
			return;		
			String sql = null;	
		if (oldVersion == 1) 	
			sql = "alter table " + USERSTABLE + " add note text;";	
		if (oldVersion == 2)	
			sql = "";		
			Log.d("EmployeeData", "onUpgrade	: " + sql);	
	if (sql != null)	
			db.execSQL(sql);	
	}

	
}