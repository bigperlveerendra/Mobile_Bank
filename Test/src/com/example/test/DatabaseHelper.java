package com.example.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	// Table Name
	public static final String TABLE_NAME = "TEST";

	// Table columns
	public static final String FNAME = "fname";
	public static final String LNAME = "lname";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	public static final String GENDER = "gender";

	// Database Information
	static final String DB_NAME = "JAVATECHIG_TODOS.DB";

	// database version
	static final int DB_VERSION = 1;

	// Creating table query
	private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + FNAME
			+ " TEXT  NOT NULL, " + LNAME + " TEXT NOT NULL, " + EMAIL + " TEXT, "+PHONE+" INTEGER PRIMARY KEY,"+GENDER+"TEXT);";

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
}
