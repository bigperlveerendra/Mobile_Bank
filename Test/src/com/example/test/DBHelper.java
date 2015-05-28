package com.example.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

public class DBHelper extends SQLiteOpenHelper 
{	
	private static final String DATABASE_NAME = "Workshop1.db";
	private static final int DATABASE_VERSION = 1;	
	// Table name
	public static final String TABLE = "Emp1";	
	// Columns	
	public static final String FNAME = "fname";
	public static final String LNAME = "lname";
	public static final String EMAIL = "email";
	public static final String PHONE = "sphone";
	public static final String GENDER = "strgender";

	
	public DBHelper(Context context) 
		{	
		super(context, DATABASE_NAME, null, DATABASE_VERSION);	
		}	
	@Override
	public void onCreate(SQLiteDatabase db)
	{		
		String sql = "create table " + TABLE + "( " + FNAME+ " text not null, " +LNAME + " text not null, "	+ EMAIL + " text not null, "+ PHONE +" integer primary key, "+ GENDER + " text not null);";	
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