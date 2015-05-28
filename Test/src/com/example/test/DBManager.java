package com.example.test;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

	private DatabaseHelper dbHelper;
	
	private Context context;
	
	private SQLiteDatabase database;

	public DBManager(Context c) {
		context = c;
	}

	public DBManager open() throws SQLException {
		dbHelper = new DatabaseHelper(context);
		database = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	public long insert(String fname, String lname,String email,String sphone,String gender) {
		ContentValues contentValue = new ContentValues();
		contentValue.put(DatabaseHelper.FNAME, fname);
		contentValue.put(DatabaseHelper.LNAME, lname);
		contentValue.put(DatabaseHelper.EMAIL, email);
		contentValue.put(DatabaseHelper.PHONE, sphone);
		contentValue.put(DatabaseHelper.GENDER, gender);
		long ab=database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
		close();
		return ab;
	}

	public Cursor fetch() {
		String[] columns = new String[] { DatabaseHelper.FNAME, DatabaseHelper.LNAME, DatabaseHelper.EMAIL,DatabaseHelper.PHONE,DatabaseHelper.GENDER };
		Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		close();
		return cursor;
	}
	public String getData1() throws SQLException{
        // TODO Auto-generated method stub
        String[] columns1 = new String[] { DatabaseHelper.FNAME };
        Cursor c1 = database.query(DatabaseHelper.TABLE_NAME, columns1, null, null, null,null,null);
        String result1 = "";

        int isName = c1.getColumnIndex(DatabaseHelper.FNAME);

        for (c1.moveToFirst(); !c1.isAfterLast(); c1.moveToNext()) {

            result1 = result1 + c1.getString(isName)
                    + "  " + "\n";

        }
        c1.close();
        return result1;

    }
	 public List<Comment> getAllComments() {
		    List<Comment> comments = new ArrayList<Comment>();
		    String[] columns = new String[] { DatabaseHelper.FNAME };
		    Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,columns, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Comment comment = cursorToComment(cursor);
		      comments.add(comment);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		    return comments;
		  }
	 private Comment cursorToComment(Cursor cursor) {
		    Comment comment = new Comment();
		    comment.setId(cursor.getLong(0));
		    comment.setComment(cursor.getString(1));
		    return comment;
		  }
		

	public int update(String fname, String lname,String email,long phone,String gender) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseHelper.FNAME, fname);
		contentValues.put(DatabaseHelper.LNAME, lname);
		contentValues.put(DatabaseHelper.EMAIL, email);
		contentValues.put(DatabaseHelper.PHONE, phone);
		contentValues.put(DatabaseHelper.GENDER, gender);
		int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.PHONE + " = " + phone, null);
		close();
		return i;
	}

	public void delete(long phone) {
		database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.PHONE + "=" + phone, null);
	}

}