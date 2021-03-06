package com.examples.androidseconddbapplications;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

public class DBHelper extends SQLiteOpenHelper 
{	
	private static final String DATABASE_NAME = "AndroidDB.db";
	private static final int DATABASE_VERSION = 1;	
	// Table name
	public static final String TABLE = "Employee";	
	// Columns	

	public static final String EID = "eid";
	public static final String ENAME = "ename";
	public static final String TECH = "tech";

	
	public DBHelper(Context context) 
		{	
		super(context, DATABASE_NAME, null, DATABASE_VERSION);	
		}	
	@Override
	public void onCreate(SQLiteDatabase db)
	{		
		String sql = "create table " + TABLE + "( " + EID+ " integer primary key autoincrement, " +ENAME + " text not null, "	+ TECH + " text not null);";	
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
			sql = "alter table " + TABLE + " add note text;";	
		if (oldVersion == 2)	
			sql = "";		
			Log.d("EmployeeData", "onUpgrade	: " + sql);	
	if (sql != null)	
			db.execSQL(sql);	
	}

	
}